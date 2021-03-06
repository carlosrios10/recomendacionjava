package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.FullRunningAverage;
import org.apache.mahout.cf.taste.impl.common.FullRunningAverageAndStdDev;
import org.apache.mahout.cf.taste.impl.common.RunningAverage;
import org.apache.mahout.cf.taste.impl.common.RunningAverageAndStdDev;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringOverlapLiked implements Scoring{
	//private static final int UMBRAL_LIKED = 3;
	GrafoModel grafoModel;
	DataModel dataModel;
	DataModel dataModelLiked;
	Double umbralLiked;
	public ScoringOverlapLiked(GrafoModel grafoModel, DataModel dataModel) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
		
	}
	public ScoringOverlapLiked(GrafoModel grafoModel, DataModel dataModel,DataModel dataModelLiked ) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
		this.dataModelLiked = dataModelLiked;
	}
	public ScoringOverlapLiked(GrafoModel grafoModel, DataModel dataModel, Double umbralLiked) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
		this.umbralLiked=umbralLiked; 
		
	}
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		return getScoring(userID1, userID2, -1);
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		
	    FastIDSet xPrefs = (this.dataModelLiked != null)?dataModelLiked.getItemIDsFromUser(userID1):getLikedItems(userID1);
	    FastIDSet yPrefs = (this.dataModelLiked != null)?dataModelLiked.getItemIDsFromUser(userID2):getLikedItems(userID2);
	    
//	    FastIDSet xPrefs = dataModel.getItemIDsFromUser(userID1);
//	    FastIDSet yPrefs = dataModel.getItemIDsFromUser(userID2);
	    
	    int xPrefsSize = xPrefs.size();
	    int yPrefsSize = yPrefs.size();
	    if (xPrefsSize == 0 && yPrefsSize == 0) {
	      return Double.NaN;
	    }
	    if (xPrefsSize == 0 || yPrefsSize == 0) {
	      return 0.0;
	    }
	    
	    int intersectionSize =
	        xPrefsSize < yPrefsSize ? yPrefs.intersectionSize(xPrefs) : xPrefs.intersectionSize(yPrefs);
	    if (intersectionSize == 0) {
	      return Double.NaN;
	    }
	    int unionSize = xPrefsSize + yPrefsSize - intersectionSize;
	    
	    return (double) intersectionSize/ (double) unionSize;
	    
	    
	}

	private FastIDSet getLikedItems(Long userID) throws TasteException {
		FastIDSet itemIDsFromUser = dataModel.getItemIDsFromUser(userID);
		double theUmbral = (umbralLiked==null) ? calcularUmbral(dataModel.getPreferencesFromUser(userID)) : umbralLiked;
		for (Long itemID : itemIDsFromUser) {
			double valor = dataModel.getPreferenceValue(userID, itemID);
			if( valor < theUmbral ){
				itemIDsFromUser.remove(itemID);
			}
		}
		
		return itemIDsFromUser;
	}
	
	
	  private double calcularUmbral(PreferenceArray prefs) {
		    if (prefs.length() < 2) {
		      // Not enough data points -- return a threshold that allows everything
		      return Double.NEGATIVE_INFINITY;
		    }
		    RunningAverage avg = new FullRunningAverage();
		    int size = prefs.length();
		    for (int i = 0; i < size; i++) {
		      avg.addDatum(prefs.getValue(i));
		    }
		    return avg.getAverage();
		  }
	  
	  private  double computeThreshold(PreferenceArray prefs) {
		    if (prefs.length() < 2) {
		      // Not enough data points -- return a threshold that allows everything
		      return Double.NEGATIVE_INFINITY;
		    }
		    RunningAverageAndStdDev stdDev = new FullRunningAverageAndStdDev();
		    int size = prefs.length();
		    for (int i = 0; i < size; i++) {
		      stdDev.addDatum(prefs.getValue(i));
		    }
		    return stdDev.getAverage() + stdDev.getStandardDeviation();
		  }
}
