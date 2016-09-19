package com.isistan.lbsn.datamodels;

import java.io.File;

import junit.framework.TestCase;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

public class FileDataModelTest extends TestCase {
	
	private FileDataModel visitasDM;
	private FileDataModel sentimientoDM;

	
	public FileDataModelTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		visitasDM = new FileDataModel(new File("C:/Users/Usuario/Desktop/carlos/Tesis/datasets/forsquare-2/AnalisisProject/clean_dataset/2_count_tips_con_user_grafo_ny.csv"));
		sentimientoDM = new FileDataModel(new File("C:/Users/Usuario/Desktop/carlos/Tesis/datasets/forsquare-2/AnalisisProject/clean_dataset/3_count_tips_con_user_grafo_ny_tips_senti_discret_2.csv"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testUserPreferenciaMatrizVistasOk() throws TasteException {
		assertEquals(2.0f, visitasDM.getPreferenceValue(32,120003));
		
	}
	
	public void testUserPreferenciaMatrizSentimientoOk() throws TasteException {
		assertEquals(1.0f, sentimientoDM.getPreferenceValue(32,120003));
		
	}
}
