package com.bos.bps.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.workflow.omservice.IWFOMService;
import com.eos.workflow.omservice.IWFPermissionService;
import com.eos.workflow.omservice.WFParticipant;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.workflow.api.PageCond;
import com.primeton.workflow.api.ParticipantType;

/**
 * 
 * 工作流组织机构接口实现<BR>
 * 
 * @author 陈志 chenzhi (mailto:chenzhi@primeton.com)
 */
public class WFOMServiceImpl implements IWFOMService {

	public static final String CS_PARTICIPANT_TYPE_PERSON = "person"; // 人员

	public static final String CS_PARTICIPANT_TYPE_ROLE = "role"; // 角色

	public static final String CS_PARTICIPANT_TYPE_ORGANIZATION = "organization";// 机构

	public static final String CS_PARTICIPANT_TYPE_POSITION = "position";// 岗位

	public static final String CS_PARTICIPANT_TYPE_WORKGROUP = "workgroup";// 工作组

	public static final String CS_PARTICIPANT_TYPE_DUTY = "duty";// 职务

	public static final String CS_PARTICIPANT_TYPE_BUSIORG = "busiorg";// 业务机构

	public static final String CS_PARTICIPANT_TYPE_ORGROLE = "orgrole";// 机构和角色的交集类型

	public static final String CS_PARTICIPANT_TYPE_ORGDUTY = "orgduty";// 机构和职务的交集类型

	// 接口实现的命名sql名称
	private String namedsqlset = "com.bos.bps.impl.workflow";

	// 参与者类型列表
	private static Map<String, ParticipantType> participantTypes = new HashMap<String, ParticipantType>();// Store

