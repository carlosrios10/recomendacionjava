package com.isistan.lbsn.vencindario;

import java.util.Collection;
import java.util.Iterator;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.common.SamplingLongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.entidades.Item;


public class NearestNUserNeighborhoodBySimilitud implements UserNeighborhoodAux{
	
	  private int n;
	  private double minSimilarity;
	  private final UserSimilarity userSimilarity;
	  private final DataModel dataModel;
	  private final double samplingRate;
	  private final RefreshHelper refreshHelper;
	  private final DataModelByItemCategory dataModelByItemCategory;
	  private final ItemModel itemModel;
	  
	  public NearestNUserNeighborhoodBySimilitud(UserSimilarity userSimilarity,
			  DataModel dataModel,DataModelByItemCategory dataModelByItemCategory, 
			  ItemModel itemModel,double samplingRate) {
		    Preconditions.checkArgument(userSimilarity != null, "userSimilarity is null");
		    Preconditions.checkArgument(dataModel != null, "dataModel is null");
		    Preconditions.checkArgument(samplingRate > 0.0 && samplingRate <= 1.0, "samplingRate must be in (0,1]");
		    this.userSimilarity = userSimilarity;
		    this.dataModel = dataModel;
		    this.samplingRate = samplingRate;
		    this.minSimilarity = Double.NEGATIVE_INFINITY;
		    this.n = 10;
		    this.dataModelByItemCategory = dataModelByItemCategory;
		    this.itemModel = itemModel;
		    this.refreshHelper = new RefreshHelper(null);
		    this.refreshHelper.addDependency(this.dataModel);
		    this.refreshHelper.addDependency(this.userSimilarity);
		  }
	  
	  final UserSimilarity getUserSimilarity() {
		    return userSimilarity;
		  }
		  
		  final DataModel getDataModel() {
		    return dataModel;
		  }
		  
		  final double getSamplingRate() {
		    return samplingRate;
		  }
		  
		  public final void refresh(Collection<Refreshable> alreadyRefreshed) {
		    refreshHelper.refresh(alreadyRefreshed);
		  }
	  
	  /**
	   * @param n neighborhood size; capped at the number of users in the data model
	   * @throws IllegalArgumentException
	   *           if {@code n < 1}, or userSimilarity or dataModel are {@code null}
	   */
	  public NearestNUserNeighborhoodBySimilitud(int n, UserSimilarity userSimilarity, DataModel dataModel,
			  DataModelByItemCategory dataModelByItemCategory,ItemModel itemModel) throws TasteException {
	    this(n, Double.NEGATIVE_INFINITY, userSimilarity, dataModel,dataModelByItemCategory,itemModel, 1.0);
	  }
	  
	  /**
	   * @param n neighborhood size; capped at the number of users in the data model
	   * @param minSimilarity minimal similarity required for neighbors
	   * @throws IllegalArgumentException
	   *           if {@code n < 1}, or userSimilarity or dataModel are {@code null}
	   */
	  public NearestNUserNeighborhoodBySimilitud(int n,
	                                  double minSimilarity,
	                                  UserSimilarity userSimilarity,
	                                  DataModel dataModel,
	                                  DataModelByItemCategory dataModelByItemCategory,ItemModel itemModel) throws TasteException {
	    this(n, minSimilarity, userSimilarity, dataModel,dataModelByItemCategory,itemModel, 1.0);
	  }
	  
	  /**
	   * @param n neighborhood size; capped at the number of users in the data model
	   * @param minSimilarity minimal similarity required for neighbors
	   * @param samplingRate percentage of users to consider when building neighborhood -- decrease to trade quality for
	   *   performance
	   * @throws IllegalArgumentException
	   *           if {@code n < 1} or samplingRate is NaN or not in (0,1], or userSimilarity or dataModel are
	   *           {@code null}
	   */
	  public NearestNUserNeighborhoodBySimilitud(int n,
	                                  double minSimilarity,
	                                  UserSimilarity userSimilarity,
	                                  DataModel dataModel,
	                                  DataModelByItemCategory dataModelByItemCategory,
	                                  ItemModel itemModel,
	                                  double samplingRate) throws TasteException {
	    this(userSimilarity, dataModel, dataModelByItemCategory,itemModel,samplingRate);
	    Preconditions.checkArgument(n >= 1, "n must be at least 1");
	    int numUsers = dataModel.getNumUsers();
	    this.n = n > numUsers ? numUsers : n;
	    this.minSimilarity = minSimilarity;
	  }
	  
	  public long[] getUserNeighborhood(long userID) throws TasteException {
	    
	    DataModel dataModel = getDataModel();
	    UserSimilarity userSimilarityImpl = getUserSimilarity();
	    
	    TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity);
	    
	    LongPrimitiveIterator userIDs = SamplingLongPrimitiveIterator.maybeWrapIterator(dataModel.getUserIDs(),
	      getSamplingRate());
	    
	    return TopItems.getTopUsers(n, userIDs, null, estimator);
	  }
	  
	  @Override
	  public String toString() {
	    return "NearestNUserNeighborhood";
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
		if (this.dataModelByItemCategory == null){
			return this.getUserNeighborhood(userID);
		}else{
			return getVecinosSegunMatrices(userID,itemID);
		}
	}

	private long[] getVecinosSegunMatrices(long userID, long itemID){
//		Item item = itemModel.getItem(itemID);
//		for (Iterator<Long> iterator = item.getCategoriaNivel1().iterator(); iterator.hasNext();) {
//			Long idCategoria =  iterator.next();
//			
//			
//		}
		return null;
	} 
}
