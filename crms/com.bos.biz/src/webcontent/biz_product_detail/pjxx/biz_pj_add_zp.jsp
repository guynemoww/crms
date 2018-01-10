<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s):lpc
  - Date: 2015-05-19
  - Description:票据信息新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="formpj" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbBizPjxxApply.amountDetailId" id="tbBizPjxxApply.amountDetailId" class="nui-hidden" />
	<input name="tbBizPjxxApply.contractId" id="tbBizPjxxApply.contractId" class="nui-hidden" />
	<fieldset>
  		<legend>
    		<span>纸票信息</span>
    	</legend>
		<div class="nui-dynpanel" columns="4" id="div_1">
			<label>合同编号：</label>
			<input id="tbBizPjxxApply.htbh" name="tbBizPjxxApply.htbh" required="true" class="nui-text" />
		</div>
		<div class="nui-dynpanel" columns="4" id="div_2">
			<label>出票人全称：</label>
			<input id="tbBizPjxxApply.cprqc" name="tbBizPjxxApply.cprqc" required="true" class="nui-text" />
			<label>出票人账号：</label>
			<input id="tbBizPjxxApply.cprzh" name="tbBizPjxxApply.cprzh" required="true" class="nui-textbox nui-form-input"  />
			<label>收款人全称：</label>
			<input id="tbBizPjxxApply.skrqc" name="tbBizPjxxApply.skrqc" required="true" class="nui-textbox nui-form-input" vtype="maxLength:300" />
			<label>收款人账号：</label>
			<input id="tbBizPjxxApply.skrzh" name="tbBizPjxxApply.skrzh" required="true" class="nui-textbox nui-form-input" vtype="maxLength:300" />
			<label>收款人开户行行名：</label>
			<input id="tbBizPjxxApply.payeebankname" name="tbBizPjxxApply.payeebankname" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_SK" />
			<label>收款人开户行行号：</label>
			<input id="tbBizPjxxApply.payeebankno" name="tbBizPjxxApply.payeebankno" required="true" class="nui-text" />
			<label>承兑行行名：</label>
			<input id="tbBizPjxxApply.acceptorbankname" name="tbBizPjxxApply.acceptorbankname" required="true" enabled="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_CD" />
			<label>承兑行行号：</label>
			<input id="tbBizPjxxApply.acceptorbankno" name="tbBizPjxxApply.acceptorbankno" required="true" class="nui-text" />
			<label>币种：</label>
			<input id="tbBizPjxxApply.currencyCd" name="tbBizPjxxApply.currencyCd" required="true" dictTypeId="CD000001" class="nui-dictcombobox nui-form-input" enabled="false" />
			<label>汇票金额：</label>
			<input id="tbBizPjxxApply.hpje" name="tbBizPjxxApply.hpje"  required="true" class="nui-textbox nui-form-input" dataType="currency"/>
			<label>汇票出票日期：</label>
			<input id="tbBizPjxxApply.hpcprq" name="tbBizPjxxApply.hpcprq" required="true" class="nui-datepicker nui-form-input" onvaluechanged="validateDate" />
			<label>汇票到期日期：</label>
			<input id="tbBizPjxxApply.hpdqrq" name="tbBizPjxxApply.hpdqrq" required="true" class="nui-datepicker nui-form-input" onvaluechanged="validateDate" />
			<label>汇票形式：</label>
			<input id="tbBizPjxxApply.hpxs" name="tbBizPjxxApply.hpxs" required="true" dictTypeId="XD_SXCD1123" class="nui-dictcombobox nui-form-input" enabled="false" />
			<label>禁止背书标记：</label>
			<input id="tbBizPjxxApply.forbidflag" name="tbBizPjxxApply.forbidflag" required="true" dictTypeId="XD_JZBSBJ01" class="nui-dictcombobox nui-form-input" enabled="false" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
