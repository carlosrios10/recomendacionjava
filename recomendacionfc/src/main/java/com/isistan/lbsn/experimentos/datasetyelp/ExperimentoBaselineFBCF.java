package com.isistan.lbsn.experimentos.datasetyelp;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.esquema.EvaluacionEsquema;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.util.Util;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;

public class ExperimentoBaselineFBCF {
	private static final double PORCENTAJE_TRAIN = 0.7;
	private static final double MEAN_PATH_YELP = 0.16463998828;
	private static final double MEAN_PATH_FOURSQUARE = 0.38356122196348025;
	private static final Logger log = LoggerFactory.getLogger(ExperimentosSeleccionVecinos.class);
	public static void main(String[] args) throws IOException {
		
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.SCORING_FRIEND_BASED_CF,AgregationType.ONLY_SCORE,SimilarityAlgorithmFactory.SimAlg.TANIMOTO));
		

		log.info("INICIA - evaluacion Experimento4");
		EvaluacionEsquema esquema =  new EvaluacionEsquema();
		boolean cache = false;
		ArrayList<Resultado>   resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN,cache);
		log.info("Exportar csv");
		Util.exportarResultadoCsv(resultados, "yelp_resultados_FBCF_matriz_tips");
		log.info("FIN - evaluacion ponderacion");
	}


}
