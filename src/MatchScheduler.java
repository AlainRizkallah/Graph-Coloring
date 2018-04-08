import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MatchScheduler<Vertex extends Comparable<Vertex>> {

	
	private UndirectedAdjGraph<Vertex> graph = new UndirectedAdjGraph<Vertex>();
	
	private List<Vertex> orderedVertices;
	protected List<List<Vertex>> coloredList = new ArrayList<List<Vertex>>();

	
	
	
	public void orderVerticebyDegree(UndirectedAdjGraph<Vertex> graph){
		List<Vertex> vertices = graph.vertices();
		Collections.sort(vertices,
				(v1,v2)->((Integer) graph.outNeighbors(v2).size()).compareTo((Integer) graph.outNeighbors(v2).size()));
		
	}
	
	public static void WelshAndPowel(UndirectedAdjGraph<CVertex> graph) {
		Map<CVertex, List<CVertex>> sortedGraph = GraphFactory.SortByDegree(graph);
		List<CVertex> orderedVertices = new ArrayList<>();
		List<CVertex> coloredVertices = new ArrayList<>();
		for( CVertex vertex:sortedGraph.keySet())
		orderedVertices.add(vertex);
		
		int c=1;
		
		while(coloredVertices.size()!=graph.n) {
			
			for(CVertex v:orderedVertices) {
				List<CVertex> Adjacent=   new ArrayList<CVertex>();
				if(v.color==0) {
					v.color=c;
					coloredVertices.add(v);
					for(CVertex V: graph.inNeighbors(v))
						Adjacent.add(V);
					
					for(CVertex u:orderedVertices) {
						if(u.color==0 && !Adjacent.contains(u)) {
							u.color=c;
							coloredVertices.add(u);
							for(CVertex U: graph.inNeighbors(u))
								Adjacent.add(U);
						}
					}
					c++;
				}
				
			}
			
			
		}
	
		
		
	}
	public static List<List<CVertex>> ListofColors(UndirectedAdjGraph<CVertex> graph){
		List<List<CVertex>> List = new ArrayList<>();
		int compteur=0;
		int couleur=0;
		while(compteur!=graph.n) {
			couleur++;
			List<CVertex> SameColor=new ArrayList<>();
			for(Entry<CVertex, List<CVertex>> entry : graph.adjacency.entrySet()) {
				 CVertex key = entry.getKey();
				 if(key.color==couleur) {
					 SameColor.add(key);
				 compteur++;}
		}
			if(!SameColor.isEmpty())
			List.add(SameColor);
		}
		return(List);
	}
	
	public static boolean Check (UndirectedAdjGraph<CVertex> graph) {
		List<List<CVertex>> List=ListofColors(graph);
		
		
		for(int i=1;i<1000;i++) {
		for(List<CVertex> C: List) {
			int mark=0;
			for(CVertex V: C) {
				if (V.match.contains(i))
					mark++;
			}
			if(mark>1)
				return false;
			
		}
		}
		return true;
	}
	
}
