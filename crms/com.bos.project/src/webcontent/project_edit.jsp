<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-5-12 12:42:24
  - Description:
-->
<head>
<title>修改项目信息</title>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<script type="text/javascript"src="<%=contextPath%>/csm/js/commValidate.js"></script>
</head>

<body>
	<div id="form1">
		<input id="tbConProject.projectId" name="tbConProject.projectId"class="nui-hidden" />
			
		<fieldset>
			<legend>
				<span>项目信息</span>
			</legend>
			<div class="nui-dynpanel" id="table1" columns="6">
				
				<label>项目名称：</label> 
				<input id="tbConProject.projectName" name="tbConProject.projectName" required="true"  vtype="maxLength:32"class="nui-textbox nui-form-input" />
						
				<label>项目编号：</label> 
				<input id="tbConProject.secuNum" name="tbConProject.secuNum"enabled="false" required="true" vtype="maxLength:32"class="nui-textbox nui-form-input"  /> 
				 
				<label>信托公司名称：</label> 
				<input id="tbConProject.secuName" name="tbConProject.secuName" required="true" vtype="maxLength:32"class="nui-textbox nui-form-input"  /> 
				 
				<label>项目封包日期：</label>
				<input id="tbConProject.packageDate" name="tbConProject.packageDate"required="true" class="nui-datepicker nui-form-input"  />
					
				<label>项目成立日：</label> 
				<input id="tbConProject.startDate" name="tbConProject.startDate"required="true" class="nui-datepicker nui-form-input"  /> 
				 
				<label>项目到期日期：</label>
				<input id="tbConProject.endDate" name="tbConProject.endDate"required="true" class="nui-datepicker nui-form-input" /> 
				
				<label>资金托管账户：</label> 
				<input id="tbConProject.fundAccount" name="tbConProject.fundAccount" vtype="maxLength:32"class="nui-textbox nui-form-input"  /> 
				
				<label>项目扣划账户开户行：</label> 
				<input id="tbConProject.fundBank"name="tbConProject.fundBank"class="nui-textbox nui-form-input" vtype="maxLength:32" />
				
				<label>资金托管账户名：</label> 
				<input id="tbConProject.fundName" name="tbConProject.fundName" vtype="maxLength:100"class="nui-textbox nui-form-input" /> 
			
				<label>应收款内部账号：</label>
				<input id="tbConProject.inRcvAcc" name="tbConProject.inRcvAcc"vtype="maxLength:32"required="true" onblur="blur"class="nui-textbox nui-form-input" /> 
				  
				<label>应收款内部账号开户行：</label> 
				<input id="tbConProject.inRcvAccDep" name="tbConProject.inRcvAccDep" required="true" allowInput="false" class="nui-buttonEdit"dictTypeId="org"onblur="blur"onbuttonclick="selectOrg"   /> 
				 
				<label>应收款内部账号户名：</label> 
				<input id="tbConProject.inRcvAccName" name="tbConProject.inRcvAccName" required="true"vtype="maxLength:100"onblur="blur"class="nui-textbox nui-form-input" /> 
					
				<label>应付款内部账号：</label> 
				<input id="tbConProject.inPayAcc" name="tbConProject.inPayAcc" required="true"required="true" vtype="maxLength:32"onblur="blur"class="nui-textbox nui-form-input"  /> 
				
				<label>应付款内部账号开户行：</label> 
				<input id="tbConProject.inPayAccDep" name="tbConProject.inPayAccDep"required="true" allowInput="false" class="nui-buttonEdit"dictTypeId="org"onblur="blur"onbuttonclick="selectOrg"     /> 
				
				<label>应付款内部账号户名：</label> 
				<input id="tbConProject.inPayAccName" name="tbConProject.inPayAccName"required="true" vtype="maxLength:100"onblur="blur"class="nui-textbox nui-form-input"  /> 
					
				<label>收本归集-内部账号：</label>
			 	<input id="tbConProject.balInAcc" name="tbConProject.balInAcc" required="true" vtype="maxLength:32"onblur="blur" class="nui-textbox nui-form-input" /> 
				 
				<label>收本归集-内部账号开户行：</label> 
				<input id="tbConProject.balInAccDep" name="tbConProject.balInAccDep" required="true" allowInput="false" class="nui-buttonEdit"dictTypeId="org"onblur="blur"onbuttonclick="selectOrg"   /> 
				
				<label>收本归集-内部账号户名：</label> 
				<input id="tbConProject.balInAccName" name="tbConProject.balInAccName"  required="true"vtype="maxLength:100"onblur="blur"class="nui-textbox nui-form-input"  /> 
				 	
				<label>收息归集-内部账号：</label> 
				<input id="tbConProject.itrInAcc" name="tbConProject.itrInAcc" required="true" vtype="maxLength:32"onblur="blur"class="nui-textbox nui-form-input"  /> 
				
				<label>收息归集-内部账号开户行：</label> 
				<input id="tbConProject.itrInAccDep" name="tbConProject.itrInAccDep"  required="true"allowInput="false" class="nui-buttonEdit"dictTypeId="org"onblur="blur"onbuttonclick="selectOrg"   /> 
				
				<label>收息归集-内部账号户名：</label>
				<input id="tbConProject.itrInAccName" name="tbConProject.itrInAccName" required="true"vtype="maxLength:100"onblur="blur"class="nui-textbox nui-form-input" />
				 
				<label>资产证券化本金账号-内部账号：</label>
				<input id="tbConProject.prnInAcc" name="tbConProject.prnInAcc" required="true" vtype="maxLength:32"onblur="blur"class="nui-textbox nui-form-input"  />
				
				<label>资产证券化本金账号-内部账号开户行：</label> 
				<input id="tbConProject.prnInAccDep" name="tbConProject.prnInAccDep"required="true" allowInput="false" class="nui-buttonEdit"dictTypeId="org"onblur="blur"onbuttonclick="selectOrg"   />
				
				<label>资产证券化本金账号-内部账号户名：</label> 
				<input id="tbConProject.prnInAccName" name="tbConProject.prnInAccName" required="true" vtype="maxLength:100"onblur="blur" class="nui-textbox nui-form-input"  />
				 
				<label>项目状态：</label>
				<input id="tbConProject.projectStatus" name="tbConProject.projectStatus" required="true"class="nui-dictcombobox" enabled="false" dictTypeId="project_status" />

				<label>项目总贷款余额：</label>
				<input id="tbConProject.packBal" name="tbConProject.packBal"enabled="false"class="nui-textbox nui-form-input" dataType="currency" />
				
				<label>备注：</label>
				<input id="tbConProject.remark" name="tbConProject.remark" vtype="maxLength:100"class="nui-textbox nui-form-input" />
			</div>
		</fieldset>
		<div class="nui-toolbar"id="btnProject"style=" text-align: right">
			<a id="btnCheck" class="nui-button"  iconCls="icon-zoomin"style="margin-right: 5px;"onclick="check()">校验账号</a> 
			<a id="btnSave" class="nui-button" style="margin-right: 5px;"iconCls="icon-save" onclick="edit()">保存</a> 
			<a id="btnClose"class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
