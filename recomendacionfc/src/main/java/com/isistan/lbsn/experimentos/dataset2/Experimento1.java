package com.isistan.lbsn.experimentos.dataset2;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.EvaluacionEsquema;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.util.Util;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;


public class Experimento1 {
	private static final double PORCENTAJE_TRAIN = 0.7;
	private static final Logger log = LoggerFactory.getLogger(Experimento1.class);
	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		
////////////////NEIGHBOR WEIGHTING///////////////

//configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//
//configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//
//configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.HARMONIC_MEAN));


////////////////NEIGHBOR WEIGHTING Seleccion de vecionos red social ///////////////
		
//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		

//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
////
//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));


//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//
//
//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));	
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));


//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));


//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

////
//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

//
configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

				 
				 log.info("Inicia evaluacion");
				 EvaluacionEsquema esquema =  new EvaluacionEsquema();
				 resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN);
				 log.info("Fin evaluacion");
				 log.info("Exportar csv");
				 Util.exportarResultadoCsv(resultados, "resultados_UserNY_Red_Social_Zona_matriz_sentimiento_V2_test3");
				 
				 log.info("FIN");
				 
				 
	}

}
