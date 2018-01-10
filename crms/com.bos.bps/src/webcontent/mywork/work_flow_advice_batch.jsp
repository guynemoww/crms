<%@page pageEncoding="UTF-8" import="com.bos.bps.dao.BusinessParameterDAO,commonj.sdo.DataObject" %>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2013-11-13 16:18:58
  - Description:流程审批
-->
<head>
<title>流程批量审批 - 意见</title>
</head>
<body>
<div id="panel1" class="nui-panel" title="审批"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input class="nui-hidden" id="it.processInstId" name="it.processInstId" value="<%=request.getParameter("processInstId")%>"/>
		<input class="nui-hidden" id="it.activityDefId" name="it.activityDefId" value="<%=request.getParameter("activityDefId")%>"/>
		<input class="nui-hidden" id="it.templateVersion" name="it.templateVersion" value="<%=request.getParameter("templateVersion")%>"/>
		<input class="nui-hidden" id="it.selectType" name="it.selectType" value="1"/>
		<input class="nui-hidden" id="it.activityType" name="it.activityType"/>
		<input class="nui-hidden" id="it.userVariable" name="it.userVariable"/>
		<input class="nui-hidden" id="it.ruleID" name="it.ruleID" value=""/>
		<input class="nui-hidden" id="it.nextOrgCd" name="it.nextOrgCd"/>
		<input class="nui-hidden" id="it.nextOrgName" name="it.nextOrgName"/>
		<input class="nui-hidden" id="it.nextPostCd" name="it.nextPostCd"/>
		<input class="nui-hidden" id="it.nextPostName" name="it.nextPostName"/>
		<input class="nui-hidden" id="it.nextUsersName" name="it.nextUsersName"/>
		<input class="nui-hidden" id="it.nextUsersNum" name="it.nextUsersNum"/>
		<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
			<tr>
				<td style="width: 20%"><label class="nui-form-label">意见： </label>
				</td>
				<td colspan="2" style="width: 80%">
					<input id="it.opinion" name="it.opinion" class="nui-textarea"
					required="true" maxLength="512" style="width: 80%" />
				</td>
			</tr>
			<tr id="person" style="display: none;">
				<td style="width: 20%"><label id="selectuserLabel" class="nui-form-label">请选择人员： </label>
				</td>
				<td colspan="2" style="width: 80%">
					<input id="selectuser" name="username" textName="username"
						allowInput="false" class="nui-buttonEdit" required="true" onbuttonclick="selectEmpOrg"/> 
				</td>
				
			</tr>
		</table>
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-ok" id="btn_submitEval" onclick="onOk()">确认</a>
	   	<a class="nui-button" iconCls="icon-cancel" id="btn_cancel" onclick="onCancel()">取消</a>
	</div>
</div>
<script type="text/javascript">

	nui.parse(document.getElementById("panel1"));
	var form = new nui.Form("#form1");
	
    //初始化，是否显示人员/角色选择框
    function initSelect() {
    	var o = form.getData();
        var json = nui.encode(o);
        //alert(json);
    	$.ajax({
        url: "com.bos.bps.op.WorkFlowManager.getNextNodeExtendAttibute.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text&&text.map){
        		nui.get("it.activityType").setValue(text.map.type);
        		nui.get("it.userVariable").setValue(text.map.WFActivityDefineId);
        		if (text.map.type=='finish') {
        			//无需选人
        			document.getElementById("person").style.display="none";
        		} else {
        		
        			window['bps_attr_map'] = text.map;
    				document.getElementById("person").style.display="";
        		}
        	}else{
        	
        		nui.alert("初始化下一岗位人员发生异常，请联系管理员！");
        		nui.get("btn_submitEval").setEnabled(false);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
        });
            
    }
    //初始化选择人数据
    $(document).ready(function(){
    
    	initSelect();
    
    });
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/bps/mywork/select_bps_user.jsp?map="+escape(nui.encode(window['bps_attr_map'])),
            showMaxButton: false,
            title: "选择人员",
            width: 350,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	//nui.alert(nui.encode(data));
                        btnEdit.setValue(data.USERID);
                        btnEdit.setText(data.OPERATORNAME);
                        nui.get("it.nextOrgCd").setValue(data.ORGCODE);
                        nui.get("it.nextOrgName").setValue(data.ORGNAME);
                        nui.get("it.nextPostCd").setValue(data.POSICODE);
                        nui.get("it.nextPostName").setValue(data.POSINAME);
                        nui.get("it.nextUsersName").setValue(data.OPERATORNAME);
                        nui.get("it.nextUsersNum").setValue(data.USERID);
                    }
                }
            }
        });
    }
    
   function GetData() {
		var json = {};
		var opinion=nui.get("it.opinion").getValue();
		var nextOrgCd=nui.get("it.nextOrgCd").getValue();
		var nextOrgName=nui.get("it.nextOrgName").getValue();
		var nextPostCd=nui.get("it.nextPostCd").getValue();
		var nextPostName=nui.get("it.nextPostName").getValue();
		var nextUsersName=nui.get("it.nextUsersName").getValue();
		var nextUsersNum=nui.get("it.nextUsersNum").getValue();
		var activityType=nui.get("it.activityType").getValue();
		var userVariable=nui.get("it.userVariable").getValue();
		//设值
		json["opinion"]=opinion;
		json["nextOrgCd"]=nextOrgCd;
		json["nextOrgName"]=nextOrgName;
		json["nextPostCd"]=nextPostCd;
		json["nextPostName"]=nextPostName;
		json["nextUsersName"]=nextUsersName;
		json["nextUsersNum"]=nextUsersNum;
		json["activityType"]=activityType;
		json["userVariable"]=userVariable;
        return json;
    } 
    
   function setBtnEnable(){
   		nui.get("btn_submitEval").setEnabled(false);
        nui.get("btn_cancel").setEnabled(false);
   } 
    
   function onOk() {
   		form.validate();
        if (form.isValid()==false) return;
        CloseWindow("ok");
    }
    
    function onCancel() {
        CloseWindow("cancel");
    }
    
	function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

</script>
</body>
</html>