package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.evaluadores.AverageAbsoluteDifferenceRecommenderEvaluatorTrainTest;
import com.isistan.lbsn.evaluadores.EvluadorCantidadVecinos;
import com.isistan.lbsn.evaluadores.ResultadoEvaluarCantidadVecinos;

public class EvaluacionEsquema {
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
			//GrafoModel grafoModel = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
			GrafoModel grafoModel = null;//new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
			//GrafoModel grafoModel2 = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml2"));
			UserModel userModel = new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
			ItemModel itemModel = null; //new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
			DataModelByItemCategory dataModelItemCat = null;//new DataModelByItemCategory(ratingModelTotal,itemModel,8);
			
			log.info("Model a evaluar con  {} usuarios y  {} items", ratingModelEvaluar.getNumUsers(), ratingModelEvaluar.getNumItems());
			EvluadorCantidadVecinos evalCantidadVecinos = new EvluadorCantidadVecinos();
			log.info("Con {}  para test, Preferencias en test {} ",(1-porcentajeTrain), evalCantidadVecinos.getCantidadTrainTest(ratingModelEvaluar, 0.0,0.5));
			for (final Configuracion configuracion : configuraciones) {
//					    ResultadoEvaluarCantidadVecinos  res = evalCantidadVecinos.evaluate(configuracion,
//					    																	ratingModelTotal,
//					    																	ratingModelEvaluar, 
//					    																	grafoModel,
//					    																	userModel, 
//					    																	0.8);
						RecommenderBuilder recBuilder = new GenRecBuilder(configuracion,ratingModelTotal,grafoModel,userModel,itemModel,dataModelItemCat,grafoModel);
					    double scoreMae = new AverageAbsoluteDifferenceRecommenderEvaluatorTrainTest().evaluate(recBuilder,null,ratingModelEvaluar, 0.0, 0.5);
					 // 	double scoreRms = new RMSRecommenderEvaluator().evaluate(recBuilder, null, ratingModelEvaluar, 0.8, 1);
					    IRStatistics stats =  new GenericRecommenderIRStatsEvaluator().evaluate(recBuilder, null, ratingModelEvaluar, null,10, 0, 1);
						Resultado resultado = new Resultado(configuracion, scoreMae, 0 ,
															0,
															0,
															0,
															0,
															0,
															0,
															new ResultadoEvaluarCantidadVecinos());
//						Resultado resultado = new Resultado(configuracion, scoreMae, 0 ,
//						0,
//						0,
//						0,
//						0,
//						0,
//						0,
//						res);
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
