package com.danimaniarqsoft.report.charts.dsl.lifecycle;

import org.jfree.chart.JFreeChart;

import com.danimaniarqsoft.report.charts.ChartConfiguration;

/**
 * Class that Execute all configuration phases
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ConfigurationPhases {

	public static void executeConfigurationPhases(
			ChartConfiguration configuration, final JFreeChart chart) {
		configChart(configuration, chart);
		configPlot(configuration, chart);
		configTitle(configuration, chart);
		configNoDataMessage(configuration, chart);
	}

	private static void configChart(ChartConfiguration configuration,
			final JFreeChart chart) {
		configuration.configChart(chart);
	}

	private static void configPlot(ChartConfiguration configuration,
			final JFreeChart chart) {
		configuration.configPlot(chart.getPlot());
	}

	private static void configTitle(ChartConfiguration configuration,
			final JFreeChart chart) {
		configuration.configTitle(chart.getTitle());
	}

	private static void configNoDataMessage(ChartConfiguration configuration,
			final JFreeChart chart) {
		chart.getPlot().setNoDataMessage(configuration.getNoDataMessage());
	}

}
