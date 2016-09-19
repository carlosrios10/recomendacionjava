package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.Item;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.util.Util;

public class CalcularDistanciasUsuario {
	private static final String PATH_DISTANCIA = "C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/distancias/";
	public static void main(String[] args) {
		try {
			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
		//	calcularDistanciaEntreItems(itemModel,ratingModel,userModel,PATH_DISTANCIA+"distancia_item_item_NY2mas.csv");
			calcularDistanciaEntreCasaItems(itemModel,ratingModel,userModel,PATH_DISTANCIA+"distancia_casa_item_NY2mas.csv");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}
		
	}



//	private static void calcularDistanciasTotales(DataModel ratingModel,
//			UserModel userModel, ItemModel itemModel) throws TasteException {
//		Iterator<Long> iterador = userModel.getMultiMap().keySet().iterator();
//		while (iterador.hasNext()){
//			Long usuarioId =  iterador.next();
//			User user = userModel.getUser(usuarioId);
//			FastIDSet ratingsIds =  ratingModel.getItemIDsFromUser(usuarioId);
//			double distanciaTotalRecorrida = calcularDistanciaTotalRecorrida(itemModel, user, ratingsIds);
//			double distanciaTotalEntreItems = calcularDistanciaTotalEntreItems(itemModel, ratingsIds);
//			user.setCantidadDeItem(ratingsIds.size());
//			user.setDistanciaTotalRecorrida(distanciaTotalRecorrida);
//			user.setDistanciaPromedioRecorrida(distanciaTotalRecorrida/ratingsIds.size());
//			user.setDistanciaTotalEntreItems(distanciaTotalEntreItems);
//			System.out.println(user.toString());
//		}
//		Util.exportarUsuarioDistanciasCsv(userModel, "usuarios_distancias");
//	}

	private static void calcularDistanciaEntreCasaItems(ItemModel itemModel,
			DataModel ratingModel, UserModel userModel, String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Distancia-Casa-Item");
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t');
		Iterator<Long> iterador = userModel.getMultiMap().keySet().iterator();
		while (iterador.hasNext()){
		Long usuarioId =  iterador.next();
		User user = userModel.getUser(usuarioId);
		FastIDSet ratingsIds =  ratingModel.getItemIDsFromUser(usuarioId);
		LongPrimitiveIterator iterItem =  ratingsIds.iterator();
		while(iterItem.hasNext()){
			Long idItem = iterItem.next();
			Item item = itemModel.getItem(idItem);
			double distancia = Util.distFrom(Double.parseDouble(user.getLatitud()), 
							Double.parseDouble(user.getLongitud()), 
							Double.parseDouble(item.getLatitud()), 
							Double.parseDouble(item.getLongitud()));
			
			 String resul = usuarioId+"#"+item+"#"+distancia;
		     String[] entries = resul.split("#"); 
		     writer.writeNext(entries);
			
		}
		
		}
		writer.close();
		System.out.println("Fin-Calcular-Distancia Casa-Item");
		
	}

	private static void calcularDistanciaEntreItems(ItemModel itemModel,DataModel ratingModel,
			UserModel userModel,String nombreArchivo) throws IOException, TasteException {
		System.out.println("Inicia-Calcular Distancia I-I");
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t');
		Iterator<Long> iterador = userModel.getMultiMap().keySet().iterator();
		while (iterador.hasNext()){
		Long usuarioId =  iterador.next();
		FastIDSet ratingsIds =  ratingModel.getItemIDsFromUser(usuarioId);
		LongPrimitiveIterator iterItem2 = ratingsIds.iterator();
		LongPrimitiveIterator iterItem3 = ratingsIds.iterator();
		int pos = 0;
		while( iterItem2.hasNext()){
			pos++;
			iterItem3.skip(pos);
			Long idItem1 = iterItem2.next();
			Item item1 = itemModel.getItem(idItem1);
			while(iterItem3.hasNext()){
				Long idItem2 = iterItem3.next();
				Item item2 = itemModel.getItem(idItem2);
				double distanciaEntreItems = Util.distFrom(Double.parseDouble(item1.getLatitud()), 
								Double.parseDouble(item1.getLongitud()), 
								Double.parseDouble(item2.getLatitud()), 
								Double.parseDouble(item2.getLongitud()));
				
				
				 String resul = usuarioId+"#"+idItem1+"#"+idItem2+"#"+distanciaEntreItems;
			     String[] entries = resul.split("#"); 
			     writer.writeNext(entries);
			
			}
		}
		
		}
		writer.close();
		System.out.println("Fin-Calcular-Distancia I-I");
	}

//	private static double calcularDistanciaTotalEntreItems(ItemModel itemModel,
//			FastIDSet ratingsIds) {
//		LongPrimitiveIterator iterItem2 = ratingsIds.iterator();
//		LongPrimitiveIterator iterItem3 = ratingsIds.iterator();
//		int pos = 0;
//		double distanciaTotalEntreItems = 0;
//		while( iterItem2.hasNext()){
//			pos++;
//			iterItem3.skip(pos);
//			Long idItem1 = iterItem2.next();
//			Item item1 = itemModel.getItem(idItem1);
//			while(iterItem3.hasNext()){
//				Long idItem2 = iterItem3.next();
//				Item item2 = itemModel.getItem(idItem2);
//				distanciaTotalEntreItems = distanciaTotalEntreItems +  
//						Util.distFrom(Double.parseDouble(item1.getLatitud()), 
//								Double.parseDouble(item1.getLongitud()), 
//								Double.parseDouble(item2.getLatitud()), 
//								Double.parseDouble(item2.getLongitud()));
//			
//			}
//		}
//		return distanciaTotalEntreItems;
//	}

//	private static double calcularDistanciaTotalRecorrida(ItemModel itemModel,
//			User user, FastIDSet ratingsIds) {
//		LongPrimitiveIterator iterItem =  ratingsIds.iterator();
//		double distanciaTotalRecorrida = 0;
//		while(iterItem.hasNext()){
//			Long idItem = iterItem.next();
//			Item item = itemModel.getItem(idItem);
//			distanciaTotalRecorrida = distanciaTotalRecorrida +  
//					Util.distFrom(Double.parseDouble(user.getLatitud()), 
//							Double.parseDouble(user.getLongitud()), 
//							Double.parseDouble(item.getLatitud()), 
//							Double.parseDouble(item.getLongitud()));
//			
//		}
//		return distanciaTotalRecorrida;
//	}

}
