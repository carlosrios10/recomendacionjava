package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.FriendsDataModel;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.similitudestructural.JaccardNetwork;

public class CalcularMedidasRed {
	private static final String PATH_SOLAPAMIENTO = "C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/similitudes-scoring/";


	public static void main(String[] args) {
		try {
			GrafoModel grafoModel =new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafototalgraphml"));
//			GrafoModel grafoModel = new FriendsDataModel(MyProperties.getInstance().getProperty("databasegrafo"));
			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			System.out.println(grafoModel.getGrafo().getVertexCount());
			JaccardNetwork jaccardNetwork = new JaccardNetwork(grafoModel);
			calcularMatrizSimilitud(ratingModel,jaccardNetwork,PATH_SOLAPAMIENTO+"jaccardNetwork"+ ".csv");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}

	}
	
	private static void calcularMatrizSimilitud(DataModel model,
			UserSimilarity sim,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Simlitud");
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t');
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		int cant=0;
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			cant++;
			LongPrimitiveIterator usersIterable2 = model.getUserIDs();
			usersIterable2.skip(cant);
			while (usersIterable2.hasNext()) {
				 Long idUser2 = usersIterable2.next();
				 double simValue = sim.userSimilarity(idUser, idUser2);
				// if (!Double.isNaN(simValue) && simValue >0) {
					 String resul = idUser+"#"+idUser2+"#"+simValue;
				     String[] entries = resul.split("#"); 
				     writer.writeNext(entries);
			     //   }
			}
			}
		writer.close();
		System.out.println("Fin-Calcular Matriz Simlitud" );
		}

}
