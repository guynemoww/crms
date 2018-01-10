package com.bos.grt.grtSynInterface;

import org.osoa.sca.annotations.Remotable;

@Remotable
public interface GrtInfoSynInterface {

	/**
	 * 押品概要信息同步
	 * 交易码 1102
	 */
	public String collInfoSyn(CollInfoInput coll);
}
