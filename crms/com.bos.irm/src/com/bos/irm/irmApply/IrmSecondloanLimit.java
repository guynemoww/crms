package com.bos.irm.irmApply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;

import commonj.sdo.DataObject;

public class IrmSecondloanLimit {
	//计算评级分数
	public int getIrmScore(HashMap map){
		String model_id = (String) map.get("model_id");//模板
		List list = (List) map.get("model");
		int all_score = 0;//总分值
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				HashMap map2 = (HashMap) list.get(i);
				Object index_param = map2.get("index_param");//参数
				map2.put("model_id", model_id);
				Object[] IrmScore = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.irm.irmApply.irmApplysql.getIrmScoreOne", map2);
				if (null != IrmScore && IrmScore.length > 0) {
					DataObject IrmScoreOne = (DataObject) IrmScore[0];
					String index_score = IrmScoreOne.getString("INDEX_SCORE");
					if(index_score != null && !"".equals(index_score));
					all_score += Integer.parseInt(index_score);
				}
			}
		}
		return all_score;
	}
	
	//获取秒贷秒贷额度
	public Map getIrmScoreRank(int limit_score){
		HashMap map = new HashMap();
		map.put("limit_score", limit_score);////总分值
		String limit_rank_min = null;
		String limit_rank_max = null;
		Object[] IrmRank = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.irm.irmApply.irmApplysql.getIrmScoreRank", map);
		if (null != IrmRank && IrmRank.length > 0) {
			DataObject IrmRankOne = (DataObject) IrmRank[0];
			limit_rank_min = IrmRankOne.getString("LIMIT_RANK_MIN");
			limit_rank_max = IrmRankOne.getString("LIMIT_RANK_MAX");
			System.out.println("秒贷秒贷额度:limit_rank_min="+limit_rank_min+"   limit_rank_max="+limit_rank_max);
		}
		map.put("limit_rank_min", limit_rank_min);
		map.put("limit_rank_max", limit_rank_max);
		return map;
	}
	
	//获取决策系数
	public String getIrmInterestCoefficient(HashMap map){
		//map.put("client_rank_id", "2");//客户级别
		//map.put("limit_score", 78);//分值
		int decisionScore = 0;//利率决策分值
		String coefficient_rank_key = null;//利率决策系数
		Object[] Interest = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.irm.irmApply.irmApplysql.getIrmScoreDecision", map);
		if (null != Interest && Interest.length > 0) {
			DataObject InterestOne = (DataObject) Interest[0];
			String decision_score = InterestOne.getString("DECISION_SCORE");
			System.out.println("获取决策系数分值=decision_score="+decision_score);
			if(decision_score != null && !"".equals(decision_score)){
				decisionScore = Integer.parseInt(decision_score);
			}
		}
		map.put("decision_score", decisionScore);
		Object[] Coefficient = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.irm.irmApply.irmApplysql.getIrmScoreCoefficient", map);
		if (null != Coefficient && Coefficient.length > 0) {
			DataObject CoefficientOne = (DataObject) Coefficient[0];
			coefficient_rank_key = CoefficientOne.getString("COEFFICIENT_RANK_KEY");
			System.out.println("获取决策系数key值=coefficient_rank_key="+coefficient_rank_key);
		}
		return coefficient_rank_key;
	}
}
