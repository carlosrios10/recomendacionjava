package org.isistan.lbsn.ii;

import java.util.Map;

import org.grouplens.grapht.annotation.DefaultProvider;
import org.lenskit.knn.item.model.ItemItemModel;
import org.lenskit.util.collections.LongUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongSortedSet;
@DefaultProvider(DistanciaItemItemModelProvider.class)
public class DistanciaItemItemModel implements  ItemItemModel{
    private static Logger logger = LoggerFactory.getLogger(DistanciaItemItemModel.class);

    private final Long2DoubleMap itemMeans;
    private final Map<Long,Long2DoubleMap> neighborhoods;
    private final LongSortedSet itemSet;
    public DistanciaItemItemModel(Long2DoubleMap means, Map<Long,Long2DoubleMap> nbrhoods, LongSortedSet items) {
        itemMeans = LongUtils.frozenMap(means);
        neighborhoods = nbrhoods;
        itemSet = items;
    }

    /**
     * Get the vector of item mean ratings.
     * @return The vector of item mean ratings.
     */
    public Long2DoubleMap getItemMeans() {
        return itemMeans;
    }

    /**
     * Get the neighbors of an item.
     * @return The neighbors of the item, sorted by decreasing score.
     */
    public Long2DoubleMap getNeighbors(long item) {
        Long2DoubleMap nbrs = neighborhoods.get(item);
        if (nbrs == null) {
            return new Long2DoubleOpenHashMap();
        } else {
            return nbrs;
        }
    }

	@Override
	public LongSortedSet getItemUniverse() {
		// TODO Auto-generated method stub
		return itemSet;
	}

}
