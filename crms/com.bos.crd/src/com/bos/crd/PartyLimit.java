/**
 * 
 */
package com.bos.crd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.bos.biz.MathHelper;
import com.bos.bizApply.getBasicRate;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-07-18 16:30:51
 *公司客户 计算客户额度
 *财报计算数据 *净资产调节系数*行业调节系数*评级调节系数-全部负债
 *=E×R×V－D
 *计算过程中，E,R,D返回值为0时，表示计算错误
 */
@Bizlet("")
public class PartyLimit {

	public static TraceLogger logger = new TraceLogger(PartyLimit.class);

	//获取客户额度
	@Bizlet("")
	public BigDecimal getPartyLimit(String partyId) {

		//客户额度不能为空
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->begin");
		if (null == partyId || "".equals(partyId)) {
			logger.info("------3231------>计算公司客户客户额度时，客户Id为空！");
			return null;
		}
		//判断是否有最近一期月报及最近两年年报
		DataObject finance = DataObjectUtil
				.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
		finance.set("partyId", partyId);
		finance.set("financeStatusCd", "02");
		finance.set("financeTypeCd", "4");
		finance.set("customerTypeCd", "002");
		DatabaseUtil.expandEntityByTemplate("default", finance, finance);
		if (null == finance.get("financeId")) {
			logger.info("------3231------>客户额度测算------partyId=" + partyId
					+ "------->客户无月报");
			return new BigDecimal("0");
		}

		HashMap map = new HashMap();

		PartyLimit pl = new PartyLimit();
		//获取有效净资产E
		BigDecimal e = pl.getPartyE(partyId);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->有效净资产E------>" + e);
		//获取行业调节系数R
		BigDecimal r = pl.getParytR(partyId);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->行业调节系数R------>" + r);
		//获取评级调节系数V
		BigDecimal v = pl.getPartyV(partyId);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->评级调节系数V------>" + v);
		//获取客户全部负债D
		BigDecimal d = pl.getPartyD(partyId);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->客户全部负债D------>" + d);

		///////计算客户额度
		String gs = "e*r*v-d";

		map.put("e", e);
		map.put("r", r);
		map.put("v", v);
		map.put("d", d);

		BigDecimal partyLimit = MathHelper.expressionBigDecimal(gs, map);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->客户额度partyLimit------>" + partyLimit);
		return partyLimit;
	}