</div>


	<form id="form2"action="com.bos.project.uploadContractExcel.flow?_eosFlowAction=conUploadExcelAction&projectId=<%=request.getParameter("projectId")%>"
		 method="post"  enctype="multipart/form-data">
			<fieldset>
				<legend>
					<span>项目贷款列表</span>
				</legend>

				<div title="借据列表" id="contract">
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label> 
						<input id="map.orgId" name="map.orgId"allowInput="false" class="nui-buttonEdit"onbuttonclick="selectOrgQuery" />
						<label>客户编号：</label> 
						<input name="map.customerNum" id="map.customerNum" required="false"class="nui-textbox nui-form-input" /> 
						<label>客户名称：</label> 
						<input name="map.customerName" id="map.customerName" required="false" class="nui-textbox nui-form-input" /> 
						<label>借据编号：</label> 
						<input id="map.summaryNum" class="nui-textbox nui-form-input" name="map.summaryNum" onvalidation="" />
						<label>借据起始时间：</label>
						<input id="map.startDate" class="nui-datepicker nui-form-input"name="map.startDate"  /> 
						<label>借据截止时间：</label> 
						<input id="map.endDate" class="nui-datepicker nui-form-input"name="map.endDate"  />
						<label>经办人：</label>
						<input id="map.userNum" name="map.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32"/>
					</div>

				<div class="nui-toolbar"style="text-align: right;">
						<a class="nui-button" iconCls="icon-search" onclick="query()"id="btnQuery"  style="margin-right: 5px;">查询</a> 
						<a class="nui-button" iconCls="icon-reset"onclick="reset()">重置</a>
				</div>
					

					<div class="nui-toolbar"id="btnSummary"style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left">
						<a id="addCust" style="margin-left: 5px" class="nui-button"iconCls="icon-add" onclick="add()">移入</a> 
						<a id="rmove"  class="nui-button"  style="margin-left: 5px"iconCls="icon-remove" onclick="remove()">移出</a> 
						<a id="editCust" class="nui-button" iconCls="icon-edit"onclick="back()">接回</a> 
						<a id="importContract1" style="margin-left: 5px"class="nui-button" iconCls="icon-zoomin"onclick="contractImport()">从excel导入借据号查询</a>
					 	<input style="width:150px;" class="nui-htmlfile" id="Fdata" name="Fdata" limitType="*.xls" />
						<label>（注意:导入借据只需要将所有借据编号放在excel文件Sheet1的第一列）</label>

					</div>
				</div>

			<div id="grid" class="nui-datagrid" sortMode="client"
				url="com.bos.project.project.findContractList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"
				showReloadButton="false" onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="7">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="SUMMARY_NUM" headerAlign="center" align="center" width="10%">借据编号</div>
					<div field="PARTY_NUM" headerAlign="center" align="center">客户编号</div>
					<div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
					<div field="CERT_TYPE" headerAlign="center" align="center"dictTypeId="CDKH0002">证件类型</div>
					<div field="CERT_NUM" headerAlign="center" align="center">证件号码</div>
					<div field="PRODUCT_TYPE" headerAlign="center" align="center"dictTypeId="product">业务品种</div>
					<div field="SUMMARY_AMT" headerAlign="center" align="right"dataType="currency">借据金额</div>
					<div field="JJYE" headerAlign="center" align="right"dataType="currency">借据余额</div>
					<div field="BEGIN_DATE" headerAlign="center" align="center">借据起期</div>
					<div field="END_DATE" headerAlign="center" align="center">借据止期</div>
					<div field="SPEC_PAYMENT_DATE" headerAlign="center" align="center">指定还息日期</div>
					<div field="SUMMARY_STATUS" headerAlign="center" align="center"dictTypeId="YesOrNo">计量是否处理</div>
					<div field="ORG_NUM" headerAlign="center" align="center"dictTypeId="org">经办机构</div>
					<div field="USER_NUM" headerAlign="center" align="center"dictTypeId="user">客户经理</div>
				</div>
			</div>
	</form>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var form2 = new nui.Form("#form2");
		//------------------页面动态控制区------------------//
		var grid = nui.get("grid"); //借据列表
		var checkStatus="2";
		var checkRes="";
		var checkTime=0;
		var projectId="<%=request.getParameter("projectId")%>";
		var view="<%=request.getParameter("view")%>";
		if (view=="0") {//查看
				form.setEnabled(false);
				nui.get("btnSave").hide();//保存，校验账号按钮
				nui.get("btnCheck").hide();//保存，校验账号按钮
				nui.get("btnSummary").hide(); //移入，移出，导入按钮
		}
		query();
		function query() {
		var json = nui.encode({"projectId" : projectId});
			$.ajax({
				url : "com.bos.project.project.findProjectDetail.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					form.setData(text);
				}
			});
			var projectStatus=nui.get("tbConProject.projectStatus").getValue();
			if(projectStatus=="3"){//项目状态为已转出
				nui.get("addCust").hide(); //移入
				nui.get("rmove").hide(); //移出
				nui.get("importContract1").hide(); //导入查询
			}else {
				nui.get("editCust").hide(); //接回
			}
			var o = form2.getData();
			o.map.projectId=projectId;
			grid.load(o);
		}
		
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['SUMMARY_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].SUMMARY_NUM+ '\');">'+e.data[i]['SUMMARY_NUM']+'</a>';
			}
		});
	
		//------------------事件操作区-----------------//
		function edit() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将项目信息填写正确");
			return;
		}
		
		if(checkRes!=""||checkStatus==1){
			nui.alert("请先校验账号");
			return;
		}
		
		//判断借据的止期是否在项目的封包日之后。
		var packageDate=nui.get("tbConProject.packageDate");
		var json1 = {"packageDate":packageDate.getValue().substr(0,10),"projectId":projectId};
			msg = exeRule("PUB_PROJECT_LOAN_DATE2", "1", json1);
			if (null != msg && '' != msg) {
				nui.alert(msg);
				grid.deselect(row);
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
			git.mask("form1");
			var o = form.getData();
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.project.project.updateProject.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						alert(text.msg);
						return;
					} else {
						nui.alert("保存成功！");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
		
	function	 blur(){
		checkStatus="1";
	}
		//移入
		function add() {
			var url = "/project/contract_list.jsp?projectId=" + projectId;
			nui.open({
				url : nui.context + url,
				showMaxButton : true,
				title : "借据信息",
				width : 1200,
				height : 700,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					query();
				}
			});
		}
