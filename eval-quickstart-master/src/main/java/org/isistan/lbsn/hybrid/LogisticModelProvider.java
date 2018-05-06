package org.isistan.lbsn.hybrid;

import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Provider;

import org.lenskit.api.ItemScorer;
import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.bias.BiasModel;
import org.lenskit.bias.UserBiasModel;
import org.lenskit.data.ratings.Rating;
import org.lenskit.data.ratings.RatingSummary;
import org.lenskit.inject.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Trainer that builds logistic models.
 */
public class LogisticModelProvider implements Provider<LogisticModel> {
    private static final Logger logger = LoggerFactory.getLogger(LogisticModelProvider.class);
    private static final double LEARNING_RATE = 0.00005;
    private static final int ITERATION_COUNT = 100;

    private final LogisticTrainingSplit dataSplit;
    private final BiasModel baseline;
    private final RecommenderList recommenders;
    private final RatingSummary ratingSummary;
    private final int parameterCount;
    private final Random random;
    //private HashTable<ItemScorer,HashTable<Long,HashTable<Long,Double>>> scorings = new HashTable<ItemScorer,HashTable<Long,HashTable<Long,Double>>>() ;
    private Hashtable<ItemScorer,Hashtable<Long,ResultMap>> scorings = new Hashtable<ItemScorer, Hashtable<Long, ResultMap>>() ;
    @Inject
    public LogisticModelProvider(@Transient LogisticTrainingSplit split,
                                 @Transient UserBiasModel bias,
                                 @Transient RecommenderList recs,
                                 @Transient RatingSummary rs,
                                 @Transient Random rng) {
        dataSplit = split;
        baseline = bias;
        recommenders = recs;
        ratingSummary = rs;
        parameterCount = 1 + recommenders.getRecommenderCount() + 1;
        random = rng;
    }
     private void cargarScoring(List<Rating> trainRating,List<ItemScorer> scorers){
         logger.info("cargando score");
         LongSet items = ratingSummary.getItems();
         for (ItemScorer score:
                     scorers) {
                 if(!scorings.containsKey(score)){
                     scorings.put(score,new Hashtable<Long, ResultMap>());
                 }
                 for (Rating ratingTrain:
                         trainRating) {
                     long user = ratingTrain.getUserId();
                     long item = ratingTrain.getItemId();

                     Result scoreResult = score.score(user,item);
                     double scoreValue = (null == scoreResult)?0:scoreResult.getScore();

                 if(!scorings.get(score).containsKey(user))
                     scorings.get(score).put(user,score.scoreWithDetails(user,items));
             }


         }
         logger.info("fin cargando score");

     }

//    private void cacheRatings( List< Rating > ratings ){
//
//        LongSet items = ratingSummary.getItems();
//        for( ItemScorer scorer : recommenders.getItemScorers() ){
//            Hashtable< Long ,ResultMap> ratingsCache = new Hashtable<>();
//            this.listRatingsCache.add( ratingsCache );
//        }
//
//        for( Rating r : ratings ){
//            long userId = r.getUserId();
//            for( int i = 0; i < this.listRatingsCache.size(); i++ ){
//                Hashtable< Long ,ResultMap > ratingsCache = this.listRatingsCache.get( i );
//                if( ! ratingsCache.containsKey( new Long( userId ) ) )
//                    ratingsCache.put( new Long( userId ), recommenders.getItemScorers().get(i).scoreWithDetails( userId, items ) );
//            }
//        }
//    }
        @Override
    public LogisticModel get() {
        List<ItemScorer> scorers = recommenders.getItemScorers();
        double intercept = 0;
        double[] params = new double[parameterCount];


        LogisticModel current = LogisticModel.create(intercept, params);
        List<Rating> trainRating = dataSplit.getTuneRatings();

        cargarScoring(trainRating,scorers);
        // TODO Implement model training
        for(int n=0;n < ITERATION_COUNT; n++){

            Collections.shuffle(trainRating);
            for (Rating ratingTrain:
                     trainRating) {

                long user = ratingTrain.getUserId();
                long item = ratingTrain.getItemId();
                double target = ratingTrain.getValue();

                //explanatory vars
                    double[] vars = new double[parameterCount];
                    double bias = this.baseline.getIntercept() + this.baseline.getUserBias(user) + this.baseline.getItemBias(item);
                    double popularity = Math.log10(this.ratingSummary.getItemRatingCount(item));
                    vars[0] = bias;
                    vars[1] = popularity;


                    int ind = 2;
                    for (ItemScorer score:
                            scorers) {
                         //Result scoreResult = score.score(user,item);
                        // double scoreValue = (null == scoreResult)?0:scoreResult.getScore() - bias;
                       // logger.info("score"+score.toString());
                        Hashtable<Long,ResultMap> scoreMap = scorings.get(score);
                        ResultMap userMap = scoreMap.get(user);
                        Result scoreValue = userMap.get(item);
                        //double scoreValue = scorings.get(score).get(user).get(item);
                        double scoreValueDouble = (null == scoreValue)?0:scoreValue.getScore()-bias;
                        vars[ind] = scoreValueDouble;
                        ind++;

                    }
                //prediction
                    double predict = current.evaluate(-1*target,vars);

                //Actualizar intercep
                    intercept = intercept +  this.LEARNING_RATE*target*(predict);
                //Actualizar params
                    params[0] = params[0] + this.LEARNING_RATE *target*(predict)*bias;
                    params[1] = params[1] + this.LEARNING_RATE *target*(predict)*popularity;
                    int j = 2;
                    for (ItemScorer score:
                         scorers) {
                       // Result scoreResult = score.score(user,item);
                        //double xSubJvalue = (null == scoreResult)?0:scoreResult.getScore() - bias;
                        params[j] = params[j] + this.LEARNING_RATE *target*(predict)*vars[j];
                        j++;

                    }
                current = LogisticModel.create(intercept, params);


            }




        }

        return current;
    }

}
