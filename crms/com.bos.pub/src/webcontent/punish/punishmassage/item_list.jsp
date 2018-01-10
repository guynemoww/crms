<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):lujinbin
  - Date: 2014-03-19
  - Description:TB_PUNISH_MESSAGE, com.bos.pub.sys.TbPunishMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">

		<label>经办人工号：</label>
		<input name="tbPunishMessage.orgPeopleNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />


		<label>经办日期：</label>
		<input name="tbPunishMessage.orgTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

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
	url="com.bos.pub.punishDeal.getTbPunishMessageList.biz.ext"
	dataField="tbPunishMessages"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="orgPeopleName" headerAlign="center" allowSort="true" dictTypeId="user">经办人姓名</div>
		<div field="orgPeopleNumber" headerAlign="center" allowSort="true" dictTypeId="org">经办人机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" >经办人工号</div>
		<div field="punishMeasure" headerAlign="center" allowSort="true" >处罚措施</div>
		<div field="punishOpinion" headerAlign="center" allowSort="true" >处罚意见</div>
		<div field="orgTime" headerAlign="center" allowSort="true" >处罚日期</div>
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
	
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "pub/punish/punishmassage/item_edit.jsp?punishId="+row.punishId+"&view="+v,
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
            	var json=nui.encode({"tbPunishMessage":{"punishId":
            		row.punishId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.punishDeal.delTbPunishMessage.biz.ext",
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
