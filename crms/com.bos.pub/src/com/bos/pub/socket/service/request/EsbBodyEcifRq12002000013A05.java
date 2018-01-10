package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyEcifRq12002000013A05 
 * @Description: 12002000013客户信息开户维护	05公司客户关键信息维护
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyEcifRq12002000013A05 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rqsStmId;	//请求系统号	String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cstNo;		//客户代号		String(10)	Y
	private String oldCstNm;	//原客户名称	String(50)	Y
	private String nerCstNm;	//新客户名称	String(50)	N	
	private List<EsbBodyEcifRqIdentInfArray> esbBodyEcifRqIdentInfArrays;//证件信息数组
	
	public EsbBodyEcifRq12002000013A05(){
		
	}

	public String getRqsStmId() {
		return rqsStmId;
	}

	@XmlElement(name = "RqsStmId")
	public void setRqsStmId(String rqsStmId) {
		this.rqsStmId = rqsStmId;
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getOldCstNm() {
		return oldCstNm;
	}

	@XmlElement(name = "OldCstNm")
	public void setOldCstNm(String oldCstNm) {
		this.oldCstNm = oldCstNm;
	}

	public String getNerCstNm() {
		return nerCstNm;
	}

	@XmlElement(name = "NerCstNm")
	public void setNerCstNm(String nerCstNm) {
		this.nerCstNm = nerCstNm;
	}

	public List<EsbBodyEcifRqIdentInfArray> getEsbBodyEcifRqIdentInfArrays() {
		return esbBodyEcifRqIdentInfArrays;
	}

	@XmlElement(name = "IdentInfArray")
	public void setEsbBodyEcifRqIdentInfArrays(
			List<EsbBodyEcifRqIdentInfArray> esbBodyEcifRqIdentInfArrays) {
		this.esbBodyEcifRqIdentInfArrays = esbBodyEcifRqIdentInfArrays;
	}
}
