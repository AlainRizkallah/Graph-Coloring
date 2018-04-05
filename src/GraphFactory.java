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

	private static Map<Integer,List<CVertex>> playersMatch = new LinkedHashMap<Integer,List<CVertex>>();
	private static List<Integer> testList = new ArrayList<Integer>();
	
	public static UndirectedAdjGraph<CVertex> createGraphFromFile(String path) {
		UndirectedAdjGraph<CVertex> G = new UndirectedAdjGraph<CVertex>();
		
	try (Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
		int i=0;
		while (scan.hasNextLine()) {
			 String S=scan.nextLine();
			 String[] tokens = S.split("J-| vs J-| J-");
			 ArrayList<Integer> A =parseStringListToIntList(tokens);
			
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
	
	private static ArrayList<Integer> parseStringListToIntList(String[] stringList){
		ArrayList<Integer> result = new ArrayList<>();
		 for (String element : stringList) {
			 if (!element.equals("")) {
		        result.add(Integer.parseInt(element));
			 }
		    }
		 return result;
	}
	
	public static UndirectedAdjGraph<CVertex> createGraphFromFile2(String path) {
		UndirectedAdjGraph<CVertex> G = new UndirectedAdjGraph<CVertex>();
		
	try (Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
		int i=0;
		while (scan.hasNextLine()) {
			 String S=scan.nextLine();
			 String[] tokens = S.split("J-| vs J-| J-");
			 ArrayList<Integer> playerList =parseStringListToIntList(tokens);
			 CVertex vertex = new CVertex(playerList,i);
			 

			
			i++;
			if(!playerList.isEmpty())
				G.addVertex(vertex);
		}
	} catch(IOException e) {
		e.printStackTrace();
	}

		return G;
}
	public static void createEdges2(UndirectedAdjGraph<CVertex> G) {
		int count1 = 0;
		Map<CVertex, List<CVertex>> ADJ = G.adjacency;
		for( CVertex vertex:ADJ.keySet()) {
			 ArrayList<Integer> playerList = vertex.match;
			 for (int p:playerList) {
				 testList.add(p);
				 List<CVertex> playIn= playersMatch.get(p);
				 if (playIn!=null) {

					 for (CVertex v:playIn) {
						 count1++;
						 if (vertex.id!=v.id) {
							 G.addEdge(vertex,v);
						 }
					 }
					 playIn.add(vertex);
					 
				 }else {
					 count1++;
					 playIn = new ArrayList<CVertex>();
					 playIn.add(vertex);
					 playersMatch.put(p,playIn);
				 }				 
			 }
		}
		System.out.println("CreateEdges2 operations: "+count1);
	}
	public static void createEdges(UndirectedAdjGraph<CVertex> G ) {
		int count1 = 0;
		Map<CVertex, List<CVertex>> ADJ = G.adjacency;
		
		 for(Entry<CVertex, List<CVertex>> entry : ADJ.entrySet()) {
			 CVertex key = entry.getKey();
			 
			 for(Entry<CVertex, List<CVertex>> entry2 : ADJ.entrySet()) {
				 CVertex key2 = entry2.getKey();
				 if (!key2.equals(key)) {
					 
					 for(Integer i : key.match) {
						 count1++;
							 if ( key2.match.contains(i)) {
								 G.addEdge(key, key2);
							 }
							 
						 
					 }
				 }else {count1++;}
			 }
			 
			}
		 System.out.println("CreateEdges operations: "+count1);
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
