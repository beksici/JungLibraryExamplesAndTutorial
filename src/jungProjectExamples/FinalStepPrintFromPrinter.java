package jungProjectExamples;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

@SuppressWarnings("serial")
public class FinalStepPrintFromPrinter extends JFrame {
	
	BasicVisualizationServer<MyNode,MyEdge> panel;
	
	class GraphImage implements Printable {
	    @Override
	    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	        if (pageIndex > 0) {
	            return (Printable.NO_SUCH_PAGE);
	        } else {
	            Graphics2D g2 = (Graphics2D) graphics;
	            panel.setDoubleBuffered(false);
	            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	            panel.paint(g2);
	            panel.setDoubleBuffered(true);
	            return (Printable.PAGE_EXISTS);
	        }
	    }
	}
	
	public FinalStepPrintFromPrinter() {
    	Dimension viewArea = new Dimension(300, 300);
		Graph<MyNode,MyEdge> graph = createGraph();
		Layout<MyNode,MyEdge> layout = new FRLayout<MyNode,MyEdge>(graph, viewArea);
		panel = new BasicVisualizationServer<MyNode,MyEdge>(layout, viewArea);
		this.getContentPane().add(panel);
	}

    public static void main(String[] args) {
        final FinalStepPrintFromPrinter frame = new FinalStepPrintFromPrinter();
        
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Print Image ...") {
        	@Override
            public void actionPerformed(ActionEvent e) {
                    PrinterJob printJob = PrinterJob.getPrinterJob();
                    printJob.setPrintable(frame.new GraphImage());
                    if (printJob.printDialog()) {
                        try {
                            printJob.print();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            }});
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setTitle("Graph View: Print Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
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