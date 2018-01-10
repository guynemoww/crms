<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-10-24 15:47:24
  - Description:
-->
<head>
<title>批复意见</title>
    <%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="form1" style="width:100%;height:auto" class="nui-form">
		<h2>&nbsp;</h2>
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户名称：</td>
				<td id="PARTY_NAME"></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申报单位：</td>
				<td id="ORGNAME"></td>
			</tr>
			<tr>
				<td>业务申报事项：</td>
				<td>总额<span id="allamt"></span>元（其中贷款<span id="daik"></span>元，银行承兑汇票<span id="cdhp"></span>元，贸易融资<span id="myrz"></span>元）</td>
			</tr>
		</table>
		<div class="nui-toolbar" style="text-align:right;padding-top:-15px;" 
		    borderStyle="border:0;">
		    <a class="nui-button" iconCls="icon-search" onclick="queryExcl()">打印</a>
		</div>
		<fieldset>
			<legend>
	    	<span>批复具体明细：</span>
	    	</legend>
			<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
			url="com.bos.accInfo.accInfo.accountIdeaBook.biz.ext"
				allowResize="true" showReloadButton="false"  allowAlternating="true"
				showPager="false" multiSelect="false" sortMode="client" dataField="obj.info1">
				<div property="columns">
					<div field="APPROVAL_NUM" headerAlign="center" align="center" width="20%">批复明细编号</div>
					<div field="PRODUCT_NAME" headerAlign="center" align="center" width="14%" >业务品种</div>
					<div field="DETAIL_AMT" headerAlign="center" align="center" width="14%" >金额（元）</div>
					<div field="CREDIT_TERM" headerAlign="center" align="center" width="14%" >期限</div>
					<div field="YEAR_RATE" headerAlign="center" align="center" width="12%">利率（%）</div>
					<div field="GUARANTY_TYPE" headerAlign="center" align="center" dictTypeId="CDZC0005" width="12%">担保方式</div>
					<div field="REPAYMENT_TYPE" headerAlign="center"  align="center" dictTypeId="XD_SXCD1162" width="13%">还款方式</div>
				</div>
			</div>	
		</fieldset>
		<h5>&nbsp;</h5>
		<fieldset>
			<legend>
	    	<span>抵质押明细：</span>
	    	</legend>
			<div id="grid2" class="nui-datagrid" style="width:99.5%;height:auto" 
				allowResize="true" showReloadButton="false"  allowAlternating="true"
				url="com.bos.accInfo.accInfo.accountIdeaBook.biz.ext"
				showPager="false" multiSelect="false" sortMode="client" dataField="obj.info2">
				<div property="columns">
					<div field="APPROVAL_NUM" headerAlign="center" align="center" width="20%">批复明细编号</div>
					<div field="SURETY_NO" headerAlign="center" align="center" width="20%" >抵质编号</div>
					<div field="PARTY_NAME" headerAlign="center" align="center" width="19%" >担保人名称</div>
					<div field="SORT_TYPE" headerAlign="center" align="center"  dictTypeId="XD_YWDB02" width="14%" >抵质押物类型</div>
					<div field="ASSESS_VALUE" headerAlign="center" align="center" width="13%">评估价值（元）</div>
					<div field="MORTGAGE_VALUE" headerAlign="center" align="center" width="13%">权利价值（元）</div>
				</div>
			</div>	
		</fieldset>
		<h5>&nbsp;</h5>
		<fieldset>
			<legend>
	    	<span>保证人明细：</span>
	    	</legend>
			<div id="grid3" class="nui-datagrid" style="width:99.5%;height:auto" 
				allowResize="true" showReloadButton="false"  allowAlternating="true"
				url="com.bos.accInfo.accInfo.accountIdeaBook.biz.ext"
				showPager="false" multiSelect="false" sortMode="client" dataField="obj.info3">
				<div property="columns">
					<div field="APPROVAL_NUM" headerAlign="center" align="center" width="35%">批复明细编号</div>
					<div field="PARTY_NAME" headerAlign="center" align="center"width="34%" >保证人名称</div>
					<div field="SURETY_AMT" headerAlign="center" align="center" width="34%" >担保金额（元）</div>
				</div>
			</div>	
		</fieldset>
		<h5>&nbsp;</h5>
		<fieldset>
			<legend>
	    	<span>保证金明细：</span>
	    	</legend>
			<div id="grid4" class="nui-datagrid" style="width:99.5%;height:auto" 
				allowResize="true" showReloadButton="false"  allowAlternating="true"
				url="com.bos.accInfo.accInfo.accountIdeaBook.biz.ext"
				showPager="false" multiSelect="false" sortMode="client" dataField="obj.info4">
				<div property="columns">
					<div field="APPROVAL_NUM" headerAlign="center" align="center"  width="25%">批复明细编号</div>
					<div field="ACCT_NAME" headerAlign="center" align="center" width="25%" >开户人</div>
					<div field="MARGIN_ACCOUNT" headerAlign="center" align="center" width="25%" >保证金账号</div>
					<div field="ACC_BALANCE" headerAlign="center" align="center" width="25%" >保证金金额（元）</div>
				</div>
			</div>	
		</fieldset>
		<h5>&nbsp;</h5>
		<fieldset>
			<legend>
	    	<span>审批结论：</span>
	    	</legend>
			<div id="grid5" class="nui-datagrid" style="width:99.5%;height:auto" 
				allowResize="true" showReloadButton="false"  allowAlternating="true"
				url="com.bos.accInfo.accInfo.accountIdeaBook.biz.ext"
				showPager="false" multiSelect="false" sortMode="client" dataField="obj.info5">
				<div property="columns">
					<div field="performtime" headerAlign="center" align="center" width="11%" dateFormat="yyyy-MM-dd HH:mm:ss">日期</div>
					<div field="orgName" headerAlign="center" align="center" width="11%" >审批机构</div>
					<div field="userName" headerAlign="center" align="center" width="10%" >审批人</div>
					<div field="activityName" headerAlign="center" align="center" width="11%" >岗位</div>
					<div field="opinion" headerAlign="center" align="center" width="60%">审批意见</div>
				</div>
			</div>	
		</fieldset>
	</div> 	


	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form1"); 
		var ollprocessInstId;
    	function search() {
	       <%--  var json=nui.encode({"contractId":"<%=request.getParameter("contractId") %>","indexid":"0","applyId":""}); --%>
	       var json=nui.encode({"applyId":"<%=request.getParameter("applyId") %>","indexid":"0","changeId":"<%=request.getParameter("changeId") %>"});
			$.ajax({
		        url: "com.bos.accInfo.accInfo.accountIdeaBook.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	$("#ORGNAME").text(text.obj.ORGNAME);
		        	$("#PARTY_NAME").text(text.obj.PARTY_NAME);
		        	$("#allamt").text(text.obj.allamt);
		        	$("#cdhp").text(text.obj.cdhp);
		        	$("#myrz").text(text.obj.myrz);
		        	$("#daik").text(text.obj.daik);
		        	var changeId = "<%=request.getParameter("changeId") %>";
		        	var applyId = "<%=request.getParameter("applyId") %>";
		        	var grid1 = nui.get("grid1");
					json1 = {"changeId":changeId,"indexid":"1","applyId":applyId};	
	       			grid1.load(json1);
	       			
	       			var grid2 = nui.get("grid2");
					json2 = {"changeId":changeId,"indexid":"2","applyId":applyId};	
	       			grid2.load(json2);
	       			
	       			var grid3 = nui.get("grid3");
					json3 = {"changeId":changeId,"indexid":"3","applyId":applyId};	
	       			grid3.load(json3);
	       			
	       			var grid4 = nui.get("grid4");
					json4 = {"changeId":changeId,"indexid":"4","applyId":applyId};	
	       			grid4.load(json4);
	       			
	       			var grid5 = nui.get("grid5");
					json5 = {"changeId":changeId,"indexid":"5","applyId":applyId};	
	       			grid5.load(json5);
	       			preloadfn(grid5);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			}); 
	    }
	    search();
	    function reset() {
			form.reset();
		}
		function preloadfn(grid5){
			grid5.on(
				"preload",
				function(e) {
					if (!e.data || e.data.length < 1) {
						return;
					}
					for (var i = 0; i < e.data.length; i++) {
						if(e.data[i].opinion && e.data[i].opinion.length>35){
							e.data[i]['opinion'] = '<a href="#" onclick="toGoCustDetail(\''
								+ e.data[i].workInstanceId
								+ '\');">'
								+ e.data[i]['opinion'] + '</a>';
						}
					}
				});
		}
	function queryExcl(){
			var json=nui.encode({"reqApply":{"applyId":"<%=request.getParameter("applyId") %>","changeId":"<%=request.getParameter("changeId") %>"},"printType":"SPYJS"});
			$.ajax({
		        url: "com.bos.accInfo.accInfo.printAccountIdeaBook.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if (text.swfPath) {
									nui
											.open({
												url : nui.context
														+ "/biz/biz_report/contract_view.jsp?filePath="
														+ text.swfPath,
												title : "批复意见书",
												width : 1000,
												height : 500,
												onload : function() {
												},
												ondestroy : function(action) {
													grid.reload();
												}
	
											});
								} else {
									alert("无打印信息!");
								}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			}); 
	}
	function toGoCustDetail(workInstanceId){
		nui.open({
        url: nui.context+"/crt/accountInfo/account_idea_edit.jsp?workInstanceId="+workInstanceId,
        title: "查看", 
        width: 800,
		height: 500,
        allowResize:true,
		showMaxButton: true,
        onload: function () {
           
        },
        ondestroy: function (action) {
           
        }
    });
	}
    </script>
</body>
</html>