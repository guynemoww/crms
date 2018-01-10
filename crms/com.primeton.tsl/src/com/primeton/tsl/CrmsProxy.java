package com.primeton.tsl;


public interface CrmsProxy {
	/*
	 * MA1_1400	PayOutVo	业务出账 
	 * tcloaninfoService.out(dueNum, nftNo, baseVO)
	 */
	//public byte[] outByteArray(byte[] byteArray);
	public byte[] getT1101AsJSON(byte[] byteArray);

	/*
	 * MA1_1101	PayOutVo	自营性贷款-0100还款方式-非借新还款旧-二次交易
	 * duebillService.payOut(dueNum, baseVO, telNo)
	 */
	public byte[] payoutByteArray(byte[] byteArray);
	
	/*
	 * MA1_1504	PayConInfo	核销申请
	 * mA11504Service.execute(dueNum, telNo, busDate, baseVO, payConInfo)
	 */
	public byte[] execute1504ByteArray(byte[] byteArray);	

	/*
	 * MA1_1501	PayConInfo	停息申请
	 * mA11501Service.execute(dueNum, telNo, busDate, baseVO, payConInfo)
	 */
	public byte[] execute1501ByteArray(byte[] byteArray);

	/*
	 * MA1_1502	PayConInfo	终止停息申请
	 * mA11502Service.execute(dueNum, telNo, busDate, baseVO, payConInfo)
	 */
	public byte[] execute1502ByteArray(byte[] byteArray);

	/*
	 * MA1_1503	PayConInfo	调整贷款利息申请
	 * mA11503Service.execute(dueNum, telNo, busDate, baseVO, payConInfo)
	 */
	public byte[] execute1503ByteArray(byte[] byteArray);

	/*
	 * MA1_1505	PayConInfo	核销收回申请
	 * mA11505Service.execute(dueNum, telNo,busDate, baseVO, payConInfo)
	 */
	public byte[] execute1505ByteArray(byte[] byteArray);

	/*
	 * MA1_1506	PayConInfo	还本计划变更申请
	 * mA11506Service.execute(dueNum, telNo,busDate, baseVO, payConInfo)
	 */
	public byte[] execute1506ByteArray(byte[] byteArray);
	
	/*
	 * mda2001	PayConInfo	还本计划变更申请
	 * mda2001Service.executemda2001(String dueNum, String busDate, PayConInfo payConInfo);

	 */
	public byte[] executemda2001ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1500	PayConInfo	还款申请 
	 * mA11500Service.execute(dueNum, telNo,busDate, baseVO, payConInfo, payConInfo.getAmtFlg())
	 */
	public byte[] execute1500ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1401	ChgPayAcctVo	变更还款账号
	 * mA11401Service.execute(dueNum, busDate, chgPayAcctVo)
	 */
	public byte[] execute1401ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1402	StopDiscVo	暂停贴息
	 * mA11402Service.execute(dueNum, busDate, baseVO, stopDiscVo)
	 */
	public byte[] execute1402ByteArray(byte[] byteArray);

	/*
	 * MA1_1102	PayConInfo	日间还款 
	 * mA1102Service.mA11102(dueNum, busDate, telNo, baseVO)
	 */
	public byte[] execute1102ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1103	PayConInfo	停息
	 * mA11103Service.execute(dueNum, telNo, busDate, baseVO)
	 */
	public byte[] execute1103ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1104	PayConInfo	终止停息
	 * mA11104Service.execute(dueNum, telNo, busDate, baseVO)
	 */
	public byte[] execute1104ByteArray(byte[] byteArray);

	/*
	 * MA1_1105	PayConInfo	利息调整
	 * mA11105Service.execute(dueNum, telNo, busDate, baseVO)
	 */
	public byte[] execute1105ByteArray(byte[] byteArray);

	/*
	 * MA1_1300	PayConInfo	核销
	 * mA11300Service.execute(dueNum, telNo, busDate, baseVO)
	 */
	public byte[] execute1300ByteArray(byte[] byteArray);

	/*
	 * MA1_1301	PayConInfo	核销收回
	 * mA11301Service.execute(dueNum, telNo, busDate, baseVO)
	 */
	public byte[] execute1301ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1403	StopDiscVo	恢复贴息
	 * mA11403Service.execute(dueNum, busDate, baseVO, stopDiscVo)
	 */
	public byte[] execute1403ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1405	ChgLengthVo	期限变更
	 * mA11405Service.execute(dueNum, busDate, baseVO,chgLengthVo)
	 */
	public byte[] execute1405ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1406	StgFirstMonVo	调整阶段性贷款首次还本期数
	 * mA11406Service.execute(dueNum, busDate, stgFirstMonVo)
	 */
	public byte[] execute1406ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1100	PayConInfo	结清试算
	 * mA11100Service.mA11100(dueNum, busDate, baseVO)
	 */
	public byte[] execute1100ByteArray(byte[] byteArray);

