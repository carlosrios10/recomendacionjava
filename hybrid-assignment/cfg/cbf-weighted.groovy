import org.lenskit.api.ItemScorer
import org.lenskit.mooc.cbf.TFIDFItemScorer
import org.lenskit.mooc.cbf.UserProfileBuilder
import org.lenskit.mooc.cbf.WeightedUserProfileBuilder

// the core: use our item scorer
bind ItemScorer to TFIDFItemScorer
// with the basic profile builder
bind UserProfileBuilder to WeightedUserProfileBuilder
