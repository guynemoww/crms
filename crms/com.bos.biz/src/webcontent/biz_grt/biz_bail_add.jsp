 <%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
 
<%@include file="/common/nui/common.jsp"%>
 
<head>
         
</head>
<body>   
 	<div id="editForm1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item.reType"  id="reType" value="01" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.biz.TbBizGrtRelation" class="nui-hidden" />
	<input type="hidden" id="item.applyId" name="item.applyId" value="<%=request.getParameter("bizId") %>" class="nui-hidden" />
	<input id="item.guarantyId" type="hidden" name="item.guarantyId" value="" class="nui-hidden" />
	
	<div class="nui-dynpanel" columns="4">
		
		<label>抵质押人名称：</label>
		<input id="dzy" name="tbGrtGuarantybasic.partyName" required="false"   class="nui-text nui-form-input"  />
		
		<label>抵质押物名称：</label>
		<input name="tbGrtGuarantybasic.guarantyName" required="false" class="nui-text nui-form-input"  />
		
		<label>我行确认价值：</label>
		<input id="tbGrtGuarantybasic.myBankSureCost" name="tbGrtGuarantybasic.myBankSureCost" required="false" class="nui-text nui-form-input"  />
		
		<label>我行已设定担保额：</label>
		<input name="tbGrtGuarantybasic.myBankSet" required="false" class="nui-text nui-form-input"  />
		
		<label>抵质押率：</label>
		<input name="tbGrtGuarantybasic.mpRate" required="false" class="nui-text nui-form-input"  />
		
		<label>本次担保债权金额：</label>
		<input id="item.occupationAmount" vtype="float;maxLength:26"  name="item.occupationAmount"  required="true" class="nui-textbox nui-form-input"  />

		<label>本次占用价值：</label>
		<input id="item.debtAmount" vtype="float;maxLength:26"  name="item.debtAmount" required="ture" class="nui-textbox nui-form-input"   />
		
		<label>备注：</label>
		<input name="item.remark" rowspan="3" colspan="3" name="" required="false" vtype="maxLength:200" class="nui-textarea nui-form-input"  />
	
	</div>
</div>

<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
    
 
    <script type="text/javascript">
    
    	 nui.parse();
        var editForm = document.getElementById("editForm1");
        var formEdit = new nui.Form("editForm1");
        
        
 
        
       function save() {
		formEdit.validate();
		if (formEdit.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
	 	
			if(parseInt(nui.get("item.occupationAmount").getValue())>parseInt(nui.get("item.debtAmount").getValue())){
				nui.alert("债权金额不可大于本次占用金额");
				return;
			}
			if(parseInt(nui.get("item.debtAmount").getValue())>parseInt(nui.get("tbGrtGuarantybasic.myBankSureCost").getValue())){
					nui.alert("本次占用价值 不可大于我行确认价值");
					return;
			}
			
		nui.get("item.applyId").setValue("<%=request.getParameter("bizId") %>");
		nui.get("reType").setValue("01");
		var o=formEdit.getData();
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
    </script>
 
</body>
</html>
