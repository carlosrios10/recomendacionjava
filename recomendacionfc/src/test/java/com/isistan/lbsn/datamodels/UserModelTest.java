package com.isistan.lbsn.datamodels;

import com.isistan.lbsn.datamodels.UserModel;
import com.isistan.lbsn.entidades.User;

import junit.framework.TestCase;

public class UserModelTest extends TestCase {
	UserModel dm;
	public UserModelTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dm = new UserModel("C:/Users/Usuario/Desktop/carlos/Tesis/datasets/forsquare-2/AnalisisProject/users_tips_grafo_zona.csv");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
//	public void testUsuario19_ok(){
//		assertEquals("34.0522342", dm.getUser(22).getLatitud());
//		assertEquals("-118.2436849", dm.getUser(22).getLongitud());
//	}
	
//	public void testUsuario32_grupo_29_ok(){
//		assertEquals(28, dm.getUser(32).getGrupo());
//	}
//	public void testGetUsersByGrupo_grupo29(){
//		User user = new User();
//		user.setGrupo(28);
//		user.setId(405311L);
//		assertEquals(16750, dm.getUserGrupo(user).size());
//	}
	
//	public void testUsuario_ok(){
//	assertEquals("40.72505", dm.getUser(32).getLatitud());
//	assertEquals("-73.98757", dm.getUser(32).getLongitud());
//	}
	
	public void testUsuario_getZonaOk(){
		User user = dm.getUser(283717);
		assertEquals(6535, dm.getUserZona(user).size());
	}
}
