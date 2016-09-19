package com.isistan.lbsn.scoring;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public interface Scoring extends UserSimilarity{
	public double getScoring(long userID1, long userID2, long itemID) throws TasteException;

}
