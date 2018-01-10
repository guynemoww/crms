<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-5-9
  - Description:com.bos.dataset.pub.TbPubRepayAccount-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item._entity" value="com.bos.dataset.pub.TbPubRepayAccount" class="nui-hidden" />
	<div class="nui-dynpanel" id="table1" columns="2">
		<label>机构名称：</label> 
		<input name="item.repayAccountOrgNum"id="item.repayAccountOrgNum"  required="true" allowInput="false"  class="nui-buttonEdit" onbuttonclick="selectOrgQuery" dictTypeId="org"/>
		<label>指定还款账号：</label> 
		<div>
		<input id="item.repayAccount" name="item.repayAccount" required="true" vtype="float;maxLength:30"class="nui-textbox nui-form-input"onkeydown="acctChange" />
		<a class="nui-button" iconCls="icon-zoomin" onclick="query()" id="btnQuery">查询</a>
		</div>
		<label>指定还款账户户名：</label>
		<input id="item.repayAccountName" name="item.repayAccountName" 	enabled="false"required="true" vtype="maxLength:150" class="nui-textbox" />
	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
			
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
    var view="<%=request.getParameter("view") %>";
    var id = "<%=request.getParameter("id")%>";
	if (view=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
		nui.get("btnQuery").hide();
	}
	if(id!='null'){
		initForm();
		nui.get("item.repayAccountOrgNum").setEnabled(false);
	}
	function initForm() {
		var json=nui.encode({"item":{"id":"<%=request.getParameter("id") %>",
			"_entity":"com.bos.dataset.pub.TbPubRepayAccount"}});
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           		git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	function save() {
		form.validate();
		if (form.isValid() == false||nui.get("item.repayAccountName").getValue()=="") {
			alert("请将信息填写完整");
			return;
		}
		var o = form.getData();
		if(id!='null'){//编辑时
			o.item.id=id;
		}else{//新增时
			o.item.createTime="<%=GitUtil.getBusiDateStr()%>";
			var json ={"repayAccountOrgNum" : nui.get("item.repayAccountOrgNum").getValue()};
			msg = exeRule("PUB_0021", "1", json);
			if (null != msg && '' != msg) {
				nui.alert(msg);
				return;
			}
		}
		o.item.userNum="<%=((UserObject) session.getAttribute("userObject")).getUserId()%>";
		o.item.orgNum="<%=((UserObject) session.getAttribute("userObject")).getAttributes().get("orgcode")%>";
		o.item.updateTime="<%=GitUtil.getBusiDateStr()%>";
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.csm.pub.crudCustInfo.saveUpdateItem.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
				if (text.msg) {
					alert(text.msg);
				} else {
					alert("保存成功!");
					CloseWindow("ok");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		});
	}
	function query() {
		if (nui.get("item.repayAccount").getValue()=="") {
			alert("请先填写账号");
			return;
		}
		var json = nui.encode({
			"acctInd" : nui.get("item.repayAccount").getValue()
		});
		var AccNo = nui.get("item.repayAccount").getValue();
		var subNo = AccNo.substring(4,5);
		if(subNo == '8' || subNo == '9'){
			 $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAcc2.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var message = text.msg;
	        	var code = text.code;
	        	if(code != 'AAAAAAA'){
	        		nui.alert(message);
	        		return;
	        	}else{
	        		nui.alert("查询成功！");
	        		return;
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
		}else{
			$.ajax({
			url : "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			async : false,
			success : function(text) {
				var code = text.code;
				if (code != 'AAAAAAA') {
					return alert("指定还款账号查询核心失败:" + text.msg);
				}
				var cusName = text.hxresponse.oxd052ResBody.custName;//账户名
				var orgNum = text.hxresponse.oxd052ResBody.openBrch;//开户机构
				if (orgNum != nui.get("item.repayAccountOrgNum").getValue()) {
					return alert("指定还款账号与机构不匹配");
				}
				nui.get("item.repayAccountName").setValue(cusName);
			}
		});
		}
	}
	//机构选择
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
	
	function acctChange(){
		nui.get("item.repayAccountName").setValue();
	}
</script>
</body>
</html>
