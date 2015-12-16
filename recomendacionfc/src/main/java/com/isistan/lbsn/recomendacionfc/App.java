package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.config.MyProperties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Properties prop = new Properties();
    	InputStream input = null;
 
    	try {
//    		//	HashMap<Long, Long> resumenSola =  new HashMap<Long, Double>();
//    			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
//    			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
//    			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
//    			ScoringCercaniaUsuarioUsuario scoring = new ScoringCercaniaUsuarioUsuario(null, null, userModel, itemModel);
//    		    LongPrimitiveIterator usersIterable = ratingModel.getUserIDs(); 
//    		    LongPrimitiveIterator usersIterable2 = ratingModel.getUserIDs(); 
//    		    int cant = 0;
//    		    while (usersIterable.hasNext()) {
//    		    	long userID = usersIterable.next();
//    		    	cant++;
//    		    	System.out.println("1 :" + userID + "cant :" + cant);
//    		    	usersIterable2.skip(cant);
//    		    	while(usersIterable2.hasNext()){
//    		    		long otherUserID = usersIterable2.next();
//    		    		double theSimilarity = scoring.userSimilarity(userID, otherUserID);
//    		    		
//    		    	}
//    		        }
    		
    		//DataModel model = new FileDataModel(new File(""));
    		DataModel model = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
    		RecommenderBuilder builder = new MyRecommenderBuilderUser();
    		RecommenderBuilder builderItem =  new MyRecommenderBuilderItem();
    		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
    		RecommenderIRStatsEvaluator evaluatorIR = new GenericRecommenderIRStatsEvaluator();
    		double result = evaluator.evaluate(builderItem, null, model, 0.7, 1.0);
    		IRStatistics stats = evaluatorIR.evaluate(builderItem, null, model, null, 25,  2, 1); 
    		System.out.println(result);
    		System.out.println(stats.getPrecision()); 
    		System.out.println(stats.getRecall()); 
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
 
    }
    
    static class MyRecommenderBuilderUser implements RecommenderBuilder  {

		public Recommender buildRecommender(DataModel dataModel)
				throws TasteException {
			UserSimilarity similarity = new  UncenteredCosineSimilarity(dataModel);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
			return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
		}
    	
    	
    }
    
    static class MyRecommenderBuilderItem implements RecommenderBuilder  {

		public Recommender buildRecommender(DataModel dataModel)
				throws TasteException {
			ItemSimilarity similarity = new  TanimotoCoefficientSimilarity(dataModel);
			return new GenericItemBasedRecommender(dataModel, similarity);
		}
    	
    	
    }
    
    }


