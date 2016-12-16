package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;

import com.isistan.lbsn.datamodels.GrafoModel;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.Item;
import com.isistan.lbsn.entidades.User;
import com.isistan.lbsn.util.Util;

public class ScoringFrecuenciaUsuarioItem implements Scoring{
	GrafoModel grafoModel;
	DataModel dataModel;
	UserModel userModel;
	ItemModel itemModel;

	public ScoringFrecuenciaUsuarioItem() {
		super();
	}
	public ScoringFrecuenciaUsuarioItem(GrafoModel grafoModel, DataModel dataModel,
			UserModel userModel, ItemModel itemModel) {
		super();
		this.grafoModel = grafoModel;
		this.dataModel = dataModel;
		this.userModel = userModel;
		this.itemModel = itemModel;
	}
	public ScoringFrecuenciaUsuarioItem(UserModel userModel, ItemModel itemModel) {
		super();
		this.userModel = userModel;
		this.itemModel = itemModel;
	}
	public ScoringFrecuenciaUsuarioItem(GrafoModel grafoModel, DataModel dataModel) {
		this.dataModel=dataModel;
		this.grafoModel=grafoModel;
	}
	public double getScoring(long userID1, long userID2, long itemID)
			throws TasteException {
		User user = userModel.getUser(userID2);
		Item item = itemModel.getItem(itemID);
		double distanciaKms = Util.distFrom(Double.valueOf(user.getLatitud()), 
					  Double.valueOf(user.getLongitud()), 
					  Double.valueOf(item.getLatitud()), 
					  Double.valueOf(item.getLongitud()));
		
		return (1/distanciaKms);
	}
	public double userSimilarity(long userID1, long userID2)
			throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}


}
