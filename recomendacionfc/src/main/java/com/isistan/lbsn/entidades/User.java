package com.isistan.lbsn.entidades;

public class User {
	Long id;
	String latitud;
	String longitud;
	double distanciaTotalRecorrida;
	double distanciaPromedioRecorrida;
	int cantidadDeItem;
	double distanciaTotalEntreItems;
	int grupo;
	int grupoNivel2;
	double radioZona = 1;
	String latitudCentroZona;
	String longitudCentroZona;

	
	public User(){
		super();
	}
	public User(String latitud, String longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getDistanciaTotalRecorrida() {
		return distanciaTotalRecorrida;
	}
	public void setDistanciaTotalRecorrida(double distanciaTotalRecorrida) {
		this.distanciaTotalRecorrida = distanciaTotalRecorrida;
	}
	public double getDistanciaPromedioRecorrida() {
		return distanciaPromedioRecorrida;
	}
	public void setDistanciaPromedioRecorrida(double distanciaPromedioRecorrida) {
		this.distanciaPromedioRecorrida = distanciaPromedioRecorrida;
	}
	public int getCantidadDeItem() {
		return cantidadDeItem;
	}
	public void setCantidadDeItem(int cantidadDeItem) {
		this.cantidadDeItem = cantidadDeItem;
	}
	public double getDistanciaTotalEntreItems() {
		return distanciaTotalEntreItems;
	}
	public void setDistanciaTotalEntreItems(double distanciaTotalEntreItems) {
		this.distanciaTotalEntreItems = distanciaTotalEntreItems;
	}
	@Override
	public String toString() {
		return "id:"+getId()+
				" total:"+getDistanciaTotalRecorrida()+
				" promedio:"+getDistanciaPromedioRecorrida()+
				" total Distancia items:"+getDistanciaTotalEntreItems()+
				" total items:"+getCantidadDeItem();
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public double getRadioZona() {
		return radioZona;
	}
	public void setRadioZona(double radioZona) {
		this.radioZona = radioZona;
	}
	public int getGrupoNivel2() {
		return grupoNivel2;
	}
	public void setGrupoNivel2(int grupoNivel2) {
		this.grupoNivel2 = grupoNivel2;
	}
	public String getLatitudCentroZona() {
		return latitudCentroZona;
	}
	public void setLatitudCentroZona(String latitudCentroZona) {
		this.latitudCentroZona = latitudCentroZona;
	}
	public String getLongitudCentroZona() {
		return longitudCentroZona;
	}
	public void setLongitudCentroZona(String longitudCentroZona) {
		this.longitudCentroZona = longitudCentroZona;
	}


}
