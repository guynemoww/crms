<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-03 10:28:23
  - Description:
-->
<head>
<title>资金使用台账列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
    <div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    	<a class="nui-button" id="creat" onclick="add">登记</a>
    	<a class="nui-button" id="edit" onclick="edit">修改</a>
    	<a class="nui-button" id="delete" onclick="del">删除</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.payInfo.MoneyUse.queryMoneyUse.biz.ext" dataField="moneyUses"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
	    <div property="columns">
	        <div type="checkcolumn" >选择</div>
	        <div type="indexcolumn">序号</div>        
	        <!-- <div field="PARTY_NAME" allowSort="true"  headerAlign="center">客户名称</div> -->
	        <div field="PAY_TIME" allowSort="true"  headerAlign="center" >支付日期</div>
			<div field="PAY_WAY" allowSort="true"  headerAlign="center" dictTypeId="XD_SXYW0218">支付方式</div>
	        <div field="APPLY_AMOUNT" allowSort="true"  headerAlign="center" >支付金额</div>
	        <div field="PAY_OBJECT" allowSort="true"  headerAlign="center">支付对象</div>
	        <div field="PAY_USE" allowSort="true"  headerAlign="center" >支付用途</div>
			<div field="IS_FIT_DEAL" allowSort="true"  headerAlign="center" dictTypeId="XD_0002">是否符合审批或约定用途</div>
	     </div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var loanId ="<%=request.getParameter("loanId") %>"; 
	var partyId ="<%=request.getParameter("partyId") %>"; 
	var summaryId ="<%=request.getParameter("summaryId") %>"; 
	var loanAmt ="<%=request.getParameter("loanAmt") %>"; 
	var beginDate ="<%=request.getParameter("beginDate") %>"; 
	var grid = nui.get("grid");
	initPage();
	function initPage(){
		grid.load({"summaryId":"<%=request.getParameter("summaryId") %>"});
	}
	
	function add(){
		nui.open({
			url:nui.context + "/pay/money_use/money_use_add.jsp?summaryId="+summaryId+"&loanId="+loanId+"&partyId="+partyId+"&beginDate="+beginDate+"&loanAmt="+loanAmt+"&addFlg=1",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	grid.load({"summaryId":"<%=request.getParameter("summaryId") %>"});
            }
		});
	}
	
	function edit(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔记录");
			return false;
		}
		nui.open({
			url:nui.context + "/pay/money_use/money_use_add.jsp?moneyUseId="+row.MONEY_USE_ID+"&addFlg=2"+"&loanAmt="+loanAmt+"&beginDate="+beginDate,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	grid.load({"summaryId":"<%=request.getParameter("summaryId") %>"});
            }
		});
	}
	
	function del() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"moneyUseId":
            		row.MONEY_USE_ID});
                $.ajax({
                     url: "com.bos.payInfo.MoneyUse.delMoneyUse.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	grid.load({"summaryId":"<%=request.getParameter("summaryId") %>"});
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
</script>
</body>
</html>