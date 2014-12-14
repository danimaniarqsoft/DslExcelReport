package com.danimaniarqsoft.report.charts.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.danimaniarqsoft.report.charts.dsl.ChartType;
import com.danimaniarqsoft.report.charts.reflection.ChartAnnotationContext;
import com.danimaniarqsoft.report.exceptions.DslException;
import com.danimaniarqsoft.report.util.ReflectionUtil;

public class DatasourceFactory {

	public static <T> Dataset createDataset(ChartAnnotationContext context,
			List<T> dataset, ChartType chartType) {

		try {
			switch (chartType) {
			case BAR_CHART:
			case LINE_CHART:
				return createCategoryDataset(dataset, context);
			case PIE_CHART:
				return createPieDataset(dataset, context);
			case XY_PLOT_CHART:
				return creaetXYDataset(dataset, context);
			default:
				throw new DslException("The type of dataset is not implemented");
			}
		} catch (Exception e) {
			throw new DslException("Is not possible to create dataset", e);
		}
	}

	private static <P> CategoryDataset createCategoryDataset(
			List<P> datasetToConvert, ChartAnnotationContext context)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method codomineMethod = context.getCodomineGetterMethod();
		Method domainMethod = context.getDomainGetterMethod();
		return createDefaultCategoryDataSet(datasetToConvert, context,
				codomineMethod, domainMethod);
	}

	private static <P> PieDataset createPieDataset(List<P> datasetToConvert,
			ChartAnnotationContext context) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method codomineMethod = context.getCodomineGetterMethod();
		Method domainMethod = context.getDomainGetterMethod();
		return createDefaultPieDataSet(datasetToConvert, context,
				codomineMethod, domainMethod);
	}

	private static <P> XYDataset creaetXYDataset(List<P> datasetToConvert,
			ChartAnnotationContext context) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method codomineMethod = context.getCodomineGetterMethod();
		Method domainMethod = context.getDomainGetterMethod();
		return createDefaultXYDataSet(datasetToConvert, context,
				codomineMethod, domainMethod);
	}

	private static <P> CategoryDataset createDefaultCategoryDataSet(
			List<P> datasetToConvert, ChartAnnotationContext context,
			Method codomineMethod, Method domainMethod) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (ReflectionUtil.isNumberClass(context.getCodomineTypeClass())) {
			for (P item : datasetToConvert) {
				Number value = (Number) ReflectionUtil.invokeGetterMethod(
						codomineMethod, item);
				String columnKey = ReflectionUtil.invokeGetterMethod(
						domainMethod, item).toString();
				dataset.addValue(value, "", columnKey);
			}
		} else {
			throw new DslException(
					"The Domains values for Category Dataset must be a Numbers ");
		}
		return dataset;
	}

	private static <P> PieDataset createDefaultPieDataSet(
			List<P> datasetToConvert, ChartAnnotationContext context,
			Method codomineMethod, Method domainMethod) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		if (ReflectionUtil.isNumberClass(context.getCodomineTypeClass())) {
			for (P item : datasetToConvert) {
				Number value = (Number) ReflectionUtil.invokeGetterMethod(
						codomineMethod, item);
				String columnKey = ReflectionUtil.invokeGetterMethod(
						domainMethod, item).toString();
				dataset.setValue(columnKey, value);
			}
		} else {
			throw new DslException(
					"The Domains values for Pie Dataset must be a Numbers ");
		}
		return dataset;
	}

	private static <P> XYDataset createDefaultXYDataSet(
			List<P> datasetToConvert, ChartAnnotationContext context,
			Method codomineMethod, Method domainMethod) {
		XYSeriesCollection datasetSeries = new XYSeriesCollection();

		if (ReflectionUtil.isNumberClass(context.getCodomineTypeClass())
				&& ReflectionUtil.isNumberClass(context.getDomainTypeClass())) {
			XYSeries dataset = new XYSeries("Team 1");
			for (P item : datasetToConvert) {
				Number domainValue = (Number) ReflectionUtil
						.invokeGetterMethod(codomineMethod, item);
				Number codomineValue = (Number) ReflectionUtil
						.invokeGetterMethod(domainMethod, item);
				dataset.add(domainValue, codomineValue);
			}
			datasetSeries.addSeries(dataset);
		} else {
			throw new DslException(
					"The Domains and codomine values for XY Dataset must be a Numbers ");
		}
		return datasetSeries;
	}
}
