package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class hkss_exportXLS {

	@Bizlet("")
	public List<Object> getData(DataObject[] exldo) {
		String[] title={"期次" ,"还款日期", "天数" ,"还款金额", "本金", "利息" ,"剩余本金"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					 exldo[i].getString("currPeri"),
					 exldo[i].getString("nextProvDate"),
					 exldo[i].getString("ts"),
					 exldo[i].getString("DTotalAmt"),
					 exldo[i].getString("DCurPrin"),
					 exldo[i].getString("DCurItr"),
					 exldo[i].getString("sybj")
			
				 
			};
//			System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");

		l.add(a);
 		}
		
		
 		return l;

	}
}
