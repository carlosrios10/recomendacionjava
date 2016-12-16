package com.isistan.lbsn.agregation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public interface Agregation extends UserSimilarity {
	public double getAgregation(long userID1, long userID2, long itemID) throws TasteException;
}
