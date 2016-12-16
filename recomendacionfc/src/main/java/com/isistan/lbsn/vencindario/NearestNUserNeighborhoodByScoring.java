package com.isistan.lbsn.vencindario;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.model.DataModel;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.scoring.Scoring;

public class NearestNUserNeighborhoodByScoring implements UserNeighborhoodAux {
	private final int n;
	private final double minSimilarity;
	private final double samplingRate;
	private final RefreshHelper refreshHelper;
	private final ItemModel itemModel;
	private final Scoring userSimilarity;
	private final DataModel dataModel;
	private int radio;
	public NearestNUserNeighborhoodByScoring(int n, Scoring userSimilarity,ItemModel itemModel,DataModel dataModel) 
			throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, itemModel,dataModel, 1.0);
		
	}
	public NearestNUserNeighborhoodByScoring(int n,double minSimilarity,Scoring userSimilarity,ItemModel itemModel,DataModel dataModel, double samplingRate) 
			throws TasteException {
		this.userSimilarity = userSimilarity;
		this.samplingRate =  samplingRate;
		this.itemModel = itemModel;
		this.dataModel = dataModel;
		Preconditions.checkArgument(n >= 1, "n must be at least 1");
		int numItems = itemModel.getCantidadDeItems();
		this.n = n > numItems ? numItems : n;
		this.minSimilarity = minSimilarity;
		this.refreshHelper = new RefreshHelper(null);
		this.refreshHelper.addDependency(this.userSimilarity);

	}
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public long[] getUserNeighborhood(long userID) throws TasteException {
		return null;
	}

	public long[] getUserNeighborhood(long userID, long itemID)
			throws TasteException {
		
	    LongPrimitiveIterator userIDs = this.dataModel.getUserIDs();
		Scoring userSimilarityImpl = this.userSimilarity;
		TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity,itemID);
		return TopItems.getTopUsers(n, userIDs, null, estimator); 
		
	}

	
	private static final class Estimator implements TopItems.Estimator<Long> {
		private final Scoring userSimilarityImpl;
		private final long theUserID;
		private final double minSim;
		private final long itemID;

		private Estimator(Scoring userSimilarityImpl, long theUserID, double minSim) {
			this.userSimilarityImpl = userSimilarityImpl;
			this.theUserID = theUserID;
			this.minSim = minSim;
			this.itemID = -1;
		}
		

		private Estimator(Scoring userSimilarityImpl, long theUserID, double minSim,long itemID) {
			this.userSimilarityImpl = userSimilarityImpl;
			this.theUserID = theUserID;
			this.minSim = minSim;
			this.itemID = itemID;
		}

		public double estimate(Long userID) throws TasteException {
			if (userID == theUserID) {
				return Double.NaN;
			}
			double sim = userSimilarityImpl.getScoring(theUserID, userID,itemID);
			return sim >= minSim ? sim : Double.NaN;
		}
	}
}
