package com.isistan.lbsn.recomendacionfc;

public class Resultado {
	Configuracion configuracion;
	double scoreMae;
	double scoreRms;
	
	public Resultado(Configuracion configuracion, double scoreMae,
			double scoreRms) {
		super();
		this.configuracion = configuracion;
		this.scoreMae = scoreMae;
		this.scoreRms = scoreRms;
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

}
