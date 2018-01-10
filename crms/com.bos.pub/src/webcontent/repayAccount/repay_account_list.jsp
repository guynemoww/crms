<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-5-9
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"style="width: 100%; height: auto;">
	<div title="指定还款账号">
	<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	
	<input type="hidden" name="item._entity" value="com.bos.dataset.pub.TbPubRepayAccount" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	
	<div id="crud"  class="nui-toolbar" style="border:0;text-align:left;padding-right:20px">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>

	</div>   
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="repayAccountOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
		<div field="repayAccount" headerAlign="center" allowSort="true">指定还款账号</div>
		<div field="repayAccountName" headerAlign="center" allowSort="true" >指定还款账户户名</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
    var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    function add() {
        nui.open({
            url: nui.context + "/pub/repayAccount/repay_account_info.jsp",
            title: "新增", 
            width: 700, 
        	height: 350,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    git.mask();
                    search();
                }
            }
        });
    }
        
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/pub/repayAccount/repay_account_info.jsp?id="+row.id+"&view="+v,
                title: "编辑", 
                width: 700,
        		height: 350,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
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
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"item":{"id":
            		row.id,
					"_entity":"com.bos.dataset.pub.TbPubRepayAccount"}});
                $.ajax({
                     url: "com.bos.csm.pub.crudCustInfo.delItem.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        grid.reload();
                    },
                    error: function () {
                    	git.unmask();
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }

</script>
</body>
</html>
