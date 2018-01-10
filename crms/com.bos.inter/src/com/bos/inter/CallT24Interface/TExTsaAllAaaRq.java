/**
 * 
 */
package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * @author lujinbin
 * @date 2014-07-29 14:57:33
 * 
 */
// TSA自动触发接口
public class TExTsaAllAaaRq extends SuperBosfxRq {
	@XmlElement(name = "TsaName")
	public String TsaName; // TSA名称

	@XmlElement(name = "InDate")
	public String InDate; // 触发时间

	@XmlElement(name = "Remark")
	public String Remark; // 备注

	@XmlElement(name = "Usage")
	public String Usage; // 用途

	@XmlElement(name = "FbNo")
	public String FbNo; // 业务类型

	@XmlElement(name = "TxnType")
	public String TxnType; // 本地交易类型

	@XmlElement(name = "FilePath")
	public String FilePath; // 文件目录

	@XmlElement(name = "FileName_1")
	public String FileName_1; // 文件名

	public void setFbNo(String fbNo) {
		FbNo = fbNo;
	}

	public void setFileName_1(String fileName_1) {
		FileName_1 = fileName_1;
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public void setInDate(String inDate) {
		InDate = inDate;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public void setTsaName(String tsaName) {
		TsaName = tsaName;
	}

	public void setTxnType(String txnType) {
		TxnType = txnType;
	}

	public void setUsage(String usage) {
		Usage = usage;
	}

	public String toString() {
		return "TExTsaAllAaa [TsaName=" + TsaName + ", InDate=" + InDate
				+ ", Remark=" + Remark + ", Usage=" + Usage + ",FbNo=" + FbNo
				+ ", TxnType=" + TxnType + ", FilePath=" + FilePath
				+ ", FileName_1=" + FileName_1 + "]";
	}
}
