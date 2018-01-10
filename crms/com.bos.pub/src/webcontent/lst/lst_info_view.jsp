<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-5-6 16:17:00
  - Description:
-->
<head>
<title>名单制查看</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div style="width:99.5%">
	<fieldset>
	  <legend>
	    <span>当前状态 </span>
	   </legend>
			<div id="form1" style="width:100%;height:auto;overflow:hidden;align:center" >
			   <input name="info.partyId" id="info.partyId" class="nui-hidden" />
					<div class="nui-dynpanel" columns="4">
						<label>客户名称：</label> 
						<input id="party.partyName"  name="party.partyName" class="nui-textbox nui-form-input" enabled="false"/>
						<label>证件类型：</label> 
						<input id="result.certType"  name="result.certType" class="nui-textbox nui-form-input" enabled="false"/>
						<label>证件号码：</label> 
						<input id="result.certNum"  name="result.certNum" class="nui-textbox nui-form-input" enabled="false"/>
						<label>分类：</label> 
						<input id="result.clsResult"  name="result.clsResult" class="nui-textbox nui-form-input" enabled="false"/>
						<label>监控名单类型：</label> 
						<input id="info.listStatus"  name="info.listStatus" class="nui-textbox nui-form-input" enabled="false"/>
						<label>判断依据：</label> 
						<input id="info.pdYj"  name="info.pdYj" class="nui-textbox nui-form-input" enabled="false"/>
						<label>加入日期：</label> 
						<input id="info.createDate"  name="info.createDate" class="nui-textbox nui-form-input" enabled="false"/>
						<label>经办人：</label> 
						<input id="info.operUserid"  name="info.operUserid" class="nui-text nui-form-input"dictTypeId="user" enabled="false"/>
					</div>
			</div>
				<fieldset>
					<legend>
					  <span>历史记录</span>
					</legend>
					<div id="datagrid1" class="nui-datagrid"
						style="width: 99.5%; height: 200" allowAlternating="true" 
						url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
						allowResize="false" showReloadButton="false"
						sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
						sortMode="client">
						<div property="columns">
							<div field="changeDate" allowSort="true" width="" headerAlign="center"
								autoEscape="false" dateformat="yyyy-MM-dd">变更日期</div>
							<div field="changeBeforStatus" allowSort="true" width="" headerAlign="center"
								dictTypeId="XD_MDCD0001">变更前状态</div>
							<div field="changeAfterStatus" allowSort="true" width="20%"
								headerAlign="center" dictTypeId="XD_MDCD0001">变更后状态</div>
							<div field="changeReson" allowSort="true" width="" headerAlign="center" >变更原因</div>
							<div field="operUserid" allowSort="true" width="" headerAlign="center"
								dictTypeId="user">经办人</div>
						</div>
					</div>
				</fieldset>



<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var partyId = '<%=request.getParameter("partyId")%>';
	function queryInit() {
		var o = form.getData();//逻辑流必须返回total
		o.item = new Object();
		o.item.partyId = '<%=request.getParameter("partyId")%>';
		o.sqlName = 'com.bos.lst.lst.changeList';
		grid.load(o);
	}
	
	<%--  数据初始化--%>
	function initForm(){
	  var json = nui.encode({"info":{"partyId":"<%=request.getParameter("partyId")%>"},"party":{"partyId":"<%=request.getParameter("partyId")%>"}});
	  $.ajax({
			  url: "com.bos.pub.lst.lst.getInfo.biz.ext",
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
	          		 git.unmask("form1");
	                 var o = nui.decode(mydata);
	                 form.setData(o);
	                 debugger;
//	                 nui.get('info.operOrgcode').setValue(nui.getDictText('org',o.info.operOrgcode));
	                 nui.get('info.listStatus').setValue(nui.getDictText('XD_MDCD0001',o.info.listStatus));
	                 nui.get('result.certType').setValue(nui.getDictText('CDKH0002',o.result.certType));
//	                 nui.get('info.operUserid').setValue(nui.getDictText('user',o.info.operUserid));
	                 queryInit();
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
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.saveItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
    
  </script>
  
  
</body>
</html>