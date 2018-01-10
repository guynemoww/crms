package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: EndStopCeasAllItrStru 
* @Description: 利息结构
* @author GIT-git
* @date 2015-5-12 下午05:33:26 
*
 */
public class EndStopCeasAllItrStru  extends SuperBosfxRq implements Serializable{

	private BigDecimal inNorItr;//表内正常利息
	private BigDecimal inDftItr;//表内拖欠利息
	private BigDecimal inPnsItr;//表内罚息
	private BigDecimal outNorItr;//表外正常利息
	private BigDecimal outDftItr;//表外拖欠利息
	private BigDecimal outPnsItr;//表外罚息
	public EndStopCeasAllItrStru(){
		this.setBaseVO(new BaseVO());
	}
	/** 
	 * @return inNorItr 
	 */
	public BigDecimal getInNorItr() {
		return inNorItr;
	}
	/** 
	 * @param inNorItr 要设置的 inNorItr 
	 */
	@XmlElement(name = "InNorItr")
	public void setInNorItr(BigDecimal inNorItr) {
		this.inNorItr = inNorItr;
	}
	/** 
	 * @return inDftItr 
	 */
	public BigDecimal getInDftItr() {
		return inDftItr;
	}
	/** 
	 * @param inDftItr 要设置的 inDftItr 
	 */
	@XmlElement(name = "InDftItr")
	public void setInDftItr(BigDecimal inDftItr) {
		this.inDftItr = inDftItr;
	}
	/** 
	 * @return inPnsItr 
	 */
	public BigDecimal getInPnsItr() {
		return inPnsItr;
	}
	/** 
	 * @param inPnsItr 要设置的 inPnsItr 
	 */
	@XmlElement(name = "InPnsItr")
	public void setInPnsItr(BigDecimal inPnsItr) {
		this.inPnsItr = inPnsItr;
	}
	/** 
	 * @return outNorItr 
	 */
	public BigDecimal getOutNorItr() {
		return outNorItr;
	}
	/** 
	 * @param outNorItr 要设置的 outNorItr 
	 */
	@XmlElement(name = "OutNorItr")
	public void setOutNorItr(BigDecimal outNorItr) {
		this.outNorItr = outNorItr;
	}
	/** 
	 * @return outDftItr 
	 */
	public BigDecimal getOutDftItr() {
		return outDftItr;
	}
	/** 
	 * @param outDftItr 要设置的 outDftItr 
	 */
	@XmlElement(name = "OutDftItr")
	public void setOutDftItr(BigDecimal outDftItr) {
		this.outDftItr = outDftItr;
	}
	/** 
	 * @return outPnsItr 
	 */
	public BigDecimal getOutPnsItr() {
		return outPnsItr;
	}
	/** 
	 * @param outPnsItr 要设置的 outPnsItr 
	 */
	@XmlElement(name = "OutPnsItr")
	public void setOutPnsItr(BigDecimal outPnsItr) {
		this.outPnsItr = outPnsItr;
	}
	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "EndStopCeasAllItrStru [inDftItr=" + inDftItr + ", inNorItr="
				+ inNorItr + ", inPnsItr=" + inPnsItr + ", outDftItr="
				+ outDftItr + ", outNorItr=" + outNorItr + ", outPnsItr="
				+ outPnsItr + "]";
	}
	
	
}
