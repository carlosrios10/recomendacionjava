package org.isistan.lbsn.hybrid;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.lenskit.api.ItemScorer;
import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.basic.AbstractItemScorer;
import org.lenskit.bias.BiasModel;
import org.lenskit.bias.UserBiasModel;
import org.lenskit.data.ratings.RatingSummary;
import org.lenskit.results.BasicResult;
import org.lenskit.results.BasicResultMap;

/**
 * Item scorer that does a logistic blend of a subsidiary item scorer and popularity.  It tries to predict
 * whether a user has rated a particular item.
 */
public class LogisticItemScorer extends AbstractItemScorer {
    private final LogisticModel logisticModel;
    private final BiasModel biasModel;
    private final RecommenderList recommenders;
    private final RatingSummary ratingSummary;
    int parameterCount;

    @Inject
    public LogisticItemScorer(Provider<LogisticModel> model, UserBiasModel bias, RecommenderList recs, RatingSummary rs) {
        logisticModel = model.get();
        biasModel = bias;
        recommenders = recs;
        ratingSummary = rs;
        parameterCount = 1 + recommenders.recommenders.size() + 1;
    }

    @Nonnull
    @Override
    public ResultMap scoreWithDetails(long user, @Nonnull Collection<Long> items) {
        // TODO Implement item scorer
        List<Result> resultados = new ArrayList<Result>();
        for (Long item:
             items) {
            //explanatory vars
                double[] vars = new double[parameterCount];
                double bias = biasModel.getIntercept() + biasModel.getUserBias(user) + biasModel.getItemBias(item);
                double popularity = Math.log10(this.ratingSummary.getItemRatingCount(item));
                vars[0] = bias;
                vars[1] = popularity;
                int ind = 2;
                for (ItemScorer score:
                        recommenders.getItemScorers()) {
                    Result scoreResult = score.score(user,item);
                    double scoreValue = (null==scoreResult)?0:scoreResult.getScore();
                    vars[ind] = scoreValue  - bias;
                    ind++;

                }

            double prediction = logisticModel.evaluate(1,vars);
            resultados.add(new BasicResult(item,prediction));
        }
        return new BasicResultMap(resultados);
    }
}
