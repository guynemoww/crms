package com.bos.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.bos.bps.util.CommonUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.das.entity.impl.hibernate.mapping.Map;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

@Bizlet("Test")
public class Test {

	@Bizlet("")
	public void saveEntity(DataObject entity, String bizId) {
		//获取实体名
		Type type = entity.getType();
		String name = type.getName();
		String uri = type.getURI();
		String entityName = uri+"."+name;
		
		//查询配置表信息
		DataObject tableEntity = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbLogPz");
		//获取留痕表信息
		tableEntity.set("dm", entityName);
		int j = DatabaseUtil.expandEntityByTemplate("default", tableEntity, tableEntity);
		if (j!=0){
			//查询数据库原实体
			String tableEntityId = tableEntity.getString("stzjm");
			DataObject oldEntity = DataObjectUtil.createDataObject(entityName);
			oldEntity.set(tableEntityId, entity.getString(tableEntityId));
			DatabaseUtil.expandEntity("default", oldEntity);
	
			//无对比数据则直接保存
			if (null != oldEntity.getDate("createTime")) {
				List<Property> list = oldEntity.getInstanceProperties();
				for(int i = 0 ;i<list.size()-1;i++){
					//对比前后数据为空则直接跳过
					if(null == (oldEntity.getString(i))&&(null == entity.getString(i))){
						continue;
					}
//					//创建时间与更新时间不参与对比
//					if("createTime".equals(list.get(i).toString()) || "updateTime".equals(list.get(i).toString()) ){
//						continue;
//					}
					//对比差异并记录
					if(!oldEntity.getString(i).equals(entity.getString(i))){
						DataObject cy = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbLogCy");
						cy.set("ywId", bizId);
						cy.set("bm", tableEntity.getString("bm"));//表名
						cy.set("stm", tableEntity.getString("stm"));//实体名
						cy.set("ljbm", tableEntity.getString("ljbm"));//逻辑表名
						cy.set("bzdm", list.get(i).getName());//字段名
						cy.set("stzdm",list.get(i).getName());//实体字段名
						cy.set("ljzdm",list.get(i).getName());//逻辑字段名
						cy.set("yz", oldEntity.getString(i));//原值
						cy.set("xz", entity.getString(i));//新值
						cy.set("userName", CommonUtil.getIUserObject().getUserId());//经办人
						cy.set("createTime",GitUtil.getBusiDate());//经办时间
						DatabaseUtil.insertEntity("default", cy);
					}
				}
	
			}
		}
		//对比差异后，将业务数据更新到数据库
		DatabaseUtil.saveEntity("default", entity);
	}

	//还原留痕信息
	@Bizlet("")
	public void restoreEntity(String bizId) {
		//查询该业务主键的修改记录

		//按时间倒叙循环查询修改记录
		//for(){
		//拼接查询记录
		String updateSql = "update ";
		//执行sql
		//}
	}

	//如果还原部分最新一次业务留痕信息
	@Bizlet("")
	public void restoreEntity(DataObject bizEntity) {
		//获取业务表及主键（配置表）

		//获取业务更新时间

		//查询该业务主键在更新时间至今 的修改记录

		//按时间倒叙循环查询修改记录
		//for(){
		//拼接查询记录
		String updateSql = "update ";
		//执行sql
		//}
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		DataObject biz = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizApply");
		biz.set("applyId", "1");
		DatabaseUtil.expandEntity("default", biz);
		biz.set("bizNum", "3");
		Test t = new Test();
		t.saveEntity(biz, "1");
	}

}
