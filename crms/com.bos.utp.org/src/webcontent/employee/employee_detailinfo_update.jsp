<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangzhou
  - Date: 2013-03-21 11:24:50
  - Description:
-->
<head>
<title>员工详细修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js"></script>
<style type="text/css">
    fieldset
    {
        border:solid 1px #aaa;
    }        
    .hideFieldset
    {
        border-left:0;
        border-right:0;
        border-bottom:0;
    }
    .hideFieldset .fieldset-body
    {
        display:none;
    }
</style>
</head>
<body>

<div style="padding-top:5px">
	<form id="form">
		<input class="nui-hidden" name="employee.orgid" />
		<input class="nui-hidden" name="employee.empid" />
		<table style="width:100%;table-layout:fixed;" class="nui-form-table" >
			<tr>
				<td class="nui-form-label"><label for="ozipcode$text">办公室邮编：</label></td>
				<td><input id="ozipcode" name="employee.ozipcode" class="nui-textbox" vtype="int;rangeLength:0,10" /></td>
				<td class="nui-form-label"><label for="oemail$text">办公室邮箱：</label></td>
				<td><input id="oemail" name="employee.oemail" class="nui-textbox" vtype="email;rangeLength:0,128" /></td>
			</tr>				
			<tr class="odd">
				<td class="nui-form-label"><label for="faxno$text">传真号码：</label></td>
				<td><input id="faxno" name="employee.faxno" class="nui-textbox"  vtype="maxLength:14"/></td>
				<td class="nui-form-label"><label for="otel$text">办公室电话：</label></td>
				<td><input id="otel" name="employee.otel" class="nui-textbox"  vtype="phone;rangeLength:0,20"/></td>
			</tr>
			<tr>
				<td class="nui-form-label"><label for="qq$text">QQ号码：</label></td>
				<td><input id="qq" name="employee.qq" class="nui-textbox"  vtype="maxLength:16"/></td>
				<td class="nui-form-label"><label for="htel$text">家庭电话：</label></td>
				<td><input id="htel" name="employee.htel" class="nui-textbox"  vtype="phone;rangeLength:0,20"/></td>
			</tr>
			<tr class="odd">
				<td class="nui-form-label"><label for="haddress$text">家庭地址：</label></td>
				<td colspan="3"><input id="haddress" name="employee.haddress" class="nui-textbox" style="width:80%;"  vtype="maxLength:128"/></td>
			</tr>
			<tr>
				<td class="nui-form-label"><label for="oaddress$text">办公地址：</label></td>
				<td colspan="3"><input id="oaddress" name="employee.oaddress" class="nui-textbox" style="width:80%;"  vtype="maxLength:255"/></td>
			</tr>
			<tr class="odd">
				<td class="nui-form-label"><label for="weibo$text">微博：</label></td>
				<td colspan="3"><input id="weibo" name="employee.weibo" class="nui-textbox" style="width:80%;"  vtype="maxLength:255"/></td>
			</tr>	
			<tr>
				<td class="nui-form-label"><label for="hzipcode$text">家庭邮编：</label></td>
				<td><input id="hzipcode" name="employee.hzipcode" class="nui-textbox" /></td>
				<td class="nui-form-label"><label for="pemail$text">私人邮箱：</label></td>
				<td><input id="pemail" name="employee.pemail" class="nui-textbox" vtype="email"/></td>
			</tr>	
			<tr class="odd">
				<td class="nui-form-label"><label for="party$text">政治面貌：</label></td>
				<td><input id="party" name="employee.party" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_PARTYVISAGE" /></td>
				<td class="nui-form-label"><label for="degree$text">职级：</label></td>
				<td><input id="degree" name="employee.degree" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_EMPLEVEL" /></td>
			</tr>			
			<tr>
				<td class="nui-form-label"><label for="major$text">直接主管：</label></td>
				<td colspan="3"><input id="major" name="employee.major"  textName="employee.major" allowInput="false" onbuttonclick="selectMajor" class="nui-buttonEdit" /></td>
			</tr>			
			<tr class="odd">
				<td class="nui-form-label"><label for="orgidlist$text">可管理机构：</label></td> 
				<td colspan="3"><input id="orgidlist" name="employee.orgidlist" textName="employee.orgidlist"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg" /></td>
			</tr>
			<tr>
				<td class="nui-form-label"><label for="specialty$text">可管理角色：</label></td>
				<td colspan="3"><input id="specialty" name="employee.specialty" textName="employee.specialty"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectRole"/></td>
			</tr>	
			<tr class="odd">
				<td class="nui-form-label"><label for="workexp$text">工作描述：</label></td>
				<td colspan="3"><input id="workexp" name="employee.workexp" class="nui-textarea" style="width:80%;" vtype="maxLength:512"/></td>
			</tr>			
			<tr>
				<td class="nui-form-label"><label for="remark$text">备注：</label></td>
				<td colspan="3"><input id="remark" name="employee.remark" class="nui-textarea" style="width:80%;" vtype="maxLength:512"/></td>
			</tr>
		</table>
	</form>
