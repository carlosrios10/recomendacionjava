package com.isistan.lbsn.recomendacionfc;

import java.io.File;
import java.util.Collection;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.collect.Iterators;

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
		dm = new FriendsDataModel(StringEscapeUtils.unescapeJava("C:/Users/Usuarioç/Desktop/carlos/Tesis/datasets/foursquare/datasets_csv/socialgraph.csv"));
		//dm = new FriendsDataModel();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
//	public void testgetCantidadAmigos(){
//		assertEquals(29,dm.getFriends(1l).size());
//	}
	public void testgetCantidadAmigosyAmigos(){
		assertEquals(4821, dm.getFriendsMyFriends(1l).size());
	}

}
