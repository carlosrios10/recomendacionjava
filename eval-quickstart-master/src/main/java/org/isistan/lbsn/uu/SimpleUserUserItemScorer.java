package org.isistan.lbsn.uu;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.basic.AbstractItemScorer;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.ratings.Rating;
import org.lenskit.results.BasicResult;
import org.lenskit.results.BasicResultMap;
import org.lenskit.util.math.Vectors;

import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongSet;

/**
 * User-user item scorer.
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleUserUserItemScorer extends AbstractItemScorer {
    private final DataAccessObject dao;
    private final int neighborhoodSize;

    /**
     * Instantiate a new user-user item scorer.
     * @param dao The data access object.
     */
    @Inject
    public SimpleUserUserItemScorer(DataAccessObject dao) {
        this.dao = dao;
        neighborhoodSize = 30;
    }

    @Nonnull
    @Override
    public ResultMap scoreWithDetails(long user, @Nonnull Collection<Long> items) {
        double meanUserScore = Vectors.mean(getUserRatingVector(user));
        Collection<Result> resultados = new ArrayList<>();
        for (long item: items) {
            Collection<Vecino> vecinos  = getVecinos(user,item);
            double itemScoreNumerator = 0;
            double itemScoreDenominator = 0;
            double itemScore = 0;
            if(vecinos.size()>1){
                for (Vecino vecino :vecinos) {
                    Long2DoubleOpenHashMap ratings =  vecino.getRatingHistory();
                    double d = ratings.get(item);
                    double meanVecinoScore = Vectors.mean(ratings);
                    itemScoreNumerator = itemScoreNumerator + (vecino.getSimilarity()*(d-meanVecinoScore));
                    itemScoreDenominator = itemScoreDenominator + vecino.getSimilarity();

                }
                itemScore = meanUserScore  + (itemScoreNumerator/itemScoreDenominator);
                resultados.add(new BasicResult(item,itemScore));
            }

        }
        return  new BasicResultMap(resultados);
    }

    private Collection<Vecino> getVecinos(long theUser,long theItem){
        Long2DoubleOpenHashMap itemRatingTheUser = meanCentering(getUserRatingVector(theUser));
        double eucNorTheUser = Vectors.euclideanNorm(itemRatingTheUser);
        LongSet users = dao.getEntityIds(CommonTypes.USER);

        List<Vecino> vecinos = new ArrayList<Vecino>();
       // double lowestTopValue = Double.NEGATIVE_INFINITY;
        for (long user : users) {
             if( user != theUser){
                 Long2DoubleOpenHashMap itemRatingUser = getUserRatingVector(user);
                 if(itemRatingUser.containsKey(theItem)){
                     Long2DoubleOpenHashMap itemRatingUserCentering = meanCentering(itemRatingUser);
                     double dotProd = Vectors.dotProduct(itemRatingTheUser,itemRatingUserCentering);
                     double eucNorUser = Vectors.euclideanNorm(itemRatingUserCentering);
                     double sim = dotProd/(eucNorTheUser*eucNorUser);
                     if (sim>0) {
                         Vecino simUser = new Vecino(user, sim, itemRatingUser);
                         vecinos.add(simUser);
                     }
                 }
             }
        }

        Collections.sort(vecinos);
        if(vecinos.size()>30){
        vecinos = vecinos.subList(0,30);
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
