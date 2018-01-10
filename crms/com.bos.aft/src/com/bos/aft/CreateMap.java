package com.bos.aft;

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

}
