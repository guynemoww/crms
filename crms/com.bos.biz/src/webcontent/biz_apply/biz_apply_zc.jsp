<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 11:53:02
  - Description:
-->
<head>
<title>业务申请新增</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<script type="text/javascript" src="<%=contextPath %>/biz/biz_js/biz.js"></script>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="party.partyId" class="nui-hidden nui-form-input" name="party.partyId" value="<%=request.getParameter("partyId")%>"/>
	<input id="party.partyTypeCd" class="nui-hidden nui-form-input" name="party.partyTypeCd" value="<%=request.getParameter("partyTypeCd")%>"/>
	<input id="biz.bizHappenType" class="nui-hidden nui-form-input" name="biz.bizHappenType"/>
	
    <div class="nui-dynpanel" columns="4" id="table1">
		<label class="nui-form-label">客户编号：</label>
		<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>
		
		<label class="nui-form-label">客户名称：</label>
		<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName" />
		
		<label id="certt">证件类型：</label>
		<input id="party.certType" class="nui-text nui-form-input" name="party.certType"  dictTypeId="CDKH0002" />
		
		<label id="certn">证件号码：</label>
		<input id="party.certNum" class="nui-text nui-form-input" name="party.certNum" />
	
		<label id="credlevel">信用等级：</label>
		<input id="party.creditLevel" class="nui-text nui-form-input" name="party.creditLevel" />
		
		<label for="isteam$text">业务性质：</label>
		<input id="biz.bizType" name="biz.bizType" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0002" required="true" onvaluechanged="changeBizType"/>
		
		<label class="nui-form-label">业务发生性质：</label>
		<input id="biz.bizHappenNature" name="biz.bizHappenNature" valueField="dictID"  required="true"
		class="nui-text nui-form-input" dictTypeId="XD_SXYW0004"/>
		
		<label id="product">业务品种：</label>
		<input id="biz.productType" name="biz.productType" class="nui-buttonEdit nui-form-input"
			allowInput="false" onbuttonclick="selectProduct" required="true" dictTypeId="product"/>
		
		<label class="nui-form-label" id="bankTeam">是否银团：</label>
		<input id="biz.isBankTeamLoan" name="biz.isBankTeamLoan" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true"/>
		
		<label for="isteam$text" id="applyMode">申报模式：</label>
		<input id="biz.applyModeType" name="biz.applyModeType" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0003" required="true" onvaluechanged="changeModeType"/>
		
		<label for="isteam$text" id="loanType">贷款形式：</label>
		<input id="biz.loanType" name="biz.loanType" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_DKXS0001" required="true"/>
		
		<label for="isteam$text" id="busiPartner">是否有按揭合作商：</label>
		<input id="biz.busiPartner" name="biz.busiPartner" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true"/>
	 		
	 	<label for="isteam$text" id="greenLoan">是否绿色贷款：</label>
		<input id="biz.isGreenLoan" name="biz.isGreenLoan" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" dvalue="0"/>
		
		<label class="nui-form-label" id="lowType">低风险类型：</label>
		<input id="biz.lowRiskBizType" name="biz.lowRiskBizType"  valueField="dictID" class="mini-newcheckbox" 
		required="true" repeatDirection="vertical" colspan="1" repeatLayout="flow" repeatItems="20" required="true"
		valueField="dictID" dictTypeId="XD_SXYW0225"/>
		
		<label class="nui-form-label" id="guarantyType">担保方式：</label>
	    <input id="tbBizAmountApply.guarantyType" name="tbBizAmountApply.guarantyType" class="mini-newcheckbox" required="true" data="data" valueField="dictID" dictTypeId="CDZC0005"/>
		
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<!-- <a class="nui-button" id="btnCreate1" iconCls="icon-add" onclick="applyPre">业务预申请</a> -->
		<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="checkInfo">确定</a>
	</div>
