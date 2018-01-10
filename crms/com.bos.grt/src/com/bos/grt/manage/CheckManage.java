/**
 * 
 */
package com.bos.grt.manage;

import java.util.HashMap;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-07-26 14:46:54
 *押品唯一性校验
 */
@Bizlet("")
public class CheckManage {

	/**
	 * 押品唯一性校验
	 */
	@SuppressWarnings("unchecked")
	@Bizlet("将数据实体名转换成对应的唯一标识")
	public String getGrtKey(String grtNum) {
		HashMap map = new HashMap();
		map.put("com.bos.dataset.grt.TbGrtHouse", "housePropNo");//房地产
		map.put("com.bos.dataset.grt.TbGrtLanduse", "landUseNo");//土地使用权类
		//map.put("com.bos.dataset.grt.TbGrtBuilding", "approvalNo");//在建工程类  BUG #9511 申请取消在建工程唯一校验规则
		map.put("com.bos.dataset.grt.TbGrtVehicle", "certificateNo");//交通工具
		map.put("com.bos.dataset.grt.TbGrtMachine", "machineNo");//机器设备
		map.put("com.bos.dataset.grt.TbGrtGoods", "inventoryNo");//存货(抵押)
		map.put("com.bos.dataset.grt.TbGrtDeposit", "depositNo");//存单
		map.put("com.bos.dataset.grt.TbGrtReceivables", "contractNo");//应收类
		map.put("com.bos.dataset.grt.TbGrtBond", "bondNo");//债券
		map.put("com.bos.dataset.grt.TbGrtInmarketstock", "stockCode");//股权
		map.put("com.bos.dataset.grt.TbGrtRoadBridge", "chargingContractNo");//收费合同编号
		map.put("com.bos.dataset.grt.TbGrtStock", "stockCode");//股票
		map.put("com.bos.dataset.grt.TbGrtFund", "fundCode");//基金
		map.put("com.bos.dataset.grt.TbGrtBill", "billNo");//票据
		map.put("com.bos.dataset.grt.TbGrtDepot", "depotNo");//货权类(仓单)
		map.put("com.bos.dataset.grt.TbGrtLanbill", "lanBillNo");//货权类(提单)
		//map.put("com.bos.dataset.grt.TbGrtGoods", "inventoryNo");//存货(质押)
		map.put("com.bos.dataset.grt.TbGrtLicense", "licenseNo");//知识产权
		map.put("com.bos.dataset.grt.TbGrtCkts", "cktszh");//出口退税
		map.put("com.bos.dataset.grt.TbGrtRestProfit", "landUseNo");//土地承包经营权证号
		map.put("com.bos.dataset.grt.TbGrtResourceprofit", "forestNo");//林权证号
		map.put("com.bos.dataset.grt.TbGrtManagemoney", "treatyNo");//理财协议编号
		map.put("com.bos.dataset.grt.TbGrtOtherProperty", "ownershipNum");//其他抵押资产类
		map.put("com.bos.dataset.grt.TbGrtQtsyq", "ownershipNum");//其他收益权
		return (String) map.get(grtNum);
	}

	@Bizlet("")
	public String checkGrt(DataObject obj) {
		//获取需要校验的押品实体
		String entityName = (String) obj.get("_entity");
		//获取该实体所需校验的编码类型
		String manageKey = getGrtKey(entityName);
		if(null!=manageKey){
			//新的实体
			DataObject objA = DataObjectUtil.createDataObject(entityName);
			String key=obj.getString(manageKey);
			//如果是更新数据  且押品为回单和股权则继续执行 ，否则直接通过
			String suretyKeyId=(String) obj.get("suretyKeyId");
			if(null!=suretyKeyId){//更新
				//数据在做更新操作 ---更新操作 只校验回单和股权的唯一性
				if("stockCode".equals(manageKey)||"depositNo".equals(manageKey)){
					return "0";
				}else{
					return "0";
				}
			}
			if(null==key){
				//存单和股权可以输入空过去
				if("stockCode".equals(manageKey)||"depositNo".equals(manageKey)||"housePropNo".equals(manageKey)||"treatyNo".equals(manageKey)){
					return "0";
				}else{
					return "1";
				}
			}
					
			//先做非空校验 避免像存单这类不需要业务申请输入的时候做处理
			key=key.replaceAll(" ", "").trim();
			objA.set(manageKey, key);
			objA.set("sortType", (String) obj.get("sortType"));
			//查询是否存在
			DatabaseUtil.expandEntityByTemplate("default", objA, objA);

			if (null == objA.get("suretyKeyId")) {
				return "0";
			}
			return "1";
		}else{
			return "0";
		}
	}
}
