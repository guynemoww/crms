package com.bos.pub.sftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.primeton.bfs.tp.common.exception.EOSException;
/**
 * SFTP服务工具类
 * @author shendl
 * 2017/9/11
 *
 */
public class SftpService {
	private JSch jSch = null;
	private ChannelSftp sftp = null;
	private Channel channel = null;
	private Session session = null;
	private String hostName;//远程服务器地址
	private int port;//端口
	private String userName;//用户名
	private String password;//密码
	
	private String localPath;
	private String remotePath;
	
	public SftpService(String hostName,int port,String userName,String password){
		this.hostName = hostName;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}
	
	//连接登录远程服务器
	public boolean connect() throws Exception{
		try{	
			jSch = new JSch();
			jSch.getSession(userName, hostName, port);
			session = jSch.getSession(userName, hostName, port);
			session.setPassword(password);
			session.setConfig(this.getSshConfig());
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("登录服务器"+hostName+"成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("连接FTP服务器发生异常："+e.getMessage());
			throw e;
		}
		return true;
	}
	//获取服务配置
	private Properties getSshConfig() throws Exception{
		Properties sshConfig = null;
		try {
			sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sshConfig;
	}
	//关闭连接
	private void disconnect() throws Exception{
		try {
			if(sftp.isConnected()){
				sftp.disconnect();
			}
			if(channel.isConnected()){
				channel.disconnect();
			}
			if(session.isConnected()){
				session.disconnect();
			}
			System.out.println("退出FTP服务器登录！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("关闭FTP服务器发生异常："+e.getMessage());
			throw e;
		}
	}
	//下载远程sftp服务器文件
	public boolean downloadFile(String remotePath,String remoteFileName,String localFileName) throws Exception{
		FileOutputStream output = null;
		boolean success = false;
		try {
//			if(null!=remotePath&&remotePath.trim()!=""){
//				sftp.cd(remotePath);
//			}
			File localFile = new File(localFileName+"/"+remoteFileName);
//			if(localFile.exists()){
//				System.out.println("文件："+localFileName+"已经存在！");
//				return success;
//			}
			output = new FileOutputStream(localFile);
			sftp.get(remotePath+"/"+remoteFileName,output);
			success = true;
			System.out.println("成功接收文件，本地路径【"+localFileName+"/"+remoteFileName+"】");
		} catch (Exception e) {
			System.out.println("接收文件发生异常。异常原因："+e.getMessage());
			return success;
		}finally{
			//关闭文件读取流
			try {
				if(null!=output){
					output.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println(e2.getMessage());
			}
		}
		return success;
	}
	//垫款文件处理---下载并同时删除远程服务器上的垫款文件
	public ArrayList<String> downLoadDKFile(String localPath, String remotePath) throws EOSException, IOException {
		//初始化文件路径
		initDKFilePath(localPath,remotePath);
		try {
			//需要处理的垫款文件
			ArrayList<String> list = getValidDKFiles(remotePath);
			// 循环拷贝
			for (String regexName : list) {
				//下载垫款文件
				boolean  downResult = downloadFile(remotePath,regexName,localPath);
				if (downResult) {
					insertDKLog("core_down_diankuan","下载",remotePath+"/"+regexName,regexName.substring(regexName.indexOf("."), regexName.length()),"成功","成功");
					//删除已经下载的垫款文件
					boolean delResult  = deleteFile(remotePath,regexName);
					if(delResult){
						insertDKLog("core_down_diankuan","删除",remotePath+"/"+regexName,regexName.substring(regexName.indexOf("."), regexName.length()),"成功","成功");
					}else{
						insertDKLog("core_down_diankuan","删除",remotePath+"/"+regexName,regexName.substring(regexName.indexOf("."), regexName.length()),"失败","失败");
						throw new EOSException("FTP文件【" +remotePath+"/"+regexName + "】删除失败！");
					}
				} else {
					insertDKLog("core_down_diankuan","下载",remotePath+"/"+regexName,regexName.substring(regexName.indexOf("."), regexName.length()),"失败","失败");
					throw new EOSException("FTP文件【" +remotePath+"/"+regexName + "】下载失败！");
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			insertDKLog("core_down_diankuan","处理","*.i/*.i.ok",".i/.i.ok","异常",e.getMessage());
			throw new EOSException(e);
		} finally {
			try {
				this.disconnect();// 关闭连接
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("关闭FTP服务器发生异常："+e2.getMessage());
				throw new EOSException(e2);
			}
		}
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
	}
	
	/**
	 * 获取有效的垫款文件列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getValidDKFiles(String remotePath) throws Exception {
		//返回的有效的垫款文件文件名---即需要下载的垫款文件---相同名称以.i和.i.ok同时出现的垫款文件即为有效的垫款文件
		ArrayList<String> list = new ArrayList<String>();
		//远程FTP服务器文件夹下所有的文件的文件名 例如： 47PDK2017073000001.i  47PDK2017073000001.i.ok
		ArrayList<String> fileNames = new ArrayList<String>();
		//远程FTP服务器垫款文件夹下的所有文件
		Vector<LsEntry> fileList = this.sftp.ls(remotePath);
		Iterator<LsEntry> it = fileList.iterator();
		while(it.hasNext()){
			String fileName = it.next().getFilename();
			//特殊文件名---忽略
			if(".".equals(fileName)||("..").equals(fileName)){
				continue;
			}
			fileNames.add(fileName);
		}
		//垫款文件夹里面没有后缀的文件名 例如： 47PDK2017073000001---后面用来判断是否存在.i和.i.ok用
		ArrayList<String> fileNames1 = new ArrayList<String>();
		if(null!=fileNames&&fileNames.size()>1){//垫款文件夹里面有文件
			for (String file : fileNames) { 
				if(file.contains(".")){
					file = file.substring(0, file.indexOf("."));
					if(!fileNames1.contains(file)){
						fileNames1.add(file);
					}
				}
			}
			for (String file : fileNames1) { 
				if(fileNames.contains(file+".i")&&fileNames.contains(file+".i.ok")){//完整的垫款文件：有.i也有.i.ok
					list.add(file+".i");
					list.add(file+".i.ok");
				}
			}
		}
		return list;
	}
	//删除远程文件
	public boolean deleteFile(String remotePath,String remoteFilename) throws Exception{
		boolean success = false;
		try {
//			if(null!=remotePath&&remotePath.trim()!=""){
//				sftp.cd(remotePath);
//			}
			//删除文件
			sftp.rm(remotePath+"/"+remoteFilename);
			System.out.println("删除远程文件【"+remotePath+"/"+remoteFilename+"】成功！");
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("删除文件【"+remotePath+"/"+remoteFilename+"】发生异常: "+e.getMessage());
			return success;
		}
		return success;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void insertDKLog(String operateType,String transferType,String file,String fileType,String operateResult,String note) {
		// 记录日志
		HashMap<String, Object> logsMap = new HashMap<String, Object>();
		logsMap.put("createDate", GitUtil.getBusiDate());
		logsMap.put("createTime", GitUtil.getBusiTimestamp());
		logsMap.put("operateType", operateType);
		logsMap.put("localPath", localPath);
		logsMap.put("remotePath", remotePath);
		logsMap.put("remoteHost", hostName);
		logsMap.put("fileName", file);
		logsMap.put("fileType", fileType);
		logsMap.put("transferType", transferType);
		logsMap.put("operateResult", operateResult);
		logsMap.put("userNum", "定时器");
		logsMap.put("orgNum", "定时器");
		logsMap.put("note", note);
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.DBFileUtil.insertFtpLog", logsMap);
	}
}
