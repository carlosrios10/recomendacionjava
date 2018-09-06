import org.isistan.lbsn.uu.NeighborhoodFinderZonaKmeans
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

bind ItemScorer to UserUserItemScorer
set NeighborhoodSize to 20
bind NeighborFinder to NeighborhoodFinderZonaKmeans
within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
} 

//bind (BaselineScorer, ItemScorer) to BiasItemScorer
//bind BiasModel to UserItemBiasModel
