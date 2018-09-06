package org.isistan.lbsn.grafos;

import java.util.Collection;



public interface GrafoModel {
	
	public Collection<Long> getFriends(long userID);
	public Collection<Long> getFriendsMyFriends(long userID); 
	public Collection<Long> getFriendsMyFriends(long userID,int nivel); 


}
