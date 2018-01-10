/**
 * 
 */
package com.primeton.tsl.ecif.port;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.system.exception.EOSException;
import com.primeton.mgrcore.client.DateTools;

import commonj.sdo.DataObject;

/**
 * @author zhouxu
 *初始化 ReqHeaderAndTranHeader
 */
public class ReqHeaderAndTranHeaderUtil {
	
		public static void InitReqHeaderAndTranHeader(CommRequestHeader requestHeader,
				CommReqTranHeader reqTranHeader, String serviceCode, String orgNum,
				String userNum) throws EOSException{
			DataObject	ccRel = 	EntityUtil.searchEntity("com.bos.dataset.sys.TbOrgAccRel", "oprOrgNo", GitUtil.getCurrentOrgCd(),"oprUserNo",GitUtil.getCurrentPositionCode());
			requestHeader.setVersionNo("ESB0001-0001"); // 版本号
			requestHeader.setReqSysCode("01201"); // 请求方系统代码
			requestHeader.setReqSecCode(""); // 安全节点号
			requestHeader.setTxType("RQ"); // RQ
			requestHeader.setTxMode("0"); // 0-正常 1-冲销2-冲正 3-重发
			requestHeader.setTxCode(serviceCode); // soapenv服务码
			String busidate=GitUtil.getBusiDateYYYYMMDD();
			requestHeader.setReqDate(busidate); // 业务日期
			String time =new SimpleDateFormat("yyyyMMddhhmmss").format(GitUtil.getBusiDate());
			System.out.println("==============机器时间戳:===="+time);
			requestHeader.setReqTime(time); // 机器时间戳
			String reqSeqNo =DateTools.getReqSeqNo();
			System.out.println( "===============请求方交易流水号================="+reqSeqNo);
			requestHeader.setReqSeqNo(reqSeqNo); // 请求方交易流水号
			requestHeader.setChanlNo("13"); // 渠道号（字符）
//			requestHeader.setBrch(orgNum); // 机构编号
//			if (StringUtils.isEmpty(orgNum)) {
//				requestHeader.setBrch(GitUtil.getCurrentOrgCd()); // 机构编号
//			}
			requestHeader.setTermNo(""); // 终端号
//			requestHeader.setOper(userNum); // 柜员
//			if (StringUtils.isEmpty(userNum)) {
//				requestHeader.setOper(GitUtil.getCurrentPositionCode()); // 柜员
//			}
			String orgCode=GitUtil.getOrgCode();
			String accOrgCode=GitUtil.getAccOrg(orgCode);
			requestHeader.setBrch(accOrgCode); // 机构编号
			requestHeader.setOper(GitUtil.getNumOrg(accOrgCode)); // 柜员
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
//			reqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());// 发送方机构ID
//			reqTranHeader.setHUserID(GitUtil.getCurrentUserId());// 服务请求者
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
}
