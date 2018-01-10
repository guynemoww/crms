package com.bos.grt.collService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet
public class CollInOper {

	public static TraceLogger logger = new TraceLogger(CollInOper.class);

	/**
	 * 通过权证编号取权证下押品权利价值之和
	 */
	@Bizlet
	public double addMortgagaValue(String registerCeritNo){
		double amt = 0.0;
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("registerCeritNo", registerCeritNo);
		map.put("regNo", registerCeritNo);
		//如果是正式出库，排除已出库的押品
		Object[] data = null;
		Object[] date = DatabaseExt.queryByNamedSql("default", 
				"com.bos.grt.grt.getRegCardDescUpdatetime", map);
		if(date != null && date.length>0){
			DataObject dataObject = (DataObject) date[0];//取最近做的一笔
			String suretyKeyId = dataObject.getString("SURETY_KEY_ID");
			
			map.put("suretyKeyId", suretyKeyId);
			//查询是否存在linshi出库记录。并且出库状态为成功，如果成功
			Object[] hdate = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.checkIfOut", map);
			if(hdate != null && hdate.length>0){ 
				int num = 0;
				for(int i = 0;i<hdate.length;i++){
					DataObject hdataObject = (DataObject) hdate[i];
					if("04".equals(hdataObject.getString("MORTGAGE_STATUS")) && "21".equals(hdataObject.getString("OUT_TYPE"))){
						num++;
					} 
				}
				if(num == hdate.length){
					data = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.grt.grt.getMortgagaValue", map);//存单
				}else{
					data = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.grt.grt.getMortgagaValueForout", map);//期转现
				}
			}else{
				data = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.grt.grt.getMortgagaValueForzc", map);//正常
				
			}
		}
		
//		//查询权利价值
//		 data = DatabaseExt.queryByNamedSql(
//				GitUtil.DEFAULT_DS_NAME,
//				"com.bos.grt.grt.getMortgagaValue", map);
		if (null == data || data.length == 0) {
			logger.info("-----未查到相关押品信息------>registerCeritNo=" + registerCeritNo);
		}
		for(int i = 0;i<data.length;i++){
			DataObject basicDataObject = (DataObject) data[i];
			if(!"".equals(basicDataObject.getString("MORTGAGE_VALUE")) && basicDataObject.getString("MORTGAGE_VALUE") != null){
				BigDecimal c1 = new BigDecimal(basicDataObject.getString("MORTGAGE_VALUE"));
				BigDecimal c2 = new BigDecimal(amt);
				amt = new Double(c1.add(c2).doubleValue());
			}
		}
		System.out.println("权利价值之和为："+amt);
		return amt;
	}
	
	/**
	 * 校验权证下押品的权利价值与前台录入的登记金额相等
	 * @param registerCeritNo 权证编号
	 * @param amount 登记金额
	 * @return
	 */
	@Bizlet
	public Map checkMortgagaValue(double amount,String registerCeritNo){
		String flag = "";
		Map mmap = new HashMap();
		//取登记金额
		double amt = addMortgagaValue(registerCeritNo);
		if(amt == amount){
			//相等
			flag = "true";
		}else{
			flag = "false";
		}
		mmap.put("flag", flag);
		mmap.put("value", amt);
		return mmap;
	}
	

