package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.similitudestructural.GrafoModel;
import com.isistan.lbsn.vencindario.NearestNUserFriends;
import com.isistan.lbsn.vencindario.NearestNUserFriendsAndFriends;

public class TypeNeighborhood {
	public enum TypeNeigh {K_NEIGHBORHOOD,K_FRIENDS,THRESHOLD,K_FRIENDS_FRIENDS,THRESHOLD_SCORING};
    public static UserNeighborhood  build(UserSimilarity userSimilarity,DataModel model, 
    								TypeNeigh  typeNeigh,int neighSize, double threshold,
    								GrafoModel fdm, Scoring scoring) {
    	UserNeighborhood userNeighborhood = null;
        switch(typeNeigh) {
            case K_NEIGHBORHOOD:
                try {
                	userNeighborhood = new NearestNUserNeighborhood(neighSize, userSimilarity, model);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_FRIENDS: 
                try {
                    userNeighborhood = new NearestNUserFriends(neighSize, userSimilarity, model,fdm);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case THRESHOLD:
                userNeighborhood = new ThresholdUserNeighborhood(threshold, userSimilarity, model);
                return userNeighborhood;
                
            case THRESHOLD_SCORING:
                userNeighborhood = new ThresholdUserNeighborhood(threshold, scoring, model);
                return userNeighborhood;
                
            case K_FRIENDS_FRIENDS:
			try {
				userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm);
			} catch (TasteException e) {
			}
                return userNeighborhood;
            default: return null; // We should never get here
        }
    }


}
