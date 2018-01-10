/**
 * 
 */
package com.bos.conPrint;

import java.util.HashMap;
import java.util.Map;

 import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2015-10-09 04:00:05
 *
 */
@Bizlet("")
public class SubContract {
	
	@Bizlet("")
	public DataObject getSubContractData(DataObject obj) {
		String contractNum=obj.getString("CONTRACT_NUM");
		String contractId=obj.getString("CONTRACT_ID");
		
		Map map=new HashMap();
		map.put("subContractNum", contractNum);
		map.put("contractId", contractId);
		map.put("subcontractType", "01");
		String sqlName="";
		String sqlNameSortType="";
		if(contractNum.startsWith("XY")){
			sqlName="com.bos.conApply.contractPrint.getXYDYZY";
			sqlNameSortType="com.bos.conApply.contractPrint.getXYSORTTYPE";
		}else{
			sqlName="com.bos.conApply.contractPrint.getCHTDYZY";
			sqlNameSortType="com.bos.conApply.contractPrint.getCHTSORTTYPE";
		}
		Object[] DY = DatabaseExt.queryByNamedSql("default",sqlName, map);
		if(0!=DY.length){
			DataObject dy = (DataObject) DY[0];
			
			Object[] SORTTYPE = DatabaseExt.queryByNamedSql("default",sqlNameSortType, map);
			
			if(0!=SORTTYPE.length){
				DataObject sortType = (DataObject) SORTTYPE[0];
				String sortyTy=sortType.getString("SORT_TYPE");
				
				StringBuffer sb=new StringBuffer();
				if(null!=sortyTy){
					String[] arraySort=sortyTy.split(",");
					for(int i=0;i<arraySort.length;i++){
						String entityName2="com.bos.dataset.grt.TbGrtSortarguments";
						DataObject con2  = DataObjectUtil.createDataObject(entityName2);
						con2.set("sortType", arraySort[i]);
						DatabaseUtil.expandEntityByTemplate("default", con2, con2);
						if(i!=arraySort.length-1){
							sb.append(con2.getString("sortName")).append(",");
						}else{
							sb.append(con2.getString("sortName"));
						}
					}
				}
				obj.set("DSORT_TYPE",sb.toString());
			}
			
			obj.set("DPARTY_NAME",dy.getString("PARTY_NAME"));
			obj.set("DSUBCONTRACT_NUM",dy.getString("SUBCONTRACT_NUM"));
		}
		
		if(contractNum.startsWith("XY")){
			sqlName="com.bos.conApply.contractPrint.getXYDYZY";
			sqlNameSortType="com.bos.conApply.contractPrint.getXYSORTTYPE";
		}else{
			sqlName="com.bos.conApply.contractPrint.getCHTDYZY";
			sqlNameSortType="com.bos.conApply.contractPrint.getCHTSORTTYPE";
		}
		
		map.put("subcontractType", "02");
		Object[] ZY = DatabaseExt.queryByNamedSql("default",sqlName, map);
		if(0!=ZY.length){
			DataObject zy = (DataObject) ZY[0];
			obj.set("ZPARTY_NAME",zy.getString("PARTY_NAME"));
			obj.set("ZSUBCONTRACT_NUM",zy.getString("SUBCONTRACT_NUM"));
			
			Object[] SORTTYPE = DatabaseExt.queryByNamedSql("default",sqlNameSortType, map);
			
			if(0!=SORTTYPE.length){
				DataObject sortType = (DataObject) SORTTYPE[0];
				String sortyTy=sortType.getString("SORT_TYPE");
				
				StringBuffer sb=new StringBuffer();
				if(null!=sortyTy){
					String[] arraySort=sortyTy.split(",");
					for(int i=0;i<arraySort.length;i++){
						String entityName2="com.bos.dataset.grt.TbGrtSortarguments";
						DataObject con2  = DataObjectUtil.createDataObject(entityName2);
						con2.set("sortType", arraySort[i]);
						DatabaseUtil.expandEntityByTemplate("default", con2, con2);
						if(i!=arraySort.length-1){
							sb.append(con2.getString("sortName")).append(",");
						}else{
							sb.append(con2.getString("sortName"));
						}
					}
				}
				obj.set("ZSORT_TYPE",sb.toString());
			}
		}
		
		
		if(contractNum.startsWith("XY")){
			sqlName="com.bos.conApply.contractPrint.getXYBZR";
		}else{
			sqlName="com.bos.conApply.contractPrint.getCHTBZR";
		}
		
		Object[] BZR = DatabaseExt.queryByNamedSql("default",sqlName, map);
		if(0!=BZR.length){
			DataObject bzr = (DataObject) BZR[0];
			obj.set("BPARTY_NAME",bzr.getString("PARTY_NAME"));
			obj.set("BSUBCONTRACT_NUM",bzr.getString("SUBCONTRACT_NUM"));
		}
		
		return obj;
	}
	
	@Bizlet("")
	public DataObject getContractData(DataObject obj) {
		String subContractNum=obj.getString("SUBCONTRACT_NUM");
		Map map=new HashMap();
		map.put("subContractNum", subContractNum);
		
		Object[] CONTRACT = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.printDYZYLinkContract", map);
		if(0!=CONTRACT.length){
			DataObject contract= (DataObject) CONTRACT[0];
			obj.set("CONTRACT_NUM",contract.getString("PAPER_CON_NUM"));
			obj.set("PRODUCT_TYPE",contract.getString("PRODUCT_TYPE"));
			obj.set("CURRENCY_CD",contract.getString("CURRENCY_CD"));
			obj.set("CONTRACT_AMT",contract.getString("CONTRACT_AMT"));
			obj.set("DCONTRACT_AMT",contract.getString("CONTRACT_AMT"));
			obj.set("YEAR_RATE",contract.getString("YEAR_RATE"));
			obj.set("BEGIN_DATE",contract.getString("BEGIN_DATE"));
			obj.set("END_DATE",contract.getString("END_DATE"));
			obj.set("CONTRACT_ADDRESS",contract.getString("CONTRACT_ADDRESS"));
			
			
			
			String productType=contract.getString("PRODUCT_TYPE");
			DataObject con  =null;
			if(null!=productType){
				String entityName="com.bos.pub.product.TbSysProduct";
				con  = DataObjectUtil.createDataObject(entityName);
				con.set("productId", productType);
				DatabaseUtil.expandEntity("default", con);
				
				String productName=con.getString("productName");
				
				if(productName.contains("银行承兑汇票")){
					productName="银行承兑协议";
				}else if(productName.contains("固定资产")){
					productName="固定资产借款合同";
				}else if(productName.contains("流动资金")){
					productName="借款合同";
				}else if(productName.contains("汇票贴现")){
					productName="商业汇票贴现合同";
				}else if(productName.contains("保函")){
					productName="开立保函协议";
				}else if(productName.contains("信用证")){
					productName="国内信用证开证合同";
				}else{
					productName="借款合同";
				}
				obj.set("CONTRACT_NAME", productName);
			}
		}
		return obj;
	}
}
