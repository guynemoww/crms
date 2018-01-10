<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zhufaying
  - Date: 2014-03-28 15:49:37
  - Description:
-->
<head>
<title>银团结构</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
   <input id="bankMember.syndicatedMemberId" class="nui-hidden nui-form-input" name ="bankMember.syndicatedMemberId" />
   <input id="bankMember.syndicatedStructId" class="nui-hidden nui-form-input" name ="bankMember.syndicatedStructId" />
   <input id="bankMember.membersBankNum" name="bankMember.membersBankNum"  class="nui-hidden nui-form-input"/>
   <div class="nui-dynpanel" columns="4" id="table1">
		<label class="nui-form-label">银团组团对象：</label>
		<input id="bankMember.syndicatedObjectCd" name="bankMember.syndicatedObjectCd" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="CDXY0064" required="true" emptyText="--请选择--"/>

		<label class="nui-form-label">成员行名称：</label>
<%--		<input id="bankMember.memberBankName" name="bankMember.memberBankName" required="true" class="nui-buttonEdit" allowInput="false"  onbuttonclick="selectMemberBank" />--%>
		<input id="bankMember.memberBankName" name="bankMember.memberBankName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:90"/>
	
		<label class="nui-form-label">成员行经办人：</label>
		<input id="bankMember.membersUserNum" name="bankMember.membersUserNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:15"/>
	
		<label class="nui-form-label">成员行分工：</label>
		<input id="bankMember.memberRoleCd" name="bankMember.memberRoleCd" data="data" valueField="dictID" 
		class="mini-newcheckbox"  dictTypeId="XD_SXYW0237" required="true" emptyText="--请选择--"/>

		<label class="nui-form-label">币种：</label>
		<input id="bankMember.currencyCd" name="bankMember.currencyCd" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" value="156" required="true" emptyText="--请选择--" />

		<label class="nui-form-label">承诺金额：</label>
		<input id="bankMember.promiseAmount" name="bankMember.promiseAmount" vtype="float;maxLength:20" class="nui-textbox nui-form-input" dataType="currency" required="true" onblur="cnjechange"/>
		
		<label class="nui-form-label">承诺比例(%)：</label>
		<input id="bankMember.promiseRatio" name="bankMember.promiseRatio" required="true" class="nui-text nui-form-input"/>%
	
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" id="biz_bankTeam_struce_save" iconCls="icon-ok" onclick="create">确定</a>
	</div>
</div>
</center>
</body>
<script type="text/javascript">

	nui.parse();
	var form = new nui.Form("#form1");
	var syndicatedStructId ="<%=request.getParameter("syndicatedStructId")%>";
	var syndicatedMemberId ="<%=request.getParameter("syndicatedMemberId")%>";
	if(syndicatedMemberId=='null'){
		syndicatedMemberId = null;
	}
	var syndicatedLoanAmt ="<%=request.getParameter("syndicatedLoanAmt")%>";
	if(syndicatedLoanAmt=='null'){
		syndicatedLoanAmt = null;
	}
	var syndicatedObjectCd ="<%=request.getParameter("syndicatedObjectCd")%>";
	var flg ="<%=request.getParameter("flg")%>";
	initPage();
	function initPage(){
		var json = nui.encode({"syndicatedMemberId":syndicatedMemberId});
        $.ajax({
            url: "com.bos.bizInfo.bizInfo.getBankMemberInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var o = nui.decode(text);
            	form.setData(o);
            	if(o.bankMember.syndicatedObjectCd==null || o.bankMember.syndicatedObjectCd==''){
            		nui.get("bankMember.syndicatedObjectCd").setValue(syndicatedObjectCd);
            		nui.get("bankMember.syndicatedObjectCd").setEnabled(false);
            	}
            	if(o.bankMember.syndicatedStructId==null || o.bankMember.syndicatedStructId==''){
            		nui.get("bankMember.syndicatedStructId").setValue(syndicatedStructId);
            	}
            	if(o.bankMember.memberBankName!=null){
            		nui.get("bankMember.memberBankName").setText(o.bankMember.memberBankName);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
        
        if(flg == '2'){
        	nui.get("biz_bankTeam_struce_save").hide();
        	form.setEnabled(false);
        }
	}
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
		//nui.get("biz_bankTeam_struce_save").setEnabled(false);
        
       	var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.bizInfo.bizInfo.saveBankMember.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var o = nui.decode(text);
            	nui.alert("保存成功!");
            	form.setData(o);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	
	function selectMemberBank(e) {
	    var btnEdit = this;
        nui.open({
            url: nui.context + "/biz/biz_info/biz_bankTeam_bankMember.jsp",
            showMaxButton: true,
            title: "选择外部银团成员行",
            width: 800,
            height: 500,
            ondestroy: function (action) {
              if (action == "ok") {
	                 var iframe = this.getIFrameEl();
	            	 var data = iframe.contentWindow.getData();
	            	 data = nui.clone(data);
	            	 btnEdit.setValue(data.bankname);
	            	 nui.get("bankMember.membersBankNum").setValue(data.bankcode);
	            	 nui.get("bankMember.memberBankName").setText(data.bankname);
	            }
            }
        });   
	}
	//承诺金额变动时
	function cnjechange(){
		var promiseAmount = nui.get("bankMember.promiseAmount").getValue();
		var bltp = parseFloat(promiseAmount)/parseFloat(syndicatedLoanAmt);
		var bl = (bltp*100).toFixed(2);
		nui.get("bankMember.promiseRatio").setValue(bl);
		nui.get("bankMember.promiseRatio").validate();
	}
</script>
</html>