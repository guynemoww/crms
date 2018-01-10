<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 16:45:29
  - Description:
-->
<head>
<title>协议附属信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" name ="tbConAttachedInfo.attached"/>
			<fieldset>
				<legend>
			    	<span>仲裁方式</span>
			    </legend>
			
				<div class="nui-dynpanel" columns="4" id="table1">
					<label class="nui-form-label">争议解决方式：</label>
					<input id="tbConAttachedInfo.arbitrateType"  name="tbConAttachedInfo.arbitrateType" data="data" valueField="dictID"  required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0209" emptyText="--请选择--"  onvaluechanged="arbiTypeChange"/>
				</div>
				<div id="zcdiv" class="nui-dynpanel" columns="4">
					<label class="nui-form-label">仲裁委员会名称：</label>	
					<input id="tbConAttachedInfo.arbitrateName" class="nui-textbox nui-form-input" name="tbConAttachedInfo.arbitrateName" vtype="String;maxLength:85;"required="true"/>	
						
					<label class="nui-form-label">仲裁委员会地点：</label>
					<input id="tbConAttachedInfo.arbitrateAddress" class="nui-textbox nui-form-input" name="tbConAttachedInfo.arbitrateAddress" vtype="String;maxLength:85;"required="true"/>
				</div>
			
				<div id="otherediv" class="nui-dynpanel">
					<label class="nui-form-label">其它方式：</label>	
					<input id="tbConAttachedInfo.other" class="nui-textbox nui-form-input" name="tbConAttachedInfo.other" vtype="String;maxLength:85;"required="true" />	
				</div>
			</fieldset>
			<fieldset>
				<legend>
			    	<span>协议签署</span>
			    </legend>
				<div class="nui-dynpanel" columns="4" id="table2">
					<label class="nui-form-label">共计份数：</label>
					<input id="tbConAttachedInfo.totalCount" class="nui-textbox nui-form-input" required="true"  name="tbConAttachedInfo.totalCount" vtype="maxLength:8;int"/>
				</div>
			</fieldset>
			<fieldset>
				<legend>
			    	<span>补充条款</span>
			    </legend>
				 <div class="nui-dynpanel" columns="4" id="table3">
				 	<label class="nui-form-label">补充条款：</label>
				 	<input id="tbConAttachedInfo.addClause" class="nui-textarea nui-form-input" name="tbConAttachedInfo.addClause"  colspan="3"  vtype="String;maxLength:85"/>
				 </div>
			<fieldset>	
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="con_fs_xy_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>


</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//协议申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	//初始化“仲裁”模块和“其他”模块隐藏
	$("#zcdiv").css("display","none");
	$("#otherediv").css("display","none");
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":contractId});
		$.ajax({
            url: "com.bos.conInfo.conInfoSxxy.getConAttachedInfoByContarctId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_fs_xy_save").hide();
			form.setEnabled(false);
		}
	}
	
	function save(){
		//仲裁方式处理
		var arbivar = nui.get("tbConAttachedInfo.arbitrateType").getValue();
		if('02'==arbivar){
			nui.get("tbConAttachedInfo.other").setValue("");
		}else if('03'==arbivar){
			nui.get("tbConAttachedInfo.arbitrateName").setValue("");
			nui.get("tbConAttachedInfo.arbitrateAddress").setValue("");
		}else{
			nui.get("tbConAttachedInfo.other").setValue("");
			nui.get("tbConAttachedInfo.arbitrateName").setValue("");
			nui.get("tbConAttachedInfo.arbitrateAddress").setValue("");
		}
		
		//仲裁方式处理结束
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_fs_xy_save").setEnabled(false);
		var o = form.getData();
		o.tbConAttachedInfo.contractId=contractId;
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.conInfo.conInfoSxxy.saveConAttachedInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_fs_xy_save").setEnabled(true);
        	}else{
        	alert("保存成功！");
        	nui.get("con_fs_xy_save").setEnabled(true);
        	}
        }});
	}
	//仲裁方式变更时触发
	function arbiTypeChange(e){
		if('02'==e.value){//如果选仲裁，显示仲裁模块,并将“其他”项隐藏
			$("#zcdiv").css("display","block");
			$("#otherediv").css("display","none");
		}else if('03'==e.value){//如果选其他，显示其他模块,并将“仲裁”隐藏
			$("#zcdiv").css("display","none");
			$("#otherediv").css("display","block");
		}else{//选诉讼或请选择，将“其他”及“仲裁”项隐藏
			$("#otherediv").css("display","none");
			$("#zcdiv").css("display","none");
		}
	}
	
	//第三方类型
	function dsflx(){
		var dsflx = nui.get("tbConAttachedInfo.cType").getValue();
		if(dsflx != '' && dsflx!=null){
			nui.get("tbConAttachedInfo.cHoldCount").setRequired(true);
		}else{
			nui.get("tbConAttachedInfo.cHoldCount").setRequired(false);
		}
		nui.get("tbConAttachedInfo.cHoldCount").validate();
	}
</script>
</body>
</html>