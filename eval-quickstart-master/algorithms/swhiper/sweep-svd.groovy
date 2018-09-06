import org.grouplens.lenskit.iterative.IterationCount
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.baseline.ItemMeanRatingItemScorer
import org.lenskit.baseline.UserMeanBaseline
import org.lenskit.baseline.UserMeanItemScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.mf.funksvd.FeatureCount
import org.lenskit.mf.funksvd.FunkSVDItemScorer



for (n in  [5,10,20,30,40,50,60,70,80,90,100,200,300,500,1000]) {

	algorithm("FunkSVD") {
		bind ItemScorer to FunkSVDItemScorer
		bind (BaselineScorer, ItemScorer) to BiasItemScorer
		bind BiasModel to UserItemBiasModel
		set FeatureCount to n
		attributes["FeatureC"] = n
		set IterationCount to 125
	}
	
	algorithm("FunkSVD2") {
		bind ItemScorer to FunkSVDItemScorer
		bind (BaselineScorer, ItemScorer) to UserMeanItemScorer
		bind (UserMeanBaseline, ItemScorer) to ItemMeanRatingItemScorer
		set FeatureCount to n
		attributes["FeatureC"] = n
		set IterationCount to 125
	}
	

}
