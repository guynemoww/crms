/**
 * 
 */
package com.bos.pub.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.bos.batch.CommonUtil;
import com.bos.pub.GitUtil;
import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;

/**
 * @author CHJ
 * @date 2015-09-24 15:51:28
 * 
 */
@Bizlet("FTP客户端")
public class FtpService {
	private String server;
	private String userName;
	private String password;
	private int port;

	// 设置传输文件的类型[文本文件或者二进制文件]
	// FTPClient.BINARY_FILE_TYPE,
	// FTPClient.ASCII_FILE_TYPE
	private String systemKey;
	private boolean isCheckOkFile;// 是否等待标识文件
	private String okFileExtName;// 标识文件扩展名
	private String okFileName;// 标识文件扩展名
	private boolean isWait;// 是否等待文件
	private long waitTime;// 等待时间（秒）
	private boolean isRepeat;// 是否重复下载
	private FTPClient ftpClient;

	private String localPath;
	private String remotePath;
	private String fileName;
	private String localFullFileName;
	private String remoteFullFileName;
	private String operateType;
	
	// sftp变量
	private ChannelSftp sftp = null;
	private String host="172.16.221.85";
	private String username="was";
	private String pwd="was";
	private final String seperator = "/";

	public boolean ftpLogin() throws IOException {
		boolean isLogin = false;
		FTPClientConfig conf = new FTPClientConfig("com.bos.pub.ftp.UnixFTPEntryParser");
		ftpClient.configure(conf);
		ftpClient.setDefaultPort(port);
		ftpClient.connect(server, port);
		ftpClient.enterLocalPassiveMode();// 修改ftp连接模式，被动模式
		ftpClient.login(userName, password);
		int reply = ftpClient.getReplyCode();// 返回值230为失败
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			LogUtil.logInfo("FTP服务器【" + server + "】【" + port + "】登录失败!", null);
			return isLogin;
		}
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		LogUtil.logInfo("FTP服务器【" + server + "】【" + port + "】登录成功!", null);
		isLogin = true;
		return isLogin;
	}

	public FtpService(String server, int port, String userName, String password) {
		this.server = server;
		this.port = port;
		this.userName = userName;
		this.password = password;
		systemKey = FTPClientConfig.SYST_UNIX;// 默认UNIX
		isCheckOkFile = false;// 是否等待标识文件,默认否
		okFileExtName = "ok";// 标识文件扩展名,默认ok
		isWait = true;// 是否等待文件,默认等待
		waitTime = 5;// 等待时间（秒），默认3秒
		isRepeat = true;// 默认可以重复下载
		this.ftpClient = new FTPClient();
	}

	/**
	 * 检查FTP服务器是否关闭 ，如果关闭接则连接登录FTP
	 * 
	 * @return
	 * @throws IOException 
	 */
	public boolean isOpenFTPConnection() throws Exception {
		boolean isOpen = false;
		// 没有连接
		if (!this.ftpClient.isConnected()) {
			isOpen = this.ftpLogin();
		}
		if(!isOpen){
			LogUtil.logError("FTP服务器【" + server + "】:【" + port + "】连接登录异常!", null);
			throw new EOSException("FTP服务器【" + server + "】:【" + port + "】连接登录异常!");
		}
		return isOpen;
	}

	/**
	 * 退出并关闭FTP连接
	 * 
	 */
	public void close() {
		if (null != this.ftpClient && this.ftpClient.isConnected()) {
			try {
				boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
				if (reuslt) {
					LogUtil.logInfo("退出并关闭FTP服务器的连接", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.logError("退出FTP服务器异常！", null);
				LogUtil.logError(e.getMessage(), null);
			} finally {
				try {
					this.ftpClient.disconnect();// 关闭FTP服务器的连接
				} catch (Exception e) {
					e.printStackTrace();
					LogUtil.logError("关闭FTP服务器的连接异常！", null);
					LogUtil.logError(e.getMessage(), null);
				}
			}
		}
	}

	/**
	 * 远程文件路径编码
	 * 
	 * @param remoteFilePath
	 * @return
	 * @throws BussinessException
	 */
	private String encodingRemoteFilePath(String remoteFilePath) throws EOSException {
		try {
			return new String(remoteFilePath.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			LogUtil.logError("***encodingRemoteFilePath Error: " + e, null);
			throw new EOSException(e);
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param checkFileName
	 * @return
	 * @throws IOException
	 */
	public long getFileSize(String checkFileName) throws IOException {

		FTPFile[] files = ftpClient.listFiles(remotePath);
		for (FTPFile file : files) {
			if (file.getName().equals(checkFileName)) {
				return file.getSize();
			}
		}
		return 0;
	}

	/**
	 * 获取有效文件列表
	 * 
	 * @return
	 * @throws IOException
	 */
	private ArrayList<String> getValidFiles() throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		String[] remoteNames = ftpClient.listNames(remotePath);
		// 是否等待标志文件
		if (isCheckOkFile) {
			boolean flag = false;
			
			//判断ok标志文件,如果没有一直挂起
			while (true) {
				//循环取到的文件列表是否有ok标志,有标识变量执为true
				for (String okFile : remoteNames) {
					if (okFile.contains("/")) {
						okFile = okFile.substring(okFile.lastIndexOf("/") + 1);
					}
					if (okFile.equals(okFileName) || Pattern.matches(okFileName, okFile)) {
						LogUtil.logInfo("FTP标志文件【" + remotePath + okFileName + "】已发现，准备下载【" + remoteFullFileName + "】文件...", null);
						flag = true;
						break;
					}
				}
				
				//判断标识文件flag如果为flash一直挂起,为true跳出循环
				if (!flag) {
					LogUtil.logInfo("FTP标志文件【" + remotePath + okFileName + "】未找到，请继续等待...", null);
					try {
						Thread.sleep(waitTime * 1000);// 等待时间
						remoteNames = ftpClient.listNames(remotePath);
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.interrupted();
					}
				} else {
					break;
				}
			}
		}
		for (String remoteName : remoteNames) {
			if (remoteName.contains("/")) {
				remoteName = remoteName.substring(remoteName.lastIndexOf("/") + 1);
			}
			// 判断是否需要下载
//			if(!isRepeat){
//				HashMap<String,String> params = new HashMap<String,String>();
//				params.put("fileName", remoteName);
//				Object[] objs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.DBFileUtil.queryFtpLog", params);
//				if(objs.length > 0){
//					continue;
//				}
//			}
			// 相同或正则表达式
//			if (remoteName.equals(fileName) || Pattern.matches(fileName, remoteName)) {
				// 等待3秒判断文件是否变化
				long fileSize1 = this.getFileSize(remoteName);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.interrupted();
				}
				long fileSize2 = this.getFileSize(remoteName);
				if (fileSize1 == fileSize2) {
					LogUtil.logInfo("FTP文件【" + remotePath + remoteName + "】已验证文件大小不变..." + fileSize2, null);
					list.add(remoteName);// 添加没问题的下载文件名
				} else {
					LogUtil.logInfo("FTP文件【" + remotePath + remoteName + "】文件大小仍在变化..." + fileSize2 + "，等待5秒...", null);
				}
//			}
		}
		return list;
	}

	/**
	 * 等待文件生成
	 * 
	 * @return
	 * @throws IOException
	 */
	private ArrayList<String> waitFTPFiles() throws IOException {
		ArrayList<String> list = this.getValidFiles();
		if (this.isWait) {
			while (true) {
				if (list != null & list.size() > 0) {
					LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】已发现，准备下载...", null);
					break;
				} else {
					LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】未找到，等待" + waitTime + "秒...", null);
					try {
						Thread.sleep(waitTime * 1000);// 等待时间
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.interrupted();
					}
					list = this.getValidFiles();// 再次获取
				}
			}
		}
		return list;
	}

	/**
	 * 下载文件，支持正则表达式1+个文件下载
	 * 
	 * @param localPath
	 * @param remotePath
	 * @param fileName
	 * @return
	 * @throws EOSException
	 * @throws IOException
	 */
	public ArrayList<String> downloadFile(String localPath, String remotePath, String fileName) throws EOSException, IOException {
		initFilePath(localPath, remotePath, fileName);
		OutputStream os = null;
		boolean result = false;
		try {
			ArrayList<String> list = waitFTPFiles();// 获取正则表达式文件
			// 循环下载
			for (String regexName : list) {
				initFilePath(localPath, remotePath, regexName);
				os = new FileOutputStream(localFullFileName);
				result = ftpClient.retrieveFile(this.encodingRemoteFilePath(remoteFullFileName), os);
				if (result) {
					LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】下载成功！", null);
					insertLog(operateType,"下载");
				} else {
					LogUtil.logError("FTP文件【" + remoteFullFileName + "】下载失败！", null);
					throw new EOSException("FTP文件【" + remoteFullFileName + "】下载失败！");
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】下载异常！", null);
			throw new EOSException(e);
		} finally {
			if (os != null) {
				os.close();
			}
			this.close();// 关闭连接
		}

	}

	/**
	 * 上传文件
	 * 
	 * @param localPath
	 * @param remotePath
	 * @param fileName
	 * @return
	 * @throws EOSException
	 * @throws IOException
	 */
	public boolean uploadFile(String localPath, String remotePath, String fileName) throws EOSException, IOException {
		initFilePath(localPath, remotePath, fileName);
		FileInputStream is = new FileInputStream(localFullFileName);
		try {
			ftpClient.changeWorkingDirectory(this.remotePath);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(this.remotePath);
			boolean result = ftpClient.storeFile(encodingRemoteFilePath(remoteFullFileName), is);
			if (result) {
				LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】上传成功！", null);
				if(isCheckOkFile){//是否上传标识文件
					String ok_fileName = this.fileName.substring(0, this.fileName.lastIndexOf(".") + 1) + okFileExtName;
					String localFileName = this.localPath + ok_fileName;
					File file = new File(localFileName);
					file.createNewFile();
					is = new FileInputStream(localFileName);
					result = ftpClient.storeFile(encodingRemoteFilePath(this.remotePath + ok_fileName), is);// 上传OK文件
					if (!result) {
						throw new EOSException("OK文件【" + this.remotePath + ok_fileName + "】上传失败！");
					}
					LogUtil.logInfo("OK文件【" + remotePath + ok_fileName + "】上传完成！", null);
				}
				insertLog(operateType,"上传");
			} else {
				LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】上传失败！", null);
			}
			return result;
		} catch (Exception e) {
			LogUtil.logError("FTP文件【" + remoteFullFileName + "】上传异常！", null);
			throw new EOSException(e);
		} finally {
			if (is != null) {
				is.close();
			}
			this.close();// 关闭连接
		}
	}

	/**
	 * 初始文件路径和文件名
	 * 
	 * @param localPath
	 * @param remotePath
	 * @param fileName
	 */
	private void initFilePath(String localPath, String remotePath, String fileName) {
		this.localPath = localPath;
		this.remotePath = remotePath;
		this.fileName = fileName;
		File file = new File(localPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (!this.localPath.endsWith("/")) {
			this.localPath += "/";
		}
		if (!this.remotePath.endsWith("/")) {
			this.remotePath += "/";
		}
		
		this.operateType = localPath.split("/")[localPath.split("/").length - 1];
		if (fileName.contains(".")) {
			okFileName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + okFileExtName;
		}
		remoteFullFileName = this.remotePath + this.fileName;
		localFullFileName = this.localPath + this.fileName;
	}
	
	/**
	 * 初始文件路径和文件名
	 * 
	 * @param localPath
	 * @param remotePath
	 * @param fileName
	 */
	private void initDKFilePath(String localPath, String remotePath) {
		this.localPath = localPath;
		this.remotePath = remotePath;
		File file = new File(localPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (!this.localPath.endsWith("/")) {
			this.localPath += "/";
		}
		if (!this.remotePath.endsWith("/")) {
			this.remotePath += "/";
		}
		remoteFullFileName = this.remotePath;
		localFullFileName = this.localPath;
		
	}
	
	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public void setWait(boolean isWait) {
		this.isWait = isWait;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

	public void setIswaitOkFile(boolean isCheckOkFile) {
		this.isCheckOkFile = isCheckOkFile;
	}

	public void setOkFileExtName(String okFileExtName) {
		this.okFileExtName = okFileExtName;
	}
	
	public void insertLog(String operateType,String transferType) {
		// 记录日志
		HashMap<String, Object> logsMap = new HashMap<String, Object>();
		logsMap.put("createDate", GitUtil.getBusiDate());
		logsMap.put("createTime", GitUtil.getBusiTimestamp());
		logsMap.put("operateType", operateType);
		logsMap.put("localPath", localPath);
		logsMap.put("remotePath", remotePath);
		logsMap.put("remoteHost", server);
		logsMap.put("fileName", fileName);
		logsMap.put("fileType", "TXT");
		logsMap.put("transferType", transferType);
		logsMap.put("operateResult", "");
		logsMap.put("userNum", "NIGHT");
		logsMap.put("orgNum", "NIGHT");
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.DBFileUtil.insertFtpLog", logsMap);
	}

	public void insertDKLog(String operateType,String transferType,String file,String fileType) {
		// 记录日志
		HashMap<String, Object> logsMap = new HashMap<String, Object>();
		logsMap.put("createDate", GitUtil.getBusiDate());
		logsMap.put("createTime", GitUtil.getBusiTimestamp());
		logsMap.put("operateType", operateType);
		logsMap.put("localPath", localPath);
		logsMap.put("remotePath", remotePath);
		logsMap.put("remoteHost", server);
		logsMap.put("fileName", file);
		logsMap.put("fileType", fileType);
		logsMap.put("transferType", transferType);
		logsMap.put("operateResult", "success");
		logsMap.put("userNum", "NIGHT");
		logsMap.put("orgNum", "NIGHT");
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.DBFileUtil.insertFtpLog", logsMap);
	}
	
	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public static void main(String[] args) throws Exception {
		// FtpService ftpService = new FtpService("172.20.12.198", 22,
		// "informix", "informix","");
		FtpService ftpService = new FtpService("172.20.12.7", 21, "devftp", "12345678");
		ftpService.setWaitTime(5);
		ftpService.setWait(true);
		ftpService.setIswaitOkFile(true);
		boolean resu = ftpService.isOpenFTPConnection();
		if (resu) {
			String sfileName1 = "hpdjb.20151107.unl.00";
//			String sfileName = "^dkhzwj.20151107.unl.[0-9]{2}.[Zz]$";
//			String sfileName2 = "^hpdjb.20151107.unl.[0-9]{2}$";
			ftpService.downloadFile("D:/ftpTest/", "/home/devftp/CRES/", sfileName1);
			// ftpService.downloadFile("D:/ftpTest/", "/home/devftp/CRES/",
			// "hpdjb.20151109.unl.01");
			// ftpService.uploadFile("D:/ftpTest/", "/home/devftp/CRES/",
			// "dkhzwj.20151107.unl.00");

		}

	}
	
	
	/**
	 * 下载垫款文件
	 * 
	 * @param localPath
	 * @param remotePath
	 * @return
	 * @throws EOSException
	 * @throws IOException
	 */
	public ArrayList<String> downDKFile(String localPath, String remotePath) throws EOSException, IOException {
		initDKFilePath(localPath,remotePath);
		OutputStream os = null;
		boolean result = false;
		try {
			ArrayList<String> list = getDKFiles();
			// 循环拷贝
			for (String regexName : list) {
				os = new FileOutputStream(localPath+"/"+regexName);
				result = ftpClient.retrieveFile(this.encodingRemoteFilePath(this.remotePath+"/"+regexName), os);
				if (result) {
					LogUtil.logInfo("FTP文件【" +remotePath+"/"+regexName + "】下载成功！", null);
					insertDKLog("core_down_diankuan","下载",remotePath+"/"+regexName,regexName.substring(regexName.indexOf("."), regexName.length()));
					boolean delResult = false;
					delResult = ftpClient.deleteFile(this.encodingRemoteFilePath(this.remotePath+"/"+regexName));
					if(delResult){
						LogUtil.logInfo("FTP文件【" +remotePath+"/"+regexName + "】删除成功！", null);
						insertDKLog("core_down_diankuan","删除",remotePath+"/"+regexName,regexName.substring(regexName.indexOf("."), regexName.length()));
					}else{
						LogUtil.logError("FTP文件【" +regexName + "】删除失败！", null);
						throw new EOSException("FTP文件【" +remotePath+"/"+regexName + "】删除失败！");
					}
				} else {
					LogUtil.logError("FTP文件【" +regexName + "】下载失败！", null);
					throw new EOSException("FTP文件【" +remotePath+"/"+regexName + "】下载失败！");
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logInfo("FTP垫款文件处理(下载/删除)异常！", null);
			throw new EOSException(e);
		} finally {
			if (os != null) {
				os.close();
			}
			this.close();// 关闭连接
		}
	}

	/**
	 * 等待垫款文件生成
	 */
	private ArrayList<String> waitDKFiles() throws IOException {
		// 获取下载文件列表
		ArrayList<String> list = this.getDKFiles();
		if (this.isWait) {
			while (true) {
				if (list != null & list.size() > 0) {
					LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】已发现，准备下载...", null);
					break;
				} else {
					LogUtil.logInfo("FTP文件【" + remoteFullFileName + "】未找到，等待" + waitTime + "秒...", null);
					try {
						Thread.sleep(waitTime * 1000);// 等待时间
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.interrupted();
					}
					list = this.getValidFiles();// 再次获取
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取有效的垫款文件列表
	 */
	private ArrayList<String> getDKFiles() throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		String[] remoteNames = ftpClient.listNames(remotePath);
		//垫款文件夹里面所有的文件名 例如： 47PDK2017073000001.i  47PDK2017073000001.i.ok
		ArrayList<String> fileNames = new ArrayList<String>();
		//垫款文件夹里面没有后缀的文件名 例如： 47PDK2017073000001
		ArrayList<String> fileNames1 = new ArrayList<String>();
		if(null!=remoteNames&&remoteNames.length>1){//垫款文件夹里面有文件
			for (String file : remoteNames) { 
				if (file.contains("/")) {
					file = file.substring(file.lastIndexOf("/") + 1);//垫款文件名
					fileNames.add(file);
					if(file.contains(".")){
						file = file.substring(0, file.indexOf("."));
						if(!fileNames1.contains(file)){
							fileNames1.add(file);
						}
						
					}
				}
			}
			for (String file : fileNames1) { 
				if(fileNames.contains(file+".i")&&fileNames.contains(file+".i.ok")){//完整的垫款文件：有.i也有.i.ok
					//list.add(remotePath+"/"+file+".i");
					//list.add(remotePath+"/"+file+".i.ok");
					list.add(file+".i");
					list.add(file+".i.ok");
				}
			}
		}
		return list;
	}
	
	// 连接sftp
	public void conSftp() {
		try {
			// 获取服务器配置信息
			String Sftp_UserName = CommonUtil.getDBConfigVal("ftp_dxpt", "username");
			String Sftp_Pwd = CommonUtil.getDBConfigVal("ftp_dxpt", "password");
			String Sftp_Host = CommonUtil.getDBConfigVal("ftp_dxpt", "host");
			String Sftp_Port = CommonUtil.getDBConfigVal("ftp_dxpt", "port");
			
			if (sftp != null) {
				System.out.println("sftp不为空");
			}
			JSch jsch = new JSch();
			jsch.getSession(Sftp_UserName, Sftp_Host, Integer.parseInt(Sftp_Port));
			Session sshSession = jsch.getSession(Sftp_UserName, Sftp_Host, Integer.parseInt(Sftp_Port));
			System.out.println("sftp创建Session.");
			
			sshSession.setPassword(Sftp_Pwd);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking","no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("sftp连接Session.");
			
			System.out.println("sftp连接成功.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("连接到: " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 关闭sftp
	public void disConSftp(){
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
			} else if (this.sftp.isClosed()) {
				System.out.println("sftp已经关闭.");
			}
		}
		
		System.out.println("关闭sftp.");
	}
		
	// 删除本地文件
	private void delFile() {
		File file = new File(localPath);
		List<String> listFile = this.getFileEntryList(file);
		
		System.out.println("--- 准备开始删除文件. ---");
		try {
			// 组装实现文件上传
			for (String filepath : listFile) {
				String loaclFile =  filepath;
				File file1 = new File(loaclFile);
				// 删文件
				if (file1.exists() && file1.isFile()){
					System.out.println("--- 删除文件开始. ---");
					if (file1.delete()) {
						System.out.println("--- 删除短信文件:" + loaclFile + ", 成功! ---");
					} else {
						System.out.println("--- 删除短信文件:" + loaclFile + ", 失败! ---");
					}
					System.out.println("--- 删除文件完成. ---");
				} else {
					System.out.println("--- 本地不存在短信文件. ---");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取文件列表
	private List<String> getFileEntryList(File filePath) {
		ArrayList<String> fileList = new ArrayList<String>();
		File[] listFile = filePath.listFiles();
		for(File a:listFile){
			if(a.isDirectory()){
				getFileEntryList(new File(a.getAbsolutePath()));
			}else if(a.isFile()){
				fileList.add(a.getAbsolutePath());
			}
		}
		return fileList;
	}
	
	// sftp上传文件
	public boolean sftpUpload() {
		operateType = "dxpt_up_sms";

		// 获取远程路径
		String remotePathConf = CommonUtil.getDBConfigVal("ftp_dxpt", operateType);
		remotePath = remotePathConf.substring(0, remotePathConf.lastIndexOf("/") + 1);
		if (!remotePath.endsWith("/")) {
			remotePath += "/";
		}
		if (remotePath.startsWith("/")){
			remotePath = remotePathConf.substring(1, remotePathConf.lastIndexOf("/") + 1);
		}
		// 获取本地路径
		localPath = CommonUtil.getDBConfigVal("ftp_common", "localPath");
		String rootPath = SystemInfo.APP_WAR_PATH.substring(0, 2);
		String dateStr = GitUtil.getBusiDateYYYYMMDD();	//8位日期格式
		if (!localPath.endsWith("/")) {
			localPath += "/";
		}
		if (rootPath.contains(":")) {
			localPath = rootPath + localPath + operateType + "/";
		} else {
			localPath = localPath + operateType + "/";
		}
		localPath = localPath + dateStr + "/";
		
		File file = new File(localPath);
		List<String> listFile = this.getFileEntryList(file);
		
		try {
			if (listFile != null) {
				// 连接sftp服务器
				this.conSftp();
				// 进入远程服务器目录
				sftp.cd(remotePath);
				
				// 组装实现文件上传
				for (String filepath : listFile) {
					String loaclFile =  filepath;
					File file1 = new File(loaclFile);
					
					// 创建文件流对象
					FileInputStream filliptstr= new FileInputStream(file1);
					// 上传
					this.sftp.put(filliptstr, file1.getName());
					System.out.println("--- 上传短信文件: " + loaclFile + " ---");
					// 关闭文件流对象
					filliptstr.close();
				}
			}
			
			// 关闭sftp连接
			this.disConSftp();
			// 批量删除本地文件
			this.delFile();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
