package com.bos.grt.importExcelDict;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("导入EXCEL数据时查询数据对应的业务字典")
public class ImportExcelDict {
	@Bizlet("票据导入EXCEL数据时查询数据对应的业务字典")
	public DataObject getDataObjectDicd(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("currencyCd")+"斤斤计较");
		if(dataObject.getString("currencyCd")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "CD000001");
			map.put("dictname", dataObject.getString("currencyCd").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null || res1.length==0){
		    	throw new Exception(dataObject.getString("currencyCd").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("currencyCd",res1[0].toString());
		    }

		}
		return dataObject;
	}
	
	@Bizlet("银行承兑汇票导入EXCEL数据时查询汇票形式数据对应的业务字典")
	public DataObject getDataObjectDicId(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("currencyCd")+"斤斤计较");
		if(dataObject.getString("hpxs")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "XD_SXCD1123");
			map.put("dictname", dataObject.getString("hpxs").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null || res1.length==0){
		    	throw new Exception(dataObject.getString("hpxs").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("hpxs",res1[0].toString());
		    }

		}
		return dataObject;
	}
	
	
	@Bizlet("银行承兑汇票导入EXCEL数据时查询汇票形式数据对应的业务字典")
	public DataObject getBilltype(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("currencyCd")+"斤斤计较");
		if(dataObject.getString("billtype")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "XD_SXYW0203");
			map.put("dictname", dataObject.getString("billtype").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null || res1.length==0){
		    	throw new Exception(dataObject.getString("billtype").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("billtype",res1[0].toString());
		    }

		}
		return dataObject;
	}
	
	@Bizlet("银行承兑汇票导入EXCEL数据时查询汇票模式数据对应的业务字典")
	public DataObject transXD_SXYW0203(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("currencyCd")+"斤斤计较");
		if(dataObject.getString("billmodel")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "XD_SXCD1123");
			map.put("dictname", dataObject.getString("billmodel").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null || res1.length==0){
		    	throw new Exception(dataObject.getString("billmodel").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("billmodel",res1[0].toString());
		    }

		}
		return dataObject;
	}
	
	@Bizlet("银行承兑汇票导入EXCEL数据时查询币种数据对应的业务字典")
	public DataObject transCD000001(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("currencyCd")+"斤斤计较");
		if(dataObject.getString("currsign")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "CD000001");
			map.put("dictname", dataObject.getString("currsign").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null || res1.length==0){
		    	throw new Exception(dataObject.getString("currsign").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("currsign",res1[0].toString());
		    }

		}
		return dataObject;
	}
	
	@Bizlet("银行承兑汇票导入EXCEL数据时查询禁止背书标记数据对应的业务字典")
	public DataObject transXD_JZBSBJ01(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("currencyCd")+"斤斤计较");
		if(dataObject.getString("forbidflag")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "XD_JZBSBJ01");
			map.put("dictname", dataObject.getString("forbidflag").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null || res1.length==0){
		    	throw new Exception(dataObject.getString("forbidflag").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("forbidflag",res1[0].toString());
		    }

		}
		return dataObject;
	}
	
	@Bizlet("交通工具导入EXCEL数据时查询数据对应的业务字典")
	public DataObject getDataObjectCarDicd(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("usingType")+"斤斤计较");
		if(dataObject.getString("usingType")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "YP_GLCD0112");
			map.put("dictname", dataObject.getString("usingType").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null|| res1.length==0){
		    	throw new Exception(dataObject.getString("usingType").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("usingType",res1[0].toString());
		    }

		}
		if(dataObject.getString("ifCarMotionCard")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "XD_0002");
			map.put("dictname", dataObject.getString("ifCarMotionCard").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null ||res1.length==0){
		    	throw new Exception(dataObject.getString("ifCarMotionCard").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("ifCarMotionCard",res1[0].toString());
		    }

		}
		return dataObject;
	}
	@Bizlet("机器设备导入EXCEL数据时查询数据对应的业务字典")
	public DataObject getDataObjectEquipmentDicd(DataObject dataObject) throws Exception{
		//System.out.println(dataObject.getString("usingType")+"斤斤计较");
		if(dataObject.getString("usedInstance")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "YP_GLCD0105");
			map.put("dictname", dataObject.getString("usedInstance").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null ||res1.length==0){
		    	throw new Exception(dataObject.getString("usedInstance").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("usedInstance",res1[0].toString());
		    }

		}
		if(dataObject.getString("pipeCase")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "YP_GLCD0106");
			map.put("dictname", dataObject.getString("pipeCase").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null ||res1.length==0){
		    	throw new Exception(dataObject.getString("pipeCase").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("pipeCase",res1[0].toString());
		    }

		}
		if(dataObject.getString("rentStatus")!=null){
			Map<String, String> map = new HashMap<String,String>();
			map.put("dicttypeid", "YP_GLCD0107");
			map.put("dictname", dataObject.getString("rentStatus").trim());
		    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
		    if(res1==null ||res1.length==0){
		    	throw new Exception(dataObject.getString("rentStatus").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("rentStatus",res1[0].toString());
		    }

		}
		return dataObject;
	}
		@Bizlet("房地产导入EXCEL数据时查询数据对应的业务字典")
		public DataObject getDataObjectHouseDicd(DataObject dataObject) throws Exception{
			//System.out.println(dataObject.getString("usingType")+"斤房地产斤计较-----------------------");
			
			if(dataObject.getString("province")==null){
			    	throw new Exception("请输入省份编码");
			    }
			if(dataObject.getString("province")!=null){
		    Map<String, String> map = new HashMap<String,String>();
			map.put("dictid", dataObject.getString("province").trim());
			Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectDistrict",map);
			if(res1==null ||res1.length==0){
		    	throw new Exception(dataObject.getString("province").trim()+"未找到对应的数据字典项");
		    }else{
		    	dataObject.set("province",res1[0].toString());
		    }
			}
			if(dataObject.getString("city")==null){
			    	throw new Exception("请输入城市编码");
			    }
			if(dataObject.getString("city")!=null){
			    Map<String, String> map = new HashMap<String,String>();
				map.put("dictid", dataObject.getString("city").trim());
				Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectDistrict",map);
				if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("city").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("city",res1[0].toString());
			    }
				}
			if(dataObject.getString("town")==null){
			    throw new Exception("请输入区编码");
			    }
			if(dataObject.getString("town")!=null){
			    Map<String, String> map = new HashMap<String,String>();
				map.put("dictid", dataObject.getString("town").trim());
				Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectDistrict",map);
				if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("town").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("town",res1[0].toString());
			    }
				}
			if(dataObject.getString("country")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "CD000003");
				map.put("dictname", dataObject.getString("country").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("country").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("country",res1[0].toString());
			    }

			}
			if(dataObject.getString("housePropertyWay")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "YP_GLCD0046");
				map.put("dictname", dataObject.getString("housePropertyWay").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("housePropertyWay").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("housePropertyWay",res1[0].toString());
			    }

			}
			if(dataObject.getString("houseStructure")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "YP_GLCD0047");
				map.put("dictname", dataObject.getString("houseStructure").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("houseStructure").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("houseStructure",res1[0].toString());
			    }

			}
			if(dataObject.getString("housePropertyUse")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "YP_GLCD0048");
				map.put("dictname", dataObject.getString("housePropertyUse").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("housePropertyUse").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("housePropertyUse",res1[0].toString());
			    }

			}
			if(dataObject.getString("landQuale")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "YP_GLCD0071");
				map.put("dictname", dataObject.getString("landQuale").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("landQuale").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("landQuale",res1[0].toString());
			    }

			}
			if(dataObject.getString("landUse")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "YP_GLCD0066");
				map.put("dictname", dataObject.getString("landUse").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("landUse").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("landUse",res1[0].toString());
			    }

			}
		return dataObject;
	}
		
		@Bizlet("大宗商品导入EXCEL数据时查询数据对应的业务字典")
		public DataObject getDataObjectBigStoreDicd(DataObject dataObject) throws Exception{
			//System.out.println(dataObject.getString("usingType")+"斤斤计较");
			if(dataObject.getString("currencyCd")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "CD000001");
				map.put("dictname", dataObject.getString("currencyCd").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("landUse").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("currencyCd",res1[0].toString());
			    }

			}
			if(dataObject.getString("valuationUnit")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "YP_GLCD0025");
				map.put("dictname", dataObject.getString("valuationUnit").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null ||res1.length==0){
			    	throw new Exception(dataObject.getString("valuationUnit").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("valuationUnit",res1[0].toString());
			    }

			}
		return dataObject;
	}
		
		@Bizlet("证件类型对应的业务字典")
		public DataObject transCertType(DataObject dataObject) throws Exception{
			if(dataObject.getString("certType")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "CDKH0002");
				map.put("dictname", dataObject.getString("certType").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null || res1.length==0){
			    	throw new Exception(dataObject.getString("certType").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("certType",res1[0].toString());
			    }

			}
			return dataObject;
		}
		
		@Bizlet("职级对应职务的业务字典")
		public DataObject transJobRank(DataObject dataObject) throws Exception{
			if(dataObject.getString("jobRank")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "MB_ZWDJ0003");
				map.put("dictname", dataObject.getString("jobRank").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null || res1.length==0){
			    	throw new Exception(dataObject.getString("jobRank").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("jobRank",res1[0].toString());
			    }

			}
			return dataObject;
		}
		
		@Bizlet("性别对应的业务字典")
		public DataObject transGender(DataObject dataObject) throws Exception{
			if(dataObject.getString("gender")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "CDKH0048");
				map.put("dictname", dataObject.getString("gender").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null || res1.length==0){
			    	throw new Exception(dataObject.getString("gender").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("gender",res1[0].toString());
			    }

			}
			return dataObject;
		}
		@Bizlet("文化程度对应的业务字典")
		public DataObject transSchoolGree(DataObject dataObject) throws Exception{
			if(dataObject.getString("schoolGree")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "MB_WHCD0001");
				map.put("dictname", dataObject.getString("schoolGree").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null || res1.length==0){
			    	throw new Exception(dataObject.getString("schoolGree").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("schoolGree",res1[0].toString());
			    }

			}
			return dataObject;
		}
		@Bizlet("婚姻状况对应的业务字典")
		public DataObject transMarriageCd(DataObject dataObject) throws Exception{
			if(dataObject.getString("marriageCd")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "MB_HYZK0002");
				map.put("dictname", dataObject.getString("marriageCd").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null || res1.length==0){
			    	throw new Exception(dataObject.getString("marriageCd").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("marriageCd",res1[0].toString());
			    }

			}
			return dataObject;
		}
		@Bizlet("与本行关系对应的业务字典")
		public DataObject transRealate(DataObject dataObject) throws Exception{
			if(dataObject.getString("transRealate")!=null){
				Map<String, String> map = new HashMap<String,String>();
				map.put("dicttypeid", "MB_BHGX0005");
				map.put("dictname", dataObject.getString("transRealate").trim());
			    Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.grt.grtImportExcelDict.selectImportDictId",map);
			    if(res1==null || res1.length==0){
			    	throw new Exception(dataObject.getString("transRealate").trim()+"未找到对应的数据字典项");
			    }else{
			    	dataObject.set("transRealate",res1[0].toString());
			    }

			}
			return dataObject;
		}
		@Bizlet("查询白名单")
		public int queryWhite(DataObject dataObject) throws Exception{
			Object[] objiects=DatabaseExt.queryByNamedSql("default", "com.primeton.tsl.transferData.querywhite", dataObject);//该客户下最新的征信信息
			if(objiects!=null&&!"".equals(objiects)&&objiects.length>0){
			return 1;
			}
			return 0;
		}
}
