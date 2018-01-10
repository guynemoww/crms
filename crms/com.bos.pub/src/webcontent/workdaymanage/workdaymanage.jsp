<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): zhaohuaiyuan@git.com.cn
  - Date: 2013-11-21
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.pub.TbPubWorkdayManage" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>查询年份：</label>
		<input name="item.workdayYear"  class="nui-textbox nui-form-input" />

		<label>查询月份：</label>
		<input name="item.workdayMonth"  class="nui-textbox nui-form-input" vtype="maxLength:20" />

	</div>
</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>&nbsp;&nbsp;
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.TbPubWorkdayManage.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="workdayYear" headerAlign="center" allowSort="true" >年份</div>
		<div field="workdayMonth" headerAlign="center" allowSort="true" >月份</div>
		<div field="workdayDay" headerAlign="center" allowSort="true">日期</div>
		<div field="dateType" headerAlign="center" allowSort="true" dictTypeId="DateType" >日期类型</div>
		<div field="holidayType" headerAlign="center" allowSort="true" dictTypeId="HolidayType">节假日类型</div>
		<div field="workdayDisc" headerAlign="center" allowSort="true" >描述</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
		<div field="handlingDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >经办日期</div>
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
                url: nui.context+"/pub/workdaymanage/workdaymanage_add.jsp",
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
        
         function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context+"/pub/workdaymanage/workdaymanage_edit.jsp?itemId="+row.workdayManageId+"&view="+v,
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
                        	if (v==1)
                        		return;//查看
	                        grid.reload();
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
	            	var json=nui.encode({"item":{"workdayManageId":
	            		row.workdayManageId,
						"_entity":"com.bos.dataset.pub.TbPubWorkdayManage"}});
	                $.ajax({
	                     url: "com.bos.pub.TbPubWorkdayManage.delItem.biz.ext",
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
<%--function save(status) {
	var row = grid.getSelected();
	if (!row) {
    	nui.alert("请选中一条记录");
    	return;
    }
    if (row.INFO_STATUS != '0' && status == 1) {
    	nui.alert("只有未发布状态才可发布");
    	return;
    }
    if (row.INFO_STATUS != '1' && status == 2) {
    	nui.alert("只有发布状态才可停止发布");
    	return;
    }
    
    var json=nui.encode({item: {infoId: row.INFO_ID, infoStatus: status}});

	$.ajax({
            url: "com.bos.pub.noticeinfo.updateItem.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		nui.alert('操作成功');
            		grid.reload();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}--%>
	</script>
</body>
</html>