<script type="text/javascript">

	/* 票据查询与插入需要同时带入合同编号,否则综合授信项下会查询到其他合同的票据信息 */

 	nui.parse();
	var formpj = new nui.Form("#formpj");
	var amountDetailId = "<%=request.getParameter("amountDetailId") %>";
	var partyId = "<%=request.getParameter("partyId") %>"; // 客户编号
	var contractNum = "<%=request.getParameter("contractNum") %>"; // 合同编号
	var contractId = "<%=request.getParameter("contractId") %>"; // 合同ID
	nui.get("tbBizPjxxApply.amountDetailId").setValue(amountDetailId); 
	nui.get("tbBizPjxxApply.currencyCd").setValue("CNY");// 币种默认人民币
	nui.get("tbBizPjxxApply.hpxs").setValue("01"); // 汇票形式默认纸票
	nui.get("tbBizPjxxApply.forbidflag").setValue("0");// 纸票禁止背书标记默认为非禁止背书 
	nui.get("tbBizPjxxApply.htbh").setValue(contractNum);// 合同编号
	nui.get("tbBizPjxxApply.contractId").setValue(contractId);// 合同编号
	formpj.validate();
	init();
	initCD();
	
	// 出票人全称默认当前客户名称
	function init() {
		var json = nui.encode({"partyId" : partyId});
		$.ajax({
			url : "com.bos.aft.conLoanChange.findCusInfo.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			async: false, //异步处理
			success : function(o) {
				if (o.tbCsmParty.partyName) {
					nui.get("tbBizPjxxApply.cprqc").setValue(o.tbCsmParty.partyName); 
					nui.get("tbBizPjxxApply.cprqc").validate();
				}else{
					nui.alert("未查询到客户名称！");
					nui.get("tbBizPjxxApply.cprqc").validate();
				}
			},
			error : function(text) {
				nui.alert("操作失败！");
				nui.get("tbBizPjxxApply.cprqc").validate();
			}
		});
	}
	
	// 承兑行行号,行名
	function initCD() {
		git.mask("cxlist");
		var json = nui.encode({"type":"2"});
		$.ajax({
			url: "com.bos.comm.pub.orgRel.orgRel.biz.ext",
			
			type: 'POST',
			data: json,
			contentType:'text/json',
			async: false, //异步处理
			success: function (mydata) {
				git.unmask("cxlist");
				debugger;
				if(mydata.orgRel[0].BANKNO== null || mydata.orgRel[0].BANKNO==""){
					ui.alert("操作失败！");
				}
				nui.get("tbBizPjxxApply.acceptorbankname").setText(mydata.orgRel[0].KEHYWM);
				nui.get("tbBizPjxxApply.acceptorbankname").setValue(mydata.orgRel[0].KEHYWM);
				nui.get("tbBizPjxxApply.acceptorbankno").setValue(mydata.orgRel[0].BANKNO);
			},
			error : function(mydata) {
				git.unmask("cxlist");
				nui.alert("操作失败！");
			}
		});
	}
	
	
	
	// 新增纸票信息 
	function save() {
		formpj.validate();
		if (formpj.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("formpj");
		var o = formpj.getData();
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.bizProductDetail.bizPjxx.insertPjxx.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("formpj");
				if (text.msg) {
					nui.alert(text.msg);
				} else {
					alert("保存成功!");
					CloseWindow("ok");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("formpj");
				nui.alert(jqXHR.responseText);
			}
		});
	}
	
	// 校验票据日期 
	function validateDate() {
		var hpcprq = nui.get("tbBizPjxxApply.hpcprq").getValue();
		var hpdqrq = nui.get("tbBizPjxxApply.hpdqrq").getValue();
		if(hpcprq!=null && hpcprq!='' && hpdqrq!=null && hpdqrq!=''){
			var beginDate = new Date(hpcprq.substr(0,10).replace(/\-/g,"\/"));
			var endDate = new Date(hpdqrq.substr(0,10).replace(/\-/g,"\/"));
			if(beginDate>=endDate){
		 		alert("到期日期必须大于出票日期!");
		 		nui.get("tbBizPjxxApply.hpdqrq").setValue("");
		 		return;
		 	}
		 }
	}
	
	// 收款行 行名行号选择
	function selectPZFHH_SK() {
		nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/queryPZFHH.jsp",
	        showMaxButton: true,
	        title: "查询",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	nui.get("tbBizPjxxApply.payeebankname").setValue(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.payeebankname").setText(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.payeebankno").setValue(data.FBHHHH);
	                }
	            }
	            formpj.validate();
	        }
	    }); 
    }
    
    // 承兑号 行名行号选择
	function selectPZFHH_CD() {
		nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/queryPZFHH.jsp",
	        showMaxButton: true,
	        title: "查询",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	nui.get("tbBizPjxxApply.acceptorbankname").setValue(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.acceptorbankname").setText(data.KEHYWM);
                    	nui.get("tbBizPjxxApply.acceptorbankno").setValue(data.FBHHHH);
	                }
	            }
	            formpj.validate();
	        }
	    }); 
    }
    
</script>
</body>
</html>
