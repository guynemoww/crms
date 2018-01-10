/**
 * 
 */
package com.bos.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.ConSecuDueNumVo;
import com.primeton.tsl.DueNumInfoVo;
import com.primeton.tsl.PayConInfo;

import commonj.sdo.DataObject;

/**
 * @author chenchuan
 * @date 2016-05-13 16:02:49
 * 
 */
@Bizlet("")
public class AssestProject {

	@Bizlet("资产证券化调用计量接口")
	public String assestProject(String projectId) {
		try {
			String bus_date = GitUtil.getBusiDateYYYYMMDD();// 当前营业日期
			// String bus_date = "20160428";// 当前营业日期
			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());// 交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			DataObject project = DataObjectUtil.createDataObject("com.bos.project.project.TbConProject");
			project.set("projectId", projectId);
			DatabaseUtil.expandEntity("default", project);

			DataObject relation = DataObjectUtil.createDataObject("com.bos.project.project.TbConProjectRelation");
			relation.set("projectId", projectId);
			DataObject relations[] = DatabaseUtil.queryEntitiesByTemplate("default", relation);

			if (relations == null || relations.length == 0) {
				return "资产证券化项目下没有借据";
			}
			// 资产证券化项目基本信息
			ConSecuDueNumVo vo = new ConSecuDueNumVo();
			vo.getBaseVO().setTranCod("MDA_2099");// 交易代码
			vo.getBaseVO().setOpr("HX001");// 操作员
			vo.getBaseVO().setAut("HX001");// 授权员
			vo.getBaseVO().setAcsMethStan(lcsStan);// 接入系统流水号
			vo.getBaseVO().setRcnStan(lcsStan);// 对账流水号
			vo.getBaseVO().setSupStan(lcsStan);// 自动生成9位放款流水号
			vo.getBaseVO().setTranFrom("001");// 业务渠道来源 001-信贷系统
			vo.getBaseVO().setAccSysDate(bus_date);// 营业日期 检查该机构在机构表中是否存在
			vo.getBaseVO().setTranDate(bus_date);// 接入系统营业日期
			vo.getBaseVO().setTranTimes("1");// 交易次数标志 1次交易后填2，二次交易后填3
			vo.getBaseVO().setToCoreSys("0");// 交易是否转发核心系统标志 0=不转发；1=向核心系统转发
			vo.getBaseVO().setLegPerCod("3600");
			vo.setConSecuNum(project.getString("secuNum"));// 合同编号或者资产证券化编号
			vo.setRcvDate(bus_date);// 发送日期
			vo.setTypeNo("A00");// 类型编号资产证券化检查typeNo=A00

			// 借据信息列表
			List<DueNumInfoVo> list = new ArrayList<DueNumInfoVo>();
			DueNumInfoVo due = new DueNumInfoVo();
			for (int i = 0; i < relations.length; i++) {
				// 根据关联关系中的借据ID查到借据编号
				DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				summary.set("summaryId", relations[i].getString("summaryId"));
				DatabaseUtil.expandEntity("default", summary);
				due.setDueNum(summary.getString("summaryNum"));// 借据编号
				due.setRcvDate(bus_date);// 发送日期
				list.add(due);
			}
			vo.setDueNumInfoVoList(list);
			// 资产证券化第一次交易
			Object[] params1 = new Object[2];
			params1[0] = "MDA_2099";
			params1[1] = vo;
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = logicComponent.invoke("newDataInsertCheck", params1);
			DataObject vo1 = (DataObject) objs[0];
			BaseVO baseVO = (BaseVO) vo1.get("baseVO");
			String returnCode = (String) baseVO.getErrCod();
			if (!"200".equals(returnCode)) {
				return baseVO.getErrMsg();
			}

		} catch (Throwable e) {
			return "调用计量接口异常";
		}
		return null;

	}

	@Bizlet("将数据插入到计量")
	public String sendSummaryToJL(String projectId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);

		delSummaryToJL(projectId);// 将计量原有的数据删除
		Object[] summarys = DatabaseExt.queryByNamedSql("default", "com.bos.project.project.findSummaryForJL", map);// 查询要插入计量的数据
		if (null != summarys && summarys.length > 0) {
			DatabaseExt.executeNamedSqlBatch("sdp", "com.bos.project.project.insertSummaryForJL", summarys);//将数据插入计量
		}
		return null;

	}

	@Bizlet("将计量原有的数据删除")
	public String delSummaryToJL(String projectId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);

		Object[] relations = DatabaseExt.queryByNamedSql("default", "com.bos.project.project.findContractList", map);// 查询项目下所有的借据
		if (null != relations && relations.length > 0) {
			DatabaseExt.executeNamedSqlBatch("sdp", "com.bos.project.project.delSummaryForJL", relations);	// 将计量已有的借据删除
		}
		return null;

	}

}
