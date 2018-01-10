/**
 * 
 */
package com.bos.csm.mtmq;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqEsttInf;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqLandBnkgInf;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqSplmtInfArray;
import com.bos.pub.socket.service.response.EsbBodyMtmqRsBsnInfArray;
import com.bos.pub.socket.service.response.EsbBodyMtmqRsCtrInfArray;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

/**
 * @author jiangzhan
 * @date 2016-03-07 14:40:33
 * 
 */
@Bizlet("")
public class CustToMtmq {
	
	public DataObject getRsObj(String returnCode, String returnMsg){
		DataObject rsObj = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");
		rsObj.setString("ReturnCode", returnCode);
		rsObj.setString("ReturnMsg", returnMsg);
		return rsObj;
	}
	
	@Bizlet("修改操作时校验客户信息并返回partyId")
	public DataObject getPartyId(DataObject rqObject){
		DataObject rsObj = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");
		//1.校验客户经理的权限
		String userPlacingCd = "01";
		String msg = checkRight(rqObject, userPlacingCd);
		if(!"".equals(msg)&&null!=msg){
			return getRsObj("00000000000002", msg);
		}
		
		//2.根据客户号查找客户信息
		String partyNum = rqObject.getString("crCstNo");
		if("".equals(partyNum)||null==partyNum){
			return getRsObj("00000000000002", "交易成功，客户号不能为空！");
		}
		DataObject tbCsmParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		tbCsmParty.setString("partyNum", partyNum);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default", tbCsmParty);
		if(result.length==0||result==null){
			rsObj = getRsObj("00000000000001", "交易成功，没有查到客户信息！");
			rsObj.setString("crCstNo", partyNum);
			return rsObj;
		}
		
		//3.更新客户信息
		DataObject party = (DataObject)result[0];
		tbCsmParty.setString("partyId", party.getString("partyId"));
		tbCsmParty.setString("partyTypeCd", "01");
		tbCsmParty.set("updateTime", GitUtil.getBusiDate());
		tbCsmParty.setString("ifDataMove", "3");
		tbCsmParty.setString("partyName", party.getString("partyName"));
		tbCsmParty.setString("ecifPartyNum", rqObject.getString("eCIFCstNo"));
		tbCsmParty.setString("isPotentialCust", "0".equals(party.getString("isPotentialCust"))?rqObject.getString("crExnCstFlg"):"1");
		tbCsmParty.setString("examineState", "0");
		DatabaseUtil.updateEntity("default", tbCsmParty);
		
		//4.返回partyId
		return tbCsmParty;
	}

