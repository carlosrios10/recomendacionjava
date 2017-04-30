package com.isistan.lbsn.similarity;




import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;

import com.isistan.lbsn.config.MyProperties;
import com.isistan.lbsn.similitudcombinada.SimilitudProxy;

public class SimilarityProxyTest extends TestCase {
	SimilitudProxy sim;
	public SimilarityProxyTest(String name) {
		super(name);
		DataModel ratingModelTotal;
		try {
			ratingModelTotal = new FileDataModel(new File(MyProperties.getInstance().getProperty("databaserating")));
			try {
				sim = new SimilitudProxy(new UncenteredCosineSimilarity(ratingModelTotal)) ;
				sim.userSimilarity(2, 34);
				sim.userSimilarity(2, 148);
			} catch (TasteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test_user_2_OK() throws TasteException{
		System.out.println(sim.userSimilarity(2,34));
		System.out.println(sim.userSimilarity(34,2));
		System.out.println(sim.userSimilarity(148,34));
	}
	
	
}



