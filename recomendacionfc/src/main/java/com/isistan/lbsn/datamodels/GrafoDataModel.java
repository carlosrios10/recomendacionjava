package com.isistan.lbsn.datamodels;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections15.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isistan.lbsn.experimentos.datasetyelp.ExperimentosSeleccionVecinos;
import com.isistan.lbsn.scoring.Nodo;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.scoring.DegreeScorer;
import edu.uci.ics.jung.algorithms.scoring.HITS;
import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.GraphIOException;
import edu.uci.ics.jung.io.graphml.EdgeMetadata;
import edu.uci.ics.jung.io.graphml.GraphMLReader2;
import edu.uci.ics.jung.io.graphml.GraphMetadata;
import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
import edu.uci.ics.jung.io.graphml.NodeMetadata;

public class GrafoDataModel implements GrafoModel {
	private UndirectedSparseGraph<Long, Integer> grafo;
	private UndirectedSparseGraph<Nodo, Integer> grafoN;
	private PageRank pageRank;
	private HITS hits;
	private BetweennessCentrality betweennes;
	private DegreeScorer degree;
	private ClosenessCentrality closeness;
	private UnweightedShortestPath<Long,Integer> distanciaCaminoCortoSinPeso;
	private static final Logger log = LoggerFactory.getLogger(GrafoDataModel.class);

	public GrafoDataModel(UndirectedSparseGraph<Long, Integer> grafo,
			UndirectedSparseGraph<Nodo, Integer> grafoN) {
		super();
		this.grafo = grafo;
		this.setGrafoN(grafoN);
	}

	public GrafoDataModel() {
		super();
	}

	public GrafoDataModel(UndirectedSparseGraph<Long, Integer> grafo) {
		super();
		this.grafo = grafo;
		pageRank = new PageRank<Long, Integer>(getGrafo(), 0.95);
		hits = new HITS<Long, Integer>(getGrafo(),1);
		betweennes =  new BetweennessCentrality<Long, Integer>(getGrafo()); 
		degree = new DegreeScorer<Long>(getGrafo());
		closeness = new ClosenessCentrality<Long,Integer>(getGrafo());
	}

	/**
	 * 
	 * @param fileName
	 */
	public GrafoDataModel(String fileName) {
		super();
		log.info("Grafo - file "+fileName);
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
//			pageRank = new PageRank<Long, Integer>(getGrafo(),0.95);
//			hits = new HITS<Long, Integer>(getGrafo());
//			degree = new DegreeScorer<Long>(getGrafo());
//			betweennes =  new BetweennessCentrality<Long, Integer>(getGrafo()); 
//			closeness = new ClosenessCentrality<Long,Integer>(getGrafo());
			distanciaCaminoCortoSinPeso = new UnweightedShortestPath<Long, Integer>(getGrafo());
			log.info("Inicia evaluacion");
			System.out.println("Grafo cantidad de nodos " + this.getGrafo().getVertexCount());
			System.out.println("Grafo cantidad de aristas " + this.getGrafo().getEdgeCount());
			

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
		if (amigos == null)
			return null;
		
		Collection<Long> totalVecinos = new ArrayList<Long>();
		for (Long amigo : amigos) {
			totalVecinos.add(amigo);
			List<Long> amigosDeAmigo = new ArrayList<Long>(getGrafo().getNeighbors(amigo));
			amigosDeAmigo.remove(new Long(userID));
			totalVecinos.addAll(amigosDeAmigo);
		}
		return 	( new HashSet<Long>(totalVecinos));
	}

	public double getPageRank(long userID) {
		return (Double) pageRank.getVertexScore(userID);
	}

	public double getHits(long userID) {
		System.out.println(hits.getVertexScore(userID));
		return  0;
	}

	public double getBetweenness(long userID) {
		return betweennes.getVertexScore(userID);
	}

	public double getHub(long userID) {
		HITS.Scores score = (HITS.Scores) hits.getVertexScore(userID);
		return score.hub;
	}

	public double getAuthority(long userID) {
		HITS.Scores score = (HITS.Scores) hits.getVertexScore(userID);
		return score.authority;
	}

	public double getDegree(long userID) {
		return degree.getVertexScore(userID);
	}

	public double getCloseness(long userID) {
		 return closeness.getVertexScore(userID);
	}
	private void buscarAmigos(Collection<Long> totalVecinos, Collection<Long> amigos){
		Collection<Long> listaAux = new  HashSet<Long>(amigos);
		for(Long amigo: listaAux){
			totalVecinos.add(amigo);
			List<Long> amigosDeAmigo = new ArrayList<Long>(getFriends(amigo));
			amigosDeAmigo.remove(new Long(amigo));
			totalVecinos.addAll(amigosDeAmigo);
			amigos.addAll(amigosDeAmigo);
			amigos.remove(amigo);
		}
	}
	public Collection<Long> getFriendsMyFriends(long userID, int nivel) {
		if (null == getFriends(userID)){
			return null;
		}
		Collection<Long> totalVecinos = new HashSet<Long>();
		Collection<Long> amigos = new HashSet<Long>();
		amigos.add(userID);
		for (int i = 1;i<=nivel;i++){
			buscarAmigos(totalVecinos, amigos);
		}
		totalVecinos.remove(new Long(userID));
		return totalVecinos;
	}

	public UnweightedShortestPath<Long, Integer> getDistanciaCaminoCortoSinPeso() {
		return distanciaCaminoCortoSinPeso;
	}

	public void setDistanciaCaminoCortoSinPeso(
			UnweightedShortestPath<Long, Integer> distanciaCaminoCortoSinPeso) {
		this.distanciaCaminoCortoSinPeso = distanciaCaminoCortoSinPeso;
	}



//	public long distancia getDistancia(long userID, long) {
//		grafo.
//		return ;
//	}

}
