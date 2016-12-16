package com.isistan.lbsn.scoring;

import org.apache.mahout.cf.taste.model.DataModel;

import com.isistan.lbsn.datamodels.DataModelByItemCategory;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;


/**
 * Clase que crea distintos tipos de funciones de scoring.
 * @author Usuarioç
 *
 */
public class ScoringFactory {
	public enum ScoringType {
		USER_OVERLAP,
		CERCANIA_USUARIO_ITEM,
		CERCANIA_USUARIO_USUARIO,
		USER_OVERLAP_LIKED,
		USER_OVERLAP_LIKED_HATED,
		SCORING_COSENO_NETWORK,
		SCORING_JACCARD_NETWORK,
		SCORING_OVERLAP_ABSTRACCION_CATEGORY,
		SCORING_OVERLAP_BY_CATEGORY,};
	
	public static Scoring build( ScoringType scoringType,
								DataModel dataModel, 
								GrafoModel grafoModel,
								UserModel userModel,
								ItemModel itemModel,
								DataModelByItemCategory dataModelItemCat) {
		Scoring scoring = null;
		switch (scoringType) {
		case USER_OVERLAP:
			scoring = new ScoringOverlap(grafoModel, dataModel);
			return scoring;
		case CERCANIA_USUARIO_ITEM:
			scoring = new ScoringCercaniaUsuarioItem(grafoModel, dataModel);
			return scoring;
		case CERCANIA_USUARIO_USUARIO:
			scoring = new ScoringCercaniaUsuarioUsuario(grafoModel, dataModel,userModel,itemModel);
			return scoring;
		case USER_OVERLAP_LIKED:
			scoring = new ScoringOverlapLiked(grafoModel, dataModel,2.5);
			return scoring;
		case USER_OVERLAP_LIKED_HATED:
			scoring = new ScoringOverlapLikedAndHated(grafoModel, dataModel,null);
			return scoring;
		case SCORING_COSENO_NETWORK:
			scoring = new ScoringCosenoNetwork(grafoModel);
			return scoring;
		case SCORING_JACCARD_NETWORK:
			scoring = new ScoringJaccardNetwork(grafoModel);
			return scoring;
		case SCORING_OVERLAP_ABSTRACCION_CATEGORY:
			scoring = new ScoringOverlapAbstraccionItemCategory(dataModel,itemModel);
			return scoring;
		case SCORING_OVERLAP_BY_CATEGORY:
			scoring = new ScoringOverlapAsFirstItemCategory(dataModel,itemModel, dataModelItemCat);
			return scoring;		
		default:
			return null; 
		}
	}
}
