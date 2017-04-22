package com.isistan.lbsn.builderrecomender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.recommender.GenericUserBasedRecommenderNoNormalizado;
import com.isistan.lbsn.vencindario.UserNeighborhoodAux;

public class RecomendadorBuilder implements RecommenderBuilder{
	DataModel modelTotal;
	public RecomendadorBuilder(DataModel modeltotal ) {
		super();
		this.modelTotal = modeltotal;
	}
	public Recommender buildRecommender(DataModel dataModele)
			throws TasteException {
		UserSimilarity sim = new UncenteredCosineSimilarity(modelTotal);		
		UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(300, sim,modelTotal);
		return new GenericUserBasedRecommender(modelTotal, userNeighborhood, sim);
	}

}
