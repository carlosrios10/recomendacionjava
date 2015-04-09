package com.isistan.lbsn.similitudestructural;

import java.util.Collection;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public interface GrafoModel {
	
	public Collection<Long> getFriends(long userID) ;
	public Collection<Long> getFriendsMyFriends(long userID); 
	public UndirectedSparseGraph<Long, Integer> getGrafo();
	public void setGrafo(UndirectedSparseGraph<Long, Integer> grafo);
	public UndirectedSparseGraph<Nodo, Integer> getGrafoN() ;
	public void setGrafoN(UndirectedSparseGraph<Nodo, Integer> grafoN) ;

}
