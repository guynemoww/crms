package com.bos.inter.CallT24Interface;

import com.eos.system.annotation.Bizlet;

public class HandleTDpAcctAllInqRs {
	@Bizlet("test")
public String check(Object obj){
			String msg="";
	TDpAcctAllInqRs rs=(TDpAcctAllInqRs)obj;
		
		//借贷标志
		if(rs.PostingRestrict!=null){
			if(rs.PostingRestrict.equals("1") || rs.PostingRestrict.equals("3")){
				msg=msg+"1;";
			}
		}
		//冻结
		if(rs.LockedAmt!=null){
			if(!rs.LockedAmt.equals("")&&rs.LockedAmt!=null){
				msg=msg+"2;";
			}
		}
		//休眠户
		if(rs.InactivMarker!=null){
			if(rs.InactivMarker.equals("Y")){
				msg=msg+"3;";
			}
		}
		//久悬户
		if(rs.CateGory!=null){
			if(rs.CateGory.equals("1604")){
				msg=msg+"4;";
			}
		}
		//活期账户
		if(rs.MaturityDate==null){
			if(rs.AgreeMentAcctNo.substring(0, 2).equals("23")||rs.AgreeMentAcctNo.substring(0, 2).equals("25"))
				return msg=msg+"7;";
		}	
		//		定期账户
		if(rs.MaturityDate!=null){
				return msg=msg+"5;";
		}	
		//内部账号
		if(rs.AgreeMentAcctNo!=null){
			if(rs.AgreeMentAcctNo.substring(0, 3).equals("CNY")){
				msg=msg+"6;";
			}
		}
		if(rs.MaturityDate==null){
			if(!(rs.AgreeMentAcctNo.substring(0, 2).equals("23")||rs.AgreeMentAcctNo.substring(0, 2).equals("25")))
	
			 return msg=msg+"8;";
		}
		
		return msg;
}

}
