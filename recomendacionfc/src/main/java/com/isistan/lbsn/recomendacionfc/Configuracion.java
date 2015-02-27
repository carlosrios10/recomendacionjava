package com.isistan.lbsn.recomendacionfc;

import com.isistan.lbsn.recomendacionfc.SimilarityAlgorithm.SimAlg;



public class Configuracion {
	private int neighSize; 
	SimilarityAlgorithm.SimAlg simAlg; 
	private double threshold;
	TypeNeighborhood.TypeNeigh typeNeigh;
	
	public Configuracion(int neighSize, SimAlg simAlg, double threshold, TypeNeighborhood.TypeNeigh  typeNeigh) {
		super();
		this.neighSize = neighSize;
		this.simAlg = simAlg;
		this.threshold = threshold;
		this.typeNeigh = typeNeigh;
	}
	public TypeNeighborhood.TypeNeigh getTypeNeigh() {
		return typeNeigh;
	}
	public void setTypeNeigh(TypeNeighborhood.TypeNeigh typeNeigh) {
		this.typeNeigh = typeNeigh;
	}
	public int getNeighSize() {
		return neighSize;
	}
	public void setNeighSize(int neighSize) {
		this.neighSize = neighSize;
	}
	public SimilarityAlgorithm.SimAlg getSimAlg() {
		return simAlg;
	}
	public void setSimAlg(SimilarityAlgorithm.SimAlg simAlg) {
		this.simAlg = simAlg;
	}
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}




}
