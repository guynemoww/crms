<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>岗位修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js" ></script>
</head>
<body>
<div class="nui-fit" style="padding-top:5px;min-width:450px;min-height:100px;">
	<div id="form1" style="width:100%;overflow:hidden;">
	<input class="nui-hidden" name="orgPosition.orgOrganization.orgid" />
	<input class="nui-hidden" name="orgPosition.orgPosition.positionid" />
	<input class="nui-hidden" name="orgPosition.positionid" />
	<input class="nui-hidden" name="orgPosition.orgPosition.posilevel" />
	<input class="nui-hidden" name="orgPosition.orgPosition.positionseq" />
	<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table" >
		<tr>
			<th class="nui-form-label"><label for="posiname$text">岗位名称：</label></th>
			<td><input id="posiname" class="nui-textbox nui-form-input" name="orgPosition.posiname" required="true" vtype="maxLength:128"/></td>
			<th class="nui-form-label"><label for="posicode$text">岗位代码：</label></th>
			<td><input id="posicode" class="nui-textbox nui-form-input" name="orgPosition.posicode" required="true" vtype="maxLength:20"/></td>
		</tr>					
		<tr class="odd">
			<th class="nui-form-label"><label for="orgname$text">所属机构：</label></th>
			<td>
			<!-- 
			<input name="orgPosition.orgOrganization.orgid" value="orgPosition.orgOrganization.orgid" textName="orgPosition.orgOrganization.orgname" allowInput="false" class="nui-textbox nui-form-input" />
			 -->
			 <input id="orgname" class="nui-textbox nui-form-input" name="orgPosition.orgOrganization.orgname" allowInput="false" />
			</td>
			<th class="nui-form-label"><label for="startdate$text">生效日期：</label></th>
			<td><input id="startdate" name="orgPosition.startdate" class="nui-datepickerx nui-form-input" /></td>
			<!-- 
			<th class="nui-form-label"><label for="posiname$text">所属职务：</label></th>
			<td><input class="nui-textbox nui-form-input" name="orgPosition.orgDuty" /></td>
			 -->
		</tr>			
		<tr>
			<th class="nui-form-label"><label for="enddate$text">失效日期：</label></th>
			<td><input id="enddate" name="orgPosition.enddate" class="nui-datepickerx nui-form-input" onvalidation="onEnddateValidation" />
			</td>
		</tr>	
		<tr>
			<td colspan="4" style="padding:0px;">
				<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
				    borderStyle="border:0;">
				    <a class="nui-button"  style="width:60px;" iconCls="icon-save" onclick="update">保存</a>
				    <span style="display:inline-block;width:25px;"></span>
				    <a class="nui-button" id="resetBtn_01" style="width:60px;" iconCls="icon-reset" onclick="resetForm">重置</a>
				    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;display:none;" onclick="cancel">取消</a>
				</div>
			</td>
		</tr>	
	</table>
</div>
</div>

<script type="text/javascript">
	 nui.parse();

	(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
		}
	})();

     var form = new nui.Form("form1");

    function update() {
        var o = form.getData(true,true);
        form.validate();
        if (form.isValid() == false) return;
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.utp.org.position.updatePosition.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response;
            	if(response && response.flag && window.isCloseWindow){
		            CloseWindow("ok");
            	}else{
            		nui.alert(response.message);
            		window['formData'] = o;
            		if(response.flag && window.parent){
            			window.parent.refreshParentNode();
            		}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }

    ////////////////////
    //标准方法接口定义
    function SetData(data) {
    	if(data.action=="update"){
    		window.isCloseWindow = true;
    		showCancelBtn();
    		$("#form1").css("height","100%");
    	}
        //跨页面传递的数据对象，克隆后才可以安全使用
        data = nui.clone(data);
		var json = nui.encode({template:data});
        $.ajax({
            url: "com.bos.utp.org.position.getPositionWithParent.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (data) {
                var o = nui.decode(data);
                form.setData(o);
                window['formData']=o;
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
	
	//校验日期
	//失效日期必须大于生效日期
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.orgPosition || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
    var bootPath = "<%=request.getContextPath() %>";
    
    //选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/position/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 350,
            onload:function(){
            	 var iframe = this.getIFrameEl();
            	 var data = form.getData();
                 iframe.contentWindow.SetData({org:{orgid:data.orgPosition.orgOrganization.orgid||""}});
            },
            ondestroy: function (action) {            
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
    
     function resetForm(){
		var data = window['formData'];
		if(data){
			form.setData(data);
		}else{
			form.reset();
		}
	}    
	
	 function showCancelBtn(){
    	$("#cancelBtn_01").show();
    	$("#resetBtn_01").hide();
    }
	
</script>

</body>
</html>