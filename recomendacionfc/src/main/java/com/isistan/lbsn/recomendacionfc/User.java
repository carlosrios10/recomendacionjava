package com.isistan.lbsn.recomendacionfc;

public class User {
	Long id;
	String latitud;
	String longitud;
	double distanciaTotalRecorrida;
	double distanciaPromedioRecorrida;
	int cantidadDeItem;
	double distanciaTotalEntreItems;
	
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


}
