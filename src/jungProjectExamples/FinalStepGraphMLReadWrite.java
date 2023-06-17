package jungProjectExamples;
//http://graphml.graphdrawing.org/ graphml hakkında bilgi için
//https://www.cs.tsukuba.ac.jp/~misue/open/tutorial/jung2/step7.html  dosya okuma yazma işlemleri için
//burada işimizi görecek png olarak dışa aktarmayı yazacağız




/*mesela bir xml dosyası örneği
 * 
 * <?xml version="1.0" encoding="UTF-8"?>
<graphml xmlns="http://graphml.graphdrawing.org/xmlns">
  <key id="label" for="node" attr.name="label" attr.type="string"/>
  <key id="count" for="edge" attr.name="count" attr.type="int"/>
  <graph edgedefault="undirected" id="G">
    <node id ="a190292">
      <data key="label">Kazuo</data>
    </node>
    <node id ="a57781">
      <data key="label">Kozo</data>
    </node>

    <edge id="e1" source="a57781" target="a190292">
      <data key="count">6</data>
    </edge>
    <edge id="e2" source="a137438" target="a190292">
      <data key="count">1</data>
    </edge>

  </graph></graphml>
 * 
 * */


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileFilter;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class FinalStepGraphMLReadWrite extends JFrame{


	BasicVisualizationServer<MyNode,MyEdge> panel;
	
	public FinalStepGraphMLReadWrite() {
    	Dimension viewArea = new Dimension(300, 300);
		Graph<MyNode,MyEdge> graph = createGraph();
		Layout<MyNode,MyEdge> layout = new FRLayout<MyNode,MyEdge>(graph, viewArea);
		panel = new BasicVisualizationServer<MyNode,MyEdge>(layout, viewArea);
		this.getContentPane().add(panel);
	}	

    public static void main(String[] args) {
        final FinalStepGraphMLReadWrite frame = new FinalStepGraphMLReadWrite();
        
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Save Image ...") {
        	@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser  = new JFileChooser();
             //setfilefilter ben ekledim sadece png yazması için ama olmadı
                fileChooser.setFileFilter(new FileFilter() {

             	   public String getDescription() {
             	       return "PNG Images (*.png)";
             	   }

             	   public boolean accept(File f) {
             	       if (f.isDirectory()) {
             	           return true;
             	       } else {
             	           String filename = f.getName().toLowerCase();
             	           return filename.endsWith(".png") ;
             	       }
             	   }
             	});
                int option = fileChooser.showSaveDialog(frame);
                if(option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    
                    frame.writePNGImage(file);
                }
            }});

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setTitle("Graph View: Save Image File");
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    protected void writePNGImage(File file) {
        BufferedImage bufferedImage = 
        	new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        panel.paint(g2);
        g2.dispose();
        
        try {
        	ImageIO.write(bufferedImage, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected static Graph<MyNode,MyEdge> createGraph() {
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
