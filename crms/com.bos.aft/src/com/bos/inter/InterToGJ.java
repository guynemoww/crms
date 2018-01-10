package com.bos.inter;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.EsbSocketService;
import com.bos.pub.socket.service.request.base.EsbAppHeadRq;
import com.bos.pub.socket.util.EsbSocketUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;
/**
 * 国结产品贷后调用国结接口已经在com.bos.aftPro.CallBackForEndProcess.java做了相关处理  这里的代码作废
 * @author shendl
 *
 */
public class InterToGJ {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);

	public void aftToGJxyz(DataObject change) throws EOSException {//信用证修改
		
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("changeId", change.getString("changeId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.queryForXyzLoan", paramap);
		if(ob.length==0){
			throw new EOSException("相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", change.getString("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		
		DataObject xyz = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq05001000001BODY02");
		xyz = db;
		EsbSocketUtil.setDataObject(xyz, "esbBodyGjRqMrgnArrays", bzjs);
		/*//字典转换
		if("CNY".equals(xyz.get("ccyTp"))){
			xyz.set("ccyTp", "01");
		}*/
		//即远期
		if("1".equals(xyz.get("newSpotFwdInd"))){
			xyz.set("newSpotFwdInd", "0");
		}else if("2".equals(xyz.get("newSpotFwdInd"))){
			xyz.set("newSpotFwdInd", "1");
		}
		//xyz.set("rmtInstNo", "61101");
		/*xyz.set("eCIFCstNo", "1234567890");
		xyz.set("spotFwdInd", "0");
		
		//担保方式
		/*if("01".equals(xyz.get("mainGryTy"))){
			xyz.set("mainGryTy", "2");
		}else if("02".equals(xyz.get("spotFwdInd"))){
			xyz.set("mainGryTy", "4");
		}else if("03".equals(xyz.get("spotFwdInd"))){
			xyz.set("mainGryTy", "3");
		}else if("04".equals(xyz.get("spotFwdInd"))){
			xyz.set("mainGryTy", "1");
		}else if("05".equals(xyz.get("spotFwdInd"))){
			xyz.set("mainGryTy", "5");
		}*/
		
		//保证金比例
		DataObject bzjbl = (DataObject)bzjbls[0];
		
		xyz.set("newMrgnPct", bzjbl.get("BZJBL"));
		xyz.set("mrgnNum", bzjs.length);
		
		/*BigDecimal bzjTotal = new BigDecimal("0");
		for(int i=0;i<bzjs.length;i++){
			EsbBodyGjRqMrgnArray bzj = (EsbBodyGjRqMrgnArray)bzjs[i];
			BigDecimal bzjamt = new BigDecimal(bzj.getMrgnAmt());
			
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("bzjTotal", bzjTotal);
			params1.put("bzjamt", bzjamt );
			bzjTotal = MathHelper.expressionBigDecimal(
					"bzjTotal+bzjamt", params1);
		}
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("bzjTotal", bzjTotal);
		params1.put("afAmdtAmt", new BigDecimal((Double)xyz.get("afAmdtAmt")) );
		BigDecimal newMrgnPct = MathHelper.expressionBigDecimal(
				"bzjTotal/afAmdtAmt", params1);
		
		xyz.set("newMrgnPct", MathHelper.round(newMrgnPct,2));
		xyz.set("mrgnNum", bzjs.length);*/
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ05001000001BODY02",iEsbAppHeadRq, xyz);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}
	}
	
	public void aftToGJbh(DataObject change) throws EOSException {//保函修改
				
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("changeId", change.getString("changeId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.queryForBhLoan", paramap);
		if(ob.length==0){
			throw new EOSException("相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		Map<String,String> map = new HashMap<String, String>();
		map.put("contractId", change.getString("contractId"));
		Object[] bzjs = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findMrgnArray", map);
		
		Object[] bzjbls = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForGjLoan.queryBzjbl", map);
		
		DataObject bh = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq05001000002BODY04");
		bh = db;
		EsbSocketUtil.setDataObject(bh, "esbBodyGjRqMrgnArrays", bzjs);
		//字典转换
		/*if("CNY".equals(bh.get("ccyTp"))){
			bh.set("ccyTp", "01");
		}
		*/
		//bh.set("rmtInstNo", "61101");
		//bh.set("bckLCInd", 'N');//备用信用证默认为N
		
		
		/*//担保方式
		if("01".equals(bh.get("mainGryTy"))){
			bh.set("mainGryTy", "2");
		}else if("02".equals(bh.get("spotFwdInd"))){
			bh.set("mainGryTy", "4");
		}else if("03".equals(bh.get("spotFwdInd"))){
			bh.set("mainGryTy", "3");
		}else if("04".equals(bh.get("spotFwdInd"))){
			bh.set("mainGryTy", "1");
		}else if("05".equals(bh.get("spotFwdInd"))){
			bh.set("mainGryTy", "5");
		}*/
		
		//保函
		DataObject bzjbl = (DataObject)bzjbls[0];
		bh.set("mrgnPct", bzjbl.get("BZJBL"));
		bh.set("mrgnNum", bzjs.length);
		
		/*BigDecimal bzjTotal = new BigDecimal("0");
		for(int i=0;i<bzjs.length;i++){
			EsbBodyGjRqMrgnArray bzj = (EsbBodyGjRqMrgnArray)bzjs[i];
			BigDecimal bzjamt = new BigDecimal(bzj.getMrgnAmt());
			
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("bzjTotal", bzjTotal);
			params1.put("bzjamt", bzjamt );
			bzjTotal = MathHelper.expressionBigDecimal(
					"bzjTotal+bzjamt", params1);
		}
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("bzjTotal", bzjTotal);
		params1.put("afAmdtAmt", new BigDecimal((Double)bh.get("afAmdtAmt")));
		BigDecimal mrgnPct = MathHelper.expressionBigDecimal(
				"bzjTotal/afAmdtAmt", params1);
		bh.set("mrgnPct", mrgnPct);
		bh.set("mrgnNum", bzjs.length);*/
		//bh.set("bckLCInd", "N");
		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ05001000002BODY04",iEsbAppHeadRq, bh);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}
	}
	
	public void aftToGJzq(DataObject change) throws EOSException {//展期
				
		Map<String,String> paramap = new HashMap<String,String>();
		paramap.put("changeId", change.getString("changeId"));
		Object[] ob = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.queryForZqLoan", paramap);
		if(ob.length==0){
			throw new EOSException("相关信息缺失！");
		}
		DataObject db = (DataObject)ob[0];
		
		DataObject zq = DataObjectUtil.createDataObject("com.bos.pub.sys.GjRq02001000003BODY01");
		zq = db;

		//zq.set("rmtInstNo", "61101");
		//zq.set("aplyInstID", "61101");
		zq.set("bsnTp", "050009");//展期050009

		try {
			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject("RQ02001000003BODY01",iEsbAppHeadRq, zq);
			String returnCode = (String)object.get("ReturnCode");
			if(!"00000000000000".equals(returnCode)){
				throw new EOSException((String)object.get("ReturnMsg"));
			}
		}catch (Throwable e1) {
			e1.printStackTrace();
			
			throw new EOSException(e1.getMessage());
		}
	}
	
}
