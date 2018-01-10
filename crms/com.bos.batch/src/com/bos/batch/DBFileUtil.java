/**
 * 
 */
package com.bos.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import com.bos.inter.LoanToLcs;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.ftp.FtpService;
import com.bos.pub.sftp.SftpService;
import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author CHJ
 * @date 2015-09-17 15:02:28
 * 
 */
@Bizlet("数据库文件操作")
public class DBFileUtil {

	private String systemId;
	private String encode;
	private String fileName;
	private String localPath;
	private String remotePath;
	private String tabName;
	private boolean isTruncate;

	@Bizlet("装载数据文件")
	public String loadDBFile(String operateType) throws Exception {
		boolean result = true;
		//实例化ftp配置信息
		FtpService ftpService = this.initConfig(operateType);
		//检查FTP服务器是否关闭 
		ftpService.isOpenFTPConnection();
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		//传入本地路径、远程路径、文件名，下载文件
		System.out.println("---- 准备开始下载loadDBFile ----");
		System.out.println("本地路径:"+localPath+",远程路径:"+remotePath+",文件名称:"+fileName);
		ArrayList<String> ftpFiles = ftpService.downloadFile(localPath, remotePath, fileName);
		for (String dbFile : ftpFiles) {
			result = this.execShell(tabName, dbFile, isTruncate);
			results.add(result);
		}
		// 如果下载文件数量为空，或者转载文件存在失败，则返回2
		if (results.contains(false) || ftpFiles.size() == 0) {
			return "2";
		}
		return "1";
	}
	
