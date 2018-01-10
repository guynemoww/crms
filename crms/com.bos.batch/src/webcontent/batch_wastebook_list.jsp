<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-04-30 10:55:07
  - Description:
-->
<head>
<title>T24流水信息列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" style="text-align:center" columns="2">
		<label>借据号：</label>
		<input name="tbBatchWastebook.duebillserialno" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	<div>T24流水信息列表</div>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.batch.batchquery.getTbBatchWastebookList.biz.ext"
		dataField="tbBatchWastebooks"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="duebillserialno" headerAlign="center" allowSort="true" >借据号</div>
			<div field="occurdate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >发生日期</div>
			<div field="businessdesc" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1212" >交易描述</div>
			<div field="acturalsum" headerAlign="center" allowSort="true" >实际发生额</div>
			<div field="orgid" headerAlign="center" allowSort="true" >机构号</div>
			<div field="currency" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0090" >币种</div>
			<div field="batchdate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >批量日期</div>
		</div>
	</div>
<!--	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
	    borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="view(1)">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>		-->
</div> 	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: nui.context + "/pay/payoutApply/payAccount/account_add.jsp?loanDetailId=<%=request.getParameter("loanDetailId") %>",
            title: "新增账户", 
            width: 800, 
        	height: 164,
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
                url: nui.context + "/pay/payoutApply/payAccount/account_edit.jsp?accountId="+row.accountId+"&view="+v,
                title: "编辑账户", 
                width: 800,
        		height: 164,
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
                url: nui.context + "/pay/payoutApply/payAccount/account_view.jsp?accountId="+row.accountId+"&view=",
                title: "查看账户", 
                width: 800,
        		height: 164,
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
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除账户吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbConLoanAccountInfo":{"accountId":
            		row.accountId,version:row.version}});
                $.ajax({
                     url: "com.bos.pay.payaccount.delTbConLoanAccountInfo.biz.ext",
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