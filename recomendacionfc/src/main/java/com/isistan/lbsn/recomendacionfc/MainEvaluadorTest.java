package com.isistan.lbsn.recomendacionfc;

import java.util.ArrayList;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;



public class MainEvaluadorTest {

	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();

		/*	configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.PEARSON,0.1));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.PEARSON,0.3));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.PEARSON,0.5));
		configuraciones.add(new Configuracion(90,SimilarityAlgorithm.SimAlg.PEARSON,0.7));
		configuraciones.add(new Configuracion(125,SimilarityAlgorithm.SimAlg.PEARSON,0.9));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.PEARSON,0.9));
		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.EUCLIDEAN,0.1));
		configuraciones.add(new Configuracion(20,SimilarityAlgorithm.SimAlg.EUCLIDEAN,0.3));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.EUCLIDEAN,0.5));
		configuraciones.add(new Configuracion(90,SimilarityAlgorithm.SimAlg.EUCLIDEAN,0.7));
		configuraciones.add(new Configuracion(125,SimilarityAlgorithm.SimAlg.EUCLIDEAN,0.9));
		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.EUCLIDEAN,0.9));*/
		

		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.1,TypeNeigh.THRESHOLD));
		
		 EvaluacionEsquema esquema =  new EvaluacionEsquema();
		 System.out.println("Inicia evaluacion");
		 resultados = esquema.evaluar(configuraciones);
		 System.out.println("Fin evaluacion");
		 System.out.println("Exportar csv");
		 exportarCsv(resultados);
		 System.out.println("FIN");

	}

	private static void exportarCsv(final ArrayList<Resultado> resultados) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/resultados/resultadosUBCF.csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("Similitud","TVecinos","Nvecinos","Threshold","Mae","Rms");
		        for (Resultado resultado : resultados) {
		        	out.writeNext(
		        			    resultado.getConfiguracion().getSimAlg().toString(),
		        			    resultado.getConfiguracion().getTypeNeigh().toString(),
		        				Integer.toString(resultado.getConfiguracion().getNeighSize()),
		        				Double.toString(resultado.getConfiguracion().getThreshold()),
		        				Double.toString(resultado.getScoreMae()),
		        				Double.toString(resultado.getScoreRms())
		        				
		        				);
				}
		        
		   }
		});
		
	}
	
	

}
