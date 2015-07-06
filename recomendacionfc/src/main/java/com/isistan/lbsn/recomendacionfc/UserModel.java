package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;

public class UserModel {
	private  File dataFile;
	Map<Long,User> multiMap;
	
	public UserModel() {
		super();
		  multiMap = new HashMap<Long,User>();
		  User user = new User();
		  user.setLatitud("46.7866719");
		  user.setLongitud("-92.1004852");
		  multiMap.put(new Long(19) , user);
		  user.setLatitud("40.715972");
		  user.setLongitud("-74.001437");
		  multiMap.put(new Long(1095708) , user);
		  user.setLatitud("40.715972");
		  user.setLongitud("-74.001437");
		  multiMap.put(new Long(1095712) , user);
		
	}

	public UserModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    multiMap = new HashMap<Long,User>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
	    csvReader.readNext();
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
        	 User user = new User();
        	 user.setId(Long.parseLong(lineaCsv[0].trim()));
        	 user.setLatitud(lineaCsv[1].trim());
        	 user.setLongitud(lineaCsv[2].trim());
        	 multiMap.put(new Long(lineaCsv[0].trim()),user);
                      
         }
         csvReader.close();
	}
	
	public User getUser(long id){
		return this.multiMap.get(new Long(id));
	}

	public Map<Long, User> getMultiMap() {
		return multiMap;
	}

	public void setMultiMap(Map<Long, User> multiMap) {
		this.multiMap = multiMap;
	}

}
