package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRq0200300000401 
 * @Description: 02003000004信贷信息查询     01信贷资料目录树查询			
 *
 */
public class EsbBodyMtmqRsRvlvNodeArray implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nodeCd				;//	节点代码	String(10)	N	
	private String nodeNm				;//	节点名              String(10)	N	
	
	public String getNodeCd() {
		return nodeCd;
	}
	
	@XmlElement(name = "NodeCd")
	public void setNodeCd(String nodeCd) {
		this.nodeCd = nodeCd;
	}
	public String getNodeNm() {
		return nodeNm;
	}
	@XmlElement(name = "NodeNm")
	public void setNodeNm(String nodeNm) {
		this.nodeNm = nodeNm;
	}
	
	
	
	
	
}
