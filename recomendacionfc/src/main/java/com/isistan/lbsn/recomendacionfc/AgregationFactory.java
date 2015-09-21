package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isitan.lbsn.agregation.AgregationHarmonicMean;
import com.isitan.lbsn.agregation.AgregationOnlyScore;
import com.isitan.lbsn.agregation.AgregationOnlySim;

public class AgregationFactory {
	public enum AgregationType {HARMONIC_MEAN,BASE,ONLY_SCORE};
	public static  Agregation build(AgregationType agregationType,UserSimilarity userSimilarityRating,
			Scoring scoring){
		Agregation agregation = null;
		switch (agregationType) {
		case HARMONIC_MEAN:
			agregation = new AgregationHarmonicMean(userSimilarityRating, scoring);
			return agregation;
			
		case BASE:
			agregation = new AgregationOnlySim(userSimilarityRating, scoring);
			return agregation;
			
		case ONLY_SCORE:
			agregation = new AgregationOnlyScore(userSimilarityRating, scoring);
			return agregation;
			

		default:
			return null;
		}
	}
	

}
