package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.recomendacionfc.AgregationFactory.AgregationType;
import com.isistan.lbsn.recomendacionfc.ScoringFactory.ScoringType;
import com.isistan.lbsn.similitudestructural.GrafoDataModel;
import com.isistan.lbsn.similitudestructural.GrafoModel;

public class EvaluacionEsquema {
/**
 * 
 * @param configuraciones
 * @return
 * To be clear, trainingPercentage and evaluationPercentage are not related. They do not need to add up to 1.0, for example.
 */
	public ArrayList<Resultado> evaluar(ArrayList<Configuracion> configuraciones) {
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		try {
		//	ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			RandomUtils.useTestSeed();
			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			GrafoModel grafoModel = new FriendsDataModel(MyProperties.getInstance().getProperty("databasegrafo"));
			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			
			//List<Future<Resultado>> futures = new LinkedList<Future<Resultado>>();
			for (final Configuracion configuracion : configuraciones) {
				//futures.add(service.submit(new java.util.concurrent.Callable<Resultado>() {
					//public Resultado call() throws Exception {
						UserSimilarity sim = SimilarityAlgorithm.build(ratingModel, grafoModel,configuracion.getSimAlg(),configuracion.getBeta(),configuracion.getBeta());
						Scoring scoring = ScoringFactory.build(configuracion.getScoringType(), ratingModel,grafoModel,userModel, itemModel);
						UserNeighborhood neighborhood = TypeNeighborhood.build(sim, ratingModel, configuracion.getTypeNeigh(),configuracion.getNeighSize(), configuracion.getThreshold(),grafoModel,scoring);
						Agregation agregation = AgregationFactory.build(configuracion.getAgregationType(), sim, scoring);
						
						RecommenderBuilder recBuilder = new GenRecBuilder(agregation,neighborhood);
						double scoreMae = new AverageAbsoluteDifferenceRecommenderEvaluator().evaluate(recBuilder,null,ratingModel, 0.7, 0.1);
					  	double scoreRms = new RMSRecommenderEvaluator().evaluate(recBuilder, null, ratingModel, 0.7, 0.1);
						IRStatistics stats =  new GenericRecommenderIRStatsEvaluator().evaluate(recBuilder, null, ratingModel, null,5, 3, 0.1);
						Resultado resultado = new Resultado(configuracion, scoreMae, scoreRms ,stats.getPrecision(),stats.getRecall());
						System.out.println(resultado.toString());
			            //return resultado;
			          //System.out.println(stats.getPrecision()+" "+stats.getRecall());
					  resultados.add(resultado);
					//}
				//}));
				}
//				for (Future f : futures) {
//					resultados.add((Resultado) f.get());
//				}
				
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultados;
	}





}
