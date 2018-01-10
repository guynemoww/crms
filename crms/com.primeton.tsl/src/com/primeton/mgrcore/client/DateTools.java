package com.primeton.mgrcore.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import com.bos.pub.EosUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.TableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.FileManager;
import commonj.sdo.DataObject;

@Bizlet("")
@SuppressWarnings("unused")
public class DateTools {

	public static String getTime20() {
		String date = null;
		Date time = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmssSSSSSS");
		date = df.format(time);
		return date;
	}

	public static String getTime8() {
		// String date = null;
		// Date time = new Date();
		// DateFormat df = new SimpleDateFormat("yyyyMMdd");
		// date = df.format(time);
		// // 核心营业日期暂为20170329
		// return "20170329";
		// //return date;
		Object[] summaryInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.querySysDate", "");
		Map map = (Map) summaryInfo[0];
		String perdate = new SimpleDateFormat("yyyyMMdd").format(map.get("OPERATING_DATE"));
		;
		return perdate;
	}

	public static String getVersionNo(String ver) {
		// ESB对应版本号填写规范，哪个环境就填哪个值
		// 测试环境ESB_SIT ESB_UAT 开发环境ESB_DEV 验证环境ESB_VERIFY 上线填写值ESB_ONLINE
		String verNo = null;
		if (ver.equals("1")) {
			verNo = "ESB_DEV";
		} else if (ver.equals("2")) {
			verNo = "ESB_SIT";
		} else if (ver.equals("3")) {
			verNo = "ESB_UAT";
		} else if (ver.equals("4")) {
			verNo = "ESB_VERIFY";
		} else if (ver.equals("5")) {
			verNo = "ESB_ONLINE";
		}
		return verNo;
	}

	public static String getReqSeqNo() {
		String reqSeqNo = getTime8() + UUID.randomUUID().toString().replace("-", "").substring(10, 18);
		return reqSeqNo;
	}

	public static String getFilePath() {
		// 配置文件地址
		ResourceBundle rb = ResourceBundle.getBundle("com/primeton/mgrcore/client/filePath");
		// 测试用本地路径
		String filePath = rb.getString("filePath");
		// 核算服务器文件路径
		// String plusServerFilePath = rb.getString("plusServerFilePath"+GitUtil.getBusiDateYYYYMMDD());
		return filePath;
	}

	@Bizlet("")
	public static BaseVO getResVo(DataObject obj) {
		BaseVO bvo = new BaseVO();
		// String baseVo = obj.getString("baseVO");
		// if(null != baseVo){
		// String[] str = baseVo.split(", ");
		// bvo.setErrCod(str[23].replaceAll("errCod=", ""));
		// bvo.setErrMsg(str[24].replaceAll("errMsg=", "").replace("]", ""));
		// }
		bvo = (BaseVO) obj.get("baseVO");
		return bvo;
	}

