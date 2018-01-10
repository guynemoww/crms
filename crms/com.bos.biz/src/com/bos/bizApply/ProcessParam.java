package com.bos.bizApply;

import java.util.HashMap;
import java.util.Map;

public class ProcessParam {
	private String templateName;
	private String modelType;
	private String partyId;
	private String userCode;
	private String userName;
	private String orgName;
	private String orgCode;
	private String bizId;
	private Map<String, Object> relaMap = new HashMap<String, Object>();
	private boolean absenceParty = false;
	private boolean batch = false;

	public ProcessParam(String bizId) {
		this.bizId = bizId;
	}

	public Map<String, Object> getRelaMap() {
		relaMap.put("bizId", bizId);
		return relaMap;
	}

	public void putRelaMap(String key, Object value) {
		relaMap.put(key, value);
	}

	public String getBizId() {
		return bizId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setRelaMap(Map<String, Object> relaMap) {
		this.relaMap = relaMap;
	}

	public boolean isAbsenceParty() {
		return absenceParty;
	}

	public void setAbsenceParty(boolean absenceParty) {
		this.absenceParty = absenceParty;
	}

	public boolean isBatch() {
		return batch;
	}

	public void setBatch(boolean batch) {
		this.batch = batch;
	}

}
