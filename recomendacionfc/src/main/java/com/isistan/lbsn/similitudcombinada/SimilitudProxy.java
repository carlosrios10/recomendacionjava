package com.isistan.lbsn.similitudcombinada;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.isistan.lbsn.experimentos.datasetyelp.ExperimentosSeleccionVecinos;

public class SimilitudProxy implements UserSimilarity {
	private static final int CACHE_SIZE_MAX = 100000000;
	ConcurrentHashMap<Key, Double> userSimCache;
	Cache<Key, Double> cache;
	UserSimilarity userSimilarity;
	private static final Logger log = LoggerFactory.getLogger(SimilitudProxy.class);
	public SimilitudProxy() {

	}

	public SimilitudProxy(UserSimilarity userSimilarity) {
		this.userSimilarity = userSimilarity;
		//userSimCache = new ConcurrentHashMap<Key, Double>();
		 cache = CacheBuilder.newBuilder().concurrencyLevel(100).maximumSize(CACHE_SIZE_MAX).expireAfterAccess(20, TimeUnit.MINUTES).build();
		 
		 log.info("Cache maximum size 100000000");
		 log.info("expireAfterAccess 20 min");
		 log.info(cache.toString());
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		userSimilarity.refresh(alreadyRefreshed);
	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
//		if(cache.size()>=CACHE_SIZE_MAX){
//			return userSimilarity.userSimilarity(userID1, userID1);
//		}
		Key key = new Key(userID1, userID2);
	//	Double sim = userSimCache.get(key);
		try {
			  // If the key wasn't in the "easy to compute" group, we need to
			  // do things the hard way.
			return   cache.get(key, new Callable<Double>() {
			    public Double call() throws TasteException {
			    	return userSimilarity.userSimilarity(key.x, key.y);
			    }
			  });
			
			} catch (ExecutionException e) {
			  throw new TasteException(e.getCause());
			}
//		if (sim != null)
//			return sim;
//		return userSimCache.computeIfAbsent(key, (Key k) -> {
//			try {
//				return userSimilarity.userSimilarity(k.x, k.y);
//			} catch (TasteException e) {
//				e.printStackTrace();
//			}
//			return 0d;
//		});
		
		
		
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		userSimilarity.setPreferenceInferrer(inferrer);

	}

	public class Key {

		private final long x;
		private final long y;

		public Key(long x, long y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Key))
				return false;
			Key key = (Key) o;

			return ((x == key.x && y == key.y) || ((x == key.y && y == key.x)));
		}

		public int hashCode() {
			return (Long.hashCode(x) + Long.hashCode(y));

		}

	}
}
