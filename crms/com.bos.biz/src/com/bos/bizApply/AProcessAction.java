package com.bos.bizApply;

import com.bos.pub.DictContents;
import com.bos.pub.UserUtil;

public abstract class AProcessAction implements IProcessAction {

	public String getDBName() {
		return DictContents.DB_NAME_CRMS;
	}

	public boolean isManager() {
		return UserUtil.isManager();
	}

}
