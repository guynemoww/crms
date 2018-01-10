package com.bos.bizApply;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("")
public class GroupInfo {
	public static TraceLogger logger = new TraceLogger(GroupInfo.class);

	/**
	 * 获取统一授信总额度
	 * @param GroupPartyId 
	 * @param proFlag 
	 * @return
	 */
	@Bizlet("")
	public BigDecimal getGroupCredit(String GroupPartyId, String proFlag) {
		BigDecimal ret = new BigDecimal("0");
		try {
			DataObject group = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
			group.set("groupPartyId", GroupPartyId);
			DataObject[] members = DatabaseUtil.queryEntitiesByTemplate("default", group);
			if (members == null || members.length == 0) {
				return ret;
			}
			for (int i = 0; i < members.length; i++) {
				String showFlag = "1";//2表示客户有在途或生效的综合授信、统一授信
				DataObject tmp = members[i];
				String memberPartyId = tmp.getString("corporationPartyId");
				Map<String, String> map = new HashMap<String, String>();
				map.put("partyId", memberPartyId);
				map.put("proFlag", proFlag);
				int count = DatabaseExt.countByNamedSql("default", "com.bos.bizApply.groupApply.getMemberZhsxCount",
						map);
				if (count > 0) {
					showFlag = "2";
				}
				map.put("showFlag", showFlag);
				Object[] amount = DatabaseExt.queryByNamedSql("default",
						"com.bos.bizInfo.groupBiz.getMemberCreditAmount", map);
				BigDecimal amt = new BigDecimal("0");
				if (((DataObject) amount[0]).get("C") != null) {
					amt = (BigDecimal) (((DataObject) amount[0]).get("C"));
				}
				ret = ret.add(amt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = new BigDecimal("-1");
		}
		return ret;
	}

	@Bizlet("获取统一授信总敞口")
	public BigDecimal getGroupCreditCk(String GroupPartyId) {
		BigDecimal ck = new BigDecimal("0");//集团敞口金额
		String proFlag = "1";
		try {
			DataObject group = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
			group.set("groupPartyId", GroupPartyId);
			DataObject[] members = DatabaseUtil.queryEntitiesByTemplate("default", group);
			if (members == null || members.length == 0) {
				return ck;
			}
			//获取该集团的所有成员
			for (int i = 0; i < members.length; i++) {
				String showFlag = "1";//2表示客户有在途或生效的综合授信、统一授信
				DataObject tmp = members[i];
				String memberPartyId = tmp.getString("corporationPartyId");
				GroupInfo gi = new GroupInfo();
				Object[] bizs = gi.getMemberBiz(memberPartyId);
				//获取每个成员所需要计算的单笔业务
				for (int j = 0; j < +bizs.length; j++) {
					if(null == ((DataObject) bizs[j]).get("CA")){
						logger.info("统一授信客户业务申请，获取集团敞口------>集团目前总额度=" + ck + "，其中集团成员=" + memberPartyId + "------>业务申请="+ ((DataObject) bizs[j]).getString("APPLY_ID") + "------>单笔敞口额度不计算");
					}else{
						BigDecimal ca = ((DataObject) bizs[j]).getBigDecimal("CA");//单笔总金额
						BigDecimal kj = new BigDecimal("0");////单笔业务需要扣减的金额
						BigDecimal ckdb = new BigDecimal("0");//单笔敞口
						//获取单笔业务计算敞口需要的扣减额度
						HashMap mapKj = new HashMap();
						mapKj.put("applyId", ((DataObject) bizs[j]).getString("APPLY_ID"));
						Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.bizInfo.bizInfo.getKjAmt", mapKj);
						if (null != objs && 0 != objs.length) {
							kj = (BigDecimal) objs[0];
						}
						//计算单笔敞口，累加集团敞口
						ckdb = ca.subtract(kj);
						ck = ck.add(ckdb);
						logger.info("统一授信客户业务申请，获取集团敞口------>集团目前总额度=" + ck + "，其中集团成员=" + memberPartyId + "------>业务申请="	+ ((DataObject) bizs[j]).getString("APPLY_ID") + "------>单笔敞口额度为("+ca+"-"+kj+")="+ckdb);
					
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ck = new BigDecimal("-1");
		}
		logger.info("统一授信客户业务申请，获取集团敞口------>集团总敞口额度=" + ck);
		return ck;
	}
	public Object[] getMemberBiz(String memberPartyId){
		String showFlag = "1";//2表示客户有在途或生效的综合授信、统一授信
		Map<String, String> map = new HashMap<String, String>();
		map.put("partyId", memberPartyId);
		map.put("proFlag", "1");
		int count = DatabaseExt.countByNamedSql("default", "com.bos.bizApply.groupApply.getMemberZhsxCount",
				map);
		if (count > 0) {
			showFlag = "2";
		}
		map.put("showFlag", showFlag);
		Object[] bizs = DatabaseExt.queryByNamedSql("default", "com.bos.bizInfo.groupBiz.getMemberCredit", map);
		return bizs;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
