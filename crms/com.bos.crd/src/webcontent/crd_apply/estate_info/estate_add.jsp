<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:33:14
  - Description:不动产项目详情
-->
<head>
<title>房地产项目详细信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbCrdProjectEstateInfo.projectId" class="nui-hidden nui-form-input" name="tbCrdProjectEstateInfo.projectId"/>
		<input id="tbCrdProjectEstateInfo.limitId" class="nui-hidden nui-form-input" name="tbCrdProjectEstateInfo.limitId" value="<%=request.getParameter("limitId")%>"/>
	<fieldset>
		<legend><span>基本信息</span></legend>
		<div class="nui-dynpanel" columns="4" id="table1">
		
			<label class="nui-form-label">合作项目名称：</label>
			<input id="tbCrdProjectEstateInfo.projectName" name="tbCrdProjectEstateInfo.projectName" class="nui-textbox nui-form-input"  required="true" />
			
			<label class="nui-form-label">合作项目类别：</label>
			<input id="tbCrdProjectEstateInfo.projectType" name="tbCrdProjectEstateInfo.projectType" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="CDXY0049"/>
			
			<label class="nui-form-label">合作项目地点：</label>
			<input id="tbCrdProjectEstateInfo.projectAddress" name="tbCrdProjectEstateInfo.projectAddress" class="nui-textbox nui-form-input" required="true" />
			
			<label class="nui-form-label">批准立项文件文号：</label>
			<input id="tbCrdProjectEstateInfo.projectFile" name="tbCrdProjectEstateInfo.projectFile" class="nui-textbox nui-form-input" required="true" />
			
			<label class="nui-form-label">批准立项批复单位：</label>
			<input id="tbCrdProjectEstateInfo.projectUnit" name="tbCrdProjectEstateInfo.projectUnit" class="nui-textbox nui-form-input" required="true" />
	</div>
			
</fieldset>	
<fieldset>

			<legend><span>许可证信息</span></legend>
<div class="nui-dynpanel" columns="4" id="table2">
					
			<label class="nui-form-label">国有土地使用权证号：</label>
			<input id="tbCrdProjectEstateInfo.stateLandUseLicence" name="tbCrdProjectEstateInfo.stateLandUseLicence" class="nui-textbox nui-form-input" required="true" />
			
			<label class="nui-form-label">建设用地规划许可证号：</label>
			<input id="tbCrdProjectEstateInfo.landUsePlanLicence" name="tbCrdProjectEstateInfo.landUsePlanLicence" class="nui-textbox nui-form-input" required="true" />
			
			<label class="nui-form-label">建设工程规划许可证号：</label>
			<input id="tbCrdProjectEstateInfo.engineePlanLicence" name="tbCrdProjectEstateInfo.engineePlanLicence" class="nui-textbox nui-form-input" required="true" />
			
			<label class="nui-form-label">建设工程施工许可证号：</label>
			<input id="tbCrdProjectEstateInfo.engineeBuildLicence" name="tbCrdProjectEstateInfo.engineeBuildLicence" class="nui-textbox nui-form-input" required="true" />
			
			<label class="nui-form-label">商品房预售许可证号：</label>
			<input id="tbCrdProjectEstateInfo.presellLicence" name="tbCrdProjectEstateInfo.presellLicence" class="nui-textbox nui-form-input" required="true" />
</div>
</fieldset>
<fieldset>
			<legend><span>其他信息</span></legend>
			
<div class="nui-dynpanel" columns="4" id="table3">
			<label class="nui-form-label">土地使用权类型：</label>
			<input id="tbCrdProjectEstateInfo.landUseRightType" name="tbCrdProjectEstateInfo.landUseRightType" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="land_use_right_type"/>
			
			<label class="nui-form-label">合作项目状况：</label>
			<input id="tbCrdProjectEstateInfo.estateCondition" name="tbCrdProjectEstateInfo.estateCondition" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="estate_condition"/>
			
			<label class="nui-form-label">备注：</label>
			<input id="tbCrdProjectEstateInfo.remark" name="tbCrdProjectEstateInfo.remark" required="false" style="height: 80px;width: 560px;"  class="nui-textarea nui-form-input" vtype="maxLength:200"  />
		</div>
</fieldset>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="crd_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">

	nui.parse();
	var form = new nui.Form("#form");
	var projectId ="<%=request.getParameter("projectId")%>";
	var oper ="<%=request.getParameter("oper")%>";
	var limitId ="<%=request.getParameter("limitId")%>";//业务申请ID
	if(oper!=1){
		initPage();
	}
	if(oper==3){//只查看
		nui.get("crd_info_save").hide();
		form.setEnabled(false);
	}
	nui.get("tbCrdProjectEstateInfo.projectType").setValue("04");
	nui.get("tbCrdProjectEstateInfo.projectType").setEnabled(false);
	//初始化页面
	function initPage(){
		var json = nui.encode({"projectId":projectId});
		$.ajax({
            url: "com.bos.crdApply.crdApply.getCrdProjectEstate.biz.ext",
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
        nui.get("crd_info_save").setEnabled(false);
		var o = form.getData();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.crdApply.crdApply.saveCrdProjectEstate.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("crd_info_save").setEnabled(true);
        	}
        	//alert("保存成功！");
        	nui.get("crd_info_save").setEnabled(true);
        	//initPage();
        }});
	        CloseWindow("ok");
	}
	
	
</script>
</body>
</html>