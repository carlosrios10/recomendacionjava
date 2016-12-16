package com.isistan.lbsn.datamodels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.util.Util;

public class UserModel {
	private  File dataFile;
	Map<Long,User> multiMap;
	Map<Integer,ArrayList<Long>> multiMapGrupo;
	Map<Integer,ArrayList<Long>> multiMapGrupoNivel2;
	
	public UserModel() {
		super();
		  multiMap = new HashMap<Long,User>();
		  User user19 = new User();
		  user19.setLatitud("46.7866719");
		  user19.setLongitud("-92.1004852");
		  multiMap.put(new Long(19) , user19);
		  User user1095708 = new User();
		  user1095708.setLatitud("40.715972");
		  user1095708.setLongitud("-74.001437");
		  multiMap.put(new Long(1095708) , user1095708);
		  User user1095712 = new User();
		  user1095712.setLatitud("40.715972");
		  user1095712.setLongitud("-74.001437");
		  multiMap.put(new Long(1095712) , user1095712);
		
	}

	public UserModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    multiMap = new HashMap<Long,User>();
	    multiMapGrupo = new HashMap<Integer,ArrayList<Long>>();
	    multiMapGrupoNivel2 = new HashMap<Integer,ArrayList<Long>>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
	    csvReader.readNext();
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
        	 User user = new User();
        	 user.setId(Long.parseLong(lineaCsv[0].trim()));
        	 user.setLatitud(lineaCsv[1].trim());
        	 user.setLongitud(lineaCsv[2].trim());
        	 user.setGrupo(Integer.parseInt(lineaCsv[3].trim()));
//        	 user.setGrupoNivel2(Integer.parseInt(lineaCsv[4].trim()));
        	 multiMap.put(new Long(lineaCsv[0].trim()),user);
        	 Integer idGrupo = new Integer(user.getGrupo());
        	 Integer idGrupoNivel2 = new Integer(user.getGrupoNivel2());
//        	 // grupo nivel 1
//        	 if( multiMapGrupo.containsKey(idGrupo) ){
//        		 multiMapGrupo.get(idGrupo).add(user.getId());
//        	 }else{
//        		 ArrayList<Long> listaUser = new ArrayList<Long>();
//        		 listaUser.add(user.getId());
//        		 multiMapGrupo.put(idGrupo,listaUser);
//        	 }
//        	 // grupo nivel 2
//        	 if( multiMapGrupoNivel2.containsKey(idGrupoNivel2) ){
//        		 multiMapGrupoNivel2.get(idGrupoNivel2).add(user.getId());
//        	 }else{
//        		 ArrayList<Long> listaUser2 = new ArrayList<Long>();
//        		 listaUser2.add(user.getId());
//        		 multiMapGrupoNivel2.put(idGrupoNivel2,listaUser2);
//        	 }
                      
         }
         csvReader.close();
	}
	
	public User getUser(long id){
		//System.out.println("idpppp: "+id);
		return this.multiMap.get(new Long(id));
	}
	
	public Collection<Long> getUserGrupo(User user){
		ArrayList<Long> grupo = multiMapGrupo.get(new Integer(user.getGrupo()));
		return grupo;
	}
	public Collection<Long> getUserGrupo(User user,int nivel){
		if ( nivel == 2 ){
			ArrayList<Long> grupo = multiMapGrupoNivel2.get(new Integer(user.getGrupoNivel2()));
			return grupo;
		}else {
			ArrayList<Long> grupo = multiMapGrupo.get(new Integer(user.getGrupo()));
			return grupo;
		}
		
	}
	
	public boolean existeInterseccionZona(User user1,User user2){
		//System.out.println(user1.getId());
		double radioTotalKMs = user1.getRadioZona() + user2.getRadioZona();
		double distanciaKms = Util.distFrom(Double.valueOf(user1.getLatitud()), 
					  Double.valueOf(user1.getLongitud()), 
					  Double.valueOf(user2.getLatitud()), 
					  Double.valueOf(user2.getLongitud()));
		if(distanciaKms<=radioTotalKMs)
			return true;
		else
		    return false;
	}
	public Collection<Long> getUserZona(User user){
		ArrayList<Long> usuariosZona = new ArrayList<Long>();
		Iterator<Long> it = multiMap.keySet().iterator();
		while (it.hasNext()) {
			Long IdUser2 = it.next();
			User user2 = getUser(IdUser2);
			if( existeInterseccionZona(user, user2) ){
				usuariosZona.add(IdUser2);
			}
		}
		if ( usuariosZona.size() == 1 )
			return null;
		else			
			return usuariosZona;
	}
	public Map<Long, User> getMultiMap() {
		return multiMap;
	}

	public void setMultiMap(Map<Long, User> multiMap) {
		this.multiMap = multiMap;
	}

}
