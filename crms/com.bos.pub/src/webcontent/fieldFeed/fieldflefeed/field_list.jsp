<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-07-04
  - Description:TB_PUB_FIELD_FLE_FEED, com.bos.pub.sys.TbPubFieldFleFeed
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>中文字段名称：</label>
		<input name="tbPubFieldFleFeed.recordeName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>英文字段名称：</label>
		<input name="tbPubFieldFleFeed.recordeField" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>输入域类型：</label>
		<input name="tbPubFieldFleFeed.fildType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="pub_grant_param"/>
		<label>补入类型：</label>
		<input name="tbPubFieldFleFeed.recordeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD2901"/>
		<label>用户工号：</label>
		<input name="tbPubFieldFleFeed.userNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		<label>是否显示：</label>
		<input name="tbPubFieldFleFeed.isShow" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_0002"/>
		<label>经办日期：</label>
		<input name="tbPubFieldFleFeed.createTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />
        
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
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.fieldflefeed.getTbPubFieldFleFeedList.biz.ext"
	dataField="tbPubFieldFleFeeds"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="recordeName" headerAlign="center" allowSort="true" >中文字段名称</div>
		<div field="recordeField" headerAlign="center" allowSort="true" >英文字段名称</div>
		<div field="recordeType" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD2901">补入类型</div>
		<div field="fildType" headerAlign="center" allowSort="true" dictTypeId="pub_grant_param">输入域类型</div>
		<div field="fildProperty" headerAlign="center" allowSort="true" >字典项</div>
		<div field="isShow" headerAlign="center" allowSort="true" dictTypeId="XD_0002">是否显示</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办用户</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="createTime" headerAlign="center" allowSort="true" >经办时间</div>
		
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
	}
	
    function add() {
        nui.open({
            url: nui.context+ "/pub/fieldFeed/fieldflefeed/field_add.jsp",
            title: "新增", 
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
                url: nui.context+ "/pub/fieldFeed/fieldflefeed/field_edit.jsp?recordeId="+row.recordeId+"&view="+v,
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
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbPubFieldFleFeed":{"recordeId":
            		row.recordeId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.fieldflefeed.delTbPubFieldFleFeed.biz.ext",
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
