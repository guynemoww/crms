package com.bos.gjService;

import java.io.Serializable;
/**
 * 外围调用信贷接口公共类请求
 * @author CHENPAN
 *
 */
public class BaseRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3719351521819423076L;
	private String channelFlag;      // 渠道标识码          ,必输                            
	private String serverCod;        // 交易码              ,必输                            
	private String flowNo;           // 流水号              ,　可选                          
	private String localTime;        // 本地时间            ,HHMMSS 必输                     
	private String localDate;        // 本地日期（营业日期）,YYYYMMDD 必输                   
	private String provinceNum;      // 省号                ,可选　                          
	private String orgCode;          // 机构代码            ,必输　                          
	private String orgName;          // 机构名称            ,可选　                          
	private String orgLevel;         // 机构层级            ,可选　                          
	private String userCode;         // 操作员工号          ,必输　                          
	private String userName;         // 操作员姓名          ,可选　                          
	private String postCd;           // 操作员岗位          ,可选　                          
	private String postName;         // 操作员岗位名称      ,可选　                          
	private String operationType;    // 操作类型            ,可选 A-新增 D-删除 M-修改 Q-查询
	private Integer startnum;         // 起始消息编号        ,可选 分页查询时使用　           
	private Integer size;             // 分页记录数          ,可选 分页查询时使用　           
	private Integer pageNum;          // 当前页码            ,可选 分页查询时使用             
	public BaseRq() {
	}
	public String getChannelFlag() {
		return channelFlag;
	}
	public void setChannelFlag(String channelFlag) {
		this.channelFlag = channelFlag;
	}
	public String getServerCod() {
		return serverCod;
	}
	public void setServerCod(String serverCod) {
		this.serverCod = serverCod;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getLocalTime() {
		return localTime;
	}
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}
	public String getLocalDate() {
		return localDate;
	}
	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}
	public String getProvinceNum() {
		return provinceNum;
	}
	public void setProvinceNum(String provinceNum) {
		this.provinceNum = provinceNum;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPostCd() {
		return postCd;
	}
	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Integer getStartnum() {
		return startnum;
	}
	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	                                                       
}
