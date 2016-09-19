package com.isistan.lbsn.recomendacionfc;

import com.isistan.lbsn.agregation.AgregationFactory;
import com.isistan.lbsn.agregation.AgregationFactory.AgregationType;
import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithmFactory.SimAlg;
import com.isistan.lbsn.scoring.ScoringFactory;
import com.isistan.lbsn.scoring.ScoringFactory.ScoringType;
import com.isistan.lbsn.vencindario.TypeNeighborhoodFactory;



public class Configuracion {
	private int neighSize; 
	SimilarityAlgorithmFactory.SimAlg simAlg; 
	private double threshold;
	TypeNeighborhoodFactory.TypeNeigh typeNeigh;
	double alfa;
	double beta;
	ScoringFactory.ScoringType scoringType;
	AgregationFactory.AgregationType agregationType;
	
	public Configuracion(int neighSize, SimAlg simAlg, double threshold, TypeNeighborhoodFactory.TypeNeigh  typeNeigh,double alfa,
			double beta) {
		super();
		this.neighSize = neighSize;
		this.simAlg = simAlg;
		this.threshold = threshold;
		this.typeNeigh = typeNeigh;
		this.alfa = alfa;
		this.beta = beta;
	}
	
	public Configuracion(int neighSize, SimAlg simAlg, double threshold, TypeNeighborhoodFactory.TypeNeigh  typeNeigh,double alfa,
			double beta, ScoringFactory.ScoringType scoringType,AgregationFactory.AgregationType agregationType) {
		super();
		this.neighSize = neighSize;
		this.simAlg = simAlg;
		this.threshold = threshold;
		this.typeNeigh = typeNeigh;
		this.alfa = alfa;
		this.beta = beta;
		this.scoringType = scoringType;
		this.agregationType = agregationType;
	}
	public TypeNeighborhoodFactory.TypeNeigh getTypeNeigh() {
		return typeNeigh;
	}
	public void setTypeNeigh(TypeNeighborhoodFactory.TypeNeigh typeNeigh) {
		this.typeNeigh = typeNeigh;
	}
	public int getNeighSize() {
		return neighSize;
	}
	public void setNeighSize(int neighSize) {
		this.neighSize = neighSize;
	}
	public SimilarityAlgorithmFactory.SimAlg getSimAlg() {
		return simAlg;
	}
	public void setSimAlg(SimilarityAlgorithmFactory.SimAlg simAlg) {
		this.simAlg = simAlg;
	}
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public double getAlfa() {
		return alfa;
	}
	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}
	public double getBeta() {
		return beta;
	}
	public void setBeta(double beta) {
		this.beta = beta;
	}
	@Override
	public String toString() {
		String resultado= ""+getSimAlg()+"-"+getTypeNeigh()+"-"+getNeighSize()+"-"+getAlfa()+"-"+getBeta()+"-"+getThreshold()+"-"+getAgregationType()+"-"+getScoringType();
		return resultado; 
	}
	public ScoringFactory.ScoringType getScoringType() {
		return scoringType;
	}
	public void setScoringType(ScoringFactory.ScoringType scoringType) {
		this.scoringType = scoringType;
	}
	public AgregationFactory.AgregationType getAgregationType() {
		return agregationType;
	}
	public void setAgregationType(AgregationFactory.AgregationType agregationType) {
		this.agregationType = agregationType;
	}





}
