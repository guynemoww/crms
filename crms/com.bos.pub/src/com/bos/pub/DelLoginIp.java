package com.bos.pub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bos.utp.tools.DBUtil;
import com.eos.runtime.core.IRuntimeListener;
import com.eos.runtime.core.RuntimeEvent;

public class DelLoginIp implements IRuntimeListener {
	public void start(RuntimeEvent arg0) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection(DBUtil.DB_NAME_CRMS);
			ps = conn.prepareStatement("delete tb_pub_ipmanage");
			ps.execute();
		} catch (SQLException e) {
			System.out.println("启动时清空tb_pub_ipmanage失败！");
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, ps, rs);
		}
	}

	public void stop(RuntimeEvent arg0) {
	}
}
