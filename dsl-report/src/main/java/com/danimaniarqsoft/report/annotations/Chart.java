package com.danimaniarqsoft.report.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danimaniarqsoft.report.charts.dsl.ChartType;

/**
 * Annotation for create JFreechart Graphs
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Chart {
	public ChartType chartType();
	public String xProperty();
	public String yProperty();
}
