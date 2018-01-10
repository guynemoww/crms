 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 钟辉
  - Date: 2015-08-29
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<script type="text/javascript" src="<%=contextPath %>/biz/biz_js/biz.js"></script>
	<title>拒贷页面</title>
</head>
<body>
	<div id="form1" >
		<input name="item.applyId" id="applyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>拒绝方：</label>
			<input name="item.refuse" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0140"  onvaluechanged="refuseType"/>
			
			<label>拒绝原因：</label>
			<input name="item.refuseReason" id="refuseReason" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0141" />
			
			<label>备注：</label>
			<textarea name="item.remark" required="true" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnsave">保存</a>
	</div>
		    
	<script type="text/javascript">
		nui.parse();
		var form1 = new nui.Form("#form1");
		
		var view="<%=request.getParameter("proFlag") %>";
		var applyId="<%=request.getParameter("applyId") %>";
		
		function initPage(view){
			nui.get("applyId").setValue(applyId);
			//查看   屏蔽按钮和可编辑状态
			if(view=="-1"){
				form1.setEnabled(false);
				nui.get("btnsave").hide();
			}
			var json=nui.encode({"item":{"applyId":applyId,"_entity":"com.bos.dataset.biz.TbBizRefuseInfo"}});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtManage.mortgageCURD.getMortgage.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		var o=nui.decode(text);
					form1.setData(o);
					nui.get("applyId").setValue(applyId);
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
		
		
		initPage(view);
		/**
		 * 保存
		 */
		function save() {
			if (form1.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form1.getData();
			var json=nui.encode(o);
			 $.ajax({
		        url: "com.bos.bizInfo.bizInfo.saveRefuseInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	alert(text.msg);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			}); 
		}
		
		function refuseType(e){
			if(e.value=='1'){	//银行
				nui.get("refuseReason").setData(getDictData("XD_YWDB0141","str","1,2,3,4,5,6,7"));
			}else{				//客户
				nui.get("refuseReason").setData(getDictData("XD_YWDB0141","str","8,9,10,11,12,13"));
			}
		}
	</script>
</body>
</html>
