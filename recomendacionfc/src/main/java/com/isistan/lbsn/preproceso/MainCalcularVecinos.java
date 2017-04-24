package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.ResultadoSimilitud;
import com.isistan.lbsn.recomendacionfc.ResultadoVecino;
import com.isistan.lbsn.scoring.ScoringCosenoNetwork;
import com.isistan.lbsn.scoring.ScoringLR;
import com.isistan.lbsn.scoring.ScoringOverlap;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

public class MainCalcularVecinos {

	private static final double UMBRAL = 0.15;
	private static final String PATH_SOLAPAMIENTO = "C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/similitudes-scoring/";
	private static final String PATH_RESULTADO = MyProperties.getInstance().getProperty("resultadosprocesar");
	public static void main(String[] args) {
		try {
			System.out.println("INICIO - MainCalcularVecinos -");
//			ArrayList<ResultadoVecino> resultadosVecino = new ArrayList<ResultadoVecino>();
//			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
//			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			DataModel	ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar")));
			DataModel	ratingModel2 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar2")));
			DataModel	ratingModel3 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar3")));
			DataModel	ratingModel4 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar4")));
//			DataModel ratingModelEval = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar2")));
			GrafoModel grafoModel = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
			//			ScoringOverlapLikedAndHated scoring3 = new ScoringOverlapLikedAndHated(null,ratingModel);
//			ScoringOverlapLiked scoring4 = new ScoringOverlapLiked(null,ratingModel,null);
//			ScoringCercaniaUsuarioUsuario scoring = new ScoringCercaniaUsuarioUsuario(null, null, userModel, itemModel);
			
//			ScoringOverlapLiked scoringOverlapLiked_3 = new ScoringOverlapLiked(null,ratingModel,3.0);
//			ScoringOverlapLiked scoringOverlapLied_mean = new ScoringOverlapLiked(null,ratingModel,null);
//			UserSimilarity simlilitudCoseno  = new UncenteredCosineSimilarity(ratingModel);
//			UserSimilarity similitudEuclidea = new EuclideanDistanceSimilarity(ratingModel);
//			UserSimilarity similitudPearson = new PearsonCorrelationSimilarity(ratingModel);
//			UserSimilarity scoringOverlap  = new ScoringOverlap(ratingModel);
//			UserSimilarity scoringOverlapSinNormalizar  = new ScoringOverlapSinNormalizar(ratingModel);
//			UserSimilarity scoringLiked = new ScoringOverlapLiked(null,ratingModel,null);
//			UserSimilarity scoringCosenoNetwork = new ScoringCosenoNetwork(grafoModel); 
//			ItemSimilarity ItemSimlilitudCoseno = new UncenteredCosineSimilarity(ratingModel);
//			ItemSimilarity ItemSimlilitudTanimoto = new TanimotoCoefficientSimilarity(ratingModel);
			
			UserSimilarity userVisitas  = new ScoringOverlap(ratingModel);
			UserSimilarity userVisitasWeekDayName  = new ScoringOverlap(ratingModel2);
			UserSimilarity userVisitasCateWeekOrWeekend  = new ScoringOverlap(ratingModel3);
			UserSimilarity userVisitasCateWeekDayName  = new ScoringOverlap(ratingModel4);
			UserSimilarity userCosenoNet  = new ScoringCosenoNetwork(grafoModel); 
			
			UserSimilarity scoringLR = new ScoringLR(
					userVisitas, 
					userVisitasWeekDayName, 
					userVisitasCateWeekOrWeekend, 
					userVisitasCateWeekDayName, 
					userCosenoNet);
			
			String resulPath = MyProperties.getInstance().getProperty("resultadosprocesar");
			//calcularMatrizSimilitudConSample(ratingModelEval,ratingModel,scoringOverlap,PATH_RESULTADO+"yelp_red_cantidad_visitas_comunes_normalizado_one_state.csv");
			//calcularMatrizSimilitudItemItem(ratingModel,ItemSimlilitudTanimoto,MyProperties.getInstance().getProperty("resultados")+"ItemSimlilitudTanimoto"+ ".csv");
			calcularMatrizSimilitud(ratingModel,scoringLR,PATH_RESULTADO+"yelp_red_relacion_LR_user_user_one_state.csv");
			//calcularMatrizSimilitud(ratingModelEval,scoringLR,PATH_RESULTADO+"goodness_yelp_grafo_coseno_one_state.csv");
			System.out.println("FIN - MainCalcularVecinos -");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}


	}

