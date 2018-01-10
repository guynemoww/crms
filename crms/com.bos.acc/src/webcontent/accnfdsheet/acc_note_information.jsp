<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:财务分析-报表附注信息
--%> <head> <%@include file="/common/nui/common.jsp"%>
<title>报表附注信息</title> 
</head> 
<body> 
<div id="form1"style="width:100%;height:auto;overflow:hidden;"> 
		<input type="hidden" name="accCustomerFinance.financeId"value="<%=request.getParameter("financeId") %>" class="nui-hidden" />
		<input type="hidden" name="accCustomerFinance.partyId"value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
		<div class="nui-dynpanel" columns="2" id="table1"> 
		
		<label >客户名称：</label> 
		<input id="tbCsmParty.partyName"name="tbCsmParty.partyName" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		
		<label >ECIF客户编号：</label> 
		<input id="tbCsmParty.ecifPartyNum"name="tbCsmParty.ecifPartyNum" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		
		<label>财务报表日期：</label>
		<input name="accCustomerFinance.financeDeadline" required="true"class="nui-datepicker nui-form-input" allowInput="false" maxDate="<%=GitUtil.getBusiDateStr()%>" /> 
		<label>财务报表类型：</label>
		 <input name="accCustomerFinance.financeTypeCd" required="true"class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0001" emptyText="--请选择--" /> 
		<label>财务报表口径：</label> 
		<input name="accCustomerFinance.caliberCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0071" emptyText="--请选择--" /> 
		<label>币种：</label> 
		<input id="accCustomerFinance.currency" name="accCustomerFinance.currency" required="true" class="nui-dictcombobox nui-form-input"
			dictTypeId="CD000001" dValue="CNY" />
		 <label>财务报表是否经过调整：</label> 
		 <input name="accCustomerFinance.regulatedInd" required="true"class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"
			emptyText="--请选择--" onvaluechanged="regulatedChange" /> 
		<label id="regulatedReason" style="display: none">调整原因：</label>
		 <input id="accCustomerFinance.regulatedReason" visible="false" name="accCustomerFinance.regulatedReason" required="true"
			class="nui-textbox nui-form-input" vtype="maxLength:4000" /> 
		<label>是否经过审计：</label>
	    <input name="accCustomerFinance.auditedInd" required="true"class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCD0003"
			emptyText="--请选择--" onvaluechanged="auditedChange" /> <%--<label id="auditCommentName"  style="display: none">审计事务所意见类型：</label>
			<input id="accCustomerFinance.auditCommentTypeCd" name="accCustomerFinance.auditCommentTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0100"  emptyText="--请选择--" onvaluechanged="auditCommentTypeCdChange"/>
			<label id="remark"  style="display: none">审计意见说明：</label>
			<input id="accCustomerFinance.remark" name="accCustomerFinance.remark"  visible="false" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />--%>
		<label id="auditCoName" style="display: none">审计事务所名称：</label> 
		<input id="accCustomerFinance.auditCoName"name="accCustomerFinance.auditCoName" required="true"class="nui-textbox nui-form-input" vtype="maxLength:100" /> <%--<label id="bizLicenseNum"     style="display: none">审计事务所经营执照号码：</label>
		<input id="accCustomerFinance.bizLicenseNum" name="accCustomerFinance.bizLicenseNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />
		--%><!--  
		<label>审计部门：</label>
		<input name="accCustomerFinance.auditDept" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		<label>审计部门审计意见：</label>
		<input name="accCustomerFinance.auditDeptCommentCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0100"  emptyText="--请选择--" />
		<label>审计时间：</label>
		<input name="accCustomerFinance.auditTime" required="false" class="nui-datepicker nui-form-input" />
		--> 
		</div> 
		<div class="nui-toolbar"style="border-bottom:0;text-align:right;"> 
		<a class="nui-button"	iconCls="icon-save" onclick="save()" id="btnSave" disableOnClick="true">保存</a>
		<!-- <a class="nui-button" iconCls="icon-close" id="btnCloseWindow" onclick="CloseWindow()">关闭</a> -->
		</div> 
</from> 
<script type="text/javascript">
 	nui.parse();
var form = new nui.Form("#form1");
if ("<%=request.getParameter("view") %>"=="1") {
    form.setEnabled(false);
    nui.get("btnSave").hide();
}