</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	 $("#bankTeam").css("display","none");
	nui.get("biz.isBankTeamLoan").setVisible(false);
	$("#lowType").css("display","none");
	nui.get("biz.lowRiskBizType").hide();
	$("#product").css("display","none");
	nui.get("biz.productType").hide();
	$("#busiPartner").css("display","none");//是否有按揭合作商
	nui.get("biz.busiPartner").hide();
	
	//var bizHappenNature="01";//默认01   避免出现业务发生性质为空的问题
	//客户信息从tab页面传过来，不用再次查询
	var partyTypeCd = "<%=request.getParameter("partyTypeCd")%>";
	var certType = "<%=request.getParameter("certType")%>";
	var certNum = "<%=request.getParameter("certNum")%>";
	var bizHappenNature = "<%=request.getParameter("bizHappenNature")%>";
	if(''==bizHappenNature || null==bizHappenNature || 'null'==bizHappenNature){
		bizHappenNature = '01';
	}
	var partyId="<%=request.getParameter("partyId")%>";
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"partyId":partyId});
		$.ajax({
            url: "com.bos.irm.irmApply.irmApply.getPartyInfoByPartyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var o = nui.decode(text);
            	nui.get("party.partyNum").setValue(o.party.partyNum);
            	nui.get("party.partyName").setValue(o.party.partyName);
            	
				nui.get("party.certType").setValue(certType);
				nui.get("party.certNum").setValue(certNum);
				risk(0);//隐藏低类型
		    	bankTeam(0);//隐藏是否银团
		    	nui.get("biz.bizHappenType").setValue("01");
		    	nui.get("biz.bizHappenNature").setValue(bizHappenNature);
		    	nui.get("biz.bizHappenNature").validate();
		    	
		    	//全局变量赋值，在点保存时再赋值一遍
		    	if("01" == partyTypeCd){//对公客户
		    		nui.get("biz.bizType").setData(getDictData("XD_SXYW0002","str","01,02,07"));//向控件设置业务字典
					getRatingLevel(partyId,'');
					nui.get("party.creditLevel").setRequired(true);
					nui.get("party.creditLevel").validate();
					
					loanType(0);//贷款形式
		    		greenLoan(0);//是否绿色贷款
		    	}
		    	if("02" == partyTypeCd){//个人客户
		    		nui.get("biz.bizType").setData(getDictData("XD_SXYW0002","str","04"));
		    		nui.get("biz.bizType").setValue("04");
		    		//nui.get("biz.bizType").setEnabled(false);
		    		nui.get("biz.loanType").setValue('0');//贷款形式---默认新增 
		    		nui.get("biz.loanType").setEnabled(false);//贷款形式
		    	}
		    	if("06" == partyTypeCd){//集团
		    		nui.get("biz.bizType").setValue("03");
		    		nui.get("biz.bizType").setEnabled(false);
		    		nui.get("biz.applyModeType").setValue("01")
		    		nui.get("biz.applyModeType").setEnabled(false);
					//证件信息、评级、担保方式
					$("#guarantyType").css("display","none");
					nui.get("tbBizAmountApply.guarantyType").setValue('');
					nui.get("tbBizAmountApply.guarantyType").hide();
					$("#certt").css("display","none");
					nui.get("party.certType").hide(false);
					$("#certn").css("display","none");
					nui.get("party.certNum").hide();
					$("#credlevel").css("display","none");
					nui.get("party.creditLevel").hide();
					nui.get('table1').refreshTable();
					
					loanType(0);//贷款形式
		    		greenLoan(0);//是否绿色贷款
		    	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	//业务性质的change事件
	function changeBizType(e){
		if(e.value == "01"){
			nui.get("biz.isBankTeamLoan").setValue("");
			nui.get("biz.applyModeType").setValue("");
			nui.get("biz.applyModeType").setData(getDictData("XD_SXYW0003","str","01"));//向控件设置业务字典
			nui.get("biz.applyModeType").setValue("01");
			nui.get("biz.applyModeType").setEnabled(false);
			
			//单笔显示业务品种
			$("#product").css("display","block");
			nui.get("biz.productType").show();
			nui.get("biz.productType").setValue('');
			nui.get("biz.productType").setText('');
			bankTeam(1);
		}else if(e.value=="02"){
			nui.get("biz.isBankTeamLoan").setValue("");
			nui.get("biz.applyModeType").setData(getDictData("XD_SXYW0003","str","01"));//向控件设置业务字典
			nui.get("biz.applyModeType").setValue("01");
			nui.get("biz.applyModeType").setEnabled(false);
			//单笔显示业务品种
			$("#product").css("display","none");
			nui.get("biz.productType").hide();
			nui.get("biz.productType").setValue('');
			nui.get("biz.productType").setText('');
			bankTeam(0);
			risk(0);
		}else if(e.value=="04"){
			nui.get("biz.isBankTeamLoan").setValue("");
			nui.get("biz.applyModeType").setValue("01");
			nui.get("biz.applyModeType").setData(getDictData("XD_SXYW0003","str","01"));//向控件设置业务字典
			nui.get("biz.applyModeType").setEnabled(false);
			
			//个人贷款显示业务品种
			$("#product").css("display","block");
			nui.get("biz.productType").show();
			bankTeam(0);
		}else if(e.value=="07"){
			nui.get("biz.isBankTeamLoan").setValue("");
			nui.get("biz.applyModeType").setValue("");
			nui.get("biz.applyModeType").setData(getDictData("XD_SXYW0003","str","02"));//向控件设置业务字典
			nui.get("biz.applyModeType").setValue("02");
			nui.get("biz.applyModeType").setEnabled(false);
			//低风险显示业务品种
			$("#product").css("display","block");
			nui.get("biz.productType").show();
			nui.get("biz.productType").setValue('');
			nui.get("biz.productType").setText('');
			if(partyTypeCd=='01'){//对公的客户有团字段
				bankTeam(1);
			}		
		}
	}
	//选择申报模式
	function changeModeType(e){
		//alert(e.value);
		if(e.value == "02"){
			risk(1);
		
		}else if(e.value=="01"){
			risk(0);
		
		}
	}
	
	//是否绿色贷款的显示与隐藏 
	function greenLoan(x){
		if(x == "0"){
			$("#greenLoan").css("display","none");
			if (nui.get("biz.isGreenLoan"))
				nui.get("biz.isGreenLoan").setVisible(false);
				nui.get("biz.isGreenLoan").setValue("");
			nui.get('table1').refreshTable();
		}else{
			$("#bankTeam").css("display","block");
			if (nui.get("biz.isGreenLoan"))
				nui.get("biz.isGreenLoan").setVisible(true);
				nui.get("biz.isGreenLoan").setValue("0");
			nui.get('table1').refreshTable();
		}
	}
	
	//贷款形式的显示与隐藏 
	function loanType(x){
		if(x == "0"){
			$("#loanType").css("display","none");
			if (nui.get("biz.loanType"))
				nui.get("biz.loanType").setVisible(false);
				nui.get("biz.loanType").setValue("");
			nui.get('table1').refreshTable();
		}else{
			$("#loanType").css("display","block");
			if (nui.get("biz.loanType"))
				nui.get("biz.loanType").setVisible(true);
				nui.get("biz.loanType").setValue("0");
			nui.get('table1').refreshTable();
		}
	}
	
	//是否银团的显示与隐藏 
	function bankTeam(x){
		if(x == "0"){
			$("#bankTeam").css("display","none");
			if (nui.get("biz.isBankTeamLoan"))
				nui.get("biz.isBankTeamLoan").setVisible(false);
				nui.get("biz.isBankTeamLoan").setValue("");
			nui.get('table1').refreshTable();
		}else{
			$("#bankTeam").css("display","block");
			if (nui.get("biz.isBankTeamLoan"))
				nui.get("biz.isBankTeamLoan").setVisible(true);
				nui.get("biz.isBankTeamLoan").setValue("0");
			nui.get('table1').refreshTable();
		}
	}
	//低类型的显示与隐藏
	function risk(x){
		if(x == "0"){
			$("#lowType").css("display","none");
			if (nui.get("biz.lowRiskBizType"))
				nui.get("biz.lowRiskBizType").hide();
				nui.get("biz.lowRiskBizType").setValue();
			    nui.get('table1').refreshTable();
			
		}else{
			$("#lowType").css("display","block");
			if (nui.get("biz.lowRiskBizType"))
				nui.get("biz.lowRiskBizType").setVisible(true);
			nui.get('table1').refreshTable();
			$("input[type='checkbox']").each(function(){
				var v1 = $(this).attr("value");
				if(v1 == "10" || v1 == "20" || v1 == "30"){
					$(this).remove();
					$("label[for='"+$(this).attr("id")+"']").attr("style","font-size: medium;");
				}
			});
		}
		
	}
	//发起流程
	function checkInfo(){
		create();
	}
	
	var batchId;		//业务预申请影像ID
	var bizApplyId;		//传入面谈面签的主键编号
    var conBatchId;		//合同预申请影像ID
	
	//创建流程
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        } 
		
		var bizType = nui.get("biz.bizType").getValue();
		var isBankTeamLoan = nui.get("biz.isBankTeamLoan").getValue();
		var applyModeType = nui.get("biz.applyModeType").getValue();
		
		var ff ;
		var json1 = nui.encode({productType:nui.get("biz.productType").value,guatype:nui.get("tbBizAmountApply.guarantyType").value});
   		$.ajax({
        url: "com.bos.bizInfo.bizInfo.checkApplyType.biz.ext",
        type: 'POST',
        data: json1,
        cache: false,
        async:false,
        contentType:'text/json',
        success: function (text) {
		        if(text.flag == "1"){
		        		nui.alert("个人住房按揭贷款,个人商用房按揭贷款担保方式必须存在抵押，不存在信用,请检查"); 
		        		ff="1";
		        }else if(text.flag == "2"){
		        		nui.alert("公积金住房委托贷款担保方式只有保证+抵押或者抵押方式,请检查");
		        		ff="1";
		        }
	        }
        });
		if(ff!="1"){
		 	debugger;
			nui.get("btnCreate").setEnabled(false);
			//贴现和单位委托贷款 只能做低风险业务
	   	    if(bizType == '01'  && applyModeType=='01'){
	   	    	if(nui.get("biz.productType").getValue()=='01006001'|| nui.get("biz.productType").getValue()=='01006002' 
	   	    	|| nui.get("biz.productType").getValue()=='02005010'//富民委托贷款
	   	    	|| nui.get("biz.productType").getValue()=='01006010' //村镇银行贴现产品
	   	    	){
	   	    		nui.alert("贴现产品只能申请低风险业务");
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
	   	    	}
	   	    }
	   	    //单笔单批只能做小微条线业务(产品树去控制 不在后台校验了)
	       /*   if(bizType == '01' && applyModeType =='01'){
	   	    	var json2 = {"productCd":nui.get("biz.productType").getValue()};
	   	    	var msg = exeRule("RBIZ_0074","1",json2);
	   	    	if(null != msg && '' != msg){
	   	    		nui.alert(msg);
	   	    		nui.get("btnCreate").setEnabled(true);
	   	    		return;
	   	    	}
	   	    } */
	   	    var json = {"partyId":nui.get("party.partyId").getValue()};
   	    	var msg = exeRule("XFE_0002","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
		   	}
			if(bizType == '02' || bizType == '03'){
				//规则校验：客户有在途或已生效业务
		   	    msg = exeRule("RBIZ_0001","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    }
		   	    msg = exeRule("RBIZ_0002","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    } 
		   	    //规则校验：客户有冻结的综合授信，不允许发起综合授信
		   	    msg = exeRule("RBIZ_0048","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    }
		   	    //成员有在途或生效综合授信或集团业务
		   	    msg = exeRule("RBIZ_0042","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    } 
		   	    //存在在途的单笔业务申请，不能进行综合授信申请
		   	    msg = exeRule("RBIZ_0044","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    }
			}else if(bizType == '01'|| bizType == '04'){
				msg = exeRule("RBIZ_0085","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    }
			}
			//有生效综合授信时不允许申请非低、银团业务的单笔
			if(bizType == '01' && isBankTeamLoan=='0' && applyModeType=='01'){
		   	    msg = exeRule("RBIZ_0002","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert("已有生效综合授信业务，只能申请低风险或银团单笔业务");
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    }
			}
	   	    //集团成员只能发起低或银团贷款
	   	    if(bizType == '02' ||(bizType == '01' && isBankTeamLoan=='0' && applyModeType=='01')
	   	    		||(bizType == '04'  && applyModeType=='01')){
	   	    	msg = exeRule("RBIZ_0019","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		nui.get("btnCreate").setEnabled(true);
			   		return;
		   	    } 
	   	    }
	   	    //单笔或低风险下 银承通和银行承兑汇票业务只能做其一
	   	    /* if(bizType == '01'||bizType=='07'){
	   	    	var productType = nui.get("biz.productType").getValue();
	   	    	if(productType == '01008001'){
	   	    		productCd = '01008002';
	   	    		var json = {"partyId":nui.get("party.partyId").getValue(),"productType":productCd};
	   	    		msg = exeRule("RBIZ_0070","1",json);
	   	    		if(msg){
	   	    			alert("此客户已做过银承通业务，不可再做银行承兑汇票业务");
	   	    			nui.get("btnCreate").setEnabled(true);
	   	    			return;
	   	    		}
	   	    	}if(productType =='01008002'){
	   	    		productCd = '01008001';
	   	    		var json = {"partyId":nui.get("party.partyId").getValue(),"productType":productCd};
	   	    		msg = exeRule("RBIZ_0070","1",json);
	   	    		if(msg){
	   	    			alert("此客户已做过银行承兑汇票业务，不可再做银承通业务");
	   	    			nui.get("btnCreate").setEnabled(true);
	   	    			return;
	   	    		}
	   	    	}
	   	    } */
	   	    
	        var o = form.getData();
	        o.biz.partyId=nui.get("party.partyId").getValue();
	        o.biz.bizHappenNature = bizHappenNature;//解决业务发生性质为空的bug
	        o.biz.applyModeType = nui.get("biz.applyModeType").getValue();//申报模式
	        if((nui.get("biz.productType").getValue()!='01006001'||nui.get("biz.productType").getValue()!='01006002'
	        || nui.get("biz.productType").getValue()!='01006010' //村镇银行贴现产品
	        ) && "03"!=bizType){//贴现无担保方式
	        	 o.biz.guarantyType = nui.get("tbBizAmountApply.guarantyType").getValue();//担保方式
	        }
	        //将业务性质的值赋给业务性质标志
	        o.biz.bizTypeFlag = o.biz.bizType;
	        //如果选择的是低风险业务 也是走单笔的流程
	        if(o.biz.bizType=='07'){
	        	if(partyTypeCd=='01'){//对公的低风险 bizType为走01
	        		o.biz.bizType='01';
	        	}else if(partyTypeCd='02'){//对私的低风险业务bizType走04
	        		o.biz.bizType='04';
	        	}
	        }
	
	        var json = nui.encode(o);
	        var bizNum;			//传入面谈面签的业务编号
	        $.ajax({
	            url: "com.bos.bizApply.bizApply.createBizProcess.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg !=null){
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            		nui.get("btnCreate").setEnabled(true);
	            		return;
	            	}
	            	bizNum=text.tbBizApply.bizNum;
	            	bizType=text.tbBizApply.bizType;
	            //	git.go(nui.context+"/biz/biz_pro_detail_info/pro_biz_tree.jsp?applyId="+text.eval.applyId+"&ifManager=2&proFlag=null&proResultId="+text.result.processInstId+"&proResultName="+text.result.processDefName,parent);
	            	git.go(nui.context+"/biz/biz_info/biz_tree.jsp?applyId="+text.tbBizApply.applyId+"&processInstId="+text.processInstId+"&proFlag=1"+"&bizType="+bizType,parent);
	            	
	            	
	            	 //钟辉    面谈面签业务预申请新增代码
			        var partyTypeCd=nui.get("party.partyTypeCd").getValue();			//对公还是小贷
			        var partyNum=nui.get("party.partyNum").getValue();
			        
			        if(null!=batchId){			//业务申请的批次号不为空说明是业务预申请
			        	var imageControlType;
			        	var imageTypeId;
			        	if(partyTypeCd=="01"){					//对公的业务申请
			        		imageControlType="SQZLWD_DOC";
			        		imageTypeId="10176";
			        	}else if(partyTypeCd=="02"){			//小贷业务申请
			        		imageControlType="X_SQZLWD_DOC";
			        		imageTypeId="10365";
			        	}
			        	
			        	if(imageControlType!=""){
			        		var json = nui.encode({"imageTypeId":imageTypeId,"barCode":batchId,"partyNum":partyNum,"businessNumber":bizNum,"bizApplyId":bizApplyId,"conBatchId":conBatchId});
			        		$.ajax({
					            url: "com.bos.pub.image.linkBizImage.biz.ext",
					            type: 'POST',
					            data: json,
					            cache: false,
					            contentType:'text/json',
					            success: function (text) {
					            	if(text.msg !=null){
					            		return;
					            	}
					            },
					            error: function (jqXHR, textStatus, errorThrown) {
					                nui.alert(jqXHR.responseText);
					            }
					        });
			        	}
			        }
			        //面谈面签结束
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        }); 
		}
	}
	
	//产品树
	function selectProduct(e) {
		var bizType = nui.get("biz.bizType").getValue();
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp?partyId="+"<%=request.getParameter("partyId")%>"
            				 +"&bizType="+bizType+"&partyTypeCd="+partyTypeCd,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);
                        btnEdit.setText(data.productName);
                        var productType = data.productCd;
                        //对私 二手房按揭贷款、个人汽车消费贷款、公积金贷款 (这3款产品需要选择是否有按揭合作商) 
                        if(productType == '02002003'||productType == '02003012'||productType == '02005001'
                           ||productType == '02002010'||productType=='02002011'){
                        	$("#busiPartner").css("display","block");
                        	nui.get("biz.busiPartner").show();
                        }else{
							$("#busiPartner").css("display","none");
							nui.get("biz.busiPartner").hide();                        
                        }
                        //贴现
                        if(productType=='01006001'||productType=='01006002'
                        ||productType=='01006010' //村镇银行贴现产品
                        ){//贴现没有担保方式
							$("#guarantyType").css("display","none");
							nui.get("tbBizAmountApply.guarantyType").setValue('');
							nui.get("tbBizAmountApply.guarantyType").hide();
							nui.get("table1").refreshTable();
                        }else{
                        	$("#guarantyType").css("display","block");
                        	nui.get("tbBizAmountApply.guarantyType").setValue('');
							nui.get("tbBizAmountApply.guarantyType").show();
							nui.get("table1").refreshTable();
                        }
                        //只有抚州分行才能申请农户联保
                        <%-- if(productType=='02001010'){
							var orgcode = "<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" ;
							if(startWith(orgcode,"77")!='1' ){
								btnEdit.setValue("");
                				btnEdit.setText("");
								nui.alert("只有抚州分行才能申办农户小额联保贷款");
								return;
							}
                        } --%>
                        //本行员工才能选员工保证贷款
                        if(productType=='02003003'){
                        	var json = {"partyId":nui.get("party.partyId").getValue()};
					   	    var msg = exeRule("RBIZ_0060","1",json);
					   	    if(null != msg && '' != msg){
					   	    	btnEdit.setValue("");
	            				btnEdit.setText("");
								nui.alert(msg);
								return;
					   	    }
                        }
                        if(partyTypeCd=='01'){//业务申请时 不需要信用等级必输
	                        if(productType=='01013001' || productType=='01013010' ||productType=='01006001'||productType=='01006002'
	                           ||productType=='01007001'||productType=='01007012'
	                           ||productType=='01006010' //村镇银行贴现产品
	                           ){
								nui.get("party.creditLevel").setRequired(false);
								nui.get("party.creditLevel").validate();
	                        }else{
								nui.get("party.creditLevel").setRequired(true);
								nui.get("party.creditLevel").validate();
	                        }
                        }
                        //信用等级赋值
                        if(partyTypeCd!='01'){//单笔对公不需要再次赋值
                        	 if(productType.indexOf('02002')!=-1||productType.indexOf('02003')!=-1||productType.indexOf('02004')!=-1||
                        	 	productType.indexOf('02005010')!=-1||productType.indexOf('02005001')!=-1||productType.indexOf('02005003')!=-1){//消费类--bug13402 公积金显示消费
                        		getRatingLevel(partyId,'1');
	                        }else{//经营类
	                        	getRatingLevel(partyId,'2');
	                        }
                        }
                        if(productType.substring(0,5) == "01008"){//选择银承产品后 担保方式默认勾选保证金
                        	nui.get("tbBizAmountApply.guarantyType").setValue("05");//保证金
                        }
                        form.validate();//产品选择后 校验保证金方式
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	//业务预申请按钮点击后
	function applyPre(e){
		nui.open({
            url: nui.context + "/biz/biz_apply/biz_apply_pre.jsp?partyId="+"<%=request.getParameter("partyId")%>",
            title: "业务预申请列表",
            width: 800,
            height: 450,
            ondestroy: function (action) {
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
		            var data = iframe.contentWindow.getData();
		            data = nui.clone(data);
		            var partyNum=data.PARTY_NUM;		//客户编号
		            var partyName=data.PARTY_NAME;	//客户名称
		            var bizType=data.BIZ_TYPE;		//业务性质
		            
		            
		            bizApplyId=data.BIZ_APPLY_ID;	//主键ID
		            
		            var bizBatchId=data.BIZ_BATCH_ID;	//业务申请批次号
		            
		            conBatchId=data.CON_BATCH_ID;	//合同申请批次号
		            
		            nui.get("party.partyNum").setValue(partyNum);
		            nui.get("party.partyName").setValue(partyName);
		            nui.get("biz.bizType").setValue(bizType);
		            changeBizType1(bizType);
		            nui.get("biz.bizType").setEnabled(false);
		            
		            batchId=bizBatchId;
		            
		            
		        }
        	}
        }); 
	}
	
	
	//由于该方法在业务预申请会被调用2次,会导致JS效果执行出问题,单独抽出来重写
	function changeBizType1(e){
		if(e == "01"){
			nui.get("biz.isBankTeamLoan").setValue("");
			nui.get("biz.applyModeType").setValue("");
			nui.get("biz.applyModeType").setData(getDictData("XD_SXYW0003","str","01,02"));//向控件设置业务字典
			nui.get("biz.applyModeType").setEnabled(true);
			bankTeam(1);
			//单笔显示业务品种
			 $("#product").css("display","block");
			nui.get("biz.productType").show(); 
		}else if(e=="02"){
			nui.get("biz.isBankTeamLoan").setValue("");
			nui.get("biz.applyModeType").setData(getDictData("XD_SXYW0003","str","01"));//向控件设置业务字典
			nui.get("biz.applyModeType").setValue("01");
			nui.get("biz.applyModeType").setEnabled(false);
			bankTeam(0);
			risk(0);
			//单笔显示业务品种
			$("#product").css("display","none");
			nui.get("biz.productType").hide();
			nui.get("biz.productType").setValue('');
			nui.get("biz.productType").setText('');
		}
	}
	
	//获取评级结果
	function getRatingLevel(partyId,ratingType){
		var json = nui.encode({"partyId":partyId,"ratingType":ratingType});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.getPartyCreditLevel.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg !=null){
            		return;
            	}
            	nui.get("party.creditLevel").setValue('');//防止更改贷种后取不到评级结果却仍然显示以前取出的评级
            	if(text.tbIrmInternalRatingApply){
            		nui.get("party.creditLevel").setValue(text.tbIrmInternalRatingApply.generalAdjustRatingCd);
            		nui.get("party.creditLevel").validate();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	function startWith(src,str){
		if(str==null || str==''|| src==null || src==''){
			return -1;
		}
		if(src.substr(0,str.length)==str){
			return 1;
		}else{
			return -1;
		}
	}
</script>
</body>
</html>