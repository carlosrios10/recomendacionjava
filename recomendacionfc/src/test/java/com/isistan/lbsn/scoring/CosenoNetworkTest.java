package com.isistan.lbsn.scoring;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.scoring.ScoringCosenoNetwork;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import junit.framework.TestCase;

public class CosenoNetworkTest extends TestCase {
	ScoringCosenoNetwork sim;
	public CosenoNetworkTest(String name) {
		super(name);
		// Graph<V, E> where V is the type of the vertices and E is the type of the edges
		UndirectedSparseGraph<Long, Integer> dataModel = new UndirectedSparseGraph<Long, Integer>();
		// Add some vertices. From above we defined these to be type Integer.
		dataModel.addVertex(1l);
		dataModel.addVertex(2l);
		dataModel.addVertex(3l);
		dataModel.addVertex(4l); 
		dataModel.addVertex(5l); 
		dataModel.addVertex(6l);
		dataModel.addVertex(7l);
		dataModel.addVertex(8l);
		dataModel.addVertex(9l);
		dataModel.addVertex(10l);

		dataModel.addEdge((Integer)1, 1l, 2l); 
		dataModel.addEdge((Integer)2, 1l, 3l);
		dataModel.addEdge((Integer)3, 2l, 3l);
		dataModel.addEdge((Integer)4, 2l, 4l);
		dataModel.addEdge((Integer)5, 3l, 5l);
		dataModel.addEdge((Integer)6, 4l, 6l);
		dataModel.addEdge((Integer)7, 5l, 6l);
		dataModel.addEdge((Integer)8, 6l, 7l);
		dataModel.addEdge((Integer)9, 8l, 9l);
		dataModel.addEdge((Integer)10, 8l, 10l);
		dataModel.addEdge((Integer)11, 9l, 10l);
		
	//	GrafoDataModel g3 = new GrafoDataModel(MyProperties.getInstance().getProperty("databasegrafographml"));
		GrafoDataModel g3 = new GrafoDataModel(dataModel);
		sim = new ScoringCosenoNetwork(g3);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
//	public void testUserSimilarityCorrecto_v2_v5() throws TasteException {
//		System.out.println(sim.userSimilarity(2l, 5l));
//		assertEquals(0.40,sim.userSimilarity(2l, 5l),0.01);
//	}
//	
//	public void testUserSimilarityCorrecto_v6_v7() throws TasteException {
//		System.out.println(sim.userSimilarity(6l, 7l));
//		assertEquals(0.40,sim.userSimilarity(6l, 7l),0.01);
//	}
//	
//	public void testUserSimilarityCorrecto_v1_v2() throws TasteException {
//		System.out.println(sim.userSimilarity(1l, 2l));
//		assertEquals(0.40,sim.userSimilarity(1l, 2l),0.01);
//	}
//
	public void testUserSimilarityCorrecto_v4_v2() throws TasteException {
		System.out.println(sim.userSimilarity(4l, 2l));
		assertEquals(0.40,sim.userSimilarity(4l, 2l),0.01);
	}
	
//	public void testUserSimilarityCorrecto_v8_v9() throws TasteException {
//		System.out.println(sim.userSimilarity(8l, 9l));
//		assertEquals(0.40,sim.userSimilarity(8l, 9l),0.01);
//	}
//	
//	public void testUserSimilarityCorrecto_v1_v9() throws TasteException {
//		System.out.println(sim.userSimilarity(1l, 9l));
//		assertEquals(0.40,sim.userSimilarity(1l, 9l),0.01);
//	}
}

