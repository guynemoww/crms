package com.bos.pub.entity;

import com.bos.pub.entity.name.AccTableName;
import com.bos.pub.entity.name.AftTableName;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.BatchTableName;
import com.bos.pub.entity.name.BizTableName;
import com.bos.pub.entity.name.ClaTableName;
import com.bos.pub.entity.name.ConTableName;
import com.bos.pub.entity.name.CrdTableName;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.pub.entity.name.GrtTableName;
import com.bos.pub.entity.name.IrmTableName;
import com.bos.pub.entity.name.LoanTableName;
import com.bos.pub.entity.name.LogTableName;
import com.bos.pub.entity.name.LstTableName;
import com.bos.pub.entity.name.PubTableName;
import com.bos.pub.entity.name.SysTableName;
import com.bos.pub.entity.name.TabTableName;
import com.bos.pub.entity.name.WfmTableName;

public interface TableName extends AccTableName, AftTableName, BatchTableName, BizTableName, ClaTableName, ConTableName, CrdTableName, CsmTableName, GrtTableName, IrmTableName, LoanTableName, LstTableName, AssetsTableName, PubTableName, SysTableName, TabTableName, WfmTableName, LogTableName {

	String OM_ORGANIZATION = "com.bos.pub.userMove.userMove.OmOrganization";
	// 机构表会查询出相应的所有父节点信息
	String OM_ORGANIZATION_TREE = "com.bos.utp.dataset.organization.OmOrganization";
}
