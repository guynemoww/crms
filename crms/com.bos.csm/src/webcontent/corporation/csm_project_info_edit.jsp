<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-05-05

  - Description:TB_CSM_PROJECT_INFO, com.bos.dataset.csm.TbCsmProjectInfo-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
		<input name="project.partyId" id="project.partyId" class="nui-hidden" />
		<input name="project.projectId" id="project.projectId" class="nui-hidden" />
		<input name="land.id" id="land.id" class="nui-hidden" />
		<input name="land.projectId" id="land.projectId" class="nui-hidden" />
		<input name="realty.promoterId" id="realty.promoterId" class="nui-hidden" />
		<input name="realty.projectId" id="realty.projectId" class="nui-hidden" />
		<fieldset>
			<legend>
				<span>基本信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4" id="tableForm">
				<label>项目名称：</label>
				<input id="project.projectName" name="project.projectName" onvalidation="checkProjectNameUnique" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

				<label>项目类别：</label>
				<input id="project.projectType" name="project.projectType" required="true" class="nui-dictcombobox nui-form-input" onvaluechanged="displayProperty" dictTypeId="CDXY0049" />

				<label>项目级别：</label>
				<input name="project.projectLevelCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_XMCD0001" />

				<label>项目地点：</label>
				<input name="project.projectAddress" required="true" class="nui-textbox nui-form-input" vtype="maxLength:300" />

				<label>币种：</label>
				<input id="project.currencyCd" dictTypeId="CD000001" name="project.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:80" dvalue="CNY" />

				<label>项目总投资：</label>
				<input id="project.projectTotalAmt" name="project.projectTotalAmt" onvalidation="careerMyamtPercent" vtype="float;maxLength:18" required="true" class="nui-textbox nui-form-input" dataType="currency" />

				<label>自有资金：</label>
				<input id="project.careerMyamt" name="project.careerMyamt" onvalidation="careerMyamtPercent" vtype="float;maxLength:18" required="true" class="nui-textbox nui-form-input" dataType="currency" />

				<label>自有资金比例（%）：</label>
				<input id="project.careerMyamtPercent" name="project.careerMyamtPercent" required="true" enabled="false" class="nui-textbox nui-form-input" />


				<label id="f_5">批准立项文件及文号：</label>
				<input id="project.projectFile" name="project.projectFile" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

				<label id="f_6">批准立项批复单位：</label>
				<input id="project.projectUnit" name="project.projectUnit" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

				<label>项目开工日：</label>
				<input id="project.projectStartDate" name="project.projectStartDate" required="false" class="nui-datepicker nui-form-input" />

				<label>项目竣工日：</label>
				<input id="project.projectEndDate" name="project.projectEndDate" required="false" class="nui-datepicker nui-form-input" />

			</div>
		</fieldset>
		<fieldset id="permit" style="display: block;">
			<legend>
				<span>许可证信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4" id="tableForm2">
				<label id="permit1">国有土地使用权证号：</label>
				<input id="project.eiaPermitNum" name="project.eiaPermitNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

				<label id="permit2">建设用地规划许可证号：</label>
				<input id="project.constructionLandPermitNum" name="project.constructionLandPermitNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

				<label id="permit3">建设工程施工许可证号：</label>
				<input id="project.constructionPermitNum" name="project.constructionPermitNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

				<label id="permit4">建设工程规划许可证号：</label>
				<input id="project.planningPermitNo" name="project.planningPermitNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
				<!-- 
    		<label id="permit5">商品房预售许可证号：</label>
			<input name="project.otherPermit" id="project.otherPermit"required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
    		    	
    		<label id="permit6">环评许可证编号：</label>
			<input id="project.otherPermitNum" name="project.otherPermitNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:300" />
    		 -->
				<label>项目备案通知：</label>
				<input id="project.recordNotice" name="project.recordNotice" class="nui-textbox nui-form-input" style="width: 580px; column-span: all" vtype="maxLength:200" />
				<label></label>
				<label></label>

				<label>备注：</label>
				<input id="project.remark" name="project.remark" style="height: 80px; width: 580px;" class="nui-textarea nui-form-input" vtype="maxLength:200" />

			</div>
		</fieldset>
		<fieldset id="realty" style="display: none;">
			<legend>
				<span>其它信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">

				<label>土地性质：</label>
				<input id="realty.landNature" name="realty.landNature" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0295" />

				<label>土地使用权类型：</label>
				<input id="realty.landUseType" name="realty.landUseType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0296" />

				<label>地类（用途）：</label>
				<input id="realty.purpose" name="realty.purpose" vtype="maxLength:200" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0297" />

				<label>土地使用权面积：</label>
				<input id="realty.landUseArea" name="realty.landUseArea" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" />

				<label>土地使用权终止日期：</label>
				<input id="realty.landUseEndDate" name="realty.landUseEndDate" required="true" class="nui-datepicker nui-form-input" />

				<label>总开发面积（平方米）：</label>
				<input id="realty.countDevArea" name="realty.countDevArea" vtype="float;maxLength:18" required="true" class="nui-textbox nui-form-input" />

				<label>住房开发面积（平方米）：</label>
				<input id="realty.liveDevArea" name="realty.liveDevArea" vtype="float;maxLength:18" required="true" class="nui-textbox nui-form-input" onblur="typeCase" />

				<label>商用房开发面积（平方米）：</label>
				<input id="realty.buildScale" name="realty.buildScale" vtype="float;maxLength:18" required="true" class="nui-textbox nui-form-input" onblur="typeCase" />

				<label>主开发类型:</label>
				<input id="realty.type" name="realty.type" required="true" class="nui-textbox nui-form-input" enabled="false" />

				<label>项目状况：</label>
				<input id="realty.projectCondition" name="realty.projectCondition" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD01860" />

			</div>
		</fieldset>
		<fieldset id="land" style="display: none;">
			<legend>
				<span>其它信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>土地储备计划批复文号：</label>
				<input id="land.landPlanApplyNum" name="land.landPlanApplyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

				<label>批复单位：</label>
				<input id="land.applyUnit" name="land.applyUnit" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

				<label>总面积（平方米）：</label>
				<input id="land.totalArea" name="land.totalArea" class="nui-textbox nui-form-input" vtype="float;maxLength:20" />

				<label>拆迁建筑面积（平方米）：</label>
				<input id="land.buildArea" name="land.buildArea" class="nui-textbox nui-form-input" vtype="float;maxLength:20" />

				<label>储备面积（平方米）：</label>
				<input id="land.storeArea" name="land.storeArea" class="nui-textbox nui-form-input" vtype="float;maxLength:20" />

				<label>收购、征用土地控制性详细规划：</label>
				<input id="land.buyLandDetailPlan" name="land.buyLandDetailPlan" class="nui-textbox nui-form-input" vtype="maxLength:20" />

				<label>上级部门批准举债计划文案文号：</label>
				<input id="land.ratifyPlanNum" name="land.ratifyPlanNum" class="nui-textbox nui-form-input" vtype="maxLength:20" />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
			<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
			<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask("form1");
		var form = new nui.Form("#form1");
		if (
	<%="\"" + request.getParameter("view") + "\""%>
		== "1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
		var projectId =
	<%="\"" + request.getParameter("itemId") + "\""%>
		;
		function initForm() {
			var json = nui.encode({
				"projectId" : projectId
			});
			$.ajax({
				url : "com.bos.csm.corporation.Project.getProjectInfoByProjectId.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					form.setData(text);
					//控制页面显示
					displayProperty();
					typeCase();
					nui.get('tableForm').refreshTable();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
		initForm();

		function displayProperty() {
			var projectType = nui.get("project.projectType").getValue();
			if ('01' == projectType || '02' == projectType || '03' == projectType) {
				//固定资产购置//基本建设项目//技术改造项目
				$("#realty").css("display", "none");
				$("#land").css("display", "none");
				$("#permit").css("display", "block");

				$("#f_5").css("display", "block");//批准立项文件及文号
				nui.get("project.projectFile").show();
				$("#f_6").css("display", "block");//批准立项批复单位
				nui.get("project.projectUnit").show();
				//$("#permit1").css("display", "block");
				//nui.get("project.eiaPermitNum").show();
				//$("#permit2").css("display", "block");
				//nui.get("project.constructionLandPermitNum").show();
				//$("#permit3").css("display", "block");
				//nui.get("project.constructionPermitNum").show();
				//$("#permit4").css("display", "block");
				//nui.get("project.planningPermitNo").show();
				//$("#permit5").css("display", "none");
				//nui.get("project.otherPermit").hide();
			} else if ('04' == projectType) {
				//房地产开发项目
				$("#realty").css("display", "block");
				$("#permit").css("display", "block");
				$("#land").css("display", "none");

				$("#f_5").css("display", "block");
				nui.get("project.projectFile").show();
				$("#f_6").css("display", "block");
				nui.get("project.projectUnit").show();

				//$("#permit1").css("display", "block");
				//nui.get("project.eiaPermitNum").show();
				//$("#permit2").css("display", "block");
				//nui.get("project.constructionLandPermitNum").show();
				//$("#permit3").css("display", "block");
				//nui.get("project.constructionPermitNum").show();
				//$("#permit4").css("display", "block");
				//nui.get("project.planningPermitNo").show();
				//$("#permit5").css("display", "block");
				//nui.get("project.otherPermit").show();

				nui.get("project.projectFile").setRequired(true);
				nui.get("project.projectUnit").setRequired(true);
				nui.get("project.eiaPermitNum").setRequired(true);
				nui.get("project.constructionPermitNum").setRequired(true);
				nui.get("project.constructionLandPermitNum").setRequired(true);
				nui.get("project.planningPermitNo").setRequired(true);
			} else if ('05' == projectType) {//土地储备项目
				$("#realty").css("display", "none");
				$("#land").css("display", "block");
				$("#permit").css("display", "block");

				$("#f_5").css("display", "none");
				nui.get("project.projectFile").hide();
				$("#f_6").css("display", "none");
				nui.get("project.projectUnit").hide();
				//$("#permit1").css("display", "none");
				//nui.get("project.eiaPermitNum").hide();
				//$("#permit2").css("display", "none");
				//nui.get("project.constructionLandPermitNum").hide();
				//$("#permit3").css("display", "none");
				//nui.get("project.constructionPermitNum").hide();
				//$("#permit4").css("display", "none");
				//nui.get("project.planningPermitNo").hide();
				//$("#permit5").css("display", "none");
				//nui.get("project.otherPermit").hide();
			} else {
				$("#realty").css("display", "none");
				$("#land").css("display", "none");
				$("#permit").css("display", "none");
			}
			nui.get('tableForm').refreshTable();
			nui.get('tableForm2').refreshTable();
		}
		nui.get('tableForm').refreshTable();

		//校验项目名称是否唯一
		function checkProjectNameUnique(e) {
			var partyid = nui.get("project.partyId").getValue();
			var projectname = nui.get("project.projectName").getValue();
			var projectid = nui.get("project.projectId").getValue();
			if (e.isValid) {
				var projson = {
					"partyId" : partyid,
					"projectName" : projectname,
					"projectId" : projectid
				};
				var msg = exeRule("PUB_0020", "1", projson);
				if (null != msg && '' != msg) {
					e.errorText = msg;
					e.isValid = false;
				}
			}
		}

		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}

			if (nui.get("project.careerMyamtPercent").getValue() > 99999) {
				nui.alert("自有资金比例超出范围");
				return;
			}
			var o = form.getData();
			var json = nui.encode(o);

			//nui.alert(json);return;
			$.ajax({
				url : "com.bos.csm.corporation.Project.updateTbCsmProjectInfo.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
					} else {
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function typeCase() {
			var liveDevArea = nui.get("realty.liveDevArea").getValue();
			var buildScale = nui.get("realty.buildScale").getValue();
			if (liveDevArea && buildScale) {
				if (liveDevArea >= buildScale) {
					nui.get("realty.type").setValue("住房开发");
				} else {
					nui.get("realty.type").setValue("商用房开发");
				}
			}

		}

		function careerMyamtPercent() {
			var careerMyamtPercent = nui.get("project.careerMyamt").getValue() / nui.get("project.projectTotalAmt").getValue() * 100;
			nui.get("project.careerMyamtPercent").setValue(careerMyamtPercent);
		}
	</script>
</body>
</html>
