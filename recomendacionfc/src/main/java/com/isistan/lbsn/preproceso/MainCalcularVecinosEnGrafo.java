package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.scoring.Scoring;
import com.isistan.lbsn.scoring.ScoringCosenoNetwork;
import com.isistan.lbsn.vencindario.NearestNUserNeighborhoodByScoring;
import com.isistan.lbsn.vencindario.UserNeighborhoodAux;

public class MainCalcularVecinosEnGrafo {
	private static final String PATH_SOLAPAMIENTO = "C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/similitudes-scoring/";
	private static final String PATH_RESULTADO = MyProperties.getInstance().getProperty("resultados");

	public static void main(String[] args) {
		try {
			System.out.println("INICIO - MainCalcularVecinos -");

			DataModel ratingModelEvaluar = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingevaluar")));
			DataModel ratingModelTotal = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			GrafoModel grafoModel = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
		//	UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			
			Scoring scoring = new ScoringCosenoNetwork(grafoModel);
			NearestNUserNeighborhoodByScoring userNeighborhood = new NearestNUserNeighborhoodByScoring(300, scoring, itemModel, ratingModelTotal);
			
			String resulPath = MyProperties.getInstance().getProperty("resultados")+"similitudes/vecinos_grafo_visitas_test.csv";
			calcularVecindario(ratingModelEvaluar,ratingModelTotal,userNeighborhood,resulPath,scoring);
			System.out.println("FIN - MainCalcularVecinos -");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}

}
	
	private static void calcularVecindario(DataModel modelEvaluar,DataModel modelTotal,
			UserNeighborhoodAux userNeighborhood,String nombreArchivo, Scoring scoring) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Vecindario");
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), ',',CSVWriter.NO_QUOTE_CHARACTER);
		LongPrimitiveIterator usersIterable = modelEvaluar.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			System.out.println(idUser);
			String resul = idUser+"";
			long[] vecinos = userNeighborhood.getUserNeighborhood(idUser,-1);
			for (int i = 0; i < vecinos.length; i++) {
				double score  = scoring.getScoring(idUser, vecinos[i], -1);
				resul = resul + "#"+vecinos[i]+"#"+score;
			}
		     String[] entries = resul.split("#"); 
		     writer.writeNext(entries);
		
			}
		writer.close();
		System.out.println("Inicia-Calcular Vecindario" );
		}
	
}	
