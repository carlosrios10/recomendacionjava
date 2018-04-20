package com.isistan.lbsn.scoring;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class ScoringLRModel2 implements Scoring{
	
//	double PARAM_VISITAS_VISITAS = 64.6598867;
//	double PARAM_VISITAS_WEEKDAYNAME = -13.7917999;
//	
//	double PARAM_VISITAS_CATE = 0.0460296161;
//	double PARAM_VISITAS_CATE_WEEKORWEEKEND = 12.8594083;
//	double PARAM_VISITAS_CATE_WEEKDAYNAME = 2.56547018;
//	
//	double PARAM_VISITAS_COSENO_NET =  10.6356523;
//	//double PARAM_VISITAS_PAHT_NET =  0.0;
//	double PARAM_VISITAS_FRIENDS_NET =  2.51100149;
//	
//	double INTERCEP = -7.91577398;
	
//	Para foursquare	
	double PARAM_VISITAS_VISITAS = 295.17235223;
	double PARAM_VISITAS_WEEKDAYNAME = 15.72450897;
	
	double PARAM_VISITAS_CATE = 10.94635606;
	double PARAM_VISITAS_CATE_WEEKORWEEKEND = -0.88311541;
	double PARAM_VISITAS_CATE_WEEKDAYNAME = 14.37108057;
	
	double PARAM_VISITAS_COSENO_NET =  -2.05895035;
	//double PARAM_VISITAS_PAHT_NET =  0.0;
	double PARAM_VISITAS_FRIENDS_NET =  5.53973007;
	
	double INTERCEP = -7.62771259;
	
	UserSimilarity userVisitas;
	UserSimilarity userVisitasWeekDayName;
	
	UserSimilarity userVisitasCate;
	UserSimilarity userVisitasCateWeekOrWeekend;
	UserSimilarity userVisitasCateWeekDayName;
	
	UserSimilarity userCosenoNet;
	UserSimilarity userPahtNet;
	UserSimilarity usersAreFriendsNet;

	public ScoringLRModel2(
			UserSimilarity userVisitas,
			UserSimilarity userVisitasWeekDayName,
			UserSimilarity userVisitasCate,
			UserSimilarity userVisitasCateWeekOrWeekend,
			UserSimilarity userVisitasCateWeekDayName,
			UserSimilarity userCosenoNet,
			UserSimilarity userPahtNet,
			UserSimilarity usersAreFriendsNet) {
		
		this.userVisitas = userVisitas;
		this.userVisitasWeekDayName = userVisitasWeekDayName;
		this.userVisitasCate = userVisitasCate;
		this.userVisitasCateWeekDayName = userVisitasCateWeekDayName;
		this.userVisitasCateWeekOrWeekend = userVisitasCateWeekOrWeekend;
		this.userCosenoNet = userCosenoNet;
		this.userPahtNet = userPahtNet;
		this.usersAreFriendsNet = usersAreFriendsNet;
		
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
		double usersAreFriendsNet = this.usersAreFriendsNet.userSimilarity(userID1, userID2);
		
		visitas = (Double.isNaN(visitas))?0:visitas;
		userVisitasWeekDayName = (Double.isNaN(userVisitasWeekDayName))?0:userVisitasWeekDayName;
		userVisitasCate = (Double.isNaN(userVisitasCate))?0:userVisitasCate;
		userVisitasCateWeekOrWeekend = (Double.isNaN(userVisitasCateWeekOrWeekend))?0:userVisitasCateWeekOrWeekend;
		userVisitasCateWeekDayName = (Double.isNaN(userVisitasCateWeekDayName))?0:userVisitasCateWeekDayName;
		userCosenoNet = (Double.isNaN(userCosenoNet))?0:userCosenoNet;
		usersAreFriendsNet = (Double.isNaN(usersAreFriendsNet))?0:usersAreFriendsNet;
		
		double score =  visitas*this.PARAM_VISITAS_VISITAS +
				userVisitasWeekDayName*this.PARAM_VISITAS_WEEKDAYNAME +
				userVisitasCate*this.PARAM_VISITAS_CATE +
				userVisitasCateWeekOrWeekend*this.PARAM_VISITAS_CATE_WEEKORWEEKEND +
				userVisitasCateWeekDayName*this.PARAM_VISITAS_CATE_WEEKDAYNAME +
				userCosenoNet*this.PARAM_VISITAS_COSENO_NET +
				usersAreFriendsNet*this.PARAM_VISITAS_FRIENDS_NET +
				this.INTERCEP;
		
		double proba = 1/(1+(Math.exp(-score)));
		
		return proba;
		 
	}

	public double getScoring(long userID1, long userID2, long itemID) throws TasteException {
		double probaGoodNess = getPrababilidad(userID1, userID2);
		return probaGoodNess;
	}
	
	

}
