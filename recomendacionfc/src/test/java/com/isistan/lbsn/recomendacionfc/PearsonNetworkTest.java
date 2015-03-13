package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.similitudestructural.CosenoNetwork;
import com.isistan.lbsn.similitudestructural.GrafoDataModel;
import com.isistan.lbsn.similitudestructural.PearsonNetwork;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import junit.framework.TestCase;

public class PearsonNetworkTest extends TestCase {
	PearsonNetwork sim;
	public PearsonNetworkTest(String name) {
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

		dataModel.addEdge((Integer)1, 1l, 2l); 
		dataModel.addEdge((Integer)2, 1l, 3l);
		dataModel.addEdge((Integer)3, 2l, 3l);
		dataModel.addEdge((Integer)4, 2l, 4l);
		dataModel.addEdge((Integer)5, 3l, 5l);
		dataModel.addEdge((Integer)6, 4l, 6l);
		dataModel.addEdge((Integer)7, 5l, 6l);
		sim = new PearsonNetwork(new GrafoDataModel(dataModel));
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testUserSimilarity() throws TasteException {
		System.out.println(sim.userSimilarity(2l, 5l));
		assertEquals(0.0, sim.userSimilarity(2l, 5l));
	}

}
