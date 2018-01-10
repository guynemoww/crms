package com.bos.grtPro;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import commonj.sdo.DataObject;

@Bizlet("押品出入库")
public class OutDetailOperation {

	/**
	 * 更新押品出库西悉尼
	 * @param productId
	 * @param outType
	 * @param outReason
	 * @param cardInRevertDate
	 * @return
	 */
	@Bizlet
	public boolean updateOut(String productId,String outType,String outReason,Date cardInRevertDate){
		Map<String,Object> map = new HashMap<String,Object>();
		boolean suc = false;
		ITransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();

		try {
			transactionManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);

			map.put("productId", productId);//出库ID
	//		map.put("outType", outType);//出库类型
			map.put("outReason", outReason);//出库原因
			if("22".equals(outType)){//临时出库，则必输录入预计归还时间
				if("".equals(cardInRevertDate)||cardInRevertDate==null){
					return false;
				}
			}
			map.put("cardInRevertDate", cardInRevertDate);//预计归还时间
			//保存入库类型在  tb_grt_out表中
	//		DatabaseExt.executeNamedSql("default", 
	//				"com.bos.grt.grt.updateGrtOut", map);
			//保存入库原因，预计归还时间 到tb_grt_out_detail 中
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateGrtOutDetail", map);
			suc = true;
			transactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			suc = false;
			if (transactionManager != null) {
				transactionManager.rollback();
			}
		}
		return suc;
	}
	
	/**
	 * 获得押品出库原因，类型
	 * @param map
	 * @return
	 */
	@Bizlet
	public Map<String,Object> getgrtOutType(Map<String,Object> map){
		Map<String,Object> htMap =new HashMap<String,Object>();
		try {
			Object[] hDate = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.getOutType", map);
			if(hDate != null && hDate.length>0){
				DataObject resultDataObject = (DataObject) hDate[0];
				htMap.put("outType", resultDataObject.getString("OUT_TYPE"));
				htMap.put("outReason", resultDataObject.getString("OUT_REASON"));
				htMap.put("outId", resultDataObject.getString("OUT_ID"));
				htMap.put("cardInRevertDate", resultDataObject.getString("CARD_IN_REVERT_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htMap;
	}
	
	/**
	 * 删除权证信息
	 * @param map
	 * @return
	 */
	@Bizlet
	public String delRegCard(Map<String,Object> map){
		String suc = "false";
		try{
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.delRegCard", map);
			suc = "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return suc;
	}
	
	/**
	 * 更新押品入库信息
	 * @param productId
	 * @param inType
	 * @param inReason
	 * @return
	 */
	@Bizlet
	public boolean updateInReason(String productId,String inType,String inReason){
		Map<String,Object> map = new HashMap<String,Object>();
		boolean suc = false;
		try {
			
			map.put("productId", productId);//出库ID
			map.put("inType", inType);//出库类型
			map.put("inReason", inReason);//出库原因
			//保存入库类型，入库原因在  tb_grt_in表中
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateInReason", map);
			suc = true;
		} catch (Exception e) {
			e.printStackTrace();
			suc = false;
		}
		return suc;
	}
	
	/**
	 * 查询押品入库原因。类型
	 * @param map
	 * @return
	 */
	@Bizlet
	public Map<String,Object> grtInReason(Map<String,Object> map){
		Map<String,Object> htMap =new HashMap<String,Object>();
		try {
			Object[] hDate = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.grtInReason", map);
			if(hDate != null && hDate.length>0){
				DataObject resultDataObject = (DataObject) hDate[0];
				htMap.put("inType", resultDataObject.getString("IN_TYPE"));
				htMap.put("inReason", resultDataObject.getString("IN_REASON"));
				htMap.put("outId", resultDataObject.getString("IN_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htMap;
	}
	
	/**
	 * 拖过权证编号查询本次入库是否属于期转现业务
	 * 1.权证类别是否属于  0117 房地产预告登记证明
	 * 2.查询出业务品种属于  02002004	个人商用房担保（按揭）贷款
	 *				  02002005	个人住房担保（按揭）贷款
	 * @param registerCertiNo
	 * @return
	 */
	@Bizlet
	public Map<String,Object> checkEpps(String inId){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> fmap = new HashMap<String,Object>();
		String ifBiz = "false";
		map.put("outId", inId);
		try {
			//通过当前inId 查询出权证编号
			Object[] date = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.queryInApprovel", map);
			if(date != null && date.length>0){
				DataObject dataObject = (DataObject)date[0];
				map.put("registerCertiNo",dataObject.getString("REGISTER_CERTI_NO"));
				
				//通过权证编号查询前一笔入库的权证类型，判断是否为 房屋预登记
				Object[] hDate = DatabaseExt.queryByNamedSql("default", 
						"com.bos.grt.grt.checkEpps", map);
				if(hDate != null && hDate.length>0){
					DataObject resultDataObject = (DataObject) hDate[1];//屏蔽掉此次申请的入库信息，取第二个值
					if("0117".equals(resultDataObject.getString("CARD_TYPE"))){
						//查询业务品种，(按揭贷款基本都是录入一个押品)
						map.put("suretyId", resultDataObject.getString("SURETY_ID"));
						fmap.put("suretyId",  resultDataObject.getString("SURETY_ID"));
						Object[] bizDate = DatabaseExt.queryByNamedSql("default", 
								"com.bos.grt.grt.getBizTypeForId", map);
						if(bizDate != null && bizDate.length>0){
							DataObject bizDataObject = (DataObject) bizDate[0];//
							if("02002004".equals(bizDataObject.getString("PRODUCT_TYPE")) || "02002005".equals(bizDataObject.getString("PRODUCT_TYPE")) 
									||"02002011".equals(bizDataObject.getString("PRODUCT_TYPE")) || "02002010".equals(bizDataObject.getString("PRODUCT_TYPE"))){
								//属于期转现业务
								ifBiz = "true";
							}
						}
					}
				}
			}
			fmap.put("ifBiz", ifBiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fmap;
	}
	
	/**
	 * 保存面积信息并调押品接口将数据传输到押品系统
	 * @param map
	 */
	@Bizlet
	public void saveArea(Map<String,Object> map){
		try{
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.saveEppsArea", map);
			
			//通过suretyId 查询出押品编号
			Object[] hDate = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.basicQuery", map);
			if(hDate != null && hDate.length>0){
				DataObject resultDataObject = (DataObject) hDate[0];
				map.put("cltNo", resultDataObject.getString("SURETY_NO"));
				//调接口，同步数据
				CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
				CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();

				ObjectMapper mapper = new ObjectMapper();
				map.put("trans_code", "1114");//接口交易码
				map.put("ope_flag", "area");//接口交易码
				// Convert object to JSON string  
				String ypxxJsonStr = null;
				ypxxJsonStr = mapper.writeValueAsString(map);
				ser.setIn0(ypxxJsonStr);
				String flag = stub.collServiceCommInter(ser).getOut1();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据权证编号查询是否存在有效的担保合同
	 * @param map
	 * @return
	 */
	@Bizlet
	public String getValidById(Map map){
		String flag = "false";
		map.put("status", "03");
		try {
			Object[] hDate = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.getValidConById", map);
			if(hDate != null && hDate.length>0){
				flag = "true";//存在有效的担保合同
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 根据权证编号查询是否存在有效的担保合同
	 * @param map
	 * @return
	 */
	@Bizlet
	public void updateReg(Map map){
		try {
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateGrtReg", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据权证ID查询权证下的押品 是不是存在质押扣划的，如果已经做过了质押扣划。则可以直接出库，不进行校验
	 * 已与行内tangw确认，存单押品与权证的关系均为唯一。
	 * @param map
	 * @return
	 */
	@Bizlet
	public String checkIfZykh(Map map){
		String flag = "false";
		try {
			Object[] hDate = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.checkIfZYkh", map);
			if(hDate != null && hDate.length>0 && "1".equals(((DataObject) hDate[0]).getString("IS_DONE_ZYKH"))){
				flag = "true";//存单做过质押扣划
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
