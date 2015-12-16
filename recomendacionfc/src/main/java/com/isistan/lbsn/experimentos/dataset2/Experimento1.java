package com.isistan.lbsn.experimentos.dataset2;

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
		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
				configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
				configuraciones.add(new Configuracion(120,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
				configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.K_NEIGHBORHOOD,-1,-1,
				ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
				
				 EvaluacionEsquema esquema =  new EvaluacionEsquema();
				 System.out.println("Inicia evaluacion");
				 resultados = esquema.evaluar(configuraciones);
				 System.out.println("Fin evaluacion");
				 System.out.println("Exportar csv");
				 
				 Util.exportarResultadoCsv(resultados, "resultadosUBCF_Base");
				 System.out.println("FIN");
	}

}
