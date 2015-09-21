package com.isitan.lbsn.agregation;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.recomendacionfc.Agregation;
import com.isistan.lbsn.recomendacionfc.Scoring;

public class AgregationOnlyScore implements Agregation{
	UserSimilarity userSimilarityRating;
	Scoring scoring;

	public AgregationOnlyScore(UserSimilarity userSimilarityRating,
			Scoring scoring) {
		super();
		this.userSimilarityRating = userSimilarityRating;
		this.scoring = scoring;
	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		double sim=scoring.getScoring(userID1, userID2, 0);
	//	System.out.println("id1: "+userID1+" id2: "+userID2+" sim: "+sim);
		return sim;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

}
