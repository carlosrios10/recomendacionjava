package com.isistan.lbsn.vencindario;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.scoring.Scoring;

public class TypeNeighborhoodFactory {
	public enum TypeNeigh {K_NEIGHBORHOOD,
						   K_FRIENDS,
						   K_FRIENDS_NIVEL_1,
						   K_FRIENDS_NIVEL_2,
						   K_FRIENDS_NIVEL_3,
						   K_FRIENDS_NIVEL_4,
						   K_FRIENDS_NIVEL_5,
						   K_NEIGHBORHOOD_GRUPO,
						   K_NEIGHBORHOOD_GRUPO_NIVEL_2,
						   K_NEIGHBORHOOD_ZONA,
						   K_NEIGHBORHOOD_ZONA_RADIO_1,
						   K_NEIGHBORHOOD_ZONA_RADIO_2,
						   K_NEIGHBORHOOD_ZONA_RADIO_3,
						   K_NEIGHBORHOOD_ZONA_RADIO_4,
						   K_NEIGHBORHOOD_ZONA_RADIO_5,
						   THRESHOLD,
						   K_FRIENDS_FRIENDS,
						   THRESHOLD_SCORING,
						   K_NEIGHBORHOOD_SCORING,
						   K_NEIGHBORHOOD_BY_SCORING,
						   K_FRIENDS_NIVEL_1_BY_SCORING,
						   K_FRIENDS_NIVEL_2_BY_SCORING,
						   K_FRIENDS_NIVEL_3_BY_SCORING,
						   K_FRIENDS_NIVEL_4_BY_SCORING,
						   K_FRIENDS_NIVEL_5_BY_SCORING};
    public static UserNeighborhoodAux  build(UserSimilarity userSimilarity,
    									  DataModel model, 
    									  TypeNeigh  typeNeigh,
    									  int neighSize, 
    									  double threshold,
    									  GrafoModel fdm, 
    									  Scoring scoring,
    									  UserModel userModel,
    									  ItemModel itemModel
    									  ) {
    	UserNeighborhoodAux userNeighborhood = null;
        switch(typeNeigh) {
            case K_NEIGHBORHOOD:
                try {
                	userNeighborhood = new NearestNUserNeighborhoodBySimilitud(neighSize, userSimilarity, model,null,null);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_FRIENDS: 
                try {
                    userNeighborhood = new NearestNUserFriends(neighSize, userSimilarity, model,fdm);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_FRIENDS_NIVEL_1: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm,1);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_FRIENDS_NIVEL_2: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm,2);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    
            case K_FRIENDS_NIVEL_3: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm,3);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    
            case K_FRIENDS_NIVEL_4: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm,4);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    
            case K_FRIENDS_NIVEL_5: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm,5);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
                
            case K_FRIENDS_NIVEL_1_BY_SCORING: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, scoring, model,fdm,1);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_FRIENDS_NIVEL_2_BY_SCORING: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, scoring, model,fdm,2);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    
            case K_FRIENDS_NIVEL_3_BY_SCORING: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, scoring, model,fdm,3);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    
            case K_FRIENDS_NIVEL_4_BY_SCORING: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, scoring, model,fdm,4);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    
            case K_FRIENDS_NIVEL_5_BY_SCORING: 
                try {
                    userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, scoring, model,fdm,5);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;    

                
            case K_NEIGHBORHOOD_GRUPO:
                try {
                    userNeighborhood = new NearestNUserGrupo(neighSize, userSimilarity,userModel,1);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_NEIGHBORHOOD_GRUPO_NIVEL_2:
                try {
                    userNeighborhood = new NearestNUserGrupo(neighSize, userSimilarity,userModel,2);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;                
            case K_NEIGHBORHOOD_ZONA:
                try {
                    userNeighborhood = new NearestNUserZona(neighSize, userSimilarity,userModel);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_NEIGHBORHOOD_ZONA_RADIO_1:
                try {
                    userNeighborhood = new NearestNUserZona(neighSize, userSimilarity,userModel,1);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_NEIGHBORHOOD_ZONA_RADIO_2:
                try {
                    userNeighborhood = new NearestNUserZona(neighSize, userSimilarity,userModel,2);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;  
            case K_NEIGHBORHOOD_ZONA_RADIO_3:
                try {
                    userNeighborhood = new NearestNUserZona(neighSize, userSimilarity,userModel,3);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_NEIGHBORHOOD_ZONA_RADIO_4:
                try {
                    userNeighborhood = new NearestNUserZona(neighSize, userSimilarity,userModel,4);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;  
            case K_NEIGHBORHOOD_ZONA_RADIO_5:
                try {
                    userNeighborhood = new NearestNUserZona(neighSize, userSimilarity,userModel,5);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;                  
            	
//            case THRESHOLD:
//            	try {
//                userNeighborhood = new ThresholdUserNeighborhood(threshold, userSimilarity, model);
//                } catch (Exception exception) {
//            	}
//                return userNeighborhood;
//                
//            case THRESHOLD_SCORING:
//                userNeighborhood = new ThresholdUserNeighborhood(threshold, scoring, model);
//                return userNeighborhood;
                
            case K_FRIENDS_FRIENDS:
			try {
				userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm);
			} catch (TasteException e) {
			}
			 return userNeighborhood;
//            case K_NEIGHBORHOOD_SCORING:
//			try {
//				userNeighborhood = new NearestNUserNeighborhood(neighSize, scoring, model);
//			} catch (TasteException e) {
//				e.printStackTrace();
//			}
//                return userNeighborhood;
            case K_NEIGHBORHOOD_BY_SCORING:
			try {
				userNeighborhood = new NearestNUserNeighborhoodByScoring(neighSize, scoring, model);
			} catch (TasteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                return userNeighborhood;                
            default: return null; // We should never get here
        }
    }


}
