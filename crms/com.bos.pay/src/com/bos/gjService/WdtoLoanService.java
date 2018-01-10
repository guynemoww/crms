package com.bos.gjService;

import org.osoa.sca.annotations.Remotable;

/**
 * 网贷调用信贷的个贷服务接口
 * @author lenovo
 *
 */

@Remotable
public interface WdtoLoanService {

	//网贷001白名单信息查询
	public D001Response executeD001(D001Request request);
	
	//网贷002业务受理
	public D002Response executeD002(D002Request d002Request);
	
	//还款流水
	public D008Response executeD008(D008Request request);
	
	//查询额度信息
	public D005Response executeD005(D005Request request);
	
	//贷款本息查询 
	public D006Response executeD006(D006Request request);
	
	//放款流水查询 
	public D007Response executeD007(D007Request request);
	
	//查询借据信息 
	public D009Response executeD009(D009Request request);
	
	//网银还款接口 
	public D004Response executeD004(D004Request request);
	
	//网银放款接口
	public D003Response executeD003(D003Request request);
	
	//网贷010客户申请状态查询
	public D010Response executeD010(D010Request d002Request);
	
	//网贷011还款账号变更
	public D011Response executeD011(D011Request d011Request);
}
