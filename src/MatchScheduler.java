import java.util.ArrayList;
import java.util.Collections;
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


	
	public static  List<List<CVertex>> WelshAndPowel(UndirectedAdjGraph<CVertex> graph) {
		
		
		List<List<CVertex>> coloredList = new ArrayList<List<CVertex>>();
		LinkedList<CVertex> unColoredVertices = new LinkedList<CVertex>();
		unColoredVertices.addAll(orderVerticebyDegree(graph));
		int count = 0;
		int color=0;
		while(unColoredVertices.size()>0) {
			color++;
			CVertex vertex = unColoredVertices.poll();
			vertex.color = color;
			LinkedList<CVertex> unAdjacent = new LinkedList<CVertex>();
			unAdjacent.add(vertex);
			LinkedList<CVertex> toRemove = new LinkedList<CVertex>();
			for (int i=0;i<unColoredVertices.size();i++) {
				CVertex v = unColoredVertices.get(i);
				boolean unlinked = true;
				for (CVertex v2:unAdjacent) {
					count++;
					if (graph.inNeighbors(v2).contains(v)) {
						unlinked = false;
						break;
					}
				}
				if (unlinked) {
					unAdjacent.add(v);
					vertex.color = color;
					toRemove.add(v);
					count++;
				}
			}
			unColoredVertices.removeAll(toRemove);
			coloredList.add(unAdjacent);		
			
				
			
		}
		System.out.println("Methode 1 : "+count+ " operations ");
		graph.colored = coloredList;
		return coloredList;
				
	}
	
	public static List<List<CVertex>> WelshAndPowel2(UndirectedAdjGraph<CVertex> graph) {
		Map<CVertex, List<CVertex>> sortedGraph = GraphFactory.SortByDegree(graph);
		List<CVertex> coloredVertices = new ArrayList<>();
		
		List<CVertex> orderedVertices = new ArrayList<>();
		for( CVertex vertex:sortedGraph.keySet())
		orderedVertices.add(vertex);
		
				int count = 0;
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
								count++;
								if(u.color==0 && !Adjacent.contains(u)) {
									u.color=c;
									coloredVertices.add(u);
									for(CVertex U: graph.inNeighbors(u)) {
										Adjacent.add(U);
										count++;
									}
										
								}
							}
							c++;
						}
						
					}
					
					
				}
				
			graph.colored = ListofColors(graph);
			System.out.println("Methode 2 : "+count+ " operations ");
				return graph.colored;
				
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
	


	
	public static boolean check (List<List<CVertex>> List) {

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
