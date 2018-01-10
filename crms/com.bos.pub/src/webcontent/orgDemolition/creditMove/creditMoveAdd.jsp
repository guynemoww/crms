<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):lujinbin
  - Date: 2014-04-15
  - Description:TB_SYS_BUSINESS_TRANSFER, com.bos.pub.sys.TbSysBusinessTransfer
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<input id="manageTeamStatus" name="" class="nui-hidden"  />
	<input id="orgId" name="" class="nui-hidden" />
	<input id="orgIds" name="" class="nui-hidden" />
	<input  name="tbSysBusinessTransfer.status" class="nui-hidden" value="01" />
	<div class="nui-dynpanel" columns="4">
		<label>原开户行机构编号：</label>
		<input id="oldOrgNum" name="tbSysBusinessTransfer.oldOrgNum" required="true" class="nui-buttonEdit" vtype="maxLength:32" onbuttonclick="selectEmpOrg" />
		<label>原用户编号：</label>
		<input id="oldUserNum" name="tbSysBusinessTransfer.oldUserNum" required="true"  class="nui-buttonEdit" onbuttonclick="selectCustManeger"  vtype="maxLength:32" />
		<label>客户编号：</label>
		<input id="customerNum" name="tbSysBusinessTransfer.customerNum" required="true" class="nui-buttonEdit" onbuttonclick="selectConList" vtype="maxLength:20" />
		<label>目标机构编号：</label>
		<input id="newOrgNum" name="tbSysBusinessTransfer.newOrgNum" required="true" class="nui-buttonEdit" vtype="maxLength:32" onbuttonclick="selectEmpOrgs" />
		<label>目标用户编号：</label>
		<input id="newUserNum" name="tbSysBusinessTransfer.newUserNum" required="true" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32" />
		<label>移交性质：</label>
		<input id="move" name="tbSysBusinessTransfer.transferType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD234" onvaluechanged="custmoer" emptyText="请选择"/>
	</div>
	
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto;visibility:hidden" 
				url="com.bos.pub.orgDemolition.getCreditContList.biz.ext"
				dataField="contarts"
				allowResize="true" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="20" sortMode="client" >
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<!-- <div field="approveId" headerAlign="center" allowSort="true" >批复ID</div> -->
					<div field="approvalNum" headerAlign="center" allowSort="true" >批复编号</div>
					<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
					<div field="productName" headerAlign="center" allowSort="true">业务品种</div>
					<div field="bizType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1038">业务性质</div>
					<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
					<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
				</div>
		</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	 
	    var form = new nui.Form("#form1");
	        nui.get("move").setRequired("true");
	     var grid = nui.get("grid1");
	function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
