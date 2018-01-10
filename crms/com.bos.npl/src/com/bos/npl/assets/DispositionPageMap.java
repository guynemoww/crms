/**
 * 
 */
package com.bos.npl.assets;

import java.util.HashMap;

import com.eos.system.annotation.Bizlet;

/**
 * @author 3231
 * @date 2014-12-24 13:52:08
 *
 */
@Bizlet("")
public class DispositionPageMap {

	HashMap pageMap = new HashMap();

	HashMap entityMap = new HashMap();

	public DispositionPageMap() {
		pageMap.put("1", "/npl/disposition/cash_collection.jsp");//现金清收
		pageMap.put("2", "/npl/disposition/assets_reorganization.jsp");//资产重组
		pageMap.put("3", "/npl/disposition/legal_proceedings.jsp");//法律诉讼
		pageMap.put("4", "/npl/disposition/agent_disposition.jsp");//代理处置
		pageMap.put("5", "/npl/disposition/bale_sale.jsp");//组包出售
		pageMap.put("6", "/npl/disposition/loans_verification.jsp");//呆账核销
		pageMap.put("7", "/npl/disposition/assets_mortgage.jsp");//资产抵债

		entityMap.put("1", "com.bos.dataset.npl.TbNplCashCollection");//现金清收
		entityMap.put("2", "com.bos.dataset.npl.TbNplAssetsReorganization");//资产重组
		entityMap.put("3", "com.bos.dataset.npl.TbNplLegalProceedings");//法律诉讼
		entityMap.put("4", "com.bos.dataset.npl.TbNplAgentDisposition");//代理处置
		entityMap.put("5", "com.bos.dataset.npl.TbNplBaleSale");//组包出售
		entityMap.put("6", "com.bos.dataset.npl.TbNplBadLoansVerification");//呆账核销
		entityMap.put("7", "com.bos.dataset.npl.TbNplAssetsMortgage");//呆账核销
	}

	//获取页面信息
	@Bizlet("")
	public String getDispositionPage(String dispostionType) {
		String dispositionPage = (String) pageMap.get(dispostionType);
		return dispositionPage;

	}

	//获取实体信息
	@Bizlet("")
	public String getDispositionEntity(String dispostionType) {
		String dispositionEntity = (String) entityMap.get(dispostionType);
		return dispositionEntity;

	}

}
