package com.danimaniarqsoft.report.poi.reflection;

import java.lang.reflect.Method;

import com.danimaniarqsoft.report.model.CellFormatContext;

/**
 * It is the object to store the context of ExcelColumn Annotation. It is used
 * to build each cell on the ExcelColumnReflection Class.
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public class ExcelColumnContext {

	private String columnName;
	private CellFormatContext cellFormatContext;

	// Properties for parsing
	private Method method;
	private Class<?> propertyType;
	private String propertyName;

	public ExcelColumnContext() {
		cellFormatContext = new CellFormatContext();
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Class<?> propertyType) {
		this.propertyType = propertyType;
	}

	public String getColumnName() {
		return columnName;
	}

	public Method getMethod() {
		return method;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public CellFormatContext getCellFormatContext() {
		return cellFormatContext;
	}

	public void setCellFormatContext(CellFormatContext cellFormatContext) {
		this.cellFormatContext = cellFormatContext;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
