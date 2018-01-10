
package com.bos.utp.auth.validator;

import java.sql.Timestamp;
import java.util.HashMap;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IDataContext;
import com.eos.data.datacontext.ISessionMap;
import com.eos.engine.core.IValidateRule;
import com.eos.engine.core.ValidateResult;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

/**
 * 
 * 验证码校验器<BR>
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: VerifyCodeLoginValidator.java,v $
 * Revision 1.4  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.3  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.2  2009/03/30 05:39:39  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 *
 * Revision 1.1  2008/11/24 13:43:43  wengzr
 * Added:增加验证码可配置,和验证码校验器VerifyCodeLoginValidator
 *
 */
public class VerifyCodeLoginValidator implements IValidateRule{

	public void validate(String[] params, IDataContext dataContext, ValidateResult result) {
		String verifyCode=dataContext.getString(params[0].trim());
		if(verifyCode!=null){
			
			ISessionMap sessionMap=DataContextManager.current().getSessionCtx();
			if(sessionMap!=null){
				if(!verifyCode.equals(sessionMap.get("verifyCode"))){
					result.addValidateErrorCode("verifyCode", "校验码错误！");
				}
			}
		}
		
	}
	/**
	 * 检查错误登录次数，如果超过九次，锁定该用户
	 * @param errorCnt
	 * @return
	 */
	@Bizlet("检查错误登录次数，如果超过九次，锁定该用户")
	public Integer checkErrorLoginCnt(Integer errorCnt,String userid,String retCode){
		if("-2".equals(retCode)){
			if(0==errorCnt || errorCnt == null){
				errorCnt = 1;
			}
			else{
				errorCnt += 1;
			}
			if(errorCnt == 9){
				//更新操作员状态为锁定
				HashMap<String, Object> sqlParams = new HashMap<String, Object>();
				sqlParams.put("userid", userid); 
				DatabaseExt.executeNamedSql(null,"com.bos.utp.auth.frame.update_operator_status",sqlParams);
			}
		}
		return errorCnt;
	}
}
