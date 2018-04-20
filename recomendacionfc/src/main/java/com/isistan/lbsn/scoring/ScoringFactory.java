package com.isistan.lbsn.scoring;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

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
		SCORING_OVERLAP_BY_CATEGORY,
		SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME,
		SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND,
		SCORING_LR2,
		SCORING_LR2_FOURSQUARE,
		SCORING_LR2_YELP,
		SCORING_DISTANCIA_NETWORK,
		SCORING_DISTANCIA_NETWORK_FOURSQUARE,
		SCORING_DISTANCIA_NETWORK_YELP,
		SCORING_OVERLAP_WEEKDAYNAME,
		SCORING_OVERLAP_WEEKORWEEKEND,
		SCORING_FRIEND_BASED_CF};
		private static final double MEAN_PATH_YELP = 0.16463998828;
		private static final double MEAN_PATH_FOURSQUARE = 0.38356122196348025;
	public static Scoring build(ScoringType scoringType,
								DataModel dataModel, 
								GrafoModel grafoModel,
								UserModel userModel,
								ItemModel itemModel,
								DataModelByItemCategory dataModelItemCat,
								DataModel modelTotalLiked,
								DataModel modelTotalHated,
								DataModel modelAbstraccionCategoria,
								DataModel modelAbstraccionCategoriaWeekDayName,
								DataModel modelAbstraccionCategoriaWeekOrWeekEnd,
								DataModel modelTotalWeekDayName,
								DataModel modelTotalWeekOrWeekend
								) {
		Scoring scoring = null;
		switch (scoringType) {
		case USER_OVERLAP:
			scoring = new ScoringOverlap(grafoModel, dataModel);
			return scoring;
		case CERCANIA_USUARIO_ITEM:
			scoring = new ScoringCercaniaUsuarioItem(userModel, itemModel);
			return scoring;
		case CERCANIA_USUARIO_USUARIO:
			scoring = new ScoringCercaniaUsuarioUsuario(grafoModel, dataModel,userModel,itemModel);
			return scoring;
		case USER_OVERLAP_LIKED:
			scoring = new ScoringOverlapLiked(grafoModel, dataModel,modelTotalLiked);
			return scoring;
		case USER_OVERLAP_LIKED_HATED:
			scoring = new ScoringOverlapLikedAndHated(dataModel,modelTotalLiked,modelTotalHated);
			return scoring;
		case SCORING_OVERLAP_WEEKDAYNAME:
			scoring = new ScoringOverlap(modelTotalWeekDayName);
			return scoring;
		case SCORING_OVERLAP_WEEKORWEEKEND:
			scoring = new ScoringOverlap(modelTotalWeekOrWeekend);
			return scoring;	
		case SCORING_COSENO_NETWORK:
			scoring = new ScoringCosenoNetwork(grafoModel);
			return scoring;
		case SCORING_JACCARD_NETWORK:
			scoring = new ScoringJaccardNetwork(grafoModel);
			return scoring;
		case SCORING_DISTANCIA_NETWORK:
			scoring = new ScoringDistanciaNetwork(grafoModel);
			return scoring;	
		case SCORING_DISTANCIA_NETWORK_FOURSQUARE:
			scoring = new ScoringDistanciaNetwork(grafoModel,MEAN_PATH_FOURSQUARE);
			return scoring;
		case SCORING_DISTANCIA_NETWORK_YELP:
			scoring = new ScoringDistanciaNetwork(grafoModel,MEAN_PATH_YELP);
			return scoring;		
		case SCORING_OVERLAP_ABSTRACCION_CATEGORY:
			scoring = new ScoringOverlapAbstraccionItemCategory(dataModel,modelAbstraccionCategoria);
			return scoring;
		case SCORING_OVERLAP_BY_CATEGORY:
			scoring = new ScoringOverlapAsFirstItemCategory(dataModel,itemModel, dataModelItemCat);
			return scoring;
		case SCORING_OVERLAP_BY_CATEGORY_WEEKDAYNAME:
			scoring = new ScoringOverlap(modelAbstraccionCategoriaWeekDayName);
			return scoring;	
		case SCORING_OVERLAP_BY_CATEGORY_WEEKORWEEKEND:
			scoring = new ScoringOverlap(modelAbstraccionCategoriaWeekOrWeekEnd);
			return scoring;

		case SCORING_LR2:{
			UserSimilarity userVisitas  = new ScoringOverlap(dataModel);
			UserSimilarity userVisitasWeekDayName  = new ScoringOverlap(modelTotalWeekDayName);
			
			UserSimilarity userVisitasCate = new ScoringOverlap(modelAbstraccionCategoria);
			UserSimilarity userVisitasCateWeekDayName  = new ScoringOverlap(modelAbstraccionCategoriaWeekDayName);
			UserSimilarity userVisitasCateWeekOrWeekend  = new ScoringOverlap(modelAbstraccionCategoriaWeekOrWeekEnd);
			UserSimilarity userCosenoNet  = new ScoringCosenoNetwork(grafoModel); 
			UserSimilarity userPathNet  = new ScoringDistanciaNetwork(grafoModel);
			UserSimilarity usersAreFriendsNet  = new ScoringAreFriendsNetwork(grafoModel);
			
			scoring = new ScoringLRModel2(
					userVisitas, 
					userVisitasWeekDayName, 
					userVisitasCate,
					userVisitasCateWeekOrWeekend, 
					userVisitasCateWeekDayName, 
					userCosenoNet,
					userPathNet,
					usersAreFriendsNet);
			return scoring;
		}
		case SCORING_LR2_FOURSQUARE:{
			UserSimilarity userVisitas  = new ScoringOverlap(dataModel);
			UserSimilarity userVisitasWeekDayName  = new ScoringOverlap(modelTotalWeekDayName);
			
			UserSimilarity userVisitasCate = new ScoringOverlap(modelAbstraccionCategoria);
			UserSimilarity userVisitasCateWeekDayName  = new ScoringOverlap(modelAbstraccionCategoriaWeekDayName);
			UserSimilarity userVisitasCateWeekOrWeekend  = new ScoringOverlap(modelAbstraccionCategoriaWeekOrWeekEnd);
			UserSimilarity userCosenoNet  = new ScoringCosenoNetwork(grafoModel); 
			UserSimilarity userPathNet  = new ScoringDistanciaNetwork(grafoModel,MEAN_PATH_FOURSQUARE);
			UserSimilarity usersAreFriendsNet  = new ScoringAreFriendsNetwork(grafoModel,MEAN_PATH_FOURSQUARE);
			
			scoring = new ScoringLRModel2(
					userVisitas, 
					userVisitasWeekDayName, 
					userVisitasCate,
					userVisitasCateWeekOrWeekend, 
					userVisitasCateWeekDayName, 
					userCosenoNet,
					userPathNet,
					usersAreFriendsNet);
			return scoring;	
			}
		case SCORING_LR2_YELP:{
			UserSimilarity userVisitas  = new ScoringOverlap(dataModel);
			UserSimilarity userVisitasWeekDayName  = new ScoringOverlap(modelTotalWeekDayName);
			
			UserSimilarity userVisitasCate = new ScoringOverlap(modelAbstraccionCategoria);
			UserSimilarity userVisitasCateWeekDayName  = new ScoringOverlap(modelAbstraccionCategoriaWeekDayName);
			UserSimilarity userVisitasCateWeekOrWeekend  = new ScoringOverlap(modelAbstraccionCategoriaWeekOrWeekEnd);
			UserSimilarity userCosenoNet  = new ScoringCosenoNetwork(grafoModel); 
			UserSimilarity userPathNet  = new ScoringDistanciaNetwork(grafoModel,MEAN_PATH_YELP);
			UserSimilarity usersAreFriendsNet  = new ScoringAreFriendsNetwork(grafoModel,MEAN_PATH_YELP);
			
			scoring = new ScoringLRModel2(
					userVisitas, 
					userVisitasWeekDayName, 
					userVisitasCate,
					userVisitasCateWeekOrWeekend, 
					userVisitasCateWeekDayName, 
					userCosenoNet,
					userPathNet,
					usersAreFriendsNet);
			return scoring;	
			}
		case SCORING_FRIEND_BASED_CF:
		{
			scoring = new ScoringFBCF(grafoModel,dataModel);
			return scoring;
			
		}
		default:
			return null; 
		}
	}
}
