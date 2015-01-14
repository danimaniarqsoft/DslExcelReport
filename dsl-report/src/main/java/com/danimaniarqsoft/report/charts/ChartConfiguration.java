package com.danimaniarqsoft.report.charts;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.TextTitle;

/**
 * Intefaces used by JfreeChartDsl to create a configuration for some kind of
 * Chart.
 * <p>
 * visit the next link for more information
 * http://thinktibits.blogspot.mx/2013/01
 * /JFreeChart-XYDataset-Line-Chart-Java-Example.html
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public interface ChartConfiguration {

	/**
	 * Config chart method make all configuration about look and feel of the
	 * background of the chart.
	 * 
	 * @param chart
	 *            the chart
	 */
	void configChart(final JFreeChart chart);

	/**
	 * Config plot method make all configuration about look and feel of the
	 * chart.
	 * 
	 * @param plot
	 *            the plot
	 */
	void configPlot(Plot plot);

	/**
	 * Config title method make the configuration of the way and look and feel
	 * of the TextTitle on the Chart.
	 * 
	 * @param title
	 *            the title
	 */
	void configTitle(TextTitle title);

	/**
	 * Gets the no data message method return the defaul string to show whe no
	 * data is found or there are not any information to show on the Chart. It
	 * message is shown when the dataset is empty.
	 * 
	 * @return the no data message
	 */
	String getNoDataMessage();
}
