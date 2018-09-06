import org.isistan.lbsn.cbf.CBFModel
import org.isistan.lbsn.cbf.TFIDFItemScorer
import org.isistan.lbsn.cbf.TFIDFModel
import org.isistan.lbsn.cbf.ThresholdUserProfileBuilder
import org.isistan.lbsn.cbf.UserProfileBuilder
import org.isistan.lbsn.hybrid.LogisticItemScorerSimple
import org.isistan.lbsn.hybrid.LogisticModelProviderSimple
import org.isistan.lbsn.hybrid.LogisticTrainingSplit
import org.isistan.lbsn.hybrid.RecommenderConfigurationList
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

def ii = {
    bind ItemScorer to ItemItemScorer
    set NeighborhoodSize to 20
    bind VectorNormalizer to MeanCenteringVectorNormalizer
}

def cbf = {
	bind ItemScorer to TFIDFItemScorer
	bind CBFModel to TFIDFModel
	bind UserProfileBuilder to ThresholdUserProfileBuilder
}

addComponent RecommenderConfigurationList.create(cbf, ii)

bind ItemScorer to LogisticItemScorerSimple
bind LogisticModelProviderSimple to LogisticModelProviderSimple

set LogisticTrainingSplit.TrainingBalance to 2.0
