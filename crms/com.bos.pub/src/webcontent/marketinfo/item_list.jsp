<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-21
  - Description:TB_PUB_MARKET_INFO, com.bos.pub.sys.TbPubMarketInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.sys.TbPubMarketInfo" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">
		<label>信息适用范围：</label>
		<input name="item.infoRangeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_range" emptyText="请选择"/>

		<label>信息标题：</label>
		<input name="item.infoTitle" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>信息类型：</label>
		<input name="item.infoType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_type" emptyText="请选择"/>

		<label>信息状态：</label>
		<input name="item.infoStatus" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_status" emptyText="请选择"/>

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>&nbsp;&nbsp;
	<a class="nui-button" onclick="save(1)">发布</a>
	<a class="nui-button" onclick="save(2)">停止发布</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.marketinfo.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="INFO_RANGE_CD" headerAlign="center" allowSort="true" dictTypeId="pub_market_info_range">信息适用范围</div>
		<div field="INFO_TYPE" headerAlign="center" allowSort="true" dictTypeId="pub_market_info_type">信息类型</div>
		<div field="INFO_STATUS" headerAlign="center" allowSort="true" dictTypeId="pub_market_info_status">状态</div>
		<div field="INFO_TITLE" headerAlign="center" allowSort="true" >信息标题</div>
		<div field="CREATE_TIME" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
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
                url: nui.context+"/pub/marketinfo/item_add.jsp",
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
			    if (row.INFO_STATUS != '0' && v != 1) {
			    	nui.alert("只有未发布状态才可编辑");
			    	return;
			    }
			    var title = "编辑";
			    if('1' == v){
			    	title = "查看";
			    }
                nui.open({
                    url: nui.context+"/pub/marketinfo/item_edit.jsp?itemId="+row.INFO_ID+"&view="+v,
                    title: title, 
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
	            	var json=nui.encode({"item":{"infoId":
	            		row.INFO_ID,
						"_entity":"com.bos.pub.sys.TbPubMarketInfo"}});
	                $.ajax({
	                     url: "com.bos.pub.systechproduct.delItem.biz.ext",
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
function save(status) {
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
	//nui.alert(json);return;
	$.ajax({
            url: "com.bos.pub.marketinfo.updateItem.biz.ext",
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
}
	</script>
</body>
</html>
