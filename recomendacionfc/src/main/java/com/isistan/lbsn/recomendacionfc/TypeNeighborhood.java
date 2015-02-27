package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class TypeNeighborhood {
	public enum TypeNeigh {K_NEIGHBORHOOD,K_FRIENDS,THRESHOLD};
    public static UserNeighborhood  build(UserSimilarity userSimilarity,DataModel model, 
    								TypeNeigh  typeNeigh,int neighSize, double threshold,
    								FriendsDataModel fdm) {
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
            default: return null; // We should never get here
        }
    }


}
