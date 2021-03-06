package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyEcifRq12002000013A04 
 * @Description: 12002000013客户信息开户维护	04公司客户基本信息开户
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyEcifRq12002000013A04 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rqsStmId;	//请求系统号	String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cstNm;		//客户名称		String(50)	Y	
	//private String idntTp;		//证件类型		String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	//private String identNo;		//证件号码		String(20)	Y	
	private List<EsbBodyEcifRqIdentInfArray> esbBodyEcifRqIdentInfArrays;//证件信息数组
	
	public EsbBodyEcifRq12002000013A04(){
		
	}

	public String getRqsStmId() {
		return rqsStmId;
	}

	@XmlElement(name = "RqsStmId")
	public void setRqsStmId(String rqsStmId) {
		this.rqsStmId = rqsStmId;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	/*public String getIdntTp() {
		return idntTp;
	}

	@XmlElement(name = "IdntTp")
	public void setIdntTp(String idntTp) {
		this.idntTp = idntTp;
	}

	public String getIdentNo() {
		return identNo;
	}

	@XmlElement(name = "IdentNo")
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}*/

	public List<EsbBodyEcifRqIdentInfArray> getEsbBodyEcifRqIdentInfArrays() {
		return esbBodyEcifRqIdentInfArrays;
	}

	@XmlElement(name = "IdentInfArray")
	public void setEsbBodyEcifRqIdentInfArrays(
			List<EsbBodyEcifRqIdentInfArray> esbBodyEcifRqIdentInfArrays) {
		this.esbBodyEcifRqIdentInfArrays = esbBodyEcifRqIdentInfArrays;
	}
}
