package org.isistan.lbsn.metric;

import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.grouplens.lenskit.util.statistics.MeanAccumulator;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.eval.traintest.AlgorithmInstance;
import org.lenskit.eval.traintest.DataSet;
import org.lenskit.eval.traintest.TestUser;
import org.lenskit.eval.traintest.metrics.MetricColumn;
import org.lenskit.eval.traintest.metrics.MetricResult;
import org.lenskit.eval.traintest.metrics.TypedMetricResult;
import org.lenskit.eval.traintest.recommend.ListOnlyTopNMetric;

import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class TopNEntropyPOICategoryMetric extends ListOnlyTopNMetric<TopNEntropyPOICategoryMetric.Context> {
    /**
     * Construct a new length metric.
     */
    public TopNEntropyPOICategoryMetric() {
        super(EntropyCateResult.class, EntropyCateResult.class);
    }

    @Nonnull
    @Override
    public MetricResult measureUser(TestUser user, int targetLength, LongList recommendations, Context context) {
        if (recommendations == null || recommendations.isEmpty()) {
            return MetricResult.empty();
        }

         Object2IntMap<String> counts = new Object2IntOpenHashMap<String>();
         int recCount = 0;
        
        LongIterator iter = recommendations.iterator();
        while (iter.hasNext()) {
            long item = iter.nextLong();
            String cateStr = context.getCategorias(item);
            for(String c : cateStr.split(Pattern.quote("|"))){
                c = c.trim();
                if(!c.equalsIgnoreCase("Restaurants")) {
                	if(!counts.containsKey(c))
                		counts.put(c,0);
                	counts.put(c, counts.get(c)+1);
              		recCount +=1;
              	}
              }
        }
        

        
        double entropy = getEntropy(counts,recCount);
        context.mean.add(entropy);
        return new EntropyCateResult(entropy);
    }
    public double getEntropy(Object2IntMap<String> counts,int recCount) {
            double entropy = 0;
            for (Entry<String, Integer> e : counts.entrySet()) {
                double p = (double) e.getValue()/recCount;
                entropy -= p*Math.log(p)/Math.log(2);
            }
            return entropy;
    }
    
    @Nullable
    @Override
    public Context createContext(AlgorithmInstance algorithm, DataSet dataSet, org.lenskit.api.Recommender recommender) {
    	return new Context(dataSet.getTrainingData().get());
    }

    @Nonnull
    @Override
    public MetricResult getAggregateMeasurements(Context context) {
        return new EntropyCateResult(context.mean.getMean());
    }

    public static class EntropyCateResult extends TypedMetricResult {
        @MetricColumn("TopN.MeanEntropyCategoria")
        public final double entropy;
        public EntropyCateResult(double e) {
            entropy = e;
        }
    }

    public static class Context {
    	final MeanAccumulator mean = new MeanAccumulator();
        DataAccessObject dao;
       
        public Context(DataAccessObject dataAccessObjectD) {
            this.dao = dataAccessObjectD;
        }
         
        private String getCategorias(long item){
            Entity iEntity = dao.lookupEntity(CommonTypes.ITEM, item);
            String cateStr = iEntity.get("categoria").toString();
            return cateStr;
        }

    }
}
