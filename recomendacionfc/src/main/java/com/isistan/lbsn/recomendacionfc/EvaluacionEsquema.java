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
			ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			RandomUtils.useTestSeed();
			final DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			final GrafoModel grafoModel = new FriendsDataModel(MyProperties.getInstance().getProperty("databasegrafo"));
			List<Future<Resultado>> futures = new LinkedList<Future<Resultado>>();
			for (final Configuracion configuracion : configuraciones) {
				futures.add(service.submit(new java.util.concurrent.Callable<Resultado>() {
					public Resultado call() throws Exception {
						UserSimilarity sim = SimilarityAlgorithm.build(ratingModel, grafoModel,configuracion.getSimAlg(),configuracion.getBeta(),configuracion.getBeta());
						UserNeighborhood neighborhood = TypeNeighborhood.build(sim, ratingModel, configuracion.getTypeNeigh(),configuracion.getNeighSize(), configuracion.getThreshold(),grafoModel);
						RecommenderBuilder recBuilder = new GenRecBuilder(sim,neighborhood);
			          //double scoreMae = new AverageAbsoluteDifferenceRecommenderEvaluator().evaluate(recBuilder,null,ratingModel, 0.7, 1);
					  //double scoreRms = new RMSRecommenderEvaluator().evaluate(recBuilder, null, ratingModel, 0.7, 1);
						IRStatistics stats =  new GenericRecommenderIRStatsEvaluator().evaluate(recBuilder, null, ratingModel, null, 10, 3, 1);
						Resultado resultado = new Resultado(configuracion, 0, 0,stats.getPrecision(),stats.getRecall());
						System.out.println(resultado.toString());
			            return resultado;
			          //System.out.println(stats.getPrecision()+" "+stats.getRecall());
					  //resultados.add(new Resultado(configuracion, scoreMae, scoreRms,0,0));
					}
				}));
				}
				for (Future f : futures) {
					resultados.add((Resultado) f.get());
				}
				
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultados;
	}





}
