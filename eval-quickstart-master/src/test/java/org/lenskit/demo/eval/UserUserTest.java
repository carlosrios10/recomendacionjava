package org.lenskit.demo.eval;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lenskit.LenskitConfiguration;
import org.lenskit.LenskitRecommender;
import org.lenskit.RecommenderConfigurationException;
import org.lenskit.api.ItemScorer;
import org.lenskit.api.RatingPredictor;
import org.lenskit.config.ConfigHelpers;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.EntityFactory;
import org.lenskit.data.ratings.Rating;
import org.lenskit.knn.user.UserUserItemScorer;
import org.lenskit.util.collections.LongUtils;

public class UserUserTest {
	 private DataAccessObject dao;
	  @Before
	  public void createRatingSource() {
	        EntityFactory efac = new EntityFactory();
	        List<Rating> rs = new ArrayList<>();
	        rs.add(efac.rating(1, 1, 3));
	        rs.add(efac.rating(1, 2, 0));
	        rs.add(efac.rating(1, 3, 3));
	        rs.add(efac.rating(1, 4, 3));
	        
	        rs.add(efac.rating(2, 1, 5));
	        rs.add(efac.rating(2, 2, 4));
	        rs.add(efac.rating(2, 3, 0));
	        rs.add(efac.rating(2, 4, 2));
	        
	        rs.add(efac.rating(3, 1, 1));
	        rs.add(efac.rating(3, 2, 2));
	        rs.add(efac.rating(3, 3, 4));
	        rs.add(efac.rating(3, 4, 2));
	        
	        rs.add(efac.rating(4, 1, 3));
	        rs.add(efac.rating(4, 3, 1));
	        rs.add(efac.rating(4, 4, 0));
	        
	        rs.add(efac.rating(5, 1, 2));
	        rs.add(efac.rating(5, 2, 2));
	        rs.add(efac.rating(5, 3, 0));
	        rs.add(efac.rating(5, 4, 1));

	        // Global Mean: 16 / 4 = 4

	        // Item  Means  Offsets
	        // 5 ->   3.5     -0.5 
	        // 7 ->   4.0      0.0
	        // 4 ->   5.0      1.0

	        // User  Offset Avg
	        //  1      -0.5+0.0 / 2 = -0.25
	        //  8      0.0+0.5 / 2  = 0.25

	        // Preds
	        // u1 on i5 -> 3.25
	        // u1 on i7 -> 3.75
	        // u1 on i10 -> unable to predict
	        // u1 on i4 -> 4.75
	        // u8 on i5 -> 3.75
	        // u8 on i7 -> 4.25
	        // u8 on i4 -> 5.25 (?)
	        // u2 on i4 -> 5.0
	        // u2 on i7 -> 4.0
	        // u2 on i5 -> 3.5
	        dao = StaticDataSource.fromList(rs).get();
	    }
	  
	   @Test
	    public void testUserUserScorer() throws RecommenderConfigurationException, IOException {
	        //ItemMeanModel model = new ItemMeanModelBuilder(dao, 0).get();
	        //ItemScorer scorer = new ExtendedItemUserMeanScorer(dao, model, 0);
	        
	        LenskitConfiguration config = ConfigHelpers.load(new File("algorithms/user-user.groovy"));
	        LenskitRecommender rec = LenskitRecommender.build(config, dao);
	        
	        LenskitConfiguration config2 = ConfigHelpers.load(new File("algorithms/item-item.groovy"));
	        LenskitRecommender rec2 = LenskitRecommender.build(config2, dao);
	       
	        RatingPredictor predictor = rec.getRatingPredictor();
	        ItemScorer scorer = rec.getItemScorer();
	        
	        RatingPredictor predictor2 = rec2.getRatingPredictor();
	        ItemScorer scorer2 = rec2.getItemScorer();
	        
	   		
	        // User 1
	        System.out.println(predictor.predict(4L, 2L).getScore());
	        System.out.println(predictor2.predict(4L, 2L).getScore());
	        
//	        long[] items = {5, 7, 10};
//	        Map<Long,Double> results = scorer.score(1L, LongUtils.packedSet(items));
//	        assertThat(results.get(5L), closeTo(3.25, 1.0e-5));
//	        assertThat(results.get(7L), closeTo(3.75, 1.0e-5));
//	        assertThat(results.get(10L), closeTo(3.75, 1.0e-5));  // user overall average
//	        assertFalse(results.containsKey(4L));



	    }

}
