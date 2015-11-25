package com.danimaniarqsoft.report.dataproviders;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.testng.annotations.DataProvider;

import com.danimaniarqsoft.report.model.Persona;

public class ExcelDataProvider {

	@DataProvider(name = "crearListaDeMapas")
	public static Object[][] crearListaDeMapas() {
		return new Object[][] { { createMapListForTest() } };
	}

	@DataProvider(name = "crearListaDePersonas")
	public static Object[][] crearListaDePersonas() {
		return new Object[][] { { getPersonas() } };
	}

	public static List<Map<String, Object>> createMapListForTest() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		int maxColumn = 500;
		int maxRow = 10;
		for (int row = 0; row < maxRow; row++) {
			Map<String, Object> rowMap = new TreeMap<String, Object>();
			for (int column = 0; column < maxColumn; column++) {
				rowMap.put(column + "", genRandomDouble());
			}
			mapList.add(rowMap);
		}
		return mapList;
	}

	private static double genRandomDouble() {
		Random r = new Random();
		double rangeMin = 1;
		double rangeMax = 101;
		return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}

	private static List<Persona> getPersonas() {
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Messi", 26, 94.45, new Date()));
		personas.add(new Persona("Cristiano Ronaldo", 28, 4.75, new Date()));
		personas.add(new Persona("Bale", 24, 84.48, new Date()));
		personas.add(new Persona("Muller", 21, 45.47, new Date()));
		personas.add(new Persona("Benzema", 25, 62.565, new Date()));
		personas.add(new Persona("Ronaldhino", 34, 84.659, new Date()));
		return personas;
	}
}