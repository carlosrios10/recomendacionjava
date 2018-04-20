package com.isistan.lbsn.experimentos.datasetfoursquare;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.esquema.EvaluacionEsquema;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.util.Util;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;

public class ExperimentosSeleccionVecinos {

	private static final double PORCENTAJE_TRAIN = 0.7;
	private static final Logger log = LoggerFactory.getLogger(ExperimentosSeleccionVecinos.class);
	public static void main(String[] args) throws IOException {
		//System.in.read();

		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		//BASELINE
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		// COUNTY
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1	,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		// STATE
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2	,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));


		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_1,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_2,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_3,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_4,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA_RADIO_5,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));


		log.info("Inicia evaluacion");
		EvaluacionEsquema esquema =  new EvaluacionEsquema();
		boolean cache = false;
		resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN,cache);
		log.info("Exportar csv");
		Util.exportarResultadoCsv(resultados, "fourq_resultados_seleccion_vecinos_basestatecountyzonaredsocial_matriz_visitas");
		log.info("FIN");
	}


}
