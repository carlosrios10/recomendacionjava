package com.isistan.lbsn.datamodels;

import com.isistan.lbsn.entidades.ScoringEntidad;

import junit.framework.TestCase;

public class ScoringEntidadTest extends TestCase {
	ScoringDataModel sd ;
	protected void setUp() throws Exception {
		super.setUp();
		sd = new ScoringDataModel("C:/Users/Usuario/Desktop/carlos/Tesis/datasets/forsquare-2/AnalisisProject/scoring_camino_usuario_usuario_grafo_visitas.csv");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testGetScore() {
		assertEquals(2.0, sd.getScore(960053l, 32l));
	}

}
