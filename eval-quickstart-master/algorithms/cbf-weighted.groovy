import org.isistan.lbsn.cbf.CBFModel
import org.isistan.lbsn.cbf.TFIDFItemScorer
import org.isistan.lbsn.cbf.TFIDFModel
import org.isistan.lbsn.cbf.UserProfileBuilder
import org.isistan.lbsn.cbf.WeightedUserProfileBuilder
import org.lenskit.api.ItemScorer

// the core: use our item scorer
bind ItemScorer to TFIDFItemScorer
bind CBFModel to TFIDFModel
// with the basic profile builder
bind UserProfileBuilder to WeightedUserProfileBuilder
