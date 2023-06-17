package jungProjectExamples;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class Step2Sample1 {
//Random Layout kullanıcaz
	public static void main(String[] args) {
		Dimension viewArea = new Dimension(300, 300);
        Graph<MyNode,MyEdge> graph = createGraph();
        Layout<MyNode,MyEdge> layout = new RandomLayout<MyNode,MyEdge>(graph, viewArea);
        BasicVisualizationServer<MyNode,MyEdge> panel = new BasicVisualizationServer<MyNode,MyEdge>(layout, viewArea);

        JFrame frame = new JFrame("Graph View: Random Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
	}
	private static Graph<MyNode,MyEdge> createGraph() {
		Graph<MyNode,MyEdge> g = new UndirectedSparseGraph<MyNode,MyEdge>();
		MyNode n1 = new MyNode("n1");
		MyNode n2 = new MyNode("n2");
		MyNode n3 = new MyNode("n3");
		MyNode n4 = new MyNode("n4");
		MyNode n5 = new MyNode("n5");
		MyNode n6 = new MyNode("n6");
		MyNode n7 = new MyNode("n7");
		MyNode n8 = new MyNode("n8");
		g.addEdge(new MyEdge("e1"), n1, n2);
		g.addEdge(new MyEdge("e2"), n2, n3);
		g.addEdge(new MyEdge("e3"), n3, n4);
		g.addEdge(new MyEdge("e4"), n4, n1);
		g.addEdge(new MyEdge("e5"), n5, n6);
		g.addEdge(new MyEdge("e6"), n6, n7);
		g.addEdge(new MyEdge("e7"), n7, n8);
		g.addEdge(new MyEdge("e8"), n8, n5);
		g.addEdge(new MyEdge("e9"), n1, n5);
		g.addEdge(new MyEdge("e10"), n2, n6);
		g.addEdge(new MyEdge("e11"), n3, n7);
		g.addEdge(new MyEdge("e12"), n4, n8);
		return g;
	}
}
