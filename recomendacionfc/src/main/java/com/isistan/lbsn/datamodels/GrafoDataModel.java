package com.isistan.lbsn.datamodels;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections15.Transformer;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Longs;
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
	DataModel dataModel;
//	private Map<Long,Collection<Long>> userCache = new Hashtable<Long, Collection<Long>>(125000);
//	private Map<Long,Integer> userCacheLevel = new Hashtable<Long, Integer>(125000);
	
//	private Map<Long,Collection<Long>> vecinosNivel1 = new ConcurrentHashMap<Long, Collection<Long>>();
//	private Map<Long,Collection<Long>> vecinosNivel2 = new ConcurrentHashMap<Long, Collection<Long>>();
//	private Map<Long,Collection<Long>> vecinosNivel3 = new ConcurrentHashMap<Long, Collection<Long>>();
//	private Map<Long,Collection<Long>> vecinosNivel4 = new ConcurrentHashMap<Long, Collection<Long>>();
//	private Map<Long,Collection<Long>> vecinosNivel5 = new ConcurrentHashMap<Long, Collection<Long>>();
	
	FastByIDMap<FastIDSet> vecinosNivel1 = 	new FastByIDMap<FastIDSet>();
	FastByIDMap<FastIDSet> vecinosNivel2 = 	new FastByIDMap<FastIDSet>();
	FastByIDMap<FastIDSet> vecinosNivel3 = 	new FastByIDMap<FastIDSet>();
	
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
		distanciaCaminoCortoSinPeso = new UnweightedShortestPath<Long, Integer>(grafo);
		
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
			//distanciaCaminoCortoSinPeso = new UnweightedShortestPath<Long, Integer>(getGrafo());
			log.info("Grafo cantidad de nodos " + this.getGrafo().getVertexCount());
			log.info("Grafo cantidad de aristas " + this.getGrafo().getEdgeCount());
			//log.info("Grafo Cargando Niveles > 2 ");
			//cargarVecindario();
			

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
	
	private void buscarAmigos(FastIDSet totalVecinos, FastIDSet amigos){
		FastIDSet listaAux = new  FastIDSet();
		listaAux.addAll(amigos.toArray());
		for(Long amigo: listaAux){
			totalVecinos.add(amigo);
			List<Long> amigosDeAmigo = new ArrayList<Long>(getFriends(amigo));
			amigosDeAmigo.remove(new Long(amigo));
			totalVecinos.addAll(Longs.toArray(amigosDeAmigo));
			amigos.addAll(Longs.toArray(amigosDeAmigo));
			amigos.remove(amigo);
		}
	}

	
//	public Collection<Long> getFriendsMyFriends(long userID, int nivel) {
//		if (null == getFriends(userID)){
//			return null;
//		}
//		
//		if(!userCache.containsKey(userID)){
//			Collection<Long> totalVecinos = new HashSet<Long>();
//			Collection<Long> amigos = new HashSet<Long>();
//			amigos.add(userID);
//			buscarAmigos(totalVecinos, amigos);
//			totalVecinos.remove(new Long(userID));
//			userCache.put(userID, totalVecinos);
//			userCacheLevel.put(userID, nivel);
//		}else{
//			int lastNivel = userCacheLevel.get(userID);
//			if(lastNivel<nivel){
//				//System.out.println(userID+" "+lastNivel+" "+nivel);
//				Collection<Long> totalVecinos = new HashSet<Long>();
//				Collection<Long> amigos = userCache.get(userID);
//				buscarAmigos(totalVecinos, amigos);
//				totalVecinos.remove(new Long(userID));
//				userCache.remove(userID);
//				userCache.put(userID,totalVecinos);
//				
//				userCacheLevel.remove(userID);
//				userCacheLevel.put(userID,nivel);
//			}
//		}
//		
//		//System.out.println(" "+userID+" "+nivel+" "+userCache.get(userID).size());
//		return userCache.get(userID);
//	}
	