function initForm() {
	var json=nui.encode({"accCustomerFinance":
		{"financeId":"<%=request.getParameter("financeId") %>"}});
	$.ajax({
        url: "com.bos.acc.acccustomerfinance.getAccCustomerFinance.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

function save() {
	form.validate();
	if (form.isValid() == false) {
		alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	//如果经过调整则【调整原因】必填
	var regulatedInd = o.accCustomerFinance.regulatedInd;
	var regulatedReason = o.accCustomerFinance.regulatedReason;
	if(regulatedInd=='1'){
	   if(regulatedReason==null || regulatedReason==''){ 
	      alert('请填写调整原因');
	      return;
	   }
	}else{
	   if(regulatedReason!=null && regulatedReason !=''){ 
	      alert('报表未经调整，不能填写调整原因');
	      nui.get("accCustomerFinance.regulatedReason").setValue("");
	      return;
	   }
	}
	//如果已经经过审计，则【审计事务所意见类型】、【审计事务所名称】、【审计事务所经营执照号码】必须填写
	var auditedInd = o.accCustomerFinance.auditedInd;
	var auditCommentTypeCd = o.accCustomerFinance.auditCommentTypeCd;
	var auditCoName = o.accCustomerFinance.auditCoName;
	var bizLicenseNum = o.accCustomerFinance.bizLicenseNum;
	var remark = o.accCustomerFinance.remark;
	if(auditedInd=='1'){
	   // if(auditCommentTypeCd==null || auditCommentTypeCd==''){ 
	    //  alert('请填写审计事务所意见类型');
	    //  return;
	    //}
	    if(auditCoName==null || auditCoName==''){ 
	      alert('请填写审计事务所名称');
	      return;
	   }
	   //if(bizLicenseNum==null || bizLicenseNum==''){ 
	   //  alert('请填写审计事务所经营执照号码');
	   //   return;
	  // }
	}else{
	   //if(auditCommentTypeCd!=null && auditCommentTypeCd !=''){ 
	   //   alert('报表未经过审计，不能填写审计事务所意见类型');
	    //  nui.get("accCustomerFinance.auditCommentTypeCd").setValue("");
	    //  return;
	  // }
	   if(auditCoName!=null && auditCoName !=''){ 
	      alert('报表未经过审计，不能填写审计事务所名称');
	      nui.get("accCustomerFinance.auditCoName").setValue("");
	      return;
	   }
	   //if(bizLicenseNum!=null && bizLicenseNum !=''){ 
	   //   alert('报表未经过审计，不能审计事务所经营执照号码');
	    //  nui.get("accCustomerFinance.bizLicenseNum").setValue("");
	   //   return;
	  // }
	}
	//if(auditCommentTypeCd=='02'){
	//  if(remark ==null && remark ==''){
	 //   alert('请填写审计意见说明');
	 //   return;
	 // }
	//}
	
	var json=nui.encode(o);
	$.ajax({
        url: "com.bos.acc.acccustomerfinance.updateAccCustomerFinance.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		//CloseWindow("ok");
        		nui.alert("保存成功");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}

 function auditedChange(e){
		//alert(e.value);
		if(e.value == "1"){
		//$("#auditCommentName").css("display","block");
		//nui.get("accCustomerFinance.auditCommentTypeCd").setVisible(true);
		$("#auditCoName").css("display","block");
		nui.get("accCustomerFinance.auditCoName").setVisible(true);
		//$("#bizLicenseNum").css("display","block");
		//nui.get("accCustomerFinance.bizLicenseNum").setVisible(true);

		nui.get('table1').refreshTable();
		
		}else if(e.value=="0"){
		//$("#auditCommentName").css("display","none");
		//nui.get("accCustomerFinance.auditCommentTypeCd").setVisible(false);
		//nui.get("accCustomerFinance.auditCommentTypeCd").setValue("");
		$("#auditCoName").css("display","none");
		nui.get("accCustomerFinance.auditCoName").setVisible(false);
		nui.get("accCustomerFinance.auditCoName").setValue("");
		//$("#bizLicenseNum").css("display","none");
		//nui.get("accCustomerFinance.bizLicenseNum").setVisible(false);
		//nui.get("accCustomerFinance.bizLicenseNum").setValue("");
		
		nui.get('table1').refreshTable();
		
		}
	}
	function regulatedChange(e){
		//alert(e.value);
		if(e.value == "1"){
		$("#regulatedReason").css("display","block");
			nui.get("accCustomerFinance.regulatedReason").setVisible(true);
			nui.get('table1').refreshTable();
		
		}else if(e.value=="0"){
		$("#regulatedReason").css("display","none");
			nui.get("accCustomerFinance.regulatedReason").setVisible(false);
			nui.get("accCustomerFinance.regulatedReason").setValue("");
			nui.get('table1').refreshTable();
		}
	}
	
	function auditCommentTypeCdChange(e){
	   if(e.value == "02"){
		    $("#remark").css("display","block");
			nui.get("accCustomerFinance.remark").setVisible(true);
			nui.get('table1').refreshTable();
		}else{
		    $("#remark").css("display","none");
			nui.get("accCustomerFinance.remark").setVisible(false);
			nui.get("accCustomerFinance.remark").setValue("");
			nui.get('table1').refreshTable();
		}
	}
</script></body> </html>