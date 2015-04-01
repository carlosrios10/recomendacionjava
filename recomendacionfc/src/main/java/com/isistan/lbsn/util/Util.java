package com.isistan.lbsn.util;

import java.util.ArrayList;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.recomendacionfc.Resultado;

public class Util {
	public static void exportarResultadoCsv(final ArrayList<Resultado> resultados, String fileName) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/resultados/"+fileName+".csv", new CSVWriteProc() {
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
