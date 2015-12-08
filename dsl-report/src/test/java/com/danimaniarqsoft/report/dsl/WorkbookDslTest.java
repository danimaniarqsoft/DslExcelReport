package com.danimaniarqsoft.report.dsl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.danimaniarqsoft.report.dataproviders.ExcelDataProvider;
import com.danimaniarqsoft.report.model.Persona;
import com.danimaniarqsoft.report.poi.dsl.WorkbookBuilder;
import com.danimaniarqsoft.report.poi.dsl.WorkbookEnum;

public class WorkbookDslTest {
  private static final Logger LOG = LoggerFactory.getLogger(WorkbookDslTest.class);

  @Test(dataProvider = "crearListaDeMapas", dataProviderClass = ExcelDataProvider.class,
      enabled = true)
  public void workbookMapSourceTest(List<Map<String, Object>> datasource) throws IOException {
    try {
      Workbook workbook = WorkbookBuilder.createWorkbook(WorkbookEnum.XLSX).createSheet("hola1")
          .createHeader(datasource.get(0)).createRows(datasource).buildWorkbookWithAverageRow(1);
      FileOutputStream fileOut = new FileOutputStream("dslExample.xlsx");
      workbook.write(fileOut);

    } catch (Exception e) {
      LOG.error("Ocurrio un error", e);
    }
  }

  @Test(dataProvider = "crearListaDePersonas", dataProviderClass = ExcelDataProvider.class,
      invocationCount = 1000)
  public void workbookListSourceTest(List<Persona> datasource) throws IOException {
    String name = Thread.currentThread().getName();
    LOG.info("name of Thread is: " + name);
    try {
      Workbook workbook = WorkbookBuilder.createWorkbook(WorkbookEnum.XLSX).createSheet("Jugadores")
          .createHeader(Persona.class).createRows(datasource, Persona.class)
          .buildWorkbookWithAverageRow(1);
      FileOutputStream fileOut = new FileOutputStream("dslClaseExample.xlsx");
      workbook.write(fileOut);
    } catch (Exception e) {
      LOG.error("Ocurrio un error", e);
    }
  }

}
