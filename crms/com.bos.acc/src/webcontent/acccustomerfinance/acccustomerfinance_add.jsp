<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.csm.pub.CsmUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%String cusType=request.getParameter("cusType"); %>
	<center>
		<form id="form1"
			action="com.bos.acc.DownloadAccExcel.flow?_eosFlowAction=accUploadExcelAction&partyId=<%=request.getParameter("partyId")%>"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="accCustomerFinance._entity"
				value="com.bos.dataset.acc.TbAccCustomerFinance" class="nui-hidden" />
			<input name="accCustomerFinance.partyId"
				value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
			<input id="cusType" name="cusType"
				value="<%=request.getParameter("cusType") %>" class="nui-hidden" />
			<fieldset>
				<legend>
					<span>财报Excel导入</span>
				</legend>
				<div
					style="width: 60%; height: auto; overflow: hidden; text-align: center; margin-top: 20px; border: 1px solid black">
					<div class="nui-dynpanel"
						style="width: 80%; height: auto; overflow: hidden; text-align: center; margin-top: 20px;"
						id="table2" columns="2">
						<label>Excel财务报表导入类别：</label> <input name="customerTypeCd"
							id="customerTypeCd" required="true"
							class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0002"
							emptyText="--请选择--" /> <label>选择要上传的财务报表：</label> <input
							class="nui-htmlfile" id="Fdata" name="Fdata" limitType="*.xls"
							required="true" />
					</div>
					<a class="nui-button" iconCls="icon-download" type="submit"
						onclick="exportDict();" disableOnClick="true" />下载财报模板</a> <a
						class="nui-button" iconCls="icon-upload" onclick="uploadExcel();"
						disableOnClick="true" />上传</a>
				</div>
			</fieldset>
		</form>
		<div id="form2" style="width: 100%; height: auto; overflow: hidden;">
			<input type="hidden" name="accCustomerFinance._entity"
				value="com.bos.dataset.acc.TbAccCustomerFinance" class="nui-hidden" />
			<input name="accCustomerFinance.partyId"
				value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
			<fieldset>
				<legend>
					<span>财报手工新增</span>
				</legend>
				<div class="nui-dynpanel"
					style="width: 60%; height: auto; overflow: hidden; text-align: center; margin-top: 20px; border: 1px solid black"
					id="table1" columns="2">
					<label>财务报表日期：</label> <input
						name="accCustomerFinance.financeDeadline"
						id="accCustomerFinance.financeDeadline" required="true"
						class="nui-datepicker nui-form-input"
						maxDate="<%=GitUtil.getBusiDateStr()%>" /> <label>财务报表类型：</label>
					<input name="accCustomerFinance.financeTypeCd"
						id="accCustomerFinance.financeTypeCd" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0001"
						emptyText="--请选择--" /> <label>财务报表口径：</label> <input
						name="accCustomerFinance.caliberCd"
						id="accCustomerFinance.caliberCd" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0071"
						emptyText="--请选择--" /> <label>财务报表类别：</label> <input
						name="accCustomerFinance.customerTypeCd"
						id="accCustomerFinance.customerTypeCd" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0002"
						emptyText="--请选择--" /> <label>币种：</label> <input
						id="accCustomerFinance.currency"
						name="accCustomerFinance.currency" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"
						dValue="CNY" /> <label>财务报表是否经过调整：</label> <input
						id="accCustomerFinance.regulatedInd"
						name="accCustomerFinance.regulatedInd" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"
						emptyText="--请选择--" onvaluechanged="regulatedChange" /> <label
						id="regulatedReason" style="display: none">调整原因：</label> <input
						id="accCustomerFinance.regulatedReason"
						name="accCustomerFinance.regulatedReason" visible="false"
						required="true" class="nui-textbox nui-form-input"
						vtype="maxLength:4000" /> <label>是否经过审计：</label> <input
						name="accCustomerFinance.auditedInd"
						id="accCustomerFinance.auditedInd" required="true"
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCD0003"
						emptyText="--请选择--" onvaluechanged="auditedChange" />
					<%--<label id="auditCommentName" style="display: none">审计事务所意见类型：</label>
		<input id="accCustomerFinance.auditCommentTypeCd" visible="false" name="accCustomerFinance.auditCommentTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0100"  emptyText="--请选择--" onvaluechanged="auditCommentTypeCdChange"/>
		<label id="remark"  style="display: none">审计意见说明：</label>
		<input id="accCustomerFinance.remark" name="accCustomerFinance.remark"  visible="false" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />--%>
					<label id="auditCoName" style="display: none">审计事务所名称：</label> <input
						id="accCustomerFinance.auditCoName" visible="false"
						name="accCustomerFinance.auditCoName" required="true"
						class="nui-textbox nui-form-input" vtype="maxLength:100" />
					<%--<label id="bizLicenseNum" style="display: none">审计事务所经营执照号码：</label>
		<input id="accCustomerFinance.bizLicenseNum" visible="false" name="accCustomerFinance.bizLicenseNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />
		--%>
					<a class="nui-button" iconCls="icon-save" onclick="save()"
						disableOnClick="true">保存</a> <a class="nui-button"
						iconCls="icon-close" onclick="CloseWindow()">关闭</a>
				</div>
			</fieldset>
			<iframe name="exportFrame" id="exportFrame" src=""
				style="display: none;"></iframe>
		</div>
	</center>
	<script type="text/javascript">
