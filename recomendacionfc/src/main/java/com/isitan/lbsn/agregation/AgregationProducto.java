package com.isitan.lbsn.agregation;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.recomendacionfc.Agregation;

public class AgregationProducto implements Agregation{

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

}
