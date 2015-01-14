package com.danimaniarqsoft.report.charts.dsl;

import org.jfree.chart.JFreeChart;

import com.danimaniarqsoft.report.charts.ChartConfiguration;
import com.danimaniarqsoft.report.charts.dsl.lifecycle.ConfigurationPhases;
import com.danimaniarqsoft.report.charts.factory.JfreeChartFactory;

/**
 * ConfigurationState represent a internal state of the ChartDsl. It is used
 * just for the framework.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ConfigurationState {

	/** The context object shared by Dsl States */
	private ChartDslContext context = null;

	/** The configuration of the Graph */
	private ChartConfiguration configuration;

	/**
	 * Instantiates a new configuration state.
	 * 
	 * @param context
	 *            the context
	 */
	public ConfigurationState(ChartDslContext context) {
		this.context = context;
		// We have a default configuration, see JfreeChartBuilder.java Class
		configuration = context.getConfiguration();
	}

	/**
	 * Creates the chart.
	 * 
	 * @return the j free chart
	 */
	public JFreeChart createChart() {
		return createChart(configuration);
	}

	/**
	 * Creates the chart.
	 * 
	 * @param configuration
	 *            the configuration
	 * @return the j free chart
	 */
	public JFreeChart createChart(ChartConfiguration configuration) {
		final JFreeChart chart = JfreeChartFactory.createChart(context);
		ConfigurationPhases.executeConfigurationPhases(configuration, chart);
		return chart;
	}
}
