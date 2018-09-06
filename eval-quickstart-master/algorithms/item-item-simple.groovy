import org.isistan.lbsn.ii.SimpleItemBasedItemScorer
import org.isistan.lbsn.ii.SimpleItemItemScorer
import org.lenskit.api.ItemBasedItemScorer
import org.lenskit.api.ItemScorer

bind ItemScorer to SimpleItemItemScorer
bind ItemBasedItemScorer to SimpleItemBasedItemScorer
