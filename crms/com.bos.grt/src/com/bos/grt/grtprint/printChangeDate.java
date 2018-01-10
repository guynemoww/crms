/**
 * 
 */
package com.bos.grt.grtprint;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2015-10-08 02:45:40
 *
 */
@Bizlet("")
public class printChangeDate {

	
	@Bizlet("")
	public Object[] ruKuChangeDate(DataObject[] objs) {
		for(int i=0;i<objs.length;i++){
			String currencyCd=objs[i].getString("CURRENCY_CD");
			if(null!=currencyCd){
				currencyCd = BusinessDictUtil.getDictName("CD000001",currencyCd);
				objs[i].set("CURRENCY_CD", currencyCd);
			}
			
			String collType =objs[i].getString("COLL_TYPE");
			if(null!=collType){
				collType = BusinessDictUtil.getDictName("XD_YWDB0131",objs[i].getString("COLL_TYPE"));
				objs[i].set("COLL_TYPE", collType);
			}
			String sortType = objs[i].getString("SORT_TYPE");
			if(null!=sortType){
				sortType = BusinessDictUtil.getDictName("XD_YPZL01",objs[i].getString("SORT_TYPE"));
				objs[i].set("SORT_TYPE", sortType);
			}
			//转换权证类型
			String cardType = objs[i].getString("CARD_TYPE");
			if(null!=cardType){
				cardType = BusinessDictUtil.getDictName("XD_SXFS0002",objs[i].getString("CARD_TYPE"));
				objs[i].set("CARD_TYPE", cardType);
			}
			String userNum = objs[i].getString("USER_NUM");
			String userName = (GitUtil.getUserInfo(userNum)[0]).getString("empname");
			objs[i].set("USER_NAME", userName);
		}
		return objs;
	}
	
	@Bizlet("")
	public DataObject getOtherInfo(DataObject object,String flag) {
		
		String SURETY_KEY_ID=object.getString("SURETY_KEY_ID");
		Map map=new HashMap();
		map.put("suretyKeyId", SURETY_KEY_ID);
		String sqlUrl = "com.bos.grt.grtSelect.getInDate";
		if("out".equals(flag)){
			sqlUrl = "com.bos.grt.grtSelect.getOutDate";
		}
		
		if(null!=SURETY_KEY_ID){
			Object[] objs = DatabaseExt.queryByNamedSql("default",sqlUrl, map);
			if(objs.length>0){
				DataObject con = (DataObject) objs[0];
				
				String time=con.getString("CREATE_TIME");
				String reason=con.getString("OUT_REASON");
				String nno = con.getString("NNO"); 
				String updatetime = con.getString("UPDATE_TIME");
				//转换字典项
				if(null!=reason){
					reason = BusinessDictUtil.getDictName("XD_SXFS0004",reason);
					object.set("OUT_REASON",reason);
				}
				if(null!=nno){
					object.set("IN_OUT_NO",nno);
				}
				
				if(null!=time){
					time=changeDate(time);
				}else{
					time=changeDate(updatetime);
				}
				object.set("CREATE_TIME", time);
			}
		}
		return object;
	}
	
	@Bizlet("")
	public Object[] fushuChangeDate(DataObject[] objs) {
		for(int i=0;i<objs.length;i++){
			//转换权证类型
			String cardType = objs[i].getString("CARD_TYPE");
			if(null!=cardType){
				cardType = BusinessDictUtil.getDictName("XD_SXFS0002",objs[i].getString("CARD_TYPE"));
				objs[i].set("CARD_TYPE", cardType);
			}
		}
		return objs;
	}
	//日期转换  原格式yyyy-MM-dd转yyyy年MM月dd日
	@Bizlet("")
	public String changeDate(String date) {
		String[] array;
		String conDate;
		if ("" != date && null != date) {
			array = date.split("-");
			conDate = array[0] + "年" + array[1] + "月" + array[2] + "日";
			return conDate;
		}
		return null;
	}
}
