<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 夏群
  - Date: 2013-12-10 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmManagementTeam" class="nui-hidden" />
	<input name="item.managementTeamId" id="item.managementTeamId" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>客户经理名称</label>
			<input id="item.userNum" name="item.userNum" allowInput="false" class="nui-text nui-form-input" dictTypeId="user"  />
			
			<label>所在机构名称</label>
			<input id="item.orgId" class="nui-text nui-form-input" name="item.orgNum"  dictTypeId="org"  enabled="true"/>
			
			<label>所在机构编号</label>
			<input id="item.orgNum" class="nui-text nui-form-input" name="item.orgNum"   required="true"/>
			<%--
			<label>岗位名称</label>
			<input id="item.post" class="nui-text nui-form-input" name="item.post"  />--%>
			
			<label>管理类型</label>
			<input id="item.userPlacingCd" name="item.userPlacingCd" data="data" valueField="dictID" textField="dictName" 
				required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0187"  requiredErrorText="管理类型必选"/>
		</div>
		<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px">
			<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
			<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</div>
</center>



 <script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
    
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	function initForm() {
		var json=nui.encode({"item":{"managementTeamId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.dataset.csm.TbCsmManagementTeam"}});
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		text.item.orgId1 = text.item.orgId;
	            		form.setData(text);
	            		nui.get("item.orgId").setValue(text.item.orgNum);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	 git.unmask("form1");
	               	 nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	
	function save() {
	
			//判断管护权的唯一性
		   if(checkMajorRightUnique()=="false"){
	            return;
	        }
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.saveItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
	
	    //校验管户权客户经理只能一个
        function checkMajorRightUnique(e){
            var userPlacingCd= nui.get("item.userPlacingCd").value;
            var userNum = nui.get("item.userNum").value;
	 		var partyId = nui.get("item.partyId").getValue();
	 		var result = "true";
	 		
   	     
   	      if(userPlacingCd=="01"){
   	      	 json = {"partyId":partyId};
   	      	$.ajax({
   	      	  	url:"com.bos.csm.pub.pubMethod.checkMajorRightUnique.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	          	 	if(text.msg){
   	          			var userPlacingCd = nui.get("item.userPlacingCd").getValue();
   	          			if(!userPlacingCd){
   	          			result = "true";
   	          			}else{
   	          			alert(text.msg);
   	          			result = "false";
   	          			}
   	          			
   	          	 	}
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	          		nui.alert(jqXHR.responseText);
   	          }
   	     });
   	    } 
   	        return result; 
   	  }
	
 
 </script>

</body>
</html>