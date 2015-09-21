package com.isistan.lbsn.recomendacionfc;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.isistan.lbsn.scoring.ScoringOverlapLikedAndHated;

public class SocoringOverlapLikedAndHatedTest extends TestCase{
	ScoringOverlapLikedAndHated scoring;
	
	public SocoringOverlapLikedAndHatedTest(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
		FastByIDMap<PreferenceArray> preferences = 	new FastByIDMap<PreferenceArray>();
		PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(4);
		prefsForUser1.setUserID(0, 1L);
		prefsForUser1.setItemID(0, 101L);
		prefsForUser1.setValue(0, 1f);
		prefsForUser1.setItemID(1, 102L);
		prefsForUser1.setValue(1, 5f);
		prefsForUser1.setItemID(2, 103L);
		prefsForUser1.setValue(2, 2f);
		prefsForUser1.setItemID(3, 104L);
		prefsForUser1.setValue(3, 3f);

		PreferenceArray prefsForUser2 = new GenericUserPreferenceArray(4);
		prefsForUser2.setUserID(0, 2L);
		prefsForUser2.setItemID(0, 101L);
		prefsForUser2.setValue(0, 4f);
		prefsForUser2.setItemID(1, 102L);
		prefsForUser2.setValue(1, 3f);
		prefsForUser2.setItemID(2, 103L);
		prefsForUser2.setValue(2, 5f);
		prefsForUser2.setItemID(3, 104L);
		prefsForUser2.setValue(3, 4f);
		
		PreferenceArray prefsForUser3 = new GenericUserPreferenceArray(4);
		prefsForUser3.setUserID(0, 3L);
		prefsForUser3.setItemID(0, 101L);
		prefsForUser3.setValue(0, 2f);
		prefsForUser3.setItemID(1, 102L);
		prefsForUser3.setValue(1, 4f);
		prefsForUser3.setItemID(2, 103L);
		prefsForUser3.setValue(2, 1f);
		prefsForUser3.setItemID(3, 104L);
		prefsForUser3.setValue(3, 4f);

		
		PreferenceArray prefsForUser4 = new GenericUserPreferenceArray(0);
		
		preferences.put(1L, prefsForUser1);
		preferences.put(2L, prefsForUser2);
		preferences.put(3L, prefsForUser3);
		preferences.put(4L, prefsForUser4);
		
		DataModel model = new GenericDataModel(preferences);
		scoring = new ScoringOverlapLikedAndHated(null, model);
		
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testgetScoringOk() throws TasteException{
		assertEquals(0.1666, scoring.getScoring(1,2,-1),0.0001);
	}
	
	public void testgetScoringOk2() throws TasteException{
		assertEquals(0.5, scoring.getScoring(1,3,-1));
	}
	
	
}
