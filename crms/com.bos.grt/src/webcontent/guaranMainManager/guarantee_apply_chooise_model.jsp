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
	<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
	<title>选择担保公司客户模式</title>
</head>
<body>
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
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button"  iconCls="icon-ok" onclick="selectType()">确定</a>
		<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWin()">关闭</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var grid2 = nui.get("datagrid2");
	function query(){
	   var partyId = "<%=request.getParameter("partyId")%>";
	   grid2.load({partyId:partyId});
	}
	query();
	
	/**
	 * 选择押品类型
	 */
	function selectType(){
		 var row = grid2.getSelected();
		 if(row){
			var row2 = grid2.getSelecteds();//当前选中集合
			var guateeAmount="";//担保客户模式主键
			var modelName = "";//担保客户模式名称
			for(var i=0;row2.length>i;i++){
				if(i==0){
					if(row2[i].limitDetailId!="" && null!= row2[i].limitDetailId){
						guateeAmount=row2[i].limitDetailId;
						modelName = row2[i].modelName
					}
				}else{
					guateeAmount=guateeAmount+",";
					guateeAmount+=row2[i].limitDetailId;
					modelName=modelName+",";
					modelName+=row2[i].modelName;
				}
			}
			var guateeAmount = guateeAmount;
			var str = [guateeAmount,modelName];

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
</script>
</body>
</html>