package com.bos.utp.tools.OperateAudit;

import java.io.Serializable;

import com.eos.das.entity.IDASCriteria;
import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IUserObject;
import com.eos.data.serialize.XMLSerializer;
import com.eos.foundation.common.utils.DateUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.das.entity.impl.handler.IEntityHandler;
import commonj.sdo.DataObject;

public class DataAuditHandler implements IEntityHandler {

	/* (non-Javadoc)
	 * @see com.primeton.das.entity.impl.handler.IEntityHandler#afterDelete(commonj.sdo.DataObject, java.io.Serializable, java.lang.String[], java.lang.Object[])
	 */
	public void afterDelete(DataObject arg0, Serializable arg1, String[] arg2, Object[] arg3) {		
	}

	/* (non-Javadoc)
	 * @see com.primeton.das.entity.impl.handler.IEntityHandler#afterLoad(commonj.sdo.DataObject, java.io.Serializable, java.lang.String[], java.lang.Object[])
	 */
	public void afterLoad(DataObject arg0, Serializable arg1, String[] arg2, Object[] arg3) {
		
	}

	/* (non-Javadoc)
	 * @see com.primeton.das.entity.impl.handler.IEntityHandler#beforeLoad(java.lang.String, com.eos.das.entity.IDASCriteria)
	 */
	public void beforeLoad(String arg0, IDASCriteria arg1) {
		
	}

	/* (non-Javadoc)
	 * @see com.primeton.das.entity.impl.handler.IEntityHandler#beforeSave(commonj.sdo.DataObject, java.io.Serializable, java.lang.String[], java.lang.Object[])
	 */
	public void beforeSave(DataObject entity, Serializable primaryKey,String[] propertyNames, Object[] values) {
		IUserObject user = null;
		if(DataContextManager.current().getMUODataContext()==null){
			user = (IUserObject)DataContextManager.current().getSessionCtx().get(IUserObject.KEY_IN_CONTEXT);
		}else{
		    user = DataContextManager.current().getMUODataContext().getUserObject();
		}
		if(DataObjectUtil.checkEntityName(entity,"com.bos.utp.dataset.organization.OmOrganization")){
			XMLSerializer  util = new XMLSerializer();		
			DataObject logObject=DataObjectUtil.createDataObject("com.bos.utp.dataset.tools.AtDataaudit");
			//operatetype operatorid operatedesc operatechange operatetime			
			logObject.set("operatorid", Long.parseLong(user.getAttributes().get("operatorid").toString()));		
			logObject.set("operatetime", DateUtil.parse(DateUtil.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
			logObject.set("operatedesc", "新增机构信息");
			logObject.set("operatetype", "增加");		
			logObject.set("operatechange",util.marshallToString(entity) );					
			//DatabaseExt.getPrimaryKey(logObject);
			DatabaseUtil.insertEntity("default", logObject);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.primeton.das.entity.impl.handler.IEntityHandler#beforeUpdate(commonj.sdo.DataObject, java.io.Serializable, java.lang.String[], java.lang.Object[], java.lang.Object[])
	 */
	public void beforeUpdate(DataObject arg0, Serializable arg1, String[] arg2, Object[] arg3, Object[] arg4) {
		
	}	
}
