package com.danimaniarqsoft.report.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danimaniarqsoft.report.constants.CellType;
import com.danimaniarqsoft.report.constants.FontFormat;
import com.danimaniarqsoft.report.constants.TextPosition;

/**
 * Annotation for create excel Cells
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn {
	public String name();

	public CellType type() default CellType.STRING;

	public String dateFormat() default "dd/MM/yyyy";

	public TextPosition[] textPosition() default { TextPosition.ALIGN_CENTER };

	public FontFormat[] fontFormat() default {FontFormat.NORMAL};
}
