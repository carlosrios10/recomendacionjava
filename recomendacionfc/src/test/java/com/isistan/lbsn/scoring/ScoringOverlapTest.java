package com.isistan.lbsn.scoring;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.isistan.lbsn.scoring.ScoringOverlap;

public class ScoringOverlapTest extends TestCase {
	ScoringOverlap scoring;
	public ScoringOverlapTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		FastByIDMap<PreferenceArray> preferences = 	new FastByIDMap<PreferenceArray>();
		PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(5);
		prefsForUser1.setUserID(0, 1L);
		prefsForUser1.setItemID(0, 101L);
		prefsForUser1.setValue(0, 3.0f);
		prefsForUser1.setItemID(1, 102L);
		prefsForUser1.setValue(1, 4.5f);
		prefsForUser1.setItemID(2, 103L);
		prefsForUser1.setValue(2, 4.5f);
		prefsForUser1.setItemID(3, 104L);
		prefsForUser1.setValue(3, 4.5f);
		prefsForUser1.setItemID(4, 105L);
		prefsForUser1.setValue(4, 4.5f);

		PreferenceArray prefsForUser2 = new GenericUserPreferenceArray(3);
		prefsForUser2.setUserID(0, 2L);
		prefsForUser2.setItemID(0, 101L);
		prefsForUser2.setValue(0, 3.0f);
//		prefsForUser2.setItemID(1, 102L);
//		prefsForUser2.setValue(1, 4.5f);
		prefsForUser2.setItemID(1, 103L);
		prefsForUser2.setValue(1, 4.5f);
		prefsForUser2.setItemID(2, 104L);
		prefsForUser2.setValue(2, 4.5f);
//		prefsForUser2.setItemID(4, 105L);
//		prefsForUser2.setValue(4, 4.5f);
		
		PreferenceArray prefsForUser3 = new GenericUserPreferenceArray(1);
		prefsForUser3.setUserID(0, 3L);
		prefsForUser3.setItemID(0, 102L);
		prefsForUser3.setValue(0, 3.0f);
		
		PreferenceArray prefsForUser4 = new GenericUserPreferenceArray(0);
		
		preferences.put(1L, prefsForUser1);
		preferences.put(2L, prefsForUser2);
		preferences.put(3L, prefsForUser3);
		preferences.put(4L, prefsForUser4);
		
		DataModel model = new GenericDataModel(preferences);
		scoring = new ScoringOverlap(null, model);
	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testgetScoringOk() throws TasteException{
		assertEquals(3.0, scoring.getScoring(1,2,-1));
	}
	
	public void testgetScoring_User1USer2_sin_interseccion_NaN() throws TasteException{
		assertEquals(Double.NaN, scoring.getScoring(2,3,-1));
	}
	
	public void testgetScoring_User1USer2_cualquier_sin_preferencias_NaN() throws TasteException{
		assertEquals(0.0, scoring.getScoring(1,4,-1));
	}
	

}
