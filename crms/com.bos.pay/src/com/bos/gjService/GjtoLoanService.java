package com.bos.gjService;

import org.osoa.sca.annotations.Remotable;

/**
 * 国结调用信贷接口
 */
@Remotable
public interface GjtoLoanService {
	//出账接口
	public G001Response executeG001(G001Request g001Request);

	//还款接口
	public G002Response executeG002(G002Request g002Request);

	//本息查询接口
	public G003Response executeG003(G003Request g003Request);

	//国结业务通知
	public G004Response executeG004(G004Request g004Request);

	//放/还款结果查询
	public G005Response executeG005(G005Request g005Request);
	
	//国结推送
	public G006Response executeG006(G006Request g006Resquest);

}
