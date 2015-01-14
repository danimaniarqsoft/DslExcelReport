package com.danimaniarqsoft.report.charts.dataset.factory;

import java.util.List;
import static com.danimaniarqsoft.report.charts.dataset.factory.DatasetFactoryValidation.validateArguments;
import org.jfree.data.general.Dataset;

/**
 * DatasetFactory, It is used by JFreechart DSL to create differents Kinds of
 * Dataset
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class DatasetFactory {

	public static <P> Dataset createDataset(List<P> dataset,
			Class<P> datasetClass) {
		validateArguments(datasetClass);

		return null;
	}
}
