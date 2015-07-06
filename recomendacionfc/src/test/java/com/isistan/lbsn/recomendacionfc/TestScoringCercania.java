package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;

import junit.framework.TestCase;

import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioItem;

public class TestScoringCercania extends TestCase {
	ScoringCercaniaUsuarioItem scoring;
	public TestScoringCercania(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		scoring = new ScoringCercaniaUsuarioItem(new UserModel(), new ItemModel());
	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testgetScoringOk() throws TasteException{
		assertEquals((1/1428.4398425581758), scoring.getScoring(19, -1, 101759),0.00001);
	}
	
	

}
