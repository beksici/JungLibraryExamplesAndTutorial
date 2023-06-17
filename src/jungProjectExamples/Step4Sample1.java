package jungProjectExamples;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;

// görsel ayarlar 
public class Step4Sample1 {

	public static void main(String[] args) {
		/*
		 * Bir düğümün görsel özniteliklerini, dizeleri görüntülemeye benzer şekilde
		 * ayarlarsınız. BasicVisualizationServer<MyNode, MyEdge > nesne panelleri için
		 * her düğümü görsel özniteliklerle eşleyen bir harita ayarlayın. Eşleme,
		 * Transformer nesnesinin transform() yöntemiyle tanımlanır.
		 */

		/*
		 * Düğümden dolguya renk eşlemesi Transformer<MyNode,Paint> nesnesinde
		 * tanımlanır ve eşleme setVertexFillPaintTransformer() yönteminde ayarlanır.
		 */
		
		
		 Graph<MyNode,MyEdge> graph = new UndirectedSparseGraph<MyNode,MyEdge>();
	        MyNode n1 = new MyNode("n1");
	        MyNode n2 = new MyNode("n2");
	        MyNode n3 = new MyNode("n3");
	        graph.addEdge(new MyEdge("e1"), n1, n2);
	        graph.addEdge(new MyEdge("e2"), n2, n3);

	        Layout<MyNode,MyEdge> layout = new StaticLayout<MyNode,MyEdge>(graph);
	        layout.setLocation(n1, new Point2D.Double(100, 100));
	        layout.setLocation(n2, new Point2D.Double(200, 100));
	        layout.setLocation(n3, new Point2D.Double(150, 200));
	        
	        BasicVisualizationServer<MyNode,MyEdge> panel = new BasicVisualizationServer<MyNode,MyEdge>(layout, new Dimension(300, 300));
	        
	        
	        //Burada renk için Transformer kullanıyouruz------
	        Transformer<MyNode, Paint> nodeFillColor = new Transformer<MyNode, Paint>() {
	        	@Override
	        	public Paint transform(MyNode n) {
	        		return Color.BLUE;
	        	}
	        };
	        panel.getRenderContext().setVertexFillPaintTransformer(nodeFillColor);

	        //Her bir düğümün rengi farklı olsun dersen de ----
	        Transformer<MyNode, Paint> nodeFillColorr = new Transformer<MyNode, Paint>() {
	        	@Override
	        	public Paint transform(MyNode n) {
	        		int id = Integer.parseInt(n.label.substring(1));//düğüm için belirtilen dizeden bir tamsayı ("n1" durumunda 1) alınır. 
	        		return new Color(Color.HSBtoRGB(id / 3f, 0.7f, 1.0f));
	        	}
	        };
	        panel.getRenderContext().setVertexFillPaintTransformer(nodeFillColorr);
	        
	        
	        
	        // düğümün içini dolduran renge ek olarak, anahattın rengini de belirtebilirsiniz. Anahat rengi setVertexDrawPaintTransformer() yönteminde ayarlanır. Aşağıdaki örnek kenarlığı yeşil yapar.-----------
	        
	        Transformer<MyNode, Paint> nodeDrawColor = new Transformer<MyNode, Paint>() {
	        	@Override
	        	public Paint transform(MyNode n) {
	        		return Color.GREEN;
	        	}
	        };
	        panel.getRenderContext().setVertexDrawPaintTransformer(nodeDrawColor);
	        
	        
	        //Düğümlerin dolgu rengini ayarlamak için --------
	        //sarıdan maviye geçişli dolgu verir
	        panel.getRenderer().setVertexRenderer(new GradientVertexRenderer<MyNode,MyEdge>(Color.YELLOW, Color.BLUE, false));
	        
	        
	        
	        //Düğümlerin şekli için ----------
	        
	        Transformer<MyNode, Shape> nodeShapeTransformer = new Transformer<MyNode, Shape>() {
	        	@Override
	        	public Shape transform(MyNode n) {
	        		return new Rectangle(-15, -10, 30, 20); //20 yükseklik diğerlerigenişlik
	        	}
	        }; 
	        panel.getRenderContext().setVertexShapeTransformer(nodeShapeTransformer);
	        
	        
	        
	        //kenarların yani edge rengi ayarlamak için -------------
	        
	        Transformer<MyEdge, Paint> edgeColor = new Transformer<MyEdge, Paint>() {
	        	@Override
	        	public Paint transform(MyEdge e) {
	        		return Color.RED;
	        	}
	        };
	        panel.getRenderContext().setEdgeDrawPaintTransformer(edgeColor);
	        
	        
	        
	        
	        
	        //kenar şekli için mesela düz çizgi olur tırıklı olur kalın olur vs -----------
	        //Kenarın şekli setEdgeShapeTransformer() yönteminde ayarlanır.
	        
	        panel.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<MyNode,MyEdge>());
	        /* Düz çizgilere ek olarak, aşağıdaki şekiller de mevcuttur.
	         * BentLine: Çizgi
	         * CubicCurve: Kübik eğri
	         * Orthogonal: Ortogonal
	         * QuadCurve: Kuadratik eğri
	         * Kama: Oluşturucu <EdgeShape.Wedge>V,E(int width))*/
	        
	        
	        
	        
	        
	        
	        
	        //Kenar kalınlığı için ---------------------------
	        
	        /*Kenar kalınlığını, kenar için bir Kontur nesnesi belirterek ayarlarsınız.
	         *Kenardan Kontura eşlemesi Transformer<MyEdge,Stroke> nesnesinde tanımlanır ve eşleme setEdgeStrokeTransformer() yöntemiyle ayarlanır.
	         */
	        final Stroke edgeStroke = new BasicStroke(10.0f);        
	        Transformer<MyEdge, Stroke> edgeStrokeTransformer = new Transformer<MyEdge, Stroke>() {
	        	@Override
	        	public Stroke transform(MyEdge e) {
	        		return edgeStroke;
	        	}
	        };        
	        panel.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
	        
	        
	        
	        // Kenar kesikli çizgi atabilmek için dash 
	        
	        float dash[] = {5f};
	        //dash a farklı değerler verip deneyebilirsin çizgi kesiği için
	        /*
	         * float dash[] = {5f};
	         * float dash[] = {10f};
	         * float dash[] = {2f, 3f};
	         * float dash[] = {5f, 2f};
	         * float dash[] = {10f, 3f, 3f, 3f};*/
	        final Stroke edgeStroker = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);        
	        Transformer<MyEdge, Stroke> edgeStrokeTransformerr = new Transformer<MyEdge, Stroke>() {
	        	@Override
	        	public Stroke transform(MyEdge e) {
	        		return edgeStroker;
	        	}
	        };        
	        panel.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformerr);
	        
	        JFrame frame = new JFrame("Graph View: Blue Nodes");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(panel);
	        frame.pack();
	        frame.setVisible(true);
	    }
		
	}

//Gradation
//vv.getRenderer().setVertexRenderer(
//		new GradientVertexRenderer<Number,Number>(
//				Color.white, Color.red, 
//				Color.white, Color.blue,
//				vv.getPickedVertexState(),
//				false));