nui.parse();
var form = new nui.Form("#form1");
var form2 = new nui.Form("form2");
	
var errMsg = "<%=request.getAttribute("errMsg")%>";
var cusType = nui.get("cusType").getValue();
var partyId = "<%=request.getParameter("partyId")%>";

if(errMsg != null && errMsg != 'null'){
	alert(errMsg);
}    
function save() {

	form2.validate();
	if (form2.isValid() == false) {
		alert("请将信息填写完整");
		return;
	}
	if(!nui.get("accCustomerFinance.financeDeadline").value){
			nui.alert("请填写财务报表日期");
			return;
		}
		if(!nui.get("accCustomerFinance.financeTypeCd").value){
			nui.alert("请填写财务报表类型");
			return;
		}
		if(!nui.get("accCustomerFinance.auditedInd").value){
			nui.alert("请填写是否经过审计");
			return;
		}
		if(!nui.get("accCustomerFinance.caliberCd").value){
			nui.alert("请填写财务报表口径");
			return;
		}
		if(!nui.get("accCustomerFinance.customerTypeCd").value){
			nui.alert("请填写财务报表类别");
			return;
		}
		if(!nui.get("accCustomerFinance.currency").value){
			nui.alert("请填写币别");
			return;
		}
		if(!nui.get("accCustomerFinance.regulatedInd").value){
			nui.alert("请填写财务报表是否经过调整");
			return;
		}
		if(nui.get("accCustomerFinance.customerTypeCd").value=='015'||nui.get("accCustomerFinance.customerTypeCd").value=='016'||nui.get("accCustomerFinance.customerTypeCd").value=='017'){
			nui.alert("该类型为数据迁移专用，不能新增该类型的财报");
			return;
		}
		
	var o=form2.getData();
	
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
	   // }
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
	   //   nui.get("accCustomerFinance.auditCommentTypeCd").setValue("");
	   //   return;
	  // }
	   if(auditCoName!=null && auditCoName !=''){ 
	      alert('报表未经过审计，不能填写审计事务所名称');
	      nui.get("accCustomerFinance.auditCoName").setValue("");
	      return;
	   }
	   //if(bizLicenseNum!=null && bizLicenseNum !=''){ 
	    //  alert('报表未经过审计，不能审计事务所经营执照号码');
	    //  nui.get("accCustomerFinance.bizLicenseNum").setValue("");
	    //  return;
	  // }
	}
	//if(auditCommentTypeCd=='02'){
	//  if(remark==null && remark ==''){
	 //   alert('请填写审计意见说明');
	 //   return;
	 // }
	//}else{
	//   nui.get("accCustomerFinance.remark").setValue("");
	//}
	
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.acc.acccustomerfinance.addAccCustomerFinance.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		var url = nui.context +"/acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="
            		+text.response.financeId+"&view=0&reportType="+text.response.reportType
            		+"&financeTypeCd="+text.response.financeTypeCd;
            		git.go(url);
            	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
	/**
	*导出财报模板
	*/
	function exportDict() {
		var ifrm = document.getElementById("exportFrame");
		var o=form.getData();
		if(!nui.get("customerTypeCd").value){
			nui.alert("请选择财务报表导入类别");
			return;
		}
		var customerType = o.customerTypeCd;
		if(nui.get("customerTypeCd").value=='015'||nui.get("customerTypeCd").value=='016'||nui.get("customerTypeCd").value=='017'){
			nui.alert("该类型为数据迁移专用，不能下载导入该类型的财报");
			return;
		}
			ifrm.src="com.bos.acc.DownloadAccExcel.flow?_eosFlowAction=accDownExcelAction&customerTypeCd="+customerType;
    }
	/**
	*导入财报模板
	*/     
    function uploadExcel() {
   	var frm = document.getElementById("form1");
		var o=form.getData();
		var customerType = o.customerTypeCd;
		if (!nui.get("customerTypeCd").value){
		nui.alert("请选择财务报表导入类别");
			return;
		}
		
    	if(!nui.get("Fdata").value){
			nui.alert("请选择文件");
			return;
		}
		if(/\.xls?$/g.test(nui.get("Fdata").value) == false){
			nui.alert("请选择.xls文件");
			return;
		}
		if(nui.get("customerTypeCd").value=='001' && nui.get("Fdata").value.indexOf("企业类（旧会计准则）")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“企业类（旧会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='002' && nui.get("Fdata").value.indexOf("企业类（新会计准则）")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“企业类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='003' && nui.get("Fdata").value.indexOf("一般事业单位类（旧")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“一般事业单位类（旧会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='004' && nui.get("Fdata").value.indexOf("一般事业单位类（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“一般事业单位类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='005' && nui.get("Fdata").value.indexOf("担保公司（旧")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“担保公司（旧会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='006' && nui.get("Fdata").value.indexOf("担保公司（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“担保公司（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='007' && nui.get("Fdata").value.indexOf("证券公司类")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“证券公司类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='008' && nui.get("Fdata").value.indexOf("保险公司类")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“保险公司类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='009' && nui.get("Fdata").value.indexOf("银行类")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“银行类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='010' && nui.get("Fdata").value.indexOf("其他非银行金融机构（旧")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“其他非银行金融机构（旧会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='011' && nui.get("Fdata").value.indexOf("其他非银行金融机构（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“其他非银行金融机构（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='012' && nui.get("Fdata").value.indexOf("医院类（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“医院类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='013' && nui.get("Fdata").value.indexOf("高校类（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“高校类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='014' && nui.get("Fdata").value.indexOf("中小学类（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“中小学类（新会计准则）”进行上传");
			return;
		}else if(nui.get("customerTypeCd").value=='019' && nui.get("Fdata").value.indexOf("个人经营类（新")==-1){
			nui.alert("财报模板名称与所选类别不匹配，请选择正确的财务报表：“个人经营类（新会计准则）”进行上传");
			return;
		}
		if(nui.get("customerTypeCd").value=='015'||nui.get("customerTypeCd").value=='016'||nui.get("customerTypeCd").value=='017'){
			nui.alert("该类型为数据迁移专用，不能下载导入该类型的财报");
			return;
		}
		frm.action ="com.bos.acc.DownloadAccExcel.flow?_eosFlowAction=accUploadExcelAction&partyId="+partyId+"&customerTypeCd="+customerType;
		frm.submit();
		git.mask();
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
		}else{
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
	//初始化财务类别
	function initCustomerTypeCd(){
	
		var str=<%=JspUtil.getStrHaveSign(CsmUtil.getCorpAccReportType(cusType)) %>
		var arr = git.getDictDataFilter("XD_ACCCD0002",str);
		nui.get("accCustomerFinance.customerTypeCd").setData(arr);
		nui.get("customerTypeCd").setData(arr);
	}
	initCustomerTypeCd();
	//等页面编译完，再重新刷新一下table,才能隐藏掉的行，彻底的隐藏掉
	nui.get('table1').refreshTable();
</script>
</body>
</html>
