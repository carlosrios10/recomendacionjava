package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringPageRank implements Scoring{
	GrafoModel grafoDataModel;
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		return 0;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		return grafoDataModel.getPageRank(userID1);
	}

}
