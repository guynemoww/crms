/**
 * 
 */
package com.primeton.tsl.ecif.port;

import com.primeton.tsl.ecif.S0110101000A202ServiceStub.FMT_CRMS_SVR_S0110101000A202_IN;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub.S0110101000A202Response;
import com.primeton.tsl.ecif.S0110101000A204ServiceStub.FMT_CRMS_SVR_S0110101000A204_IN;
import com.primeton.tsl.ecif.S0110101000A204ServiceStub.S0110101000A204Response;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.FMT_CRMS_SVR_S0110101000A207_IN;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.S0110101000A207Response;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.FMT_CRMS_SVR_S0110101000A208_IN;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.S0110101000A208Response;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.FMT_CRMS_SVR_S0110102000B201_IN;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.S0110102000B201Response;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.FMT_CRMS_SVR_S0110102000B210_IN;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.S0110102000B210Response;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub.FMT_CRMS_SVR_S0110102000B211_IN;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub.S0110102000B211Response;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.FMT_CRMS_SVR_S0110102000B212_IN;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.S0110102000B212Response;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub.FMT_CRMS_SVR_S0110102000B213_IN;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub.S0110102000B213Response;

/**
 * @author zhouxu
 * 对公客户交易与维护 所有接口
 */
public interface OrgCustEcif {
	/**
	 *描述  对公客户基本信息查询  A202
	 *输出  S0110101000A202Response 返回客户基本信息
	 *输入     FMT_CRMS_SVR_S0110101000A202_IN 
	 */
	S0110101000A202Response	COrgCustBaseQuery(FMT_CRMS_SVR_S0110101000A202_IN request) throws Exception;

	
	/**
	 *描述  对公客户上市信息查询  A204
	 *输出  S0110101000A202Response 返回客户上市信息
	 *输入     FMT_CRMS_SVR_S0110101000A204_IN 
	 */
	S0110101000A204Response	COrgCustIpoInfoQuery(FMT_CRMS_SVR_S0110101000A204_IN request) throws Exception;
	
	
	/**
	 *描述  对公客户关系个人信息查询  A207
	 *输出  S0110101000A202Response 返回客户关系个人信息
	 *输入     FMT_CRMS_SVR_S0110101000A207_IN 
	 */
	S0110101000A207Response	COrgRelPsnQuery(FMT_CRMS_SVR_S0110101000A207_IN request) throws Exception;
	
	
	/**
	 *描述  对公客户关系企业信息查询  A208
	 *输出  S0110101000A202Response 返回客户关系企业信息
	 *输入     FMT_CRMS_SVR_S0110101000A208_IN 
	 */
	S0110101000A208Response	COrgRelComQuery(FMT_CRMS_SVR_S0110101000A208_IN request) throws Exception;
	
	
	
	/**
	 *描述  对公客户基本信息创建与维护  B201
	 *输出  S0110102000B201Response 返回修改后客户基本信息
	 *输入     FMT_CRMS_SVR_S0110102000B201_IN 
	 */
	S0110102000B201Response	COrgCustBaseMaint(FMT_CRMS_SVR_S0110102000B201_IN request) throws Exception;
	
	
	
	/**
	 *描述  对公客户关系个人信息创建与维护  B210
	 *输出  S0110101000A011Response 返回修改后客户关系个人信息
	 *输入     FMT_CRMS_SVR_S0110102000B210_IN 
	 */
	S0110102000B210Response	CCustAddrInfoQuery(FMT_CRMS_SVR_S0110102000B210_IN request) throws Exception;
	
	
	/**
	 *描述  对公客户关系个人删除  B211
	 *输出  S0110102000B211Response 返回
	 *输入     FMT_CRMS_SVR_S0110102000B211_IN 
	 */
	S0110102000B211Response	COrgRelPsnDel(FMT_CRMS_SVR_S0110102000B211_IN request) throws Exception;
	
	
	/**
	 *描述  对公客户关系企业信息创建与维护  B212
	 *输出  S0110102000B212Response 返回修改后对公客户关系企业信息
	 *输入     FMT_CRMS_SVR_S0110102000B212_IN 
	 */
	S0110102000B212Response	COrgRelComMaint(FMT_CRMS_SVR_S0110102000B212_IN request) throws Exception;
	
	
	/**
	 *描述  对公客户关系企业删除  B213
	 *输出  S0110102000B213Response 返回
	 *输入     FMT_CRMS_SVR_S0110102000B213_IN 
	 */
	S0110102000B213Response	COrgRelComDel(FMT_CRMS_SVR_S0110102000B213_IN request) throws Exception;

}