	/**
	 * 校验客户经理的权限信息
	 * @param rqObject
	 * @param userPlacingCd 需要校验的权限，01管护权，02业务权，00业务或管护权
	 * @return
	 */
	@Bizlet("校验客户经理的权限信息")
	public String checkRight(DataObject rqObject,String userPlacingCd){
		String cstMgrNo = rqObject.getString("cstMgrNo");
		String cstMgrInstNo = rqObject.getString("cstMgrInstNo");
		String ittbrId=rqObject.getString("ittbrId");
		String eCIFCstNo = rqObject.getString("eCIFCstNo");
		String crCstNo= rqObject.getString("crCstNo");
		//查询参数
		Map<String,String>map = new HashMap<String, String>();
		map.put("cstMgrNo", cstMgrNo);
		map.put("cstMgrInstNo", cstMgrInstNo);
		map.put("ittbrId", ittbrId);
		map.put("eCIFCstNo", eCIFCstNo);
		map.put("crCstNo", crCstNo);
		if(!"00".equals(userPlacingCd)){
			map.put("userPlacingCd", userPlacingCd);
		}
		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.csm.mtmq.toMtmq.checkManageRight", map);
		if(result.length==0||result==null){
			return "交易成功，该客户经理权限不够！";
		}else{
			return null;
		}
	}
	
	
	@Bizlet("校验Corporation信息")
	public DataObject toCorporation(DataObject dataObject, DataObject c) {
		try {
			String oprTp = dataObject.getString("oprTp");// 操作类型
			String corpCustomerTypeCd = dataObject.getString("cstTp");// 客户类型
			String isThirdCust = dataObject.getString("thrdPartyCstFlg");// 是否第三方客户
			String areaType = dataObject.getString("dmstInd");// 境内标志
			String contryRegionCd = dataObject.getString("ctyCd");// 国家编码
			String legalName = dataObject.getString("lglPrsnNm");// 法人代表姓名
			String legalCertType = dataObject.getString("lglPrsnIdntTp");// 法人代表证件类型
			String legalCertificateNo = dataObject.getString("lglPrsnIdentNo");// 法人代表证件号码
			String legalCertificateEndDate = dataObject.getString("identEfftEndDt");// 证件到期日
			String registrCd = dataObject.getString("bsnLcsNo");// 注册登记号码
			String registrationType = dataObject.getString("rgstTp");// 登记注册类型
			String registerDate = dataObject.getString("bsnLcsRcrdDt");// 注册登记日期
			String registerEndDate = dataObject.getString("bsnLcsExpDt");// 注册登记证到期日
			String businessScope = dataObject.getString("bsnScop");// 经营范围
			String registerAssetsCurrencyCd = dataObject.getString("rgstCcy");// 注册资本币种
			double registerAssets = dataObject.getDouble("rgstTch");// 注册资本
			String orgRegisterCd = dataObject.getString("orgInstCd");// 组织机构代码
			String orgRegisterEndDate = dataObject.getString("orgInstCdCtfExpDt");// 组织机构代码证到期日
			String middelCode = dataObject.getString("oldLoanCardNo");// 中征码
			String nationalTaxNo = dataObject.getString("natTaxRgstCtfNo");// 国税登记证号码
			String governmentTentNo = dataObject.getString("lclTaxRgstCtfNo");// 地税登记证号码
			String industrialTypeCd = dataObject.getString("idyCd");// 行业门类
			String industrialTypeBigCd = dataObject.getString("idyBrdHdngCd");// 行业大类
			String industrialTypeMidCd = dataObject.getString("idScdClCd");// 行业中类
			String industrialTypeSamllCd = dataObject.getString("idyThrdClCd");// 行业小类
			String fundSrc = dataObject.getString("tchmSrcDsc");// 经费来源

			if ("1".equals(contryRegionCd) && orgRegisterCd == null) {
				c.set("ReturnMsg", "交易成功，境内客户组织结构代码不能为空！");
				return c;
			}

			// 新增客户时要校验注册登记号码，组织机构号码，中证码是否唯一
			if ("add".equals(oprTp)) {
				String msg = "";
				// 校验营业执照号码(注册登记号码)是否唯一
				if (!("".equals(registrCd) || null == registrCd)) {
					msg = excuRule("RCSM_0001", registrCd);
					if(!"".equals(msg)&&null!=msg){
						c.set("ReturnMsg", "交易成功"+msg);
						return c;
					}
				}
				// 校验组织机构号码是否唯一
				if (!("".equals(orgRegisterCd) || null == orgRegisterCd)) {
					msg = excuRule("RCSM_0011", orgRegisterCd);
					if(!"".equals(msg)&&null!=msg){
						c.set("ReturnMsg", "交易成功"+msg);
						return c;
					}
				}
				// 校验中征码是否唯一
				if (!("".equals(middelCode) || null == middelCode)) {
					msg = excuRule("RCSM_0111", middelCode);
					if(!"".equals(msg)&&null!=msg){
						c.set("ReturnMsg", "交易成功"+msg);
						return c;
					}
				}
				//添加三要素（修改时不允许修改）
				c.set("registrCd", registrCd);	// 注册登记号码
				c.set("orgRegisterCd", orgRegisterCd);	// 组织机构代码
				c.set("middelCode", middelCode);	// 中征码
			}

			c.set("corpCustomerTypeCd", corpCustomerTypeCd);
			c.set("isThirdCust", isThirdCust);
			c.set("areaType", areaType);
			c.set("contryRegionCd", contryRegionCd);
			c.set("legalName", legalName);
			c.set("legalCertType", legalCertType);
			c.set("legalCertificateNo", legalCertificateNo);
			c.set("legalCertificateEndDate", GitUtil.toDate(legalCertificateEndDate, "yyyyMMdd"));
			c.set("registrationType", registrationType);
			c.set("registerDate", GitUtil.toDate(registerDate, "yyyyMMdd"));
			c.set("registerEndDate", GitUtil.toDate(registerEndDate, "yyyyMMdd"));
			c.set("businessScope", businessScope);
			c.set("registerAssetsCurrencyCd", registerAssetsCurrencyCd);
			c.set("registerAssets", new java.math.BigDecimal(registerAssets));
			c.set("orgRegisterEndDate", GitUtil.toDate(orgRegisterEndDate, "yyyyMMdd"));
			c.set("nationalTaxNo", nationalTaxNo);
			c.set("governmentTentNo", governmentTentNo);
			c.set("industrialTypeCd", industrialTypeCd);
			c.set("industrialTypeBigCd", industrialTypeBigCd);
			c.set("industrialTypeMidCd", industrialTypeMidCd);
			c.set("industrialTypeSamllCd", industrialTypeSamllCd);
			c.set("fundSrc", fundSrc);
			
		} catch (Exception e) {
			c.set("ReturnMsg", "交易成功，日期或数字格式不正确！");
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}catch (Throwable e) {
			c.set("ReturnMsg", "交易成功，三要素唯一性校验失败！");
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return c;
	}
	public String excuRule(String rid,String para) throws Throwable{
		String componentName = "com.bos.rule.ruleMgr";
		String operationName = "runRule";
		Map<String, String> paraMap = new HashMap<String, String>();
		String eType = "";
		Object[] obj = new Object[3];
		Object[] result = null;
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		
		paraMap.put("certno", para);
		eType = "1";
		obj[0] = rid;
		obj[1] = paraMap;
		obj[2] = eType;
		result = logicComponent.invoke(operationName, obj);
		if("".equals(result[0]+"")||null==result[0]){
			return null;
		}
		return result[0].toString();
	}

	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("返回splmtInfArray")
	public DataObject saveAttachInfo(DataObject dataObject) {
		DataObject tbCsmAttacheInfo = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmAttachedInfo");
		Date time = GitUtil.getBusiDate();
		//根据partyId查附属信息
		String partyId = dataObject.getString("partyId");
		tbCsmAttacheInfo.setString("partyId", partyId);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default", tbCsmAttacheInfo);
		if(result.length!=0&&result!=null){
			String addressId = ((DataObject)result[0]).getString("addressId");
			tbCsmAttacheInfo.setString("addressId", addressId);
		}else{
			tbCsmAttacheInfo.set("createTime", time);
		}
		tbCsmAttacheInfo.set("updateTime", time);
		// 地址信息 - 对公附件信息 注册地址信息
		if (dataObject.getList("esbBodyMtmqSplmtInfArrays").size() > 0) {
			if (dataObject.getList("esbBodyMtmqSplmtInfArrays").size() > 1) {
				tbCsmAttacheInfo.setString("ReturnMsg", "交易成功，附属信息不允许出现多条！");
			} else {
				for (EsbBodyMtmqRqSplmtInfArray adr : (List<EsbBodyMtmqRqSplmtInfArray>) dataObject
						.getList("esbBodyMtmqSplmtInfArrays")) {
					// 注册地址
					tbCsmAttacheInfo.setString("streetAddress", adr.getStrAdr());
					tbCsmAttacheInfo.setString("nationalityCd", adr.getCtyCd());
					tbCsmAttacheInfo.setString("provinceCd", adr.getProvCd());
					tbCsmAttacheInfo.setString("cityCd", adr.getCityCd());
					tbCsmAttacheInfo.setString("district", adr.getDstcCd());
				}
			}
		}
		DatabaseUtil.saveEntity("default", tbCsmAttacheInfo);
		return getRsObj(null, null);
	}
	
	@Bizlet("校验项目基本信息")
	public DataObject addProject(DataObject rqObject, DataObject project) {
		try {
			String prjNm = rqObject.getString("prjNm");// 项目名称 String(100)
			String prjTp = rqObject.getString("prjTp");// 项目类别 String(4)
			String prjLvl = rqObject.getString("prjLvl");// 项目级别 String(4)
			String prjAdr = rqObject.getString("prjAdr");// 项目地址 String(300)
			String ccy = rqObject.getString("ccy");// 币种 String(10)
			double prjTotIvsAmt = rqObject.getDouble("prjTotIvsAmt");// 项目总投资金额Double(20,2)
			String prjFileNo = rqObject.getString("prjFileNo");// 批准立项文件文号String(20)
			String prjReplyOffcNm = rqObject.getString("prjReplyOffcNm");// 批准立项批复机关名称String(100)
			String prjStrtDt = rqObject.getString("prjStrtDt");// 项目开工日   String(8)
			String prjEndDt = rqObject.getString("prjEndDt");// 项目竣工日 String(8)
			String statOwnLandUseRghtCtfNo = rqObject.getString("statOwnLandUseRghtCtfNo");// 国有土地使用权证号String(20)
			String consLandUsePrmtNo = rqObject.getString("consLandUsePrmtNo");// 建设用地规划许可证号String(20)
			String consEngnPlnPrmtNo = rqObject.getString("consEngnPlnPrmtNo");// 建设工程规划许可证号String(20)
			String consEngnWrkPrmtNo = rqObject.getString("consEngnWrkPrmtNo");// 建设工程施工许可证号String(20)
			project.set("projectName", prjNm);
			project.set("projectType", prjTp);
			project.set("projectLevelCd", prjLvl);
			project.set("projectAddress", prjAdr);
			project.set("currencyCd", ccy);
			project.set("projectTotalAmt", new java.math.BigDecimal(prjTotIvsAmt));
			project.set("projectFile", prjFileNo);
			project.set("projectUnit", prjReplyOffcNm);
			project.set("projectStartDate", GitUtil.toDate(prjStrtDt, "yyyyMMdd"));
			project.set("projectEndDate", GitUtil.toDate(prjEndDt, "yyyyMMdd"));
			project.set("eiaPermitNum", statOwnLandUseRghtCtfNo);
			project.set("constructionLandPermitNum", consLandUsePrmtNo);
			project.set("constructionPermitNum", consEngnPlnPrmtNo);
			project.set("planningPermitNo", consEngnWrkPrmtNo);
		} catch (Exception e) {
			project.set("ReturnMsg", "交易成功，日期或数字格式不正确！");
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return project;
	}

	@Bizlet("校验房地产开发项目附属信息")
	public DataObject addRealty(DataObject rqObject, DataObject realty) {
		// 地址信息 - 对公附件信息 注册地址信息
		try {
			EsbBodyMtmqRqEsttInf rel = (EsbBodyMtmqRqEsttInf) rqObject.get("esbBodyMtmqRqEsttInf");
			realty.set("landNature", rel.getLandKnd());
			realty.set("landUseType", rel.getLandUseRghtTp());
			realty.set("purpose", rel.getLandPpsCd());
			realty.set("landUseArea", rel.getLandUseArea());
			realty.set("landUseEndDate", GitUtil.toDate(rel.getLandUseEndDt(), "yyyyMMdd"));
		} catch (Exception e) {
			realty.set("ReturnMsg", "交易成功，数据不合法！");
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return realty;
	}

	@Bizlet("校验土地储备开发贷款附属信息")
	public DataObject addLand(DataObject rqObject, DataObject land) {
		try {
			// 地址信息 - 对公附件信息 注册地址信息
			EsbBodyMtmqRqLandBnkgInf lnd = (EsbBodyMtmqRqLandBnkgInf) rqObject.get("esbBodyMtmqRqLandBnkgInf");
			land.set("landPlanApplyNum", lnd.getLandBnkgPlnReplyFileNo());
			land.set("applyUnit", lnd.getLandBnkgPlnReplyOfficNm());
			land.set("totalArea", lnd.getTotArea());
//			if (rqObject.getList("esbBodyMtmqRqLandBnkgInfArrays").size() > 0) {
//				if (rqObject.getList("esbBodyMtmqRqLandBnkgInfArrays").size() > 1) {
//					land.set("ReturnMsg", "交易成功，同一个项目下不允许出现多个土地储备开发贷款附属信息！");
//				} else {
//					for (EsbBodyMtmqRqLandBnkgInf lnd : (List<EsbBodyMtmqRqLandBnkgInf>) rqObject
//							.getList("esbBodyMtmqRqLandBnkgInfArrays")) {
//						land.set("landPlanApplyNum", lnd.getLandBnkgPlnReplyFileNo());
//						land.set("applyUnit", lnd.getLandBnkgPlnReplyOfficNm());
//						land.set("totalArea", lnd.getTotArea());
//					}
//				}
//			}
		} catch (Exception e) {
			land.set("ReturnMsg", "交易成功，数据不合法！");
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return land;
	}

	@Bizlet("查询合同信息")
	public DataObject findConMsg(DataObject rqObject) {
		DataObject rsObject = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");
		String idntTp = rqObject.getString("idntTp");
		String identNo = rqObject.getString("identNo");
		String ctrSt = rqObject.getString("ctrSt");
		if("".equals(ctrSt)||null == ctrSt){
			ctrSt = "01,02,03";
		}
		if ((!("".equals(idntTp) || null == idntTp)) && ("".equals(identNo) || null == identNo)) {
			rsObject.set("ReturnCode", "00000000000002");
			rsObject.set("ReturnMsg", "交易成功，证件类型类型不为空时证件号码不能为空！");
			return rsObject;
		}
		// 传入分页参数
		int pgRcrdNum = Integer.parseInt(rqObject.getString("pgRcrdNum"));// 每页记录数
		int crnPgNo = Integer.parseInt(rqObject.getString("crnPgNo"));// 当前页码
		// 查询条件
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cstMgrNo", rqObject.getString("cstMgrNo"));// 客户经理
		map.put("ittbrId", rqObject.getString("ittbrId"));// 起始机构号
		map.put("cstNm", rqObject.getString("cstNm"));// 客户名称
		map.put("idntTp", idntTp);// 证件类型
		map.put("identNo", identNo);// 证件号码
		map.put("ctrSt", "("+ctrSt+")");// 合同状态

		DataObject pageCond = DataObjectUtil.createDataObject("com.eos.foundation.PageCond");
		pageCond.set("isCount", true);
		pageCond.set("count", -1);
		pageCond.set("begin", (crnPgNo-1)*pgRcrdNum);
		pageCond.set("length", pgRcrdNum);

		Object[] result = DatabaseExt.queryByNamedSqlWithPage("default", "com.bos.csm.mtmq.toMtmq.findConMsg", pageCond, map);
		if (result == null || result.length == 0) {
			rsObject.set("ReturnCode", "00000000000001");
			rsObject.set("ReturnMsg", "交易成功，没有找到合同信息！");
			return rsObject;
		}
		EsbBodyMtmqRsCtrInfArray[] ctrInfArrays = new EsbBodyMtmqRsCtrInfArray[result.length];
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];// ctrInfArray
			EsbBodyMtmqRsCtrInfArray ctrInfArray = new EsbBodyMtmqRsCtrInfArray();
			ctrInfArray.setCstNm(resultDataObject.getString("PARTY_NAME"));// 客户名称
			ctrInfArray.setIdntTp(resultDataObject.getString("CERT_TYPE"));// 证件类型
			ctrInfArray.setIdentNo(resultDataObject.getString("CERT_NUM"));// 证件类型
			ctrInfArray.setCtrNo(resultDataObject.getString("CONTRACT_NUM")); // 合同号
			ctrInfArray.setBsnTp(resultDataObject.getString("PRODUCT_TYPE")); // 业务类型
			ctrInfArray.setCtrIttDt(resultDataObject.getString("BEGIN_DATE")); // 合同起始日期
			ctrInfArray.setCtrExpDt(resultDataObject.getString("END_DATE")); // 合同到期日
			ctrInfArray.setCtrAmt(resultDataObject.getString("CONTRACT_AMT")); // 合同金额
			ctrInfArray.setImgBsnNo(resultDataObject.getString("BAR_CODE")); // 影像业务编号
			ctrInfArray.setCtrSt(resultDataObject.getString("CON_STATUS")); // 合同状态
			ctrInfArrays[i] = ctrInfArray;
		}
		rsObject.set("ReturnCode", "00000000000000");
		rsObject.set("ReturnMsg", "交易成功，查到合同信息！");
		rsObject.set("esbBodyMtmqRsCtrInfArrays", ctrInfArrays);
		rsObject.set("totRcrdNum", pageCond.getInt("count"));

		return rsObject;

	}
	
	@Bizlet("查询业务申请信息")
	public DataObject findBsnInf(DataObject rqObject) {
		DataObject rsObject = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");
		String idntTp = rqObject.getString("idntTp");
		String identNo = rqObject.getString("identNo");
		String aplySt = "("+rqObject.getString("aplySt")+")";
		if ((!("".equals(idntTp) || null == idntTp)) && ("".equals(identNo) || null == identNo)) {
			rsObject.set("ReturnCode", "00000000000002");
			rsObject.set("ReturnMsg", "交易成功，证件类型类型不为空时证件号码不能为空！");
			return rsObject;
		}
		// 传入分页参数
		int pgRcrdNum = Integer.parseInt(rqObject.getString("pgRcrdNum"));// 每页记录数
		int crnPgNo = Integer.parseInt(rqObject.getString("crnPgNo"));// 当前页码
		// 查询条件
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cstMgrNo", rqObject.getString("cstMgrNo"));// 客户经理
		map.put("ittbrId", rqObject.getString("ittbrId"));// 起始机构号
		map.put("cstNm", rqObject.getString("cstNm"));// 客户名称
		map.put("idntTp", idntTp);// 证件类型
		map.put("identNo", identNo);// 证件号码
		map.put("aplySt", aplySt);// 申请状态

		DataObject pageCond = DataObjectUtil.createDataObject("com.eos.foundation.PageCond");
		pageCond.set("isCount", true);
		pageCond.set("count", -1);
		pageCond.set("begin", (crnPgNo-1)*pgRcrdNum);
		pageCond.set("length", pgRcrdNum);

		Object[] result = DatabaseExt.queryByNamedSqlWithPage("default", "com.bos.csm.mtmq.toMtmq.findBsnInf", pageCond, map);
		if (result == null || result.length == 0) {
			rsObject.set("ReturnCode", "00000000000001");
			rsObject.set("ReturnMsg", "交易成功，没有找到业务信息！");
			return rsObject;
		}
		EsbBodyMtmqRsBsnInfArray[] bsnInfArrays = new EsbBodyMtmqRsBsnInfArray[result.length];
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];
			EsbBodyMtmqRsBsnInfArray bsnInfArray = new EsbBodyMtmqRsBsnInfArray();
			bsnInfArray.setCstNm(resultDataObject.getString("PARTY_NAME"));// 客户名称
			bsnInfArray.setCrCstNo(resultDataObject.getString("PARTY_NUM"));//客户编号
			bsnInfArray.setIdntTp(resultDataObject.getString("CERT_TYPE"));// 证件类型
			bsnInfArray.setIdentNo(resultDataObject.getString("CERT_NUM"));// 证件类型
			bsnInfArray.setBsnNo(resultDataObject.getString("BIZ_NUM")); // 业务编号
			bsnInfArray.setBsnKnd(resultDataObject.getString("BIZ_TYPE")); // 业务编号
			bsnInfArray.setBsnTp(resultDataObject.getString("PRODUCT_TYPE")); // 业务类型
			bsnInfArray.setImgBsnNo(resultDataObject.getString("BAR_CODE")); // 影像业务编号
			bsnInfArray.setAplySt(resultDataObject.getString("STATUS_TYPE")); // 业务申请
			bsnInfArrays[i] = bsnInfArray;
		}
		rsObject.set("ReturnCode", "00000000000000");
		rsObject.set("ReturnMsg", "交易成功，查到业务申请信息！");
		rsObject.set("esbBodyMtmqRsBsnInfArrays", bsnInfArrays);
		rsObject.set("totRcrdNum", pageCond.getInt("count"));
		
		return rsObject;
	}
}
