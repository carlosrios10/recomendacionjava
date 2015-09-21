package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.ItemUserAverageRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/**
 * Clase que crea un recomendador basado en usuario.
 * @author Usuario�
 *
 */
public class GenRecBuilder implements RecommenderBuilder {
	private UserSimilarity userSimilarity;
	UserNeighborhood neighborhood;
	
	public GenRecBuilder(UserSimilarity userSimilarity,UserNeighborhood neighborhood ) {
		super();
		this.userSimilarity = userSimilarity;
		this.neighborhood = neighborhood;
	}

	
	public Recommender buildRecommender(DataModel model) throws TasteException {
		return new GenericUserBasedRecommenderNormalizado(model, neighborhood, userSimilarity);
	}

}
