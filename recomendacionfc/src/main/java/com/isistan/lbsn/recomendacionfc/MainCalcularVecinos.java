package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithm.SimAlg;
import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;

public class MainCalcularVecinos {

	public static void main(String[] args) {
		
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		
	    configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.1,TypeNeigh.THRESHOLD));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.3,TypeNeigh.THRESHOLD));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.6,TypeNeigh.THRESHOLD));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.7,TypeNeigh.THRESHOLD));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.8,TypeNeigh.THRESHOLD));
		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.95,TypeNeigh.THRESHOLD));
		
		DataModel model;
		FriendsDataModel fmodel;
		try {
			ArrayList<ResultadoVecino> resultadosVecino = new ArrayList<ResultadoVecino>();
			model = new FileDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/ratingsMeanReducido.csv")));
			fmodel = new FriendsDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/redSocialReducida.csv")));
			for (Configuracion configuracion : configuraciones) {
				UserSimilarity sim = SimilarityAlgorithm.build(model, configuracion.getSimAlg());
				UserNeighborhood neighborhood = TypeNeighborhood.build(sim, model, configuracion.getTypeNeigh(),
												configuracion.getNeighSize(), configuracion.getThreshold(),fmodel);
				 calcularNumeroVecinos(model, neighborhood, sim, resultadosVecino,configuracion);
				
			}
			exportarCsvUsuariosVecinos(resultadosVecino);
			System.out.println("FIN");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	private static void exportarCsvUsuariosVecinos(final ArrayList<ResultadoVecino> resultados) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/usuariosVecinosL.csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("IdUsuario","CantVecinos","Threshold");
		        for (ResultadoVecino resultado : resultados) {
		        	out.writeNext(
		        				Long.toString(resultado.getIdUsuario()),
		        				Integer.toString(resultado.getCantiddadVecinos()),
		        				Double.toString(resultado.getConfig().getThreshold())
		        				);
				}
		        
		   }
		});
		
	}
	private static void calcularNumeroVecinos(DataModel model, UserNeighborhood neighborhood ,UserSimilarity sim,
			ArrayList<ResultadoVecino> resultadosVecinos,Configuracion config ) throws TasteException{
		LongPrimitiveIterator usersIterable = model.getUserIDs();
	
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			resultadosVecinos.add(new ResultadoVecino(idUser, neighborhood.getUserNeighborhood(idUser).length, config));
			}
	}


}
