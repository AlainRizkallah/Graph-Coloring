import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class main {
	
	public static void Affichage(UndirectedAdjGraph<CVertex> G) {
		 
		 Map<CVertex, List<CVertex>> ADJ = G.adjacency;
		 for(Entry<CVertex, List<CVertex>> entry : ADJ.entrySet()) {
			 
			 CVertex key = entry.getKey();
			 List<CVertex> Value = entry.getValue();
			 System.out.println("Vertex "+key.id);
			 for(Integer i : key.match) {
				 System.out.print(i + " \t");
				 }
			 System.out.print(" Neigbhours: ");
			 for(CVertex v: Value)
				 System.out.print(" "+v.id);
			 System.out.println();
			}
		 System.out.println();
	}
	public static void Affichage2(Map<CVertex, List<CVertex>> S ) {
		 for(Entry<CVertex, List<CVertex>> entry : S.entrySet()) {
			 
			 CVertex key = entry.getKey();
			 List<CVertex> Value = entry.getValue();
			 System.out.println("Vertex "+key.id);
			 for(Integer i : key.match) {
				 System.out.print(i + " \t");
				 }
			 System.out.print(" Neigbhours: ");
			 for(CVertex v: Value)
				 System.out.print(" \t"+v.id);
			 System.out.println();
			}
		 System.out.println();
	}

	public static void main(String[] args) {
		UndirectedAdjGraph<CVertex> G= GraphFactory.createGraphFromFile("C:\\Users\\Alain\\Desktop\\Ecole\\ii2415-tc4\\src\\Tournament-data.txt");
		GraphFactory.createEdges(G);
		//Affichage(G);
		Affichage2(GraphFactory.SortByDegree(G));
		
		
	}

}
