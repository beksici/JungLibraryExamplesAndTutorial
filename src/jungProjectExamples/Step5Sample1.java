package jungProjectExamples;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
//Fare işlemleri sürükleme yakınlaştırma vs
public class Step5Sample1 {

	 public static void main(String[] args) {
	    	Dimension viewArea = new Dimension(300, 300);
	        Graph<MyNode,MyEdge> graph = createGraph();
	        Layout<MyNode,MyEdge> layout = new FRLayout<MyNode,MyEdge>(graph, viewArea);
	        VisualizationViewer<MyNode,MyEdge> panel = new VisualizationViewer<MyNode,MyEdge>(layout, viewArea);
	        /*Shift e basıp mouse ile tıklarsan göndürebiliyorsun şekli
	         * yakınlaştırma ve uzaklaştırma yapabiliyorsun 
	         * istediğin yere taşıaybiliyorsun
	         * */
	        DefaultModalGraphMouse<MyNode,MyEdge> gm = new DefaultModalGraphMouse<MyNode,MyEdge>();
	        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);  // default
	        panel.setGraphMouse(gm); 
	        
  /*
	        
	        //--Düğüm yakalamak için sadece mode değiştirmek yeterli----
	        /* İstediğim düğümü sürükleyip bırakabiliyorum artık
	         * */
	        DefaultModalGraphMouse<Integer,Number> gm1 = new DefaultModalGraphMouse<Integer,Number>();
	        gm1.setMode(ModalGraphMouse.Mode.PICKING);
	        panel.setGraphMouse(gm1);
	        
	        
	        //--- Fare ile taşınan düğümğn rengi için -------
	        /**yakalanmadan önce kırmızı yakalandıktan sonra fareye mavi*/
	        Transformer<MyNode, Paint> pickedNodePaint = new PickableVertexPaintTransformer<MyNode>(panel.getPickedVertexState(), Color.RED, Color.BLUE);
	        panel.getRenderContext().setVertexFillPaintTransformer(pickedNodePaint);

	        
	        
	        // --- düğümlerin üzerine getirince fareyi ek açıklama getirme pop-up yani-----
	        /*
	         * setVertexToolTipTransformer() yönteminde düğümden dizeye eşlemeyi ayarlarsanız, fareyle düğümün üzerine geldiğinizde düğüme karşılık gelen dize görüntülenir.
	         * */
	        panel.setVertexToolTipTransformer(new ToStringLabeller<MyNode>());
	        /*
	         * kendimde açıklama(note) ekleyebilirim transformer ile şöyle ki*/
	        
	        Transformer<MyNode, String> pointedNodeNote = new Transformer<MyNode, String>() {
	        	@Override
	        	public String transform(MyNode n) {
	        		return "annotation of " + n.label;
	        	}       	
	        };
	        panel.setVertexToolTipTransformer(pointedNodeNote);
	        
	      
	        
	        
	        JFrame frame = new JFrame("Graph View: Mouse Operation");
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
