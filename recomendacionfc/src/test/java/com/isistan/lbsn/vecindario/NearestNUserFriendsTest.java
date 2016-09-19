package com.isistan.lbsn.vecindario;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.scoring.PearsonNetwork;
import com.isistan.lbsn.vencindario.NearestNUserFriends;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import junit.framework.TestCase;

public class NearestNUserFriendsTest extends TestCase {
	NearestNUserFriends vecindario;

	public NearestNUserFriendsTest(String name) {
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
		GrafoDataModel g= new GrafoDataModel();
		g.setGrafo(dataModel);
		vecindario = new NearestNUserFriends(g, Integer.MAX_VALUE);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUserNeighborhood() throws TasteException {
		assertEquals(2, vecindario.getUserNeighborhood(1l).length);
		assertEquals(3, vecindario.getUserNeighborhood(2l).length);
	}

}
