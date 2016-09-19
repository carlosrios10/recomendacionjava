package com.isistan.lbsn.datamodels;

import java.util.Collection;

import com.isistan.lbsn.scoring.Nodo;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;



public interface GrafoModel {
	
	public Collection<Long> getFriends(long userID);
	public Collection<Long> getFriendsMyFriends(long userID); 
	public Collection<Long> getFriendsMyFriends(long userID,int nivel); 
	public UndirectedSparseGraph<Long, Integer> getGrafo();
	public void setGrafo(UndirectedSparseGraph<Long, Integer> grafo);
	public UndirectedSparseGraph<Nodo, Integer> getGrafoN();
	public void setGrafoN(UndirectedSparseGraph<Nodo, Integer> grafoN);
	public double getPageRank(long userID);
	public double getBetweenness(long userID);
	public double getHub(long userID);
	public double getAuthority (long userID);
	public double getDegree(long userID);
	public double getCloseness(long userID);

}
