<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 09:59:52
  - Description:
-->
<head>
<title>资产保全台账回收</title>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
	<div class="nui-dynpanel" >
  		
  		<label id="recoveryType" >回收类型：</label>
		<input id="recovery.recoveryType"  name="" class="nui-textbox nui-form-input" required="true"  />
	</div>
  	<div class="nui-dynpanel" columns="4">	
  		<label id="loanRestructuringLoss">贷款重组发生的损失：</label>
  		<input id="recovery.loanRestructuringLoss" name="recovery.loanRestructuringLoss"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="bondedMaterialIntoValue">抵债物的抵入值：</label>
  		<input id="recovery.bondedMaterialIntoValue" name="recovery.bondedMaterialIntoValue"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="recyclingPrincipalAmount">回收金额抵偿本金：</label>
  		<input id="recovery.recyclingPrincipalAmount" name="recovery.recyclingPrincipalAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
  		<label id="repayingPrincipalAmount">抵债物抵偿本金：</label>
  		<input id="recovery.repayingPrincipalAmount" name="recovery.repayingPrincipalAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="recyclingInterestAmount">回收金额抵偿表内利息：</label>
  		<input id="recovery.recyclingInterestAmount" name="recovery.recyclingInterestAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="repayingInterestAmount">抵偿物抵偿表内利息：</label>
  		<input id="recovery.repayingInterestAmount" name="recovery.repayingInterestAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="recyclingOutsideAmount">回收金额抵偿表外利息：</label>
  		<input id="recovery.recyclingOutsideAmount" name="recovery.recyclingOutsideAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="repayingOutsideAmount">抵偿物抵偿表外利息：</label>
  		<input id="recovery.repayingOutsideAmount" name="recovery.repayingOutsideAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
  		<label id="recyclingKindsAmount">回收金额抵偿各种费用：</label>
  		<input id="recovery.recyclingKindsAmount" name="recovery.recyclingKindsAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="repayingKindsAmount">抵债物抵偿各种费用：</label>
  		<input id="recovery.repayingKindsAmount" name="recovery.repayingKindsAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="recyclingTotalAmount">回收总费用：</label>
  		<input id="recovery.recyclingTotalAmount" name="recovery.recyclingTotalAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="recyclingDeadline">回收期：</label>
  		<input id="recovery.recyclingDeadline" name="recovery.recyclingDeadline"
  		class="nui-textbox nui-form-input" required="true"/>
  
  		<label id="recyclingFinalAmount">最终回收金额：</label>
  		<input id="recovery.recyclingFinalAmount" name="recovery.recyclingFinalAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="recyclingDate">回收日期：</label>
  		<input id="recovery.recyclingDate" name="recovery.recyclingDate"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<input id="recovery.bookId" name="recovery.bookId" 
		class="nui-hidden nui-form-input" required="true"/>
		
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="save" iconCls="icon-save" onclick="create">保存</a>
	</div>
</div>	
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	query();
	
	//页面初始化
	function query(){
		var form = new nui.Form("#form");
		var json = nui.encode({"bookId":"<%=request.getParameter("bookId")%>"});
		$.ajax({
	        url: "com.bos.npl.book.AssetsBook.getAccessBookRetrieve.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	            form.setData(o);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	}
	//保存
	function create(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
	        url: "com.bos.npl.book.AssetsBook.createAccessBookRetrieve.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	alert("保存成功！");
	            query();
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	}
</script>
</body>
</html>