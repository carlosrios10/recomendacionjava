package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
/**
 * 
 * @author Usuario�
 *
 */
public class FriendsDataModel {
	private  File dataFile;
	Map<Long,ArrayList<Long>> multiMap;
	
	public FriendsDataModel(){
		multiMap = new HashMap<Long,ArrayList<Long>>();
		ArrayList<Long> ids1= new ArrayList<Long>();
		ids1.add(2l);
		ids1.add(3l);
		ids1.add(4l);
		ArrayList<Long> ids2= new ArrayList<Long>();
		ids2.add(1l);
		ArrayList<Long> ids3= new ArrayList<Long>();
		ids3.add(1l);
		ids3.add(4l);
		
		ArrayList<Long> ids4= new ArrayList<Long>();
		ids4.add(1l);
		ids4.add(3l);
		
		multiMap.put(1l, ids1);
		multiMap.put(2l, ids2);
		multiMap.put(3l, ids3);
		multiMap.put(4l, ids4);
		
	}
	public FriendsDataModel(File dataFile) throws IOException{
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	  //  Preconditions.checkArgument(minReloadIntervalMS >= 0L, "minReloadIntervalMs must be non-negative");

//	    System.out.println("Creating FileDataModel for file {}" +  dataFile);
//	    FileLineIterator iterator = new FileLineIterator(dataFile, false);
//	    String firstLine = iterator.peek();
//	    while (firstLine.isEmpty()) {
//	      iterator.next();
//	      firstLine = iterator.peek();
//	      System.out.println(firstLine);
//	    }
//	    Closeables.close(iterator, true);
//	    Splitter delimiterPattern = Splitter.on(',');
//	    for (String token : delimiterPattern.split(firstLine)) {
//	        System.out.println(token);
//	      }
	    multiMap = new HashMap<Long,ArrayList<Long>>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
                      if (multiMap.get(Long.valueOf(lineaCsv[0]))== null ){
                    	  ArrayList<Long> idsUsersdos = new ArrayList<Long>();
                    	  multiMap.put(new Long(lineaCsv[0]),idsUsersdos);
                    	  }
                      	
                      	multiMap.get(Long.valueOf(lineaCsv[0])).add(new Long(lineaCsv[1]));
                      
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

}
