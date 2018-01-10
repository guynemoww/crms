<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-04-25 10:05:12
  - Description:
-->
<head>
<title>账户列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto" class="nui-form">
	<div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
	    borderStyle="border:0;">
		<%--<a class="nui-button" iconCls="icon-add" id="add" onclick="add()">增加</a>--%>
		<%--<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>--%>
		<a class="nui-button" iconCls="icon-edit" onclick="view(1)">查看</a>
		<%--<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>--%>
	</div>	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.accInfo.accInfo.getLoanAccInfoList.biz.ext"
		dataField="tbLoanZhs"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="zhlx" headerAlign="center"  dictTypeId="XD_ZHLX10001" allowSort="true" >账户类型</div>
			<div field="zh" headerAlign="center" allowSort="true" >账户账号</div>
			<div field="zhmc" headerAlign="center" allowSort="true" >账户名称</div>
			<div field="kzbs" headerAlign="center" allowSort="true"   dictTypeId="XD_SXYW0220">卡折标志</div>
			<div field="zhkhjg" headerAlign="center" allowSort="true" dictTypeId="org">开户行</div>
		</div>
	</div>	
</div> 	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	
    function search() {
		var loanId = "<%=request.getParameter("loanId") %>";
		var json = {"loanId":loanId};	
        grid.load(json);

        
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: nui.context + "/pay/account_info/account_add.jsp?loanId=<%=request.getParameter("loanId") %>",
            title: "新增账户", 
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
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/pay/account_info/account_edit.jsp?id="+row.id+"&view="+v,
                title: "编辑账户", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
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
        function view(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/pay/account_info/account_view.jsp?id="+row.id+"&view=1",
                title: "查看账户", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
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
        	nui.confirm("确定删除账户吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbLoanZh":{"id":
            		row.id,version:row.version}});
                $.ajax({
                     url: "com.bos.accInfo.accInfo.deleteLoanAccInfo.biz.ext",
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