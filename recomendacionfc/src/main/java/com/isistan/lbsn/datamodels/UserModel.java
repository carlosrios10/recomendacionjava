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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.util.Util;

public class UserModel {
	private  File dataFile;
	Map<Long,User> usuariosTabla;
	Map<Integer,ArrayList<Long>> grupoNivel1;
	Map<Integer,ArrayList<Long>> grupoNivel2;
	private static final Logger log = LoggerFactory.getLogger(UserModel.class);
	public UserModel() {
		super();
		  usuariosTabla = new HashMap<Long,User>();
		  User user19 = new User();
		  user19.setLatitud("46.7866719");
		  user19.setLongitud("-92.1004852");
		  usuariosTabla.put(new Long(19) , user19);
		  User user1095708 = new User();
		  user1095708.setLatitud("40.715972");
		  user1095708.setLongitud("-74.001437");
		  usuariosTabla.put(new Long(1095708) , user1095708);
		  User user1095712 = new User();
		  user1095712.setLatitud("40.715972");
		  user1095712.setLongitud("-74.001437");
		  usuariosTabla.put(new Long(1095712) , user1095712);
		
	}

	public UserModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    usuariosTabla = new HashMap<Long,User>();
	    grupoNivel1 = new HashMap<Integer,ArrayList<Long>>();
	    grupoNivel2 = new HashMap<Integer,ArrayList<Long>>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
	    csvReader.readNext();
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
        	 User user = new User();
        	 user.setId(Long.parseLong(lineaCsv[0].trim()));
        	 user.setLatitud(lineaCsv[1].trim());
        	 user.setLongitud(lineaCsv[2].trim());
        	 user.setGrupoNivel1(Integer.parseInt(lineaCsv[4].trim()));
        	 user.setGrupoNivel2(Integer.parseInt(lineaCsv[5].trim()));
        	 usuariosTabla.put(new Long(lineaCsv[0].trim()),user);
        	 Integer idGrupoNivel1 = new Integer(user.getGrupoNivel1());
        	 Integer idGrupoNivel2 = new Integer(user.getGrupoNivel2());
        	 // grupo nivel 1
        	 if( grupoNivel1.containsKey(idGrupoNivel1) ){
        		 grupoNivel1.get(idGrupoNivel1).add(user.getId());
        	 }else{
        		 ArrayList<Long> listaUser = new ArrayList<Long>();
        		 listaUser.add(user.getId());
        		 grupoNivel1.put(idGrupoNivel1,listaUser);
        	 }
        	 // grupo nivel 2
        	 if( grupoNivel2.containsKey(idGrupoNivel2) ){
        		 grupoNivel2.get(idGrupoNivel2).add(user.getId());
        	 }else{
        		 ArrayList<Long> listaUser2 = new ArrayList<Long>();
        		 listaUser2.add(user.getId());
        		 grupoNivel2.put(idGrupoNivel2,listaUser2);
        	 }
                      
         }
         csvReader.close();
         log.info("Levantado datos de usuarios");
         log.info("Cantidad de usarios con zona: {}",usuariosTabla.size());
	}
	
	public User getUser(long id){
		//System.out.println("idpppp: "+id);
		return this.usuariosTabla.get(new Long(id));
	}
	
	public Collection<Long> getUserGrupo(User user){
		ArrayList<Long> grupo = grupoNivel1.get(new Integer(user.getGrupoNivel1()));
		return grupo;
	}
	public Collection<Long> getUserGrupo(User user,int nivel){
		if ( nivel == 2 ){
			ArrayList<Long> grupo = grupoNivel2.get(new Integer(user.getGrupoNivel2()));
			return grupo;
		}else {
			ArrayList<Long> grupo = grupoNivel1.get(new Integer(user.getGrupoNivel1()));
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
		Iterator<Long> it = usuariosTabla.keySet().iterator();
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
	
	public Collection<Long> getUserZona(User user,int radio){
		ArrayList<Long> usuariosZona = new ArrayList<Long>();
		Iterator<Long> it = usuariosTabla.keySet().iterator();
		while (it.hasNext()) {
			Long IdUser2 = it.next();
			User user2 = getUser(IdUser2);
			double distanciaKms = Util.distFrom(Double.valueOf(user.getLatitud()), 
					Double.valueOf(user.getLongitud()), 
					Double.valueOf(user2.getLatitud()), 
					Double.valueOf(user2.getLongitud()));
			if( distanciaKms<= radio ){
				usuariosZona.add(IdUser2);
			}
		}
		if ( usuariosZona.size() == 1 )
			return null;
		else			
			return usuariosZona;
	}
//	public Map<Long, User> getMultiMap() {
//		return usuarios;
//	}
//
//	public void setMultiMap(Map<Long, User> multiMap) {
//		this.usuarios = multiMap;
//	}

	public Map<Long, User> getUsuariosTabla() {
		return usuariosTabla;
	}

	public void setUsuariosTabla(Map<Long, User> usuariosTabla) {
		this.usuariosTabla = usuariosTabla;
	}

}
