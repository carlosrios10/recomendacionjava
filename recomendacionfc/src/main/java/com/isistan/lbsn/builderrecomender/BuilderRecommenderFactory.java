package com.isistan.lbsn.builderrecomender;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;

public class BuilderRecommenderFactory {
	public enum BuilderRecommenderType {
		SDV_PLUSPLUS,
		SDV,
		ALSWR};
		public static RecommenderBuilder build(BuilderRecommenderType builderRecommenderType
				) {
			RecommenderBuilder recommenderBuilder=null;
			switch (builderRecommenderType) {
			case SDV:
				//recommenderBuilder = new ScoringOverlap(grafoModel, dataModel);
				return recommenderBuilder;
			case SDV_PLUSPLUS:
				//recommenderBuilder = new ScoringOverlap(grafoModel, dataModel);
				return recommenderBuilder;
			default:
				return null; 	
			}
		}
}
