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
	public enum TypeNeigh {K_NEIGHBORHOOD,K_FRIENDS,THRESHOLD,K_FRIENDS_FRIENDS};
    public static UserNeighborhood  build(UserSimilarity userSimilarity,DataModel model, 
    								TypeNeigh  typeNeigh,int neighSize, double threshold,
    								GrafoModel fdm) {
    	UserNeighborhood userNeighborhood = null;
        switch(typeNeigh) {
            case K_NEIGHBORHOOD:
                try {
                	System.out.println("NearestNUserNeighborhood");
                	userNeighborhood = new NearestNUserNeighborhood(neighSize, userSimilarity, model);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case K_FRIENDS: 
                try {
                	System.out.println("NearestNUserFriends");
                    userNeighborhood = new NearestNUserFriends(neighSize, userSimilarity, model,fdm);
                } catch (TasteException exception) {
                	}
                return userNeighborhood;
            case THRESHOLD:
            	System.out.println("ThresholdUserNeighborhood");
                userNeighborhood = new ThresholdUserNeighborhood(threshold, userSimilarity, model);
                return userNeighborhood;
                
            case K_FRIENDS_FRIENDS:
			try {
				System.out.println("NearestNUserFriendsAndFriends");
				userNeighborhood = new NearestNUserFriendsAndFriends(neighSize, userSimilarity, model,fdm);
			} catch (TasteException e) {
			}
                return userNeighborhood;
            default: return null; // We should never get here
        }
    }


}