	/*
	 * MA1_1404	PayTypVo	还款方式变更
	 * mA11404Service.execute(dueNum, busDate, baseVO,payTypVo)
	 */
	public byte[] execute1404ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1407	ChgDiscEndVo	调整贴息到期日
	 * mA11407Service.execute(dueNum, busDate, baseVO, chgDiscEndVo)
	 */
	public byte[] execute1407ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1409	PayConInfo	贷款核销前查询
	 * mA11409Service.execute(dueNum, busDate, baseVO)
	 */
	public byte[] execute1409ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1411	PayConInfo	输入本金试算还款额
	 * mA11411Service.mA11411(dueNum, payPrnAmt, busDate, baseVO)
	 */
	public byte[] execute1411ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1414	AppChgLengthVo	展期申请控制信息
	 * mA11414Service.execute(dueNum, busDate,baseVO, appChgLengthVo)
	 */
	public byte[] execute1414ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1600	QueryPayPlanVo	查询还款计划 
	 * mA11600Service.execute(busDate,baseVO, queryPayPlanVo)
	 */
	public byte[] execute1600ByteArray(byte[] byteArray);

	/*
	 * MDB_2002	CancelDelVo	查询还款计划 
	 * mdb2002Service.execute(busDate,baseVO, queryPayPlanVo)
	 */
	public byte[] executemdb2002ByteArray(byte[] byteArray);

	
	/*
	 * MA1_1107	PayConInfo	借新还旧(不向核心发交易，日终不参与对账)
	 * mA11107Service.mA11107(dueNum, busDate,baseVO)
	 */
	public byte[] execute1107ByteArray(byte[] byteArray);

	/*
	 * MA1_1106	PayConInfo	垫款放款
	 * mA11106Service.mA1106(dueNum, busDate,baseVO)
	 */
	public byte[] execute1106ByteArray(byte[] byteArray);
	
	/*
	 * MA1_1109	PayConInfo	利息查询
	 * mA11109Service.mA1106(dueNum, busDate,baseVO)
	 */
	public byte[] execute1109ByteArray(byte[] byteArray);
	
	
	/*
	 * MB1_1101	PayOutVo	放款冲正
	 * mB11101Service.execute(dueNum, primAcct, busDate, rcnStan, baseVO)
	 */
	public byte[] execute1101ByteArray(byte[] byteArray);

	/*
	 * MA1_1507	PayConInfo	终止停息查询
	 * mA11507Service.execute(dueNum, busDate, baseVO, payConInfo)
	 */
	public byte[] execute1507ByteArray(byte[] byteArray);
	
	/*
	 * MA1_2100	AbnormalTran	异常登记 
	 * mA12100Service.ma12100(msgCore3181In)
	 */
	public byte[] execute2100ByteArray(byte[] byteArray);
	
	/*
	 * MB1_1102	PayConInfo	还款控制信息恢复
	 * mB11102Service.mb11102(due_num, bus_date, tel_no, rcn_stan)
	 */
	public byte[] executemb1102ByteArray(byte[] byteArray);
	public byte[] executemb1102(String due_num,String bus_date,String tel_no,String rcn_stan,BaseVO baseVO);
	
	
	/*
	 * MA1_1508	PayConInfo 客户调账申请
	 * mA11508Service.execute(dueNum, telNo, busDate, baseVO, payConInfo)
	 */
	public byte[] execute1508ByteArray(byte[] byteArray);
	public byte[] execute1508(String dueNum,String telNo,String busDate,BaseVO baseVO,PayConInfo payConInfo);
	/*
	 * MDA_2003	PayConInfo 客户调账
	 * mDA2003Service.execute(dueNum, telNo, busDate, baseVO)
	 */
	public byte[] executemda2003ByteArray(byte[] byteArray);
	public byte[] executemda2003(String dueNum,String telNo,String busDate,PayConInfo payConInfo);

	//委托人收本收息账号变更
	public byte[] execute1416ByteArray(byte[] byteArray);
	/*
	 * MDA_2099 资产证券化检查
	 * mDA2001Service.execute(dueNum, busDate, payConInfo)
	 */
	public byte[] executemda2099ByteArray(byte[] byteArray);
	public byte[] executemda2099(ConSecuDueNumVo conSecuDueNumVo,String busDate);
}
