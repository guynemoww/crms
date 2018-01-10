package com.bos.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;

/**
 * 对日期对象与指定格式的 字符串之间转换的处理
 */
@Bizlet("CommonUtil")
public class CommonUtil {
	@Bizlet("执行shell命令")
	public static String execShell(String shFile, String params, String logFile) throws Exception {
		//chmod();
		Runtime rt = Runtime.getRuntime();
		LogUtil.logDebug("执行命令：sh " + shFile,null);
		Process proc = rt.exec("sh " + shFile);
		
		int exitVal = 1;
		boolean processFinished = false;
		while (!processFinished) {
			try {
				exitVal = proc.exitValue();
				processFinished = true;
			} catch (IllegalThreadStateException e) {
				processFinished = false;
			}
			try {
				Thread.sleep(DEFAULT_INTERVAL);
			} catch (InterruptedException e1) {
				LogUtil.logError("Process, failed [ {0} ]", e1, e1.getMessage());
			}
		}
		// 记录日志
		if (exitVal == 0) {
			LogUtil.logInfo("命令：({0})({1})({2})执行成功", null, shFile, params, logFile);
			return "1";
		} else {
			LogUtil.logError("命令：({0})({1})({2})执行失败", null, shFile, params, logFile);
			return "2";
		}

	}

	// 一分钟检查一次
	public static final int DEFAULT_INTERVAL = 1000;

