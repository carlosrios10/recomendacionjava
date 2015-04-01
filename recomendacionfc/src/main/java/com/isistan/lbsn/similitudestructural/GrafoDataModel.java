package com.isistan.lbsn.similitudestructural;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
	private UndirectedSparseGraph<Nodo, Integer> grafoN;

	public GrafoDataModel(UndirectedSparseGraph<Long, Integer> grafo,
			UndirectedSparseGraph<Nodo, Integer> grafoN) {
		super();
		this.grafo = grafo;
		this.setGrafoN(grafoN);
	}

	public GrafoDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

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
				// long idLong = 0;
				public Long transform(NodeMetadata nmd) {
					// idLong++;
					String name = nmd.getProperty("v_name");
					
					// Long Long = new Long(idLong,name);
					return new Long(name.trim());
					//
					// Long Long = new Long(id, nombre);
					// return new Long();
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

	public UndirectedSparseGraph<Nodo, Integer> getGrafoN() {
		return grafoN;
	}

	public void setGrafoN(UndirectedSparseGraph<Nodo, Integer> grafoN) {
		this.grafoN = grafoN;
	}

	public Collection<Long> getFriends(long userID) {
			Collection<Long> amigos = getGrafo().getNeighbors(userID);
			return amigos;
	}

	public int getCantidadAmigos(long userID) {
		return getGrafo().getNeighborCount(userID);
	}

	public Collection<Long> getFriendsMyFriends(long userID) {
		Collection<Long> amigos = getGrafo().getNeighbors(userID);
		Collection<Long> totalVecinos = new ArrayList<Long>();
		for (Long amigo : amigos) {
			totalVecinos.add(amigo);
			List<Long> amigosDeAmigo = new ArrayList<Long>(getGrafo().getNeighbors(amigo));
			amigosDeAmigo.remove(new Long(userID));
			totalVecinos.addAll(amigosDeAmigo);
		}
		return 	( new HashSet<Long>(totalVecinos));
	}

}
