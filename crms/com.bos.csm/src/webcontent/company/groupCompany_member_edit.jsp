<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 16:17:00
  - Description:
-->
<head>
<title>集团客户成员关系修改</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div style="width:99.5%">
	<fieldset>
	  <legend>
	    <span>集团/关联客户信息</span>
	   </legend>
			<div id="form1" style="width:100%;height:auto;overflow:hidden;align:center" >
			<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmGroupMember" class="nui-hidden" />
			   <input name="item.groupMemberId" id="item.groupMemberId" class="nui-hidden" />
			   <input name="item.groupPartyId" id="item.groupPartyId" class="nui-hidden" />
			   <input name="item.corporationPartyId" id="item.corporationPartyId" class="nui-hidden" />
			   <input name="item.partyId" id="item.partyId" class="nui-hidden" />
					<div class="nui-dynpanel" columns="2">
						
						<label>是否集团客户：</label> 
						<input id="item.isGroup" required="ture"name="item.isGroup"  class="nui-dictcombobox nui-form-input"  dictTypeId="YesOrNo" />
						
						<label>成员类别：</label> 
						<input id="item.memberTypeCd" required="ture" required="ture" name="item.memberTypeCd" data="data"  
							class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0142"/>
						
						<label>关联类型：</label>
						<input id="item.groupRelTypeCd" required="ture" name="item.groupRelTypeCd" 
							class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0207" onvaluechanged="groupRelTypeCd" />
						
						<label>关联关系说明：</label>
						<input id="item.relationshipState" required="false" name="item.relationshipState" vtype="maxLength:1000" class="nui-textarea nui-form-input" />
					</div>
					<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px" >
						<a class="nui-button" iconCls="icon-save" onclick="save()">修改</a>
						<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
					</div>
			</div>
	</fieldset>
</div>
<script type="text/javascript">
	nui.parse();
	git.mask("form1");
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	var groupMemberId = "<%=request.getParameter("itemId") %>";
	var groupMemberPartyId = "<%=request.getParameter("memPartyId") %>";
	
	
	function initForm() {
	
	   if (groupMemberPartyId) {
			nui.get("item.partyId").setValue(groupMemberPartyId);
			nui.get("item.groupMemberId").setValue(groupMemberId);
		}
		var json=nui.encode({"item":{"groupMemberId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.dataset.csm.TbCsmGroupMember"}});
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
	            		form.setData(text);
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
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.saveItem.biz.ext",//saveItemEcif
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
	
	function groupRelTypeCd(e){
        	var groupRelTypeCd = e.value;
        	if(groupRelTypeCd=='04'){
        		nui.get('item.relationshipState').setRequired(true);
        		form.validate();
        	}else{
        		nui.get('item.relationshipState').setRequired(false);
        		form.validate();
        	}
      }
    
  </script>
  
  
</body>
</html>