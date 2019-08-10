package RootElement;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

import mappingEntities.Coordinate;
import mappingEntities.CoordinateRow;

public class Plotter {
	private final XYChart chart;
	private final SwingWrapper<XYChart> sw;
	
	public Plotter(CoordinateRow fs){
        double[][] initdata = getData(fs);

        // Create Chart
        chart = new XYChartBuilder().width(600).height(400).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(6);
        
        chart.addSeries("points", initdata[0], initdata[1]);

        // Show it
        sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();
	}
	
	private double[][] getData(CoordinateRow fs) {
		int numberOfCoordinates = fs.size();
		if(numberOfCoordinates == 0){
			return new double[][] {{0},{0}};
		}
		
		double[] x = new double[numberOfCoordinates];
		double[] y = new double[numberOfCoordinates];
		
		int xCounter = 0;
		int yCounter = 0;
		
		for(Coordinate c: fs){
			x[xCounter] = c.getX();
			y[yCounter] = c.getY();
			xCounter += 1;
			yCounter += 1;
		}
		
		return new double[][] { x, y };
	}
	
	public void updateChart(CoordinateRow fs){
	      final double[][] data = getData(fs);

	      chart.updateXYSeries("points", data[0], data[1], null);
	      sw.repaintChart();
	}
}
