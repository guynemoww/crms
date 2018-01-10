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
	<div class="nui-dynpanel" columns="2" id="financeIndexWeightForm">
		<label>财务指标权重(%)：</label>
		<input  name="financeIndexWeight.indexWeight" id="financeIndexWeight" required="true" vtype="range:0,100"  class="nui-textbox nui-form-input" vtype="maxLength:6" />
	</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getFinanceIndexList.biz.ext"
	dataField="tbIrmModelIndexs"
	allowResize="true" showReloadButton="false" oncellbeginedit="getIndexName" oncellendedit="getIndexFormula" allowCellValid="true" oncellendedit="onPropertyTypeCdChanged" 
	sizeList="[10,15,20,50,100]" multiSelect="false"  pageSize="10" sortMode="client" editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="propertyTypeCd" headerAlign="center" allowSort="true"  dictTypeId="XD_ACCCD0006"  required="true"  >指标小类
			<input property="editor"  class="nui-dictcombobox nui-form-input"  style="width:100%;" dictTypeId="XD_ACCCD0006" />
		</div>
		<div field="indexName" id="indexName" headerAlign="center" allowSort="true"  required="true" >指标名称
			<input property="editor"  class="nui-combobox" textField="indexName" valueField="indexName"  dataField="tbAccFinanceIndexCds" 
			 style="width:100%;" />
			 
		</div>
		<div field="indexFormula" headerAlign="center" allowSort="true" >指标公式
			<input property="editor" class="nui-textarea" style="width:100%;"  enabled="false"/>
		</div>
		<div field="indexWeight" headerAlign="center" allowSort="true"   required="true" vtype="range:0,100" >指标权重(%)
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
	</div>
		

		
</div>
	<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="addRow1()" id="add1">增加</a>
<!--	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>-->
	<a class="nui-button" iconCls="icon-remove" onclick="removeRow1()" id="remove1">删除</a>
	&nbsp;
	&nbsp;
	<a class="nui-button" iconCls="icon-add" onclick="selectScore1()">添加指标分值</a>
</div>
	<div class="nui-dynpanel" columns="2" id="nonFinanceIndexWeightForm">
		<label>非财务指标权重(%)：</label>
		<input name="nonFinanceIndexWeight.indexWeight" id ="nonFinanceIndexWeight" required="true" vtype="range:0,100"  class="nui-textbox nui-form-input" vtype="maxLength:6" />
	</div>
