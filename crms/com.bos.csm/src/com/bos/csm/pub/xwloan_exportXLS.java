package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;
 


import commonj.sdo.DataObject;

@Bizlet("getdata")
public class xwloan_exportXLS {

	@Bizlet("")
	public List<Object> getData(DataObject[] exldo) {
		String[] title = { "期次", "还款时间", "天数", "还款金额 ", "本金", "利息 ",
				"剩余本金 "};
		List<Object> l = new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a = {
					exldo[i].getString("QC"),
					exldo[i].getString("END_DATE").substring(0, 10),
					exldo[i].getString("DAYS"),
					exldo[i].getString("AMT"),
					exldo[i].getString("BJ"),
					exldo[i].getString("LX"),
					exldo[i].getString("SYBJ")

					
					
			};
			//			System.out.print(i+"----"+(String)exldo[i].get("partyName")+"------>");
			//			System.out.println(GitUtil.getUserInfo((String)exldo[i].get("userNum")));
			l.add(a);

		}

		return l;

	}
}
