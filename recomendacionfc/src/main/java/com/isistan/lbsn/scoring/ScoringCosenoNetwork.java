package com.isistan.lbsn.scoring;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringCosenoNetwork implements Scoring {
	GrafoModel grafoDataModel;

	public ScoringCosenoNetwork(GrafoModel grafoDataModel) {
		super();
		this.grafoDataModel = grafoDataModel;
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {

	}

	/**
	 * 
	 */
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		
		Collection<Long> vecinosU1aux = grafoDataModel.getFriends(
				userID1);
		Collection<Long> vecinosU2aux = grafoDataModel.getFriends(
				userID2);
		
		if (vecinosU1aux == null || vecinosU2aux == null)
			return Double.NaN;
		
		Collection<Long> vecinosU1 = new ArrayList<Long>(grafoDataModel.getFriends(
				userID1));
		vecinosU1.add(new Long(userID1));
		Collection<Long> vecinosU2 = new ArrayList<Long>(grafoDataModel.getFriends(
				userID2));
		vecinosU2.add(new Long(userID2));
		
		if (vecinosU1.size() == 0 && vecinosU2.size() == 0)
			return Double.NaN;
	    
		if (vecinosU1.size() == 0 || vecinosU2.size() == 0)
			return 0.0;

//		Collection<Long> interseccion = new ArrayList<Long>(vecinosU1);
//		interseccion.retainAll(vecinosU2);
		Collection<Long> interseccion = new ArrayList<Long>(vecinosU2);
		interseccion.retainAll(vecinosU1);
		
	    if (interseccion.size() == 0) 
		      return Double.NaN;
		
		int cantU1 = vecinosU1.size();
		int cantU2 = vecinosU2.size();
		double denominador = Math.sqrt(cantU1 * cantU2);
		
		return (interseccion.size() / denominador);

	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		return this.userSimilarity(userID1, userID2);
	}

}
