<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-14 10:38:33
  - Description:基本指标修改页面
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input class="nui-hidden nui-form-input" name="base" id="base"/>
	<div class="nui-dynpanel" columns="4">
		<label>指标名称：</label>
		<input class="nui-textbox nui-form-input" enabled="false" name="base.indexName" vtype="maxLength:50" required="true"/>
		
		<label>指标类型：</label>
		<input name="base.indexType" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="model_index_type" enabled="false"/>
		
		<label>得分类型：</label>
		<input colspan="3" name="base.gradeType" required="true" class="nui-dictcombobox nui-form-input" 
			dictTypeId="model_grade_type" enabled="false"/>
			
		<label>指标描述：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="base.indexDesc" vtype="maxLength:500" required="false" style="width:300px;"/>
		
		<label>指标表达式：</label>
		<div colspan="3">
			<!-- <input id="ridIndex" class="nui-hidden nui-form-input" name="base.indexExpr" required="false" style="width:300px;"/>
			 -->
			<a href="#" onclick="editRule('index');return false;">请点击此处</a>
		</div>

		<label>得分表达式：</label>
		<div colspan="3">
			<!-- <input id="ridGrade" class="nui-hidden nui-form-input" name="base.gradeExpr" required="false" style="width:300px;"/>
			-->
			<a href="#" onclick="editRule('indexgrade');return false;">请点击此处</a>
			<br/>以“指标值”表示指标的值
		</div>
		
		<label>动态参数列表（逗号分隔的参数标识）：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="base.indexParam" required="false" style="width:300px;"/>
		
		<!-- <label>嵌套引用的指标列表（逗号分隔）：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="base.indexIndex" required="false" style="width:300px;"/>
		 -->
	</div>
	
	<!-- 
<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
						<tr>
							<td class="nui-form-label">指标名称：</td>
							<td>
<input class="nui-hidden nui-form-input" name="base"/>
<input class="nui-textbox nui-form-input" name="base.indexName" vtype="maxLength:50" required="true" enabled="false"/>
					        </td>
					        <td class="nui-form-label">指标类型:</td>
							<td>
<input name="base.indexType" required="true"
	class="nui-dictcombobox nui-form-input" dictTypeId="model_index_type" enabled="false"/>
					        </td>
							<td class="nui-form-label">指标描述：</td>
							<td>
<input class="nui-textbox nui-form-input" name="base.indexDesc" vtype="maxLength:500" required="false"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">得分类型:</td>
							<td>
<input name="base.gradeType" required="true"
	class="nui-dictcombobox nui-form-input" dictTypeId="model_grade_type" enabled="false"/>
					        </td>
					        <td class="nui-form-label">&nbsp;</td>
							<td>&nbsp;
					        </td>
					        <td class="nui-form-label">&nbsp;</td>
							<td>&nbsp;
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">指标表达式:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="base.indexExpr" required="false" style="width:300px;"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">参数列表（逗号分隔）:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="base.indexParam" required="false" style="width:300px;"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">嵌套引用的指标列表（逗号分隔）:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="base.indexIndex" required="false" style="width:300px;"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">得分表达式:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="base.gradeExpr" required="false" style="width:300px;"/>
<br/>以value表示指标的值
						</tr>
					</table>
					 -->
</div>
				
		 <div class="nui-toolbar" style="border-bottom:0;">
	        <table style="width:100%;">
	            <tr>
		            <td style="width:100%;text-align:center;">
		                <a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
						<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		            </td>
	            </tr>
	        </table>
	    </div>

<div id="indexItemDiv">
		指标项列表：
		 <div class="nui-toolbar" style="border-bottom:0;" id="indexItemToolbar">
			<a class="nui-button" iconCls="icon-add" onclick="addItem()">新增</a>
			<a class="nui-button"  iconCls="icon-close" onclick="delItem">删除</a>
			<a class="nui-button"  iconCls="icon-save" onclick="saveItem">保存</a>
			<a class="nui-button" onclick="initForm2">刷新</a>
	    </div>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.index.getIndexItemList.biz.ext" dataField="items"
		    idField="iId" allowResize="false" showReloadButton="false"
		    sizeList="[100]" multiSelect="false" pageSize="100" sortMode="client"
		    showPager="false" showFooter="false"
		    allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="iValue" headerAlign="center" allowSort="true">选择项值
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>    
			        <div field="iText" headerAlign="center" allowSort="true">选择项名称
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			        <div field="iOrder" headerAlign="center" allowSort="true">顺序号
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			    </div>
		</div>
</div>

