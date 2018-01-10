/**
 * 
 */
package com.bos.irm.irmApply;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-06-24 15:56:52
 * 
 */
@Bizlet("")
public class IrmModel {
	public static TraceLogger logger = new TraceLogger(IrmModel.class);

	/**
	 * @param partyId
	 * @param args
	 * @author 3231 根据客户 工信部企业规模 和 行业门类 确定客户评级模版
	 */
	@Bizlet("")
	public String getIrmModel(String partyId, String choose, String pjlx) {

		if (null == partyId || "" == partyId) {
			logger.info("获取评级模版时 客户ID为空！");
			return null;
		}
		// 自然人客户
		// DataObject naturalPerson = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
		// naturalPerson.set("partyId", partyId);
		// DatabaseUtil.expandEntityByTemplate("default", naturalPerson, naturalPerson);
		// String naturalPersonTypeCd = (String )naturalPerson.get("naturalPersonTypeCd");

		if (pjlx != null && "1".equals(pjlx)) {
			return "P3";
		} else if (pjlx != null && "2".equals(pjlx)) {
			return "P1";
		} else {
			DataObject corporation = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			corporation.set("partyId", partyId);
			DatabaseUtil.expandEntityByTemplate("default", corporation, corporation);
			String corpCustomerTypeCd = (String) corporation.get("corpCustomerTypeCd");
			if ("3".equals(corpCustomerTypeCd)) {
				return "P1";
			}
			if ("true".equals(choose)) {
				return "C1";
			}
			// 行业门类
			if (null == corporation.get("industrialTypeCd") || "" == corporation.get("industrialTypeCd")) {
				logger.info("获取评级模版时  行业门类为空！");
				return null;
			}
			String typeCd = (String) corporation.get("industrialTypeCd");

			if (null == corporation.get("bankScaleIdentify") || "" == corporation.get("bankScaleIdentify")) {
				logger.info("获取评级模版时  银行认定企业规模为空！");
				return null;
			}
			String scaleGx =  corporation.getString("bankScaleIdentify") ;
			System.out.println(corpCustomerTypeCd);
			/**
			 * CDKH0027 1 大 CDKH0027 2 中 CDKH0027 3 小 CDKH0027 4 微
			 */
			// 1农、林、牧、渔业 xiao: P5 zhong: M5
			if ("A".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("A".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 2采矿业 xiao: P1 zhong: M1
			if ("B".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S1";
			if ("B".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M1";

			// 3制造业 xiao: P1 zhong: M1
			if ("C".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S1";
			if ("C".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M1";

			// 4电力、热力、燃气及水的生产和供应业 xiao: P1 zhong: M1
			if ("D".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S1";
			if ("D".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M1";

			// 5建筑业 xiao: P3 zhong: M3
			if ("E".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S3";
			if ("E".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M3";

			// 6批发和零售业 xiao: P2 zhong: M2
			if ("F".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S2";
			if ("F".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M2";

			// 7交通运输、仓储和邮政业 xiao: P5 zhong: M5
			if ("G".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("G".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 8住宿和餐饮业 xiao: P4 zhong: M4
			if ("H".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S4";
			if ("H".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M4";

			// 9信息传输、计算机服务和软件业 xiao: P4 zhong: M4
			if ("I".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S4";
			if ("I".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M4";

			// 10金融业 xiao: P5 zhong: M5
			if ("J".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("J".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 11房地产业 xiao: P3 zhong: M3
			if ("K".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S3";
			if ("K".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M3";

			// 12租赁和商务服务业 xiao: P4 zhong: M4
			if ("L".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S4";
			if ("L".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M4";

			// 13科学研究和技术服务 xiao: P4 zhong: M4
			if ("M".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S4";
			if ("M".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M4";

			// 14水利、环境和公共设施管理业 xiao: P5 zhong: M5
			if ("N".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("N".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 15居民服务、修理和其他服务业 xiao: P4 zhong: M4
			if ("O".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S4";
			if ("O".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M4";

			// 16教育 xiao: P5 zhong: M5
			if ("P".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("P".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 17卫生、社会工作 xiao: P5 zhong: M5
			if ("Q".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("Q".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 18文化、体育和娱乐业 xiao: P5 zhong: M5
			if ("R".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("R".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 19公共管理、社会保障和社会组织 xiao: P5 zhong: M5
			if ("S".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("S".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";

			// 20国际组织 xiao: P5 zhong: M5
			if ("T".equals(typeCd) && ("3".equals(scaleGx) || "4".equals(scaleGx)))
				return "S5";
			if ("T".equals(typeCd) && ("1".equals(scaleGx) || "2".equals(scaleGx)))
				return "M5";
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
