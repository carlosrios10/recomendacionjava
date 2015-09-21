package com.isistan.lbsn.recomendacionfc;

import com.isistan.lbsn.datamodels.ItemModel;

import junit.framework.TestCase;

public class ItemTest extends TestCase {
	ItemModel dm;
	public ItemTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dm = new ItemModel("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/venues.csv");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testItem101759_ok(){
		assertEquals("45.5405832", dm.getItem(101759).getLatitud());
		assertEquals("-73.5965186", dm.getItem(101759).getLongitud());
	}


}
