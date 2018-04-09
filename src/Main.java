import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {
	
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
			 System.out.println("Vertex "+key.id+":");
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
	public static void Affichage3(List<List<CVertex>> C) {
		int c=1;
		for(List<CVertex> SameColoredList : C ) {
		
			System.out.println("Color "+c+":");
			for(CVertex v : SameColoredList)
				System.out.print(" "+v.id);
			System.out.println();
			c++;
			}
	}
	

	public static void main(String[] args) {
		UndirectedAdjGraph<CVertex> G= GraphFactory.createGraphFromFile("Data/Tournament-data.txt");
		GraphFactory.createEdges(G);
		UndirectedAdjGraph<CVertex> G2= GraphFactory.createGraphFromFile("Data/Tournament-data.txt");
		GraphFactory.createEdges(G2);

		
		
		List<List<CVertex>> result = MatchScheduler.WelshAndPowel(G);
	
		
		Affichage3(result);
		boolean S=MatchScheduler.check(result);
		System.out.println();
		System.out.println(S);
		System.out.println();

		 result = MatchScheduler.WelshAndPowel2(G2);

		 
		 S=MatchScheduler.check(result);
		Affichage3(result);
		System.out.println();
		System.out.println(S);
		System.out.println();
		

	}

}


