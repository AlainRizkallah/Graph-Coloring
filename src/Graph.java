

import java.util.List;


/**
 * This class is an Abstract Graph. 
 * Types of Vertices (V) and Edges (E) are not defined by default. 
 * The only constraints on theses types are that Vertices must be Comparable ie. 
 * there must be a way to order vertices with one another. This is done through the use of the Comparable interface.
 *  
 * 
 * @author slefebvr
 *
 * @param <V>
 * @param <E>
 */
public abstract class Graph<Vertex extends Comparable<Vertex>> {
	
	protected int n;
	protected int m;
	
	
	public Graph(int nbVertices, int nbEdges) {
		this.n= nbVertices;
		this.m = nbEdges;
	}
	
	
	/**
	 * Initializes an empty graph
	 */
	public Graph() {
		this(0,0);
	}
	
	public int order() {
		return n;
	}
	
	public int size() {
		return m;
	}

	/**
	 * Adds the corresponding vertex if needed
	 * @param e
	 */
	public abstract void addEdge(Vertex s, Vertex t);
	
	/**
	 * Adds a vertex to the graph. On success n is increased
	 * @param v
	 */
	public abstract void addVertex(Vertex v);
	
	/**
	 * 
	 * @return the list of the graph vertices
	 */
	public abstract List<Vertex> vertices();
		
	/** 
	 * Return the list of v's neighbors
	 * @param v
	 */
	public abstract List<Vertex> inNeighbors(Vertex v);
	public abstract List<Vertex> outNeighbors(Vertex v);
		
	/**
	 * Returns the inbound degree of node v
	 * in case of undirected graph should return the same as outDegree
	 * @param v
	 * @return inbound degree of vertex v
	 */
	public abstract int inDegree(Vertex v);
	public abstract int outDegree(Vertex v);

}
