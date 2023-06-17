package jungProjectExamples;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;

public class Step1Sample3 {

	public static void main(String[] args) {
		Graph<MyNode, MyEdge> graph = new UndirectedSparseGraph<MyNode, MyEdge>();

		MyNode n1 = new MyNode("n1");
		MyNode n2 = new MyNode("n2");
		MyNode n3 = new MyNode("n3");
		graph.addVertex(n1);
		graph.addVertex(n2);
		graph.addVertex(n3);
		MyEdge e1 = new MyEdge("e1");
		MyEdge e2 = new MyEdge("e1");
		graph.addEdge(e1, n1, n2);
		graph.addEdge(e2, n2, n3);
		System.out.println("Graph G = " + graph.toString());

//Pairi anlamadım
		/*
		 * etEndpoints() yöntemi, bağımsız değişken olarak bir kenar alır ve ona bağlı
		 * bir çift düğüm döndürür. getFirst() ve getSecond() yöntemleri bir demetten
		 * bir öğe (bu durumda, MyNode örneği) döndürür.
		 */
		Pair<MyNode> pair = graph.getEndpoints(e1);
		MyNode m1 = pair.getFirst();
		MyNode m2 = pair.getSecond();
		System.out.println(e1.label + " = {" + m1 + ", " + m2 + "}");

	}

}
