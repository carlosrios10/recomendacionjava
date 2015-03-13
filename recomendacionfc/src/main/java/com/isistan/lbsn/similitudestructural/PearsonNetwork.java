package com.isistan.lbsn.similitudestructural;

import java.util.Collection;

import org.apache.commons.math.stat.correlation.PearsonsCorrelation;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import edu.uci.ics.jung.algorithms.matrix.GraphMatrixOperations;

public class PearsonNetwork implements UserSimilarity{
	GrafoDataModel grafoDataModel;

	public PearsonNetwork(GrafoDataModel grafoDataModel) {
		super();
		this.grafoDataModel = grafoDataModel;
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		int n = grafoDataModel.getGrafo().getVertexCount();
		PearsonsCorrelation p = new PearsonsCorrelation();
		SparseDoubleMatrix2D adMatrix =  GraphMatrixOperations.graphToSparseMatrix(grafoDataModel.getGrafo());
		
		double []user1 = new double[n];
		double []user2 = new double[n];
		for (int i = 0; i < n; i++) {
			user1[i] = adMatrix.getQuick((int)userID1, i);	
			user2[i] = adMatrix.getQuick((int)userID2, i);
		}		
		 double corr = p.correlation(user1,user2);
		 return corr;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

}
