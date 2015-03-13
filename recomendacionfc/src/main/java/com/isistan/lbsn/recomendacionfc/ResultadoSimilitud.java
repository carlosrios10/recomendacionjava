package com.isistan.lbsn.recomendacionfc;

public class ResultadoSimilitud {

	Long idUser1;
	Long idUser2;
	double simValue;
	public Long getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(Long idUser1) {
		this.idUser1 = idUser1;
	}
	public Long getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(Long idUser2) {
		this.idUser2 = idUser2;
	}
	public double getSimValue() {
		return simValue;
	}
	public void setSimValue(double simValue) {
		this.simValue = simValue;
	}
	public ResultadoSimilitud(Long idUser1, Long idUser2, double simValue) {
		super();
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
		this.simValue = simValue;
	}
	
}
