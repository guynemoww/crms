<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.eos.data.datacontext.IUserObject"%>
<%@page import="com.bos.bps.util.CommonUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08
  - Description:TB_PUB_GRANT_TABLE_COL, com.bos.pub.decision.TbPubGrantTableCol
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<%
		IUserObject user = CommonUtil.getIUserObject();
		String orgid = (String)user.getUserOrgId();
		
 %>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="temp" required="false" class="nui-hidden" />
	<input id="pid" name="tbPubGrantTableCol.pid" class="nui-hidden" value="<%=request.getParameter("itemId") %>"/>
	<div class="nui-dynpanel" columns="4">
		<label>规则标识：</label>
		<input name="tbPubGrantTableCol.rind" required="true" class="nui-buttonEdit" onbuttonclick="selectRule" vtype="maxLength:1000" />

		<label>参数名称：</label>
		<input name="tbPubGrantTableCol.tname" required="true" data="tname" class="nui-combobox nui-form-input" vtype="maxLength:1000" />

		<label>参数值：</label>
		<input name="tbPubGrantTableCol.tvalue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:2000" />
		
		<label>适用机构：</label>
		<input name="tbPubGrantTableCol.torg" required="true" class="nui-buttonEdit"  vtype="maxLength:20" onbuttonclick="selectEmpOrg" dictTypeId="org"/>

		<!--<label>岗位：</label>
		<input name="tbPubGrantTableCol.tposition" required="false" class="nui-buttonEdit"  vtype="maxLength:4000" onbuttonclick="selectRole" />
-->
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
     var tname=[{text:"单笔金额",id:"单笔金额"},{text:"单户金额",id:"单户金额"}];
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var itemId="<%=request.getParameter("itemId") %>";
	   nui.get("pid").setValue(itemId);
	   
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	   //specialty = self.posicode.substr(1,1);
       // if (specialty != window.orglevel) {
       // 	alert('所选机构的级别和岗位的级别应相同');
       // 	return;
       // }
	
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.decision.addTbPubGrantTableCol.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}

function selectRule(e) {
        var btnEdit = this;
          var pid=nui.get("pid").getValue();
        
        nui.open({
            url: nui.context + "/pub/grant/pack/table_col/rule_list.jsp?pid="+pid,
            showMaxButton: false,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.grid.getSelected();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.rind);
                        btnEdit.setText(data.rname);
                         nui.get("temp").setValue(data.rind);
                        // 在此也可做其他操作
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
                	// 在此也可做其他操作
            	}
        	}
        });            
}

function selectParam(e) {
var temp;
temp= nui.get("temp").getValue();
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/grant/pack/table_col/param_list.jsp?temp="+temp,
            showMaxButton: false,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.grid.getSelected();
                    data = nui.clone(data);
                    if (data) {
                    
                        btnEdit.setValue(data.paramid);
                        btnEdit.setText(data.paramname);
                        // 在此也可做其他操作
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
                	// 在此也可做其他操作
            	}
        	}
        });            
}

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
	                    if(data.orgid!='<%=orgid%>'){
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgid);
	                        btnEdit.setText(data.orgname);
                        }else{
                        	nui.alert("您不可以给自己机构添加授权表项!");
                        }
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
                    }
                }
            }
        });            
    }
	</script>
</body>
</html>
