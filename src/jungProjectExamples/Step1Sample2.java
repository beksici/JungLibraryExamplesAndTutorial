package jungProjectExamples;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class Step1Sample2 {

	public static void main(String[] args) {
		 Graph<String,String> graph = new UndirectedSparseGraph<String,String>();
	        graph.addVertex("n1");
	        graph.addVertex("n2");
	        graph.addVertex("n3");
	        graph.addEdge("e1", "n1", "n2");
	        graph.addEdge("e2", "n2", "n3");
	        System.out.println("Graph G = " + graph.toString());
	        
	        /*
	         * 
	         *  Graph<Integer,String> graph = new UndirectedSparseGraph<Integer,String>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge("e1", 1, 2);
        graph.addEdge("e2", 2, 3);
        System.out.println("Graph G = " + graph.toString());        
	         */

	}

}
