<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): lpc
  - Date: 2015-6-3
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbLoanHpAmt.moneyUseId" id="tbLoanHpAmt.moneyUseId" class="nui-hidden" />
	<input name="tbLoanHpAmt.loanId" id="tbLoanHpAmt.loanId" class="nui-hidden" />
	<fieldset>
  		<legend>
    		<span>票据信息</span>
    	</legend>
    	<div class="nui-dynpanel" columns="4" id="tableForm">
			<label>承兑协议编号：</label>
			<input name="conInfo.contractNum" required="true" class="nui-text nui-form-input"/>
			
			<label>出票人全称：</label>
			<input name="accInfo.zhmc" required="true" class="nui-text nui-form-input"/>
			
			<label>出票人账号：</label>
			<input name="accInfo.zh" required="true" class="nui-text nui-form-input"/>
			
			<label>付款行全称：</label>
			<input name="tbLoanInfo.drweBnkNm" required="true" class="nui-text nui-form-input"  />
			
			<label>付款行行号：</label>
			<input name="tbLoanInfo.drweBnkNo" required="true" class="nui-text nui-form-input"  />
			
			<label>付款行地址：</label>
			<input name="tbLoanInfo.drweBnkAdr" required="true" class="nui-text nui-form-input"  />
			 
			<label>收款人全称：</label>
			<input name="tbLoanHpAmt.pyeAcctNm" id="tbLoanHpAmt.pyeAcctNm" required="true" class="nui-textbox nui-form-input"  onblur="checkLength2(this.value)"/>
			
			<label>收款人账号：</label>
			<input name="tbLoanHpAmt.pyeAcctNo" id="tbLoanHpAmt.pyeAcctNo" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:32"/>
			
			<label>收款人开户银行：</label>
			<input name="tbLoanHpAmt.pyeOpenAcctBnkNm" id="tbLoanHpAmt.pyeOpenAcctBnkNm" required="true" class="nui-textbox nui-form-input"  onblur="checkLength4(this.value)"/>
			
			<label>出票金额：</label>
			<input id="tbLoanHpAmt.loanAmt" name="tbLoanHpAmt.loanAmt"  required="true" class="nui-textbox nui-form-input" dataType="currency" vtype="float;maxLength:20;range:100,100000000000"/>
			
			<label>出票日期：</label>
			<input id="tbLoanHpAmt.issuDt" name="tbLoanHpAmt.issuDt" class="nui-text nui-form-input" />
			
			<label>汇票到期日：</label>
			<input id="tbLoanHpAmt.drftExpDt" name="tbLoanHpAmt.drftExpDt" class="nui-text nui-form-input" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	var moneyUseId = "<%=request.getParameter("moneyUseId") %>";
	function initForm() {
		var json=nui.encode({"moneyUseId":moneyUseId});
		$.ajax({
	            url: "com.bos.pay.pjxx.expandHpxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           		git.unmask("form1");
	            	form.setData(text);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.pay.pjxx.updateHpxx.biz.ext",
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
	function checkLength2(a){//收款人全称
		var ret = maxLength(a,50)
		if(false == ret){
			nui.alert("超过最大字符长度:50");
			nui.get("tbLoanHpAmt.pyeAcctNm").setValue('');
		}
	}
	function checkLength3(a){//收款人账号
		var ret = maxLength(a,32)
		if(false == ret){
			nui.alert("超过最大字符长度:32");
			nui.get("tbLoanHpAmt.pyeAcctNo").setValue('');
		}
	}
	function checkLength4(a){//收款人开户行
		var ret = maxLength(a,60)
		if(false == ret){
			nui.alert("超过最大字符长度:60");
			nui.get("tbLoanHpAmt.pyeOpenAcctBnkNm").setValue('');
		}
	}
	
	function maxLength(a, c) {
        //修改中文所占长度为2，其余字符长度1--begin
        var re = /[^\u4e00-\u9fa5]/;
		var len = 0;
		for(var i=0;i<a.length;i++){
			var t = a.charAt(i);
			if(re.test(t)){
				len = parseInt(len)+1;
			}else{
				len = parseInt(len)+2;//如果是中文，增加2长度
			}
		}
        if(len <= c){
            return true
        } else {
            return false
        }
    }
</script>
</body>
</html>
