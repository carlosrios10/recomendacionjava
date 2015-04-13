package com.isistan.lbsn.recomendacionfc;

public class Resultado {
	Configuracion configuracion;
	double scoreMae;
	double scoreRms;
	double scorePrecision;
	double scoreRecall;
	

	public Resultado(Configuracion configuracion, double scoreMae,
			double scoreRms, double scorePrecision, double scoreRecall) {
		super();
		this.configuracion = configuracion;
		this.scoreMae = scoreMae;
		this.scoreRms = scoreRms;
		this.scorePrecision = scorePrecision;
		this.scoreRecall = scoreRecall;
	}
	public Configuracion getConfiguracion() {
		return configuracion;
	}
	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}
	public double getScoreMae() {
		return scoreMae;
	}
	public void setScoreMae(double scoreMae) {
		this.scoreMae = scoreMae;
	}
	public double getScoreRms() {
		return scoreRms;
	}
	public void setScoreRms(double scoreRms) {
		this.scoreRms = scoreRms;
	}
	public double getScorePrecision() {
		return scorePrecision;
	}
	public void setScorePrecision(double scorePrecision) {
		this.scorePrecision = scorePrecision;
	}
	public double getScoreRecall() {
		return scoreRecall;
	}
	public void setScoreRecall(double scoreRecall) {
		this.scoreRecall = scoreRecall;
	}
	public String toString() {
		String resultado= ""+getConfiguracion().toString()+"----"+getScoreMae()+"--"+getScoreRms()+""+"--"+getScorePrecision()+"--"+getScoreRecall();
		return resultado;
	}

}
