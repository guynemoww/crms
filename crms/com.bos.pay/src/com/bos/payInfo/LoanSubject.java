/**
 * 
 */
package com.bos.payInfo;

import java.util.Date;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-08-02 20:52:45
 *业务别计算
 *业务别共7位
 */
@Bizlet("")
public class LoanSubject {
	@Bizlet("")
	public String getDay(Date begin, Date end){
		Date beginDate = begin;
		Date endDate = end;
		Long days = endDate.getTime() - beginDate.getTime();
		int day = (int) (days/(1000*60*60*24));
		return String.valueOf(day);

	}
	/**
	 * @param loanId 
	 * @param args
	 * @author 3231
	 * @throws EOSException 
	 */
	@Bizlet("")
	public String getLoanSubject(String loanId) throws EOSException {
//		//业务别
//		String ls;
//		DataObject loanInfo = DataObjectUtil
//				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
//		loanInfo.set("loanId", loanId);
//		DatabaseUtil.expandEntity("default", loanInfo);
//
//		//业务别第1-6位从数据库取
//		String ls1to6;
//		String productType = (String)loanInfo.get("productType");
//		
//		DataObject productInfo = DataObjectUtil
//				.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
//		productInfo.set("productCd", productType);
//		DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
//		
//		if(null== productInfo.get("bizDon")){
//			ls1to6 = "000000";
//		}else{
//			ls1to6 = productInfo.get("bizDon").toString();
//		}
//
//		HashMap map = new HashMap();
//		map.put("loanId", loanId);
//		if ("01002002".equals(productType)) {//普通个人住房、商业用房开发贷款根据项目类型区分个人还是商用
//			Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.LoanSubject.getZkflx", map);
//			DataObject ms = (DataObject) objs[0];
//			int m = ms.getInt("C");
//			//9632 根据房地产开发贷款业务明细中录入的项目信息中的“主开发类型”来区分，
//			//如果关联多个项目信息，主开发类型既有住房开发，又有商用户开发，以住房开发优先
//			if(m>0){//只要有一个符合住房开发就算住房开发
//				ls1to6 = "110202";
//			}else{
//				ls1to6 = "110203";
//			}
//		}
//		//业务别第七位
//		String ls7 = "1";
//		String contractId = loanInfo.getString("contractId");
//		DataObject conInfo = DataObjectUtil
//				.createDataObject("com.bos.dataset.crt.TbConContractInfo");
//		conInfo.set("contractId", contractId);
//		DatabaseUtil.expandEntity("default", conInfo);
//		String mainGuarantyType = conInfo.getString("mainGuarantyType");
//		/**
//		 * 01-信用
//		 * 02-抵押
//		 * 03-质押
//		 * 04-保证
//		 * 05-保证金
//		 * */
//		if ("01".equals(mainGuarantyType)) {//信用
//			ls7 = "1";
//		} else if ("02".equals(mainGuarantyType)) {//抵押
//			ls7 = "3";
//		} else if ("03".equals(mainGuarantyType)) {//质押
//			ls7 = "4";
//		} else if ("04".equals(mainGuarantyType)) {//保证
//			ls7 = "2";
//		}
//
//		//业务别第八位
//		String ls8;
//		Object[] objs = DatabaseExt.queryByNamedSql("default",
//				"com.bos.payInfo.LoanSubject.getLoanSummary", map);
//		DataObject ms = (DataObject) objs[0];
//		BigDecimal m = ms.getBigDecimal("M");
//		//if(aviAmt.compareTo(loanAmt)>=0)
//		if (m.compareTo(new BigDecimal("12")) <= 0) {
//			ls8 = "1";
//		} else if (m.compareTo(new BigDecimal("12")) > 0
//				&& m.compareTo(new BigDecimal("60")) <= 0) {
//			ls8 = "2";
//		} else {
//			ls8 = "3";
//		}
//
//		//业务别
//		ls = ls1to6 + ls7 + ls8;
//		
//		if(productType.equals("01007001")||productType.equals("01007002")||productType.equals("01007003")||
//				productType.equals("01007004")||productType.equals("01007005")||productType.equals("01007006")||
//				productType.equals("01007007")||productType.equals("01007008")||productType.equals("01009001")||
//				productType.equals("01009002")||productType.equals("01011001")||productType.equals("01012001")
//				||productType.equals("01007009")||productType.equals("01007010")||productType.equals("01007011")
//				||productType.equals("01007012")||productType.equals("01007013")||productType.equals("01007014")){
//			ls = "";
//		}
		/**
		 * 
		 *业务别规则（垫款除外）：
		 *1位：客户类型  1 对公 ， 2 对私
		 *2位：贷款类型  1自营贷款   ，2 委托贷款
		 *3,4位：产品类别  13 固定资产贷款、流动资金贷款，08 国内贸易融资，09 国际贸易融资 ，05经营性贷款 ，04 消费类贷款，01 一手房住房贷款，02 二手房住房贷款，03 商业住房贷款,06 单位委托贷款，07 个人委托贷款，10 公积金委托贷款
		 *5位：期限标志   1 短期，2 中长期，0 无效位（注：委托贷款为无效位0）
		 *6位：担保方式 1 信用，2 保证 ， 3 抵押 ， 4 质押 ，0 无效位（注：委托贷款为无效位0）
		 *7位：企业规模  1 大型，2 中型 ， 3 小型 ， 4 微型，0无效（注：委托贷款为无效位0）
		 */
		//业务别
		DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		//第一位：客户类型
		String ls;
		String productType = loanInfo.getString("productType");
		if("01".equals(productType.substring(0, 2))){// 1 对公 ， 2 对私
			ls="1";
		}else{
			ls="2";
		}
		//第二位：贷款类型
		String ls2;
		if("02005".equals(productType.substring(0, 5)) || "01013".equals(productType.substring(0, 5))){//1自营贷款   ，2 委托贷款
			ls2 = "2";
		}else{
			ls2 = "1";
		}
		//第三、四位：产品类型
		String ls3="";
		if("01001".equals(productType.substring(0, 5)) || "01003".equals(productType.substring(0, 5))){//13固定资产贷款、流动资金贷款
			ls3 = "13";
		}else if("01007".equals(productType.substring(0, 5))){//09 国际贸易融资
			ls3 = "09";
		}else if("01004".equals(productType.substring(0, 5)) || "01009".equals(productType.substring(0, 5))
				|| "01008".equals(productType.substring(0, 5)) || "01006".equals(productType.substring(0, 5))){//08 国内贸易融资
			ls3 = "08";
		}else if("02001".equals(productType.substring(0, 5))){//05经营性贷款
			ls3 = "05";
		}else if("02003".equals(productType.substring(0, 5))){//04 消费类贷款
			ls3 = "04";
		}else if("02002005".equals(productType)||"02002010".equals(productType)){//01 一手房住房贷款
			ls3 = "01";
		}else if("02002003".equals(productType)){//02 二手房住房贷款
			ls3 = "02";
		}else if("02002004".equals(productType)||"02002011".equals(productType)){//03 商业住房贷款
			ls3 = "03";
		}else if("02005001".equals(productType)){//10 公积金委托贷款
			ls3 = "10";
		}else if("02005".equals(productType.substring(0, 5))){//07 个人委托贷款
			ls3 = "07";
		}else if("01013".equals(productType.substring(0, 5))){//06 单位委托贷款
			ls3 = "06";
		}
		String ls4="";
		String ls5="";
		String ls6="";
		if("02005".equals(productType.substring(0, 5)) || "01013".equals(productType.substring(0, 5))){
			ls4="0";
			ls5="0";
			ls6="0";
		}else{
			//第五位：期限标志1 短期，2中长期
			String loanTerm = loanInfo.getString("loanTerm");//期限-按天计算
			String beginDate = loanInfo.getString("beginDate");//开始时间
			String endDate = loanInfo.getString("endDate");//结束时间
			if(null != loanTerm){
				int day = Integer.valueOf(loanTerm);
				int byear = Integer.valueOf(beginDate.substring(0, 4));
				int eyear = Integer.valueOf(endDate.substring(0, 4));
				boolean b = isLeapYear(byear);
				boolean e = isLeapYear(eyear);
				if(b || e){
					if(day>366){
						ls4 = "2";
					}else{
						ls4 = "1";
					}
				}else{
					if(day>365){
						ls4 = "2";
					}else{
						ls4 = "1";
					}
				}
			}
			//第六位：担保方式
			String contractId = loanInfo.getString("contractId");
			DataObject conInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			conInfo.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", conInfo);
			String mainGuarantyType = conInfo.getString("mainGuarantyType");
			if ("01".equals(mainGuarantyType)) {//1-信用
				ls5 = "1";
			} else if ("02".equals(mainGuarantyType)) {//3-抵押
				ls5 = "3";
			} else if ("03".equals(mainGuarantyType)) {//4-质押
				ls5 = "4";
			} else if ("04".equals(mainGuarantyType)) {//2-保证
				ls5 = "2";
			}else{//0 无效位
				ls5 = "0";
			}
			//第七位：企业规模
			DataObject crmsCorp = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			crmsCorp.set("partyId", loanInfo.getString("partyId"));
			DatabaseUtil.expandEntity("default", crmsCorp);
			String enterpriseScaleGx = crmsCorp.getString("bankScaleIdentify");
			if("1".equals(enterpriseScaleGx)){//1 大型
				ls6 = "1";
			}else if("2".equals(enterpriseScaleGx)){//2 中型
				ls6 = "2";
			}else if("3".equals(enterpriseScaleGx)){// 3 小型
				ls6 = "3";
			}else if("4".equals(enterpriseScaleGx)){//4 微型
				ls6 = "4";
			}else{//0无效
				ls6 = "0";
			}
		}
		ls = ls+ls2+ls3+ls4+ls5+ls6;
		if(ls.length() !=7){
			throw new EOSException("业务别创建失败!");
		}
		return ls;
	}
	public static boolean isLeapYear(int year){
		if(year % 100 == 0){
			if(year % 400 ==0){
				return true;
			}
		}else {
			if(year % 4 == 0){
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String a = "03001002";
		String b = a.substring(3, 5);
		String c = a.substring(6, 8);
		System.out.print(b + c);

	}

}
