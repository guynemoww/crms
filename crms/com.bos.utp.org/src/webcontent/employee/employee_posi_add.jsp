<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2014-02-19
  - Description:
-->
<head>
<title>人员机构及关联岗位新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="padding:0px 5px 0px 5px">
<table width="100%">
    <tr>
       <td class="nui-form-label"><label for="orgid$text">机构：</label></td>
	   <td width="60%"><input id="orgid" name="item.omOrganization.orgid" textName="item.omOrganization.orgname" allowInput="false" 
	   		class="nui-buttonEdit" onbuttonclick="selectEmpOrg" required="true"/>
	   		</td>
    </tr>
	<tr>
		<td class="nui-form-label"><label for="specialty$text">岗位：</label></td>
		<td><input name="item.omPosition.positionid" textName="item.omPosition.positionid" allowInput="false" 
			class="nui-buttonEdit" onbuttonclick="selectRole" required="true"/></td>
	</tr>
</table>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="save">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="cancel">取消</a>
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	
	function save(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
        //specialty = self.posicode.substr(1,1);
        /*
       if (self.posilevel != self.orglevel) {
        	alert('所选机构的级别和岗位的级别应相同');
        	return;
        } 
		*/
		var data = form.getData();
		data.item.omEmployee={empid:'<%=request.getParameter("empid") %>'};        
        var json = nui.encode(data);
        nui.ajax({
            url: "com.bos.utp.rights.empposition.addEmpposition.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
	            if(text.returnCode==-2){
	           		 alert("岗位已存在！");
	           		 return;
	            }
            	if(text.returnCode==1) {
            		CloseWindow("ok");
            	} else {
            		alert("操作失败！");
            	}
            },
            error: function () {
            	nui.alert("操作失败，请联系管理员");
            }
        });
	}
	
	function cancel(){
		CloseWindow("cancel");
	}
	
	function getData(id) {
		return nui.get(id).getValue();
	}
	
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
 function selectRole(e) {
    	var btnEdit = this;
    	var orglevel = window.orglevel;
        nui.open({
            url: nui.context + "/utp/org/employee/select_position.jsp?level=" + orglevel,
            showMaxButton: false,
            title: "选择岗位",
            width: 800,
            height: 450,
            onload:function(){
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.data;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.positionid);
                        btnEdit.setText(data.posiname);
                        self.posicode = data.posicode;
                        self.posilevel = data.posilevel;
                    }
                }
            }
        });            
    }
</script>

</body>
</html>