package com.isistan.lbsn.recomendacionfc;

public class ResultadoVecino {
	long idUsuario ;
	int cantiddadVecinos;
	Configuracion config;
	public ResultadoVecino(long idUsuario, int cantiddadVecinos,Configuracion config) {
		super();
		this.idUsuario = idUsuario;
		this.cantiddadVecinos = cantiddadVecinos;
		this.config = config;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getCantiddadVecinos() {
		return cantiddadVecinos;
	}
	public void setCantiddadVecinos(int cantiddadVecinos) {
		this.cantiddadVecinos = cantiddadVecinos;
	}
	public Configuracion getConfig() {
		return config;
	}
	public void setConfig(Configuracion config) {
		this.config = config;
	}


}
