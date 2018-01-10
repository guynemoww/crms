/**
 * 
 */
package com.bos.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.util.FlowUtil;
import com.bos.jaxb.JAXBUtil;
import com.bos.pub.model.ImageBatchBean;
import com.bos.pub.model.ImageBatchsBean;
import com.bos.pub.model.ImageBatchsRootBean;
import com.bos.pub.model.ImageBean;
import com.bos.pub.model.ImageRootBean;
import com.bos.pub.model.ImagesBean;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.sunyard.TransEngine.client.ClientApi;
import com.sunyard.TransEngine.doc.ECMDoc;
import com.sunyard.TransEngine.exception.SunTransEngineException;
import com.sunyard.TransEngine.util.OptionKey;

/**
 * @author ljf
 * @date 2015-08-26 20:44:56
 *
 */
@Bizlet("影像资料工具类")
public class ImageMagerUtil {
	
	public static  TraceLogger logger = new TraceLogger(FlowUtil.class);

	private static  String ip = "172.20.12.54";
	private static  String port = "8021";
	private static  String username = "admin";
	private static  String password = "111";
	//private String batchId = "201206212AD886FE-0862-D97B-C6B0-69E54B33711D-1";// 影像批次号
	//private String objName = "QYZTZL_DOC";
	//private String part = "QYZTZL_PART";
	
	
	/**
	 * 查询批次下的所有文件 必需传批次号、业务模型名、查询标识、业务开始时间
	 * 
	 * 创建客户端Api对象连接 ClientApi api = new ClientApi(ip, port, username, password);
	 * 创建一个ECMDoc对象 ECMDoc doc = new ECMDoc(); 设置需要的批次号 doc.setBatchID(batchId);
	 * 批次的业务模型名 doc.setObjName(objName); 设置令牌校验，第一个参数为：令牌号 第二个参数为：令牌用户
	 * 第三个参数为:令牌值。 三个参数值可通过三个接口来分别设置分别为：setTokenCode、setTokenUser、setTokenValue
	 * doc.setToken("8B9DBDE22FF2F159223A0AB18E33861A", "Token_User",
	 * "Token_Value"); 设置查询文件信息标识 doc.setOption(OptionKey.QUERY_BATCH_FILE);
	 * 设置批次上传的业务开始时间 ,时间格式为YYYYMMDD doc.setQueryTime("20110401");
	 * 设置查询的文件信息按字段排序，传入字符串数组 doc.setQuerySort(new ArrayList<String>());
	 * 设置查询历史版本， true 所有的历史版本 false 历史版本 “1”查询第一个版本 “2”查询第二个版本 以此类推
	 * doc.setIsHistory(true); 调用api接口，查询文件信息 api.queryFile(doc);
	 */
	public static  List<ImagesBean> queryBatchFile(String batchid,String qtime,String objName) {
		List<ImagesBean> ls = new ArrayList<ImagesBean>();
		try {
			ClientApi api = new ClientApi(ip, port, username, password);
			ECMDoc doc = new ECMDoc();
			// 必选信息， 设置要查询的批注号
			doc.setBatchID(batchid);
			// 必选信息， 设置业务模型英文名
			doc.setObjName(objName);
			// 必选信息， 设置查询标识，以下设置为查询批次文件信息
			doc.setOption(OptionKey.QUERY_BATCH_FILE);
			// 必选信息， 设置批次业务开始时间
			doc.setQueryTime(qtime);
			// 调用查询文件信息接口
			String rtXml = api.queryFile(doc);
			ImageRootBean rtObject = (ImageRootBean)JAXBUtil.unmarshal(rtXml, ImageRootBean.class);
			ls = rtObject.getImages();
		} catch (SunTransEngineException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	
	/**
	 * 高级搜索，查询批次信息 必需传业务模型名、业务开始时间、高级搜索查询标识、查询条件
	 * 
	 * 创建客户端Api对象连接 
	 * ClientApi api = new ClientApi(ip, port, username, password);
	 * 创建一个ECMDoc对象 
	 * ECMDoc doc = new ECMDoc(); 
	 * 批次的业务模型名 
	 * doc.setObjName(objName);
	 * 设置令牌校验，第一个参数为：令牌号 第二个参数为：令牌用户 第三个参数为令牌值。
	 * 三个参数值可通过三个接口来分别设置分别为：setTokenCode、setTokenUser、setTokenValue
	 * doc.setToken("8B9DBDE22FF2F159223A0AB18E33861A", "Token_User", "Token_Value"); 
	 * 设置查询批次信息标识 
	 * doc.setOption(OptionKey.HEIGHT_QUERY);
	 * 设置批次上传的业务开始时间 ,时间格式为YYYYMMDD
	 * doc.setQueryTime("20110401");
	 * 高级搜索支持，过滤条件和业务属性来查询，可通过setFilter和setBusiAttribute两种方式实现 查询需要过滤的条件
	 * doc.setFilter("LSH='123456789'"); 
	 * 根据业务属性值来查询 
	 * doc.setBusiAttribute("LSH", "1234567"); 
	 * 调用api查询批次信息接口 
	 * api.queryBatch(doc);
	 */
	public static  List<ImageBatchsBean> heightQuery(String busiNo,String qtime,String objName) {
		List<ImageBatchsBean> ls = new ArrayList<ImageBatchsBean>();
		try {
			ClientApi api = new ClientApi(ip, port, username, password);
			ECMDoc doc = new ECMDoc();
			// 必选信息， 设置业务模型英文名
			doc.setObjName(objName);
			// 必选信息， 设置业务开始时间,时间格式为YYYYMMDD
			doc.setQueryTime(qtime);
			// 必选信息， 设置查询标识， 以下设置为高级搜索
			doc.setOption(OptionKey.HEIGHT_QUERY);
			// 可选信息， 设置查询过滤条件
			 doc.setBusiAttribute("BUSI_SERIAL_NO", busiNo);
			// 调用查询批次信息接口
			String rtXml = api.queryBatch(doc);
			
			ImageBatchsRootBean rtObject = (ImageBatchsRootBean)JAXBUtil.unmarshal(rtXml, ImageBatchsRootBean.class);
			
			ls = rtObject.getBatchs();
			//System.out.println("----->"+ls.get(0).getBatchBean().getBatchId());
			
		} catch (SunTransEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	
	/**
	 * 读取业务编号下的影像文件
	 * @param busiNo(测试数据：1234567890)
	 * @param qtime
	 * @param objName
	 */
	@Bizlet("读取业务编号下的影像文")
	public static String importImageFile(String busiNo,String qtime,String objName){
		
		logger.info("=======================>导入影像的[业务流水号："+busiNo+",文档部件："+objName+"],导入开始....");
		
		//存放该业务流水下，所有的影像资料
		List<ImagesBean> rt_all = new ArrayList<ImagesBean>();
		//返回的批次号集合
		List<ImageBatchsBean> list = heightQuery(busiNo,qtime,objName);
		if(null !=list && list.size()>0){
			
			for (ImageBatchsBean ibbs : list) {
				ImageBatchBean ibb= ibbs.getBatchBean();
				List<ImagesBean> rt= queryBatchFile(ibb.getBatchId(),ibb.getQueryTime(),objName);
				rt_all.addAll(rt);
			}
		}
		
		//拼接影像导入报文
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?><root><head><transcode>td0048</transcode></head><body>");
		if(null!=rt_all && rt_all.size()>0){
			for (ImagesBean ibs : rt_all) {
				
				ImageBean ib = ibs.getImageBean();
				sb.append("<param url='").append(ib.getUrl()).append("' operflag='2'>").append(ib.getUrl()).append("</param>");
				System.out.println("----->地址："+ib.getUrl());
			}
		}
		sb.append("</body></root>");
		
		//提交影像控件
		
		logger.info("=======================>导入影像的[业务流水号："+busiNo+",文档部件："+objName+"],导入结束....");
		
		return sb.toString();
	}
	
	/**
	 * 初始化影像控件
	 */
	public static void initSumsan(){
		
		
		
	}
	
	/**
	 * 提交影像控件
	 */
	public static void submitSumsan(){
		
		
	}
	
	
	/**
	 * 根据业务编号，获取文档部件,客户编号
	 * @param businessNumber
	 * @return
	 */
	@Bizlet("根据业务编号，获取文档部件,客户编号")
	public static Map<String,String> getImageConfData(String businessNumber,String orgid){
		
		Map<String,String> rtmap = null;
		if(null!=businessNumber && !"".equals(businessNumber)){
			
			Map<String,String> para = new HashMap<String,String>();
			para.put("orgid", orgid);
			para.put("businessNumber",businessNumber);
			//从客户参与者表中获取
			Object[] obj = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.image.get_image_config", para);
			if(null!=obj && obj.length>0){
				rtmap = (Map<String,String>)obj[0];
				//获取过滤节点，自然人申请需要单独处理，因为分不动产申请信息，动产申请信息，一般类申请信息三种。
				String flowModuleType = rtmap.get("flowModuleType");
				String [] strs = flowModuleType.split("\\|");
				if(null !=strs && strs.length>1){
					/**
					 * 02002:个人房屋按揭贷款
					 * 02005001:住房公积金委托贷款－一手房
					 * 02005003:住房公积金委托贷款－二手房
					 * 02001004:个人专项机械设备按揭贷款
					 * 02004001:“方向顺”个人自用汽车贷款
					 * 02004002:“方向顺”个人商用汽车贷款
					 * 02003012:个人汽车消费贷款
					 */
					String productType = strs[1];
					if(productType.startsWith("02002")==true || productType=="02005001"|| productType=="02005010"||productType == "02005003"){//不动产
						flowModuleType = "22,222,225";
					}else if(productType == "02001004"||productType == "02004001"||productType == "02004002"||productType == "02003012"){//动产
						flowModuleType = "22,222,226";
					}else{//其他
						flowModuleType = "22,221,222";
					}
					//将判断好的值，设回原位
					rtmap.put("flowModuleType", flowModuleType);
				}
			}
		}
		return rtmap;
	}
	
	public static void main(String[] args) {
		
		importImageFile("1234567890","20150814","QYZTZL_DOC");
		
	}
}
