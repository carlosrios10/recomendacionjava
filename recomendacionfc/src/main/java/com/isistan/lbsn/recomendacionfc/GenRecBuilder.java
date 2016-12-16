package com.isistan.lbsn.recomendacionfc;

import java.util.Iterator;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.isistan.lbsn.agregation.Agregation;
import com.isistan.lbsn.agregation.AgregationFactory;
import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.recommender.GenericUserBasedRecommenderNoNormalizado;
import com.isistan.lbsn.recommender.GenericUserBasedRecommenderNormalizado;
import com.isistan.lbsn.scoring.Scoring;
import com.isistan.lbsn.scoring.ScoringFactory;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory;
import com.isistan.lbsn.vencindario.UserNeighborhoodAux;
/**
 * Clase que crea un recomendador basado en usuario.
 * @author Usuario�
 *
 */
public class GenRecBuilder implements RecommenderBuilder {
	private static final Logger log = LoggerFactory.getLogger(GenRecBuilder.class);
	DataModel modelTotal;
	Configuracion configuracion ;
	GrafoModel grafoModel;
	UserModel userModel;
	ItemModel itemModel;
	DataModelByItemCategory dataModelItemCat;
	GrafoModel grafoModel2;
	
	public GenRecBuilder(Configuracion configuracion,DataModel modeltotal,GrafoModel grafoModel ,UserModel userModel, 
			ItemModel itemModel,DataModelByItemCategory dataModelItemCat) {
		super();
		this.modelTotal = modeltotal;
		this.configuracion = configuracion;
		this.grafoModel = grafoModel;
		this.userModel = userModel;
		this.itemModel = itemModel;
		this.dataModelItemCat = dataModelItemCat;

	}
	
	public GenRecBuilder(Configuracion configuracion,DataModel modeltotal,GrafoModel grafoModel ,UserModel userModel, 
			ItemModel itemModel,DataModelByItemCategory dataModelItemCat,GrafoModel grafoModel2) {
		super();
		this.modelTotal = modeltotal;
		this.configuracion = configuracion;
		this.grafoModel = grafoModel;
		this.userModel = userModel;
		this.itemModel = itemModel;
		this.dataModelItemCat = dataModelItemCat;
		this.grafoModel2 = grafoModel2;

	}
	public GenRecBuilder(Configuracion configuracion,GrafoModel grafoModel ) {
		super();
		this.modelTotal = null;
		this.configuracion = configuracion;
		this.grafoModel = grafoModel;

	}


	private boolean existeItem(long itemId, PreferenceArray arrayPref){
		int size = arrayPref.length();
		for (int i = 0; i < size; i++) {
			long itemId2 = arrayPref.getItemID(i);
			if (itemId == itemId2){
				return true;
			}

		}
		return false;

	}
	public Recommender buildRecommender(DataModel model) throws TasteException {
		log.info("Train con  {} usuarios y  {} items", modelTotal.getNumUsers(), modelTotal.getNumItems());
//		if(modelTotal != null){
//			FastByIDMap<PreferenceArray> preferencias = filtrarPreferenciasdeModelTotal(model);
//			DataModel modelFiltrado = new GenericDataModel(preferencias);
//			UserSimilarity sim = SimilarityAlgorithmFactory.build(modelFiltrado, grafoModel,configuracion.getSimAlg(),configuracion.getBeta(),configuracion.getBeta());
//			Scoring scoring = ScoringFactory.build(configuracion.getScoringType(), modelFiltrado,grafoModel,userModel,itemModel,dataModelItemCat);
//			UserNeighborhoodAux neighborhood = TypeNeighborhoodFactory.build(sim, modelFiltrado, configuracion.getTypeNeigh(),configuracion.getNeighSize(), configuracion.getThreshold(),grafoModel,scoring,userModel,itemModel);
//			Agregation agregation = AgregationFactory.build(configuracion.getAgregationType(), sim, scoring);
//			return new GenericUserBasedRecommenderNoNormalizado(modelFiltrado, neighborhood, agregation);
//		}else{
			UserSimilarity sim = SimilarityAlgorithmFactory.build(this.modelTotal, grafoModel,configuracion.getSimAlg(),configuracion.getBeta(),configuracion.getBeta());
			Scoring scoring = ScoringFactory.build(configuracion.getScoringType(), modelTotal,grafoModel2,userModel,itemModel,dataModelItemCat);
			UserNeighborhoodAux neighborhood = TypeNeighborhoodFactory.build(sim, modelTotal, configuracion.getTypeNeigh(),configuracion.getNeighSize(), configuracion.getThreshold(),grafoModel,scoring,userModel,itemModel);
			Agregation agregation = AgregationFactory.build(configuracion.getAgregationType(), sim, scoring);
			return new GenericUserBasedRecommenderNoNormalizado(modelTotal, neighborhood, agregation);
//		}
	}
	//Filtra las preferencias en modelTotal que pertenecen al test para cada usuario de model
	//obtengo las preferencias de modelTotal
	//realizo una interseccion con las preferencias de model
	//eliminar aquellas preferencias que estan en model total pero no estan en model
	private FastByIDMap<PreferenceArray> filtrarPreferenciasdeModelTotal(DataModel model)
			throws TasteException {
		FastByIDMap<PreferenceArray> validPref = new FastByIDMap<PreferenceArray>(modelTotal.getNumUsers());
		LongPrimitiveIterator it = modelTotal.getUserIDs();
		while (it.hasNext()) {
			long userIDModelTotal = it.nextLong();
			PreferenceArray prefModelTotal = modelTotal.getPreferencesFromUser(userIDModelTotal);
			List<Preference> prefs2 = Lists.newArrayListWithCapacity(prefModelTotal.length());
			for (Preference pref : prefModelTotal) {
				prefs2.add(pref);
			}
			try {
				PreferenceArray prefModel = model.getPreferencesFromUser(userIDModelTotal);
				for (Iterator<Preference> iterator = prefs2.iterator(); iterator.hasNext();) {
					Preference pref = iterator.next();
					if (!existeItem(pref.getItemID(), prefModel)) {
						iterator.remove();
					}
				}
				
			} catch (Exception e) {
				///si un usuario que esta en model total no esta en model, entonces no hace nada
				//solo lo agrega a validpref
			}
		   validPref.put(userIDModelTotal, new GenericUserPreferenceArray(prefs2));
		}
		return validPref;
	}
		


}
