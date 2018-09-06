import org.isistan.lbsn.ii.SimpleItemItemScorer
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer



for (n in  [5,10,20,30,40,50,60,70,80,90,100]) {

	algorithm("UserUserBaseline") {
		bind ItemScorer to UserUserItemScorer
		within (UserVectorNormalizer) {
			bind VectorNormalizer to MeanCenteringVectorNormalizer
		}
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	algorithm("ItemItemBaseline") {
		bind ItemScorer to SimpleItemItemScorer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	

}