	@Bizlet("shell权限")
	public static void chmod() throws Exception {
		LogUtil.logDebug("chmod begin", null, null);
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec(new String[] { "/bin/sh", "-c", "perl -p -i -e 's/\\r\\n/\\n/g' " + getShellFilePath("*.sh") });
			rt.exec(new String[] { "/bin/sh", "-c", "chmod a+x " + getShellFilePath("*.sh") });
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			LogUtil.logDebug("chmod error", null, null);
			throw new Exception("shell chmod error！");
		}
		LogUtil.logDebug("chmod end", null, null);
		Thread.sleep(DEFAULT_INTERVAL);
	}

	@Bizlet("组合参数")
	public static String creParams(String... args) {
		StringBuffer comm = new StringBuffer();
		for (String temp : args) {
			if (temp != null && temp != "" && !"null".equals(temp)) {
				comm.append(temp);
				comm.append(" ");
			}
		}
		return comm.toString().trim();
	}

	//
	// @Bizlet("构造shell命令")
	// public static String getshFile(String path, String... args) {
	// StringBuffer comm = new StringBuffer();
	// // comm.append("ksh "+path);
	// // comm.append(" ");
	// comm.append(path);
	// comm.append(" ");
	// for (String temp : args) {
	// if(temp!=null&&temp!=""&&!"null".equals(temp)){
	// comm.append(temp);
	// comm.append(" ");
	// }
	// }
	// return comm.toString().trim();
	// }

	@Bizlet("将文件路径和名称封装入Map集合")
	public static void getDownloadMap(String fileName, String filePath) throws IOException {

		Map<String, String> params = new HashMap<String, String>();

		params.put("DOC_NAME", fileName);

		params.put("DOC_PATH", filePath);

		// params.put("DOC_NAME", "JJQD.10001.20140731.unl.gz");
		//
		// params.put("DOC_PATH",
		// "E:\\TEMP\\20140731\\JJQD.10001.20140731.unl.gz");

		downloadFile(params);

	}

	@Bizlet("文件下载")
	public static void downloadFile(Map<String, String> param) throws IOException {
		ServletResponse res = GitUtil.getResponse();
		String docName = param.get("DOC_NAME").toString();
		String docPath = param.get("DOC_PATH").toString();

		File file = new File(docPath);
		if (file.exists() == false) {
			// 文件不存在
			LogUtil.logDebug("docName:{0},docPath{1}", null, docName, docPath);
			writeMessage(res, "文档不存在！");
			return;
		}

		res.setContentType("application/octet-stream");
		((HttpServletResponse) res).setHeader("Content-disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(docName, "UTF-8") + "\"");
		OutputStream out = res.getOutputStream();
		FileInputStream in = new FileInputStream(file);
		int i = 0;
		byte[] b = new byte[1024];
		try {
			while ((i = in.read(b)) > 0) {
				out.write(b, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			if (out != null)
				out.close();
		}
	}

	private static void writeMessage(ServletResponse res, String msg) {
		try {
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter wr = res.getWriter();
			wr.write("<html><body><script>alert('");
			wr.write(msg);
			wr.write("');</script></body></html>");
		} catch (IOException e) {
		}
	}

	@Bizlet("处理字符串将其转化为yyyyMMdd格式")
	public static String getFileDate(String fileDate) {

		SimpleDateFormat st = new SimpleDateFormat("yyyy/MM/dd");

		SimpleDateFormat tt = new SimpleDateFormat("yyyyMMdd");

		try {
			Date dt = st.parse(fileDate);

			fileDate = tt.format(dt);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return fileDate;

	}

	@Bizlet("获取批量日志路径")
	public static String getBatchLogPath() {

		ApplicationContext ac = ApplicationContext.getInstance();// 应用上下文
		String appconfig = ac.getApplicationConfigPath();
		String filePath = appconfig.substring(0, appconfig.lastIndexOf("/"));
		return filePath;
		// + "/work_temp/logs/batch.log";
		// return "/crmsshare/CRMS/logs/trace_batch.log";
		// return "work_temp/logs/trace_batch.log";

	}

	@Bizlet("获取批量路径")
	public static String getBatchPath() {

		ApplicationContext ac = ApplicationContext.getInstance();// 应用上下文
		String apppath = ac.getWarRealPath();

		return apppath + File.separator + "crmsbatch";

	}

	/**
	 * 
	 * @Title: readTextFromEnd
	 * @Description: 倒序读取文件内容
	 * @param pathAndFileName
	 *            文件路径
	 * @param encoding
	 *            字符集
	 * @return void 返回类型
	 * @throws
	 * @author GIT-Sunny
	 * @date 2012-12-5 下午12:52:52
	 * @version V1.0
	 */
	@Bizlet("倒序读取文件")
	public static void readTextFromEnd(String pathAndFileName, String encoding) {
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(pathAndFileName, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line;
			rf.seek(nextend);
			int c = -1;
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if (line == null) {// 处理文件末尾是空行这种情况
						nextend--;
						rf.seek(nextend);
						continue;
					}
					System.out.println(new String(line.getBytes("ISO-8859-1"), encoding));
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), encoding));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Title: dataReader
	 * @Description: 按行数读取指定文件
	 * @param nameFile
	 *            文件路径
	 * @param start
	 *            开始行数
	 * @param finish
	 *            结束行数
	 * @return
	 * @throws EasyLoanException
	 *             设定文件
	 * @return List<String> 返回类型
	 * @throws
	 * @author GIT-Sunny
	 * @date 2012-12-5 下午12:53:55
	 * @version V1.0
	 */
	@Bizlet("读取最后500行文件")
	public static String dataReader(String nameFile) {
		List<String> retList = new ArrayList<String>();
		InputStream inputStream = null;
		LineNumberReader reader = null;
		String text = "";
		try {
			inputStream = new FileInputStream(new File(nameFile));

			reader = new LineNumberReader(new InputStreamReader(inputStream));
			int lines = getTotalLines(new File(nameFile));

			String line = reader.readLine();
			int showLines = 500;
			int start = lines - showLines;
			int readline = 0;
			while (line != null) {
				readline++;
				if (readline < start) {
					reader.readLine();
				} else {
					line = reader.readLine();
					if (line == null || line == "") {// 处理文件末尾是空行这种情况
						break;
					}
					text = text.concat(new String(line.getBytes(), ("UTF-8"))).concat("\n");
				}
			}
			System.out.println(text);
			inputStream.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return text;
	}

	@Bizlet("根据系统日期读取最后500行文件")
	public static String dataReader(String nameFile, String startDate) {

		// 日志路径是导数日志或者卸数日志
		if (nameFile.contains("loadData") || nameFile.contains("dataDrawOut") || nameFile.contains("gzipClist")) {
			// 任务开始时间startDate为空，则取系统日期
			if (null == startDate || startDate.equals("")) {
				SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
				startDate = st.format(new Date());
			}
			startDate = startDate.substring(0, 10);
			startDate = startDate.replace("-", "");
			// 转换成指定文件后缀 XXXX.log->XXXX_20141121.log
			nameFile = nameFile.replace(".log", "_" + startDate + ".log");
		}
		// 日志路径是逻辑流日志(batch.log) 或者 批量监控日志(batchMonitor.log) 或者 额度日志(crd.log)
		List<String> retList = new ArrayList<String>();
		InputStream inputStream = null;
		LineNumberReader reader = null;
		String text = "";
		// LogUtil.logDebug("读取日志的路径：{0}，日期：{1}", null, nameFile,startDate);
		try {
			inputStream = new FileInputStream(new File(nameFile));
			if (nameFile.contains("dataDrawOut")) {
				reader = new LineNumberReader(new InputStreamReader(inputStream, "GBK"));
			} else {
				reader = new LineNumberReader(new InputStreamReader(inputStream));
			}
			int lines = getTotalLines(new File(nameFile)); // 总行数
			// LogUtil.logDebug("日志总行数：{0}", null, lines);
			if (lines == 0) {
				return "该路径【" + nameFile + "】下的日志文件为空！";
			}

			String line = reader.readLine();
			int showLines = 500;
			int start = lines - showLines;
			int readline = 0;

			if (lines < showLines) {
				text = text.concat(new String(line.getBytes(), ("UTF-8"))).concat("\n\r");
			}
			while (line != null) {
				readline++;
				if (readline < start) {
					reader.readLine();
				} else {
					line = reader.readLine();
					if (line == null || line == "") {// 处理文件末尾是空行这种情况
						break;
					}
					text = text.concat(new String(line.getBytes(), ("UTF-8"))).concat("\n\r");
				}
			}
			System.out.println(text);
			inputStream.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return text;
	}

	@Bizlet("获取文件行数")
	public static int getTotalLines(File file) throws IOException {
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);
		String line = reader.readLine();
		int lines = 0;
		while (line != null) {
			lines++;
			line = reader.readLine();
		}
		reader.close();
		in.close();
		return lines;
	}

	public static void main(String[] aa) {
		// String path=new
		// String("C:/work/BOS/Platform/apps_config/default/work_temp/logs/crd.log");
		// readTextFromEnd(path,"utf-8");
		// dataReader(path);

		// 第一个参数：path /crmsshare/CRMS/logs/loadData.log
		String path = "F:/loadData.log";

		// 第二个参数：startDate
		// 日期处理 2014-11-21 20:13:52->20141121
		String startDate = "2014-11-21 20:13:52";
		startDate = startDate.substring(0, 10);
		startDate = startDate.replace("-", "");
		// 组合成指定文件后缀 XXXX.log->XXXX_20141121.log
		path = path.replace(".log", "_" + startDate + ".log");

		// /crmsshare/CRMS/logs/loadData_20141121.log
		// String
		// text=dataReader("F:/Primeton1/Platform/apps_config/default/work_temp/logs/loadData.log","2014-11-21 20:13:52");
		SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
		startDate = st.format(new Date());
		System.out.println(startDate);

	}

	@Bizlet("获取shell文件的绝对路径")
	public static String getShellFilePath(String fileName) {

		return getBatchPath() + File.separator + fileName;

	}

	@Bizlet("获取shell文件的绝对路径(应用外)")
	public static String getShellFilePath2(String fileName) {
		String shellPath = ConfigurationUtil.getUserConfigSingleValue("CustomConfig", "System", "shellPath");
		return shellPath + File.separator + fileName;

	}
	
	@Bizlet("获取配置表值")
	public static String getDBConfigVal(String cfgGroup,String cfgKey,String getVal) {
		String cfgValue = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("cfgGroup", cfgGroup);
		hm.put("cfgKey", cfgKey);
		Object[] confs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.commonutil.queryPubConfig", hm);
		if(confs.length == 1){
			hm = (HashMap<String, String>) confs[0];
			cfgValue = hm.get(getVal == null || "".equals(getVal) ? "CFG_VALUE" : getVal);
		}
		if(confs.length == 0 || cfgValue == null || "".equals(cfgValue)){
			throw new EOSException("获取配置表值失败！【"+cfgGroup+"】【"+cfgKey+"】");
		}
		return cfgValue;
	}
	
	@Bizlet("获取配置表值")
	public static String getDBConfigVal(String cfgGroup,String cfgKey) {
		return getDBConfigVal(cfgGroup, cfgKey,null);
	}
	
	@Bizlet("执行外部脚本")
	public static boolean execShell(String command) throws EOSException, IOException {
		LogUtil.logInfo("执行命令：" + command, null);
		Process process = Runtime.getRuntime().exec(command);
		boolean exitVal = true;
		try {
			// 输入流获取shell输出
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
			String line;
			while ((line = input.readLine()) != null) {
				if (!line.trim().equals("")) {
					LogUtil.logInfo("【执行日志：】" + line, null);
				}
			}

			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));
			while ((line = error.readLine()) != null) {
				if (!line.trim().equals("")) {
					LogUtil.logError("【错误日志：】" + line, null);
				}
				exitVal = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			process.destroy();
		}
		return exitVal;
	}
}
