package com.bos.mq.server;

import java.io.UnsupportedEncodingException;
import com.bos.jaxb.javabean.CommonRsHdr;
import com.bos.pub.GitUtil;
import com.bos.pub.exception.ParamEmptyException;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.log.LogFactory;
import com.eos.system.logging.Logger;
import com.git.easyetl.threadpool.common.TaskBean;
import commonj.sdo.DataObject;

public class BaseWorkTask {
	Logger log = LogFactory.getLogger(BaseWorkTask.class);
	protected Object threadkey; // 为了显示执行线程编号
    protected final int TaskExecTime = 1; // 任务执行时间
    protected TaskBean taskBean;//kettle任务的属性
    protected CommonRsHdr rsh = new CommonRsHdr();

    public void setTaskThreadKey(Object key) {
        this.threadkey = key;
    }

    public Object getTaskThreadKey() {
        return threadkey;
    }

    public TaskBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(TaskBean taskBean) {
        this.taskBean = taskBean;
    }
    
    /**
     * 交易成功
     * @param rsh
     */
    protected CommonRsHdr success(){
		rsh.setStatusCode(CrmsMsg._SUCCESS.value());
		rsh.setServerStatusCode(CrmsMsg._SUCCESS_MSG.value());
		return rsh;
    }
    
    /**
     * 交易失败
     * @param code	响应码
     * @param msg	响应信息
     */
    protected CommonRsHdr error(String code,String msg){
		rsh.setStatusCode(code);
		rsh.setServerStatusCode(msg);
		return rsh;
    }
    
    /**
     * 根据流水号获取历史报文信息
     * @param rqUID
     * @param taskBean
     * @return
     * @throws UnsupportedEncodingException
     * @throws ParamEmptyException 
     * @throws UnsupportedEncodingException 
     */
    protected byte[] getContext(String rqUID) throws ParamEmptyException, UnsupportedEncodingException{
    	String context = null;
		DataObject sdo = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubTradInfo");
		sdo.setString("rquid", rqUID);
    	DataObject tradeInfo = GitUtil.queryEntityByTemplate(sdo); //DatabaseUtil.queryEntitiesByTemplate("com.bos.dataset.pub.TbPubTradInfo", sdo);
    	DatabaseUtil.expandLobProperty(GitUtil.DEFAULT_DS_NAME, tradeInfo, "msginfo");
    	context = tradeInfo.getString("msginfo");
    	return context == null ? null : context.getBytes("UTF-8");
    }
    
    /**
     * 根据业务流水号获取历史报文信息
     * @param bizNumber
     * @return
     * @throws UnsupportedEncodingException
     * @throws ParamEmptyException 
     * @throws UnsupportedEncodingException 
     */
    protected byte[] getContexts(String bizNumber) throws ParamEmptyException, UnsupportedEncodingException{
    	String context = null;
		DataObject sdo = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubTradInfo");
		sdo.setString("bizNumber", bizNumber);
    	DataObject tradeInfo = GitUtil.queryEntityByTemplate(sdo); //DatabaseUtil.queryEntitiesByTemplate("com.bos.dataset.pub.TbPubTradInfo", sdo);
    	DatabaseUtil.expandLobProperty(GitUtil.DEFAULT_DS_NAME, tradeInfo, "msginfo");
    	if(tradeInfo==null){
    		
    	}else{
    		context = tradeInfo.getString("msginfo");
    	}
    	return context == null ? null : context.getBytes("UTF-8");
    }
}
