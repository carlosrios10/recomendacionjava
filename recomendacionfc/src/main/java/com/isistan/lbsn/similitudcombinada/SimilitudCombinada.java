package com.isistan.lbsn.similitudcombinada;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/**
 * 
 * @author Usuarioç
 *
 */
public class SimilitudCombinada  implements UserSimilarity{
	UserSimilarity userSimilarityRating;
	UserSimilarity userSimilarityNetwork;
	double alfa;
	double beta;
	
/**
 * Clase que combina dos tipos de similitudes, una similitud de ratings y otra similitud sobre la estructura de la red.
 * similitudCombinada = alfa*simRating + beta*simNetwork.
 * @param userSimilarityRating
 * @param userSimilarityNetwork
 * @param alfa 
 * @param beta
 */
	public SimilitudCombinada(UserSimilarity userSimilarityRating,
			UserSimilarity userSimilarityNetwork, double alfa, double beta) {
		super();
		this.userSimilarityRating = userSimilarityRating;
		this.userSimilarityNetwork = userSimilarityNetwork;
		this.alfa = alfa;
		this.beta = beta;
	}

	public SimilitudCombinada() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		double simRating = userSimilarityRating.userSimilarity(userID1, userID2);
		double simNetwork = userSimilarityNetwork.userSimilarity(userID1, userID2);
		double simCombinada = (alfa*simRating)+(beta*simNetwork);
		return simCombinada;
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public UserSimilarity getUserSimilarityRating() {
		return userSimilarityRating;
	}

	public void setUserSimilarityRating(UserSimilarity userSimilarityRating) {
		this.userSimilarityRating = userSimilarityRating;
	}

	public UserSimilarity getUserSimilarityNetwork() {
		return userSimilarityNetwork;
	}

	public void setUserSimilarityNetwork(UserSimilarity userSimilarityNetwork) {
		this.userSimilarityNetwork = userSimilarityNetwork;
	}

	public double getAlfa() {
		return alfa;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

}
