import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MatchScheduler<Vertex extends Comparable<Vertex>> {

	
	private UndirectedAdjGraph<Vertex> graph = new UndirectedAdjGraph<Vertex>();
	
	private List<Vertex> orderedVertices;
	protected List<List<Vertex>> coloredList = new ArrayList<List<Vertex>>();

	
	
	
	public void orderVerticebyDegree(UndirectedAdjGraph<Vertex> graph){
		List<Vertex> vertices = graph.vertices();
		Collections.sort(vertices,
				(v1,v2)->((Integer) graph.outNeighbors(v2).size()).compareTo((Integer) graph.outNeighbors(v2).size()));
		
	}
	
	public void WelshAndPowel(UndirectedAdjGraph<Vertex> graph) {
		orderVerticebyDegree(graph);
		List<Vertex> colored=   new ArrayList<Vertex>();
		colored.addAll(orderedVertices);
		while(orderedVertices.size()>0) {
			List<Vertex> unAdjacent = new ArrayList<Vertex>();
			unAdjacent.addAll(colored);
			unAdjacent.removeAll(graph.outNeighbors(orderedVertices.get(0)));
			coloredList.add(unAdjacent);	
			colored.removeAll(unAdjacent);
			orderedVertices.remove(0);
		}
	}
	
}
