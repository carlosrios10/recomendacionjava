import org.isistan.lbsn.uu.DistBetweenZonas
import org.isistan.lbsn.uu.NeighborhoodFinderGrafo
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

bind ItemScorer to UserUserItemScorer
bind VectorNormalizer to MeanCenteringVectorNormalizer
set NeighborhoodSize to 100
bind NeighborFinder to NeighborhoodFinderGrafo
set DistBetweenZonas to 0.8
 
