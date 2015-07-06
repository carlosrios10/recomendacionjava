package com.isistan.lbsn.recomendacionfc;

public class Item {
	
	String latitud;
	String longitud;
	
	public Item() {
		super();
	}

	public Item(String latitud, String longitud) {
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

	

}
