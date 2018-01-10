/**
 * 
 */
package com.bos.inter.CallT24Interface;

import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;

/**
 * @author lujinbin
 * @date 2014-07-29 15:12:52
 * 
 */
@Bizlet("")
public class TExTsaAllAaa extends BaseWorkTask implements WorkTask {

	public void execute() throws Exception {
		// TODO 自动生成方法存根
		BOSFXII bf = new BOSFXII();
		TExTsaAllAaaRs rs = new TExTsaAllAaaRs();
		BaseMQRequest bmr = new BaseMQRequest();
		rs.setCommonRsHdr(this.success());
		bf.t24BosfxRs = rs;
		bmr.mqSend(bf, taskBean);
	}

}
