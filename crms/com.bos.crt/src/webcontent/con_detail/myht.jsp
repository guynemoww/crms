<%@page pageEncoding="UTF-8"%>
<div id="myhtxxgrid">
	<fieldset>
		  	<legend>
		   		<span>贸易合同信息</span>
		    </legend>
			<div class="nui-toolbar" style="border-bottom:0;text-align: left;">
				<a class="nui-button" iconCls="icon-add" onclick="add('grid4')">增加</a>
				<a class="nui-button" iconCls="icon-remove" onclick="remove('grid4')">删除</a>
			</div>
			<div id="grid4" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.conInfo.conContractInfo.getMyhtxx.biz.ext" dataField="myhtxxs"
				allowResize="true" showReloadButton="false" allowCellEdit="true" 
			    allowCellSelect="true"
				sizeList="[20,20,20,20,20,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="htbh" headerAlign="center" width="80" >合同编号
						<input property="editor" class="nui-textbox" />
					</div>
					<div field="htgf" headerAlign="center" width="80" >合同供方
						<input property="editor" class="nui-textbox" />
					</div>
					<div field="htxf" headerAlign="center" width="80" >合同需方
						<input property="editor" class="nui-textbox" />
					</div>
					<div field="htqdrq" headerAlign="center" width="80" dateFormat="yyyy-MM-dd"> 合同签订日期
						<input property="editor" dateFormat="yyyy-MM-dd" class="nui-datepicker"  allowInput="false"/>
					</div>
					<div field="htdqrq" headerAlign="center" width="80" dateFormat="yyyy-MM-dd"> 合同到期日期
						<input property="editor" dateFormat="yyyy-MM-dd" class="nui-datepicker"  allowInput="false"/>
					</div>
					<div field="htzje" headerAlign="center" width="80" dataType="currency" align="right" >合同总金额
						<input property="editor" class="nui-textbox" required="true" />
					</div>
					<div field="currencyCd" headerAlign="center"  width="80"  allowSort="true" dictTypeId="CD000001">币种
						<input property="editor"  class="nui-dictcombobox"  dictTypeId="CD000001"  />
					</div>
					<div field="bz" headerAlign="center" width="80"  >备注
						<input property="editor" class="nui-textbox" />
					</div>
				</div>
			</div>
		</fieldset>
	</div>
