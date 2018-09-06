package org.isistan.lbsn.uu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
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

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;


public class NeighborhoodFinderZonaKmeans implements NeighborFinder {
	private static final Logger logger = LoggerFactory.getLogger(NeighborhoodFinderZonaKmeans.class);
    private final DataAccessObject dao;
    private  int neighborhoodRadio;
    private final UserSimilarity similarity;
    private final RatingVectorPDAO rvDAO;
    private final UserVectorNormalizer normalizer;
    private final Threshold threshold;
    
    Map<Long,List<CentroidCluster<EntityItemWrapper>>> zonas = new HashMap<Long, List<CentroidCluster<EntityItemWrapper>>>();
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
    public NeighborhoodFinderZonaKmeans(RatingVectorPDAO rvd, DataAccessObject dao,UserSimilarity sim,UserVectorNormalizer norm,@UserSimilarityThreshold Threshold thresh) {
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
    	logger.info("Calculando Zonas K-means");
    	LongSet usersIds = dao.getEntityIds(CommonTypes.USER);
    	for (Long user : usersIds) {
    		Long2DoubleMap userRating = this.rvDAO.userRatingVector(user);
    		LongSet itemsIds = userRating.keySet();
	        int listItemsSize = itemsIds.size();
	        List<EntityItemWrapper> clusterInput  = new ArrayList<EntityItemWrapper>(listItemsSize);
	        for(Long item:itemsIds) {
	        	Entity enti =  dao.lookupEntity(CommonTypes.ITEM, item);
	        	double lat = Double.parseDouble(enti.get("latitude").toString());
			    double lon = Double.parseDouble(enti.get("longitude").toString());
			    double[] points = {lat,lon};
	        	clusterInput.add(new EntityItemWrapper(enti));
	        	
	        }
	        
			KMeansPlusPlusClusterer<EntityItemWrapper> clusterer = new KMeansPlusPlusClusterer<EntityItemWrapper>(3, 1000);
			List<CentroidCluster<EntityItemWrapper>> clusterResults = clusterer.cluster(clusterInput);
			zonas.put(user, clusterResults);
			
		}
    	logger.info("Se calcularon K-means zonas para {} usuarios ",zonas.size());
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
		
		List<CentroidCluster<EntityItemWrapper>> zonasTheUser = zonas.get(user);
		LongSet vecinos = dao.getEntityIds(CommonTypes.USER);
		LongSet vecinosZona = new LongArraySet();
		
		for (Long vec : vecinos) {
			if(vec!=user){
				List<CentroidCluster<EntityItemWrapper>> zonasTheVec = zonas.get(vec);
				outerloop:
				for (CentroidCluster<EntityItemWrapper> centroidClusterVecino : zonasTheVec) {
					for (CentroidCluster<EntityItemWrapper> centroidClusterTheUser : zonasTheUser) {
						
					   double distanciaKms = Util.distFrom(centroidClusterVecino.getCenter().getPoint()[0], 
							   			centroidClusterVecino.getCenter().getPoint()[1], 
							   			centroidClusterTheUser.getCenter().getPoint()[0], 
							   			centroidClusterTheUser.getCenter().getPoint()[1]);
						
					   if( distanciaKms <= this.neighborhoodRadio){
							vecinosZona.add(vec);
							break outerloop;
						}

					}
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


