package org.isistan.lbsn.cbf;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import org.grouplens.grapht.annotation.DefaultProvider;
import org.lenskit.inject.Shareable;

import com.google.common.collect.ImmutableMap;
//LensKit models are annotated with @Shareable so they can be serialized and reused
@Shareable
//This model class will be built by the model builder
@DefaultProvider(ATTModelProvider.class)
public class ATTModel implements Serializable, CBFModel  {
	private static final long serialVersionUID = -4188044357105138412L;
	
    private final Map<Long, Map<String, Double>> itemVectors;

    /**
     * Constructor for the model.  This is package-private; the only way to build a model is with
     * the {@linkplain TFIDFModelProvider model builder}.
     *
     * @param itemVectors A map of item IDs to tag vectors.
     */
    ATTModel(Map<Long, Map<String, Double>> itemVectors) {
        ImmutableMap.Builder<Long,Map<String,Double>> bld = ImmutableMap.builder();
        for (Map.Entry<Long,Map<String,Double>> e: itemVectors.entrySet()) {
            bld.put(e.getKey(), ImmutableMap.copyOf(e.getValue()));
        }
        this.itemVectors = bld.build();
    }

	@Override
	public Map<String, Double> getItemVector(long item) {
        // Look up the item
        Map<String, Double> vec = itemVectors.get(item);
        if (vec == null) {
            // We don't know the item! Return an empty vector
            return Collections.emptyMap();
        } else {
            return vec;
        }
    }

}
