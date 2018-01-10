<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-06 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<div id="form1" clsss="nui-form"style="width: 99.9%; height: auto; overflow: hidden;">
				<input type="hidden" name="item._entity"value="com.bos.dataset.csm.TbCsmCreditInfo" class="nui-hidden" />
				<input name="item.partyId" id="item.partyId" value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
		
		<fieldset>
  		<legend>
    		<span>信用信息</span>
    	</legend>
				<div class="nui-dynpanel" columns="4">
					<label>记录来源：</label> 
					<input id="item.infoSrc" required="ture" name="item.infoSrc"   class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD2008" /> 
					
					<label>查询日期：</label> 
					<input id="item.queryDate" required="ture" name="item.queryDate" maxDate="<%=GitUtil.getBusiDateStr() %>" class="nui-datepicker nui-form-input" allowInput="false" format="yyyy-MM-dd" /> 
				</div>
		</fieldset>
			
		<fieldset>
  		<legend>
    		<span>截至贷款申请日24个月内信用记录</span>
    	</legend>
				<div id="djk"class="nui-dynpanel" columns="4">
					<label>贷记卡及准贷记卡连续逾期次数：</label> 
					<input id="item.djkLxyqTimes" name="item.djkLxyqTimes" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0255"/> 
					
					<label>贷记卡及准贷记卡累计逾期次数：</label> 
					<input id="item.djkLjyqTimes" name="item.djkLjyqTimes" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0256"/> 
				</div>
					
				<div class="nui-dynpanel" columns="4">
					<label>贷款连续欠息次数：</label> 
					<input id="item.dkLxqxTimes" name="item.dkLxqxTimes" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0255"/> 
					
					<label>贷款累计欠息次数：</label> 
					<input id="item.dkLjqxTimes" name="item.dkLjqxTimes" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0256"/> 
					
					<label>贷款本金逾期情况：</label> 
					<input id="item.sxbjYqCondition" name="item.sxbjYqCondition" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="HaveOrNot"/> 
					<label>在工商税务等其他部门存在不良记录：</label> 
					<input id="item.branchbadrecord" name="item.branchbadrecord" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/> 
					
					<label>被银监部门列为预警单位：</label> 
					<input id="item.isalertcorp" name="item.isalertcorp" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/> 
										<label>存在在诉案件或败诉案件：</label> 
					<input id="item.havelawsuit" name="item.havelawsuit" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/> 
					
					<label>出资人或高管人员存在不良信用记录：</label> 
					<input id="item.badleaderflag" name="item.badleaderflag" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/> 
				</div>
		</fieldset>
		
			<div class="nui-toolbar"
				style="border: 0; text-align: right; padding-right:">
				<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a> <a
					class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
		</div>


	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var partyId = "<%=request.getParameter("partyId")%>";
		var id = "<%=request.getParameter("id")%>";
		var partyTypeCd= "<%=request.getParameter("partyTypeCd")%>" ;
		if (partyTypeCd == "01") {
			nui.get("djk").hide();
		} else {
			nui.get("djk").show();
		}
		if(id!='null'){
			initForm();
		}
		function initForm() {
			var json = nui.encode({
				"item" : {"id" : "<%=request.getParameter("id")%>","_entity":"com.bos.dataset.csm.TbCsmCreditInfo"}});
			$.ajax({
				url : "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(mydata) {
					git.unmask("form1");
					var o = nui.decode(mydata);
					form.setData(o);
					oldData = form.getData();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}


		function save() {
			form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			git.mask("form1");
			var o = form.getData();
			if(id!='null'){
				o.item.id=id;
			}
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
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
	</script>

</body>
</html>