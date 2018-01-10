<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>岗位添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js" ></script>
</head>
<body>
<div class="nui-fit" style="padding-top:5px;min-width:450px;min-height:100px;">
	<div id="form1" style="width:100%;height:99%;overflow:hidden;">
		<input class="nui-hidden" name="orgPosition.orgOrganization.orgid" />
		<table style="width:100%;height:100%;" class="nui-form-table" >
			<tr>
				<th class="nui-form-label"><label for="posiname$text">岗位名称：</label></th>
				<td><input id="posiname" class="nui-textbox nui-form-input" name="orgPosition.posiname" required="true" vtype="maxLength:128"/></td>
				<th class="nui-form-label"><label for="posicode$text">岗位代码：</label></th>
				<td><input id="posicode" class="nui-textbox nui-form-input" name="orgPosition.posicode" required="true" vtype="maxLength:20"/></td>
			</tr>		
			<tr class="odd">
				<th class="nui-form-label"><label for="orgname$text">所属机构：</label></th>
				<td>
				 <input id="orgname" class="nui-textbox nui-form-input" name="orgPosition.orgOrganization.orgname" allowInput="false" />
				</td>
				<th class="nui-form-label"><label for="startdate$text">生效日期：</label></th>
				<td>
				<input id="startdate" name="orgPosition.startdate" class="nui-datepickerx nui-form-input" />
				</td>
			</tr>				
			<tr>
				<th class="nui-form-label"><label for="enddate$text">失效日期：</label></th>
				<td><input id="enddate" name="orgPosition.enddate" class="nui-datepickerx nui-form-input" onvalidation="onEnddateValidation" />
				</td>
			</tr>	
		</table>
	</div>
	
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  style="width:60px;" iconCls="icon-save" onclick="add">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;" onclick="cancel">取消</a>
</div>


<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	
	function add(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
       var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.utp.org.position.addPosition.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                var response = text.response || {};
            	if(response && response.flag){
            		CloseWindow("ok");
            	}else{
            		nui.alert(response.message);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
	}
	
	function SetData(data){
		data = nui.clone(data);
		var node = data.parentNode || {};
		form.setData({orgPosition:{orgOrganization:data.parentNode}});
	}
	
	//失效日期必须大于生效日期
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.orgPosition || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
	function resetForm(){
		form.reset();
	}
	
	var bootPath = "<%=request.getContextPath() %>";
	
	//选择机构
    function selectOrg(e){
        var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/position/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 350,
            ondestroy: function (action){
                if (action == "ok"){
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
    
</script>

</body>
</html>