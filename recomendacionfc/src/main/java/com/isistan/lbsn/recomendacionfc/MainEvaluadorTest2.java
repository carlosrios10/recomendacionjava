package com.isistan.lbsn.recomendacionfc;

import java.util.ArrayList;

import com.isistan.lbsn.recomendacionfc.AgregationFactory.AgregationType;
import com.isistan.lbsn.recomendacionfc.ScoringFactory.ScoringType;
import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;
import com.isistan.lbsn.util.Util;

public class MainEvaluadorTest2 {
	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		////////////////NEIGHBOR WEIGHTING///////////////
		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
		
		////////////////NEIGHBOR SELECTION///////////////
		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.ONLY_SCORE));
//
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.HARMONIC_MEAN));
		
		////////////////NEIGHBOR WEIGHTING///////////////
		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP,AgregationType.BASE));
//
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));		
		
		
		////////////////NEIGHBOR SELECTION///////////////
		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.BASE));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.BASE));
//		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.BASE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.BASE));

		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));
		configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.HARMONIC_MEAN));		
		
		EvaluacionEsquema esquema =  new EvaluacionEsquema();
		 System.out.println("Inicia evaluacion");
		 resultados = esquema.evaluar(configuraciones);
		 System.out.println("Fin evaluacion");
		 System.out.println("Exportar csv");
		 
		 Util.exportarResultadoCsv(resultados, "resultadosUBCF_NY_UE_NeighborSelectionCercaniaUsuarioUsuario");
		 System.out.println("FIN");
		 
		
	}

}
