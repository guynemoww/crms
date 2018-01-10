<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-12
  - Description:TB_IRM_INDEX_SCORE, com.bos.dataset.irm.TbIrmIndexScore
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbIrmIndexScore.indexId"  value="<%=request.getParameter("indexId") %>"   class="nui-hidden"  />

</div>
				

<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="addRow()" id ="add">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)" id ="check">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="removeRow()" id ="delete">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getTbIrmIndexScoreList.biz.ext"
	dataField="tbIrmIndexScores"
	allowResize="true" showReloadButton="false" allowCellValid="true" allowCellEdit="true" allowCellSelect="true" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client" allowCellWrap="true">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="indexNum" headerAlign="center" allowSort="true" required="true" >档位选项
			<input property="editor"  class="nui-textbox"   style="width:100%;" />
		</div>
		<div field="indexScore" headerAlign="center" allowSort="true"  vtype="range:0,100" required="true" vtype="range:0,100">档位分值
			<input property="editor"  class="nui-textbox"   style="width:100%;"  />
		</div>
	
		<div field="indexDesc" headerAlign="center" allowSort="true" >档位描述
		
			<input property="editor"  class="nui-textbox"   style="width:100%;" />
		</div>
		</div>
	</div>
	
	
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
	<a class="nui-button" iconCls="icon-save" onclick="saveData" id="btnSave">保存</a>

</div>		
	
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var indexId="<%=request.getParameter("indexId") %>";

	if ("<%=request.getParameter("view") %>"=="1") {
	
		form.setEnabled(false);
		grid.setReadOnly(true);
		nui.get("btnSave").hide();
		nui.get("add").hide();
		nui.get("check").hide();
		nui.get("delete").hide();
	}
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
         git.unmask();
    }
    search();
    
    function reset(){
		form.reset();
	}
	function addRow() {
        var newRow = { name: "New Row" };
        grid.addRow(newRow, 0);
        //var row = dicttype_grid.getRowByUID(newRow._uid)
        //row.allowInput = true;
        //var json = nui.encode(row);
    }	
    
     function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
    }
   	   function saveData() {
         	git.mask();
         	grid.validate();
         	if (grid.isValid() == false) {
					nui.alert("请将信息填写完整");
					git.unmask();
					return;
			}
			var tbIrmIndexScores = grid.getChanges();
		
		
			//alert(a[0]._state);
			
            var data = {"tbIrmIndexScores":grid.getChanges(),"indexId":indexId};
           
            var json = nui.encode(data);
        	
           grid.loading("保存中，请稍后......");
          			
        
            nui.ajax({
            	
                url: "com.bos.irm.model.saveTbIrmIndexScore.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	grid.reload();
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });	
        }     
        
      function add() {
    
        nui.open({
            url: "<%=request.getContextPath() %>/irm/model/index_score/item_add.jsp?indexId="+indexId,
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
                url: "<%=request.getContextPath() %>/irm/model/index_score/item_edit.jsp?scoreId="+row.scoreId+"&view="+v,
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
            		git.mask();
            	var json=nui.encode({"tbIrmIndexScore":{"scoreId":
            		row.scoreId,version:row.version}});
                $.ajax({
                     url: "com.bos.irm.model.delTbIrmIndexScore.biz.ext",
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
            			git.unmask();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    	git.unmask();
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
