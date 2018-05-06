package org.isistan.lbsn.cbf;

import java.util.Map;

public interface CBFModel {

    /**
     * Get the normalized tag vector for a particular item.
     *
     * @param item The item.
     * @return The item's tag vector.  If the item is not known to the model, then this vector is
     *         empty.
     */
	 public Map<String, Double> getItemVector(long item);

}
