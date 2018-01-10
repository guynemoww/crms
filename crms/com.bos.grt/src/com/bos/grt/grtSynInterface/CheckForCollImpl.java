package com.bos.grt.grtSynInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.fasterxml.jackson.databind.ObjectMapper;

import commonj.sdo.DataObject;

/**
 * 针对押品系统需要的零星校验，开发此接口，根据 目标值判断 属于哪种校验。校验结果
 * @author lenovo
 *
 */
public class CheckForCollImpl implements CheckForCollInterface {

	public String checkForColl(String inputInfoJsonStr) {
		String ypxxJsonStr= "";
		System.out.println("Jackson:inputInfoJsonStr转换为Map开始...");
		ObjectMapper mapper = new ObjectMapper();
        Map inputInfoMap = new HashMap();;
		try {
			inputInfoMap = mapper.readValue(inputInfoJsonStr,HashMap.class);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		System.out.println("Jackson:inputInfoJsonStr转换为Map结束..."+inputInfoMap);
		
		try {
			String trans_code = (String)inputInfoMap.get("trans_code");
			//解析参数，如果trans_code = 0001 ,则校验位判断该押品下是否存在生效的担保合同
			if( trans_code.equals("0001")){	
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("cltNo", (String)inputInfoMap.get("clt_no"));
				map.put("status", "03");//生效的担保合同
				Object[] outs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
						"com.bos.grt.grt.getSubForSuretyno", map);
				if(outs.length > 0){
					//存在生效的担保合同，将map转成String
					map.put("isSuccess", "true");
					List list = new ArrayList();
					for(int i = 0;i<outs.length;i++){
						List list1 = new ArrayList();
						DataObject dobject =  (DataObject)outs[i];
						Map outsmap = new HashMap();
						outsmap.put("subcontractNum",dobject.getString("SUBCONTRACT_NUM"));
						outsmap.put("suretyAmt",dobject.getString("SURE_AMT")); 
						list1.add(outsmap);
						list.add(list1);
					}
					map.put("list", list);
					ypxxJsonStr = mapper.writeValueAsString(map);
				//	System.out.println("检查押品下是否存在生效的担保合同出参："+ypxxJsonStr);
				}
			}else if(trans_code.equals("0002")){//根据申请号查询该贷款是不是属于借新还旧
				System.out.println("校验该贷款是不是属于借新还旧开始...");
				//add by shangmf：增加担保合同总金额之和是否超过最高额担保合同的总金额的校验
				Map map_jxhj = new HashMap();
				map_jxhj.put("applyId", (String)inputInfoMap.get("apply_id"));
				//通过合同id查询申请ID，如果申请ID存在
				Object[] objs_jxhj = DatabaseExt.queryByNamedSql("default","com.bos.conApply.conApply.checkifJxhj", map_jxhj);
				if(objs_jxhj.length>0){//是借新还旧
					map_jxhj.put("isSuccess", "true");
				}else{
					map_jxhj.put("isSuccess", "false");
				}
				ypxxJsonStr = mapper.writeValueAsString(map_jxhj);
			}
			System.out.println("检查押品下是否存在生效的担保合同出参："+ypxxJsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ypxxJsonStr;
	}

}
