package com.bos.grtPro;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.bos.pub.GitUtil;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.mgrcore.FXD091;
import com.primeton.mgrcore.FXD092;
import com.primeton.mgrcore.OXD091_PawnInOutReq;
import com.primeton.mgrcore.OXD092_PawnInOutRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;

public class CallBackForEndInProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndInProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	
	//入库流程结束时执行的逻辑
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为03
		
		/*--入库步骤
		 *1.更新业务表审批状态
		 *2.调接口
		-*/
		String[] xpath={"bizId","wfCreateOrgCode"};//获取相关数据的数组        add wfCreateOrgCode by shangmf
		ITransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();

		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				
			//add by shangmf:取经办人机构
			String ownerOrgCode=(String)list.get(1);
			if(null==ownerOrgCode||"".equals(ownerOrgCode)){
				logger.info("流程返回的经办人机构为空！");
				throw new EOSException("流程返回的经办人机构为空");
			}	
				
	        logger.info("------------>押品入库流程结束，开始更新业务状态------outId="+outId+"------->开始!");
			transactionManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);

			Map<String,Object> hmap = new HashMap<String,Object>();
			hmap.put("bizId", outId);
			hmap.put("state", "03");
			hmap.put("user", GitUtil.getCurrentUserId()); //--
			hmap.put("org", GitUtil.getCurrentOrgId()); //--
			
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateGrtIn", hmap);
			Map<String,Object> mmap = new HashMap<String,Object>();
			mmap.put("id", outId);
			Object[] outs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
					"com.bos.grt.grt.selectGrtInDetail", mmap);
			if(outs.length == 0){
				logger.info("-----未查到相关入库信息------");
				throw new EOSException("未查到相关入库信息");
			}
			if(outs.length > 0){
				DataObject datat = (DataObject) outs[0];
				String suretyKeyId=datat.getString("SURETY_KEY_ID");
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("outId", outId);
				map1.put("suretyKeyId", suretyKeyId);
				Object[] data = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.grt.grt.queryInApprovel", map1);
				if (null == data || data.length == 0) {
					logger.info("-----未查到相关押品信息------>bizId=" + outId);
				}
				//更新押品状态为09，已入库
				map1.put("mortgagaStatus", "09");//押品状态已入库
				
				DatabaseExt.executeNamedSql("default", "com.bos.grt.grt.updateMortgagaStatus", map1);
				
				//保存押品入库账务机构  added 2017 11 21 sdl
				String zwjg= GitUtil.getAccOrgOffBalance(ownerOrgCode);//根据经办人机构获取账务机构
				DataObject tbGrtRegCard = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRegCard");
				tbGrtRegCard.setString("suretyKeyId", suretyKeyId);
				if(!(null==suretyKeyId||"".equals(suretyKeyId))){
					DatabaseUtil.expandEntity("default", tbGrtRegCard);
					tbGrtRegCard.setString("accOrgCode", zwjg);//账务机构
					DatabaseUtil.updateEntity("default", tbGrtRegCard);//保存信息
				}
			
				
				//调接口，同步数据
				CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
				CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
				CollServiceImplServiceServiceStub.CollServiceCommInter serQuery = new CollServiceImplServiceServiceStub.CollServiceCommInter();
				ObjectMapper mapper = new ObjectMapper();
				
				//判断入库权证类对应的产品是按揭贷款（ 02002004个人商用房按揭贷款，02002005个人住房按揭贷款），且是“他项权证”或者“不动产登记证明-抵押权”的话，入库成功后，后台判断如果有保证，就自动解除关联关系
				//取合同号，担保合同号 
				List<String> conlist = new ArrayList<String>();
				List<Map> maplist = new ArrayList<Map>();
				String conno = "";
				String subno = "";
				for(int j =0;j<data.length;j++){
					Map conMap = new HashMap();
					DataObject jdataObject = (DataObject) data[j];
					conno = jdataObject.getString("CONTRACT_ID");
					if(!conlist.contains(conno)){
						conlist.add(conno);
						conMap.put("contractId", conno);
						conMap.put("cardType", jdataObject.getString("CARD_TYPE"));
						maplist.add(conMap);
					}
				}
				//针对不同的业务合同进行操作(现 入库。支持本人不同合同下的押品在一个权证下)
				for(int z = 0;z<maplist.size();z++){
					Map tmp = maplist.get(z);
					String guarantyType = "";
					if("0301".equals(tmp.get("cardType")) || "0302".equals(tmp.get("cardType")) || "0119".equals(tmp.get("cardType"))){
						//查询合同下担保(保证)合同及合同产品代码是否为；02002004个人商用房按揭贷款，02002005个人住房按揭贷款
						Object[] zdata = DatabaseExt.queryByNamedSql(
								GitUtil.DEFAULT_DS_NAME,
								"com.bos.grt.grt.queryconInfo", tmp);
						if (null == zdata || zdata.length == 0) {
							
						}else{
							for(int p = 0;p<zdata.length;p++){
								DataObject zzob = (DataObject) zdata[p];
								if(("02002004".equals(zzob.getString("PRODUCT_TYPE")) || "02002005".equals(zzob.getString("PRODUCT_TYPE")))
										&& ("04,02".equals(zzob.getString("GUARANTY_TYPE")) || "02,04".equals(zzob.getString("GUARANTY_TYPE")))
										&& "01".equals(zzob.getString("PROGUARANTY_FORM"))){
										//担保方式 仅 保证，抵押 执行此操作
										Map<String,String> pmap = new HashMap<String,String>();
										pmap.put("subcontractId", zzob.getString("SUBCONTRACT_ID"));
										pmap.put("contractId", String.valueOf(tmp.get("contractId")));
										
										// 有在途的担保合同调整流程
										List<MessageObj> msgList = new ArrayList<MessageObj>();
										RuleService rs = new RuleService();
										msgList = rs.runRule("SUBCON_FLOW", pmap);
										String msg = convertMsg(msgList);
										if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
											System.out.println("---有在途的担保合同调整流程---");
											if (transactionManager != null) {
												transactionManager.rollback();
											}
											throw new EOSException("有在途的担保合同调整流程");
										}
										
										//担保合同状态置为  06  已删除  XD_SXCD8003
										DatabaseExt.executeNamedSql("default", 
												"com.bos.grt.grt.updateSubcontractStatus", pmap);
										//通过担保合同ID将关联关系删除
										DatabaseExt.executeNamedSql("default", 
												"com.bos.grt.grt.delSubcontract", pmap);
										//修改业务合同中 担保方式。如果存在保证，则将保证删除
										if(guarantyType==""){
											guarantyType = zzob.getString("GUARANTY_TYPE");
										}
										if(guarantyType.indexOf("04") != -1){
											//包含保证，此操作为避免对合同担保方式多次更新操作
											String[] guaarray = guarantyType.split(",");
											String gua = "";
											StringBuffer sb = new StringBuffer();
											for(int yy = 0; yy<guaarray.length ; yy++){
												gua = guaarray[yy];
												if(!"04".equals(gua)){
													if(sb.length() == 0){
														sb.append(guaarray[yy]);
													}else{
														sb.append(","+guaarray[yy]);
													}
												}
											}
											guarantyType = sb.toString();
											pmap.put("guarantyType", guarantyType);
											DatabaseExt.executeNamedSql("default", 
													"com.bos.grt.grt.updateGuaType", pmap);
										}
										
										//通过担保合同号查询押品编号
										Object[] ydata = DatabaseExt.queryByNamedSql(
												GitUtil.DEFAULT_DS_NAME,
												"com.bos.grt.grt.querySuretyid", pmap);
										if (null == ydata || ydata.length == 0) {
											
										}else{
											for(int q = 0;q<ydata.length;q++){
												DataObject qqob = (DataObject) ydata[q];
												pmap.put("suretyId", qqob.getString("SURETY_ID"));
												//恢复担保品可用价值
												DatabaseExt.executeNamedSql("default", 
														"com.bos.grt.subContractManage.subContractManage.updateBzrAviAmt", pmap);
											}
										}
									}
								}
							}
						}
					}
				
				for(int i =0;i<data.length;i++){
					DataObject dataObject = (DataObject) data[i];
					
					logger.info("------------>调用核心押品接口------->开始!");
				//管理调用核心【XD09】信贷抵质押物表外记账接口
					CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

					OXD091_PawnInOutReq oxd091ReqBody = new OXD091_PawnInOutReq();
					OXD092_PawnInOutRes oxd092Res = new OXD092_PawnInOutRes();
					
					oxd091ReqBody.setOperFlag("0");// 操作标志 0-入库记账
					//oxd091ReqBody.setChargeBrch(dataObject.getString("ORG_NUM"));// 入账机构   ORG_NUM   TB_GRT_IN					
					//modi by shangmf:赋经办人机构
					oxd091ReqBody.setChargeBrch(ownerOrgCode);
					
//					oxd091ReqBody.setChargeBrch("0400");
					//已向业务确认，根据产品区分，公司委托贷款，个人委托贷款，公积金委托贷款，直接赋值 代保管品。其他不是 N-否 Y-是
					String type = "";
					if("01".equals(dataObject.getString("COLL_TYPE"))){
						type = "1";
					}else{
						type = "2";
					}
					oxd091ReqBody.setCollateralWay(type);// 抵质押方式  TB_GRT_MORTGAGE_BASIC COLL_TYPE  需转成1,2
					Map ffmap = new HashMap();
					String yn = "N";//是否代保管品
					ffmap.put("suretyId", dataObject.getString("SURETY_ID"));
					Object[] bizDate = DatabaseExt.queryByNamedSql("default", 
							"com.bos.grt.grt.getBizTypeForId", ffmap);
					if(bizDate != null && bizDate.length>0){
						DataObject bizDataObject = (DataObject) bizDate[0];//
						if("02005001".equals(bizDataObject.getString("PRODUCT_TYPE")) || "02005010".equals(bizDataObject.getString("PRODUCT_TYPE")) || "02005002".equals(bizDataObject.getString("PRODUCT_TYPE"))
								 || "01012002".equals(bizDataObject.getString("PRODUCT_TYPE"))  || "02005003".equals(bizDataObject.getString("PRODUCT_TYPE"))){
							//属于委托业务，为代管品
							yn = "Y";
						}
					}
					oxd091ReqBody.setYnFlag(yn);// 是否标志
//					oxd091ReqBody.setPrdCode("");// 产品代码  权证编号 REGISTER_CERTI_NO
					oxd091ReqBody.setActualValue(dataObject.getString("MORTGAGE_VALUE"));// 实际价值   TB_GRT_MORTGAGE_BASIC  MORTGAGE_VALUE
					//币种转换
					String cy = dataObject.getString("CURRENCY_CD");
					if("".equals(cy) || cy==null){
						oxd091ReqBody.setCurrCode("01");// 货币代号   TB_GRT_MORTGAGE_BASIC CURRENCY_CD 需转换datat.getString("CURRENCY_CD")
					}else{
						if("CNY".equals(cy)){
							cy="01";
						}else if("FRF".equals(cy)){//法国法郎
							cy="250";
						}else if("DEM".equals(cy)){//德国马克
							cy="276";
						}else if("HKD".equals(cy)){//港币
							cy="13";
						}else if("ITL".equals(cy)){//意大利里拉
							cy="380";
						}else if("JPY".equals(cy)){//日元
							cy="27";
						}else if("KRW".equals(cy)){//韩国元
							cy="410";
						}else if("MOP".equals(cy)){//澳门元
							cy="81";
						}else if("MYR".equals(cy)){//马来西亚币
							cy="458";
						}else if("NLG".equals(cy)){//荷兰盾
							cy="528";
						}else if("NZD".equals(cy)){//新西兰元 
							cy="554";
						}else if("AUD".equals(cy)){//澳洲元
							cy="29";
						}else if("NOK".equals(cy)){//挪威克朗
							cy="578";
						}else if("PHP".equals(cy)){//菲律宾比索
							cy="608";
						}else if("RUB".equals(cy)){//卢布
							cy="643";
						}else if("SGD".equals(cy)){//新加坡元
							cy="32";
						}else if("ESP".equals(cy)){//西班牙比塞塔
							cy="724";
						}else if("SEK".equals(cy)){//瑞典克朗
							cy="752";
						}else if("CHF".equals(cy)){//瑞士法郎
							cy="15";
						}else if("THB".equals(cy)){//泰国铢
							cy="764";
						}else if("GBP".equals(cy)){//英镑
							cy="12";
						}else if("USD".equals(cy)){//美元
							cy="14";
						}else if("EUR".equals(cy)){//欧元
							cy="38";
						}else if("ATS".equals(cy)){//奥地利先令
							cy="040";
						}else if("OTHER".equals(cy)){//其他
							cy="999";
						}else if("BEF".equals(cy)){//比利时法郎
							cy="056";
						}else if("CAD".equals(cy)){//加拿大元
							cy="28";
						}else if("TWD".equals(cy)){//新台湾币
							cy="158";
						}else if("DKK".equals(cy)){//丹麦克朗
							cy="208";
						}else if("FIM".equals(cy)){//芬兰马克
							cy="246";
						}else{
							throw new EOSException("不支持的币种");
						}
						oxd091ReqBody.setCurrCode(cy);
					}
//					oxd091ReqBody.setSummaryCode("");// 摘要代码
					oxd091ReqBody.setSummary(dataObject.getString("REGISTER_CERTI_NO"));// 摘要内容 权利价值
					oxd091ReqBody.setCustNo(dataObject.getString("ECIF_PARTY_NUM"));// 客户号 ECIF_PARTY_NUM   TB_CSM_PARTY
//					oxd091ReqBody.setBackupAmt("");// 备用金额
					
					//判断押品种类是否 “存单”，如果是存单，则输入列表，如果不是，则赋列表记录数为-0[采用一条数据一条数据传输,一个存单只有一个账号不传押品分账号]
					if("A01010101".equals(dataObject.getString("SORT_TYPE")) || "A01010102".equals(dataObject.getString("SORT_TYPE"))){
						
						oxd091ReqBody.setRecNum(new BigInteger("1"));//循环记录数
						FXD091[] fxd091Array = new FXD091[(oxd091ReqBody.getRecNum().intValue())];
						for (int iu = 0; iu < fxd091Array.length; iu++) {
							//调用押品详细信息查询接口，
							Map ypxxQueryMap = new HashMap();
							ypxxQueryMap.put("cltNo", dataObject.getString("SURETY_NO"));//押品编号 tb_grt_card_mortagage  tb_grt_mortgage_basic
							ypxxQueryMap.put("trans_code","1113");//押品详细信息查询接口交易码
							// Convert object to JSON string  
							String ypxxQueryJsonStr = null;
							ypxxQueryJsonStr = mapper.writeValueAsString(ypxxQueryMap);
							serQuery.setIn0(ypxxQueryJsonStr);
							String queryStr = stub.collServiceCommInter(serQuery).getOut1();	
							//解析queryStr
							Map strmap = mapper.readValue(queryStr,HashMap.class);
							logger.info("信贷查回的押品详情:"+queryStr);
							//add by shangmf:增加是否是我行存单的条件校验 1:是，0:否
							if( "1".equals((String) strmap.get("isDepsNo"))){
								
								FXD091 fxd091 = new FXD091();
								fxd091.setYnFlag1("1");// 是否标志,存单默认为“是”
	//							fxd091.setFrzNum("");// 冻结编号
								fxd091.setCustAcct((String) strmap.get("depsAccountNo"));// 客户账号 "6223670000000078841"
		//						fxd091.setCustAcct("03062200000012");// 客户账号 "6223670000000078841"
	//							fxd091.setAcctname("");// 账户名称
	//							fxd091.setSubAcctSeri("");// 子账户序号
								
								//modi by shangmf:币种按实际的币种赋值
								//fxd091.setCurrCode("01");// 货币代号 depsCcyCd  (String) strmap.get("depsCcyCd")
								String depsCcyCd = (String) strmap.get("depsCcyCd");
								if( depsCcyCd != null && depsCcyCd.length() > 0 ){
									fxd091.setCurrCode(depsCcyCd);
								}else{
									//如果取不到值的话赋值01，以避免异常
									fxd091.setCurrCode("01");
								}
								
	//							fxd091.setCashFlag("");// 钞汇标志
	//							fxd091.setFreezeType("");// 冻结种类
	//							fxd091.setFreezeEndDate("");// 冻结终止日期
								fxd091.setFreezeAmt( String.valueOf(strmap.get("depsAmt")) );// 需冻结金额 depsAmt
	//							fxd091.setFrzCase("");// 冻结原因
	//							fxd091.setFreezeEnsureFileType("");// 冻结证明文书类别
	//							fxd091.setFreezeNotifyNo("");// 冻结通知书编号
								fxd091.setYnFlag2("1");// 是否标志
	//							fxd091.setBackupAmt("");// 备用金额
								fxd091Array[iu] = fxd091 ;
								oxd091ReqBody.setFxd091(fxd091Array);
							}else{
								oxd091ReqBody.setRecNum(new BigInteger("0"));//循环记录数
							}
						}
						
					}else{
						oxd091ReqBody.setRecNum(new BigInteger("0"));//循环记录数
					}
					logger.info("------------>调用核心押品接口XD09---押品编号="+dataObject.getString("SURETY_NO")+",权证编号="+dataObject.getString("REGISTER_CERTI_NO")+",ECIF客户号="+dataObject.getString("ECIF_PARTY_NUM")+",实际价值="+dataObject.getString("MORTGAGE_VALUE"));
					oxd092Res = impl.executeXD09(oxd091ReqBody);
					System.out.println(oxd092Res.getResTranHeader().getHRetCode()+","+oxd092Res.getOxd092ResBody().toString());
					logger.info("------------>调用核心押品接口XD09---oxd091ReqBody="+oxd091ReqBody+"---->结束!");

					if(!"AAAAAAA".equals(oxd092Res.getResTranHeader().getHRetCode())){
						throw new EOSException("提示信息："+oxd092Res.getResTranHeader().getHRetMsg());
					}
					
					//保存核心押品冻结信息
					List<FXD092> FXD092List = oxd092Res.getOxd092ResBody().getFxd092();
					if(!"".equals(FXD092List.get(0).getFrzNum()) && FXD092List.get(0).getFrzNum() != null){
						
						for(int d= 0;d < FXD092List.size();d++){
							Map freemap = new HashMap();
							if(!"".equals(FXD092List.get(d).getOperFlag()) && FXD092List.get(d).getOperFlag() != null){
								freemap.put("caozbz",FXD092List.get(d).getOperFlag());// 操作标志
							}
							if(!"".equals(FXD092List.get(d).getFrzNum()) && FXD092List.get(d).getFrzNum() != null){
								freemap.put("frenum",FXD092List.get(d).getFrzNum());// 冻结编号
							}
							if(!"".equals(FXD092List.get(d).getCustAcct()) && FXD092List.get(d).getCustAcct() != null){
								freemap.put("cuacno",FXD092List.get(d).getCustAcct());// 客户账号
							}
							if(!"".equals(FXD092List.get(d).getFreezeType()) && FXD092List.get(d).getFreezeType() != null){
								freemap.put("freekd",FXD092List.get(d).getFreezeType());// 冻结种类
							}
							double amt = 0.0;
							if("".equals(FXD092List.get(d).getFreezeAmt()) || FXD092List.get(d).getFreezeAmt() == null){
								freemap.put("freamt",amt);// 需冻结金额
							}else{
								freemap.put("freamt",Double.parseDouble(FXD092List.get(d).getFreezeAmt()));// 需冻结金额
							}
							if(!"".equals(FXD092List.get(d).getFrzCase()) && FXD092List.get(d).getFrzCase() != null){
								freemap.put("frerea",FXD092List.get(d).getFrzCase());// 冻结原因
							}
							if(!"".equals(FXD092List.get(d).getFreezeNotifyNo()) && FXD092List.get(d).getFreezeNotifyNo() != null){
								freemap.put("fzntnm",FXD092List.get(d).getFreezeNotifyNo());// 冻结通知书编号
							}
							if(!"".equals(dataObject.getString("REGISTER_CERTI_NO")) && dataObject.getString("REGISTER_CERTI_NO") != null){
								freemap.put("register_certi_no",dataObject.getString("REGISTER_CERTI_NO"));// 权证编号
							}
							if(!"".equals(dataObject.getString("SURETY_ID")) && dataObject.getString("SURETY_ID") != null){
								freemap.put("surety_id",dataObject.getString("SURETY_ID"));// 押品基本信息ID
							}
							freemap.put("create_time",GitUtil.getBusiDate());// 创建时间
							freemap.put("update_time",GitUtil.getBusiDate());// 更新时间
							System.out.println("保存至冻结表参数："+freemap);
							DatabaseExt.executeNamedSql("default", 
									"com.bos.grt.grt.insertFreeze", freemap);
						}
					}
					
					logger.info("------------>调用核心押品接口---oxd091ReqBody="+oxd091ReqBody+"---->结束!");
					logger.info("------------>调用押品系统押品同步接口------>开始!");
					Map ypxxMap = new HashMap();
					ypxxMap.put("ope_flag", "1");//入库
					ypxxMap.put("in_out_flag", dataObject.getString("IN_TYPE"));//入库类型 tb_grt_in
					ypxxMap.put("in_out_result", dataObject.getString("IN_REASON"));//入库原因 tb_grt_in
					ypxxMap.put("clt_no", dataObject.getString("SURETY_NO"));//押品编号 tb_grt_card_mortagage  tb_grt_mortgage_basic
					ypxxMap.put("guarantyright_nm", dataObject.getString("CARD_TYPE"));//权证名称 TB_GRT_REG_CARD
					ypxxMap.put("signeename",dataObject.getString("PARTY_NAME"));//押品所有权人名称 tb_csm_party
					ypxxMap.put("guarantyright_id", dataObject.getString("REGISTER_CERTI_NO"));//登记权证编号 TB_GRT_REG_CARD 
					ypxxMap.put("warrant_management_org_name",dataObject.getString("REG_ORG_NAME"));//登记机构名称TB_GRT_REG_CARD 
					ypxxMap.put("warrant_amt", dataObject.getString("REG_ORG_MONEY"));//登记金额 TB_GRT_REG_CARD
					ypxxMap.put("warrant_info_force_dt", dataObject.getString("CARD_REG_DATE"));//登记生效日期 TB_GRT_REG_CARD
					ypxxMap.put("warrant_efficacy_dt", dataObject.getString("REG_DUE_DATE"));//登记到期日期TB_GRT_REG_CARD
					ypxxMap.put("warrant_management_org_no", dataObject.getString("SAVE_ORG"));//保管机构TB_GRT_REG_CARD 
					ypxxMap.put("roll_out_dt", dataObject.getString("UPDATE_TIME"));//入库时间 tb_grt_in
					ypxxMap.put("storage_id", dataObject.getString("IN_ID"));//出入库申请流水号 tb_grt_in
					ypxxMap.put("storage_issue_nm", dataObject.getString("EMPNAME"));//发起人名称 om_employee
					
					//added by shendl 20171121 押品的账务机构同步到押品系统
					ypxxMap.put("warrant_acc_org_no",GitUtil.getAccOrgOffBalance(ownerOrgCode));//账务机构
					
					//将发起机构code变为机构ID
					Map map=new HashMap();
					map.put("orgCode", dataObject.getString("ORG_NUM"));
					Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.pay.getLoanNoticeInfo.getOrg", map);
					if(orgS.length>0){
						DataObject con4 = (DataObject) orgS[0];
						if(con4.getString("ORGID")!=null && con4.getString("ORGID")!=""){
							ypxxMap.put("storage_issue_org", con4.getString("ORGID"));//发起机构 tb_grt_in
						}
					}
				
					ypxxMap.put("tally_dt", dataObject.getString("UPDATE_TIME"));//记账日期 tb_grt_in
					ypxxMap.put("storage_issue_id", dataObject.getString("USER_NUM"));//发起人ID  TB_GRT_IN
					ypxxMap.put("trans_code","1105");//出入库同步接口交易码
					// Convert object to JSON string  
					String ypxxJsonStr = null;
					ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
					System.out.println("押品出入库接口入参："+ypxxJsonStr);
					ser.setIn0(ypxxJsonStr);
					logger.info("------------>调用押品系统押品同步接口---ypxxJsonStr="+ypxxJsonStr+"---->结束!");
					String flag = stub.collServiceCommInter(ser).getOut1();	
					logger.info("------------>调用押品系统押品同步接口结束------flag="+flag+"------>结束!");
				}
			}
			logger.info("------------>押品入库流程结束，开始更新业务状态------outId="+outId+"------>结束!");
			transactionManager.commit();
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			if (transactionManager != null) {
				transactionManager.rollback();
			}
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			if (transactionManager != null) {
				transactionManager.rollback();
			}
			throw new EOSException(e.getMessage());
		}
		
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		//提交流程  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		ITransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();

		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
	logger.info("------------>押品入库流程拒绝，开始更新业务状态------outId="+outId+"------->开始!");
			transactionManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bizId", outId);
			map.put("state", "06");
			map.put("user", GitUtil.getCurrentUserId()); //--
			map.put("org", GitUtil.getCurrentOrgId()); //--
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateGrtIn", map);
			
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateCardState", map);//将已入库的状态恢复
			
			//恢复押品的状态--将押品状态 是已入库的变成01
			map.put("mortgageStatus", "01");
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateMortgageStatus", map);//将已入库的状态恢复
			
			transactionManager.commit();
	logger.info("------------>押品入库流程拒绝，开始更新业务状态---map--outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			if (transactionManager != null) {
				transactionManager.rollback();
			}
			throw new EOSException("------------>押品入库流程拒绝失败!");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			if (transactionManager != null) {
				transactionManager.rollback();
			}
			throw new EOSException("------------>押品入库流程拒绝失败!");
		}
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

	private String convertMsg(List<MessageObj> msgList) {
		StringBuffer sf = new StringBuffer();
		if (msgList != null && !msgList.isEmpty()) {
			for (int i = 0; i < msgList.size(); i++) {
				MessageObj t = msgList.get(i);
				if (EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())) {
					sf.append("[(" + (i + 1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
				}
			}
		}
		if (sf.length() > 0) {
			return sf.toString();
		}
		return "true";
	}
}
