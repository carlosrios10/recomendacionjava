package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.recomendacionfc.Item;
import com.isistan.lbsn.recomendacionfc.ItemModel;
import com.isistan.lbsn.recomendacionfc.User;
import com.isistan.lbsn.recomendacionfc.UserModel;
import com.isistan.lbsn.util.Util;

public class CalcularDistanciasUsuario {
	public static void main(String[] args) {
		try {
			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			Iterator<Long> iterador = userModel.getMultiMap().keySet().iterator();
			while (iterador.hasNext()){
				Long usuarioId =  iterador.next();
				User user = userModel.getUser(usuarioId);
				FastIDSet ratingsIds =  ratingModel.getItemIDsFromUser(usuarioId);
				double distanciaTotalRecorrida = calcularDistanciaTotalRecorrida(itemModel, user, ratingsIds);
				double distanciaTotalEntreItems = calcularDistanciaTotalEntreItems(itemModel, ratingsIds);
				user.setCantidadDeItem(ratingsIds.size());
				user.setDistanciaTotalRecorrida(distanciaTotalRecorrida);
				user.setDistanciaPromedioRecorrida(distanciaTotalRecorrida/ratingsIds.size());
				user.setDistanciaTotalEntreItems(distanciaTotalEntreItems);
				System.out.println(user.toString());
			}
			Util.exportarUsuarioDistanciasCsv(userModel, "usuarios_distancias");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}
		
	}

	private static double calcularDistanciaTotalEntreItems(ItemModel itemModel,
			FastIDSet ratingsIds) {
		LongPrimitiveIterator iterItem2 = ratingsIds.iterator();
		LongPrimitiveIterator iterItem3 = ratingsIds.iterator();
		int pos = 0;
		double distanciaTotalEntreItems = 0;
		while( iterItem2.hasNext()){
			pos++;
			iterItem3.skip(pos);
			Long idItem1 = iterItem2.next();
			Item item1 = itemModel.getItem(idItem1);
			while(iterItem3.hasNext()){
				Long idItem2 = iterItem3.next();
				Item item2 = itemModel.getItem(idItem2);
				distanciaTotalEntreItems = distanciaTotalEntreItems +  
						Util.distFrom(Double.parseDouble(item1.getLatitud()), 
								Double.parseDouble(item1.getLongitud()), 
								Double.parseDouble(item2.getLatitud()), 
								Double.parseDouble(item2.getLongitud()));
			
			}
		}
		return distanciaTotalEntreItems;
	}

	private static double calcularDistanciaTotalRecorrida(ItemModel itemModel,
			User user, FastIDSet ratingsIds) {
		LongPrimitiveIterator iterItem =  ratingsIds.iterator();
		double distanciaTotalRecorrida = 0;
		while(iterItem.hasNext()){
			Long idItem = iterItem.next();
			Item item = itemModel.getItem(idItem);
			distanciaTotalRecorrida = distanciaTotalRecorrida +  
					Util.distFrom(Double.parseDouble(user.getLatitud()), 
							Double.parseDouble(user.getLongitud()), 
							Double.parseDouble(item.getLatitud()), 
							Double.parseDouble(item.getLongitud()));
			
		}
		return distanciaTotalRecorrida;
	}

}
