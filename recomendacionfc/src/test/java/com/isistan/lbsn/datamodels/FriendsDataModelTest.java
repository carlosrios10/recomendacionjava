package com.isistan.lbsn.datamodels;

import java.io.File;
import java.util.Collection;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.collect.Iterators;
import com.isistan.lbsn.datamodels.FriendsDataModel;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FriendsDataModelTest extends TestCase {
	private FriendsDataModel dm;

	public FriendsDataModelTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dm = new FriendsDataModel(StringEscapeUtils.unescapeJava("C:/Users/Usuario/Desktop/carlos/Tesis/datasets/forsquare-2/AnalisisProject/grafoUserTips_df.csv"));
		//dm = new FriendsDataModel();
	}


	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testgetCantidadAmigosNivel1(){
		assertEquals(195,dm.getFriendsMyFriends(1661l, 1).size());
	}
	public void testgetCantidadAmigosNivel2(){
		assertEquals(18477,dm.getFriendsMyFriends(1661l, 2).size());
	}
	public void testgetCantidadAmigosNivel3(){
		assertEquals(39812,dm.getFriendsMyFriends(1661l, 3).size());
	}
	public void testgetCantidadAmigosNivel4(){
		assertEquals(42649,dm.getFriendsMyFriends(1661l, 4).size());
	}

	public void testgetCantidadAmigosNivel5(){
		assertEquals(42807,dm.getFriendsMyFriends(1661l, 5).size());
	}

	public void testUsuarioSinAmigos(){
		assertNull(dm.getFriendsMyFriends(2452751l, 5));
	}



}
