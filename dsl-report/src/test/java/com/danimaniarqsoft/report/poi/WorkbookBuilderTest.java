package com.danimaniarqsoft.report.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Workbook;

import com.danimaniarqsoft.report.model.Persona;
import static com.danimaniarqsoft.report.poi.dsl.WorkbookBuilder.createWorkbook;
import com.danimaniarqsoft.report.poi.dsl.WorkbookEnum;

public class WorkbookBuilderTest {

	public static void main(String[] args) throws IOException {
		List<Persona> personas = getPlayers();

		Workbook workbook = createWorkbook(WorkbookEnum.XLSX)
				.createSheet("hola1").createHeader(Persona.class)
				.createRows(personas, Persona.class).buildWorkbook();

		FileOutputStream fileOut = new FileOutputStream("dslExample.xlsx");
		workbook.write(fileOut);
		fileOut.close();
	}

	private static List<Persona> getPlayers() {
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Messi", 26, 94.45, new Date()));
		personas.add(new Persona("Cristiano Ronaldo", 28, 4.75, new Date()));
		personas.add(new Persona("Bale", 24, 84.48, new Date()));
		personas.add(new Persona("Muller", 21, 45.47, new Date()));
		personas.add(new Persona("Benzema", 25, 62.565, new Date()));
		personas.add(new Persona("Ronaldhino", 34, 84.659, new Date()));
		return personas;
	}

	public static Map<String, Object> createHeaders() {
		Map<String, Object> header = new TreeMap<String, Object>();
		header.put("col01", BigDecimal.valueOf(genRandomDouble()));
		header.put("col02", BigDecimal.valueOf(genRandomDouble()));
		header.put("col03", BigDecimal.valueOf(genRandomDouble()));
		header.put("col04", BigDecimal.valueOf(genRandomDouble()));
		header.put("col05", BigDecimal.valueOf(genRandomDouble()));
		header.put("col06", BigDecimal.valueOf(genRandomDouble()));
		header.put("col07", BigDecimal.valueOf(genRandomDouble()));
		header.put("col08", BigDecimal.valueOf(genRandomDouble()));
		header.put("col09", BigDecimal.valueOf(genRandomDouble()));
		header.put("col10", BigDecimal.valueOf(genRandomDouble()));
		return header;
	}

	private static double genRandomDouble() {
		Random r = new Random();
		double rangeMin = 1;
		double rangeMax = 101;
		return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}
}
