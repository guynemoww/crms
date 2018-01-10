<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28

  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item.relationId" id="relationId" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.biz.TbBizGrtRelation" class="nui-hidden" />
	<input name="tbGrtGuarantybasic.suretyId" id="tbGrtGuarantybasic.suretyId" class="nui-hidden" />
	<input type="hidden" name="item.reType" id="reType" value="02" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>抵质押人名称：</label>
		<input id="dzy" name="tbGrtGuarantybasic.partyName" required="false"   class="nui-textbox nui-form-input"  />
		
		<label>抵质押物名称：</label>
		<input name="tbGrtGuarantybasic.guarantyName" required="false" class="nui-textbox nui-form-input"  />
		
		<label>我行确认价值：</label>
		<input name="tbGrtGuarantybasic.myBankSureCost" required="false" class="nui-textbox nui-form-input"  />
		
		<label>我行已设定担保额：</label>
		<input name="tbGrtGuarantybasic.myBankSet" required="false" class="nui-textbox nui-form-input"  />
		
		<label>抵质押率：</label>
		<input name="tbGrtGuarantybasic.mpRate" required="false" class="nui-textbox nui-form-input"  />
		
		<label>本次担保债权金额：</label>
		<input id="item.occupationAmount" vtype="float;maxLength:26"  name="item.occupationAmount"  required="true" class="nui-textbox nui-form-input"  />

		<label>本次占用价值：</label>
		<input id="item.debtAmount" vtype="float;maxLength:26" name="item.debtAmount" required="true" class="nui-textbox nui-form-input"   />
		
		<label>备注：</label>
		<input name="tbGrtGuarantybasic.remark" rowspan="3" colspan="3" name="" required="false" vtype="maxLength:200" class="nui-textarea nui-form-input"  />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<%--<a class="nui-button"  iconCls="icon-add" onclick="open()" id="btnchange" >替换抵质押</a>--%>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    <%--nui.get("btnchange").hide();--%>
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	if("<%=request.getParameter("type") %>"=="04"){
		nui.get("btnchange").show();
	}
	
	

	function initForm() {
		var json=nui.encode({"item":{"relationId":"<%=request.getParameter("relationId") %>",
			"_entity":"com.bos.dataset.biz.TbBizGrtRelation"}});
		$.ajax({
	            url: "com.bos.biz.Collateral.getCollatera.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		nui.get("relationId").setValue("<%=request.getParameter("relationId") %>");
	            		nui.get("tbGrtGuarantybasic.suretyId").setValue(text.tbGrtGuarantybasic.suretyId);
	            		nui.get("item.debtAmount").setValue(text.tbGrtGuarantybasic.debtAmount);
	            		nui.get("item.occupationAmount").setValue(text.tbGrtGuarantybasic.occupationAmount);
	            		nui.get("reType").setValue("02");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	<%--function open(){
		 nui.open({
                url:nui.context+"/biz/biz_grt/biz_Collateral_change.jsp?relationId=<%=request.getParameter("relationId") %>" ,
                title: "替换抵质押物", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                       initForm();
               	 	}
                }
            });
	}--%>
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		if("<%=request.getParameter("type") %>"!="null"){
			if(form.isDataChanged()){
        		if(parseInt(nui.get("item.occupationAmount").getValue())>parseInt(nui.get("item.debtAmount").getValue())){
					nui.alert("债权金额不可大于本次占用金额");
					return;
				}
				if(parseInt(nui.get("item.debtAmount").getValue())>parseInt(nui.get("tbGrtGuarantybasic.myBankSureCost").getValue())){
					nui.alert("本次占用价值 不可大于我行确认价值");
					return;
				}
        	}else{
        		nui.alert("您并未做出相应的信息修改，请勿重复此操作。","无效操作");
        		nui.get("btnCreate").setEnabled(true);
        		return;
        	}		
		}else{
			var o=form.getData();
			var json=nui.encode(o);
			//nui.alert(json);return;
			$.ajax({
		            url: "com.bos.biz.Collateral.addOrUpdateCollateral.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	} else {
		            		CloseWindow("ok");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
			});
		}
		
	}
</script>
</body>
</html>
