package com.danimaniarqsoft.report.model;

import java.util.Date;

import com.danimaniarqsoft.report.annotations.Chart;
import com.danimaniarqsoft.report.annotations.ExcelColumn;
import com.danimaniarqsoft.report.charts.dsl.ChartType;

@Chart(chartType = ChartType.BAR_CHART, xProperty = "cantidad", yProperty = "cantidad")
public class Direccion {

	@ExcelColumn(name = "Persona")
	private String persona;

	@ExcelColumn(name = "Cantidad")
	private Double cantidad;

	@ExcelColumn(name = "Edad")
	private Integer edad;

	@ExcelColumn(name = "Fecha")
	private Date fecha;

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Direccion [persona=" + persona + ", cantidad=" + cantidad
				+ ", edad=" + edad + ", fecha=" + fecha + "]";
	}
}
