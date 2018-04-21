import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.baseline.ItemMeanRatingItemScorer
import org.lenskit.baseline.UserMeanBaseline
import org.lenskit.baseline.UserMeanItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

// [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 60, 70, 80, 100]
for (n in  [5, 10]) {
	algorithm("ItemItem") {
		bind ItemScorer to ItemItemScorer
		bind (BaselineScorer, ItemScorer) to UserMeanItemScorer
		bind (UserMeanBaseline, ItemScorer) to ItemMeanRatingItemScorer
		bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	algorithm("UserUser") {
		bind ItemScorer to UserUserItemScorer
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	

}
