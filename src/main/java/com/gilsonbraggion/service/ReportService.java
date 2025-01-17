package com.gilsonbraggion.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gilsonbraggion.bean.PessoaBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	public byte[] generateReport(Map<String, Object> parametros , List<PessoaBean> listaPessoas) throws JRException {
		// Load the .jrxml file
		InputStream reportStream = getClass().getResourceAsStream("/report_template.jrxml");

		// Compile the report
		JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

		// Create a data source from the provided data
		JRBeanCollectionDataSource listaIteracao = new JRBeanCollectionDataSource(listaPessoas);

		// Fill the report with data
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, listaIteracao);

		// Export the report to a PDF
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}