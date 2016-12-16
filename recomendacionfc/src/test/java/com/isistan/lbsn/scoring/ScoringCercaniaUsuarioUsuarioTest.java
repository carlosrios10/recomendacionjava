package com.isistan.lbsn.scoring;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.ItemModel;
import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.scoring.ScoringCercaniaUsuarioUsuario;

public class ScoringCercaniaUsuarioUsuarioTest extends TestCase {
	ScoringCercaniaUsuarioUsuario scoring;
	ScoringCercaniaUsuarioUsuario scoringFile;
	
	public ScoringCercaniaUsuarioUsuarioTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		scoring = new ScoringCercaniaUsuarioUsuario(new UserModel(), new ItemModel());
		UserModel userModel =  new UserModel(MyProperties.getInstance().getProperty("databaseusers"));
		scoringFile = new ScoringCercaniaUsuarioUsuario(userModel,new ItemModel());
	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
//	public void testgetScoringOk() throws TasteException{
//		assertEquals(1, scoring.getScoring(1095708, 1095712, -1),0.00001);
//	}
//	
//	public void testgetScoring2Ok() throws TasteException{
//		assertEquals(1, scoring.getScoring(1095708, 19, -1),0.00001);
//	}
	
	public void testgetScoring3Ok() throws TasteException{
		assertEquals(1, scoringFile.getScoring(445771, 33, -1),0.00001);
	}
	
	public void testgetScoring4Ok() throws TasteException{
		assertEquals(1, scoringFile.getScoring(1783275, 73982, -1),0.00001);
	}
	
	public void testgetScoring5Ok() throws TasteException{
		assertEquals(1, scoringFile.getScoring(1783275, 1783275, -1),0.00001);
	}
	
	
	//id1: 445771 id2: 33
	//id1: 1783275 id2: 73982
}
