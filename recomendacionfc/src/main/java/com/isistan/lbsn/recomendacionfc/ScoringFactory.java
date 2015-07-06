package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.model.DataModel;

import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioItem;
import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioUsuario;
import com.isistan.lbsn.scoring.ScoringOverlap;
import com.isistan.lbsn.similitudestructural.GrafoModel;


/**
 * Clase que crea distintos tipos de funciones de scoring.
 * @author Usuarioç
 *
 */
public class ScoringFactory {
	public enum ScoringType {USER_OVERLAP,CERCANIA_USUARIO_ITEM,CERCANIA_USUARIO_USUARIO};
	
	public static Scoring build( ScoringType scoringType,
								DataModel dataModel, 
								GrafoModel grafoModel,
								UserModel userModel,
								ItemModel itemModel) {
		Scoring scoring = null;
		switch (scoringType) {
		case USER_OVERLAP:
			scoring = new ScoringOverlap(grafoModel, dataModel);
			return scoring;
		case CERCANIA_USUARIO_ITEM:
			scoring = new ScoringCercaniaUsuarioItem(grafoModel, dataModel);
			return scoring;
		case CERCANIA_USUARIO_USUARIO:
			scoring = new ScoringCercaniaUsuarioUsuario(grafoModel, dataModel,userModel,itemModel);
			return scoring;
		default:
			return null; 
		}
	}
}
