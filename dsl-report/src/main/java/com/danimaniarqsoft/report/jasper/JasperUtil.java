package com.danimaniarqsoft.report.jasper;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import com.danimaniarqsoft.net.ContentDisposition;
import com.danimaniarqsoft.net.ServletUtil;

public class JasperUtil {

	public static JasperReport compileJasper(String jasperXmlFileName)
			throws JRException {
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperXmlFileName);
		return jasperReport;
	}

	public static JasperPrint fillJasper(JasperReport jasperReport,
			Map<String, Object> params, Connection connection)
			throws JRException {
		return JasperFillManager.fillReport(jasperReport, params, connection);
	}

	/** API FOR EXPORT **/
	public void exportToPdfFile(JasperPrint jasperPrint, String pdfFileName)
			throws JRException {
		JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
	}

	public void exportToPdfFromWeb(JasperPrint jasperPrint,
			HttpServletResponse response, ContentDisposition contentDispositon, String fileName) throws JRException, IOException {
		JRPdfExporter exporter = new JRPdfExporter();
		response.setContentType("application/pdf");
		ServletUtil.addContentDisposition(response, ContentDisposition.ATTACHMENT, fileName);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				response.getOutputStream());
		exporter.exportReport();
	}

	public void exportToXlsFile(JasperPrint jasperPrint, String xlsFileName)
			throws JRException {
		JRXlsExporter xlsExporter = new JRXlsExporter();
		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				xlsFileName);
		xlsExporter.exportReport();
	}

	public void exportToXlsFromWeb(JasperPrint jasperPrint,
			HttpServletResponse response) throws JRException, IOException {
		JRXlsExporter xlsExporter = new JRXlsExporter();
		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				response.getOutputStream());
		xlsExporter.exportReport();
	}
}
