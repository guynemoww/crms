package com.bos.gjService;

import java.io.Serializable;
/**
 * 外围调用信贷接口公共类响应
 * @author CHENPAN
 *
 */
public class BaseRs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050975135782969077L;
	private String resCode;   //响应码          ,000000-成功999999-失败
	private String resError;  //响应信息        ,失败异常信息          
	private Integer allnum;    //消息总个数                       
	private Integer startnum;  //起始消息编号                     
	private Integer count;     //消息个数        ,当前页个数            
	private String comeon;    //有无后续消息标志,1-有 0-无   
	public BaseRs() {
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResError() {
		return resError;
	}
	public void setResError(String resError) {
		this.resError = resError;
	}
	public Integer getAllnum() {
		return allnum;
	}
	public void setAllnum(Integer allnum) {
		this.allnum = allnum;
	}
	public Integer getStartnum() {
		return startnum;
	}
	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getComeon() {
		return comeon;
	}
	public void setComeon(String comeon) {
		this.comeon = comeon;
	}
	
}
