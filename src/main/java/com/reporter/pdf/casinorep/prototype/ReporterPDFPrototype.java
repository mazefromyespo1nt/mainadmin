package com.reporter.pdf.casinorep.prototype;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReporterPDFPrototype implements Cloneable {
	//All final attributes
	private final String reportName; // required
	private final String subsidiaryName; // required
	//private final int age; // optional
	private final Date reportDate; // optional
	private List<String> defaultImageList; // optional
	private String logoPath; // optional

	private ReporterPDFPrototype(ReporterPDFPrototypeBuilder builder) {
		this.reportName = builder.reportName;
		this.subsidiaryName = builder.subsidiaryName;
		this.reportDate = builder.reportDate;
		this.defaultImageList = builder.defaultImageList;
		this.logoPath = builder.logoPath;
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

	public void setDefaultImageList(List<String> defaultImageList) {
		this.defaultImageList = defaultImageList;
	}

	public String getLogoPath() {
		return logoPath;
	}
	
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	@Override
	public String toString() {
		return "ReporterPDFPrototype: "+this.reportName+", "+this.subsidiaryName+", "+this.reportDate+", "+this.defaultImageList+", "+this.logoPath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(defaultImageList, logoPath, reportDate, reportName, subsidiaryName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReporterPDFPrototype other = (ReporterPDFPrototype) obj;
		return Objects.equals(defaultImageList, other.defaultImageList) && Objects.equals(logoPath, other.logoPath)
				&& Objects.equals(reportDate, other.reportDate) && Objects.equals(reportName, other.reportName)
				&& Objects.equals(subsidiaryName, other.subsidiaryName);
	}

	@Override
	public ReporterPDFPrototype clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new ReporterPDFPrototype.ReporterPDFPrototypeBuilder(this.reportName, this.subsidiaryName)
				.reportDate(this.reportDate)
				.logoPath(this.logoPath)
				.build();
		//return reporter;
	}

	public static class ReporterPDFPrototypeBuilder
	{

		private final String reportName;
		private final String subsidiaryName;
		private Date reportDate;
		private List<String> defaultImageList;
		private String logoPath;

		public ReporterPDFPrototypeBuilder(String reportName, String subsidiaryName) {
			this.reportName = reportName;
			this.subsidiaryName = subsidiaryName;
		}
		public ReporterPDFPrototypeBuilder reportDate(Date reportDate) {
			this.reportDate = reportDate;
			return this;
		}
		public ReporterPDFPrototypeBuilder defaultImageList(List<String> defaultImageList) {
			this.defaultImageList = defaultImageList;
			return this;
		}
		public ReporterPDFPrototypeBuilder logoPath(String logoPath) {
			this.logoPath = logoPath;
			return this;
		}
		//Return the finally constructed User object
		public ReporterPDFPrototype build() {
			ReporterPDFPrototype reporter =  new ReporterPDFPrototype(this);
			validateUserObject(reporter);
			return reporter;
		}
		private void validateUserObject(ReporterPDFPrototype reporter) {
			//Do some basic validations to check
			//if user object does not break any assumption of system
		}
	}
}