<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-04-08 11:41:49
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品封包</title>
</head>
<body>
	<fieldset style="border:solid 1px #aaa;padding:1px;">
		<legend >选择信封编号</legend>
		<div id="form1" style="width:100%;height:auto;overflow:hidden;">
			<div class="nui-dynpanel" columns="4">
				<label>信封编号：</label>
				<input name="tbGrtRegcardinfo.mailerNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32"
					id="tbGrtRegcardinfo.mailerNum" />
				<label>贷款合同编号：</label>
				<input name="tbGrtRegcardinfo.contractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32"
					id="tbGrtRegcardinfo.contractNum" />
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    		borderStyle="border:0;">
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reload" onclick="reset()">重置</a>
		</div>
		<div id="grid1" class="nui-datagrid" style="width:auto;height:auto" 
			url="com.bos.grt.regmanage.collateralinandoutin.getMailerNumList.biz.ext"
			dataField="tbGrtRegcardinfos" onRowclick="rowClick"
			allowResize="false" showReloadButton="false"
			sizeList="[5,10,15,20,50,100]" multiSelect="false" pageSize="5" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" >选择</div>
				<div field="mailerNum" headerAlign="center" allowSort="true" >信封编号</div>
				<div field="partyName" headerAlign="center" allowSort="true" >担保人名称</div>
				<div field="partyNum" headerAlign="center" allowSort="true" >担保人编号</div>
				<div field="contractNum" headerAlign="center" allowSort="true" >贷款合同号</div>
			</div>
		</div>
	</fieldset>
	
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
   	 	borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-ok" onclick="sureok()">确定</a>
    	<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>
	
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var getMailerNum=null;
	var datathis={};
	//信封编号
	var mnum=null;
	
	function search() {
		var data = form.getData(); //获取表单多个控件的数据
		grid.load(data);
	}
	search();

	function SetData(data) {
		datathis = data;
	}

	/**
	 * 点击行
	 */
	function rowClick(){
		var row = grid.getSelected();
		mnum=row.mailerNum;
	}

	/**
	 * 点击确定
	 */
	function sureok(){
		CloseWindow("ok");
	}

	/**
	 * 关闭
	 */
	function closeok(){
		 CloseWindow();
	}
</script>
</body>
</html>