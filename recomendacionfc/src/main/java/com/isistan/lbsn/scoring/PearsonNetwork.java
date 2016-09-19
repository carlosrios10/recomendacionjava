package com.isistan.lbsn.scoring;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math.stat.correlation.PearsonsCorrelation;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.datamodels.GrafoModel;

import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import edu.uci.ics.jung.algorithms.matrix.GraphMatrixOperations;

public class PearsonNetwork implements UserSimilarity {
	GrafoModel grafoDataModel;

	public PearsonNetwork(GrafoModel grafoDataModel) {
		super();
		this.grafoDataModel = grafoDataModel;
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub

	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		if (!grafoDataModel.getGrafo().containsVertex(userID1)||!grafoDataModel.getGrafo().containsVertex(userID2))
			return 0;
		
		int n = grafoDataModel.getGrafo().getVertexCount();
		int degID1 = grafoDataModel.getGrafo().degree(userID1);
		int degID2 = grafoDataModel.getGrafo().degree(userID2);
		int vecinosComunes = getInterseccionVecinos(userID1, userID2);
		double numerador = (n * vecinosComunes) - (degID1 * degID2);
		double denominador = Math.sqrt((degID1 * degID2) * (n - degID1)
				* (n - degID2));
		return (numerador / denominador);
	}

	public double userSimilarity(Nodo userID1, Nodo userID2)
			throws TasteException {
		int n = grafoDataModel.getGrafoN().getVertexCount();
		int degID1 = grafoDataModel.getGrafoN().degree(userID1);
		int degID2 = grafoDataModel.getGrafoN().degree(userID2);
		int vecinosComunes = getInterseccionVecinos(userID1, userID2);
		double numerador = (n * vecinosComunes) - (degID1 * degID2);
		double denominador = Math.sqrt((degID1 * degID2) * (n - degID1)
				* (n - degID2));
		return (numerador / denominador);
	}

	protected int getInterseccionVecinos(Nodo userID1, Nodo userID2) {
		Collection<Nodo> vecinosU1 = grafoDataModel.getGrafoN().getNeighbors(
				userID1);
		Collection<Nodo> vecinosU2 = grafoDataModel.getGrafoN().getNeighbors(
				userID2);
		if (vecinosU1 == null || vecinosU2 == null)
			return 0;

		if (vecinosU1.size() == 0 || vecinosU2.size() == 0)
			return 0;
		Collection<Nodo> interseccion = new ArrayList<Nodo>(vecinosU1);

		if (interseccion.retainAll(vecinosU2)) {
			return interseccion.size();
		} else {
			return 0;
		}
	}

	protected int getInterseccionVecinos(Long userID1, Long userID2) {
		Collection<Long> vecinosU1 = grafoDataModel.getGrafo().getNeighbors(
				userID1);
		Collection<Long> vecinosU2 = grafoDataModel.getGrafo().getNeighbors(
				userID2);
		if (vecinosU1 == null || vecinosU2 == null)
			return 0;

		if (vecinosU1.size() == 0 || vecinosU2.size() == 0)
			return 0;

		Collection<Long> interseccion = new ArrayList<Long>(vecinosU1);

		if (interseccion.retainAll(vecinosU2)) {
			return (interseccion.size());
		} else {
			return 0;
		}

	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub

	}

}
