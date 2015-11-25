package com.danimaniarqsoft.report.model;

import java.util.Date;

import com.danimaniarqsoft.report.annotations.ExcelColumn;
import com.danimaniarqsoft.report.constants.CellType;
import com.danimaniarqsoft.report.constants.FontFormat;
import com.danimaniarqsoft.report.constants.TextPosition;

public class Persona {

	@ExcelColumn(name = "Persona", textPosition = { TextPosition.ALIGN_LEFT,
			TextPosition.VERTICAL_JUSTIFY }, fontFormat = { FontFormat.STRIKEOUT, FontFormat.BOLD,
					FontFormat.COLOR_GREEN })
	private String persona;

	@ExcelColumn(name = "Cantidad", fontFormat = { FontFormat.COLOR_PURPLE })
	private double cantidad;

	@ExcelColumn(name = "Edad", fontFormat = { FontFormat.COLOR_BLUE })
	private int edad;

	@ExcelColumn(name = "Fecha", type = CellType.DATE, fontFormat = { FontFormat.BOLD, FontFormat.COLOR_RED,
			FontFormat.TIMES_NEW_ROMAN, FontFormat.COLOR_ORANGE }, dateFormat = "dd/MM/YYYY")
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