<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-12-27 15:09:09
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>

</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysCsmcreditwarnParam" class="nui-hidden" />
	<h3>客户资信预警参数</h3>
	<div class="nui-dynpanel" columns="4">
		<label>机构级别:</label>
		<input name="item.institutionLevel" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6004"/>
		<label>客户类型:</label>
		<input name="item.customerType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CustomerType "/>
		
		<label>资信自动预警参数:</label>
		<input name="item.creditwarningParameter" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CreditAutWarPar"/>
		

	</div>
</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<!--<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>-->
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<span class="separator"></span>
	<a class="nui-button" onclick="editParam()">修改客户类预警参数</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"

	url="com.bos.pub.earlyWarning.eariyWarningItem.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="institutionLevel" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD6004">机构级别</div>
		<div field="customerType" headerAlign="center" allowSort="true" dictTypeId="CustomerType ">客户类型</div>
	    <div field="creditwarningParameter" headerAlign="center" allowSort="true" dictTypeId="CreditAutWarPar">资信自动预警参数</div>
		<div field="warningThreshold" headerAlign="center" allowSort="true" >预警阀值</div>
		<div field="warningIndexdescribe" headerAlign="center" allowSort="true"   >预警指标描述</div>
		<div field="warningSignalreceiver" headerAlign="center" allowSort="true" dictTypeId="user" >预警信号接收人</div>
		<div field="warningSignallevel" headerAlign="center" allowSort="true" dictTypeId="WarningSignalLevel">预警信号等级</div>
		<div field="handlingOrgId" headerAlign="center" allowSort="true" dictTypeId="org" >经办机构</div>
		<div field="handlingDate" headerAlign="center" allowSort="true"  >经办日期</div>
		<div field="handlingUserId" headerAlign="center" allowSort="true"  dictTypeId="user">经办人员名称</div>
		
		</div>
	</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		
        function search() {
			var data = form.getData(); //获取表单多个控件的数据
            grid.load(data);
        }
        search();
        
        function reset(){
			form.reset();
			search();
		}
		
        function add() {
            nui.open({
                url: nui.context+"/pub/customerWarningParameters/item_add.jsp",
                title: "新增", 
                width: 800, 
            	height: 500,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                    }
                }
            });
        }
        
        function editParam() {
            var row = grid.getSelected();
            if (!row) {
            	alert("请选中一条记录");
            	return;
            }
            nui.open({
                url: nui.context+"/pub/customerWarningParameters/item_edit.jsp?itemId="+row.csmCreditWarnId,
                title: "参数列表", 
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
                        //grid.reload();
                        //参数编辑完成后无需任何操作
               	 	}
                }
            });
        }
        
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context+"/pub/customerWarningParameters/item_edit.jsp?itemId="+row.pid+"&view="+v,
                    title: "编辑", 
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
            	if (row.ptype == '02') {
            		alert('该条记录不能删除');
            		return;
            	}
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	
	            	var json=nui.encode({"item":{"csmCreditWarnId":
	            		row.csmCreditWarnId}});
	                $.ajax({
	                     url: "com.bos.pub.earlyWarning.eariyWarningItem.delItem.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    		return;
	                    	}
	                        grid.reload();
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