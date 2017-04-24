package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.isistan.lbsn.datamodels.GrafoModel;

public class ScoringLR implements Scoring{
	
	double PARAM_VISITAS_VISITAS = 44.55304635;
	double PARAM_VISITAS_WEEKDAYNAME = 4.60367513;
	double PARAM_VISITAS_CATE_WEEKORWEEKEND = 6.87598754;
	double PARAM_VISITAS_CATE_WEEKDAYNAME = 10.36533161;
	double PARAM_VISITAS_COSENO_NET = 10.23751773;
	double INTERCEP = -7.6510278;
	
	UserSimilarity userVisitas;
	UserSimilarity userVisitasWeekDayName;
	UserSimilarity userVisitasCateWeekOrWeekend;
	UserSimilarity userVisitasCateWeekDayName;
	UserSimilarity userCosenoNet;

	public ScoringLR(
			UserSimilarity userVisitas,
			UserSimilarity userVisitasWeekDayName,
			UserSimilarity userVisitasCateWeekOrWeekend,
			UserSimilarity userVisitasCateWeekDayName,
			UserSimilarity userCosenoNet) {
		
		this.userVisitas = userVisitas;
		this.userVisitasWeekDayName = userVisitasWeekDayName;
		this.userVisitasCateWeekDayName = userVisitasCateWeekDayName;
		this.userVisitasCateWeekOrWeekend = userVisitasCateWeekOrWeekend;
		this.userCosenoNet = userCosenoNet;
		
	}

	public double userSimilarity(long userID1, long userID2) throws TasteException {
		return getScoring(userID1, userID2, -1);
	}

	public void setPreferenceInferrer(PreferenceInferrer inferrer) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}
	
	
	private double getPrababilidad(long userID1, long userID2) throws TasteException{
		double visitas = this.userVisitas.userSimilarity(userID1, userID2);
		double  userVisitasWeekDayName = this.userVisitasWeekDayName.userSimilarity(userID1, userID2);
		double userVisitasCateWeekOrWeekend = this.userVisitasCateWeekOrWeekend.userSimilarity(userID1, userID2);
		double userVisitasCateWeekDayName = this.userVisitasCateWeekDayName.userSimilarity(userID1, userID2);
		double userCosenoNet = this.userCosenoNet.userSimilarity(userID1, userID2);
		
		visitas = (Double.isNaN(visitas))?0:visitas;
		userVisitasWeekDayName = (Double.isNaN(userVisitasWeekDayName))?0:userVisitasWeekDayName;
		userVisitasCateWeekOrWeekend = (Double.isNaN(userVisitasCateWeekOrWeekend))?0:userVisitasCateWeekOrWeekend;
		userVisitasCateWeekDayName = (Double.isNaN(userVisitasCateWeekDayName))?0:userVisitasCateWeekDayName;
		userCosenoNet = (Double.isNaN(userCosenoNet))?0:userCosenoNet;
		
		double score =  visitas*this.PARAM_VISITAS_VISITAS +
				userVisitasWeekDayName*this.PARAM_VISITAS_WEEKDAYNAME +
				userVisitasCateWeekOrWeekend*this.PARAM_VISITAS_CATE_WEEKORWEEKEND +
				userVisitasCateWeekDayName*this.PARAM_VISITAS_CATE_WEEKDAYNAME +
				userCosenoNet*this.PARAM_VISITAS_COSENO_NET + 
				this.INTERCEP;
		
		double proba = 1/(1+(Math.exp(-score)));
		
		return proba;
		 
	}

	public double getScoring(long userID1, long userID2, long itemID) throws TasteException {
		double probaGoodNess = getPrababilidad(userID1, userID2);
		return probaGoodNess;
	}
	
	

}