	/**
	 * 获取客户有效净资产E
	 * 返回0，则表示计算错误
	 * E=E0×净资产收益率调节系数
	 * */
	public BigDecimal getPartyE(String partyId) {

		HashMap map = new HashMap();
		map.put("partyId", partyId);
		//本年最近月度的所有者权益 qy
		Object[] objQy = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getQy", map);
		DataObject objQy0 = (DataObject) objQy[0];
		BigDecimal qy = objQy0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->本年最近月度的所有者权益qy------>" + qy);

		//账龄两年（含）以上的应收款项（包括应收账款、其他应收款）   财务补充资料   yskx
		Object[] objYskx = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getYskx", map);
		DataObject objYskx0 = (DataObject) objYskx[0];
		BigDecimal yskx = objYskx0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->账龄两年（含）以上的应收款项（包括应收账款、其他应收款）yskx------>" + yskx);

		//存货（不含产成品和库存商品） 财务补充资料  ch
		Object[] objCh = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getCh", map);
		DataObject objCh0 = (DataObject) objCh[0];
		BigDecimal ch = objCh0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->存货ch------>" + ch);
		Object[] objCk = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getCK", map);
		DataObject objCk0 = (DataObject) objCk[0];
		BigDecimal ck = objCk0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->不含产成品和库存商品ck------>" + ck);

		//折扣率   存货折扣率=1-存贷最高抵押率	zkl
		BigDecimal zkl = new BigDecimal("0.3");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->折扣率zkl------>" + zkl);

		//无形资产（不含土地使用权、采矿权等市场价值较高的资产）   财务补充资料	wxzc
		Object[] objWxzc = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getWxzc", map);
		DataObject objWxzc0 = (DataObject) objWxzc[0];
		BigDecimal wxzc = objWxzc0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->无形资产------>" + wxzc);
		Object[] objTc = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getTc", map);
		DataObject objTc0 = (DataObject) objTc[0];
		BigDecimal tc = objTc0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->不含土地使用权、采矿权等市场价值较高的资产tc------>" + tc);

		//待处理财产损失	财务补充资料	 cs
		Object[] objCs = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getCs", map);
		DataObject objCs0 = (DataObject) objCs[0];
		BigDecimal cs = objCs0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->待处理财产损失cs------>" + cs);

		//净资产收益率调节系数		净资产收益率调节系数=近两年加权净资产收益率（前两年×40%+前一年×60%）/（一年期贷款基准利率*150%）	tjxs
		Date date = GitUtil.getBusiDate();
		String year = DateHelper.formatDateYYYYMMDD(
				DateHelper.calculateDate(date, -1, 0, 0)).substring(0, 4);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->净资产收益率调节系数去年年分------>" + year);
		//去年收益率
		BigDecimal syl0;
		map.put("year", year);
		Object[] objSyl0 = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getSyl0", map);
		if (0 == objSyl0.length) {
			syl0 = new BigDecimal("0");
		} else {
			DataObject sylA0 = (DataObject) objSyl0[0];
			syl0 = sylA0.getBigDecimal("INDEX_VALUE_DATA_TYPE");
		}
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->去年收益率syl0------>" + syl0);
		//前年收益率
		BigDecimal syl1;
		year = DateHelper.formatDateYYYYMMDD(
				DateHelper.calculateDate(date, -2, 0, 0)).substring(0, 4);
		map.put("year", year);
		Object[] objSyl1 = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getSyl1", map);
		if (0 == objSyl1.length) {
			syl1 = new BigDecimal("0");
		} else {
			DataObject sylA1 = (DataObject) objSyl1[0];
			syl1 = sylA1.getBigDecimal("INDEX_VALUE_DATA_TYPE");
		}
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->前年收益率syl1------>" + syl1);
		//一年期基准利率
		getBasicRate gb = new getBasicRate();
		BigDecimal jzlv = (BigDecimal) gb.getBasicrate(12, "1");
		if (null == jzlv || new BigDecimal("0") == jzlv) {
			jzlv = new BigDecimal("1");
		}
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->一年期基准利率jzlv------>" + jzlv);
		String gsTjxs = "(p1*syl1+p2*syl2)/(p3*jzlv/p4)";

		//E0=本年最近月度的所有者权益-账龄两年（含）以上的应收款项（包括应收账款、其他应收款）-存货（不含产成品和库存商品）×折扣率-无形资产（不含土地使用权、采矿权等市场价值较高的资产）－待处理财产损失
		String gsE0 = "qy-yskx-(ch-ck)*zkl-(wxzc-tc)-cs";
		///////////////计算	
		map.put("p1", new BigDecimal("0.4"));
		map.put("p2", new BigDecimal("0.6"));
		map.put("p3", new BigDecimal("1.5"));
		map.put("p4", new BigDecimal("100"));
		map.put("syl1", syl1);
		map.put("syl2", syl0);
		map.put("jzlv", jzlv);
		map.put("qy", qy);
		map.put("yskx", yskx);
		map.put("ch", ch);
		map.put("ck", ck);
		map.put("zkl", zkl);
		map.put("wxzc", wxzc);
		map.put("tc", tc);
		map.put("cs", cs);
		//调节系数
		BigDecimal tjxs = MathHelper.expressionBigDecimal(gsTjxs, map);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->净资产调节系数------>" + tjxs);
		//E0
		BigDecimal E0 = MathHelper.expressionBigDecimal(gsE0, map);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->净资产E0------>" + E0);
		//E
		String gsE = "E0 * tjxs";
		map.put("E0", E0);
		map.put("tjxs", tjxs);

		BigDecimal E = MathHelper.expressionBigDecimal(gsE, map);

		return E;
	}

	/**
	 * 获取行业调节系数R
	 * 先用行业大类查询系数表，如果找不到则用门类查询，再找不到返回0，表示行业系数未配置
	 * 找不到则返回0，表示评级系数未配置
	 * */
	public BigDecimal getParytR(String partyId) {
		logger.info("------>3231------>客户额度计算，获取客户行业系数");
		//获取客户信息
		DataObject corporation = DataObjectUtil
				.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
		corporation.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", corporation);
		//行业门类
		String typeCd = (String) corporation.get("industrialTypeCd");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->行业门类------>" + typeCd);
		//行业大类
		String typeBigCd = (String) corporation.get("industrialTypeBigCd");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->行业大类------>" + typeBigCd);
		HashMap map = new HashMap();
		map.put("typeCd", typeCd);
		map.put("typeBigCd", typeBigCd);

		Object[] objs = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getPartyLimitR", map);
		DataObject obj = (DataObject) objs[0];
		BigDecimal r = (BigDecimal) obj.get("XS");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->行业调节系数R------>" + r);
		return r;
	}

