import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MatchScheduler<Vertex extends Comparable<Vertex>> {

	
	private UndirectedAdjGraph<Vertex> graph = new UndirectedAdjGraph<Vertex>();
	
	private List<Vertex> orderedVertices;
	protected List<List<Vertex>> coloredList = new ArrayList<List<Vertex>>();

	
	
	
	public void orderVerticebyDegree(UndirectedAdjGraph<Vertex> graph){
		List<Vertex> vertices = graph.vertices();
		Collections.sort(vertices,
				(v1,v2)->((Integer) graph.outNeighbors(v2).size()).compareTo((Integer) graph.outNeighbors(v2).size()));
		
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
	
}
