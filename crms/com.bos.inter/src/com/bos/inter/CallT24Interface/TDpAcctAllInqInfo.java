package com.bos.inter.CallT24Interface;

import com.eos.system.annotation.Bizlet;

public class TDpAcctAllInqInfo {
	@Bizlet("test")

	public TDpAcctAllInqRq sendInfoToT24(){
	
	TDpAcctAllInqRq rq=new TDpAcctAllInqRq();
	rq.setAcctNo("03000000287");
	rq.setMediumType("A");
	return rq;
}
}
