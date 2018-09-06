import javax.inject.Provider

import org.grouplens.lenskit.iterative.IterationCount
import org.grouplens.lenskit.iterative.LearningRate
import org.isistan.lbsn.hybrid.LogisticItemScorer
import org.isistan.lbsn.hybrid.LogisticModel
import org.isistan.lbsn.hybrid.LogisticModelProvider
import org.isistan.lbsn.hybrid.LogisticModelProviderWeka
import org.isistan.lbsn.hybrid.LogisticTrainingSplit
import org.isistan.lbsn.hybrid.RecommenderConfigurationList
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.mf.funksvd.FeatureCount
import org.lenskit.mf.funksvd.FunkSVDItemScorer

def svd = {
    bind ItemScorer to FunkSVDItemScorer
    set FeatureCount to 30
    set IterationCount to 125
    set LearningRate to 0.0015

    bind (BaselineScorer, ItemScorer) to BiasItemScorer
    bind BiasModel to UserItemBiasModel
}

addComponent RecommenderConfigurationList.create(svd)

bind ItemScorer to LogisticItemScorer
bind LogisticModelProviderWeka to LogisticModelProviderWeka
//bind LogisticModelProvider to LogisticModelProvider
 
set LogisticTrainingSplit.TrainingBalance to 2.0

bind (BaselineScorer, ItemScorer) to BiasItemScorer
bind BiasModel to UserItemBiasModel