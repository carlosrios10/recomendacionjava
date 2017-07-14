package com.isistan.lbsn.scoring;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.google.common.primitives.Longs;
import com.isistan.lbsn.datamodels.GrafoModel;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class ScoringJaccardNetwork implements Scoring {
	GrafoModel grafoDataModel;

	public ScoringJaccardNetwork() {
		super();
	}

	public ScoringJaccardNetwork(GrafoModel grafoDataModel) {
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
		
//		Collection<Long> vecinosU1 = new ArrayList<Long>(vecinosU1aux);
//		Collection<Long> vecinosU2 = new ArrayList<Long>(vecinosU2aux);
//		
//		vecinosU1.add(new Long(userID1));
//		vecinosU2.add(new Long(userID2));
//
//		if (vecinosU1.size() == 0 || vecinosU2.size() == 0)
//			return 0.0;
//
//		Collection<Long> union = new ArrayList<Long>(vecinosU1);
//		Collection<Long> interseccion = new ArrayList<Long>(vecinosU1);
//
//		interseccion.retainAll(vecinosU2); 
//		
//	    if (interseccion.size() == 0) 
//		      return Double.NaN;
//		
//		union.removeAll(vecinosU2);
//		union.addAll(vecinosU2);
//		
//		return (interseccion.size() / (double) union.size());
		
		FastIDSet vecinosU1 = new FastIDSet(Longs.toArray(vecinosU1aux));
		vecinosU1.add(userID1);
		FastIDSet vecinosU2 = new FastIDSet(Longs.toArray(vecinosU2aux));
		vecinosU2.add(userID2);
	    int xPrefsSize = vecinosU1.size();
	    int yPrefsSize = vecinosU2.size();
		
	    
	    if (xPrefsSize == 0 && yPrefsSize == 0) {
		      return Double.NaN;
		    }
		
	    if (xPrefsSize == 0 || yPrefsSize == 0) {
		      return 0.0;
		    }
		    
		    int intersectionSize =
		        xPrefsSize < yPrefsSize ? vecinosU2.intersectionSize(vecinosU1) : vecinosU1.intersectionSize(vecinosU2);
		    if (intersectionSize == 0) {
		      return Double.NaN;
		    }
		    int unionSize = xPrefsSize + yPrefsSize - intersectionSize;
		    return (double) intersectionSize/ (double) unionSize;

	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {

	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		return this.userSimilarity(userID1, userID2);
	}

}
