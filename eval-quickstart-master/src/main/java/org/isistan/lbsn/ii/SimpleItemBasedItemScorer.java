package org.isistan.lbsn.ii;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.basic.AbstractItemBasedItemScorer;
import org.lenskit.results.BasicResult;
import org.lenskit.results.Results;
import org.lenskit.util.math.Vectors;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Global item scorer to find similar items.
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */

public class SimpleItemBasedItemScorer extends AbstractItemBasedItemScorer {
    private final SimpleItemItemModel model;

    @Inject
    public SimpleItemBasedItemScorer(SimpleItemItemModel mod) {
        model = mod;
    }

    /**
     * Score items with respect to a set of reference items.
     * @param basket The reference items.
     * @param items The score vector. Its domain is the items to be scored, and the scores should
     *               be stored into this vector.
     */
    @Override
    public ResultMap scoreRelatedItemsWithDetails(@Nonnull Collection<Long> basket, Collection<Long> items) {
        List<Result> results = new ArrayList<>();

        // TODO Score the items and put them in results
        for (Long itemId : items) {
            Long2DoubleMap potencialesVecinos = model.getNeighbors(itemId);
            Long2DoubleOpenHashMap simBasket = getVecinos(basket, potencialesVecinos);
            //potencialesVecinos.keySet().retainAll(basket);
            //double score = Vectors.sum(potencialesVecinos);
           if (!simBasket.isEmpty()){
               double score = Vectors.sum(simBasket);
               Result result =  new BasicResult(itemId,score);
               results.add(result);

           }
        }
        return Results.newResultMap(results);
    }

    private Long2DoubleOpenHashMap getVecinos(@Nonnull Collection<Long> basket, Long2DoubleMap potencialesVecinos) {
        Long2DoubleOpenHashMap simBasket = new Long2DoubleOpenHashMap();
        for (Long itemBasket:basket) {
            if (potencialesVecinos.containsKey(itemBasket))
                simBasket.put(itemBasket.longValue(),potencialesVecinos.get(itemBasket).doubleValue());

        }
        return simBasket;
    }
}
