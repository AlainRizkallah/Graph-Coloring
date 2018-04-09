import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import java.util.Map.Entry;


public class MatchScheduler<Vertex extends Comparable<Vertex>> {

		
	public static  <Vertex extends Comparable<Vertex>> List<Vertex> orderVerticebyDegree(UndirectedAdjGraph<Vertex> graph){
		List<Vertex> vertices = graph.vertices();
		Collections.sort(vertices,
				(v1,v2)->(
						(Integer) graph.outNeighbors(v2).size()).compareTo((Integer) graph.outNeighbors(v1).size())
				);
		return vertices;
	}


	
	public static <Vertex extends Comparable<Vertex>> List<List<Vertex>> WelshAndPowel(UndirectedAdjGraph<Vertex> graph) {
		
		List<Vertex> orderedList = new ArrayList<Vertex>();
		orderedList.addAll(orderVerticebyDegree(graph));
		
		List<List<Vertex>> coloredList = new ArrayList<List<Vertex>>();
		
		LinkedList<Vertex> unColoredVertices = new LinkedList<Vertex>();
		unColoredVertices.addAll(orderedList);
		
		
		int color=0;
		while(unColoredVertices.size()>0) {
			color++;
			Vertex vertex = unColoredVertices.poll();
			LinkedList<Vertex> unAdjacent = new LinkedList<Vertex>();
			unAdjacent.add(vertex);
			
			for (int i=0;i<unColoredVertices.size();i++) {
				Vertex v = unColoredVertices.get(i);
				boolean unlinked = true;
				for (Vertex v2:unAdjacent) {
					if (graph.inNeighbors(v2).contains(v)) {
						unlinked = false;
						break;
					}
				}
				if (unlinked) {
					unAdjacent.add(v);
					
					unColoredVertices.remove(v);
				}
			}
			
			coloredList.add(unAdjacent);		
			
				
			
		}
		return coloredList;
				
	}
	
	public static void WelshAndPowel2(UndirectedAdjGraph<CVertex> graph) {
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
	
	public static boolean Check (List<List<CVertex>> List) {

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
