package org.isistan.lbsn.uu;



import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;

/**
 * Created by Usuarioç on 22/11/2016.
 */
public class Vecino implements Comparable<Vecino> {
    private  long userID;
    private  double similarity;
    private Long2DoubleOpenHashMap ratingHistory;
    public Vecino(long userID, double similarity, Long2DoubleOpenHashMap ratingHistory) {
        this.userID = userID;
        this.similarity = similarity;
        this.ratingHistory = ratingHistory;
    }
    long getUserID() {
        return userID;
    }

    double getSimilarity() {
        return similarity;
    }
    Long2DoubleOpenHashMap getRatingHistory(){
        return  ratingHistory;
    }
    @Override
    public int compareTo(Vecino other) {
        double otherSimilarity = other.getSimilarity();
        if (similarity > otherSimilarity) {
            return -1;
        }
        if (similarity < otherSimilarity) {
            return 1;
        }
        long otherUserID = other.getUserID();
        if (userID < otherUserID) {
            return -1;
        }
        if (userID > otherUserID) {
            return 1;
        }
        return 0;
    }
}
