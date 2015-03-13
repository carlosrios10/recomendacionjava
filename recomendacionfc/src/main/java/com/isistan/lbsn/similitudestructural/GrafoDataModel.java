package com.isistan.lbsn.similitudestructural;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.GraphIOException;
import edu.uci.ics.jung.io.graphml.EdgeMetadata;
import edu.uci.ics.jung.io.graphml.GraphMLReader2;
import edu.uci.ics.jung.io.graphml.GraphMetadata;
import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
import edu.uci.ics.jung.io.graphml.NodeMetadata;

public class GrafoDataModel {
	private UndirectedSparseGraph<Long, Integer> grafo;

	public GrafoDataModel(UndirectedSparseGraph<Long, Integer> grafo) {
		super();
		this.grafo = grafo;
	}

	/**
	 * 
	 * @param fileName
	 */
	public GrafoDataModel(String fileName) {
		super();
		try {
			FileReader reader = new FileReader(fileName);
			Transformer<NodeMetadata, Long> vtrans = new Transformer<NodeMetadata, Long>() {
				public Long transform(NodeMetadata nmd) {
					return new Long(nmd.getProperty("v_name"));
				}
			};
			Transformer<EdgeMetadata, Integer> etrans = new Transformer<EdgeMetadata, Integer>() {
				private int e = 0;

				public Integer transform(EdgeMetadata emd) {
					return (e++);
				}
			};
			Transformer<GraphMetadata, UndirectedSparseGraph<Long, Integer>> gtrans = new Transformer<GraphMetadata, UndirectedSparseGraph<Long, Integer>>() {
				public UndirectedSparseGraph<Long, Integer> transform(
						GraphMetadata gmd) {
					return new UndirectedSparseGraph<Long, Integer>();
				}
			};
			Transformer<HyperEdgeMetadata, Integer> hetrans = new Transformer<HyperEdgeMetadata, Integer>() {

				public Integer transform(HyperEdgeMetadata emd) {
					return (new Integer(1));
				}
			};
			GraphMLReader2<UndirectedSparseGraph<Long, Integer>, Long, Integer> gmlr = new GraphMLReader2<UndirectedSparseGraph<Long, Integer>, Long, Integer>(
					reader, gtrans, vtrans, etrans, hetrans);
			setGrafo(gmlr.readGraph());

		} catch (FileNotFoundException e) {
			System.out.println("archivo no existe");
			e.printStackTrace();
		} catch (GraphIOException e1) {
			System.out.println("error al cargar el grafo");
			e1.printStackTrace();
		}
	}

	public UndirectedSparseGraph<Long, Integer> getGrafo() {
		return grafo;
	}

	public void setGrafo(UndirectedSparseGraph<Long, Integer> grafo) {
		this.grafo = grafo;
	}

}
