package com.bos.gjService;

import org.osoa.sca.annotations.Remotable;

/**
 * 网银调用信贷的个贷服务接口
 * @author lenovo
 *
 */
@Remotable
public interface WytoLoanService {
	//个贷业务申请接口
	public WY001Response executeWY001(WY001Request request);
}
