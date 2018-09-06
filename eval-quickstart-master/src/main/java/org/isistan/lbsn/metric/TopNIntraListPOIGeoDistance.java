package org.isistan.lbsn.metric;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.grouplens.lenskit.util.statistics.MeanAccumulator;
import org.isistan.lbsn.util.Util;
import org.lenskit.api.Recommender;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.data.ratings.RatingSummary;
import org.lenskit.eval.traintest.AlgorithmInstance;
import org.lenskit.eval.traintest.DataSet;
import org.lenskit.eval.traintest.TestUser;
import org.lenskit.eval.traintest.metrics.MetricColumn;
import org.lenskit.eval.traintest.metrics.MetricResult;
import org.lenskit.eval.traintest.metrics.TypedMetricResult;
import org.lenskit.eval.traintest.recommend.ListOnlyTopNMetric;

import it.unimi.dsi.fastutil.longs.LongList;

public class TopNIntraListPOIGeoDistance extends ListOnlyTopNMetric<TopNIntraListPOIGeoDistance.Context> {

    public TopNIntraListPOIGeoDistance() {
        super(IntraListGeoResult.class, IntraListGeoResult.class);
    }
    @SuppressWarnings("unchecked")
    @Override
    public Set<Class<?>> getRequiredRoots() {
        return (Set) Collections.singleton(RatingSummary.class);
    }

    @Nullable
    @Override
    public Context createContext(AlgorithmInstance algorithm, DataSet dataSet, Recommender recommender) {
        return new Context(dataSet.getTrainingData().get());
    }
    

	@Override
	public MetricResult measureUser(TestUser user, int targetLength, LongList recommendations, Context context) {
        if (recommendations == null || recommendations.isEmpty()) {
            return MetricResult.empty();
        }
        double dist = 0;
        int cant = 0;
        for (int i = 0; i < (recommendations.size()-1); i++) {
            for (int j = i+1; j < recommendations.size(); j++) {
            	Long item1 = recommendations.get(i);
            	Long item2 = recommendations.get(j);
            	dist += context.getItemDistancia(item1,item2);
            	cant += 1;
    		}
            
		}
        
        
        dist = dist / cant;
        context.mean.add(dist);
        return new IntraListGeoResult(dist);
	}
	
    @Nonnull
    @Override
    public MetricResult getAggregateMeasurements(Context context) {
        return new IntraListGeoResult(context.mean.getMean());
    }

    public static class IntraListGeoResult extends TypedMetricResult {
        @MetricColumn("TopN.MeanIntraListPoiGeoDistance")
        public final double mean;

        public IntraListGeoResult(double mu) {
            mean = mu;
        }
    }

    public class Context {
        final MeanAccumulator mean = new MeanAccumulator();
        DataAccessObject dao;
        public Context(DataAccessObject dataAccessObjectD) {
            this.dao = dataAccessObjectD;
        }
        
    	public double getItemDistancia(long theItemId,long theVecinoId) {
    		Entity theItemE = dao.lookupEntity(CommonTypes.ITEM, theItemId);
    		Entity theVecinoE = dao.lookupEntity(CommonTypes.ITEM, theVecinoId);
    		double distanciaKms = Util.distFrom(Double.valueOf(theItemE.get("latitude").toString()), 
    				Double.valueOf(theItemE.get("longitude").toString()), 
    				Double.valueOf(theVecinoE.get("latitude").toString()), 
    				Double.valueOf(theVecinoE.get("longitude").toString()));
    		

    		return distanciaKms;
    	}
    }
    
    

}
