package com.isistan.lbsn.similitudcombinada;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.recomendacionfc.Agregation;
import com.isistan.lbsn.recomendacionfc.Scoring;
/**
 * Clase que implementa la funcion de agregacion base, solo devuelve la similitud entre el usuario objetivo y el vecino.
 * @author Usuarioç
 *
 */
public class AgregationOnlySim implements Agregation{
	UserSimilarity userSimilarityRating;
	Scoring scoring;
	public AgregationOnlySim(UserSimilarity userSimilarityRating,
			Scoring scoring) {
		super();
		this.userSimilarityRating = userSimilarityRating;
		this.scoring = scoring;
	}


	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		return userSimilarityRating.userSimilarity(userID1, userID2);
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

}
