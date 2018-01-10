/**
 * 
 */
package com.bos.bizApply;

import java.util.HashMap;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-08-18 23:27:54
 *
 */
@Bizlet("")
public class SQ {
	public static TraceLogger logger = new TraceLogger(SQ.class);
	/**
	 * 流程授权时，判断该业务担保方式
	 * 返回值：1-信用，2-优质，3-普通，4-保证
	 * 判断方式，低往高开始
	 *  ：信用>普通担保>保证>优质担保
	 * 
	 * 优质担保品种：土地=030%   在建工程 =020%   房产=010%   国债-凭证式=090100   国债-记账式=090200  国债-储蓄(电子)式=090300   银行承兑汇票=140100  
	 * 本行本币存单=080100   本行外币存单=080200   我行发行的保本型理财产品=130100   我行发行的非保本型理财产品=130200  保证金 coll_type=03
	 */
	public String getGuarantyType(String applyId){
		//初始化授权参数
		HashMap map = new HashMap();
		map.put("applyId", applyId);
		logger.info("流程授权：------bizId="+applyId+"------->开始!");
		
		//判断担保方式是否含有信用（最高）
		Object[] objxys = DatabaseExt.queryByNamedSql("default",
				"com.bos.bizApply.sq.get1", map);
		String xy = (String) ((DataObject) objxys[0]).get("XY");
		if ("1".equals(xy)) {
			logger.info("流程授权：------bizId="+applyId+"------->担保方式含有信用，返回担保方式为信用!");
			return "1";
		}
		
		//判断是否一般担保    取出该业务的所有担保物，循环判断是否优质，有一笔不满足则直接返回普通担保
		String flag="0";//是否优质担保
		Object[] obdbs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get2", map);
		DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
		bizApply.set("applyId", applyId);
		DatabaseUtil.expandEntity("default", bizApply);
		//个人业务优质担保只包括自有的房产类
		if("04".equals(bizApply.get("bizType")) || "06".equals((bizApply.get("bizType")))){
			logger.info("流程授权：------bizId="+applyId+"------->个人业务，开始判断抵质押物信息" );
			//循环该业务下的所有押品
			for(int i=0;i<obdbs.length;i++){
				//判断该押品是否自有
				DataObject obdb = (DataObject) obdbs[i];
				//非特定品种，直接返回普通担保
				if( null !=obdb.getString("ST") &&("010101".equals(obdb.get("ST"))||"010102".equals(obdb.get("ST"))||"010200".equals(obdb.get("ST"))
						||"010300".equals(obdb.get("ST"))||"010400".equals(obdb.get("ST"))||"010500".equals(obdb.get("ST")) //010101：普通商品房		010102别墅 	010200办公用房	 010300商业用房	010400工业用房	 010500商住两用房
						)){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"属于约定种类,开始判断优质担保" );
				}else{
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"不属于约定种类,返回担保方式为普通担保" );
					return "3";
				}
				map.put("conPartyId", bizApply.get("partyId"));
				map.put("partyId",obdb.get("PI"));
				Object[] gxrdbs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get3", map);
				String zy = ((DataObject) gxrdbs[0]).getString("C");
				//自有
				if(!"0".equals(zy)){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"属于自有，开始判断抵质押率" );
					//普通商品房权利
					if("010101".equals(obdb.get("ST"))){
						//价值不得超过评估价值的70%
						if(0.7>=(obdb.getDouble("SJBL"))){
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"属于优质担保!" );
						}else{
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"自有普通商品房权利价值超过评估价值的70%，返回担保方式为普通担保." );
							return "3";
						}
					}
					//别墅、办公用房、商业用房、工业用房、商住两用房权利价值不得超过其评估价值的50%  且不超过年租金20倍
					if("010102".equals(obdb.get("ST"))||"010200".equals(obdb.get("ST"))||"010300".equals(obdb.get("ST"))||"010400".equals(obdb.get("ST"))||"010500".equals(obdb.get("ST"))){
						if( (0.5>=(obdb.getDouble("SJBL")))){
							map.put("partyId", obdb.get("SUI"));
							Object[] zjs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get5", map);
							String zj = ((DataObject) zjs[0]).getString("C");
							if("1".equals(zj)){ //1超过，0不超过
								logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"房租已出租，且权利价值超过租金的20倍，返回担保方式为普通担保");
								return "3";
							}else{
								logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"房租未出租，或权利价值未超过租金的20倍，属于优质担保");
							}
						}else{
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"自有别墅、办公用房、商业用房、工业用房、商住两用房权利价值超过评估价值的50%，返回担保方式为普通担保");
							return "3";
						}
					}
				}else{
					//非自有
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+(obdb.get("SN"))+"不属于自有，开始判断抵质押率" );
					//他人普通商品房权利价值不得超过评估价值的50%
					if("010101".equals(obdb.get("ST"))){
						if(0.5>=obdb.getDouble("SJBL")){
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"他人普通商品房权利价值未超过评估价值的50%，属于优质担保");
						}else{
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"他人普通商品房权利价值超过评估价值的50%，返回担保方式为普通担保");
							return "3";
						}
						
					}
					//他人别墅、办公用房、商业用房、工业用房、商住两用房权利价值不得超过其评估价值的35%。
					if(("010102".equals(obdb.get("ST"))||"010200".equals(obdb.get("ST"))||"010300".equals(obdb.get("ST"))||"010400".equals(obdb.get("ST"))||"010500".equals(obdb.get("ST")))){
						if( (0.35>= obdb.getDouble("SJBL")) ){
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"他人别墅、办公用房、商业用房、工业用房、商住两用房权利价值未超过评估价值的35%，属于优质担保" );
						}else{
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"他人别墅、办公用房、商业用房、工业用房、商住两用房权利价值超过评估价值的35%，返回担保方式为普通担保" );
							return "3";
						}
					}
				}
				//优质担保
			}
		}else{
			//对公业务
			for(int i=0;i<obdbs.length;i++){
				logger.info("流程授权：------bizId="+applyId+"------->对公业务，开始判断抵质押物信息" );
				DataObject obdb = (DataObject) obdbs[i];
				//非特定品种，直接返回普通担保
				if( null !=obdb.getString("ST") &&( obdb.getString("ST").startsWith("020") || "010101".equals(obdb.get("ST")) //020：在建工程 ||010101：普通商品房
						//010102别墅 	010200办公用房	 010300商业用房	010400工业用房	 010500商住两用房
						||"010102".equals(obdb.get("ST"))||"010200".equals(obdb.get("ST"))||"010300".equals(obdb.get("ST"))||"010400".equals(obdb.get("ST"))||"010500".equals(obdb.get("ST"))  
						//100股权类	090100国债-凭证式	090200国债-记账式	090300国债-储蓄(电子)式
						||obdb.getString("ST").startsWith("100") ||"090100".equals(obdb.getString("ST")) ||"090200".equals(obdb.getString("ST"))  ||"090300".equals(obdb.getString("ST")) 
						//140100银行承兑汇票		080100本行本币存单	080200本行外币存单
						||"140100".equals(obdb.getString("ST"))  ||"080100".equals(obdb.getString("ST")) ||"080200".equals(obdb.getString("ST")) 
						//130100我行发行的保本型理财产品		130200我行发行的非保本型理财产品	000000//保证金
						|| "130100".equals(obdb.getString("ST")) ||"130200".equals(obdb.getString("ST")) ||"000000".equals(obdb.getString("ST")))){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"属于约定种类,开始判断优质担保" );
				}else{
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"不属于约定种类,返回担保方式为普通担保" );
					return "3";
				}
				//在建工程 在建工程权利价值不得超过其工程预算造价的50%(省会分行在建工程权利价值不得超过其成本价值的70%)  //省会分行：58 广州78 南昌61 合肥
				if(null !=obdb.getString("ST") && obdb.getString("ST").startsWith("020")){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：在建工程" );
					map.put("suretyId", obdb.get("SUI"));
					Object[] zjgcdbs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get6", map);
					((DataObject) zjgcdbs[0]).getDouble("YS");
					if(0.5<((DataObject) zjgcdbs[0]).getDouble("YS")){
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"在建工程权利价值超过其工程预算造价的50%,返回担保方式为普通担保" );
						return "3";
					}else{
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"在建工程权利价值未超过其工程预算造价的50%,属于优质担保" );
						//省会分行在建工程权利价值不得超过其成本价值的70%  省会分行：58 广州78 南昌61 合肥
						/*if(bizApply.getString("orgNum").startsWith("58") || bizApply.getString("orgNum").startsWith("78") ||bizApply.getString("orgNum").startsWith("61")){
						}*/
					}
				}
				//普通商品房
				if("010101".equals(obdb.get("ST"))){
					//价值不得超过评估价值的70%
					if(0.7>=  (obdb.getDouble("SJBL"))){
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"属于优质担保!" );
					}else{
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"自有普通商品房权利价值超过评估价值的70%，返回担保方式为普通担保." );
						return "3";
					}
				}
				//别墅、办公用房、商业用房、工业用房、商住两用房权利价值不得超过其评估价值的50%  且不超过年租金20倍
				if("010102".equals(obdb.get("ST"))||"010200".equals(obdb.get("ST"))||"010300".equals(obdb.get("ST"))||"010400".equals(obdb.get("ST"))||"010500".equals(obdb.get("ST"))){
					if( (0.5>= (obdb.getDouble("SJBL")))){
						map.put("suretyId", obdb.get("SUI"));
						Object[] zjs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get5", map);
						String zj = ((DataObject) zjs[0]).getString("C");
						if("0".equals(zj)){
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"房租未出租，或权利价值未超过租金的20倍，属于优质担保");
						}else{
							logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"房租已出租，且权利价值超过租金的20倍，返回担保方式为普通担保");
							return "3";
						}
					}else{
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+obdb.get("SN")+"自有别墅、办公用房、商业用房、工业用房、商住两用房权利价值超过评估价值的50%，返回担保方式为普通担保");
						return "3";
					}
				}
				//股权类
				if(null !=obdb.getString("ST") && obdb.getString("ST").startsWith("100")){
					return "3";
//					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：股权类" );
//					map.put("suretyId", obdb.get("SUI"));
//					Object[] gqdbs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get7", map);
//					if("1".equals(((DataObject) gqdbs[0]).getString("C"))){
//						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"上市公司股权,属于优质担保" );
//					}else{
//						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"非上市公司股权,返回担保方式为普通担保" );
//						return "3";
//					}
				}
				//国债	“抵质押物类型”为“国债-凭证式”、“国债-记账式”、“国债-储蓄(电子)式”
				if(null !=obdb.getString("ST") &&( "090100".equals(obdb.getString("ST")) ||"090200".equals(obdb.getString("ST")) ||"090300".equals(obdb.getString("ST")) )){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：国债" );
				}
				//银行承兑汇票
				if(null !=obdb.getString("ST") && "140100".equals(obdb.getString("ST"))){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：银行承兑汇票" );
				}
				//我行定期存单：“抵质押物类型”为“本行本币存单”、“本行外币存单”，“存单类型”为“定期”
				if(null !=obdb.getString("ST") &&( "080100".equals(obdb.getString("ST")) ||"080200".equals(obdb.getString("ST")) )){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：我行存单" );
					map.put("suretyId", obdb.get("SUI"));
					Object[] cddbs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.sq.get8", map);
					if("1".equals(((DataObject) cddbs[0]).getString("C"))){
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"定期存单,属于优质担保" );
					}else{
						logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"非定期存单,返回担保方式为普通担保" );
						return "3";
					}
					
				}
				//理财产品：“抵质押物类型”为“我行发行的保本型理财产品”、“我行发行的非保本型理财产品”
				if(null !=obdb.getString("ST") &&( "130100".equals(obdb.getString("ST")) ||"130200".equals(obdb.getString("ST")) )){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：理财产品,属于优质担保" );
				}
				//保证金
				if(null !=obdb.getString("ST") && "000000".equals(obdb.getString("ST"))){
					logger.info("流程授权：------bizId="+applyId+"------->抵质押物="+((DataObject) obdbs[i]).get("SN")+"类型：保证金,属于优质担保" );
				}
				
			}
			//优质担保
		}
		
		//判断担保方式是否含有保证（第三）
		Object[] objbzs = DatabaseExt.queryByNamedSql("default",
				"com.bos.bizApply.sq.get4", map);
		String bz = (String) ((DataObject) objbzs[0]).get("BZ");
		if ("1".equals(bz)) {
			logger.info("流程授权：------bizId="+applyId+"------->担保方式含有保证，返回担保方式为保证!");
			return "4";
		}
		
		// 无信用，无普通担保，无保证  则返回优质担保
		return "2";
	}

	/**
	 * 流程授权时，判断该业务担保方式
	 * 按照从大到小多次循环集团成员， 
	 * 返回值：1-信用，2-优质，3-普通，4-保证
	 * 判断方式，低往高开始
	 *  ：信用>普通担保>保证>优质担保
	 */
	@Bizlet("")
	public String getGuarantyTypeJt(String applyId) {
		
		//按照从大到小多次循环集团成员， 
		DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
		bizApply.set("applyId", applyId);
		DatabaseUtil.expandEntity("default", bizApply);
		//获取所有成员
		HashMap map = new HashMap();
		map.put("partyId", bizApply.get("partyId"));
		Object[] members = DatabaseExt.queryByNamedSql("default","com.bos.bizInfo.groupBiz.getMember", map);
		//查询所有集团成员  看是否含有信用	1-信用
		for(int i=0;i<members.length;i++){
			DataObject member = (DataObject) members[i];//成员
			GroupInfo gi = new GroupInfo();
			Object[] bizs = gi.getMemberBiz(((DataObject) members[i]).getString("PARTY_ID"));
			for(int j=0;j<bizs.length;j++){
				DataObject biz = (DataObject) bizs[j];//成员
				SQ sq = new SQ();
				if("1".equals(sq.getGuarantyType(biz.getString("APPLY_ID")))){
					logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->担保方式含有信用，返回担保方式为信用!");
					logger.info("集团流程授权：------>集团授权结果------partyId="+bizApply.get("partyId")+"------->返回担保方式为普通信用!");
					return "1";
				}
			}
		}
		//查询所有集团成员  看是否含有普通担保	3-普通
		for(int i=0;i<members.length;i++){
			DataObject member = (DataObject) members[i];//成员
			GroupInfo gi = new GroupInfo();
			Object[] bizs = gi.getMemberBiz(((DataObject) members[i]).getString("PARTY_ID"));
			for(int j=0;j<bizs.length;j++){
				DataObject biz = (DataObject) bizs[j];//成员
				SQ sq = new SQ();
				if("3".equals(sq.getGuarantyType(biz.getString("APPLY_ID")))){
					logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->担保方式含有普通担保，返回担保方式为普通担保!");
					logger.info("集团流程授权：------>集团授权结果------partyId="+bizApply.get("partyId")+"------->返回担保方式为普通担保!");
					return "3";
				}
			}
		}
		//查询所有集团成员  看是否含有普通担保	4-保证
		for(int i=0;i<members.length;i++){
			DataObject member = (DataObject) members[i];//成员
			GroupInfo gi = new GroupInfo();
			Object[] bizs = gi.getMemberBiz(((DataObject) members[i]).getString("PARTY_ID"));
			for(int j=0;j<bizs.length;j++){
				DataObject biz = (DataObject) bizs[j];//成员
				SQ sq = new SQ();
				if("4".equals(sq.getGuarantyType(biz.getString("APPLY_ID")))){
					logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->担保方式含有保证，返回担保方式为保证!");
					logger.info("集团流程授权：------>集团授权结果------partyId="+bizApply.get("partyId")+"------->返回担保方式为保证!");
					return "4";
				}
			}
		}
		// 无信用，无普通担保，无保证  则返回优质担保
		logger.info("集团流程授权：------>集团授权结果------partyId="+bizApply.get("partyId")+"------->返回担保方式为优质担保!");
		return "2";
	}

	@Bizlet("")
	public String getProductCd(String applyId) {

		HashMap map = new HashMap();
		map.put("applyId", applyId);
		Object[] objxys = DatabaseExt.queryByNamedSql("default",
				"com.bos.bizApply.sq.getProductType", map);
		String productCd = (String) ((DataObject) objxys[0])
				.get("PRODUCT_TYPE");
		return productCd;
	}

	//是否监管
	@Bizlet("")
	public String getJgFlag(String applyId) {

		HashMap map = new HashMap();
		map.put("applyId", applyId);
		Object[] jgFlags = DatabaseExt.queryByNamedSql("default",
				"com.bos.bizApply.sq.getJgFlag", map);
		String jgFlag = (String) ((DataObject) jgFlags[0]).get("JGFLAG");
		return jgFlag;
	}
	
	//获取集团监管数据，循环成员的业务，只要有一个为高则返回高，成员自然人直接返回非高
	@Bizlet("")
	public String getJgFlagJt(String partyId) {
		//获取所有成员
		HashMap map = new HashMap();
		map.put("partyId", partyId);
		Object[] members = DatabaseExt.queryByNamedSql("default","com.bos.bizInfo.groupBiz.getMember", map);
		//判断成员的每笔业务的监管报送  
		for(int i=0;i<members.length;i++){
			DataObject member = (DataObject) members[i];//成员
			if("02".equals(member.get("PARTY_TYPE_CD"))){//自然人客户直接返回非高
				logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->为自然人客户，监管报送为非高!");
			}else{
				GroupInfo gi = new GroupInfo();
				Object[] bizs = gi.getMemberBiz(((DataObject) members[i]).getString("PARTY_ID"));
				for(int j=0;j<bizs.length;j++){
					DataObject biz = (DataObject) bizs[j];//成员
					SQ sq = new SQ();
					if("1".equals(sq.getJgFlag(biz.getString("APPLY_ID")))){
						logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->为高，返回监管报送为高!");
						return "1";
					}
					logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->为对公客户，监管报送为非高!");
				}
			}
		}
		//返回非高
		logger.info("集团流程授权：------>集团授权结果------partyId="+partyId+"------->返回监管报送为非高!");
		return "0";
	}

	@Bizlet("")
	public String getIsInternational(String applyId) {
		HashMap map = new HashMap();
		map.put("applyId", applyId);
		Object[] isInternationals = DatabaseExt.queryByNamedSql("default",
				"com.bos.bizApply.sq.getIsInternational", map);
		String isInternational = (String) ((DataObject) isInternationals[0])
				.get("ISINTERNATIONAL");
		return isInternational;
	}
	
	//获取集团是否国结业务，循环成员的业务，只要有一个国结业务则返回国结业务，成员自然人直接返回非国结业务
	@Bizlet("")
	public String getIsInternationalJt(String partyId) {
		//获取所有成员
		HashMap map = new HashMap();
		map.put("partyId", partyId);
		Object[] members = DatabaseExt.queryByNamedSql("default","com.bos.bizInfo.groupBiz.getMember", map);
		//判断成员的每笔业务是否国结业务
		for(int i=0;i<members.length;i++){
			DataObject member = (DataObject) members[i];//成员
			if("02".equals(member.get("PARTY_TYPE_CD"))){//自然人客户直接返回非国结业务
				logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->为自然人客户，为非国结业务!");
			}else{
				GroupInfo gi = new GroupInfo();
				Object[] bizs = gi.getMemberBiz(((DataObject) members[i]).getString("PARTY_ID"));
				for(int j=0;j<bizs.length;j++){
					DataObject biz = (DataObject) bizs[j];//成员
					SQ sq = new SQ();
					if("1".equals(sq.getIsInternational(biz.getString("APPLY_ID")))){
						logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->含国结业务，返回监管报送有国结业务!");
						return "1";
					}
					logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->为对公客户，监管报送为非国结业务!");
				}
			}
		}
		//返回非高
		logger.info("集团流程授权：------>集团授权结果------partyId="+partyId+"------->返回监管报送为非国结业务!");
		return "0";
	}

	//是否异地
	@Bizlet("")
	public String getIsYd(String applyId) {
		HashMap map = new HashMap();
		map.put("applyId", applyId);
		Object[] isYds = DatabaseExt.queryByNamedSql("default",
				"com.bos.bizApply.sq.getIsYd", map);
		String isYd = (String) ((DataObject) isYds[0])
				.get("ISYD");
		return isYd;
	}

	//是否异地集团
	@Bizlet("")
	public String getIsYdJt(String partyId) {
		//获取所有成员
		HashMap map = new HashMap();
		map.put("partyId", partyId);
		Object[] members = DatabaseExt.queryByNamedSql("default","com.bos.bizInfo.groupBiz.getMember", map);
		//判断成员的每笔业务是否异地
		for(int i=0;i<members.length;i++){
			DataObject member = (DataObject) members[i];//成员
			if("02".equals(member.get("PARTY_TYPE_CD"))){//自然人客户直接返回非异地
				logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->为自然人客户，为非异地!");
			}else{
				GroupInfo gi = new GroupInfo();
				Object[] bizs = gi.getMemberBiz(((DataObject) members[i]).getString("PARTY_ID"));
				for(int j=0;j<bizs.length;j++){
					DataObject biz = (DataObject) bizs[j];//成员
					SQ sq = new SQ();
					if("1".equals(sq.getIsYd(biz.getString("APPLY_ID")))){
						logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->为异地业务，返回监管报送异地业务!");
						return "1";
					}
					logger.info("集团流程授权：------partyId="+member.get("PARTY_ID")+"------->bizId="+biz.getString("APPLY_ID")+"------->为对公客户，返回监管报送非异地业务!");
				}
			}
		}
		//返回非高
		logger.info("集团流程授权：------>集团授权结果------partyId="+partyId+"------->返回监管报送非异地业务!");
		return "0";
	
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
	}

}
