<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<title>日常检查信息</title>
</head>
<body>
<div id="form1">
	<input id="inspect._entity" class="nui-hidden nui-form-input" name="inspect._entity" value="com.bos.dataset.aft.TbAftDailyInspect"/>
	<div><h5>客户基本信息</h5></div>
		<div class="nui-dynpanel" columns="4"  id="corp">
				<label class="nui-form-label" >客户编号：</label>
				<input id="corp.partyId" class="nui-text nui-form-input" name="corp.partyId" required="true"/>
				
				<label class="nui-form-label">组织机构代码：</label>
				<input id="corp.orgnNum" class="nui-text nui-form-input" name="corp.orgnNum" required="true" />
				
				<label class="nui-form-label">客户名称：</label>
				<input id="corp.partyName" class="nui-text nui-form-input" name="corp.partyName" required="true"/>
				
				<label class="nui-form-label">营业执照号码：</label>
				<input id="corp.businessLicenseNum" class="nui-text nui-form-input" name="corp.businessLicenseNum" required="true" />
				
				<label class="nui-form-label">客户类型：</label>
				<input id="corp.customerTypeCd" name="corp.customerTypeCd" data="data" valueField="dictID" 	class="nui-text nui-form-input" dictTypeId="XD_KHCD0098"/>
				
				<label class="nui-form-label">信用等级：</label>
				<input id="" class="nui-text nui-form-input" name="" required="true" />
				
				<label class="nui-form-label">授信金额：</label>
				<input id="" class="nui-text nui-form-input" name="" required="true" />
				
				<label class="nui-form-label">授信余额：</label>
				<input id="" class="nui-text nui-form-input" name="" required="true" />
	</div>			
		
	<div><h5>资金流向信息</h5></div>
	<div class="nui-dynpanel" columns="4" >	
				<label class="nui-form-label">资金流向监控：</label>
				<div><input id="01" name="inspect.cashFlowInspectCd" required="true" class="nui-checkbox nui-form-input" text="已完成" />
				<input id="02" name="inspect.cashFlowInspectCd" required="true" class="nui-checkbox nui-form-input" text="尚未完成" /></div>
				
				<label class="nui-form-label">情况说明：</label>
				<input class="nui-textarea nui-form-input" name="inspect.cashFlowInspectState" required="false" />
			
			
				<label class="nui-form-label">实际流向与计划流向：</label>
				<div><input id="01" name="inspect.realFlowPlanFlowCd" required="true" class="nui-checkbox nui-form-input" text="一致" />
				<input id="02" name="inspect.realFlowPlanFlowCd" required="true" class="nui-checkbox nui-form-input" text="不一致" /></div>
								
				<label class="nui-form-label">情况说明：</label>
				<input class="nui-textarea nui-form-input" name="inspect.realFlowPlanFlowState" required="false" />
								
			
				<label class="nui-form-label">相关交易资料收集情况：</label>
				<div><input id="01" name="inspect.dataCollectionCd" required="true" class="nui-checkbox nui-form-input" text="已收集完成" />
				<input id="02" name="inspect.dataCollectionCd" required="true" class="nui-checkbox nui-form-input" text="尚未收集完成" /></div>
				
				<label class="nui-form-label">情况说明：</label>
				<input class="nui-textarea nui-form-input" name="inspect.dataCollectionState" required="false" />
	</div>			
		
	<div><h5>检查信息</h5></div>
	<div class="nui-dynpanel" columns="4" >		
				<label class="nui-form-label">检查方式：</label>
				<div><input id="01" name="inspect.inspectWayCd" required="true" class="nui-checkbox nui-form-input" text="非现场检查" />
				<input id="02" name="inspect.inspectWayCd" required="true" class="nui-checkbox nui-form-input" text="现场检查" />
				<input id="03" name="inspect.inspectWayCd" required="true" class="nui-checkbox nui-form-input" text="约见检查" /></div>
				
				<label class="nui-form-label">检查地点：</label>
				<input id="inspect.inspectAddress"  class="nui-textbox nui-form-input" name="inspect.inspectAddress" required="true" />
			
				<label class="nui-form-label">检查日期：</label>
				<input id="inspect.inspectDate" class="nui-datepicker nui-form-input" name="inspect.inspectDate"  vtype="maxLength:64" required="true"/>
				
				<label for="isteam$text">检查次数：</label>
				<input id="inspect.inspectCount" class="nui-textbox nui-form-input" name="inspect.inspectCount" required="true" />
	</div>		
		
	<div><h5>银行负债及或有负债情况、他行增贷或退出情况（每期更新）</h5></div>
	<input class="nui-textarea nui-form-input" name="inspect.debtState" required="false" style="width:100%;height:50px;"/>
	<div><h5>地质押物情况</h5></div>
	<input class="nui-textarea nui-form-input" name="inspect.collateralState" required="false" style="width:100%;height:50px;"/>
	<div><h5>担保情况</h5></div>
	<input class="nui-textarea nui-form-input" name="inspect.guarantyState" required="false" style="width:100%;height:50px;"/>
	<div><h5>我行业务合作综合汇报（存款、中收、业务覆盖率、交叉销售、并对综合收益落实情况进行后评价，每期更新）</h5></div>
	<input class="nui-textarea nui-form-input" name="inspect.myBankBizReportState" required="false" style="width:100%;height:50px;"/>
	<div><h5>项目贷款应列项目进度、还款情况（每期更新）</h5></div>
	<input class="nui-textarea nui-form-input" name="inspect.projectRepayState" required="false" style="width:100%;height:50px;"/>
	<div><h5>存在问题、业务机会、风控建议</h5></div>
	<input class="nui-textarea nui-form-input" name="inspect.problemRiskState" required="false" style="width:100%;height:50px;"/>
	
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" id="btnCreate" onclick="add">保存</a>
</div>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#corp");
	var form1 = new nui.Form("#form1");
	
	//查询客户信息。
	function queryCorp(){
		var json = nui.encode({"map/partyId":"<%=request.getParameter("corpid") %>"});
        $.ajax({
            url: "com.bos.aft.dailyInspect.querydailyInspect.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                form.setData(o);
            }
        });
	}
	
	queryCorp();
	
	//保存检查信息
	function add(){
		var json = nui.encode(form1.getData());
		alert(json);
		
		 $.ajax({
            url: "com.bos.aft.dailyInspect.addInspect.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            }
        });
	}
	
	
</script>
</html>