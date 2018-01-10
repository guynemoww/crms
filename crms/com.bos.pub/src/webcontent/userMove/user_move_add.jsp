<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
  <!-- Author(s):陈川
  - Date: 2015-09-10
--> 
<head> 
<%@include file="/common/nui/common.jsp"%>
</head>
<body> 
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">

<input id="originalOrgId" name="originalOrgId" class="nui-hidden nui-form-input"  	/>
 <fieldset>
<legend>
 <span>移交信息</span> 
 </legend> 
 <div class="nui-dynpanel"columns="4"> 
	<label>待移交的用户：</label> 
	<input id="map.userName" name="map.userName"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"required="true" /> 
	
	<label>用户编号：</label> 
	<input id="map.userNum" name="map.userNum" class="nui-textbox"enabled="false" required="true" />
	
	<label>原所在机构：</label> 
	<input id="map.originalOrgId" name="map.originalOrgId" class="nui-buttonEdit"enabled="false"required="true"  />
	
	<label>目标机构：</label>
	<input id="map.targetOrgId" name="map.targetOrgId"allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg" required="true" />
 
 </div> 
</fieldset>
 <div class="nui-toolbar"
	style="border: 0; text-align: right; padding-right: 20px;">
	 <a id="btnSave" class="nui-button" style="margin-right: 5px;" iconCls="icon-save" onclick="save()">移交</a> 
	<a id="btnClose" class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
</div>
 </div> 
 <script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	// 经办人
	function selectCustManegers(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/userMove/user_select_list.jsp",
			showMaxButton : true,
			title : "选择用户",
			width : 800,
			height : 500,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					data = nui.clone(data);
					if (data) {
						btnEdit.setText(data.EMPNAME);
						btnEdit.setValue(data.USERID);
						nui.get("map.userNum").setValue(data.USERID);
						nui.get("map.originalOrgId").setValue(data.ORGID);
						nui.get("map.originalOrgId").setText(data.ORGNAME);
					}
				}
			}
		});

	}
	//机构选择
	function selectOrg() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					data = nui.clone(data);
					if (data) {
						btnEdit.setValue(data.orgid);
						btnEdit.setText(data.orgname);
					}
				}
			}
		});
	}

	function save() {
	
		form.validate();
        if (form.isValid()==false){
         	return alert("请按规则填写信息");
        }
		
		if (nui.get("map.originalOrgId").getValue() == nui.get("map.targetOrgId").getValue()) {
			alert("原所在机构和移交到的机构不能相同");
			return;
		}
		//判断该用户是否已在目标机构
		var json = {"userNum":nui.get("map.userNum").getValue(),"targetOrgId":nui.get("map.targetOrgId").getValue()};
	  	msg = exeRule("PUB_ORG_USER","1",json);
 	    if(null != msg && '' != msg){//用户已在目标机构下
   	    	alert(msg);
   	    	return;
  	     }
  	     //移交
  	     move();
	}
	
	function move(){
			var o = form.getData(); //获取表单多个控件的数据
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.pub.userMove.userMove.moveUser.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
				nui.alert(text.msg);
				CloseWindow('ok');
				},
				error : function() {
					nui.alert("操作失败！");
				}
			});
	}
	
	
</script> </body> </html>
