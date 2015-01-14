package com.danimaniarqsoft.report.chart.model;

import java.util.Date;

import com.danimaniarqsoft.report.chart.annotations.Chart;
import com.danimaniarqsoft.report.chart.dsl.ChartType;

@Chart(chartType = ChartType.PIE_CHART, xProperty = "edad", yProperty = "cantidad")
public class Persona {

	private String persona;

	private double cantidad;

	private int edad;

	private Date fecha;

	public Persona(String persona, int edad, double cantidad, Date fecha) {
		this.persona = persona;
		this.edad = edad;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
