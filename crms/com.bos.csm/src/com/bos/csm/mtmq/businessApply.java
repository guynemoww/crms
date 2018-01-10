/**
 * 
 */
package com.bos.csm.mtmq;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.socket.service.response.EsbBodyMtmqRsRvlvNodeArray;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2016-03-09 16:48:10
 *
 */
@Bizlet("")
public class businessApply {

	/**
	 * 新增业务预申请信息
	 * @return success表示处理成功,如果为其他字符串则给出相应的提示,如果抛出异常则返回异常信息
	 */
	@Bizlet("")
	public String addData(DataObject object){
		String userNum=object.getString("cstMgrNo");
		String orgNum=object.getString("ittbrId");
		String partyNum=object.getString("crCstNo");
		String applyNum=object.getString("bsnNo");
		String businessType=object.getString("aplyBsnTp");
		String bizType=object.getString("bsnKnd");
		String bizBatchId=object.getString("bsnAplyImgNo");
		String conBatchId=object.getString("ctrAplyImgNo");
		Date createTime=object.getDate("createTime");
		Date updateTime=object.getDate("updateTime");
		
		
		if("".equals(userNum)||null==userNum||"".equals(orgNum)||null==orgNum||"".equals(partyNum)||null==partyNum||"".equals(businessType)||null==businessType){
			return "报文不完整!";
		}else{
			if(businessType.equals("biz")){
				if("".equals(bizBatchId)||null==bizBatchId){
					return "业务类型为合同签订预申请时,业务申请影像批次号应为必输项!";
				}else{
					try {
						return insertData(userNum,orgNum,partyNum,applyNum,businessType,bizType,bizBatchId,conBatchId,createTime,updateTime);
					} catch (Exception e) {
						return "添加预申请数据异常!";
					}
				}
			}else if(businessType.equals("con")){
				if("".equals(applyNum)||null==applyNum){
					return "业务类型为合同签订预申请时,业务申请编号应为必输项!";
				}else{
					if("".equals(conBatchId)||null==conBatchId){
						return "业务类型为合同签订预申请时,合同申请影像批次号应为必输项!";
					}else{
						try {
							return insertData(userNum,orgNum,partyNum,applyNum,businessType,bizType,bizBatchId,conBatchId,createTime,updateTime);
						} catch (Exception e) {
							return "添加预申请数据异常!";
						}
					}
				}
			}else{
				return "业务类型传输错误!";
			}
		}
	}
	
	public String insertData(String userNum,String orgNum,String partyNum,String applyNum,String businessType,String bizType,String bizBatchId,String conBatchId,Date createTime,Date updateTime) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("partyNum", partyNum);
		map.put("userNum", userNum);
		map.put("orgNum", orgNum);
		
		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.csm.mtmq.toMtmq.findPartyId", map);
		if(result.length == 0||result ==null){
			return "交易成功，没有找到对应客户信息！";
		}
		
		DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizBusinessApply");
		con.set("userNum", userNum);
		con.set("orgNum", orgNum);
		con.set("partyNum", partyNum);
		con.set("partyId", ((DataObject)result[0]).get("PARTY_ID"));
		con.set("applyNum", applyNum);
		con.set("businessType", businessType);
		con.set("bizType", bizType);
		con.set("bizBatchId", bizBatchId);
		con.set("conBatchId", conBatchId);
		con.set("createTime", createTime);
		con.set("updateTime", updateTime);
		DatabaseUtil.insertEntity("default", con);
		return null;
	}
	
	@Bizlet("将数据库查到的数组转换为EsbBodyMtmqRsRvlvNodeArray对象数组")
	public Object[] getRvlvNodeArray(DataObject[] obj){
		
		EsbBodyMtmqRsRvlvNodeArray rvlvNodeArrays[] = new EsbBodyMtmqRsRvlvNodeArray[obj.length];
		for (int i = 0; i < rvlvNodeArrays.length; i++) {
			DataObject resultObject = obj[i];
			EsbBodyMtmqRsRvlvNodeArray rvlvNode = new EsbBodyMtmqRsRvlvNodeArray();
			rvlvNode.setNodeCd(resultObject.getString("IMAGE_TYPE_ID"));
			rvlvNode.setNodeNm(resultObject.getString("IMAGE_TYPE_NAME"));
			rvlvNodeArrays[i] = rvlvNode;
		}
		return rvlvNodeArrays;
	}
}