//	public Collection<Long> getFriendsMyFriends(long userID, int nivel) {
//		if( !this.grafo.containsVertex(new Long(userID)))
//			return null;
//		KNeighborhoodFilter<Long, Integer> kNeighborhoodFilter = new KNeighborhoodFilter<Long , Integer>(userID, nivel, KNeighborhoodFilter.EdgeType.IN_OUT); 
//		Graph<Long, Integer> grafoVecinos =  kNeighborhoodFilter.transform(this.getGrafo());
//		grafoVecinos.removeVertex(new Long(userID));
////		log.info("user : " + userID);
////		log.info("vecinos : " + grafoVecinos.getVertexCount());
//		return grafoVecinos.getVertices();
//	}
	
	private void cargarVecindario(){
		try {
			log.info("Cargando Vecindario para usuarios :   " + this.getDataModel().getNumUsers());
			LongPrimitiveIterator vertices;
			vertices = this.getDataModel().getUserIDs();
			int cantidad = this.getDataModel().getNumUsers();
			int count = 0;
			while (vertices.hasNext()) {
				Long long1 = (Long) vertices.next();
				count++;
				if (count%100 == 0)
					log.info("cantidad usuarios procesados "+count);
				if(this.grafo.containsVertex(long1)){
					for(int i =1; i<=3;i++){
						
						FastIDSet totalVecinos = new FastIDSet();
						FastIDSet amigos = new FastIDSet();
						amigos.add(long1);
						
						if (i == 2){
						//	totalVecinos.addAll(vecinosNivel1.get(long1));
							amigos.addAll(vecinosNivel1.get(long1));
						}
	
						if (i == 3){
							//totalVecinos.addAll(vecinosNivel2.get(long1));
							amigos.addAll(vecinosNivel2.get(long1));
						}
//	
//						if (i == 4){
//							totalVecinos.addAll(vecinosNivel3.get(long1));
//							amigos.addAll(vecinosNivel3.get(long1));
//						}
//	
//						if (i == 5){
//							totalVecinos.addAll(vecinosNivel4.get(long1));
//							amigos.addAll(vecinosNivel4.get(long1));
//						}
						
			
						for (int j = i;j<=i;j++){
							buscarAmigos(totalVecinos, amigos);
						}
						totalVecinos.remove(new Long(long1));
						
						
						if (i == 1)
							vecinosNivel1.put(long1,totalVecinos);
						
						if (i == 2)
							vecinosNivel2.put(long1,totalVecinos);
	
						if (i == 3)
							vecinosNivel3.put(long1,totalVecinos);
	
//						if (i == 4)
//							vecinosNivel4.put(long1, totalVecinos);
//	
//						if (i == 5)
//							vecinosNivel5.put(long1,totalVecinos);
	
						
					}	
					
				}
					
				
				
			}
			
		log.info("Fin : Cargando Vecindario para usuarios :   " + this.getDataModel().getNumUsers());	
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		for (Long vert1 : vertices) {
//			
//			
////				KNeighborhoodFilter<Long, Integer> kNeighborhoodFilter3 = new KNeighborhoodFilter<Long , Integer>(vert1, 3, KNeighborhoodFilter.EdgeType.IN_OUT); 
////				Graph<Long, Integer> grafoVecinos3 =  kNeighborhoodFilter3.transform(this.getGrafo());
////				grafoVecinos3.removeVertex(vert1);
////				vecinosNivel3.put(vert1, grafoVecinos3.getVertices());
//				
////				KNeighborhoodFilter<Long, Integer> kNeighborhoodFilter4 = new KNeighborhoodFilter<Long , Integer>(vert1, 4, KNeighborhoodFilter.EdgeType.IN_OUT); 
////				Graph<Long, Integer> grafoVecinos4 =  kNeighborhoodFilter4.transform(this.getGrafo());
////				grafoVecinos4.removeVertex(vert1);
////				vecinosNivel4.put(vert1, grafoVecinos4.getVertices());
////				
////				KNeighborhoodFilter<Long, Integer> kNeighborhoodFilter5 = new KNeighborhoodFilter<Long , Integer>(vert1, 5, KNeighborhoodFilter.EdgeType.IN_OUT); 
////				Graph<Long, Integer> grafoVecinos5 =  kNeighborhoodFilter5.transform(this.getGrafo());
////				grafoVecinos5.removeVertex(vert1);
////				vecinosNivel5.put(vert1, grafoVecinos5.getVertices());
//			
//		}
		
		
	}

	public Integer getDistanciaSinPeso(long userID1, long userID2) {
		//return (Integer) this.distanciaCaminoCortoSinPeso.getDistance(userID1, userID2);
		if(!this.grafo.containsVertex(userID1))
			return null;

		if(!this.grafo.containsVertex(userID2))
			return null;

		if (this.grafo.isNeighbor(userID1,userID2))
			return 1;
		
		FastIDSet vecinos = getFriendsMyFriendsFastIDSet(userID1, 2);
		if (vecinos == null)
			return null;
		
		if(vecinos.contains(userID2))
			return 2;
		
		return 3;
			
			
		
//		Integer distancia = null; 
//		int nivel = 1;
//		Collection<Long> vecinos = getFriendsMyFriends(userID1, nivel);
//		Collection<Long> vecinosAux = new HashSet<Long>(vecinos);
//		int maxNivelProfundidad = 2;
//		while( (nivel<=maxNivelProfundidad) )
//		{
//			if(vecinos.isEmpty())
//				break;
//			if(vecinos.contains(userID2)){
//				distancia = nivel;
//				break;
//			}
//			nivel++;
//			vecinos = getFriendsMyFriends(userID1, nivel);
//			
//			if (CollectionUtils.isEqualCollection(vecinos,vecinosAux))
//				break;
//			else
//				vecinosAux = new HashSet<Long>(vecinos);
//				
//				
//		}
//		if (nivel>maxNivelProfundidad)
//			distancia = nivel;
		
//		return distancia;
		
//		for(int i=1;i<nivel;i++){
//			
//			if (vecinos.contains(userID2)){
//				distancia = i;
//				break;
//			}
//		}
	}
	
	public Boolean areFriends(long userID1, long userID2) {
		//return (Integer) this.distanciaCaminoCortoSinPeso.getDistance(userID1, userID2);
		if(!this.grafo.containsVertex(userID1))
			return null;

		if(!this.grafo.containsVertex(userID2))
			return null;

		//Collection<Long> vecinos = this.grafo.getNeighbors(userID1);

		return this.grafo.isNeighbor(userID1,userID2);

	}

	public DataModel getDataModel() {
		
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
		cargarVecindario();
	}

	@Override
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

	@Override
	public FastIDSet getFriendsMyFriendsFastIDSet(long userID, int nivel) {
		if (null == getFriends(userID)){
			return null;
		}
		if( nivel == 1 )
			return  this.vecinosNivel1.get(userID);
		if( nivel == 2 )
			return this.vecinosNivel2.get(userID);
			
		return this.vecinosNivel3.get(userID);
	}



//	public long distancia getDistancia(long userID, long) {
//		grafo.
//		return ;
//	}

}
