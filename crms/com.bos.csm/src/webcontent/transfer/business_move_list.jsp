<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):chenchuan
  - Date: 2016-04-10
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="业务列表" >
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.transfer.transfer.businessMoveList"/>
	<input name="roleType" id="roleType" required="true"  class="nui-hidden" value="2"/>
	<div class="nui-dynpanel" columns="6">
		<label class="nui-form-label">机构名称：</label>
		<input id="item.orgNum" name="item.orgNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false"  class="nui-buttonEdit" onbuttonclick="selectOrgQuery"/>
		
		<label>客户经理：</label>
		<input id="item.userNum" name="item.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32"/>
		
		<label>客户名称：</label>
		<input name="item.partyName" id="item.partyName" required="false" class="nui-textbox nui-form-input"/>
		
		<label>客户类型：</label>
		<input id="item.partyTypeCd" name="item.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD1001"  allowInput="false" />
		
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
		
		<label>证件号码：</label>			
		<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" required="false" />
		<!--
		<label>批复编号：</label>			
		<input id="item.approvalNum" name="item.approvalNum" class="nui-textbox nui-form-input nui-hidden" required="false" />
		-->
		<label>中征码：</label>
		<input id="item.middleCode" name="item.middleCode" class="nui-textbox nui-form-input" required="false" />
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none" >
  		<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
    	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>

		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-add" onclick="move()">部分移交</a>
			<a class="nui-button" iconCls="icon-add" onclick="moveAll()">全部移交</a>
			<a class="nui-button" iconCls="icon-zoomin" onclick="moveHistory()">移交历史记录</a>		
		</div>

	<div id="grid1"  class="nui-datagrid" style="width:99.5%;height:auto;"sortMode="client"
				url="com.bos.csm.pub.ibatis.getItem.biz.ext"
				dataField="items"allowAlternating="true"  multiSelect="true"
				allowResize="true" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client" onselectionchanged="onSelectionChanged" >
				<div property="columns">
				<div type="checkcolumn"></div>
				<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
				<div field="approvalNum" headerAlign="center" allowSort="true" >批复编号</div>
				<div field="partyNameLink" headerAlign="center" allowSort="true" >客户名称</div>
				<div field="certType" allowSort="true"  headerAlign="center" dictTypeId="CDKH0002">证件类型</div>   
				<div field="certNum" allowSort="true"  headerAlign="center" dictTypeId="">证件号码</div>   
				<div field="middleCode" allowSort="true"  headerAlign="center" dictTypeId="">中征码</div>   
				<div field="currencyCd" headerAlign=partyNameLinkenter" allowSort="true" dictTypeId="CD000001">币种</div>
				<div field="creditAmt" headerAlign="center" allowSort="true"	dataType="currency">批复金额</div>
				<div field="availableAmt" headerAlign="center" allowSort="true"	dataType="currency">批复可用金额</div>
				<div field="creditUsed" headerAlign="center" allowSort="true"	dataType="currency">批复已用金额</div>
				<div field="userNum" headerAlign="center" allowSort="true"	dictTypeId="user">经办人</div>
			</div>
	</div>