	/**
	 * 根据权证编号查询担保合同到期时间及登记到期时间
	 * 登记到期日期，控制>=担保合同日期。
	 * @param map
	 * @return
	 */
	@Bizlet
	public Map checkSubDate(Map map){
		Map fmap = new HashMap();
		String flag = "true";
		String date = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Object[] data = DatabaseExt.queryByNamedSql("default", 
					"com.bos.grt.grt.getSubDate", map);
			if(data != null && data.length>0){
				for(int i = 0;i<data.length;i++){
					DataObject dataObject = (DataObject) data[i];
					String endDate = dataObject.getString("END_DATE");//担保合同失效日期
					String regDueDate =  dataObject.getString("REG_DUE_DATE");//他项权证登记到期日期
					if(endDate!= null && regDueDate!=null){
						int j = sdf.parse(endDate).compareTo(sdf.parse(regDueDate));
						if(j > 0){
							flag = "false";
							date = endDate;
						}
					}
				}
			}
			map.put("flag", flag);
			map.put("date", date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Bizlet
	public Map checkInApp(double amount,String registerCeritNo){
		Map map = new HashMap();
		map.put("certiNo", registerCeritNo);
		Map flagValue = checkMortgagaValue(amount,registerCeritNo);
		Map flagDate = checkSubDate(map);
		if("false".equals(flagValue.get("flag"))){
			map.put("flag", flagValue);
			//map.put("msg", "权证下押品的权利价值["+flagValue.get("value")+"]与前台录入的登记金额不相等，请检查!");
			map.put("msg", "权证下押品的权利价值与前台录入的登记金额不相等，请检查!");
			return map;
		}
		if("false".equals(flagDate.get("flag"))){
			map.put("flag", flagDate);
			map.put("msg", "关联的担保合同失效日期["+flagDate.get("date")+"]大于他项权证信息录入页面中登记失效日期!");
			return map;
		}
		map.put("flag", "true");
		map.put("msg", "校验通过!");
		return map;
	}
	
	@Bizlet
	public String getIschukulx(String outType,String outId){
		String ischukulx="3";//无意义的值
		try {
			if("21".equals(outType)){//正式出库中结清，赋值-结清出库 （参数：结清出库、特殊出库（临时出库/强制出库）ischukulx：1-结清出库；2-特殊出库）
				ischukulx="2";
				Map map = new HashMap();
				map.put("outId", outId);
				//通过outId 查询tb_grt_out_detail.查询出库原因
				Object[] hDate = DatabaseExt.queryByNamedSql("default", 
						"com.bos.grt.grt.getOutType", map);
				if(hDate != null && hDate.length>0){
					DataObject dataObject = (DataObject) hDate[0];
					if("2101".equals(dataObject.getString("OUT_REASON"))){
						ischukulx="1";
					}
				}
			}else{
				ischukulx="2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ischukulx;
	}
	
	@Bizlet
	public String checkAnjie(String contractId){
		String msg = "1";
		try {
			//查询合同信息
			DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			tbConContractInfo.set("contractId",contractId);
			DatabaseUtil.expandEntity("default", tbConContractInfo);
			
			if( "02005001".equals(tbConContractInfo.getString("productType"))&& 
					("02".equals(tbConContractInfo.getString("guarantyType")) || "02,04".equals(tbConContractInfo.getString("guarantyType"))
					|| "04,02".equals(tbConContractInfo.getString("guarantyType")))
					){
				msg = "0";//校验 36
			}
			
			if( ("02002004".equals(tbConContractInfo.getString("productType")) || "02002005".equals(tbConContractInfo.getString("productType")))
					&& !"04,02".equals(tbConContractInfo.getString("guarantyType")) && !"02,04".equals(tbConContractInfo.getString("guarantyType"))
					){
				msg = "0";//校验 36
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 通过权证编号查询是否出库
	 * @return
	 */
	@Bizlet
	public String checkifout(Map map){
		String flag = "false";
		try {
			//首次录入校验
			if(map.containsKey("regNo") && map.get("suretyKeyId")==null){
				Object[] date = DatabaseExt.queryByNamedSql("default", 
						"com.bos.grt.grt.getRegCardDescUpdatetime", map);
				if(date != null && date.length>0){
					DataObject dataObject = (DataObject) date[0];//取最近做的一笔
					String suretyKeyId = dataObject.getString("SURETY_KEY_ID");
					map.put("suretyKeyId", suretyKeyId);
					//查询是否存在正式出库记录。并且出库状态为成功，如果成功
					Object[] hdate = DatabaseExt.queryByNamedSql("default", 
							"com.bos.grt.grt.checkIfOut", map);
					if(hdate != null && hdate.length>0){ 
						int num = 0;
						for(int i = 0;i<hdate.length;i++){
							DataObject hdataObject = (DataObject) hdate[i];
							if("04".equals(hdataObject.getString("MORTGAGE_STATUS")) && "21".equals(hdataObject.getString("OUT_TYPE"))){
								num++;
							} 
						}
						if(num == hdate.length){
							flag = "true";//如果为true。不校验是否重复
						}
					}
				}
			}
			//不安套路出牌，保存一次成功，完后更换权证编号，此时此刻，关系ID已经更新，但是传过来仍然是第一次id，此时需判断。如果传的不一样。校验，传的一样。不校验
			if(map.containsKey("regNo") && map.get("suretyKeyId")!=null){
				Object[] date = DatabaseExt.queryByNamedSql("default", 
						"com.bos.grt.grt.getRegCardDescUpdatetime", map);
				if(date != null && date.length>0){
					DataObject dataObject = (DataObject) date[0];//取最近做的一笔
					String suretyKeyId = dataObject.getString("SURETY_KEY_ID");
					if(map.get("suretyKeyId").equals(suretyKeyId)){
						flag = "other";
					}else{
						map.put("suretyKeyId", suretyKeyId);
						//查询是否存在正式出库记录。并且出库状态为成功，如果成功
						Object[] hdate = DatabaseExt.queryByNamedSql("default", 
								"com.bos.grt.grt.checkIfOut", map);
						if(hdate != null && hdate.length>0){ 
							int num = 0;
							for(int i = 0;i<hdate.length;i++){
								DataObject hdataObject = (DataObject) hdate[i];
								if("04".equals(hdataObject.getString("MORTGAGE_STATUS")) && "21".equals(hdataObject.getString("OUT_TYPE"))){
									num++;
								} 
							}
							if(num == hdate.length){
								flag = "true";//如果为true。不校验是否重复
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Bizlet
	public Map checkIfEfps(String suretyKeyId){
		Map map = new HashMap();
		
		DataObject TbGrtRegCard = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRegCard");
		TbGrtRegCard.set("suretyKeyId", suretyKeyId);
		DataObject[] cards = DatabaseUtil.queryEntitiesByTemplate("default", TbGrtRegCard);
		
		if("1".equals(cards[0].getString("ifEfps"))){
			map.put("flag", "false");
			map.put("msg", "该操作为期转现操作，不允许删除关联押品信息!");
			return map;
		}
		map.put("flag", "true");
		map.put("msg", "校验通过!");
		return map;
	}
}
