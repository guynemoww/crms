<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
 - Author(s):lpc
 - Date: 2015-05-19
 - Description: 贴现信息新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="formtx" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbBizTxxxApply.applyDetailId" id="tbBizTxxxApply.applyDetailId" class="nui-hidden" />
	<input name="tbBizTxxxApply.amountDetailId" id="tbBizTxxxApply.amountDetailId" class="nui-hidden" />
	<input name="tbBizTxxxApply.contractId" id="tbBizTxxxApply.contractId" class="nui-hidden" />
	<fieldset>
		<legend>
  			<span>纸票信息</span>
		</legend>
		<div class="nui-dynpanel" columns="4" id="div_1">
			<label>汇票类型 :</label>
			<input id="tbBizTxxxApply.billtype" name="tbBizTxxxApply.billtype" required="true" dictTypeId="XD_SXYW0203" class="nui-text" />
			<label>汇票模式 :</label>
			<input id="tbBizTxxxApply.billmodel" name="tbBizTxxxApply.billmodel" required="true" dictTypeId="XD_SXCD1123" class="nui-text" />
  			<label>汇票号码 :</label>
			<input id="tbBizTxxxApply.billno" name="tbBizTxxxApply.billno" required="true" class="nui-textbox nui-form-input" vtype="string;maxLength:32" onvaluechanged="validateBillno" />
			<label>利率(%):</label>
			<input id="tbBizTxxxApply.interate" name="tbBizTxxxApply.interate" vtype="float;maxLength:5;range:0,50" required="true" class="nui-textbox nui-form-input" />
  		</div>
  		<div class="nui-dynpanel" columns="4" id="div_2">
			<label>票面金额 :</label>
			<input id="tbBizTxxxApply.billamt" name="tbBizTxxxApply.billamt" vtype="float;maxLength:16;range:0,100000000000" required="true" class="nui-textbox nui-form-input" />
			<label>币种:</label>
			<input id="tbBizTxxxApply.currsign" dictTypeId="CD000001" name="tbBizTxxxApply.currsign" required="true" class="nui-text" />
			<label>出票日期:</label>
			<input id="tbBizTxxxApply.billbegindate" name="tbBizTxxxApply.billbegindate" required="true" class="nui-datepicker nui-form-input" allowInput="true" onvaluechanged="validateDate" />
			<label>到期日期:</label>
			<input id="tbBizTxxxApply.billenddate" name="tbBizTxxxApply.billenddate" required="true" class="nui-datepicker nui-form-input" allowInput="true" onvaluechanged="validateDate" />
		</div>
		<div class="nui-dynpanel" columns="4" id="div_2">
			<label>出票人全称:</label>
			<input id="tbBizTxxxApply.takeoutacname" name="tbBizTxxxApply.takeoutacname" vtype="string;maxLength:80" required="true" class="nui-textbox nui-form-input" />
			<label>出票人开户行账号:</label>
			<input id="tbBizTxxxApply.takeoutacno" name="tbBizTxxxApply.takeoutacno" vtype="string;maxLength:40" required="true" class="nui-textbox nui-form-input"/>
			<label>出票人开户行名称:</label>
	    	<input id="tbBizTxxxApply.takeoutacbankname" name="tbBizTxxxApply.takeoutacbankname" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_CP" />
			<label>出票人开户行行号:</label>
			<input id="tbBizTxxxApply.takeoutacbankno" name="tbBizTxxxApply.takeoutacbankno" required="true" class="nui-text" />
		</div>
		<div class="nui-dynpanel" columns="4" id="div_3">
			<label>收款人全称:</label>
			<input id="tbBizTxxxApply.benename" name="tbBizTxxxApply.benename" vtype="string;maxLength:80" required="true" class="nui-textbox nui-form-input" />
			<label>收款人开户行账号:</label>
			<input id="tbBizTxxxApply.beneno" name="tbBizTxxxApply.beneno" vtype="string;maxLength:40" required="true" class="nui-textbox nui-form-input" />
			<label>收款人开户行名称:</label>
			<input id="tbBizTxxxApply.benebankname" name="tbBizTxxxApply.benebankname" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_SK" />
			<label>收款人开户行行号 :</label>
			<input id="tbBizTxxxApply.benebankno" name="tbBizTxxxApply.benebankno" required="true" class="nui-text" />
		</div>
		<div class="nui-dynpanel" columns="4" id="div_4">
			<label>承兑人全称:</label>
			<input id="tbBizTxxxApply.billacname" name="tbBizTxxxApply.billacname" vtype="string;maxLength:80" required="true" class="nui-textbox nui-form-input" />
			<label>承兑人账号:</label>
			<input id="tbBizTxxxApply.billacno" name="tbBizTxxxApply.billacno" vtype="string;maxLength:40" required="true" class="nui-textbox nui-form-input" />
		</div>
		<div class="nui-dynpanel" columns="4" id="div_5">
			<label>承兑行名称:</label>
			<input id="tbBizTxxxApply.billbankname" name="tbBizTxxxApply.billbankname" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectPZFHH_CD" />
			<label>承兑行行号:</label>
			<input id="tbBizTxxxApply.billbankno" name="tbBizTxxxApply.billbankno" required="true" class="nui-text" />
		</div>
		<div class="nui-dynpanel" columns="4" id="div_6">
			<label>票据签发地:</label>
			<input id="tbBizTxxxApply.billaddtype" name="tbBizTxxxApply.billaddtype" vtype="string;maxLength:1" required="true" dictTypeId="XDPJQFD" class="nui-dictcombobox nui-form-input" />
			<label>禁止背书标记 :</label>
			<input id="tbBizTxxxApply.forbidflag" name="tbBizTxxxApply.forbidflag" required="true" dictTypeId="XD_JZBSBJ01" class="nui-text" />
			<label>直接前手全称:</label>
			<input id="tbBizTxxxApply.direfrontname" name="tbBizTxxxApply.direfrontname" vtype="string;maxLength:80" required="false" class="nui-textbox nui-form-input" />
			<label>被书次数 :</label>
			<input id="tbBizTxxxApply.discbenum" name="tbBizTxxxApply.discbenum" vtype="int;maxLength:10;range:0,1000000000" required="false" class="nui-textbox nui-form-input"/>
			<label>调整天数:</label>
			<input id="tbBizTxxxApply.adjustnum" name="tbBizTxxxApply.adjustnum" vtype="int;maxLength:2" required="false" class="nui-textbox nui-form-input" />
			<label>实际到期日期:</label>
			<input id="tbBizTxxxApply.billtrueenddate" name="tbBizTxxxApply.billtrueenddate" required="false" class="nui-datepicker nui-form-input" />
			<!--<label>线上清算标识 :</label>-->
			<!--<input id="tbBizTxxxApply.onlinemark" name="tbBizTxxxApply.onlinemark" vtype="string;maxLength:1" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>承兑行类型:</label>
			<input id="tbBizTxxxApply.billbanktype" name="tbBizTxxxApply.billbanktype" vtype="string;maxLength:2" required="true" dictTypeId="XD_SXCD1123" class="nui-dictcombobox nui-form-input"/> -->
			<!--<label>影像编号:</label>-->
			<!--<input id="tbBizTxxxApply.contimageno" name="tbBizTxxxApply.contimageno" vtype="string;maxLength:60" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>查询查复结论 :</label>-->
			<!--<input id="tbBizTxxxApply.queryresult" name="tbBizTxxxApply.queryresult" vtype="string;maxLength:1" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>审查结论:</label>-->
			<!--<input id="tbBizTxxxApply.reviewresult" name="tbBizTxxxApply.reviewresult" vtype="string;maxLength:1" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>审查意见说明 :</label>-->
			<!--<input id="tbBizTxxxApply.reviewoption" name="tbBizTxxxApply.reviewoption" vtype="string;maxLength:225"  required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>验票员:</label>-->
			<!--<input id="tbBizTxxxApply.reviewer" name="tbBizTxxxApply.reviewer" vtype="string;maxLength:10" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>验票机构 :</label>-->
			<!--<input id="tbBizTxxxApply.reviewerorg" name="tbBizTxxxApply.reviewerorg" vtype="string;maxLength:9"  required="false" class="nui-textbox nui-form-input"/>-->
			<!--<label>最后修改人:</label>-->
			<!--<input id="tbBizTxxxApply.lastchanperson" name="tbBizTxxxApply.lastchanperson" vtype="string;maxLength:10" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>最后操作机构 :</label>-->
			<!--<input id="tbBizTxxxApply.lastchanbankid" name="tbBizTxxxApply.lastchanbankid" vtype="string;maxLength:9" required="false" class="nui-textbox nui-form-input" />-->
			<!--<label>最后修改时间 :</label>-->
			<!--<input id="tbBizTxxxApply.lastchandate" name="tbBizTxxxApply.lastchandate" required="false" class="nui-textbox nui-form-input"/> -->
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button" iconCls="icon-save" onclick="validateBillno()">检查是否风险票</a>
		<a class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var formtx = new nui.Form("#formtx");
	var amountDetailId = "<%=request.getParameter("amountDetailId") %>";
	var productType = "<%=request.getParameter("productType") %>";
	var contractId = "<%=request.getParameter("contractId") %>";
	nui.get("tbBizTxxxApply.contractId").setValue(contractId); 
	nui.get("tbBizTxxxApply.amountDetailId").setValue(amountDetailId); 
	nui.get("tbBizTxxxApply.billmodel").setValue("01"); // 默认 01-纸质汇票
	nui.get("tbBizTxxxApply.currsign").setValue("CNY"); // 默认 CNY-人民币
	nui.get("tbBizTxxxApply.forbidflag").setValue("0");// 默认 0-非禁止背书
	if ("01006001"==productType || "01006010"==productType) {
		nui.get("tbBizTxxxApply.billtype").setValue("0"); // 0-银行承兑汇票
		// 承兑人信息不可编辑 账号默认0 名称联动承兑行
		nui.get('tbBizTxxxApply.billacno').setValue("0");
		nui.get('tbBizTxxxApply.billacname').setEnabled(false);
		nui.get('tbBizTxxxApply.billacno').setEnabled(false);
	}
	if ("01006002"==productType) {
		nui.get("tbBizTxxxApply.billtype").setValue("1"); // 1-商业承兑汇票
	}
	formtx.validate();
	initRate();
	
	// 利率默认为申请利率不能修改
	function initRate() {
		var json = nui.encode({"amountDetailId" : amountDetailId});
		$.ajax({
			url : "com.bos.bizProductDetail.bizPjxx.getBizRate.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(o) {
				if (o.loanrate.yearRate) {
					nui.get("tbBizTxxxApply.interate").setValue(o.loanrate.yearRate); 
					nui.get("tbBizTxxxApply.interate").setEnabled(false);
				}else{
					nui.alert("未查询到利率信息！");
				}
				nui.get("tbBizTxxxApply.interate").validate();
			},
			error : function(text) {
				nui.alert("操作失败！");
				nui.get("tbBizTxxxApply.interate").validate();
			}
		});
	}
	
	// 新增纸票信息 
	function save() {
		formtx.validate();
		if (formtx.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		} 
		git.mask("formtx");
		var json = {"billno":nui.get("tbBizTxxxApply.billno").getValue()};
		var msg = exeRule("RBIZ_0066","1",json);
		if(null != msg && '' != msg){
			nui.alert(msg);
			git.unmask("formtx");
			return;
		}
		var o=formtx.getData();
		var json=nui.encode(o);
		$.ajax({
			url: "com.bos.bizProductDetail.bizPjxx.InsertTxxx.biz.ext",
			type: 'POST',
			data: json,
			cache: false,
			contentType:'text/json',
			success: function (text) {
				git.unmask("formtx");
				if(text.msg){
					nui.alert(text.msg);
				} else {
					alert("保存成功!");
					CloseWindow("ok");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				git.unmask("formtx");
				nui.alert(jqXHR.responseText);
			}
		});
	}		
	
	// 检查风险票 
	function validateBillno() {
		var billtype = nui.get("tbBizTxxxApply.billtype").getValue();
		var billno = nui.get("tbBizTxxxApply.billno").getValue();
		if(billno == ""){
			alert("请先填写汇票号码");
			return false;
		}
		if (billtype == "0") {// 银行承兑汇票
			if (billno.length != 16 || billno.charAt(3) != '0' || billno.charAt(6) != '5') {
				alert("纸质银行承兑汇票由16位数字组成，第四位为0，第七位为5");
				return false;
			}
		} else if (billtype == "1") {// 商业承兑汇票
			if (billno.length != 16 || billno.charAt(3) != '0' || billno.charAt(6) != '6') {
				alert("纸质商业承兑汇票由16位数字组成，第四位为0，第七位为6");
				return false;
			}
		} else {// 其他，直接返回
			return false;
		}
		git.mask("formtx");
		var json = nui.encode({"billno":billno});
		$.ajax({
			url: "com.primeton.ecds.client.CallECDS.ECDS021020.biz.ext",
			type: 'POST',
			data: json,
			cache: false,
			async: false,
			contentType:'text/json',
			success: function (text) {
				git.unmask("formtx");
				alert(text.msg);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				git.unmask("formtx");
				nui.alert(jqXHR.responseText);
			}
		});
	}
	
	// 校验票据日期 
	function validateDate() {
		var hpcprq = nui.get("tbBizTxxxApply.billbegindate").getValue();
		var hpdqrq = nui.get("tbBizTxxxApply.billenddate").getValue();
		if(hpcprq!=null && hpcprq!='' && hpdqrq!=null && hpdqrq!=''){
			var beginDate = new Date(hpcprq.substr(0,10).replace(/\-/g,"\/"));
			var endDate = new Date(hpdqrq.substr(0,10).replace(/\-/g,"\/"));
			if(beginDate>=endDate){
		 		alert("到期日期必须大于出票日期!");
		 		nui.get("tbBizTxxxApply.billenddate").setValue("");
		 		return;
			}
		}
	}
	
	 // 出票行 行名行号选择
	function selectPZFHH_CP() {
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
                    	nui.get("tbBizTxxxApply.takeoutacbankname").setValue(data.KEHYWM);
                    	nui.get("tbBizTxxxApply.takeoutacbankname").setText(data.KEHYWM);
                    	nui.get("tbBizTxxxApply.takeoutacbankno").setValue(data.FBHHHH);
	                }
	            }
	            formtx.validate();
	        }
	    }); 
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
                    	nui.get("tbBizTxxxApply.benebankname").setValue(data.KEHYWM);
                    	nui.get("tbBizTxxxApply.benebankname").setText(data.KEHYWM);
                    	nui.get("tbBizTxxxApply.benebankno").setValue(data.FBHHHH);
	                }
	            }
	            formtx.validate();
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
                    	nui.get("tbBizTxxxApply.billbankname").setValue(data.KEHYWM);
                    	nui.get("tbBizTxxxApply.billbankname").setText(data.KEHYWM);
                    	nui.get("tbBizTxxxApply.billbankno").setValue(data.FBHHHH);
                    	var billtype = nui.get("tbBizTxxxApply.billtype").getValue(); // 0-银行承兑汇票
                    	if (billtype == "0") {
	                    	nui.get("tbBizTxxxApply.billacname").setValue(data.KEHYWM);
	                    	nui.get("tbBizTxxxApply.billacno").setValue("0");
                    	}
	                }
	            }
	            formtx.validate();
	        }
	    }); 
    }
				
</script>
</body>
</html>
