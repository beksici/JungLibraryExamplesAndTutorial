package jungProjectExamples;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

//Etiketleri yani nodelerin adları yazmayı görücez
public class Step3Sample1 {

	public static void main(String[] args) {
		Graph<MyNode, MyEdge> graph = new UndirectedSparseGraph<MyNode, MyEdge>();
		MyNode n1 = new MyNode("n1");
		MyNode n2 = new MyNode("n2");
		MyNode n3 = new MyNode("n3");
		graph.addEdge(new MyEdge("e1"), n1, n2);
		graph.addEdge(new MyEdge("e1"), n2, n3);

		// Çok node olursa aşağıdakini kullanmak mantıklı
		// Layout<MyNode, MyEdge> layout = new FRLayout(graph,new Dimension(300,300));

		Layout<MyNode, MyEdge> layout = new StaticLayout<MyNode, MyEdge>(graph);
		layout.setLocation(n1, new Point2D.Double(100, 100));
		layout.setLocation(n2, new Point2D.Double(200, 100));
		layout.setLocation(n3, new Point2D.Double(150, 200));

		/*
		 * BasicVisualizationServer<MyNode ve MyEdge > nesne panelleri için etiketleme
		 * yöntemini ayarlar.
		 */
		BasicVisualizationServer<MyNode, MyEdge> panel = new BasicVisualizationServer<MyNode, MyEdge>(layout,
				new Dimension(300, 300));

		/*
		 * ToStringLabeller<V> sınıfı, Transformer><V,String arabirimini uygular ve dize
		 * gösterimini V sınıfı nesnelerle eşler. MyNode sınıfında, oluşturucuda
		 * ayarlanan dize etiketi toString() yönteminde döndürülür, böylece "n1" dizesi,
		 * etiketin "n1" olduğu düğümde olduğu gibi görüntülenir.
		 */

		panel.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyNode>());

		// eğer kendi tanımlı node laballeri göstermek istiyorsan---------
		/*
		 * Transformer sınıfı, JUNG15.2'da değil, org.apache.commons.collections0
		 * paketinde tanımlanır.
		 * 
		 * JUNG2.0, Transformer sınıfını yalnızca etiketleri görüntülemek için değil,
		 * aynı zamanda düğümü ve grafiğin kenarları için öznitelik değerlerini
		 * tanımlamak için de kullanır. Bu nedenle, yukarıdaki tanımlar her yerde
		 * kullanılır.
		 */
		Transformer<MyNode, String> labeller = new Transformer<MyNode, String>() {
			@Override
			public String transform(MyNode n) {
				return "node: " + n.label;
			}
		};

		panel.getRenderContext().setVertexLabelTransformer(labeller);

		// labelin yerini ayarlamak için--------------
		/*
		 * Aşağıdaki örnekte, etiket kısadır ve düğümün içine sığar, ancak etiket uzunsa
		 * düğümün soluna ve sağına doğru uzanır.
		 */
		panel.getRenderer().getVertexLabelRenderer().setPosition(Position.N);// merkezde oluyor nodeun ismi yani
																				// içinde oluyor
		/*
		 * CNTR'ye (ortada) ek olarak, üstten saat yönünde sekiz yön seçilebilir: N, NE,
		 * E, SE, S, SW, W ve NW. Örneğin, Position.N bunu düğümün en üstüne
		 * yerleştirir. Varsayılan değer sağ altta bulunan SE'dir. Bunlara ek olarak,
		 * AUTO da belirtilebilir. Otomatik olarak "iyi" düzenleyen bir şey gibi
		 * görünüyor. Örneğin, aynı grafik için Position.AUTO değerini belirtirseniz
		 * grafik şöyle görünür:
		 */

		JFrame frame = new JFrame("Graph View: Showing Node Labels");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
