package com.danimaniarqsoft.report.chart.dataset.factory;

import static com.googlecode.charts4j.collect.Preconditions.checkArgument;
import static com.googlecode.charts4j.collect.Preconditions.checkNotNull;

import com.danimaniarqsoft.report.chart.annotations.Chart;

/**
 * The DatasetFactory Validation class, It is used by the DatasetFactory class
 * to validate operations.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class DatasetFactoryValidation {

	private static final String DATASET_NULL_MESSAGE = "The datasetClass can't be null";
	private static final String PLOT_ANNOTATION_NOT_PRESENT_MESSAGE = "The annotation \"com.danimaniarqsoft.report.chart.annotations.Plot\" is not present on the current class";

	public static <P> void validateArguments(Class<P> datasetClass) {
		checkNotNull(datasetClass, DATASET_NULL_MESSAGE);
		checkArgument(datasetClass.isAnnotationPresent(Chart.class),
				PLOT_ANNOTATION_NOT_PRESENT_MESSAGE);
	}
}
