package com.isistan.lbsn.recomendacionfc;

import java.util.Collection;



import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.RefreshHelper;
import org.apache.mahout.cf.taste.impl.recommender.TopItems;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import com.google.common.base.Preconditions;
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
	  private final FriendsDataModel friendsDm;

	private final RefreshHelper refreshHelper;
	  
	   public NearestNUserFriends(int n, UserSimilarity userSimilarity, DataModel dataModel,FriendsDataModel friendsDm) throws TasteException {
		       this(n, Double.NEGATIVE_INFINITY, userSimilarity, dataModel, 1.0,friendsDm);
		   }
		     
	   public NearestNUserFriends(int n,double minSimilarity,UserSimilarity userSimilarity,
			   						DataModel dataModel,double samplingRate,FriendsDataModel friendsDm) throws TasteException {
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
		 DataModel dataModel = this.dataModel;
	     UserSimilarity userSimilarityImpl = this.userSimilarity;
	     TopItems.Estimator<Long> estimator = new Estimator(userSimilarityImpl, userID, minSimilarity);
	     LongPrimitiveIterator userIDs =  this.friendsDm.getFriends(userID);
	     int cantAmigos = this.friendsDm.getCantidadAmigos(userID);
	     if( cantAmigos < n )
	    	return TopItems.getTopUsers(cantAmigos, userIDs, null, estimator); 
	     else
	    	 return TopItems.getTopUsers(n, userIDs, null, estimator); 
	     
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
