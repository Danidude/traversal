package uni.agder.traversal;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.Dataset;
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
	
	public void createLineChartFromSurivers(HashMap<Integer, Integer> antSystemSurivers, HashMap<Integer, Integer> randomSurivers, int bruteForceSurvivers, int numberOfTimesteps)
	{
		XYSeries antSystem = new XYSeries("AntSystem Survivers");
		XYSeries random = new XYSeries("Random Survivers");
		XYSeries bruteForce = new XYSeries("BruteForce Survivers");
		
		addNumbersToSeries(antSystem, antSystemSurivers, numberOfTimesteps);
		addNumbersToSeries(random, randomSurivers, numberOfTimesteps);
		addNumbersToSeries(bruteForce, bruteForceSurvivers, numberOfTimesteps);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		dataset.addSeries(bruteForce);
		dataset.addSeries(random);
		dataset.addSeries(antSystem);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Surivers", "Ants", "Survivers", dataset, PlotOrientation.VERTICAL, true, true, false);
		
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
