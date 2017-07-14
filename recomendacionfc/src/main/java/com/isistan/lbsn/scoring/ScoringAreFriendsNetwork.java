package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringAreFriendsNetwork implements Scoring{
	GrafoModel grafoDataModel;
	double MEAN_PATH = 0.16463;
	public ScoringAreFriendsNetwork(GrafoModel grafoDataModel) {
		super();
		this.grafoDataModel = grafoDataModel;
	}
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		Boolean dist = grafoDataModel.areFriends(userID1, userID2);
		
		if (dist == null)
			return 0;
		
		if (!dist)
			return MEAN_PATH;
		
		return 1.0;
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