	public WFOMServiceImpl() {
		this.namedsqlset = namedsqlset.endsWith(".") ? namedsqlset : namedsqlset + ".";
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WFOMServiceImpl(String namedsqlset) {
		this.namedsqlset = namedsqlset.endsWith(".") ? namedsqlset : namedsqlset + ".";
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() throws Exception {

		// initial the participant
		// types:person,role,organization,position,workgroup,duty,bizorg
		// person 为直接参与者
		// 无子类型
		// 父类型 role,organization,position workgroup
		ParticipantType ptPerson = null;// prefix,code,displayname,description,showatroot,priority,isleaf,subparti,joinpart,joinparttype
		ptPerson = new ParticipantType('P', CS_PARTICIPANT_TYPE_PERSON, "个人", "个人", false, 1, true, null, false, null);

		// role 角色
		// 子类型为 person
		// 无父类型
		ParticipantType ptRole = null;
		List<String> roleChildParticipantTypeCodes = new ArrayList<String>();
		roleChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_PERSON);// person
		ptRole = new ParticipantType('R', CS_PARTICIPANT_TYPE_ROLE, "角色", "角色", true, 3, false, roleChildParticipantTypeCodes, false, null);

		// organization 机构
		// 子类型为 person organization position workgroup
		// 父类型为 organization,bizorg
		ParticipantType ptOrganization = null;
		List<String> organizationChildParticipantTypeCodes = new ArrayList<String>();
		organizationChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_PERSON);// person
		organizationChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_ORGANIZATION);// organization
		organizationChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_POSITION);// position
		organizationChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_WORKGROUP);// workgroup
		ptOrganization = new ParticipantType('O', CS_PARTICIPANT_TYPE_ORGANIZATION, "机构", "机构", true, 4, false, organizationChildParticipantTypeCodes, false, null);

		// position 岗位
		// 子类型为 person,position
		// 父类型为 position,organization,workgroup
		ParticipantType ptPosition = null;
		List<String> positionChildParticipantTypeCodes = new ArrayList<String>();
		positionChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_POSITION);// position
		positionChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_PERSON);// person
		ptPosition = new ParticipantType('Z', CS_PARTICIPANT_TYPE_POSITION, "岗位", "岗位", false, 2, false, positionChildParticipantTypeCodes, false, null);

		// workgroup 工作组
		// 子类型为 person,position,workgroup
		// 父类型为 workgroup organization
		ParticipantType ptWorkgroup = null;
		List<String> workgroupChildParticipantTypeCodes = new ArrayList<String>();
		workgroupChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_PERSON);// person
		workgroupChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_POSITION);// position
		workgroupChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_WORKGROUP);// workgroup
		ptWorkgroup = new ParticipantType('G', CS_PARTICIPANT_TYPE_WORKGROUP, "工作组", "工作组", true, 5, false, workgroupChildParticipantTypeCodes, false, null);

		// duty 职务
		// 子类型为 person,duty
		// 父类型为 duty

		ParticipantType ptDuty = null;
		List<String> dutyChildParticipantTypeCodes = new ArrayList<String>();
		dutyChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_PERSON);
		dutyChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_DUTY);
		ptDuty = new ParticipantType('D', CS_PARTICIPANT_TYPE_DUTY, "职务", "职务", true, 6, false, dutyChildParticipantTypeCodes, false, null);

		// bizorg 业务机构
		// 子类型为 person,organization,bizorg
		// 父类型为 bizorg
		ParticipantType ptBusiorg = new ParticipantType();
		List<String> busiorgChildParticipantTypeCodes = new ArrayList<String>();
		busiorgChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_PERSON);
		busiorgChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_ORGANIZATION);
		busiorgChildParticipantTypeCodes.add(CS_PARTICIPANT_TYPE_BUSIORG);
		ptBusiorg.setPrefix('B');
		ptBusiorg.setCode(CS_PARTICIPANT_TYPE_BUSIORG);
		ptBusiorg.setDisplayName("业务机构");
		ptBusiorg.setDescription("业务机构");
		ptBusiorg.setLeafParticipant(false);
		ptBusiorg.setShowAtRootArea(true);
		ptBusiorg.setPriority(7);
		ptBusiorg.setJuniorParticipantTypeCodes(busiorgChildParticipantTypeCodes);
		ptBusiorg.setJointParticipantType(false);
		ptBusiorg.setJointTypeCodeList(null);
		// 组织角色交集类型
		ParticipantType ptOrgRole = new ParticipantType();
		ptOrgRole.setPrefix('X');
		ptOrgRole.setCode(CS_PARTICIPANT_TYPE_ORGROLE);
		ptOrgRole.setDisplayName("机构和角色");
		ptOrgRole.setJointParticipantType(true);
		ptOrgRole.setPriority(8);
		ArrayList<String> ltOrgRole = new ArrayList<String>();
		ltOrgRole.add(CS_PARTICIPANT_TYPE_PERSON);
		ptOrgRole.setJuniorParticipantTypeCodes(ltOrgRole);
		ArrayList<String> jtOrgRole = new ArrayList<String>();
		jtOrgRole.add(CS_PARTICIPANT_TYPE_ORGANIZATION);
		jtOrgRole.add(CS_PARTICIPANT_TYPE_ROLE);
		ptOrgRole.setJointTypeCodeList(jtOrgRole);
		// 组织职务交集类型
		ParticipantType ptOrgDuty = new ParticipantType();
		ptOrgDuty.setPrefix('Y');
		ptOrgDuty.setCode(CS_PARTICIPANT_TYPE_ORGDUTY);
		ptOrgDuty.setDisplayName("机构和职务");
		ptOrgDuty.setJointParticipantType(true);
		ptOrgDuty.setPriority(9);
		ArrayList<String> ltOrgDuty = new ArrayList<String>();
		ltOrgDuty.add(CS_PARTICIPANT_TYPE_PERSON);
		ptOrgDuty.setJuniorParticipantTypeCodes(ltOrgDuty);
		ArrayList<String> jtOrgDuty = new ArrayList<String>();
		jtOrgDuty.add(CS_PARTICIPANT_TYPE_ORGANIZATION);
		jtOrgDuty.add(CS_PARTICIPANT_TYPE_DUTY);
		ptOrgDuty.setJointTypeCodeList(jtOrgDuty);

		// 加入类别集合缓存
		participantTypes.put(CS_PARTICIPANT_TYPE_PERSON, ptPerson);
		participantTypes.put(CS_PARTICIPANT_TYPE_ROLE, ptRole);
		participantTypes.put(CS_PARTICIPANT_TYPE_ORGANIZATION, ptOrganization);
		participantTypes.put(CS_PARTICIPANT_TYPE_POSITION, ptPosition);
		participantTypes.put(CS_PARTICIPANT_TYPE_WORKGROUP, ptWorkgroup);
		participantTypes.put(CS_PARTICIPANT_TYPE_DUTY, ptDuty);
		participantTypes.put(CS_PARTICIPANT_TYPE_BUSIORG, ptBusiorg);

		participantTypes.put(CS_PARTICIPANT_TYPE_ORGROLE, ptOrgRole);
		participantTypes.put(CS_PARTICIPANT_TYPE_ORGDUTY, ptOrgDuty);
	}

	/**
	 * 根据ID和类型获取相应参与者
	 */
	public WFParticipant findParticipantByID(String typeCode, String participantID) {
		LogUtil.logDebug("findParticipantByID(typeCode={1};participantID={2})", null, new Object[] { typeCode, participantID });
		ParticipantType partType = participantTypes.get(typeCode);
		if (partType == null) {
			LogUtil.logDebug("findParticipantByID(typeCode={1};participantID={2}):partType is null!", null, new Object[] { typeCode, participantID });
			return null;
		}

		WFParticipant participant = new WFParticipant(participantID, participantID, typeCode);

		try {

			List<WFParticipant> ret = new ArrayList<WFParticipant>();
			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {// 机构
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				ret = queryNamedSql("query_organization", parameterMap);
			} else if (CS_PARTICIPANT_TYPE_ROLE.equalsIgnoreCase(typeCode)) {// 角色
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				ret = queryNamedSql("query_role", parameterMap);
			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {// 岗位
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				ret = queryNamedSql("query_position", parameterMap);
			} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(typeCode)) {// 个人
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				ret = queryNamedSql("query_person", parameterMap);
			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {// 工作组
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				ret = queryNamedSql("query_workgroup", parameterMap);
			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {// 职务
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				ret = queryNamedSql("query_duty", parameterMap);
			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {// 业务机构
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				ret = queryNamedSql("query_busiorg", parameterMap);
			}
			if (ret != null && ret.size() > 0) {
				participant = ret.get(0);
				participant.setTypeCode(typeCode);
				participant.setId(participantID);
			}
		} catch (Exception e) {
			LogUtil.logError("findParticipantByID(typeCode={1};participantID={2})", e, new Object[] { typeCode, participantID });
		}
		return participant;
	}

	/**
	 * 获取某个类型的根参与者
	 */
	public List<WFParticipant> findRootParticipants(String typeCode) {

		LogUtil.logDebug("findRootParticipants(typeCode={1})", null, new Object[] { typeCode });
		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();

		ParticipantType partType = participantTypes.get(typeCode);
		if (partType == null || !partType.isShowAtRootArea()) {
			if (partType == null) {
				LogUtil.logInfo("findRootParticipants(typeCode={1}):no such ParticipantType ", null, new Object[] { typeCode });
			} else {
				LogUtil.logInfo("findRootParticipants(typeCode={1}):no such ParticipantType in root ", null, new Object[] { typeCode });
			}
			return wfParticipants;
		}

		try {
			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_rootorganizations", null);

			} else if (CS_PARTICIPANT_TYPE_ROLE.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_rootroles", null);

			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_rootpositions", null);

			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_rootgroups", null);

			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_rootdutys", null);

			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_rootbusiorgs", null);

			}
		} catch (Exception e) {
			LogUtil.logError("findRootParticipants(typeCode={1})", e, new Object[] { typeCode });
		}

		return wfParticipants;
	}

	/**
	 * 查询所有下级参与者。对于指定的参与者，该方法需要返回该参与者的各种不同类型的子参与者
	 */
	public List<WFParticipant> getAllChildParticipants(String typeCode, String participantID) {

		LogUtil.logDebug("getAllChildParticipants(typeCode={1}, participantID={2})", null, new Object[] { typeCode, participantID });

		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();
		try {
			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allorgsub", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_ROLE.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				wfParticipants = queryNamedSql("query_allrolesub", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allpositionsub", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allgroupsub", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_alldutysub", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allbusiorgsub", parameterMap);
			}
		} catch (Exception e) {
			LogUtil.logError("getAllChildParticipants(typeCode={1}, participantID={2})", e, new Object[] { typeCode, participantID });
		}
		return wfParticipants;
	}

	/**
	 * 查询所有的上级参与者。在实现的时候，应该将指定参与者的所有父参与者查询出来，并返回
	 */
	public List<WFParticipant> getAllParentParticipants(String typeCode, String participantID) {

		LogUtil.logDebug("getAllParentParticipants(typeCode={1}, participantID={2})", null, new Object[] { typeCode, participantID });

		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();
		try {
			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allorgparents", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allpositionparents", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allgroupparents", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				wfParticipants = queryNamedSql("query_allpersonparents", parameterMap);
				wfParticipants.addAll(queryNamedSql("query_allpersonparents1", parameterMap));
				wfParticipants.addAll(queryNamedSql("query_allpersonparents2", parameterMap));
				wfParticipants.addAll(queryNamedSql("query_allpersonparents3", parameterMap));

			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_alldutyparents", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				wfParticipants = queryNamedSql("query_allbusiorgparents", parameterMap);

			}
		} catch (Exception e) {
			LogUtil.logError("getAllParentParticipants(typeCode={1}, participantID={2})", e, new Object[] { typeCode, participantID });
		}
		return wfParticipants;
	}

	/**
	 * 查询参与者的某个指定类型的子参与者
	 */
	public List<WFParticipant> getChildParticipants(String typeCode, String participantID, String childCode) {

		LogUtil.logDebug("getChildParticipants(typeCode={1}, participantID={2},childCode={3})", null, new Object[] { typeCode, participantID, childCode });

		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();
		try {
			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_orgsuborg", parameterMap);

				} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_orgsubposi", parameterMap);

				} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_orgsubperson", parameterMap);

				} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_orgsubgroup", parameterMap);

				}
			} else if (CS_PARTICIPANT_TYPE_ROLE.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_allrolesub", parameterMap);
				}
			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_positionsubposi", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_positionsubperson", parameterMap);
				}
			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_groupsubposi", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_groupsubperson", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_groupsubgroup", parameterMap);
				}
			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_dutysubperson", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_duytsubduty", parameterMap);
				}

			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_busiorgsubperson", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(childCode)) {
					wfParticipants = queryNamedSql("query_busiorgsubbusiorg", parameterMap);
				}

			}
		} catch (Exception e) {
			LogUtil.logError("getChildParticipants(typeCode={1}, participantID={2},childCode={3})", e, new Object[] { typeCode, participantID, childCode });
		}
		return wfParticipants;

	}

	/**
	 * 查询某个参与者的指定类型的父参与者
	 */
	public List<WFParticipant> getParentParticipants(String typeCode, String participantID, String parentCode) {

		LogUtil.logDebug("getAllParentParticipants(typeCode={1}, participantID={2})", null, new Object[] { typeCode, participantID });

		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();

		try {
			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_orgparentsbizorg", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_orgparentsorg", parameterMap);
				}
			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_positionparentsorg", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_positionparentsgroup", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_positionparentsposi", parameterMap);
				}

			} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_personparentsorg", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_personparentsgroup", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_personparentsposi", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_ROLE.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_personparentsrole", parameterMap);
				}
			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id", participantID);
				if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_groupparentsorg", parameterMap);
				} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_groupparentsgroup", parameterMap);
				}

			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_alldutyparents", parameterMap);
				}

			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id", new Integer(participantID));
				if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(parentCode)) {
					wfParticipants = queryNamedSql("query_allbusiorgparents", parameterMap);
				}
			}
		} catch (Exception e) {
			LogUtil.logError("getAllParentParticipants(typeCode={1}, participantID={2})", e, new Object[] { typeCode, participantID });
		}
		return wfParticipants;

	}

	/**
	 * 交集类型
	 */
	public List<WFParticipant> getJointChildParticipant(String typeCode, List<String> jointType) {
		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();

		String id1 = jointType.get(0);
		String id2 = jointType.get(1);
		try {
			if (CS_PARTICIPANT_TYPE_ORGROLE.equalsIgnoreCase(typeCode)) {
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("id1", new String(id1));
				parameterMap.put("id2", new String(id2));
				wfParticipants = queryNamedSql("query_orgrole", parameterMap);

			} else if (CS_PARTICIPANT_TYPE_ORGDUTY.equalsIgnoreCase(typeCode)) {
				Map<String, Integer> parameterMap = new HashMap<String, Integer>();
				parameterMap.put("id1", new Integer(id1));
				parameterMap.put("id2", new Integer(id2));
				wfParticipants = queryNamedSql("query_orgduty", parameterMap);

			}
		} catch (Exception e) {
			LogUtil.logError("getJointChildParticipant(typeCode={1}, jointType:[id1={2},id2={3}])", e, new Object[] { typeCode, id1, id2 });
		}
		return wfParticipants;

	}

	/**
	 * 获取某个参与者的参与范围
	 * 
	 */
	public List<WFParticipant> getParticipantScope(String typeCode, String participantID) {

		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();

		try {
			if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(typeCode)) {
				WFParticipant wfParticipantSelf = findParticipantByID("person", participantID);
				if (wfParticipantSelf != null) {
					wfParticipants.add(wfParticipantSelf);// 首先加入自己
					Map<String, String> parameterMap = new HashMap<String, String>();
					parameterMap.put("id", participantID);
					// 依次加入人员所属机构、岗位、角色列表
					wfParticipants.addAll(queryNamedSql("query_personscopeorg", parameterMap));
					wfParticipants.addAll(queryNamedSql("query_personscopeposi", parameterMap));
					wfParticipants.addAll(queryNamedSql("query_personscoperole", parameterMap));

					getIterativeParent(wfParticipants, participantID, CS_PARTICIPANT_TYPE_PERSON, CS_PARTICIPANT_TYPE_ORGANIZATION);
					getIterativeParent(wfParticipants, participantID, CS_PARTICIPANT_TYPE_PERSON, CS_PARTICIPANT_TYPE_WORKGROUP);

				}
			}
		} catch (Exception e) {
			LogUtil.logError("getParticipantScope(typeCode={1}, participantID={2})", e, new Object[] { typeCode, participantID });
		}
		return wfParticipants;
	}

	/**
	 * 获取指定参与者类型
	 */
	public ParticipantType getParticipantType(String typeCode) {
		return participantTypes.get(typeCode);
	}

	/**
	 * 
	 * 获取所有的参与者类型，该方法通常在构造函数中调用，用以初始化参与者类型列表
	 * 
	 */
	public List<ParticipantType> getParticipantTypes() {
		// 把map转为list
		return new ArrayList<ParticipantType>(participantTypes.values());
	}

	/**
	 * 
	 * 获取某个类型的所有参与者
	 * 
	 * @param typeCode
	 * @param pagecond
	 * @return
	 * @throws Exception
	 */
	public List<WFParticipant> getParticipants(String typeCode, PageCond pagecond) {
		ParticipantType partType = participantTypes.get(typeCode);
		List<WFParticipant> wfParticipants = new ArrayList<WFParticipant>();

		try {
			if (partType == null) {
				return wfParticipants;
			}

			if (CS_PARTICIPANT_TYPE_ORGANIZATION.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_allorganizations", null);

			} else if (CS_PARTICIPANT_TYPE_ROLE.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_allroles", null);

			} else if (CS_PARTICIPANT_TYPE_POSITION.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_allpositions", null);

			} else if (CS_PARTICIPANT_TYPE_PERSON.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_allpersons", null);

			} else if (CS_PARTICIPANT_TYPE_WORKGROUP.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_allworkgroups", null);

			} else if (CS_PARTICIPANT_TYPE_DUTY.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_alldutys", null);

			} else if (CS_PARTICIPANT_TYPE_BUSIORG.equalsIgnoreCase(typeCode)) {
				wfParticipants = queryNamedSql("query_allbusiorgs", null);

			}
		} catch (Exception e) {
			LogUtil.logError("getAllParentParticipants(typeCode={1}})", e, new Object[] { typeCode });
		}
		return wfParticipants;
	}

	/**
	 * 递归查询父节点
	 * 
	 * @param wfParticipantParents
	 * @param orgID
	 * @return
	 */
	private void getIterativeParent(List<WFParticipant> wfParticipantParents, String participantID, String typeCode, String parentCode) {
		List<WFParticipant> wfParticipantParentTemps = null;

		wfParticipantParentTemps = getParentParticipants(typeCode, participantID, parentCode);

		int parentCount = wfParticipantParentTemps == null ? 0 : wfParticipantParentTemps.size();

		if (parentCount > 0) {
			for (int i = 0; i < parentCount; i++) {
				if (!wfParticipantParents.contains(wfParticipantParentTemps.get(i))) {
					wfParticipantParents.add(wfParticipantParentTemps.get(i));
				}

				getIterativeParent(wfParticipantParents, wfParticipantParentTemps.get(i).getId(), wfParticipantParentTemps.get(i).getTypeCode(), parentCode);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected List<WFParticipant> queryNamedSql(String namedSql, Object param) {

		return BPSOMDBUtil.queryNamedSql(namedsqlset + namedSql, param);
	}

	/**
	 * 执行命名sql
	 * @param namedSql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<String> queryStringByNamedSql(String namedSql, Object param) {

		return BPSOMDBUtil.queryNamedSql(namedsqlset + namedSql, param);
	}

	// 业务权限控制接口（IWFPermissionService）
	// 业务权限列表
	
	private static List<String> roleList = Arrays.asList(new String[] {	
			//IWFPermissionService.PERM_BPFCUSTOM,//TODO BPFCUSTOM 为BPS6.2.1之后支持
			IWFPermissionService.PERM_BPMMANAGER,
			IWFPermissionService.PERM_BPSADMIN,
			IWFPermissionService.PERM_BPSBIZCAT,
			IWFPermissionService.PERM_BPSCLIENT,
			IWFPermissionService.PERM_BPSCONFIG,
			IWFPermissionService.PERM_BPSCUSTOM
			});

	/**
	 * 获取用户的权限列表
	 */
	public List<String> getPermissionList(String userID) {
		List<String> roles = new ArrayList<String>();
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", userID);
		List<String> rets = queryStringByNamedSql("query_personbpsrole", parameterMap);
		for (String str : rets) {
			if (roleList.contains(str) && !roles.contains(str)) {
				roles.add(str);
			}
		}
		return roles;
	}

	/**
	 * EOS6.0 兼容方法 6.0中接口的方法名有拼写错误
	 */
	public boolean hasPersimmion(String userID, String permType) {
		return hasPermission(userID, permType);
	}

	/**
	 * 判断用户是否有权限
	 */
	public boolean hasPermission(String userID, String permType) {
		boolean ret = false;
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", userID);
		parameterMap.put("roleid", permType);
		List<String> roles = queryStringByNamedSql("query_personhasrole", parameterMap);
		if (roles != null & roles.size() > 0) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 验证用户的合法性，被bps的登录接口调用
	 *  workspace登录使用到
	 */
	public boolean validate(String userName, String password, Map attribues) {
		boolean ret = false;
		try {
			ILogicComponent comp = LogicComponentFactory.create("cn.bolz.frame.auth.LoginManager");
			Object[] params = new Object[2];
			params[0] = userName;
			params[1] = password;

			Object[] rets = comp.invoke("authentication", params);
			if (rets != null && rets.length > 0) {
				ret = "0".equals(String.valueOf(rets[0]));
			}
		} catch (Throwable e) {
			ret = false;
			LogUtil.logError("验证出错！userid={0},passwd={1}", e, new Object[] { userName, password });
		}
		return ret;
	}
}