	private static void  calcaularSolapamiento(DataModel model,
			UserSimilarity userSimilarity,String nombreArchivo) 
			throws TasteException, IOException{
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t');
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			int cantidadSolpa = 0;
			LongPrimitiveIterator usersIterable2 = model.getUserIDs();
			while (usersIterable2.hasNext()) {
				Long idUser2 = usersIterable2.next();
				if(idUser2!=idUser){
					double sim = userSimilarity.userSimilarity(idUser, idUser2);
					if (!Double.isNaN(sim) && sim >0) {
						cantidadSolpa++;
					}
				}
			}
			
			 String resul = idUser+"#"+cantidadSolpa;
		     String[] entries = resul.split("#"); 
		     writer.writeNext(entries);
			
			}
		writer.close();
	}
	
	private static void calcularNumeroVecinos(DataModel model, UserNeighborhood neighborhood ,UserSimilarity sim,
			ArrayList<ResultadoVecino> resultadosVecinos,Configuracion config ) throws TasteException{
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			resultadosVecinos.add(new ResultadoVecino(idUser, neighborhood.getUserNeighborhood(idUser).length, config));
			}
	}
	
	private static void calcularMatrizSimilitudConSample(DataModel modelEvaluar,DataModel modelTotal,
			UserSimilarity sim,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Simlitud-sample");
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t',CSVWriter.NO_QUOTE_CHARACTER);
		LongPrimitiveIterator usersIterable = modelEvaluar.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			LongPrimitiveIterator usersIterable2 = modelTotal.getUserIDs();
			while (usersIterable2.hasNext()) {
				 Long idUser2 = usersIterable2.next();
				 if ( idUser2!=idUser ){
				 double simValue = sim.userSimilarity(idUser, idUser2);
				 if (!Double.isNaN(simValue) && simValue >0) {
					 String resul = idUser+"#"+idUser2+"#"+simValue;
				     String[] entries = resul.split("#"); 
				     writer.writeNext(entries);
			      }
				 }
			}
			}
		writer.close();
		System.out.println("Fin-Calcular Matriz Simlitud-sample" );
		}
	private static void calcularMatrizSimilitud(DataModel model,
			UserSimilarity sim,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Simlitud ");
		System.out.println("Umbral :  " +UMBRAL);
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t',CSVWriter.NO_QUOTE_CHARACTER);
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
				 if (!Double.isNaN(simValue) && simValue >UMBRAL) {
					 String resul = idUser+"#"+idUser2+"#"+simValue;
					// System.out.println(resul);
				     String[] entries = resul.split("#"); 
				     writer.writeNext(entries);
			       }
			}
			}
		writer.close();
		System.out.println("Fin-Calcular Matriz Simlitud" );
		}
	private static void calcularMatrizSimilitudItemItem(DataModel model,
			ItemSimilarity sim,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Simlitud");
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t');
		LongPrimitiveIterator itemIterable = model.getItemIDs();
		int cant=0;
		while (itemIterable.hasNext()) {
			Long idItem =  itemIterable.next();
			cant++;
			LongPrimitiveIterator itemIterable2 = model.getItemIDs();
			itemIterable2.skip(cant);
			while (itemIterable2.hasNext()) {
				 Long idItem2 = itemIterable2.next();
				 double simValue = sim.itemSimilarity(idItem, idItem2);
				// if (!Double.isNaN(simValue) && simValue >0) {
					 String resul = idItem+"#"+idItem2+"#"+simValue;
				     String[] entries = resul.split("#"); 
				     writer.writeNext(entries);
			     //   }
			}
			}
		writer.close();
		System.out.println("Fin-Calcular Matriz Simlitud" );
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
		
		csv.write(PATH_SOLAPAMIENTO+file+ ".csv", new CSVWriteProc() {
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
