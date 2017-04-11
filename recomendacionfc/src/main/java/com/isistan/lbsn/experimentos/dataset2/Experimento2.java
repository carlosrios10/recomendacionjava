package com.isistan.lbsn.experimentos.dataset2;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.esquema.EvaluacionEsquema;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.util.Util;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;

public class Experimento2 {
	private static final double PORCENTAJE_TRAIN = 0.7;
	private static final Logger log = LoggerFactory.getLogger(Experimento1.class);
	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.ONLY_SCORE));
//		
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED_HATED,AgregationType.ONLY_SCORE));
//
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.CERCANIA_USUARIO_USUARIO,AgregationType.ONLY_SCORE));
//		
//
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_ABSTRACCION_CATEGORY,AgregationType.ONLY_SCORE));
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
//				ScoringType.SCORING_OVERLAP_BY_CATEGORY,AgregationType.ONLY_SCORE));
//			
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_COSENO_NETWORK,AgregationType.ONLY_SCORE));

		
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		
		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));		
		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));
		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_BY_SCORING,-1,-1,
				ScoringType.SCORING_JACCARD_NETWORK,AgregationType.ONLY_SCORE));

		
		log.info("Inicia evaluacion Experimento2");
		EvaluacionEsquema esquema =  new EvaluacionEsquema();
		resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN);
		log.info("Fin evaluacion");
		log.info("Exportar csv");
		Util.exportarResultadoCsv(resultados, "resultados_UserNY_Red_Visitas_matriz_sentimiento_scoring_varios_5");

		log.info("FIN");

	}

}
