/**
 * 
 */
package com.bos.batch;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author 李磊BOS
 * @date 2014-06-13 15:52:14
 *
 */
@Bizlet("抽取数据清单数据")
public class UnLoadDatalist {
	@Bizlet("删除数据")
	public String deleteData(String inputDate,String logFile,String dataListType) throws Exception {
		String status = "1"; // 成功
		String ifReloadData = null;// 重跑标识 1：重跑 其他：正常跑
		java.util.Map<String, String> map = new HashMap<String, String>();
		Object[] results =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.getorgid.getReload",map);
		if(results!=null && results.length>0){
			ifReloadData = (String) results[0];
		}
		
		//重跑
		if("1".equals(ifReloadData)){
			// 命令集与SQL语句写入到文件中 gzip写入到backup.sh SQL语句写入到unload.sql
			String batchPath = ConfigurationUtil.getUserConfigSingleValue("CustomConfig","System","batchPath");
			String backupShfile = BusinessDictUtil.getDictName("XD_RZCD0002", "backupDatalistShFile");
			backupShfile = batchPath+backupShfile;
			status = backup(backupShfile,inputDate,logFile);
		} else {//正常跑
			String oldDate = null;
			oldDate = this.diffDay(inputDate, -8);

			// 如果正常跑已经存在的月末数据清单时,会发生这个月末数据清单的重复插入;
			// 加入这句SQL,避免出现错误.  
			java.util.Map<String, String> reloadMap = new HashMap<String, String>();
			reloadMap.put("inputDate", inputDate);
			
			//  删除当前日期七天的数据清单与所有月末数据清单
			reloadMap.put("oldDate", oldDate);
			//当前数据清单类型 dataListType
			reloadMap.put("dataListType", dataListType);
			
			DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.batch.getorgid.deleteAllDate",reloadMap);
		
		} 

		// 删掉重跑日期的记录。
		java.util.Map<String, String> reloadMap = new HashMap<String, String>();
		reloadMap.put("inputDate", inputDate);
		reloadMap.put("dataListType", dataListType);
		
		DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.getorgid.deleteInputDate",reloadMap);

		return status;	
	}
	
	
	@Bizlet("分机构打包数据")
	public static String gzipClistByOrgs(String shFile,String batchDate,String logFile,String datatype) throws Exception{
		LogUtil.logDebug("日志文件A：{0}", null, logFile);
		// 查询需要打包的所有机构
		java.util.Map<String, String> map = new HashMap<String, String>();
		Object[] results =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.getorgid.getOrgIds",map);
		if(results!=null && results.length>0){
			String params = new String();
			params += CommonUtil.getBatchPath()+" ";
			if(datatype.equals("JJQD")){
			    params += sqlCols.replace(" ", "")+" ";
			}else if(datatype.equals("TZQD")){
				params += sqlCols_TZ.replace(" ", "")+" ";
			}
			params += batchDate+" ";
			params += getOrgIds(results).replace(" ", "")+" ";
			params += datatype;
			// 执行shell
			LogUtil.logDebug("日志文件：{0}", null, logFile);
			return CommonUtil.execShell(shFile,params,logFile);
		}
		return "1";
	}
	
