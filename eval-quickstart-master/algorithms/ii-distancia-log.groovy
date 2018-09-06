import org.isistan.lbsn.hybrid.LogisticItemScorer
import org.isistan.lbsn.hybrid.LogisticTrainingSplit
import org.isistan.lbsn.hybrid.RecommenderConfigurationList
import org.isistan.lbsn.ii.DistanciaItemItemModel
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.ItemBiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.knn.item.model.ItemItemModel
import org.lenskit.transform.normalize.BiasUserVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer

def ii = {
	bind ItemScorer to ItemItemScorer
	set NeighborhoodSize to 20
	bind ItemItemModel to DistanciaItemItemModel
	bind UserVectorNormalizer to BiasUserVectorNormalizer
	within (UserVectorNormalizer) {
		bind BiasModel to ItemBiasModel
	}
	
	bind (BaselineScorer, ItemScorer) to BiasItemScorer
	bind BiasModel to UserItemBiasModel
}

addComponent RecommenderConfigurationList.create(ii)

bind ItemScorer to LogisticItemScorer
set LogisticTrainingSplit.TrainingBalance to 2.0

bind (BaselineScorer, ItemScorer) to BiasItemScorer
bind BiasModel to UserItemBiasModel