package org.lenskit.demo.eval;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.isistan.lbsn.ii.LuceneItemItemModel;
import org.isistan.lbsn.ii.LuceneModelBuilder;
import org.junit.Before;
import org.junit.Test;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.results.Results;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;

public class LuceneItemModelTest {
	 private DataAccessObject dao;
	 LuceneItemItemModel iiModel;
	 
	  @Before
	  public void createRatingSource() throws IOException {
	        Path dataFile = Paths.get("data/yelp.yml");
	        dao = StaticDataSource.load(dataFile).get();
	        iiModel =  new LuceneModelBuilder(dao).get();
	    }
	  
	   @Test
	    public void testItemNeighOk() {
		   long item1 = 2387;
		   long item2 = 2395;
		   Entity e1 = dao.lookupEntity(CommonTypes.ITEM, item1);
		   Long2DoubleMap neighs = iiModel.getNeighbors(item1);
		   System.out.println("Vecinos de : " + e1.get("name") + e1.get("categoria") );
		   for (Map.Entry<Long, Double> entry : neighs.entrySet()) {
			   Entity e = dao.lookupEntity(CommonTypes.ITEM,entry.getKey());
			   System.out.println(e.get("name") +" : "+ entry.getValue());
		    }



	   }

}
