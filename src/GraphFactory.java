import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class GraphFactory{

	public static UndirectedAdjGraph<CVertex> createGraphFromFile(String path) {
		UndirectedAdjGraph<CVertex> G = new UndirectedAdjGraph<CVertex>();
		
	try (Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
		int i=0;
		while (scan.hasNextLine()) {
			ArrayList<Integer> A = new ArrayList<>();
			 String S=scan.nextLine();
			 String[] tokens = S.split("J-| vs J-| J-");
			 for (String token : tokens) {
				 if (!token.equals("")) {
			        A.add(Integer.parseInt(token));
				 }
			    }
			
			
			CVertex u = new CVertex(A,i);
			i++;
			if(!A.isEmpty())
				G.addVertex(u);
		}
	} catch(IOException e) {
		e.printStackTrace();
	}
		return G;
}
	public static void createEdges(UndirectedAdjGraph<CVertex> G ) {
		Map<CVertex, List<CVertex>> ADJ = G.adjacency;
		
		 for(Entry<CVertex, List<CVertex>> entry : ADJ.entrySet()) {
			 CVertex key = entry.getKey();
			 
			 for(Entry<CVertex, List<CVertex>> entry2 : ADJ.entrySet()) {
				 CVertex key2 = entry2.getKey();
				 if (!key2.equals(key)) {
					 
					 for(Integer i : key.match) {
						
							 if ( key2.match.contains(i)) {
								 G.addEdge(key, key2);
							 }
							 
						 
					 }
					 
					 
					 
				 }
			 }
			 
			}
	}
	public static Map<CVertex, List<CVertex>> SortByDegree (UndirectedAdjGraph<CVertex> G ){
		
		Map<CVertex, List<CVertex>> sorted = G.adjacency.entrySet().stream()
		        .sorted(comparingInt(e->((Entry<CVertex, List<CVertex>>) e).getValue().size()).reversed())
		        .collect(toMap(
		                Map.Entry::getKey,
		                Map.Entry::getValue,
		                (a,b) -> {throw new AssertionError();},
		                LinkedHashMap::new
		        )); 
		return sorted;
	}
	
}
