<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-14 10:38:33
  - Description:参数列表
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
						<tr>
							<td >指标名称：</td>
							<td>
							  <input type="hidden" name="criteria/_entity" value="com.bos.pub.model.TbPubIndexBase" class="nui-hidden" />
							  <input name="criteria/_expr[1]/indexName" class="nui-textbox"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_op" value="like"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_likeRule" value="all"/>
				              
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[2]/indexStatus" value="1"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[2]/_op" value="="/>
					        </td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>
						</tr>
					</table>
				</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
	    
		    <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.index.getIndexList.biz.ext" dataField="indexes"
		    idField="indexId" allowResize="false" showReloadButton="false"
		    sizeList="[10,15,20,50,100]" multiSelect="false" onrowdblclick="save" pageSize="15" sortMode="client">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="indexName" headerAlign="center" allowSort="true">指标名称</div>    
			        <div field="indexInd" headerAlign="center" allowSort="true">指标标识</div>
			        <div field="indexStatus" headerAlign="center" allowSort="true" renderer="onStatus">指标状态</div>
			        <div field="indexType" headerAlign="center" allowSort="true" renderer="onType">指标类型</div>
			        <div field="indexDesc" headerAlign="center" allowSort="true">指标描述</div>
			    </div>
			</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-edit" onclick="view()">查看</a>
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow('clear')">清空</a>
</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		
        function search() {
			var data = form.getData(false,false); //获取表单多个控件的数据
            grid.load(data);
        }
        search();
        
		function save(){
			var row = grid.getSelected();
			if (!row) {
				nui.alert("请选中一条记录");
				return;
			}
        	CloseWindow("ok");
		}
	
        function view() {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/baseindex/index_edit.jsp?view=1&indexId="+row.indexId,
                    title: "查看", 
                    width: 800,
            		height: 500,
                    allowResize:false,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        //iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                        //grid.reload();
                   	 	}
                    }
                });
            } else {
                alert("请选中一条记录");
            }
            
        }
        
        function reset(){
			form.reset();
			search();
		}
		
		function onStatus(e) {
			return nui.getDictText("model_index_status", e.row.indexStatus);
        }
		
		function onType(e) {
			return nui.getDictText("model_index_type", e.row.indexType);
        }
	</script>
</body>
</html>