package org.lenskit.mooc.hybrid;

import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.lenskit.api.ItemScorer;
import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.bias.BiasModel;
import org.lenskit.bias.UserBiasModel;
import org.lenskit.data.ratings.Rating;
import org.lenskit.data.ratings.RatingSummary;
import org.lenskit.inject.Transient;
import org.lenskit.results.BasicResult;
import org.lenskit.results.BasicResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Trainer that builds logistic models.
 */
public class LogisticModelProvider implements Provider<LogisticModel> {
	private static final Logger logger = LoggerFactory
			.getLogger(LogisticModelProvider.class);
	private static final double LEARNING_RATE = 0.00005;
	private static final int ITERATION_COUNT = 100;

	private final LogisticTrainingSplit dataSplit;
	private final BiasModel baseline;
	private final RecommenderList recommenders;
	private final RatingSummary ratingSummary;
	private final int parameterCount;
	private final Random random;

	@Inject
	public LogisticModelProvider(@Transient LogisticTrainingSplit split,
			@Transient UserBiasModel bias, @Transient RecommenderList recs,
			@Transient RatingSummary rs, @Transient Random rng) {
		dataSplit = split;
		baseline = bias;
		recommenders = recs;
		ratingSummary = rs;
		parameterCount = 1 + recommenders.getRecommenderCount() + 1;
		random = rng;
	}

	private Set<Long> getItemsFromRatings(List<Rating> trainRating) {
		Set<Long> longSet = new HashSet<Long>();
		for (Rating ratingTrain : trainRating) {
			long item = ratingTrain.getItemId();
			longSet.add(item);
		}
		return longSet;

	}


	private ResultMap calScores(long user, Set<Long> items, ItemScorer score) {
		List<Result> allResults = new ArrayList<>(items.size());
		for (long item : items) {

			Result scoreResult = score.score(user, item);
			double scoreValue = (null == scoreResult) ? 0.0 : scoreResult
					.getScore();

			Result r = new BasicResult(item, scoreValue);

			allResults.add(r);
		}

		return new BasicResultMap(allResults);
	}


