package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.similitudcombinada.SimilitudCombinada;
import com.isistan.lbsn.similitudestructural.CosenoNetwork;
import com.isistan.lbsn.similitudestructural.JaccardNetwork;
import com.isistan.lbsn.similitudestructural.PearsonNetwork;

public class SimilarityAlgorithm {
	public enum SimAlg {
		EUCLIDEAN, PEARSON, TANIMOTO, COSENO, LOGLIKE, SPEARMAN, JACCARDNETWORK, COSENONETWORK,PEARSONNETWORK,COMBINADA
	};

	public static UserSimilarity build(DataModel model,
			GrafoModel grafoDataModel, 
									   SimAlg simAlg,double alfa,double beta ) {
		UserSimilarity similarity = null;
		switch (simAlg) {
		case COSENO:
			try {
				similarity = new UncenteredCosineSimilarity(model);
			} catch (TasteException xception) {

			}
		case EUCLIDEAN:
			try {
				similarity = new EuclideanDistanceSimilarity(model);
			} catch (TasteException exception) {
			}
			return similarity;
		case PEARSON:
			try {
				similarity = new PearsonCorrelationSimilarity(model);
			} catch (TasteException exception) {
			}
			return similarity;
		case LOGLIKE:
			similarity = new LogLikelihoodSimilarity(model);
			return similarity;
		case SPEARMAN:
			similarity = new SpearmanCorrelationSimilarity(model);
			return similarity;
		case TANIMOTO:
			similarity = new TanimotoCoefficientSimilarity(model);
			return similarity;
		case JACCARDNETWORK:
			similarity = new JaccardNetwork(grafoDataModel);
			return similarity;
		case COSENONETWORK:
			similarity = new CosenoNetwork(grafoDataModel);
			return similarity;
		case PEARSONNETWORK:
			similarity = new PearsonNetwork(grafoDataModel);
			return similarity;
		case COMBINADA:
			try {
				UserSimilarity similarityRating = new UncenteredCosineSimilarity(model);
				UserSimilarity similarityNetwork =  new CosenoNetwork(grafoDataModel);
				similarity = new SimilitudCombinada(similarityRating,similarityNetwork,alfa,beta);
			} catch (TasteException e) {
			}
			return similarity;
			
		default:
			return null; // We should never get here
		}
	}

}
