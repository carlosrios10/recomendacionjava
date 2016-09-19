package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.recommender.GenericUserBasedRecommenderNormalizado;
import com.isistan.lbsn.util.Util;

public class ExperimentoFCTradicional {

	public static void main(String[] args) {
		DataModel ratingModel;
		try {
			ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprueba")));

		UserSimilarity sim = new PearsonCorrelationSimilarity(ratingModel);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, sim, ratingModel);
		GenericUserBasedRecommenderNormalizado re= new GenericUserBasedRecommenderNormalizado(ratingModel, neighborhood, sim);
		System.out.println(re.estimatePreference(3867, 275));
//		List<RecommendedItem> recommendedItems = re.recommend(3867,100);
//      for (RecommendedItem recommendedItem : recommendedItems) {
//    	  System.out.println(recommendedItem.getItemID()+" "+recommendedItem.getValue());
//      }
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
