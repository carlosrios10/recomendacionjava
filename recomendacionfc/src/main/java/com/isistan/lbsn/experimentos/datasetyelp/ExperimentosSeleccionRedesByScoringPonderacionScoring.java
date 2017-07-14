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

public class ExperimentosSeleccionRedesByScoringPonderacionScoring {

	private static final double PORCENTAJE_TRAIN = 0.7;
	private static final Logger log = LoggerFactory.getLogger(ExperimentosSeleccionVecinos.class);
	public static void main(String[] args) throws IOException {
		//System.in.read();

		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));


		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));

		//
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));


		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKDAYNAME,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_WEEKORWEEKEND,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));



		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_DISTANCIA_NETWORK,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,AgregationType.ONLY_SCORE));


		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));

		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));


		//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));		
		//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));		
		//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//
		//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));		
		//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));		
		//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_2_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//
		//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));		
		//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));		
		//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));
		//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_3_BY_SCORING,-1,-1,
		//		ScoringType.SCORING_LR2,AgregationType.ONLY_SCORE));

		log.info("INICIA - evaluacion seleccion by scoring en redes ponderaciones scorings");
		EvaluacionEsquema esquema =  new EvaluacionEsquema();
		boolean cache = false;
		ArrayList<Resultado>   resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN,cache);
		log.info("Exportar csv");
		Util.exportarResultadoCsv(resultados, "yelp_resultados_seleccion_by_scoring_grafo_visitas_ponderacion_scoring_mas10");
		log.info("FIN - evaluacion seleccion by scoring en redes ponderaciones scorings");
	}


}
