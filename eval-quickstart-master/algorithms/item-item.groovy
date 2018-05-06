import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer

bind ItemScorer to ItemItemScorer
set NeighborhoodSize to 2
//bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
//within (UserVectorNormalizer) {
//    bind (BaselineScorer, ItemScorer) to ItemMeanRatingItemScorer
//}
//within (UserVectorNormalizer) {
//	// for normalization, just center on user means
//	bind VectorNormalizer to MeanCenteringVectorNormalizer
//}

