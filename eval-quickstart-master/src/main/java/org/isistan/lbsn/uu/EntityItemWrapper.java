package org.isistan.lbsn.uu;

import org.apache.commons.math3.ml.clustering.Clusterable;
import org.lenskit.data.entities.Entity;

public class EntityItemWrapper implements Clusterable{
	 Entity entityItem;
	 private double[] points;
	 public EntityItemWrapper(Entity entityItem) {
		this.entityItem = entityItem;
	 	double lat = Double.parseDouble(entityItem.get("latitude").toString());
		double lon = Double.parseDouble(entityItem.get("longitude").toString());
		double[] points = {lat,lon};
	    this.points = points;
	 }
	 
	 public EntityItemWrapper(double[] points) {
	    this.points = points;
	 }
	 
	public Entity getEItem() {
		return this.entityItem;
	}
	 public double[] getPoint() {
	     return points;
	 }

}
