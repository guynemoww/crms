package com.bos.gjService;

import org.osoa.sca.annotations.Remotable;

/**
 * 票据调用信贷的接口
 * @author lenovo
 *
 */
@Remotable
public interface PjtoLoanService {
	//额度恢复
	public P001Response executeP001(P001Request request);
}
