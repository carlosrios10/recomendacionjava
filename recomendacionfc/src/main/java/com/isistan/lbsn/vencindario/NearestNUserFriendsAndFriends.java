package com.isistan.lbsn.vencindario;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;

public class NearestNUserFriendsAndFriends implements UserNeighborhoodAux{
	private final int n;
	private final double minSimilarity;
	private final UserSimilarity userSimilarity;
	private final DataModel dataModel;
	private final double samplingRate;
	private final GrafoModel friendsDm;
	private final RefreshHelper refreshHelper;
	private  int nivel;
	//private Hashtable<Long, long[]> topUserCache;

	public NearestNUserFriendsAndFriends(int n, UserSimilarity userSimilarity, DataModel dataModel,GrafoModel friendsDm) 
			throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, dataModel, 1.0,friendsDm);
	}
	public NearestNUserFriendsAndFriends(int n, UserSimilarity userSimilarity, DataModel dataModel,GrafoModel friendsDm,int nivel) 
			throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, dataModel, 1.0,friendsDm);
		this.nivel = nivel;
	}
	public NearestNUserFriendsAndFriends(int n,double minSimilarity,UserSimilarity userSimilarity,DataModel dataModel,double samplingRate,GrafoModel friendsDm) 
			throws TasteException {
		this.userSimilarity = userSimilarity;
		this.dataModel = dataModel;
		this.samplingRate =  samplingRate;
		Preconditions.checkArgument(n >= 1, "n must be at least 1");
		int numUsers = dataModel.getNumUsers();
		this.n = n > numUsers ? numUsers : n;
		this.minSimilarity = minSimilarity;
		this.refreshHelper = new RefreshHelper(null);
		this.refreshHelper.addDependency(this.dataModel);
		this.refreshHelper.addDependency(this.userSimilarity);
		this.friendsDm =  friendsDm;
		//topUserCache = new Hashtable<Long, long[]>();

	}
	public NearestNUserFriendsAndFriends(GrafoDataModel friendsDm, int n) {
		this.userSimilarity = null;
		this.dataModel = null;
		this.samplingRate =  0;
		int numUsers = 0;
		this.minSimilarity = 0;
		this.refreshHelper = new RefreshHelper(null);
		this.refreshHelper.addDependency(this.dataModel);
		this.refreshHelper.addDependency(this.userSimilarity);
		this.n = n;
		this.friendsDm =  friendsDm;
	}
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		refreshHelper.refresh(alreadyRefreshed);		
	}
	public double getSamplingRate() {
		return samplingRate;
	}
	/**
	 * 
	 */
	public long[] getUserNeighborhood(long userID) throws TasteException {
		//if(!topUserCache.containsKey(userID)){
			
		UserSimilarity userSimilarityImpl = this.userSimilarity;
		TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity);
		Collection<Long> totalVecinos= this.friendsDm.getFriendsMyFriends(userID,nivel);
		if(totalVecinos==null){
		//	topUserCache.put(userID, new long[0]);
			return new long[0];
		}
		long[] topVecinos = null;
		long[] ids = 	Longs.toArray(totalVecinos);
		if( totalVecinos.size() < n )
			topVecinos =  ids; 
		else{
			LongPrimitiveIterator usersIDs = new  LongPrimitiveArrayIterator(ids);
			topVecinos = TopItems.getTopUsers(n, usersIDs, null, estimator);
		}
		//topUserCache.put(userID, topVecinos);
		return  topVecinos;
	
	//	}else{
	//		return topUserCache.get(userID);
	//	}
	}
	private static final class Estimator implements TopItems.Estimator<Long> {
		private final UserSimilarity userSimilarityImpl;
		private final long theUserID;
		private final double minSim;

		private Estimator(UserSimilarity userSimilarityImpl, long theUserID, double minSim) {
			this.userSimilarityImpl = userSimilarityImpl;
			this.theUserID = theUserID;
			this.minSim = minSim;
		}


		public double estimate(Long userID) throws TasteException {
			if (userID == theUserID) {
				return Double.NaN;
			}
			
			double sim = userSimilarityImpl.userSimilarity(theUserID, userID);
			return sim >= minSim ? sim : Double.NaN;
		}
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public long[] getUserNeighborhood(long userID, long itemID)
			throws TasteException {
		return this.getUserNeighborhood(userID);
	}
}
