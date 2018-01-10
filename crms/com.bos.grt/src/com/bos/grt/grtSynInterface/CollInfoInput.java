package com.bos.grt.grtSynInterface;

import java.util.Date;

/**
 * 押品概要信息同步 接口 传参类
 * @author lenovo
 *
 */
		
public class CollInfoInput {
	
	//-----------------报文头开始
	public String trans_code;//交易代码

	public String trans_desc;//交易中文描述
	
	public String ori_channel;//发起方渠道
	
	public String ori_trans_seri_num; //发起方流水号
	
	public Date trans_time; //发起方交易时间
	//-----------------报文头结束
	public String ope_flag; //操作标志 "0：新增/修改 1：删除：删除时只赋押品编号"

	public String clt_flag; //抵质押类型 "1：抵押 2：质押"
	
	public String signeename; //押品所有权人名称
	
	public String clt_no; //押品编号
	
	public String clt_tp_cd; //押品种类 直接赋押品种类对应的名称
	
	public String clt_nm; //押品名称
	
	public String clt_rmk; //备注 基本信息备注
	
	public double initial_amt; //初始认定价值
	
	public String sys_eval_ccy_cd; //币种 01：人民币：默认为人民币
	
	public String sys_eval_amt; //内部评估价值
	
	public Date sys_eval_dt; //内部评估日期yyyymmdd
	
	public String warrant_no; //权证编号
	
	public String extl_eval_org_name; //外部评估机构名称
	
	public String extl_eval_ccy_cd; //外部评估价值币种  01：人民币：默认为人民币
	
	public String extl_eval_amt; //外部评估价值

	public Date extl_eval_dt; //外部评估日期  yyyymmdd
	
	public String asesType_cd; //评估方式  1-外部预评估；2-外部正式评估；3-内部评估
	
	public String party_id; //客户ID
	
	public String apply_id;//业务ID
	
	public String scene_id;//场景ID  0-押品信息管理 1-贷后重估
	
	public String user_num;//经办人
	
	public String org_num;//经办机构
	
	public String mortgaga_amt;//权利价值
	
	public String getOpe_flag() {
		return ope_flag;
	}

	public void setOpe_flag(String ope_flag) {
		this.ope_flag = ope_flag;
	}

	public String getClt_flag() {
		return clt_flag;
	}

	public void setClt_flag(String clt_flag) {
		this.clt_flag = clt_flag;
	}

	public String getSigneename() {
		return signeename;
	}

	public void setSigneename(String signeename) {
		this.signeename = signeename;
	}

	public String getClt_no() {
		return clt_no;
	}

	public void setClt_no(String clt_no) {
		this.clt_no = clt_no;
	}

	public String getClt_tp_cd() {
		return clt_tp_cd;
	}

	public void setClt_tp_cd(String clt_tp_cd) {
		this.clt_tp_cd = clt_tp_cd;
	}

	public String getClt_nm() {
		return clt_nm;
	}

	public void setClt_nm(String clt_nm) {
		this.clt_nm = clt_nm;
	}

	public String getClt_rmk() {
		return clt_rmk;
	}

	public void setClt_rmk(String clt_rmk) {
		this.clt_rmk = clt_rmk;
	}

	public double getInitial_amt() {
		return initial_amt;
	}

	public void setInitial_amt(double initial_amt) {
		this.initial_amt = initial_amt;
	}

	public String getSys_eval_ccy_cd() {
		return sys_eval_ccy_cd;
	}

	public void setSys_eval_ccy_cd(String sys_eval_ccy_cd) {
		this.sys_eval_ccy_cd = sys_eval_ccy_cd;
	}

	public String getSys_eval_amt() {
		return sys_eval_amt;
	}

	public void setSys_eval_amt(String sys_eval_amt) {
		this.sys_eval_amt = sys_eval_amt;
	}

	public Date getSys_eval_dt() {
		return sys_eval_dt;
	}

	public void setSys_eval_dt(Date sys_eval_dt) {
		this.sys_eval_dt = sys_eval_dt;
	}

	public String getWarrant_no() {
		return warrant_no;
	}

	public void setWarrant_no(String warrant_no) {
		this.warrant_no = warrant_no;
	}

	public String getExtl_eval_org_name() {
		return extl_eval_org_name;
	}

	public void setExtl_eval_org_name(String extl_eval_org_name) {
		this.extl_eval_org_name = extl_eval_org_name;
	}

	public String getExtl_eval_ccy_cd() {
		return extl_eval_ccy_cd;
	}

	public void setExtl_eval_ccy_cd(String extl_eval_ccy_cd) {
		this.extl_eval_ccy_cd = extl_eval_ccy_cd;
	}

	public String getExtl_eval_amt() {
		return extl_eval_amt;
	}

	public void setExtl_eval_amt(String extl_eval_amt) {
		this.extl_eval_amt = extl_eval_amt;
	}

	public Date getExtl_eval_dt() {
		return extl_eval_dt;
	}

	public void setExtl_eval_dt(Date extl_eval_dt) {
		this.extl_eval_dt = extl_eval_dt;
	}

	public String getAsesType_cd() {
		return asesType_cd;
	}

	public void setAsesType_cd(String asesType_cd) {
		this.asesType_cd = asesType_cd;
	}

	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	public String getApply_id() {
		return apply_id;
	}

	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public String getTrans_desc() {
		return trans_desc;
	}

	public void setTrans_desc(String trans_desc) {
		this.trans_desc = trans_desc;
	}

	public String getOri_channel() {
		return ori_channel;
	}

	public void setOri_channel(String ori_channel) {
		this.ori_channel = ori_channel;
	}

	public String getOri_trans_seri_num() {
		return ori_trans_seri_num;
	}

	public void setOri_trans_seri_num(String ori_trans_seri_num) {
		this.ori_trans_seri_num = ori_trans_seri_num;
	}

	public Date getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(Date trans_time) {
		this.trans_time = trans_time;
	}

	public String getUser_num() {
		return user_num;
	}

	public void setUser_num(String user_num) {
		this.user_num = user_num;
	}

	public String getOrg_num() {
		return org_num;
	}

	public void setOrg_num(String org_num) {
		this.org_num = org_num;
	}

	public String getMortgaga_amt() {
		return mortgaga_amt;
	}

	public void setMortgaga_amt(String mortgaga_amt) {
		this.mortgaga_amt = mortgaga_amt;
	}

}
