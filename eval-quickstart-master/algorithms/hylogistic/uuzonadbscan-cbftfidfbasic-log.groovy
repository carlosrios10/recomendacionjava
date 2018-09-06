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
import org.isistan.lbsn.uu.DistBetweenZonas
import org.isistan.lbsn.uu.NeighborhoodFinderZonaDBscan
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

def uuzona = {
    bind ItemScorer to UserUserItemScorer
    bind VectorNormalizer to MeanCenteringVectorNormalizer
    set NeighborhoodSize to 100
	bind NeighborFinder to NeighborhoodFinderZonaDBscan
	set DistBetweenZonas to 0.8

}

def cbf = {
	bind ItemScorer to TFIDFItemScorer
	bind CBFModel to TFIDFModel
	bind UserProfileBuilder to ThresholdUserProfileBuilder
}

addComponent RecommenderConfigurationList.create(cbf, uuzona)

bind ItemScorer to LogisticItemScorerSimple
bind LogisticModelProviderSimple to LogisticModelProviderSimple

set LogisticTrainingSplit.TrainingBalance to 2.0
