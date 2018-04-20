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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.esquema.EvaluacionEsquema;

public final class NearestNUserGrupo implements UserNeighborhoodAux {
	private static final Logger log = LoggerFactory.getLogger(NearestNUserGrupo.class);
	
	private final int n;
	private final double minSimilarity;
	private final double samplingRate;
	private final RefreshHelper refreshHelper;
	private final UserModel userModel;
	private final UserSimilarity userSimilarity;
	private final int nivel;
	


	public NearestNUserGrupo(int n, UserSimilarity userSimilarity,UserModel userModel,int nivel) throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, userModel,nivel, 1.0);
	}
	public NearestNUserGrupo(int n,double minSimilarity,UserSimilarity userSimilarity,UserModel userModel, int
			nivel, double samplingRate) throws TasteException {
		this.userSimilarity = userSimilarity;
		this.samplingRate =  samplingRate;
		this.userModel = userModel;
		Preconditions.checkArgument(n >= 1, "n must be at least 1");
		int numUsers = userModel.getUsuariosTabla().size();
		this.n = n > numUsers ? numUsers : n;
		this.nivel = nivel;
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
		Collection<Long> totalVecinos =  this.userModel.getUserGrupo(user,nivel);
		if(totalVecinos==null)
			return new long[0];
		//System.out.println(userID +": "+ user.getGrupo() + ": "+ totalVecinos.size());
		long[] ids = Longs.toArray(totalVecinos);
		if( totalVecinos.size() > n ){
			LongPrimitiveIterator usersIDs = new  LongPrimitiveArrayIterator(ids);
			ids = TopItems.getTopUsers(n, usersIDs, null, estimator); 
		}
		String vecinos = userID+"";
//		for (int i = 0; i < ids.length; i++) {
//			vecinos = vecinos +","+ Long.toString(ids[i]); 
//		}
		//log.debug(vecinos);
		return ids; 
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
