package com.isistan.lbsn.esquema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.builderrecomender.SVDBuildRecommender;
import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.evaluadores.AverageAbsoluteDifferenceRecommenderEvaluatorKfold;
import com.isistan.lbsn.evaluadores.ResultadoEvaluarCantidadVecinos;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;

public class EvaluacionEsquemaSVD {
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
			DataModel ratingModelEvaluar = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			for (final Configuracion configuracion : configuraciones) {

						RecommenderBuilder recBuilder = new SVDBuildRecommender(configuracion);
					    double scoreMae = new AverageAbsoluteDifferenceRecommenderEvaluatorKfold().evaluate(recBuilder, null, ratingModelEvaluar,3.0, 1);
					    double scoreRms = new RMSRecommenderEvaluator().evaluate(recBuilder, null, ratingModelEvaluar, 0.7, 1);
//					    IRStatistics iRStatistics = new GenericRecommenderIRStatsEvaluator().evaluate(recBuilder, null, ratingModelEvaluar, null, 10, 3.5, 1);
//						Resultado resultado = new Resultado(configuracion, scoreMae, scoreRms ,
//								iRStatistics.getPrecision(),
//								iRStatistics.getRecall(),
//								iRStatistics.getF1Measure(),
//								iRStatistics.getFallOut(),
//								iRStatistics.getNormalizedDiscountedCumulativeGain(),
//								iRStatistics.getReach(),
//															new ResultadoEvaluarCantidadVecinos());
					    Resultado resultado = new Resultado(configuracion, scoreMae, 0,0,0,0,0,0,0, new ResultadoEvaluarCantidadVecinos());
					   log.info("MAE"+scoreMae);
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