//移出
		function remove() {
			var row = grid.getSelected();
			if(!row){
				return alert("请选中一条记录");
			}
				nui.confirm(
								"确定移出吗？",
								"确认",
								function(action) {
									if (action != "ok")
										return;
									var json = nui.encode({"summaryId" : row.SUMMARY_ID,"projectId":projectId});
									$.ajax({
												url : "com.bos.project.project.contract_remove.biz.ext",
												type : 'POST',
												data : json,
												cache : false,
												contentType : 'text/json',
												success : function(text) {
													nui.alert("移出成功！");
													query();
												},
												error : function() {
													nui.alert("操作失败！");
												}
											});
								});
		}
		//接回
			function back() {
			var row = grid.getSelected();
			if (row) {
				nui.confirm(
								"确定接回吗？",
								"确认",
								function(action) {
									if (action != "ok")
										return;
									var json = nui.encode({"contractId" : row.CONTRACT_ID,"projectId":projectId});
									$
											.ajax({
												url : "com.bos.project.project.contract_remove.biz.ext",
												type : 'POST',
												data : json,
												cache : false,
												contentType : 'text/json',
												success : function(text) {
													nui.alert(text.msg);
													query();
												},
												error : function() {
													nui.alert("操作失败！");
												}
											});
								});
			} else {
				nui.alert("请选中一条记录");
			}
		}
		
    
    //移入借据查询
    function contractImport() {
   		var frm = document.getElementById("form2");
		var o=form2.getData();
    	if(!nui.get("Fdata").value){
			nui.alert("请选择文件");
			return;
		}
		if(/\.xls?$/g.test(nui.get("Fdata").value) == false){
			nui.alert("请选择.xls文件");
			return;
		}
		frm.submit();
    }
   
		// 经办人
	function selectCustManegers(e) {
			var btnEdit = this;
			nui.open({
						url : nui.context
								+ "/pub/orgDemolition/creditMove/userManageALL.jsp",
						showMaxButton : true,
						title : "选择经办人",
						width : 800,
						height : 500,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								data = nui.clone(data);
								if (data) {
									//  alert(nui.encode(data));
									btnEdit.setValue(data.userId);
									btnEdit.setText(data.empName);
								}
							}
						}
					});
	}
		//机构选择
		function selectOrg() {
			var btnEdit = this;
			nui.open({
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
		//查询机构选择
			function selectOrgQuery() {
			var btnEdit = this;
			nui.open({
						url : nui.context
								+ "/pub/sys/select_org_tree.jsp",
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
									btnEdit.setValue(data.orgid);
									//btnEdit.setValue(data.orgcode);
									btnEdit.setText(data.orgname);
								}
							}
						}
					});
		}
			//重置
		function reset() {
			form2.reset();
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
							checkRes+=accTypes+":"+message+" ";
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
							checkRes +=accTypes+":"+"账户名与账号不匹配!  ";
							//	nui.get(accIds).setValue('');
						}
						if (orgid != accDeps) {
							checkRes +=accTypes+":"+ "账户开户机构与账号不匹配!  ";
						}
// 						if (currcd != '156') {
// 							checkRes += accTypes+":"+"账户币种必须为人民币!  ";
// 						}
						if(null!=currcd&&""!=currcd){
							var json1 = {"projectId" : projectId,"summaryCurrencyCd":currcd};
							msg = exeRule("PUB_PROJECT_RELATION", "1", json1);
							if (null != msg && '' != msg) {//判断项目下是否有借据，才校验币种
								msg = exeRule("PUB_PROJECT_CURRENCY", "1", json1);
								if (null == msg || '' == msg) {
									checkRes += accTypes+":"+"项目下的借据币种与账号币种不一致!  ";
								}
							}
						}else{
							checkRes += accTypes+":"+"核心系统币种为空!  ";
						}
						
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

