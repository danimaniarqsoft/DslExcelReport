package com.danimaniarqsoft.report.chart.config;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.TextTitle;

/**
 * ChartConfigurationAdapter is used whe you need to implements or modify the
 * behavior of the way of how the DSL configure the Chart. This class make a
 * default implementation of the interface ChartConfiguration and provide a
 * flexibility way to override a especific phase of the Chart configuration made
 * by the JFreecharDSL.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ChartConfigurationAdapter implements ChartConfiguration {

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is the default configuration for configChart method. It phase can be
	 * override.
	 */
	@Override
	public void configChart(JFreeChart chart) {
		chart.setBackgroundPaint(Color.WHITE);
		chart.setTextAntiAlias(true);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is the default configuration for configPlot method. It phase can be
	 * override.
	 */
	@Override
	public void configPlot(Plot plot) {
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlinePaint(Color.WHITE);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is the default configuration for configTitle method. It phase can be
	 * override.
	 */
	@Override
	public void configTitle(final TextTitle title) {
		int style = Font.BOLD | Font.ITALIC;
		Font font = new Font("Arial", style, 18);
		title.setFont(font);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is the default configuration for getNoDataMessage method. It phase
	 * can be override.
	 */
	@Override
	public String getNoDataMessage() {
		return "[NO HAY INFORMACIÓN]";
	}
}
