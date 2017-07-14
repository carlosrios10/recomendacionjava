package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.FullRunningAverage;
import org.apache.mahout.cf.taste.impl.common.RunningAverage;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringOverlapLikedAndHated implements Scoring{
	//private static final int UMBRAL_LIKED = 3;
	GrafoModel grafoModel;
	DataModel dataModel;
	DataModel dataModelLiked;
	DataModel dataModelHated;
	private Double umbralLiked;
	
	public ScoringOverlapLikedAndHated(DataModel dataModel,DataModel dataModelLiked,DataModel dataModelHated) {
		this.dataModelLiked = dataModelLiked;
		this.dataModelHated = dataModelHated;
		this.dataModel = dataModel;
	}
	public ScoringOverlapLikedAndHated(GrafoModel grafoModel, DataModel dataModel) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
	}

	public ScoringOverlapLikedAndHated(GrafoModel grafoModel, DataModel dataModel, Double umbralLiked) {
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
		
		Double theUmbralUserID1 = null;
		Double theUmbralUserID2 = null;
		if( dataModelLiked == null ){
			 theUmbralUserID1 = (umbralLiked==null) ? calcularUmbral(dataModel.getPreferencesFromUser(userID1)) : umbralLiked;
			 theUmbralUserID2 = (umbralLiked==null) ? calcularUmbral(dataModel.getPreferencesFromUser(userID2)) : umbralLiked;
		}
		
		double simLiked = 0;
		try{
//	    FastIDSet xPrefs = dataModelLiked.getItemIDsFromUser(userID1);
//	    FastIDSet yPrefs = dataModelLiked.getItemIDsFromUser(userID2);
		//System.out.println(userID1+" "+userID2);
	    FastIDSet xPrefs = (dataModelLiked != null)? dataModelLiked.getItemIDsFromUser(userID1) : getLikedItems(userID1,theUmbralUserID1);
	    FastIDSet yPrefs = (dataModelLiked != null)? dataModelLiked.getItemIDsFromUser(userID2) : getLikedItems(userID2,theUmbralUserID2);

	    simLiked = getJaccardSim(xPrefs, yPrefs);
		}catch(NoSuchUserException ne){
			ne.printStackTrace();
		}
		
		
		double simHated = 0;
	    try{
//	    FastIDSet xPrefsHated = dataModelHated.getItemIDsFromUser(userID1);
//	    FastIDSet yPrefsHated = dataModelHated.getItemIDsFromUser(userID1);

	    FastIDSet xPrefsHated = (dataModelHated != null)? dataModelHated.getItemIDsFromUser(userID1) : getHatedItems(userID1,theUmbralUserID1);
	    FastIDSet yPrefsHated = (dataModelHated != null)? dataModelHated.getItemIDsFromUser(userID2) : getHatedItems(userID2,theUmbralUserID2);

	    
	    simHated = getJaccardSim(xPrefsHated, yPrefsHated);
	    }catch(NoSuchUserException ne){
	    	
	    }
	    
	    return (double) (simLiked + simHated)/ (double) 2;
	}

	private double getJaccardSim(FastIDSet xPrefs, FastIDSet yPrefs) {
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
	      return Double.NaN ;
	    }
	    int unionSize = xPrefsSize + yPrefsSize - intersectionSize;
	    
	    double sim = (double) intersectionSize/ (double) unionSize;
		return sim;
	}
	
	private FastIDSet getLikedItems(Long userID, double theUmbralLiked) throws TasteException {
		
		FastIDSet itemIDsFromUser = dataModel.getItemIDsFromUser(userID);
		for (Long itemID : itemIDsFromUser) {
			double valor = dataModel.getPreferenceValue(userID, itemID);
			if( valor < theUmbralLiked ){
				itemIDsFromUser.remove(itemID);
			}
		}
		
		return itemIDsFromUser;
	}
	
	private FastIDSet getHatedItems(Long userID, double theUmbralLiked) throws TasteException {
		FastIDSet itemIDsFromUser = dataModel.getItemIDsFromUser(userID);
		for (Long itemID : itemIDsFromUser) {
			double valor = dataModel.getPreferenceValue(userID, itemID);
			if( valor >= theUmbralLiked ){
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

}