<div id="grid2" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getNonFinanceIndexList.biz.ext"
	dataField="tbIrmModelIndexs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client" oncellbeginedit="getPropertyTypeCd"  
	editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="propertyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0019" required="true" >指标小类
			<input property="editor"  class="nui-dictcombobox nui-form-input"    dictTypeId="XD_PJCD0019"   style="width:100%;" />
		</div>
		<div field="indexName" headerAlign="center" allowSort="true" required="true">指标名称
			<input property="editor"  class="nui-textbox" style="width:100%;"  />
		</div>
		<div field="indexDesc" headerAlign="center" allowSort="true" >指标描述
			<input property="editor" class="nui-textarea" style="width:100%;" />
		</div>
		<div field="indexWeight" headerAlign="center" allowSort="true" vtype="range:0,100" required="true">指标权重(%)
			<input property="editor" class="nui-textbox" style="width:100%;" />
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
	var grid2 = nui.get("grid2");
	var modelId="<%=request.getParameter("modelId") %>";
	//alert(nui.encode(getDictData("XD_PJCD0019","str","0201")));
	
	
	if ("<%=request.getParameter("view") %>"=="1") {
		nui.get("financeIndexWeight").setReadOnly(true);
		nui.get("nonFinanceIndexWeight").setReadOnly(true);
		//nui.get("financeIndexWeight").setEnabled(false);
		//nui.get("nonFinanceIndexWeight").setEnabled(false);
		//form.setEnabled(false);
		//grid.setEnabled(false);
		//grid2.setEnabled(false);
		grid.setReadOnly(true);
		grid2.setReadOnly(true);
		nui.get("btnSave").hide();
		nui.get("add").hide();
		nui.get("add1").hide();
		nui.get("remove").hide();
		nui.get("remove1").hide();
	}
	
	function getPropertyTypeCd(e){
		if(e.field != 'propertyTypeCd'){
			return;
		}
		 e.editor.load(getDictData("XD_PJCD0019","str","0201,0202,0203"));
		 
	}
	function initForm() {
		var json=nui.encode({"tbIrmModelIndex":{"modelId":modelId,
			"indexCode":"01"}});

		$.ajax({
	            url: "com.bos.irm.model.getTbIrmModelIndexList.biz.ext.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            	
	            		if(	text.tbIrmModelIndexs.length != 0){
	            		nui.get("financeIndexWeight").setValue(text.tbIrmModelIndexs[0].indexWeight);
	            		nui.get("financeIndexId").setValue(text.tbIrmModelIndexs[0].indexId);
	            		}
	            		
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
		function initForm1() {
		var json=nui.encode({"tbIrmModelIndex":{"modelId":modelId,"indexCode":"02"}});
		
		$.ajax({
	            url: "com.bos.irm.model.getTbIrmModelIndexList.biz.ext.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		if(text.tbIrmModelIndexs.length != 0){
	            		nui.get("nonFinanceIndexWeight").setValue(text.tbIrmModelIndexs[0].indexWeight);
	            		nui.get("nonFinanceIndexId").setValue(text.tbIrmModelIndexs[0].indexId);
	            		}
	            		
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	initForm1();

	var propertyTypeCd;
	function onPropertyTypeCdChanged(e){
		if(e.field=='propertyTypeCd'){
			if(propertyTypeCd!=e.value){
		
				grid.updateRow(e.row, {"indexName":""});
				
			}
		}	
		//nui.get("indexName").setText("122211");
	}
	

	
	
	function getIndexName(e){
		var rowIndex = e.rowIndex;
		var row=e.row;
		var column=e.column;
		var editor=e.editor;
		if (e.field == 'indexName'){
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

		

	}
	
	function getIndexFormula(e){
		var rowIndex = e.rowIndex;
		var row=e.row;
		var column=e.column;
		var editor=e.editor;
		
		if (e.field == 'indexName'){
		
			var json=nui.encode({"indexFormula":{"indexName": row.indexName}});
			//alert(json);
			$.ajax({
		        url: "com.bos.irm.model.getIndexFormula.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        		
		        	} else {
		        		
		        		if(text.indexFormulas){
		        		
			        		grid.updateRow(row, {"indexFormula":text.indexFormulas[0].indexFormula});
		        		}
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		   
		        }
			});
		}

		

	}

    
    


	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
		
        grid.load(data);
       grid2.load(data);
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
   	function addRow1() {
            var newRow = { name: "New Row" };
            grid2.addRow(newRow, 0);
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
        	if(row.indexId){
	            nui.open({
	                url: "<%=request.getContextPath() %>/irm/model/index_score/item_list.jsp?indexId="+row.indexId+"&view=<%=request.getParameter("view") %>",
	                title: "添加财务指标"+row.indexName+"分值", 
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
        	}else{
        		alert("请先保存指标,再添加得分信息");
        	}
        } else {
            alert("请选中一条记录");
        }
        
    }
          function selectScore1() {
        var row = grid2.getSelected();
        if (row) {
        	if(row.indexId){
	            nui.open({
	                url: "<%=request.getContextPath() %>/irm/model/index_score/item_list1.jsp?indexId="+row.indexId+"&view=<%=request.getParameter("view") %>",
	                title: "添加非财务指标"+row.indexName+"分值", 
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
	        }else{
	        	alert("请先保存指标,再添加得分信息");
	        }
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
         function removeRow1() {
            var rows = grid2.getSelecteds();
            if (rows.length > 0) {
                grid2.removeRows(rows, true);
            }
        }
    
         function saveData() {
         	git.mask();
			 var form = new nui.Form("#financeIndexWeightForm");
			 var form1 = new nui.Form("#nonFinanceIndexWeightForm");
			form.validate();
         	form1.validate();
         	grid.validate();
         	grid2.validate();
         	if (grid.isValid() == false||grid2.isValid() == false||form.isValid() == false||form1.isValid() == false) {
				nui.alert("请将信息填写完整");
				git.unmask();
				return;
			}
			var a = grid.getChanges();
			var a1=grid2.getChanges();
			var financeIndex=grid.getData();
			var nonFinanceIndex=grid2.getData();
			var financeIndexWeight = nui.get("financeIndexWeight").getValue();
			var nonFinanceIndexWeight = nui.get("nonFinanceIndexWeight").getValue();
			var financeIndexId = nui.get("financeIndexId").getValue();
			var	nonFinanceIndexId = nui.get("nonFinanceIndexId").getValue();
			var financeCount = 0;
			
			
        
			//判断财务指标总权重是否为100%
		/*	for(var i = 0;i<financeIndex.length;i++){
				
				financeCount =parseFloat( (financeIndex[i].indexWeight))+parseFloat(financeCount);
				
			}
		
			if(100!=financeCount){
				alert("财务指标总权重不为100%");
					git.unmask();
				return;
			}
		
			var nonFinanceCount = 0;
			//判断非财务指标总权重是否为100%
			for(var i = 0;i<nonFinanceIndex.length;i++){
			nonFinanceCount =parseFloat( (nonFinanceIndex[i].indexWeight))+parseFloat(nonFinanceCount);
				
			}
			
			if(100!=nonFinanceCount){
				alert("非财务指标总权重不为100%");
					git.unmask();
				return;
			}
			if(100!=parseFloat(financeIndexWeight)+parseFloat(nonFinanceIndexWeight)){
				alert("财务指标和非财务指标总权重不为100%");
					git.unmask();
				return;
			}
			
			
						*/
	
			//alert(a[0]._state);
			
            var data = {"financeIndexs":grid.getChanges(),"nonFinanceIndexs":grid2.getChanges(),"financeIndexWeight":financeIndexWeight,"financeIndexId":financeIndexId,"nonFinanceIndexWeight":nonFinanceIndexWeight,"nonFinanceIndexId":nonFinanceIndexId,"modelId":modelId};
           
            var json = nui.encode(data);
        	
           grid.loading("保存中，请稍后......");
           grid2.loading("保存中，请稍后......");
        
            nui.ajax({
            	
                url: "com.bos.irm.model.saveModelIndex.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	grid.reload();
                	grid2.reload();
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
