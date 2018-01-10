package com.bos.bizApply;

import com.bos.biz.product.AddWhiteAction;
import com.bos.biz.product.AftBAction;
import com.bos.biz.product.AftCAction;
import com.bos.biz.product.AssetAction;
import com.bos.biz.product.AssetChangeMgrAction;
import com.bos.biz.product.AssetRetransferAction;
import com.bos.biz.product.AssetTransferAction;
import com.bos.biz.product.BizAction;
import com.bos.biz.product.BizUnfreezeAction;
import com.bos.biz.product.BizZykhAction;
import com.bos.biz.product.BizfreezeAction;
import com.bos.biz.product.CrtAction;
import com.bos.biz.product.CrtBzjAction;
import com.bos.biz.product.CsmCorpScaleIdentifyAction;
import com.bos.biz.product.CsmxfeAction;
import com.bos.biz.product.DbjgAction;
import com.bos.biz.product.DelWhiteAction;
import com.bos.biz.product.GrdAction;
import com.bos.biz.product.IrmAction;
import com.bos.biz.product.PayAction;
import com.bos.biz.product.PayBack;
import com.bos.biz.product.RepayBack;

public enum ProcessType {
	biz(new BizAction()), // 业务申请
	irm(new IrmAction()), // 额度申请
	grd(new GrdAction()), // 评级申请
	bizjd(new BizUnfreezeAction()), // 解冻
	bizdj(new BizfreezeAction()), // 冻结
	zykh(new BizZykhAction()),//存单质押扣划
	dbjg(new DbjgAction()), // 专业担保协议
	crt(new CrtAction()), // 合同签约
	crt_bzj(new CrtBzjAction()), // 保证金
	pay(new PayAction()), // 出账放款
	aftb(new AftBAction()), // 贷后变更
	aftc(new AftCAction()), // 贷后检查
	asset(new AssetAction()), // 不良资产处置方案
	asset_transfer(new AssetTransferAction()), // 不良资产移交
	asset_retransfer(new AssetRetransferAction()), // 不良资产逆移交
	asset_change_mgr(new AssetChangeMgrAction()), // 不良资产管户权变更
	crm_corp_scale_identify(new CsmCorpScaleIdentifyAction()), // 企业规模认定
	payback(new PayBack()), // 放款撤销
	repayback(new RepayBack()),// 还款撤销
	whitadd(new AddWhiteAction()), // 添加白名单
	whitdel(new DelWhiteAction()), // 移除白名单
	csmxfe(new CsmxfeAction()); // 机构拆并
	private IProcessAction action;

	private ProcessType(IProcessAction action) {
		this.action = action;
	}

	public IProcessAction getAction() {
		return action;
	}

	public static ProcessType get(String type) {
		ProcessType[] temps = ProcessType.values();
		for (ProcessType p : temps) {
			if (p.toString().equals(type)) {
				return p;
			}
		}
		return null;
	}
}
