package com.bos.grt.collateralparameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.GitUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.data.DataContextUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/***
 * 胡海军
 * @author Administrator
 *
 */
@Bizlet("押品分类参数方法")
public class CollateralUtil {
	
	public static final String DEFAULT_DS_NAME = "default"; // 默认数据源
	
		
	/**dataObjects:[{contractId:'',subcontractId:'',suretyId:'',sortType:'',subcontractTypeCd:''--担保合同,isNeedInsure:'',ifOtherCommon:''}]
	 * @param dataObjects
	 * @return
	 */
	@Bizlet("验证押品在01-申请阶段 02-贷款合同阶段 录入押品信息的完整性;返回0000表示不需要验证完整性或者已经具备完整性,9999表示需要验证完整性且需要补充数据")	
	public static Map getValidIntegrity(DataObject[] dataObjects,String statgeStatus){
		StringBuffer sb = new StringBuffer();//装载验证描述
		Map<String,String> map = new HashMap<String,String>();
		Connection conn = DBUtil.getConnection();
		//根据押品分类sortType和阶段状态查询出验证SQL
		String collQueryValidSql = "select t.SORT_TYPE,t.VALID_SQL,t.VALID_DESC from TB_GRT_VALID_INTEGRITY t where (t.SORT_TYPE like 'ALL%' or t.SORT_TYPE=?) and t.STAGE_STATUS=? order by t.SORT_TYPE";
		String guaranQueryValidSql = "select t.SORT_TYPE,t.VALID_SQL,t.VALID_DESC from TB_GRT_VALID_INTEGRITY t where t.SORT_TYPE=? and t.STAGE_STATUS=? order by t.SORT_TYPE";
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		if(dataObjects!=null){
			int objectsLenth = dataObjects.length;
			try{
				for(int i=0;i<objectsLenth;i++){
					if(map.get("validResult")!=null&&map.get("validResult").equals("9999")){
						break;
					}
					if(dataObjects[i].getString("subcontractTypeCd").equals("03")){
						ps = null;
						ps = conn.prepareStatement(guaranQueryValidSql);
						ps.setString(1, dataObjects[i].getString("sortType"));
						ps.setString(2, statgeStatus);
						rs = null;
						rs = ps.executeQuery();
						while(rs.next()){
							String validSql = rs.getString(2);
							String validDesc = rs.getString(3);
							ps1 = null;
							ps1 = conn.prepareStatement(validSql);
							ps1.setString(1, dataObjects[i].getString("suretyId"));
							rs1 = null;
							rs1 = ps1.executeQuery();
							if(rs1.next()){//查询出满足SQL条件的数量
								int count = rs1.getInt(1);
								if(count==0){
									map.put("validResult", "9999");
									sb.append(validDesc+"\n");
									break;
								}
							}
						}
					}else{
						ps = null;
						ps = conn.prepareStatement(collQueryValidSql);
						ps.setString(1, dataObjects[i].getString("sortType"));
						ps.setString(2, statgeStatus);
						rs = null;
						rs = ps.executeQuery();
						while(rs.next()){
							String sortType = rs.getString(1);
							String validSql = rs.getString(2);
							String validDesc = rs.getString(3);
							if(sortType.equals("ALL_INSURE")){
								if(dataObjects[i].getString("isNeedInsure").equals("1")){
									ps1 = null;
									ps1 = conn.prepareStatement(validSql);
									ps1.setString(1, dataObjects[i].getString("suretyId"));
									rs1 = null;
									rs1 = ps1.executeQuery();
									if(rs1.next()){//查询出满足SQL条件的数量
										int count = rs1.getInt(1);
										if(count==0){
											map.put("validResult", "9999");
											sb.append(validDesc+"\n");
											break;
										}
									}
								}
							}else if(sortType.equals("ALL_OTHERCOMMON")){
								if(dataObjects[i].getString("ifOtherCommon").equals("1")){
									ps1 = null;
									ps1 = conn.prepareStatement(validSql);
									ps1.setString(1, dataObjects[i].getString("suretyId"));
									rs1 = null;
									rs1 = ps1.executeQuery();
									if(rs1.next()){//查询出满足SQL条件的数量
										int count = rs1.getInt(1);
										if(count==0){
											map.put("validResult", "9999");
											sb.append(validDesc+"\n");
											break;
										}
									}
								}
							}else if(sortType.equals("ALL_BASICOTHER")){
								ps1 = null;
								ps1 = conn.prepareStatement(validSql);
								ps1.setString(1, dataObjects[i].getString("suretyId"));
								ps1.setString(2, dataObjects[i].getString("suretyId"));
								rs1 = null;
								rs1 = ps1.executeQuery();
								if(rs1.next()){//查询出满足SQL条件的数量
									int count = rs1.getInt(1);
									if(count==0){
										map.put("validResult", "9999");
										sb.append(validDesc+"\n");
										break;
									}
								}
							}else{
								ps1 = null;
								ps1 = conn.prepareStatement(validSql);
								ps1.setString(1, dataObjects[i].getString("suretyId"));
								rs1 = null;
								rs1 = ps1.executeQuery();
								if(rs1.next()){//查询出满足SQL条件的数量
									int count = rs1.getInt(1);
									if(count==0){
										map.put("validResult", "9999");
										sb.append(validDesc+"\n");
										break;
									}
								}
							}
					     }
					}
				}	
			}catch (Exception e) {
				LogUtil.logError("查询验证SQL出错！", e, (Object) null);
			} finally {
				DBUtil.closeAll(conn, new Statement[] { ps,ps1 },new ResultSet[] { rs,rs1});
			}
		}
		
		if(map.get("validResult")==null){
			map.put("validResult", "0000");
		}else{
			map.put("validResult", sb.toString());
		}
		return map;
	}
	
