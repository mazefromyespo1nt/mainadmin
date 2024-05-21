package com.main.users.accesspoint.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.users.accesspoint.dto.PDFReporterDTO;
import com.main.users.accesspoint.dto.PDFRequestDTO;
import com.main.users.accesspoint.prototype.ReporterPDFPrototype;
import com.main.users.accesspoint.resources.RequestDTO;
import com.main.users.accesspoint.resources.ResponseDTO;
import com.main.users.accesspoint.utils.ReporterDateTimeUtil;

@RestController
public class PdfRestController {

	private static Logger logger = LoggerFactory.getLogger(PdfRestController.class);
	
	@GetMapping("/pdf-report")
	ResponseEntity<ResponseDTO<PDFReporterDTO>> testGet() {
		ReporterPDFPrototype reporter = new ReporterPDFPrototype.ReporterPDFPrototypeBuilder("casino-pdf-report-%DATE%.pdf", "Hipocampo")
				.reportDate(new Date())
				.logoPath("/home/asus13/Documents/projects/casinorep/resources/images-api/aseco.png")
				.build();
		
		logger.debug("Get Reporter TEST type: {}", reporter.getReportName());

		logger.debug("Get Reporter TEST name: {}", reporter.getSubsidiaryName());

		logger.debug("Get Reporter TEST key: {}", reporter.getLogoPath());

		logger.debug("Get Reporter TEST hashCode: {}", reporter.hashCode());
		PDFReporterDTO reporterDto =  new PDFReporterDTO.PDFReporterDTOBuilder(reporter).build();
		
		return ResponseEntity.ok(new ResponseDTO<PDFReporterDTO>("O0", "se procesa peticion", reporterDto));

	}	
	
	@PostMapping("/pdf-report")
	ResponseEntity<ResponseDTO<?>> testPostBodySpecificResEntityClass(@RequestBody RequestDTO<PDFRequestDTO> reqBody) {

		logger.debug("Post Req Reporter TEST type: {}", reqBody.getRequest_type());

		logger.debug("Post Req Reporter TEST name: {}", reqBody.getRequest_body().getReport_name());

		logger.debug("Post Req Reporter TEST key: {}", reqBody.getRequest_body().getSubsidiary_name());

		logger.debug("Post Req Reporter TEST key: {}", reqBody.getRequest_body().getReport_date());
		
		ReporterPDFPrototype reporter = new ReporterPDFPrototype.ReporterPDFPrototypeBuilder("casino-pdf-report-"+reqBody.getRequest_body().getReport_name()+"-%DATE%.pdf", reqBody.getRequest_body().getSubsidiary_name())
				.reportDate(ReporterDateTimeUtil.getDateFromString(reqBody.getRequest_body().getReport_date(), "dd-mm-yyyy"))
				.logoPath("/home/asus13/Documents/projects/casinorep/resources/images-api/aseco.png")
				.build();

		logger.debug("Post Prototype Reporter TEST type: {}", reporter.getReportName());

		logger.debug("Post Prototype Reporter TEST name: {}", reporter.getSubsidiaryName());

		logger.debug("Post Prototype Reporter TEST key: {}", reporter.getLogoPath());

		logger.debug("Post Prototype Reporter TEST hashCode: {}", reporter.hashCode());
		
		try {
			ReporterPDFPrototype cloned_reporter = reporter.clone();
			cloned_reporter.setLogoPath("/home/asus13/Documents/projects/casinorep/resources/images-api/limp.png");
			List<String> imageList = new ArrayList<String>();
			imageList.add("/home/asus13/Documents/projects/casinorep/resources/images-api/test-1.png");
			imageList.add("/home/asus13/Documents/projects/casinorep/resources/images-api/test-2.png");
			cloned_reporter.setDefaultImageList(imageList);
			logger.debug("Post ClonedPrototype Reporter TEST type: {}", cloned_reporter.getReportName());

			logger.debug("Post ClonedPrototype Reporter TEST name: {}", cloned_reporter.getSubsidiaryName());

			logger.debug("Post ClonedPrototype Reporter TEST key: {}", cloned_reporter.getLogoPath());

			logger.debug("Post ClonedPrototype Reporter TEST hashCode: {}", cloned_reporter.hashCode());
			
			PDFReporterDTO reporterDto =  new PDFReporterDTO.PDFReporterDTOBuilder(cloned_reporter).build();

			logger.debug("Post DTO Reporter TEST type: {}", reporterDto.getReportName());

			logger.debug("Post DTO Reporter TEST name: {}", reporterDto.getSubsidiaryName());

			logger.debug("Post DTO Reporter TEST hashCode: {}", reporterDto.hashCode());
			
			if(reqBody.getRequest_type().equals("generate-pdf")) {

				//response_data.setMessage("logged-in"); 
				//response_data.setToken_key("ndkjdhfadd0q3980q923849q3r"); 
				//ResponseDTO<Map<String, Object>> responseBody = new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data);

				//return ResponseEntity.badRequest().build();
				reporterDto.setFileStatus(true);
				logger.debug("Post DTO Reporter TEST key: {}", reporterDto.getFileStatus());
				return ResponseEntity.ok(new ResponseDTO<PDFReporterDTO>("O0", "se procesa peticion", reporterDto));
			} else {
				//response_data.setMessage("logged-fail"); 
				//response_data.setToken_key("fail?token?generation"); 

				reporterDto.setFileStatus(false);
				logger.debug("Post DTO Reporter TEST key: {}", reporterDto.getFileStatus());
				return ResponseEntity.badRequest().body(new ResponseDTO<PDFReporterDTO>("O1", "no se procesa peticion", reporterDto));
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();

			Map<String, Object> response_data = new HashMap<String, Object>();

			response_data.put("error_message", e.getMessage()); 
			response_data.put("error", e.getCause()); 
			return ResponseEntity.badRequest().body(new ResponseDTO<Map<String, Object> >("O2", "no se procesa peticion", response_data));
			
		}
		

	}
}
