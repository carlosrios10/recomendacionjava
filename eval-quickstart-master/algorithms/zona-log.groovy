import org.isistan.lbsn.hybrid.LogisticItemScorer
import org.isistan.lbsn.hybrid.LogisticTrainingSplit
import org.isistan.lbsn.hybrid.RecommenderConfigurationList
import org.isistan.lbsn.uu.NeighborhoodFinderZona
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

def userZona = {
    bind ItemScorer to UserUserItemScorer
    set NeighborhoodSize to 20
	bind NeighborFinder to NeighborhoodFinderZona
	within (UserVectorNormalizer) {
		bind VectorNormalizer to MeanCenteringVectorNormalizer
	}
}


addComponent RecommenderConfigurationList.create(userZona)

bind ItemScorer to LogisticItemScorer
set LogisticTrainingSplit.TrainingBalance to 2.0

bind (BaselineScorer, ItemScorer) to BiasItemScorer
bind BiasModel to UserItemBiasModel