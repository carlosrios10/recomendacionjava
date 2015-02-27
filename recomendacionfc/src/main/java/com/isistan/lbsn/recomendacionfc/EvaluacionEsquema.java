package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class EvaluacionEsquema {

	public ArrayList<Resultado> evaluar(ArrayList<Configuracion> configuraciones) {
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		try {
			RandomUtils.useTestSeed();
			DataModel model = new FileDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/dataset2.csv")));
			FriendsDataModel fmodel = new FriendsDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/datasetSN.csv")));
			for (Configuracion configuracion : configuraciones) {
				UserSimilarity sim = SimilarityAlgorithm.build(model, configuracion.getSimAlg());
				UserNeighborhood neighborhood = TypeNeighborhood.build(sim, model, configuracion.getTypeNeigh(),
												configuracion.getNeighSize(), configuracion.getThreshold(),fmodel);
				RecommenderBuilder recBuilder = new GenRecBuilder(sim,neighborhood);
	            double scoreMae = new AverageAbsoluteDifferenceRecommenderEvaluator().evaluate(recBuilder,null,model, 0.9, 1.0);
	            double scoreRms = new RMSRecommenderEvaluator().evaluate(recBuilder, null, model, 0.9, 1.0);
	            resultados.add(new Resultado(configuracion, scoreMae, scoreRms));
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}
		return resultados;
	}



}
