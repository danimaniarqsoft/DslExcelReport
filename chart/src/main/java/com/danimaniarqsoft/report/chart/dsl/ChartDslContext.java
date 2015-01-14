package com.danimaniarqsoft.report.chart.dsl;

import org.jfree.data.general.Dataset;

import com.danimaniarqsoft.report.chart.config.ChartConfiguration;

/**
 * The ChartDslContext used by de JFreeChart Dsl Engine
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ChartDslContext {
	private String chartTitle = "";
	private String xLabel = "X";
	private String yLabel = "Y";
	private Dataset dataset;
	private ChartConfiguration configuration;

	public ChartConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ChartConfiguration configuration) {
		this.configuration = configuration;
	}

	ChartType chartType = null;

	public ChartDslContext(ChartType chartType) {
		this.chartType = chartType;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public String getxLabel() {
		return xLabel;
	}

	public String getyLabel() {
		return yLabel;
	}

	public ChartType getChartType() {
		return chartType;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}

	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
}
