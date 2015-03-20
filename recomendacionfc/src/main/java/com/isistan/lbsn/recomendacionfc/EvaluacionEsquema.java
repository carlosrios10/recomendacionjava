package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;


import com.isistan.lbsn.similitudestructural.GrafoDataModel;

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
			final DataModel model = new FileDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/ratingsMeanReducido.csv")));
			final FriendsDataModel fmodel = new FriendsDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/redSocialReducida.csv")));
			final GrafoDataModel gModel = new GrafoDataModel("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/redSocialReducida.graphml");
			List<Future<Resultado>> futures = new LinkedList<Future<Resultado>>();
			for (final Configuracion configuracion : configuraciones) {
				futures.add(service.submit(new java.util.concurrent.Callable<Resultado>() {
					public Resultado call() throws Exception {
						UserSimilarity sim = SimilarityAlgorithm.build(model, gModel,configuracion.getSimAlg());
						UserNeighborhood neighborhood = TypeNeighborhood.build(sim, model, configuracion.getTypeNeigh(),
														configuracion.getNeighSize(), configuracion.getThreshold(),fmodel);
						RecommenderBuilder recBuilder = new GenRecBuilder(sim,neighborhood);
			            double scoreMae = new AverageAbsoluteDifferenceRecommenderEvaluator().evaluate(recBuilder,null,model, 0.9, 1.0);
			            double scoreRms = new RMSRecommenderEvaluator().evaluate(recBuilder, null, model, 0.9, 1.0);
						return new Resultado(configuracion, scoreMae, scoreRms);
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

	private double getVecinosPromedios(DataModel model,
			UserNeighborhood neighborhood) throws TasteException {
		LongPrimitiveIterator it = model.getUserIDs();
		int cantidadVecinos = 0;
		while (it.hasNext()) {
			Long idUser =  it.next();
			cantidadVecinos= cantidadVecinos+ neighborhood.getUserNeighborhood(idUser).length;
		}
		//double vecinosPromedio = cantidadVecinos/model.getNumUsers();
		return cantidadVecinos;
	}



}
