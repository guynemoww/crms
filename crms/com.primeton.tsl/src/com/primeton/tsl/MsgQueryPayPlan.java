package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 
* @ClassName: MsgPayPlan 
* @Description: TODO查询还款计划文件打印 
* @author GIT_CXT  
* @date 2015-6-15 下午06:04:08 
*
 */
public class MsgQueryPayPlan implements Serializable {
    private String currPeri;
    private String forwProvDate = ""; //本期起始日
    private String nextProvDate = ""; //本期到期日
    private BigDecimal dCurPrin = new BigDecimal("0.00"); //本期本金
    private BigDecimal dCurItr = new BigDecimal("0.00"); //本期利息
    private BigDecimal dTotalAmt = new BigDecimal("0.00"); //本期合计
    private BigDecimal dTotalPrin = new BigDecimal("0.00");	//汇总本金
    private BigDecimal dTotalItr = new BigDecimal("0.00"); //汇总利息
    private BigDecimal dDstAmt = new BigDecimal("0.00");//本期贴息
    
    public String getCurrPeri(){
    	return currPeri;
    }
    
    @XmlElement(name = "CurrPeri")
   public void setCurrPeri(String currPeri){
    	this.currPeri = currPeri;
    }
    public String getForwProvDate(){
    	return forwProvDate;
    }
    
    @XmlElement(name = "ForwProvDate")
   public void setForwProvDate(String forwProvDate){
    	this.forwProvDate = forwProvDate;
    }
    
    public String getNextProvDate(){
    	return nextProvDate;
    }
    
    @XmlElement(name = "NextProvDate")
   public void setNextProvDate(String nextProvDate){
    	this.nextProvDate = nextProvDate;
    }
    
    public BigDecimal getDCurPrin(){
    	return dCurPrin;
    }
    
    @XmlElement(name = "DCurPrin")
   public void setDCurPrin(BigDecimal dCurPrin){
    	this.dCurPrin = dCurPrin;
    }
    
    public BigDecimal getDCurItr(){
    	return dCurItr;
    }
    
    @XmlElement(name = "DCurItr")
   public void setDCurItr(BigDecimal dCurItr){
    	this.dCurItr = dCurItr;
    }
    public BigDecimal getDTotalAmt(){
    	return dTotalAmt;
    }
    
    @XmlElement(name = "DTotalAmt")
   public void setDTotalAmt(BigDecimal dTotalAmt){
    	this.dTotalAmt = dTotalAmt;
    }
    
    public BigDecimal getDTotalPrin(){
    	return dTotalPrin;
    }
    
    @XmlElement(name = "DTotalPrin")
   public void setDTotalPrin(BigDecimal dTotalPrin){
    	this.dTotalPrin = dTotalPrin;
    }
    
    public BigDecimal getDTotalItr(){
    	return dTotalItr;
    }
    
    @XmlElement(name = "DTotalItr")
   public void setDTotalItr(BigDecimal dTotalItr){
    	this.dTotalItr = dTotalItr;
    }
    @XmlElement(name = "DdstAmt")
    public void setDdstAmt(BigDecimal dDstAmt){
    	this.dDstAmt = dDstAmt;
    }
    public BigDecimal getDdstAmt() {
		return dDstAmt;
	}
}
