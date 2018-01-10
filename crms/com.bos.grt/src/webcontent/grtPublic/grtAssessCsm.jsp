<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-05-23 14:15:27
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>选择客户</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="assessCsmEntity.partyName" class="nui-textbox nui-form-input" vtype="maxLength:64"/>
			<label>证件号码：</label>
			<input name="assessCsmEntity.certificateCode" class="nui-textbox nui-form-input"/>
			<label>证件类型：</label>
			<input name="assessCsmEntity.certificateTypeCd" textField="dictname" valueField="dictid" class="nui-buttonEdit nui-form-input" 
				dictTypeId="CDKH0002" onbuttonclick="selectCertList" />
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	   	<a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
	   	<a class="nui-button" onclick="reset">重置</a>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	   	url="com.bos.grtpublic.getCsm.getAssessCsm.biz.ext" dataField="assessCsmEntitys"
	   	allowAlternating="true" multiSelect="false" showEmptyText="true"
	   	emptyText="没有查到数据" showReloadButton="false" onselectionchanged=""
	   	onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	   	sizeList="[10,20,50,100]" pageSize="10">
	   	<div property="columns">
	       	<div type="checkcolumn">选择</div>
	       	<div field="partyId" name="partyId" allowSort="true" headerAlign="center" autoEscape="false">客户ID</div>
	       	<div field="partyName" allowSort="true" headerAlign="center" autoEscape="false">客户名称</div> 
	       	<div field="partyTypeCd" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0219">客户类型</div>
	       	<div field="partyNum" allowSort="true" headerAlign="center" autoEscape="false">客户编号</div> 
	       	<div field="certificateTypeCd" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div> 
	       	<div field="certificateCode" allowSort="true" headerAlign="center" >证件号码</div>
	    	</div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button"  iconCls="icon-ok" onclick="selectAssessCsm()">确定</a>
		<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWin()">关闭</a>
	</div>
	<!--
	</br>
	<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;" multiSelect="true" allowResize="true" dataField="forOrgs"
		     url="com.bos.platform.Amount.queryOrgName.biz.ext" valueField="id" >
		   <div property="columns">            
		        <div type="checkcolumn"></div>            
		        <div field="modelName" allowSort="true" width="" headerAlign="center" autoEscape="false">模式名称</div>
		        <div field="currencyCd" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>                           
		        <div field="creditAmt" allowSort="true" width="" headerAlign="center" autoEscape="false">金额</div>
		        <div field="occupiedAmt" allowSort="true" width="" headerAlign="center" autoEscape="false">已用金额</div> 
		        <div field="availableAmt" allowSort="true" width="" headerAlign="center" autoEscape="false">可用金额</div> 
            </div>
	</div> 
	 -->
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	//var grid1 = nui.get("datagrid2");
	var flag;
	grid.hideColumn(grid.getColumn("partyId"));
	//nui.get("datagrid2").hide();
	function query(){//对公单一客户查询
		var o = form.getData(false, true);//逻辑流必须返回total
		grid.load(o);
	}
	query();
	
	function reset() {
		form.setData({});
	}
	
	function rendertype(e){
		return nui.getDictText("XD_KHCD0001",e.row.partyTypeCd);
	}
	
	/**
	 * 点击确定按钮，向父页面传递数据
	 */
	function selectAssessCsm(){
		 var row = grid.getSelected();
		 if(row){
		    //客户主键
		 	var partyId = row.partyId;
		 	//定义客户编号
		 	var partyNum = row.partyNum;
		 	//定义客户名称
		 	var partyName = row.partyName;
		 	//定义客户证件类型
		 	var certificateTypeCd = row.certificateTypeCd;
		 	//定义客户证件号码
		 	var certificateCode = row.certificateCode;
		 	//客户类型
		 	var partyTypeCd = row.partyTypeCd;
		 	
		 	var str = [partyId,partyNum,partyName,certificateTypeCd,certificateCode,partyTypeCd];
		 	CloseWindow(str);//向父页面传入参与人id
		 }else{
		 	nui.alert("请选择一条记录！");
		 }
		     
	}
	
	function CloseWindow(action) {  
	  if(action!="ok" && action!="close"){
	  	window.CloseOwnerWindow(action);
	  }else{
	  	window.CloseOwnerWindow("ok");
	  }	          
	}
	
	function CloseWin(){
		CloseWindow("ok");
	}
	
	function selectCertList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0002",
            title: "选择证件类型",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
</script>
</body>
</html>