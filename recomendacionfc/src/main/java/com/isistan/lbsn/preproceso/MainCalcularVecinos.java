package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.collect.Streams;
import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.ResultadoSimilitud;
import com.isistan.lbsn.recomendacionfc.ResultadoVecino;
import com.isistan.lbsn.scoring.ScoringAreFriendsNetwork;
import com.isistan.lbsn.scoring.ScoringCosenoNetwork;
import com.isistan.lbsn.scoring.ScoringDistanciaNetwork;
import com.isistan.lbsn.scoring.ScoringJaccardNetwork;
import com.isistan.lbsn.scoring.ScoringLR;
import com.isistan.lbsn.scoring.ScoringLRModel2;
import com.isistan.lbsn.scoring.ScoringOverlap;
import com.isistan.lbsn.scoring.ScoringOverlapLiked;
import com.isistan.lbsn.scoring.ScoringOverlapLikedAndHated;
import com.isistan.lbsn.scoring.ScoringPearsonNetwork;

public class MainCalcularVecinos {

	private static final double UMBRAL = 0.0;
	private static final String PATH_SOLAPAMIENTO = "C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/similitudes-scoring/";
	private static final String PATH_RESULTADO = MyProperties.getInstance().getProperty("resultadosprocesar");
	public static void main(String[] args) {
		try {
			System.out.println("INICIO - MainCalcularVecinos -");
//			ArrayList<ResultadoVecino> resultadosVecino = new ArrayList<ResultadoVecino>();
//			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
//			ItemModel itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			DataModel	ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
//			DataModel	ratingModel1 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar")));
//			DataModel	ratingModel2 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar2")));
			DataModel	ratingModel3 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar3")));
			DataModel	ratingModel4 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar4")));
			DataModel	ratingModel5 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar5")));
			DataModel	ratingModel6 = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar6")));

			
//			DataModel ratingModelEval = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar2")));
			GrafoModel grafoModel = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
			grafoModel.setDataModel(ratingModel);
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
//			

//			
//			ScoringOverlapLiked scoringOverlapLiked = new ScoringOverlapLiked(null,ratingModel1);
//			ScoringOverlapLikedAndHated scoringOverlapLikedHated = new ScoringOverlapLikedAndHated(ratingModel1,ratingModel2);
//			ScoringOverlap scoringOverlap  = new ScoringOverlap(ratingModel);
			
//			Collection<UserSimilarity> similitudes = new ArrayList<UserSimilarity>();
//			UserSimilarity simlilitudCoseno  = new UncenteredCosineSimilarity(ratingModel1);
//			UserSimilarity similitudEuclidea = new EuclideanDistanceSimilarity(ratingModel1);
//			UserSimilarity similitudPearson = new PearsonCorrelationSimilarity(ratingModel1);
//			UserSimilarity similitudOverlap  = new ScoringOverlap(ratingModel1);
//			
//			similitudes.add(simlilitudCoseno);
//			similitudes.add(similitudEuclidea);
//			similitudes.add(similitudPearson);
//			similitudes.add(similitudOverlap);
			
//			Collection<UserSimilarity> similitudesEnGrafoSocial = new ArrayList<UserSimilarity>();
//			UserSimilarity userCosenoNet  = new ScoringCosenoNetwork(grafoModel);
//			UserSimilarity userJaccardNet  = new ScoringJaccardNetwork(grafoModel);
//			UserSimilarity userDistanciaNet  = new ScoringDistanciaNetwork(grafoModel);
//			
//			similitudesEnGrafoSocial.add(userCosenoNet);
//			similitudesEnGrafoSocial.add(userJaccardNet);
//			similitudesEnGrafoSocial.add(userDistanciaNet);
			
//			Collection<UserSimilarity> similitudesMatrizRivewTemporales = new ArrayList<UserSimilarity>();
//			
//			UserSimilarity similitudOverlapMatrizWeekOrWeekend  = new ScoringOverlap(ratingModel2);
//			UserSimilarity similitudOverlapMatrizWeekDay  = new ScoringOverlap(ratingModel3);
//			UserSimilarity similitudOverlapMatrizCate  = new ScoringOverlap(ratingModel4);
//			UserSimilarity similitudOverlapMatrizCateWeekDay  = new ScoringOverlap(ratingModel5);
//			UserSimilarity similitudOverlapMatrizCateWeekOrWeekend  = new ScoringOverlap(ratingModel6);
//			
//			
//			
//			similitudesMatrizRivewTemporales.add(similitudOverlapMatrizWeekOrWeekend);
//			similitudesMatrizRivewTemporales.add(similitudOverlapMatrizWeekDay);
//			similitudesMatrizRivewTemporales.add(similitudOverlapMatrizCate);
//			similitudesMatrizRivewTemporales.add(similitudOverlapMatrizCateWeekDay);
//			similitudesMatrizRivewTemporales.add(similitudOverlapMatrizCateWeekOrWeekend);
			
////////////////////////////Scoring LR-1//////////////////////////////////////////////
			
			UserSimilarity userVisitas  = new ScoringOverlap(ratingModel);
			UserSimilarity userVisitasWeekDayName  = new ScoringOverlap(ratingModel3);
			
			UserSimilarity userVisitasCate = new ScoringOverlap(ratingModel4);
			UserSimilarity userVisitasCateWeekDayName  = new ScoringOverlap(ratingModel5);
			UserSimilarity userVisitasCateWeekOrWeekend  = new ScoringOverlap(ratingModel6);
			UserSimilarity userCosenoNet  = new ScoringCosenoNetwork(grafoModel); 
			UserSimilarity userPathNet  = new ScoringDistanciaNetwork(grafoModel);
			UserSimilarity usersAreFriendsNet  = new ScoringAreFriendsNetwork(grafoModel);
			
			
//			UserSimilarity scoringLR = new ScoringLR(
//					userVisitas, 
//					userVisitasWeekDayName, 
//					userVisitasCate,
//					userVisitasCateWeekOrWeekend, 
//					userVisitasCateWeekDayName, 
//					userCosenoNet,
//					userPathNet);

////////////////////////////Scoring LR-2//////////////////////////////////////////////		
			
			UserSimilarity scoringLRModel2 = new ScoringLRModel2(
					userVisitas, 
					userVisitasWeekDayName, 
					userVisitasCate,
					userVisitasCateWeekOrWeekend, 
					userVisitasCateWeekDayName, 
					userCosenoNet,
					userPathNet,
					usersAreFriendsNet);
			
			String resulPath = MyProperties.getInstance().getProperty("resultadosprocesar");
			//calcularMatrizSimilitudConSample(ratingModelEval,ratingModel,scoringOverlap,PATH_RESULTADO+"yelp_red_cantidad_visitas_comunes_normalizado_one_state.csv");
			//calcularMatrizSimilitudItemItem(ratingModel,ItemSimlilitudTanimoto,MyProperties.getInstance().getProperty("resultados")+"ItemSimlilitudTanimoto"+ ".csv");
			//calcularMatrizSimilitud(ratingModel,scoringOverlapLikedHated,PATH_RESULTADO+"user_interseccion_likedhated_matriz_review.csv");
			//calcularMatrizSimilitud(ratingModel,scoringLR,PATH_RESULTADO+"user_interseccion_LR.csv");
			//calcularMatrizSimilitudParallel(ratingModel,scoringLRModel2,PATH_RESULTADO+"user_interseccion_LR_m2.csv");
			calcularMatrizSimilitudParallel(ratingModel,userPathNet,PATH_RESULTADO+"user_userPathNet.csv");
			//calcularMatrizSimilitud(ratingModel1,similitudes,PATH_RESULTADO+"goodness_similitudes_matriz.csv");
			//calcularMatrizSimilitud(ratingModel1,similitudesEnGrafoSocial,PATH_RESULTADO+"goodness_grafosocial_matriz.csv");
			//calcularMatrizSimilitud(ratingModel1,similitudesMatrizRivewTemporales,PATH_RESULTADO+"goodness_simi_tmp_categoria_matriz.csv");
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
			Collection<UserSimilarity> similitudes,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Similitudes ");
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
					try{
					String StrSimilitudes = "";	
					for (UserSimilarity userSimilarity : similitudes) {	
						double simValue = userSimilarity.userSimilarity(idUser, idUser2);
						StrSimilitudes = StrSimilitudes +"#"+simValue;
					}
					String resul = idUser+"#"+idUser2+"#"+StrSimilitudes;
					String[] entries = resul.split("#"); 
					writer.writeNext(entries);
					}catch(TasteException te){
						te.printStackTrace();
					}
				}
			}