nui.get("btnSave").setEnabled(false);
	     var o=form.getData();
		 var rows=grid.getSelecteds();
		 var move=nui.get("move").getValue();
		 var newOrgNum,oldOrgNum,oldUserNum,newUserNum;
		 newOrgNum=nui.get("newOrgNum").getValue();
		  oldOrgNum=nui.get("oldOrgNum").getValue();
		   oldUserNum=nui.get("oldUserNum").getValue();
		    newUserNum=nui.get("newUserNum").getValue();
		 if(newOrgNum==oldOrgNum &&oldUserNum==newUserNum){
		 	alert("移交机构和用户不能相同");
		 	nui.get("btnSave").setEnabled(true);
		 	return;
		 }else{
		 if(move=="01"){// 全部移交
			 nui.confirm("确认要将指定客户的全部业务移交？", "客户移交提醒",
	         function (action) {            
	         if (action == "ok") {
	         git.mask("form1");
		    var json=nui.encode({"map":{"businessTransfer":o,"manageTeamStatus":nui.get("manageTeamStatus").getValue(),"move":move,"oldUserNum":nui.get("oldUserNum").getValue()}});
				    $.ajax({
			        url: "com.bos.pub.orgDemolition.addTbSysBusinessTransfer.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		nui.get("btnSave").setEnabled(true);
					        	var cus=text.msg.split(":");
				        		var cuss="";
				        		for(var i=0;i<cus.length;i++){
				        	    cuss=cuss+cus[i]+"<br>";
			        	 		}
			        	 	if(cus.length>0){
			        	 	if(text.msg=="该客户下没有可以移交的业务。"){
			        	 	nui.alert("该客户下没有可以移交的业务，选择其他客户试试吧。");
			        	 	nui.get("btnSave").setEnabled(true);
			        	 	git.unmask("form1");
			        	 	}else{
			        	 	git.unmask("form1");	
			        	 	nui.alert("有在途业务的批复有：<br> "+cuss);
			        	 	}
			        	 	}
			        	} else {
			        		nui.get("btnSave").setEnabled(true);
			        		git.unmask("form1");	
			        		CloseWindow("ok");
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});	         
	         }else{
	         nui.get("btnSave").setEnabled(true);
	         git.unmask("form1");	
	         }
	         
	         });
	         	 

		 }else{// 部分移交
		     nui.confirm("确认要将指定客户的这些业务移交？", "客户移交提醒",
	         function (action) {            
	         if (action == "ok") {
	         git.mask("form1");
					  if(rows.length<=0){
						  alert("至少选择一条记录");
						 nui.get("btnSave").setEnabled(true);
						  return;
						 }else{
								 var  conId=new Array;
								 for( var i=0;i<rows.length;i++){
								  conId[i]=rows[i].approveId
								 }
					//  alert(nui.encode(conId));
					  var json=nui.encode({"map":{"businessTransfer":o,"manageTeamStatus":nui.get("manageTeamStatus").getValue(),"move":move,"rows":conId}});
								  $.ajax({
							        url: "com.bos.pub.orgDemolition.addTbSysBusinessTransfer.biz.ext",
							        type: 'POST',
							        data: json,
							        cache: false,
							        contentType:'text/json',
							        success: function (text) {
							       //  alert(nui.encode(text));
							        	if(text.msg){
									        	var cus=text.msg.split(":");
								        		var cuss="";
								        		for(var i=0;i<cus.length;i++){
								        	    cuss=cuss+cus[i]+"<br>";
							        	 	}
							        	 	if(cus.length>0){
							        	 	alert("有在途业务的批复有：<br> "+cuss);
							        	 	}else{
							        	 	nui.alert(text.msg);
							        	 	}
					        	 			
							        	} else {
							        	 git.unmask("form1");
							        	 CloseWindow("ok");
							        	}
							        },
							        error: function (jqXHR, textStatus, errorThrown) {
							            nui.alert(jqXHR.responseText);
							        }
								});
					 }	         
	         }else{
 	         nui.get("btnSave").setEnabled(true);
	         git.unmask("form1");
	         }
	         });

		 	
		 }
		 }
								 
								
	
}
// 根据机构code,用户code,客户id,已放款状态查询客户合同信息
			function custmoer(){
			var customerNum;
			customerNum=nui.get("customerNum").getValue();
			if(customerNum==""){
			   alert("请选择原客户");
			   nui.get("move").setValue();
			   return;
			  }else{
		   			var move=nui.get("move").getValue();
		   			  if(move=="02"){
		   			  //部分移交
					  $("#grid1").css("visibility", "visible");
					  if(customerNum==""){
					  alert("请选择原客户");
					  nui.get("move").setValue();
					  }
					  grid.load({"contart":{"partyId":nui.get("customerNum").getValue(),"userNum":nui.get("oldUserNum").getValue(),"orgNum":nui.get("oldOrgNum").getValue()}});
					  }else{
					  $("#grid1").css("visibility", "hidden");
					  grid.load({"contart":{"partyId":"=="}});
					  }
		   			}
			}
