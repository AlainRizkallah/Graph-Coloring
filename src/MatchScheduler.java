import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MatchScheduler<Vertex extends Comparable<Vertex>> {

	
	private UndirectedAdjGraph<Vertex> graph = new UndirectedAdjGraph<Vertex>();
	
//	protected  List<List<Vertex>> coloredList = new ArrayList<List<Vertex>>();

	
	
	
	public static  <Vertex extends Comparable<Vertex>> List<Vertex> orderVerticebyDegree(UndirectedAdjGraph<Vertex> graph){
		List<Vertex> vertices = graph.vertices();
		Collections.sort(vertices,
				(v1,v2)->(
						(Integer) graph.outNeighbors(v2).size()).compareTo((Integer) graph.outNeighbors(v1).size())
				);
		return vertices;
	}
	
	public static <Vertex extends Comparable<Vertex>> void testVertexOrder(UndirectedAdjGraph<Vertex> graph) {
	for (Vertex v: MatchScheduler.orderVerticebyDegree(graph)) {
			System.out.println(graph.outNeighbors(v).size());
		}
	}
	
	public static List<List<CVertex>> WelshAndPowel(Map<CVertex, List<CVertex>> sortedGraph) {
		List<CVertex> orderedVertices = new ArrayList<>();
		List<List<CVertex>> coloredList = new ArrayList<List<CVertex>>();
		for( CVertex vertex:sortedGraph.keySet())
		orderedVertices.add(vertex);
		List<CVertex> colored=   new ArrayList<CVertex>();
		colored.addAll(orderedVertices);
		while(orderedVertices.size()>0) {
			List<CVertex> unAdjacent = new ArrayList<CVertex>();
			unAdjacent.addAll(colored);
			unAdjacent.removeAll(sortedGraph.get(orderedVertices.get(0)));
			if(!unAdjacent.isEmpty())
			coloredList.add(unAdjacent);	
			colored.removeAll(unAdjacent);
			orderedVertices.remove(0);
		}
		return(coloredList);
	}
	
	
	public static <Vertex extends Comparable<Vertex>> List<List<Vertex>> WelshAndPowel(UndirectedAdjGraph<Vertex> graph) {
		
		List<Vertex> unColoredVertices = new ArrayList<Vertex>();
		unColoredVertices.addAll(orderVerticebyDegree(graph));
		

		List<Vertex> colored=   new ArrayList<Vertex>();
		
		List<List<Vertex>> coloredList = new ArrayList<List<Vertex>>();
		
		while(unColoredVertices.size()>0) {

				List<Vertex> unAdjacent = new ArrayList<Vertex>();

				unAdjacent.addAll(unColoredVertices);
				unAdjacent.removeAll(graph.outNeighbors(unColoredVertices.get(0)));
				coloredList.add(unAdjacent);	
				colored.addAll(unAdjacent);

				
			
				unColoredVertices.removeAll(unAdjacent);
			
		}
		return coloredList;
				
	}
	
	public static boolean Check (List<List<CVertex>> List) {
		for(int i=1;i<1000;i++) {
			int count=0;
		for(List<CVertex> C: List) {
			count++;
			int mark=0;
			for(CVertex V: C) {
				
				if (V.match.contains(i)) {
					mark++;
					System.out.println(i+" "+V.match +" "+V);
				}
					
				if(mark>1)
					return false;
			}
			
		if(mark>1)
				return false;
			
		}
		}
		return true;
	}
	
}
