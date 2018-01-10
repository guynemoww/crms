package com.bos.bizApply;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;
import edu.emory.mathcs.backport.java.util.Arrays;

@Bizlet("")
public class ApplyCheck {

	// 检查贴现时间
	@Bizlet("检查贴现信息")
	public HashMap<String, String> checkTX(DataObject tbBizTxxxApply) {
		int term = 6; // 纸票期限最长6个月
		HashMap<String, String> retuMap = new HashMap<String, String>();
		retuMap.put("check", "0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 票据号码
		String billtype = tbBizTxxxApply.getString("billtype");
		String billno = tbBizTxxxApply.getString("billno");
		if ("0".equals(billtype)) { // 银行承兑汇票
			if (billno.length() != 16 || !"0".equals(billno.substring(3, 4)) || !"5".equals(billno.substring(6, 7))) {
				retuMap.put("check", "1");
				retuMap.put("msg", "纸质银行承兑汇票由16位数字组成，第四位为0，第七位为5");
				return retuMap;
			}
		}
		if ("1".equals(billtype)) {// 商业承兑汇票
			if (billno.length() != 16 || !"0".equals(billno.substring(3, 4)) || !"6".equals(billno.substring(6, 7))) {
				retuMap.put("check", "1");
				retuMap.put("msg", "纸质商业承兑汇票由16位数字组成，第四位为0，第七位为6");
				return retuMap;
			}
		}
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("amountDetailId", tbBizTxxxApply.getString("amountDetailId"));
		Object[] bizs=DatabaseExt.queryByNamedSql("default", "com.bos.biz.bizCollateral.getBizRate", paramMap);
		if( null != bizs && bizs.length>0 ){
			DataObject biz=(DataObject) bizs[0];
			BigDecimal bizRate = biz.getBigDecimal("BIZRATE");
			BigDecimal txRate = tbBizTxxxApply.getBigDecimal("interate");
			if(txRate!=null && bizRate!= null && bizRate.compareTo(txRate)!=0){
				retuMap.put("check", "1");
				retuMap.put("msg", "业务申请利率与票据信息利率不一致，请检查");
				return retuMap;
			}
		}
		// 业务日期
		String busiDate = GitUtil.getBusiDateYYYYMMDD();
		Calendar ca_busi = Calendar.getInstance();
		try {
			ca_busi.setTime(sdf.parse(busiDate));
		} catch (ParseException e) {
			retuMap.put("check", "1");
			retuMap.put("msg", "获取业务时间错误");
			return retuMap;
		}
		// 出票日期
		Date tb_beginDate = tbBizTxxxApply.getDate("billbegindate");
		Calendar ca_begin = Calendar.getInstance();
		ca_begin.setTime(tb_beginDate);
		// 到期日期
		Date tb_endDate = tbBizTxxxApply.getDate("billenddate");
		Calendar ca_end = Calendar.getInstance();
		ca_end.setTime(tb_endDate);

		if (ca_begin.after(ca_busi)) {
			retuMap.put("check", "1");
			retuMap.put("msg", "纸票票据出票日期必须小于当前日期" + GitUtil.getBusiDateYYYYMMDD());
			return retuMap;
		}

		ca_begin.add(Calendar.MONTH, term);
		if (ca_end.after(ca_begin)) {
			retuMap.put("check", "1");
			retuMap.put("msg", "纸票票据期限不能大于" + term + "个月");
			return retuMap;
		}

		return retuMap;
	}
	
	
	@Bizlet("检查担保方式")
	public String checkGuaType(String applyId,String guatype) {
		String msg = "";
		String flag = "";
		try {
			DataObject bizApp = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApp.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApp);
			
			if (null != bizApp.get("productType") && !"".equals(bizApp.get("productType"))) {
				String productType = (String) bizApp.get("productType");
				msg = "false";
				if ("02002004".equals(productType) || "02002005".equals(productType) ) {
					//查询担保方式是否以包含抵押，不存在信用
					String[] guaArray = guatype.split(",");
					boolean type02 = Arrays.asList(guaArray).contains("02"); //是否存在抵押
					boolean type01 = Arrays.asList(guaArray).contains("01"); //是否存在信用
					if(type02 != true || type01 == true){
						msg = "true";
					}
					
					if ("true".equals(msg)) { // 校验不成功返回校验失败结果
						flag = "1";
					}
				}else if("02005001".equals(productType)){
					//公积金住房委托贷款，控制为，选择保证+抵押或者抵押方式
					if("02,04".equals(guatype) || "02".equals(guatype) || "04,02".equals(guatype)){
					}else{
						msg = "true";
					}
						
					if ("true".equals(msg)) { // 校验不成功返回校验失败结果
						flag = "2";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@Bizlet("检查担保方式-担保方式，业务种类")
	public String checkGuaTypeByType(String productType,String guatype) {
		String msg = "";
		String flag = "";
		try {
			msg = "false";
			if ("02002004".equals(productType) || "02002005".equals(productType) ) {
				//查询担保方式是否以包含抵押，不存在信用
				String[] guaArray = guatype.split(",");
				boolean type02 = Arrays.asList(guaArray).contains("02"); //是否存在抵押
				boolean type01 = Arrays.asList(guaArray).contains("01"); //是否存在信用
				if(type02 != true || type01 == true){
					msg = "true";
				}
				
				if ("true".equals(msg)) { // 校验不成功返回校验失败结果
					flag = "1";
				}
			}else if("02005001".equals(productType)){
				//公积金住房委托贷款，控制为，选择保证+抵押或者抵押方式
				if("02,04".equals(guatype) || "02".equals(guatype) || "04,02".equals(guatype)){
				}else{
					msg = "true";
				}
					
				if ("true".equals(msg)) { // 校验不成功返回校验失败结果
					flag = "2";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
