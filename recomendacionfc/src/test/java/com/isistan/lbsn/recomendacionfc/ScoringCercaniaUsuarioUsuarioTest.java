package com.isistan.lbsn.recomendacionfc;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioUsuario;

public class ScoringCercaniaUsuarioUsuarioTest extends TestCase {
	ScoringCercaniaUsuarioUsuario scoring;
	public ScoringCercaniaUsuarioUsuarioTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		scoring = new ScoringCercaniaUsuarioUsuario(new UserModel(), new ItemModel());
	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testgetScoringOk() throws TasteException{
		assertEquals(1, scoring.getScoring(1095708, 1095712, -1),0.00001);
	}
	

}
