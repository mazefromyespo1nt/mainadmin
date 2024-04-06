package com.reporter.pdf.casinorep.dto;

public class PDFRequestDTO {

	protected String report_name;
	
	protected String subsidiary_name;
	
	protected String report_date;

	public PDFRequestDTO(String report_name, String subsidiary_name, String report_date) {
		super();
		this.report_name = report_name;
		this.subsidiary_name = subsidiary_name;
		this.report_date = report_date;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public String getSubsidiary_name() {
		return subsidiary_name;
	}

	public void setSubsidiary_name(String subsidiary_name) {
		this.subsidiary_name = subsidiary_name;
	}

	public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	
	
	
}
