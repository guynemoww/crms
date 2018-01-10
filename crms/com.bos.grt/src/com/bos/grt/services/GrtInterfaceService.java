package com.bos.grt.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GrtInterfaceService {
	@SuppressWarnings("unchecked")
	public static Map<String, String> YP1113(String suretyId) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
		CollServiceImplServiceServiceStub.CollServiceCommInter serQuery = new CollServiceImplServiceServiceStub.CollServiceCommInter();

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("cltNo", suretyId);
		queryMap.put("trans_code", "1113");// 押品详细信息查询接口交易码
		String queryJson = mapper.writeValueAsString(queryMap);
		serQuery.setIn0(queryJson);
		String queryStr = stub.collServiceCommInter(serQuery).getOut1();
		if (queryStr == null || queryStr.isEmpty()) {
			return null;
		}
		Map<String, String> strmap = mapper.readValue(queryStr, HashMap.class);
		return strmap;
	}

	public static Map<String, String> YP1113_UNERR(String suretyId) {
		try {
			return YP1113(suretyId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
