package com.isistan.lbsn.vencindario;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

public interface UserNeighborhoodAux extends UserNeighborhood{
	  /**
	   * @param userID
	   *          ID of user for which a neighborhood will be computed
	   * @return IDs of users in the neighborhood
	   * @throws TasteException
	   *           if an error occurs while accessing data
	   */
	  long[] getUserNeighborhood(long userID,long itemID) throws TasteException;
}
