package org.lenskit.demo.eval;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lenskit.LenskitConfiguration;
import org.lenskit.LenskitRecommender;
import org.lenskit.api.ItemScorer;
import org.lenskit.api.Recommender;
import org.lenskit.api.RecommenderBuildException;
import org.lenskit.api.Result;
import org.lenskit.baseline.BaselineScorer;
import org.lenskit.baseline.ItemMeanRatingItemScorer;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.lenskit.data.entities.EntityFactory;
import org.lenskit.data.ratings.Rating;
import org.lenskit.knn.NeighborhoodSize;
import org.lenskit.knn.item.ItemItemScorer;
import org.lenskit.knn.user.UserUserItemScorer;
import org.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer;
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer;
import org.lenskit.transform.normalize.UserVectorNormalizer;
import org.lenskit.transform.normalize.VectorNormalizer;

import it.unimi.dsi.fastutil.longs.LongSet;

public class ItemItemScorerTest {
	
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

	        dao = StaticDataSource.fromList(rs).get();
	    }
	    @Test
	    public void testUserItemMeanScorer() {
	    	
	    	// Second step is to create the LensKit configuration...
	        LenskitConfiguration config = new LenskitConfiguration();
	        // ... configure the data source
	        config.addComponent(dao);
	        // ... and configure the item scorer.  The bind and set methods
	        // are what you use to do that. Here, we want an item-item scorer.
	        config.bind(ItemScorer.class).to(ItemItemScorer.class);
	        config.bind(BaselineScorer.class,ItemScorer.class).to(ItemMeanRatingItemScorer.class);
	        config.bind(UserVectorNormalizer.class).to(BaselineSubtractingUserVectorNormalizer.class);
	        config.set(NeighborhoodSize.class).to(2);
	        // let's use personalized mean rating as the baseline/fallback predictor.
	        // 2-step process:
	        // First, use the user mean rating as the baseline scorer
//	        config.bind(BaselineScorer.class, ItemScorer.class)
//	               .to(UserMeanItemScorer.class);
//	        // Second, use the item mean rating as the base for user means
//	        config.bind(UserMeanBaseline.class, ItemScorer.class)
//	              .to(ItemMeanRatingItemScorer.class);
//	        // and normalize ratings by baseline prior to computing similarities
//	        config.bind(UserVectorNormalizer.class)
//	              .to(BaselineSubtractingUserVectorNormalizer.class);

	        // There are more parameters, roles, and components that can be set. See the
	        // JavaDoc for each recommender algorithm for more information.

	        // Now that we have a factory, build a recommender from the configuration
	        // and data source. This will compute the similarity matrix and return a recommender
	        // that uses it.
	    	// Second step is to create the LensKit configuration...
	        LenskitConfiguration config2 = new LenskitConfiguration();
	        // ... configure the data source
	        config2.addComponent(dao);
	        // ... and configure the item scorer.  The bind and set methods
	        // are what you use to do that. Here, we want an item-item scorer.
	        config2.bind(ItemScorer.class).to(UserUserItemScorer.class);
	        config2.bind(VectorNormalizer.class).to(MeanCenteringVectorNormalizer.class);
	        config2.set(NeighborhoodSize.class).to(2);
	        
	        Recommender recItemItem = null;
	        Recommender recUserUser = null;
	        try {
	        	recItemItem = LenskitRecommender.build(config);
	        	recUserUser = LenskitRecommender.build(config2);
	        } catch (RecommenderBuildException e) {
	            throw new RuntimeException("recommender build failed", e);
	        }
	        // we want to recommend items
	        long user = 4 ;
	        long item = 2;
	        Result res = recItemItem.getItemScorer().score(user, item);
	        System.out.format("\t%d\t%.2f\n", res.getId(), res.getScore());
	        
	        Result res2 = recUserUser.getItemScorer().score(user, item);
	        Result res3 = recUserUser.getRatingPredictor().predict(user, item);
	        System.out.format("\t%d\t%.2f\n", res2.getId(), res2.getScore());
	        System.out.format("\t%d\t%.2f\n", res3.getId(), res3.getScore());
	    }

}
