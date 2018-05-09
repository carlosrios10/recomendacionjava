import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.mooc.hybrid.LogisticItemScorer
import org.lenskit.mooc.hybrid.LogisticTrainingSplit
import org.lenskit.mooc.hybrid.RecommenderConfigurationList
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

def uu = {
	// use our item scorer
	bind ItemScorer to UserUserItemScorer
	within (UserVectorNormalizer) {
		// for normalization, just center on user means
		bind VectorNormalizer to MeanCenteringVectorNormalizer
	}
	set NeighborhoodSize to 20
}

addComponent RecommenderConfigurationList.create(uu)

bind ItemScorer to LogisticItemScorer
set LogisticTrainingSplit.TrainingBalance to 2.0

bind (BaselineScorer, ItemScorer) to BiasItemScorer
bind BiasModel to UserItemBiasModel