// 原机构信息
function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {

                   		btnEdit.setValue(data.orgcode);
                   		nui.get("orgId").setValue(data.orgid);
                   		var oldUserNum;
                   		if(oldUserNum!=""){
                    	  nui.get("oldUserNum").setValue();
                    	  nui.get("oldUserNum").setText();
                        }

                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    // 新机构信息
function selectEmpOrgs(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/orgDemolition/creditMove/select_all_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                   		btnEdit.setValue(data.orgcode);
                   		nui.get("orgIds").setValue(data.orgid);
                   			var newUserNum;
                   		if(newUserNum!=""){
                    	  nui.get("newUserNum").setValue();
                    	  nui.get("newUserNum").setText();
                    	}
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    // 原客户经理
     function selectCustManeger(e) {
     var oldOrgNum,move,customerNum;
     oldOrgNum=nui.get("oldOrgNum").getValue();
      move=nui.get("move").getValue();
	     if(move==""){
	     
	     }else{
			   	$("#grid1").css("visibility", "hidden");
			   	nui.get("move").setValue("");
			   	nui.get("customerNum").setValue("");
			   	nui.get("customerNum").setText("");
			 grid.load({"contart":{"partyId":"=="}});
											     
	     }
     if(oldOrgNum==""){
       alert("请选择原开户行机构");
       return;
     }else{
     		 var orgId;
          	 orgId=nui.get("oldOrgNum").getValue();
		     var btnEdit = this;
		        nui.open({
		            url: nui.context + "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="+orgId,
		            showMaxButton: true,
		            title: "选择客户经理",
		            width: 800,
		            height: 500,
		            ondestroy: function (action) {            
		                if (action == "ok") {
		                    var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.getData();
		                    data = nui.clone(data);
		                    if (data) {
		                    //  alert(nui.encode(data));
		                    	btnEdit.setValue(data.userId);
		                        btnEdit.setText(data.empName);
		                    }
		                }
		            }
		        });   
		     }
       
        }
        
        
            // 新客户经理
     function selectCustManegers(e) {
     var newOrgNum;
       newOrgNum=nui.get("newOrgNum").getValue();
     if(newOrgNum==""){
       alert("请选择目标机构");
       return;
     }else{
     		 var orgIds;
          	 orgIds=nui.get("newOrgNum").getValue();
		     var btnEdit = this;
		        nui.open({
		            url: nui.context + "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="+orgIds,
		            showMaxButton: true,
		            title: "选择客户经理",
		            width: 800,
		            height: 500,
		            ondestroy: function (action) {            
		                if (action == "ok") {
		                    var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.getData();
		                    data = nui.clone(data);
		                    if (data) {
		                    //  alert(nui.encode(data));
		                    	btnEdit.setValue(data.userId);
		                        btnEdit.setText(data.empName);
		                    }
		                }
		            }
		        });   
		     }
       
        }
          
        //合同信息
        function selectConList(e){
		  var oldOrgNum,oldUserNum,move;//机构code,用户code,移动性质
	     oldOrgNum=nui.get("oldOrgNum").getValue();
	     oldUserNum=nui.get("oldUserNum").getValue();
	      move=nui.get("move").getValue();
	     if(move==""){
	     
	     }else{
											       	$("#grid1").css("visibility", "hidden");
											       	nui.get("move").setValue("");
											     grid.load({"contart":{"partyId":"=="}});
											     
	     }
		 if(oldUserNum==""){
         alert("请选择原用户");
         return;
       }else{
             var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/orgDemolition/creditMove/cusMassage.jsp?oldOrgNum="+oldOrgNum+"&oldUserNum="+oldUserNum,
	            showMaxButton: true,
	            title: "选择客户",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.getData();
	                    data = nui.clone(data);
	                    if (data) {
	                    //  alert(nui.encode(data));
	                    	 btnEdit.setValue(data.partyId);// 客户id
	                         btnEdit.setText(data.partyName); // 客户名称
	                         nui.get("manageTeamStatus").setValue(data.userPlacingCd);// 对此客户的管理权限
	                          
	                    }
	                }
	            }
	        });   
       }
		 
	}
	</script>
</body>
</html>
