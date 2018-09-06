import org.isistan.lbsn.uu.DistBetweenZonas
import org.isistan.lbsn.uu.NeighborhoodFinderZona
import org.isistan.lbsn.uu.NeighborhoodFinderZonaDBscan
import org.isistan.lbsn.uu.NeighborhoodFinderZonaKmeans
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

for (d in  [0.3,0.5,1.0,2.0,3.0]) {
	 for(n in [10,20,30,50,100]) {
		algorithm("UserUserZonaDBscan") {
			bind ItemScorer to UserUserItemScorer
			set NeighborhoodSize to n
			attributes["NeighBrs"] = n
			bind NeighborFinder to NeighborhoodFinderZonaDBscan
			set DistBetweenZonas to d
			attributes["DistZonas"] = d
			within (UserVectorNormalizer) {
						bind VectorNormalizer to MeanCenteringVectorNormalizer
			} 
		}
		
		algorithm("UserUserZonaKmeans") {
			bind ItemScorer to UserUserItemScorer
			set NeighborhoodSize to n
			attributes["NeighBrs"] = n
			bind NeighborFinder to NeighborhoodFinderZonaKmeans
			set DistBetweenZonas to d
			attributes["DistZonas"] = d
			within (UserVectorNormalizer) {
						bind VectorNormalizer to MeanCenteringVectorNormalizer
			} 
		}	
	
		algorithm("UserUserZona") {
			bind ItemScorer to UserUserItemScorer
			set NeighborhoodSize to n
			attributes["NeighBrs"] = n
			bind NeighborFinder to NeighborhoodFinderZona
			set DistBetweenZonas to d
			attributes["DistZonas"] = d
			within (UserVectorNormalizer) {
						bind VectorNormalizer to MeanCenteringVectorNormalizer
			}
		}
	}
}
