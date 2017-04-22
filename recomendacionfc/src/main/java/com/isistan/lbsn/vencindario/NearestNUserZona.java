package com.isistan.lbsn.vencindario;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.User;

public class NearestNUserZona implements UserNeighborhoodAux {
	private final int n;
	private final double minSimilarity;
	private final double samplingRate;
	private final RefreshHelper refreshHelper;
	private final UserModel userModel;
	private final UserSimilarity userSimilarity;
	private int radio;
	public NearestNUserZona(int n, UserSimilarity userSimilarity,UserModel userModel) throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, userModel, 1.0,1);
	}
	public NearestNUserZona(int n, UserSimilarity userSimilarity,UserModel userModel,int radio) throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, userModel, 1.0,radio);
	}
	public NearestNUserZona(int n,double minSimilarity,UserSimilarity userSimilarity,UserModel userModel, double samplingRate,int radio) throws TasteException {
		this.userSimilarity = userSimilarity;
		this.samplingRate =  samplingRate;
		this.userModel = userModel;
		this.radio = radio;
		Preconditions.checkArgument(n >= 1, "n must be at least 1");
		int numUsers = userModel.getMultiMap().size();
		this.n = n > numUsers ? numUsers : n;
		this.minSimilarity = minSimilarity;
		this.refreshHelper = new RefreshHelper(null);
		this.refreshHelper.addDependency(this.userSimilarity);

	}
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub

	}

	public long[] getUserNeighborhood(long userID) throws TasteException {
		UserSimilarity userSimilarityImpl = this.userSimilarity;
		TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity);
		User user =  userModel.getUser(userID);
		Collection<Long> totalVecinos =  this.userModel.getUserZona(user);
		if(totalVecinos==null)
			return new long[0];
		//System.out.println(userID +": "+ user.getGrupo() + ": "+ totalVecinos.size());
		long[] ids = Longs.toArray(totalVecinos);
		if( totalVecinos.size() < n )
			return ids; 
		else{
			LongPrimitiveIterator usersIDs = new  LongPrimitiveArrayIterator(ids);
			return TopItems.getTopUsers(n, usersIDs, null, estimator); 
		}
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
	public long[] getUserNeighborhood(long userID, long itemID)
			throws TasteException {
		return this.getUserNeighborhood(userID);
	}

}