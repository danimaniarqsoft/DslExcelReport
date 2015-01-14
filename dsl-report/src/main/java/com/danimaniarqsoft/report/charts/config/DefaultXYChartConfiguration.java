package com.danimaniarqsoft.report.charts.config;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * Default Chart Configuration used by JfreeChartDSL
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DefaultXYChartConfiguration extends
		ChartConfigurationAdapter {

	@Override
	public void configChart(JFreeChart chart) {
		// common configuration
		super.configChart(chart);
		plotConfiguration(chart.getXYPlot());
		domainAxisConfiguration((NumberAxis) chart.getXYPlot().getDomainAxis());
		rangeAxisConfiguration((NumberAxis) chart.getXYPlot().getRangeAxis());
	}

	private void plotConfiguration(final XYPlot plot) {
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		plot.setRenderer(renderer);

	}

	private void domainAxisConfiguration(final NumberAxis domainAxis) {
		DecimalFormat newFormat = new DecimalFormat("0");
		domainAxis.setNumberFormatOverride(newFormat);
		domainAxis.setTickUnit(new NumberTickUnit(1));
	}

	private void rangeAxisConfiguration(final NumberAxis rangeAxis) {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		rangeAxis.setNumberFormatOverride(fmt);
	}
}
