package jungProjectExamples;

import java.awt.Dimension;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.util.IterativeContext;
import edu.uci.ics.jung.graph.Graph;

public class RandomLayout2<V,E> extends AbstractLayout<V,E> implements IterativeContext {

    public RandomLayout2(Graph<V,E> g) {
    	super(g);
    }
    
    public RandomLayout2(Graph<V,E> g, Dimension d) {
    	super(g);
    	setSize(d);
    }

    @Override
    public void initialize() {
    	layoutNodes();
    }

    @Override
	public void reset() {
	}
    
    @Override
    public void step() {
    	layoutNodes();
    }

    @Override
    public boolean done() {
    	return false;
    }
	
    protected void layoutNodes() {
    	int clearance = 20;
    	
        Dimension d = getSize();
        int width = d.width - clearance * 2;
        int height = d.height - clearance * 2;

        for (V v : getGraph().getVertices()) {
            if (isLocked(v)) continue;
            double x = Math.random() * width + clearance;
            double y = Math.random() * height + clearance;
            transform(v).setLocation(x, y);
        }
    }

}
