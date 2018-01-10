/**
 * 
 */
package com.bos.utp.org;


import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author liuqi
 * @date 2014-03-06 10:22:45
 *
 */
public class queryEmpUserstSpecial {
	@Bizlet("setSpecialty")
	public DataObject[] setSpecialty(DataObject[] datas) {
		if (datas != null && datas.length > 0) {
			for (int i = 0; i < datas.length; i++) {
				long operatorid = datas[i].getLong("operatorid");
				Object[] object = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.dataset.omemployee.queryRoleidForEmpExt", operatorid);
				if (object != null && object.length > 0) {
					String rolename = "";
					String roleids = "";
					for (int m = 0; m < object.length; m++) {
                        DataObject dotemp = (DataObject)object[m];
						rolename += dotemp.getString("ROLENAME") + ",";
                        roleids += dotemp.getString("ROLEID") + ",";
					}
					if (rolename != null && rolename.length() > 0) {
						rolename = rolename.substring(0,rolename.length()-1);
                        roleids = roleids.substring(0,roleids.length()-1);
						datas[i].setString("rolename", rolename);
						datas[i].setString("roleids", roleids);
					}
					
				}
			}
		}
		
		return datas;
	}

	
}
