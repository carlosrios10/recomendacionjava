package com.isistan.lbsn.entidades;

import java.util.Collection;

public class Item {
	
	String latitud;
	String longitud;
	
	Collection<Long> categoriaNivel1;
	Collection<Long> categoriaNivel2;
	Collection<Long> categoriaNivel3;
	
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

	public Collection<Long> getCategoriaNivel1() {
		return categoriaNivel1;
	}

	public void setCategoriaNivel1(Collection<Long> categoriaNivel1) {
		this.categoriaNivel1 = categoriaNivel1;
	}

	public Collection<Long> getCategoriaNivel2() {
		return categoriaNivel2;
	}

	public void setCategoriaNivel2(Collection<Long> categoriaNivel2) {
		this.categoriaNivel2 = categoriaNivel2;
	}

	public Collection<Long> getCategoriaNivel3() {
		return categoriaNivel3;
	}

	public void setCategoriaNivel3(Collection<Long> categoriaNivel3) {
		this.categoriaNivel3 = categoriaNivel3;
	}



	

}
