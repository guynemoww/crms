package com.bos.ews;

import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

@Bizlet("createMap")
public class CreateMap {

	@Bizlet("")
	public Map createMap(Map mp) {
		mp = new HashMap();
		return mp;

	}

	@Bizlet("")
	public String returnOrgLevel(String postCd) {
		String orgLevel = null;
		orgLevel=postCd.substring(1, 2);
		return orgLevel;
	}

}
