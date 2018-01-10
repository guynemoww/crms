<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-05-04

  - Description:TB_SYS_RISK_REGROUP_LIST, com.bos.pub.sys.TbSysRiskRegroupList-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbSysRiskRegroupList" class="nui-hidden" />
	<input type="hidden" name="tbSysRiskRegroupList.id" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	<label>客户名称：</label>
		<input id="cus" name="tbSysRiskRegroupList.custName" required="true" class="nui-buttonedit nui-form-input" onbuttonclick="onButtonEdit" vtype="maxLength:200" enabled="false"/>


		<label>授权机构：</label>
		<input name="tbSysRiskRegroupList.grantOrgCode" required="true" class="nui-buttonEdit" vtype="maxLength:10" onbuttonclick="selectEmpOrg"  dictTypeId="org"/>


		<label>证件编码：</label>
		<input id="orgn" name="tbSysRiskRegroupList.orgCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" onvalidation="checkOrgnNum" enabled="false"/>
		<label>证件类型：</label>
		<input id="codeType" name="tbSysRiskRegroupList.codeType" required="false" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002" enabled="false"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbSysRiskRegroupList":
		{"id":
		"<%=request.getParameter("id") %>"}});
	$.ajax({
        url: "com.bos.pub.sysManagementList.getTbSysRiskRegroupList.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		nui.get("cus").setText(text.tbSysRiskRegroupList.custName);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.sysManagementList.updateTbSysRiskRegroupList.biz.ext",
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
// 担保方式代码
function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_SXCD1020",
            title: "选择字典项",
            width: 200,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	  //选择客户
    function onButtonEdit(e){
             var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/sysManagementList/cusMsg/cusMsg.jsp",
	            showMaxButton: true,
	            title: "选择客户",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.getData();
	                    data = nui.clone(data);
	                    if (data) {
	                    	 btnEdit.setValue(data.partyName);// 客户id
	                         btnEdit.setText(data.partyName); // 客户名称
	                         nui.get("orgn").setValue(data.certificateCode);// 组织机构代码
	                         var orgn=  nui.get("orgn");
	                         orgn.setEnabled(false);
	                    }
	                }
	            }
	        });   
    }
	 //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url:  nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
	                    	if(data.orgid=="10001"){
	                     		 btnEdit.setValue("BS001");
		                    }else{
		                   		 btnEdit.setValue(data.orgcode);
		                    }
	                        	btnEdit.setText(data.orgname);
                }
            }
            }
        });            
    }
	</script>
</body>
</html>
