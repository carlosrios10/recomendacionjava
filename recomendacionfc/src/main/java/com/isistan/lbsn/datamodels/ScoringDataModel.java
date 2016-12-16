package com.isistan.lbsn.datamodels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
import com.isistan.lbsn.entidades.ScoringEntidad;

public class ScoringDataModel {
	Map<ScoringEntidad,Double> multiMap;
	private  File dataFile;
	public ScoringDataModel(String fileName) throws IOException{
		File dataFile = new File(fileName);
	    this.dataFile = Preconditions.checkNotNull(dataFile.getAbsoluteFile());
	    if (!dataFile.exists() || dataFile.isDirectory()) {
	      throw new FileNotFoundException(dataFile.toString());
	    }
	    Preconditions.checkArgument(dataFile.length() > 0L, "dataFile is empty");
	    multiMap = new HashMap<ScoringEntidad,Double>();
	    CSVReader csvReader = new CSVReader(new FileReader(dataFile));
	    String[] lineaCsv  = null;
         while ((lineaCsv = csvReader.readNext()) != null) 
         {
        	 ScoringEntidad scoring = new ScoringEntidad();
        	 scoring.setIdUser1(Long.parseLong(lineaCsv[0].trim()));
        	 scoring.setIdUser2(Long.parseLong(lineaCsv[1].trim()));
        	 String scoreString = lineaCsv[2].trim();
        	 if (scoreString.equals("Inf"))
        		 scoring.setScore(Double.POSITIVE_INFINITY);
        	 else
        		 scoring.setScore(Double.parseDouble(lineaCsv[2].trim()));
        	 if(!multiMap.containsKey(scoring))
        		 multiMap.put(scoring,scoring.getScore());

         }
         csvReader.close();
	}
	
	public double getScore(long id1,long id2){
		ScoringEntidad s = new ScoringEntidad();
		s.setIdUser1(id1);
		s.setIdUser2(id2);
		return this.multiMap.get(s);
		
	}
	
}
