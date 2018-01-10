<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-5 13:06:54
  - Description:
-->
<head>
<title>联保小组基本信息</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
<input id="guarGroup.partyId" class="nui-hidden nui-form-input" name="guarGroup.partyId"/>
		<fieldset>
		  <legend>
		    <span>基本信息</span>
		  </legend>
		<div class="nui-dynpanel" columns="4">
			<label>联保小组编号</label>
			<input  id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum" />

			<label>联保小组类型</label>
			<input required="false" name="guarGroup.jointGuaranteeType" data="data" valueField="dictID" textField="dictName" 
				 class="nui-dictcombobox nui-form-input"  readonly="true" Enabled="ture"  dictTypeId="XD_KHCD4001"  requiredErrorText="联保小组类型必须填写"/>
			
			<label>成员数</label>
			<input id="memberNum" readonly="true" Enabled="ture" class="nui-textbox nui-form-input" name="memberNum"  />
			
			<label>联保小组状态</label>
			<input  id="guarGroup.jointGuaranteeStatus" readonly="true" Enabled="ture" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0231" name="guarGroup.jointGuaranteeStatus" />
			<!-- 
			<label>是否跨机构建立联保小组</label>
			<input id="guarGroup.isDifOrgGuarantee" class="nui-dictcombobox nui-form-input" name="guarGroup.isDifOrgGuarantee" dictTypeId="YesOrNo"/>	
			 -->
			 <label>联保客户管理方式</label>
			<input id="guarGroup.manageWay" class="nui-dictcombobox nui-form-input" name="guarGroup.manageWay" required="required" dictTypeId="XD_KHCD0501" Enabled="ture" />	
		</div>
		</fieldset>
		
		<fieldset>
		  <legend>
		    <span>系统信息</span>
		  </legend>
		  <div class="nui-dynpanel" columns="4">
			<label>登记时间</label>
			<input  name="guarGroup.createDate"  required="false"  Enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
				
		    <label>更新人</label>
			<input class="nui-text nui-form-input" id="guarGroup.updateUserNum"  name="guarGroup.updateUserNum" dictTypeId="user" enabled="true"/>
			
			<label>更新机构</label>
			<input class="nui-text nui-form-input" id="guarGroup.updateOrgNum" name="guarGroup.updateOrgNum" dictTypeId="org" enabled="true"/>
			
			<label>更新时间</label>
			<input  name="guarGroup.updateDate" required="false"  Enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			
		  </div>
		  
		</fieldset>

		
		<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;" >
		    <a id="btnSave" class="nui-button"  iconCls="icon-save" onclick="add">保存</a>
<!-- 		    <a id="groupComfirm" class="nui-button"  iconCls="icon-save" onclick="groupComfirm">集团认定</a> -->
		  <!--   <a id="back" class="nui-button"  iconCls="icon-save" onclick="backBps">撤销</a> -->
		    <span style="display:inline-block;width:25px;"></span>
		</div>
	</div>
	

<script type="text/javascript">
	nui.parse();
	git.mask("form1");
	var form = new nui.Form("#form1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var processInstId = "<%=request.getParameter("processInstId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	
	var json=nui.encode({"party":{"partyId":partyId},"processInstId":processInstId});
/* 	nui.get("back").hide();
	nui.get("groupComfirm").hide();
	nui.get("btnSave").hide(); */
/* 	if(qote==1){
	   form.setEnabled(false);
	   nui.get("groupComfirm").hide();
	   nui.get("btnSave").hide();
	}else if(qote==4){
		form.setEnabled(false);
		nui.get("btnSave").hide();
		nui.get("groupComfirm").show();
	} */
	function formInit(){
	if(qote==2){
	$.ajax({
            url: "com.bos.csm.guar.guarGroup.getGuarGroupInfoEcif.biz.ext",
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
	}else{
	$.ajax({
            url: "com.bos.csm.guar.guarGroup.getGuarGroupInfo.biz.ext",
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
	
	
	
	function add(){
		//校验
		
		form.validate();
        if (form.isValid()==false) return;
        git.mask("form1");
        var o = form.getData();
        var json = nui.encode(o);
        if(qote==2){
        $.ajax({
            url: "com.bos.csm.guar.guarGroup.saveGuarGroupEcif.biz.ext",//saveGuarGroup
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            if(text.map.msg!="AAAAAAA"){
            git.unmask("form1");
            alert(text.map.msgg);
            }else{
            	git.unmask("form1");
            	if(text.msg){
            		alert(text.msg);
            	} else {
            		alert("保存成功！");
            		formInit();
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
            url: "com.bos.csm.guar.guarGroup.saveGuarGroup.biz.ext",//saveGuarGroup
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
	
	function groupComfirm(){
		form.validate();
        if (form.isValid()==false) return;
		git.mask("form1");
		var o = form.getData();
        var json2 = nui.encode(o);
       <%-- _alert(json2);
        return;--%>
        $.ajax({
            url: "com.bos.csm.guar.guarGroup.comfirmGuarGroup.biz.ext",
            type: 'POST',
            data: json2,
            cache: false,
            contentType:'text/json',
            success: function (text) {
       			git.unmask("form1");
            	if(text.msg){
            		alert(text.msg);
            	} else {
					openSubmitView2(text.processInstId);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
        
	}
	
	//弹出审批意见页面
function openSubmitView2(processInstId){
	var url = nui.context + "/com.bos.bps.service.workFlowAdvice.flow?processInstId="+processInstId;
	nui.open({
               url: url,
               title: "提交审批", 
               width: 550, 
               height: 260,
               onload: function () {
               },
               ondestroy: function (action) {
               	  nui.get("groupComfirm").hide();
				  nui.get("btnSave").hide();
               }
           });

}
	
	
	
	
</script>



</body>
</html>