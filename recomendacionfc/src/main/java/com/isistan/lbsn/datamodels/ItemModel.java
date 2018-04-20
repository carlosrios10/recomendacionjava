package com.isistan.lbsn.datamodels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.entidades.Item;

public class ItemModel {
	private  File dataFile;
	Map<Long,Item> itemsTabla;
	
	public ItemModel() {
		super();
		 itemsTabla = new HashMap<Long,Item>();
		 Item item = new Item();
    	 item.setLatitud("45.5405832");
    	 item.setLongitud("-73.5965186");
    	 itemsTabla.put(new Long(101759),item);
	}

	public ItemModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    itemsTabla = new HashMap<Long,Item>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
	    csvReader.readNext();
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
        	 Item item = new Item();
        	 item.setCategoriaNivel1(new HashSet<Long>());
        	 item.setCategoriaNivel2(new HashSet<Long>());
        	 item.setCategoriaNivel3(new HashSet<Long>());
        	 item.setLatitud(lineaCsv[1].trim());
        	 item.setLongitud(lineaCsv[2].trim());
        	 int cantCategoria = Integer.parseInt(lineaCsv[3].trim());
        	 for (int i = 0; i < cantCategoria; i++) {
        		 String cate = lineaCsv[4+i].trim();
        		 String[] ides =  cate.split(":");
        		 Long  cat3 = new Long(ides[0]);
        		 Long  cat2 = new Long(ides[1]);
        		 Long  cat1 = new Long(ides[2]);
        		 if( cat1!= -1)
        			 item.getCategoriaNivel1().add(new Long(cat1));
        		 if( cat2!= -1)
        			 item.getCategoriaNivel2().add(new Long(cat2));
        		 if( cat3!= -1)
        			 item.getCategoriaNivel3().add(new Long(cat3));
			}
        	 itemsTabla.put(new Long(lineaCsv[0].trim()),item);
                      
         }
         csvReader.close();
	}
	
	public Item getItem(long id){
		return this.itemsTabla.get(new Long(id));
	}
	
	public int getCantidadDeItems(){
		return this.itemsTabla.size();
	}

}
