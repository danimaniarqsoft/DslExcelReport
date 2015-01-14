package com.danimaniarqsoft.report.chart.factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;

import com.danimaniarqsoft.report.chart.dsl.ChartDslContext;

public class JfreeChartFactory {

	public static JFreeChart createChart(ChartDslContext context) {
		switch (context.getChartType()) {
		case BAR_CHART:
			return createBarChart(context);
		case LINE_CHART:
			return createLineChart(context);
		case XY_PLOT_CHART:
			return createXYLineChart(context);
		case PIE_CHART:
			return createPieChart(context);
		default:
			throw new IllegalArgumentException(
					"The Type of Chart is not implemented yet!");
		}
	}

	private static JFreeChart createLineChart(ChartDslContext context) {
		return ChartFactory.createLineChart(context.getChartTitle(),
				context.getxLabel(), context.getyLabel(),
				(CategoryDataset) context.getDataset(),
				PlotOrientation.VERTICAL, true, true, false);
	}

	private static JFreeChart createXYLineChart(ChartDslContext context) {
		return ChartFactory.createXYLineChart(context.getChartTitle(),
				context.getxLabel(), context.getyLabel(),
				(XYDataset) context.getDataset(), PlotOrientation.VERTICAL,
				true, true, false);
	}

	private static JFreeChart createBarChart(ChartDslContext context) {
		return ChartFactory.createBarChart(context.getChartTitle(),
				context.getxLabel(), context.getyLabel(),
				(CategoryDataset) context.getDataset(),
				PlotOrientation.VERTICAL, true, true, false);
	}

	private static JFreeChart createPieChart(ChartDslContext context) {
		return ChartFactory.createPieChart(context.getChartTitle(),
				(PieDataset) context.getDataset(), true, true, true);
	}
}