			writer.close();
		System.out.println("Fin-Calcular Matriz Similitudes" );
		}
	private static void calcularMatrizSimilitud(DataModel model,
			UserSimilarity sim,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Simlitud ");
		System.out.println("Umbral : " +UMBRAL);
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
				 try{
				 double simValue = sim.userSimilarity(idUser, idUser2);
				 
				 if (!Double.isNaN(simValue) && simValue >= UMBRAL) {
					 String resul = idUser+"#"+idUser2+"#"+simValue;
					// System.out.println(resul);
				     String[] entries = resul.split("#"); 
				     writer.writeNext(entries);
			       }
				 }catch(TasteException te){
					 
					 
				 }
			}
			}
		writer.close();
		System.out.println("Fin-Calcular Matriz Simlitud" );
		}
	
	private static void calcularMatrizSimilitudParallel(DataModel model,
			UserSimilarity sim,String nombreArchivo) throws TasteException, IOException {
		System.out.println("Inicia-Calcular Matriz Simlitud ");
		System.out.println("Umbral : " +UMBRAL);
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t',CSVWriter.NO_QUOTE_CHARACTER);
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		
		List<Long> myList = Lists.newArrayList(usersIterable);
	    ICombinatoricsVector<Long> initialVector = Factory.createVector(myList);
	    // Create a simple combination generator to generate 3-combinations of the initial vector
	    
	    Generator<Long> gen =  Factory.createSimpleCombinationGenerator(initialVector, 2);
	    Streams.stream( gen.iterator()).parallel().forEach( i ->{
	    		  try{
						 double simValue = sim.userSimilarity(i.getValue(0), i.getValue(1));
						 if (!Double.isNaN(simValue) && simValue > UMBRAL) {
							 String resul = i.getValue(0)+"#"+i.getValue(1)+"#"+simValue;
						     String[] entries = resul.split("#"); 
						     writer.writeNext(entries);
					       }
						 }catch(TasteException te){
						 }
	    	   });
		
	
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
