package com.isistan.lbsn.evaluadores;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.mahout.cf.taste.common.TasteException;
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

import com.google.common.collect.Lists;
import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.recomendacionfc.Configuracion;
import com.isistan.lbsn.recomendacionfc.EvaluacionEsquema;

public class EvluadorCantidadVecinos {
	private static final Logger log = LoggerFactory.getLogger(EvluadorCantidadVecinos.class);
	private final Random random;
	int nivel;
	boolean booleanKN;
	boolean booleanKF1;
	boolean booleanKF2;
	boolean booleanKF3;
	boolean booleanKF4;
	boolean booleanKF5;
	boolean booleanZona;
	boolean booleanGrupo;
	boolean booleanGrupoN2;
	public enum TypeNeigh {
			K_NEIGHBORHOOD,
		   K_FRIENDS,
		   K_FRIENDS_NIVEL_1,
		   K_FRIENDS_NIVEL_2,
		   K_FRIENDS_NIVEL_3,
		   K_FRIENDS_NIVEL_4,
		   K_FRIENDS_NIVEL_5,
		   THRESHOLD,
		   K_FRIENDS_FRIENDS,
		   THRESHOLD_SCORING,
		   K_NEIGHBORHOOD_SCORING,
		   K_NEIGHBORHOOD_GRUPO,
		   K_NEIGHBORHOOD_GRUPO_NIVEL_2,
		   K_NEIGHBORHOOD_ZONA};
	public EvluadorCantidadVecinos() {
		random = RandomUtils.getRandom();
	} 
	public ResultadoEvaluarCantidadVecinos evaluate( 
			Configuracion config,
			DataModel dataModelTotal,
			DataModel dataModel,
			GrafoModel grafoModel,
			UserModel userModel,
			double trainingPercentage)
					throws TasteException {
		if(config.getTypeNeigh().name() == TypeNeigh.K_NEIGHBORHOOD.name() ){
			return   calcularVecinosBase(dataModel, userModel, trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_FRIENDS_NIVEL_1.name() && booleanKF1 == false ){
			nivel = 1;
			booleanKF1 = true;
			return calcularVecinosRed(dataModel, grafoModel, trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_FRIENDS_NIVEL_2.name() && booleanKF2 == false ){
			nivel = 2;
			booleanKF2 = true;
			return calcularVecinosRed(dataModel, grafoModel, trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_FRIENDS_NIVEL_3.name() && booleanKF3 == false){
			nivel = 3;
			booleanKF3 = true;
			return calcularVecinosRed(dataModel, grafoModel, trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_FRIENDS_NIVEL_4.name() && booleanKF4 == false){
			nivel = 4;
			booleanKF4 = true;
			return calcularVecinosRed(dataModel, grafoModel, trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_FRIENDS_NIVEL_5.name() && booleanKF5 == false){
			nivel = 5;
			booleanKF5 = true;
			return calcularVecinosRed(dataModel, grafoModel, trainingPercentage);
		}
		
		if(config.getTypeNeigh().name() == TypeNeigh.K_NEIGHBORHOOD_GRUPO.name() && booleanGrupo == false){
			nivel = 1;
			booleanGrupo = true;
			return calcularVecinosGrupo(dataModel,userModel,trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_NEIGHBORHOOD_GRUPO_NIVEL_2.name() && booleanGrupoN2 == false){
			nivel = 2;
			booleanGrupoN2 = true;
			return calcularVecinosGrupo(dataModel,userModel,trainingPercentage);
		}
		if(config.getTypeNeigh().name() == TypeNeigh.K_NEIGHBORHOOD_ZONA.name() && booleanZona == false){
			booleanZona = true;
			return calcularVecinosZonas(dataModel,userModel,trainingPercentage);
		}
		
		return new ResultadoEvaluarCantidadVecinos(0,0,0,0);
	}
	
	private ResultadoEvaluarCantidadVecinos calcularVecinosBase(
			DataModel dataModel,UserModel userModel,
			double trainingPercentage)
			throws TasteException {
			log.info("Calcular Vecinos Base");
			int cantidadUsuarioTestConVecinos=0; 
			int cantidadUsuariosTest = 0;
			int totalVecinos = 0;
			double promedioVecinos = 0.0;
			int cantidadPreferenciasTest = 0;
			int numUsers = dataModel.getNumUsers();
			FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			
			LongPrimitiveIterator it = dataModel.getUserIDs();
			while (it.hasNext()) {
				long userID = it.nextLong();
				splitOneUsersPrefs(trainingPercentage, trainingPrefs, testPrefs, userID, dataModel);
			}
			for (Map.Entry<Long,PreferenceArray> entry : testPrefs.entrySet()) {
				cantidadUsuariosTest++;
				int cantidadVecinos = 0;
				Long userID = entry.getKey();
				cantidadPreferenciasTest = cantidadPreferenciasTest + testPrefs.get(userID).length();
				totalVecinos = totalVecinos + cantidadVecinos;
			}
			
			promedioVecinos = 0;
			cantidadUsuarioTestConVecinos = cantidadUsuariosTest;
			
			return new ResultadoEvaluarCantidadVecinos(cantidadUsuariosTest, 
														promedioVecinos,
														cantidadUsuarioTestConVecinos,
														cantidadPreferenciasTest);
	}

	private ResultadoEvaluarCantidadVecinos calcularVecinosGrupo(
			DataModel dataModel,UserModel userModel,
			double trainingPercentage)
			throws TasteException {
			log.info("Calcular Vecinos Grupo Nivel " + nivel);
			int cantidadUsuarioTestConVecinos=0; 
			int cantidadUsuariosTest = 0;
			int totalVecinos = 0;
			int cantidadPreferenciasTest = 0;
			double promedioVecinos = 0.0;
			int numUsers = dataModel.getNumUsers();
			FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			
			LongPrimitiveIterator it = dataModel.getUserIDs();
			while (it.hasNext()) {
				long userID = it.nextLong();
				splitOneUsersPrefs(trainingPercentage, trainingPrefs, testPrefs, userID, dataModel);
			}
			for (Map.Entry<Long,PreferenceArray> entry : testPrefs.entrySet()) {
				cantidadUsuariosTest++;
				int cantidadVecinos = 0;
				User user = userModel.getUser(entry.getKey());
				Collection<Long> vecinos = userModel.getUserGrupo(user,nivel);
				cantidadPreferenciasTest = cantidadPreferenciasTest + testPrefs.get(entry.getKey()).length();
				if( vecinos != null ){
					if( vecinos.size()>1 ){
					cantidadVecinos = vecinos.size();
					cantidadUsuarioTestConVecinos++;
					}
				}
				totalVecinos = totalVecinos + cantidadVecinos;
			}
			
			promedioVecinos = totalVecinos/cantidadUsuarioTestConVecinos;
			return new ResultadoEvaluarCantidadVecinos(cantidadUsuariosTest, 
					promedioVecinos,
					cantidadUsuarioTestConVecinos,
					cantidadPreferenciasTest);
	}
	private ResultadoEvaluarCantidadVecinos calcularVecinosZonas(
			DataModel dataModel,UserModel userModel,
			double trainingPercentage)
			throws TasteException {
			log.info("Calcular Vecinos Zonas");
			int cantidadUsuarioTestConVecinos=0; 
			int cantidadUsuariosTest = 0;
			int totalVecinos = 0;
			int cantidadPreferenciasTest = 0;
			double promedioVecinos = 0.0;
			int numUsers = dataModel.getNumUsers();
			FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			
			LongPrimitiveIterator it = dataModel.getUserIDs();
			while (it.hasNext()) {
				long userID = it.nextLong();
				splitOneUsersPrefs(trainingPercentage, trainingPrefs, testPrefs, userID, dataModel);
			}
			for (Map.Entry<Long,PreferenceArray> entry : testPrefs.entrySet()) {
				cantidadUsuariosTest++;
				int cantidadVecinos = 0;
				User user = userModel.getUser(entry.getKey());
				Collection<Long> vecinos = userModel.getUserZona(user);
				cantidadPreferenciasTest = cantidadPreferenciasTest + testPrefs.get(entry.getKey()).length();
				if( vecinos != null ){
					if( vecinos.size()>1 ){
					cantidadVecinos = vecinos.size();
					cantidadUsuarioTestConVecinos++;
					}
				}
				totalVecinos = totalVecinos + cantidadVecinos;
			}
			
			promedioVecinos = totalVecinos/cantidadUsuarioTestConVecinos;
			return new ResultadoEvaluarCantidadVecinos(cantidadUsuariosTest, 
					promedioVecinos,
					cantidadUsuarioTestConVecinos,
					 cantidadPreferenciasTest);
	}

	private ResultadoEvaluarCantidadVecinos calcularVecinosRed(
			DataModel dataModel, GrafoModel vecindario,
			double trainingPercentage)
			throws TasteException {
			log.info("Calcular Vecinos Red");
			int cantidadUsuarioTestConVecinos=0; 
			int cantidadUsuariosTest = 0;
			int cantidadPreferenciasTest = 0;
			int totalVecinos = 0;
			double promedioVecinos = 0.0;
			int numUsers = dataModel.getNumUsers();
			FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(numUsers);
			LongPrimitiveIterator it = dataModel.getUserIDs();
			while ( it.hasNext() ) {
				long userID = it.nextLong();
				splitOneUsersPrefs(trainingPercentage, trainingPrefs, testPrefs, userID, dataModel);
			}
			
			for (Map.Entry<Long,PreferenceArray> entry : testPrefs.entrySet()) {
				cantidadUsuariosTest++;
				int cantidadVecinos = 0;
				Collection<Long> vecinos = vecindario.getFriendsMyFriends(entry.getKey(),nivel);
				cantidadPreferenciasTest = cantidadPreferenciasTest + testPrefs.get(entry.getKey()).length();
				if( vecinos != null ){
					if( vecinos.size()>1 ){
					cantidadVecinos = vecinos.size();
					cantidadUsuarioTestConVecinos++; 
					}
				}
				
				totalVecinos = totalVecinos + cantidadVecinos;
			}
			promedioVecinos = totalVecinos/cantidadUsuarioTestConVecinos;
			return new ResultadoEvaluarCantidadVecinos(cantidadUsuariosTest, 
					promedioVecinos,
					cantidadUsuarioTestConVecinos,
					cantidadPreferenciasTest);
	}
	public double getCantidadTrainTest(DataModel dataModel,double trainingPercentage) throws TasteException{
		int numUsers = dataModel.getNumUsers();
		FastByIDMap<PreferenceArray> trainingPrefs = new FastByIDMap<PreferenceArray>(numUsers);
		FastByIDMap<PreferenceArray> testPrefs = new FastByIDMap<PreferenceArray>(numUsers);
		LongPrimitiveIterator it = dataModel.getUserIDs();
		int cantidadPreferTest = 0;
		while ( it.hasNext() ) {
			long userID = it.nextLong();
			splitOneUsersPrefs(trainingPercentage, trainingPrefs, testPrefs, userID, dataModel);
		}
		for (Map.Entry<Long,PreferenceArray> entry : testPrefs.entrySet()) {
			cantidadPreferTest = cantidadPreferTest + entry.getValue().getIDs().length;

		}
		return cantidadPreferTest;
	}
//	  private void splitOneUsersPrefs(double trainingPercentage,
//              FastByIDMap<PreferenceArray> trainingPrefs,
//              FastByIDMap<PreferenceArray> testPrefs,
//              long userID,
//              DataModel dataModel) throws TasteException {
//		  List<Preference> oneUserTrainingPrefs = null;
//		  List<Preference> oneUserTestPrefs = null;
//		  PreferenceArray prefs = dataModel.getPreferencesFromUser(userID);
//		  int size = prefs.length();
//		  for (int i = 0; i < size; i++) {
//			  Preference newPref = new GenericPreference(userID, prefs.getItemID(i), prefs.getValue(i));
//			  if (random.nextDouble() < trainingPercentage) {
//				  if (oneUserTrainingPrefs == null) {
//					  oneUserTrainingPrefs = Lists.newArrayListWithCapacity(3);
//				  }
//				  oneUserTrainingPrefs.add(newPref);
//			  } else {
//				  if (oneUserTestPrefs == null) {
//					  oneUserTestPrefs = Lists.newArrayListWithCapacity(3);
//				  }
//				  oneUserTestPrefs.add(newPref);
//			  }
//		  }
//		  if (oneUserTrainingPrefs != null) {
//			  trainingPrefs.put(userID, new GenericUserPreferenceArray(oneUserTrainingPrefs));
//			  if (oneUserTestPrefs != null) {
//				  testPrefs.put(userID, new GenericUserPreferenceArray(oneUserTestPrefs));
//			  }
//		  }
//	  }
//	  
	  
		private void splitOneUsersPrefs(double trainingPercentage,
				FastByIDMap<PreferenceArray> trainingPrefs,
				FastByIDMap<PreferenceArray> testPrefs,
				long userID,
				DataModel dataModel) throws TasteException {
			if( trainingPercentage != 0.0){ 
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
			}else{
				PreferenceArray prefs = dataModel.getPreferencesFromUser(userID);
				testPrefs.put(userID, prefs);
			}
		}

}
