package com.isistan.lbsn.esquema;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.builderrecomender.GenRecBuilder;
import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.evaluadores.EvluadorCantidadVecinos;
import com.isistan.lbsn.evaluadores.GenericRecommenderIRStatsEvaluatorTrainTest;
import com.isistan.lbsn.evaluadores.ResultadoEvaluarCantidadVecinos;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.similitudcombinada.SimilitudProxy;

public class EvaluacionEsquemaIRS {
	  private static final Logger log = LoggerFactory.getLogger(EvaluacionEsquema.class);
/**
 * 
 * @param configuraciones
 * @return
 * To be clear, trainingPercentage and evaluationPercentage are not related. They do not need to add up to 1.0, for example.
 */
	public ArrayList<Resultado> evaluar(ArrayList<Configuracion> configuraciones, double porcentajeTrain) {
		ArrayList<Resultado> resultados = new ArrayList<Resultado>();
		try {
			RandomUtils.useTestSeed();
			DataModel ratingModelEvaluar = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingevaluar")));
			DataModel ratingModelTotal = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			
			DataModel ratingModelTotalWeekDayName = null;//new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingweekdayname")));
			DataModel ratingModelLiked =new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingliked")));
			DataModel ratingModelHated =new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratinghated")));
			DataModel ratingModelAbstraccionCategoria = null;//new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingabstraccioncate")));
			DataModel ratingModelCategoriaWeekDayName = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingcateweekdayname")));
			DataModel ratingModelCategoriaWeekOrWeekend = null;//new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingcateweekorweekend")));
			
			GrafoModel grafoModel =  new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
			UserModel userModel = null;//new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = null; //new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			DataModelByItemCategory dataModelItemCat = null;//new DataModelByItemCategory(ratingModelTotal,itemModel,8);
			UserSimilarity similarityCache = null;
			
			log.info("Model a evaluar con  {} usuarios y  {} items", ratingModelEvaluar.getNumUsers(), ratingModelEvaluar.getNumItems());
			EvluadorCantidadVecinos evalCantidadVecinos = new EvluadorCantidadVecinos();
			log.info("Con {}  para test, Preferencias en test {} ",(1-porcentajeTrain), evalCantidadVecinos.getCantidadTrainTest(ratingModelEvaluar, 0.0,1));
			for (final Configuracion configuracion : configuraciones) {
//					    ResultadoEvaluarCantidadVecinos  res = evalCantidadVecinos.evaluate(configuracion,
//					    																	ratingModelTotal,
//					    																	ratingModelEvaluar, 
//					    																	grafoModel,
//					    																	userModel, 
//					    																	0.8);
				RecommenderBuilder recBuilder = new GenRecBuilder(configuracion,
						ratingModelTotal,grafoModel,userModel,
						itemModel,dataModelItemCat,grafoModel,
						similarityCache,ratingModelLiked,ratingModelHated,
						ratingModelAbstraccionCategoria,
						ratingModelCategoriaWeekDayName,
						ratingModelCategoriaWeekOrWeekend,
						ratingModelTotalWeekDayName,
						null);
					   GenericRecommenderIRStatsEvaluatorTrainTest irEval = new GenericRecommenderIRStatsEvaluatorTrainTest();
					   irEval.setTrainDataModel(ratingModelTotal);
					   IRStatistics stats =  irEval.evaluate(recBuilder, null, ratingModelEvaluar, null,10,Double.NaN ,1);
					   Resultado resultado = new Resultado(configuracion, 0, 0 ,
								stats.getPrecision(),
								stats.getRecall(),
													stats.getF1Measure(),
															0,
															0,
															0,
															new ResultadoEvaluarCantidadVecinos());
					  log.info("alcance"+stats.getReach()); 
					  log.info(resultado.toString());
					  resultados.add(resultado);
				}
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultados;
	}





}
