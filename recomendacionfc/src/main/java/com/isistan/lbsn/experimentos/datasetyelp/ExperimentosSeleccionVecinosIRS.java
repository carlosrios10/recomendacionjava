
package com.isistan.lbsn.experimentos.datasetyelp;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.esquema.EvaluacionEsquema;
import com.isistan.lbsn.esquema.EvaluacionEsquemaIRS;
import com.isistan.lbsn.experimentos.dataset2.Experimento1;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.util.Util;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;

public class ExperimentosSeleccionVecinosIRS {

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
//
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));	
//		
//		
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.PEARSON,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));	
		
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
configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
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
//configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
//ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));

//
//		configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(20,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(50,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(100,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
//		configuraciones.add(new Configuracion(150,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(250,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
//		configuraciones.add(new Configuracion(300,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD_ZONA,-1,-1,
//		ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));		
		
				 log.info("Inicia evaluacion IRS");
				 EvaluacionEsquemaIRS esquema =  new EvaluacionEsquemaIRS();
				 resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN);
				 log.info("Fin evaluacion IRS");
				 log.info("Exportar csv");
				 Util.exportarResultadoCsv(resultados, "yelp_resultados_irs_red_social_one_state");
				 log.info("FIN");
				 
				 
	}


}