	@Bizlet("删除押品时,需要将关联该押品的所有表的信息全部删除")
	public static String deleteGrtIntegrity(String surtyId,String sortType){
		String msgStr = "删除成功";
		Connection conn = DBUtil.getConnection();
		String deleteIntegritySql = "select t.DEL_TABLE_NAME from TB_GRT_DELETE_INTEGRITY t where t.ABLE_STATUS='1' and (t.SORT_TYPE='ALL' OR t.SORT_TYPE=?)";
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(deleteIntegritySql);
			ps.setString(1, sortType);
			rs = ps.executeQuery();
			if(rs!=null){
				while(rs.next()){
					String tableName = rs.getString(1);
					String deleteSQL = "delete from "+tableName+" where SURETY_ID=?";
					ps1 = null;
					ps1 = conn.prepareStatement(deleteSQL);
					ps1.setString(1, surtyId);
					ps1.execute();
					
				}
			}
		} catch (SQLException e) {
			msgStr = "删除出错";
			LogUtil.logError("删除出错！", e, (Object) null);
		}finally {
			DBUtil.closeAll(conn, new Statement[] { ps,ps1},new ResultSet[] { rs});
		}
		
		return msgStr;
	}
	
	@SuppressWarnings("unchecked")
	@Bizlet("获得抵质押品编号")
	public Map getSuretyNum(Map map){
		Map suretyNumMap = new HashMap<String, String>();
		//String sortType = map.get("sortType").toString();
		String userNum = map.get("userNum").toString();
		String suretyNum = map.get("suretyNum").toString();
		suretyNumMap.put("suretyNum",userNum+suretyNum);
		return suretyNumMap;
	}
	
	/**
	 * @return
	 * @author 王世春
	 */
	@Bizlet("生成业务编号")
	public static String genBizNum() {
		String date = new SimpleDateFormat("yyyyMMdd").format(GitUtil
				.getBusiTimestamp());
		return date + getNextSequence("SEQ_CRT_YW");
	}
	
	private static String getNextSequence(String seqName){
		Map seqMap=new HashMap();
		seqMap.put("sequenceName", seqName);
		String num = null;
		try {
			Object[] sdos = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.sequences.querySequence", seqMap);
			if(sdos.length > 0){
				num = String.valueOf(DatabaseExt.getNextSequence(seqName));
				if(seqName.equals("SEQ_CRT_KH")){
					return StringUtils.leftPad(num, 6, '0');
				}else if("SEQ_LCS_STAN".equals(seqName)){
					return StringUtils.leftPad(num, 8,'0');
				}else{
					return StringUtils.leftPad(num, 4, '0');
				}
				
			}else{
				DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.sequences.createSequence", seqMap);
				return getNextSequence(seqName);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			LogUtil.logError("查询sequence时出错："+seqName, e, new Object[0]);
			return null;
		}
	}
	
	@Bizlet("判断是否是供应链业务")
	public int getScfBusinessByBusiness(String applyId){
		int count = 0;
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String countSql = "select count(*) COUNT from TB_BIZ_SCF_BUSINESS where APPLY_ID='"+applyId+"'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(countSql);
			while(rs.next()){
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			LogUtil.logError("数据库查询押品是否重复出错！", e, (Object) null);
		}finally{
			DBUtil.closeAll(conn, new Statement[] { st},new ResultSet[] { rs});
		}
		return count;
	}
	
	/**获得押品合格性默认的字段
	 * @param sortType
	 * @return
	 */
	@Bizlet("获得押品合格性默认的字段")
	public Map getGrtQualifiedObject(String sortType){
		Map<String,String> qualifiedMap = new HashMap<String,String>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String qualifiedSql = "select g.SORT_TYPE,g.GUARANTY_IF_FLAW,g.IS_REGISTER,g.IF_ENOUGH_INSURE,g.GUARANTY_IF_MY_ONE,g.GUARANTY_MOBILITY,g.IS_RELATED,g.QUALIFIED_RESULT" +
				" from TB_GRT_QUALIFIEDPARAMS_CONFIG g where g.SORT_TYPE='"+sortType+"'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(qualifiedSql);
			if(rs.next()){
				//押品权属是否清晰
				String  GUARANTY_IF_FLAW = rs.getString("GUARANTY_IF_FLAW");	
				if(GUARANTY_IF_FLAW!=null && (GUARANTY_IF_FLAW.equals("0")||GUARANTY_IF_FLAW.equals("1"))){
					qualifiedMap.put("GUARANTY_IF_FLAW", GUARANTY_IF_FLAW);
				}
				//押品登记情况
				String  IS_REGISTER = rs.getString("IS_REGISTER");
				if(IS_REGISTER!=null &&(IS_REGISTER.equals("01") || IS_REGISTER.equals("02") || IS_REGISTER.equals("03"))){
					qualifiedMap.put("IS_REGISTER", IS_REGISTER);
				}
				//押品足额保险
				String  IF_ENOUGH_INSURE = rs.getString("IF_ENOUGH_INSURE");
				if(IF_ENOUGH_INSURE!=null&&(IF_ENOUGH_INSURE.equals("0")||IF_ENOUGH_INSURE.equals("1"))){
					qualifiedMap.put("IF_ENOUGH_INSURE", IF_ENOUGH_INSURE);
				}
				//押品是否有优先权
				String GUARANTY_IF_MY_ONE = rs.getString("GUARANTY_IF_MY_ONE");
				if(GUARANTY_IF_MY_ONE!=null &&(GUARANTY_IF_MY_ONE.equals("0")||GUARANTY_IF_MY_ONE.equals("1"))){
					qualifiedMap.put("GUARANTY_IF_MY_ONE", GUARANTY_IF_MY_ONE);
				}
				//押品流动性
				String GUARANTY_MOBILITY = rs.getString("GUARANTY_MOBILITY");
				if(GUARANTY_MOBILITY!=null && (GUARANTY_MOBILITY.equals("1")||GUARANTY_MOBILITY.equals("2")||GUARANTY_MOBILITY.equals("3")
						||GUARANTY_MOBILITY.equals("4")||GUARANTY_MOBILITY.equals("5"))){
					qualifiedMap.put("GUARANTY_MOBILITY", GUARANTY_MOBILITY);
				}
				//押品与客户的相关性
				String IS_RELATED = rs.getString("IS_RELATED");
				if(IS_RELATED!=null &&(IS_RELATED.equals("1")||IS_RELATED.equals("0"))){
					qualifiedMap.put("IS_RELATED", IS_RELATED);
				}
				//押品合格性
				String QUALIFIED_RESULT = rs.getString("QUALIFIED_RESULT");
				if(QUALIFIED_RESULT!=null && (QUALIFIED_RESULT.equals("0")||QUALIFIED_RESULT.equals("1"))){
					qualifiedMap.put("QUALIFIED_RESULT", QUALIFIED_RESULT);
				}
			}
		} catch (SQLException e) {
			LogUtil.logError("数据库查询出错！", e, (Object) null);
		}finally{
			DBUtil.closeAll(conn, new Statement[] { st},new ResultSet[] { rs});
		}
		return qualifiedMap;
	}
	
	/**获得保证人合格性默认的字段
	 * @param sortType
	 * @return
	 */
	@Bizlet("获得保证人合格性默认的字段")
	public Map getGuaranteeQualifiedObject(String guaranteeType,String guaranteerType){
		Map<String,String> qualifiedMap = new HashMap<String,String>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		if(guaranteerType!=null &&(guaranteerType.equals("21101")||guaranteerType.equals("21102")||guaranteerType.equals("21103")
		  ||guaranteerType.equals("21104")||guaranteerType.equals("21199"))){//企业包括以上列举的类型
			guaranteerType = "211";
		}
		String qualifiedSql ="select g.GUARANTEER_DEPENDENCE,g.GUARANTEER_SUBJECT_QUALIF,g.GUARANTEER_FRECURR_CONTROL,g.GUARANTEE_IS_LIMIT," +
				"g.QUALIFIED_RESULT from TB_GRT_GUARANTEEPARAM_CONFIG g where g.GUARANTEE_TYPE='"+guaranteeType+"' and g.GUARANTEERTYPE='"+guaranteerType+"'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(qualifiedSql);
			if(rs.next()){
				//保证:实质正相关性
				String GUARANTEER_DEPENDENCE = rs.getString("GUARANTEER_DEPENDENCE");
				if(GUARANTEER_DEPENDENCE!=null &&(GUARANTEER_DEPENDENCE.equals("1")||GUARANTEER_DEPENDENCE.equals("0"))){
					qualifiedMap.put("GUARANTEER_DEPENDENCE",GUARANTEER_DEPENDENCE);
				}
			    //保证:主体要求
				String GUARANTEER_SUBJECT_QUALIF = rs.getString("GUARANTEER_SUBJECT_QUALIF");
				if(GUARANTEER_SUBJECT_QUALIF!=null &&(GUARANTEER_SUBJECT_QUALIF.equals("0")||GUARANTEER_SUBJECT_QUALIF.equals("1"))){
					qualifiedMap.put("GUARANTEER_SUBJECT_QUALIF",GUARANTEER_SUBJECT_QUALIF);
				}
				//外汇管制
				String GUARANTEER_FRECURR_CONTROL = rs.getString("GUARANTEER_FRECURR_CONTROL");
				if(GUARANTEER_FRECURR_CONTROL!=null &&(GUARANTEER_FRECURR_CONTROL.equals("0")||GUARANTEER_FRECURR_CONTROL.equals("1"))){
					qualifiedMap.put("GUARANTEER_FRECURR_CONTROL",GUARANTEER_FRECURR_CONTROL);
				}
				//无条件不可撤销
				String GUARANTEE_IS_LIMIT = rs.getString("GUARANTEE_IS_LIMIT");
				if(GUARANTEE_IS_LIMIT!=null &&(GUARANTEE_IS_LIMIT.equals("0")||GUARANTEE_IS_LIMIT.equals("1"))){
					qualifiedMap.put("GUARANTEE_IS_LIMIT",GUARANTEE_IS_LIMIT);
				}
				
				//保证的合格性
				String QUALIFIED_RESULT = rs.getString("QUALIFIED_RESULT");
				if(QUALIFIED_RESULT!=null &&(QUALIFIED_RESULT.equals("0")||QUALIFIED_RESULT.equals("1"))){
					qualifiedMap.put("QUALIFIED_RESULT",QUALIFIED_RESULT);
				}
			}
		}catch(SQLException e){
			LogUtil.logError("数据库查询出错！", e, (Object) null);
		}finally{
			DBUtil.closeAll(conn, new Statement[] { st},new ResultSet[] { rs});
		}
		return qualifiedMap;
	}
	
	//根据担保客户的模式主键查询出模式名称
	@Bizlet("根据担保客户的模式主键查询出模式名称")
	public String getModelNamesByModelId(String modelIds){
		String modelNames = "";
		if(modelIds!=null&&!modelIds.equals("")){
			StringBuffer sb = new StringBuffer();
			String[] modelIdsStr = modelIds.split(",");
			for(int i=0;i<modelIdsStr.length;i++){
				sb.append("'").append(modelIdsStr[i]).append("'").append(",");
			}
			sb.deleteCharAt(sb.toString().lastIndexOf(","));
			String sql ="select listagg(e.MODEL_NAME,',') MODEL_NAME from tb_crd_guatee_limit_mode e where e.LIMIT_DETAIL_ID in("+sb.toString()+")";
			Connection conn = DBUtil.getConnection();
			Statement st = null;
			ResultSet rs = null;
			try{
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				if(rs.next()){
					modelNames = rs.getString("MODEL_NAME");
				}
			}catch(SQLException e){
				LogUtil.logError("数据库查询出错！", e, (Object) null);
			}finally{
				DBUtil.closeAll(conn, new Statement[] { st},new ResultSet[] { rs});
			}
		}
		return modelNames;
	}
}