<div id="indexRangeDiv">
		比较范围列表：
		 <div class="nui-toolbar" style="border-bottom:0;" id="indexRangeToolbar">
			<a class="nui-button" iconCls="icon-add" onclick="addItem2()">新增</a>
			<a class="nui-button"  iconCls="icon-close" onclick="delItem2">删除</a>
			<a class="nui-button"  iconCls="icon-save" onclick="saveItem2">保存</a>
			<a class="nui-button" onclick="initForm3">刷新</a>
	    </div>
		<div id="grid2" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.index.getIndexRangeList.biz.ext" dataField="items"
		    idField="rId" allowResize="false" showReloadButton="false"
		    sizeList="[100]" multiSelect="false" pageSize="100" sortMode="client"
		    showPager="false" showFooter="false"
		    allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="rOperator" headerAlign="center" allowSort="true" width="40">比较符号
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>    
			        <div field="rTarget" headerAlign="center" allowSort="true">目标值
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			        <div field="rOrder" headerAlign="center" allowSort="true" width="20">顺序号
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			        <div field="rGrade" headerAlign="center" allowSort="true">得分<!-- 得分或表达式(以value表示指标的值) -->
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			    </div>
		</div>
</div>
<br/>
<br/>
<br/>
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var view="<%=request.getParameter("view") %>";
if (view=="1") {
	form.setEnabled(false);
	nui.get("indexItemToolbar").hide();
	nui.get("indexRangeToolbar").hide();
	nui.get("btnSave").hide();
}

function initForm() {
	var json=nui.encode({"base":{"indexId":"<%=request.getParameter("indexId") %>"}});
	$.ajax({
            url: "com.bos.pub.model.index.getIndex.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		//nui.alert(text.base.indexExpr);
            		form.setData(text);
            		if (text.base.indexType=="1") {
            			document.getElementById("indexItemDiv").style.display="";
            		} else {
            			document.getElementById("indexItemDiv").style.display="none";
            		}
            		if (text.base.gradeType=="2") {
            			document.getElementById("indexRangeDiv").style.display="";
            		} else {
            			document.getElementById("indexRangeDiv").style.display="none";
            		}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
initForm(); 
function initForm2() {
	nui.get("grid1").load({"indexId":"<%=request.getParameter("indexId") %>"});
}
initForm2();
function initForm3() {
	nui.get("grid2").load({"indexId":"<%=request.getParameter("indexId") %>"});
}
initForm3();

function addItem() {
	var g=nui.get("grid1");
    var newRow = { name: "New Row","tbPubIndexBase":{"indexId":"<%=request.getParameter("indexId") %>"} };
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
                url: "com.bos.pub.model.index.saveIndexItems.biz.ext",
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

function addItem2() {
	var g=nui.get("grid2");
    var newRow = { name: "New Row","tbPubIndexBase":{"indexId":"<%=request.getParameter("indexId") %>"} };
	g.addRow(newRow, 0);
}
function delItem2() {
	var g=nui.get("grid2");
	var rows = g.getSelecteds();
	if (rows.length > 0) {
		g.removeRows(rows, true);
	}
}
        
function saveItem2() {
			var g=nui.get("grid2");
            var data = {items:g.getChanges()};
            for(var i=0;i<data.items.length;i++) {
            	var rOperator=data.items[i].rOperator;
            	if (rOperator != "="
            		&& rOperator != ">="
            		&& rOperator != ">"
            		&& rOperator != "<="
            		&& rOperator != "<") {
            		nui.alert("操作符只有<、<=、=、>=、>五种！");
            		return;
            	}
            	if (!data.items[i].rTarget
            		|| !data.items[i].rOrder
            		|| !data.items[i].rGrade) {
            		nui.alert("请将信息填写完整！");
            		return;
            	}
            }
            
            var json = nui.encode(data);
            g.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.pub.model.index.saveIndexRanges.biz.ext",
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
	    
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	/*2013-12-19 wsc.hi@163.com 指标表达式、得分表达式都已修改为可视化编辑工具编辑，无法校验。
	if ((o.base.indexType=='2'||o.base.indexType=='3') && !o.base.indexExpr) {
		nui.alert("指标类型为“计算公式”时，必须输入指标表达式");
		return;
	}
	if ((o.base.gradeType=='3') && !o.base.gradeExpr) {
		nui.alert("得分类型为“计算公式得分”时，必须输入得分表达式");
		return;
	}*/
	var json=nui.encode(o);
	//nui.alert(json);
	$.ajax({
            url: "com.bos.pub.model.index.updateIndex.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		//CloseWindow("ok");
            		nui.alert("保存成功");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}

function editRule(t) {
	var o=form.getData();
	var rid= ( t == 'index' ? o.base.indexExpr : o.base.gradeExpr );
	if (!rid) {
		alert('此指标无此表达式！');
		return;
	}
	nui.open({
            url: nui.context+"/pub/grant/pack/rule/rule_edit.jsp?type="
            	+t
            	+"&rid="+rid
            	+"&view="+(view == '1' ? 'const' : view),
            title: (view == 1 ? "查看" : "编辑"), 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    //initForm();
                }
            }
    });
}
	</script>
</body>
</html>