	@Bizlet("分机构插入文件")
	public static String insertDataList(String batchDate,String datatype) throws IOException, InterruptedException{
		// 查询需要打包的所有机构
		java.util.Map<String, String> map = new HashMap<String, String>();
		Object[] results =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.getorgid.getOrgIds",map);
		String fileDate = batchDate.replace("-", "");
		if(results!=null && results.length>0){
			for(Object org:results){
				String orgId = (String) org;
				// 插入清单03
				WriteDB2BLOB(orgId,fileDate,datatype);
				//WriteDB2BLOB1(orgId,fileDate,"JJQD");
			}
		}
		return "1";
	}
	
	
	
	
	//写入文件到数据库
	private static void WriteDB2BLOB(String orgId,String inputDate,String dataListType) throws IOException{
        //通过机构号+数据日期+数据清单类型.unl.gz得到数据文件名称 
		String fileName = dataListType + "."+orgId + "."+inputDate + ".unl.gz";
		//数据清单文件路径
		String fpath = BusinessDictUtil.getDictName("XD_RZCD0002", "clistDir");
		//台账清单文件路径
		if(dataListType.equals("TZQD")){fpath="/crmsshare/CRMS/data/hbclist";}
		fpath = fpath + File.separator+ inputDate+File.separator+fileName;
			File f = new File(fpath);
			FileInputStream fis;
			if(f.exists()){
				LogUtil.logDebug("文件路径：{0}", null, fpath);
				fis = new FileInputStream(fpath);
				int fileSize = fis.available();
				DataObject dobj = DataObjectUtil.createDataObject("com.bos.dataset.batch.TbBatchDatalistAttachment"); 
				dobj.set("uuid", GitUtil.genUUIDString());
				dobj.set("orgNum", orgId);
				dobj.set("inputdate", inputDate);
				dobj.set("datalisttype", dataListType);
				dobj.set("filename", fileName);
				dobj.set("datalistpath", fpath);
				dobj.set("contenttype", CONTENTTYPE);
				dobj.setInt("contentlength", fileSize);
				dobj.set("contentstatus", CONTENTSTATUS);
				// 只需要存路径就行了，不需要存内容！
//				byte[] b = new byte[10*1024*1024];
//				//byte[] b = new byte[10*1024*1024];
//				fis.read(b, 0, fileSize);
//				dobj.setBytes("datalistcontent",b);
				fis.close();
				DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, dobj);
				}else {
					LogUtil.logError("文件{0}不存在", null, fpath);
				}
			
	}
//	写入文件到数据库
	private static void WriteDB2BLOB1(String orgId,String inputDate,String dataListType) throws IOException{
        //通过机构号+数据日期+数据清单类型.unl.gz得到数据文件名称 
		String fileName = dataListType + "."+orgId + "."+inputDate + ".unl.gz";
//		String batchPath = ConfigurationUtil.getUserConfigSingleValue("CustomConfig","System","batchPath");
//		String fpath = BusinessDictUtil.getDictName("XD_RZCD0002", "clistDir");
		String fpath = "E:\\TEMP\\20140731\\" +fileName;
			File f = new File(fpath);
			
			if(f.exists()){
				FileInputStream fis = new FileInputStream(fpath);
				int fileSize = fis.available();
				DataObject dobj = DataObjectUtil.createDataObject("com.bos.dataset.batch.TbBatchDatalistAttachment"); 
				dobj.set("uuid", GitUtil.genUUIDString());
				dobj.set("orgNum", orgId);
				dobj.set("inputdate", inputDate);
				dobj.set("datalisttype", dataListType);
				dobj.set("filename", fileName);
				dobj.set("contenttype", CONTENTTYPE);
				dobj.setInt("contentlength", fileSize);
				dobj.set("contentstatus", CONTENTSTATUS);
				// 只需要存路径就行了，不需要存内容！
//				byte[] b = new byte[10*1024*1024];
//				//byte[] b = new byte[10*1024*1024];
//				fis.read(b, 0, fileSize);
//				dobj.setBytes("datalistcontent",b);
//				fis.close();
				DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, dobj);
				}else {
					LogUtil.logError("文件{0}不存在", null, fpath);
				}
			
	}
	
	
//	说明文件的类型
	private static String CONTENTTYPE = "application/x-gzi";
	private static final String CONTENTSTATUS = "1";
	private static String getOrgIds(Object[] results) {
		String orgs = "";
		for(Object org:results){
			String orgId = (String) org;
			orgs += orgId + ",";
		}
		return orgs;
	}
	
