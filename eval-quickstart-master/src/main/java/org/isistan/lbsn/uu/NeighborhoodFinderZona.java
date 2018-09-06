package org.isistan.lbsn.uu;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.grouplens.lenskit.transform.threshold.Threshold;
import org.grouplens.lenskit.vectors.ImmutableSparseVector;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;
import org.isistan.lbsn.util.Util;
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


public class NeighborhoodFinderZona implements NeighborFinder {
	private static final Logger logger = LoggerFactory.getLogger(NeighborhoodFinderZona.class);
    private final DataAccessObject dao;
    private  int neighborhoodRadio;
    private final UserSimilarity similarity;
    private final RatingVectorPDAO rvDAO;
    private final UserVectorNormalizer normalizer;
    private final Threshold threshold;
    
    Map<Long,Entity> zonas = new HashMap<Long, Entity>();
    Map<Long,LongSet> neigborsCercanos = new HashMap<Long, LongSet>();
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
    public NeighborhoodFinderZona(RatingVectorPDAO rvd, DataAccessObject dao,UserSimilarity sim,UserVectorNormalizer norm,@UserSimilarityThreshold Threshold thresh) {
        similarity = sim;
        normalizer = norm;
        rvDAO = rvd;
        this.dao = dao;
        threshold = thresh;
        neighborhoodRadio = 3;
        calcularZonas();
        calcularNeigborCercanos();
        Preconditions.checkArgument(sim.isSparse(), "user similarity function is not sparse");
    }
    private boolean isInRadio(Entity eItemCentro ,Entity eItem){
		double distanciaKms = Util.distFrom(Double.valueOf(eItemCentro.get("latitude").toString()), 
				Double.valueOf(eItemCentro.get("longitude").toString()), 
				Double.valueOf(eItem.get("latitude").toString()), 
				Double.valueOf(eItem.get("longitude").toString()));
		if(distanciaKms<=radioZona)
			return true;
		else 
			return false;
    }
    private void calcularNeigborCercanos() {
    	logger.info("Calcular Neigbor Cercanos");
    	LongSet usersIds = dao.getEntityIds(CommonTypes.USER);
    	for (Long user : usersIds) {
    		LongSet vecinosDistancia = getVecinosRadio(user);
    		neigborsCercanos.put(user, vecinosDistancia);
    	}
    	logger.info("Fin Calcular Neigbor Cercanos");
    	zonas = null;
    }
    private void calcularZonas(){
    	logger.info("Calculando Zonas");
    	
    	LongSet usersIds = dao.getEntityIds(CommonTypes.USER);
    	for (Long user : usersIds) {
    		Long2DoubleMap userRating = this.rvDAO.userRatingVector(user);
    		LongSet itemsIds = userRating.keySet();
    		int maxAcumular = 0;
    		long itemSeleccionado = 0;
    		for (Long itemCentro : itemsIds) {
    			Entity eItemCentro = dao.lookupEntity(CommonTypes.ITEM, itemCentro);
    			int acumular = 0;
    			for (Long item : itemsIds) {
	    			Entity eItem = dao.lookupEntity(CommonTypes.ITEM, item);	
	    				if(isInRadio(eItemCentro,eItem))
	    					acumular++;
					}
    			
    			if(acumular>=maxAcumular) {
    				maxAcumular = acumular;
    				itemSeleccionado = itemCentro;
    			}
				
			}
    		logger.debug("Usuario {} item {} cantidad {} ",user,itemSeleccionado,maxAcumular);
    		zonas.put(user,dao.lookupEntity(CommonTypes.ITEM, itemSeleccionado));
			
		}
    	logger.info("Se calcularon zonas para {} usuarios ",zonas.size());
    }
	public Iterable<Neighbor> getCandidateNeighbors(long user, LongSet items) {
	       Long2DoubleMap ratings = rvDAO.userRatingVector(user);
	        if (ratings.isEmpty()) {
	            return Collections.emptyList();
	        }
	        
	       // logger.debug("El item {} es la zona para el usuario {}", zonas.get(user).getId(),user);

	        SparseVector urs = ImmutableSparseVector.create(ratings);
	        final ImmutableSparseVector nratings = normalizer.normalize(user, urs, null)
	                                                   .freeze();
	        //final LongSet candidates = findCandidateNeighbors(user, nratings, items);
	        final LongSet candidates = neigborsCercanos.get(user);
	       //final LongSet candidates = getVecinosRadio(user);
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
	private LongSet getVecinosRadio(long user){
		Entity userE = zonas.get(user);
		LongSet vecinos = dao.getEntityIds(CommonTypes.USER);
		LongSet vecinosZona = new LongArraySet();
		for (Long vec : vecinos) {
			if(vec!=user){
				Entity userV = zonas.get(vec);
				double distanciaKms = Util.distFrom(Double.valueOf(userE.get("latitude").toString()), 
						Double.valueOf(userE.get("longitude").toString()), 
						Double.valueOf(userV.get("latitude").toString()), 
						Double.valueOf(userV.get("longitude").toString()));
				
				if( distanciaKms <= getNeighborhoodRadio() ){
					vecinosZona.add(vec);
				}
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
                    if (acceptSimilarity(sim)) {
                        // we have found a neighbor
                        return new Neighbor(neighbor, rawRatings, sim);
                    }
                }
            }
            // no neighbor found, done
            return endOfData();
        }
    }



	public int getNeighborhoodRadio() {
		return neighborhoodRadio;
	}
	public void setNeighborhoodRadio(int neighborhoodRadio) {
		this.neighborhoodRadio = neighborhoodRadio;
	}

}
