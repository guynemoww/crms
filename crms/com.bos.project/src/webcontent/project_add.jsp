<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-9-10 12:42:24
  - Description:
-->
<head>
<title>新增项目向导</title>
<%@page
	import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>

</head>
<body>
	<div id="form1" style="width: 99.5%; height: 99.5%; overflow: hidden;">
		<fieldset>
			<legend>
				<span>项目信息</span>
			</legend>
			<div class="nui-dynpanel" id="table1" columns="6">
				<label>项目名称：</label> <input id="tbConProject.projectName"
					name="tbConProject.projectName" required="true" vtype="maxLength:32"
					class="nui-textbox nui-form-input" />
				
				<label>备注：</label>
				<input id="tbConProject.remark" name="tbConProject.remark" vtype="maxLength:100"
					 class="nui-textbox nui-form-input" />
						
				 
				 <label>信托公司名称：</label> <input
					id="tbConProject.secuName" name="tbConProject.secuName" required="true" vtype="maxLength:100"
					class="nui-textbox nui-form-input"  /> 
				
				 <label>项目封包日期：</label>
				<input id="tbConProject.packageDate" name="tbConProject.packageDate"
					required="true" class="nui-datepicker nui-form-input" />
				<label>项目成立日：</label> <input
					id="tbConProject.startDate" name="tbConProject.startDate" required="true"
				 class="nui-datepicker nui-form-input" /> 
					 <label>项目到期日期：</label>
				<input id="tbConProject.endDate" name="tbConProject.endDate"
					required="true" class="nui-datepicker nui-form-input" /> 
				
					 <label>资金托管账户：</label> <input
					id="tbConProject.fundAccount" name="tbConProject.fundAccount" vtype="maxLength:32" 
					class="nui-textbox nui-form-input"  /> 
					<label>项目扣划账户开户行：</label> <input
					id="tbConProject.fundBank" name="tbConProject.fundBank"	class="nui-textbox nui-form-input"
					vtype="maxLength:32" 	/>
				  <label>资金托管账户名称：</label> <input
					id="tbConProject.fundName" name="tbConProject.fundName" vtype="maxLength:100"
					class="nui-textbox nui-form-input" /> 
				
				   <label>应收款内部账号：</label> <input
					id="tbConProject.inRcvAcc" name="tbConProject.inRcvAcc" required="true" vtype="maxLength:32"
					class="nui-textbox nui-form-input" /> 
				   <label>应收款内部账号开户行：</label> <input
					id="tbConProject.inRcvAccDep" name="tbConProject.inRcvAccDep" required="true" allowInput="false" class="nui-buttonEdit"	dictTypeId="org"
						onbuttonclick="selectOrg" /> 
				  <label>应收款内部账号户名：</label> <input
					id="tbConProject.inRcvAccName" name="tbConProject.inRcvAccName"  vtype="maxLength:100"required="true" 
					class="nui-textbox nui-form-input" /> 
				 
				   <label>应付款内部账号：</label> <input
					id="tbConProject.inPayAcc" name="tbConProject.inPayAcc" vtype="maxLength:32" required="true"
					class="nui-textbox nui-form-input"  /> 
				   <label>应付款内部账号开户行：</label> <input
					id="tbConProject.inPayAccDep" name="tbConProject.inPayAccDep"required="true"  allowInput="false" class="nui-buttonEdit"	dictTypeId="org"
						onbuttonclick="selectOrg"  /> 
				   <label>应付款内部账号户名：</label> <input
					id="tbConProject.inPayAccName" name="tbConProject.inPayAccName" vtype="maxLength:100"required="true" 
					class="nui-textbox nui-form-input"  /> 
				 
				 	<label>收本归集-内部账号：</label> <input
					id="tbConProject.balInAcc" name="tbConProject.balInAcc" required="true" vtype="maxLength:32"
					class="nui-textbox nui-form-input" /> 
				  <label>收本归集-内部账号开户行：</label> <input
					id="tbConProject.balInAccDep" name="tbConProject.balInAccDep"required="true"  allowInput="false" class="nui-buttonEdit"	dictTypeId="org"
						onbuttonclick="selectOrg" /> 
				 <label>收本归集-内部账号户名：</label> <input
					id="tbConProject.balInAccName" name="tbConProject.balInAccName"vtype="maxLength:100"required="true" 
				 	class="nui-textbox nui-form-input"  /> 
				 
				  <label>收息归集-内部账号：</label> <input
					id="tbConProject.itrInAcc" name="tbConProject.itrInAcc" required="true" vtype="maxLength:32"
					class="nui-textbox nui-form-input"  /> 
				   <label>收息归集-内部账号开户行：</label> <input
					id="tbConProject.itrInAccDep" name="tbConProject.itrInAccDep"allowInput="false" required="true" class="nui-buttonEdit"	dictTypeId="org"
						onbuttonclick="selectOrg"/> 
				    <label>收息归集-内部账号户名：</label> <input
					id="tbConProject.itrInAccName" name="tbConProject.itrInAccName"  vtype="maxLength:100"required="true" 
					class="nui-textbox nui-form-input" />
				 
				 <label>资产证券化本金账号-内部账号：</label> <input
					id="tbConProject.prnInAcc" name="tbConProject.prnInAcc" required="true" vtype="maxLength:32"
					class="nui-textbox nui-form-input"  />
				  <label>资产证券化本金账号-内部账号开户行：</label> <input
					id="tbConProject.prnInAccDep" name="tbConProject.prnInAccDep" required="true" allowInput="false" class="nui-buttonEdit"	dictTypeId="org"
						onbuttonclick="selectOrg" />
				   <label>资产证券化本金账号-内部账号户名：</label> <input
					id="tbConProject.prnInAccName" name="tbConProject.prnInAccName"  vtype="maxLength:100"required="true" 
					class="nui-textbox nui-form-input"  />
				 
				
			</div>
		</fieldset>
		<div class="nui-toolbar"
			style="border: 0; text-align: right; padding-right: 20px;">
			
				<a id="btnCheck" class="nui-button"  iconCls="icon-zoomin"style="margin-right: 5px;"
				iconCls="icon-save" onclick="check()">校验账号</a> 
				
			<a id="btnSave" class="nui-button" style="margin-right: 5px;"
				iconCls="icon-save" onclick="add">保存</a> 
				
				<a id="btnClose"
				class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>