	private boolean verificarScoring(List<Rating> trainRating,
			List<ItemScorer> scorers, List<Hashtable<Long, ResultMap>> scorings) {
		boolean completo = true;
		Set<Long> train = new HashSet<Long>();
		for (Rating ratingTrain : trainRating) {
			long user = ratingTrain.getUserId();
			train.add(user);
		}
		for (int i = 0; i < scorers.size(); i++) {
			Set<Long> cached = new HashSet<Long>(scorings.get(i).keySet());
			Set<Long> trainAux = new HashSet<Long>(train);
			trainAux.removeAll(cached);
			Hashtable<Long, ResultMap> scoreMap = scorings.get(i);
			cached.removeAll(train);
			if (!cached.isEmpty()) {
				logger.info("Error carga scoring : user que no estan en train "
						+ cached);
				completo = false;
			}
			if (!trainAux.isEmpty()) {
				completo = false;
				logger.info("Error carga scoring - users que no estan en cache : "
						+ trainAux);
			}

		}

		return completo;
	}
	private LongSet getUsersFromRatings(List<Rating> trainRating) {
		LongSet longSet = new LongArraySet();
		for (Rating ratingTrain : trainRating) {
			long user = ratingTrain.getUserId();
			longSet.add(user);
		}
		return longSet;
		
	}
	private LongSet getItemFromUser(List<Rating> trainRating,long user) {
		LongSet longSet = new LongArraySet();
		for (Rating ratingTrain : trainRating) {
			long userId = ratingTrain.getUserId();
			if(user==userId)
				longSet.add(ratingTrain.getItemId());
		}
		return longSet;

	}
	public LogisticModel get() {
		List<Hashtable<Long, ResultMap>> scorings = new ArrayList<Hashtable<Long, ResultMap>>();

		List<ItemScorer> scorers = recommenders.getItemScorers();
		double intercept = 0;
		double[] params = new double[parameterCount];

		LogisticModel current = LogisticModel.create(intercept, params);
		List<Rating> trainRating = dataSplit.getTuneRatings();

		logger.info("Cargando Scoringss");
		//Set<Long> itemsTraining = getItemsFromRatings(trainRating);
		LongSet usersTraining = getUsersFromRatings(trainRating);
		logger.info("Cargando Scoring -  cantidad de scorers " + scorers.size());
		logger.info("Cargando Scoring -  cantidad de users " + usersTraining.size());
		//logger.info("Cargando Scoring -  cantidad de items " + itemsTraining.size());

		for (ItemScorer scorer : scorers) {
			Hashtable<Long, ResultMap> scoreCache = new Hashtable<Long, ResultMap>();
			scorings.add(scoreCache);
		}

		for (Long user : usersTraining) {
			LongSet usersItemTraining = getItemFromUser(trainRating, user);
				for (int i = 0; i < scorings.size(); i++) {
					Hashtable<Long, ResultMap> scoreCache = scorings.get(i);
					scoreCache.put(user,scorers.get(i).scoreWithDetails(user, usersItemTraining));
//					if (!scoreCache.containsKey(user))
//						scoreCache.put(user,scorers.get(i).scoreWithDetails(user, usersItemTraining));
	
				}
		}

//		logger.info("Cargando Scoring - version vieja"); //VER ERROR DE ESTE CODIGO!! SE PIERDEN USUARIOS EN LA CACHE
//		for (Rating ratingTrain : trainRating) {
//			long user = ratingTrain.getUserId();
//			long item = ratingTrain.getItemId();
//				for (int i = 0; i < scorings.size(); i++) {
//					Hashtable<Long, ResultMap> scoreCache = scorings.get(i);
//					if (!scoreCache.containsKey(user))
//						scoreCache.put(user,
//								calScores(user, itemsTraining, scorers.get(i)));
//	
//				}
//		}

		logger.info("Fin cargando Scoring");

//		if (!verificarScoring(trainRating, scorers, scorings))
//			logger.info("Inicio-Model Training - Error al cargar los scorings");
//		else
//			logger.info("Inicio-Model Training - Correcta carga de los scorings");

		logger.info("Inicio-Model Training");
		// TODO Implement model training
		for (int n = 0; n < ITERATION_COUNT; n++) {
			Collections.shuffle(trainRating);
			for (Rating ratingTrain : trainRating) {

				long user = ratingTrain.getUserId();
				long item = ratingTrain.getItemId();
				double target = ratingTrain.getValue();

				// explanatory vars
				double[] vars = new double[parameterCount];
				double bias = this.baseline.getIntercept()
						+ this.baseline.getUserBias(user)
						+ this.baseline.getItemBias(item);
				double popularity = Math.log10(this.ratingSummary
						.getItemRatingCount(item));
				vars[0] = bias;
				vars[1] = popularity;
				int ind = 2;

				for (int i = 0; i < scorers.size(); i++) {
					Hashtable<Long, ResultMap> scoreCache = scorings.get(i);
					ResultMap userMap = scoreCache.get(user);
					Result scoreValue = userMap.get(item);
					double scoreValueDouble = (null == scoreValue) ? 0
							: scoreValue.getScore() - bias;
					vars[ind] = scoreValueDouble;
					ind++;

				}

				// prediction
				double predict = current.evaluate(-1 * target, vars);

				// Actualizar intercep
				intercept = intercept + this.LEARNING_RATE * target * (predict);
				// Actualizar params
				params[0] = params[0] + this.LEARNING_RATE * target * (predict)
						* bias;
				params[1] = params[1] + this.LEARNING_RATE * target * (predict)
						* popularity;
				int j = 2;
				for (ItemScorer score : scorers) {
					// Result scoreResult = score.score(user,item);
					// double xSubJvalue = (null ==
					// scoreResult)?0:scoreResult.getScore() - bias;
					params[j] = params[j] + this.LEARNING_RATE * target
							* (predict) * vars[j];
					j++;

				}
				current = LogisticModel.create(intercept, params);

			}

		}
		logger.info("Fin-Model Training");
		return current;
	}


}
