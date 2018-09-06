import java.io.File;

import javax.inject.Inject;
import javax.inject.Provider;

import org.lenskit.api.ItemScorer;
import org.lenskit.data.dao.EventDAO;
import org.lenskit.data.dao.UserDAO;
import org.lenskit.eval.traintest.QueryData;
import org.lenskit.external.ExternalProcessItemScorerBuilder;
import org.lenskit.inject.Transient;

class ExternalItemMeanScorerBuilder implements Provider<ItemScorer>{
	EventDAO eventDAO
	UserDAO userDAO
 
	@Inject
	public ExternalItemMeanScorerBuilder(@Transient EventDAO events,
										 @Transient @QueryData UserDAO users) {
		eventDAO = events
		userDAO = users
	}
 
	@Override
	ItemScorer get() {
		def wrk = new File("external-scratch")
		wrk.mkdirs()
		def builder = new ExternalProcessItemScorerBuilder()
		// Note: don't use file names because it will interact badly with crossfolding
		return builder.setWorkingDir(wrk)
					  .setExecutable("C:/Users/carlo/Python27/python") //can be "R", "matlab", "ruby" etc
					  .addArgument("../python/item_mean.py") //relative (or absolute) location of sample recommender
					  .addArgument("--for-users")
					  .addRatingFileArgument(eventDAO)
					  .addUserFileArgument(userDAO)
					  .build()
	}
}

algorithm("ExternalAlgorithm") {
	bind ItemScorer toProvider ExternalItemMeanScorerBuilder
}
 