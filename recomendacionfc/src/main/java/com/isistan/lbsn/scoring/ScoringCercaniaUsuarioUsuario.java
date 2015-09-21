package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.recomendacionfc.Item;
import com.isistan.lbsn.recomendacionfc.Scoring;
import com.isistan.lbsn.recomendacionfc.User;
import com.isistan.lbsn.util.Util;

public class ScoringCercaniaUsuarioUsuario implements Scoring {
	GrafoModel grafoModel;
	DataModel dataModel;
	UserModel userModel;
	ItemModel itemModel;

	public ScoringCercaniaUsuarioUsuario(UserModel userModel,
			ItemModel itemModel) {
		super();
		this.userModel = userModel;
		this.itemModel = itemModel;
	}

	public ScoringCercaniaUsuarioUsuario(GrafoModel grafoModel,
			DataModel dataModel, UserModel userModel, ItemModel itemModel) {
		super();
		this.grafoModel = grafoModel;
		this.dataModel = dataModel;
		this.userModel = userModel;
		this.itemModel = itemModel;
	}

	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		return getScoring(userID1, userID2, -1);
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		User user1 = userModel.getUser(userID1);
		User  user2 = userModel.getUser(userID2);
		double distanciaKms = Util.distFrom(Double.valueOf(user1.getLatitud()), 
					  Double.valueOf(user1.getLongitud()), 
					  Double.valueOf(user2.getLatitud()), 
					  Double.valueOf(user2.getLongitud()));
		if(distanciaKms==0)
			return 1;
		else
		    return (1/distanciaKms);
	}

}
