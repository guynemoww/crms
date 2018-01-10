<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:33:14
  - Description:
-->
<head>
<title>担保额度信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbCrdThirdPartyLimit.thirdLimitId" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.thirdLimitId"/>
		<input id="tbCrdThirdPartyLimit.limitId" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.limitId"/>
		<input id="tbCrdThirdPartyLimit.itemType" class="nui-hidden nui-form-input" name="tbCrdThirdPartyLimit.itemType"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
				
			<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			
			<label class="nui-form-label">币种：</label>
			<input id="tbCrdThirdPartyLimit.currencyCd" name="tbCrdThirdPartyLimit.currencyCd"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="CD000001"  />

			<label class="nui-form-label">担保放大倍数：</label>
			<input id="tbCrdThirdPartyLimit.amplifyInd" name="tbCrdThirdPartyLimit.amplifyInd" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1,1000000" required="true" />
			
			<label class="nui-form-label">担保额度止期：</label>
			<input id="tbCrdThirdPartyLimit.endDate" name="tbCrdThirdPartyLimit.endDate" class="nui-datepicker nui-form-input" required="true" />
			
			<!-- 
			<label class="nui-form-label">担保额度期限（月）：</label>
			<input id="tbCrdThirdPartyLimit.guarantTerm" name="tbCrdThirdPartyLimit.guarantTerm" class="nui-textbox nui-form-input" vtype="int;maxLength:4;range:1,12" required="true" />
			 -->
			 
			<label class="nui-form-label">担保机构实收资本：</label>
			<input id="tbCrdThirdPartyLimit.guarantOrgReal" name="tbCrdThirdPartyLimit.guarantOrgReal" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1,1000000000000" dataType="currency1" required="true" />
			
			<label class="nui-form-label">主办行：</label>
			<input id="tbCrdThirdPartyLimit.mainOrgId" name="tbCrdThirdPartyLimit.mainOrgId" class="nui-text nui-form-input"   dictTypeId="org" enabled="false"/>
 			
 			<label class="nui-form-label">经办日期：</label>
			<input id="tbCrdThirdPartyLimit.dealDate" name="tbCrdThirdPartyLimit.dealDate" class="nui-text nui-form-input"  required="true" value="<%=GitUtil.getBusiDate()%>" />
			
			<label class="nui-form-label">备注：</label>
			<input name="tbCrdThirdPartyLimit.remark" id="tbCrdThirdPartyLimit.remark"  class="nui-textarea nui-form-input" />
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
	var partyId = "<%=request.getParameter("partyId") %>";//客户ID
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"limitId":limitId,"partyId":partyId});
		$.ajax({
            url: "com.bos.crdApply.crdApply.getThirdPartyCrdByLimitId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	nui.get("tbCrdThirdPartyLimit.currencyCd").setValue('CNY');
            	nui.get("tbCrdThirdPartyLimit.itemType").setValue('8');
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
        var begin = nui.get("tbCrdThirdPartyLimit.dealDate").getValue();
		var end = nui.get("tbCrdThirdPartyLimit.endDate").getValue();
		if(begin > end){
			nui.alert("【担保额度止期】必须大于【经办日期】！");
			return;
		}
        nui.get("crd_info_save").setEnabled(false);
		var o = form.getData();
		o.tbCrdThirdPartyLimit.partyId = partyId;
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.crdApply.crdApply.saveThirdPartyCrd.biz.ext",
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
        	initPage();
        }});
	}
</script>
</body>
</html>