package com.gilsonbraggion.contoller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilsonbraggion.bean.PessoaBean;
import com.gilsonbraggion.service.ReportService;

@RestController
public class ReportController {

	private final ReportService reportService;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping("/generate-pdf")
	public ResponseEntity<byte[]> generatePdf() {
		try {
			Map<String, Object> listaParametros = new HashMap<>();
			listaParametros.put("reportTitle", "Os: 2025-123 - Padaria da Bilola / Bilola Pães");
			
			PessoaBean teste = new PessoaBean();
			teste.setNome("Nome de objeto a ser recebido");
			listaParametros.put("pessoaObjeto", teste);

			PessoaBean bean = new PessoaBean();
			bean.setNome("Nome do Caboclo 1");
			bean.setUuid("UUID do Registros 1");
			bean.setDataNascimento(new Date());

			PessoaBean bean2 = new PessoaBean();
			bean2.setNome("Nome do Caboclo 2");
			bean2.setUuid("UUID do Registros 2");
			bean2.setDataNascimento(new Date());
			
			List<PessoaBean> listaPessoas = new ArrayList<>();
			listaPessoas.add(bean);
			listaPessoas.add(bean2);
			
			// Enviando os dados para o gerador do relatório
			byte[] pdfBytes = reportService.generateReport(listaParametros, listaPessoas);

			// recebendo o relatório como response
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=aaa.pdf").contentType(MediaType.APPLICATION_PDF).body(pdfBytes);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
}