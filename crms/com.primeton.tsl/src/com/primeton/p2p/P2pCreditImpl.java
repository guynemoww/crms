package com.primeton.p2p;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.engine.ListenerManager;
import org.apache.commons.beanutils.BeanUtils;

import com.bos.gjService.G001Response;
import com.bos.gjService.ResTranHeader;
import com.bos.gjService.ResponseHeader;
import com.bos.gjService.WY001Response;
import com.bos.gjService.WY001ResponseBody;
import com.bos.gjService.WdBaseService;
import com.bos.gjService.ZX001Request;
import com.bos.gjService.ZX001RequestBody;
import com.bos.gjService.ZX001Response;
import com.bos.gjService.ZX001ResponseBody;
import com.bos.inter.IntimeNoteClient;
import com.bos.irm.irmApply.IrmSecondloanLimit;
import com.bos.pub.DateStyle;
import com.bos.pub.DateUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.bos.utp.tools.DBUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.client.DateTools;
import com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.S0110102000B011Response;
import com.primeton.tsl.ecif.port.CommReqTranHeader;
import com.primeton.tsl.ecif.port.CommRequestHeader;

import commonj.sdo.DataObject;
@Bizlet("网贷")
public class P2pCreditImpl implements Ip2pCredit {
	private String getUrl(){
		ListenerManager.defaultConfigurationContext = null;
		String module = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_GROUP;
		String ip = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_IP;
		String port = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_PORT;
		//String wservice = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_SERVICE;
		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		//String zservice = ConfigurationUtil.getUserConfigSingleValue(module, group, wservice);
		String url = "http://" + zip + ":" + zport;
		return url;
	}
	private void initESBRequestHeader(CommRequestHeader requestHeader,
			CommReqTranHeader reqTranHeader, String serviceCode, String orgNum,
			String userNum) throws EOSException {
		requestHeader.setVersionNo("ESB0001-0001"); // 版本号
		requestHeader.setReqSysCode("01601"); // 请求方系统代码
		requestHeader.setReqSecCode(""); // 安全节点号
		requestHeader.setTxType("RQ"); // RQ
		requestHeader.setTxMode("0"); // 0-正常 1-冲销2-冲正 3-重发
		requestHeader.setTxCode(serviceCode); // soapenv服务码
		String busidate=GitUtil.getBusiDateYYYYMMDD();
		requestHeader.setReqDate(busidate); // 业务日期
		String time =new SimpleDateFormat("yyyyMMddhhmmss").format(GitUtil.getBusiDate());
		requestHeader.setReqTime(time); // 机器时间戳
		System.out.println("==============机器时间戳:===="+time);
		String reqSeqNo =DateTools.getReqSeqNo();
		System.out.println( "===============请求方交易流水号================="+reqSeqNo);
		requestHeader.setReqSeqNo(reqSeqNo); // 请求方交易流水号
		requestHeader.setChanlNo("13"); // 渠道号（字符）
/*		requestHeader.setBrch("0313"); // 机构编号
*///		requestHeader.setBrch(orgNum); // 机构编号
//		if (StringUtils.isEmpty(orgNum)) {
//			requestHeader.setBrch(GitUtil.getCurrentOrgCd()); // 机构编号
//		}
		requestHeader.setTermNo(""); // 终端号
//		requestHeader.setOper(userNum); // 柜员
		if(orgNum==null||"".equals(orgNum)){
			orgNum=GitUtil.getOrgCode();
		}
		String accOrgCode=GitUtil.getAccOrg(orgNum);
		requestHeader.setBrch(accOrgCode); // 机构编号
		requestHeader.setOper(GitUtil.getNumOrg(accOrgCode)); // 柜员
//		if (StringUtils.isEmpty(userNum)) {
//			requestHeader.setOper(GitUtil.getCurrentPositionCode()); // 柜员
//		}
		requestHeader.setSendFileName(""); // 发送文件名
		requestHeader.setBeginRec(""); // 开始记录数
		requestHeader.setMaxRec(30); // 一次查询最大记录数
		requestHeader.setFileHMac(""); // 文件摘要
		requestHeader.setHMac(""); // 报文摘要

		reqTranHeader.setHPinSeed("");// PIN种子
		reqTranHeader.setHOriChnl("");// 源渠道
		reqTranHeader.setHSecFlag("0");// 安全标志
		reqTranHeader.setHPwdFlag("0");// 加密标志
		reqTranHeader.setHCombFlag("0");// 组合标志
		reqTranHeader.setHSvcInfo("zuhejy_01");// 服务信息
		reqTranHeader.setHSecInfoVerNo("");// 安全信息版本号
		reqTranHeader.setHSysChnl("");// 渠道号
		reqTranHeader.setHLegaObj("9999");// 责任承担者
		reqTranHeader.setHMsgRefNo("");// 消息参考号
		reqTranHeader.setHTermNo("");// 终端号
		reqTranHeader.setHCityCd("");// 城市代码
//		reqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());// 发送方机构ID
//		reqTranHeader.setHUserID(GitUtil.getCurrentUserId());// 服务请求者
		reqTranHeader.setHBrchNo(accOrgCode);// 发送方机构ID
		reqTranHeader.setHUserID(GitUtil.getNumOrg(accOrgCode));// 服务请求者
		reqTranHeader.setHTxnCd("");// 交易代码
		reqTranHeader.setHTxnMod("");// 交易模式
		reqTranHeader.setHReserveLen("");// 保留数量字段
		reqTranHeader.setHSenderSvcCd("");// 发起端服务码
		reqTranHeader.setHSenderSeq(reqSeqNo);// 发起端流水
		reqTranHeader.setHSenderDate(busidate);// 发起端日期
		reqTranHeader.setHAuthUserID("");// 授权服务请求者
		reqTranHeader.setHAuthVerfInfo("");// 授权验证信息
		reqTranHeader.setHAuthFlag("");// 授权标志
		reqTranHeader.setHRefSeq("");// 关联流水
		reqTranHeader.setHAuthSeri("");// 授权序号
		reqTranHeader.setHHostSeq("");// 核心流水号
		reqTranHeader.setHRefDt("");// 关联日期
		reqTranHeader.setHSvcVer("");// 服务版本号
		reqTranHeader.setHreserveMsg("");// 保留信息字段
	}
	/**
	 * 网贷查询征信查询
	 * @return
	 */
	public void p2pCreditReport(String applyId ,String ecifPartyNum,String partyId) throws Exception{
//		try{
			String orgNum="";//管护机构
			Map map =new HashMap();
			String code="";//征信接口返回的状态码
			String msg="";//征信接口返回的状态值
			Date sucessTime=new Date();//征信成功时间
			Date dateTime=GitUtil.getBusiDate();//当前时间
			int sucessTimeLenth=0;//征信成功距离现在几个月
			int n=3;//征信成功距离现在几个月内不用重复查询
			String CRMS_DS_NAME = "default";
			long errorNum=0;//失败次数
			String applyStatus="02";//申请状态  02成功 03失败
			String errorMsg="";//错误信息
			DataObject middle = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApplyMiddle");//业务中间表
			middle.set("ecifPartyNum", ecifPartyNum);
			middle.set("applyId", applyId);
			DataObject	middleUpdate = 	EntityUtil.searchEntity("com.bos.dataset.biz.TbBizApplyMiddle",  "applyId", applyId);
			Object[] objiects=DatabaseExt.queryByNamedSql(CRMS_DS_NAME, "com.primeton.tsl.transferData.queryMiddle", middle);//该客户下最新的征信信息
			int flag=beforep2pRule(partyId,map, middleUpdate, objiects, errorNum, applyStatus, errorMsg, sucessTime, dateTime, sucessTimeLenth, n);//调征信前判断规则 根据结果发短信
			if(2==flag||3==flag){
				return ;//不满足不用向下执行
				//0 不在工作日内
			    //1 在工作日20:00-8:00期间内办理
				//2 调征信失败次数大于10次
				//3 距离调征信小于"+n+"个月"
				//4 没有征信记录
			}
			//====接口输入参数==================================
			//p2pCreditReportEsq();
			map=p2pEsb(applyId,map, partyId, orgNum, middleUpdate, code, errorNum, dateTime);//调取征信接口
			
//		}catch (Exception e) {
//			throw new Exception("调用征信接口失败：" + e.getMessage());
//		}
	}
	
