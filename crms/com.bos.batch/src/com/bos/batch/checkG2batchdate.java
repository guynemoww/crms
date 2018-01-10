package com.bos.batch;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;

import com.bos.pub.GitUtil;
import com.bos.utp.auth.bizlet.LogonUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import commonj.sdo.DataObject;


@Bizlet("checkG2batchdate")
public class checkG2batchdate {
	Logger logger = TraceLoggerFactory.getLogger("com.bos.batch.acbatchconsolebiz.BatchMonitorConsole");
	DateUtil dateUtil=new DateUtil();
	String date=dateUtil.getCurDate("yyyy-MM-dd");
	//String date="2014-11-01";


	
    @Bizlet("判断批量日期是否是系统日期前一天")
	public int checkG2batchdate() throws ParseException{
    	int status=2;
		java.util.Map<String, String> map = new HashMap<String, String>();
		Object[] res1 =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.batch.task.getbatchdate",map);
		if(res1!=null && res1.length>0){
			DataObject batchdate1=(DataObject)res1[0];
			String batchdate=batchdate1.getString("batch_date");
			batchdate=batchdate.substring(0, 10);
			if(!batchdate.equals(dateUtil.getDate(date, "yyyy-MM-dd", -1))){
				//跑批日期不是系统日期前一天
				status=2;
			}else{status=1;}
		}
    	return status;
    	
    }
	
    
    
    //计算前一天的日期
    public String get1daybefore(String today) throws ParseException{
    	String date1daybefore="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        Date date1=sdf.parse(today);
        //System.out.println("当前时间：" + sdf.format(date7daysbefore));          
        Calendar rightNow = Calendar.getInstance(); 
        rightNow.setTime(date1);  
        rightNow.roll(Calendar.DAY_OF_YEAR, -1);//减1天 
        date1daybefore=sdf.format(rightNow.getTime());
     
    	return date1daybefore;
    }
    
}
