package org.lenskit.demo.eval;

import it.unimi.dsi.fastutil.longs.LongSet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.grouplens.lenskit.transform.threshold.RealThreshold;
import org.grouplens.lenskit.vectors.similarity.CosineVectorSimilarity;
import org.isistan.lbsn.uu.NeighborhoodFinderZona;
import org.isistan.lbsn.uu.NeighborhoodFinderZonaDBscan;
import org.isistan.lbsn.uu.NeighborhoodFinderZonaKmeans;
import org.junit.Before;
import org.junit.Test;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.EntityFactory;
import org.lenskit.data.ratings.RatingVectorPDAO;
import org.lenskit.data.ratings.StandardRatingVectorPDAO;
import org.lenskit.knn.user.Neighbor;
import org.lenskit.knn.user.NeighborFinder;
import org.lenskit.knn.user.UserSimilarity;
import org.lenskit.knn.user.UserVectorSimilarity;
import org.lenskit.transform.normalize.DefaultUserVectorNormalizer;

public class NeighborhoodFinderZonaTest {
	 private DataAccessObject dao;
	 RatingVectorPDAO rvDAO;
	 UserSimilarity usersim;
	 NeighborFinder nf ;
	  @Before
	  public void createRatingSource() throws IOException {
	        EntityFactory efac = new EntityFactory();
	        Path dataFile = Paths.get("data/yelp.yml");
	        dao = StaticDataSource.load(dataFile).get();
	        rvDAO = new StandardRatingVectorPDAO(dao);
	        usersim = new  UserVectorSimilarity(new CosineVectorSimilarity());
	        
	        nf = new NeighborhoodFinderZonaDBscan(rvDAO,dao,usersim,new DefaultUserVectorNormalizer(),new RealThreshold(0.0),1.0);
	    }
	   @Test
	    public void testUserDataOk() {
		   LongSet items = dao.getEntityIds(CommonTypes.ITEM);
		  long userID = 311;
		  
		 Iterable<Neighbor>  res = nf.getCandidateNeighbors(userID, items);
		  System.out.println(res);
//		  for (Neighbor nbr: nf.getCandidateNeighbors(userID, items)) {
//				System.out.println(nbr.user);
//				System.out.println(nbr.similarity);
//			}

	   }

}
