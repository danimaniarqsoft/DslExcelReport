package com.danimaniarqsoft.report.builders;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.danimaniarqsoft.report.config.AbstractTest;
import com.danimaniarqsoft.report.model.Direccion;
import com.danimaniarqsoft.report.poi.PoiUtil;
import com.danimaniarqsoft.report.poi.builders.ModelObjectBuilder;
import com.danimaniarqsoft.report.poi.dsl.WorkbookEnum;

/**
 * ModelObjectBuilderTest Class for Test the ModelObjectBuilder Class.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ModelObjectBuilderTest extends AbstractTest {

	@Test
	public void ModelObjectCreatorTest() throws IOException,
			NoSuchMethodException, SecurityException {
		Workbook workbook = PoiUtil.readWorkbook(WorkbookEnum.XLSX,
				"dslExample.xlsx");
		List<Direccion> direcciones = ModelObjectBuilder.createModelObject(
				workbook, Direccion.class);
		for (Direccion direccion : direcciones) {
			LOG.info(direccion.toString());
		}
	}
}
