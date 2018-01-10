package com.bos.csm.ecif;

import java.util.List;
import java.util.Map;

import com.bos.pub.socket.service.request.EsbBodyEcifRqCtcInfArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqGCtcInfArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqGKeyPrsnInfArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqGLoAdrArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqLoAdrArray;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.workflow.commons.utility.StringUtil;

import commonj.sdo.DataObject;

@Bizlet("返回管理系统数据转换")
public class toCSM {
	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("ecif返回转Corporation")
	public static DataObject toCorporation(DataObject dataObject, DataObject c) {
		if (StringUtil.isNotNull(dataObject.getString("idyCd"))) {
			c.setString("industrialTypeCd", dataObject.getString("idyCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("listCoFlg"))) {
			c.setString("listingCorporation", dataObject.getString("listCoFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("rgstTch"))) {
			c.setString("registerAssets", dataObject.getString("rgstTch"));
		}
		if (StringUtil.isNotNull(dataObject.getString("lclTaxRgstCtfNo"))) {
			c.setString("governmentTentNo", dataObject.getString("lclTaxRgstCtfNo"));
		}
		if (StringUtil.isNotNull(dataObject.getString("empeNum"))) {
			c.setString("employeesNumber", dataObject.getString("empeNum"));
		}
		if (StringUtil.isNotNull(dataObject.getString("identNo"))) {
			c.setString("legalCertificateNo", dataObject.getString("identNo"));
		}
		if (StringUtil.isNotNull(dataObject.getString("rgstTp"))) {
			c.setString("registrationType", dataObject.getString("rgstTp"));
		}
		if (StringUtil.isNotNull(dataObject.getString("ctyCd"))) {
			c.setString("contryRegionCd", dataObject.getString("ctyCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("bsnScop"))) {
			c.setString("businessScope", dataObject.getString("bsnScop"));
		}
		if (StringUtil.isNotNull(dataObject.getString("natTaxRgstCtfNo"))) {
			c.setString("nationalTaxNo", dataObject.getString("natTaxRgstCtfNo"));
		}
		if (StringUtil.isNotNull(dataObject.getString("idyInvlCd"))) {
			c.setString("industrialTypeBigCd", dataObject.getString("idyInvlCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("idyInvlCd"))) {
			c.setString("industrialTypeMidCd", dataObject.getString("idyInvlCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("idyInvlCd"))) {
			c.setString("industrialTypeSamllCd", dataObject.getString("idyInvlCd"));
		}
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("enterpriseScaleGx",
		// dataObject.getString("entpMgtSclCd"));
		// }
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("countBoreEnterScale",
		// dataObject.getString("entpMgtSclCd"));
		// }
		if (StringUtil.isNotNull(dataObject.getString("imprExprtRghtFlg"))) {
			c.setString("whetherImpExp", dataObject.getString("imprExprtRghtFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("imptCstFlg"))) {
			c.setString("focusCustomer", dataObject.getString("imptCstFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("esttDvlpmFlg"))) {
			c.setString("isRealEstateDev", dataObject.getString("esttDvlpmFlg"));
		}
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("countBoreEnterScale",
		// dataObject.getString("entpMgtSclCd"));
		// }
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("countBoreEnterScale",
		// dataObject.getString("entpMgtSclCd"));
		// }
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("countBoreEnterScale",
		// dataObject.getString("entpMgtSclCd"));
		// }
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("countBoreEnterScale",
		// dataObject.getString("entpMgtSclCd"));
		// }
		// if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
		// c.setString("countBoreEnterScale",
		// dataObject.getString("entpMgtSclCd"));
		// }
		if (StringUtil.isNotNull(dataObject.getString("fncPltfmFlg"))) {
			c.setString("whetherGovernmentFinanceCor", dataObject.getString("fncPltfmFlg"));
		}
		try {
			// 关键人 - 对公法人信息
			if (dataObject.getList("esbBodyEcifRqGKeyPrsnInfArrays").size() > 0) {
				for (EsbBodyEcifRqGKeyPrsnInfArray adr : (List<EsbBodyEcifRqGKeyPrsnInfArray>) dataObject
						.getList("esbBodyEcifRqGKeyPrsnInfArrays")) {
					if ("2".equals(adr.getShrhlrTp())) {
						// 法人信息
						c.setString("legalName", adr.getShrhlrNm());
						c.setString("legalCertType", adr.getIdntTp());
						c.setString("legalCertificateNo", adr.getIdentNo());
					}
				}
			}
		} catch (Exception e) {

		}
		return c;
	}

	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("ecif返回转Natural")
	public static DataObject toNatural(DataObject dataObject, DataObject c) {
		if (StringUtil.isNotNull(dataObject.getString("enNm"))) {
			c.setString("englishName", dataObject.getString("enNm"));
		}
		if (StringUtil.isNotNull(dataObject.getString("gndInd"))) {
			c.setString("genderCd", dataObject.getString("gndInd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("hltSt"))) {
			c.setString("marriageCd", dataObject.getString("hltSt"));
		}
		if (StringUtil.isNotNull(dataObject.getString("dgrCd"))) {
			c.setString("degreeCd", dataObject.getString("dgrCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("highEdct"))) {
			c.setString("educationBackgroudCd", dataObject.getString("highEdct"));
		}
		if (StringUtil.isNotNull(dataObject.getString("nation"))) {
			c.setString("nation", dataObject.getString("nation"));
		}
		if (StringUtil.isNotNull(dataObject.getString("coNm"))) {
			c.setString("workUnit", dataObject.getString("coNm"));
		}
		if (StringUtil.isNotNull(dataObject.getString("hltSt"))) {
			c.setString("healthState", dataObject.getString("hltSt"));
		}
		if (StringUtil.isNotNull(dataObject.getString("ocpCd"))) {
			c.setString("profession", dataObject.getString("ocpCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("posTtlCd"))) {
			c.setString("professionalTitle", dataObject.getString("posTtlCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("posCd"))) {
			c.setString("accountingAssistant", dataObject.getString("posCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("strtWrkDate"))) {
			c.setString("workYears", dataObject.getString("strtWrkDate"));
		}
		if (StringUtil.isNotNull(dataObject.getString("idyInvlCd"))) {
			c.setString("industry", dataObject.getString("idyInvlCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("wrkBnkStfLvl"))) {
			c.setString("isBankEmployee", "1");
			c.setString("employeeGrade", dataObject.getString("wrkBnkStfLvl"));
		}

		if (StringUtil.isNotNull(dataObject.getString("bnkStkhdFlg"))) {
			c.setString("stockholderOfBank", dataObject.getString("bnkStkhdFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("ntntyCd"))) {
			c.setString("countrySign", dataObject.getString("ntntyCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("imptCstFlg"))) {
			c.setString("focusCustomer", dataObject.getString("imptCstFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("esttDvlpmFlg"))) {
			c.setString("isRealEstateDev", dataObject.getString("esttDvlpmFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
			c.setString("countBoreEnterScale", dataObject.getString("entpMgtSclCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
			c.setString("countBoreEnterScale", dataObject.getString("entpMgtSclCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
			c.setString("countBoreEnterScale", dataObject.getString("entpMgtSclCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
			c.setString("countBoreEnterScale", dataObject.getString("entpMgtSclCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("entpMgtSclCd"))) {
			c.setString("countBoreEnterScale", dataObject.getString("entpMgtSclCd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("hshdRgstKnd"))) {
			c.setString("hukouProperty", dataObject.getString("hshdRgstKnd"));
		}
		if (StringUtil.isNotNull(dataObject.getString("hsTp"))) {
			c.setString("houseProperty", dataObject.getString("hsTp"));
		}
		if (StringUtil.isNotNull(dataObject.getString("wrkBnkStfLvl"))) {
			c.setString("employeeGrade", dataObject.getString("wrkBnkStfLvl"));
		}
		try {
			if (dataObject.getList("esbBodyEcifRqLoAdrArrays").size() > 0) {
				for (EsbBodyEcifRqLoAdrArray adr : (List<EsbBodyEcifRqLoAdrArray>) dataObject
						.getList("esbBodyEcifRqLoAdrArrays")) {
					if ("114".equals(adr.getAdrTp())) {
						// 家庭地址
						c.setString("familyAddress", adr.getCtcAdr());
					} else if ("112".equals(adr.getAdrTp())) {
						// 公司地址
						c.setString("unitAdress", adr.getCtcAdr());
					}
				}
			}

			if (dataObject.getList("esbBodyEcifRqCtcInfArrays").size() > 0) {
				for (EsbBodyEcifRqCtcInfArray ctcIn : (List<EsbBodyEcifRqCtcInfArray>) dataObject
						.getList("esbBodyEcifRqCtcInfArrays")) {
					if ("13".equals(ctcIn.getCtcMth())) {
						// 移动电话
						c.setString("phoneNumber", ctcIn.getCtcInf());
					} else if ("11".equals(ctcIn.getCtcMth())) {
						// 家庭电话
						c.setString("familyPhone", ctcIn.getCtcInf());
					} else if ("12".equals(ctcIn.getCtcMth())) {
						// 单位
						c.setString("unitPhone", ctcIn.getCtcInf());
					}
				}
			}
		} catch (Exception e) {

		}
		return c;
	}

	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("ecif返回转Financial")
	public static DataObject toFinancial(DataObject dataObject, DataObject c) {
		if (StringUtil.isNotNull(dataObject.getString("enNm"))) {
			c.setString("englishCustomerName", dataObject.getString("enNm"));
		}
		if (StringUtil.isNotNull(dataObject.getString("rgstTch"))) {
			c.setString("registerAssets", dataObject.getString("rgstTch"));
		}
		if (StringUtil.isNotNull(dataObject.getString("rgstCcy"))) {
			c.setString("registerAssetsCurrencyCd", dataObject.getString("rgstCcy"));
		}
		if (StringUtil.isNotNull(dataObject.getString("listCoFlg"))) {
			c.setString("listingCorporation", dataObject.getString("listCoFlg"));
		}
		if (StringUtil.isNotNull(dataObject.getString("dmstInd"))) {
			c.setString("areaType", dataObject.getString("dmstInd"));
		}
		try {
			// 地址信息 - 对公附件信息 注册地址信息
			if (dataObject.getList("esbBodyEcifRqGLoAdrArrays").size() > 0) {
				for (EsbBodyEcifRqGLoAdrArray adr : (List<EsbBodyEcifRqGLoAdrArray>) dataObject
						.getList("esbBodyEcifRqGLoAdrArrays")) {
					if ("210".equals(adr.getAdrTp())) {
						// 注册地址
						c.setString("street", adr.getStrAdr());
						c.setString("country", adr.getCtyCd());
						c.setString("province", adr.getProvCd());
						c.setString("city", adr.getDstcCd());
						c.setString("county", adr.getCityCd());
						c.setString("regAdministrativeDivisions", adr.getDstcCd());
						c.setString("jyAddress", adr.getOffcAdr());
					}
				}
			}
		} catch (Exception e) {

		}

		return c;
	}

	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("ecif返回转Corporation")
	public static DataObject toAttachedInfo(DataObject dataObject, DataObject c) {
		try {
			// 地址信息 - 对公附件信息 注册地址信息
			if (dataObject.getList("esbBodyEcifRqGLoAdrArrays").size() > 0) {
				for (EsbBodyEcifRqGLoAdrArray adr : (List<EsbBodyEcifRqGLoAdrArray>) dataObject
						.getList("esbBodyEcifRqGLoAdrArrays")) {
					if ("210".equals(adr.getAdrTp())) {
						// 注册地址
						c.setString("streetAddress", adr.getStrAdr());
						c.setString("nationalityCd", adr.getCtyCd());
						c.setString("provinceCd", adr.getProvCd()+"000000");
						c.setString("cityCd", adr.getCityCd()+"000000");
						c.setString("district", adr.getDstcCd()+"000000");
						c.setString("regAdministrativeDivisions", adr.getDstcCd());
						c.setString("addressValue", adr.getOffcAdr());
					}
				}
			}

			if (dataObject.getList("esbBodyEcifRqGCtcInfArrays").size() > 0) {
				for (EsbBodyEcifRqGCtcInfArray ctc : (List<EsbBodyEcifRqGCtcInfArray>) dataObject
						.getList("esbBodyEcifRqGCtcInfArrays")) {
					if ("10".equals(ctc.getCtcMth())) {
						c.setString("telephone", ctc.getCtcInf());
					} else if ("14".equals(ctc.getCtcMth())) {
						c.setString("fax", ctc.getCtcInf());
					} else if ("23".equals(ctc.getCtcMth())) {
						c.setString("website", ctc.getCtcInf());
					} else if ("13".equals(ctc.getCtcMth())) {
						c.setString("accountContactsPhone", ctc.getCtcInf());
					} else if ("30".equals(ctc.getCtcMth())) {
						c.setString("email", ctc.getCtcInf());
					} else if ("45".equals(ctc.getCtcMth())) {
						c.setString("zipNum", ctc.getCtcInf());
					}
				}
			}

		} catch (Exception e) {

		}
		return c;
	}

	/**
	 * 根据数据对象返回关键人信息数组
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("根据数据对象返回关键人信息数组")
	public static EsbBodyEcifRqGKeyPrsnInfArray[] couverArray(Object[] rows) {
		EsbBodyEcifRqGKeyPrsnInfArray[] kpifs = new EsbBodyEcifRqGKeyPrsnInfArray[rows.length];

		if (null != rows && rows.length > 0) {

			for (int i = 0; i < rows.length; i++) {
				EsbBodyEcifRqGKeyPrsnInfArray kpif = new EsbBodyEcifRqGKeyPrsnInfArray();
				Map<String, String> data = (Map<String, String>) rows[i];
				kpif.setShrhlrTp(String.valueOf(data.get("relatedCd")));// 股东类型
				kpif.setShrhlrNm(String.valueOf(data.get("partyName")));// 股东姓名
				kpif.setIdentNo(String.valueOf(data.get("certNum")));// 股东证件号
				kpif.setIdntTp(String.valueOf(data.get("certType")));// 股东证件类型
				kpif.setKeyPrsnSt("01");
				kpifs[i] = kpif;
			}
		}
		return kpifs;

	}

	/**
	 * 根据自然人数据对象返回联系信息数组
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("根据自然人数据对象返回联系信息数组")
	public static EsbBodyEcifRqCtcInfArray[] connectArray(DataObject natural) {
		EsbBodyEcifRqCtcInfArray[] ctcs = new EsbBodyEcifRqCtcInfArray[3];
		String phoneNumber = String.valueOf(natural.get("phoneNumber"));
		String unitPhone = String.valueOf(natural.get("unitPhone"));
		String familyPhone = String.valueOf(natural.get("familyPhone"));
		if (phoneNumber != null && !"".equals(phoneNumber) && !"null".equals(phoneNumber)) {
			EsbBodyEcifRqCtcInfArray ctc = new EsbBodyEcifRqCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(phoneNumber);
			ctc.setCtcMth("13");
			ctcs[0] = ctc;
		}
		if (unitPhone != null && !"".equals(unitPhone) && !"null".equals(unitPhone)) {
			EsbBodyEcifRqCtcInfArray ctc = new EsbBodyEcifRqCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(unitPhone);
			ctc.setCtcMth("12");
			ctcs[1] = ctc;
		}
		if (familyPhone != null && !"".equals(familyPhone) && !"null".equals(familyPhone)) {
			EsbBodyEcifRqCtcInfArray ctc = new EsbBodyEcifRqCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(familyPhone);
			ctc.setCtcMth("11");
			ctcs[2] = ctc;
		}

		return ctcs;

	}

	/**
	 * 根据对公附属数据对象返回联系信息数组
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("根据对公附属数据对象返回联系信息数组")
	public static EsbBodyEcifRqGCtcInfArray[] connectCorpArray(DataObject attachedInfo) {
		EsbBodyEcifRqGCtcInfArray[] ctcs = new EsbBodyEcifRqGCtcInfArray[6];
		String telephone = String.valueOf(attachedInfo.get("telephone"));// 固定电话
		String fax = String.valueOf(attachedInfo.get("fax"));// 传真
		String website = String.valueOf(attachedInfo.get("website"));// 网址
		String accountContactsPhone = String.valueOf(attachedInfo.get("accountContactsPhone"));// 联系人电话
		String email = String.valueOf(attachedInfo.get("email"));// 电子邮件
		String zipNum = String.valueOf(attachedInfo.get("zipNum"));// 电子邮件

		if (telephone != null && !"".equals(telephone) && !"null".equals(telephone)) {
			EsbBodyEcifRqGCtcInfArray ctc = new EsbBodyEcifRqGCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(telephone);
			ctc.setCtcMth("10");
			ctcs[0] = ctc;
		}
		if (fax != null && !"".equals(fax) && !"null".equals(fax)) {
			EsbBodyEcifRqGCtcInfArray ctc = new EsbBodyEcifRqGCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(fax);
			ctc.setCtcMth("14");
			ctcs[1] = ctc;
		}
		if (website != null && !"".equals(website) && !"null".equals(website)) {
			EsbBodyEcifRqGCtcInfArray ctc = new EsbBodyEcifRqGCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(website);
			ctc.setCtcMth("23");
			ctcs[2] = ctc;
		}

		if (accountContactsPhone != null && !"".equals(accountContactsPhone) && !"null".equals(accountContactsPhone)) {
			EsbBodyEcifRqGCtcInfArray ctc = new EsbBodyEcifRqGCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(accountContactsPhone);
			ctc.setCtcMth("13");
			ctcs[3] = ctc;
		}

		if (email != null && !"".equals(email) && !"null".equals(email)) {
			EsbBodyEcifRqGCtcInfArray ctc = new EsbBodyEcifRqGCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(email);
			ctc.setCtcMth("30");
			ctcs[4] = ctc;
		}

		if (zipNum != null && !"".equals(zipNum) && !"null".equals(zipNum)) {
			EsbBodyEcifRqGCtcInfArray ctc = new EsbBodyEcifRqGCtcInfArray();
			ctc.setCtcCd("1");
			ctc.setCtcInf(zipNum);
			ctc.setCtcMth("45");
			ctcs[5] = ctc;
		}

		return ctcs;

	}

	/**
	 * 根据数据对象返回地址信息数组
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("根据数据对象返回地址信息数组")
	public static EsbBodyEcifRqLoAdrArray[] addressArray(DataObject natural) {
		EsbBodyEcifRqLoAdrArray[] adds = new EsbBodyEcifRqLoAdrArray[2];
		String familyAddress = String.valueOf(natural.get("familyAddress"));
		String unitAdress = String.valueOf(natural.get("unitAdress"));
		if (familyAddress != null && !"".equals(familyAddress) && !"null".equals(familyAddress)) {
			EsbBodyEcifRqLoAdrArray add = new EsbBodyEcifRqLoAdrArray();
			add.setAdrCd("1");
			add.setAdrTp("114");
			add.setCtcAdr(familyAddress);
			adds[0] = add;
		}
		if (unitAdress != null && !"".equals(unitAdress) && !"null".equals(unitAdress)) {
			EsbBodyEcifRqLoAdrArray add = new EsbBodyEcifRqLoAdrArray();
			add.setAdrCd("1");
			add.setAdrTp("112");
			add.setCtcAdr(unitAdress);
			adds[1] = add;
		}
		return adds;

	}

	/**
	 * 企业规模转码
	 * 
	 * @param String
	 * @param String
	 */
	@Bizlet("企业规模转码成标准数据")
	public static String getEnterpriseScaleGx(String enterpriseScaleGx) {
		String newEnterpriseScaleGx = null;
		if (null != enterpriseScaleGx && !"".equals(enterpriseScaleGx)) {
			if ("1".equals(enterpriseScaleGx)) {
				newEnterpriseScaleGx = "CS02";
			} else if ("2".equals(enterpriseScaleGx)) {
				newEnterpriseScaleGx = "CS03";
			} else if ("3".equals(enterpriseScaleGx)) {
				newEnterpriseScaleGx = "CS04";
			} else if ("4".equals(enterpriseScaleGx)) {
				newEnterpriseScaleGx = "CS05";
			} else if ("5".equals(enterpriseScaleGx)) {
				newEnterpriseScaleGx = "-1";
			}
		}
		return newEnterpriseScaleGx;
	}

	/**
	 * 职业转码
	 * 
	 * @param String
	 * @param String
	 */
	@Bizlet("职业成标准数据")
	public static String getProfession(String profession) {
		String newProfession = null;
		if (null != profession && !"".equals(profession)) {
			if ("1".equals(profession)) {
				newProfession = "0";
			} else if ("2".equals(profession)) {
				newProfession = "1";
			} else if ("3".equals(profession)) {
				newProfession = "3";
			} else if ("4".equals(profession)) {
				newProfession = "4";
			} else if ("5".equals(profession)) {
				newProfession = "5";
			} else if ("6".equals(profession)) {
				newProfession = "6";
			} else if ("X".equals(profession)) {
				newProfession = "X";
			} else if ("Y".equals(profession)) {
				newProfession = "Y";
			}
		}
		return newProfession;
	}

	/**
	 * 住宅性质
	 * 
	 * @param String
	 * @param String
	 */
	@Bizlet("住宅性质转换成标准数据")
	public static String getHouseProperty(String houseProperty) {
		String newHouseProperty = null;
		if (null != houseProperty && !"".equals(houseProperty)) {
			if ("1".equals(houseProperty)) {
				newHouseProperty = "100";
			} else if ("2".equals(houseProperty)) {
				newHouseProperty = "110";
			} else if ("3".equals(houseProperty)) {
				newHouseProperty = "330";
			} else if ("4".equals(houseProperty)) {
				newHouseProperty = "320";
			} else if ("5".equals(houseProperty)) {
				newHouseProperty = "310";
			} else if ("6".equals(houseProperty)) {
				newHouseProperty = "400";
			} else if ("7".equals(houseProperty)) {
				newHouseProperty = "900";
			}
		}
		return newHouseProperty;
	}
}
