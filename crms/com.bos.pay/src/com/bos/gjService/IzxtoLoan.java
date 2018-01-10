package com.bos.gjService;

import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.bos.gjService.ZX001Request;
import com.bos.gjService.ZX001Response;

@Remotable
public interface IzxtoLoan {
	/**
	 *网贷调取CRMS服务
	 * @return
	 */
	public ZX001Response executeX001(ZX001Request request)
			throws Exception;
}
