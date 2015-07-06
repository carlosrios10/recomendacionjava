package com.isistan.lbsn.recomendacionfc;

import junit.framework.TestCase;

public class UserModelTest extends TestCase {
	UserModel dm;
	public UserModelTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dm = new UserModel("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/NY/users_check_NY2_UE.csv");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testUsuario19_ok(){
		assertEquals("34.0522342", dm.getUser(22).getLatitud());
		assertEquals("-118.2436849", dm.getUser(22).getLongitud());
	}

}
