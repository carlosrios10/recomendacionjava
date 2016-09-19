package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory.SimAlg;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;



public class MainEvaluadorTest {

	public static void main(String[] args) {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
//
//   	    configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(90,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(125,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_NEIGHBORHOOD));
//		
//	    configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(90,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(125,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS));
//		
//	    configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.3,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENO,0.95,TypeNeigh.THRESHOLD));
//		
//   	    configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(90,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(125,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_NEIGHBORHOOD));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_NEIGHBORHOOD));
//		
//	    configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(30,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(60,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(90,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(125,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_FRIENDS));
//		configuraciones.add(new Configuracion(200,SimilarityAlgorithm.SimAlg.PEARSON,-1,TypeNeigh.K_FRIENDS));
//		
//	    configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSON,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSON,0.3,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSON,0.6,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSON,0.7,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSON,0.8,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSON,0.95,TypeNeigh.THRESHOLD));
		
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.JACCARDNETWORK,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.JACCARDNETWORK,0.3,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.JACCARDNETWORK,0.6,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.JACCARDNETWORK,0.7,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.JACCARDNETWORK,0.8,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.JACCARDNETWORK,0.9,TypeNeigh.THRESHOLD));
//		
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENONETWORK,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENONETWORK,0.3,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENONETWORK,0.6,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENONETWORK,0.7,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENONETWORK,0.8,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COSENONETWORK,0.9,TypeNeigh.THRESHOLD));

//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSONNETWORK,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSONNETWORK,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSONNETWORK,0.6,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSONNETWORK,0.7,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSONNETWORK,0.8,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.PEARSONNETWORK,0.9,TypeNeigh.THRESHOLD));

//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.1,0.9));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.2,0.8));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.3,0.7));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.4,0.6));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.5,0.5));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.6,0.4));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.7,0.3));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.8,0.2));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.COMBINADA,0.8,TypeNeigh.THRESHOLD,0.9,0.1));
		
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.THRESHOLD,-1,-1));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.COSENO,0.7,TypeNeigh.THRESHOLD,-1,-1));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.COSENO,0.8,TypeNeigh.THRESHOLD,-1,-1));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.COSENO,0.9,TypeNeigh.THRESHOLD,-1,-1));
		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS,-1,-1));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS,-1,-1));
		configuraciones.add(new Configuracion(120,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS,-1,-1));
		configuraciones.add(new Configuracion(Integer.MAX_VALUE,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS,-1,-1));
		
		configuraciones.add(new Configuracion(10,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS_FRIENDS,-1,-1));
		configuraciones.add(new Configuracion(60,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS_FRIENDS,-1,-1));
		configuraciones.add(new Configuracion(120,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS_FRIENDS,-1,-1));
		configuraciones.add(new Configuracion(Integer.MAX_VALUE,SimilarityAlgorithmFactory.SimAlg.COSENO,-1,TypeNeigh.K_FRIENDS_FRIENDS,-1,-1));
		
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.6,TypeNeigh.THRESHOLD,-1,-1));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.7,TypeNeigh.THRESHOLD,-1,-1));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.8,TypeNeigh.THRESHOLD,-1,-1));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithmFactory.SimAlg.TANIMOTO,0.9,TypeNeigh.THRESHOLD,-1,-1));

		 EvaluacionEsquema esquema =  new EvaluacionEsquema();
		 System.out.println("Inicia evaluacion");
		 resultados = esquema.evaluar(configuraciones,0.7);
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
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/resultados/resultadosUBCF_PR_NY_UE.csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("Similitud","TVecinos","Nvecinos","Threshold","alfa","beta","Mae","Rms","Precision","Recall");
		        for (Resultado resultado : resultados) {
		        	out.writeNext(
		        			    resultado.getConfiguracion().getSimAlg().toString(),
		        			    resultado.getConfiguracion().getTypeNeigh().toString(),
		        				Integer.toString(resultado.getConfiguracion().getNeighSize()),
		        				Double.toString(resultado.getConfiguracion().getThreshold()),
		        				Double.toString(resultado.getConfiguracion().getAlfa()),
		        				Double.toString(resultado.getConfiguracion().getBeta()),
		        				Double.toString(resultado.getScoreMae()),
		        				Double.toString(resultado.getScoreRms()),
		        				Double.toString(resultado.getScorePrecision()),
		        				Double.toString(resultado.getScoreRecall())
		        				
		        				);
				}
		        
		   }
		});
		
	}
	

	

}
