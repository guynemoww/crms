/*
 * Copyright 2013 Primeton Technologies Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bos.utp.org;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.org.util.OrgHelper;
import com.bos.utp.org.util.OrgResponse;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.spring.DASDaoSupport;
import commonj.sdo.DataObject;

/**
 * 岗位服务类
 *
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class OrgPositionService extends DASDaoSupport implements IOrgPositionService {
	
	private IOrgEmppositionService empPositionService = null;
	
	public OrgResponse addOrgPosition(OmPosition position){
		if(!validatePosicode(position)){
			return new OrgResponse(false, "岗位编号："+position.getPosicode()+"已存在");
		}
		/*
		OmPosition parentPosition = position.getOmPosition();
		if(parentPosition != null && String.valueOf(parentPosition.getPositionid()) != null){
			parentPosition = getOrgPositionWithDetail(parentPosition);
		}
		getDASTemplate().getPrimaryKey(position);
		OrgHelper.expandPositionPropertyByParent(position, parentPosition);
		getDASTemplate().insertEntity(position);
		if(parentPosition != null && String.valueOf(parentPosition.getPositionid()) != null){
			OrgHelper.expandParentPositionProperty(parentPosition);
			getDASTemplate().updateEntity(parentPosition);
		}*/
		return new OrgResponse(true, "添加成功");
	}

	public void deleteOrgPosition(OmPosition[] orgPositions ){
		if(orgPositions == null) return;
		for(OmPosition orgPosition:orgPositions){
			getDASTemplate().deleteEntityCascade(orgPosition);
		}
	}

	public void getOrgPosition(OmPosition orgPosition){
		getDASTemplate().expandEntity(orgPosition);
	}
	
	private OmPosition getOrgPositionWithDetail(OmPosition position){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmPosition.QNAME);
		dasCriteria.addAssociation("orgPosition");
		dasCriteria.addAssociation("orgOrganization");
		dasCriteria.add(ExpressionHelper.eq("positionid", position.getPositionid()));
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		if(positions != null && positions.length == 1){
			return positions[0];
		}
		return OmPosition.FACTORY.create();
	}
	
	

	public OmPosition[] queryOrgPositions(CriteriaType criteriaType,PageCond pageCond){
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		dasCriteria.addAssociation("omOrganization");
		dasCriteria.addAssociation("omPosition");
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntityWithPage(OmPosition.class, dasCriteria, pageCond);
		return positions;
	}


    public OrgResponse updateOrgPosition(OmPosition position){
		if(!validatePosicode(position)){
			return new OrgResponse(false, "不能修改为已存在的岗位编号："+position.getPosicode());
		}
		getDASTemplate().updateEntity(position);
		return new OrgResponse(true, "修改成功");
    }

	/**
	 * 获取岗位下的所有子岗位
	 * @param positionid
	 * @return
	 */
	public OmPosition[] querySubPositions(String positionid) {
		if(StringUtils.isBlank(positionid)) {
			return new OmPosition[0];
		}
		
		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgPosition.positionid", positionid));
		
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		
		return positions;
	}
	
//	/**
//	 * 获取岗位下的所有员工
//	 * @param orgid
//	 * @return
//	 */
//	public QueryPositionEmp[] queryEmployeesOfPosition(String positionid) {
//		if(StringUtils.isBlank(positionid)) {
//			return new QueryPositionEmp[0];
//		}
//		
//		// 设置查询条件
//		IDASCriteria dasCriteria = getDASTemplate().createCriteria(QueryPositionEmp.QNAME);
//		dasCriteria.add(ExpressionHelper.eq("positionid", positionid));
//		
//		QueryPositionEmp[] emps = getDASTemplate().queryEntitiesByCriteriaEntity(QueryPositionEmp.class, dasCriteria);
//		
//		return emps;
//	}
	
	/**
	 * 岗位的总记录
	 * @param criteria
	 * @return 总记录
	 */
	public int countOrgPositions(CriteriaType criteria) {
		criteria.set_entity(OmPosition.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	
	/**
	 * 获取子岗位的同时获取父岗位信息
	 * @param position
	 */
	public OmPosition getOrgPositionWithParent(OmPosition position){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("positionid", position.getPositionid()));
		dasCriteria.addAssociation("orgPosition");
		dasCriteria.addAssociation("orgOrganization");
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		if(positions != null && positions.length == 1){
			return positions[0];
		}
		return OmPosition.FACTORY.create();
	}
	
	/* (non-Javadoc)
	 * @see om.bos.utp.org.IOmPositionService#deleteOrgPositionCascade(om.bos.utp.org.dataset.OrgOrganization)
	 */
	public void deleteOrgPositionCascadeByParentOrg(DataObject parentOrg) {
		//删除子岗位之前，先删除子岗位和员工的关联关系
		OmPosition[] children = querySubPositionsByOrg(parentOrg);
		for(OmPosition position : children){
			empPositionService.deleteEmppositionsByPosition(position);
		}
		deleteOrgPosition(children);
	}

	/**
	 * 根据父机构查询所有子岗位
	 * @param parentOrg 父机构
	 * @return 子岗位列表
	 */
	public OmPosition[] querySubPositionsByOrg(DataObject parentOrg) {
		if(parentOrg.get("orgid") != null){
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmPosition.QNAME);
			dasCriteria.add(ExpressionHelper.eq("omOrganization.orgid", parentOrg.getBigDecimal("orgid")));
			return getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		}
		return new OmPosition[0];
	}
	
	/**
	 * 检验岗位code是否合格
	 * @param posicode
	 * @return true合格，false不合格
	 */
	public boolean validatePosicode(OmPosition position) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("posicode", position.getPosicode()));
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		if(positions == null || positions.length == 0){
			return true;
		}
		if(String.valueOf(position.getPositionid()) != null && positions[0].getPositionid()== position.getPositionid()){//修改的情况，只能为1个
			return positions.length == 1;
		}else{//新增,必定没有
			return positions.length == 0;
		}
	}
	
	public void deleteOrgPosition(String positionid, String parentPositionid, String parentType) {
		//以本节点为基准删除
		if(!StringUtils.isBlank(positionid)){
			//先删其子岗位的
			OmPosition[] positions = querySubPositions(positionid);
			for(OmPosition position : positions){
				deleteOrgPosition(position.getPositionid()+"", null, null);
			}
			//删除这个岗位和人员的关联关系
			OmPosition position = OmPosition.FACTORY.create();
			position.setPositionid(new BigDecimal(positionid));
			empPositionService.deleteEmppositionsByPosition(position);
			//删除自身
			deleteOrgPosition(new OmPosition[]{position});
		}
	}
	
	public void deleteOrgPosition(String positionid) {
		if(!StringUtils.isBlank(positionid)){
			OmPosition position = OmPosition.FACTORY.create();
			position.setPositionid(new BigDecimal(positionid));
			deleteOrgPosition(new OmPosition[]{position});
		}
	}
	
	public void setEmpPositionService(IOrgEmppositionService empPositionService) {
		this.empPositionService = empPositionService;
	}

}