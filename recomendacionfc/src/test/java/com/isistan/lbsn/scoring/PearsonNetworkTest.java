package com.isistan.lbsn.scoring;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.scoring.ScoringCosenoNetwork;
import com.isistan.lbsn.scoring.Nodo;
import com.isistan.lbsn.scoring.ScoringPearsonNetwork;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class PearsonNetworkTest extends TestCase {
	ScoringPearsonNetwork sim;
	public PearsonNetworkTest(String name) {
		super(name);
//		// Graph<V, E> where V is the type of the vertices and E is the type of the edges
//		UndirectedSparseGraph<Nodo, Integer> dataModel = new UndirectedSparseGraph<Nodo, Integer>();
//		// Add some vertices. From above we defined these to be type Integer.
//		
//		
//		dataModel.addVertex(new Nodo(1l,"10"));
//		dataModel.addVertex(new Nodo(2l,"20"));
//		dataModel.addVertex(new Nodo(3l,"30"));
//		dataModel.addVertex(new Nodo(4l,"40")); 
//		dataModel.addVertex(new Nodo(5l,"50")); 
//		dataModel.addVertex(new Nodo(6l,"60"));
//
//		dataModel.addEdge((Integer)1,new Nodo(1l,"10"), new Nodo(2l,"20")); 
//		dataModel.addEdge((Integer)2,new Nodo(1l,"10"), new Nodo(3l,"30"));
//		dataModel.addEdge((Integer)3,new Nodo(2l,"20"), new Nodo(3l,"30"));
//		dataModel.addEdge((Integer)4,new Nodo(2l,"20"), new Nodo(4l,"40"));
//		dataModel.addEdge((Integer)5,new Nodo(3l,"30"), new Nodo(5l,"50"));
//		dataModel.addEdge((Integer)6,new Nodo(4l,"40"), new Nodo(6l,"60"));
//		dataModel.addEdge((Integer)7,new Nodo(5l,"50"), new Nodo(6l,"60"));
//		sim = new PearsonNetwork(new GrafoDataModel(null,dataModel));
		
		// Graph<V, E> where V is the type of the vertices and E is the type of the edges
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
		sim = new ScoringPearsonNetwork(new GrafoDataModel(dataModel));
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testUserSimilarityN2N5() throws TasteException {
		assertEquals(0.0, sim.userSimilarity(2l, 5l));
	}
	public void testUserSimilarityN1N6() throws TasteException {
		assertEquals(-0.5, sim.userSimilarity(1l,6l));
	}
	public void testUserSimilarityN1N4() throws TasteException {
		assertEquals(0.25, sim.userSimilarity(1l,4l));
	}
	public void testUserSimilarityN2N3() throws TasteException {
		assertEquals(-0.3333333, sim.userSimilarity(2l,3l),0.001);
	}

	public void testUserSimilarityN2Nnoexistente() throws TasteException {
		assertEquals(0.0, sim.userSimilarity(2l,10l));
	}

}
