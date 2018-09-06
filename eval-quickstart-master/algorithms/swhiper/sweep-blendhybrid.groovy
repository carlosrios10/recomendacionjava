import org.grouplens.lenskit.iterative.IterationCount
import org.grouplens.lenskit.iterative.LearningRate
import org.isistan.lbsn.cbf.ATTModel
import org.isistan.lbsn.cbf.CBFModel
import org.isistan.lbsn.cbf.TFIDFItemScorer
import org.isistan.lbsn.cbf.TFIDFModel
import org.isistan.lbsn.cbf.ThresholdUserProfileBuilder
import org.isistan.lbsn.cbf.UserProfileBuilder
import org.isistan.lbsn.cbf.WeightedUserProfileBuilder
import org.isistan.lbsn.hybrid.BlendWeight
import org.isistan.lbsn.hybrid.Left
import org.isistan.lbsn.hybrid.LinearBlendItemScorer
import org.isistan.lbsn.hybrid.Right
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
import org.lenskit.mf.funksvd.FeatureCount
import org.lenskit.mf.funksvd.FunkSVDItemScorer
import org.lenskit.transform.normalize.BiasUserVectorNormalizer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer



bind ItemScorer to LinearBlendItemScorer
bind (BaselineScorer, ItemScorer) to BiasItemScorer
bind BiasModel to UserItemBiasModel

for (blend in [0, 0.2, 0.4, 0.6,0.8,1.0]) {
	
	
//    algorithm("zona-ii-blend") {
//        set BlendWeight to blend
//        attributes["BlendWeight"] = blend
//		
//		bind (Left, ItemScorer) to UserUserItemScorer
//		within(UserUserItemScorer){
//			set NeighborhoodSize to 20
//			bind NeighborFinder to NeighborhoodFinderZona
//			within (UserVectorNormalizer) {
//				bind VectorNormalizer to MeanCenteringVectorNormalizer
//			}
//			
//		}
//		
//        bind (Right, ItemScorer) to ItemItemScorer
//		within(ItemItemScorer){
//			set NeighborhoodSize to 20
//			bind UserVectorNormalizer to BiasUserVectorNormalizer
//			within (UserVectorNormalizer) {
//				bind BiasModel to ItemBiasModel
//			}
//		}
//
//
//    }
	
	algorithm("zona-svd-blend") {
		set BlendWeight to blend
		attributes["BlendWeight"] = blend

		bind (Left, ItemScorer) to UserUserItemScorer
		set NeighborhoodSize to 20
		bind NeighborFinder to NeighborhoodFinderZona
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}

		bind (Right, ItemScorer) to FunkSVDItemScorer
		set FeatureCount to 30
		set IterationCount to 125
		set LearningRate to 0.0015
	}
	
//	algorithm("zona-lucene-blend") {
//		set BlendWeight to blend
//		attributes["BlendWeight"] = blend
//
//		bind (Left, ItemScorer) to UserUserItemScorer
//		within(UserUserItemScorer){
//			set NeighborhoodSize to 20
//			bind NeighborFinder to NeighborhoodFinderZona
//			within (UserVectorNormalizer) {
//				bind VectorNormalizer to MeanCenteringVectorNormalizer
//			}
//		}
//        bind (Right, ItemScorer) to ItemItemScorer
//		within(ItemItemScorer){
//	        set NeighborhoodSize to 20
//	        bind ItemItemModel to LuceneItemItemModel
//	        bind UserVectorNormalizer to BiasUserVectorNormalizer
//	        within (UserVectorNormalizer) {
//	            bind BiasModel to ItemBiasModel
//	        }
//        }
//	}
	
	
	algorithm("zona-cbf-tfidf-basic-blend") {
		set BlendWeight to blend
		attributes["BlendWeight"] = blend

		bind (Left, ItemScorer) to UserUserItemScorer
		set NeighborhoodSize to 20
		bind NeighborFinder to NeighborhoodFinderZona
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}

		bind (Right, ItemScorer) to TFIDFItemScorer
		bind CBFModel to TFIDFModel
		bind UserProfileBuilder to ThresholdUserProfileBuilder
		
	}
	
	algorithm("zona-cbf-tfidf-weighted-blend") {
		set BlendWeight to blend
		attributes["BlendWeight"] = blend

		bind (Left, ItemScorer) to UserUserItemScorer
		set NeighborhoodSize to 20
		bind NeighborFinder to NeighborhoodFinderZona
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}

		bind (Right, ItemScorer) to TFIDFItemScorer
		bind CBFModel to TFIDFModel
		bind UserProfileBuilder to WeightedUserProfileBuilder
		
	}
	
	algorithm("zona-cbf-att-basic-blend") {
		set BlendWeight to blend
		attributes["BlendWeight"] = blend

		bind (Left, ItemScorer) to UserUserItemScorer
		set NeighborhoodSize to 20
		bind NeighborFinder to NeighborhoodFinderZona
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}
		
		bind (Right, ItemScorer) to TFIDFItemScorer
		bind CBFModel to ATTModel
		bind UserProfileBuilder to ThresholdUserProfileBuilder
	}
	
	algorithm("zona-cbf-att-weighted-blend") {
		set BlendWeight to blend
		attributes["BlendWeight"] = blend

		bind (Left, ItemScorer) to UserUserItemScorer
		set NeighborhoodSize to 20
		bind NeighborFinder to NeighborhoodFinderZona
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}

		bind (Right, ItemScorer) to TFIDFItemScorer
		bind CBFModel to ATTModel
		bind UserProfileBuilder to WeightedUserProfileBuilder
	}


}