package org.lenskit.demo.eval;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.data.entities.EntityFactory;

public class LoadUserTest {
	
	 private DataAccessObject dao;
	  @Before
	  public void createRatingSource() throws IOException {
	        EntityFactory efac = new EntityFactory();
	        Path dataFile = Paths.get("data/yelp.yml");
	        dao = StaticDataSource.load(dataFile).get();
	    }
	   @Test
	    public void testUserDataOk() {
		   System.out.println(dao.getEntityIds(CommonTypes.USER).size());
		   Entity enti = dao.lookupEntity(CommonTypes.USER, 713);
		   System.out.println(enti.get("id"));
		   System.out.println(enti.get("amigos"));
		   System.out.println(enti.get("amigosN2"));
		   //		   System.out.println(enti.get("lat")); 
//		   System.out.println(enti.get("lon"));
		  // List<Entity> users = dao.streamEntities(CommonTypes.USER).get();
//		   for (Entity ent : dao.streamEntities(CommonTypes.USER)) {
//			   System.out.println(ent.get("id"));
////			   System.out.println(ent.get("lat")); 
////			   System.out.println(ent.get("lon"));
//			
//		}
		  String att = enti.get("amigosN2").toString();
		  if (" ".equals(att))
			  System.out.println("att"+att);
		  for(String aa : att.split(Pattern.quote(","))){
			  System.out.println("----");
			  System.out.println(Long.parseLong(aa));
		  }
	   }

}