//	 查询借据清单的列（这个一定不能有空格！）
	private final static String sqlCols = 
	"verify,replace(inputDate,'-','/'),serialno,relativeorgidname,operateorgidname,"
	+ "customername,certid,customerid,warrantor,corpid,"
	+ "warrantorid,vouchtypename,vouchtypename13,replace(putoutdate,'-','/'),replace(actualmaturity,'-','/'),"
	+ "replace(extendmaturity,'-','/'),businesscurrencyname,balance,normalbalance,overduebalance1,"
	+ "overduebalance2,dullbalance,badbalance,cancelsum,occurtypename,"
	+ "direction,directionname,loancardno,bcloancardno,industrytype,"
	+ "industrytypename,industrytypename1,industrytypename2,industrytypename3,"
	+ "orgtypename,orgtypename1,scopename,artificialno,contractNum,"//contractNum|relativeserialno2
	+ "overduedays,bcbusinesstypename,subjectno,subjectnoname,interestbalance1,"
	+ "interestbalance2,maturitydays,flagname5,businesssum,lastyearbalance,"
	+ "lastyearresult,lastseasonbalance,lastseasonresult,lastmonthbalance,lastmonthresult,"
	+ "currentlyresult,fictitiouscorpid,fictitiousperson,regioncode,regioncodename,"
	+ "registeradd,totalassets,totaldebt,listingcorpornotname,boursename,"
	+ "stockholdertypename1,stockholder1,stockholderid1,stockholdercorpid1,stockholdertypename2,"
	+ "stockholder2,stockholderid2,stockholdercorpid2,stockholdertypename3,stockholder3,"
	+ "stockholderid3,stockholdercorpid3,stockholdertypename4,stockholder4,stockholderid4,"
	+ "stockholdercorpid4,stockholdertypename5,stockholder5,stockholderid5,stockholdercorpid5,"
	+ "relationid1,relationcertid1,relationname1,relationid2,relationcertid2,"
	+ "relationname2,relationid3,relationcertid3,relationname3,relationid4,"
	+ "relationcertid4,relationname4,relationid5,relationcertid5,relationname5,"
	+ "contractflagname,creditaggreement,creditartificialno,bccbusinesscurrencyname,bccbusinesssum,"
	+ "bcbusinesscurrencyname,bcbusinesssum,replace(bcputoutdate,'-','/'),replace(bcmaturity,'-','/'),actualbusinessrate,"
	+ "manageorgidname,finishorgname,groupflagname,groupname,stockholderflagname,"
	+ "relationflagname,blacklistflagname,advancedflagname,advanceflagname,shifttypename,"
	+ "operatetypename,risklevelname,riconfirmdate,replace(accountmonth,'-','/'),evaluateresultname,"
	+ "riflagname1,riflagname2,riflagname3,riflagname4,riflagname5,"
	+ "sum4,thirdparty1,ourrolename,flagname4,bankname,"
	+ "economytypename,financeornotname,thirdpartyid3name,describe2name,bailratio,"
	+ "orgnaturename,replace(approveuserid1,'|','??'),iforphanname,syndicatedloantypename,iffixloanname," //modi tlu 20110608 approveuserid1--> replace(approveuserid1,'|','??') 替换乱码文字excel导出的时候多出| 字段个数变化 无法导入的问题
	+ "fixloantypename,ifprojectfinancename,paymentname,rccurrencyname,registercapital,"
	+ "ratefloattypename,ratefloat,overindustryname,isimportantloanname,"
	+ "contractsum,changesum,usesum,modelnoname,replace(newaccountmonth,'-','/'),"
	+ "newevaluateresultname,newmodelnoname,pbankclassifytypename,bbankclassifytypename,sloanbusinesstypename,"
	+ "entholdingtypename,entflag1name,entflag2name,credittermtypename,statisticsorgname,"
	+ "contractflag1name,contractflag2Name,IndustrySector,direction1,direction1Name," //add by tlu 20110324
	+ "direction2,direction2Name,direction3,direction3Name,"                           //add by tlu 20110324
	+ "industryType3,industryType2,industryType1"
	+ ",newtechcorpornotname,AgreeSelfSum,AgreeTrusteeSum,ShouldTrusteeSum"//add by tlu 20110920
	+ ",CorpusPayMethodName,MeetsReqInstalName,SupAgStartDate"//add by tlu 20110920
	+ ",directionNew1,directionNew1Name,directionNew2,directionNew2Name,directionNew3,directionNew3Name"
	+ ",directionNew4,directionNew4Name,flag6Name,flag7Name,flag8Name,EconomyTypeNewName"
	+ ",IndustryTypeNew1,IndustryTypeNew1Name,IndustryTypeNew2,IndustryTypeNew2Name"
	+ ",IndustryTypeNew3,IndustryTypeNew3Name,IndustryTypeNew4,IndustryTypeNew4Name"
	+ ",directionNew5,directionNew5Name,IfModernZonesName,producttypename,ordinaryhousloanname"//add by tlu 20120130 增加投向小类
	+ ",smallordhousloanname,firectestateloansname,capitalfundname,firstyearreimbursename,confirmvalue"
	+ ",totalassetsentinfo,employees,incomesum,economytypenewautoname,regioncodenew,regioncodenamenew"
	+ ",futureresidualpaymentstimes,futureresidualpaymentssum,currentresidualpaymentstimes,currentresidualpaymentssum,accumulatresidualpaymentstimes"
	+ ",residueresidualpaymentstimes,greeneffiloanName,loanenergyName"
	+ ",bailcurName,bailsum,"//保证金
	+ "RestructurLName,dealNoLoansOne,dealNoLoansTwo,dealNoLoansThree,"
	+ "dealNoLoansFour,dealNoLoansFive,ProjectFlagName,ConvenLoansName,SmallBusProductName,UrbanConstLoansName,FreeTradeBusName,''";//bailcurrency RestructurLoans  ProjectFlag  

