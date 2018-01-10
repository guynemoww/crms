/**
 * 
 */
package com.bos.grt.manage;

import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.events.IndexEvents.Entry;

import commonj.sdo.DataObject;

@Bizlet("")
public class deleteGrt {

	/**
	 * 押品类型
	 */
	public HashMap<String, String> getGrtKey() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("01", "com.bos.dataset.grt.TbGrtHouse");// 房地产
		map.put("02", "com.bos.dataset.grt.TbGrtBuilding");// 在建工程类
		map.put("03", "com.bos.dataset.grt.TbGrtLanduse");// 土地使用权类
		map.put("04", "com.bos.dataset.grt.TbGrtVehicle");// 交通工具
		map.put("05", "com.bos.dataset.grt.TbGrtMachine");// 机器设备
		map.put("06", "com.bos.dataset.grt.TbGrtGoods");// 存货(抵押)
		map.put("07", "com.bos.dataset.grt.TbGrtOtherProperty");// 其他抵质押资产类
		map.put("08", "com.bos.dataset.grt.TbGrtDeposit");// 存单
		map.put("09", "com.bos.dataset.grt.TbGrtBond");// 债券
		map.put("10", "com.bos.dataset.grt.TbGrtInmarketstock");// 股权
		map.put("11", "com.bos.dataset.grt.TbGrtStock");// 股票
		map.put("12", "com.bos.dataset.grt.TbGrtFund");// 基金
		map.put("13", "com.bos.dataset.grt.TbGrtManagemoney");// 理财协议编号
		map.put("14", "com.bos.dataset.grt.TbGrtBill");// 票据
		map.put("15", "com.bos.dataset.grt.TbGrtReceivables");// 应收类
		map.put("16", "com.bos.dataset.grt.TbGrtCkts");// 出口退税
		map.put("1701", "com.bos.dataset.grt.TbGrtDepot");// 货权类(仓单)
		map.put("1703", "com.bos.dataset.grt.TbGrtLanbill");// 货权类(提单)
		map.put("18", "com.bos.dataset.grt.TbGrtLicense");// 知识产权
		map.put("1901", "com.bos.dataset.grt.TbGrtRestProfit");// 土地承包经营权证号
		map.put("1902", "com.bos.dataset.grt.TbGrtTxjyq");// 特许经营权
		map.put("1903", "com.bos.dataset.grt.TbGrtQtsyq");// 其他收益权
		map.put("1904", "com.bos.dataset.grt.TbGrtResourceprofit");// 林权证号
		map.put("21", "com.bos.dataset.grt.TbGrtRoadBridge");// 收费合同编号
		map.put("22", "com.bos.dataset.grt.TbGrtQtzyzc");// 其他质押资产类
		return map;
	}

	@Bizlet("")
	public String deleteInfo(DataObject obj) {
		String sortType = obj.getString("sortType");
		if(null==sortType||"".equals(sortType)){
			return null;
		}
		//根据押品类型查找对应的数据实体名
		DataObject grt=DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtSortarguments");
		grt.set("sortType", sortType);
		DatabaseUtil.expandEntityByTemplate("default", grt, grt);
		return grt.getString("typeTable");
//		HashMap<String, String> map = getGrtKey();
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			String key = entry.getKey();
//			if (sortType.startsWith(key)) {
//				return entry.getValue();
//			}
//		}
	}
}
