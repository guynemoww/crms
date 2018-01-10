/**
 * 
 */
package com.bos.comm.csm;

import com.eos.system.annotation.Bizlet;

/**
 * @author git
 * @date 2014-08-16 17:14:32
 * 
 */
@Bizlet("自动计算四部委企业规模")
public class FourzEnterpriseSizeCd {

	/**
	 * 
	 * @param incomeSum
	 *            营业收入
	 * @param employees
	 *            从业人数
	 * @param totalAssets
	 *            资产总额
	 * @param sIndustryTypeNew
	 *            国标行业大类
	 */
	@Bizlet("自动计算四部委企业规模")
	public String AutoFourzEnterpriseSizeCd(String incomeSumStr,
			String employeeStr, String totalAssetStr, String sIndustryTypeNew) {
		String fourzEnterpriseSizeCd = "2";
		Double incomeSum =0.0;
		Double employees = 0.0;
		Double totalAssets = 0.0;
		if(incomeSumStr !=null){
			incomeSum = Double.parseDouble(incomeSumStr);
		}
		if(employeeStr !=null){
			employees = Double.parseDouble(employeeStr);
		}
		if(totalAssetStr !=null){
			totalAssets = Double.parseDouble(totalAssetStr);
		}
		 
		if (incomeSum == null || incomeSum == 0.0) {
			fourzEnterpriseSizeCd = "2";
		}
		if ("".equals(sIndustryTypeNew) || sIndustryTypeNew ==null){
			fourzEnterpriseSizeCd = "2";
		}else{
			// 农、林、牧、渔业
			if (sIndustryTypeNew.substring(0, 1) == "A") {
				if (incomeSum < 20000) {
					if (incomeSum < 50) {
						fourzEnterpriseSizeCd = "4";
					}
					if (incomeSum >= 50) {
						fourzEnterpriseSizeCd = "3";
					}
					if (incomeSum >= 500) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}

			}

			// 工业
			else if (sIndustryTypeNew.substring(0, 1) == "B"
					|| sIndustryTypeNew.substring(0, 1) == "C"
					|| sIndustryTypeNew.substring(0, 1) == "D") {
				if (employees < 1000 || incomeSum < 40000) {
					if (incomeSum < 300 || employees < 20) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 300) && (employees >= 20)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 2000) && (employees >= 300)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 建筑业
			else if (sIndustryTypeNew.substring(0, 1) == "E") {
				if (incomeSum < 80000 || totalAssets < 80000) {
					if (incomeSum < 300 || totalAssets < 300) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 300) && (totalAssets >= 300)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 6000) && (totalAssets >= 5000)) {
						fourzEnterpriseSizeCd = "2";
					}

				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 批发业
			else if (sIndustryTypeNew.substring(0, 3) == "F51") {
				if (incomeSum < 40000 || employees < 200) {
					if (incomeSum < 1000 || employees < 5) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 1000) && (employees >= 5)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 5000) && (employees >= 20)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 零售业
			else if (sIndustryTypeNew.substring(0, 3) == "F52") {
				if (incomeSum < 20000 || employees < 300) {
					if (incomeSum < 100 || employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (employees >= 10)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 500) && (employees >= 50)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 交通运输业sIndustryTypeNew.substring(0,3)=="G53")
			else if (sIndustryTypeNew.substring(0, 3) == "G54"
					|| sIndustryTypeNew.substring(0, 3) == "G55"
					|| sIndustryTypeNew.substring(0, 3) == "G56"
					|| sIndustryTypeNew.substring(0, 3) == "G57"
					|| sIndustryTypeNew.substring(0, 3) == "G58") {
				if (incomeSum < 30000 || employees < 1000) {
					if (incomeSum < 200 || employees < 20) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 200) && (employees >= 20)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 3000) && (employees >= 300)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 仓储业
			else if (sIndustryTypeNew.substring(0, 3) == "G59") {
				if (incomeSum < 30000 || employees < 200) {
					if (incomeSum < 100 || employees < 20) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (employees >= 20)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 1000) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 邮政业
			else if (sIndustryTypeNew.substring(0, 3) == "G60") {
				if (incomeSum < 30000 || employees < 1000) {
					if (incomeSum < 100 || employees < 20) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (employees >= 20)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 2000) && (employees >= 300)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 住宿业
			else if (sIndustryTypeNew.substring(0, 3) == "H61") {
				if (incomeSum < 10000 || employees < 300) {
					if (incomeSum < 100 || employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (employees >= 10)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 2000) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 餐饮业
			else if (sIndustryTypeNew.substring(0, 3) == "H62") {
				if (incomeSum < 10000 || employees < 300) {
					if (incomeSum < 100 || employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (employees >= 10)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 2000) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 信息传输业
			else if (sIndustryTypeNew.substring(0, 3) == "I63"
					|| sIndustryTypeNew.substring(0, 3) == "I64") {
				if (incomeSum < 100000 || employees < 2000) {
					if (incomeSum < 100 || employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (employees >= 10)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 1000) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 软件和信息技术服务业
			else if (sIndustryTypeNew.substring(0, 3) == "I65") {
				if (incomeSum < 10000 || employees < 300) {
					if (incomeSum < 50 || employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 50) && (employees >= 10)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 1000) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 房地产开发经营业
			else if (sIndustryTypeNew.substring(0, 4) == "K701") {
				if (incomeSum < 200000 || totalAssets < 10000) {
					if (incomeSum < 100 || totalAssets < 2000) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 100) && (totalAssets >= 2000)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 1000) && (totalAssets >= 5000)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 物业管理
			else if (sIndustryTypeNew.substring(0, 4) == "K702") {
				if (incomeSum < 5000 || employees < 1000) {
					if (incomeSum < 500 || employees < 100) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((incomeSum >= 500) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((incomeSum >= 1000) && (employees >= 300)) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 租赁和商务服务业
			else if (sIndustryTypeNew.substring(0, 1) == "L") {
				if (totalAssets < 120000 || employees < 300) {
					if (totalAssets < 100 || employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if ((totalAssets >= 100) && (employees >= 10)) {
						fourzEnterpriseSizeCd = "3";
					}
					if ((totalAssets >= 8000) && (employees >= 100)) {
						fourzEnterpriseSizeCd = "2";
					}

				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

			// 其他行业
			else {
				if (employees < 300) {
					if (employees < 10) {
						fourzEnterpriseSizeCd = "4";
					}
					if (employees >= 10) {
						fourzEnterpriseSizeCd = "3";
					}
					if (employees >= 100) {
						fourzEnterpriseSizeCd = "2";
					}
				} else {
					fourzEnterpriseSizeCd = "1";
				}
			}

		}
		return fourzEnterpriseSizeCd;
	}

}
