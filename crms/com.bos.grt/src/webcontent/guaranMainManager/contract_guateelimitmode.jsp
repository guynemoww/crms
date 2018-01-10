<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-06-30 17:04:32
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>查询模式名称</title>
</head>
<body>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" multiSelect="false" allowResize="true" dataField="forOrgs"
		     url="com.bos.platform.Amount.queryOrgName.biz.ext" valueField="id" showPager="false">
		   <div property="columns">            
		        <div type="checkcolumn"></div>            
		        <div field="modelName"  width="" headerAlign="center" autoEscape="false">模式名称</div>
		        <div field="currencyCd"  width="" headerAlign="center" dictTypeId="CD000001">币种</div>                           
		        <div field="creditAmt"  width="" headerAlign="center" autoEscape="false">金额</div>
		        <div field="availableAmt"  width="" headerAlign="center" autoEscape="false">可用金额</div> 
            </div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button"  iconCls="icon-ok" onclick="selectType()">确定</a>
		<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWin()">关闭</a>
	</div>
	<script type="text/javascript">
	nui.parse();
	
	var grid = nui.get("datagrid1");
	function query(){//对公单一客户查询
		<%--var o = form.getData(false, true);//逻辑流必须返回total
		grid.load(o);--%>
		editRow();
	}
	query();
	function editRow(){
       var partyId="<%=request.getParameter("partyId") %>";
       var contractId="<%=request.getParameter("contractId")%>";
       var json = nui.encode({"partyId":partyId,"contractId":contractId});
       $.ajax({
	       url: "com.bos.crt.subcontract.getGuaranteePerson.biz.ext",
	       type: 'POST',
	       data: json,
	       cache: false,
	       contentType:'text/json',
	       success:function(text){
	         if(text.guateeAmount !=null && text.guateeAmount !=""){
	           var guateeAmount=text.guateeAmount;
    		   var guatees=guateeAmount.split(",");
	           if(guatees !=null && guatees.length >0){
				  for(var i=0;i< guatees.length;i++){
				     var json1 = nui.encode({"limitDetailId":guatees[i]});
				      $.ajax({
					      url: "com.bos.crt.subcontract.guaranteeApvdtl.biz.ext",
					      type: 'POST',
					      data: json1,
					      cache: false,
					      contentType:'text/json',
					      success:function(text){
					         grid.addRows(text.forOrgs);
					      },
					      error: function (jqXHR, textStatus, errorThrown) {
					          nui.alert(jqXHR.responseText);
					      }
					    });
				     }
				   }
	          }else{
	        	 var json1 = nui.encode({"partyId":partyId});
				 $.ajax({
					  url: "com.bos.platform.Amount.queryOrgName.biz.ext",
					  type: 'POST',
					  data: json1,
					  cache: false,
					  contentType:'text/json',
					  success:function(text){
					       grid.addRows(text.forOrgs);
					  },
					  error: function (jqXHR, textStatus, errorThrown) {
					       nui.alert(jqXHR.responseText);
					  }
					 });
	        	   }
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            		nui.alert(jqXHR.responseText);
	            }
	    	});
    		
    		
    	
    }
	
	/**
	 * 选择押品类型
	 */
	function selectType(){
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
		 	//模式名称
		 	var modelName = row.modelName;
			var row1 = grid.getSelected();
			var row2 = grid.getSelecteds();//当前选中集合
			if(null==row1){
				nui.alert("请选择担保模式");
				return;
			}
			var guateeAmount="";//担保客户模式
			var amt;//担保模式金额
			for(var i=0;row2.length>i;i++){
				if(i==0){
					if(row2[i].limitDetailId!="" && null!= row2[i].limitDetailId){
						guateeAmount=row2[i].limitDetailId;
					}
				}else{
					guateeAmount=guateeAmount+",";
					guateeAmount+=row2[i].limitDetailId;
				}
				
			}
			var guateeAmount = guateeAmount;
			var str = [partyId,partyNum,partyName,certificateTypeCd,certificateCode,guateeAmount,modelName];
		 	CloseWindow(str);//向父页面传入参与人id
		 }else{
		 	alert("请选择一条记录！");
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