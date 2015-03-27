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
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.isistan.lbsn.recomendacionfc.TypeNeighborhood.TypeNeigh;
import com.isistan.lbsn.similitudestructural.GrafoDataModel;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class MainCalcularVecinos {

	public static void main(String[] args) {
		
	      UndirectedSparseGraph<Long, Integer> grafo = new UndirectedSparseGraph<Long, Integer>();
	      // Add some vertices. From above we defined these to be type Integer.
	      grafo.addVertex(1l);
	      grafo.addVertex(2l);
	      grafo.addVertex(3l);
	      grafo.addVertex(4l); 
	      grafo.addVertex(5l); 
	      grafo.addVertex(6l);

	      grafo.addEdge((Integer)1, 1l, 2l); 
	      grafo.addEdge((Integer)2, 1l, 3l);
	      grafo.addEdge((Integer)3, 2l, 3l);
	      grafo.addEdge((Integer)4, 2l, 4l);
	      grafo.addEdge((Integer)5, 3l, 5l);
	      grafo.addEdge((Integer)6, 4l, 6l);
	      grafo.addEdge((Integer)7, 5l, 6l);
		
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		
//	    configuraciones.add(new Configuracion(10,SimilarityAlgorithm.SimAlg.PEARSON,0.1,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.3,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.6,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.7,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.8,TypeNeigh.THRESHOLD));
//		configuraciones.add(new Configuracion(-1,SimilarityAlgorithm.SimAlg.LOGLIKE,0.95,TypeNeigh.THRESHOLD));
		
		DataModel model;
		FriendsDataModel fmodel;
		try {
			ArrayList<ResultadoVecino> resultadosVecino = new ArrayList<ResultadoVecino>();
			model = new FileDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/ratingsMeanReducido.csv")));
			fmodel = new FriendsDataModel(new File(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/redSocialReducida.csv")));
			GrafoDataModel gModel = new GrafoDataModel("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/redSocialReducida.graphml");
			for (Configuracion configuracion : configuraciones) {
				UserSimilarity sim = SimilarityAlgorithm.build(model, gModel,configuracion.getSimAlg(),0,0);
				UserNeighborhood neighborhood = TypeNeighborhood.build(sim, model, configuracion.getTypeNeigh(),
												configuracion.getNeighSize(), configuracion.getThreshold(),fmodel);
			//	 calcularNumeroVecinos(model, neighborhood, sim, resultadosVecino,configuracion);
				
			//	 exportarCsvUsuariosSimilitud( calcularMatrizSimilitud(model,sim));
				  calcaularSolapamiento(model);
			}
		//	exportarCsvUsuariosVecinos(resultadosVecino);
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
		HashMap<Integer, Integer> resumenSola =  new HashMap<Integer, Integer>();
		
		//usersIterable.skip(cant);
		while (usersIterable.hasNext()) {
			ArrayList<ResultadoSimilitud> resultado =  new ArrayList<ResultadoSimilitud>();
			Long idUser =  usersIterable.next();
			cant++;
			System.out.println("1 :" + idUser + "cant :" + cant);
			LongPrimitiveIterator usersIterable2 = model.getUserIDs();
			usersIterable2.skip(cant);
			while (usersIterable2.hasNext()) {
				Long idUser2 = usersIterable2.next();

			    FastIDSet xPrefs = model.getItemIDsFromUser(idUser);
			    FastIDSet yPrefs = model.getItemIDsFromUser(idUser2);
			    int xPrefsSize = xPrefs.size();
			    int yPrefsSize = yPrefs.size();
			    int intersectionSize =  xPrefsSize < yPrefsSize ? yPrefs.intersectionSize(xPrefs) : xPrefs.intersectionSize(yPrefs);
				
			    if(resumenSola.get(intersectionSize)==null){
			    	resumenSola.put(intersectionSize, 1);
			    }else{
			    	int cantidad = resumenSola.get(intersectionSize);
			    	cantidad++;
			    	resumenSola.put(intersectionSize, cantidad);
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
		while (usersIterable.hasNext() && cant<=100) {
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
	
	private static void exportarCsvSolapamiento(final HashMap<Integer,Integer> resultados) {
		CSV csv = CSV
			    .separator(',')  // delimiter of fields
			    .quote('"')      // quote character
			    .create();       // new instance is immutable
		
		csv.write("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/solapFreq.csv", new CSVWriteProc() {
		    public void process(CSVWriter out) {
		        Iterator iter =  resultados.keySet().iterator();
		    	out.writeNext("overlap","frecuencia");
		        for (Iterator iterator = resultados.keySet().iterator(); iterator.hasNext();) {
					Integer id =  (Integer) iterator.next();
					Integer freq =  resultados.get(id);
		        	out.writeNext(
	        				Long.toString(id),
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
