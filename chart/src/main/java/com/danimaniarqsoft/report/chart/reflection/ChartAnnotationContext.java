package com.danimaniarqsoft.report.chart.reflection;

import java.lang.reflect.Method;

import com.danimaniarqsoft.report.chart.dsl.ChartType;

/**
 * Chart Annotation Context Class is used by ChartReflection clas to do
 * reflection operations.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ChartAnnotationContext {

	private ChartType chartType;
	private Class<?> domainTypeClass;
	private Class<?> codomineTypeClass;
	private Method domainGetterMethod;
	private Method codomineGetterMethod;

	public Class<?> getDomainTypeClass() {
		return domainTypeClass;
	}

	public void setDomainTypeClass(Class<?> domainTypeClass) {
		this.domainTypeClass = domainTypeClass;
	}

	public Class<?> getCodomineTypeClass() {
		return codomineTypeClass;
	}

	public void setCodomineTypeClass(Class<?> codomineTypeClass) {
		this.codomineTypeClass = codomineTypeClass;
	}

	public ChartType getChartType() {
		return chartType;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	public Method getDomainGetterMethod() {
		return domainGetterMethod;
	}

	public void setDomainGetterMethod(Method domainGetterMethod) {
		this.domainGetterMethod = domainGetterMethod;
	}

	public Method getCodomineGetterMethod() {
		return codomineGetterMethod;
	}

	public void setCodomineGetterMethod(Method codomineGetterMethod) {
		this.codomineGetterMethod = codomineGetterMethod;
	}

}
