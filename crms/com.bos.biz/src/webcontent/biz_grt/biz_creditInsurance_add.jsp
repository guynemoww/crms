<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/common/nui/common.jsp"%>     
</head>
<body>
	<jsp:include flush="true" page="/grt/creditsafe/creditsafe_list.jsp"/>   
 
 	<div id="editForm1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item.reType" id="reType" value="03" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.biz.TbBizGrtRelation" class="nui-hidden" />
	<input id="item.applyId" type="hidden" name="item.applyId" value="<%=request.getParameter("bizId") %>" class="nui-hidden" />
	<input id="item.guarantyId" type="hidden" name="item.guarantyId" value="" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>保险公司名称：</label>
		<input id="dzy" name="tbGrtCreditsafe.insuranceCompanyName" required="false" class="nui-text nui-form-input"  />
		
		<label>信用保险类型：</label>
		<input name="tbGrtCreditsafe.insuranceCoverage" required="false" class="nui-text nui-form-input" dictTypeId="YP_GLCD0119" />
		
		<label>保险单编号：</label>
		<input name="tbGrtCreditsafe.policyNum" required="false" class="nui-text nui-form-input"  />
		
		<label>可用金额：</label>
		<input name="tbGrtCreditsafe.availableAmt" required="false" class="nui-text nui-form-input"  />
		
		<label>已用金额：</label>
		<input name="tbGrtCreditsafe.occupiedAmt" required="false" class="nui-text nui-form-input"  />
		
		<label>币种：</label>
		<input name="tbGrtCreditsafe.currencyCd" required="false" class="nui-text nui-form-input" dictTypeId="CD000001"  />
		
		<label>收益人：</label>
		<input name="tbGrtCreditsafe.beneficiary" required="false" class="nui-text nui-form-input"  />
		
		<label>投保人：</label>
		<input name="tbGrtCreditsafe.policyHolder" required="false" class="nui-text nui-form-input"  />
		
		<label>本次占用价值：</label>
		<input id="item.debtAmount" vtype="float;maxLength:26" name="item.debtAmount" required="true" class="nui-textbox nui-form-input"   />
		
		
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
        
        
 
 			grid.on("selectionchanged",function(e){
	                editRow();
        	})
       
        
        function editRow() 
        {	
        	var row = grid.getSelected();
            var id = row.suretyId;
            var json = nui.encode({"tbGrtCreditsafe":{"suretyId":id}});
                nui.ajax(
                {
                    url: "com.bos.grt.creditsafe.creditsafe.getTbGrtCreditsafe.biz.ext",//上部分需要的逻辑流(返回对象tbGrtCreditsafe)
	                type: 'POST',
	                data: json,
	                contentType:'text/json',
                    success: function (text)
                    {
                        formEdit.unmask();
                        var o = nui.decode(text);
                        formEdit.setData(o);
                        nui.get("item.guarantyId").setValue(text.tbGrtCreditsafe.suretyId);
                                                
                    },
                    error: function () 
                    {
                        alert("表单加载错误");
                        formEdit.unmask();
                    }
            	});
        }
        
       function save() {
		formEdit.validate();
		if (formEdit.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var row = grid.getSelected();
	 	
		if (null == row) {
			nui.alert("请选择担保品");
			return;
		}
		nui.get("item.applyId").setValue("<%=request.getParameter("bizId") %>");
		nui.get("reType").setValue("03");
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
