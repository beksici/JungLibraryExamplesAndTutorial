package jungProjectExamples;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class Step1Sample4 {

	public static void main(String[] args) {
		/*
		 * Grafikleri temsil eden arayüz Arayüzün alt arayüzleri olarak Graph<V,E>,
		 * yönlendirilmemiş grafikleri temsil eden UndirectedGraph <V,E> ve
		 * yönlendirilmiş grafikleri temsil eden DirectedGraph <V,E> sağlanır. Ek
		 * olarak, bir K-parçası grafiğini temsil eden KPartiteGraph <V,E> (düğümlerin K
		 * alt kümelerine bölündüğü ve her alt kümedeki düğümleri birbirine bağlayan
		 * kenarların olmadığı bir grafik), Grafik <V,E> arabiriminin bir alt arabirimi
		 * olarak da sağlanır. Buna ek olarak, DirectedGraph <V,E> ormanları alt
		 * arabirimler olarak temsil eden Orman<V,E> ve alt arabirimler olarak
		 * yönlendirilmiş (köklü) ağaçları temsil eden Ağaç<V,E> vardır.
		 * 
		 * Grafik Uygulaması Yukarıdaki arayüzleri uygulayan birkaç sınıf da
		 * sağlanmıştır.
		 * 
		 * UndirectedGraph<V,E> Arayüz Uygulaması UndirectedSparseGraph<V,E>: Nispeten
		 * az kenarlı grafikler için uygun bir uygulama.
		 * UndirectedSparseMultigraph<V,E>: Paralel kenarlara izin verir.
		 * UndirectedOrderedSparseMultigraph<V,E>: Düğümler ve kenarlar oluşturma
		 * zamanına göre sıralanır. DirectedGraph<V,E> Arayüz Uygulaması
		 * DirectedSparseGraph<V,E>: Nispeten az kenarlı grafikler için uygun bir
		 * uygulama. DirectedSparseMultigraph <V,E>: Paralel kenarlara izin verir.
		 * DirectedOrderedSparseMultigraph<V,E>: Düğümler ve kenarlar oluşturma zamanına
		 * göre sıralanır. Orman<V,E> Arayüz Uygulaması DelegateForest<V,E> Tree<V,E>
		 * Arayüzünün Uygulanması DelegateTree<V,E> OrderedKAryTree<V,E>: K-dakika
		 * ağacı。 K, constructor parametresinde belirtilir.
		 */
		Graph<MyNode, MyEdge> graph = new UndirectedSparseGraph <MyNode, MyEdge>();
		MyNode n1 = new MyNode("n1");
		MyNode n2 = new MyNode("n2");
		MyNode n3 = new MyNode("n3");
		graph.addVertex(n1);
		graph.addVertex(n2);
		graph.addVertex(n3);
		graph.addEdge(new MyEdge("e1"), n1, n2);
		graph.addEdge(new MyEdge("e2"), n2, n3);
		//graph.addEdge(new MyEdge("e3"), n1, n3);
		Layout<MyNode, MyEdge> layout = new StaticLayout<MyNode, MyEdge>(graph);
		// düğüm konumları
		layout.setLocation(n1, new Point2D.Double(100, 100));
		layout.setLocation(n2, new Point2D.Double(200, 100));
		layout.setLocation(n3, new Point2D.Double(150, 200));

		BasicVisualizationServer<MyNode, MyEdge> panel = new BasicVisualizationServer<MyNode, MyEdge>(layout,
				new Dimension(300, 300));

		JFrame frame = new JFrame("Graph View: Manual Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
