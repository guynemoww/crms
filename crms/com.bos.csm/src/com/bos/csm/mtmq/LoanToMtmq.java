/**
 * 
 */
package com.bos.csm.mtmq;

import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.service.response.EsbBodyMtmqRsPrjArray;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author chenchuan
 * @date 2016-02-23 10:29:17 面谈面签接口公共类
 */
@Bizlet("")
public class LoanToMtmq {
	/**
	 * 
	 * @param obj
	 * @return
	 */
	@Bizlet("查询客户信息")
	public DataObject findCsmMsg(DataObject obj) {
		DataObject ret = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqRs02003000003BODY01");

		HashMap<String, Object> map = new HashMap<String, Object>();
		String userNum = obj.getString("cstMgrNo");// 客户经理
		String orgNum = obj.getString("cstMgrInstNo");// 客户经理机构号
		String certType = obj.getString("idntTp");// 证件类型
		String certNum = obj.getString("identNo");// 证件号码
		String flag1 = "flag";
		String flag2 = "flag";
		
		if (userNum == null || orgNum == null || certNum == null || certType == null) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000002");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，客户信息要素不完整");
			return ret;
		}
		
		if("201".equals(certType)){
			flag2 = "";
		}else{
			flag1 = "";
		}
		
		map.put("flag1", flag1);
		map.put("flag2", flag2);
		map.put("userNum", userNum);
		map.put("orgNum", orgNum);
		map.put("certType", certType);
		map.put("certNum", certNum);
		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.csm.mtmq.toMtmq.findCsmMsg", map);

		if (result == null || result.length == 0) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000001");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，没有找到对应的客户信息");
			return ret;
		}
		DataObject resultDataObject = (DataObject) result[0];
		ret.set("crCstNo", resultDataObject.getString("PARTY_NUM")); // 客户代号
		ret.set("cstNm", resultDataObject.getString("PARTY_NAME")); // 客户名称
		ret.set("idntTp", resultDataObject.getString("CERT_TYPE")); // 证件类型
		ret.set("identNo", resultDataObject.getString("CERT_NUM")); // 证件号码
		ret.set("cstTp", resultDataObject.getString("PARTY_TYPE_CD")); // 客户类型
		ret.set("blcklistFlg", resultDataObject.getString("WHETHER_BLACK_LIST")); // 黑名单标识
		ret.set("crExnCstFlg", resultDataObject.getString("IS_POTENTIAL_CUST")); // 授信客户标识
		ret.set("blngCstMgrNo", resultDataObject.getString("USER_NUM")); // 管户客户经理
		ret.set("blngInstNo", resultDataObject.getString("ORG_NUM")); // 管户机构号
		ret.set("blngInstNm", resultDataObject.getString("ORG_NAME")); // 管户机构名称
		ret.set("dealPrsnPrvgCd", resultDataObject.getString("USER_PLACING_CD")); // 经办人员权限
		ret.set("imgBsnNo", resultDataObject.getString("BAR_CODE")); // 影像业务编号

		ret.set(EsbSocketConstant.RETURN_CODE, "00000000000000");
		ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，客户信息已返回");
		return ret;

	}

	@Bizlet("对私客户信息赋值校验")
	public DataObject saveNaturalCsm(DataObject obj) {
		DataObject natural = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
		// party表数据
		String op = obj.getString("oprTp"); // 操作类型
		String partyNum = obj.getString("crCstNo"); // 客户代号
		String isPotentialCust = obj.getString("crExnCstFlg"); // 授信客户标识
		String ecifPartyNum = obj.getString("eCIFCstNo");// ECIF客户编号
		// 管理团队表数据
		String userNum = obj.getString("cstMgrNo"); // 客户经理
		String orgNum = obj.getString("cstMgrInstNo"); // 客户经理机构号
		// 自然人表数据
		String certType = obj.getString("idntTp"); // 证件类型
		String certNum = obj.getString("identNo"); // 证件号码
		String partyName = obj.getString("cstNm"); // 客户名称
		String isThirdCust = obj.getString("thrdPartyCstFlg"); // 第三方客户标识
		String genderCd = obj.getString("gndInd"); // 性别标志
		String birthday = obj.getString("birthDate"); // 出生日期
		String countrySign = obj.getString("ntntyCd"); // 国籍代码
		String nation = obj.getString("nation"); // 民族
		String marriageCd = obj.getString("marriageCd"); // 婚姻状况代码
		String hukouProperty = obj.getString("domcKnd"); // 户籍性质
		String hukouRegisted = obj.getString("domcLo"); // 户籍所在地
		String streetPoliceStation = obj.getString("lclPolcStnNm"); // 街道派出所名称
		String profession = obj.getString("ocpCd"); // 职业代码
		String professionalTitle = obj.getString("posTtlCd"); // 职称代码
		String accountingAssistant = obj.getString("posCd"); // 职务代码
		String positionProperty = obj.getString("pstKnd"); // 岗位性质
		String workYears = obj.getString("coWrkTrm"); // 现单位工作年限
		String familyAddress = obj.getString("famAdr"); // 家庭住址
		String phoneNumber = obj.getString("mblNo"); // 移动号码
		String workUnit = obj.getString("corpNm"); // 工作单位
		String unitPhone = obj.getString("offcTel"); // 办公电话
		String isFarmer = obj.getString("frmrFlg"); // 农户标识
		String healthState = obj.getString("hltSt"); // 健康状况
		// 数据校验
		if (null == op || !("update".equals(op) || "add".equals(op))) {
			natural.set(EsbSocketConstant.RETURN_MSG, "操作类型不符合规范");
			return natural;
		}
		if (null == userNum || null == orgNum) {
			natural.set(EsbSocketConstant.RETURN_MSG, "客户经理和客户经理机构号不能为空");
			return natural;
		}
		if ("update".equals(op)) {
			if (null == partyNum) {
				natural.set(EsbSocketConstant.RETURN_MSG, "操作类型为update时，客户代号不能为空");
				return natural;
			}
			CustToMtmq custToMtmq = new CustToMtmq();
			if (null != custToMtmq.checkRight(obj,"01")) {
				natural.set(EsbSocketConstant.RETURN_MSG, custToMtmq.checkRight(obj,"01"));
				return natural;
			}
		}

		if ("add".equals(op)) {
			if (!(null == partyNum || "".equals(partyNum))) {
				natural.set(EsbSocketConstant.RETURN_MSG, "操作类型为add时，客户代号应该为空");
				return natural;
			}
			if (null == ecifPartyNum || "".equals(ecifPartyNum)) {
				natural.set(EsbSocketConstant.RETURN_MSG, "操作类型为add时，ECIF客户编号不能为空");
				return natural;
			}
			natural.set("certType", certType); // 证件类型
			natural.set("certNum", certNum); // 证件号码
			DataObject[] naturals = DatabaseUtil.queryEntitiesByTemplate("default", natural);
			if (naturals.length > 0) {
				natural.set(EsbSocketConstant.RETURN_MSG, "证件号码" + certNum + "已经存在");
				return natural;
			}
		}

		natural.set("userNum", userNum); // 客户经理
		natural.set("orgNum", orgNum); // 客户经理机构号
		// natural.set("partyName", partyName); // 客户名称
		natural.set("isThirdCust", isThirdCust); // 第三方客户标识
		natural.set("genderCd", genderCd); // 性别标志
		natural.set("birthday", GitUtil.toDate(birthday, "yyyyMMdd")); // 出生日期
		natural.set("countrySign", countrySign); // 国籍代码
		natural.set("nation", nation); // 民族
		natural.set("marriageCd", marriageCd); // 婚姻状况代码
		natural.set("hukouProperty", hukouProperty); // 户籍性质
		natural.set("hukouRegisted", hukouRegisted); // 户籍所在地
		natural.set("streetPoliceStation", streetPoliceStation); // 街道派出所名称
		natural.set("profession", profession); // 职业代码
		natural.set("professionalTitle", professionalTitle); // 职称代码
		natural.set("accountingAssistant", accountingAssistant); // 职务代码
		natural.set("positionProperty", positionProperty); // 岗位性质
		natural.set("workYears", workYears); // 现单位工作年限
		natural.set("familyAddress", familyAddress); // 家庭住址
		natural.set("phoneNumber", phoneNumber); // 移动号码
		natural.set("workUnit", workUnit); // 工作单位
		natural.set("unitPhone", unitPhone); // 办公电话
		natural.set("isFarmer", isFarmer); // 农户标识
		natural.set("healthState", healthState); // 健康状况

		return natural;

	}

	@Bizlet("查询项目信息")
	public DataObject findProjectMsg(DataObject obj) {
		DataObject ret = DataObjectUtil.createDataObject("com.bos.pub.meta.TbPubDate");

		String userNum = obj.getString("cstMgrNo");// 客户经理
		String orgNum = obj.getString("ittbrId");// 起始机构号
		String certType = obj.getString("idntTp");// 证件类型
		String certNum = obj.getString("identNo");// 证件号码
		String projectName = obj.getString("prjNm");// 项目名称
		int pgRcrdNum = Integer.parseInt(obj.getString("pgRcrdNum"));// 每页记录数
		int crnPgNo = Integer.parseInt(obj.getString("crnPgNo"));// 当前页码

		if (userNum == null || orgNum == null) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000002");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，项目信息要素不完整");
			return ret;
		}
		if ((certType != null && certNum == null) || (certType == null && certNum != null)) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000002");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，证件类型和证件号码必须成对出现");
			return ret;
		}
		
		//查询参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userNum", userNum);
		map.put("orgNum", orgNum);
		map.put("certType", certType);
		map.put("certNum", certNum);
		map.put("projectName", projectName);

		//分页参数
		DataObject pageCond = DataObjectUtil.createDataObject("com.eos.foundation.PageCond");
		pageCond.set("isCount", true);
		pageCond.set("count", -1);
		pageCond.set("begin", (crnPgNo-1)*pgRcrdNum);
		pageCond.set("length", pgRcrdNum);

		Object[] result = DatabaseExt.queryByNamedSqlWithPage("default", "com.bos.csm.mtmq.toMtmq.findProjectMsg", pageCond, map);

		if (result == null || result.length == 0) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000001");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，没有找到对应的项目信息");
			return ret;
		}
		// 将查询到的数据放到ret中
		EsbBodyMtmqRsPrjArray[] projectArrays = new EsbBodyMtmqRsPrjArray[result.length];
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];

			ret.set("totRcrdNum", pageCond.getInt("count")); // 总记录数

			EsbBodyMtmqRsPrjArray projectArray = new EsbBodyMtmqRsPrjArray();
			projectArray.setImgBsnNo(resultDataObject.getString("BAR_CODE"));// 影像批次号
			projectArray.setPrjNo(resultDataObject.getString("PROJECT_ID"));// 项目编号
			projectArray.setPrjNm(resultDataObject.getString("PROJECT_NAME"));// 项目名称
			projectArray.setCrtDt(resultDataObject.getString("CREATE_TIME"));// 创建时间
			projectArray.setCrCstNo(resultDataObject.getString("PARTY_NUM")); // 客户代号
			projectArray.setCstNm(resultDataObject.getString("PARTY_NAME")); // 客户名称
			projectArray.setIdntTp(resultDataObject.getString("CERT_TYPE")); // 证件类型
			projectArray.setIdentNo(resultDataObject.getString("CERT_NUM")); // 证件号码
			projectArrays[i] = projectArray;
		}

		ret.set("esbBodyMtmqRsPrjArrays", projectArrays);

		ret.set(EsbSocketConstant.RETURN_CODE, "00000000000000");
		ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，项目信息已返回");
		return ret;

	}

	@Bizlet("查询抵质押品信息")
	public DataObject findDzyMsg(DataObject obj) {
		DataObject ret = DataObjectUtil.createDataObject("com.bos.pub.meta.TbPubDate");

		HashMap<String, Object> map = new HashMap<String, Object>();
		String userNum = obj.getString("cstMgrNo");// 客户经理
		String orgNum = obj.getString("ittbrId");// 起始机构号
		String sortType = obj.getString("plgTp");// 押品类型
		String suretyNo = obj.getString("plgNo");// 押品编号
		if (userNum == null || orgNum == null || sortType == null || suretyNo == null) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000002");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，押品信息要素不完整");
			return ret;
		}
		map.put("userNum", userNum);
		map.put("orgNum", orgNum);
		map.put("sortType", sortType);
		map.put("suretyNo", suretyNo);
		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.csm.mtmq.toMtmq.findDzyMsg", map);

		if (result == null || result.length == 0) {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000001");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，没有找到对应的押品信息");
			return ret;
		}
		DataObject resultDataObject = (DataObject) result[0];
		ret.set("plgNo", resultDataObject.getString("SURETY_NO")); // 押品编号
		ret.set("plgTp", resultDataObject.getString("SORT_TYPE")); // 押品类型
		ret.set("plgSt", resultDataObject.getString("MORTGAGE_STATUS")); // 押品状态
		ret.set("crtrNm", resultDataObject.getString("EMPNAME")); // 创建人名称

		ret.set(EsbSocketConstant.RETURN_CODE, "00000000000000");
		ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，押品信息已返回");
		return ret;

	}
}
