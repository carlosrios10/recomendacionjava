package org.isistan.lbsn.ii;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import org.apache.commons.lang3.tuple.Pair;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.ratings.Rating;
import org.lenskit.data.ratings.Ratings;
import org.lenskit.inject.Transient;
import org.lenskit.util.IdBox;
import org.lenskit.util.collections.LongUtils;
import org.lenskit.util.io.ObjectStream;
import org.lenskit.util.math.Vectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleItemItemModelProvider implements Provider<SimpleItemItemModel> {
    private static final Logger logger = LoggerFactory.getLogger(SimpleItemItemModelProvider.class);

    private final DataAccessObject dao;

    /**
     * Construct the model provider.
     * @param dao The data access object.
     */
    @Inject
    public SimpleItemItemModelProvider(@Transient DataAccessObject dao) {
        this.dao = dao;
    }

    /**
     * Construct the item-item model.
     * @return The item-item model.
     */
    @Override
    public SimpleItemItemModel get() {
        Map<Long,Long2DoubleMap> itemVectors = Maps.newHashMap();
        Long2DoubleMap itemMeans = new Long2DoubleOpenHashMap();

        try (ObjectStream<IdBox<List<Rating>>> stream = dao.query(Rating.class)
                                                           .groupBy(CommonAttributes.ITEM_ID)
                                                           .stream()) {
            for (IdBox<List<Rating>> item : stream) {
                long itemId = item.getId();
                List<Rating> itemRatings = item.getValue();
                Long2DoubleOpenHashMap ratings = new Long2DoubleOpenHashMap(Ratings.itemRatingVector(itemRatings));

                // Compute and store the item's mean.
                double mean = Vectors.mean(ratings);
                itemMeans.put(itemId, mean);

                // Mean center the ratings.
                for (Map.Entry<Long, Double> entry : ratings.entrySet()) {
                    entry.setValue(entry.getValue() - mean);
                }

                itemVectors.put(itemId, LongUtils.frozenMap(ratings));
            }
        }

        // Map items to vectors (maps) of item similarities.
        Map<Long,Long2DoubleMap> itemSimilarities = Maps.newHashMap();

        // TODO Compute the similarities between each pair of items
        // Ignore nonpositive similarities
        for (Map.Entry<Long, Long2DoubleMap> theItem : itemVectors.entrySet()) {
            Long2DoubleMap vecinos = new Long2DoubleOpenHashMap();
            long theItemId =  theItem.getKey();
            double theItemEucNorm = Vectors.euclideanNorm(theItem.getValue());
            for (Map.Entry<Long, Long2DoubleMap> theVecino : itemVectors.entrySet()) {
                long theVecinoId = theVecino.getKey();
                if (theItemId != theVecinoId){
                    double dotProd = Vectors.dotProduct(theItem.getValue(),theVecino.getValue());
                    double theVecinoEucNorm = Vectors.euclideanNorm(theVecino.getValue());
                    double sim = dotProd/(theItemEucNorm*theVecinoEucNorm);
                    if (sim>0) {
                        vecinos.put(theVecinoId,sim);
                    }
                }

            }
            itemSimilarities.put(theItemId,vecinos);
        }

        return new SimpleItemItemModel(LongUtils.frozenMap(itemMeans), itemSimilarities);
    }
}
