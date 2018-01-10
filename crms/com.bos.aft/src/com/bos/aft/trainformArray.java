package com.bos.aft;

import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

@Bizlet("trainformArray")
public class trainformArray {
	@Bizlet("")
	public String[] trainform(String name) {
		String res[];
		String result = name;
		res = result.split(",");

		return res;
	}

}
