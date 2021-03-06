package com.isistan.lbsn.datamodels;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.google.common.primitives.Longs;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class GrafoDataModelTest extends TestCase {
	GrafoDataModel g;
	GrafoDataModel g2;
	GrafoDataModel g3;
	public GrafoDataModelTest(String name) {
		super(name);
		UndirectedSparseGraph<Long, Integer> dataModel = new UndirectedSparseGraph<Long, Integer>();
		// Add some vertices. From above we defined these to be type Integer.
		dataModel.addVertex(1l);
		dataModel.addVertex(2l);
		dataModel.addVertex(3l);
		dataModel.addVertex(4l); 
		dataModel.addVertex(5l); 
		dataModel.addVertex(6l);

		dataModel.addEdge((Integer)1, 1l, 2l); 
		dataModel.addEdge((Integer)2, 1l, 3l);
		dataModel.addEdge((Integer)3, 2l, 3l);
		dataModel.addEdge((Integer)4, 2l, 4l);
		dataModel.addEdge((Integer)5, 3l, 5l);
		dataModel.addEdge((Integer)6, 4l, 6l);
		dataModel.addEdge((Integer)7, 5l, 6l);
	//	g = new GrafoDataModel(dataModel);
		
		UndirectedSparseGraph<Long, Integer> dataModel2 = new UndirectedSparseGraph<Long, Integer>();
		// Add some vertices. From above we defined these to be type Integer.
		dataModel2.addVertex(1l);
		dataModel2.addVertex(2l);
		dataModel2.addVertex(3l);
		dataModel2.addVertex(4l); 
		dataModel2.addVertex(5l);
		dataModel2.addVertex(6l);
		dataModel2.addVertex(7l);
		dataModel2.addVertex(8l);
		dataModel2.addVertex(9l);
		dataModel2.addVertex(10l);

		dataModel2.addEdge((Integer)1, 1l, 2l); 
		dataModel2.addEdge((Integer)2, 1l, 4l);
		dataModel2.addEdge((Integer)3, 1l, 5l);
		
		dataModel2.addEdge((Integer)4, 2l, 3l);
		dataModel2.addEdge((Integer)5, 2l, 5l);

		dataModel2.addEdge((Integer)6, 3l, 4l);
		dataModel2.addEdge((Integer)7, 3l, 5l);
		
		dataModel2.addEdge((Integer)8, 5l, 7l);
		dataModel2.addEdge((Integer)9, 7l, 8l);
		dataModel2.addEdge((Integer)10, 7l, 9l);
		
		g2 = new GrafoDataModel(dataModel2);
		//g3 = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
		
		FastByIDMap<PreferenceArray> preferences = 	new FastByIDMap<PreferenceArray>();
		PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(5);
		prefsForUser1.setUserID(0, 1L);
		prefsForUser1.setItemID(0, 101L);
		prefsForUser1.setValue(0, 1f);
		prefsForUser1.setItemID(1, 102L);
		prefsForUser1.setValue(1, 2f);
		prefsForUser1.setItemID(2, 103L);
		prefsForUser1.setValue(2, 5f);
		prefsForUser1.setItemID(3, 104L);
		prefsForUser1.setValue(3, 3f);
		prefsForUser1.setItemID(4, 105L);
		prefsForUser1.setValue(4, 4f);

		PreferenceArray prefsForUser2 = new GenericUserPreferenceArray(5);
		prefsForUser2.setUserID(0, 2L);
		prefsForUser2.setItemID(0, 101L);
		prefsForUser2.setValue(0, 4f);
		
		prefsForUser2.setItemID(1, 102L);
		prefsForUser2.setValue(1, 3f);
		prefsForUser2.setItemID(2, 103L);
		prefsForUser2.setValue(2, 5f);
		prefsForUser2.setItemID(3, 104L);
		prefsForUser2.setValue(3, 1f);
		prefsForUser2.setItemID(4, 105L);
		prefsForUser2.setValue(4, 3f);
		
		PreferenceArray prefsForUser3 = new GenericUserPreferenceArray(3);
		prefsForUser3.setUserID(0, 3L);
		prefsForUser3.setItemID(0, 103L);
		prefsForUser3.setValue(0, 5f);
		prefsForUser3.setItemID(1, 104L);
		prefsForUser3.setValue(1, 4f);
		prefsForUser3.setItemID(2, 105L);
		prefsForUser3.setValue(2, 3f);

		
		PreferenceArray prefsForUser4 = new GenericUserPreferenceArray(0);
		PreferenceArray prefsForUser5 = new GenericUserPreferenceArray(0);
		PreferenceArray prefsForUser6 = new GenericUserPreferenceArray(0);
		
		preferences.put(1L, prefsForUser1);
		preferences.put(2L, prefsForUser2);
		preferences.put(3L, prefsForUser3);
		preferences.put(4L, prefsForUser4);
		preferences.put(5L, prefsForUser4);
		preferences.put(6L, prefsForUser4);
		preferences.put(7L, prefsForUser4);
		preferences.put(8L, prefsForUser4);
		preferences.put(9L, prefsForUser4);
		preferences.put(10L, prefsForUser4);
		preferences.put(new Long(85), prefsForUser4);
		DataModel model = new GenericDataModel(preferences);
	//	g2.setDataModel(model);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testGetFriendsMyFriends() {
//		assertEquals(4, g.getFriendsMyFriends(1l).size());
//		assertEquals(5, g.getFriendsMyFriends(5l).size());
//		g.getPageRank(2L);
//	}
//	
//	public void testGetPageRankOK() {
//		assertEquals(0.2, g2.getPageRank(4L),0.0001);
//	}
//	
//	public void testGetHubOK() {
//		assertEquals(0.2, g2.getHub(4L),0.0001);
//	}
//
//	public void testGetPageBetweennessOK() {
//		assertEquals(1.0, g2.getBetweenness(1L),0.0001);
//	}
//	public void testGetDegreeOK() {
//		assertEquals(3.0, g2.getDegree(1L));
//	}
//	public void testGetClosness() {
//		assertEquals(0.8, g2.getCloseness(1L));
//	}
//	public void testGetCantidadNodosOK(){
//		assertEquals(101, g3.getGrafo().getVertexCount());
//	}
//	
//	public void testGetCantidadAristasOK(){
//		assertEquals(100, g3.getGrafo().getEdgeCount());
//	}
//	public void testGetDistanciaCaminoCortoSinPeso_32_33_OK(){
//		assertEquals(1, g3.getDistanciaCaminoCortoSinPeso().getDistance(32l, 33l));
//	}
//	public void testGetDistanciaCaminoCortoSinPeso_32_32_OK(){
//		assertEquals(0, g3.getDistanciaCaminoCortoSinPeso().getDistance(32l, 32l));
//	}
//	public void testGetDistanciaCaminoCortoSinPeso_45_215_OK(){
//		assertEquals(2, g3.getDistanciaCaminoCortoSinPeso().getDistance(45l, 215l));
//	}
	
//	public void testGetFriends_g2_1_nivel1_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(2l);
//		trueVal.add(4l);
//		trueVal.add(5l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(1l,1)));
//	}
//	
//	public void testGetFriends_g2_1_nivel2_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(2l);
//		trueVal.add(4l);
//		trueVal.add(5l);
//		trueVal.add(3l);
//		trueVal.add(7l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(1l,2)));
//	}
//	
//	public void testGetFriends_g2_4_nivel1_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(1l);
//		trueVal.add(3l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(4l,1)));
//	}
//	
//	public void testGetFriends_g2_4_nivel2_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(1l);
//		trueVal.add(3l);
//		trueVal.add(2l);
//		trueVal.add(5l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(4l,2)));
//	}
//	
//	public void testGetFriends_g2_6_sinVecinos_OK(){
//		assertEquals(0,g2.getFriendsMyFriends(6l,1).size());
//	}
//	
//	public void testGetFriends_g2_9_nivel1_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(9l,1)));
//	}
//	
//	public void testGetFriends_g2_9_nivel2_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		trueVal.add(8l);
//		trueVal.add(5l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(9l,2)));
//	}
//	
//	public void testGetFriends_g2_9_nivel3_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		trueVal.add(8l);
//		trueVal.add(5l);
//		trueVal.add(1l);
//		trueVal.add(2l);
//		trueVal.add(3l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(9l,3)));
//	}
//	
//	public void testGetFriends_g2_9_nivel4_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		trueVal.add(8l);
//		trueVal.add(5l);
//		trueVal.add(1l);
//		trueVal.add(2l);
//		trueVal.add(3l);
//		trueVal.add(4l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,g2.getFriendsMyFriends(9l,4)));
//	}
//	
//	
//	public void testGetFriends_g2_1_nivel1_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(2l);
//		trueVal.add(4l);
//		trueVal.add(5l);
//
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(1l,1).toArray())));
//	}
//	
//	public void testGetFriends_g2_1_nivel2_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(2l);
//		trueVal.add(4l);
//		trueVal.add(5l);
//		trueVal.add(3l);
//		trueVal.add(7l);
//		g2.getFriendsMyFriends(1l,1);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(1l,2).toArray())));
//	}
//	
//	public void testGetFriends_g2_4_nivel1_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(1l);
//		trueVal.add(3l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(4l,1).toArray())));
//	}
//	
//	public void testGetFriends_g2_4_nivel2_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(1l);
//		trueVal.add(3l);
//		trueVal.add(2l);
//		trueVal.add(5l);
//		g2.getFriendsMyFriends(4l,1);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(4l,2).toArray())));
//	}
//	
//	public void testGetFriends_g2_6_sinVecinos_OK(){
//		assertEquals(0,g2.getFriendsMyFriends(6l,1).size());
//	}
//	
//	public void testGetFriends_g2_9_nivel1_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(9l,1).toArray())));
//	}
//	
//	public void testGetFriends_g2_9_nivel2_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		trueVal.add(8l);
//		trueVal.add(5l);
//		g2.getFriendsMyFriends(9l,1);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(9l,2).toArray())));
//	}
//	
//	public void testGetFriends_g2_9_nivel3_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		trueVal.add(8l);
//		trueVal.add(5l);
//		trueVal.add(1l);
//		trueVal.add(2l);
//		trueVal.add(3l);
//		g2.getFriendsMyFriends(9l,1);
//		g2.getFriendsMyFriends(9l,2);
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,
//				Longs.asList(g2.getFriendsMyFriendsFastIDSet(9l,3).toArray())));
//	}
//	
//	public void testGetFriends_g2_9_nivel4_OK(){
//		Collection<Long> trueVal = new  HashSet<Long>();
//		trueVal.add(7l);
//		trueVal.add(8l);
//		trueVal.add(5l);
//		trueVal.add(1l);
//		trueVal.add(2l);
//		trueVal.add(3l);
//		trueVal.add(4l);
//		g2.getFriendsMyFriends(9l,1);
//		g2.getFriendsMyFriends(9l,2);
//		g2.getFriendsMyFriends(9l,4);
//		System.out.println(g2.getFriendsMyFriends(9l,4));
//		assertEquals(true, CollectionUtils.isEqualCollection(trueVal,Longs.asList(g2.getFriendsMyFriendsFastIDSet(9l,4).toArray())));
//	}
//	
//	public void testGetFriends_not_Vertex_OK(){
//		assertNull(g2.getFriendsMyFriendsFastIDSet(100l,1));
//	}
//	
//	
//	public void testGetFriends_Vertex_without_neigh_OK(){
//		assertEquals(0, g2.getFriendsMyFriendsFastIDSet(10l,1).size());
//	}
	
	
	public void testGetFriends_not_Vertex_OK(){
		assertNull(g2.getFriendsMyFriends(100l,1));
	}
	
	
	public void testGetFriends_Vertex_without_neigh_OK(){
		assertEquals(0, g2.getFriendsMyFriends(10l,1).size());
	}
}
