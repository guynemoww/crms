<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%-- 
  - Author(s): chenchuan
  - Date: 2016-05-12 11:02:56
  - Description:
--%>
<head>
	<%@include file="/common/nui/common.jsp"%>
	<title>申请引入押品</title>
</head>

<body>
	<div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;">
		<input name="collType" class="nui-hidden" id="collType" />
		<input name="partyId" class="nui-hidden" id="partyId" />
		<input name="applyId" class="nui-hidden" id="applyId" />
		<div class="nui-dynpanel" columns="4">
			<label>抵质押人名称：</label>
			<input name="partyName" required="false" class="nui-textbox nui-form-input" id="basicInfo.partyName" vtype="maxLength:60" />
			<label>证件类型：</label>
			<input id="item.certType" name="certType" class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid" dictTypeId="CDKH0002"  allowInput="false" />
			<label>证件号码：</label>			
			<input id="item.certCode" name="certCode" class="nui-textbox nui-form-input"   />
			<label>抵质押物类型：</label>
			<input name="sortType" required="false" class="nui-buttonEdit" allowInput="false" vtype="maxLength:60" id="basicInfo.sortType" onbuttonclick="selectCollSortTree"/>
			<label>抵质押物编号：</label>
			<input name="suretyNo" required="false" class="nui-textbox nui-form-input" id="basicInfo.suretyNo" vtype="maxLength:60" />
		</div>

		<div class="nui-toolbar" style="text-align:right;border:none" >
      	<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
      	<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
    </div>
   	</div>
   	 
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" dataField="tbGrtGuarantybasics"allowAlternating="true"
	    url="com.bos.grt.grtMainManage.grtApply.getAppImportTbGrtGuarantybasicList.biz.ext" allowResize="false" 
	    showReloadButton="false" sizeList="[10,15,20,50,100]" allowCellEdit="true" allowCellSelect="true"
	    multiSelect="false" pageSize="10" sortMode="client" >
	    <div property="columns">
			  <div type="checkcolumn">选择</div>
		       <div type="indexcolumn">序号</div>
			  <div field="PARTY_NAME" headerAlign="center" allowSort="true" >抵质押人名称</div>
			  <div field="CERT_TYPE" headerAlign="center" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" allowSort="true" >证件类型</div>
			  <div field="CERT_NUM" headerAlign="center" allowSort="true" >证件号码</div>
			  <div field="SORT_TYPE" headerAlign="center" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YPZL01" allowSort="true" >抵质押物类型</div>
			  <div field="SURETY_NO" headerAlign="center"  allowSort="true" >抵质押物编号</div>
			  <div field="OWNERSHIP_NUM" allowSort="true" headerAlign="center" >权属证编号</div>
			  <div field="ASSESS_VALUE" headerAlign="center"  allowSort="true" dataType="currency">评估价值</div>
			  <div field="ORG_NUM" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
			  <div field="USER_NUM" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
			  <div field="UPDATE_TIME" headerAlign="center" allowSort="true" >更新日期</div>
		</div>
    </div>
    <div class="nui-toolbar" style="border-bottom:0;text-align:right;" >
		<a class="nui-button" iconCls="icon-ok" onclick="sendCollInfoToApp()" id="">确认</a>
	</div>
	
	<script>
		nui.parse();
		var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var sortType ;//押品分类编号
		var partyId="<%=request.getParameter("partyId") %>";//客户的主键

		//业务ID
		var applyId="<%=request.getParameter("applyId") %>";
		
		 //抵质押标志
		 var collType="<%=request.getParameter("collType") %>";
	 
		//根据输入的条件查询出该抵质押人项下的所有资产
		function search(){
		
			nui.get("collType").setValue(collType);
			nui.get("partyId").setValue(partyId);
			nui.get("applyId").setValue(applyId);
			
    	 	var data = form.getData(); //获取表单多个控件的数据
	     	grid.load(data);

	     	grid.on("preload",function(e){
	       		if (!e.data || e.data.length < 1)
	       			return;
	       		for (var i=0; i<e.data.length; i++){
	       			e.data[i]['PARTY_NAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].PARTY_ID+ '\');">'+e.data[i]['PARTY_NAME']+'</a>';
		       		e.data[i]['SURETY_NO']='<a href="#" onclick="clickSuretyId(\''+e.data[i].SURETY_ID+","+e.data[i].COLL_TYPE+","+e.data[i].SORT_TYPE+ '\')"'+ '">'+e.data[i]['SURETY_NO']+'</a>';
	       		}
	       });
	    }
	    search();
	    
	    function reset(){
			form.reset();
	   }
	   
	   	function clickSuretyId(data){
	   			var arr=data.split(",");
	   			var suretyId=arr[0];
	   			var collType=arr[1];
	   			var sortType=arr[2];
	   			debugger;
	   			var view='1';
	            nui.open({
		            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+suretyId+"&view="+view+"&collType="+collType+"&applyId="+applyId+"&collType="+collType+"&sortType="+sortType,
		            showMaxButton: true,
		            title: "查看押品信息",
		            width: 800,
		            height: 500,
		            state:"max",
		            ondestroy: function(e) {
		            	search();
		            }
		        });
		}
    
    	function init(){
	 		git.mask();
		    var json = nui.encode({parentId:"10000"});
		     $.ajax({
		        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("item.certType").setData(text.levels);
		            custFlag = true;
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		     });
		}
    	init();	
    	
	    /**
	     * 向业务申请发送信息
	     */
		function sendCollInfoToApp(){
			var rows = grid.getSelecteds();
			var row=grid.getSelected();
			//选中的行不允许空值
			if(rows.length>0){
<%--				nui.open({
		            url: nui.context + "/crt/con_grt/saveSuretyAmt.jsp?suretyId="+row.SURETY_ID+"&view=0",
		            showMaxButton: true,
		            title: "添加权利价值",
		            width: 500,
		            height: 200,
		            ondestroy: function(action) {
		            	if (action == "ok") {
		            		var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.GetData();
		                    data = nui.clone(data);
							//var json = nui.encode({"bizGrtRel":{"suretyId":row.SURETY_ID,"applyId":applyId,"suretyType":row.COLL_TYPE,"mortgageValue":data.grtMortgageBasic.mortgageValue}});
							//var json2 = nui.encode({"grtMortgageBasic":{"suretyId":row.SURETY_ID,"mortgageValue":data.grtMortgageBasic.mortgageValue}});
							//var json=json1+json2;--%>
							var json = nui.encode({"bizGrtRel":{"suretyId":row.SURETY_ID,"applyId":applyId,"suretyType":row.COLL_TYPE}});
							git.mask();
							$.ajax({ 
					       		url: "com.bos.grt.grtMainManage.grtImport.importGuaranty.biz.ext",
					       		type: 'POST',
					       		data: json,
					       		cache: false,
					       		contentType:'text/json',
					       		success: function (text) {
					       		    nui.alert(text.msg);
					       			CloseWindow("ok");
					       			git.unmask();
					       		},
					       		error: function (jqXHR, textStatus, errorThrown) {
									nui.alert(jqXHR.responseText);
					       		}
							});
		            	<%--}
		            	//CloseWindow("ok");
		            }
		        });--%>
			}else{
				nui.alert("请选择数据！");
				return;
			}
		}
    
		//选择押品类型
		function selectCollSortTree(e){
			var btnEdit = this;
			nui.open({
			   url:nui.context+"/grt/collateralparameter/colllsortparameter/selectCollateralSortTree.jsp?collType="+collType,
			   title:"请选择押品类型",
			   width:350,
			   height:400,
			   ondestroy:function(action){
			   		if (action == "ok") {
                  		var iframe = this.getIFrameEl();
                  		var data = iframe.contentWindow.GetData();
                  		data = nui.clone(data);
                 		if (data) {
                     		btnEdit.setValue(data.sortType);
                     		btnEdit.setText(data.sortName);
                  		}
              		}
			  }
		    });
	   }
        
        /*
		 *点击窗口中的关闭按钮
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
		
	</script>
</body>
</html>