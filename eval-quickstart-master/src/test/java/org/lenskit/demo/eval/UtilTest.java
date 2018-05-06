package org.lenskit.demo.eval;

import java.io.IOException;

import org.isistan.lbsn.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.lenskit.data.dao.DataAccessObject;

public class UtilTest {
	
	 private DataAccessObject dao;
	  @Before
	  public void createRatingSource() throws IOException {

	    }
	   @Test
	    public void testDistanciaOk() {
		   double lat1 = 33.513697;	
		   double lng1 = -112.073412;

		   	
		   double lat2 = 33.512673;	
		   double lng2 = -112.074100;
		   
		   double lat3 = 33.508987;	
		   double lng3 = -112.071274;
		   
		   double lat4 = 33.494749;	
		   double lng4 = -112.063276;
		   
		   	
		   	
		 System.out.println(Util.distFrom(lat1, lng1, lat2, lng2));  
		 System.out.println(Util.distFrom(lat1, lng1, lat3, lng3));
		 System.out.println(Util.distFrom(lat1, lng1, lat4, lng4));
		  
	   }

}
