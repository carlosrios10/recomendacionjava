import org.grouplens.lenskit.iterative.IterationCount
import org.grouplens.lenskit.iterative.LearningRate
import org.isistan.lbsn.hybrid.LogisticItemScorerSimple
import org.isistan.lbsn.hybrid.LogisticTrainingSplit
import org.isistan.lbsn.hybrid.RecommenderConfigurationList
import org.isistan.lbsn.uu.NeighborhoodFinderZonaKmeans
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.mf.funksvd.FeatureCount
import org.lenskit.mf.funksvd.FunkSVDItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

def uuzona = {
    bind ItemScorer to UserUserItemScorer
    set NeighborhoodSize to 20
	bind NeighborFinder to NeighborhoodFinderZonaKmeans
	within (UserVectorNormalizer) {
		bind VectorNormalizer to MeanCenteringVectorNormalizer
	}
}

def svd = {
    bind ItemScorer to FunkSVDItemScorer
    set FeatureCount to 20
    set IterationCount to 125
    set LearningRate to 0.0015

    bind (BaselineScorer, ItemScorer) to BiasItemScorer
    bind BiasModel to UserItemBiasModel
}

addComponent RecommenderConfigurationList.create(svd, uuzona)

bind ItemScorer to LogisticItemScorerSimple
set LogisticTrainingSplit.TrainingBalance to 2.0
