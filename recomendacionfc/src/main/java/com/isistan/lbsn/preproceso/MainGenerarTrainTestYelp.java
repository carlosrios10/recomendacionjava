package com.isistan.lbsn.preproceso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.common.RandomUtils;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.collect.Lists;
import com.isistan.lbsn.config.MyProperties;

public class MainGenerarTrainTestYelp {
	  private static Random random;
	public static void main(String[] args) {
		try {
			System.out.println("INICIO - MainGenerarTrainTest -");
			//RandomUtils.useTestSeed();
			//random = RandomUtils.getRandom();
			DataModel ratingModelEvaluar = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaseratingtotal")));
			//DataModel ratingModelTotal = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
		   
			int numUsers = ratingModelEvaluar.getNumUsers();
		    double trainingPercentage = 0.7 ;
			FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(numUsers);
		    FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(numUsers);
		    
		    LongPrimitiveIterator it = ratingModelEvaluar.getUserIDs();
		    while (it.hasNext()) {
		      random = RandomUtils.getRandom();
		      long userID = it.nextLong();
		      splitOneUsersPrefs(trainingPercentage, trainingPrefs, testPrefs, userID, ratingModelEvaluar);
		    }
		    DataModel trainingModel =  new  GenericDataModel(trainingPrefs);
//			FastByIDMap<PreferenceArray> preferencias = filtrarPreferenciasdeModelTotal(trainingModel,ratingModelTotal);
//			DataModel modelFiltrado = new GenericDataModel(preferencias);
		    DataModel testingModel = new GenericDataModel(testPrefs);
		    String resulTest = MyProperties.getInstance().getProperty("resultados")+"test_matriz_estrella_mas_10.csv";
		    String resulTrain = MyProperties.getInstance().getProperty("resultados")+"train_matriz_estrella_mas_10.csv";
		    toCSV(testingModel,resulTest);
		    toCSV(trainingModel,resulTrain);
		    System.out.println("FIN - MainGenerarTrainTest -");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}

	}

	private static void toCSV(DataModel model, String nombreArchivo) throws IOException, TasteException {
		CSVWriter writer = new CSVWriter(new FileWriter(nombreArchivo), ',',CSVWriter.NO_QUOTE_CHARACTER);
		LongPrimitiveIterator usersIterable = model.getUserIDs();
		while (usersIterable.hasNext()) {
			Long idUser =  usersIterable.next();
			PreferenceArray pref = model.getPreferencesFromUser(idUser);
			for (Preference preference : pref) {
				String resul = preference.getUserID()+"#"+preference.getItemID()+"#"+preference.getValue();
			    String[] entries = resul.split("#"); 
			    writer.writeNext(entries);
			}
			}
		writer.close();
		
	}

	private static void splitOneUsersPrefs(double trainingPercentage,
			FastByIDMap<PreferenceArray> trainingPrefs,
			FastByIDMap<PreferenceArray> testPrefs,
			long userID,
			DataModel dataModel) throws TasteException {
		List<Preference> oneUserTrainingPrefs = null;
		List<Preference> oneUserTestPrefs = null;
		PreferenceArray prefs = dataModel.getPreferencesFromUser(userID);
		int size = prefs.length();
		for (int i = 0; i < size; i++) {
			Preference newPref = new GenericPreference(userID, prefs.getItemID(i), prefs.getValue(i));
			if (random.nextDouble() < trainingPercentage) {
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
	private static boolean existeItem(long itemId, PreferenceArray arrayPref){
		int size = arrayPref.length();
		for (int i = 0; i < size; i++) {
			long itemId2 = arrayPref.getItemID(i);
			if (itemId == itemId2){
				return true;
			}

		}
		return false;

	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	//Filtra las preferencias en modelTotal que pertenecen al test para cada usuario de model
	//obtengo las preferencias de modelTotal
	//realizo una interseccion con las preferencias de model
	//eliminar aquellas preferencias que estan en model total pero no estan en model
	private static FastByIDMap<PreferenceArray> filtrarPreferenciasdeModelTotal(DataModel model,DataModel modelTotal)
			throws TasteException {
		FastByIDMap<PreferenceArray> validPref = new FastByIDMap<PreferenceArray>(modelTotal.getNumUsers());
		LongPrimitiveIterator it = modelTotal.getUserIDs();
		while (it.hasNext()) {
			long userIDModelTotal = it.nextLong();
			PreferenceArray prefModelTotal = modelTotal.getPreferencesFromUser(userIDModelTotal);
			List<Preference> prefs2 = Lists.newArrayListWithCapacity(prefModelTotal.length());
			for (Preference pref : prefModelTotal) {
				prefs2.add(pref);
			}
			try {
				PreferenceArray prefModel = model.getPreferencesFromUser(userIDModelTotal);
				for (Iterator<Preference> iterator = prefs2.iterator(); iterator.hasNext();) {
					Preference pref = iterator.next();
					if (!existeItem(pref.getItemID(), prefModel)) {
						iterator.remove();
					}
				}

			} catch (Exception e) {
				///si un usuario que esta en model total no esta en model, entonces no hace nada
				//solo lo agrega a validpref
			}
			validPref.put(userIDModelTotal, new GenericUserPreferenceArray(prefs2));
		}
		return validPref;
	}

}
