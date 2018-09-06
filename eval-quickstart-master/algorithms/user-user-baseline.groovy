import org.isistan.lbsn.uu.NeighborhoodFinderZona
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

bind ItemScorer to UserUserItemScorer
within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
} 
set NeighborhoodSize to 2

