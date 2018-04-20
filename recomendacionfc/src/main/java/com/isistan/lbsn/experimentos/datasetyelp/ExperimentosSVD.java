package com.isistan.lbsn.experimentos.datasetyelp;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.builderrecomender.SVDBuildRecommender;
import com.isistan.lbsn.esquema.EvaluacionEsquemaSVD;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.Resultado;
import com.isistan.lbsn.util.Util;

public class ExperimentosSVD {
	private static final Logger log = LoggerFactory.getLogger(ExperimentosSVD.class);
	private static final double PORCENTAJE_TRAIN = 0.7;
	public static void main(String[] args) throws IOException {
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV,10));		
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV,20));
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV,30));
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV,40));
		
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV_PLUSPLUS,10));		
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV_PLUSPLUS,20));
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV_PLUSPLUS,30));
		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.SDV_PLUSPLUS,40));
		
//		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.ALSWR,10));		
//		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.ALSWR,20));
//		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.ALSWR,30));
//		configuraciones.add(new Configuracion(SVDBuildRecommender.BuilderRecommenderType.ALSWR,40));

		log.info("INICIA - evaluacion svd");
		EvaluacionEsquemaSVD esquema =  new EvaluacionEsquemaSVD();
		boolean cache = false;
		ArrayList<Resultado>   resultados = esquema.evaluar(configuraciones,PORCENTAJE_TRAIN);
		log.info("Exportar csv");
		Util.exportarResultadoCsvSVD(resultados, "yelp_svd2");
		log.info("FIN - evaluacion svd");
	}
}
