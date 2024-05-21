package com.main.users.accesspoint.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.main.users.accesspoint.prototype.ReporterPDFPrototype;

public class PDFReporterDTO {

	//All final attributes
	private final String reportName; // required
	private final String subsidiaryName; // required
	//private final int age; // optional
	private final Date reportDate; // optional
	private final List<String> defaultImageList; // optional
	private Boolean fileStatus;
	//private final String logoPath; // optional

	private PDFReporterDTO(PDFReporterDTOBuilder builder) {
		this.reportName = builder.reportName;
		this.subsidiaryName = builder.subsidiaryName;
		this.reportDate = builder.reportDate;
		this.defaultImageList = builder.defaultImageList;
		this.fileStatus = builder.fileStatus;
		//this.logoPath = builder.logoPath;
	}

	public Boolean getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(Boolean fileStatus) {
		this.fileStatus = fileStatus;
	}

	//All getter, and NO setter to provide immutability
	public String getReportName() {
		return reportName;
	}
	public String getSubsidiaryName() {
		return subsidiaryName;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public List<String> getDefaultImageList() {
		return defaultImageList;
	}
	/*
	 * public String getLogoPath() { return logoPath; }
	 */

	@Override
	public String toString() {
		return "ReporterPDFAseco: "+this.reportName+", "+this.subsidiaryName+", "+this.reportDate+", "+this.defaultImageList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(defaultImageList, reportDate, reportName, subsidiaryName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PDFReporterDTO other = (PDFReporterDTO) obj;
		return Objects.equals(defaultImageList, other.defaultImageList)
				&& Objects.equals(reportDate, other.reportDate) && Objects.equals(reportName, other.reportName)
				&& Objects.equals(subsidiaryName, other.subsidiaryName);
	}

	public static class PDFReporterDTOBuilder
	{
		private final String reportName;
		private final String subsidiaryName;
		private Date reportDate;
		private List<String> defaultImageList;
		private Boolean fileStatus;
		//private String logoPath;

		public PDFReporterDTOBuilder(ReporterPDFPrototype reporter) {
			this.reportName = reporter.getReportName();
			this.subsidiaryName = reporter.getSubsidiaryName();
			this.reportDate = reporter.getReportDate();
			this.defaultImageList = reporter.getDefaultImageList();
			this.fileStatus=true;
		}
		
		public PDFReporterDTOBuilder(String reportName, String subsidiaryName) {
			this.reportName = reportName;
			this.subsidiaryName = subsidiaryName;
		}
		public PDFReporterDTOBuilder reportDate(Date reportDate) {
			this.reportDate = reportDate;
			return this;
		}
		public PDFReporterDTOBuilder defaultImageList(List<String> defaultImageList) {
			this.defaultImageList = defaultImageList;
			return this;
		}
		
		public PDFReporterDTOBuilder fileStatus(Boolean fileStatus) { 
			this.fileStatus = fileStatus; return this; 
		}
		 
		/*
		 * public ReporterPDFAsecoBuilder logoPath(String logoPath) { this.logoPath =
		 * logoPath; return this; }
		 */
		//Return the finally constructed User object
		public PDFReporterDTO build() {
			PDFReporterDTO reporter =  new PDFReporterDTO(this);
			validateUserObject(reporter);
			return reporter;
		}
		private void validateUserObject(PDFReporterDTO reporter) {
			//Do some basic validations to check
			//if user object does not break any assumption of system
		}
	}
}
