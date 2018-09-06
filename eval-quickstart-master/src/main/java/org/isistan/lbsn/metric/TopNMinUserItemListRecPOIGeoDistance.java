package org.isistan.lbsn.metric;

import java.util.Collections;
import java.util.Iterator;
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

import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.longs.LongSet;

public class TopNMinUserItemListRecPOIGeoDistance extends ListOnlyTopNMetric<TopNMinUserItemListRecPOIGeoDistance.Context> {
	
	   public TopNMinUserItemListRecPOIGeoDistance() {
	        super(MinUIGeoResult.class, MinUIGeoResult.class);
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
	    
	    private double getMinDistance(LongSet userTrainItems, Long itemRec,Context context) {
	    	  LongIterator it = userTrainItems.iterator();
	    	  double minDist = Double.POSITIVE_INFINITY;
	    	  while (it.hasNext()) {
				Long itemUser = (Long) it.next();
				double dist = context.getItemDistancia(itemRec,itemUser);
				if(dist<minDist)
					minDist = dist;
			}
	    	return minDist;
	    }
		@Override
		public MetricResult measureUser(TestUser user, int targetLength, LongList recommendations, Context context) {
	        if (recommendations == null || recommendations.isEmpty()) {
	            return MetricResult.empty();
	        }
	        LongSet userTrainItems = user.getTrainItems();
	        LongIterator iter = recommendations.iterator();
	        double dist = 0;
	        while (iter.hasNext()) {
	        	dist += getMinDistance(userTrainItems,iter.nextLong(),context);
	         }

	        dist = dist / recommendations.size();
	        context.mean.add(dist);
	        return new MinUIGeoResult(dist);
		}
		
	    @Nonnull
	    @Override
	    public MetricResult getAggregateMeasurements(Context context) {
	        return new MinUIGeoResult(context.mean.getMean());
	    }

	    public static class MinUIGeoResult extends TypedMetricResult {
	        @MetricColumn("TopN.MeanMinPoiGeoDistance")
	        public final double mean;

	        public MinUIGeoResult(double mu) {
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
