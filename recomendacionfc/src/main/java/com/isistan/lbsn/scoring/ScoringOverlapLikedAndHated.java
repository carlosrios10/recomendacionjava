package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringOverlapLikedAndHated implements Scoring{
	//private static final int UMBRAL_LIKED = 3;
	GrafoModel grafoModel;
	DataModel dataModel;
	private Double umbralLiked;
	public ScoringOverlapLikedAndHated(GrafoModel grafoModel, DataModel dataModel) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
		this.umbralLiked=3.0;
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
	    FastIDSet xPrefs = getLikedItems(userID1);
	    FastIDSet yPrefs = getLikedItems(userID2);
	    double simLiked = getJaccardSim(xPrefs, yPrefs);
	    FastIDSet xPrefsHated = getHatedItems(userID1);
	    FastIDSet yPrefsHated = getHatedItems(userID2);
	    double simHated = getJaccardSim(xPrefsHated, yPrefsHated);
	    
	    return (double) (simLiked+ simHated)/ (double) 2;
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
	      return Double.NaN;
	    }
	    int unionSize = xPrefsSize + yPrefsSize - intersectionSize;
	    
	    double sim = (double) intersectionSize/ (double) unionSize;
		return sim;
	}
	
	private FastIDSet getLikedItems(Long userID) throws TasteException {
		FastIDSet itemIDsFromUser = dataModel.getItemIDsFromUser(userID);
		for (Long itemID : itemIDsFromUser) {
			double valor = dataModel.getPreferenceValue(userID, itemID);
			if( valor < umbralLiked ){
				itemIDsFromUser.remove(itemID);
			}
		}
		
		return itemIDsFromUser;
	}
	
	private FastIDSet getHatedItems(Long userID) throws TasteException {
		FastIDSet itemIDsFromUser = dataModel.getItemIDsFromUser(userID);
		for (Long itemID : itemIDsFromUser) {
			double valor = dataModel.getPreferenceValue(userID, itemID);
			if( valor >= umbralLiked ){
				itemIDsFromUser.remove(itemID);
			}
		}
		
		return itemIDsFromUser;
	}

}
