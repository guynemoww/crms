package com.bos.grt.grtSynInterface;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.bos.pub.GitUtil;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.database.DatabaseExt;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;

import commonj.sdo.DataObject;

@Remotable
public class GrtInfoSynServiceImpl implements GrtInfoSynInterface {

	protected Logger LOG = TraceLoggerFactory.getContributionTraceLogger("com.bob.bcms.comm", "log4j-contribution.xml");

	/*
	 * $log$
	 */
	public String collInfoSyn(CollInfoInput coll) {
		Map<String,Object> map = new HashMap<String,Object>();
		//随机生成押品关联ID,押品表主键，暂用时间来记录，未来需按照特定规则修改
		Date date = new Date();
		String suretyId = String.valueOf(date.getTime());
		String cltFlag = "";
		String suc = "";
		ITransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();
		try{
			//封装map,封装前需要做必输项校验
			if(coll != null){
				if(coll.getOpe_flag() != null){
					map.put("ope_flag",coll.getOpe_flag());
				}
				/**
				 * 需增加所有接口字段必输项校验，公共方法，通过交易码，类去区别接口
				 */
				if(coll.getClt_flag() != null){
					cltFlag = coll.getClt_flag();
//					if("1".equals(cltFlag)){
//						cltFlag = "01";
//					}else if("2".equals(cltFlag)){
//						cltFlag = "02";
//					}
					map.put("clt_flag",cltFlag);
				}
				if(coll.getSigneename() != null){
					map.put("signeename",coll.getSigneename());//注意修改namingsqlx
	
				}
				if(coll.getClt_no() != null){
					map.put("clt_no",coll.getClt_no());
					map.put("suretyNo", coll.getClt_no());
				}
				if(coll.getClt_tp_cd() != null){
					map.put("clt_tp_cd",coll.getClt_tp_cd());//测试时看是不是翻译后数据
				}
				if(coll.getClt_nm() != null){
					map.put("clt_nm",coll.getClt_nm());
				}
				if(coll.getClt_rmk() != null){
					map.put("clt_rmk",coll.getClt_rmk());
				}
				map.put("initial_amt",coll.getInitial_amt());
				if(coll.getSys_eval_ccy_cd() != null){
					map.put("sys_eval_ccy_cd",coll.getSys_eval_ccy_cd());
				}
				map.put("sys_eval_amt",coll.getSys_eval_amt());
				if(coll.getSys_eval_dt() != null){
					map.put("sys_eval_dt",coll.getSys_eval_dt());
				}
				if(coll.getWarrant_no() != null){
					map.put("warrant_no",coll.getWarrant_no());
				}
				if(coll.getExtl_eval_org_name() != null){
					map.put("extl_eval_org_name",coll.getExtl_eval_org_name());
				}
				if(coll.getExtl_eval_ccy_cd() != null){
					map.put("extl_eval_ccy_cd",coll.getExtl_eval_ccy_cd());
				}
				map.put("extl_eval_amt",coll.getExtl_eval_amt());
				if(coll.getExtl_eval_dt() != null){
					map.put("extl_eval_dt",coll.getExtl_eval_dt());
				}
				
				//如果存在主键ID。则为从前，否则用新增
				Object[] data = DatabaseExt.queryByNamedSql("default", 
						"com.bos.grt.grt.basicQuery", map);
				if(data != null && data.length>0){
					DataObject dataObject = (DataObject) data[0];
					map.put("suretyId",dataObject.getString("SURETY_ID"));
				}else{
					map.put("suretyId", suretyId);//主键ID
				}
				
				if(coll.getOrg_num() != null){
					Map<String,Object> orgmap = new HashMap<String,Object>();
					orgmap.put("orgId", Integer.parseInt(coll.getOrg_num()));
					System.out.println("押品概要信息同步机构ID："+Integer.parseInt(coll.getOrg_num()));
					Object[] org = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
							"com.bos.aft.queryOrgInfo.queryOrgInfo", orgmap);
					if(org.length>0){
							DataObject orgdata = (DataObject) org[0];
							map.put("orgNum", orgdata.getString("orgCode") );//经办机构。,由于押品系统保存的是orgId 。信贷系统保存的是orgCode,需转
							System.out.println("押品概要信息同步机构CODE："+orgdata.getString("orgCode"));
					}else{
						LOG.info("请检查传入参数！");
						suc = "0001";
						return suc;
					}
				}
				if(coll.getUser_num() != null){
					map.put("userNum",coll.getUser_num());// 经办人。暂为空，以后需赋值！！！！！！！
				}
				if(coll.getAsesType_cd() != null){
					map.put("asesType_cd", coll.getAsesType_cd());//评估方式：需要转
				}
				if(coll.getApply_id() != null){
					map.put("apply_id", coll.getApply_id());
				}
				if(coll.getMortgaga_amt() != null){
					map.put("mortgagaAmt", coll.getMortgaga_amt());//权利价值
				}
				
				map.put("approvelId", "");//批复ID
				map.put("mortgage_rate", 0);//抵质押率...暂为0，担保合同后。权利价值/担保金额
			//	map.put("mortgage_value", 0);//权利价值---传值？
				map.put("surety_amt", 0);//担保金额
				map.put("mortgage_status", "03");//抵质押物状态(:XD_YWDB0139).新录入押品，状态默认“正常”
				Map<String,Object> mmap = new HashMap<String,Object>();
				mmap.put("partyNum", coll.getParty_id());//传过来的是客户号，需要转成客户ID
				Object[] outs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
						"com.bos.account.csm.getCsmParty", mmap);
				if(outs.length>0){
					DataObject datat = (DataObject) outs[0];
					map.put("party_id", datat.getString("PARTY_ID"));
				}
			}else{
				LOG.info("请检查传入参数！");
				suc = "0001";
				return suc;
			}
			transactionManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
			LOG.info("押品概要信息同步开始，入参："+map);
			System.out.println("押品概要信息同步开始，入参："+map);
			if("1".equals(coll.getOpe_flag())){//删除操作
//				//先查询是否引入。如果该押品引入则返回标志“0111”，此时押品系统删除时逻辑删除
//				Map<String,Object> mmap = new HashMap<String,Object>();
//				mmap.put("suretyNo", coll.getClt_no());
//				Object[] gaga = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
//						"com.bos.grt.grt.selectGrtgaga", mmap);
//				if(gaga.length>0){
//					for(int i = 0;i<gaga.length;i++){
//						DataObject data = (DataObject) gaga[i];
//						String suretyNo=data.getString("SURETY_NO");
//						if(suretyNo.equals(coll.getClt_no())){
//							return "0111";
//						}
//					}
//				}
				if("0".equals(coll.getScene_id())){//贷后重估只对最新评估价值进行更新操作，不存在删除？
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.delGrtRel", map);
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.delMybankAssess", map);
					if(!"3".equals(coll.getAsesType_cd()) && (!"".equals(coll.getAsesType_cd()) && coll.getAsesType_cd()!=null )){//内部评估只更新内部价值表。，如果不是内部评估，则同时更新内部评估与外部评估
						DatabaseExt.executeNamedSql("default", 
								"com.bos.grt.grtSynInterface.grtInfoSyn.delOuterAssess", map);
					}
					//20171117 删除押品与权证关联关系  要先删关联关系  再删押品  *****顺序不要去动*****
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.delGrtCardMortgage", map);
					
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.delMortgageBasic", map);
				}
				suc = "0000";
				transactionManager.commit();

			}else if("0".equals(coll.getOpe_flag())){//新增/修改操作
				if("0".equals(coll.getScene_id())){
					//判断是否引入。使用押品种类判断。引入时。押品系统不传押品种类到信贷
					if(map.containsKey("clt_tp_cd")){
						DatabaseExt.executeNamedSql("default", 
								"com.bos.grt.grtSynInterface.grtInfoSyn.mergeMortgageBasic", map);
					}else{
						DatabaseExt.executeNamedSql("default", 
								"com.bos.grt.grtSynInterface.grtInfoSyn.updateCollType", map);
					}
					/* modi by shangmf:20171020
					 * 去掉，此问题会导致批复调整时提交押品信息，会产生重复记录，改为先查询，如果不存在就插入
					 * 且押品提交两次会导致原业务申请和押品的关联关系丢失，再次批复调整会有问题
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.mergeGrtRel", map);
					*/
					//查询：queryGrtRel
					//插入：insertGrtRel
					Object[] grtRelObj = DatabaseExt.queryByNamedSql("default",
							"com.bos.grt.grtSynInterface.grtInfoSyn.queryGrtRel", map);
					if( grtRelObj.length <= 0 ){
						DatabaseExt.executeNamedSql("default", 
								"com.bos.grt.grtSynInterface.grtInfoSyn.insertGrtRel", map);
					}
					
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.mergeMybankAssess", map);
					if(!"3".equals(coll.getAsesType_cd())){//内部评估只更新内部价值表。，如果不是内部评估，则同时更新内部评估与外部评估
						DatabaseExt.executeNamedSql("default", 
								"com.bos.grt.grtSynInterface.grtInfoSyn.mergeOuterAssess", map);
					}
				}else{
					//仅更新主表中最新价值    ，贷时评估价值---评估价值。。---贷后重估时，最新确认价值。覆盖评估价值。。价值审核前。贷前押品系统中无贷时评估价值此字段,价值审核后通过同步接口同步数据
					//将初始认定价值更新到数据库中
					//20171007，增加最新权利价值字段
					DatabaseExt.executeNamedSql("default", 
							"com.bos.grt.grtSynInterface.grtInfoSyn.updateMortgagaValue", map);
					
				}
				suc = "0000";
				transactionManager.commit();
			}else{
				LOG.info("操作标志字段输入错误，请输入0-新增/修改，1-删除");
				suc = "0001";
				if (transactionManager != null) {
					transactionManager.rollback();
				}
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
			suc = "0001";
			if (transactionManager != null) {
				transactionManager.rollback();
			}
		}
		return suc;
	}

}
