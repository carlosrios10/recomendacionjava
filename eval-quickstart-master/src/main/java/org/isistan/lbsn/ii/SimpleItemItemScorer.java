package org.isistan.lbsn.ii;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.basic.AbstractItemScorer;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.ratings.Rating;
import org.lenskit.knn.NeighborhoodSize;
import org.lenskit.results.BasicResult;
import org.lenskit.results.Results;
import org.lenskit.util.ScoredIdAccumulator;
import org.lenskit.util.TopNScoredIdAccumulator;
import org.lenskit.util.math.Vectors;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleItemItemScorer extends AbstractItemScorer {
    private final SimpleItemItemModel model;
    private final DataAccessObject dao;
    private final int neighborhoodSize;

    @Inject
    public SimpleItemItemScorer(SimpleItemItemModel m, DataAccessObject dao,  @NeighborhoodSize int nnbrs) {
        model = m;
        this.dao = dao;
        neighborhoodSize = nnbrs;
    }

    /**
     * Score items for a user.
     * @param user The user ID.
     * @param items The score vector.  Its key domain is the items to score, and the scores
     *               (rating predictions) should be written back to this vector.
     */
    @Override
    public ResultMap scoreWithDetails(long user, @Nonnull Collection<Long> items) {
        Long2DoubleMap itemMeans = model.getItemMeans();
        Long2DoubleMap ratings = getUserRatingVector(user);
        Long2DoubleMap ratingCentering = meanCentering(ratings,itemMeans);

        List<Result> results = new ArrayList<>();
        for (long item: items ) {
            Long2DoubleMap potencialesVecinos =  filtrarVecinos(item,ratingCentering);
            double dotProd = Vectors.dotProduct(potencialesVecinos,ratingCentering);
            double denominador = Vectors.sum(potencialesVecinos);
            double itemRatingMean = itemMeans.get(item);
            double score = itemRatingMean +(dotProd/denominador);
            Result result =  new BasicResult(item,score);
            results.add(result);
        }

        return Results.newResultMap(results);

    }
    private Long2DoubleMap filtrarVecinos(long item,Long2DoubleMap userRatings){
        Long2DoubleMap potencialesVecinos =  model.getNeighbors(item);
        //potencialesVecinos.keySet().retainAll(userRatings.keySet());
        potencialesVecinos = getSimilitudes(userRatings.keySet(),  potencialesVecinos);

        if( potencialesVecinos.size() <= neighborhoodSize)
            return potencialesVecinos;

        Map<Long, Double>  sortedMap2 =
                potencialesVecinos.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .limit(neighborhoodSize)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e2, LinkedHashMap::new));

        return  new Long2DoubleOpenHashMap(sortedMap2);
    }

    private Long2DoubleOpenHashMap getSimilitudes(Collection<Long> basket, Long2DoubleMap potencialesVecinos) {
        Long2DoubleOpenHashMap simBasket = new Long2DoubleOpenHashMap();
        for (Long itemBasket:basket) {
            if (potencialesVecinos.containsKey(itemBasket))
                simBasket.put(itemBasket.longValue(),potencialesVecinos.get(itemBasket).doubleValue());

        }
        return simBasket;
    }
    /**
     * Get a user's ratings.
     * @param user The user ID.
     * @return The ratings to retrieve.
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

    private Long2DoubleMap meanCentering(Long2DoubleMap v,Long2DoubleMap itemMeans){
        Long2DoubleOpenHashMap ratings = new Long2DoubleOpenHashMap();
        for(long item :v.keySet()){
            ratings.put(item,v.get(item)-itemMeans.get(item));

        }
        return ratings;
    }


}
