package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringDistanciaNetwork implements Scoring{
	GrafoModel grafoDataModel;
	double meanPath = 0.16463998828 ;
	public ScoringDistanciaNetwork(GrafoModel grafoDataModel) {
		super();
		this.grafoDataModel = grafoDataModel;
	}
	public ScoringDistanciaNetwork(GrafoModel grafoDataModel,double meanPath) {
		super();
		this.grafoDataModel = grafoDataModel;
		this.meanPath = meanPath;
		
	}
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		Integer dist = grafoDataModel.getDistanciaSinPeso(userID1, userID2);
		
		
		if (dist == null)
			return 0;
		
		if (dist > 2 )
			return meanPath;
		
		return (1.0/dist);
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		return this.userSimilarity(userID1, userID2);
	}

}
