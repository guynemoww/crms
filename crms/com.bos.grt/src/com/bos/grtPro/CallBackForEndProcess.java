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
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.mgrcore.FXD091;
import com.primeton.mgrcore.OXD091_PawnInOutReq;
import com.primeton.mgrcore.OXD092_PawnInOutRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.spring.support.DataObjectUtil;
import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);

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

	
	//出库流程结束时执行的逻辑
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为03
		
		/*--出库步骤
		--1.查出库主表 返回主键
		select * from TB_GRT_OUT where OUT_ID='ff8080814fabb184014fac3d43ad0336'
		--2.查出库明细表 返回权证主键
		select * from TB_GRT_OUT_DETAIL where OUT_ID='ff8080814fabb184014fac3d43ad0336'
		--3.查权证主表  
		select * from TB_GRT_REG_CARD where SURETY_KEY_ID='ff8080814fabb184014fac3cea710332'
		--4.查权证与押品管理表 返回押品主键ID
		select * from TB_GRT_CARD_MORTAGAGE where SURETY_KEY_ID='ff8080814fabb184014fac3cea710332'
		--5.更新权证状态和押品状态*/
		String[] xpath={"bizId"};//获取相关数据的数组
		ITransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();

		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			transactionManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);

			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
	logger.info("------3231------>押品出库流程结束，开始更新业务状态------outId="+outId+"------->开始!");
			DataObject out = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtOut");
			out.set("outId", outId);
			out.set("approveState", "03");
			DatabaseUtil.updateEntity("default", out);
			
			DataObject outDetail = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtOutDetail");
			outDetail.set("outId", outId);
			DataObject[] outs = DatabaseUtil.queryEntitiesByTemplate("default", outDetail);
			
