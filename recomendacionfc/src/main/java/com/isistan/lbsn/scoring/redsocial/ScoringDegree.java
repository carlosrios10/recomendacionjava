package com.isistan.lbsn.scoring.redsocial;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.recomendacionfc.Scoring;
import com.isistan.lbsn.similitudestructural.GrafoModel;

public class ScoringDegree implements Scoring {
	GrafoModel grafoDataModel;
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		return 0;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		return grafoDataModel.getDegree(userID1);
	}

}
