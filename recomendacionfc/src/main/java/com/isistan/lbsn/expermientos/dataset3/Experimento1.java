package com.isistan.lbsn.expermientos.dataset3;

import java.util.ArrayList;

import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.EvaluacionEsquema;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithm;
import com.isistan.lbsn.recomendacionfc.AgregationFactory.AgregationType;
import com.isistan.lbsn.recomendacionfc.ScoringFactory.ScoringType;
import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;
import com.isistan.lbsn.util.Util;

public class Experimento1 {

	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		
////////////////NEIGHBOR WEIGHTING///////////////

configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(50,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(100,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
configuraciones.add(new Configuracion(50,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
configuraciones.add(new Configuracion(100,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));

configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
configuraciones.add(new Configuracion(50,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
configuraciones.add(new Configuracion(100,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
		
////////////////NEIGHBOR SELECTION///////////////		

//configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//
//configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.001,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.02,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.04,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.07,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//
//configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.001,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
//configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.04,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
//configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.07,TypeNeigh.K_NEIGHBORHOOD_SCORING,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
		
				
				 EvaluacionEsquema esquema =  new EvaluacionEsquema();
				 System.out.println("Inicia evaluacion");
				 resultados = esquema.evaluar(configuraciones);
				 System.out.println("Fin evaluacion");
				 System.out.println("Exportar csv");
				 
				 Util.exportarResultadoCsv(resultados, "USER_NEIGHBOR_WEIGHTING_RATING_SENTI2");
				 System.out.println("FIN");
	}

}
