package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.FriendsDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.evaluadores.EvluadorCantidadVecinos;
import com.isistan.lbsn.evaluadores.ResultadoEvaluarCantidadVecinos;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory.TypeNeigh;

public class CalcularVecinosPotenciales {
	
	public static void main(String[] args) {
		try {
			DataModel ratingModelEvaluar = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingevaluar")));
			DataModel ratingModelTotal = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			GrafoModel grafoModel = new FriendsDataModel(MyProperties.getInstance().getProperty("databasegrafo"));
			EvluadorCantidadVecinos evalCantidadVecinos = new EvluadorCantidadVecinos();
			ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
			configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_1,-1,-1,
					ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
			configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_2,-1,-1,
					ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
			configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_3,-1,-1,
					ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
			configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_4,-1,-1,
					ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
			configuraciones.add(new Configuracion(5,SimilarityAlgorithmFactory.SimAlg.COSENO,0.6,TypeNeigh.K_FRIENDS_NIVEL_5,-1,-1,
					ScoringType.USER_OVERLAP_LIKED,AgregationType.BASE));
			for (final Configuracion configuracion : configuraciones) {
				ResultadoEvaluarCantidadVecinos  res = evalCantidadVecinos.evaluate(configuracion,ratingModelTotal,
						ratingModelEvaluar, 
						grafoModel,
						null, 
						0.0);
				System.out.println(res.toString());
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
