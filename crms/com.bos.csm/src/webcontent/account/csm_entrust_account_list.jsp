<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-5-17
  - Description:TB_CSM_IMPORNANT_EVENT, com.bos.dataset.csm.TbCsmImpornantEvent
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item/_entity" value="com.bos.dataset.csm.TbCsmEntrustAccount" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div id="crud"  class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit('0')">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit('1')">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>

	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="accName" headerAlign="center" allowSort="true" >账户名称</div>
		<div field="entrustProjectName" headerAlign="center" allowSort="true" >委托项目名称</div>
		<div field="entrustAcc" headerAlign="center" allowSort="true" >委托存款账号</div>
		<div field="entrustLoanAcc" headerAlign="center" allowSort="true" >委托贷款基金账号</div>
		<div field="entrustReturnAcc" headerAlign="center" allowSort="true" >委托贷款收息账号</div>
		<div field="entrustReturnPrincipalAcc" headerAlign="center" allowSort="true" >委托人收本账号</div>
		<div field="entrustReturnInterestAcc" headerAlign="center" allowSort="true" >委托人收息账号</div>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
    var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		
	if(qote==1){
		nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}	
  
    git.unmask();
    search();
      function search() {
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    function add() {
    	var rows = nui.get("grid1").getData();
    	var length=rows.length;//记录条数
        nui.open({
            url: nui.context + "/csm/account/csm_entrust_account_add.jsp?partyId="+partyId+"&length="+length,
            title: "新增", 
            width: 800, 
        	height: 400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                git.unmask();
                if(action=="ok"){
                    grid.mask();
                    search();
                }
            }
        });
    }
        
    function edit(v) {
        var row = grid.getSelected();
    	var rows = nui.get("grid1").getData();
    	var length=rows.length;//记录条数
        var title = '编辑';
        if (row) {
        	if(v=='1'){
        		title='查看';
        	}
            nui.open({
                url: nui.context + "/csm/account/csm_entrust_account_edit.jsp?accId="+row.accId+"&view="+v+"&length="+length,
                title: title, 
                width: 800,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    	search();
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
            	var json=nui.encode({"item":{"accId":row.accId,"_entity":"com.bos.dataset.csm.TbCsmEntrustAccount"}});
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
                        grid.mask();
                    	search();
                    },
                    error: function () {
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
