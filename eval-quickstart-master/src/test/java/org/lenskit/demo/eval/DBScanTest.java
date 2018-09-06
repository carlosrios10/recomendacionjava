package org.lenskit.demo.eval;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.isistan.lbsn.uu.EntityItemWrapper;
import org.junit.Before;
import org.junit.Test;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.data.entities.EntityFactory;
import org.lenskit.data.ratings.RatingVectorPDAO;
import org.lenskit.data.ratings.StandardRatingVectorPDAO;

public class DBScanTest {
	 private DataAccessObject dao;
	 List<EntityItemWrapper> clusterInput;
	 private RatingVectorPDAO rvDAO;
	 
	  @Before
	  public void createRatingSource() throws IOException {
	        EntityFactory efac = new EntityFactory();
	        Path dataFile = Paths.get("data/yelp.yml");
	        dao = StaticDataSource.load(dataFile).get();
	        rvDAO = new StandardRatingVectorPDAO(dao);
	        
	        int listItemsSize = dao.query(CommonTypes.ITEM).get().size();
	        clusterInput = new ArrayList<EntityItemWrapper>(listItemsSize);
	        for(Long item:rvDAO.userRatingVector(311).keySet()) {
	        	Entity enti =  dao.lookupEntity(CommonTypes.ITEM, item);
	        	double lat = Double.parseDouble(enti.get("latitude").toString());
			    double lon = Double.parseDouble(enti.get("longitude").toString());
			    double[] points = {lat,lon};
	        	clusterInput.add(new EntityItemWrapper(enti));
	        	
	        }

	    }
	   @Test
	    public void testBusinessDataOk() {
		// initialize a new clustering algorithm. 
		// we use KMeans++ with 10 clusters and 10000 iterations maximum.
		// we did not specify a distance measure; the default (euclidean distance) is used.
		   DBSCANClusterer<EntityItemWrapper> clusterer = new DBSCANClusterer<EntityItemWrapper>(0.05, 3);
		List<Cluster<EntityItemWrapper>> clusterResults = clusterer.cluster(clusterInput);
		
		// output the clusters
		for (int i=0; i<clusterResults.size(); i++) {
		    System.out.println("Cluster " + i);
		    List<EntityItemWrapper> points = clusterResults.get(i).getPoints();
		    double centroidLatitude = 0;
		    double centroidLongitude = 0;
		    for (Iterator iterator = points.iterator(); iterator.hasNext();) {
				EntityItemWrapper entityItemWrapper = (EntityItemWrapper) iterator.next();
				centroidLatitude += Double.parseDouble(entityItemWrapper.getEItem().get("latitude").toString());
				centroidLongitude += Double.parseDouble(entityItemWrapper.getEItem().get("longitude").toString());
			}
		    double [] position = {centroidLatitude/points.size(),centroidLongitude/points.size()};
		    System.out.println("Centroid [" + position[0] +","+position[1]+"]");
//		    for (LocationWrapper locationWrapper : clusterResults.get(i).getPoints()) {
//		    	System.out.println(locationWrapper.getEItem().getId());
//		    	System.out.println(locationWrapper.getEItem().get("name").toString());
//		        System.out.println(locationWrapper.getEItem().get("latitude").toString());
//		    	System.out.println(locationWrapper.getEItem().get("longitude").toString());
//		    	}
		    System.out.println();
		}
		   
	   }
	   
}
