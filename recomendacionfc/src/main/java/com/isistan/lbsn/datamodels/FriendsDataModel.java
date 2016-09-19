package com.isistan.lbsn.datamodels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.scoring.Nodo;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
/**
 * 
 * @author Usuario�
 *
 */
public class FriendsDataModel implements GrafoModel{
	private  File dataFile;
	Map<Long,ArrayList<Long>> multiMap;
	
	public FriendsDataModel(){
		multiMap = new HashMap<Long,ArrayList<Long>>();
		ArrayList<Long> ids1= new ArrayList<Long>();
		ids1.add(2l);
		ids1.add(3l);
		ArrayList<Long> ids2= new ArrayList<Long>();
		ids2.add(1l);
		ids2.add(3l);
		ids2.add(4l);
		ArrayList<Long> ids3= new ArrayList<Long>();
		ids3.add(1l);
		ids3.add(2l);
		ids3.add(5l);
		ArrayList<Long> ids4= new ArrayList<Long>();
		ids4.add(2l);
		ids4.add(6l);
		ArrayList<Long> ids5= new ArrayList<Long>();
		ids5.add(3l);
		ids5.add(6l);
		ArrayList<Long> ids6= new ArrayList<Long>();
		ids6.add(4l);
		ids6.add(6l);
		multiMap.put(1l, ids1);
		multiMap.put(2l, ids2);
		multiMap.put(3l, ids3);
		multiMap.put(4l, ids4);
		multiMap.put(5l, ids5);
		multiMap.put(6l, ids6);
		
	}
	public FriendsDataModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    multiMap = new HashMap<Long,ArrayList<Long>>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
	    csvReader.readNext();
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
                      if (multiMap.get(Long.valueOf(lineaCsv[0].trim()))== null ){
                    	  ArrayList<Long> idsUsersdos = new ArrayList<Long>();
                    	  multiMap.put(new Long(lineaCsv[0].trim()),idsUsersdos);
                    	  }
                      	
                      	multiMap.get(Long.valueOf(lineaCsv[0].trim())).add(new Long(lineaCsv[1].trim()));
                      
         }
         csvReader.close();
	}
	
	public LongPrimitiveIterator getFriends(Long userID){
		ArrayList<Long> friends =  multiMap.get(userID);
		if (friends == null )
			return new LongPrimitiveArrayIterator(new long[0]);
		long [] friendsLong = new long[friends.size()];
		for (int i = 0; i < friends.size(); i++) {
			Long friend = friends.get(i);
			friendsLong[i]=friend;
		}
		LongPrimitiveIterator usersIDs = new  LongPrimitiveArrayIterator(friendsLong);
		return usersIDs;
	}

	public int getCantidadAmigos(long userID) {
		ArrayList<Long> friends =  multiMap.get(userID);
		if (friends == null )
			return 0;
		else 
			return friends.size();
		
	}
	public Collection<Long> getFriends(long userID) {
		ArrayList<Long> friends =  multiMap.get(userID);
		if (friends == null )
			return null;
		else
			return friends;
	}
	public Collection<Long> getFriendsMyFriends(long userID) {
		Collection<Long> amigos =  multiMap.get(userID);
		if (amigos == null)
			return null;
		
		Collection<Long> totalVecinos = new HashSet<Long>();
		for (Long amigo : amigos) {
			totalVecinos.add(amigo);
			List<Long> amigosDeAmigo = new ArrayList<Long>(multiMap.get(amigo));
			amigosDeAmigo.remove(new Long(userID));
			totalVecinos.addAll(amigosDeAmigo);
		}
		//return 	(new HashSet<Long>(totalVecinos));
		return totalVecinos;

	}
	
	private void buscarAmigos(Collection<Long> totalVecinos, Collection<Long> amigos){
		Collection<Long> listaAux = new  HashSet<Long>(amigos);
		for(Long amigo: listaAux){
			totalVecinos.add(amigo);
			List<Long> amigosDeAmigo = new ArrayList<Long>(multiMap.get(amigo));
			amigosDeAmigo.remove(new Long(amigo));
			totalVecinos.addAll(amigosDeAmigo);
			amigos.addAll(amigosDeAmigo);
			amigos.remove(amigo);
		}
	}
	public Collection<Long> getFriendsMyFriends(long userID, int nivel) {
		if (null == getFriends(userID)){
			return null;
		}
		Collection<Long> totalVecinos = new HashSet<Long>();
		Collection<Long> amigos = new HashSet<Long>();
		amigos.add(userID);
		for (int i = 1;i<=nivel;i++){
			buscarAmigos(totalVecinos, amigos);
		}
		totalVecinos.remove(new Long(userID));
		return totalVecinos;

	}
	public UndirectedSparseGraph<Long, Integer> getGrafo() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setGrafo(UndirectedSparseGraph<Long, Integer> grafo) {
		// TODO Auto-generated method stub
		
	}
	public UndirectedSparseGraph<Nodo, Integer> getGrafoN() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setGrafoN(UndirectedSparseGraph<Nodo, Integer> grafoN) {
		// TODO Auto-generated method stub
		
	}
	public double getPageRank(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getHits(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getBetweenness(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getHub(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getAuthority(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getDegree(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getCloseness(long userID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
