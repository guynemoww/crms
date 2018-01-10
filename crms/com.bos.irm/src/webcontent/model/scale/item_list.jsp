<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-14
  - Description:TB_IRM_MODEL_SCALE, com.bos.dataset.irm.TbIrmModelScale
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbIrmModelScale.modelId"  value="<%=request.getParameter("modelId") %>"   class="nui-hidden"  />
	
</div>
	    
<!--<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.param.getTbIrmScaleDefList.biz.ext"
	dataField="tbIrmScaleDefs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client" editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true">
	<div property="columns">
		
		<div field="creditRatingCd" headerAlign="center" >等级编号
			<input property="editor" class="nui-textbox" style="width:100%;" />
	    </div>
		<div field="creditRatingDisplay" headerAlign="center" allowSort="false" >等级名称
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
		<div field="minValue" headerAlign="center" allowSort="false">最小分值
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
		
		<div field="maxValue" headerAlign="center" allowSort="false">最大分值
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
		</div>
	</div>-->
	
	<div id="grid2" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getTbIrmModelScaleList.biz.ext"
	dataField="tbIrmModelScales"
	allowResize="true" showReloadButton="false" showTotalCount="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="16" sortMode="client" editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true">
	<div property="columns">
		
		
		<div field="creditRatingCd" headerAlign="center" allowSort="false" >等级名称
			
		</div>
		<div field="minValue" headerAlign="center" allowSort="false" vtype="range:0,100">最小分值
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
		
		<div field="maxValue" headerAlign="center" allowSort="false" vtype="range:0,100">最大分值
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
		</div>
	</div>
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">

	<a class="nui-button" iconCls="icon-save" onclick="saveData()" id="btnSave">保存</a>
	<a class="nui-button"  onclick="updateData()" id="btnUpdate">刷新主标尺配置</a>
</div>
<!--			alert(grid1.totalCount)
	if(grid1.totalCount ==0){
		grid1.style.display ="none";
	}else{
		grid.style.display="none";
	}
-->
			
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
<%--	var grid = nui.get("grid1");--%>
	var grid1 = nui.get("grid2");
	var modelId="<%=request.getParameter("modelId") %>";
	
	
	if ("<%=request.getParameter("view") %>"=="1") {
	
		form.setEnabled(false);
		grid1.setReadOnly(true);
		nui.get("btnSave").hide();
		nui.get("btnUpdate").hide();
	}
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
    	 <%-- grid.load(data);--%>
         grid1.load(data);
         git.unmask();
         
    }
    search();
    

    function reset(){
		form.reset();
	}
	
    function add() {
    
        nui.open({
            url: "<%=request.getContextPath() %>/irm/model/scale/item_add.jsp?modelId="+modelId,
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
                url: "<%=request.getContextPath() %>/irm/model/scale/item_edit.jsp?modelScaleId="+row.modelScaleId+"&view="+v,
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
      var rows = grid1.getSelecteds();
            if (rows.length > 0) {
                grid1.removeRows(rows, true);
            }
    }


function saveData() {
			git.mask();
			grid1.validate();
         	if (grid1.isValid() == false) {
					nui.alert("请将信息填写完整");
					git.unmask();
					return;
			}
            var data = {tbIrmModelScales:grid1.getChanges()};
            var json = nui.encode(data);
           grid1.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.irm.model.saveModelScales.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	grid1.reload();
                	git.unmask();

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });
        }
        
   function updateData(){
			nui.confirm("刷新后将删除原有记录是否确认？","确认",function(action){
	            	if(action!="ok") return;
  			 git.mask();
   			var json=nui.encode({"tbIrmModelScale":{"modelId":
            		modelId}});
                nui.ajax({
                url: "com.bos.irm.model.updateModelScales.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	grid1.reload();
                	git.unmask();

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });
           })
   }
	</script>
</body>
</html>