//			Map map=new HashMap();
//			map.put("outId", outId);
//			Object[] arrays= DatabaseExt.queryByNamedSql("default","com.bos.grt.grt.queryOutLogList", map);
			
			//首先判断是不是期房出库，如果是期房临时出库，则该笔为期转现，置期转现标志为是，否则为否
			String ifEfps = "0";
			HashMap mmmmap = new HashMap();
			mmmmap.put("bizId", outId);
			Object[] mdata = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grt.getGrtOut", mmmmap);
			if (null == mdata || mdata.length == 0) {
				throw new EOSException("未查到相关出库信息");
			}else{
				DataObject mdataObject = (DataObject) mdata[0];
				String outReason = mdataObject.getString("OUT_REASON");
				String outType = mdataObject.getString("OUT_TYPE");
				if("22".equals(outType) && "2204".equals(outReason)){
					ifEfps = "1";
				}
			}
			
			for(int i = 0;i<outs.length;i++){
				
				//取出库明细表的权证ID
				String suretyKeyId=outs[i].getString("suretyKeyId");
				
				//更新权证主表状态为已出库
				DataObject grtCard = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRegCard");
				grtCard.set("suretyKeyId", suretyKeyId);
				grtCard.set("cardState", "04");		//标记为已出库
				grtCard.set("ifEfps", ifEfps);		//标记为已出库
				DatabaseUtil.updateEntity("default", grtCard);
				
				//根据条件查询权证与押品关联表  
				DataObject grtCardMortagage = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtCardMortagage");
				grtCardMortagage.set("suretyKeyId", suretyKeyId);
				DataObject[] cards = DatabaseUtil.queryEntitiesByTemplate("default", grtCardMortagage);
				
				//遍历关联表 取押品ID 更改状态
				for(int j=0;j<cards.length;j++){
					String suretyId=cards[j].getString("suretyId");
					
					DataObject basic = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMortgageBasic");
					basic.set("suretyId", suretyId);
					basic.set("mortgageStatus", "04");
					DatabaseUtil.updateEntity("default", basic);
				}
				//调用接口传输数据
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("outId", outId);
				map.put("suretyKeyId", suretyKeyId);
				Object[] data = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.grt.grt.queryOutApprovel", map);
				if (null == data || data.length == 0) {
					logger.info("-----未查到相关押品信息------>bizId=" + outId);
					throw new EOSException("未查到相关出库押品信息");
				}
				//调接口，同步数据
				CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
				CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
				ObjectMapper mapper = new ObjectMapper();
				List checklist = new ArrayList();
				String checkflag = "false";
				for(int j =0;j<data.length;j++){
					DataObject dataObject = (DataObject) data[j];
					//因为老数据可能会存在同一个押品循环出库的情况，现加上限制，如果已经出库，跳出循环
					for(int y =0;y<checklist.size();y++){
						if((dataObject.getString("SURETY_ID")).equals(checklist.get(y))){
							checkflag = "true";
						}
					}
					if("true".equals(checkflag)){
						break;
					}else{
						checklist.add(dataObject.getString("SURETY_ID"));
					}
					
					//20171204 取入库时保存的账务机构，出库的账务机构必须是入库的账务机构
					String accOrgCode = dataObject.getString("ACC_ORG_CODE");
					
					logger.info("------------>检查押品是否重复出库------->结束checkflag:"+checkflag);
					logger.info("------------>调用核心押品接口------->开始!");
				//管理调用核心【XD09】信贷抵质押物表外记账接口
					CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

					OXD091_PawnInOutReq oxd091ReqBody = new OXD091_PawnInOutReq();
					OXD092_PawnInOutRes oxd092Res = new OXD092_PawnInOutRes();
					
					oxd091ReqBody.setOperFlag("1");// 操作标志 1-出库记账
					if(accOrgCode != null && !"".equals(accOrgCode)){
						oxd091ReqBody.setChargeBrch(accOrgCode);//入库时直接保存账务机构
						oxd091ReqBody.setReserveMark1("o");//备用标志。现表示，如果是o，则账务机构直接取传入值，不用再转换
					}else{
						oxd091ReqBody.setChargeBrch(dataObject.getString("ORG_NUM"));// 入账机构   ORG_NUM   TB_GRT_IN
					}
					
				//	oxd091ReqBody.setOrgNum(oxd091ReqBody.getChargeBrch());// 入账机构   ORG_NUM   TB_GRT_IN
				//	oxd091ReqBody.setChargeBrch("0400");
					//已向业务确认，根据产品区分，公司委托贷款，个人委托贷款，公积金委托贷款，直接赋值 代保管品。其他不是 N-否 Y-是
					Map ffmap = new HashMap();
					String yn = "N";//是否代保管品
					ffmap.put("suretyId", dataObject.getString("SURETY_ID"));
					Object[] bizDate = DatabaseExt.queryByNamedSql("default", 
							"com.bos.grt.grt.getBizTypeForId", ffmap);
					if(bizDate != null && bizDate.length>0){
						DataObject bizDataObject = (DataObject) bizDate[0];//
						if("02005010".equals(bizDataObject.getString("PRODUCT_TYPE")) || "02005001".equals(bizDataObject.getString("PRODUCT_TYPE")) || "02005002".equals(bizDataObject.getString("PRODUCT_TYPE"))
								 || "01012002".equals(bizDataObject.getString("PRODUCT_TYPE"))  || "02005003".equals(bizDataObject.getString("PRODUCT_TYPE"))){
							//属于委托业务，为代管品
							yn = "Y";
						}
					}
					oxd091ReqBody.setYnFlag(yn);// 是否标志
//					oxd091ReqBody.setPrdCode("");// 产品代码  权证编号 REGISTER_CERTI_NO
					String type = "";
					if("01".equals(dataObject.getString("COLL_TYPE"))){
						type = "1";
					}else{
						type = "2";
					}
					oxd091ReqBody.setCollateralWay(type);// 抵质押方式  TB_GRT_MORTGAGE_BASIC COLL_TYPE  需转成1,2
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
						CollServiceImplServiceServiceStub.CollServiceCommInter serQuery11 = new CollServiceImplServiceServiceStub.CollServiceCommInter();
						//调用押品详细信息查询接口，
						Map ypxxQueryMap11 = new HashMap();
						ypxxQueryMap11.put("cltNo", dataObject.getString("SURETY_NO"));//押品编号 tb_grt_card_mortagage  tb_grt_mortgage_basic
						ypxxQueryMap11.put("trans_code","1113");//押品详细信息查询接口交易码
						// Convert object to JSON string  
						String ypxxQueryJsonStr11 = null;
						ypxxQueryJsonStr11 = mapper.writeValueAsString(ypxxQueryMap11);
						serQuery11.setIn0(ypxxQueryJsonStr11);
						String queryStr11 = stub.collServiceCommInter(serQuery11).getOut1();	
						//解析queryStr
						Map strmap11 = mapper.readValue(queryStr11,HashMap.class);
						logger.info("信贷查回的押品详情:"+queryStr11);
						
						//针对质押扣划，如果质押扣划的金额已经扣完，则不需要给核心发送质押列表
						DataObject tbGrtMortgageBasic = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMortgageBasic");
						tbGrtMortgageBasic.set("suretyNo", dataObject.getString("SURETY_NO"));
						DatabaseUtil.expandEntityByTemplate("default", tbGrtMortgageBasic, tbGrtMortgageBasic);
						String zykhflag = "false";
						//如果该押品做过质押扣划，判断已担保金额与已扣划金额是否相等，如果相等，则认为已扣划完全，出库不给核心发送冻结信息
						if(tbGrtMortgageBasic != null & tbGrtMortgageBasic.getString("isDoneZykh") != null && "1".equals(tbGrtMortgageBasic.getString("isDoneZykh"))){
							 if(tbGrtMortgageBasic.getDouble("mortgageValue") == tbGrtMortgageBasic.getDouble("totalKhAmt"))
								 zykhflag = "true";
						}
						
						//add by shangmf:增加是否是我行存单的条件校验 1:是，0:否
						if( "1".equals((String) strmap11.get("isDepsNo"))){
							oxd091ReqBody.setRecNum(new BigInteger("1"));//循环记录数
							FXD091[] fxd091Array = new FXD091[(oxd091ReqBody.getRecNum().intValue())];
							for (int iu = 0; iu < fxd091Array.length; iu++) {
								FXD091 fxd091 = new FXD091();
								if("false".equals(zykhflag)){
									fxd091.setYnFlag1("1");// 是否标志,存单默认为“是”
								}else{
									fxd091.setYnFlag1("0");// 是否标志,存单质押扣划，扣划完全后不进行解冻
								}
								
								//查询上一笔冻结数据，用权证编号与押品ID作条件，desc
								Map freemap = new HashMap();
								if("0".equals(dataObject.getString("TRANS_FLAG"))){//非移植数据，使用权证编号加押品ID查询，移植数据，仅使用押品ID查询
									freemap.put("regNo", dataObject.getString("REGISTER_CERTI_NO"));
								}
								freemap.put("si", dataObject.getString("SURETY_ID"));
								Object[] freeDate = DatabaseExt.queryByNamedSql("default", 
										"com.bos.grt.grt.getFreeze", freemap);
								if(freeDate != null && freeDate.length>0){
									DataObject fo = (DataObject) freeDate[0];
									fxd091.setFrzNum(fo.getString("FRENUM"));// 冻结编号  FRENUM
									fxd091.setCustAcct(fo.getString("CUACNO"));// 客户账号 "6223670000000078841"
									//						fxd091.setAcctname("");// 账户名称
									//						fxd091.setSubAcctSeri("");// 子账户序号
									
									String depsCcyCd = (String) strmap11.get("depsCcyCd");
									if( depsCcyCd != null && depsCcyCd.length() > 0 ){
										fxd091.setCurrCode(depsCcyCd);
									}else{
										//如果取不到值的话赋值01，以避免异常
										fxd091.setCurrCode("01");// 货币代号 depsCcyCd  (String) strmap.get("depsCcyCd")
										//fxd091.setCurrCode("01");
									}
									//						fxd091.setCashFlag("");// 钞汇标志
									//						fxd091.setFreezeType("");// 冻结种类
									//						fxd091.setFreezeEndDate("");// 冻结终止日期
									fxd091.setFreezeAmt(fo.getString("FREAMT"));// 需冻结金额 depsAmt FREAMT
									//						fxd091.setFrzCase("");// 冻结原因
									//						fxd091.setFreezeEnsureFileType("");// 冻结证明文书类别
									//						fxd091.setFreezeNotifyNo("");// 冻结通知书编号
									fxd091.setYnFlag2("1");// 是否标志
									//						fxd091.setBackupAmt("");// 备用金额
									fxd091Array[iu] = fxd091 ;
								}else{
									logger.info("------------>出错： 押品出库未查到相关冻结信息!");
									throw new EOSException("押品出库未查到相关冻结信息!");
								}
							}
							oxd091ReqBody.setFxd091(fxd091Array);
						}else{
							oxd091ReqBody.setRecNum(new BigInteger("0"));//循环记录数
						}
					}else{
						oxd091ReqBody.setRecNum(new BigInteger("0"));//循环记录数
					}
					logger.info("------------>调用核心押品接口XD09---押品编号="+dataObject.getString("SURETY_NO")+",权证编号="+dataObject.getString("REGISTER_CERTI_NO")+",ECIF客户号="+dataObject.getString("ECIF_PARTY_NUM")+",实际价值="+dataObject.getString("MORTGAGE_VALUE"));
					oxd092Res = impl.executeXD09(oxd091ReqBody);
					logger.info("------------>调用核心押品接口XD09---oxd091ReqBody="+oxd091ReqBody+"---->结束!");
					
					if(!"AAAAAAA".equals(oxd092Res.getResTranHeader().getHRetCode())){
						throw new EOSException("提示信息："+oxd092Res.getResTranHeader().getHRetMsg());
					}
					
					Map ypxxMap = new HashMap();
					String sdate = dataObject.getString("UPDATE_TIME");
					ypxxMap.put("ope_flag", "2");//出库
					ypxxMap.put("in_out_flag", dataObject.getString("OUT_TYPE"));//出库类型 TB_GRT_OUT
					ypxxMap.put("in_out_result", dataObject.getString("OUT_REASON"));//出库原因 TB_GRT_OUT_DETAIL
					ypxxMap.put("clt_no", dataObject.getString("SURETY_NO"));//押品编号 tb_grt_card_mortagage  tb_grt_mortgage_basic
					ypxxMap.put("guarantyright_nm", dataObject.getString("CARD_TYPE"));//权证名称 TB_GRT_REG_CARD
					ypxxMap.put("signeename",dataObject.getString("PARTY_NAME"));//押品所有权人名称 tb_csm_party
					ypxxMap.put("guarantyright_id", dataObject.getString("REGISTER_CERTI_NO"));//登记权证编号 TB_GRT_REG_CARD 
					ypxxMap.put("warrant_management_org_name",dataObject.getString("REG_ORG_NAME"));//登记机构名称TB_GRT_REG_CARD 
					ypxxMap.put("warrant_amt", dataObject.getString("REG_ORG_MONEY"));//登记金额 TB_GRT_REG_CARD
					ypxxMap.put("warrant_info_force_dt", dataObject.getString("CARD_REG_DATE"));//登记生效日期 TB_GRT_REG_CARD
					ypxxMap.put("warrant_efficacy_dt", dataObject.getString("REG_DUE_DATE"));//登记到期日期TB_GRT_REG_CARD
					ypxxMap.put("warrant_management_org_no", dataObject.getString("SAVE_ORG"));//保管机构TB_GRT_REG_CARD 
					ypxxMap.put("roll_out_dt", sdate);//出库时间 TB_GRT_OUT
					ypxxMap.put("storage_id", dataObject.getString("OUT_ID"));//出入库申请流水号 TB_GRT_OUT
					ypxxMap.put("storage_issue_nm", dataObject.getString("EMPNAME"));//发起人名称 om_employee
				//	ypxxMap.put("storage_issue_org", dataObject.getString("ORG_NUM"));//发起机构 TB_GRT_OUT
					Map map111=new HashMap();
					map111.put("orgCode", dataObject.getString("ORG_NUM"));
					Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.pay.getLoanNoticeInfo.getOrg", map111);
					if(orgS.length>0){
						DataObject con4 = (DataObject) orgS[0];
						if(con4.getString("ORGID")!=null && con4.getString("ORGID")!=""){
							ypxxMap.put("storage_issue_org", con4.getString("ORGID"));//发起机构 tb_grt_in
						}
					}
				
					ypxxMap.put("tally_dt", sdate);//记账日期 TB_GRT_OUT
					ypxxMap.put("storage_issue_id", dataObject.getString("USER_NUM"));//发起人ID  TB_GRT_OUT
					ypxxMap.put("trans_code","1105");//出入库同步接口交易码
					// Convert object to JSON string  
					String ypxxJsonStr = null;
					ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
					System.out.println("押品出入库接口入参："+ypxxJsonStr);
					ser.setIn0(ypxxJsonStr);
					String flag = stub.collServiceCommInter(ser).getOut1();
					logger.info("------------>调用押品系统押品同步接口结束------flag="+flag+"------>结束!");
				}
			}
		logger.info("------3231------>押品出库流程结束，开始更新业务状态------outId="+outId+"------>结束!");
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
		//提交流程  业务状态更新为02
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				
	logger.info("------3231------>押品出库流程拒绝，开始更新业务状态------outId="+outId+"------->开始!");
				DataObject out = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtOut");
				out.set("outId", outId);
				out.set("approveState", "06");
				DatabaseUtil.updateEntity("default", out);
	logger.info("------3231------>押品出库流程拒绝，开始更新业务状态------outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>押品出库流程拒绝失败!");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>押品出库流程拒绝失败!");
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

}
