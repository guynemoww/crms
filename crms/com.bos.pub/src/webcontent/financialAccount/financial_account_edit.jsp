<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<head>
<title>财政贴息账号</title>
</head>
<body>
	<div id="form1" style="width: 100%; height: 100%; overflow: hidden;">
			<input name="financialAccount.id"id="financialAccount.id"   class="nui-hidden" />
	
		<fieldset>
			<legend>
				<span>财政贴息账号</span>
			</legend>
			<input name="financialAccount.tiexiOrgNum"id="financialAccount.tiexiOrgNum"    class="nui-hidden"  />
			<input name="financialAccount.accType"id="financialAccount.accType"    class="nui-hidden"  />
			<div class="nui-dynpanel" id="table1" columns="2">
				<label>机构名称：</label> 
				<input name="financialAccount.orgNum"id="financialAccount.orgNum"  required="true" allowInput="false"  class="nui-buttonEdit"dictTypeId="org" onbuttonclick="selectOrgQuery" />
				<label>贴息主体：</label>
				<input id="financialAccount.tiexiZt" name="financialAccount.tiexiZt" 	required="true" vtype="maxLength:100" class="nui-textbox nui-form-input" />
				<label>贴息账号：</label> 
				<input id="financialAccount.tiexiZh" name="financialAccount.tiexiZh" required="true" vtype="float;maxLength:32"class="nui-textbox nui-form-input" />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="border: 0; text-align: center; padding-right: 20px;">
			<a id="btnSave" class="nui-button" style="margin-right: 5px;" iconCls="icon-save" onclick="update">保存</a> 
			<a id="btnClose" class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var id="<%=request.getParameter("id")%>";
		var view="<%=request.getParameter("view")%>";
		if(view=="0"){
			form.setEnabled(false);
			nui.get("btnSave").hide();//保存按钮
		}
		//初始化表格数据
		$(document).ready(function() {
			var json = nui.encode({"id" : id});
			$.ajax({
				url : "com.bos.pub.financialAccount.financialAccount.findFinancialAccountDetail.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					form.setData(text);
					//备份数据
					window.form1Data = form.getData();
				}
			});
		});
		
		//修改操作
		function update() {
			form.validate();
			if (form.isValid() == false) {
				return alert("请按规则填写信息");
			}
			var json1 = {"orgNum":nui.get("financialAccount.orgNum").getValue(),"tiexiZh":nui.get("financialAccount.tiexiZh").getValue()};
	   	    msg = exeRule("PUB_0030","1",json1);
	   	    if(null != msg && '' != msg){//一个机构不能重复关联同一账号
	   	    	nui.alert(msg);
	   	   		return;
	   	    }
			var errorMsg="";
			var json = nui.encode({"acctInd" : nui.get("financialAccount.tiexiZh").getValue()});
			$.ajax({
				url : "com.bos.accInfo.accInfo.queryAccNew.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				async : false,
				success : function(text) {
					if (text.msg != '查询成功') {
						errorMsg=text.msg;
						return;
					}
					var cusName = text.queryAcc.cstNm.trim();//账户名
					var orgNum = text.queryAcc.rgonCd + text.queryAcc.branchId;//开户机构
					var storeCd=text.queryAcc.storeCd;	//科目存储	0-对公活期1-对私活期2-定期3-贷款4-内部 5-表外
					if("0"==storeCd||"4"==storeCd){
						nui.get("financialAccount.tiexiOrgNum").setValue(orgNum);
						nui.get("financialAccount.accType").setValue(storeCd);
					}else{
						errorMsg="账户类型不符合要求";
						return;
					}
					if(cusName!=nui.get("financialAccount.tiexiZt").getValue()){
						errorMsg="贴息主体与账户名称不一致";
						return;
					}
				}
			});
			if(errorMsg!=""){
				alert(errorMsg);
				return;
			}
				var o = form.getData();
				var json = nui.encode(o);
						$.ajax({
						url : "com.bos.pub.financialAccount.financialAccount.updateFinancialAccount.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								nui.alert(text.msg);
							} else {
								nui.alert("修改成功!","提示",function(action){
						    		CloseWindow();
						  	 	});
							}
						},
						error : function() {
							nui.alert("操作失败！");
						}
					});
		}
	//查询机构选择
	function selectOrgQuery() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
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
	
	</script>
</body>
</html>