</div>
	<script type="text/javascript">
		nui.parse();
		
		var checkStatus="1";
		var checkRes="";
		var checkTime=0;
		var form = new nui.Form("#form1");
		
		//机构选择
		function selectOrg() {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/utp/org/employee/select_all_org_tree.jsp",
						showMaxButton : true,
						title : "选择机构",
						width : 350,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.GetData();
								data = nui.clone(data);
								if (data) {
									self.orglevel = data.orglevel;
									btnEdit.setValue(data.orgcode);
									btnEdit.setText(data.orgname);
								}
							}
						}
					});
		}

		//------------------事件操作区-----------------//
		function add() {
			//校验
		
		form.validate();
			if (form.isValid() == false) {
				return;
			}
			
		if(checkRes!=""||checkStatus==1){
			nui.alert("请先校验账号");
			return;
		}		
	
			var date = "<%=GitUtil.getBusiDateStr()%>";
			var packageDate = nui.get("tbConProject.packageDate").getValue().substr(0,10);
			var startDate = nui.get("tbConProject.startDate").getValue().substr(0,10);
			var endDate = nui.get("tbConProject.endDate").getValue().substr(0,10);
			//alert("项目到期日:"+endDate+"项目成立日:"+startDate+"当前日期:"+date+"项目封包日:"+packageDate);
			if (endDate > startDate&& startDate >= date && date >= packageDate ) {
			} else {
				alert("项目日期必须满足：项目到期日>项目成立日>=当前日期>=项目封包日");
				return;
			}
			var o = form.getData();
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.project.project.addProject.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
					} else {
						nui.alert("保存成功!","提示",function(action){
				    		CloseWindow();
				  	 	});
					}
				},
				error : function() {
					nui.alert("操作失败！");
				}
			});
		}
		
		
		function check(){
		
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将项目信息填写正确");
			return;
		}
		
		
		
		
			var inRcvAcc=nui.get("tbConProject.inRcvAcc");
			var inRcvAccDep=nui.get("tbConProject.inRcvAccDep");
			var inRcvAccName=nui.get("tbConProject.inRcvAccName");
			
			var inPayAcc=nui.get("tbConProject.inPayAcc");
			var inPayAccDep=nui.get("tbConProject.inPayAccDep");
			var inPayAccName=nui.get("tbConProject.inPayAccName");
			
			var balInAcc=nui.get("tbConProject.balInAcc");
			var balInAccDep=nui.get("tbConProject.balInAccDep");
			var balInAccName=nui.get("tbConProject.balInAccName");
			
			var itrInAcc=nui.get("tbConProject.itrInAcc");
			var itrInAccDep=nui.get("tbConProject.itrInAccDep");
			var itrInAccName=nui.get("tbConProject.itrInAccName");			
			
			var prnInAcc=nui.get("tbConProject.prnInAcc");
			var prnInAccDep=nui.get("tbConProject.prnInAccDep");
			var prnInAccName=nui.get("tbConProject.prnInAccName");
			
			var accNums=[inRcvAcc.getValue(),inPayAcc.getValue(),balInAcc.getValue(),itrInAcc.getValue(),prnInAcc.getValue()];
			var accDeps=[inRcvAccDep.getValue(),inPayAccDep.getValue(),balInAccDep.getValue(),itrInAccDep.getValue(),prnInAccDep.getValue()];
			var accNames=[inRcvAccName.getValue(),inPayAccName.getValue(),balInAccName.getValue(),itrInAccName.getValue(),prnInAccName.getValue()];
			var accTypes=["应收款内部账户","应付款内部账户","收本归集-内部账户","收息归集-内部账户","资产证券化本金账号-内部账户"];
			var accIds=["tbConProject.inRcvAcc","tbConProject.inPayAcc","tbConProject.balInAcc","tbConProject.itrInAcc","tbConProject.prnInAcc"];
			checkStatus="2";
			checkRes="";
			checkTime=0;
			checkAcc(inRcvAcc.getValue(),inRcvAccDep.getValue(),inRcvAccName.getValue(),"应收款内部账户","1");
			checkAcc(inPayAcc.getValue(),inPayAccDep.getValue(),inPayAccName.getValue(),"应付款内部账户","2");
			checkAcc(balInAcc.getValue(),balInAccDep.getValue(),balInAccName.getValue(),"收本归集-内部账户","3");
			checkAcc(itrInAcc.getValue(),itrInAccDep.getValue(),itrInAccName.getValue(),"收息归集-内部账户","4");
			checkAcc(prnInAcc.getValue(),prnInAccDep.getValue(),prnInAccName.getValue(),"资产证券化本金账号-内部账户","5");
		}
		
		function checkAcc(accNums,accDeps,accNames,accTypes,accIds){
				var json = nui.encode({"acctInd" : accNums});
				$.ajax({
					url : "com.bos.accInfo.accInfo.queryAccNew.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						checkTime=checkTime+1;
						var message = text.msg;
						if (message != '查询成功') {
							checkRes+=accTypes+":"+message+"    ";
							checkStatus="1";
 							if(checkTime==5){//最后一次校验才弹出信息
								alert(checkRes);
							}
							return;
						}
						var cusName = text.queryAcc.cstNm;//帐户名
						var currcd = text.queryAcc.ccyTp;//币种
						var orgid = text.queryAcc.rgonCd + text.queryAcc.branchId;//开户机构
						if (cusName.trim() != accNames) {
							checkRes +=accTypes+":"+"账户名与账号不匹配!     ";
							//	nui.get(accIds).setValue('');
						}
						if (orgid != accDeps) {
							checkRes +=accTypes+":"+ "账户开户机构与账号不匹配!     ";
						}
// 						if (currcd != '156') {
// 							checkRes += accTypes+":"+"账户币种必须为人民币!  ";
// 						}
						if(checkRes!=""){
							checkStatus="1";
							if(checkTime==5){
								alert(checkRes);
							}
						}
						
						if(checkRes==""&&checkTime==5){//最后一次进行校验，显示校验成功
							alert("校验成功");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						nui.alert(jqXHR.responseText);
					}
				});
		}
	</script>
</body>
</html>