//	 查询台账清单的列（这个一定不能有空格！）
	private final static String sqlCols_TZ = 
		"memo,relativeserialno,occurserialno,customerid,certid,"
		+ "customername,operateorgidname,replace(occurdate,'-','/'),occursubjectname,actualsum,"
		+ "normalsum,overduesum1,overduesum2,dullsum,badsum,balance,FreeTradeBusName,''";

		
		
	@Bizlet("备份数据")
	private String backup(String shFile,String InputDate, String logFile) throws Exception {
		
		String params = new String();
		params += CommonUtil.getBatchPath();
		params += "\""+sqlCols.replace(" ", "")+"\"";
//		String command = CommonUtil.shFile(shFile,params);
		// 执行shell
		return CommonUtil.execShell(shFile,params,logFile);
	}
	
	@Bizlet("获取clist最大日期")
	public String getInputDate() throws SQLException {
		String inputDate = null;
		java.util.Map<String, String> map = new HashMap<String, String>();
		Object[] rs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.getorgid.getMaxDate",map);
		if(rs!=null && !rs.equals("")){
			inputDate = (String) rs[0];
		}		
		return inputDate;
	}
	
	
	@Bizlet("获取tzclist最大日期")
	public String getTzInputDate() throws SQLException {
		String inputDate = null;
		java.util.Map<String, String> map = new HashMap<String, String>();
		Object[] rs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.getorgid.getTzMaxDate",map);
		if(rs!=null && !rs.equals("")){
			inputDate = (String) rs[0];
		}		
		return inputDate;
	}
	
	
	/**
	 * 获得和给定日期sDate相差Days天的日期
	 * @param sDate
	 * @param Days
	 * @return
	 */
	public String diffDay(String sDate, int Days) {

		if (Days == 0) {
			return sDate;
		}
		
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			cal.setTime(formatter.parse(sDate));
		} catch (Exception e) {
			;
		}

		cal.add(Calendar.DATE, Days);
		
		return formatter.format(cal.getTime());
	}
	

}
