<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>

<%@page import="commonj.sdo.DataObject"%>
<%@page import="java.util.Map"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08
  - Description:TB_PUB_GRANT_TABLE_COL, com.bos.pub.decision.TbPubGrantTableCol
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
    <%
    		IUserObject user = CommonUtil.getIUserObject();
		String orgcode=user.getUserOrgId();
     %>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="tbPubGrantTableCol.pid" name="tbPubGrantTableCol.pid" class="nui-hidden" value="<%=request.getParameter("itemId") %>"/>
	<div class="nui-dynpanel" columns="6">
		<label>规则标识：</label>
		<input name="tbPubGrantTableCol.rind" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>参数名称：</label>
		<input name="tbPubGrantTableCol.tname" required="false" data="tname" class="nui-combobox nui-form-input" vtype="maxLength:1000" />

<!--		<label>适用机构：</label>
		<input name="tbPubGrantTableCol.torg" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" dictTypeId="org" />

		<label>岗位：</label>
		<input name="tbPubGrantTableCol.tposition" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4000" />

		<label>人员：</label>
		<input type="hidden" name="tbPubGrantTableCol.tuser" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4000" dictTypeId="user"/>
-->
		<label>参数值：</label>
		<input name="tbPubGrantTableCol.tvalue" required="false" class="nui-textbox nui-form-input" vtype="maxLength:2000" />
	</div>
</div>

<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
	<a class="nui-button" iconCls="icon-close" onclick="CloseWindow();">关闭</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" id='edit' onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" id='del' onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getTbPubGrantTableColList.biz.ext"
	dataField="tbPubGrantTableCols"
	allowResize="true" showReloadButton="false" onrowclick="rowclick"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<!--<div field="tid" headerAlign="center" allowSort="true" >授权表项ID</div>
		<div field="pid" headerAlign="center" allowSort="true" >授权表编号</div>-->
		<div field="rind" headerAlign="center" allowSort="true" >规则标识</div>
		<div field="torg" headerAlign="center" allowSort="true" dictTypeId="org">适用机构</div>
		<div field="tname" headerAlign="center" allowSort="true" >参数名称</div>
		<!--<div field="tposition" headerAlign="center" allowSort="true" >岗位</div>-->
		<div field="tvalue" headerAlign="center" allowSort="true" >参数值</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">创建机构</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>
		</div>
	</div>
			
    <script type="text/javascript">

    function rowclick(e){
		if('<%=orgcode %>'==e.record.torg){
			nui.get("edit").hide();
			nui.get("del").hide();
		}else{
			nui.get("edit").show();
			nui.get("del").show();
		}
    }

    var tname=[{text:"单笔金额",id:"单笔金额"},{text:"单户金额",id:"单户金额"}];
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var itemId = "<%=request.getParameter("itemId") %>"
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		nui.get("tbPubGrantTableCol.pid").setValue(itemId);
	}
	
    function add() {
        nui.open({
            url: "pub/grant/pack/table_col/item_add.jsp?itemId="+itemId,
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
                url: "pub/grant/pack/table_col/item_edit.jsp?tid="+row.tid+"&view="+v,
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
            	var json=nui.encode({"tbPubGrantTableCol":{"tid":
            		row.tid,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.decision.delTbPubGrantTableCol.biz.ext",
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
