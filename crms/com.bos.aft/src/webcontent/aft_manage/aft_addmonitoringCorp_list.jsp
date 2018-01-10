<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-26 15:57:52
  - Description:贷后重点客户的管理
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>移入贷后重点客户</title>
</head>
<body>
<div>移入贷后重点客户</div>

<div id="editForm" class="nui-dynpanel" columns="4" >
	<label>客户编号：</label>
	<input name="partyNum" class="nui-text nui-form-input" /> 
	<label>客户名称：</label>
	<input name="partyName" class="nui-text nui-form-input"/> 
	<label>组织机构代码：</label>
	<input name="orgNum" class="nui-text nui-form-input"/> 
	<label>所属机构：</label>
	<input name="orgName" dictTypeId="org"class="nui-text nui-form-input"/> 
	<label>客户类型：</label>
	<input class="nui-text nui-form-input" name="partyTypeCd" dictTypeId="XD_KHCD0219"/>
	<label>监控级别：</label>
	 <input id="monitoringLevelCd" name="monitoringLevelCd" onvaluechanged="onLevelValueChanged" required="true"
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0007" emptyText="--请选择--" />  
	<!--<input id="monitoringLevelCd" name="monitoringLevelCd" onvaluechanged="onLevelValueChanged" required="true"
		class="nui-combobox nui-form-input" textField="dictName" valueField="dictId" dataField="out" emptyText="--请选择--" /> -->
	<input id="partyId" class="nui-hidden nui-form-input" name="partyId" />
</div>
<div class="nui-toolbar" borderStyle="border:0;"
	style="text-align:right;padding-top:5px;margin-bottom:15px;">
	<label><a class="nui-button" iconCls="icon-ok" onclick="btnSave()" enabled="false" id="confirm" name="confirm">确定</a></label>
</div>


<script type="text/javascript">
		nui.parse();
		
		//git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。	
		var rows=<%=request.getParameter("rows") %>
		var editForm = new nui.Form("#editForm");
		editForm.setData(rows);
		/*init();
		function init(){
			 nui.ajax({
				url: "com.bos.aft.aft_manage.queryMonitoringLevelCd.biz.ext",
            	type: 'POST',
            	cache: false,
            	contentType:'text/json',
           		success: function (text) {
           			var o = nui.decode(text);
					nui.get("monitoringLevelCd").setData(o.out);
           		},
           		error: function (jqXHR, textStatus, errorThrown) {
                	nui.alert(jqXHR.responseText);
            		}
			
			});
		}*/
		function onLevelValueChanged(){
		  //  alert(nui.encode(nui.get("attentionTypeCd").getValue()));
			if(nui.get("monitoringLevelCd").getValue()==undefined||nui.get("monitoringLevelCd").getValue()==""){
				return;
			}else{
			nui.get("confirm").setEnabled("true");
			}
		}
		
		function btnSave(){
			var formData=editForm.getData();
			var json=nui.encode({"item":formData});
			 $.ajax({
				url: "com.bos.aft.aft_manage.addMonitoringCorp.biz.ext",
            	type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
           		success: function (data) {
           			if(data.msg==1){
           				CloseWindow();
           			}else if(data.msg==0){
           				alert("保存失败");
           			}
           		},
           		error: function (jqXHR, textStatus, errorThrown) {
                	nui.alert(jqXHR.responseText);
            		}
			
			});
			
		}
		
	</script>
</body>
</html>
