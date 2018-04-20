package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.evaluadores.GoodNessEvaluator;

public class MainCalcularGoodness {
	private static final String PATH_RESULTADO = MyProperties.getInstance().getProperty("resultadosprocesar");
	public static void main(String[] args) {
		try {
			System.out.println("INICIO - MainCalcularGoodness  -");
			DataModel	ratingModel = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingprocesar")));
			new GoodNessEvaluator().evaluate( ratingModel, 0.5, 1.0,PATH_RESULTADO+"vecinos_goodness_matriz_tips_foursq.csv");
			System.out.println("FIN - MainCalcularGoodness -");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}


	}

}
