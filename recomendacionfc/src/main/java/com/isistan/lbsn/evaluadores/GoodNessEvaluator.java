package com.isistan.lbsn.evaluadores;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.common.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.collect.Lists;
import com.isistan.lbsn.builderrecomender.RecomendadorBuilder;

public class GoodNessEvaluator {
	private static final Logger log = LoggerFactory.getLogger(GoodNessEvaluator.class);
	private final Random random;
	public GoodNessEvaluator() {
		super();
		random = RandomUtils.getRandom();
	}
	private void splitUsersPrefsWithoutUserV(double trainingPercentage,
			FastByIDMap<PreferenceArray> trainingPrefs,
			long idUserV,
			DataModel dataModel) throws TasteException {
		LongPrimitiveIterator usersIterable = dataModel.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUserTrain =  usersIterable.next();
			if( (idUserTrain !=  idUserV)){
				PreferenceArray prefsTrain = dataModel.getPreferencesFromUser(idUserTrain);
				trainingPrefs.put(idUserTrain, prefsTrain);
			}
		}

	}
	

	
	
	private void getTestPrefence(double trainingPercentage,
			FastByIDMap<PreferenceArray> testPrefs,
			FastByIDMap<PreferenceArray> trainingPrefs,
			long userID,
			DataModel dataModel) throws TasteException {
			List<Preference> oneUserTrainingPrefs = null;
			List<Preference> oneUserTestPrefs = null;
			PreferenceArray prefs = dataModel.getPreferencesFromUser(userID);
			int size = prefs.length();
			for (int i = 0; i < size; i++) {
				Preference newPref = new GenericPreference(userID, prefs.getItemID(i), prefs.getValue(i));
				
				//if (random.nextDouble() < trainingPercentage)
				if ((i % 2)==0) {
					if (oneUserTrainingPrefs == null) {
						oneUserTrainingPrefs = Lists.newArrayListWithCapacity(3);
					}
					oneUserTrainingPrefs.add(newPref);
					
				} else {
					if (oneUserTestPrefs == null) {
						oneUserTestPrefs = Lists.newArrayListWithCapacity(3);
					}
					oneUserTestPrefs.add(newPref);
					
				}
			}
			if (oneUserTrainingPrefs != null) {
				trainingPrefs.put(userID, new GenericUserPreferenceArray(oneUserTrainingPrefs));
				if (oneUserTestPrefs != null) {
					testPrefs.put(userID, new GenericUserPreferenceArray(oneUserTestPrefs));
				}
			}
	}
	
	public void evaluate(DataModel dataModel,double trainingPercentage, double evaluationPercentage,String nombreArchivo)
					throws TasteException, IOException {
		int numUsers = dataModel.getNumUsers();
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), '\t',CSVWriter.NO_QUOTE_CHARACTER);
		
		LongPrimitiveIterator usersIterable = dataModel.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUserV = usersIterable.next();
			FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(
					1 + (int) (evaluationPercentage * numUsers));
			splitUsersPrefsWithoutUserV(trainingPercentage, trainingPrefs,idUserV, dataModel);
			
			LongPrimitiveIterator usersIterable2 = dataModel.getUserIDs();
			while (usersIterable2.hasNext()) {
				Long idUserU = usersIterable2.next();
				
				if( !idUserU.equals(idUserV) ){
					
					FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(2);
					trainingPrefs.remove(idUserU);
					getTestPrefence(0.5,testPrefs,trainingPrefs,idUserU,dataModel);
					DataModel testModel =  new GenericDataModel(testPrefs);

					DataModel trainingModel =  new GenericDataModel(trainingPrefs);
					RecommenderBuilder recBuilderSinV = new RecomendadorBuilder(trainingModel);
					double scoreMaeSinV = new AverageAbsoluteDifferenceRecommenderEvaluatorTrainTest().evaluate(recBuilderSinV,null,testModel, 0.0, 1);
					
					PreferenceArray prefs = dataModel.getPreferencesFromUser(idUserV);
					trainingPrefs.put(idUserV, prefs);
					DataModel trainingModel2 =  new GenericDataModel(trainingPrefs);
					RecommenderBuilder recBuilderConV = new RecomendadorBuilder(trainingModel2);
					double scoreMaeConV = new AverageAbsoluteDifferenceRecommenderEvaluatorTrainTest().evaluate(recBuilderConV,null,testModel, 0.0, 1);
					boolean isbuenVecino =  (scoreMaeSinV > scoreMaeConV);
					boolean isMalVecino =  (scoreMaeSinV < scoreMaeConV);
					boolean isNeutro =  (scoreMaeSinV == scoreMaeConV);
					
					String resul = idUserU+"#"+idUserV+"#"+scoreMaeSinV+"#"+scoreMaeConV+"#"+isbuenVecino+"#"+isMalVecino;
				    String[] entries = resul.split("#"); 
				    writer.writeNext(entries);
				    
				    trainingPrefs.remove(idUserV);
				    trainingPrefs.remove(idUserU);
				    trainingPrefs.put(idUserU, dataModel.getPreferencesFromUser(idUserU));

				}


			}
		}
		
		writer.close();


	}
}
