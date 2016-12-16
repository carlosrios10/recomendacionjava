package com.isistan.lbsn.scoring;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.ItemModel;

public class ScoringOverlapAbstraccionItemCategoryTest extends TestCase{
	ScoringOverlapAbstraccionItemCategory scoring;
	ScoringOverlap scoring2;
	FastIDSet itemIds;
	DataModel ratingModelTotal;
	ItemModel itemModel;
	public ScoringOverlapAbstraccionItemCategoryTest(String name) {
		super(name);
		FastByIDMap<PreferenceArray> preferences = 	new FastByIDMap<PreferenceArray>();
		PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(5);
		prefsForUser1.setUserID(0, 1L);
		prefsForUser1.setItemID(0, 101L);
		prefsForUser1.setValue(0, 1f);
		prefsForUser1.setItemID(1, 102L);
		prefsForUser1.setValue(1, 2f);
		prefsForUser1.setItemID(2, 103L);
		prefsForUser1.setValue(2, 5f);
		prefsForUser1.setItemID(3, 104L);
		prefsForUser1.setValue(3, 3f);
		prefsForUser1.setItemID(4, 105L);
		prefsForUser1.setValue(4, 4f);

		PreferenceArray prefsForUser2 = new GenericUserPreferenceArray(5);
		prefsForUser2.setUserID(0, 2L);
		prefsForUser2.setItemID(0, 101L);
		prefsForUser2.setValue(0, 4f);
		
		prefsForUser2.setItemID(1, 102L);
		prefsForUser2.setValue(1, 3f);
		prefsForUser2.setItemID(2, 103L);
		prefsForUser2.setValue(2, 5f);
		prefsForUser2.setItemID(3, 104L);
		prefsForUser2.setValue(3, 1f);
		prefsForUser2.setItemID(4, 105L);
		prefsForUser2.setValue(4, 3f);
		
		PreferenceArray prefsForUser3 = new GenericUserPreferenceArray(3);
		prefsForUser3.setUserID(0, 3L);
		prefsForUser3.setItemID(0, 103L);
		prefsForUser3.setValue(0, 5f);
		prefsForUser3.setItemID(1, 104L);
		prefsForUser3.setValue(1, 4f);
		prefsForUser3.setItemID(2, 105L);
		prefsForUser3.setValue(2, 3f);

		
		PreferenceArray prefsForUser4 = new GenericUserPreferenceArray(0);
		
		preferences.put(1L, prefsForUser1);
		preferences.put(2L, prefsForUser2);
		preferences.put(3L, prefsForUser3);
		preferences.put(4L, prefsForUser4);
		
		try {
			 ratingModelTotal = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			 itemModel = new ItemModel(MyProperties.getInstance().getProperty("databasevenues"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	protected void setUp() throws Exception {
		super.setUp();
		scoring = new ScoringOverlapAbstraccionItemCategory(ratingModelTotal, itemModel);
		scoring2 = new ScoringOverlap(ratingModelTotal);
		itemIds = new FastIDSet(2);
		itemIds.add(4);
		itemIds.add(9);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
//	public void testgetCategoriaOk() throws TasteException{
//		FastIDSet idCategoriasEsperadas = new FastIDSet(4);
//		idCategoriasEsperadas.add(173);
//		idCategoriasEsperadas.add(178);
//		idCategoriasEsperadas.add(160);
//		idCategoriasEsperadas.add(182);
//		idCategoriasEsperadas.add(213);
//		idCategoriasEsperadas.add(194);
//		FastIDSet resultado = scoring.getCategoria(itemIds, 2);
//		assertEquals(idCategoriasEsperadas.intersectionSize(resultado), idCategoriasEsperadas.size());
//	}
//	public void testgetScoringOk() throws TasteException{
//		System.out.println(scoring2.getScoring(1783275, 73982,-1));
//		System.out.println(scoring.getScoring(1783275, 73982,-1));
//		assertEquals(3.0, scoring.getScoring(1783275, 73982,-1));
//	}
	
//	public void testgetScoringOk2() throws TasteException{
//		System.out.println(scoring2.getScoring(3108923, 4187570,-1));
//		System.out.println(scoring.getScoring(3108923, 4187570,-1));
//		assertEquals(3.0, scoring.getScoring(3108923, 4187570,-1));
//	}
	
	public void testgetScoringOk3() throws TasteException{
		System.out.println(scoring2.getScoring(556607, 354845,-1));
		System.out.println(scoring.getScoring(556607, 354845,-1));
		assertEquals(3.0, scoring.getScoring(556607, 354845,-1));
	}
}
