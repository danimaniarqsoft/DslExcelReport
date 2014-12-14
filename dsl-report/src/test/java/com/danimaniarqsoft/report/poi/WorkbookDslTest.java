package com.danimaniarqsoft.report.poi;

import static com.danimaniarqsoft.report.poi.dsl.WorkbookBuilder.createWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.danimaniarqsoft.report.config.AbstractPoiTest;
import com.danimaniarqsoft.report.poi.dsl.WorkbookEnum;

public class WorkbookDslTest extends AbstractPoiTest {

    private List<Map<String, Object>> datasource;

    public WorkbookDslTest(List<Map<String, Object>> datasource) {
        this.datasource = datasource;
    }

    @Test
    public void workbookMapSourceTest() throws IOException {

        try {
            Workbook workbook = createWorkbook(WorkbookEnum.XLSX)
                    .createSheet("hola1").createHeader(datasource.get(0))
                    .createRows(datasource).buildWorkbookWithAverageRow(1);
            FileOutputStream fileOut = new FileOutputStream("dslExample.xlsx");
            workbook.write(fileOut);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
