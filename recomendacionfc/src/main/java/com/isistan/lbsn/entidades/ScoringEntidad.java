package com.isistan.lbsn.entidades;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;


public class ScoringEntidad {
	
	long idUser1;
	long idUser2;
	long idItem;
	double score;
	public long getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(long idUser1) {
		this.idUser1 = idUser1;
	}
	public long getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(long idUser2) {
		this.idUser2 = idUser2;
	}
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}
	 @Override
	public int hashCode(){
	        return Objects.hashCode(this.getIdUser1(), this.getIdUser2());
	    }

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ScoringEntidad))return false;
	    ScoringEntidad otroUser = (ScoringEntidad)other;
	   if (this.idUser1 == otroUser.getIdUser1() 
			   && this.idUser2 == otroUser.getIdUser2()){
		   return true;
	   }else {
		   return false;
	   }
	   }
	

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

}
