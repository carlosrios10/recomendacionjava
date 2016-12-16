package com.isistan.lbsn.vencindario;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.entidades.Item;
import com.isistan.lbsn.scoring.Scoring;

public class NearestNUserNeighborhoodSplitDMByScoring implements UserNeighborhoodAux {

	private final int n;
	private final double minSimilarity;
	private final double samplingRate;
	private final RefreshHelper refreshHelper;
	private final ItemModel itemModel;
	private final UserSimilarity userSimilarity;
	private final DataModelByItemCategory dataModel;
	private int radio;
	public NearestNUserNeighborhoodSplitDMByScoring(int n, Scoring userSimilarity,ItemModel itemModel,DataModelByItemCategory dataModel) 
			throws TasteException {
		this(n, Double.NEGATIVE_INFINITY, userSimilarity, itemModel,dataModel, 1.0);

	}
	public NearestNUserNeighborhoodSplitDMByScoring(int n,double minSimilarity,Scoring userSimilarity,ItemModel itemModel,DataModelByItemCategory dataModel, double samplingRate) 
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
		LongPrimitiveIterator userIDs = mergeUserIDs(itemID);
		UserSimilarity userSimilarityImpl = this.userSimilarity;
		TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity);
		return TopItems.getTopUsers(n, userIDs, null, estimator); 
	}

	private LongPrimitiveArrayIterator mergeUserIDs(long itemID) throws TasteException{
		Collection<Long> cateNivelTodas = new HashSet<Long>(); 
		Item item = itemModel.getItem(itemID);
		Collection<Long> coll = item.getCategoriaNivel1();
		for (Iterator<Long> iterator = coll.iterator(); iterator.hasNext();) {
			Long long1 = iterator.next();
			LongPrimitiveIterator userIDs = this.dataModel.getUserIDs(long1);
			cateNivelTodas.addAll(IteratorUtils.toList(userIDs));
		} 
		long[] catArray = Longs.toArray(cateNivelTodas);
		return new LongPrimitiveArrayIterator(catArray);
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
