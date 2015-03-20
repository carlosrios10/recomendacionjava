package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.RandomRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MainTest {

	public static void main(String[] args) {
//		RunningAverage average = new FullRunningAverage();
//		RunningAverage average1 = new FullRunningAverageAndStdDev();
//		for (int i = 1; i <10 ; i++) {
//			average.addDatum(i);
//		}
//		System.out.println(average.getAverage());
//		for (int i = 1; i <10 ; i++) {
//			average1.addDatum(i);
//		}
//		System.out.println(average1.getAverage());
		DataModel model;
		try {
			model = new FileDataModel(new File("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/dataset.csv"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			
			RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
			RecommenderBuilder builderGen = new GenRecBuilder(similarity,neighborhood);
			RecommenderBuilder builderRan = new RandomRecBuilder();
			double resultGen =0;
			double resultRan =0;
			for (int i = 0; i < 10; i++) {
				 resultGen =+ evaluator.evaluate(builderGen, null, model, 0.9, 1.0);
				 System.out.println(evaluator.getMaxPreference());
				 resultRan =+ evaluator.evaluate(builderRan, null, model, 0.9, 1.0);
					
			}
				
			
			System.out.println(resultGen/10);
			System.out.println(resultRan/10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static  class RandomRecBuilder implements RecommenderBuilder {
		public RandomRecBuilder() {
			super();
		}
		public Recommender buildRecommender(DataModel model) throws TasteException {
			return new RandomRecommender(model);
		}

	}

}
