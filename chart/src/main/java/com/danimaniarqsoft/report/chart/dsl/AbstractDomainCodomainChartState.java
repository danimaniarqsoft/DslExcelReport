package com.danimaniarqsoft.report.chart.dsl;

import java.util.List;

import org.jfree.data.general.Dataset;

import com.danimaniarqsoft.report.chart.reflection.ChartAnnotationContext;
import com.danimaniarqsoft.report.chart.reflection.ChartReflection;

/**
 * Abstract Class for implements all the commons methods on
 * DomainCOdomainCharts. For see more information about the kind of Dataset that
 * we can use see the next link:
 * <p>
 * http://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/general/package-
 * tree.html
 * 
 * @author Daniel Cortes Pichardo
 * 
 * @param <T>
 * @param <P>
 */
@SuppressWarnings("unchecked")
public class AbstractDomainCodomainChartState<T extends AbstractDomainCodomainChartState<T, P>, P extends Dataset> {
	protected ChartDslContext context;

	public AbstractDomainCodomainChartState(ChartDslContext context) {
		this.context = context;
	}

	public T withChartTitle(String chartTitle) {
		context.setChartTitle(chartTitle);
		return (T) this;
	}

	public T withYAxisLabel(String yLabel) {
		context.setyLabel(yLabel);
		return (T) this;
	}

	public T withXAxisLabel(String xLabel) {
		context.setxLabel(xLabel);
		return (T) this;
	}

	public ConfigurationState addDataSet(P dataset) {
		context.setDataset(dataset);
		return new ConfigurationState(context);
	}

	public <S> ConfigurationState addDataSet(List<S> dataset,
			Class<S> datasetClass) {
		Dataset reflectionDataset = ChartReflection.createDataset(dataset,
				datasetClass, context.getChartType());
		context.setDataset(reflectionDataset);
		return new ConfigurationState(context);
	}
}
