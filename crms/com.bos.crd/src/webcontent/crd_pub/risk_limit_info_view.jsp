<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 11:05:35
  - Description:
-->
<head>
<title>限额</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<center>

	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="riskLimit.limitId" class="nui-hidden nui-form-input" name ="riskLimit.limitId"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label">限额机构：</label>	
			<input id="riskLimit.limitOrg" class="nui-text nui-form-input" name="riskLimit.limitOrg" required="true" dictTypeId="org"/>	
			
			<label class="nui-form-label">限额组：</label>
			<input id="riskGroup.groupName" class="nui-text nui-form-input" name="riskGroup.groupName" required="true"/>
			
			<label class="nui-form-label">分配额度：</label>
			<input id="riskLimit.limitAmt" class="nui-textbox nui-form-input" name="riskLimit.limitAmt" required="true"/>
			
			<label class="nui-form-label">可用额度：</label>
			<input id="riskLimit.availableAmt" class="nui-text nui-form-input" name="riskLimit.availableAmt"/>
			
			<label class="nui-form-label">起始日期：</label>
			<input id="riskLimit.startDate" class="nui-datepicker nui-form-input" name="riskLimit.startDate" enabled="false"/>
		
			<label class="nui-form-label">到期日期：</label>
			<input id="riskLimit.endDate" class="nui-datepicker nui-form-input" name="riskLimit.endDate" enabled="false"/>
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="risk_limit_info_view" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var limitId ="<%=request.getParameter("limitId") %>";//限额ID
	var v ="<%=request.getParameter("v") %>";//限额ID
	if("0"==v){
		nui.get("risk_limit_info_view").hide();
		form.setEnabled(false);
	}
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"limitId":"<%=request.getParameter("limitId")%>"});
		$.ajax({
            url: "com.bos.crdPub.riskLimitInfo.getRiskLimit.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
			}
        });
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("risk_limit_info_view").setEnabled(false);
		var o = form.getData();
		var json = nui.encode(o);
   		$.ajax({
	        url: "com.bos.crdPub.riskLimitInfo.saveRiskLimitView.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		nui.alert(text.msg); //失败时后台直接返回出错信息
	        		nui.get("risk_limit_info_view").setEnabled(true);
	        		return;
	        	}
	        	alert("保存成功！");
	        	CloseWindow("ok");
	        	nui.get("risk_limit_info_view").setEnabled(true);
	        }}
	    );
	}
	function selectGroup(e){
        var btnEdit = this;
        nui.open({
            url: nui.context + "/crd/crd_pub/risk_group_list.jsp",
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.ID);
                        btnEdit.setText(data.GROUP_NAME);
                    }
                }
        	}
        });  
	}
	//选择机构
	 function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
</script>
</body>
</html>