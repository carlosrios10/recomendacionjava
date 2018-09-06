import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

bind ItemScorer to UserUserItemScorer
bind VectorNormalizer to MeanCenteringVectorNormalizer
set NeighborhoodSize to 20



//bind (BaselineScorer, ItemScorer) to BiasItemScorer
//bind BiasModel to UserItemBiasModel