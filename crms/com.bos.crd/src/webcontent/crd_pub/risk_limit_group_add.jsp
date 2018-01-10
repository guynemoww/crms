<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 09:00:58
  - Description:
-->
<head>
<title>品种组添加</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="group.id" class="nui-hidden nui-form-input"  name="group.id" value=<%=request.getParameter("id")%> />
		<div class="nui-dynpanel" columns="6">
			<label class="nui-form-label">组名称：</label>	
			<input name="group.groupName" id="group.groupName" required="true" 	class="nui-textbox nui-form-input" vtype="maxLength:100"/>
		</div>
</div>		
		<div id="panel4" class="nui-panel" title="品种组信息" expanded="true" style="width:99.5%;height:auto;" showToolbar="false"
			showCollapseButton="true" showFooter="false" allowResize="false">
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		    	<a class="nui-button" id="biz_meeting_add2" iconCls="icon-add" onclick="addRow">添加</a>
		    	<a class="nui-button" id="biz_meeting_remove" iconCls="icon-remove" onclick="del">删除</a>
			</div>
			<div>
				<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
					 url="com.bos.crdPub.riskLimitGroup.getGroupInfo.biz.ext" dataField="groupProducts" allowResize="false" 
					 multiSelect="true" pageSize="200" sortMode="client" 
					 showPager="false" showFooter="false" virtualScroll="true"
					 allowCellEdit="true" allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true">

					 <div property="columns">
					 	<div type="checkcolumn">选择</div>
						<div field="aa" type="indexcolumn" headerAlign="center" allowSort="false">序号</div>
						
						<div  field="productName" headerAlign="center" >品种名称
							<input property="editor"  class="nui-buttonEdit" allowInput="false" dictTypeId="product" onbuttonclick="selectProduct()"/>
						</div>
						
					 </div>
					 
				</div>
			</div>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" id="group_save" iconCls="icon-save" onclick="create">保存</a>
		    <a class="nui-button" id="" iconCls="icon-cancel" onclick="closeWindow">关闭</a>
		</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");
	initPage();
	//初始化
	function initPage(){
		var json = nui.encode({"group":{"id":"<%=request.getParameter("id")%>"}});
        grid.load({"group":{"id":"<%=request.getParameter("id")%>"}});
    }
	//产品树
	function selectProduct(e){
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp",
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action){
                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.currentNode;
	                    data = nui.clone(data);
                    if (data) {
						var cells = grid.getCurrentCell();
						var row = cells[0];
						grid.cancelEdit();
		                grid.updateRow(row, {productCd:data.productCd,productName:data.productName});
                    }
                }
      			return;
        	}
        });
	}
	//新增一行信息
    function addRow() {
        var newRow = { name: "New Row" };
        grid.addRow(newRow,grid.getData());
    }
	//删除品种
	function del(){
		var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
		
		return;
	}
	//保存品种
	function create(){
		//校验
		
		if (form.isValid()==false) {
        	nui.alert("请按规则填写");
        	return;   
        }  
        var oGrid = grid.getChanges();
        var oForm = form.getData();
        
     	if(""==oForm.group.groupName){
     		alert("组名称不能为空！");
     		return false;
        }
        
        var jsonGrid = nui.encode(oGrid);
        var jsonForm = nui.encode(oForm);
        
        var json = jsonForm.substr(0,jsonForm.length-1) + ",members:" + jsonGrid + "}";
        

        $.ajax({
            url: "com.bos.crdPub.riskLimitGroup.saveGroupInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg){
            		alert(mydata.msg);
            		return;
            	}
                alert("保存成功！");
                closeWindow();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	
	function closeWindow(){
		CloseWindow('ok');
	}
</script>
</body>
</html>