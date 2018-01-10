package com.bos.conPrint;

import java.util.Map;

public interface IConInfoLoad {

	/**
	 * 加载合同数据
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> loadReport(Map<String, String> param);

}
