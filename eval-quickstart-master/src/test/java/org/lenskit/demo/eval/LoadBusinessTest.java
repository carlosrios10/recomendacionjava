package org.lenskit.demo.eval;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.data.entities.EntityFactory;

public class LoadBusinessTest {
	 private DataAccessObject dao;
	  @Before
	  public void createRatingSource() throws IOException {
	        EntityFactory efac = new EntityFactory();
	        Path dataFile = Paths.get("data/yelp.yml");
	        dao = StaticDataSource.load(dataFile).get();
	    }
	   @Test
	    public void testBusinessDataOk() {
		   System.out.println( dao.query(CommonTypes.ITEM).get().size());
		   Entity ent = dao.lookupEntity(CommonTypes.ITEM, 2387);
		   System.out.println(ent.get("name"));
		   System.out.println(ent.get("categoria"));
		   System.out.println(ent.get("att"));
		   System.out.println(ent.get("latitude"));
		   System.out.println(ent.get("longitude"));
		   String att = ent.get("att").toString();
		   System.out.println(att);
		  for(String aa : att.split(Pattern.quote("|"))){
			  System.out.println(aa);
			  String [] val = aa.split("=");
			  System.out.println(val[0]);
			  System.out.println(val[1]);
			  if(val[1].equalsIgnoreCase("True"))
				  System.out.println("siii");
		  }
		  
	   }
}
