package com.danimaniarqsoft.report.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.runners.Parameterized.Parameters;

import com.danimaniarqsoft.report.model.Persona;

public abstract class AbstractJfreeChartTest extends AbstractParameterizedTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ createPieDataset(), getCategoryDataset(), createDataSet(),
						createPersonaDataset() }, { "exmaple@example.com" }, });
	}

	public static XYSeriesCollection createDataSet() {
		/* Define some XY Data series for the chart */
	        int initYear = 1990;
	        int maxData = 100;
		XYSeries team1_xy_data = new XYSeries("Team 1");
		for (int i = 1990; i < initYear+maxData; i++) {
		    team1_xy_data.add(Double.valueOf(String.valueOf(i)).doubleValue(), getRandomDouble());                    
                }
		XYSeries team2_xy_data = new XYSeries("Team 2");
		for (int i = 1990; i < initYear+maxData; i++) {
		    team2_xy_data.add(Double.valueOf(String.valueOf(i)).doubleValue(), getRandomDouble());
                }

		XYSeries team3_xy_data = new XYSeries("Team 3");
		for (int i = 1990; i < initYear+maxData; i++) {
		    team3_xy_data.add(Double.valueOf(String.valueOf(i)).doubleValue(), getRandomDouble());
	        }
		
		/* Add all XYSeries to XYSeriesCollection */
		// XYSeriesCollection implements XYDataset
		XYSeriesCollection my_data_series = new XYSeriesCollection();
		// add series using addSeries method
		my_data_series.addSeries(team1_xy_data);
		my_data_series.addSeries(team2_xy_data);
		my_data_series.addSeries(team3_xy_data);
		return my_data_series;

	}

	public static List<Map<String, Object>> createMapListForTest() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		Map<String, Object> row1 = new TreeMap<String, Object>();
		row1.put("col1", 2005.0);
		row1.put("col2", 22.456);
		row1.put("col3", 7.328);
		row1.put("col5", 9.537);
		row1.put("col4", 12.801);
		mapList.add(row1);

		Map<String, Object> row2 = new TreeMap<String, Object>();
		row2.put("col1", 2006.0);
		row2.put("col3", 12.346);
		row2.put("col2", 22.086);
		row2.put("col5", 17.56);
		row2.put("col4", 15.445);
		mapList.add(row2);

		Map<String, Object> row3 = new TreeMap<String, Object>();
		row3.put("col5", 16.23);
		row3.put("col1", 2007.0);
		row3.put("col3", 34.556);
		row3.put("col4", 34.345);
		row3.put("col2", 56.34);
		mapList.add(row3);
		return mapList;
	}

	public static CategoryDataset getCategoryDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1.0, "Row 1", "Column 1");
		dataset.addValue(5.0, "Row 1", "Column 2");
		dataset.addValue(3.0, "Row 1", "Column 3");
		dataset.addValue(2.0, "Row 2", "Column 1");
		dataset.addValue(3.0, "Row 2", "Column 2");
		dataset.addValue(2.0, "Row 2", "Column 3");
		return new DefaultCategoryDataset();
	}

	private static PieDataset createPieDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Linux", 29);
		result.setValue("Mac", 20);
		result.setValue("Windows", 51);
		return result;

	}

	public static List<Persona> createPersonaDataset() {
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Messi", 26, 94.45, new Date()));
		personas.add(new Persona("Cristiano Ronaldo", 28, 4.75, new Date()));
		personas.add(new Persona("Bale", 24, 84.48, new Date()));
		personas.add(new Persona("Muller", 21, 45.47, new Date()));
		personas.add(new Persona("Benzema", 25, 62.565, new Date()));
		personas.add(new Persona("Ronaldhino", 34, 84.659, new Date()));
		return personas;
	}
	
	
	private static double getRandomDouble(){
	    int rangeMin=10;
	    int rangeMax=100;
	    Random r = new Random();
	    return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}
}
