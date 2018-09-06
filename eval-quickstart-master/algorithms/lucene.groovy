import org.isistan.lbsn.ii.LuceneItemItemModel
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.knn.item.model.ItemItemModel
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

bind ItemScorer to ItemItemScorer
set NeighborhoodSize to 30
bind ItemItemModel to LuceneItemItemModel
bind VectorNormalizer to MeanCenteringVectorNormalizer

//bind (BaselineScorer, ItemScorer) to BiasItemScorer
//bind BiasModel to UserItemBiasModel