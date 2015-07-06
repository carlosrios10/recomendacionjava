package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;
import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioUsuario;
import com.isistan.lbsn.similitudestructural.GrafoDataModel;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class MainCalcularVecinos {

	public static void main(String[] args) {
		try {
			ArrayList<ResultadoVecino> resultadosVecino = new ArrayList<ResultadoVecino>();
			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			DataModel ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			ScoringCercaniaUsuarioUsuario scoring = new ScoringCercaniaUsuarioUsuario(null, null, userModel, itemModel);
		    exportarCsvUsuariosSimilitud( calcularMatrizSimilitud(ratingModel,scoring),"distanciaskm");
				 //calcaularSolapamiento(model);
			 	//exportarCsvUsuariosVecinos(resultadosVecino);
			System.out.println("FIN");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	private static ArrayList<ResultadoSimilitud>  calcaularSolapamiento(DataModel model) throws TasteException{
	
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		int cant=0;
		HashMap<Double, Integer> resumenSola =  new HashMap<Double, Integer>();
		TanimotoCoefficientSimilarity similarity = new TanimotoCoefficientSimilarity(model);
		//usersIterable.skip(cant);
		while (usersIterable.hasNext()) {
			//ArrayList<ResultadoSimilitud> resultado =  new ArrayList<ResultadoSimilitud>();
			Long idUser =  usersIterable.next();
			cant++;
			System.out.println("1 :" + idUser + "cant :" + cant);
			LongPrimitiveIterator usersIterable2 = model.getUserIDs();
			usersIterable2.skip(cant);
			while (usersIterable2.hasNext()) {
				Long idUser2 = usersIterable2.next();

//			    FastIDSet xPrefs = model.getItemIDsFromUser(idUser);
//			    FastIDSet yPrefs = model.getItemIDsFromUser(idUser2);
//			    int xPrefsSize = xPrefs.size();
//			    int yPrefsSize = yPrefs.size();
//			    int intersectionSize =  xPrefsSize < yPrefsSize ? yPrefs.intersectionSize(xPrefs) : xPrefs.intersectionSize(yPrefs);
//			    int unionSize = xPrefsSize + yPrefsSize - intersectionSize;
//			    double porcentaje = (double) intersectionSize / (double) unionSize;
			    
			    double sim = similarity.userSimilarity(idUser, idUser2);
			    
			    if(resumenSola.get(sim)==null){
			    	resumenSola.put(sim, 1);
			    }else{
			    	int cantidad = resumenSola.get(sim);
			    	cantidad++;
			    	resumenSola.put(sim, cantidad);
			    }
			//    resultado.add(new ResultadoSimilitud(idUser, idUser2, intersectionSize));
				
			}
		//	exportarCsvUsuariosSimilitud(resultado, Integer.toString(cant));
			
			}
		exportarCsvSolapamiento(resumenSola);
		return null;
	}
	
	private static void calcularNumeroVecinos(DataModel model, UserNeighborhood neighborhood ,UserSimilarity sim,
			ArrayList<ResultadoVecino> resultadosVecinos,Configuracion config ) throws TasteException{
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			resultadosVecinos.add(new ResultadoVecino(idUser, neighborhood.getUserNeighborhood(idUser).length, config));
			}
	}
	private static ArrayList<ResultadoSimilitud> calcularMatrizSimilitud(DataModel model,
			UserSimilarity sim) throws TasteException {
		ArrayList<ResultadoSimilitud> resultado =  new ArrayList<ResultadoSimilitud>();
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		int cant=0;
		//usersIterable.skip(cant);
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			cant++;
			System.out.println("1 :" + idUser + "cant :" + cant);
			LongPrimitiveIterator usersIterable2 = model.getUserIDs();
			usersIterable2.skip(cant);
			while (usersIterable2.hasNext()) {
				Long idUser2 = usersIterable2.next();
				//System.out.println("2 :" + idUser2);
				double simValue = sim.userSimilarity(idUser, idUser2);
				resultado.add(new ResultadoSimilitud(idUser, idUser2, simValue));
				
			}
			System.out.println("FIN 1 :" + usersIterable2.hasNext());
			}
		
		return resultado;
	}
	
	private static void exportarCsvSolapamiento(final HashMap<Double,Integer> resultados) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/solap_NY_Porcentaje_Freq.csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        Iterator iter =  resultados.keySet().iterator();
		    	out.writeNext("overlap","frecuencia");
		        for (Iterator iterator = resultados.keySet().iterator(); iterator.hasNext();) {
					Double id =  (Double) iterator.next();
					Integer freq =  resultados.get(id);
		        	out.writeNext( 
		        			Double.toString(id),
	        				Long.toString(freq)
	        				);
				}

		        
		   }
		});
		
	}
	private static void exportarCsvUsuariosSimilitud(final ArrayList<ResultadoSimilitud> resultados,String file) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/usuariosSolapa/"+file+ ".csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        out.writeNext("IdUsuario1","IdUsuario2","Similitud");
		        for (ResultadoSimilitud resultado : resultados) {
		        	out.writeNext(
		        				Long.toString(resultado.getIdUser1()),
		        				Long.toString(resultado.getIdUser2()),
		        				Double.toString(resultado.getSimValue())
		        				);
				}
		        
		   }
		});
		
	}

	private static void exportarCsvUsuariosVecinos(final ArrayList<ResultadoVecino> resultados) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/usuariosVecinosJacc.csv", new CSVWriteProc() {
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



}
