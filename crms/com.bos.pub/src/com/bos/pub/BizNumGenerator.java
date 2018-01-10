package com.bos.pub;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 王世春
 * @date 2014-02-26 15:48:52
 * @description 各类编号的生成
 */
@Bizlet("各类编号的生成器")
public class BizNumGenerator {

	/**
	 * @return
	 * @author 王世春
	 */
	@Bizlet("生成客户编号")
	public static String genCustomerNum() {
		String date = new SimpleDateFormat("yyyyMMdd").format(GitUtil
				.getBusiTimestamp());
		return date + getNextSequence("SEQ_CRT_KH");
	}
	@Bizlet("生成ECIF的流水号")
	public static String nowString() {
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(GitUtil
				.getBusiTimestamp());
		
//	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//    String dateStr=df.format(new Date());// new Date()为获取当前系统时间
    String transNO=date+getNextSequence("SEQ_CRT_FK")+".I01.S64.167";
     return transNO;
	}
	/**
	 * @return
	 * @author 王世春
	 */
	@Bizlet("生成评级编号")
	public static String genEvalNum() {
		String date = new SimpleDateFormat("yyMMdd").format(GitUtil
				.getBusiTimestamp());
		return date + getNextSequence("SEQ_CRT_PJ");
	}

	/**
	 * @return
	 * @author 王世春
	 */
	@Bizlet("生成业务编号")
	public static String genBizNum() {
		

		String date = new SimpleDateFormat("yyMMdd").format(GitUtil
				.getBusiTimestamp());
		return date + getNextSequence("SEQ_CRT_YW");
	}

	/**
	 * @return
	 * con:合同数  今天
	 * isCon:是否综合授信合同
	 * appDtlNum:综合授信分项下批复合同数
	 * isConX:是否综合授信协议
	 * @author 王世春  
	 */
	@Bizlet("生成合同编号")
	public static String genConctractNum(Integer con,String isCon,Integer appDtlNum,Integer isConX,String ConNum,String ConNums) {
		String date = new SimpleDateFormat("yyyyMMdd").format(GitUtil
				.getBusiTimestamp());
		String strCon = "CON"+date+getNextSequence("SEQ_CRT_HT");
		con = con+1;
		String str = con.toString();
		if(ConNums != null && ConNums.length()>17){
			Integer ing = Integer.parseInt(ConNums.substring(17))+1;
			str = ing.toString();
		}
		String appStr = "0";
		if(isCon.equals("02") && (ConNum != null && ConNum != "" && ConNum != "null")){
			appDtlNum = appDtlNum+1;
			appStr = appDtlNum.toString();
			strCon = ConNum.substring(0,11)+getNextSequence("SEQ_CRT_HT")+ConNum.substring(15)+"-"+appStr;
			return strCon; 
		}
		if(str.length() == 1){
			strCon += "000" + Integer.parseInt(str);
		}if(str.length() == 2){
			strCon += "00" + Integer.parseInt(str);
		}if(str.length() == 3){
			strCon += "0" + Integer.parseInt(str);
		}
		
		return strCon;
	}
	@Bizlet("生成合同编号")
	public static String genConctractNum(String strCon,Integer len){
		strCon = strCon.substring(0,16);
		strCon = strCon + len.toString();
		return strCon;
	}

	/**
	 * @return
	 * @author 王世春
	 */
	@Bizlet("生成放款申请编号")
	public static String genPayNum() {
				String date = new SimpleDateFormat("yyMMdd").format(GitUtil
						.getBusiTimestamp());
				return date + getNextSequence("SEQ_CRT_FK");
	}
	
	@Bizlet("生成额度编号")
	public static String getCrdNum(String edString){
		String date = new SimpleDateFormat("yyMMdd").format(GitUtil
				.getBusiTimestamp());
		return edString + date + getNextSequence("SEQ_CRT_ED");
	}
	
	@Bizlet("生成计量系统需要的8位流水号")
	public static String getLcsStan(){
		return getNextSequence("SEQ_LCS_STAN");
	}
	
	private static String getNextSequence(String seqName){
		String num = null;
		try {
				num = String.valueOf(DatabaseExt.getNextSequence(seqName));
				if(seqName.equals("SEQ_CRT_KH")||seqName.equals("SEQ_BIZ_PF")||seqName.equals("SEQ_CON_XY")||seqName.equals("SEQ_CON_JK")||seqName.equals("SEQ_CON_SUB")||seqName.equals("SEQ_PAY_JJ")){
					return StringUtils.leftPad(num, 6, '0');
				}else if("SEQ_LCS_STAN".equals(seqName) || "SEQ_SYS_TRC_NO".equals(seqName)){
					return StringUtils.leftPad(num, 8,'0');
				}else{
					return StringUtils.leftPad(num, 4, '0');
				}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			LogUtil.logError("查询sequence时出错："+seqName, e, new Object[0]);
			return null;
		}
	}
	
	/**
	 * @return String
	 * @author 甘泉
	 */
	@Bizlet("生成联保客户编号")
	public static String genGuarGroupNum() {
		String num_str = "LB";
		return num_str + StringUtils.leftPad(getNextSequence("SEQ_CSM_LB"), 6, '0');
	}
	
	/**
	 * @return String
	 * @author 甘泉
	 */
	@Bizlet("生成集团客户编号")
	public static String genGroupCompanyNum() {
		String num_str = "JT";
		return num_str + StringUtils.leftPad(getNextSequence("SEQ_CSM_JT"), 6, '0');
	}
	
	/**
	 * @return String
	 * @author xiaoxia
	 */
	@Bizlet("生成贷后检查编号")
	public static String genAftCheckNum() {
		String str = "JCBH";
		String date = new SimpleDateFormat("yyMMdd").format(GitUtil.getBusiTimestamp());
		return str + date + StringUtils.leftPad(getNextSequence("SEQ_AFT_JC"), 6, '0');
	}
	
	/**
	 * @return String
	 * @author xiaoxia
	 */
	@Bizlet("生成贷后变更编号")
	public static String genLoanChangeNum() {
		String str = "BGBH";
		String date = new SimpleDateFormat("yyMMdd").format(GitUtil.getBusiTimestamp());
		return str + date + StringUtils.leftPad(getNextSequence("SEQ_AFT_BG"), 6, '0');
	}
	
	/**
	 * @return String
	 * @author zhaochunming
	 */
	@Bizlet("生成系统跟踪编号")
	public static String genSysTrcNo() {
		return getNextSequenceByName("SEQ_SYS_TRC_NO");
	}
	private static String getNextSequenceByName(String seqName){
		Map seqMap=new HashMap();
		seqMap.put("sequenceName", seqName);
		Object[] objects = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.sequences.getSequence", seqMap);
		
		String ret = "0";
		if(objects!=null && objects.length==1)
		{
			Object object = objects[0];
			ret = object.toString();
		}
		return ret;
	}
	/**
	 * 获取业务编号
	 * BY 3231
	 * */
	@Bizlet("根据类别生成业务编号")
	public static String getBizNum(String seqName){
		String date = new SimpleDateFormat("yyMMdd").format(GitUtil
				.getBusiTimestamp());
		return date + getNextSequence(seqName);
	}
}
