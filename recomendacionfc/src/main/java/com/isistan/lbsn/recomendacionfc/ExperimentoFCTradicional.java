package com.isistan.lbsn.recomendacionfc;

import java.util.ArrayList;

import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;
import com.isistan.lbsn.util.*;

public class ExperimentoFCTradicional {

	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD,0.5,0.5));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD,0.5,0.5));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD,0.5,0.5));

		 EvaluacionEsquema esquema =  new EvaluacionEsquema();
		 System.out.println("Inicia evaluacion");
		 resultados = esquema.evaluar(configuraciones);
		 System.out.println("Fin evaluacion");
		 System.out.println("Exportar csv");
		 Util.exportarResultadoCsv(resultados,"fcTradiconal");
		 System.out.println("FIN");
	}

}
