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
	
	double PARAM_VISITAS_VISITAS = 66.86987485;
	double PARAM_VISITAS_WEEKDAYNAME = -12.46436044;
	
	double PARAM_VISITAS_CATE = -0.40458312;
	double PARAM_VISITAS_CATE_WEEKORWEEKEND = 11.96624005;
	double PARAM_VISITAS_CATE_WEEKDAYNAME = 2.21811122;
	
	double PARAM_VISITAS_COSENO_NET =  -3.13208094;
	double PARAM_VISITAS_PAHT_NET =  5.45577425;
	
	double INTERCEP = -8.26998001;
	

	
	UserSimilarity userVisitas;
	UserSimilarity userVisitasWeekDayName;
	
	UserSimilarity userVisitasCate;
	UserSimilarity userVisitasCateWeekOrWeekend;
	UserSimilarity userVisitasCateWeekDayName;
	
	UserSimilarity userCosenoNet;
	UserSimilarity userPahtNet;

	public ScoringLR(
			UserSimilarity userVisitas,
			UserSimilarity userVisitasWeekDayName,
			UserSimilarity userVisitasCate,
			UserSimilarity userVisitasCateWeekOrWeekend,
			UserSimilarity userVisitasCateWeekDayName,
			UserSimilarity userCosenoNet,
			UserSimilarity userPahtNet) {
		
		this.userVisitas = userVisitas;
		this.userVisitasWeekDayName = userVisitasWeekDayName;
		this.userVisitasCate = userVisitasCate;
		this.userVisitasCateWeekDayName = userVisitasCateWeekDayName;
		this.userVisitasCateWeekOrWeekend = userVisitasCateWeekOrWeekend;
		this.userCosenoNet = userCosenoNet;
		this.userPahtNet = userPahtNet;
		
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
		double  userVisitasCate = this.userVisitasCate.userSimilarity(userID1, userID2);
		double userVisitasCateWeekOrWeekend = this.userVisitasCateWeekOrWeekend.userSimilarity(userID1, userID2);
		double userVisitasCateWeekDayName = this.userVisitasCateWeekDayName.userSimilarity(userID1, userID2);
		double userCosenoNet = this.userCosenoNet.userSimilarity(userID1, userID2);
		double userPahtNet = this.userPahtNet.userSimilarity(userID1, userID2);
		
		visitas = (Double.isNaN(visitas))?0:visitas;
		userVisitasWeekDayName = (Double.isNaN(userVisitasWeekDayName))?0:userVisitasWeekDayName;
		userVisitasCate = (Double.isNaN(userVisitasCate))?0:userVisitasCate;
		userVisitasCateWeekOrWeekend = (Double.isNaN(userVisitasCateWeekOrWeekend))?0:userVisitasCateWeekOrWeekend;
		userVisitasCateWeekDayName = (Double.isNaN(userVisitasCateWeekDayName))?0:userVisitasCateWeekDayName;
		userCosenoNet = (Double.isNaN(userCosenoNet))?0:userCosenoNet;
		userPahtNet = (Double.isNaN(userPahtNet))?0:userPahtNet;
		
		double score =  visitas*this.PARAM_VISITAS_VISITAS +
				userVisitasWeekDayName*this.PARAM_VISITAS_WEEKDAYNAME +
				userVisitasCate*this.PARAM_VISITAS_CATE +
				userVisitasCateWeekOrWeekend*this.PARAM_VISITAS_CATE_WEEKORWEEKEND +
				userVisitasCateWeekDayName*this.PARAM_VISITAS_CATE_WEEKDAYNAME +
				userCosenoNet*this.PARAM_VISITAS_COSENO_NET +
				userPahtNet*this.PARAM_VISITAS_PAHT_NET +
				this.INTERCEP;
		
		double proba = 1/(1+(Math.exp(-score)));
		
		return proba;
		 
	}

	public double getScoring(long userID1, long userID2, long itemID) throws TasteException {
		double probaGoodNess = getPrababilidad(userID1, userID2);
		return probaGoodNess;
	}
	
	

}
