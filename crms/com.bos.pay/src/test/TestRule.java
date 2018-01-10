package test;

import java.util.HashMap;
import java.util.List;

import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.git.easyrule.util.RuleException;

public class TestRule {

	
	public void testRuleEngine(){
		RuleService rs = new RuleService();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("partyId", "999999999");
		
		try {
			List<MessageObj> msgList = rs.runRule("RBIZ_0075", map);
			String msg = convertMsg(msgList);
			if(!msg.equals("true")){
				
			}
			
		} catch (RuleException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private String convertMsg(List<MessageObj> msgList) {
		StringBuffer sf = new StringBuffer();
		if (msgList != null && !msgList.isEmpty()) {
			for (int i = 0; i < msgList.size(); i++) {
				MessageObj t = msgList.get(i);
				if (EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())) {
					sf.append("[(" + (i + 1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
				}
			}
		}
		if (sf.length() > 0) {
			return sf.toString();
		}
		return "true";
	}
	
	
}