</div>
</form>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="update">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="resetBtn_01"  iconCls="icon-reset" onclick="resetForm">重置</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="display:none;" onclick="cancel">取消</a>
</div>
</div>

<script type="text/javascript">
	 nui.parse();
	 
	 var form = new nui.Form("#form");
	 var orgidlistBtn = nui.get("orgidlist");
	 var specialtyBtn = nui.get("specialty");
	 var majorBtn = nui.get("major");
	 (function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
			var json = nui.encode({template:node});
	        $.ajax({
	            url: "om.bos.utp.org.employee.getEmployee.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            async: false,//非异步，即同步
	            success: function (data) {
	                var o = nui.decode(data);
	                var orgidlist = o.employee.orgidlist;
	                var specialty = o.employee.specialty;
	                var major = o.employee.major;
	                o.employee.orgidlist = analysiString(orgidlist);
	                o.employee.specialty = analysiString(specialty);
	                o.employee.major = loadEmpnameById(major);
	                form.setData(o);
	                orgidlistBtn.setValue(orgidlist);
	                specialtyBtn.setValue(specialty);
	                majorBtn.setValue(major);
	                window['formData'] = o;
	            }
	        });
		}
	 })();
	 
	function analysiString(value){
	    if(value==null){
	        return null;
	    }
	    var values = value.split(",");
        var strs = [];
        for(var i=0,len=values.length;i<len;i++){
            var str = values[i].split(":");
            strs.push(str[1]);
        }
        return strs.join(",");
	}

    function update() {
    	var data = {};
    	
		form.validate();
        if (form.isValid()==false) return;
		
        var json = nui.encode(form.getData());
        $.ajax({
            url: "om.bos.utp.org.employee.updateEmpDetaiInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response;
            	if(response){
	            	if(response.flag){
	            		if(window.isCloseWindow){
		            		CloseWindow("ok");
		            		return;
	            		}
	            		window['formData']=data;
	            		nui.alert(response.message);
	            		//刷新其父节点
	            		if(window.parent){
	            			window.parent.refreshParentNode();
	            		}
	            		return;
	            	}
            	}
            	nui.alert("修改失败，请联系管理员");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }

    function CloseWindow(action) {
        if (action == "close" && form.isChanged()) {
            if (confirm("数据被修改了，是否先保存？")) {
                return false;
            }
        }
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();            
    }
    
    function cancel(e) {
        CloseWindow("cancel");
    }
	
	function resetForm(){
		/*
		var data = window['formData'];
		if(data){
			form.setData(data);
		} else {
			form.reset();
		}
		*/
		
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
			var json = nui.encode({template:node});
	        $.ajax({
	            url: "om.bos.utp.org.employee.getEmployee.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            async: false,//非异步，即同步
	            success: function (data) {
	                var o = nui.decode(data);
	                var orgidlist = o.employee.orgidlist;
	                var specialty = o.employee.specialty;
	                var major = o.employee.major;
	                o.employee.orgidlist = analysiString(orgidlist);
	                o.employee.specialty = analysiString(specialty);
	                o.employee.major = loadEmpnameById(major);
	                form.setData(o);
	                orgidlistBtn.setValue(orgidlist);
	                specialtyBtn.setValue(specialty);
	                majorBtn.setValue(major);
	                window['formData'] = o;
	            }
	        });
		}
		
	}
	
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.user || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}else{
			e.errorText = "";
		}
		updateError(e);
	}
	
    function updateError(e) {
        var id = e.sender.name + "_error";
        var el = document.getElementById(id);
        if (el) {
            el.innerHTML = e.errorText;
        }
    }
    
    var bootPath = "<%=request.getContextPath() %>";
    
    //选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_manageorg_tree.jsp",
            showMaxButton: false,
            title: "选择员工",
            width: 500,
            height: 400,
            onload:function(){
                var iframe = this.getIFrameEl();
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   ids:ids,
                   texts:texts
                };
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }    
    
    function selectRole(e) {
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_managed_role.jsp",
            showMaxButton: false,
            title: "选择可管理角色",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: window['parentNode'],
                   ids:ids,
                   texts:texts
                };
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }    
    
     function selectMajor(e){
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_major.jsp",
            showMaxButton: false,
            title: "选择直接主管",
            width: 800,
            height: 600,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });
    }
    
    function loadEmpnameById(id){
	 	var ret = "";
	 	if(!id){
	 		return ret;
	 	}
	 	var json = nui.encode({template:{empid:id}});
	 	$.ajax({
	         url: "om.bos.utp.org.employee.getEmployee.ext",
	         type: 'POST',
	         data: json,
	         cache: false,
	         contentType:'text/json',
	         cache: false,
	         async: false,//非异步，即同步
	         success: function (data) {
	             var o = nui.decode(data);
	            	ret = o.employee.empname;
	         }
	     });
	     return ret;
	 }
    
    function showCancelBtn(){
    	$("#cancelBtn_01").show();
    	$("#resetBtn_01").hide();
    }
</script>

</body>
</html>