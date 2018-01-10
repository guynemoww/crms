/**
 * 
 */
package com.bos.risk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author CHJ
 * @date 2015-08-17 10:44:06
 *
 */
@Bizlet("分类客户")
public class RiskCust {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bizlet("过滤客户")
	public Object[] filterRiskCust(Object[] items,String custtype){
		List<Map> list = new ArrayList<Map>();
		List<Map> list2 = new ArrayList<Map>();
		list = Arrays.asList(items);
		for(int i = 0 ; i< list.size() ; i++){
			if("mloan".equals(custtype)){
				break;
			}else{
				if(custtype.equals(list.get(i).get("custtype"))){
					list2.add(list.get(i));
				}
			}
		}
		return list2.toArray();
	}

}
