<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): LUJINBIN
  - Date: 2014-03-14
  - Description:TB_SCORE_PROJECT_MESSAGE, com.bos.pub.sys.TbScoreProjectMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>项目名称：</label>
		<input name="tbScoreProjectMessage.projectName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		<label>机构名称：</label>
		<input id="nid" name="tbScoreProjectMessage.organizationName" required="true"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" dictTypeId="org" value="<%=request.getParameter("orgId") %>" enabled="false"/>
		<label>机构级别：</label>
		<input id="level" name="tbScoreProjectMessage.orgLevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="CDZZ0002" enabled="false" value=''/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	 	nui.get("level").setValue("0<%=request.getParameter("orgLevel") %>");
	    var form = new nui.Form("#form1");
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
        url: "com.bos.pub.scorePorject.addTbScoreProjectMessage.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        	 var nid;
        	 nid=  nui.get("nid").getValue();
        		CloseWindow("ok:"+nid);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
<%--  
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
                  //   alert(nui.encode(data));
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                        nui.get("level").setValue("0"+data.orglevel);
                    }
                }
            }
        });            
    }
--%> 
	</script>
</body>
</html>
