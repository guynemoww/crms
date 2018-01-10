<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-12
  - Description:TB_IRM_MODEL_INDEX, com.bos.dataset.irm.TbIrmModelIndex
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
			<input  type ="hidden" name="tbIrmModelIndex.modelId" value="<%=request.getParameter("modelId") %>"  class="nui-hidden " />
	
		<input  type ="hidden" name="nonFinanceIndexWeight.indexId" id="nonFinanceIndexId"  class="nui-hidden " />
		<input type ="hidden" name="financeIndexWeight.indexId" id="financeIndexId"  class="nui-hidden " />

</div>
				

<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="addRow()" id="add">增加</a>
<!--	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>-->
	<a class="nui-button" iconCls="icon-remove" onclick="removeRow()" id="remove">删除</a>
	&nbsp;
	&nbsp;
	<a class="nui-button" iconCls="icon-add" onclick="selectScore()">添加指标分值</a>
</div>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getGovernmentFinancingPlatformIndexList.biz.ext"
	dataField="tbIrmModelIndexs"
	allowResize="true" showReloadButton="false"  allowCellValid="true"
	sizeList="[10,15,20,50,100]" multiSelect="false"  pageSize="10" sortMode="client" oncellbeginedit="getPropertyTypeCd"
	 editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true" >
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="propertyTypeCd"  headerAlign="center" allowSort="true"  dictTypeId="XD_PJCD0019" required="true">指标小类
			<input property="editor"  class="nui-dictcombobox nui-form-input"     style="width:100%;" dictTypeId="XD_PJCD0019" />
		</div>
		<div field="indexName" id="indexName" headerAlign="center" allowSort="true" required="true">指标名称
			<input property="editor"  class="nui-textbox"   style="width:100%;" />
			 
		</div>
		<div field="indexDesc" headerAlign="center" allowSort="true" >指标描述
			<input property="editor" class="nui-textbox" style="width:100%;"  />
		</div>
		
		<div field="indexWeight" headerAlign="center" allowSort="true" required="true" vtype="range:0,100">指标权重(%)
			<input property="editor" class="nui-textbox" style="width:100%;"  />
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
	
	var modelId="<%=request.getParameter("modelId") %>";
	var view ="<%=request.getParameter("view") %>";
	if ("<%=request.getParameter("view") %>"=="1") {
	
		form.setEnabled(false);
		//grid.setEnabled(false);
		grid.setReadOnly(true);
		nui.get("btnSave").hide();
		nui.get("add").hide();
		nui.get("remove").hide();
	}
	function getPropertyTypeCd(e){
		if(e.field !='propertyTypeCd'){
			return;
		}
		 e.editor.load(getDictData("XD_PJCD0019","str","0204"));
	}

	function onIndexNameChanged(e){
		//alert(e);
		//nui.get("indexName").setText("122211");
	}
	function getIndexName(e){
		var rowIndex = e.rowIndex;
		var row=e.row;
		var column=e.column;
		var editor=e.editor;
		if (e.field != 'indexName'){
			return;
		}
		
		if(row.propertyTypeCd==""||row.propertyTypeCd==null){
			alert("请先选择指标小类");
			return;
		}
		var json=nui.encode({"tbAccFinanceIndexCd":{"propertyTypeCd": row.propertyTypeCd}});
		//alert(json);
		$.ajax({
	        url: "com.bos.irm.model.getTbAccFinanceIndexCd.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        		
	        	} else {
	        		
	        		editor.load(text.tbAccFinanceIndexCds);
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	   
	        }
		});
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
	
//	function rendererIndexTypeCd(e){
	//		
	//	if(nui.getDictText("XD_GGCD7701",e.row.indexType) != ""){
	///		return nui.getDictText("XD_GGCD7701",e.row.indexType);		
	//	}else if(nui.getDictText("XD_PJCD0019",e.row.indexType) != ""){
	//		return nui.getDictText("XD_PJCD0019",e.row.indexType);
	//	}
	//}
	

	
	function addRow() {
            var newRow = { name: "New Row" };
            grid.addRow(newRow, 0);
            //var row = dicttype_grid.getRowByUID(newRow._uid)
            //row.allowInput = true;
            //var json = nui.encode(row);
        }
 
    function add() {
 
        nui.open({
            url: "<%=request.getContextPath() %>/irm/model/index/item_add.jsp?modelId="+modelId,
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
                url: "<%=request.getContextPath() %>/irm/model/index/item_edit.jsp?indexId="+row.indexId+"&view="+v,
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
    
      function selectScore() {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "<%=request.getContextPath() %>/irm/model/index_score/item_list1.jsp?indexId="+row.indexId +"&view="+view,
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
          
      function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
    
    
         function saveData() {
         	git.mask();
			var a = grid.getChanges();
			grid.validate();
			if (grid.isValid() == false) {
					nui.alert("请将信息填写完整");
					git.unmask();
					return;
			}
			var financeIndex=grid.getData();
	
			

		
 
		
			
			
						
	
			//alert(a[0]._state);
			
            var data = {"financeIndexs":grid.getChanges(),"modelId":modelId};
           
            var json = nui.encode(data);
        	
           grid.loading("保存中，请稍后......");
          
        
            nui.ajax({
            	
                url: "com.bos.irm.model.saveGovernmentFinancingPlatformIndex.biz.ext",
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
        
               	//通用获取业务字典方法
	//param1.dictId:业务字典ID; 
	//param2.type:过滤类型(str:指定字符id过滤(多id以","隔开);sub:获取指定字符串子集;top:获取顶级业务字典)
	//param3.指定的字符串(type为top时可以为空,不做处理)
	
	function getDictData(dictId,type,str){
		var dictData = nui.getDictData(dictId);//获取业务字典的数据
		var arr = nui.encode(dictData).split("},");//业务字典数据字符串化，方便处理
		var strArr = new Array();
		//将字符串存入数组
		if(str.indexOf(",") != -1){
			strArr = str.split(",");
		}else{
			strArr.push(str);
		}
		var dictStr = "";//拼接业务字典字符串
		if(type == "str"){//如果是指定字符串过滤
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var flag = arr[i].indexOf('"dictID":"'+strArr[n]+'"')!="-1";//如果包含指定的字符串
					if(flag){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "sub"){//如果是只获取指定字符串子集
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var s = strArr[n];
					//var flag = arr[i].indexOf('"dictID":"'+s)!="-1";//必须为指定字符串及其子项
					//var flag1 = arr[i].indexOf('"dictID":"'+s+'"')=="-1";//不能为父项
					var flag2 = arr[i].indexOf('"parentid":"'+s+'"')!="-1";//必须为子项（不包含子项的子项）
					if(flag2){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "top"){//如果是只获取最顶级业务字典
			for(var i = 0;i<arr.length;i++){
				var flag = arr[i].indexOf('"parentid":"null"')!="-1";//必须为顶级业务字典
				if(flag){
					dictStr = contactStr(i,dictStr,arr);
				}
			}
		}
		//如果最后一个字典项不符合条件，则增加结束标识符号“}]”
		if(dictStr.charAt(dictStr.length-1) != "]"){
			dictStr = dictStr + "}]";
		}
		var dict = nui.decode(dictStr);
		return dict;
	}
	
		//根据索引值，字符串和数组值拼接(用于过滤业务字典-getDictData)
	function contactStr(index,str,arr){
		if(index == 0){
			str = str + arr[index];
		}else if(index != (arr.length)){
			if(str == ""){
				str = "[" + arr[index];
			}else{
				str = str + "}," + arr[index];
			}
		}
		return str;
	}

	</script>
</body>
</html>
