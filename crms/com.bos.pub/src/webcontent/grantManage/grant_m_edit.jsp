<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-07-03 09:05:14
  - Description:小贷中心授权维护
-->
<head>
<title>小贷中心授权维护</title>
</head>
<body>
<div id="form1"  style="width:100%;height:auto;overflow:hidden; text-align:left">
		<input id="item.gId" name="item.gId" class="nui-hidden">
		<div class="nui-dynpanel" columns="2">
			<label>用户名称：</label>
			<input id="item.userId" name="item.userId" required="true" allowInput="false" 
			class="nui-buttonEdit nui-form-input" onbuttonclick="selectCust"  />
			<label>权限金额(万元)：</label>
			<input id="item.maxAmt" name="item.maxAmt" required="true"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div>
		<div class="nui-toolbar" style="border:0;text-align:right;">
	    	<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save">保存</a>
	    	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1"); 
	
	var gId = "<%=request.getParameter("gId")%>";
	var v = "<%=request.getParameter("v")%>";
	//初始化页面
    $(document).ready(function(){
    	if('2'==v){
    	
    		var json = nui.encode({"item":{"gId":gId}});
			$.ajax({
		        url: "com.bos.pub.grantManage.grantManage.queryGrantsM.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
	               	nui.get("item.maxAmt").setValue(text.items[0].maxAmt);
	               	nui.get("item.gId").setValue(text.items[0].gId);
	               	nui.get("item.userId").setValue(text.items[0].userId);
	               	nui.get("item.userId").setText(text.items[0].userName);
		        }
    		});
    	}
	});
	
	//选人事件
	function selectCust(e) {
		nui.open({
	        url: nui.context + "/pub/grantManage/grant_m_employee_list.jsp",
	        showMaxButton: true,
	        title: "选择员工",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	nui.get("item.userId").setValue(data.userId);
                    	nui.get("item.userId").setText(data.userName);
	                }
	            }
	        }
	    }); 
    }
	//保存事件
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		
		if('2'!=v){
			//校验是否已经加入授权
			var json = {"userId":o.item.userId};
	   	   	var msg = exeRule("RGRANT_0001","1",json);
	   	    if(null != msg && '' != msg){
		   	     nui.alert(msg);
		   	     return;
	   	     }
		}
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.pub.grantManage.grantManage.saveGrantM.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		alert(text.msg);
	            		return;
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
	}
	
</script>
</html>