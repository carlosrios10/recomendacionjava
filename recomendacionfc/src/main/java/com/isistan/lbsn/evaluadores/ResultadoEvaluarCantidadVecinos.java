package com.isistan.lbsn.evaluadores;

public class ResultadoEvaluarCantidadVecinos {
	
	int cantidadUsuariosTest;
	int cantidadUsuarioTestConVecinos;
	int cantidadPreferenciasTest;
	double promedioVecinos;
	
	public ResultadoEvaluarCantidadVecinos(int cantidadUsuariosTest,
			double promedioVecinos,
			int cantidadUsuarioTestConVecinos,
			int cantidadPreferenciasTest){
		this.cantidadUsuariosTest = cantidadUsuariosTest;
		this.promedioVecinos = promedioVecinos;
		this.cantidadUsuarioTestConVecinos = cantidadUsuarioTestConVecinos;
		this.cantidadPreferenciasTest =  cantidadPreferenciasTest;
		
		
	}
	public ResultadoEvaluarCantidadVecinos() {
		this.cantidadUsuariosTest = 0;
		this.promedioVecinos = 0;
		this.cantidadUsuarioTestConVecinos = 0;
		this.cantidadPreferenciasTest =  0;
	}
	public int getCantidadUsuariosTest() {
		return cantidadUsuariosTest;
	}
	public void setCantidadUsuariosTest(int cantidadUsuariosTest) {
		this.cantidadUsuariosTest = cantidadUsuariosTest;
	}
	public double getPromedioVecinos() {
		return promedioVecinos;
	}
	public void setPromedioVecinos(double promedioVecinos) {
		this.promedioVecinos = promedioVecinos;
	}
	public String toString() {
		return ""+"---"+getCantidadUsuariosTest()+"---"+getCantidadUsuarioTestConVecinos()+"---"+getPromedioVecinos()+"---"+getCantidadPreferenciasTest();
	}
	public int getCantidadUsuarioTestConVecinos() {
		return cantidadUsuarioTestConVecinos;
	}
	public void setCantidadUsuarioTestConVecinos(int cantidadUsuarioTestConVecinos) {
		this.cantidadUsuarioTestConVecinos = cantidadUsuarioTestConVecinos;
	}
	public int getCantidadPreferenciasTest() {
		return cantidadPreferenciasTest;
	}
	public void setCantidadPreferenciasTest(int cantidadPreferenciasTest) {
		this.cantidadPreferenciasTest = cantidadPreferenciasTest;
	}
	

}
