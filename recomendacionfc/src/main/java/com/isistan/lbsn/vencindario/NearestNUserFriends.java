package com.isistan.lbsn.vencindario;

import java.util.Collection;

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
/**
 * 
 * @author Usuario�
 *
 */
public final class NearestNUserFriends implements UserNeighborhood  {
	private final int n;
	private final double minSimilarity;
	private final UserSimilarity userSimilarity;
	private final DataModel dataModel;
	private final double samplingRate;
	private final GrafoModel friendsDm;

	private final RefreshHelper refreshHelper;


	public NearestNUserFriends(GrafoModel friendsDm,int n) {
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

	public NearestNUserFriends(int n, UserSimilarity userSimilarity, DataModel dataModel,GrafoModel friendsDm) throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, dataModel, 1.0,friendsDm);
	}

	public NearestNUserFriends(int n,double minSimilarity,UserSimilarity userSimilarity,
			DataModel dataModel,double samplingRate,GrafoModel friendsDm) throws TasteException {
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


	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		refreshHelper.refresh(alreadyRefreshed);

	}
	/**
	 * 	     //obtener la lista de amigos de userID
	     //si size(lista_amigos)==0,??? 
	     //si n > size(lista_amigos), return TopItems.getTopUsers(size(lista_amigos), userIDs, null, estimator);
	     //sino return TopItems.getTopUsers(n, lista_amigos, null, estimator);
	 */
	public long[] getUserNeighborhood(long userID) throws TasteException {
		UserSimilarity userSimilarityImpl = this.userSimilarity;
		TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity);
		Collection<Long> totalVecinos =  this.friendsDm.getFriends(userID);
		if(totalVecinos==null)
			return new long[0];
		
		long[] ids = Longs.toArray(totalVecinos);
		if( totalVecinos.size() < n )
			return ids; 
		else{
			LongPrimitiveIterator usersIDs = new  LongPrimitiveArrayIterator(ids);
			return TopItems.getTopUsers(n, usersIDs, null, estimator); 
		}

	}
	public double getSamplingRate() {
		return samplingRate;
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


}
