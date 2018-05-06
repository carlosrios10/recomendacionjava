import org.isistan.lbsn.cbf.ATTModel
import org.isistan.lbsn.cbf.CBFModel
import org.isistan.lbsn.cbf.TFIDFItemScorer
import org.isistan.lbsn.cbf.ThresholdUserProfileBuilder
import org.isistan.lbsn.cbf.UserProfileBuilder
import org.lenskit.api.ItemScorer

bind ItemScorer to TFIDFItemScorer
bind CBFModel to ATTModel
// with the basic profile builder
bind UserProfileBuilder to ThresholdUserProfileBuilder