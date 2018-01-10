<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-6 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<fieldset>
	  <legend>
	    <span>白名单客户信息</span>
	   </legend>
<input name="item.customerId" id="item.customerId" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input id="item.cusName" name="item.cusName" required="true" class="nui-textbox nui-form-input" />
			
			<label>ECIF客户编号：</label>
			<input id="item.ecifPartyNum" name="item.ecifPartyNum"  required="true"  class="nui-textbox nui-form-input" />	
		
			<label>手机号码：</label>
			<input id="item.phoneNum" name="item.phoneNum" required="true"  class="nui-textbox nui-form-input" />
			
			<label>职级：</label>
			<input id="item.jobRank" required="true" name="item.jobRank" allowInput="false"  class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD2004"  />
			
			<label>收入：</label>
			<input id="item.income" name="item.income"  required="true"  class="nui-textbox nui-form-input"  />
			
			<label>利率：</label>
			<input id="item.rate" required="true" name="item.rate" class="nui-textbox nui-form-input" />
			
			<label>最高额度：</label>
			<input id="item.totalLimit" name="item.totalLimit"  required="true"  class="nui-textbox nui-form-input"  />
			
			<label>备注：</label>
			<input id="item.remark" name="item.remark"  class="nui-textbox nui-form-input"  />
		</div>
		 </fieldset>
		<div class="nui-toolbar" style="border:0;text-align:right;padding-right:">
			<a class="nui-button" iconCls="icon-save" id="saveOK" onclick="save()">保存</a>
			<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>	
	</div>  

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	
	var customerId = "<%=request.getParameter("customerId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		if(qote==0){
		form.setEnabled(false);//不能修改
		nui.get("saveOK").hide();
		}
		
		function initForm(){
			
	  		var json=nui.encode({"item":{"customerId":customerId,"_entity":"com.bos.dataset.csm.TbCsmWhiteCustomer"}});
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
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
		
		if(customerId!='null'){
			initForm();
		}
		
		function save() {
           form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			git.mask("form1");
			var o = form.getData();
			var json=nui.encode(o)+nui.encode({"item":{"customerId":customerId}});
			$.ajax({
		            url: "com.bos.csm.natural.natural.updateWhiteCustomer.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		          		git.unmask("form1");
		            	if(text.msg){
		            		alert(text.msg);
		            	} else {
		            		alert("保存成功!");
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