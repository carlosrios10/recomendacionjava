package com.isistan.lbsn.recomendacionfc;

import com.isistan.lbsn.similitudestructural.GrafoDataModel;

import junit.framework.TestCase;

public class GrafoDataModelTest extends TestCase {

	public GrafoDataModelTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGrafoDataModelString() {
		GrafoDataModel dm =  new GrafoDataModel("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/redSocialReducida.graphml");
		//dm.getGrafo().degree(vertex);
		
	}

}
