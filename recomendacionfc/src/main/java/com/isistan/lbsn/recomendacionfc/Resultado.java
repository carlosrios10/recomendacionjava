package com.isistan.lbsn.recomendacionfc;

import com.isistan.lbsn.evaluadores.ResultadoEvaluarCantidadVecinos;

public class Resultado {
	Configuracion configuracion;
	double scoreMae;
	double scoreRms;
	double scorePrecision;
	double scoreRecall;
	double scoreF1Measure;
	double nDgg;
	double reach;
	double fallout;
	ResultadoEvaluarCantidadVecinos resultadoCantV;

	public Resultado(Configuracion configuracion, double scoreMae,
			double scoreRms, double scorePrecision, double scoreRecall, double scoreF1Measur,
			double nDgg,double reach,double fallout,ResultadoEvaluarCantidadVecinos resultadoCantV) {
		super();
		this.configuracion = configuracion;
		this.scoreMae = scoreMae;
		this.scoreRms = scoreRms;
		this.scorePrecision = scorePrecision;
		this.scoreRecall = scoreRecall;
		this.scoreF1Measure = scoreF1Measur;
		this.nDgg = nDgg;
		this.reach = reach;
		this.fallout = fallout;
		this.resultadoCantV = resultadoCantV;
		
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
		String resultado= ""+getConfiguracion().toString()+"----"+getResultadoCantV().toString()+"---"+
							getScoreMae()+"--"+getScoreRms()+""+"--"+getScorePrecision()+"--"+getScoreRecall()+"--"+getScoreF1Measure();
		return resultado;
	}
	public double getScoreF1Measure() {
		return scoreF1Measure;
	}
	public void setScoreF1Measure(double scoreF1Measure) {
		this.scoreF1Measure = scoreF1Measure;
	}
	public double getnDgg() {
		return nDgg;
	}
	public void setnDgg(double nDgg) {
		this.nDgg = nDgg;
	}
	public double getReach() {
		return reach;
	}
	public void setReach(double reach) {
		this.reach = reach;
	}
	public double getFallout() {
		return fallout;
	}
	public void setFallout(double fallout) {
		this.fallout = fallout;
	}
	public ResultadoEvaluarCantidadVecinos getResultadoCantV() {
		return resultadoCantV;
	}
	public void setResultadoCantV(ResultadoEvaluarCantidadVecinos resultadoCantV) {
		this.resultadoCantV = resultadoCantV;
	}

}
