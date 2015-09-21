package com.isistan.lbsn.recomendacionfc;

import org.apache.mahout.cf.taste.common.TasteException;

import com.isistan.lbsn.datamodels.GrafoDataModel;
import com.isistan.lbsn.vencindario.NearestNUserFriends;
import com.isistan.lbsn.vencindario.NearestNUserFriendsAndFriends;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import junit.framework.TestCase;

public class NearestNUserFriendsAndFriendsTest extends TestCase {
	NearestNUserFriendsAndFriends vecindario;
	public NearestNUserFriendsAndFriendsTest(String name) {
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
		vecindario = new NearestNUserFriendsAndFriends(g, Integer.MAX_VALUE);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUserNeighborhood() throws TasteException {
		assertEquals(4, vecindario.getUserNeighborhood(1l).length);
		
	}

}
