package com.isistan.lbsn.agregation;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.scoring.Scoring;
/**
 * Clase que combina la simitlud entre los usuarios y una medida de scoring.
 * Calcula la media armonica entre dos valores.
 * @author Usuarioç
 *
 */
public class AgregationHarmonicMean implements Agregation {
	UserSimilarity userSimilarityRating;
	Scoring scoring;
	public AgregationHarmonicMean(UserSimilarity userSimilarityRating,
			Scoring scoring) {
		super();
		this.userSimilarityRating = userSimilarityRating;
		this.scoring = scoring;
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		double simRating = userSimilarityRating.userSimilarity(userID1, userID2);
		double simScoring = scoring.getScoring(userID1, userID2,0);
		double harmonicMean	= (2*simRating*simScoring)/(simRating+simScoring);
		return harmonicMean;
		
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

}
