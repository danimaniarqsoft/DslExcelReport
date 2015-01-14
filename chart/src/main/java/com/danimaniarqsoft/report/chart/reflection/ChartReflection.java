package com.danimaniarqsoft.report.chart.reflection;

import java.util.List;

import org.jfree.data.general.Dataset;

import com.danimaniarqsoft.report.chart.annotations.Chart;
import com.danimaniarqsoft.report.chart.dsl.ChartType;
import com.danimaniarqsoft.report.chart.factory.DatasourceFactory;
import com.danimaniarqsoft.report.chart.util.ReflectionUtil;

/**
 * The class for read the ChartAnnotation of given class.
 * <p>
 * This class is used just for the DSL framework
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ChartReflection {
	/**
	 * Method that create the especified dataset based on the type of Chart
	 * <p>
	 * This method is used by the ChartDsl Framework for create a dataset by
	 * reflection.
	 * 
	 * @param dataset
	 *            that is a list of Elements that we want to graph.
	 * @param datasetClass
	 *            the class of the elements conteined on the dataset. This
	 *            element is used by the method just for make the reflection.
	 * @param chartType
	 *            the type of chart for wich we want to create the dataset.
	 * @return the dataset
	 */
	public static <T> Dataset createDataset(List<T> dataset,
			Class<T> datasetClass, ChartType chartType) {
		ChartAnnotationContext context = readChartAnnotation(datasetClass);
		return DatasourceFactory.createDataset(context, dataset, chartType);
	}

	private static <P> ChartAnnotationContext readChartAnnotation(
			Class<P> datasetClass) {
		ChartAnnotationContext context = new ChartAnnotationContext();
		Chart annotation = datasetClass.getAnnotation(Chart.class);
		context.setChartType(annotation.chartType());
		context.setDomainGetterMethod(ReflectionUtil.searchGetterMethod(
				annotation.xProperty(), datasetClass));
		context.setCodomineGetterMethod(ReflectionUtil.searchGetterMethod(
				annotation.yProperty(), datasetClass));
		context.setDomainTypeClass(ReflectionUtil.getClassPropertyType(
				annotation.xProperty(), datasetClass));
		context.setCodomineTypeClass(ReflectionUtil.getClassPropertyType(
				annotation.yProperty(), datasetClass));
		return context;
	}

}
