<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-03 10:55:01
  - Description:
-->
<head>
<title>放款确认信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>

	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<!-- 报文表头 -->
		<input id="loanInfo.Opr" class="nui-hidden nui-form-input" name ="loanInfo.Opr"/>
		<input id="loanInfo.Aut" class="nui-hidden nui-form-input" name ="loanInfo.Aut"/>
		<input id="loanInfo.AcsMethStan" class="nui-hidden nui-form-input" name ="loanInfo.AcsMethStan"/>
		<input id="loanInfo.SupStan" class="nui-hidden nui-form-input" name ="loanInfo.SupStan"/>
		<input id="loanInfo.RcnStan" class="nui-hidden nui-form-input" name ="loanInfo.RcnStan"/>
		<input id="loanInfo. RlsDep" class="nui-hidden nui-form-input" name ="loanInfo.RlsDep"/>
		<input id="loanInfo. TranFrom" class="nui-hidden nui-form-input" name ="loanInfo.TranFrom"/>
		<input id="loanInfo.SndDate" class="nui-hidden nui-form-input" name ="loanInfo.SndDate"/>
		<input id="loanInfo.BusDate" class="nui-hidden nui-form-input" name ="loanInfo.BusDate"/>
		<input id="loanInfo.TrnTimes" class="nui-hidden nui-form-input" name ="loanInfo.TrnTimes"/>
		<input id="loanInfo.ToCoreSys" class="nui-hidden nui-form-input" name ="loanInfo.ToCoreSys"/>
		<input id="loanInfo.RpsCod" class="nui-hidden nui-form-input" name ="loanInfo.RpsCod"/>
		<!-- 报文表头 -->
		<input id="loanInfo.loanId" class="nui-hidden nui-form-input" name ="loanInfo.loanId"/>
		<div class="nui-dynpanel" columns="2" id="table1">
			<label class="nui-form-label">客户名称：</label>	
			<input id="loanInfo.BrwName" class="nui-text nui-form-input" name="loanInfo.BrwName"/>	
			<label class="nui-form-label">借据编号：</label>	
			<input id="loanInfo.CusNo" class="nui-text nui-form-input" name="loanInfo.CusNo"/>
			<label class="nui-form-label">借据金额：</label>	
			<input id="loanInfo.Amt" class="nui-text nui-form-input" name="loanInfo.Amt"/>
			<label class="nui-form-label">借据币种：</label>	
			<input id="loanInfo.CurrCod" class="nui-text nui-form-input" name="loanInfo.CurrCod"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="biz_gs_info_save" iconCls="icon-save" onclick="save">放款</a>
    		<a class="nui-button" id="" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var loanId ="<%=request.getParameter("loanId") %>";//放款申请ID
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"loanId":"<%=request.getParameter("loanId")%>"});
		$.ajax({
            url: "com.bos.pay.LoanSummary.loanToLcs1.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
			}
        });
	}
	function save(){
        nui.get("biz_gs_info_save").setEnabled(false);
        var o = form.getData();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.pay.LoanSummary.loanToLcs2.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("biz_gs_info_save").setEnabled(true);
        	}
        	alert("放款成功！");
        	CloseWindow('ok');
        	nui.get("biz_gs_info_save").setEnabled(true);
        }});
	}
</script>
</body>
</html>