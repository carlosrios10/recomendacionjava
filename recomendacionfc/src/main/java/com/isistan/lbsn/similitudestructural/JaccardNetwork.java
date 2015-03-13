package com.isistan.lbsn.similitudestructural;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class JaccardNetwork implements UserSimilarity {
	GrafoDataModel grafoDataModel;

	public JaccardNetwork() {
		super();
	}

	public JaccardNetwork(GrafoDataModel grafoDataModel) {
		super();
		this.grafoDataModel = grafoDataModel;
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {

		Collection<Long> vecinosU1 = grafoDataModel.getGrafo().getNeighbors(
				userID1);
		Collection<Long> vecinosU2 = grafoDataModel.getGrafo().getNeighbors(
				userID2);
		if (vecinosU1 == null || vecinosU2 == null)
			return 0;

		if (vecinosU1.size() == 0 || vecinosU2.size() == 0)
			return 0;

		Collection<Long> union = new ArrayList<Long>(vecinosU1);
		Collection<Long> interseccion = new ArrayList<Long>(vecinosU1);

		if (interseccion.retainAll(vecinosU2)) {
			union.removeAll(vecinosU2);
			union.addAll(vecinosU2);
			return (interseccion.size() / (double) union.size());
		} else {
			return 0;
		}
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub

	}

}
