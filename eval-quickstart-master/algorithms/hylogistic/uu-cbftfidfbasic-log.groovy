import org.isistan.lbsn.cbf.CBFModel
import org.isistan.lbsn.cbf.TFIDFItemScorer
import org.isistan.lbsn.cbf.TFIDFModel
import org.isistan.lbsn.cbf.ThresholdUserProfileBuilder
import org.isistan.lbsn.cbf.UserProfileBuilder
import org.isistan.lbsn.hybrid.LogisticItemScorerSimple
import org.isistan.lbsn.hybrid.LogisticModelProvider
import org.isistan.lbsn.hybrid.LogisticModelProviderSimple
import org.isistan.lbsn.hybrid.LogisticTrainingSplit
import org.isistan.lbsn.hybrid.RecommenderConfigurationList
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

def uu = {
	bind ItemScorer to UserUserItemScorer
	bind VectorNormalizer to MeanCenteringVectorNormalizer
	set NeighborhoodSize to 20
}

def cbf = {
	bind ItemScorer to TFIDFItemScorer
	bind CBFModel to TFIDFModel
	bind UserProfileBuilder to ThresholdUserProfileBuilder
}

addComponent RecommenderConfigurationList.create(cbf, uu)

bind ItemScorer to LogisticItemScorerSimple
bind LogisticModelProviderSimple to LogisticModelProviderSimple

set LogisticTrainingSplit.TrainingBalance to 2.0
