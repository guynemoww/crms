/**
 * 
 */
package com.bos.bizApprove;

import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2015-10-14 19:32:46
 *
 */
@Bizlet("")
public class ApproveDataConver {
	
	@Bizlet("")
	public DataObject approveDataConvert(DataObject object){
		
		String bizType=object.getString("BIZ_TYPE");		//04 小贷  其他对公
		String conclusion=object.getString("APPROVE_CONCLUSION");		//1 3 同意
		
		String reportName;
		if(bizType.equals("04")||bizType.equals("06")){
			if(conclusion.equals("1")||conclusion.equals("3")){
				reportName="approve/XDZXApproveNotice.docx";
			}else{
				reportName="approve/XQYXDZXRefuseNotice.docx";
			}
			object.set("reportName", reportName);
			
		}else{
			reportName="approve/PFTZS.docx";
			object.set("reportName", reportName);
		}
		
		String applyDate=object.getString("APPLY_DATE");
		applyDate=changeDate(applyDate);
		
		String validDate=object.getString("VALID_DATE");
		validDate=changeDate(validDate);
		
		object.set("APPLY_DATE", applyDate);
		object.set("VALID_DATE", validDate);
		
		Object[] arrays1=(Object[]) object.get("arrays1");
		
		for(int k=0;k<arrays1.length;k++){
			DataObject db=(DataObject) arrays1[k];
			String sortType=db.getString("GUARANTY_TYPE");
			
			StringBuffer sb=new StringBuffer();
			if(sortType.contains(",")){
				String[] arr=sortType.split(",");
				
				for(int i=0;i<arr.length;i++){
					sortType = BusinessDictUtil.getDictName("CDZC0005",arr[i]);
					if(i!=arr.length-1){
						sb.append(sortType).append(",");
					}else{
						sb.append(sortType);
					}
				}
				db.set("GUARANTY_TYPE", sb.toString());
				arrays1[k]=db;
			}else{
				sortType = BusinessDictUtil.getDictName("XD_YWDB0131",sortType);
				db.set("GUARANTY_TYPE", sortType);
				arrays1[k]=db;
			}
		}
		object.set("arrays1", arrays1);
		
		return object;
	}
	
	/**
	 * 转换担保类型  例:02,03,04  --->抵押,质押,保证
	 * @param GUARANTY_TYPE  (02,03,04)
	 * @return
	 */
	@Bizlet("")
	public String changeDBLX(String GUARANTY_TYPE){
		 if(GUARANTY_TYPE==null){
			 return null;
		 }
		String[] arrStr=GUARANTY_TYPE.split(",");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<arrStr.length;i++){
			if((i+1)==arrStr.length){
				if("02".equals(arrStr[i])){
					sb.append("抵押");
				}else if("03".equals(arrStr[i])){
					sb.append("质押");
				}else if("04".equals(arrStr[i])){
					sb.append("保证");
				}
			}else{
				if("02".equals(arrStr[i])){
					sb.append("抵押").append(",");
				}else if("03".equals(arrStr[i])){
					sb.append("质押").append(",");
				}else if("04".equals(arrStr[i])){
					sb.append("保证").append(",");
				}
			}
		}
		return sb.toString();
	}
	
	
	//日期转换  原格式yyyy-MM-dd转yyyy年MM月dd日
	@Bizlet("")
	private String changeDate(String date) {
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
