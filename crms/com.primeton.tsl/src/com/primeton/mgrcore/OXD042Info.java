package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD042Info implements Serializable {

	private static final long serialVersionUID = 9173090286442089141L;

	private String reportPath;// 报表路径

	public OXD042Info() {

	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	@Override
	public String toString() {
		return "OXD042Info [reportPath=" + reportPath + "]";
	}

}
