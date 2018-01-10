<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 13:06:54
  - Description:
-->
<head>
<title>集团客户基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
<input id="groupCompany.partyId" class="nui-hidden nui-form-input" name="groupCompany.partyId"/>
		<fieldset>
		  <legend>
		    <span>基本信息</span>
		  </legend>
		<div class="nui-dynpanel" columns="4">
			<label>集团客户编号</label>
			<input  id="party.partyNum"name="party.partyNum" class="nui-textbox nui-form-input" required="true"  enabled="false" />
			
			<label>集团客户名称</label>
			<input id="party.partyName" name="party.partyName"   class="nui-textbox nui-form-input" required="true"  enabled="false"/>

			<label>集团客户管理方式</label>
			<input required="true" name="groupCompany.groupManageWayCd" 
				 class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0204" required="true"  enabled="false"/>
			
			<label>集团客户成员数</label>
			<input id="memberNum"  class="nui-textbox nui-form-input" name="memberNum"  required="true"  enabled="false" />
			
			<label>集团工商注册编号</label>
			<input id="groupCompany.entmark"  name="groupCompany.entmark" vtype="maxLength:64" class="nui-textbox nui-form-input" required="false"  />
			
		</div>
		</fieldset>

		<fieldset>
		  <legend>
		    <span>系统信息</span>
		  </legend>
		  <div class="nui-dynpanel" columns="4">
			<label>登记日期</label>
			<input  name="groupCompany.createTime"  required="true"  enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
				
		    <label>客户经理</label>
			<input class="nui-text nui-form-input" id="groupCompany.updateUserNum"  name="groupCompany.updateUserNum" dictTypeId="user" required="true"  enabled="false"/>
			
			<label>主办行</label>
			<input class="nui-text nui-form-input" id="groupCompany.updateOrgNum" name="groupCompany.updateOrgNum" dictTypeId="org"required="true"  enabled="false"/>
			
			<label>更新日期</label>
			<input  name="groupCompany.updateTime"  required="true"  enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
				
		  </div>
		  
		</fieldset>
		
		<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;" >
		
		    <a id="btnSave" class="nui-button"  iconCls="icon-save" onclick="add">保存</a>
<!-- 		    <a id="groupComfirm" class="nui-button"  iconCls="icon-save" onclick="groupComfirm">集团认定</a> -->
<!-- 		    <a id="back" class="nui-button"  iconCls="icon-save" onclick="backBps">撤销</a> -->
		    <span style="display:inline-block;width:25px;"></span>
		</div> 
	</div>
	

<script type="text/javascript">
	nui.parse();
	git.mask("form1");
	var form = new nui.Form("#form1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var processInstId = "<%=request.getParameter("processInstId") %>";
	
	if(qote==1){
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	   
	function formInit(){
	var json=nui.encode({"party":{"partyId":partyId},"processInstId":processInstId});
		if(qote==0){
		$.ajax({
            url: "com.bos.csm.company.company.GetCompanyInfoEcif.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (data) {
            	git.unmask("form1");
            	if("AAAAAAA"!=data.map.msg){
            	alert(data.map.msgg);
            	}else{
            	if(data.msg){
            		alert(data.msg);
            	} else {
            		form.setData(data);
            	}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
	});
		}else{
		$.ajax({
            url: "com.bos.csm.company.company.getCompanyInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (data) {
            	git.unmask("form1");
            	if(data.msg){
            		alert(data.msg);
            	} else {
            		form.setData(data);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
	});
		}
	
	}
	
	formInit();
	
	
	//保存
	function add(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        git.mask("form1");
       var o = form.getData();
        var json = nui.encode(o);
        if(qote==0){
        $.ajax({
            url: "com.bos.csm.company.company.saveCompanyEcif.biz.ext",//saveCompanyEcif
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask("form1");
            	if(text.msg){
            		alert(text.msg);
            	} else {
            		alert("保存成功！");
            		formInit();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
        }else{
                $.ajax({
            url: "com.bos.csm.company.company.saveCompany.biz.ext",//saveCompanyEcif
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask("form1");
            	if(text.msg){
            		alert(text.msg);
            	} else {
            		alert("保存成功！");
            		formInit();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
        }
        
	}
	
	
	
	
</script>



</body>
</html>