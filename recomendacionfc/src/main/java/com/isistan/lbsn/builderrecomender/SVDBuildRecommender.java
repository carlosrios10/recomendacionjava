package com.isistan.lbsn.builderrecomender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.RatingSGDFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;

import com.isistan.lbsn.recomendacionfc.Configuracion;

public class SVDBuildRecommender implements RecommenderBuilder{
	public enum BuilderRecommenderType {SDV_PLUSPLUS,SDV,ALSWR};
	Configuracion conf;
	public SVDBuildRecommender(Configuracion conf){
		this.conf = conf;
	}
	@Override
	public Recommender buildRecommender(DataModel dataModel)
			throws TasteException {
		SVDRecommender recommender=null;
		switch (conf.getTypeModel()) {
		case SDV:
			Factorizer fac = new RatingSGDFactorizer(dataModel, conf.getNumFeature(), 100);
			recommender = new SVDRecommender(dataModel, fac);
			return recommender;
		case SDV_PLUSPLUS:
			Factorizer fac2 = new SVDPlusPlusFactorizer(dataModel, conf.getNumFeature(), 100);
			recommender = new SVDRecommender(dataModel, fac2);
			return recommender;
			
		case ALSWR:
			Factorizer fac3 = new ALSWRFactorizer(dataModel, conf.getNumFeature(),0.1, 100);
			recommender = new SVDRecommender(dataModel, fac3);
			return recommender;
		default:
			return null; 	
		}

	}

}