	/**
	 * 获取评级调节系数V
	 * 找不到则返回0，表示评级系数未配置
	 * */
	public BigDecimal getPartyV(String partyId) {
		logger.info("------>3231------>客户额度计算，获取评级系数");
		//获取评级信息
		DataObject irm = DataObjectUtil
				.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingResult");
		irm.set("partyId", partyId);
		irm.set("ratingState", "03");
		DatabaseUtil.expandEntityByTemplate("default", irm, irm);
		String creditRatingCd = (String) irm.get("creditRatingCd");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->客户评级结果为------>" + creditRatingCd);
		DataObject xs = DataObjectUtil
				.createDataObject("com.bos.dataset.crd.TbCrdXs");
		xs.set("xslx", creditRatingCd);
		xs.set("type", "V");
		DatabaseUtil.expandEntityByTemplate("default", xs, xs);
		BigDecimal v = (BigDecimal) xs.get("xs");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->评级调节系数V------>" + v);
		return v;
	}

	/**
	 * 获取客户负债D
	 * 根据财报信息计算
	 * 返回0表示不存在
	 * */
	public BigDecimal getPartyD(String partyId) {
		HashMap map = new HashMap();
		map.put("partyId", partyId);
		Object[] objFz = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getFz", map);
		DataObject obj = (DataObject) objFz[0];
		BigDecimal D = (BigDecimal) obj.get("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->客户负债D------>" + D);
		return D;
	}

	/**
	 * @param partyId 
	 * 
	 * */
	@Bizlet("")
	public BigDecimal getPartyLimitSy(String partyId) {
		//客户额度不能为空
		logger.info("------3231------>事业单位客户额度测算------partyId=" + partyId
				+ "------->begin");
		//判断是否有最近一期月报及最近两年年报
		DataObject finance = DataObjectUtil
				.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
		finance.set("partyId", partyId);
		finance.set("financeStatusCd", "02");
		finance.set("customerTypeCd", "004");
		DatabaseUtil.expandEntityByTemplate("default", finance, finance);
		if (null == finance.get("financeId")) {
			logger.info("------3231------>客户额度测算------partyId=" + partyId
					+ "------->客户无财报");
			return new BigDecimal("0");
		}
		if (null == partyId || "".equals(partyId)){
			logger.info("------3231------>计算事业单位客户客户额度时，客户Id为空！");
			return null;
		}
		HashMap map = new HashMap();
		map.put("partyId", partyId);

		//近三年事业性收费资金自留部分平均值
		Object[] objZlpj = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getZlpj", map);
		DataObject objZlpj0 = (DataObject) objZlpj[0];
		BigDecimal zlpj = objZlpj0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->近三年事业性收费资金自留部分平均值zlpj------>" + zlpj);
		
		//已停止收费项目
//		Object[] objYtzsf = DatabaseExt.queryByNamedSql("default",
//				"com.bos.crd.partyLimit.getYtzsf", map);
//		DataObject objYtzsf0 = (DataObject) objYtzsf[0];
//		BigDecimal ytzsf = objYtzsf0.getBigDecimal("PROJECT_VALUE");
//		logger.info("------3231------>客户额度测算------partyId=" + partyId
//				+ "------->已停止收费项目ytzsf------>" + ytzsf);
		
		//上年度经费结余
		Object[] objJfjy = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getJfjy", map);
		DataObject objJfjy0 = (DataObject) objJfjy[0];
		BigDecimal jfjy = objJfjy0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->上年度经费结余jfjy------>" + jfjy);

		//专项（信贷）拨款
		Object[] objZxbk = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getZxbk", map);
		DataObject objZxbk0 = (DataObject) objZxbk[0];
		BigDecimal zxbk = objZxbk0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->专项（信贷）拨款zxbk------>" + zxbk);

		//全部有息债务
		Object[] objYxzw = DatabaseExt.queryByNamedSql("default",
				"com.bos.crd.partyLimit.getYxzw", map);
		DataObject objYxzw0 = (DataObject) objYxzw[0];
		BigDecimal yxzw = objYxzw0.getBigDecimal("PROJECT_VALUE");
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->全部有息债务yxzw------>" + yxzw);

		///////客户最高额度 = 近三年事业性收费资金自留部分平均值（剔除已停止收费项目） + 上年度经费结余 + 专项（信贷）拨款 - 全部有息债务。
		String gs = "zlpj-ytzsf+jfjy+zxbk-yxzw";

		map.put("zlpj", zlpj);
		map.put("ytzsf", 0);
		map.put("jfjy", jfjy);
		map.put("zxbk", zxbk);
		map.put("yxzw", yxzw);

		BigDecimal partyLimit = MathHelper.expressionBigDecimal(gs, map);
		logger.info("------3231------>客户额度测算------partyId=" + partyId
				+ "------->客户额度partyLimit------>" + partyLimit);
		return partyLimit;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
	}

}
