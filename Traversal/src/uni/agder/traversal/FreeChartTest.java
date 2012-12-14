package uni.agder.traversal;
import java.awt.BasicStroke;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FreeChartTest {
	/**
	* The starting point for the demo.
	*
	* @param args ignored.
	*/
	/*public static void main(String[] args) {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Category 1", 43.2);
		data.setValue("Category 2", 27.9);
		data.setValue("Category 3", 79.5);
		// create a chart...
		
		//JFreeChart chart = ChartFactory.createLineChart3D(title, categoryAxisLabel, valueAxisLabel, dataset, orientation, legend, tooltips, urls)
		JFreeChart chart = ChartFactory.createPieChart(
			"Sample Pie Chart",
			data,
			true, // legend?
			true, // tooltips?
			false // URLs?
		);
		// create and display a frame...
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);
	}*/
	
	public FreeChartTest()
	{
		
	}
	
	public void createLineChartFromSurivers(HashMap<Integer, Integer> antSystemSurvivors, HashMap<Integer, Integer> randomSurvivors, HashMap<Integer, Integer> bruteForceSurvivors, int numberOfTimesteps)
	{
		XYSeries antSystem = new XYSeries("Ant Colony Optimization");
		XYSeries random = new XYSeries("Random");
		XYSeries bruteForce = new XYSeries("Brute force");
		
		addNumbersToSeries(antSystem, antSystemSurvivors, numberOfTimesteps);
		addNumbersToSeries(random, randomSurvivors, numberOfTimesteps);
		addNumbersToSeries(bruteForce, bruteForceSurvivors, numberOfTimesteps);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		dataset.addSeries(bruteForce);
		dataset.addSeries(random);
		dataset.addSeries(antSystem);
		
		
		
		JFreeChart chart = ChartFactory.createXYLineChart("Survivors", "Ants", "Survivors", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
      /*  final Shape[] shapes = new Shape[3];
        int[] xpoints;
        int[] ypoints;

        // right-pointing triangle
        xpoints = new int[] {-3, 3, -3};
        ypoints = new int[] {-3, 0, 3};
        shapes[0] = new Polygon(xpoints, ypoints, 3);

        // vertical rectangle
        shapes[1] = new Rectangle2D.Double(-2, -3, 3, 6);

        // left-pointing triangle
        xpoints = new int[] {-3, 3, 3};
        ypoints = new int[] {0, -3, 3};
        shapes[2] = new Polygon(xpoints, ypoints, 3);
		
		
		final DrawingSupplier supplier = new DefaultDrawingSupplier(
	            DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
	            shapes
	        );
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		
		plot.setDrawingSupplier(supplier);

		plot.getRenderer().setSeriesStroke(
	            0, 
	            new BasicStroke(
	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
	                1.0f, new float[] {10.0f, 6.0f}, 0.0f
	            )
	        );
		plot.getRenderer().setSeriesStroke(
	            1, 
	            new BasicStroke(
	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
	                1.0f, new float[] {10.0f, 6.0f}, 0.0f
	            )
	        );*/
		
		
		ChartFrame frame = new ChartFrame("Data", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	private XYSeries addNumbersToSeries(XYSeries theSelectedOne, HashMap<Integer, Integer> theNumbers, int numberOfTimeSteps)
	{
		for(int i = 1; i<numberOfTimeSteps; i++)
		{
			theSelectedOne.add(i, theNumbers.get(i));
		}
		return theSelectedOne;
	}
	private XYSeries addNumbersToSeries(XYSeries theSelectedOne, int surivers, int numberOfTimeSteps)
	{
		for(int i = 1; i<numberOfTimeSteps; i++)
		{
			theSelectedOne.add(i, surivers);
		}
		return theSelectedOne;
	}
}
