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
		<a class="nui-button" iconCls="icon-add" id="account_add" onclick="add()">增加</a>
		<%--<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>--%>
		<a class="nui-button" iconCls="icon-edit" onclick="view(1)">查看</a>
		<a class="nui-button" iconCls="icon-remove" id="account_remove" onclick="remove()">删除</a>
	</div>	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.accInfo.accInfo.getAccInfoList1.biz.ext"
		dataField="tbConZhs"  allowAlternating="true"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="zhlx" headerAlign="center"  dictTypeId="XD_ZHLX10001" allowSort="true" >账户类型</div>
			<div field="zh" headerAlign="center" allowSort="true" >账号</div>
			<div field="zhmc" headerAlign="center" allowSort="true" >账户名称</div>
			<div field="zhkhjg" headerAlign="center" allowSort="true" dictTypeId="org">开户行</div>
			<div field="accStatus" headerAlign="center" allowSort="true" >账户状态</div>
		</div>
	</div>	
</div> 	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var contractId = "<%=request.getParameter("contractId") %>";
    //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
    var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
		if("1" != proFlag){
			nui.get("account_add").hide();
			nui.get("account_remove").hide();
			form.setEnabled(false);
		}
    function search() {
		//var data = form.getData(); //获取表单多个控件的数据
		var json = {"contractId":contractId};	
			
        grid.load(json);

        
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: nui.context + "/crt/accountInfo/account_add.jsp?contractId=<%=request.getParameter("contractId") %>",
            title: "新增账户", 
            width: 800, 
        	height: 300,
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
                url: nui.context + "/crt/accountInfo/account_edit.jsp?id="+row.id+"&view="+v,
                title: "编辑账户", 
                width: 800,
        		height: 300,
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
                url: nui.context + "/crt/accountInfo/account_view.jsp?id="+row.id+"&view=1",
                title: "查看账户", 
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
    
    function remove() {
        var row = grid.getSelected();
        if (row) {
	         if(row.zhlx=='0'){
		         var json=nui.encode({"contractId":contractId});
			        $.ajax({
	                     url: "com.bos.accInfo.accInfo.getAccInfoBycontractId.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.count != null && text.count != 0) {
	                    		nui.alert("借新还旧不能删除放款账户！");
	                    		return;
	                    	}else{
					                 nui.confirm("确定删除账户吗？","确认",function(action){
					            	if(action!="ok") return;
					            	var json=nui.encode({"tbConZh":{"id":
					            		row.id,version:row.version}});
					                $.ajax({
					                     url: "com.bos.accInfo.accInfo.deleteAccInfo.biz.ext",
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
	                    	}
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
	         }else{
		         	nui.confirm("确定删除账户吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"tbConZh":{"id":
	            		row.id,version:row.version}});
	                $.ajax({
	                     url: "com.bos.accInfo.accInfo.deleteAccInfo.biz.ext",
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
	         }
        } else {
            alert("请选中一条记录");
        }
    }
    
    

	</script>
</body>
</html>