	/**
	 * 网贷查询征信查询 定时器
	 * @return
	 */
	@Bizlet("网贷")
	public void p2pCreditReportEsq() throws Exception{
		String applyStatuss="02";
		DataObject[] dataObjects=EntityUtil.searchEntitys("com.bos.dataset.biz.TbBizApplyMiddle",  "applyStatus", applyStatuss);
		if(dataObjects!=null&&!"".equals(dataObjects)){ 
			for (DataObject middleUpdate : dataObjects) {
				String partyId=middleUpdate.getString("partyId");
				String orgNum="";//管护机构
				String applyId=middleUpdate.getString("applyId");//
				Object[] objiects=DatabaseExt.queryByNamedSql("default", "com.primeton.tsl.transferData.queryMiddle", middleUpdate);
				Map map =new HashMap();
				String code="";//征信接口返回的状态码
				String msg="";//征信接口返回的状态值
				Date sucessTime=new Date();//征信成功时间
				Date dateTime=GitUtil.getBusiDate();//当前时间
				int sucessTimeLenth=0;//征信成功距离现在几个月
				int n=3;//征信成功距离现在几个月内不用重复查询
				String CRMS_DS_NAME = "default";
				int errorNum=0;//失败次数
				String applyStatus="02";//申请状态  02成功 03失败
				String errorMsg="";//错误信息
				int flag=beforep2pRule(partyId,map, middleUpdate, objiects, errorNum, applyStatus, errorMsg, sucessTime, dateTime, sucessTimeLenth, n);//调征信前判断规则 根据结果发短信
				if(2==flag||3==flag){
					return ;//不满足不用向下执行
					//0 不在工作日内
				    //1 在工作日20:00-8:00期间内办理
					//2 调征信失败次数大于10次
					//3 距离调征信小于"+n+"个月"
					//4 没有征信记录
				}
				map=p2pEsb(applyId,map, partyId, orgNum, middleUpdate, code, errorNum, dateTime);//调取征信接口
				//====接口输入参数==================================
				
			}
	
		}

	}
	/**
	 * 调征信接口之前向中间表插入数据
	 * @param object
	 * @return
	 */
	public DataObject saveApplyMiddle(DataObject object) {
		String CRMS_DS_NAME = "crms";
		DataObject middle = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApplyMiddle");//业务中间表
		Object[] objiects=DatabaseExt.queryByNamedSql(CRMS_DS_NAME, "com.primeton.tsl.transferData.queryMiddle", middle);//该客户下最新的征信信息
		middle.set("ecifPartyNum", object.get("ecifPartyNum"));
		if(objiects.length > 0){
			DataObject amiddle=(DataObject) objiects[0];
			object.setInt("errorNum", amiddle.getInt("errorNum"));
			object.setString("applyStatus", amiddle.getString("applyStatus"));
			object.setDate("sucessTime", amiddle.getDate("sucessTime"));
		}else{
		int errorNum=0;//失败次数
		String applyStatus="1"; //申请状态  1受理；2查询征信成功；3查询征信失败；4拒接受理；5合同生效；6合同失效；7合同撤销；
		Date creatTime=GitUtil.getBusiDate();//创建时间
		Date updateTime=GitUtil.getBusiDate();//更新时间
		String errorMsg="";//错误信息
//		Date sucessTime=GitUtil.getBusiDate();//征信成功时间
//		object.set("errorNum", errorNum);
		object.set("applyStatus", applyStatus);
		object.set("creatTime", creatTime);
		}
//		object.set("errorMsg", errorMsg);
//		object.set("updateTime", updateTime);
	    DatabaseUtil.insertEntity("default", object);//开始就向中间表插入数据
		return object;
		
	}
	/**
	 * 根据征信报考的参数按照一定的规则判断是否拒贷
	 * @param object
	 * @return
	 */
	public Map<String,String> p2pRule(ZX001RequestBody object,String income) {
		Map<String,String> map =new HashMap();
	    String flag="1";//0 拒贷 1 正常
	    String mesg="拒绝受理";//拒贷原因
   	 //拒贷规则
	   String CurOverNodk= object.getCurOverNodk();//贷款当前逾期期数
	   String CurreOveAmountdk= object.getCurreOveAmountdk();//贷款当前逾期金额 
	   String HigtNoOfOvePerdsdk= object.getHigtNoOfOvePerdsdk();//贷款历史最高连续逾期期数
	   String CumuOverNoOfHstrydk= object.getCumuOverNoOfHstrydk();//贷款历史累计逾期次数
	   String CurOverNodjk= object.getCurOverNodjk();//贷记卡当前逾期期数
	   String CurreOveAmountdjk= object.getCurreOveAmountdjk();//贷记卡当前逾期金额 
	   String HigtNoOfOvePerdsdjk = object.getHigtNoOfOvePerdsdjk ();//贷记卡历史最高连续逾期期数
	   String CumuOverNoOfHstrydjk= object.getCumuOverNoOfHstrydjk();//贷记卡历史累计逾期次数
	   String CurOverNozdjk= object.getCurOverNozdjk();//准贷记卡当前透支月数
	   String CurreOveAmountzdjk= object.getCurreOveAmountzdjk();//准贷记卡当前透支金额 
	   String HigtNoOfOvePerdszdjk= object.getCurreOveAmountdk();//准贷记卡历史最长透支月数
	   String CumuOverNoOfHstryzdjk= object.getCumuOverNoOfHstryzdjk();//准贷记卡历史透支月份数
	   String OrgNumdk= object.getOrgNumdk();//最近一月内贷款审批查询机构数 
	   String OrgNumxyk= object.getOrgNumxyk();//最近一月内信用卡审批查询机构数
	   String QueNumdk= object.getQueNumdk();//最近一月内贷款审批查询次数
	   String QueNumxyk= object.getQueNumxyk();//最近一月内信用卡审批查询次数
	   String FiveLevClass= object.getFiveLevClass();//五级分类 
	   String dkspcs3= object.getDkspcs3();//最近3个月查询原因为贷款审批的次数
	   String xykspcs3= object.getXykspcs3();//最近3个月查询原因为信用卡审批的次数
	   String jydks= object.getJydks();//经营性贷款的笔数
	   String pjyhk6= object.getPjyhk6();//最近6个月平均应还款金额 
	   String ifdwdbye= object.getIfdwdbye();//是否有对外担保余额
   	if(jydks!=null&&!"".equals(jydks)&&Double.parseDouble(jydks.trim())>0){//有经营贷款字段>0 拒贷
   		flag="0";
   		mesg=mesg+" 有经营贷款";
   	}
   	if(ifdwdbye!=null&&!"".equals(ifdwdbye)&&Double.parseDouble(ifdwdbye.trim())>0){//有对外担保余额>0 拒贷
   		flag="0";
   		mesg=mesg+"  有对外担保余额";
   	}
   	if(!"正常".equals(FiveLevClass)){//五级分类正常以外 拒贷
   		flag="0";
   		mesg=mesg+"  连续逾期期数>0";
   	}
   	if((CurOverNodk!=null&&!"".equals(CurOverNodk)&&Double.parseDouble(CurOverNodk.trim())>0)
   	 ||(CurOverNodjk!=null&&!"".equals(CurOverNodjk)&&Double.parseDouble(CurOverNodjk.trim())>0)
   	 ||(CurOverNozdjk!=null&&!"".equals(CurOverNozdjk)&&Double.parseDouble(CurOverNozdjk.trim())>0)){//前逾期期数，当前欠息>0 拒贷
   		flag="0";
   		mesg=mesg+"  有前逾期期数，当前欠息";
   	}
   	if((CumuOverNoOfHstrydk!=null&&!"".equals(CumuOverNoOfHstrydk)&&Double.parseDouble(CumuOverNoOfHstrydk.trim())>=3)
   	   	 ||(CumuOverNoOfHstrydjk!=null&&!"".equals(CumuOverNoOfHstrydjk)&&Double.parseDouble(CumuOverNoOfHstrydjk.trim())>=3)
   	   	 ||(CumuOverNoOfHstryzdjk!=null&&!"".equals(CumuOverNoOfHstryzdjk)&&Double.parseDouble(CumuOverNoOfHstryzdjk.trim())>=3)){//授信业务累计逾期+欠息》=3 拒贷
   	   		flag="0";
   	   		mesg=mesg+"  有授信业务累计逾期，授信业务累计逾期欠息>=3";
   	   	}
   	if((HigtNoOfOvePerdsdk!=null&&!"".equals(HigtNoOfOvePerdsdk)&&Double.parseDouble(HigtNoOfOvePerdsdk.trim())>=2)
      	   	 ||(HigtNoOfOvePerdsdjk!=null&&!"".equals(HigtNoOfOvePerdsdjk)&&Double.parseDouble(HigtNoOfOvePerdsdjk.trim())>=2)
      	   	 ||(HigtNoOfOvePerdszdjk!=null&&!"".equals(HigtNoOfOvePerdszdjk)&&Double.parseDouble(HigtNoOfOvePerdszdjk.trim())>=2)){//授信业务连续逾期字段出现“2”的次数》=1 拒贷
      	   		flag="0";
      	   		mesg=mesg+"  连续逾期>=2";
      	   	}
   	if(dkspcs3!=null&&!"".equals(dkspcs3)&&Double.parseDouble(dkspcs3.trim())>=3){//贷款审批>=3拒贷
   		flag="0";
   		mesg=mesg+"  贷款审批>=3";
   	}
   	if(xykspcs3!=null&&!"".equals(xykspcs3)&&Double.parseDouble(xykspcs3.trim())>=3){//信用卡审批>=3拒贷
   		flag="0";
   		mesg=mesg+"  信用卡审批>=3";
   	}
   	Double dincome=(double) 0;
   	if(income!=null&&!"".equals(income)){
   		dincome=Double.parseDouble(income.trim());
   	}
   	if(xykspcs3!=null&&!"".equals(xykspcs3)&&Double.parseDouble(xykspcs3.trim())>=dincome/2){//月平均还款额》系统取月收入/2拒贷
   		flag="0";
   		mesg=mesg+"  月平均还款额》系统取月收入/2";
   	}
   	   map.put("flag", flag);
   	   map.put("mesg", mesg);
		return map;
		
	}
	/**
	 * 调征信前判断规则 根据结果发短信
	 * @param object
	 * @return
	 */
	public 	int beforep2pRule(String partyId,Map map,DataObject middleUpdate,Object[] objiects,long errorNum,String applyStatus,String errorMsg,Date sucessTime,Date dateTime,int sucessTimeLenth,int n) throws Exception{
		//公共的判断规则
		String orgNum="";
		DataObject team = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
		team=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam","partyId",partyId);
	if(team!=null&&!"".equals(team)){
	 orgNum=team.getString("orgNum");}else{
		 orgNum= GitUtil.getOrgCode();
	 }
		String pho=middleUpdate.getString("phoneNum");
		String msg="";//测试玩的
		String messge="0701|*|crms_note_001|*|"+orgNum+"|*|"+pho+"|*|";
		IntimeNoteClient  clent=new IntimeNoteClient();
		messge=messge+msg;
//		clent.intimeNoteSocketClint(messge);
		int flag=0;
		DataObject workdayManage = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubWorkdayManage");//业务中间表
		Boolean workDay=false;//是否工作日
		Boolean workDaytime=false;//是否在时间20:00-8:00内
		Calendar calend=Calendar.getInstance();
		calend.setTime(dateTime);//当前时间
		int workdayYear=calend.get(Calendar.YEAR);
		int workdayMonth=calend.get(Calendar.MONTH);
		int workdayDay=calend.get(Calendar.DAY_OF_MONTH);
		workdayManage.set("workdayYear", workdayYear);
		workdayManage.set("workdayMonth", workdayMonth);
		workdayManage.set("workdayDay", workdayDay);
		Object[] WorkdayManageob=DatabaseExt.queryByNamedSql("default", "com.primeton.tsl.transferData.queryworkdayManage", workdayManage);//下旬是否是规则日
		if(WorkdayManageob!=null&&WorkdayManageob.length>0){
			workDay=true;//当前时间在工作日
		}
		SimpleDateFormat df=new SimpleDateFormat("HH:mm");//设置日期格式
		Date now =df.parse(df.format(new Date()));
		Date begin =df.parse("08:00");
		Date end =df.parse("20:00");
		Calendar calendnow=Calendar.getInstance();
		calendnow.setTime(now);
		Calendar calendbegin=Calendar.getInstance();
		
		calendbegin.setTime(begin);
		Calendar calendend=Calendar.getInstance();
		calendend.setTime(end);
		if(calendnow.after(calendbegin)&&calendnow.before(calendend)){
			workDaytime=true;
		}
		if(workDay){//!workDay
		    map.put("msg", "0000001");//成功失败原因 表示失败
		    map.put("code", "不在工作日内，最迟三天给反馈");//成功失败码值
			middleUpdate.setString("applyStatus", "08");
			middleUpdate.setString("errorMsg", "不在工作日内，最迟三天给反馈");
		    DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
		    //调用发送短信
		    msg="不在工作日内，最迟三天给反馈";
			messge=messge+msg;
			clent.intimeNoteSocketClint(messge);
		    return 0;
		}
		if(!workDaytime){//!workDaytime
		    map.put("msg", "0000002");//成功失败原因 表示失败
		    map.put("code", "该业务在工作日20:00-8:00期间内办理");//成功失败码值
			middleUpdate.setString("applyStatus", "08");
			middleUpdate.setString("errorMsg", "该业务在工作日20:00-8:00期间内办理");
		    DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
		    //调用发送短信
		    msg="该业务在工作日20:00-8:00期间内办理，最迟三天给反馈";
			messge=messge+msg;
			clent.intimeNoteSocketClint(messge);
		    return 1;
		}
		if(objiects.length > 0){//有征信记录
			DataObject amiddle=(DataObject) objiects[0];
			errorNum=amiddle.getInt("errorNum");
			applyStatus=amiddle.getString("applyStatus");
			errorMsg=amiddle.getString("errorMsg");
		String	sucessTime1=amiddle.getString("sucessTime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(sucessTime1!=null&&!"".equals(sucessTime1.trim())){
			
			sucessTime=sdf.parse(sucessTime1.substring(0, 10));
		}
			middleUpdate.set("errorNum", errorNum);
			middleUpdate.setString("applyStatus", applyStatus);
			middleUpdate.setDate("sucessTime", sucessTime);
			middleUpdate.setDate("updateTime", dateTime);
			middleUpdate.setString("errorMsg", errorMsg);
			//计算征信成功到现在有几个月
			Calendar calendsucessTime=Calendar.getInstance();
			calendsucessTime.setTime(sucessTime);//征信成功时间
			Calendar calendcureent=Calendar.getInstance();
			calendcureent.setTime(dateTime);//当前时间
			int month=calendcureent.get(Calendar.MONTH)-calendsucessTime.get(Calendar.MONTH);//相差月数
			int year=calendcureent.get(Calendar.YEAR)-calendsucessTime.get(Calendar.YEAR);//相差年
			int day=calendcureent.get(Calendar.DAY_OF_MONTH)-calendsucessTime.get(Calendar.DAY_OF_MONTH);//该月相差的天数
			//该月相差的天数为正数在总的月数上加1
			sucessTimeLenth=month+year*12;
			if(day>0){
				sucessTimeLenth=sucessTimeLenth+1;
			}
			//====调征信前进行判断开始判断规则==================================
			
			if(errorNum>10){//调征信失败次数大于10次，判断为四百不用继续后续流程
			    map.put("msg", "0000003");//成功失败原因 表示失败
			    map.put("code", "调征信失败次数大于10次");//成功失败码值
				middleUpdate.setString("applyStatus", "02");
				middleUpdate.setString("errorMsg", "调征信失败次数大于10次");
			    DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
				return 2;
			}
			if("04".equals(applyStatus)&&(sucessTimeLenth<=n)){//距离调征信小于3个月
			    map.put("msg", "AAAAAAA");//成功失败原因 表示失败
			    map.put("code", "距离调征信小于"+n+"个月");//成功失败码值
				middleUpdate.setString("applyStatus", "04");
				middleUpdate.setString("errorMsg", "距离调征信小于"+n+"个月");
			    DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
			    basicApply(middleUpdate);//调用基础业务
				return 3;
			}
			//====调征信前进行判断结束判断规则==================================
		}
		
		return 4;
		
		
	}
	/**
	 * 调取ESB征信接口
	 * @param object
	 * @return
	 */
	public Map<String,String> p2pEsb(String applyId,Map map,String partyId,String orgNum,DataObject middleUpdate,String code,long errorNum,Date dateTime) throws Exception{
		DataObject party=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmParty","partyId",partyId);
		DataObject natural=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmNaturalPerson","partyId",partyId);
		String CustType="0";//客户类型
		IntimeNoteClient clent =new IntimeNoteClient();
		//String ywbh=applyId;//业务编号
		String CustName=party.getString("partyName");//客户名称
		String certType=natural.getString("certType");//证件类型  CDKH0002 转换
		String CertType="";
		if("10".equals(certType)){
			CertType="0";
		}else if("11".equals(certType)){
			CertType="1";
		}
		else if("121".equals(certType)){
			CertType="2";
		}
		else if("122".equals(certType)){
			CertType="2";
		}
		else if("13".equals(certType)){
			CertType="3";
		}
		else if("14".equals(certType)){
			CertType="4";
		}
		else if("15".equals(certType)){
			CertType="5";
		}
		else if("1h".equals(certType)){
			CertType="5";
		}
		else if("16".equals(certType)){
			CertType="6";
		}
		else if("17".equals(certType)){
			CertType="7";
		}
		else if("18".equals(certType)){
			CertType="8";
		}
		else if("19".equals(certType)){
			CertType="9";
		}
		else {
			CertType="X";
		}
		String CertNo=natural.getString("certNum");//证件号码
		String IdvCtfEffDate="1900-01-01";//个人证件生效日期
		String IdvCtfLsefcDate="9999-12-31";//个人证件失效日期
		String OnslfEnqr="A";//是否本人查询 A：是  B：否
		String zservice ="/WebService/CRMS_SVR/S03601010ZX0001";
		String url = getUrl() + zservice;//配置IP地址
		S03601010ZX0001ServiceStub.S03601010ZX0001 req = new S03601010ZX0001ServiceStub.S03601010ZX0001();
		S03601010ZX0001ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S03601010ZX0001ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S03601010ZX0001ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S03601010ZX0001ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S03601010ZX0001ServiceStub.FMT_CRMS_SVR_S03601010ZX0001_IN request=new S03601010ZX0001ServiceStub.FMT_CRMS_SVR_S03601010ZX0001_IN();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		DataObject team = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
		team=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam","partyId",partyId);
	if(team!=null&&!"".equals(team)){
	 orgNum=team.getString("orgNum");}else{
		 orgNum= GitUtil.getOrgCode();
	 }
	    request.setBsnNo(applyId);
	    request.setBrchNo(orgNum);
		request.setCustType(CustType);
		request.setCustName(CustName);
		request.setCertType(CertType);
		request.setCertNo(CertNo);
		request.setIdvCtfEffDate(IdvCtfEffDate);
		request.setIdvCtfLsefcDate(IdvCtfLsefcDate);
		request.setOnslfEnqr(OnslfEnqr);
		request.setBwflag("A");
		request.setQueryReason("01");
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S03601010ZX0001",orgNum, GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		S03601010ZX0001ServiceStub client = new S03601010ZX0001ServiceStub(url);
		req.setRequestHeader(requestHeader);
		req.setReqTranHeader(reqTranHeader);                               
		req.setRequestBody(request);
		S03601010ZX0001Response response= client.S03601010ZX0001(req);
	    map.put("msg", response.getResTranHeader().getHRetMsg());//成功失败原因
	    map.put("code", response.getResTranHeader().getHRetCode() +"    流水号"+commRequestHeader.getReqSeqNo());//成功失败码值
	    System.out.println("===================================="+map.get("code"));
	    P2pEntry object=new P2pEntry();
	   // map.put("boday", boday);//返回的征信内容.
	    if(response.getResTranHeader().getHRetCode().equals("AAAAAAA")){//调取征信成功
/*	    	Map<String,String> mapp =new HashMap();
	    	String curOverNodk=response.getResponseBody().getCurOverNodk();
	    	String higtNoOfOvePerdsdk=response.getResponseBody().getHigtNoOfOvePerdsdk();
	    	String cumuOverNoOfHstrydk=response.getResponseBody().getCumuOverNoOfHstrydk();
	    	int odk=0;
	    	int dsdk=0;
	    	int trydk=0;
	    	if(curOverNodk!=null&&!"".endsWith(curOverNodk.trim())){
	    		odk=Integer.parseInt(curOverNodk.trim());
	    	}
	    	if(higtNoOfOvePerdsdk!=null&&!"".endsWith(higtNoOfOvePerdsdk.trim())){
	    		dsdk=Integer.parseInt(higtNoOfOvePerdsdk.trim());
	    	}
	    	if(cumuOverNoOfHstrydk!=null&&!"".endsWith(cumuOverNoOfHstrydk.trim())){
	    		trydk=Integer.parseInt(cumuOverNoOfHstrydk.trim());
	    	}
	    	object.setDqyqje(odk);//当前逾期期数 CurOverNodk
	    	object.setZgyqqs(dsdk);//历史最高连续逾期期数
	    	object.setLsyqqs(trydk);//历史累计逾期次数
	    	mapp=p2pRule(object);//调取业务规则
			middleUpdate.set("errorNum", 0);
			middleUpdate.setString("applyStatus", "02");
			middleUpdate.setDate("sucessTime", dateTime);
			middleUpdate.setDate("updateTime", dateTime);
			middleUpdate.setString("errorMsg", mapp.get("mesg"));
			DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
			if(!"1".equals(mapp.get("mesg"))){//拒贷
				//推送短信
				return map;
			}else{
				//继续以后的业务
				return map;
			}*/
	    	middleUpdate.setString("applyStatus", "08");
	    	DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
	    	return map;
	    }else{//调取征信失败
			middleUpdate.set("errorNum", errorNum+1);
			middleUpdate.setString("applyStatus", "02");
		//	middleUpdate.setDate("sucessTime", sucessTime);
			middleUpdate.setDate("updateTime", dateTime);
			middleUpdate.setString("errorMsg", code +"    流水号"+commRequestHeader.getReqSeqNo());
			DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
	    	return map;
	    }
		
//	}catch (Exception e) {
//		throw new Exception("调用征信接口失败：" + e.getMessage());
//	}

}
	/**
	 * 征信调信贷
	 */
	@Bizlet("网贷")
	public ZX001Response crmsTop2pCreditReport(ZX001Request request)
			throws Exception {
		System.out.println("网银调用INTERFACE---ZX001。。。");
		ZX001Response rs = new ZX001Response();
		String flag="1";
		String applyId="";
		applyId=request.getRequestBody().getBusiNo();
		DataObject	middleUpdate = 	EntityUtil.searchEntity("com.bos.dataset.biz.TbBizApplyMiddle",  "applyId", applyId);
		if(middleUpdate==null||"".equals(middleUpdate)){
			rs=reCrmsToP2p(rs,request,"0");
		}else{
		DataObject	white = EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmWhiteCustomer","ecifPartyNum", middleUpdate.getString("ecifPartyNum"),"cusStatus","02");
    	String curOverNodk=request.getRequestBody().getCurOverNodk();
    	String higtNoOfOvePerdsdk=request.getRequestBody().getHigtNoOfOvePerdsdk();
    	String cumuOverNoOfHstrydk=request.getRequestBody().getCumuOverNoOfHstrydk();
    	ZX001RequestBody object=request.getRequestBody();
    	   Map<String,String> mapp =new HashMap();
    	mapp=p2pRule(object,white.getString("income"));//调取业务规则
    	long errorNum=0;
    	String orgNum="";
    	DataObject team = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
    	team=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam","partyId",middleUpdate.getString("partyId"));
    if(team!=null&&!"".equals(team)){
     orgNum=team.getString("orgNum");}else{
    	 orgNum= GitUtil.getOrgCode();
     }
    	String pho=middleUpdate.getString("phoneNum");
    	String msg="";//测试玩的
    	String messge="0701|*|crms_note_001|*|"+orgNum+"|*|"+pho+"|*|";
    	IntimeNoteClient  clent=new IntimeNoteClient();
		if(!"1".equals(mapp.get("flag"))){//拒贷
			//推送短信
	    	middleUpdate.set("errorNum", errorNum);
			middleUpdate.setString("applyStatus", "03");
			middleUpdate.setDate("sucessTime", GitUtil.getBusiDate());
			middleUpdate.setDate("updateTime", GitUtil.getBusiDate());
			middleUpdate.setString("errorMsg", mapp.get("mesg"));
			DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
			msg=mapp.get("mesg");
	    	messge=messge+msg;
	    	clent.intimeNoteSocketClint(messge);
	
		}else{
			//继续以后的业务
	    	middleUpdate.set("errorNum", errorNum);
			middleUpdate.setString("applyStatus", "04");
			middleUpdate.setDate("sucessTime", GitUtil.getBusiDate());
			middleUpdate.setDate("updateTime", GitUtil.getBusiDate());
			middleUpdate.setString("errorMsg", "征信查询成功");
			DatabaseUtil.updateEntity("default", middleUpdate);//更新中间表
			basicApply(middleUpdate);
		}
		//插入征信历史表
		DataObject creadReport = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCredtReportRecord");
		creadReport.set("curovernodk",object.getCurOverNodk());//贷款当前逾期期数
		creadReport.set("curreoveamountdk",object.getCurreOveAmountdk());//贷款当前逾期金额 
		creadReport.set("higtnoofoveperdsdk",object.getHigtNoOfOvePerdsdk());//贷款历史最高连续逾期期数
		creadReport.set("cumuovernoofhstrydk",object.getCumuOverNoOfHstrydk());//贷款历史累计逾期次数
		creadReport.set("curovernodjk",object.getCurOverNodjk());//贷记卡当前逾期期数
		creadReport.set("curreoveamountdjk",object.getCurreOveAmountdjk());//贷记卡当前逾期金额 
		creadReport.set("higtnoofoveperdsdjk",object.getHigtNoOfOvePerdsdjk());//贷记卡历史最高连续逾期期数
		creadReport.set("cumuovernoofhstrydjk",object.getCumuOverNoOfHstrydjk());//贷记卡历史累计逾期次数
		creadReport.set("curovernozdjk",object.getCurOverNozdjk());//准贷记卡当前透支月数
		creadReport.set("curreoveamountzdjk",object.getCurreOveAmountzdjk());//准贷记卡当前透支金额 
		creadReport.set("curreoveamountdk",object.getCurreOveAmountdk());//准贷记卡历史最长透支月数
		creadReport.set("cumuovernoofhstryzdjk",object.getCumuOverNoOfHstryzdjk());//准贷记卡历史透支月份数
		creadReport.set("orgnumdk",object.getOrgNumdk());//最近一月内贷款审批查询机构数 
		creadReport.set("orgnumxyk",object.getOrgNumxyk());//最近一月内信用卡审批查询机构数
		creadReport.set("quenumdk",object.getQueNumdk());//最近一月内贷款审批查询次数
		creadReport.set("quenumxyk",object.getQueNumxyk());//最近一月内信用卡审批查询次数
		creadReport.set("fivelevclass",object.getFiveLevClass());//五级分类 
		creadReport.set("dkspcs3",object.getDkspcs3());//最近3个月查询原因为贷款审批的次数
		creadReport.set("xykspcs3",object.getXykspcs3());//最近3个月查询原因为信用卡审批的次数
		creadReport.set("jydks",object.getJydks());//经营性贷款的笔数
		creadReport.set("pjyhk6",object.getPjyhk6());//最近6个月平均应还款金额 
		creadReport.set("ifdwdbye",object.getIfdwdbye());//是否有对外担保余额
		creadReport.set("busino",applyId);//业务编号
		creadReport.set("ecifPartyNum",middleUpdate.getString("ecifPartyNum"));//ECIF客户编号
		DatabaseUtil.insertEntity("default", creadReport);//更新中间表
		rs=reCrmsToP2p(rs,request,flag);
		}
		return rs;
	}
	public ZX001Response reCrmsToP2p(ZX001Response rs,ZX001Request request,String flag){
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
//		responseHeader.setReqTime(request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		ZX001ResponseBody responseBody = new ZX001ResponseBody();
		responseBody.setFlag(flag);
		rs.setResponseBody(responseBody);
		return rs;
	}
public void basicApply(DataObject middleUpdate) throws Exception {
	DataObject	white = EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmWhiteCustomer","ecifPartyNum", middleUpdate.getString("ecifPartyNum"),"cusStatus","02");
	DataObject	creadReport = EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmCredtReportRecord","busino", middleUpdate.getString("applyId"));
	String age ="";//年龄
	String schoolGree ="";//文化程度
	String marriageCd ="";//婚姻状况
	String workTime ="";//工作年限
	String jobRank ="";//职务
	String income ="";//月均收入
	String totallIncome ="";//家庭总资产
	String record ="1";//征信记录
	String realate ="";//与本行关系
	String pjyhk6 ="";//月还本付息额
	double cordp=0;
	double cordi=0;
	double cord=0;
	if(creadReport!=null&&!"".equals(creadReport)){
		pjyhk6=creadReport.getString(pjyhk6);
	if(pjyhk6!=null&&!"".equals(pjyhk6)){
		cordp=Double.parseDouble(pjyhk6.trim());
	}
	}
	if(white!=null&&!"".equals(white)){
		age=white.getString("age");
		schoolGree=white.getString("schoolGree");
		marriageCd=white.getString("marriageCd");
		workTime=white.getString("workTime");
		jobRank=white.getString("jobRank");
		income=white.getString("income");
		totallIncome=white.getString("totallIncome");
		realate=white.getString("realate");
		if(income!=null&&!"".equals(income)){
			cordi=Double.parseDouble(income.trim());
		}
	}
	cord=Math.round((cordp/cordi)*100);
	//--获取分值
	HashMap map = new HashMap();
	map.put("model_id", "1");
	List list = new ArrayList();
	HashMap mapindex11 = new HashMap();
	mapindex11.put("bigmold_index_id", "1001");//基本情况
	mapindex11.put("smallmold_index_id", "1001001");//年龄
	mapindex11.put("index_param", age);//参数
	list.add(mapindex11);
	HashMap mapindex12 = new HashMap();
	mapindex12.put("bigmold_index_id", "1001");//基本情况
	mapindex12.put("smallmold_index_id", "1001002");//文化程度
	mapindex12.put("index_param", schoolGree);//参数
	list.add(mapindex12);
	HashMap mapindex13 = new HashMap();
	mapindex13.put("bigmold_index_id", "1001");//基本情况
	mapindex13.put("smallmold_index_id", "1001003");//婚姻状况
	mapindex13.put("index_param", marriageCd);//
	list.add(mapindex13);
	
	HashMap mapindex21 = new HashMap();
	mapindex21.put("bigmold_index_id", "1002");//工作情况
	mapindex21.put("smallmold_index_id", "1002001");//工作年限
	mapindex21.put("index_param", workTime);//参数
	list.add(mapindex21);
	HashMap mapindex22 = new HashMap();
	mapindex22.put("bigmold_index_id", "1002");//工作情况
	mapindex22.put("smallmold_index_id", "1002002");//职务
	mapindex22.put("index_param", jobRank);//参数
	list.add(mapindex22);
	HashMap mapindex23 = new HashMap();
	mapindex23.put("bigmold_index_id", "1002");//工作情况
	mapindex23.put("smallmold_index_id", "1002003");//月均收入
	mapindex23.put("index_param", workTime);//参数
	list.add(mapindex23);
	HashMap mapindex31 = new HashMap();
	mapindex31.put("bigmold_index_id", "1003");//家庭收入情况
	mapindex31.put("smallmold_index_id", "1003001");//家庭总资产
	mapindex31.put("index_param", totallIncome);//
	list.add(mapindex31);
	HashMap mapindex32 = new HashMap();
	mapindex32.put("bigmold_index_id", "1003");//家庭收入情况
	mapindex32.put("smallmold_index_id", "1003002");//月还本付息额占月收入比率
	mapindex32.put("index_param", cord);//参数
	list.add(mapindex32);
	HashMap mapindex41 = new HashMap();
	mapindex41.put("bigmold_index_id", "1004");//与银行关系
	mapindex41.put("smallmold_index_id", "1004001");//信用记录
	mapindex41.put("index_param", 1);//参数
	list.add(mapindex41);
	HashMap mapindex42 = new HashMap();
	mapindex42.put("bigmold_index_id", "1004");//与银行关系
	mapindex42.put("smallmold_index_id", "1004002");//与本行关系
	mapindex42.put("index_param", realate);//参数
	list.add(mapindex42);
	map.put("model", list);
	IrmSecondloanLimit irmSecondloanLimit=new IrmSecondloanLimit();
	int score=irmSecondloanLimit.getIrmScore(map);//分数
	Map ed=irmSecondloanLimit.getIrmScoreRank(score);//额度
	HashMap mapjc = new HashMap();
	mapjc.put("client_rank_id", realate);
	mapjc.put("limit_score", score);
	String jc=irmSecondloanLimit.getIrmInterestCoefficient(mapjc);//获取决策系数
	String edm="0";
	if( ed.get("limit_rank_max")!=null){
		edm=(String)ed.get("limit_rank_max");
	}
	if(jc==null||"".equals(jc)){
		jc="0";
	}
	if(edm==null||"".equals(edm)){
		edm="0";
	}
	if(white!=null&&!"".equals(white)){
		white.set("totalLimit",edm );
		white.set("rate", jc);
		BigDecimal jcl=new BigDecimal(jc);
		BigDecimal edl=new BigDecimal(edm);
		Integer scorel= score;
		middleUpdate.set("amt", edl);
		middleUpdate.set("applyRate", jcl);
		middleUpdate.set("score", scorel);
		DatabaseUtil.updateEntity("default", white);
		DatabaseUtil.updateEntity("default", middleUpdate);
	}
	
	
	WdBaseService service=new WdBaseService();
	//业务申请
	HashMap<String, String> mapApp = new HashMap<String, String>();
	mapApp.put("applyId", middleUpdate.getString("applyId"));
	mapApp.put("ecifPartyNum", middleUpdate.getString("ecifPartyNum"));
	mapApp.put("productType", middleUpdate.getString("productType"));
	mapApp.put("applyXwTerm", middleUpdate.getString("applyXwTerm"));
	mapApp.put("creditAmount", middleUpdate.getString("amt"));
	mapApp.put("rate", middleUpdate.getString("applyRate"));//申请利率(网贷存的是小数，信贷存的是百分数)
	mapApp.put("guarantyType", "01");//担保方式
	mapApp.put("repaymentType", middleUpdate.getString("repaymentType"));
	mapApp.put("payment", middleUpdate.getString("payment"));
	mapApp.put("orgNum", middleUpdate.getString("orgNum"));
	mapApp.put("userNum", middleUpdate.getString("userNum"));
	mapApp.put("loanUse", middleUpdate.getString("loanUse"));//贷款用途
	
	HashMap<String, String> mapApprove = service.saveApply(mapApp);
	//业务批复
	service.saveApprove(mapApprove);
	//合同签约
	HashMap<String, String> mapContract = new HashMap<String, String>();
	mapContract.put("orgNum", middleUpdate.getString("orgNum"));
	mapContract.put("applyId", middleUpdate.getString("applyId"));
	mapContract.put("loanTurn",middleUpdate.getString("loanTurn"));//行业投向
	mapContract.put("signPlace", "绵阳商业银行");//签约地点
	mapContract.put("payWay", "0");//资金支付方式
	mapContract.put("zhzt", "正常");//状态

	mapContract.put("zhbs", "11");//账户标识(11储蓄账户;12公司账户;60内部账户)
	mapContract.put("kzbs", "11");//卡折标识(11卡;20折)
	mapContract.put("zh", middleUpdate.getString("zhzh"));//账号
	mapContract.put("zhkhjg", middleUpdate.getString("zhkhjg"));//账户开户机构
	mapContract.put("zhmc", middleUpdate.getString("zhmc"));//账户名称
	mapContract.put("accStatus", "正常");//
    mapContract.put("firstZhzh", middleUpdate.getString("firstZhZh"));//第一还款账号
    mapContract.put("firstZhkhjg", middleUpdate.getString("firstZhKhjg"));//第一还款账号开户行
    mapContract.put("firstZhmc", middleUpdate.getString("firstZhMc"));//第一还款账户名称
    mapContract.put("firstZhstatus", "正常");//第一还款账户名称
    mapContract.put("secondZhzh", middleUpdate.getString("secondZhZh"));//第二还款账号
    mapContract.put("secondZhkhjg", middleUpdate.getString("secondZhKhjg"));//第二还款账号开户行
    mapContract.put("secondZhmc", middleUpdate.getString("secondZhMc"));//第二还款账户名称
    mapContract.put("secondZhstatus", "正常");//第二还款账户名称
    mapContract.put("thirdZhzh", middleUpdate.getString("thirdZhZh"));//第三还款账号
    mapContract.put("thirdZhkhjg", middleUpdate.getString("thirdZhKhjg"));//第三还款账号开户行
    mapContract.put("thirdZhmc", middleUpdate.getString("thirdZhMc"));//第三还款账户名称
    mapContract.put("thirdZhstatus", "正常");//第三还款账户名称
	
    service.saveContract(mapContract);
}
}