</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	
	<%  UserObject user = (UserObject)session.getAttribute("userObject");
						String manage = "";
						DataObject[] roles =  (DataObject[]) user.getAttributes().get("roles");
						if (null != roles && roles.length > 0) {
							for (int i=0; i<roles.length; i++) {
									DataObject role = roles[i];
									if ("R1002".equals(role.get("roleid"))||"R1003".equals(role.get("roleid"))||
										"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))||
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
										"R1008".equals(role.get("roleid"))||"R1049".equals(role.get("roleid"))||
										"R1051".equals(role.get("roleid"))){
										manage="1";//客户经理
									}else if("R1010".equals(role.get("roleid"))){
										manage="2";//支行行长
									}else{
										continue;
									}
								}	        			
						}
						if(manage.equals("1")){
					%>
						init("1");
					<% 
						}else if(manage.equals("2")){
					%>
						init("2");
					<%
						}
					%>
	function init(v){
		nui.get("item.orgNum").setEnabled(false);
		if(v=="1"){
			nui.get("item.userNum").setValue('<%=((UserObject)session.getAttribute("userObject")).getUserId() %>');
			nui.get("item.userNum").setText('<%=((UserObject)session.getAttribute("userObject")).getUserName() %>');
			nui.get("item.userNum").setEnabled(false);
		}
		nui.get('roleType').setValue(v);
	}
	
	query();
	function query() {
		if(nui.get("item.orgNum").getValue()==""){
			nui.get("item.orgNum").setValue("<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>");
			nui.get("item.orgNum").setText("<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>");
		}
		var o = form.getData();
		grid.load(o);
	}
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['approvalNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].approvalNum+ '\');">'+e.data[i].approvalNum+'</a>';	
       			e.data[i]['partyNameLink']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';	
       		}
       });
	
	function move(){
		var rows=grid.getSelecteds();
		var row = grid.getSelected();
		if (rows.length <= 0) {
			alert("至少选择一条记录");
			return;
		}
		for(var i=0; i<rows.length;i++){
	   	    //判断批复下是否有合同在途
	   	   	var json1 = {"approveId":rows[i].approveId};
	   	    msg = exeRule("PUB_CON_BIZ","1",json1);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
	   	    //判断批复下是否有放款在途	   	    
	   	    var json1 = {"approveId":rows[i].approveId};
	   	    msg = exeRule("PUB_LOAN_BIZ","1",json1);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	   	   		grid.deselect (row);
	   	   	 	return;
	   	    }
	   	    //首次检查
	   	   	var json1 = {"approveId":rows[i].approveId};
	   	    msg = exeRule("PUB_FIRST_CHECK","1",json1);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
	   	    //日常检查
	   	    var json1 = {"partyId":rows[i].partyId};
	   	    msg = exeRule("PUB_NORMAL_CHECK","1",json1);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
	  		//重点检查
	   	  	var json1 = {"approveId":rows[i].approveId};
	   	    msg = exeRule("PUB_POINT_CHECK","1",json1);
	   	    if(null != msg && '' != msg){
	   	   		nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
	   	    //合同变更
	   	    var json1 = {"approveId":rows[i].approveId};
	   	    msg = exeRule("PUB_LOAN_CHANGE","1",json1);
	   	    if(null != msg && '' != msg){
	   	   		nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
	   	    //预警
	   	    var json1 = {"partyId":rows[i].partyId};
	   	    msg = exeRule("PUB_SIGNAL_BIZ","1",json1);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
	   	     //分类
	   	    var json1 = {"approveId":rows[i].approveId};
	   	    msg = exeRule("PUB_CLASS_BIZ","1",json1);
	   	    if(null != msg && '' != msg){
	   	    	nui.alert(msg);
	   	   		grid.deselect (row);
	   	   		return;
	   	    }
		}
		nui.open({
	        url: nui.context + "/csm/transfer/business_move_add.jsp?oldUserNum="+rows[0].userNum+"&oldOrgNum="+rows[0].orgNum,
	        title: "部分移交", 
	        width: 800,
			height: 300,
	        allowResize:true,
			showMaxButton: true,
	        ondestroy: function (action) {
	            if (action == "ok") {
	            	git.mask("form1");
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = nui.clone(data);
	                if (data) {
	                	var o= form.getData();
	                	o.newUserNum =data.newUserNum;
	                	o.newOrgNum = data.newOrgNum;
	                	o.transPerson= data.transPerson;
						o.oldOrgNum = rows[0].orgNum;
						o.oldUserNum =rows[0].userNum;
						o.transferWay="1";
						for(var i=0; i<rows.length;i++){
							var json1 = {"partyId":rows[i].partyId,"orgNum":data.newOrgNum,"userNum":data.newUserNum};
	   	    				msg = exeRule("PUB_0015","1",json1);
	   	    				if(null != msg && '' != msg){
	   	    	 				nui.alert("变更后的客户经理没有客户的权限，请先进行客户移交或客户共享");
	   	   						grid.deselect (row);
	   	   						git.unmask("form1");
	   	   		 				return;
	   	    				}
	   	    			} 
	    				var json = nui.encode({"item" : o,"rows" : rows});
						$.ajax({
							url : "com.bos.csm.transfer.transfer.BusinessMoving.biz.ext",
					        type: 'POST',
					        data: json,
					        contentType:'text/json',
					        cache: false,
					        success: function (text) {
					        debugger;
				            		git.unmask("form1");
									if("00000"==text.msgCod){
										openSubmitView(text.response);
									} else {
										nui.alert(text.msg);
										query();
									}
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            alert(jqXHR.responseText);
					        }
				        });
	                }
	            }
	        }
	    });
	}
	
	 //全部移交
     function moveAll() {
     	var rows=grid.getSelecteds();
		if (rows.length > 0) {
			alert("全部移交不能选定业务");
			return;
		}
        nui.open({
            url: nui.context+ "/csm/transfer/business_moveAll_add.jsp",
            title: "全部移交", 
            width: 800, 
        	height: 300,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    query();
                }
            }
        });
    }
	
	//移交历史记录
     function moveHistory() {
        nui.open({
            url:  nui.context+"/csm/transfer/business_move_history.jsp",
            title: "移交历史记录", 
            width: 1300, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
            }
        });
    }
	
	//弹出审批意见页面
	function openSubmitView(response){
		var url = nui.context + "/csm/transfer/business_move_confirm_tree.jsp?bizId="
					+ response.bizId + "&processInstId="+response.processInstId+"&isSrc=2";
		nui.open({
	               url: url,
	               title: "业务移交", 
	               width: 550, 
	               height: 260,
	               state:"max",
	               onload: function () {
	               },
	               ondestroy: function (action) {
	               		CloseWindow('ok');
	               }
	           });
	}
	
	function onSelectionChanged() {
		var row = grid.getSelected();
		var rows = grid.getSelecteds();
		for (var i = 0; i < rows.length; i++) {
			if (rows[0].userNum != rows[i].userNum) {
				alert("请选择同一客户经理的业务");
				grid.deselect(row);
				return;
			}
		}
		/* 	if (row) {
				if (form.isValid() == false) {
				grid.deselect (row);
				nui.alert("请将移交信息先填写完整");
				return;
			}
			
				//是否有业务权
		        var json1 = {"partyId":row.partyId,"orgNum":nui.get("newOrgNum").getValue(),"userNum":nui.get("newUserNum").getValue()};
		   	    msg = exeRule("PUB_0015","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
			   	     return;
		   	    }
		   	    //判断批复下是否有合同在途
		   	   	var json1 = {"approveId":row.approveId};
		   	    msg = exeRule("PUB_CON_BIZ","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		nui.alert(msg);
		   	   		grid.deselect (rows);
		   	   		return;
		   	    }
		   	    //判断批复下是否有放款在途	   	    
		   	    var json1 = {"approveId":row.approveId};
		   	    msg = exeRule("PUB_LOAN_BIZ","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
		   	   		 return;
		   	    }
		// 	   	    //判断批复下是否有借据在途	   	    
		// 	   	     var json1 = {"approveId":row.approveId};
		// 	   	    msg = exeRule("PUB_SUMMARY_BIZ","1",json1);
		// 	   	    if(null != msg && '' != msg){
		// 	   	   		 nui.alert(msg);
		// 	   	   		 grid.deselect (row);
		// 	   	    }    
		   	    //首次检查
		   	   	var json1 = {"approveId":row.approveId};
		   	    msg = exeRule("PUB_FIRST_CHECK","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
		   	   		 return;
		   	   		
		   	    }
		   	    //日常检查
		   	    var json1 = {"partyId":row.partyId};
		   	    msg = exeRule("PUB_NORMAL_CHECK","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
			   	     return;
		   	    }
		  //重点检查
		   	  	var json1 = {"approveId":row.approveId};
		   	    msg = exeRule("PUB_POINT_CHECK","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
			   	     return;
		   	    }
		   	    //合同变更
		   	    var json1 = {"approveId":row.approveId};
		   	    msg = exeRule("PUB_LOAN_CHANGE","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
		   	   		 return;
		   	   		 
		   	    }
		   	    //预警
		   	       var json1 = {"partyId":row.partyId};
		   	    msg = exeRule("PUB_SIGNAL_BIZ","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
		   	   		 return;
		   	    }
		   	    
		   	       //分类
		   	    var json1 = {"approveId":row.approveId};
		   	    msg = exeRule("PUB_CLASS_BIZ","1",json1);
		   	    if(null != msg && '' != msg){
		   	   		 nui.alert(msg);
		   	   		 grid.deselect (row);
			   	     return;
		   	    }
		   	    
			} */
	}
	//查询机构选择
	function selectOrgQuery() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					data = nui.clone(data);
					if (data) {
						self.orglevel = data.orglevel;
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
					}
				}
			}
		});
	}

	// 客户经理
	function selectCustManegers(e) {
		var orgNum = nui.get("item.orgNum").getValue();
		if (orgNum == "") {
			nui.alert("请选择机构");
			return;
		} else {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum=" + orgNum,
				showMaxButton : true,
				title : "选择客户经理",
				width : 850,
				height : 500,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						data = nui.clone(data);
						if (data) {
							btnEdit.setValue(data.userId);
							btnEdit.setText(data.empName);
						}
					}
				}
			});
		}
	}

	function reset() {
		if (nui.get("roleType").getValue() == "2") {
			nui.get("item.orgNum").setValue("<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>");
			nui.get("item.orgNum").setText("<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>");
			nui.get("item.userNum").setValue();
			nui.get("item.userNum").setText();
		}
		nui.get("item.partyName").setValue();
		nui.get("item.partyTypeCd").setValue();
		nui.get("item.certType").setValue();
		nui.get("item.certNum").setValue();
		//nui.get("item.approvalNum").setValue();
		nui.get("item.middleCode").setValue();
	}
</script>
</body>
</html>
