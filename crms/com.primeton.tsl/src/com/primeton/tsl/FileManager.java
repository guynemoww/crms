package com.primeton.tsl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eos.system.exception.EOSException;
import com.lenovo.ebank.transfer.TftClt;

public class FileManager {
	public Logger logger = LoggerFactory.getLogger(FileManager.class);
	String shell;
	Integer hostId;
	
	public FileManager(){
		hostId = 1;
		shell = "./tftclient";
	}
	//windows环境下测试下载
	public int down(String RemotePath,String LocalFileName) throws EOSException {
		logger.info("down file "+RemotePath+" ...");
		//-----------------------------------本地测试开启---------------------------------------------------
		int down;
		TftClt tftClient = new TftClt();
//		tftClient.setTftCfgFile(configFileName);//configFileName 这个就是transfer.cfg文件的路径
		tftClient.setTftCfgFile("C:/Users/lenovo/Desktop/修改到核算的数据/tftclient/win/tools/transfer.cfg");//configFileName 这个就是transfer.cfg文件的路径
		try {//"ext/XDJZ_47/20170403/47PKK2017040300001.i"  47PKK2017040300001.o" /cbs/ftp/ext/XDJZ_47/20170404/47PKK2017040400001.o
			System.out.println("远程服务器地址：" + RemotePath + ",本地下载地址：" + LocalFileName);
			down = tftClient.TransferFile("down",3,RemotePath,LocalFileName,"HOST");//测试路径ext/XDJZ_47/20170328/47PDD2017032800001.i
			//down = tftClient.TransferFile("down",3,RemotePath + ".ok",LocalFileName + ".ok","HOST");//测试路径ext/XDJZ_47/20170328/47PDD2017032800001.i
		}catch(Exception e ){
			System.out.println(e.getMessage());
			throw new EOSException("11065","下载文件失败");
		}
		System.out.println(down);
		//-----------------------------------本地测试结束---------------------------------------------------
		logger.info("下载文件成功，文件路径：" + LocalFileName);
		return down;
	}
	//windows环境下测试上传
	public int up(String RemotePath,String LocalFileName) throws EOSException{
		logger.info("up file "+RemotePath+" ...");
		//-----------------------------------本地测试开启---------------------------------------------------
		int up;
		TftClt tftClient = new TftClt();
//		tftClient.setTftCfgFile(configFileName);//configFileName 这个就是transfer.cfg文件的路径
		tftClient.setTftCfgFile("C:/Users/lenovo/Desktop/业务别/tftclient/win/tools/transfer.cfg");//configFileName 这个就是transfer.cfg文件的路径
		try {
			up = tftClient.TransferFile("up",3,"ext/XDJZ_47/20170406/47PBB2017040600001.i","D:/batch/send/PKK/9999/20170403/47PKK2017040300001.i","HOST");
		}catch(Exception e ){
			System.out.println(e.getMessage());
			throw new EOSException("11064","上传文件失败");
		}
		//-----------------------------------本地测试结束----------------------------------------------------
		System.out.println(up);
		logger.info("上传文件成功，文件路径：" + LocalFileName);
		return up;
	}
	//下载文件linux
	public boolean fileDown(String remoteFileName,String locaFileName) throws EOSException{
		logger.info("down file "+remoteFileName+" ...");
		boolean result = false;
		try{
			exe("down",1,remoteFileName,locaFileName,"HOST");
			result = true;
		}catch(Exception e ){
			logger.error("下载文件失败,失败原因：" + e.getMessage() + ",目标文件路径：" + remoteFileName + ",本地文件路径：" + locaFileName);
			throw new EOSException("11065","下载文件失败");
		}
		return result;
	}
	//上传文件linux
	public boolean fileUp(String remoteFileName,String locaFileName) throws EOSException{
		logger.info("up file "+locaFileName+" ...");
		boolean result = false;
		try{
			exe("up",1,remoteFileName,locaFileName,"HOST");
			result = true;
		}catch(Exception e ){
			logger.error("上传文件失败,失败原因：" + e.getMessage() + ",目标文件路径：" + remoteFileName + ",本地文件路径：" + locaFileName);
			throw new EOSException("11064","上传文件失败");
		}
		return result;
	}
	
	private boolean exe(String transFlag,int hostId,String remoteFileName,String locaFileName,String transPlatform) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(shell);
			sb.append(" -d");
			sb.append(transFlag);
			sb.append(" -h");
			sb.append(hostId);
			sb.append(" -r");
			sb.append(remoteFileName);
			sb.append(" ");
			sb.append(locaFileName);
			sb.append(" -t");
			sb.append(transPlatform);
			//logger.info("shell: "+sb);
			System.out.println("shell: "+sb.toString().substring(sb.indexOf("tftclient")));
			Process pp = Runtime.getRuntime().exec(sb.toString().substring(sb.indexOf("tftclient")));
			pp.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(pp.getInputStream()));
			BufferedReader err = new BufferedReader(new InputStreamReader(pp.getErrorStream()));
			String line;
			StringBuffer msg = new StringBuffer();
			StringBuffer errMsg = new StringBuffer();
			while((line=br.readLine())!=null) {
				msg.append(line);
			}
			if(msg.length()>0)
				logger.info(msg.toString());
			while((line=err.readLine())!=null) {
				errMsg.append(line);
			}
			if(errMsg.length()>0)
				logger.error(errMsg.toString());
			if(msg.indexOf("File transfer SUCCESS")!=-1||errMsg.indexOf("File transfer SUCCESS")!=-1) {
				return true;
			}
		} catch (Exception e) {
			logger.error("fail_to_tftpserver_download",e);
		}
		return false;
	}
}