	@Bizlet("")
	public static DataObject[] getT1302Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取 放款清单-T1302返回文件内容
		// String filePath = DateTools.getFilePath();
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);
		DataObject[] da = getT1302DataObject(arry);
		return da;
	}

	// 转换为DataObject[]类型
	public static DataObject[] getT1302DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1302Entry");
			repayment.set("dueNum", line[1]);
			repayment.set("rlsDep", line[0]);
			repayment.set("brwName", line[3]);
			repayment.set("amt", line[4] + "元");
			repayment.set("begDate", line[5]);
			repayment.set("endDate", line[6]);
			repayment.set("norItrRate", line[7] + "%");
			repayment.set("delItrRate", line[8] + "%");
			repayment.set("curPrmPayTyp", line[9]);
			obj[i] = repayment;
		}
		return obj;
	}

	@Bizlet("")
	public static DataObject[] getT1303Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取 还款清单-T1303 返回文件内容
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);
		DataObject[] da = getT1303DataObject(arry);
		return da;
	}

	public static DataObject[] getT1303DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1303Entry");
			repayment.set("rcvDate", line[0]);
			repayment.set("opnDep", line[1]);
			repayment.set("dueNum", line[2]);
			repayment.set("brwName", line[3]);
			repayment.set("payPrimAcct", line[4]);
			repayment.set("payPrimName", line[5]);
			repayment.set("payTyp", line[6]);
			repayment.set("begDate", line[7]);
			repayment.set("endDate", line[8]);
			repayment.set("padUpPrn", line[9]);
			repayment.set("padUpPentPrn", line[10]);
			repayment.set("padUpNorItrIn", line[11]);
			repayment.set("padUpDftItrIn", line[12]);
			repayment.set("padUpPnsItrIn", line[13]);
			repayment.set("padUpCpdItrIn", line[14]);
			repayment.set("padUpNorItrOut", line[15]);
			repayment.set("padUpDftItrOut", line[16]);
			repayment.set("padUpPnsItrOut", line[17]);
			repayment.set("padUpCpdItrOut", line[18]);
			repayment.set("padUpPentIcm", line[19]);
			repayment.set("padUpOftPrn", line[20]);
			repayment.set("padUpOftItr", line[21]);
			repayment.set("oftAcrItrBal", line[22]);
			obj[i] = repayment;
		}
		return obj;
	}

	@Bizlet("")
	public static DataObject[] getT1304Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取 结息清单-T1304 返回文件内容
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);
		DataObject[] da = getT1304DataObject(arry);
		return da;
	}

	// 转换为DataObject[]类型
	public static DataObject[] getT1304DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1304Entry");
			repayment.set("rcvDate", line[0]);// 还款日期
			repayment.set("dueNum", line[1]);// 借据编号
			repayment.set("brwName", line[2]);// 客户名称
			repayment.set("currCod", line[3]);// 币种
			repayment.set("rcvNorItrIn", line[4] + "元");// 表内正常利息
			repayment.set("rcvDftItrIn", line[5] + "元");// 表内拖欠利息
			repayment.set("rcvPnsItrIn", line[6] + "元");// 表内罚息
			repayment.set("rcvCpdItrIn", line[7] + "元");// 表内复利
			repayment.set("rcvNorItrOut", line[8] + "元");// 表外正常利息
			repayment.set("rcvDftItrOut", line[9] + "元");// 表外拖欠利息
			repayment.set("rcvPnsItrOut", line[10] + "元");// 表外罚息
			repayment.set("rcvCpdItrOut", line[11] + "元");// 表外复利
			repayment.set("oftAcrItrBal", line[12] + "元");// 核销收回应计利息
			obj[i] = repayment;
		}
		return obj;
	}

	@Bizlet("")
	public static DataObject[] getT1305Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取 结清证明-T1305 返回文件内容
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);

		DataObject[] da = getT1305DataObject(arry);
		return da;
	}

	// 转换为DataObject[]类型
	public static DataObject[] getT1305DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1305Entry");
			repayment.set("rcvDate", line[0]);// 结清日期
			repayment.set("opnDep", line[1]);// 机构号
			repayment.set("dueNum", line[2]);// 借据编号
			repayment.set("brwName", line[3]);// 客户名称
			repayment.set("amt", line[4] + "元");// 本金
			repayment.set("begDate", line[5]);// 发放日期
			repayment.set("endDate", line[6]);// 到期日期
			obj[i] = repayment;
		}
		return obj;
	}

	@Bizlet("")
	public static DataObject[] getT1306Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取T1305返回文件内容
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);

		DataObject[] da = getT1306DataObject(arry);
		return da;
	}

	// 转换为DataObject[]类型
	public static DataObject[] getT1306DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1306Entry");
			repayment.set("dueNum", line[0]);// 借据编号
			repayment.set("rcvDate", line[1]);// 计提日期
			repayment.set("opnDep", line[2]);// 机构
			repayment.set("brwName", line[3]);// 借款人名称
			repayment.set("orgPrvIpr", line[4] + "元");// 原减值准备金额(元)
			repayment.set("iprBase", line[5] + "元");// 减值基数(元)
			repayment.set("prop", line[6] + "%");// 减值比例(%)
			repayment.set("nowPrvIpr", line[7] + "元");// 提取减值金额(元)
			repayment.set("currCod", line[8]);// 币种
			repayment.set("busCod", line[9]);// 业务别
			repayment.set("orgClsFlg", line[10]);// 上月五级分类结果
			repayment.set("nowClsFlg", line[11]);// 本月五级分类结果
			obj[i] = repayment;
		}
		return obj;
	}

	@Bizlet("")
	public static DataObject[] getT1308Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取T1305返回文件内容
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);

		DataObject[] da = getT1308DataObject(arry);
		return da;
	}

	// 转换为DataObject[]类型
	public static DataObject[] getT1308DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1308Entry");
			repayment.set("trnDep", line[0]);// 放款机构
			repayment.set("dueNum", line[1]);// 借据编号
			repayment.set("amt", line[2]);// 放款金额
			repayment.set("begDate", line[3]);// 贷款起期
			repayment.set("endDate", line[4]);// 贷款止期
			repayment.set("clsFlg", line[5]);// 五级分类
			repayment.set("discProcType", line[6]);// 贴息类型
			repayment.set("discType", line[7]);// 贴息方式
			repayment.set("protNum", line[8]);// 协议号
			repayment.set("discAccTyp", line[9]);// 贴息账号类型
			repayment.set("discAccOpnDep", line[10]);// 贴息主体账号开户机构
			repayment.set("discAcc", line[11]);// 贴息账号
			repayment.set("discAccNm", line[12]);// 贴息账户名称
			repayment.set("discRate", line[13] + "%");// 贴息率
			repayment.set("totAmtItr", line[14] + "元");// 正常利息
			repayment.set("brwItrPay", line[15] + "元");// 借款人应付利息
			repayment.set("rcvDiscAmt", line[16] + "元");// 应还贴息
			obj[i] = repayment;
		}
		return obj;
	}

	@Bizlet("")
	public static DataObject[] getT1301Obj(String rltFileDir, String rltFile) throws EOSException {
		// 获取T1305返回文件内容
		FileManager fileManager = new FileManager();
		fileManager.fileDown(rltFileDir.substring(rltFileDir.indexOf("discFiles")) + rltFile, "aplus/" + rltFile);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/" + rltFile;
		ArrayList<String[]> arry = DateTools.readTxtFile(loanFilePath);

		DataObject[] da = getT1301DataObject(arry);
		return da;
	}

	// 转换为DataObject[]类型
	public static DataObject[] getT1301DataObject(ArrayList<String[]> array) {
		DataObject[] obj = new DataObject[array.size()];
		for (int i = 0; i < array.size(); i++) {
			String[] line = array.get(i);
			DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1301Entry");
			repayment.set("rcvDate", line[0]);// 交易日期
			repayment.set("opnDep", line[1]);// 开户机构
			repayment.set("dueNum", line[2]);// 借据编号
			repayment.set("brwName", line[3]);// 客户名称
			repayment.set("amt", line[4] + "元");// 放款/还款金额（元）
			repayment.set("payTyp", line[5]);// 交易类型
			repayment.set("begDate", line[6]);// 发放日期
			repayment.set("endDate", line[7]);// 到期日期
			repayment.set("padUpPrn", line[8] + "元");// 还本金额
			repayment.set("padUpPentPrn", line[9] + "元");// 提前还本金额
			repayment.set("padUpNorItrIn", line[10] + "元");// 归还表内正常利息金额
			repayment.set("padUpDftItrIn", line[11] + "元");// 归还表内拖欠利息金额
			repayment.set("padUpPnsItrIn", line[12] + "元");// 归还表内罚息
			repayment.set("padUpCpdItrIn", line[13] + "元");// 归还表内复利
			repayment.set("padUpNorItrOut", line[14] + "元");// 归还表外正常利息金额
			repayment.set("padUpDftItrOut", line[15] + "元");// 归还表外拖欠利息金额
			repayment.set("padUpPnsItrOut", line[16] + "元");// 归还表外罚息
			repayment.set("padUpCpdItrOut", line[17] + "元");// 归还表外复利
			repayment.set("padUpPentIcm", line[18] + "元");// 提前还款违约金
			repayment.set("padUpOftPrn", line[19] + "元");// 核销收回本金
			repayment.set("padUpOftItr", line[20] + "元");// 核销收回利息
			repayment.set("oftAcrItrBal", line[21] + "元");// 核销收回应计利息
			repayment.set("padUpDiscAmt", line[22] + "元");// 归还贴息金额
			repayment.set("voucherType", line[23]);// 凭证类型
			repayment.set("summary", line[24]);// 摘要
			repayment.set("stan", line[25]);// 流水号
			repayment.set("opr", line[26]);// 操作员
			obj[i] = repayment;
		}
		return obj;
	}

	// 解析文件
	public static ArrayList<String[]> readTxtFile(String filePath) {
		ArrayList<String[]> array = new ArrayList<String[]>();
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int len = 0;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] aa = lineTxt.split("\\");
					array.add(aa);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return array;
	}

	@Bizlet("查询放款信息")
	public DataObject getLoanInfo(String dueNum) {
		Map map = new HashMap();
		map.put("dueNum", dueNum);
		Object[] summaryInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.rights.funds.queryCrmsLoanInfo", map);
		Object[] summaryZhInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.rights.funds.queryCrmsZhInfo", map);// 还款账户
		DataObject summaryObj = (DataObject) summaryInfo[0];
		DataObject summaryZhObj = (DataObject) summaryZhInfo[0];
		DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1311Entry");
		summary.set("ecifNo", summaryObj.get("ECIFNO"));
		summary.set("partyName", summaryObj.get("PARTYNAME"));
		summary.set("summaryNum", summaryObj.get("SUMMARYNUM"));
		summary.set("productName", summaryObj.get("PRODUCTNAME"));
		summary.set("contractNum", summaryObj.get("CONTRACTNUM"));
		summary.set("currencyCd", summaryObj.get("CURRENCYCD"));
		summary.set("contractAmt", summaryObj.get("CONTRACTAMT"));
		summary.set("yearRate", summaryObj.get("YEARRATE") + "%");
		summary.set("beginDate", summaryObj.get("BEGINDATE"));
		summary.set("endDate", summaryObj.get("ENDDATE"));
		summary.set("summaryAmt", summaryObj.get("SUMMARYAMT"));
		summary.set("repayType", summaryObj.get("REPAYTYPE"));
		summary.set("fkzh", summaryObj.get("FKZH"));
		summary.set("loanOrg", summaryObj.get("LOANORG"));
		summary.set("hkzh", summaryZhObj.get("HKZH"));
		return summary;
	}

	@Bizlet("查询保函信息")
	public DataObject getBhLoanInfo(String dueNum) {
		Map map = new HashMap();
		map.put("dueNum", dueNum);
		Object[] summaryInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.rights.funds.queryBhCrmsLoanInfo", map);
		DataObject summaryObj = (DataObject) summaryInfo[0];
		DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1311Entry");
		summary.set("ecifNo", summaryObj.get("ECIFNO"));
		summary.set("partyName", summaryObj.get("PARTYNAME"));
		summary.set("summaryNum", summaryObj.get("SUMMARYNUM"));
		summary.set("productName", summaryObj.get("PRODUCTNAME"));
		summary.set("contractNum", summaryObj.get("CONTRACTNUM"));
		summary.set("currencyCd", summaryObj.get("CURRENCYCD"));
		summary.set("contractAmt", summaryObj.get("CONTRACTAMT"));
		summary.set("yearRate", summaryObj.get("YEARRATE") + "%");
		summary.set("beginDate", summaryObj.get("BEGINDATE"));
		summary.set("endDate", summaryObj.get("ENDDATE"));
		summary.set("summaryAmt", summaryObj.get("SUMMARYAMT"));
		summary.set("repayType", summaryObj.get("REPAYTYPE"));
		summary.set("fkzh", summaryObj.get("FKZH"));
		summary.set("loanOrg", summaryObj.get("LOANORG"));
		return summary;
	}

	@SuppressWarnings("unchecked")
	@Bizlet("委托贷款回单打印")
	public DataObject getWTDKHD(String dueNum, String rcvDate, String stan) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dueNum", dueNum);
		map.put("summaryNum", dueNum);
		map.put("rcvDate", rcvDate);
		map.put("stan", stan);
		Object[] loans = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryWtdkInfo", map);
		if (loans == null || loans.length == 0) {
			return null;
		}
		DataObject summary = EosUtil.createDataObject();
		Map<String, Object> loanMap = (Map<String, Object>) loans[0];
		summary.set("CURRENCY_CD", loanMap.get("CURRENCY_CD"));
		summary.set("WTR_NAME", loanMap.get("WTR_NAME"));
		summary.set("OTHER_NAME", loanMap.get("OTHER_NAME"));
		summary.set("OTHER_ACCT", loanMap.get("OTHER_ACCT"));
		Object[] zhs = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryWtrAccInfo", map);//
		Object[] repays = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.utp.rights.funds.queryRepayWitness", map);
		if (repays != null && repays.length > 0) {
			Object[][] datas = { { "gh_bj", "BJ", BigDecimal.ZERO },//
					{ "gh_lx", "LX", BigDecimal.ZERO } };
			for (Object data : repays) {
				Map<String, Object> repay = (Map<String, Object>) data;
				for (Object[] d : datas) {
					d[2] = ((BigDecimal) d[2]).add((BigDecimal) repay.get(d[1]));
				}
			}
			summary.set("PRN_AMT", GitUtil.getMoney((BigDecimal) datas[0][2]));
			summary.set("ITR_AMT", GitUtil.getMoney((BigDecimal) datas[1][2]));
			summary.set("STAN", ((Map<String, Object>) repays[0]).get("STAN"));
			summary.set("RCV_DATE", ((Map<String, Object>) repays[0]).get("RCV_DATE"));
		}
		if (zhs != null && zhs.length > 0) {
			Map<String, Object> zhMap = (Map<String, Object>) zhs[0];
			summary.set("PRN_SETT_ACC", zhMap.get("PRN_SETT_ACC"));
			summary.set("ITR_SETT_ACC", zhMap.get("ITR_SETT_ACC"));
		}
		summary.set("CHANNEL", "信贷管理");
		return summary;
	}

	@SuppressWarnings("unchecked")
	@Bizlet("查询还款信息")
	public DataObject getHKPZ(String dueNum, String rcvDate, String stan) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dueNum", dueNum);
		map.put("summaryNum", dueNum);
		map.put("rcvDate", rcvDate);
		map.put("stan", stan);
		Object[] loanspj = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryPjjjinfo", map);
		DataObject loanObjpj = (DataObject) loanspj[0];
		Object[] loans=null;
		Object[] zhs=null;
		Object[] zhsZykh=null;//存单质押扣划---账户信息
		//判断当前的交易流水是不是质押扣划还款交易---质押扣划的还款需要使用存单客户账号作为打印信息
		zhsZykh = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryCrmsZhInfoForZykh", map);// 还款账户
		
		if("01008001".equals(loanObjpj.getString("PRODUCT_TYPE")) || "01008002".equals(loanObjpj.getString("PRODUCT_TYPE"))
				||"01008010".equals(loanObjpj.getString("PRODUCT_TYPE"))){
			loans = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryCrmsLoanInfoPJ", map);
			zhs = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryCrmsZhInfoPJ", map);// 还款账户
		}else{
			loans = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryCrmsLoanInfo", map);
			zhs = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryCrmsZhInfo", map);// 还款账户
		}
		if (zhsZykh != null && zhsZykh.length > 0) {//有扣划账户信息  表示当前打印的是扣划还款凭证
			zhs = zhsZykh;
		}
		
		Object[] repays = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.utp.rights.funds.queryRepayWitness", map);
		Object[] sybjlxs = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.utp.rights.funds.queryCurrSybjlx", map);
		DataObject summary = EosUtil.createDataObject();
		if (loans != null && loans.length > 0) {
			DataObject loanObj = (DataObject) loans[0];
			summary.set("ecifNo", loanObj.get("ECIFNO"));
			summary.set("partyName", loanObj.get("PARTYNAME"));
			summary.set("summaryNum", loanObj.get("SUMMARYNUM"));
			summary.set("productName", loanObj.get("PRODUCTNAME"));
			summary.set("contractNum", loanObj.get("CONTRACTNUM"));
			summary.set("currencyCd", loanObj.get("CURRENCYCD"));
			summary.set("summaryAmt", GitUtil.getMoney(loanObj.getBigDecimal("SUMMARYAMT")));
			summary.set("repayType", loanObj.get("REPAYTYPE"));
			summary.set("fkzh", loanObj.get("FKZH"));
			String orgNum = loanObj.getString("ORG_NUM");
			if (orgNum != null) {
				DataObject org = EntityUtil.searchEntity(TableName.OM_ORGANIZATION, "orgcode", orgNum);
				if (org != null) {
					summary.set("orgname", org.get("orgname"));
				}
			}
			summary.set("loanOrg", loanObj.get("LOANORG"));
		}
		if (zhs != null && zhs.length > 0) {
			DataObject zhObj = (DataObject) zhs[0];
			summary.set("hkzh", zhObj.get("HKZH"));
			summary.set("zhmc", zhObj.get("ZHMC"));
		}
		if (repays != null && repays.length > 0) {
			Object[][] datas = { { "gh_amt", "AMT", BigDecimal.ZERO }, { "gh_bj", "BJ", BigDecimal.ZERO },//
					{ "gh_lx", "LX", BigDecimal.ZERO }, { "gh_fx", "FX", BigDecimal.ZERO },//
					{ "gh_fl", "FL", BigDecimal.ZERO }, { "gh_tq", "TQ", BigDecimal.ZERO } };
			for (Object data : repays) {
				Map<String, Object> repay = (Map<String, Object>) data;
				for (Object[] d : datas) {
					d[2] = ((BigDecimal) d[2]).add((BigDecimal) repay.get(d[1]));
				}
			}
			for (Object[] d : datas) {
				summary.set((String) d[0], GitUtil.getMoney((BigDecimal) d[2]));
			}
			summary.set("stan", ((Map<String, Object>) repays[0]).get("STAN"));
		}
		if (sybjlxs != null && sybjlxs.length > 0) {
			Map<String, Object> sybjlx = (Map<String, Object>) sybjlxs[0];
			BigDecimal tq_bj = (BigDecimal) sybjlx.get("TQ_BJ");
			BigDecimal tq_lx = (BigDecimal) sybjlx.get("TQ_LX");
			BigDecimal bj = (BigDecimal) sybjlx.get("BJ");
			summary.set("sy_bj", GitUtil.getMoney(tq_bj));
			summary.set("sy_lx", GitUtil.getMoney(tq_lx));
			summary.set("jjye", GitUtil.getMoney(bj));
		}
		summary.set("rcvDate", rcvDate);
		return summary;
	}

	@Bizlet("借据信息")
	public DataObject getSummaryInfo(String dueNum) {
		Map map = new HashMap();
		map.put("dueNum", dueNum);
		Object[] summaryInfo = (Object[]) DatabaseExt.queryByNamedSql("aplus", "com.bos.utp.rights.funds.queryAplusSummaryInfo", map);
		DataObject summaryObj = (DataObject) summaryInfo[0];
		DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1309Entity");
		summary.set("dueNum", summaryObj.get("DUE_NUM"));
		summary.set("datePeriod", summaryObj.get("DATE_PERIOD"));
		summary.set("amt", summaryObj.get("AMT"));
		summary.set("resNor", summaryObj.get("RES_NOR"));
		summary.set("balNor", summaryObj.get("BAL_NOR"));
		return summary;
	}

	@Bizlet("交易明细信息")
	public DataObject[] getDeatilInfo(String dueNum) {
		Map map = new HashMap();
		map.put("dueNum", dueNum);
		Object[] detailInfo = (Object[]) DatabaseExt.queryByNamedSql("aplus", "com.bos.utp.rights.funds.queryAplusDetailInfo", map);
		DataObject[] obj = new DataObject[detailInfo.length];
		for (int i = 0; i < detailInfo.length; i++) {
			DataObject detailObj = (DataObject) detailInfo[i];
			DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1310Entry");
			detail.set("rcvDate", detailObj.get("RCV_DATE"));
			detail.set("currPeri", detailObj.get("CURR_PERI"));
			detail.set("endDate", detailObj.get("END_DATE"));
			detail.set("dateDays", detailObj.get("DATE_DAYS"));
			detail.set("padUpPrn", detailObj.get("PAD_UP_PRN"));
			detail.set("padPrn", detailObj.get("PAD_PRN"));
			detail.set("rcvNor", detailObj.get("RCV_NOR"));
			detail.set("rcvDft", detailObj.get("RCV_DFT"));
			detail.set("dftPrn", detailObj.get("DFT_PRN"));
			detail.set("allOtd", detailObj.get("ALL_OTD"));
			obj[i] = detail;
		}
		return obj;
	}

	@Bizlet("查询回单打印")
	public DataObject getDeatilPrintInfo(String dueNum) {
		Map map = new HashMap();
		map.put("dueNum", dueNum);
		Object[] detailInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.rights.funds.printDetailInfo", map);
		DataObject detailObj = (DataObject) detailInfo[0];
		DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1312Entry");
		if ("1403".equals(detailObj.get("ORGCODE"))) {
			detail.set("orgName", "纪念碑支行");
		} else {
			detail.set("orgName", detailObj.get("ORGNAME"));
		}
		detail.set("dueNum", detailObj.get("SUMMARY_NUM"));
		String busiTime = GitUtil.getBusiDateYYYYMMDD();
		detail.set("currTime", busiTime.substring(0, 4) + "年" + busiTime.substring(4, 6) + "月" + busiTime.substring(6, 8) + "日");
		detail.set("jjye", detailObj.get("JJYE"));
		detail.set("custName", detailObj.get("PARTY_NAME"));
		return detail;
	}

	@Bizlet("客户回单打印")
	public DataObject getCustDeatilPrintInfo(String loanId) throws EOSException {
		Map map = new HashMap();
		map.put("loanId", loanId);
		Object[] detailInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.rights.funds.printAccountDetailInfo", map);
		DataObject detailObj = (DataObject) detailInfo[0];
		DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1313Entry");
		detail.set("payName", detailObj.get("ZHMC"));
		detail.set("payAccount", detailObj.get("ZH"));
		detail.set("repayName", detailObj.get("ZHM1"));
		detail.set("repayAccount", detailObj.get("ZH1"));
		detail.set("amt", detailObj.get("LOAN_AMT"));
		if ("CNY".equals(detailObj.get("CURRENCY_CD"))) {
			detail.set("currAmt", "人民币");
		} else if ("EUR".equals(detailObj.get("CURRENCY_CD"))) {
			detail.set("currAmt", "欧元");
		} else if ("GBP".equals(detailObj.get("CURRENCY_CD"))) {
			detail.set("currAmt", "英镑");
		} else if ("HKD".equals(detailObj.get("CURRENCY_CD"))) {
			detail.set("currAmt", "香港元");
		} else if ("JPY".equals(detailObj.get("CURRENCY_CD"))) {
			detail.set("currAmt", "日元");
		} else if ("USD".equals(detailObj.get("CURRENCY_CD"))) {
			detail.set("currAmt", "美元");
		} else {
			throw new EOSException("不支持的币种");
		}
		return detail;
	}

	@Bizlet("客户回单受托支付方打印")
	public DataObject getCustOtherDeatilPrintInfo(String dueNum) throws EOSException {
		Map map = new HashMap();
		map.put("dueNum", dueNum);
		Object[] loans = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryStzfCrmsLoanInfo", map);
		// Object[] loans1 = (Object[]) DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.utp.rights.funds.queryStzfkfsCrmsLoanInfo", map);
		DataObject detailObj = (DataObject) loans[0];
		// DataObject detailObj1 = (DataObject) loans1[0];
		DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1314Entry");
		detail.set("name", detailObj.get("ZHM1"));
		detail.set("acct", detailObj.get("ZH1"));
		detail.set("amt", detailObj.get("AMT1"));
		detail.set("currencyCd", detailObj.get("CURRENCYCD"));
		detail.set("othername", detailObj.get("PARTYNAME"));
		detail.set("otherAcct", detailObj.get("FKZH"));
		detail.set("beginDate", detailObj.get("BEGINDATE"));
		detail.set("rcvstan", detailObj.get("RCNSTAN"));
		detail.set("channel", "信贷管理");
		if (null == detailObj.get("ZHM2") || "".equals(detailObj.get("ZHM2"))) {
			detail.set("surityName", "");
		} else {
			detail.set("surityName", detailObj.get("ZHM2"));
		}
		if (null == detailObj.get("ZH2") || "".equals(detailObj.get("ZH2"))) {
			detail.set("surityAcct", "");
		} else {
			detail.set("surityAcct", detailObj.get("ZH2"));
		}
		if (null == detailObj.get("AMT2") || "".equals(detailObj.get("AMT2"))) {
			detail.set("surityAmt", "");
		} else {
			detail.set("surityAmt", detailObj.get("AMT2"));
		}
		return detail;
	}

	@Bizlet("审批意见打印")
	public DataObject[] getWorkFlow(String processInstId) {
		Map map = new HashMap();
		map.put("processInstId", processInstId);
		Object[] detailInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.bps.dataset.query.queryWorkItemsByProcessId", map);
		DataObject[] obj = new DataObject[detailInfo.length];
		SimpleDateFormat perdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < detailInfo.length; i++) {
			DataObject detailObj = (DataObject) detailInfo[i];
			DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1315Entry");
			detail.set("performtime", perdate.format(detailObj.get("performtime")));
			detail.set("userName", detailObj.get("userName"));
			detail.set("postName", detailObj.get("orgName") + "-" + detailObj.get("postName"));
			detail.set("nextUsersName", detailObj.get("nextUsersName"));
			if (detailObj.get("nextPostName") == null || "".equals(detailObj.get("nextPostName"))) {
				detail.set("nextPostName", "");
			} else {
				detail.set("nextPostName", detailObj.get("nextOrgName") + "-" + detailObj.get("nextPostName"));
			}
			String conclusion = (String) detailObj.get("conclusion");
			if ("1".equals(conclusion)) {
				conclusion = "同意";
			} else if ("2".equals(conclusion)) {
				conclusion = "不同意";
			} else if ("99".equals(conclusion)) {
				conclusion = "退回";
			}
			detail.set("conclusion", conclusion);
			detail.set("opinion", detailObj.get("opinion"));
			obj[i] = detail;
		}
		return obj;
	}
}
