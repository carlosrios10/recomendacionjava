package com.isistan.lbsn.datamodels;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.datamodels.GrafoModel;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import junit.framework.TestCase;

public class GrafoDataModelTest extends TestCase {
	GrafoDataModel g;
	GrafoDataModel g2;
	GrafoDataModel g3;
	public GrafoDataModelTest(String name) {
		super(name);
//		UndirectedSparseGraph<Long, Integer> dataModel = new UndirectedSparseGraph<Long, Integer>();
//		// Add some vertices. From above we defined these to be type Integer.
//		dataModel.addVertex(1l);
//		dataModel.addVertex(2l);
//		dataModel.addVertex(3l);
//		dataModel.addVertex(4l); 
//		dataModel.addVertex(5l); 
//		dataModel.addVertex(6l);
//
//		dataModel.addEdge((Integer)1, 1l, 2l); 
//		dataModel.addEdge((Integer)2, 1l, 3l);
//		dataModel.addEdge((Integer)3, 2l, 3l);
//		dataModel.addEdge((Integer)4, 2l, 4l);
//		dataModel.addEdge((Integer)5, 3l, 5l);
//		dataModel.addEdge((Integer)6, 4l, 6l);
//		dataModel.addEdge((Integer)7, 5l, 6l);
//		g = new GrafoDataModel(dataModel);
//		
//		UndirectedSparseGraph<Long, Integer> dataModel2 = new UndirectedSparseGraph<Long, Integer>();
//		// Add some vertices. From above we defined these to be type Integer.
//		dataModel2.addVertex(1l);
//		dataModel2.addVertex(2l);
//		dataModel2.addVertex(3l);
//		dataModel2.addVertex(4l); 
//		dataModel2.addVertex(5l); 
//
//		dataModel2.addEdge((Integer)1, 1l, 2l); 
//		dataModel2.addEdge((Integer)2, 1l, 4l);
//		dataModel2.addEdge((Integer)3, 1l, 5l);
//		
//		dataModel2.addEdge((Integer)4, 2l, 3l);
//		dataModel2.addEdge((Integer)5, 2l, 5l);
//
//		dataModel2.addEdge((Integer)6, 3l, 4l);
//		dataModel2.addEdge((Integer)7, 3l, 5l);
//		
//		g2 = new GrafoDataModel(dataModel2);
	///	GrafoModel grafoModel = new FriendsDataModel(MyProperties.getInstance().getProperty("databasegrafo"));
		g3 = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
		
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
	public void testGetCantidadNodosOK(){
		assertEquals(101, g3.getGrafo().getVertexCount());
	}
	
	public void testGetCantidadAristasOK(){
		assertEquals(100, g3.getGrafo().getEdgeCount());
	}
	public void testGetDistanciaCaminoCortoSinPeso_32_33_OK(){
		assertEquals(1, g3.getDistanciaCaminoCortoSinPeso().getDistance(32l, 33l));
	}
	public void testGetDistanciaCaminoCortoSinPeso_32_32_OK(){
		assertEquals(0, g3.getDistanciaCaminoCortoSinPeso().getDistance(32l, 32l));
	}
	public void testGetDistanciaCaminoCortoSinPeso_45_215_OK(){
		assertEquals(2, g3.getDistanciaCaminoCortoSinPeso().getDistance(45l, 215l));
	}
}
