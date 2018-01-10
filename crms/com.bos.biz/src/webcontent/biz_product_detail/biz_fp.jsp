<%@page pageEncoding="UTF-8"%>
<div id="fpgrid">
	<fieldset>
		  	<legend>
		   		<span>发票信息</span>
		    </legend>
			<div class="nui-toolbar" style="border-bottom:0;text-align: left;" id="fpdiv">
				<a class="nui-button" iconCls="icon-add" onclick="addfp('gridfp')">增加</a>
				<a class="nui-button" iconCls="icon-remove" onclick="removefp('gridfp')">删除</a>
			</div>
			<div id="gridfp" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizInfo.bizInfo.getFpList.biz.ext" dataField="fps"
				allowResize="true" showReloadButton="false" allowCellEdit="true" 
			    allowCellSelect="true"
				sizeList="[10,10,10,10,10,10,10,20,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="fphm" headerAlign="center"  allowSort="true" align="right"  >发票号码
						<input property="editor"  class="nui-textbox"  />
					</div>
					<div field="fpzl" headerAlign="center"   allowSort="true" >发票种类
						<input property="editor"  class="nui-textbox"     />
					</div>
					<div field="fprq" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd"> 发票日期
						 <input property="editor" dateFormat="yyyy-MM-dd" class="nui-datepicker" />
					</div>
					<div field="currencyCd" headerAlign="center"   dictTypeId="CD000001">币种
						<input property="editor"  class="nui-dictcombobox"  dictTypeId="CD000001"/>
					</div>
					<div field="fpje" headerAlign="center"  dataType="currency"  currencyUnit="￥">发票金额
						<input property="editor" class="nui-textbox" required="true" />
					</div>
					<div field="zjbz" headerAlign="center"   allowSort="true" dictTypeId="XD_0002">追加标志
						<input property="editor"  class="nui-dictcombobox"   dictTypeId="XD_0002"/>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
