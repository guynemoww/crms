<%@page pageEncoding="UTF-8"%>
<div id="requiregrid">
	<fieldset>
		<legend>
			<span>贷款条件及落实情况</span>
		</legend>
		<div class="nui-toolbar" style="border-bottom: 0; text-align: left;">
			<a class="nui-button" iconCls="icon-add" onclick="add('grid2')" id="grid2add">增加</a>
			<a class="nui-button" iconCls="icon-remove" onclick="remove('grid2')" id="grid2del">删除</a>
		</div>
		<div id="grid2" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.normalCheck.queryRequireInfo.biz.ext"
			dataField="tbAftRequireExecute" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15"
			sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="requirement" headerAlign="center" width="80" allowSort="true">
					本次检查需要落实的条件 <input property="editor" class="nui-textbox" />
				</div>
				<div field="executeResult" headerAlign="center" allowSort="true" width="80" >
					落实情况 <input property="editor" class="nui-textbox" />
				</div>
			</div>
		</div>
	</fieldset>
</div>
