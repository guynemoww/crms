<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 20:14:50
  - Description:
-->
<head>
<title>人员机构及关联角色新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="padding:0px 5px 0px 5px">
<input id="oid" name="" class="nui-hidden"/>
<input id="oname" name="" class="nui-hidden"/>
<input id="olevel" name="" class="nui-hidden"/>
<table width="100%">
    <tr>
       <td class="nui-form-label"><label for="orgid$text">机构：</label></td>
	   <td width="60%"><input id="orgid" name="employee.orgid" textName="employee.orgid" allowInput="false" 
	   		class="nui-buttonEdit" onbuttonclick="selectEmpOrg" required="true"/>
	   		</td>
    </tr>
	<tr>
		<td class="nui-form-label"><label for="specialty$text">角色：</label></td>
		<td><input id="specialty" name="employee.specialty" textName="employee.specialty" allowInput="false" 
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
	//标准方法接口定义
        function SetData(data) {
        	nui.get("oid").setValue(data.oid);
        	nui.get("oname").setValue(data.oname);
        	nui.get("olevel").setValue(data.olevel);
        }
	
	function save(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
        if (self.orglevel_role != self.orglevel_org) {
        	alert('所选机构的级别和角色的级别应相同');
        	return;
        }
        
        CloseWindow("ok");
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
        var orgid=nui.get("oid").getValue();
        if (orgid='null')
        	orgid='';
        nui.open({
            url: nui.context + "/utp/org/employee/selectRoleOrg.jsp?orgid="+orgid
            	+"&orglevel="+nui.get("olevel").getValue()
            	+"&orgname="+git.toUrlParam(nui.get("oname").getValue()),
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
                    	self.orglevel_org=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
 function selectRole(e) {
    	var btnEdit = this;
    	var orglevel = !!window.orglevel ? window.orglevel : '1';
        nui.open({
            url: nui.context + "/utp/org/employee/select_managed_role.jsp?level=" + orglevel,
            showMaxButton: false,
            title: "选择人员在其归属机构的角色",
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
                    	self.orglevel_role=data.level;
                    	//alert(nui.encode(data));
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }
</script>

</body>
</html>