	@Bizlet("装载数据文件")
	//下载垫款文件：通过FTP连接到存放垫款文件的服务器 下载垫款文件
	public void loadDKFile2(String operateType){
		//boolean result = true;
		try {
			FtpService ftpService = this.initConfigDK(operateType);//创建垫款FTP连接
			boolean openFTPConnection = ftpService.isOpenFTPConnection();//FTP连接是否打开
			//ArrayList<Boolean> results = new ArrayList<Boolean>();
			//ArrayList<String> ftpFiles = 
			if(openFTPConnection){
				ftpService.downDKFile(localPath, remotePath);//需要处理的垫款文件文件名
				//return "1";
			}else{
				System.out.println("垫款处理连接垫款文件的FTP服务器异常，未能打开服务器FTP连接！");
				//return "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接FTP服务器下载垫款文件异常，异常信息："+e.getMessage());
			//return "2";
			
		}
//		ArrayList<String> ftpFilesLocal = new ArrayList<String>();//需要处理的垫款文件---本地 绝对路径
//		for(String dbFile : ftpFiles){
//			dbFile = localPath+"/"+dbFile;
//			ftpFilesLocal.add(dbFile);
//		}
//		for (String dbFile : ftpFilesLocal) {
//			if(".i".equals((dbFile.substring(dbFile.indexOf("."), dbFile.length())))){//.i文件
//				result = this.execShell(tabName, dbFile, isTruncate);
//				results.add(result);
//			}
//			if(".i.ok".equals((dbFile.substring(dbFile.indexOf("."), dbFile.length())))){//.i.ok文件
//				result = this.execShell("tb_core_diankuan_rec", dbFile, isTruncate);
//				results.add(result);
//			}
//		}
//		// 如果下载文件数量为空，或者转载文件存在失败，则返回2
//		if (results.contains(false) || ftpFiles.size() == 0) {
//			return "2";
//		}
//		return "1";
		
	}
	@Bizlet("装载数据文件")
	//下载垫款文件：核心直接推送垫款文件到本地---下载垫款文件
	public void loadDKFileNew(String operateType) {
		String cfg_group = "ftp_" + operateType.split("_")[2];
		// 核心推送的垫款文件路径
		String remotePathConf = CommonUtil.getDBConfigVal(cfg_group, operateType);
		remotePath = remotePathConf;
		// 需要备份垫款文件的路径
		String cfg_group_local = "ftp_" + operateType.split("_")[2];
		localPath = CommonUtil.getDBConfigVal(cfg_group_local, "diankuan_down_core");
		if (remotePath.endsWith("/")) {
			remotePath = remotePath.substring(0, remotePath.length() - 1);
		}
		if (localPath.endsWith("/")) {
			localPath = localPath.substring(0, localPath.length() - 1);
		}
		LogUtil.logInfo(">>已获取文件配置：核心推送本地路径【" + localPath + "】", null);
		LogUtil.logInfo(">>已获取文件配置：信贷备份本地路径【" + remotePath + "】", null);
		// 本地备份路径要是不存在---创建一个
		File fileBackUp = new File(localPath);
		if (!fileBackUp.exists()) {
			fileBackUp.mkdirs();
		}
		// 返回的有效的垫款文件文件名---即需要备份到本地路径的垫款文件---相同名称以.i和.i.ok同时出现的垫款文件即为有效的垫款文件
		ArrayList<String> list = new ArrayList<String>();
		// 核心推送本地路径文件夹下所有的文件的文件名 例如： 47PDK2017073000001.i
		// 47PDK2017073000001.i.ok
		ArrayList<String> fileNames = new ArrayList<String>();
		// 核心推送本地路径文件夹下的所有文件
		File fileSource = new File(remotePath);
		// 所有核心推送过来的垫款文件
		File files[] = fileSource.listFiles();
		if (null == files || files.length <= 0) {// 没有发现需要处理的垫款文件
			return;
		}
		for (File f : files) {
			String fileName = f.getName();
			fileNames.add(fileName);
		}
		// 垫款文件夹里面没有后缀的文件名 例如： 47PDK2017073000001---后面用来判断是否存在.i和.i.ok用
		ArrayList<String> fileNames1 = new ArrayList<String>();
		if (null != fileNames && fileNames.size() > 1) {// 垫款文件夹里面有文件
			for (String file : fileNames) {
				if (file.contains(".")) {
					file = file.substring(0, file.indexOf("."));
					if (!fileNames1.contains(file)) {
						fileNames1.add(file);
					}
				}
			}
			for (String file : fileNames1) {
				if (fileNames.contains(file + ".i") && fileNames.contains(file + ".i.ok")) {// 完整的垫款文件：有.i也有.i.ok
					list.add(file + ".i");
					list.add(file + ".i.ok");
				}
			}
		}
		if (null == list || list.isEmpty() || list.size() <= 0) {// 没有合法的垫款文件
			return;
		}
		try {
			// 循环拷贝
			for (String regexName : list) {
				// 下载垫款文件
				File  srcDir = new File(remotePath+"/"+regexName);
				File  destDir = new File(localPath+"/"+regexName);
				FileUtils.copyFile(srcDir, destDir);
				srcDir.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("处理垫款文件异常，异常信息：" + e.getMessage());
		}
	}
	
	@Bizlet("装载数据文件")
	//下载垫款文件：通过SFTP连接到存放垫款文件的FTP服务器 下载垫款文件
	public void loadDKFileBySftp(String operateType){
		try {
			//创建垫款FTP连接
			SftpService sftpService = this.initConfigDKSftp(operateType);
			//是否打开连接---是否登录FTP服务器
			boolean openConnection = sftpService.connect();
			if(openConnection){
				//下载垫款文件
				sftpService.downLoadDKFile(localPath, remotePath);
			}else{
				//连接失败
				System.out.println("垫款处理连接垫款文件的FTP服务器异常，未能打开服务器FTP连接！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接FTP服务器下载垫款文件异常，异常信息："+e.getMessage());
		}
	}
	
	//将文件内容写入数据库表同时移除当前文件到已处理文件夹[]下面
	@Bizlet("垫款文件内容写入数据库表")
	public ArrayList<String>  insertDKFile(String operateType) {
		//需要备份垫款文件的路径
		String cfg_group_local = "ftp_" + operateType.split("_")[0];
		String filePath = CommonUtil.getDBConfigVal(cfg_group_local, operateType);
		File file = new File(filePath);//需要处理的垫款文件所在的文件夹
		File[] files = file.listFiles();//需要处理的垫款文件---所有垫款文件
		ArrayList<String> validFiles = new ArrayList<String>();//有效的垫款文件---也就是本次处理的入库的垫款文件
		if(null==files || files.length <= 0){//没有垫款文件
			return validFiles;
		}
			for(File f:files){
				boolean validFile = false;//是否要处理的文件
				//.i和.i.ok文件是需要处理的文件
				validFile = ".i".equals((f.toString().substring(f.toString().indexOf("."), f.toString().length())))||".i.ok".equals((f.toString().substring(f.toString().indexOf("."), f.toString().length())));
				if(validFile){//.i.ok文件
					BufferedReader reader = null;
					FileInputStream fileInputStream = null;
					InputStreamReader inputStreamReader = null;
					validFiles.add(f.getName());
					try {
						fileInputStream = new FileInputStream(f);
						inputStreamReader = new InputStreamReader(fileInputStream,"GB2312");
						reader = new BufferedReader(inputStreamReader);
						String tempString = null;
						int line = 1;
						while((tempString=reader.readLine())!=null){
							System.out.println("line"+line+":"+tempString);//读取出来的内容
							String fgf = String.valueOf((char)0x01);
							String[] detail = tempString.split(fgf);
							if(".i".equals((f.toString().substring(f.toString().indexOf("."), f.toString().length())))){//.i文件
								Map map = new HashMap();
								map.put("seqNum", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
								map.put("tradeDate", detail[0]);//交易日期
								map.put("tradeOrg", detail[1]);//交易机构
								map.put("summaryNum", detail[2]);//借据编号
								map.put("ywbh", detail[3]);//业务编号
								map.put("accagrNum", detail[4]);//协议编号
								map.put("advaccNum", detail[5]);//垫款账号
								map.put("advaccName", detail[6]);//垫款户名
								map.put("advOrg", detail[7]);//垫款机构
								map.put("payaccNum", detail[8]);//还款账号
								map.put("payaccName", detail[9]);//还款户名
								map.put("payaccType", detail[10]);//还款账户类型
								map.put("payOrg", detail[11]);//还款机构
								map.put("advAmt", detail[12]);//垫款金额
								map.put("currency", detail[13]);//垫款金额
								map.put("dealFlag", "1");//处理标识：1未处理 2已处理 3 处理报错
								map.put("msg", "初始化导入数据");//处理信息：初始化导入数据
								map.put("advFileName",f.getName());//垫款文件名
								DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.insertCoreDiankuan", map);
							}
							if(".i.ok".equals((f.toString().substring(f.toString().indexOf("."), f.toString().length())))){//.i文件
								Map map = new HashMap();
								map.put("totNum", detail[0]);//总笔数
								map.put("totAmt", detail[1]);//总金额
								map.put("advFileName", detail[2]);//垫款文件名
								DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.insertCoreDiankuanRec", map);
							}
							line++;
						}
						fileInputStream.close();
						inputStreamReader.close();
						reader.close();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("导入垫款文件内容到数据库表出现异常，异常原因："+e.getMessage());
					}finally{
						if(fileInputStream!=null){
							try {
								fileInputStream.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
						if(inputStreamReader!=null){
							try {
								inputStreamReader.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
						
						if(reader!=null){
							try {
								reader.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
				}
			}
			return validFiles;
	}
	//将已经读取到数据库的垫款文件标记为已处理：后缀名加.done
	@Bizlet("标记已处理的垫款文件")
	public void markDKFile(String operateType,ArrayList<String> validFiles){
		//需要备份垫款文件的路径
		String cfg_group_local = "ftp_" + operateType.split("_")[0];
		String filePath = CommonUtil.getDBConfigVal(cfg_group_local, operateType);
		File file = new File(filePath);//需要处理的垫款文件所在的文件夹
		File[] files = file.listFiles();//需要处理的垫款文件---所有垫款文件
		
		List<File> filesToDo = new ArrayList<File>();//目标垫款文件---即本次需要修改后缀名的垫款文件
		for(File f:files){
			if(validFiles.contains(f.getName())){
				filesToDo.add(f);
			}
		}
		System.gc();
		try {
			Thread.sleep(100);//线程等待100毫秒---因为上一步做入库的时候使用到了流  等待流关闭结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(File f:filesToDo){
			//修改文件后缀 .done表示该文件已经做了插入数据库的处理
			if(f.renameTo(new File(f.toString()+".done"))){
				System.out.println("垫款文件"+f.toString()+"标记已处理成功！");
			}else{
				System.out.println("垫款文件"+f.toString()+"标记已处理失败！");
			}
		}
	}
	@Bizlet("将数据库生成TXT文件")
	public String buildDBFile(String operateType, String sql) throws Exception {
		FtpService ftpService = this.initConfig(operateType);
		this.buildTxtFile2(sql, null, localPath, "|");// 生成文件
		boolean ftpIsCon = ftpService.isOpenFTPConnection();
		if (ftpIsCon) {
			boolean res = ftpService.uploadFile(localPath, remotePath, fileName);// 上传文件
			if (res) {
				return "1";
			} else {
				return "2";
			}
		} else {
			throw new EOSException("FTP服务器连接失败！");
		}
	}
	
	@Bizlet("生成短信txt文件并发送")
	public String buildSMSFile(String operateType, String sql) throws Exception {
		boolean sftpIsUpload;
		// ftp初始化
 		FtpService ftpService = this.initConfig(operateType);
 		
		// 生成文件
		this.buildSMSTxt(sql, null, localPath, "|*|");
		
		// 上传短信文件 
		sftpIsUpload = ftpService.sftpUpload();
		
		// 判断上传
		if (sftpIsUpload) {
			System.out.println("短信文件上传成功!");
			return "1";
		} else {
			throw new EOSException("短信文件上传失败！");
		}
	}

	// 生成短信txt文件(sql, null, localPath, "|*|")
	public File buildSMSTxt(String sql, Map<String, String> params, String fileDir, String separator) throws Exception {
		HashMap<String, String> obj = null;
		String StrCurrDate = null;
		Object[] objs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, sql, params);
		List<String> lines = new ArrayList<String>();
		// 处理报文头日期
		String fName = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.indexOf("."));
		
		// 处理获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StrCurrDate = sdf.format(new Date());
		
		fileName = "07_0700_" + StrCurrDate + ".txt";
		System.out.println("时间: " + fileName);
		
		// 增加txt文件头信息
		lines.add("#" + (objs.length+3) + separator + "0700" + separator + StrCurrDate
				+ separator + GitUtil.getCurrentOrgCd() + separator + "07" + separator + GitUtil.getCurrentUserId() + separator
				+ "0#");
		
		// 第二行固定文件
		lines.add("ADDRESS" + separator + "MSG" + separator + "PAYBRANCH");
		
		// 拼装发送短信的内容
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] instanceof HashMap) {
				obj = (HashMap<String, String>) objs[i];
			}
			int size = obj.size();
			StringBuffer line = new StringBuffer();
			for (int j = 1; j <= size; j++) {
				String value = obj.get("C" + j);
				line.append(value + separator);
			}
			lines.add(line.toString());
		}
		// 增加末尾行数
		lines.add("#" + (objs.length+3) + "#");
		
		// 准备生成短信txt文件
		String localFileName = localPath + File.separator + fileName;
		File file = new File(localFileName);
		
		try{
			// 写入生成的短信对象到txt文件
			FileUtils.writeLines(file, encode, lines);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return file;
	}

	/**
	 * 通过keyId获取value(固定写法 C1,C2....)
	 * @param sql
	 * @param params
	 * @param fileDir
	 * @param separator
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public File buildTxtFile2(String sql, Map<String, String> params, String fileDir, String separator) throws Exception {
		HashMap<String, String> obj = null;
		Object[] objs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, sql, params);
		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] instanceof HashMap) {
				obj = (HashMap<String, String>) objs[i];
			}
			int size = obj.size();
			StringBuffer line = new StringBuffer();
			for (int j = 1; j <= size; j++) {
				String value = obj.get("C" + j);
				line.append(value + separator);
			}
			lines.add(line.toString());
		}
		
		String localFileName = localPath + File.separator + fileName;
		File file = new File(localFileName);
		FileUtils.writeLines(file, encode, lines);
		return file;
	}
	
	/**
	 * 通过keySet获取value
	 * @param sql
	 * @param params
	 * @param fileDir
	 * @param separator
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public File buildTxtFile(String sql, Map<String, String> params, String fileDir, String separator) throws Exception {
		HashMap<String, String> obj = null;
		Object[] objs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, sql, params);
		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] instanceof HashMap) {
				obj = (HashMap<String, String>) objs[i];
			}
			Set<String> keys = obj.keySet();
			Iterator<String> it = keys.iterator();
			String key = null;
			StringBuffer line = new StringBuffer();
			while (it.hasNext()) {
				key = it.next();
				String value = obj.get(key);
				line.append(value + separator);
			}
			lines.add(line.toString());
		}

		String localFileName = localPath + File.separator + fileName;
		File file = new File(localFileName);
		FileUtils.writeLines(file, encode, lines);
		return file;
	}

	/**
	 * 国结系统 -借据表 国结系统 -流水表tb_loan_summary_flowdetail 核心系统- 垫款文件tb_loan_diankuan
	 * 核心系统 -贷款户主文件dkhzwj
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void resolveFile(File fileName) throws IOException {
		List<String> lines = FileUtils.readLines(fileName, "UTF-8");

		ArrayList<DataObject> list = new ArrayList<DataObject>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] vals = line.split("\\|");
			list.add(setTxtField(vals));
		}
		DataObject[] objs = new DataObject[list.size()];
		for (int i = 0; i < list.size(); i++) {
			objs[i] = list.get(i);
		}
		DatabaseUtil.insertEntityBatch(GitUtil.DEFAULT_DS_NAME, objs);
	}
	
	
	@Bizlet("垫款出账/放款")
	public void delwithDK() throws Exception {
		LoanToLcs ll = new LoanToLcs();
		Map<String, String> map = new HashMap<String, String>();
		map.put("tradeDate", GitUtil.getBusiDateYYYYMMDD());//当日垫款信息
		//查询垫款文件信息表
		Object[] tbCoreDiankan = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTbCoreDiankuan", map);
		if(null ==tbCoreDiankan || tbCoreDiankan.length==0){
			return;
		}
		//循环处理垫款
		for(int i = 0;i < tbCoreDiankan.length;i++){
			Map  temp = (Map)tbCoreDiankan[i];
			Map<String,String> map2 = new HashMap<String,String>();//更新垫款信息表参数集
			
			//垫款出账之前先检查文件--校验文件的正确性
			String advfilename = temp.get("ADVFILENAME")+"";
			int num = DatabaseExt.countByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.checkDKData", advfilename);
			if(num>0){
				map2.put("seqNum", temp.get("SEQNUM")+"");
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "垫款文件数据校验失败，"+advfilename+".ok文件的总笔数/总金额与"+advfilename+"文件统计的总笔数/总金额不一致，请检查相关文件！");
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}
			//目前一个借据只能做一次垫款---暂时做一个校验
			//一个借据 只能有一个状态为处理成功的记录
			String summaryNum = temp.get("SUMMARY_NUM")+"";
			int num1 = DatabaseExt.countByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.checkDKDataRepeatDk", summaryNum);
			if(num1>0){
				map2.put("seqNum", temp.get("SEQNUM")+"");
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "垫款文件数据校验失败，借据"+summaryNum+"已经做过垫款处理，不能重复垫款");
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}
			//查询老借据信息
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryNum", temp.get("SUMMARY_NUM"));
			DatabaseUtil.expandEntityByTemplate("default", summary, summary);
			if(null==summary.getString("summaryId")||"".equals(summary.getString("summaryId"))){
				map2.put("seqNum", temp.get("SEQNUM")+"");
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "未获取到对应垫款借据号"+temp.get("SUMMARY_NUM")+"对应的借据信息，请检查！");
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}
			String loanId = summary.getString("loanId");//放款id
			//String summaryNum = summary.getString("summaryNum");//借据编号
			String tradeOrg = temp.get("TRADEORG")+"";//交易机构
			String advOrg = temp.get("ADVORG")+"";//垫款机构
			BigDecimal dkamt = (BigDecimal)temp.get("ADVAMT");//垫款金额
			String seqNum = temp.get("SEQNUM")+"";//垫款信息表主键编号
			map2.put("seqNum", seqNum);
			try {
				//垫款出账
				ll.dataInsertDkMccb(loanId, summaryNum, tradeOrg,temp);
			} catch (Exception e) {
				//修改垫款信息表状态
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "垫款调用出账时错误："+e.getMessage());
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}
			
			try {
				//垫款出账检查
				ll.loanToLcs1(summaryNum, summaryNum, advOrg);
			} catch (Exception e) {
				//修改垫款信息表状态
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "垫款出账检查时错误："+e.getMessage());
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}
			
			
			//垫款放款
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());//对账流水号
			Map<String,String> ap = null;
			try {
				//垫款放款
				 ap = ll.payLoanToAplus(summaryNum, summaryNum, advOrg, lcsStan,"","1");
			} catch (Exception e) {
				//修改垫款信息表状态
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "垫款放款时错误："+e.getMessage());
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}
			
			if(!"00000".equals(ap.get("resCode"))){
				//修改垫款信息表状态
				map2.put("dealFlag", "3");//处理失败
				map2.put("msg", "垫款放款时错误："+ap.get("msg"));
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				continue;
			}else{
				//票据垫款只会出现一次---垫款金额就是要还的金额也就是已用金额---借据余额就是垫款
				//但是国结可以垫款几次---每次垫款都有金额  多次垫款 不能直接更新借据余额就是垫款金额---要累加
				
				//注：本次上线---核算只接受一个借据一次放款  也就是一个借据一次垫款 后期需要做改造
				//放款成功后更新借据信息
				//这个地方需要确认
				//summary.set("orgNum", tradeOrg);//经办机构
				
				summary.set("beginDate", summary.get("endDate"));
				//String status = summary.getString("summaryStatusCd");
				//if(DictContents.XD_SXYW0226_03.equals(status)){//状态---本身就是逾期垫款的---借据余额累加---表示已经做过垫款
					//BigDecimal jjye = summary.getBigDecimal("jjye");
					//jjye = jjye.add(dkamt);
				//}
				summary.set("jjye", dkamt);
				summary.set("rcnStan", lcsStan);
				summary.set("summaryStatusCd", DictContents.XD_SXYW0226_03);
				summary.set("fljg","0203");//关注3
				summary.set("nftNo", summaryNum);
				DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, summary);
				//修改垫款信息表状态
				map2.put("dealFlag", "2");//处理成功
				map2.put("msg", "处理成功");//处理成功
				DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.updateCoreDiankuan", map2);
				//同步借据
				DealAccount.singleSynch(summaryNum);
				
				// 调用额度重算
				String partyId = summary.getString("partyId");
				Map map1 = new HashMap();
				map1.put("partyId", partyId);
				DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map1);
			}
		}
	}
	
	/**
	 * 初始化配置
	 * @param operateType
	 * @return
	 * @throws Exception
	 */
	public FtpService initConfig(String operateType) throws Exception {
		// 获取FTP配置信息
		localPath = CommonUtil.getDBConfigVal("ftp_common", "localPath");
		String waitTime = CommonUtil.getDBConfigVal("ftp_common", "waitTime");
		
		String cfg_group = "ftp_" + operateType.split("_")[0];
		
		String server = CommonUtil.getDBConfigVal(cfg_group, "host");
		String port = CommonUtil.getDBConfigVal(cfg_group, "port");
		String userName = CommonUtil.getDBConfigVal(cfg_group, "username");
		String password = CommonUtil.getDBConfigVal(cfg_group, "password");
		String syskey = CommonUtil.getDBConfigVal(cfg_group, "syskey");
		
		String remotePathConf = CommonUtil.getDBConfigVal(cfg_group, operateType);
		encode = CommonUtil.getDBConfigVal(cfg_group, operateType,"OTHER1");
		boolean isCheckOkFile = Boolean.parseBoolean(CommonUtil.getDBConfigVal(cfg_group, operateType,"OTHER2"));
		
		// 配置shell数据装载
		if ("core_down_hpdjb".equals(operateType)) {// 核心-汇票登记簿文件-下载
			isTruncate = true;
			tabName = "hpdjb";
		} else if ("core_down_dzhpdjb".equals(operateType)) {// 核心-电子汇票登记簿文件-下载
			isTruncate = true;
			tabName = "dzhpdjb";
		} else if ("core_down_dkhzwj".equals(operateType)) {// 核心-贷款户主文件-下载
			isTruncate = true;
			tabName = "dkhzwj";
		} else if ("core_down_diankuan".equals(operateType)) {// 核心-垫款文件-下载
			isTruncate = false;
			tabName = "tb_core_diankuan";
		} else if ("gjyw_down_loan".equals(operateType)) {// 国结业务-借据文件-下载
			isTruncate = false;
			tabName = "tt_gjyw_loan";
		} else if ("gjyw_down_loanflow".equals(operateType)) {// 国结业务-借据流水文件-下载
			isTruncate = false;
			tabName = "tt_gjyw_loanflow";
		} else if ("gjyw_up_cls".equals(operateType)) {// 国结业务-五级分类-上传
			
		} else if ("dxpt_up_sms".equals(operateType)) {// 短信文件-上传

		} else if ("weixin_down_loanapply".equals(operateType)) {// 微信银行-业务申请文件-下载
			isTruncate = false;
			tabName = "tt_wx_loan_apply";
		}
		
		// YYYYMMDD、YYYYMMDDHHMMSS日期格式变量
		String dateStr = GitUtil.getBusiDateYYYYMMDD();//8位日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String time = sdf.format(Calendar.getInstance().getTime());
		String dateTimeStr = dateStr + time;//14位日期格式
		
		// 处理文件路径
		// 处理远程文件路径
		remotePath = remotePathConf.substring(0, remotePathConf.lastIndexOf("/") + 1);
		if (!remotePath.endsWith("/")) {
			remotePath += "/";
		}
		remotePath = remotePath.replace("{YYYYMMDD}", dateStr);
		// 处理本地文件路径
		if (!localPath.endsWith("/")) {
			localPath += "/";
		}
		String rootPath = SystemInfo.APP_WAR_PATH.substring(0, 2);
		if (rootPath.contains(":")) {
			localPath = rootPath + localPath + operateType + "/";
		} else {
			localPath = localPath + operateType + "/";
		}
		localPath = localPath + dateStr + "/";
		
		// 处理文件名
		fileName = remotePathConf.substring(remotePathConf.lastIndexOf("/") + 1);
		fileName = fileName.replace("{YYYYMMDD}", dateStr);
		fileName = fileName.replace("{YYYYMMDDHHMMSS}", dateTimeStr);
		
		LogUtil.logInfo("", null);
		LogUtil.logInfo(">>已获取FTP配置：入库表名【" + tabName + "】", null);
		LogUtil.logInfo(">>已获取FTP配置：本地路径【" + localPath + "】", null);
		LogUtil.logInfo(">>已获取FTP配置：远程路径【" + remotePath + "】", null);

		// 创建FTP实例
		FtpService ftpService = new FtpService(server, Integer.parseInt(port), userName, password);
		ftpService.setWait(true);
		ftpService.setIswaitOkFile(isCheckOkFile);
		ftpService.setWaitTime(Long.parseLong(waitTime));
		ftpService.setSystemKey(syskey);
		if ("core_down_diankuan".equals(operateType)) {// 核心-垫款文件-已下载不再重复下载
			ftpService.setRepeat(false);
			ftpService.setWait(false);
		}
		return ftpService;
	}
	
	/**
	 * 初始化配置(垫款)---通过FTP协议
	 * @param operateType
	 * @return
	 * @throws Exception
	 */
	public FtpService initConfigDK(String operateType) throws Exception {
		//获取FTP配置信息
		//文件等待时间
		//String waitTime = CommonUtil.getDBConfigVal("ftp_common", "waitTime");
		//路径参数---核心存放垫款文件的路径
		String cfg_group = "ftp_" + operateType.split("_")[0];
		String remotePathConf = CommonUtil.getDBConfigVal(cfg_group, operateType);
		remotePath = remotePathConf;
		//登录参数---IP 端口 用户 用户名 密码
		String server = CommonUtil.getDBConfigVal(cfg_group, "host");//IP
		String port = CommonUtil.getDBConfigVal(cfg_group, "port");//端口
		String userName = CommonUtil.getDBConfigVal(cfg_group, "username");//登录人
		String password = CommonUtil.getDBConfigVal(cfg_group, "password");//登录密码
		String syskey = CommonUtil.getDBConfigVal(cfg_group, "syskey");//FTP系统类型
		encode = CommonUtil.getDBConfigVal(cfg_group, operateType,"OTHER1");//字符集
		//boolean isCheckOkFile = Boolean.parseBoolean(CommonUtil.getDBConfigVal(cfg_group, operateType,"OTHER2"));//是否检查OK文件
		//需要备份垫款文件的路径
		String cfg_group_local = "ftp_" + operateType.split("_")[2];
		localPath = CommonUtil.getDBConfigVal(cfg_group_local, "diankuan_down_core");
		// 核心-垫款文件-下载
		isTruncate = false;
		tabName = "tb_core_diankuan";
		// 处理文件路径---相对路径--而且前后不要/
		if(remotePathConf.startsWith("/")){
			remotePath = remotePath.substring(1, remotePath.length());
		}
		if (remotePath.endsWith("/")) {
			remotePath = remotePath.substring(0, remotePath.length()-1);
		}
		if(localPath.startsWith("/")){
			localPath = localPath.substring(1, localPath.length());
		}
		if (localPath.endsWith("/")) {
			localPath = localPath.substring(0, localPath.length()-1);
		}
		LogUtil.logInfo(">>已获取FTP配置：本地路径【" + localPath + "】", null);
		LogUtil.logInfo(">>已获取FTP配置：远程路径【" + remotePath + "】", null);
		
		// 创建FTP实例
		FtpService ftpService = new FtpService(server, Integer.parseInt(port), userName, password);
		ftpService.setWait(false);//垫款文件的获取---执行一次 就可以了  定时器会循环执行 不需要在处理中不停的等待
		//ftpService.setIswaitOkFile(isCheckOkFile);//是否检查OK类型的文件
		//ftpService.setWaitTime(Long.parseLong(waitTime));//等待时间
		ftpService.setSystemKey(syskey);//FTP系统类型
		//if ("core_down_diankuan".equals(operateType)) {// 核心-垫款文件-已下载不再重复下载
			//ftpService.setRepeat(false);
			//ftpService.setWait(false);
		//}
		return ftpService;
	}
	
	/**
	 * 初始化配置(垫款)---通过SFTP协议
	 * @param operateType
	 * @return
	 * @throws Exception
	 */
	public SftpService initConfigDKSftp(String operateType) throws Exception {
		//路径参数---核心存放垫款文件的路径
		String cfg_group = "ftp_" + operateType.split("_")[2];
		String remotePathConf = CommonUtil.getDBConfigVal(cfg_group, operateType);
		remotePath = remotePathConf;
		//登录参数---IP 端口 用户 用户名 密码
		String server = CommonUtil.getDBConfigVal(cfg_group, "host");//IP
		String port = CommonUtil.getDBConfigVal(cfg_group, "port");//端口
		String userName = CommonUtil.getDBConfigVal(cfg_group, "username");//登录人
		String password = CommonUtil.getDBConfigVal(cfg_group, "password");//登录密码
		encode = CommonUtil.getDBConfigVal(cfg_group, operateType,"OTHER1");//字符集
		//需要备份垫款文件的路径
		String cfg_group_local = "ftp_" + operateType.split("_")[2];
		localPath = CommonUtil.getDBConfigVal(cfg_group_local, "diankuan_down_core");
		// 处理文件路径--而且前后不要/
		if(remotePathConf.startsWith("/")){
			remotePath = remotePath.substring(1, remotePath.length());
		}
		if (remotePath.endsWith("/")) {
			remotePath = remotePath.substring(0, remotePath.length()-1);
		}
		//if(localPath.startsWith("/")){
			//localPath = localPath.substring(1, localPath.length());
		//}
		if (localPath.endsWith("/")) {
			localPath = localPath.substring(0, localPath.length()-1);
		}
		LogUtil.logInfo(">>已获取FTP配置：本地路径【" + localPath + "】", null);
		LogUtil.logInfo(">>已获取FTP配置：远程路径【" + remotePath + "】", null);
		
		// 创建FTP实例
		SftpService sftpService = new SftpService(server, Integer.parseInt(port), userName, password);
		return sftpService;
	}
	@Bizlet("Shell脚本装载数据")
	public boolean execShell(String tabName, String fileName, boolean isTruc) throws EOSException, IOException {
		String shellPath = ConfigurationUtil.getUserConfigSingleValue("CustomConfig", "System", "shellPath");
		if(!shellPath.endsWith("/")){
			shellPath += "/";
		}
		String shPath = shellPath + "dbload.sh";
		String shell = "sh " + shPath + " -t " + tabName + " -d " + localPath + " -f " + fileName;
		if (!isTruc) {
			shell += " -a";
		}
		System.out.println("拼装shell:"+shell+", 时间:"+GitUtil.getBusiTimestamp());
		return CommonUtil.execShell(shell);
	}

	
	/**
	 * JAVA装载数据（暂不用）
	 * @param vals
	 * @return
	 */
	private DataObject setTxtField(String[] vals) {
		DataObject obj = null;
		int len = vals.length;
		if ("gjyw_loan".equals(systemId)) {
			obj = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			if (vals.length == 10) {
				obj.setString("summaryNum", vals[0]);// 借据编号
				obj.setString("partyId", vals[1]);// ECIF客户编号
				obj.setString("summaryStatusCd", vals[2]);// 借据状态
				obj.setBigDecimal("jjye", new BigDecimal(vals[3]));// 借据余额
				obj.setInt("yqts", Integer.parseInt(vals[4]));// 逾期天数
				obj.setBigDecimal("dftItr", new BigDecimal(vals[5]));// 累计欠息
				obj.setBigDecimal("dftItrIn", new BigDecimal(vals[6]));// 表内欠息
				obj.setBigDecimal("dftItrOut", new BigDecimal(vals[7]));// 表外欠息
				obj.setInt("ljyqcs", Integer.parseInt(vals[8]));// 累计逾期次数
				obj.setInt("lxyqcs", Integer.parseInt(vals[9]));// 连续逾期次数
			}
		} else if ("gjyw_loanflow".equals(systemId)) {
			obj = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			if (vals.length > 10) {
				obj.setString("summaryNum", vals[0]);// 借据编号
				obj.setString("partyId", vals[1]);// ECIF客户编号
				obj.setString("summaryStatusCd", vals[2]);// 本期起期
				obj.setBigDecimal("jjye", new BigDecimal(vals[3]));// 本期止期
				obj.setInt("yqts", Integer.parseInt(vals[4]));// 期次
				obj.setBigDecimal("dftItr", new BigDecimal(vals[5]));// 应还本金
				obj.setBigDecimal("dftItrIn", new BigDecimal(vals[6]));// 实还本金
				obj.setBigDecimal("dftItrOut", new BigDecimal(vals[7]));// 应还利息
				obj.setInt("ljyqcs", Integer.parseInt(vals[8]));// 实还利息
				obj.setInt("lxyqcs", Integer.parseInt(vals[9]));// 实际还款日
				obj.setInt("lxyqcs", Integer.parseInt(vals[9]));// 剩余本金
			}
		} else if ("core_daikuanhu".equals(systemId)) {
			obj = DataObjectUtil.createDataObject("com.bos.dataset.pay.Dkhzwj");
			if (len > 10) {
				obj.setString("zhdh", vals[0]);// 帐户代号
				obj.setString("dqdh", vals[1]);// 地区代号
				obj.setString("jgdh", vals[2]);// 机构代号
				obj.setString("hbzl", vals[3]);// 货币种类
				obj.setString("ywdh", vals[4]);// 业务代号
				obj.setString("zhxh", vals[5]);// 帐号序号
				obj.setString("jcw", vals[6]);// 检查位
				obj.setString("tctdbj", vals[7]);// 通存通兑标记
				obj.setString("khmc", vals[8]);// 客户名称
				obj.setString("khdh", vals[9]);// 客户代号
				obj.setString("kmdh", vals[10]);// 科目代号
				obj.setString("flbz", vals[11]);// 分类标志0
				obj.setString("dkfs", vals[12]);// 贷款方式
				obj.setString("hdlb", vals[13]);// 还贷类别
				obj.setString("yexz", vals[14]);// 余额性质
				obj.setBigDecimal("srye", new BigDecimal(vals[15]));// 上日余额
				obj.setBigDecimal("zhye", new BigDecimal(vals[16]));// 帐户余额
				obj.setBigDecimal("fxl", new BigDecimal(vals[17]));// 罚息率
				obj.setString("lldh", vals[18]);// 利率代号
				obj.setString("jxbz", vals[19]);// 计息标志
				obj.setBigDecimal("fdll", new BigDecimal(vals[20]));// 浮动利率
				obj.setDate("qsrq", DateUtil.convertString2Date(vals[21], "yyyy/mm/dd"));// 起始日期
				obj.setDate("llsxrq", DateUtil.convertString2Date(vals[22], "yyyy/mm/dd"));// 利率生效日期
				obj.setDate("khrq", DateUtil.convertString2Date(vals[23], "yyyy/mm/dd"));// 开户日期
				obj.setString("khgy", vals[24]);// 开户柜员
				obj.setBigDecimal("khje", new BigDecimal(vals[25]));// 开户金额
				obj.setDate("jyrq", DateUtil.convertString2Date(vals[26], "yyyy/mm/dd"));// 交易日期
				obj.setString("jygy", vals[27]);// 交易柜员
				obj.setDate("schkrq", DateUtil.convertString2Date(vals[28], "yyyy/mm/dd"));// 上次还款日期
				obj.setDate("scdzrq", DateUtil.convertString2Date(vals[29], "yyyy/mm/dd"));// 上次对帐日期
				obj.setString("scdzls", vals[30]);// 上次对帐流水号
				obj.setString("htbh", vals[31]);// 合同编号
				obj.setString("jjh", vals[32]);// 借据号
				obj.setString("fxzl", vals[33]);// 五级分类
				obj.setDate("fxflrq", DateUtil.convertString2Date(vals[34], "yyyy/mm/dd"));// 分类日期
				obj.setBigDecimal("kdgcz", new BigDecimal(vals[35]));// 可抵公允值
				obj.setBigDecimal("ycxs", new BigDecimal(vals[36]));// 预测损失
				obj.setString("jxhjbz", vals[37]);// 借新还旧标志
				obj.setString("edhth", vals[38]);// 额度合同号
				obj.setDate("sjyrq", DateUtil.convertString2Date(vals[39], "yyyy/mm/dd"));// 上交易日期
				obj.setBigDecimal("hted", new BigDecimal(vals[40]));// 合同额度
				obj.setBigDecimal("dked", new BigDecimal(vals[41]));// 贷款额度
				obj.setDate("dqrq", DateUtil.convertString2Date(vals[42], "yyyy/mm/dd"));// 到期日期
				obj.setString("hpzl", vals[43]);// 汇票种类
				obj.setBigDecimal("ljjs", new BigDecimal(vals[44]));// 累计积数
				obj.setBigDecimal("yjyjjs", new BigDecimal(vals[45]));// 应加应减积数
				obj.setString("xgzh", vals[46]);// 相关帐号
				obj.setString("zdzyq", vals[47]);// 自动转逾期标志
				obj.setString("dkxz", vals[48]);// 贷款性质
				obj.setString("qxzh", vals[49]);// 欠息帐号
				obj.setString("jszh", vals[50]);// 结算帐号
				obj.setString("kxzh", vals[51]);// 扣息帐号
				obj.setLong("fdjxxh", Long.parseLong(vals[52]));// 分段计息序号
				obj.setString("jxzq", vals[53]);// 计息周期
				obj.setBigDecimal("fxxs", new BigDecimal(vals[54]));// 系数
				obj.setString("llfdfs", vals[55]);// 利率方式
				obj.setString("yjbj", vals[56]);// 应计贷款标记
				obj.setDate("gxrq", DateUtil.convertString2Date(vals[57], "yyyy/mm/dd"));// 更新日期
				obj.setString("jlzt", vals[58]);// 记录状态
			}
		} else if ("core_diankuan".equals(systemId)) {
			obj = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			if (vals.length == 7) {
				obj.setDate("tradeDate", DateUtil.convertString2Date(vals[0], "yyyymmdd"));// 交易日期
				obj.setString("tradeArea", vals[1]);// 交易地区
				obj.setString("tradeOrg", vals[2]);// 交易机构
				obj.setString("contractNum", vals[3]);// 合同编号
				obj.setString("billNum", vals[4]);// 票据号码
				obj.setString("advAccNum", vals[5]);// 垫款账号
				obj.setBigDecimal("advAmt", new BigDecimal(vals[6]));// 垫款金额
			}
		} else if ("core_huipiao".equals(systemId)) {// 核心-汇票登记薄
			obj = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			if (vals.length == 29) {
				obj.setString("dqdh", vals[0]);// 交易日期
				obj.setString("zhdh", vals[1]);// 交易地区
				obj.setString("pzdh", vals[2]);// 交易机构
				obj.setString("hpfw", vals[3]);// 合同编号
				obj.setBigDecimal("hpje", new BigDecimal(vals[4]));// DECIMAL(13,2)
				obj.setBigDecimal("sjje", new BigDecimal(vals[5]));// DECIMAL(13,2)
				obj.setDate("zqrq", DateUtil.convertString2Date(vals[6], "yyyymmdd"));// DATE
				obj.setString("xzbz", vals[7]);// CHAR(1) NOT NULL,
				obj.setString("jgdh", vals[8]);// CHAR(3) NOT NULL,
				obj.setString("hbzl", vals[9]);// CHAR(2) NOT NULL,
				obj.setString("hpsfbz", vals[10]);// CHAR(1) NOT NULL,
				obj.setDate("hpqfrq", DateUtil.convertString2Date(vals[11], "yyyymmdd"));// DATE
				obj.setString("hkrxm", vals[12]);// CHAR(50) NOT NULL,
				obj.setString("skdwmc", vals[13]);// CHAR(50) NOT NULL,
				obj.setString("yskdwm", vals[14]);// CHAR(50) NOT NULL,
				obj.setString("skzh", vals[15]);// CHAR(32) NOT NULL,
				obj.setDate("dqrq", DateUtil.convertString2Date(vals[16], "yyyymmdd"));// DATE
				obj.setString("gylsh", vals[17]);// CHAR(8) NOT NULL,
				obj.setString("xzxh", vals[18]);// CHAR(10) NOT NULL,
				obj.setString("hpmy", vals[19]);// CHAR(8) NOT NULL,
				obj.setString("jygy", vals[20]);// CHAR(4) NOT NULL,
				obj.setString("xgzh", vals[21]);// CHAR(18) NOT NULL,
				obj.setString("dfhh", vals[22]);// CHAR(10) NOT NULL,
				obj.setString("hkyt", vals[23]);// CHAR(30) NOT NULL,
				obj.setString("dfdz", vals[24]);// CHAR(30) NOT NULL,
				obj.setString("hxgy", vals[25]);// CHAR(4) NOT NULL,
				obj.setString("hxgyls", vals[26]);// CHAR(8) NOT NULL,
				obj.setString("htbh", vals[27]);// CHAR(14) NOT NULL,
				obj.setString("jlzt", vals[28]);// CHAR(1) NOT NULL
			}
		}

		return obj;
	}
	
	@Bizlet("装载下载装载垫款数据文件")
	public String loadDKFile(String operateType) throws Exception {
		boolean result = true;
		// 实例化ftp配置信息
		FtpService ftpService = this.initConfig(operateType);
		// 检查FTP服务器是否关闭 
		ftpService.isOpenFTPConnection();
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		
		// (传入本地路径、远程路径、文件名，下载文件)
		System.out.println("---- 准备开始下载loadDKFile ----"+GitUtil.getBusiTimestamp());
		ArrayList<String> ftpFiles = ftpService.downDKFile(localPath, remotePath);
		System.out.println("---- 数据文件下载完成loadDKFile ----"+GitUtil.getBusiTimestamp());
		
		System.out.println("---- 准备开始入库loadDKFile ----"+GitUtil.getBusiTimestamp());
		for (String dbFile : ftpFiles) {
			// 调用shell文件入库(传入表名称,数据文件名称,)
			System.out.println("入库表名:"+tabName+", 入库文件:"+dbFile+", isTruncate:"+isTruncate
					+", start: "+GitUtil.getBusiTimestamp());
			result = this.execShell(tabName, dbFile, isTruncate);
			results.add(result);
		}
		System.out.println("---- 入库完成loadDKFile ----"+GitUtil.getBusiTimestamp());
		
		// 如果下载文件数量为空，或者转载文件存在失败，则返回2
		if (results.contains(false) || ftpFiles.size() == 0) {
			return "2";
		}
		return "1";
	}
	
	//票据状态更新  票据系统生成更新文件---信贷接收文件后入库到数据库表
	@Bizlet("票据文件内容写入数据库表")
	public String insertHuiPiaoFile(String operateType) {
		// 需要入库的票据文件的本地路径
		String cfg_group_local = "ftp_" + operateType.split("_")[2];
		String filePath = CommonUtil.getDBConfigVal(cfg_group_local, operateType);
		String date = GitUtil.getBusiDateYYYYMMDD();
		filePath = filePath.substring(0, filePath.lastIndexOf("/"))+ "/" + date;

		File file = new File(filePath);// 需要处理的票据文件所在的文件夹
		File[] files = file.listFiles();// 需要处理的票据文件---所有票据文件
		if(null==files || files.length <= 0){//没有票据文件
			return "1";
		}
		for (File f : files) {
				if(f.getName().endsWith(".ok")){
					continue;
				}
				BufferedReader reader = null;
				FileInputStream fileInputStream = null;
				InputStreamReader inputStreamReader = null;
				try {
					fileInputStream = new FileInputStream(f);
					//inputStreamReader = new InputStreamReader(fileInputStream, "GB2312");
					inputStreamReader = new InputStreamReader(fileInputStream);
					reader = new BufferedReader(inputStreamReader);
					String tempString = null;
					int line = 1;
					while ((tempString = reader.readLine()) != null) {
						System.out.println("line" + line + ":" + tempString);// 读取出来的内容
						String splitStr = "\\|";
						String[] detail = tempString.split(splitStr);
						Map map = new HashMap();
						map.put("billNo", detail[0]);
						map.put("billStatus", detail[1]);
						map.put("billAmt", detail[2]);
						map.put("productType", detail[3]);
						map.put("contractNo", detail[4]);
						map.put("contractStatus", detail[5]);
						map.put("summaryId", detail[6]);
						map.put("createTime", GitUtil.getBusiDate());
						map.put("fileName", f.getName());
						DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.insertHuiPiaoData", map);
						line++;
					}
					fileInputStream.close();
					inputStreamReader.close();
					reader.close();
				} catch (Exception e) {
					throw new RuntimeException("导入票据文件内容到数据库表出现异常，异常原因：" + e.getMessage());
				} finally {
					if (fileInputStream != null) {
						try {
							fileInputStream.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					if (inputStreamReader != null) {
						try {
							inputStreamReader.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					if (reader != null) {
						try {
							reader.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
			return "1";
		}
	
	//ECIF客户归并文件  ECIF系统生成归并文件---信贷接收文件后入库到数据库表
	@Bizlet("ECIF文件内容写入数据库表")
	public String insertEcifFile(String operateType) {
		// 需要入库的客户归并文件的本地路径
		String cfg_group_local = "ftp_" + operateType.split("_")[2];
		String filePath = CommonUtil.getDBConfigVal(cfg_group_local, operateType);
		String date = GitUtil.getBusiDateYYYYMMDD();
		filePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + date;

		File file = new File(filePath);// 需要处理的客户归并文件所在的文件夹
		File[] files = file.listFiles();// 需要处理的客户归并文件---所有客户归并文件
		if(null==files || files.length <= 0){//没有客户归并文件
			return "1";
		}
		for (File f : files) {
			BufferedReader reader = null;
			FileInputStream fileInputStream = null;
			InputStreamReader inputStreamReader = null;
			try {
				fileInputStream = new FileInputStream(f);
				inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
				//inputStreamReader = new InputStreamReader(fileInputStream);
				reader = new BufferedReader(inputStreamReader);
				String tempString = null;
				int line = 1;
				while ((tempString = reader.readLine()) != null) {
					System.out.println("line" + line + ":" + tempString);// 读取出来的内容
					String splitStr = "\\|\\+\\|";
					String[] detail = tempString.split(splitStr,-1);
					//String fgf = String.valueOf((char)0x01);
					//String[] detail = tempString.split(fgf);
					if (".dat".equals((f.toString().substring(f.toString().indexOf("."), f.toString().length())))){
						Map map = new HashMap();
						map.put("custType", detail[0]);
						map.put("oldPartyId", detail[1]);
						map.put("oldEcifCustNo", detail[2]);
						map.put("newPartyId", detail[3]);
						map.put("newEcifCustNo", detail[4]);
						map.put("partyName", detail[5]);
						map.put("certType", detail[6]);
						map.put("certNo", detail[7]);
						map.put("status", detail[8]);
						map.put("createTime", detail[9]);
						map.put("mergedOrg", detail[10]);
						map.put("mergedEmp", detail[11]);
						map.put("mergedAuthEmp", detail[12]);
						map.put("fileName", f.getName());
						DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.payInfo.getJlData.insertECIFData", map);
						line++;
					}
				}
				fileInputStream.close();
				inputStreamReader.close();
				reader.close();
			} catch (Exception e) {
				throw new RuntimeException("导入客户归并文件内容到数据库表出现异常，异常原因：" + e.getMessage());
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		return "1";
	}
}
