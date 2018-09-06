package org.isistan.lbsn.hybrid;

import java.io.File;
import java.util.ArrayList;
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

import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongSet;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 * Trainer that builds logistic models.
 */
public class LogisticModelProviderWeka implements Provider<LogisticModel> {
	private static final Logger logger = LoggerFactory
			.getLogger(LogisticModelProviderWeka.class);
	private final LogisticTrainingSplit dataSplit;
	private final BiasModel baseline;
	private final RecommenderList recommenders;
	private final RatingSummary ratingSummary;
	private final int parameterCount;
	private final Random random;
	

	@Inject
	public LogisticModelProviderWeka(@Transient LogisticTrainingSplit split,
			@Transient UserBiasModel bias, @Transient RecommenderList recs,
			@Transient RatingSummary rs, @Transient Random rng) {
		dataSplit = split;
		baseline = bias;
		recommenders = recs;
		ratingSummary = rs;
		random = rng;
		parameterCount = 1 + recommenders.getRecommenderCount() + 1;

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
	
	public  Instances getDataSet(List<Rating> trainRating,List<Hashtable<Long, ResultMap>> scorings,int cantScorers) throws Exception {
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		
		attributes.add(new Attribute("bias"));
		attributes.add(new Attribute("popu"));
		
		for(int i = 0;i<cantScorers;i++) {
			attributes.add(new Attribute("comp"+i));
		}
		attributes.add(new Attribute("clase"));
		Instances dataRaw = new Instances("TrainInstance", attributes , 0);
		dataRaw.setClassIndex(dataRaw.numAttributes()-1); // Assuming z (z on lastindex) as classindex 
		
		for (Rating ratingTrain : trainRating) {
			long user = ratingTrain.getUserId();
			long item = ratingTrain.getItemId();
			double target = ratingTrain.getValue();
			// explanatory vars
			double[] vars = new double[parameterCount+1];
			double bias = this.baseline.getIntercept()
					+ this.baseline.getUserBias(user)
					+ this.baseline.getItemBias(item);
			double popularity = Math.log10(this.ratingSummary.getItemRatingCount(item));
			vars[0] = bias;
			vars[1] = popularity;
			int ind = 2;
			for (int i = 0; i < cantScorers; i++) {
				Hashtable<Long, ResultMap> scoreCache = scorings.get(i);
				ResultMap userMap = scoreCache.get(user);
				Result scoreValue = userMap.get(item);
				double scoreValueDouble = (null == scoreValue) ? 0: scoreValue.getScore() - bias;
				vars[ind] = scoreValueDouble;
				ind++;
			}
			vars[ind] = (target == -1) ? 2: target;
			Instance inst = new DenseInstance(1.0, vars);
			dataRaw.add(inst);
			
		}
		 NumericToNominal convert= new NumericToNominal();
	     String[] options= new String[2];
	     options[0]="-R";
	     options[1]=""+(dataRaw.classIndex()+1);  //range of variables to make numeric
	     convert.setOptions(options);
	     convert.setInputFormat(dataRaw);
	     Instances newData = Filter.useFilter(dataRaw, convert);
	     
		return newData;
	}
	public LogisticModel get() {
		List<Hashtable<Long, ResultMap>> scorings = new ArrayList<Hashtable<Long, ResultMap>>();

		List<ItemScorer> scorers = recommenders.getItemScorers();
		double intercept = 0;
		double[] params = new double[parameterCount];

		LogisticModel current = LogisticModel.create(intercept, params);
		List<Rating> trainRating = dataSplit.getTuneRatings();

		logger.info("Cargando Scoringss");
		LongSet usersTraining = getUsersFromRatings(trainRating);
		for (ItemScorer scorer : scorers) {
			Hashtable<Long, ResultMap> scoreCache = new Hashtable<Long, ResultMap>();
			scorings.add(scoreCache);
		}

		for (Long user : usersTraining) {
			LongSet usersItemTraining = getItemFromUser(trainRating, user);
				for (int i = 0; i < scorings.size(); i++) {
					Hashtable<Long, ResultMap> scoreCache = scorings.get(i);
					scoreCache.put(user,scorers.get(i).scoreWithDetails(user, usersItemTraining));
				}
		}
		logger.info("Fin cargando Scoring");

		logger.info("Inicio-Model Training weka");
		Instances trainingDataSet;
		try {
		trainingDataSet = getDataSet(trainRating,scorings,scorers.size());
		Logistic classifier = new weka.classifiers.functions.Logistic();
		Evaluation eval2 = new Evaluation(trainingDataSet);
		eval2.crossValidateModel(classifier, trainingDataSet, 10, new Random(1));
		logger.info(eval2.toSummaryString());
		logger.info(eval2.toClassDetailsString());
		classifier.buildClassifier(trainingDataSet);
		double [][] coef = classifier.coefficients();
		logger.info(classifier.toString());

		intercept = coef[0][0];
		for(int i = 1;i<=parameterCount;i++) {
			params[i-1] = coef[i][0];
		} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		current = LogisticModel.create(intercept, params);
		logger.info("Fin-Model Training weka" + current.toString());
		return current;
	}


}
