package com.bos.pub;

//import com.crms.image.service.client.impl.ForPersonProcessImpl;
import com.eos.system.annotation.Bizlet;

@Bizlet("电子影像相关")
public class EcmUtil {

	//private ForPersonProcessImpl process = ForPersonProcessImpl.getInstance();

	@Bizlet("电子影像新增：客户")
	public String custAdd(String custnum) {
		return null;//process.pluginAdd("", "");
	}

	@Bizlet("电子影像查看：客户")
	public String custview(String custnum) {
		return null;//process.pluginView(custnum, "");
	}

	@Bizlet("电子影像新增：业务")
	public String bizAdd(String custnum, String bizno) {
		return null;//process.pluginAdd(custnum, bizno);
	}

	@Bizlet("电子影像查看：业务")
	public String bizview(String bizno) {
		return null;//process.pluginView(bizno, "");
	}

	@Bizlet("电子影像打印条码")
	public String getBisiCode(String biznoOrCustnum) {
		return null;//process.getBisiCode(biznoOrCustnum);
	}
}
