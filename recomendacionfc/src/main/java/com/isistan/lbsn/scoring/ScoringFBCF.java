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

public class ScoringFBCF implements Scoring{
	GrafoModel grafoModel;
	DataModel dataModel;
	ScoringJaccardNetwork sc2;
	ScoringOverlap sc1;
	double delta = 0.5;

	public ScoringFBCF(GrafoModel grafoModel, DataModel dataModel) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
		sc1 = new ScoringOverlap(dataModel);
		sc2 = new ScoringJaccardNetwork(grafoModel);
	}

	public ScoringFBCF(DataModel ratingModel) {
		this.dataModel=ratingModel;
	}



	
	public double getScoring(long userID1, long userID2, long itemID) throws TasteException {
		
		double scoreOverlapMatriz = sc1.getScoring(userID1, userID2, itemID);
		double scoreOverlapRedSocial = sc2.getScoring(userID1, userID2, itemID);
		double resultado = (delta*scoreOverlapRedSocial) + ((1-delta)*scoreOverlapMatriz); 
		
	    FastIDSet xPrefs = dataModel.getItemIDsFromUser(userID1);
	    FastIDSet yPrefs = dataModel.getItemIDsFromUser(userID2);
	    
	    
	    return resultado;
	    
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
