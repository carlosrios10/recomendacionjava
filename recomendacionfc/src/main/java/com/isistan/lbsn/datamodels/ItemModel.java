package com.isistan.lbsn.datamodels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.entidades.Item;

public class ItemModel {
	private  File dataFile;
	Map<Long,Item> multiMap;
	
	public ItemModel() {
		super();
		 multiMap = new HashMap<Long,Item>();
		 Item item = new Item();
    	 item.setLatitud("45.5405832");
    	 item.setLongitud("-73.5965186");
    	 multiMap.put(new Long(101759),item);
	}

	public ItemModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    multiMap = new HashMap<Long,Item>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
	    csvReader.readNext();
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
        	 Item item = new Item();
        	 item.setLatitud(lineaCsv[1].trim());
        	 item.setLongitud(lineaCsv[2].trim());
        	 multiMap.put(new Long(lineaCsv[0].trim()),item);
                      
         }
         csvReader.close();
	}
	
	public Item getItem(long id){
		return this.multiMap.get(new Long(id));
	}

}
