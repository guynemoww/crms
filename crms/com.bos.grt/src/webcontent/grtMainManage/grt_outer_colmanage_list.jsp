<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-10 12:59:41
  - Description: 押品维护进入主页面
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="押品信息" >
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="6">
			<label>保管机构名称：</label>
			<input name="item.saveOrg" id="item.saveOrg" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" allowInput="false"/>
			
			<label>抵质押人名称：</label>
			<input name="item.partyName" id="item.partyName" class="nui-textbox nui-form-input" vtype="maxLength:60" />
			
			<label>证件类型：</label>
			<input name="item.certType" id="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"  />
		
			<label>证件号码：</label>
			<input name="item.certNum" id="item.certNum" class="nui-textbox nui-form-input" />
			
			<label>出入库状态：</label>
			<input name="item.status"  class="nui-combobox" data="rukuStatus"/>
			
					<% UserObject user = (UserObject)session.getAttribute("userObject");
							String manage = "";
							DataObject[] roles =  (DataObject[]) user.getAttributes().get("roles");
							if (null != roles && roles.length > 0) {
								for (int i=0; i<roles.length; i++) {
										DataObject role = roles[i];
										if ("R1002".equals(role.get("roleid"))||"R1003".equals(role.get("roleid"))||
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
										"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))){
											manage="true";
										}else{
											continue;
										}
									}	        			
							}
							if(manage.equals("true")){
						%>
							<input id="item.userNum"  class="nui-hidden" name="item.userNum"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" />
						<% 
							}
						%>
		</div>

	
	<div class="nui-toolbar" style="text-align:right;border:none" >
      	<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
      	<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
    </div>
  </div>  
	
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" iconCls="icon-add" onclick="add()" id="add">新增</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit(0)" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)" id="edit1">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="rmove">删除</a>
  	</div>
 	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	   url="com.bos.grt.grtMainManage.grtOuter.getOuterGrtInfoList.biz.ext" dataField="tbGrtMortgageBasics"
	   allowResize="true" showReloadButton="false" allowAlternating="true"
	   sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	   <div property="columns">
		  <div type="checkcolumn">选择</div>
		  <div field="ORG_NUM" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
		  <div field="SURETY_NO" headerAlign="center">抵质押物编号</div>
		  <div field="PARTY_NAME" headerAlign="center" allowSort="true" >抵质押人名称</div>
		  <div field="COLL_TYPE" headerAlign="center" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0131" allowSort="true" >抵质押类型</div>
		  <div field="SORT_TYPE" headerAlign="center" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YPZL01" allowSort="true" >抵质押物类型</div>
		  <div field="ASSESS_VALUE" headerAlign="center" allowSort="true" dataType="currency">评估价值（元）</div>
		  <div field="MORTGAGE_VALUE" headerAlign="center" allowSort="true" dataType="currency">权利价值（元）</div>
		  <div field="TOTAL_AMT" headerAlign="center" allowSort="true" dataType="currency">已担保价值（元）</div>
		  <div field="CARD_REG_DATE" headerAlign="center" allowSort="true" >登记生效日期</div>
		  <div field="REG_DUE_DATE" headerAlign="center" allowSort="true" >登记到期日期</div>
		  <div field="REGISTER_CERTI_NO" headerAlign="center" allowSort="true" >登记权证编号</div>
		  <div field="STATUS" headerAlign="center"  allowSort="true" >抵质押物入库状态</div>
	   </div>
   </div>
   </div>
  </div>
	
  <script>
  		var rukuStatus = [{ id: '03', text: '未入库' }, { id: '09', text: '已入库'},{ id: '04', text: '已出库'}];
		nui.parse();
		var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var sortType ;//抵质押物类型
   		
       function reset(){
			form.reset();
	   }
		search();//查询
    	function search() {
			var data = form.getData(); //获取表单多个控件的数据
        	grid.load(data);
    	}
		//新增抵质押人名下的资产
		function add(){
			var partyId;
	    	var sortType;
	    	nui.open({
		        url: nui.context + "/grt/manage/chioseParty/queryPartyList.jsp",
		        showMaxButton: true,
		        title: "选择客户",
		        width: 1000,
		        height: 500,
		        ondestroy: function (action) {            
		            if (action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.getData();
		                data = nui.clone(data);
		                if (data) {
							partyId=data.partyId;
							nui.open({
							   url:nui.context+"/grt/collateralparameter/colllsortparameter/selectCollateralSortInfoTree.jsp",
							   title:"请选择押品分类",
							   width:400,
							   height:500,
							   allowResize: false,
					           showMaxButton: true,
							   ondestroy:function(action){
							    	var iframe = this.getIFrameEl();
									var data = iframe.contentWindow.GetData();
									data = nui.clone(data);
									if (data) {
										sortType = data.sortType;
										parentSortType = data.parentSortType;
										collType = data.collType;
										localGeneral(sortType,parentSortType,collType,partyId);
									}
							    }
							  }); 
		                }
		            }
		        }
		    }); 
		   search();
		    
		}
		
		function localGeneral(sortType,parentSortType,collType,partyId,partyNum,partyName,certType,certCode){
    		nui.open({
			   url:nui.context+"/grt/grtMainManage/addOuterGrtList.jsp?partyId="+partyId+"&sortType="+sortType+"&collType="+collType,
			   title:"押品列表",
			   width:800,
			   height:600,
			   state:"max",
			   allowResize: false,
	           showMaxButton: true,
			   ondestroy:function(action){
					search();
			    }
			 });
    	}
		
		//编辑或者查看
		function edit(v) {
        	var row = grid.getSelected();
        	if(!row){
        		return alert("请选中一条记录");
        	}
        	if('已入库'==row.STATUS&&v=='0'){
        		alert("无法编辑已入库的押品信息!");
        		return;
        	}
        	var title="查看";
        	if(v == "0"){
        		title="编辑";
        		var json = {"suretyId":row.SURETY_ID};
	   			msg = exeRule("RGRT_0003","1",json);//是否关联了生效的担保合同
	   			if(null != msg && '' != msg){
	   				v="2";//部分可修改
	   			}
        	}
			nui.open({
					            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+row.SURETY_ID+"&view="+v+"&collType="+row.COLL_TYPE+"&isManage=1",
					            showMaxButton: true,
					            title: title+"押品信息",
					            width: 800,
					            height: 500,
					            state:"max",
					            ondestroy: function(e) {
					            	search();
					            }
					        });
	      }
      
      //删除押品信息后需要删除关联信息?
 	  function remove() {
         var row = grid.getSelected();
         if('已入库'==row.STATUS){
        		alert("无法删除已入库的押品信息!");
        		return;
        }
    
         if (row) {
         	//判断押品是否关联有用的担保合同
            var json = {"suretyId":row.SURETY_ID};
	   		msg = exeRule("RGRT_0002","1",json);
	   		if(null != msg && '' != msg){
	   			return alert(msg);
	   		}
    		nui.confirm("确定删除吗？","确认",function(action){
	        	if(action!="ok") return;
	        	//需求379  押品管理里如果押品编号为空则物理删除押品信息,否则逻辑删除押品信息
	        	suretyNo=row.SURETY_NO;
	        	if(""==suretyNo||null==suretyNo){			//物理删除
	        		var json=nui.encode({"grtMortgageBasic":{"suretyId":row.SURETY_ID,"sortType":row.SORT_TYPE}});
		            $.ajax({
		                 url: "com.bos.grt.grtMainManage.grtOuter.deleteOuterGrt.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
		                success: function (text) {
		                   nui.alert(text.msg);
		                   search();
		                },
		                error: function () {
		                	nui.alert("操作失败！");
		                }
		            }); 
	        	}else{										//逻辑删除
	        		//这里只把押品状态改成06
	        		var json1=nui.encode({"grtMortgageBasic":{"suretyId":row.SURETY_ID,"mortgageStatus":"06"}});
	        		$.ajax({
		                url: "com.bos.grt.grtMainManage.grtOuter.updateOuterTbGrtGuarantybasic.biz.ext",
		                type: 'POST',
		                data: json1,
		                cache: false,
		                contentType:'text/json',
		                success: function (text) {
		                   nui.alert(text.msg);
		                   search();
		                },
		                error: function () {
		                	nui.alert("操作失败！");
		                }
		            }); 
	        	}
	        });
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
 
	
	//选择机构
	function selectEmpOrg(e){
		var btnEdit = this;
		 nui.open({
            url:  nui.context + "/utp/org/employee/select_all_org_tree.jsp",
            showMaxButton: false,
            title: "选择保管机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
            	if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
		                btnEdit.setValue(data.orgcode);
	                    btnEdit.setText(data.orgname);
                }
            }
            }
        });
	}
    </script>
</body>
</html>
