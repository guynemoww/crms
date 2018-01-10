<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:33:14
  - Description:
-->
<head>
<title>客户额度信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="partyLimit.limitId" class="nui-hidden nui-form-input" name="partyLimit.limitId"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
				
			<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			
			<label class="nui-form-label">证件类型：</label>
			<input id="zjlx" class="nui-text nui-form-input" name="zjlx"/>
			
			<label class="nui-form-label">证件号码：</label>
			<input id="corpParty.orgRegisterCd" class="nui-text nui-form-input" name="corpParty.orgRegisterCd"/>
			
			<label class="nui-form-label">额度编号：</label>	
			<input id="partyLimit.creditNum" class="nui-text nui-form-input" name="partyLimit.creditNum"/>	
						
			<label class="nui-form-label">额度期限(月)：</label>
			<input id="partyLimit.creditTerm" name="partyLimit.creditTerm" class="nui-textbox nui-form-input" vtype="int;maxLength:20;range:1,12" required="true"/>
		 			
			<label class="nui-form-label">额度币种：</label>
			<input id="partyLimit.currencyCd" name="partyLimit.currencyCd"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="CD000001"  />

			<label class="nui-form-label">有效净资产法测算额度：</label>
			<input id="partyLimit.csAmt" name="partyLimit.csAmt" class="nui-text nui-form-input" dataType="currency"/>

			<label class="nui-form-label">申请额度：</label>
			<input id="partyLimit.applyAmt" name="partyLimit.applyAmt" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1,1000000000000" dataType="currency" required="true"/>
 			
 			<label class="nui-form-label">经办日期：</label>
			<input id="partyLimit.applyDate" name="partyLimit.applyDate" class="nui-text nui-form-input"   required="true" />
			
			<label class="nui-form-label">经办人：</label>
			<input id="partyLimit.userNum" name="partyLimit.userNum" class="nui-text nui-form-input" vtype="float;maxLength:20"  dictTypeId="user"/>
			 			
 			<label class="nui-form-label">经办机构：</label>
			<input id="partyLimit.orgNum" name="partyLimit.orgNum" class="nui-text nui-form-input" vtype="float;maxLength:20"  dictTypeId="org"/>

 		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="crd_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var limitId ="<%=request.getParameter("limitId")%>";//业务申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"limitId":limitId});
		$.ajax({
            url: "com.bos.crdApply.crdApply.getPartyCrdByLimitId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	if('' == o.partyLimit.applyAmt || null ==o.partyLimit.applyAmt){
            		o.partyLimit.applyAmt = o.partyLimit.csAmt;
            	}
            	form.setData(o);
            	nui.get("partyLimit.currencyCd").setValue("CNY");
            	nui.get("zjlx").setValue('组织机构代码');
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("crd_info_save").hide();
			form.setEnabled(false);
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("crd_info_save").setEnabled(false);
		var o = form.getData();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.crdApply.crdApply.savePartyCrd.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("crd_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
        	nui.get("crd_info_save").setEnabled(true);
        }});
	}
</script>
</body>
</html>