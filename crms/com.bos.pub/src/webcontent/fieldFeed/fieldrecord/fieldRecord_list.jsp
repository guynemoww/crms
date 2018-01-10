<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-07-08
  - Description:TB_PUB_FIELD_RECORD, com.bos.pub.sys.TbPubFieldRecord
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">


		<label>英文字段名称：</label>
		<input name="tbPubFieldRecord.recordeField" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>中文名称：</label>
		<input name="tbPubFieldRecord.recordeName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>补入类型：</label>
		<input name="tbPubFieldRecord.recordeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD2901"/>
		<label>用户工号：</label>
		<input name="tbPubFieldRecord.userNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		<label>经办日期：</label>
		<input name="tbPubFieldRecord.createTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.fieldflefeed.getTbPubFieldRecordList.biz.ext"
	dataField="tbPubFieldRecords"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="businessId" headerAlign="center" allowSort="true" >补入类型ID</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="recordeField" headerAlign="center" allowSort="true" >英文字段名称</div>
		<div field="recordeName" headerAlign="center" allowSort="true" >中文名称</div>
		<div field="recordeType" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD2901">补入类型</div>
		<div field="recordeValue" headerAlign="center" allowSort="true" >补录结果值</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办用户</div>
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
            url: nui.context+"/pub/fieldFeed/fieldrecord/fieldRecord_add.jsp",
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
                url: nui.context+"/pub/fieldFeed/fieldrecord/fieldRecord_edit.jsp?dataRecordId="+row.dataRecordId+"&view="+v,
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
            	var json=nui.encode({"tbPubFieldRecord":{"dataRecordId":
            		row.dataRecordId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.fieldflefeed.delTbPubFieldRecord.biz.ext",
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
