import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

// use our item scorer
bind ItemScorer to UserUserItemScorer
within (UserVectorNormalizer) {
	// for normalization, just center on user means
	bind VectorNormalizer to MeanCenteringVectorNormalizer
}
set NeighborhoodSize to 20