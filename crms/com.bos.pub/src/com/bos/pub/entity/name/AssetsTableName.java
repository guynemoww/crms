package com.bos.pub.entity.name;

public interface AssetsTableName {
	/**
	 * 不良资产处置方案
	 */
	String TB_ASSET_HANDLE_PLAN = "com.bos.dataset.asset.TbAssetHandlePlan";
	/**
	 * 委外清收
	 */
	String TB_ASSET_CLEAN_TAKE_ENTRUST = "com.bos.dataset.asset.TbAssetCleanTakeEntrust";
	/**
	 * 委外清收关联合同
	 */
	String TB_ASSET_CLEAN_TAKE_ENT_CON = "com.bos.dataset.asset.TbAssetCleanTakeEntCon";
	/**
	 * 诉讼清收
	 */
	String TB_ASSET_CLEAN_TAKE_LAW = "com.bos.dataset.asset.TbAssetCleanTakeLaw";
	/**
	 * 诉讼清收关联合同
	 */
	String TB_ASSET_CLEAN_TAKE_LAW_CON = "com.bos.dataset.asset.TbAssetCleanTakeLawCon";
	/**
	 * 现金清收
	 */
	String TB_ASSET_CLEAN_TAKE_MONEY = "com.bos.dataset.asset.TbAssetCleanTakeMoney";
	/**
	 * 不良资产核销
	 */
	String TB_ASSET_VERIFY_OFF = "com.bos.dataset.asset.TbAssetVerifyOff";
	/**
	 * 抵债资产冲销
	 */
	String TB_ASSET_WRITE_OFF = "com.bos.dataset.asset.TbAssetWriteOff";
	/**
	 * 抵债资产冲销关联合同
	 */
	String TB_ASSET_WRITE_OFF_LOAN = "com.bos.dataset.asset.TbAssetWriteOffLoan";
	/**
	 * 不良资产移交
	 */
	String TB_ASSET_TRANSFER = "com.bos.dataset.asset.TbAssetTransfer";
	/**
	 * 不良资产逆移交
	 */
	String TB_ASSET_RETRANSFER = "com.bos.dataset.asset.TbAssetRetransfer";
	/**
	 * 不良资产管户变更
	 */
	String TB_ASSET_CHANGE_MGR = "com.bos.dataset.asset.TbAssetChangeMgr";
}
