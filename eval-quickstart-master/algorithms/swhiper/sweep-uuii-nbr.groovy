import org.isistan.lbsn.ii.DistanciaItemItemModel
import org.isistan.lbsn.ii.LuceneItemItemModel
import org.isistan.lbsn.ii.SimpleItemItemScorer
import org.lenskit.api.ItemScorer
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.knn.item.model.ItemItemModel
import org.lenskit.knn.user.UserUserItemScorer
import org.lenskit.transform.normalize.MeanCenteringVectorNormalizer;
import org.lenskit.transform.normalize.VectorNormalizer



for (n in  [10,20,30,40,50,60,70,80,90,100]) {

	algorithm("UserUser") {
		bind ItemScorer to UserUserItemScorer
		bind VectorNormalizer to MeanCenteringVectorNormalizer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	algorithm("ItemItem") {
		bind ItemScorer to ItemItemScorer
		bind VectorNormalizer to MeanCenteringVectorNormalizer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	algorithm("ItemItemLucene") {
		bind ItemScorer to ItemItemScorer
		bind ItemItemModel to LuceneItemItemModel
		bind VectorNormalizer to MeanCenteringVectorNormalizer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}

	algorithm("ItemItemDistancia") {
		bind ItemScorer to ItemItemScorer
		bind ItemItemModel to DistanciaItemItemModel
		bind VectorNormalizer to MeanCenteringVectorNormalizer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}
	
	algorithm("ItemItemSimple") {
		bind ItemScorer to SimpleItemItemScorer
		set NeighborhoodSize to n
		attributes["NNbrs"] = n
	}

}
