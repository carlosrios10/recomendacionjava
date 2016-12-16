package com.isistan.lbsn.datamodels;

import com.isistan.lbsn.entidades.User;

import junit.framework.TestCase;

public class ItemModelTest extends TestCase {
ItemModel im;
public ItemModelTest(String name) {
	super(name);
}

protected void setUp() throws Exception {
	super.setUp();
	im = new ItemModel("C:/Users/Usuario/Desktop/carlos/Tesis/datasets/forsquare-2/AnalisisProject/venues_categoria.csv");
}

protected void tearDown() throws Exception {
	super.tearDown();
}

public void testGetItemOK(){
	im.getItem(1);
}
}
