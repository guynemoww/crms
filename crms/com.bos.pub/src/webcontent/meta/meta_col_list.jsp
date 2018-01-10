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
		表名称(英文、大写、下划线分隔的)：
<input id="tableName" class="nui-textbox nui-form-input" name="tableName"  vtype="maxLength:30" required="true"/>
<a class="nui-button" onclick="initForm2">查询</a>

		 <div class="nui-toolbar" style="border-bottom:0;" id="">
			<a class="nui-button"  iconCls="icon-save" onclick="saveItems">保存</a>
			<a class="nui-button" onclick="initForm2">刷新</a>
	    </div>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.meta.getMetaCols.biz.ext" dataField="items"
		    idField="colName" allowResize="false" showReloadButton="false"
		    sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client"
		    showPager="true" showFooter="true" allowAlternating="true"
		    allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true">
			    <div property="columns">
			        <div type="indexcolumn">序号</div>
			        <div field="colName" headerAlign="center" allowSort="true">列名称
			        </div>
			        <div field="colZhname" headerAlign="center" allowSort="true">列中文名称
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			        <div field="colType" headerAlign="center" allowSort="true">列类型
			        </div>
			        <div field="isPk" headerAlign="center" allowSort="true" renderer="renderIsPk">是否主键
			        	<input property="editor" class="nui-boolcheckbox nui-form-input" />
			        </div>
			        <div field="showOrder" headerAlign="center" allowSort="true">显示顺序
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			        <div field="dictTypeId" headerAlign="center" allowSort="true">业务字典类型代码
			        	<input property="editor" class="nui-textbox" style="width:100%;" />
			        </div>
			    </div>
		</div>

<br/>
<div id="editForm1">
	<div class="nui-dynpanel" columns="4">
			<label>列中文名称：</label>
			<input class="nui-text nui-form-input" name="colZhname" vtype="maxLength:100" required="true"/>
			
			<label>显示顺序：</label>
			<input class="nui-textbox nui-form-input" name="showOrder" vtype="maxLength:100" required="false"/>
			
			<label>业务字典类型代码：</label>
			<input class="nui-textbox nui-form-input" name="dictTypeId" vtype="maxLength:100" required="false"/>
			
			<input class="nui-text nui-form-input" name="x"/>
			<input class="nui-text nui-form-input" name="x"/>
			<div colspan="4">
				<input name="isPk" class="nui-boolcheckbox nui-form-input" text="是否主键" />
				<input name="showInList" class="nui-boolcheckbox nui-form-input" text="是否在查询列表展现" />
				<input name="showInAdd" class="nui-boolcheckbox nui-form-input" text="是否在新增界面展现" />
				<input name="showInEdit" class="nui-boolcheckbox nui-form-input" text="是否在编辑界面展现" />
				<input name="showInView" class="nui-boolcheckbox nui-form-input" text="是否在查看界面展现" />
			</div>
	</div>
</div>

数据模型名称(全名、含点号分隔的)：
<input id="dataName" class="nui-textbox nui-form-input" name="dataName"  vtype="maxLength:300" required="true" style="width:500px;"/>

	<div class="nui-toolbar" style="border-bottom:0;" id="">
		<a class="nui-button"  iconCls="icon-save" onclick="generate">生成</a>
	</div>

    <script type="text/javascript">
	 	nui.parse();

//绑定表单
var db = new nui.DataBinding();
db.bindForm("editForm1", nui.get("grid1"));

function initForm2() {
	var tableName = nui.get("tableName").getValue();//查询时需要
	var dataName = nui.get("dataName").getValue(); //生成页面时需要
	if (!tableName) {
		nui.alert("请先填写表名称");
		return;
	}
	nui.get("grid1").load({"tableName":tableName});
}

function saveItems() {
			var g=nui.get("grid1");
            var data = {items:g.getChanges()};
            var json = nui.encode(data);
            g.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.pub.meta.saveMetaCols.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	g.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });
}


function generate() {
	var tableName = nui.get("tableName").getValue();//查询时需要
	var dataName = nui.get("dataName").getValue(); //生成页面时需要
	if (!tableName || !dataName) {
		nui.alert("请先填写表名称、数据模型名称");
		return;
	}
	var map = {"map": {"tableName":tableName, "dataName":dataName}};
	nui.ajax({
                url: "com.bos.pub.meta.genPage.biz.ext",
                type: 'POST',
                data: map,
                success: function (text) {
                	if (text.msg) {
                		nui.alert(text.msg);
                		return;
                	}
                	nui.alert(text.path);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
	});
}

function renderIsPk(e) {
	if (e.row.isPk == '1')
		return '是';
	return '';
}
	</script>
</body>
</html>