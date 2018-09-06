package org.isistan.lbsn.uu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.grouplens.lenskit.transform.threshold.Threshold;
import org.grouplens.lenskit.vectors.ImmutableSparseVector;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.data.ratings.Rating;
import org.lenskit.data.ratings.RatingVectorPDAO;
import org.lenskit.knn.user.Neighbor;
import org.lenskit.knn.user.NeighborFinder;
import org.lenskit.knn.user.UserSimilarity;
import org.lenskit.knn.user.UserSimilarityThreshold;
import org.lenskit.transform.normalize.UserVectorNormalizer;
import org.lenskit.util.math.Vectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;

public class NeighborhoodFinderGrafoCosenoNet implements NeighborFinder {

	private static final Logger logger = LoggerFactory.getLogger(NeighborhoodFinderGrafoCosenoNet.class);
    private final DataAccessObject dao;
    private final UserSimilarity similarity;
    private final RatingVectorPDAO rvDAO;
    private final UserVectorNormalizer normalizer;
    private final Threshold threshold;
    
    Map<Long,LongSet> friendsLevel2 = new HashMap<Long, LongSet>();
    int radioZona = 2;
    /**
     * Construct a new user neighborhood finder.
     *
     * @param rvd    The user rating vector dAO.
     * @param dao    The data access object.
     * @param sim    The similarity function to use.
     * @param norm   The normalizer for user rating/preference vectors.
     * @param thresh The threshold for user similarities.
     */
    @Inject
    public NeighborhoodFinderGrafoCosenoNet(RatingVectorPDAO rvd, DataAccessObject dao,UserSimilarity sim,UserVectorNormalizer norm,@UserSimilarityThreshold Threshold thresh,@DistBetweenZonas double radioNeig) {
        similarity = sim;
        normalizer = norm;
        rvDAO = rvd;
        this.dao = dao;
        threshold = thresh;
        calcularNeigborCercanos();
        Preconditions.checkArgument(sim.isSparse(), "user similarity function is not sparse");
    }
    private void calcularNeigborCercanos() {
    	logger.info("Calcular Neigbor Cercanos Grafo");
    	LongSet usersIds = dao.getEntityIds(CommonTypes.USER);
    	for (Long user : usersIds) {
    		LongSet vecinosDistancia = getVecinosGrafo(user);
    		friendsLevel2.put(user, vecinosDistancia);
    	}
    	logger.info("Fin Calcular Neigbor Cercanos Grafo");
    }
    
 
	public Iterable<Neighbor> getCandidateNeighbors(long user, LongSet items) {
	       Long2DoubleMap ratings = rvDAO.userRatingVector(user);
	        if (ratings.isEmpty()) {
	            return Collections.emptyList();
	        }
	        
	        SparseVector urs = ImmutableSparseVector.create(ratings);
	        final ImmutableSparseVector nratings = normalizer.normalize(user, urs, null)
	                                                   .freeze();
	        final LongSet candidates = findCandidateNeighbors(user, nratings, items);
	        logger.debug("found {} candidate neighbors for {}", candidates.size(), user);
	        return new Iterable<Neighbor>() {
	            @Override
	            public Iterator<Neighbor> iterator() {
	                return new NeighborIterator(user, nratings, candidates);
	            }
	        };
		
	}
    /**
     * Get the IDs of the candidate neighbors for a user.
     * @param user The user.
     * @param uvec The user's normalized preference vector.
     * @param itemSet The set of target items.
     * @return The set of IDs of candidate neighbors.
     */
    private LongSet findCandidateNeighbors(long user, SparseVector uvec, LongCollection itemSet) {
        LongSet users = new LongOpenHashSet(100);
        LongSet userItems = uvec.keySet();

        LongIterator items;
        if (userItems.size() < itemSet.size()) {
            items = userItems.iterator();
        } else {
            items = itemSet.iterator();
        }
        while (items.hasNext()) {
            LongSet iusers = dao.query(CommonTypes.RATING)
                    .withAttribute(CommonAttributes.ITEM_ID, items.nextLong())
                    .valueSet(CommonAttributes.USER_ID);
            if (iusers != null) {
                users.addAll(iusers);
            }
        }
        users.remove(user);

        return users;
    }

	private LongSet getVecinosGrafo(long user){
		LongSet vecinosZona = new LongArraySet();
		Entity userEntity = dao.lookupEntity(CommonTypes.USER, user);
		String strFriends = userEntity.get("amigosN2").toString();
		if (!" ".equals(strFriends)) {
		for(String friend : strFriends.split(Pattern.quote(","))){
			vecinosZona.add(Long.parseLong(friend));
		  }
		}
		return vecinosZona;
	}

	

    private Long2DoubleOpenHashMap meanCentering(Long2DoubleOpenHashMap v){
        double mean = Vectors.mean(v);
        for(long item :v.keySet()){
            v.put(item,v.get(item)-mean);

        }
        return v;
    }
    private Long2DoubleOpenHashMap getUserRatingVector2(long user) {
        List<Rating> history = dao.query(Rating.class)
                                  .withAttribute(CommonAttributes.USER_ID, user)
                                  .get();

        Long2DoubleOpenHashMap ratings = new Long2DoubleOpenHashMap();
        for (Rating r: history) {
            ratings.put(r.getItemId(), r.getValue());
        }

        return ratings;
    }
    
    private boolean acceptSimilarity(double sim) {
        return !Double.isNaN(sim) && !Double.isInfinite(sim) && threshold.retain(sim);
    }

    private MutableSparseVector getUserRatingVector(long user) {
        Long2DoubleMap ratings = rvDAO.userRatingVector(user);
        if (ratings.isEmpty()) {
            return null;
        } else {
            return MutableSparseVector.create(ratings);
        }
    }
    
    private double calcularCosenoNet(long user,long neighbor) {
    	LongSet userFriends = friendsLevel2.get(user);
    	LongSet neighFriends = friendsLevel2.get(neighbor);
    	int lenUserFriends = userFriends.size();
    	int lenNeighFriends = neighFriends.size();
	    
    	if(lenUserFriends == 0 && lenNeighFriends == 0) {
		      return 0.0;
		    }
		
	    if (lenNeighFriends == 0 || lenNeighFriends == 0) {
		      return 0.0;
		    }
    	
	    userFriends.retainAll(neighFriends);
	    final int intersection = userFriends.size();
	    return 1d / (lenUserFriends + lenNeighFriends - intersection) * intersection;
    	
    }

	private class NeighborIterator extends AbstractIterator<Neighbor> {
        private final long user;
        private final SparseVector userVector;
        private final LongIterator neighborIter;

        public NeighborIterator(long uid, SparseVector uvec, LongSet nbrs) {
            user = uid;
            userVector = uvec;
            neighborIter = nbrs.iterator();
        }
        
        @Override
        protected Neighbor computeNext() {
            while (neighborIter.hasNext()) {
                final long neighbor = neighborIter.nextLong();
                MutableSparseVector nbrRatings = getUserRatingVector(neighbor);
                if (nbrRatings != null) {
                    ImmutableSparseVector rawRatings = nbrRatings.immutable();
                    normalizer.normalize(neighbor, rawRatings, nbrRatings);
                    final double sim = similarity.similarity(user, userVector, neighbor, nbrRatings);
                    final double simJaccNet = calcularCosenoNet(user,neighbor);
                    
                    if (acceptSimilarity(sim)) {
                        // we have found a neighbor
                        return new Neighbor(neighbor, rawRatings, simJaccNet+sim);
                    }
                }
            }
            // no neighbor found, done
            return endOfData();
        }
    }


}
