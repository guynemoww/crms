<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-16 10:38:33
  - Description:新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>


		<%String miName = request.getParameter("miName");
		if (null == miName || miName.length() < 1 || "null".equals(miName)) miName = "";
		else miName = miName + ": ";
		%>
		<%=miName %>下级指标项或指标分类列表
		 <div class="nui-toolbar" style="border-bottom:0;" id="indexItemToolbar">
			<a class="nui-button" iconCls="icon-add" onclick="addItem()">新增</a>
			<a class="nui-button"  iconCls="icon-close" onclick="delItem">删除</a>
			<a class="nui-button"  iconCls="icon-save" onclick="saveItem">保存</a>
			<a class="nui-button" onclick="initForm">刷新</a>
			&nbsp;&nbsp;
			<a class="nui-button" onclick="modelIndex">编辑下级指标</a>
	    </div>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.model.getModelIndexSubList.biz.ext" dataField="items"
		    idField="iId" allowResize="false" showReloadButton="false"
		    sizeList="[100]" multiSelect="false" pageSize="100" sortMode="client"
		    showPager="false" showFooter="false" 
		    allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="miName" headerAlign="center" allowSort="true">模型指标名称
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>    
			        <div field="indexInd" headerAlign="center" allowSort="true">引用的基本指标
			        	<input property="editor" allowInput="false" 
	   						class="nui-buttonEdit" onbuttonclick="selectBaseIndex" required="true"/>
			        </div>
			        <div field="miOrder" headerAlign="center" allowSort="true">模型指标顺序号
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			    </div>
		</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
if ("<%=request.getParameter("view") %>"=="1") {
	nui.get("indexItemToolbar").hide();
}


function initForm() {
	nui.get("grid1").load({"itemId":"<%=request.getParameter("pMiId") %>"});
}
initForm();

function addItem() {
	var g=nui.get("grid1");
    var newRow = { name: "New Row","tbPubModel":{"modelId":"<%=request.getParameter("modelId") %>"},
    	"pMiId":"<%=request.getParameter("pMiId") %>" };
	g.addRow(newRow, 0);
}
function delItem() {
	var g=nui.get("grid1");
	var rows = g.getSelecteds();
	if (rows.length > 0) {
		g.removeRows(rows, true);
	}
}
        
function saveItem() {
			var g=nui.get("grid1");
            var data = {items:g.getChanges()};
            var json = nui.encode(data);
            g.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.pub.model.model.saveModelIndexs.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	g.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
}

function selectBaseIndex(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/model/model/index_list.jsp",
            showMaxButton: false,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.grid.getSelected();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.indexInd);
                        btnEdit.setText(data.indexInd);
                        var g=nui.get("grid1");
                        g.getSelected().indexId=data.indexId;
                        g.updateRow(g.getSelected(),{"miName":data.indexInd,
                        	"indexId":data.indexId,
                        	"indexInd":data.indexInd
                        	});
                    }
                }
                if (action == "clear") {
                	btnEdit.setValue("");
                	btnEdit.setText("");
                	var g=nui.get("grid1");
                    g.updateRow(g.getSelected(),{
                        	"indexId":"",
                        	"indexInd":""
                        	});
                }
            }
        });            
}

function modelIndex(v) {
			var grid=nui.get("grid1");
			var row = grid.getSelected();
            if (row) {
            	if (!row.miId || row['_state']=='modified') {
            		nui.alert("数据已修改，请先点击保存按钮保存数据");
            		return;
            	}
            	if (!!row.indexInd) {
            		nui.alert("已引用基本指标时，该指标为模型的最末级");
            		return;
            	}
                nui.open({
url: "<%=request.getContextPath() %>/pub/model/model/model_index_sub.jsp?modelId=<%=request.getParameter("modelId") %>&pMiId="
                    	+row.miId+"&view="+v+"&miName="+encodeURI("<%=request.getParameter("miName") %> >> " + row.miName),
                    title: "模型指标", 
                    width: 800,
            		height: 500,
                    allowResize:true,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        //iframe.contentWindow.SetData(data);
                        //this.max();//最大化窗口
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                        //grid.reload();
                   	 	}
                    }
                });
            } else {
                alert("请选中一条记录");
                return;
            }
}
	</script>
</body>
</html>