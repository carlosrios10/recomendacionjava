import org.isistan.lbsn.ii.LuceneItemItemModel
import org.isistan.lbsn.uu.NeighborhoodFinderZona
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.ItemBiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.knn.item.model.ItemItemModel
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.BiasUserVectorNormalizer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer



for (n in  [5,10,20,30,40,50,60,70,80,90,100]) {

	algorithm("UserUserZona") {
		bind ItemScorer to UserUserItemScorer
		bind NeighborFinder to NeighborhoodFinderZona
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	algorithm("UserUserBaseline") {
		bind ItemScorer to UserUserItemScorer
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	algorithm("ItemItemBaseline") {
		bind ItemScorer to ItemItemScorer
		bind UserVectorNormalizer to BiasUserVectorNormalizer
		within (UserVectorNormalizer) {
		    bind BiasModel to ItemBiasModel
		}
		bind (BaselineScorer, ItemScorer) to BiasItemScorer
		bind BiasModel to UserItemBiasModel
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	algorithm("ItemItemLucene") {
		bind ItemScorer to ItemItemScorer
		bind ItemItemModel to LuceneItemItemModel
		bind UserVectorNormalizer to BiasUserVectorNormalizer
		within (UserVectorNormalizer) {
		    bind BiasModel to ItemBiasModel
		}
		bind (BaselineScorer, ItemScorer) to BiasItemScorer
		bind BiasModel to UserItemBiasModel
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}

}
