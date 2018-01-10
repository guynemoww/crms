<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>贴息信息页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="tbBizTx.txId" name="tbBizTx.txId" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.txjg1" name="tbBizTx.txjg1" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.txjg2" name="tbBizTx.txjg2" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.txjg3" name="tbBizTx.txjg3" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.txjg4" name="tbBizTx.txjg4" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.zhlx1" name="tbBizTx.zhlx1" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.zhlx2" name="tbBizTx.zhlx2" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.zhlx3" name="tbBizTx.zhlx3" class="nui-hidden nui-form-input" />
	<input id="tbBizTx.zhlx4" name="tbBizTx.zhlx4" class="nui-hidden nui-form-input" />
	<div class="nui-dynpanel" columns="4">
		<label>贴息方式：</label>
		<input id="tbBizTx.txfs" name="tbBizTx.txfs" class="nui-dictcombobox nui-form-input"
			 onvaluechanged="selectFs" required="true" dictTypeId="XD_TXFS0001"/>
	</div>	
	<div id="txbldv" class="nui-dynpanel" columns="4">
		<label>贴息比例(%)：</label>
		<input id="tbBizTx.txbl" name="tbBizTx.txbl" class="nui-textbox nui-form-input" required="true"  vtype="negative;maxLength:11"/>
	</div>	
	<div id="gdjedv" class="nui-dynpanel" columns="4">	
		<label>固定金额：</label>
		<input id="tbBizTx.gdje" name="tbBizTx.gdje" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>	
	<div id="xedv" class="nui-dynpanel" columns="4">					
		<label>限额：</label>
		<input id="tbBizTx.xe" name="tbBizTx.xe" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>	
	<div class="nui-dynpanel" columns="4">					
		<label>期限：</label>
		<div style="width:80%">
		<input name="tbBizTx.qx" style="width:60%;float:left" id="tbBizTx.qx" required="true" vtype="int;maxLength:4" class="nui-textbox nui-form-input"/>
		<input name="tbBizTx.qxdw" id="tbBizTx.qxdw" style="width:40%;float:left"  required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" value="04" enabled="false"/>
		</div>
	</div>	
	<div class="nui-dynpanel" columns="4">		
		<label>贴息主体一：</label>
		<input name="tbBizTx.txzt1" class="nui-textbox nui-form-input"  id="tbBizTx.txzt1"  required="true" />																
	
		<label>贴息账户一：</label>
		<input name="tbBizTx.txzh1" class="nui-textbox nui-form-input"  id="tbBizTx.txzh1"  required="true" />																
		
		<label>贴息主体二：</label>
		<input name="tbBizTx.txzt2" class="nui-textbox nui-form-input"  id="tbBizTx.txzt2" />																
	
		<label>贴息账户二：</label>
		<input name="tbBizTx.txzh2" class="nui-textbox nui-form-input"  id="tbBizTx.txzh2" />																
		
		<label>贴息主体三：</label>
		<input name="tbBizTx.txzt3" class="nui-textbox nui-form-input"  id="tbBizTx.txzt3" />																
	
		<label>贴息账户三：</label>
		<input name="tbBizTx.txzh3" class="nui-textbox nui-form-input"  id="tbBizTx.txzh3" />																
		
		<label>贴息主体四：</label>
		<input name="tbBizTx.txzt4" class="nui-textbox nui-form-input"  id="tbBizTx.txzt4" />																
	
		<label>贴息账户四：</label>
		<input name="tbBizTx.txzh4" class="nui-textbox nui-form-input"  id="tbBizTx.txzh4" />																
		
	</div>	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var applyId = "<%=request.getParameter("applyId") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var retMsg = "0000";
	initPage();
	function initPage(){
        var json = nui.encode({"applyId":applyId});
		$.ajax({
            url: "com.bos.bizProductDetail.bizTx.getXwTx.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	//期限默认月
				nui.get("tbBizTx.qxdw").setValue('04');
            	selectFs(1);
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if(proFlag=="0" ||proFlag=="-1" ){
			nui.get("btnCreate").hide();
			form.setEnabled(false);
		}
	}
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
        nui.get("btnCreate").setEnabled(false);
        var o = form.getData();
        
        //贴息方式选择按固定金额贴息时，只能选择一个贴息主体
        //贴息主体和贴息账户校验
        var txzt1 = o.tbBizTx.txzt1;
        var txzh1 = o.tbBizTx.txzh1;
        checkAcc(txzh1,txzt1,'1');
        if(retMsg!='0000'){
        	nui.alert("贴息主体一："+retMsg);
        	nui.get("btnCreate").setEnabled(true);
         	return;
        }
        
        var txzt2 = o.tbBizTx.txzt2;
        var txzh2 = o.tbBizTx.txzh2;
        if(txzt2!=null && txzt2!='' && txzt2!='null' &&
        	txzh2!=null && txzh2!='' && txzh2!='null' ){
        	if(o.tbBizTx.txfs=='02'){
        		nui.alert("贴息方式选择按固定金额贴息时，只能选择一个贴息主体");
	        	nui.get("btnCreate").setEnabled(true);
	         	return;
        	}
	    	checkAcc(txzh2,txzt2,'2');
	        if(retMsg!='0000'){
	        	nui.alert("贴息主体二："+retMsg);
	        	nui.get("btnCreate").setEnabled(true);
	         	return;
	        }
        }else{
        	if((txzt2==null || txzt2=='')&&(txzh2==null || txzh2=='')){
	        	
	        }else{
	        	nui.alert("贴息主体二信息不完整");
		        nui.get("btnCreate").setEnabled(true);
		        return;
	        }
        }
        
        var txzt3 = o.tbBizTx.txzt3;
        var txzh3 = o.tbBizTx.txzh3;
        if(txzt3!=null && txzt3!='' && txzt3!='null' &&
        	txzh3!=null && txzh3!='' && txzh3!='null' ){
        	if(o.tbBizTx.txfs=='02'){
        		nui.alert("贴息方式选择按固定金额贴息时，只能选择一个贴息主体");
	        	nui.get("btnCreate").setEnabled(true);
	         	return;
        	}
        	checkAcc(txzh3,txzt3,'3');
	        if(retMsg!='0000'){
	        	nui.alert("贴息主体三："+retMsg);
	        	nui.get("btnCreate").setEnabled(true);
	         	return;
	        }
        }else{
        	if((txzt3==null || txzt3=='')&&(txzh3==null || txzh3=='')){
	        	
	        }else{
	        	nui.alert("贴息主体三信息不完整");
		        nui.get("btnCreate").setEnabled(true);
		        return;
	        }
        }
        var txzt4 = o.tbBizTx.txzt4;
        var txzh4 = o.tbBizTx.txzh4;
        if(txzt4!=null && txzt4!='' && txzt4!='null' &&
        	txzh4!=null && txzh4!='' && txzh4!='null' ){
        	if(o.tbBizTx.txfs=='02'){
        		nui.alert("贴息方式选择按固定金额贴息时，只能选择一个贴息主体");
	        	nui.get("btnCreate").setEnabled(true);
	         	return;
        	}
        	checkAcc(txzh4,txzt4,'4');
	        if(retMsg!='0000'){
	        	nui.alert("贴息主体四："+retMsg);
	        	nui.get("btnCreate").setEnabled(true);
	         	return;
	        }
        }else{
        	if((txzt4==null || txzt4=='')&&(txzh4==null || txzh4=='')){
	        	
	        }else{
	        	nui.alert("贴息主体四信息不完整");
		        nui.get("btnCreate").setEnabled(true);
		        return;
	        }
        }
        //在校验时给贴息机构赋值了所以要再取一次
        o = form.getData();
        //贴息方式处理---
        var txfs= nui.get("tbBizTx.txfs").getValue();
		if(txfs=="01"){//按比例贴息
			nui.get("tbBizTx.gdje").setValue('');
			nui.get("tbBizTx.xe").setValue('');
		}else if(txfs=="02"){//选择固定金额贴息
			nui.get("tbBizTx.txbl").setValue('');
			nui.get("tbBizTx.xe").setValue('');
		}else if(txfs=="03"){//选择限额+比例
			nui.get("tbBizTx.gdje").setValue('');
		}
		//贴息方式处理---end
        
        o.tbBizTx.applyId=applyId;
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizProductDetail.bizTx.saveBizTx.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	alert("保存成功!");
            	form.setData(o);
			}
        });
        nui.get("btnCreate").setEnabled(true);
	}
    //贴息方式选择按比例贴息，仅展示贴息比例信息项；
    //选择固定金额贴息，仅展示固定金额信息项；
    //选择限额+比例，展示贴息比例和限额两个信息项
    function selectFs(e){
		var txfs= nui.get("tbBizTx.txfs").getValue();
		if(txfs=="01"){//按比例贴息
			$("#txbldv").css("display","block");
			$("#gdjedv").css("display","none");
			$("#xedv").css("display","none");
		}else if(txfs=="02"){//选择固定金额贴息
			$("#txbldv").css("display","none");
			$("#gdjedv").css("display","block");
			$("#xedv").css("display","none");
		}else if(txfs=="03"){//选择限额+比例
			$("#txbldv").css("display","block");
			$("#gdjedv").css("display","none");
			$("#xedv").css("display","block");
		}else{//新增时页面初始化或选择空值
			$("#txbldv").css("display","none");
			$("#gdjedv").css("display","none");
			$("#xedv").css("display","none");
		}
	}
	
	//账户名账号校验
	function checkAcc(AcctNo,zhm,a){
		  retMsg = "0000";
		  var json=nui.encode({"acctInd":AcctNo});
		  $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAccNew.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        async: false,
	        success: function (text) {
	        	var message = text.msg;
	        	if(message != '查询成功'){
	        		retMsg = "贴息账号查询失败";
	        		return;
	        	}else{
	        		var cusName = text.queryAcc.cstNm;
		        	cusName = cusName.trim();
		        	
		        	if(cusName != zhm){
		        		retMsg= "账户名与账号不匹配!";
		        		return ;
		        	}
		        	//给机构赋值
		        	var orgid = text.queryAcc.rgonCd+text.queryAcc.branchId;
		        	if(a=='1'){
		        		nui.get("tbBizTx.txjg1").setValue(orgid);
		        	}else if(a=='2'){
		        		nui.get("tbBizTx.txjg2").setValue(orgid);
		        	}else if(a=='3'){
		        		nui.get("tbBizTx.txjg3").setValue(orgid);
		        	}else if(a=='4'){
		        		nui.get("tbBizTx.txjg4").setValue(orgid);
		        	}
		        	
		        	//账户类型
		        	var zhbs = text.queryAcc.storeCd;
		        	if(zhbs=='0'){
		        		zhbs = '12';
		        	}else if (zhbs == '4'){
		        		zhbs = '60';
		        	}else{
		        		retMsg="不支持的账户类型"+zhbs;
		        		return;
		        	}
		        	if(a=='1'){
		        		nui.get("tbBizTx.zhlx1").setValue(zhbs);
		        	}else if(a=='2'){
		        		nui.get("tbBizTx.zhlx2").setValue(zhbs);
		        	}else if(a=='3'){
		        		nui.get("tbBizTx.zhlx3").setValue(zhbs);
		        	}else if(a=='4'){
		        		nui.get("tbBizTx.zhlx4").setValue(zhbs);
		        	}
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
		        retMsg= "账号查询失败!";
	            return jqXHR.responseText;
	        }
		});
	}
</script>
</body>
</html>