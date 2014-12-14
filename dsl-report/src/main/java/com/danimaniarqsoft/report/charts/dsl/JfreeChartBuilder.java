package com.danimaniarqsoft.report.charts.dsl;

import com.danimaniarqsoft.report.charts.config.DefaultBarChartConfiguration;
import com.danimaniarqsoft.report.charts.config.DefaultLineChartConfiguration;
import com.danimaniarqsoft.report.charts.config.DefaultPieChartConfiguration;
import com.danimaniarqsoft.report.charts.config.DefaultXYChartConfiguration;

/**
 * Initial state of the JfreeChartDsl
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class JfreeChartBuilder {

	private JfreeChartBuilder() {
	}

	public static JfreeChartBuilder createChart() {
		return new JfreeChartBuilder();
	}

	public LineChartState ofTypeLineChart() {
		ChartDslContext context = new ChartDslContext(ChartType.LINE_CHART);
		context.setConfiguration(new DefaultLineChartConfiguration());
		return new LineChartState(context);
	}

	public BarChartState ofTypeBarChart() {
		ChartDslContext context = new ChartDslContext(ChartType.BAR_CHART);
		context.setConfiguration(new DefaultBarChartConfiguration());
		return new BarChartState(context);
	}

	public PieChartState ofTypePieChart() {
		ChartDslContext context = new ChartDslContext(ChartType.PIE_CHART);
		context.setConfiguration(new DefaultPieChartConfiguration());
		return new PieChartState(context);
	}

	public XYPlotChartState ofTypeXYPlotChart() {
		ChartDslContext context = new ChartDslContext(ChartType.XY_PLOT_CHART);
		context.setConfiguration(new DefaultXYChartConfiguration());
		return new XYPlotChartState(context);
	}
}
