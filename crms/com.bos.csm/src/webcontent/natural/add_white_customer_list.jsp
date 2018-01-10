<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2015-5-19 8:26:27
  - Description:
-->
<head>
<title>白名单客户信息维护</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%; height: auto;">
<div title="白名单客户" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		
<input id="item.batchNumber" name="item.batchNumber" class="nui-hidden nui-form-input"/>
		
	</div>
</div>

<div style="width:99.5%">
	<div class="nui-toolbar toolbar-add">
		<a id="importExcel"  class="nui-button" iconCls="icon-add" onclick="importExcel()" >导入</a>
		<!-- <a class="nui-button" id="pj_import" iconCls="icon-add" onclick="importExcel()">Excel 批量导入</a> -->
		<!-- <a id="downExcel"  class="nui-button" iconCls="icon-edit" onclick="downExcel" >模板下载</a> -->
<!-- 	    <a id ="edit" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">修改</a>
	    <a id ="update" class="nui-button" iconCls="icon-zoomin" onclick="edit(2)">失效</a> -->
	     <a id ="edit" class="nui-button" iconCls="icon-zoomin" onclick="edit(3)">删除</a>
	    <a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a>
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.natural.natural.getWhiteCustomerList.biz.ext" dataField="items"  allowAlternating="true"
	allowResize="true" showReloadButton="false"  
	sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client">
	    <div property="columns">
	        <div type="checkcolumn"></div>  
	        <div field="cusName" allowSort="true"headerAlign="center" >客户名称</div>
	        <div field="ecifPartyNum" allowSort="true" headerAlign="center" >ECIF客户编号</div>     
	        <div field="certNum" allowSort="true" headerAlign="center" >证件号码</div>
<!-- 			<div field="rate" allowSort="true" headerAlign="center" >利率</div> -->
			<div field="totalLimit"  allowSort="true" headerAlign="center" >最高额度</div>
			<div field="jobRank"  allowSort="true" headerAlign="center"  dictTypeId="XD_KHCD2004">职级</div>
			<div field="income"  allowSort="true" headerAlign="center" >收入</div>
			<div field="manageUser" dictTypeId="user"  allowSort="true" headerAlign="center" >管户人</div>
			<div field="manageOrg"  allowSort="true" headerAlign="center" >管户机构</div>
			<div field="cusStatus"  allowSort="true" headerAlign="center" dictTypeId="XD_KHZT0001">客户状态</div>
	     </div>
</div>

</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");	
	var batchNumber = "<%=request.getParameter("batchNumber") %>" ;
	nui.get('item.batchNumber').setValue(batchNumber);
    queryInit();
    function queryInit() {
		var o = form.getData();//逻辑流必须返回total
		grid.load(o);
	} 
			function zhunru() {

				var url = "/csm/natural/white_natural_tree.jsp?partyId="
						+ row.partyId + "&qote=" + v + "&partyNum="
						+ row.partyNum;
				//客户类型为企业客户
				var custType = row.corpCustomerTypeCd;
				url += "&cusType=" + custType;
				nui.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "认定白名单流程",
							width : 1024,
							height : 768,
							state : "max",
							onload : function(e) {
								var iframe = this.getIFrameEl();
								var text = iframe.contentWindow.document.body.innerText;
								//alert(text);
							},
							ondestroy : function(action) {
								queryInit();
							}
						});

			

		}

	  /* grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				if(e.data[i]['cusStatus']!='01'){
				debugger;
				e.data[i]['cusName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['cusName']+'</a>';
				}
			}
		});  */

//Excel批量导入
 	function importExcel(){
		nui.open({
			url:nui.context + "/csm/natural/import_white_customer.jsp?batchNumber="+batchNumber,
			title: "Excel 批量导入", 
			width: 800, 
			height: 500,
			allowResize:true,
			showMaxButton: true,
			ondestroy: function (action) {
				if(action=="ok"){
					queryInit();
				} 
			}
		});
 	}
 	
	function reset() {
		form.reset();
		queryInit();
	}

	function edit(v) {
	
		var row = grid.getSelected();
		debugger;
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		if(v=='3'){//失效
		var updateTime="<%=com.bos.pub.GitUtil.getBusiDate()%>";
		var json=nui.encode({"item":{"customerId":row.customerId,"cusStatus":"05","updateTime":updateTime}});
			$.ajax({
		            url: "com.bos.csm.natural.natural.updateWhiteCustomer.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		          		git.unmask("form1");
		            	if(text.msg){
		            		alert(text.msg);
		            	} else {
		            		alert("保存成功!");
		            		queryInit();
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		             	git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		           }
			});
		}
	}


	function add() {
		nui.open({
			url : nui.context + "/csm/natural/natural_add.jsp",
			showMaxButton : true,
			title : "添加自然人",
			width : 820,
			height : 440,
			onload : function(e) {
				var iframe = this.getIFrameEl();
				var text = iframe.contentWindow.document.body.innerText;
			},
			ondestroy : function(action) {
				queryInit();
			}
		});
	}
	//创建流程
	function create(applyId,biz){
			var json=nui.encode({"applyId":applyId,"biz":biz});
	        $.ajax({
	            url: "com.bos.bizApply.bizApply.CreateAddWhiteProcess.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg !=null){
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            		nui.get("btnCreate").setEnabled(true);
	            		return;
	            	}
	
			        //面谈面签结束
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        }); 
	}
	//ECIF同步接口
	function SynchronizationEcif() {
		nui.confirm("确定要同步吗？","同步确认",function(action){
			if(action!="ok") return;
			var rows=grid.getSelecteds();
			//var row = grid.getSelected();
			if (rows.length <= 0) {
				alert("至少选择一条记录");
				return;
			}
			if(rows.length == 1){
				var json = nui.encode({"items":rows[0]});
			}else{
				var json = nui.encode({"items":rows});
			}
			git.mask();
			$.ajax({
				url : "com.bos.csm.inteface.ecif.synchronizationEcif1.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask();
					if (text.errMsg) {
						alert("ECIF提示信息："+text.errMsg);
					} else if(text.code=='AAAAAAA'){
						alert("同步成功!");
						queryInit();
					} 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask();
					nui.alert(jqXHR.responseText);
				}
			});
   		}); 
	}
	
</script>
</body>
</html>