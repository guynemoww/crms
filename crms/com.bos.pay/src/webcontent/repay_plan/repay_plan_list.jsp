<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-04-25 10:05:12
  - Description:
-->
<head>
<title>还款计划列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto" class="nui-form">
	<div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
	    borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-add" id="add" onclick="add()">增加</a>
		<a class="nui-button" iconCls="icon-edit" id="edit" onclick="edit()">编辑</a>
		<a class="nui-button" iconCls="icon-remove" id="del" onclick="remove()">删除</a>
	</div>	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.payInfo.repayPlan.getHkjhList.biz.ext"
		dataField="hkjhs"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="periodsNumber" headerAlign="center"  allowSort="true" >期数</div>
			<div field="repayDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">日期</div>
			<div field="repayAmt" headerAlign="center" allowSort="true" >金额</div>
		</div>
	</div>	
</div> 	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var loanId = "<%=request.getParameter("loanId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";
	
    function search() {
		var json = {"loanId":loanId};	
        grid.load(json);
		if("1" != proFlag){
			nui.get("add").hide();
			nui.get("edit").hide();
			nui.get("del").hide();
			form.setEnabled(false);
		}
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: nui.context + "/pay/repay_plan/repay_plan_add.jsp?loanId="+loanId,
            title: "新增还款计划", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
    function edit() {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/pay/repay_plan/repay_plan_edit.jsp?repayPlanId="+row.repayPlanId,
                title: "编辑还款计划", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
    }
       
    
    function remove() {
        var row = grid.getSelected();
        if (row) {
        	nui.confirm("确定删除还款计划吗？","确认",function(action){
            	if(action!="ok") return;
            	var json = nui.encode({"repayPlan":row});
                $.ajax({
                     url: "com.bos.payInfo.repayPlan.delRepayPlan.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
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