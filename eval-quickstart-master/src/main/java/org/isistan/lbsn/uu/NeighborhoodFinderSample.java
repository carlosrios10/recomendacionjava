package org.isistan.lbsn.uu;

import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.grouplens.lenskit.vectors.ImmutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.ratings.Rating;
import org.lenskit.knn.user.Neighbor;
import org.lenskit.knn.user.NeighborFinder;
import org.lenskit.util.math.Vectors;

public class NeighborhoodFinderSample  implements NeighborFinder{
	
    private final DataAccessObject dao;
    private final int neighborhoodSize;
    
    @Inject
    public NeighborhoodFinderSample(DataAccessObject dao) {
        this.dao = dao;
        neighborhoodSize = 30;
    }

	public Iterable<Neighbor> getCandidateNeighbors(long user, LongSet items) {
		//System.out.println(user);
		Long2DoubleOpenHashMap itemRatingTheUser = meanCentering(getUserRatingVector(user));
		double eucNorTheUser = Vectors.euclideanNorm(itemRatingTheUser);
		 LongSet users = dao.getEntityIds(CommonTypes.USER);	
		 List<Neighbor> vecinos = new ArrayList<Neighbor>();
		 
		 
	       for (long userV : users) {
	             if( userV != user){
	                 Long2DoubleOpenHashMap itemRatingUser = getUserRatingVector(userV);
	                 Long2DoubleOpenHashMap itemRatingUserCentering = meanCentering(itemRatingUser);
	                     double dotProd = Vectors.dotProduct(itemRatingTheUser,itemRatingUserCentering);
	                     double eucNorUser = Vectors.euclideanNorm(itemRatingUserCentering);
	                     double sim = dotProd/(eucNorTheUser*eucNorUser);
	                     SparseVector sv = ImmutableSparseVector.create(itemRatingUser);
	                     if (sim>0) {
	                    	 Neighbor simUser = new Neighbor(userV, sv, sim);
	                         vecinos.add(simUser);
	                     }
	             }
	        }
	       
	        Collections.sort(vecinos,Neighbor.SIMILARITY_COMPARATOR);
	        if(vecinos.size()>neighborhoodSize){
	        vecinos = vecinos.subList(0,neighborhoodSize);
	        }
	        return vecinos;
		 
	}
	
 
    private Long2DoubleOpenHashMap meanCentering(Long2DoubleOpenHashMap v){
        double mean = Vectors.mean(v);
        for(long item :v.keySet()){
            v.put(item,v.get(item)-mean);

        }
        return v;
    }
    
    /**
     * Get a user's rating vector.
     * @param user The user ID.
     * @return The rating vector, mapping item IDs to the user's rating
     *         for that item.
     */
    private Long2DoubleOpenHashMap getUserRatingVector(long user) {
        List<Rating> history = dao.query(Rating.class)
                                  .withAttribute(CommonAttributes.USER_ID, user)
                                  .get();

        Long2DoubleOpenHashMap ratings = new Long2DoubleOpenHashMap();
        for (Rating r: history) {
            ratings.put(r.getItemId(), r.getValue());
        }

        return ratings;
    }
}
