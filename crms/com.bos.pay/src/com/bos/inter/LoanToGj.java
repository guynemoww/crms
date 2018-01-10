package com.bos.inter;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.EsbSocketService;
import com.bos.pub.socket.service.request.base.EsbAppHeadRq;
import com.bos.pub.socket.util.EsbSocketUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.crmsgj.GJS01501030000001Req;
import com.primeton.crmsgj.GJS01501030000001Res;
import com.primeton.crmsgj.GJS01501030000006Req;
import com.primeton.crmsgj.GJS01501030000006Res;
import com.primeton.crmsgj.GJS01501110000002Req;
import com.primeton.crmsgj.GJS01501110000002Res;
import com.primeton.crmsgj.GJS01501110000004Req;
import com.primeton.crmsgj.GJS01501110000004Res;
import com.primeton.crmsgj.client.CrmsMgrCallGjImpl;
import com.primeton.crmsgj.client.CrmsMgrCallGjProxy;
import commonj.sdo.DataObject;

public class LoanToGj {
	/**
	 * 信用证放款
	* @Title: jkxyz 
	* @Description: 信用证放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月6日 上午10:04:44 
	* @version V1.0
	 */
	public void jkxyz(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForXyzLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		
		DataObject xyz = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq05001000001BODY01");
		xyz = db;
		EsbSocketUtil.setDataObject(xyz, "esbBodyGjRqMrgnArrays", bzjs);
		/*//字典转换
		if("CNY".equals(xyz.get("ccyTp"))){
			xyz.set("ccyTp", "01");
		}*/
		//即远期
		if("1".equals(xyz.get("spotFwdInd"))){
			xyz.set("spotFwdInd", "0");
		}else if("2".equals(xyz.get("spotFwdInd"))){
			xyz.set("spotFwdInd", "1");
		}
		/*xyz.set("rmtInstNo", "61101");
		xyz.set("eCIFCstNo", "1234567890");
		xyz.set("spotFwdInd", "0");
		xyz.set("benfNm", "李文");*/
		
		//担保方式
		if("01".equals(xyz.get("mainGryTy"))){
			xyz.set("mainGryTy", "2");
		}else if("02".equals(xyz.get("mainGryTy"))){
			xyz.set("mainGryTy", "4");
		}else if("03".equals(xyz.get("mainGryTy"))){
			xyz.set("mainGryTy", "3");
		}else if("04".equals(xyz.get("mainGryTy"))){
			xyz.set("mainGryTy", "1");
		}else if("05".equals(xyz.get("mainGryTy"))){
			xyz.set("mainGryTy", "5");
		}
		
		//保证金
		DataObject bzjbl = (DataObject)bzjbls[0];
		xyz.set("mrgnPct", bzjbl.get("BZJBL"));
		xyz.set("mrgnNum", bzjs.length);
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ05001000001BODY01",iEsbAppHeadRq, xyz);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}
	}
	/**
	 * 进口保函
	* @Title: jkbh 
	* @Description: 进口保函放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月6日 上午10:05:39 
	* @version V1.0
	 */
	public void jkbh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForBhLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		
		DataObject bh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq05001000002BODY03");
		bh = db;
		EsbSocketUtil.setDataObject(bh, "esbBodyGjRqMrgnArrays", bzjs);
		//字典转换
		/*if("CNY".equals(bh.get("ccyTp"))){
			bh.set("ccyTp", "01");
		}
		
		bh.set("rmtInstNo", "61101");*/
		bh.set("bckLCInd", 'N');//备用信用证默认为N
		
		
		//担保方式
		if("01".equals(bh.get("mainGryTy"))){
			bh.set("mainGryTy", "2");
		}else if("02".equals(bh.get("mainGryTy"))){
			bh.set("mainGryTy", "4");
		}else if("03".equals(bh.get("mainGryTy"))){
			bh.set("mainGryTy", "3");
		}else if("04".equals(bh.get("mainGryTy"))){
			bh.set("mainGryTy", "1");
		}else if("05".equals(bh.get("mainGryTy"))){
			bh.set("mainGryTy", "5");
		}
		
		
		//保函
		DataObject bzjbl = (DataObject)bzjbls[0];
		bh.set("mrgnPct", bzjbl.get("BZJBL"));
		bh.set("mrgnNum", bzjs.length);
		bh.set("bckLCInd", "N");
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ05001000002BODY03",iEsbAppHeadRq, bh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}
	}
	/**
	 * 出口信用证押汇
	* @Title: ckxyzyh 
	* @Description: 放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月10日 下午3:47:39 
	* @version V1.0
	 */
	public void ckxyzyh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForCkxyzyhLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		GJS01501030000001Req ckxyzyh = (GJS01501030000001Req)ob[0];
		/*Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		DataObject ckxyzyh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000001BODY01");*/
		//请求参数
		//DataObject ckxyzyh = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		//ckxyzyh = db;
		ckxyzyh.setPrdCode("030203");//产品代码
		ckxyzyh.setProSubTp(null);//产品子类型
		ckxyzyh.setBusiCode(null);//业务编号---议付号
		if(ckxyzyh.getCurrency().equals("CNY")){
			ckxyzyh.setCurrency("01");
		}else if(ckxyzyh.getCurrency().equals("FRF")){//法国法郎
			ckxyzyh.setCurrency("250");
		}else if(ckxyzyh.getCurrency().equals("DEM")){//德国马克
			ckxyzyh.setCurrency("276");
		}else if(ckxyzyh.getCurrency().equals("HKD")){//港币
			ckxyzyh.setCurrency("13");
		}else if(ckxyzyh.getCurrency().equals("ITL")){//意大利里拉
			ckxyzyh.setCurrency("380");
		}else if(ckxyzyh.getCurrency().equals("JPY")){//日元
			ckxyzyh.setCurrency("27");
		}else if(ckxyzyh.getCurrency().equals("KRW")){//韩国元
			ckxyzyh.setCurrency("410");
		}else if(ckxyzyh.getCurrency().equals("MOP")){//澳门元
			ckxyzyh.setCurrency("446");
		}else if(ckxyzyh.getCurrency().equals("MYR")){//马来西亚币
			ckxyzyh.setCurrency( "458");
		}else if(ckxyzyh.getCurrency().equals("NLG")){//荷兰盾
			ckxyzyh.setCurrency("528");
		}else if(ckxyzyh.getCurrency().equals("NZD")){//新西兰元 
			ckxyzyh.setCurrency("554");
		}else if(ckxyzyh.getCurrency().equals("AUD")){//澳洲元
			ckxyzyh.setCurrency("16");
		}else if(ckxyzyh.getCurrency().equals("NOK")){//挪威克朗
			ckxyzyh.setCurrency("578");
		}else if(ckxyzyh.getCurrency().equals("PHP")){//菲律宾比索
			ckxyzyh.setCurrency("608");
		}else if(ckxyzyh.getCurrency().equals("RUB")){//卢布
			ckxyzyh.setCurrency("643");
		}else if(ckxyzyh.getCurrency().equals("SGD")){//新加坡元
			ckxyzyh.setCurrency("702");
		}else if(ckxyzyh.getCurrency().equals("ESP")){//西班牙比塞塔
			ckxyzyh.setCurrency("724");
		}else if(ckxyzyh.getCurrency().equals("SEK")){//瑞典克朗
			ckxyzyh.setCurrency("752");
		}else if(ckxyzyh.getCurrency().equals("CHF")){//瑞士法郎
			ckxyzyh.setCurrency("756");
		}else if(ckxyzyh.getCurrency().equals("THB")){//泰国铢
			ckxyzyh.setCurrency("764");
		}else if(ckxyzyh.getCurrency().equals("GBP")){//英镑
			ckxyzyh.setCurrency("12");
		}else if(ckxyzyh.getCurrency().equals("USD")){//美元
			ckxyzyh.setCurrency("14");
		}else if(ckxyzyh.getCurrency().equals("EUR")){//欧元
			ckxyzyh.setCurrency("15");
		}else if(ckxyzyh.getCurrency().equals("ATS")){//奥地利先令
			ckxyzyh.setCurrency("040");
		}else if(ckxyzyh.getCurrency().equals("OTHER")){//其他
			ckxyzyh.setCurrency("999");
		}else if(ckxyzyh.getCurrency().equals("BEF")){//比利时法郎
			ckxyzyh.setCurrency("056");
		}else if(ckxyzyh.getCurrency().equals("CAD")){//加拿大元
			ckxyzyh.setCurrency("124");
		}else if(ckxyzyh.getCurrency().equals("TWD")){//新台湾币
			ckxyzyh.setCurrency("158");
		}else if(ckxyzyh.getCurrency().equals("DKK")){//丹麦克朗
			ckxyzyh.setCurrency("208");
		}else if(ckxyzyh.getCurrency().equals("FIM")){//芬兰马克
			ckxyzyh.setCurrency("246");
		}else{
			throw new EOSException("不支持的币种");
		}
		/*EsbSocketUtil.setDataObject(ckxyzyh, "esbBodyGjRqMrgnArrays", bzjs);
		//国结业务类型与个贷不一样
		ckxyzyh.set("bsnTp", "030203");
		//信保融资标签
		Object[] xbrz = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForXbrz", map);
		if(xbrz.length != 0){
			DataObject xb = (DataObject)xbrz[0];
			ckxyzyh.set("bsnTp", "030215");
			ckxyzyh.set("crInsfncTp", "BP");
			ckxyzyh.set("gntNo", xb.get("POLICY_NUM"));
		}
		
		//字典转换
		/*if("CNY".equals(ckxyzyh.get("ccyTp"))){
			ckxyzyh.set("ccyTp", "01");
		}
		ckxyzyh.set("rmtInstNo", "61101");*/
		//还款方式
		/*if("1200".equals((String)ckxyzyh.get("intSetlMth"))){
			ckxyzyh.set("intSetlMth", "1");
		}else if("21".equals((String)ckxyzyh.get("intSetlMth"))){
			ckxyzyh.set("intSetlMth", "0");
		}else{
			throw new EOSException("还款方式不合法!");
		}

		//ckxyzyh.set("doctyDcnInd", "0");//出口0 进口1
		
		//不要业务号码
		//ckxyzyh.set("bsnNo", "");
		
		//担保方式
		if("01".equals(ckxyzyh.get("mainGryTy"))){
			ckxyzyh.set("mainGryTy", "2");
		}else if("02".equals(ckxyzyh.get("mainGryTy"))){
			ckxyzyh.set("mainGryTy", "4");
		}else if("03".equals(ckxyzyh.get("mainGryTy"))){
			ckxyzyh.set("mainGryTy", "3");
		}else if("04".equals(ckxyzyh.get("mainGryTy"))){
			ckxyzyh.set("mainGryTy", "1");
		}else if("05".equals(ckxyzyh.get("mainGryTy"))){
			ckxyzyh.set("mainGryTy", "5");
		}
		//保证金
		DataObject bzjbl = (DataObject)bzjbls[0];
		ckxyzyh.set("mrgnPct", bzjbl.get("BZJBL"));
		ckxyzyh.set("mrgnNum", bzjs.length);*/
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(ckxyzyh);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	/**
	 * 出口TT押汇
	* @Title: ckttyh 
	* @Description: 放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月10日 下午3:59:49 
	* @version V1.0
	 */
	public void ckttyh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForCkttLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		
		DataObject ckttyh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000001BODY01");
		ckttyh = db;
		EsbSocketUtil.setDataObject(ckttyh, "esbBodyGjRqMrgnArrays", bzjs);
		//字典转换
		/*if("CNY".equals(ckttyh.get("ccyTp"))){
			ckttyh.set("ccyTp", "01");
		}
		ckttyh.set("rmtInstNo", "61101");*/
		//还款方式
		if("1200".equals((String)ckttyh.get("intSetlMth"))){
			ckttyh.set("intSetlMth", "1");
		}else if("21".equals((String)ckttyh.get("intSetlMth"))){
			ckttyh.set("intSetlMth", "0");
		}else{
			throw new EOSException("还款方式不合法!");
		}
		
		//国结业务类型与个贷不一样
		ckttyh.set("bsnTp", "030217");
		ckttyh.set("doctyDcnInd", "0");//出口0 进口1
		
		//担保方式
		if("01".equals(ckttyh.get("mainGryTy"))){
			ckttyh.set("mainGryTy", "2");
		}else if("02".equals(ckttyh.get("mainGryTy"))){
			ckttyh.set("mainGryTy", "4");
		}else if("03".equals(ckttyh.get("mainGryTy"))){
			ckttyh.set("mainGryTy", "3");
		}else if("04".equals(ckttyh.get("mainGryTy"))){
			ckttyh.set("mainGryTy", "1");
		}else if("05".equals(ckttyh.get("mainGryTy"))){
			ckttyh.set("mainGryTy", "5");
		}
		
		//信保融资标签
		Object[] xbrz = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForXbrz", map);
		if(xbrz.length != 0){
			DataObject xb = (DataObject)xbrz[0];
			ckttyh.set("bsnTp", "030215");
			ckttyh.set("crInsfncTp", "TT");
			ckttyh.set("gntNo", xb.get("POLICY_NUM"));
		}
		
		//保证金
		DataObject bzjbl = (DataObject)bzjbls[0];
		ckttyh.set("mrgnPct", bzjbl.get("BZJBL"));
		ckttyh.set("mrgnNum", bzjs.length);
		
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ02001000001BODY01",iEsbAppHeadRq, ckttyh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}
	}
	/**
	 * 出口托收押汇
	* @Title: cktsyh 
	* @Description: 放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月10日 下午4:04:20 
	* @version V1.0
	 */
	public void cktsyh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForCktsyhLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		//DataObject db = (DataObject)ob[0];
		/*Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		DataObject cktsyh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000001BODY01");*/
		//DataObject cktsyh = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		//cktsyh = db;
		//请求参数
		GJS01501030000001Req cktsyh = (GJS01501030000001Req)ob[0];
		cktsyh.setPrdCode("030205");//产品代码
		cktsyh.setProSubTp(null);//产品子类型
		cktsyh.setBusiCode(null);//业务编号---托收号
		if(cktsyh.getCurrency().equals("CNY")){
			cktsyh.setCurrency("01");
		}else if(cktsyh.getCurrency().equals("FRF")){//法国法郎
			cktsyh.setCurrency("250");
		}else if(cktsyh.getCurrency().equals("DEM")){//德国马克
			cktsyh.setCurrency("276");
		}else if(cktsyh.getCurrency().equals("HKD")){//港币
			cktsyh.setCurrency("13");
		}else if(cktsyh.getCurrency().equals("ITL")){//意大利里拉
			cktsyh.setCurrency("380");
		}else if(cktsyh.getCurrency().equals("JPY")){//日元
			cktsyh.setCurrency("27");
		}else if(cktsyh.getCurrency().equals("KRW")){//韩国元
			cktsyh.setCurrency("410");
		}else if(cktsyh.getCurrency().equals("MOP")){//澳门元
			cktsyh.setCurrency("446");
		}else if(cktsyh.getCurrency().equals("MYR")){//马来西亚币
			cktsyh.setCurrency( "458");
		}else if(cktsyh.getCurrency().equals("NLG")){//荷兰盾
			cktsyh.setCurrency("528");
		}else if(cktsyh.getCurrency().equals("NZD")){//新西兰元 
			cktsyh.setCurrency("554");
		}else if(cktsyh.getCurrency().equals("AUD")){//澳洲元
			cktsyh.setCurrency("16");
		}else if(cktsyh.getCurrency().equals("NOK")){//挪威克朗
			cktsyh.setCurrency("578");
		}else if(cktsyh.getCurrency().equals("PHP")){//菲律宾比索
			cktsyh.setCurrency("608");
		}else if(cktsyh.getCurrency().equals("RUB")){//卢布
			cktsyh.setCurrency("643");
		}else if(cktsyh.getCurrency().equals("SGD")){//新加坡元
			cktsyh.setCurrency("702");
		}else if(cktsyh.getCurrency().equals("ESP")){//西班牙比塞塔
			cktsyh.setCurrency("724");
		}else if(cktsyh.getCurrency().equals("SEK")){//瑞典克朗
			cktsyh.setCurrency("752");
		}else if(cktsyh.getCurrency().equals("CHF")){//瑞士法郎
			cktsyh.setCurrency("756");
		}else if(cktsyh.getCurrency().equals("THB")){//泰国铢
			cktsyh.setCurrency("764");
		}else if(cktsyh.getCurrency().equals("GBP")){//英镑
			cktsyh.setCurrency("12");
		}else if(cktsyh.getCurrency().equals("USD")){//美元
			cktsyh.setCurrency("14");
		}else if(cktsyh.getCurrency().equals("EUR")){//欧元
			cktsyh.setCurrency("15");
		}else if(cktsyh.getCurrency().equals("ATS")){//奥地利先令
			cktsyh.setCurrency("040");
		}else if(cktsyh.getCurrency().equals("OTHER")){//其他
			cktsyh.setCurrency("999");
		}else if(cktsyh.getCurrency().equals("BEF")){//比利时法郎
			cktsyh.setCurrency("056");
		}else if(cktsyh.getCurrency().equals("CAD")){//加拿大元
			cktsyh.setCurrency("124");
		}else if(cktsyh.getCurrency().equals("TWD")){//新台湾币
			cktsyh.setCurrency("158");
		}else if(cktsyh.getCurrency().equals("DKK")){//丹麦克朗
			cktsyh.setCurrency("208");
		}else if(cktsyh.getCurrency().equals("FIM")){//芬兰马克
			cktsyh.setCurrency("246");
		}else{
			throw new EOSException("不支持的币种");
		}
		//EsbSocketUtil.setDataObject(cktsyh, "esbBodyGjRqMrgnArrays", bzjs);
		//字典转换
		/*if("CNY".equals(cktsyh.get("ccyTp"))){
			cktsyh.set("ccyTp", "01");
		}
		cktsyh.set("rmtInstNo", "61101");*/
		//还款方式
		/*if("1200".equals((String)cktsyh.get("intSetlMth"))){
			cktsyh.set("intSetlMth", "1");
		}else if("21".equals((String)cktsyh.get("intSetlMth"))){
			cktsyh.set("intSetlMth", "0");
		}else{
			throw new EOSException("还款方式不合法!");
		}
		//国结业务类型与个贷不一样
		cktsyh.set("bsnTp", "030205");
		//cktsyh.set("doctyDcnInd", "0");//出口0 进口1
		
		//信保融资标签
		Object[] xbrz = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForXbrz", map);
		if(xbrz.length != 0){
			DataObject xb = (DataObject)xbrz[0];
			cktsyh.set("bsnTp", "030215");
			cktsyh.set("crInsfncTp", "OC");
			cktsyh.set("gntNo", xb.get("POLICY_NUM"));
		}
		
		//担保方式
		if("01".equals(cktsyh.get("mainGryTy"))){
			cktsyh.set("mainGryTy", "2");
		}else if("02".equals(cktsyh.get("mainGryTy"))){
			cktsyh.set("mainGryTy", "4");
		}else if("03".equals(cktsyh.get("mainGryTy"))){
			cktsyh.set("mainGryTy", "3");
		}else if("04".equals(cktsyh.get("mainGryTy"))){
			cktsyh.set("mainGryTy", "1");
		}else if("05".equals(cktsyh.get("mainGryTy"))){
			cktsyh.set("mainGryTy", "5");
		}
		//保证金
		DataObject bzjbl = (DataObject)bzjbls[0];
		cktsyh.set("mrgnPct", bzjbl.get("BZJBL"));
		cktsyh.set("mrgnNum", bzjs.length);
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ02001000001BODY01",iEsbAppHeadRq, cktsyh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}*/
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(cktsyh);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	/**
	 * 进口信用证押汇
	* @Title: jkxyzyh 
	* @Description: 放款接口 
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月10日 下午4:15:10 
	* @version V1.0
	 */
	public void jkxyzyh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkxyzLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		/*Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);*/
		//DataObject jkxyzyh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000001BODY01");
		//请求参数
		DataObject jkxyzyh = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		jkxyzyh = db;
		jkxyzyh.set("prdCode", "020035");//产品代码
		jkxyzyh.set("proSubTp",null);//产品子类型
		jkxyzyh.set("busiCode", null);//业务编号---来单号
		//币种转换
		if(jkxyzyh.get("currency").equals("CNY")){
			jkxyzyh.set("currency", "01");
		}else if(jkxyzyh.get("currency").equals("FRF")){//法国法郎
			jkxyzyh.set("currency", "250");
		}else if(jkxyzyh.get("currency").equals("DEM")){//德国马克
			jkxyzyh.set("currency", "276");
		}else if(jkxyzyh.get("currency").equals("HKD")){//港币
			jkxyzyh.set("currency", "13");
		}else if(jkxyzyh.get("currency").equals("ITL")){//意大利里拉
			jkxyzyh.set("currency", "380");
		}else if(jkxyzyh.get("currency").equals("JPY")){//日元
			jkxyzyh.set("currency", "27");
		}else if(jkxyzyh.get("currency").equals("KRW")){//韩国元
			jkxyzyh.set("currency", "410");
		}else if(jkxyzyh.get("currency").equals("MOP")){//澳门元
			jkxyzyh.set("currency", "446");
		}else if(jkxyzyh.get("currency").equals("MYR")){//马来西亚币
			jkxyzyh.set("currency", "458");
		}else if(jkxyzyh.get("currency").equals("NLG")){//荷兰盾
			jkxyzyh.set("currency", "528");
		}else if(jkxyzyh.get("currency").equals("NZD")){//新西兰元 
			jkxyzyh.set("currency", "554");
		}else if(jkxyzyh.get("currency").equals("AUD")){//澳洲元
			jkxyzyh.set("currency", "16");
		}else if(jkxyzyh.get("currency").equals("NOK")){//挪威克朗
			jkxyzyh.set("currency", "578");
		}else if(jkxyzyh.get("currency").equals("PHP")){//菲律宾比索
			jkxyzyh.set("currency", "608");
		}else if(jkxyzyh.get("currency").equals("RUB")){//卢布
			jkxyzyh.set("currency", "643");
		}else if(jkxyzyh.get("currency").equals("SGD")){//新加坡元
			jkxyzyh.set("currency", "702");
		}else if(jkxyzyh.get("currency").equals("ESP")){//西班牙比塞塔
			jkxyzyh.set("currency", "724");
		}else if(jkxyzyh.get("currency").equals("SEK")){//瑞典克朗
			jkxyzyh.set("currency", "752");
		}else if(jkxyzyh.get("currency").equals("CHF")){//瑞士法郎
			jkxyzyh.set("currency", "756");
		}else if(jkxyzyh.get("currency").equals("THB")){//泰国铢
			jkxyzyh.set("currency", "764");
		}else if(jkxyzyh.get("currency").equals("GBP")){//英镑
			jkxyzyh.set("currency", "12");
		}else if(jkxyzyh.get("currency").equals("USD")){//美元
			jkxyzyh.set("currency", "14");
		}else if(jkxyzyh.get("currency").equals("EUR")){//欧元
			jkxyzyh.set("currency", "15");
		}else if(jkxyzyh.get("currency").equals("ATS")){//奥地利先令
			jkxyzyh.set("currency", "040");
		}else if(jkxyzyh.get("currency").equals("OTHER")){//其他
			jkxyzyh.set("currency", "999");
		}else if(jkxyzyh.get("currency").equals("BEF")){//比利时法郎
			jkxyzyh.set("currency", "056");
		}else if(jkxyzyh.get("currency").equals("CAD")){//加拿大元
			jkxyzyh.set("currency", "124");
		}else if(jkxyzyh.get("currency").equals("TWD")){//新台湾币
			jkxyzyh.set("currency", "158");
		}else if(jkxyzyh.get("currency").equals("DKK")){//丹麦克朗
			jkxyzyh.set("currency", "208");
		}else if(jkxyzyh.get("currency").equals("FIM")){//芬兰马克
			jkxyzyh.set("currency", "246");
		}else{
			throw new EOSException("不支持的币种");
		}
		/*EsbSocketUtil.setDataObject(jkxyzyh, "esbBodyGjRqMrgnArrays", bzjs);*/
		//字典转换
		/*if("CNY".equals(jkxyzyh.get("ccyTp"))){
			jkxyzyh.set("ccyTp", "01");
		}
		jkxyzyh.set("rmtInstNo", "61101");*/
		//还款方式
		/*if("1200".equals((String)jkxyzyh.get("intSetlMth"))){
			jkxyzyh.set("intSetlMth", "1");
		}else if("21".equals((String)jkxyzyh.get("intSetlMth"))){
			jkxyzyh.set("intSetlMth", "0");
		}else{
			throw new EOSException("还款方式不合法!");
		}*/
		
		//国结业务类型与个贷不一样
		//jkxyzyh.set("bsnTp", "020035");
		//jkxyzyh.set("doctyDcnInd", "1");//出口0 进口1
		//担保方式
		/*if("01".equals(jkxyzyh.get("mainGryTy"))){
			jkxyzyh.set("mainGryTy", "2");
		}else if("02".equals(jkxyzyh.get("mainGryTy"))){
			jkxyzyh.set("mainGryTy", "4");
		}else if("03".equals(jkxyzyh.get("mainGryTy"))){
			jkxyzyh.set("mainGryTy", "3");
		}else if("04".equals(jkxyzyh.get("mainGryTy"))){
			jkxyzyh.set("mainGryTy", "1");
		}else if("05".equals(jkxyzyh.get("mainGryTy"))){
			jkxyzyh.set("mainGryTy", "5");
		}*/
		//保证金
		/*DataObject bzjbl = (DataObject)bzjbls[0];
		jkxyzyh.set("mrgnPct", bzjbl.get("BZJBL"));
		jkxyzyh.set("mrgnNum", bzjs.length);
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ02001000001BODY01",iEsbAppHeadRq, jkxyzyh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new EOSException(e1.getMessage());
		}*/
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = (GJS01501030000001Req)jkxyzyh;
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	/**
	 * 进口tt押汇
	* @Title: jkttyh 
	* @Description: 放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月10日 下午4:18:20 
	* @version V1.0
	 */
	public void jkttyh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForjkttyhLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		/*Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		DataObject jkttyh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000001BODY01");*/
		
		//请求参数
		DataObject jkttyh = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		jkttyh = db;
		jkttyh.set("prdCode", null);//产品代码
		jkttyh.set("proSubTp",null);//产品子类型
		jkttyh.set("busiCode", null);//业务编号
		//币种转换
		if(jkttyh.get("currency").equals("CNY")){
			jkttyh.set("currency", "01");
		}else if(jkttyh.get("currency").equals("FRF")){//法国法郎
			jkttyh.set("currency", "250");
		}else if(jkttyh.get("currency").equals("DEM")){//德国马克
			jkttyh.set("currency", "276");
		}else if(jkttyh.get("currency").equals("HKD")){//港币
			jkttyh.set("currency", "13");
		}else if(jkttyh.get("currency").equals("ITL")){//意大利里拉
			jkttyh.set("currency", "380");
		}else if(jkttyh.get("currency").equals("JPY")){//日元
			jkttyh.set("currency", "27");
		}else if(jkttyh.get("currency").equals("KRW")){//韩国元
			jkttyh.set("currency", "410");
		}else if(jkttyh.get("currency").equals("MOP")){//澳门元
			jkttyh.set("currency", "446");
		}else if(jkttyh.get("currency").equals("MYR")){//马来西亚币
			jkttyh.set("currency", "458");
		}else if(jkttyh.get("currency").equals("NLG")){//荷兰盾
			jkttyh.set("currency", "528");
		}else if(jkttyh.get("currency").equals("NZD")){//新西兰元 
			jkttyh.set("currency", "554");
		}else if(jkttyh.get("currency").equals("AUD")){//澳洲元
			jkttyh.set("currency", "16");
		}else if(jkttyh.get("currency").equals("NOK")){//挪威克朗
			jkttyh.set("currency", "578");
		}else if(jkttyh.get("currency").equals("PHP")){//菲律宾比索
			jkttyh.set("currency", "608");
		}else if(jkttyh.get("currency").equals("RUB")){//卢布
			jkttyh.set("currency", "643");
		}else if(jkttyh.get("currency").equals("SGD")){//新加坡元
			jkttyh.set("currency", "702");
		}else if(jkttyh.get("currency").equals("ESP")){//西班牙比塞塔
			jkttyh.set("currency", "724");
		}else if(jkttyh.get("currency").equals("SEK")){//瑞典克朗
			jkttyh.set("currency", "752");
		}else if(jkttyh.get("currency").equals("CHF")){//瑞士法郎
			jkttyh.set("currency", "756");
		}else if(jkttyh.get("currency").equals("THB")){//泰国铢
			jkttyh.set("currency", "764");
		}else if(jkttyh.get("currency").equals("GBP")){//英镑
			jkttyh.set("currency", "12");
		}else if(jkttyh.get("currency").equals("USD")){//美元
			jkttyh.set("currency", "14");
		}else if(jkttyh.get("currency").equals("EUR")){//欧元
			jkttyh.set("currency", "15");
		}else if(jkttyh.get("currency").equals("ATS")){//奥地利先令
			jkttyh.set("currency", "040");
		}else if(jkttyh.get("currency").equals("OTHER")){//其他
			jkttyh.set("currency", "999");
		}else if(jkttyh.get("currency").equals("BEF")){//比利时法郎
			jkttyh.set("currency", "056");
		}else if(jkttyh.get("currency").equals("CAD")){//加拿大元
			jkttyh.set("currency", "124");
		}else if(jkttyh.get("currency").equals("TWD")){//新台湾币
			jkttyh.set("currency", "158");
		}else if(jkttyh.get("currency").equals("DKK")){//丹麦克朗
			jkttyh.set("currency", "208");
		}else if(jkttyh.get("currency").equals("FIM")){//芬兰马克
			jkttyh.set("currency", "246");
		}else{
			throw new EOSException("不支持的币种");
		}
		/*EsbSocketUtil.setDataObject(jkttyh, "esbBodyGjRqMrgnArrays", bzjs);
		//字典转换
		/*if("CNY".equals(jkttyh.get("ccyTp"))){
			jkttyh.set("ccyTp", "01");
		}
		jkttyh.set("rmtInstNo", "61101");
		//还款方式
		if("1200".equals((String)jkttyh.get("intSetlMth"))){
			jkttyh.set("intSetlMth", "1");
		}else if("21".equals((String)jkttyh.get("intSetlMth"))){
			jkttyh.set("intSetlMth", "0");
		}else{
			throw new EOSException("还款方式不合法!");
		}
		//国结业务类型与个贷不一样
		jkttyh.set("bsnTp", "030217");
		jkttyh.set("doctyDcnInd", "1");//出口0 进口1
		//担保方式
		if("01".equals(jkttyh.get("mainGryTy"))){
			jkttyh.set("mainGryTy", "2");
		}else if("02".equals(jkttyh.get("mainGryTy"))){
			jkttyh.set("mainGryTy", "4");
		}else if("03".equals(jkttyh.get("mainGryTy"))){
			jkttyh.set("mainGryTy", "3");
		}else if("04".equals(jkttyh.get("mainGryTy"))){
			jkttyh.set("mainGryTy", "1");
		}else if("05".equals(jkttyh.get("mainGryTy"))){
			jkttyh.set("mainGryTy", "5");
		}*/
		//保证金
		/*DataObject bzjbl = (DataObject)bzjbls[0];
		jkttyh.set("mrgnPct", bzjbl.get("BZJBL"));
		jkttyh.set("mrgnNum", bzjs.length);
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ02001000001BODY01",iEsbAppHeadRq, jkttyh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}*/
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = (GJS01501030000001Req)jkttyh;
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	/**
	 * 进口代收押汇
	* @Title: jkdsyh 
	* @Description: 放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月10日 下午4:18:41 
	* @version V1.0
	 */
	public void jkdsyh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkdsyhLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		/*Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", (String)loanInfo.get("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		DataObject jkdsyh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000001BODY01");*/
		//请求参数
		DataObject jkdsyh = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		jkdsyh = db;
		//币种转换
		if(jkdsyh.get("currency").equals("CNY")){
			jkdsyh.set("currency", "01");
		}else if(jkdsyh.get("currency").equals("FRF")){//法国法郎
			jkdsyh.set("currency", "250");
		}else if(jkdsyh.get("currency").equals("DEM")){//德国马克
			jkdsyh.set("currency", "276");
		}else if(jkdsyh.get("currency").equals("HKD")){//港币
			jkdsyh.set("currency", "13");
		}else if(jkdsyh.get("currency").equals("ITL")){//意大利里拉
			jkdsyh.set("currency", "380");
		}else if(jkdsyh.get("currency").equals("JPY")){//日元
			jkdsyh.set("currency", "27");
		}else if(jkdsyh.get("currency").equals("KRW")){//韩国元
			jkdsyh.set("currency", "410");
		}else if(jkdsyh.get("currency").equals("MOP")){//澳门元
			jkdsyh.set("currency", "446");
		}else if(jkdsyh.get("currency").equals("MYR")){//马来西亚币
			jkdsyh.set("currency", "458");
		}else if(jkdsyh.get("currency").equals("NLG")){//荷兰盾
			jkdsyh.set("currency", "528");
		}else if(jkdsyh.get("currency").equals("NZD")){//新西兰元 
			jkdsyh.set("currency", "554");
		}else if(jkdsyh.get("currency").equals("AUD")){//澳洲元
			jkdsyh.set("currency", "16");
		}else if(jkdsyh.get("currency").equals("NOK")){//挪威克朗
			jkdsyh.set("currency", "578");
		}else if(jkdsyh.get("currency").equals("PHP")){//菲律宾比索
			jkdsyh.set("currency", "608");
		}else if(jkdsyh.get("currency").equals("RUB")){//卢布
			jkdsyh.set("currency", "643");
		}else if(jkdsyh.get("currency").equals("SGD")){//新加坡元
			jkdsyh.set("currency", "702");
		}else if(jkdsyh.get("currency").equals("ESP")){//西班牙比塞塔
			jkdsyh.set("currency", "724");
		}else if(jkdsyh.get("currency").equals("SEK")){//瑞典克朗
			jkdsyh.set("currency", "752");
		}else if(jkdsyh.get("currency").equals("CHF")){//瑞士法郎
			jkdsyh.set("currency", "756");
		}else if(jkdsyh.get("currency").equals("THB")){//泰国铢
			jkdsyh.set("currency", "764");
		}else if(jkdsyh.get("currency").equals("GBP")){//英镑
			jkdsyh.set("currency", "12");
		}else if(jkdsyh.get("currency").equals("USD")){//美元
			jkdsyh.set("currency", "14");
		}else if(jkdsyh.get("currency").equals("EUR")){//欧元
			jkdsyh.set("currency", "15");
		}else if(jkdsyh.get("currency").equals("ATS")){//奥地利先令
			jkdsyh.set("currency", "040");
		}else if(jkdsyh.get("currency").equals("OTHER")){//其他
			jkdsyh.set("currency", "999");
		}else if(jkdsyh.get("currency").equals("BEF")){//比利时法郎
			jkdsyh.set("currency", "056");
		}else if(jkdsyh.get("currency").equals("CAD")){//加拿大元
			jkdsyh.set("currency", "124");
		}else if(jkdsyh.get("currency").equals("TWD")){//新台湾币
			jkdsyh.set("currency", "158");
		}else if(jkdsyh.get("currency").equals("DKK")){//丹麦克朗
			jkdsyh.set("currency", "208");
		}else if(jkdsyh.get("currency").equals("FIM")){//芬兰马克
			jkdsyh.set("currency", "246");
		}else{
			throw new EOSException("不支持的币种");
		}
		/*EsbSocketUtil.setDataObject(jkdsyh, "esbBodyGjRqMrgnArrays", bzjs);
		//字典转换
		/*if("CNY".equals(jktsyh.get("ccyTp"))){
			jktsyh.set("ccyTp", "01");
		}
		jkdsyh.set("rmtInstNo", "61101");
		//还款方式
		if("1200".equals((String)jkdsyh.get("intSetlMth"))){
			jkdsyh.set("intSetlMth", "1");
		}else if("21".equals((String)jkdsyh.get("intSetlMth"))){
			jkdsyh.set("intSetlMth", "0");
		}else{
			throw new EOSException("还款方式不合法!");
		}
		//国结业务类型与个贷不一样
		jkdsyh.set("bsnTp", "030615");
		jkdsyh.set("doctyDcnInd", "0");//没有此标志默认0
		//担保方式
		if("01".equals(jkdsyh.get("mainGryTy"))){
			jkdsyh.set("mainGryTy", "2");
		}else if("02".equals(jkdsyh.get("mainGryTy"))){
			jkdsyh.set("mainGryTy", "4");
		}else if("03".equals(jkdsyh.get("mainGryTy"))){
			jkdsyh.set("mainGryTy", "3");
		}else if("04".equals(jkdsyh.get("mainGryTy"))){
			jkdsyh.set("mainGryTy", "1");
		}else if("05".equals(jkdsyh.get("mainGryTy"))){
			jkdsyh.set("mainGryTy", "5");
		}
		//保证金
		DataObject bzjbl = (DataObject)bzjbls[0];
		jkdsyh.set("mrgnPct", bzjbl.get("BZJBL"));
		jkdsyh.set("mrgnNum", bzjs.length);
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ02001000001BODY01",iEsbAppHeadRq, jkdsyh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}*/
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = (GJS01501030000001Req)jkdsyh;
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	
	/**
	 * 进口代付
	* @Title: jkdf 
	* @Description: 进口代付放款接口
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author shendl
	* @date 2017年5月12日 
	* @version V1.0
	 */
	public void jkdf(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkdfLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		//请求参数
		DataObject jkdf = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		jkdf = db;
		jkdf.set("prdCode", "020040");//产品代码
		/**
		 * 1、信用证进口代付2、代收进口代付3、TT项下进口代付
		 */
		jkdf.set("proSubTp",null);//产品子类型 
		jkdf.set("busiCode", null);//业务编号
		//币种转换
		if(jkdf.get("currency").equals("CNY")){
			jkdf.set("currency", "01");
		}else if(jkdf.get("currency").equals("FRF")){//法国法郎
			jkdf.set("currency", "250");
		}else if(jkdf.get("currency").equals("DEM")){//德国马克
			jkdf.set("currency", "276");
		}else if(jkdf.get("currency").equals("HKD")){//港币
			jkdf.set("currency", "13");
		}else if(jkdf.get("currency").equals("ITL")){//意大利里拉
			jkdf.set("currency", "380");
		}else if(jkdf.get("currency").equals("JPY")){//日元
			jkdf.set("currency", "27");
		}else if(jkdf.get("currency").equals("KRW")){//韩国元
			jkdf.set("currency", "410");
		}else if(jkdf.get("currency").equals("MOP")){//澳门元
			jkdf.set("currency", "446");
		}else if(jkdf.get("currency").equals("MYR")){//马来西亚币
			jkdf.set("currency", "458");
		}else if(jkdf.get("currency").equals("NLG")){//荷兰盾
			jkdf.set("currency", "528");
		}else if(jkdf.get("currency").equals("NZD")){//新西兰元 
			jkdf.set("currency", "554");
		}else if(jkdf.get("currency").equals("AUD")){//澳洲元
			jkdf.set("currency", "16");
		}else if(jkdf.get("currency").equals("NOK")){//挪威克朗
			jkdf.set("currency", "578");
		}else if(jkdf.get("currency").equals("PHP")){//菲律宾比索
			jkdf.set("currency", "608");
		}else if(jkdf.get("currency").equals("RUB")){//卢布
			jkdf.set("currency", "643");
		}else if(jkdf.get("currency").equals("SGD")){//新加坡元
			jkdf.set("currency", "702");
		}else if(jkdf.get("currency").equals("ESP")){//西班牙比塞塔
			jkdf.set("currency", "724");
		}else if(jkdf.get("currency").equals("SEK")){//瑞典克朗
			jkdf.set("currency", "752");
		}else if(jkdf.get("currency").equals("CHF")){//瑞士法郎
			jkdf.set("currency", "756");
		}else if(jkdf.get("currency").equals("THB")){//泰国铢
			jkdf.set("currency", "764");
		}else if(jkdf.get("currency").equals("GBP")){//英镑
			jkdf.set("currency", "12");
		}else if(jkdf.get("currency").equals("USD")){//美元
			jkdf.set("currency", "14");
		}else if(jkdf.get("currency").equals("EUR")){//欧元
			jkdf.set("currency", "15");
		}else if(jkdf.get("currency").equals("ATS")){//奥地利先令
			jkdf.set("currency", "040");
		}else if(jkdf.get("currency").equals("OTHER")){//其他
			jkdf.set("currency", "999");
		}else if(jkdf.get("currency").equals("BEF")){//比利时法郎
			jkdf.set("currency", "056");
		}else if(jkdf.get("currency").equals("CAD")){//加拿大元
			jkdf.set("currency", "124");
		}else if(jkdf.get("currency").equals("TWD")){//新台湾币
			jkdf.set("currency", "158");
		}else if(jkdf.get("currency").equals("DKK")){//丹麦克朗
			jkdf.set("currency", "208");
		}else if(jkdf.get("currency").equals("FIM")){//芬兰马克
			jkdf.set("currency", "246");
		}else{
			throw new EOSException("不支持的币种");
		}
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = (GJS01501030000001Req)jkdf;
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	
	/**
	 * 国际福费廷放款
	* @Title: gjfft 
	* @Description: 国际福费廷放款
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author shendl
	* @date 2017年5月12日 
	* @version V1.0
	 */
	public void gjfft(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForGjfftLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		//请求参数
		DataObject gjfft = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		gjfft = db;
		gjfft.set("prdCode", "030211");//产品代码
		/**
		 * 1、自行买入
		 * 2、转卖他行
		 * 3、包买他行
		 * 4、中介
		 */
		gjfft.set("proSubTp",null);//产品子类型  
		gjfft.set("busiCode", null);//业务编号
		//币种转换
		if(gjfft.get("currency").equals("CNY")){
			gjfft.set("currency", "01");
		}else if(gjfft.get("currency").equals("FRF")){//法国法郎
			gjfft.set("currency", "250");
		}else if(gjfft.get("currency").equals("DEM")){//德国马克
			gjfft.set("currency", "276");
		}else if(gjfft.get("currency").equals("HKD")){//港币
			gjfft.set("currency", "13");
		}else if(gjfft.get("currency").equals("ITL")){//意大利里拉
			gjfft.set("currency", "380");
		}else if(gjfft.get("currency").equals("JPY")){//日元
			gjfft.set("currency", "27");
		}else if(gjfft.get("currency").equals("KRW")){//韩国元
			gjfft.set("currency", "410");
		}else if(gjfft.get("currency").equals("MOP")){//澳门元
			gjfft.set("currency", "446");
		}else if(gjfft.get("currency").equals("MYR")){//马来西亚币
			gjfft.set("currency", "458");
		}else if(gjfft.get("currency").equals("NLG")){//荷兰盾
			gjfft.set("currency", "528");
		}else if(gjfft.get("currency").equals("NZD")){//新西兰元 
			gjfft.set("currency", "554");
		}else if(gjfft.get("currency").equals("AUD")){//澳洲元
			gjfft.set("currency", "16");
		}else if(gjfft.get("currency").equals("NOK")){//挪威克朗
			gjfft.set("currency", "578");
		}else if(gjfft.get("currency").equals("PHP")){//菲律宾比索
			gjfft.set("currency", "608");
		}else if(gjfft.get("currency").equals("RUB")){//卢布
			gjfft.set("currency", "643");
		}else if(gjfft.get("currency").equals("SGD")){//新加坡元
			gjfft.set("currency", "702");
		}else if(gjfft.get("currency").equals("ESP")){//西班牙比塞塔
			gjfft.set("currency", "724");
		}else if(gjfft.get("currency").equals("SEK")){//瑞典克朗
			gjfft.set("currency", "752");
		}else if(gjfft.get("currency").equals("CHF")){//瑞士法郎
			gjfft.set("currency", "756");
		}else if(gjfft.get("currency").equals("THB")){//泰国铢
			gjfft.set("currency", "764");
		}else if(gjfft.get("currency").equals("GBP")){//英镑
			gjfft.set("currency", "12");
		}else if(gjfft.get("currency").equals("USD")){//美元
			gjfft.set("currency", "14");
		}else if(gjfft.get("currency").equals("EUR")){//欧元
			gjfft.set("currency", "15");
		}else if(gjfft.get("currency").equals("ATS")){//奥地利先令
			gjfft.set("currency", "040");
		}else if(gjfft.get("currency").equals("OTHER")){//其他
			gjfft.set("currency", "999");
		}else if(gjfft.get("currency").equals("BEF")){//比利时法郎
			gjfft.set("currency", "056");
		}else if(gjfft.get("currency").equals("CAD")){//加拿大元
			gjfft.set("currency", "124");
		}else if(gjfft.get("currency").equals("TWD")){//新台湾币
			gjfft.set("currency", "158");
		}else if(gjfft.get("currency").equals("DKK")){//丹麦克朗
			gjfft.set("currency", "208");
		}else if(gjfft.get("currency").equals("FIM")){//芬兰马克
			gjfft.set("currency", "246");
		}else{
			throw new EOSException("不支持的币种");
		}
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = (GJS01501030000001Req)gjfft;
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	/**
	 * 国际信用证打包贷款
	* @Title: gjxyzdbdk 
	* @Description: 国际信用证打包贷款
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author shendl
	* @date 2017年5月12日 
	* @version V1.0
	 */
	public void gjxyzdbdk(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForGjxyzdbdkLoan", paramap);
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		//请求参数
		DataObject gjxyzdbdk = DataObjectUtil.createDataObject("com.primeton.crmsgj.GJS01501030000001Req");
		gjxyzdbdk = db;
		gjxyzdbdk.set("prdCode", "030201");//产品代码
		gjxyzdbdk.set("proSubTp",null);//产品子类型  
		gjxyzdbdk.set("busiCode", null);//业务编号
		//币种转换
		if(gjxyzdbdk.get("currency").equals("CNY")){
			gjxyzdbdk.set("currency", "01");
		}else if(gjxyzdbdk.get("currency").equals("FRF")){//法国法郎
			gjxyzdbdk.set("currency", "250");
		}else if(gjxyzdbdk.get("currency").equals("DEM")){//德国马克
			gjxyzdbdk.set("currency", "276");
		}else if(gjxyzdbdk.get("currency").equals("HKD")){//港币
			gjxyzdbdk.set("currency", "13");
		}else if(gjxyzdbdk.get("currency").equals("ITL")){//意大利里拉
			gjxyzdbdk.set("currency", "380");
		}else if(gjxyzdbdk.get("currency").equals("JPY")){//日元
			gjxyzdbdk.set("currency", "27");
		}else if(gjxyzdbdk.get("currency").equals("KRW")){//韩国元
			gjxyzdbdk.set("currency", "410");
		}else if(gjxyzdbdk.get("currency").equals("MOP")){//澳门元
			gjxyzdbdk.set("currency", "446");
		}else if(gjxyzdbdk.get("currency").equals("MYR")){//马来西亚币
			gjxyzdbdk.set("currency", "458");
		}else if(gjxyzdbdk.get("currency").equals("NLG")){//荷兰盾
			gjxyzdbdk.set("currency", "528");
		}else if(gjxyzdbdk.get("currency").equals("NZD")){//新西兰元 
			gjxyzdbdk.set("currency", "554");
		}else if(gjxyzdbdk.get("currency").equals("AUD")){//澳洲元
			gjxyzdbdk.set("currency", "16");
		}else if(gjxyzdbdk.get("currency").equals("NOK")){//挪威克朗
			gjxyzdbdk.set("currency", "578");
		}else if(gjxyzdbdk.get("currency").equals("PHP")){//菲律宾比索
			gjxyzdbdk.set("currency", "608");
		}else if(gjxyzdbdk.get("currency").equals("RUB")){//卢布
			gjxyzdbdk.set("currency", "643");
		}else if(gjxyzdbdk.get("currency").equals("SGD")){//新加坡元
			gjxyzdbdk.set("currency", "702");
		}else if(gjxyzdbdk.get("currency").equals("ESP")){//西班牙比塞塔
			gjxyzdbdk.set("currency", "724");
		}else if(gjxyzdbdk.get("currency").equals("SEK")){//瑞典克朗
			gjxyzdbdk.set("currency", "752");
		}else if(gjxyzdbdk.get("currency").equals("CHF")){//瑞士法郎
			gjxyzdbdk.set("currency", "756");
		}else if(gjxyzdbdk.get("currency").equals("THB")){//泰国铢
			gjxyzdbdk.set("currency", "764");
		}else if(gjxyzdbdk.get("currency").equals("GBP")){//英镑
			gjxyzdbdk.set("currency", "12");
		}else if(gjxyzdbdk.get("currency").equals("USD")){//美元
			gjxyzdbdk.set("currency", "14");
		}else if(gjxyzdbdk.get("currency").equals("EUR")){//欧元
			gjxyzdbdk.set("currency", "15");
		}else if(gjxyzdbdk.get("currency").equals("ATS")){//奥地利先令
			gjxyzdbdk.set("currency", "040");
		}else if(gjxyzdbdk.get("currency").equals("OTHER")){//其他
			gjxyzdbdk.set("currency", "999");
		}else if(gjxyzdbdk.get("currency").equals("BEF")){//比利时法郎
			gjxyzdbdk.set("currency", "056");
		}else if(gjxyzdbdk.get("currency").equals("CAD")){//加拿大元
			gjxyzdbdk.set("currency", "124");
		}else if(gjxyzdbdk.get("currency").equals("TWD")){//新台湾币
			gjxyzdbdk.set("currency", "158");
		}else if(gjxyzdbdk.get("currency").equals("DKK")){//丹麦克朗
			gjxyzdbdk.set("currency", "208");
		}else if(gjxyzdbdk.get("currency").equals("FIM")){//芬兰马克
			gjxyzdbdk.set("currency", "246");
		}else{
			throw new EOSException("不支持的币种");
		}
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = (GJS01501030000001Req)gjxyzdbdk;
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		String returnCode = gjS01501030000001Res.getResTranHeader().getHRetCode();
		if(!"AAAAAAA".equals(returnCode)){
			throw new EOSException((String)gjS01501030000001Res.getResTranHeader().getHRetMsg());
		}
	}
	
	
	/**
	 * 表内融资业务放款
	* @Title: bnrzywfk 
	* @Description: 表内融资业务放款
	* @param loanInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author shendl
	* @date 2017年5月16日 
	* @version V1.0
	 */
	public void bnrzywfk(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		/**
		 * 请求参数
		 */
		//查询相关的放款信息
		Object[] ob = null;
		String productType = (String)loanInfo.get("productType");
		if(ProductConstant.CKXYZYH.equals(productType)){//出口信用证押汇
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForCkxyzyhLoan", paramap);
		}else if(ProductConstant.CKTSYH.equals(productType)){//出口托收押汇
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForCktsyhLoan", paramap);
		}else if(ProductConstant.JKXYZYH.equals(productType)){//进口信用证押汇
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkxyzLoan", paramap);
		}else if(ProductConstant.JKDF.equals(productType)){//进口代付
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkdfLoan", paramap);
		}else if(ProductConstant.GJFFT.equals(productType)){//国际福费廷
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForGjfftLoan", paramap);
		}else if(ProductConstant.GJXYZDBDK.equals(productType)){//国际信用证打包贷款
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForGjxyzdbdkLoan", paramap);
		}else if(ProductConstant.JKDSYH.equals(productType)){//进口代收押汇
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkdsyhLoan", paramap);
		}else if(ProductConstant.JKTTYH.equals(productType)){//进口T/T押汇
			ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForJkttyhLoan", paramap);
		}
		if(null== ob || ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		//请求对象
		GJS01501030000001Req gJS01501030000001Req = (GJS01501030000001Req)ob[0];
		//产品代码
		gJS01501030000001Req.setPrdCode(productType);
		//进口代收押汇的产品 子类型是3(国结业务需求)
		if(ProductConstant.JKDSYH.equals(productType)){//进口代收押汇
			gJS01501030000001Req.setProSubTp("3");
		}
		//进口TT押汇的产品 子类型是2(国结业务需求)
		if(ProductConstant.JKTTYH.equals(productType)){//进口TT押汇
			gJS01501030000001Req.setProSubTp("2");
		}
		//币种转换
		if(gJS01501030000001Req.getCurrency().equals("CNY")){
			gJS01501030000001Req.setCurrency("01");
		}else if(gJS01501030000001Req.getCurrency().equals("FRF")){//法国法郎
			gJS01501030000001Req.setCurrency("250");
		}else if(gJS01501030000001Req.getCurrency().equals("DEM")){//德国马克
			gJS01501030000001Req.setCurrency("276");
		}else if(gJS01501030000001Req.getCurrency().equals("HKD")){//港币
			gJS01501030000001Req.setCurrency("13");
		}else if(gJS01501030000001Req.getCurrency().equals("ITL")){//意大利里拉
			gJS01501030000001Req.setCurrency("380");
		}else if(gJS01501030000001Req.getCurrency().equals("JPY")){//日元
			gJS01501030000001Req.setCurrency("27");
		}else if(gJS01501030000001Req.getCurrency().equals("KRW")){//韩国元
			gJS01501030000001Req.setCurrency("410");
		}else if(gJS01501030000001Req.getCurrency().equals("MOP")){//澳门元
			gJS01501030000001Req.setCurrency("81");
		}else if(gJS01501030000001Req.getCurrency().equals("MYR")){//马来西亚币
			gJS01501030000001Req.setCurrency("458");
		}else if(gJS01501030000001Req.getCurrency().equals("NLG")){//荷兰盾
			gJS01501030000001Req.setCurrency("528");
		}else if(gJS01501030000001Req.getCurrency().equals("NZD")){//新西兰元 
			gJS01501030000001Req.setCurrency("554");
		}else if(gJS01501030000001Req.getCurrency().equals("AUD")){//澳洲元
			gJS01501030000001Req.setCurrency("29");
		}else if(gJS01501030000001Req.getCurrency().equals("NOK")){//挪威克朗
			gJS01501030000001Req.setCurrency("578");
		}else if(gJS01501030000001Req.getCurrency().equals("PHP")){//菲律宾比索
			gJS01501030000001Req.setCurrency("608");
		}else if(gJS01501030000001Req.getCurrency().equals("RUB")){//卢布
			gJS01501030000001Req.setCurrency("643");
		}else if(gJS01501030000001Req.getCurrency().equals("SGD")){//新加坡元
			gJS01501030000001Req.setCurrency("32");
		}else if(gJS01501030000001Req.getCurrency().equals("ESP")){//西班牙比塞塔
			gJS01501030000001Req.setCurrency("724");
		}else if(gJS01501030000001Req.getCurrency().equals("SEK")){//瑞典克朗
			gJS01501030000001Req.setCurrency("752");
		}else if(gJS01501030000001Req.getCurrency().equals("CHF")){//瑞士法郎
			gJS01501030000001Req.setCurrency("15");
		}else if(gJS01501030000001Req.getCurrency().equals("THB")){//泰国铢
			gJS01501030000001Req.setCurrency("764");
		}else if(gJS01501030000001Req.getCurrency().equals("GBP")){//英镑
			gJS01501030000001Req.setCurrency("12");
		}else if(gJS01501030000001Req.getCurrency().equals("USD")){//美元
			gJS01501030000001Req.setCurrency("14");
		}else if(gJS01501030000001Req.getCurrency().equals("EUR")){//欧元
			gJS01501030000001Req.setCurrency("38");
		}else if(gJS01501030000001Req.getCurrency().equals("ATS")){//奥地利先令
			gJS01501030000001Req.setCurrency("040");
		}else if(gJS01501030000001Req.getCurrency().equals("OTHER")){//其他
			gJS01501030000001Req.setCurrency("999");
		}else if(gJS01501030000001Req.getCurrency().equals("BEF")){//比利时法郎
			gJS01501030000001Req.setCurrency("056");
		}else if(gJS01501030000001Req.getCurrency().equals("CAD")){//加拿大元
			gJS01501030000001Req.setCurrency("28");
		}else if(gJS01501030000001Req.getCurrency().equals("TWD")){//新台湾币
			gJS01501030000001Req.setCurrency("158");
		}else if(gJS01501030000001Req.getCurrency().equals("DKK")){//丹麦克朗
			gJS01501030000001Req.setCurrency("208");
		}else if(gJS01501030000001Req.getCurrency().equals("FIM")){//芬兰马克
			gJS01501030000001Req.setCurrency("246");
		}else{
			throw new EOSException("不支持的币种");
		}
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Res gjS01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gJS01501030000001Req);
		String returnCode = gjS01501030000001Res.getGjS01501030000001ResBody().getTransStat();
		if(null==returnCode){
			throw new EOSException("调用国结系统[表内融资业务放款接口]发生异常！");
		}
		if(!"0000".equals(returnCode)){
			throw new EOSException(gjS01501030000001Res.getGjS01501030000001ResBody().getErrMsg());
		}
	}
	/**
	 * 国际信用证开证接口
	 */
	public void gjxyzkz(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		/**
		 * 请求参数
		 */
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForGjxyzkzLoan", paramap);;
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		//请求对象
		GJS01501110000002Req gJS01501110000002Req = (GJS01501110000002Req)ob[0];
		//即期 远期处理 期限类型处理
		if("1".equals(gJS01501110000002Req.getLetOfCreDate())){//即期
			gJS01501110000002Req.setLetOfCreDate("0");
			gJS01501110000002Req.setMatuType("001");
		}else if("2".equals(gJS01501110000002Req.getLetOfCreDate())){//远期
			gJS01501110000002Req.setLetOfCreDate("1");
			gJS01501110000002Req.setMatuType("002");
		}
		//币种转换
		if(gJS01501110000002Req.getCurrency().equals("CNY")){
			gJS01501110000002Req.setCurrency("01");
		}else if(gJS01501110000002Req.getCurrency().equals("FRF")){//法国法郎
			gJS01501110000002Req.setCurrency("250");
		}else if(gJS01501110000002Req.getCurrency().equals("DEM")){//德国马克
			gJS01501110000002Req.setCurrency("276");
		}else if(gJS01501110000002Req.getCurrency().equals("HKD")){//港币
			gJS01501110000002Req.setCurrency("13");
		}else if(gJS01501110000002Req.getCurrency().equals("ITL")){//意大利里拉
			gJS01501110000002Req.setCurrency("380");
		}else if(gJS01501110000002Req.getCurrency().equals("JPY")){//日元
			gJS01501110000002Req.setCurrency("27");
		}else if(gJS01501110000002Req.getCurrency().equals("KRW")){//韩国元
			gJS01501110000002Req.setCurrency("410");
		}else if(gJS01501110000002Req.getCurrency().equals("MOP")){//澳门元
			gJS01501110000002Req.setCurrency("81");
		}else if(gJS01501110000002Req.getCurrency().equals("MYR")){//马来西亚币
			gJS01501110000002Req.setCurrency("458");
		}else if(gJS01501110000002Req.getCurrency().equals("NLG")){//荷兰盾
			gJS01501110000002Req.setCurrency("528");
		}else if(gJS01501110000002Req.getCurrency().equals("NZD")){//新西兰元 
			gJS01501110000002Req.setCurrency("554");
		}else if(gJS01501110000002Req.getCurrency().equals("AUD")){//澳洲元
			gJS01501110000002Req.setCurrency("29");
		}else if(gJS01501110000002Req.getCurrency().equals("NOK")){//挪威克朗
			gJS01501110000002Req.setCurrency("578");
		}else if(gJS01501110000002Req.getCurrency().equals("PHP")){//菲律宾比索
			gJS01501110000002Req.setCurrency("608");
		}else if(gJS01501110000002Req.getCurrency().equals("RUB")){//卢布
			gJS01501110000002Req.setCurrency("643");
		}else if(gJS01501110000002Req.getCurrency().equals("SGD")){//新加坡元
			gJS01501110000002Req.setCurrency("32");
		}else if(gJS01501110000002Req.getCurrency().equals("ESP")){//西班牙比塞塔
			gJS01501110000002Req.setCurrency("724");
		}else if(gJS01501110000002Req.getCurrency().equals("SEK")){//瑞典克朗
			gJS01501110000002Req.setCurrency("752");
		}else if(gJS01501110000002Req.getCurrency().equals("CHF")){//瑞士法郎
			gJS01501110000002Req.setCurrency("15");
		}else if(gJS01501110000002Req.getCurrency().equals("THB")){//泰国铢
			gJS01501110000002Req.setCurrency("764");
		}else if(gJS01501110000002Req.getCurrency().equals("GBP")){//英镑
			gJS01501110000002Req.setCurrency("12");
		}else if(gJS01501110000002Req.getCurrency().equals("USD")){//美元
			gJS01501110000002Req.setCurrency("14");
		}else if(gJS01501110000002Req.getCurrency().equals("EUR")){//欧元
			gJS01501110000002Req.setCurrency("38");
		}else if(gJS01501110000002Req.getCurrency().equals("ATS")){//奥地利先令
			gJS01501110000002Req.setCurrency("040");
		}else if(gJS01501110000002Req.getCurrency().equals("OTHER")){//其他
			gJS01501110000002Req.setCurrency("999");
		}else if(gJS01501110000002Req.getCurrency().equals("BEF")){//比利时法郎
			gJS01501110000002Req.setCurrency("056");
		}else if(gJS01501110000002Req.getCurrency().equals("CAD")){//加拿大元
			gJS01501110000002Req.setCurrency("28");
		}else if(gJS01501110000002Req.getCurrency().equals("TWD")){//新台湾币
			gJS01501110000002Req.setCurrency("158");
		}else if(gJS01501110000002Req.getCurrency().equals("DKK")){//丹麦克朗
			gJS01501110000002Req.setCurrency("208");
		}else if(gJS01501110000002Req.getCurrency().equals("FIM")){//芬兰马克
			gJS01501110000002Req.setCurrency("246");
		}else{
			throw new EOSException("不支持的币种");
		}		
		//保证金币种
		if(null != gJS01501110000002Req.getBondCurr() && !"".equals(gJS01501110000002Req.getBondCurr())){
			if(gJS01501110000002Req.getBondCurr().equals("CNY")){
				gJS01501110000002Req.setBondCurr("01");
			}else if(gJS01501110000002Req.getBondCurr().equals("FRF")){//法国法郎
				gJS01501110000002Req.setBondCurr("250");
			}else if(gJS01501110000002Req.getBondCurr().equals("DEM")){//德国马克
				gJS01501110000002Req.setBondCurr("276");
			}else if(gJS01501110000002Req.getBondCurr().equals("HKD")){//港币
				gJS01501110000002Req.setBondCurr("13");
			}else if(gJS01501110000002Req.getBondCurr().equals("ITL")){//意大利里拉
				gJS01501110000002Req.setBondCurr("380");
			}else if(gJS01501110000002Req.getBondCurr().equals("JPY")){//日元
				gJS01501110000002Req.setBondCurr("27");
			}else if(gJS01501110000002Req.getBondCurr().equals("KRW")){//韩国元
				gJS01501110000002Req.setBondCurr("410");
			}else if(gJS01501110000002Req.getBondCurr().equals("MOP")){//澳门元
				gJS01501110000002Req.setBondCurr("81");
			}else if(gJS01501110000002Req.getBondCurr().equals("MYR")){//马来西亚币
				gJS01501110000002Req.setBondCurr( "458");
			}else if(gJS01501110000002Req.getBondCurr().equals("NLG")){//荷兰盾
				gJS01501110000002Req.setBondCurr("528");
			}else if(gJS01501110000002Req.getBondCurr().equals("NZD")){//新西兰元 
				gJS01501110000002Req.setBondCurr("554");
			}else if(gJS01501110000002Req.getBondCurr().equals("AUD")){//澳洲元
				gJS01501110000002Req.setBondCurr("29");
			}else if(gJS01501110000002Req.getBondCurr().equals("NOK")){//挪威克朗
				gJS01501110000002Req.setBondCurr("578");
			}else if(gJS01501110000002Req.getBondCurr().equals("PHP")){//菲律宾比索
				gJS01501110000002Req.setBondCurr("608");
			}else if(gJS01501110000002Req.getBondCurr().equals("RUB")){//卢布
				gJS01501110000002Req.setBondCurr("643");
			}else if(gJS01501110000002Req.getBondCurr().equals("SGD")){//新加坡元
				gJS01501110000002Req.setBondCurr("32");
			}else if(gJS01501110000002Req.getBondCurr().equals("ESP")){//西班牙比塞塔
				gJS01501110000002Req.setBondCurr("724");
			}else if(gJS01501110000002Req.getBondCurr().equals("SEK")){//瑞典克朗
				gJS01501110000002Req.setBondCurr("752");
			}else if(gJS01501110000002Req.getBondCurr().equals("CHF")){//瑞士法郎
				gJS01501110000002Req.setBondCurr("15");
			}else if(gJS01501110000002Req.getBondCurr().equals("THB")){//泰国铢
				gJS01501110000002Req.setBondCurr("764");
			}else if(gJS01501110000002Req.getBondCurr().equals("GBP")){//英镑
				gJS01501110000002Req.setBondCurr("12");
			}else if(gJS01501110000002Req.getBondCurr().equals("USD")){//美元
				gJS01501110000002Req.setBondCurr("14");
			}else if(gJS01501110000002Req.getBondCurr().equals("EUR")){//欧元
				gJS01501110000002Req.setBondCurr("38");
			}else if(gJS01501110000002Req.getBondCurr().equals("ATS")){//奥地利先令
				gJS01501110000002Req.setBondCurr("040");
			}else if(gJS01501110000002Req.getBondCurr().equals("OTHER")){//其他
				gJS01501110000002Req.setBondCurr("999");
			}else if(gJS01501110000002Req.getBondCurr().equals("BEF")){//比利时法郎
				gJS01501110000002Req.setBondCurr("056");
			}else if(gJS01501110000002Req.getBondCurr().equals("CAD")){//加拿大元
				gJS01501110000002Req.setBondCurr("28");
			}else if(gJS01501110000002Req.getBondCurr().equals("TWD")){//新台湾币
				gJS01501110000002Req.setBondCurr("158");
			}else if(gJS01501110000002Req.getBondCurr().equals("DKK")){//丹麦克朗
				gJS01501110000002Req.setBondCurr("208");
			}else if(gJS01501110000002Req.getBondCurr().equals("FIM")){//芬兰马克
				gJS01501110000002Req.setBondCurr("246");
			}else{
				throw new EOSException("不支持的币种");
			}
		}
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501110000002Res gjS01501110000002Res = crmsMgrCallGjImpl.executeS01501110000002(gJS01501110000002Req);
		String returnCode = gjS01501110000002Res.getGjS01501110000002ResBody().getTransStat();
		if(null==returnCode){
			throw new EOSException("调用国结系统[信用证开证接口]发生异常！");
		}
		if(!"0000".equals(returnCode)){
			throw new EOSException(gjS01501110000002Res.getGjS01501110000002ResBody().getErrMsg());
		}
	}
	/**
	 * 国际保函接口 
	 */
	public void gjbh(DataObject loanInfo){
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("loanId", (String)loanInfo.get("loanId"));//放款ID
		/**
		 * 请求参数
		 */
		//查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForGjbhLoan", paramap);;
		if(ob.length==0){
			throw new EOSException("放款相关信息缺失！");
		}
		//请求对象
		GJS01501110000004Req gjS01501110000004Req = (GJS01501110000004Req)ob[0];
		//即期 远期处理 期限类型处理
		if("1".equals(gjS01501110000004Req.getCertMatuType())){//即期
			gjS01501110000004Req.setCertMatuType("0");
			gjS01501110000004Req.setMatuType("001");
		}else if("2".equals(gjS01501110000004Req.getCertMatuType())){//远期
			gjS01501110000004Req.setCertMatuType("1");
			gjS01501110000004Req.setMatuType("002");
		}
		//保函类型处理
		if("1".equals(gjS01501110000004Req.getGrantType())){
			gjS01501110000004Req.setGrantType("01");//融资性保函
		}else if("2".equals(gjS01501110000004Req.getGrantType())){
			gjS01501110000004Req.setGrantType("02");//非融资性保函
		}
		//币种转换
		if(gjS01501110000004Req.getCurrency().equals("CNY")){
			gjS01501110000004Req.setCurrency("01");
		}else if(gjS01501110000004Req.getCurrency().equals("FRF")){//法国法郎
			gjS01501110000004Req.setCurrency("250");
		}else if(gjS01501110000004Req.getCurrency().equals("DEM")){//德国马克
			gjS01501110000004Req.setCurrency("276");
		}else if(gjS01501110000004Req.getCurrency().equals("HKD")){//港币
			gjS01501110000004Req.setCurrency("13");
		}else if(gjS01501110000004Req.getCurrency().equals("ITL")){//意大利里拉
			gjS01501110000004Req.setCurrency("380");
		}else if(gjS01501110000004Req.getCurrency().equals("JPY")){//日元
			gjS01501110000004Req.setCurrency("27");
		}else if(gjS01501110000004Req.getCurrency().equals("KRW")){//韩国元
			gjS01501110000004Req.setCurrency("410");
		}else if(gjS01501110000004Req.getCurrency().equals("MOP")){//澳门元
			gjS01501110000004Req.setCurrency("81");
		}else if(gjS01501110000004Req.getCurrency().equals("MYR")){//马来西亚币
			gjS01501110000004Req.setCurrency( "458");
		}else if(gjS01501110000004Req.getCurrency().equals("NLG")){//荷兰盾
			gjS01501110000004Req.setCurrency("528");
		}else if(gjS01501110000004Req.getCurrency().equals("NZD")){//新西兰元 
			gjS01501110000004Req.setCurrency("554");
		}else if(gjS01501110000004Req.getCurrency().equals("AUD")){//澳洲元
			gjS01501110000004Req.setCurrency("29");
		}else if(gjS01501110000004Req.getCurrency().equals("NOK")){//挪威克朗
			gjS01501110000004Req.setCurrency("578");
		}else if(gjS01501110000004Req.getCurrency().equals("PHP")){//菲律宾比索
			gjS01501110000004Req.setCurrency("608");
		}else if(gjS01501110000004Req.getCurrency().equals("RUB")){//卢布
			gjS01501110000004Req.setCurrency("643");
		}else if(gjS01501110000004Req.getCurrency().equals("SGD")){//新加坡元
			gjS01501110000004Req.setCurrency("32");
		}else if(gjS01501110000004Req.getCurrency().equals("ESP")){//西班牙比塞塔
			gjS01501110000004Req.setCurrency("724");
		}else if(gjS01501110000004Req.getCurrency().equals("SEK")){//瑞典克朗
			gjS01501110000004Req.setCurrency("752");
		}else if(gjS01501110000004Req.getCurrency().equals("CHF")){//瑞士法郎
			gjS01501110000004Req.setCurrency("15");
		}else if(gjS01501110000004Req.getCurrency().equals("THB")){//泰国铢
			gjS01501110000004Req.setCurrency("764");
		}else if(gjS01501110000004Req.getCurrency().equals("GBP")){//英镑
			gjS01501110000004Req.setCurrency("12");
		}else if(gjS01501110000004Req.getCurrency().equals("USD")){//美元
			gjS01501110000004Req.setCurrency("14");
		}else if(gjS01501110000004Req.getCurrency().equals("EUR")){//欧元
			gjS01501110000004Req.setCurrency("38");
		}else if(gjS01501110000004Req.getCurrency().equals("ATS")){//奥地利先令
			gjS01501110000004Req.setCurrency("040");
		}else if(gjS01501110000004Req.getCurrency().equals("OTHER")){//其他
			gjS01501110000004Req.setCurrency("999");
		}else if(gjS01501110000004Req.getCurrency().equals("BEF")){//比利时法郎
			gjS01501110000004Req.setCurrency("056");
		}else if(gjS01501110000004Req.getCurrency().equals("CAD")){//加拿大元
			gjS01501110000004Req.setCurrency("28");
		}else if(gjS01501110000004Req.getCurrency().equals("TWD")){//新台湾币
			gjS01501110000004Req.setCurrency("158");
		}else if(gjS01501110000004Req.getCurrency().equals("DKK")){//丹麦克朗
			gjS01501110000004Req.setCurrency("208");
		}else if(gjS01501110000004Req.getCurrency().equals("FIM")){//芬兰马克
			gjS01501110000004Req.setCurrency("246");
		}else{
			throw new EOSException("不支持的币种");
		}
		//保证金币种
		if(null != gjS01501110000004Req.getBondCurr() && !"".equals(gjS01501110000004Req.getBondCurr())){
			if(gjS01501110000004Req.getBondCurr().equals("CNY")){
				gjS01501110000004Req.setBondCurr("01");
			}else if(gjS01501110000004Req.getBondCurr().equals("FRF")){//法国法郎
				gjS01501110000004Req.setBondCurr("250");
			}else if(gjS01501110000004Req.getBondCurr().equals("DEM")){//德国马克
				gjS01501110000004Req.setBondCurr("276");
			}else if(gjS01501110000004Req.getBondCurr().equals("HKD")){//港币
				gjS01501110000004Req.setBondCurr("13");
			}else if(gjS01501110000004Req.getBondCurr().equals("ITL")){//意大利里拉
				gjS01501110000004Req.setBondCurr("380");
			}else if(gjS01501110000004Req.getBondCurr().equals("JPY")){//日元
				gjS01501110000004Req.setBondCurr("27");
			}else if(gjS01501110000004Req.getBondCurr().equals("KRW")){//韩国元
				gjS01501110000004Req.setBondCurr("410");
			}else if(gjS01501110000004Req.getBondCurr().equals("MOP")){//澳门元
				gjS01501110000004Req.setBondCurr("81");
			}else if(gjS01501110000004Req.getBondCurr().equals("MYR")){//马来西亚币
				gjS01501110000004Req.setBondCurr( "458");
			}else if(gjS01501110000004Req.getBondCurr().equals("NLG")){//荷兰盾
				gjS01501110000004Req.setBondCurr("528");
			}else if(gjS01501110000004Req.getBondCurr().equals("NZD")){//新西兰元 
				gjS01501110000004Req.setBondCurr("554");
			}else if(gjS01501110000004Req.getBondCurr().equals("AUD")){//澳洲元
				gjS01501110000004Req.setBondCurr("29");
			}else if(gjS01501110000004Req.getBondCurr().equals("NOK")){//挪威克朗
				gjS01501110000004Req.setBondCurr("578");
			}else if(gjS01501110000004Req.getBondCurr().equals("PHP")){//菲律宾比索
				gjS01501110000004Req.setBondCurr("608");
			}else if(gjS01501110000004Req.getBondCurr().equals("RUB")){//卢布
				gjS01501110000004Req.setBondCurr("643");
			}else if(gjS01501110000004Req.getBondCurr().equals("SGD")){//新加坡元
				gjS01501110000004Req.setBondCurr("32");
			}else if(gjS01501110000004Req.getBondCurr().equals("ESP")){//西班牙比塞塔
				gjS01501110000004Req.setBondCurr("724");
			}else if(gjS01501110000004Req.getBondCurr().equals("SEK")){//瑞典克朗
				gjS01501110000004Req.setBondCurr("752");
			}else if(gjS01501110000004Req.getBondCurr().equals("CHF")){//瑞士法郎
				gjS01501110000004Req.setBondCurr("15");
			}else if(gjS01501110000004Req.getBondCurr().equals("THB")){//泰国铢
				gjS01501110000004Req.setBondCurr("764");
			}else if(gjS01501110000004Req.getBondCurr().equals("GBP")){//英镑
				gjS01501110000004Req.setBondCurr("12");
			}else if(gjS01501110000004Req.getBondCurr().equals("USD")){//美元
				gjS01501110000004Req.setBondCurr("14");
			}else if(gjS01501110000004Req.getBondCurr().equals("EUR")){//欧元
				gjS01501110000004Req.setBondCurr("38");
			}else if(gjS01501110000004Req.getBondCurr().equals("ATS")){//奥地利先令
				gjS01501110000004Req.setBondCurr("040");
			}else if(gjS01501110000004Req.getBondCurr().equals("OTHER")){//其他
				gjS01501110000004Req.setBondCurr("999");
			}else if(gjS01501110000004Req.getBondCurr().equals("BEF")){//比利时法郎
				gjS01501110000004Req.setBondCurr("056");
			}else if(gjS01501110000004Req.getBondCurr().equals("CAD")){//加拿大元
				gjS01501110000004Req.setBondCurr("28");
			}else if(gjS01501110000004Req.getBondCurr().equals("TWD")){//新台湾币
				gjS01501110000004Req.setBondCurr("158");
			}else if(gjS01501110000004Req.getBondCurr().equals("DKK")){//丹麦克朗
				gjS01501110000004Req.setBondCurr("208");
			}else if(gjS01501110000004Req.getBondCurr().equals("FIM")){//芬兰马克
				gjS01501110000004Req.setBondCurr("246");
			}else{
				throw new EOSException("不支持的币种");
			}
		}
		//调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501110000004Res gjS01501110000004Res = crmsMgrCallGjImpl.executeS01501110000004(gjS01501110000004Req);
		String returnCode = gjS01501110000004Res.getGjS01501110000004ResBody().getTransStat();
		if(null==returnCode){
			throw new EOSException("调用国结系统[保函开立接口]发生异常！");
		}
		if(!"0000".equals(returnCode)){
			throw new EOSException(gjS01501110000004Res.getGjS01501110000004ResBody().getErrMsg());
		}
	}
	/**
	 * 提货担保接口
	 */
	public void thdb(DataObject loanInfo) {
		Map<String, String> paramap = new HashMap<String, String>();
		paramap.put("loanId", (String) loanInfo.get("loanId"));// 放款ID
		/**
		 * 请求参数
		 */
		// 查询相关的放款信息
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryForThdbLoan", paramap);
		;
		if (ob.length == 0) {
			throw new EOSException("放款相关信息缺失！");
		}
		// 请求对象
		GJS01501030000006Req gjS01501030000006Req = (GJS01501030000006Req) ob[0];

		// 币种转换
		if (gjS01501030000006Req.getCurrency().equals("CNY")) {
			gjS01501030000006Req.setCurrency("01");
		} else if (gjS01501030000006Req.getCurrency().equals("FRF")) {// 法国法郎
			gjS01501030000006Req.setCurrency("250");
		} else if (gjS01501030000006Req.getCurrency().equals("DEM")) {// 德国马克
			gjS01501030000006Req.setCurrency("276");
		} else if (gjS01501030000006Req.getCurrency().equals("HKD")) {// 港币
			gjS01501030000006Req.setCurrency("13");
		} else if (gjS01501030000006Req.getCurrency().equals("ITL")) {// 意大利里拉
			gjS01501030000006Req.setCurrency("380");
		} else if (gjS01501030000006Req.getCurrency().equals("JPY")) {// 日元
			gjS01501030000006Req.setCurrency("27");
		} else if (gjS01501030000006Req.getCurrency().equals("KRW")) {// 韩国元
			gjS01501030000006Req.setCurrency("410");
		} else if (gjS01501030000006Req.getCurrency().equals("MOP")) {// 澳门元
			gjS01501030000006Req.setCurrency("81");
		} else if (gjS01501030000006Req.getCurrency().equals("MYR")) {// 马来西亚币
			gjS01501030000006Req.setCurrency("458");
		} else if (gjS01501030000006Req.getCurrency().equals("NLG")) {// 荷兰盾
			gjS01501030000006Req.setCurrency("528");
		} else if (gjS01501030000006Req.getCurrency().equals("NZD")) {// 新西兰元
			gjS01501030000006Req.setCurrency("554");
		} else if (gjS01501030000006Req.getCurrency().equals("AUD")) {// 澳洲元
			gjS01501030000006Req.setCurrency("29");
		} else if (gjS01501030000006Req.getCurrency().equals("NOK")) {// 挪威克朗
			gjS01501030000006Req.setCurrency("578");
		} else if (gjS01501030000006Req.getCurrency().equals("PHP")) {// 菲律宾比索
			gjS01501030000006Req.setCurrency("608");
		} else if (gjS01501030000006Req.getCurrency().equals("RUB")) {// 卢布
			gjS01501030000006Req.setCurrency("643");
		} else if (gjS01501030000006Req.getCurrency().equals("SGD")) {// 新加坡元
			gjS01501030000006Req.setCurrency("32");
		} else if (gjS01501030000006Req.getCurrency().equals("ESP")) {// 西班牙比塞塔
			gjS01501030000006Req.setCurrency("724");
		} else if (gjS01501030000006Req.getCurrency().equals("SEK")) {// 瑞典克朗
			gjS01501030000006Req.setCurrency("752");
		} else if (gjS01501030000006Req.getCurrency().equals("CHF")) {// 瑞士法郎
			gjS01501030000006Req.setCurrency("15");
		} else if (gjS01501030000006Req.getCurrency().equals("THB")) {// 泰国铢
			gjS01501030000006Req.setCurrency("764");
		} else if (gjS01501030000006Req.getCurrency().equals("GBP")) {// 英镑
			gjS01501030000006Req.setCurrency("12");
		} else if (gjS01501030000006Req.getCurrency().equals("USD")) {// 美元
			gjS01501030000006Req.setCurrency("14");
		} else if (gjS01501030000006Req.getCurrency().equals("EUR")) {// 欧元
			gjS01501030000006Req.setCurrency("38");
		} else if (gjS01501030000006Req.getCurrency().equals("ATS")) {// 奥地利先令
			gjS01501030000006Req.setCurrency("040");
		} else if (gjS01501030000006Req.getCurrency().equals("OTHER")) {// 其他
			gjS01501030000006Req.setCurrency("999");
		} else if (gjS01501030000006Req.getCurrency().equals("BEF")) {// 比利时法郎
			gjS01501030000006Req.setCurrency("056");
		} else if (gjS01501030000006Req.getCurrency().equals("CAD")) {// 加拿大元
			gjS01501030000006Req.setCurrency("28");
		} else if (gjS01501030000006Req.getCurrency().equals("TWD")) {// 新台湾币
			gjS01501030000006Req.setCurrency("158");
		} else if (gjS01501030000006Req.getCurrency().equals("DKK")) {// 丹麦克朗
			gjS01501030000006Req.setCurrency("208");
		} else if (gjS01501030000006Req.getCurrency().equals("FIM")) {// 芬兰马克
			gjS01501030000006Req.setCurrency("246");
		} else {
			throw new EOSException("不支持的币种");
		}
		// 保证金币种
		if (null != gjS01501030000006Req.getBondCurr() && !"".equals(gjS01501030000006Req.getBondCurr())) {
			if (gjS01501030000006Req.getBondCurr().equals("CNY")) {
				gjS01501030000006Req.setBondCurr("01");
			} else if (gjS01501030000006Req.getBondCurr().equals("FRF")) {// 法国法郎
				gjS01501030000006Req.setBondCurr("250");
			} else if (gjS01501030000006Req.getBondCurr().equals("DEM")) {// 德国马克
				gjS01501030000006Req.setBondCurr("276");
			} else if (gjS01501030000006Req.getBondCurr().equals("HKD")) {// 港币
				gjS01501030000006Req.setBondCurr("13");
			} else if (gjS01501030000006Req.getBondCurr().equals("ITL")) {// 意大利里拉
				gjS01501030000006Req.setBondCurr("380");
			} else if (gjS01501030000006Req.getBondCurr().equals("JPY")) {// 日元
				gjS01501030000006Req.setBondCurr("27");
			} else if (gjS01501030000006Req.getBondCurr().equals("KRW")) {// 韩国元
				gjS01501030000006Req.setBondCurr("410");
			} else if (gjS01501030000006Req.getBondCurr().equals("MOP")) {// 澳门元
				gjS01501030000006Req.setBondCurr("81");
			} else if (gjS01501030000006Req.getBondCurr().equals("MYR")) {// 马来西亚币
				gjS01501030000006Req.setBondCurr("458");
			} else if (gjS01501030000006Req.getBondCurr().equals("NLG")) {// 荷兰盾
				gjS01501030000006Req.setBondCurr("528");
			} else if (gjS01501030000006Req.getBondCurr().equals("NZD")) {// 新西兰元
				gjS01501030000006Req.setBondCurr("554");
			} else if (gjS01501030000006Req.getBondCurr().equals("AUD")) {// 澳洲元
				gjS01501030000006Req.setBondCurr("29");
			} else if (gjS01501030000006Req.getBondCurr().equals("NOK")) {// 挪威克朗
				gjS01501030000006Req.setBondCurr("578");
			} else if (gjS01501030000006Req.getBondCurr().equals("PHP")) {// 菲律宾比索
				gjS01501030000006Req.setBondCurr("608");
			} else if (gjS01501030000006Req.getBondCurr().equals("RUB")) {// 卢布
				gjS01501030000006Req.setBondCurr("643");
			} else if (gjS01501030000006Req.getBondCurr().equals("SGD")) {// 新加坡元
				gjS01501030000006Req.setBondCurr("32");
			} else if (gjS01501030000006Req.getBondCurr().equals("ESP")) {// 西班牙比塞塔
				gjS01501030000006Req.setBondCurr("724");
			} else if (gjS01501030000006Req.getBondCurr().equals("SEK")) {// 瑞典克朗
				gjS01501030000006Req.setBondCurr("752");
			} else if (gjS01501030000006Req.getBondCurr().equals("CHF")) {// 瑞士法郎
				gjS01501030000006Req.setBondCurr("15");
			} else if (gjS01501030000006Req.getBondCurr().equals("THB")) {// 泰国铢
				gjS01501030000006Req.setBondCurr("764");
			} else if (gjS01501030000006Req.getBondCurr().equals("GBP")) {// 英镑
				gjS01501030000006Req.setBondCurr("12");
			} else if (gjS01501030000006Req.getBondCurr().equals("USD")) {// 美元
				gjS01501030000006Req.setBondCurr("14");
			} else if (gjS01501030000006Req.getBondCurr().equals("EUR")) {// 欧元
				gjS01501030000006Req.setBondCurr("38");
			} else if (gjS01501030000006Req.getBondCurr().equals("ATS")) {// 奥地利先令
				gjS01501030000006Req.setBondCurr("040");
			} else if (gjS01501030000006Req.getBondCurr().equals("OTHER")) {// 其他
				gjS01501030000006Req.setBondCurr("999");
			} else if (gjS01501030000006Req.getBondCurr().equals("BEF")) {// 比利时法郎
				gjS01501030000006Req.setBondCurr("056");
			} else if (gjS01501030000006Req.getBondCurr().equals("CAD")) {// 加拿大元
				gjS01501030000006Req.setBondCurr("28");
			} else if (gjS01501030000006Req.getBondCurr().equals("TWD")) {// 新台湾币
				gjS01501030000006Req.setBondCurr("158");
			} else if (gjS01501030000006Req.getBondCurr().equals("DKK")) {// 丹麦克朗
				gjS01501030000006Req.setBondCurr("208");
			} else if (gjS01501030000006Req.getBondCurr().equals("FIM")) {// 芬兰马克
				gjS01501030000006Req.setBondCurr("246");
			} else {
				throw new EOSException("不支持的币种");
			}
		}
		// 调用国结接口
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000006Res gjS01501030000006Res = crmsMgrCallGjImpl.executeS01501030000006(gjS01501030000006Req);

		String returnCode = gjS01501030000006Res.getGjS01501030000006ResBody().getTransStat();
		if (null == returnCode) {
			throw new EOSException("调用国结系统[提货担保接口]发生异常！");
		}
		if (!"0000".equals(returnCode)) {
			throw new EOSException(gjS01501030000006Res.getGjS01501030000006ResBody().getErrMsg());
		}
	}
}
