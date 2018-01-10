package com.bos.utp.tools.testcase;

import java.sql.Statement;

import com.eos.infra.connection.IStatementSynchronization;

public class StatementMonitor implements IStatementSynchronization {

	public void afterSqlExecute(Statement arg0, String arg1) {
		/*
		 * String tmpsql = arg1.toUpperCase().trim();
		 * if(tmpsql.startsWith("UPDATE")){ loger.println(arg1); }
		 */
	}

	public void afterSqlExecuteWithException(Statement arg0, String arg1, Exception arg2) {

	}

	public void beforeSqlExecute(Statement arg0, String arg1) {

	}

	public void onClose(Statement arg0) {

	}

	public void onCreate(Statement arg0) {

	}

	public void onException(Statement arg0, Exception arg1) {

	}

}
