package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.recomendacionfc.Scoring;

public class ScoringOverlap implements Scoring{
	GrafoModel grafoModel;
	DataModel dataModel;
	UserSimilarity tanimoto;
	

	public ScoringOverlap(GrafoModel grafoModel, DataModel dataModel) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
		tanimoto = new TanimotoCoefficientSimilarity(dataModel);
	}

	public ScoringOverlap(DataModel ratingModel) {
		this.dataModel=ratingModel;
	}

	public double getScoring(long userID1, long userID2, long itemID) throws TasteException {
	    FastIDSet xPrefs = dataModel.getItemIDsFromUser(userID1);
	    FastIDSet yPrefs = dataModel.getItemIDsFromUser(userID2);
	   

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
	    
	    return (double) intersectionSize;
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

}
