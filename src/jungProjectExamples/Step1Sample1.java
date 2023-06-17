package jungProjectExamples;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class Step1Sample1 {

	public static void main(String[] args) {
		 Graph<Integer,Integer> graph = new UndirectedSparseGraph<Integer,Integer>();
	     //vertex köşedir nodedir yani
		 //edge kenarlık
//		 Graph<Integer,String> graph = new UndirectedSparseGraph<Integer,String>(); yapsaydık graph.addEdge("ali", 1, 2); 1 2ile 2 arası ali yazardı

		    graph.addVertex(1);
	        graph.addVertex(2);
	        graph.addVertex(3);
	        graph.addEdge(101, 1, 2);
	        graph.addEdge(102, 2, 3);
	        System.out.println("Graph G = " + graph.toString());

	        
	}

}
