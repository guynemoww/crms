<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<!-- 
  - Author(s): chenchuan 
  - Date: 2016-05-11 14:04:16
  - Description:
-->
<head>
<title>评估信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	
		<fieldset>
		  	<legend>
		    	<span>我行评估</span>
		    </legend>
		    <div class="nui-toolbar" style="border-bottom:0;width:100%;text-align: left;">
				<a class="nui-button" iconCls="icon-add" onclick="addMyBankAssess()" id="addMyBankAssess">增加</a>
				<a class="nui-button" iconCls="icon-edit" onclick="editMyBankAssess(0)" id="editMyBankAssess">编辑</a>
				<a class="nui-button" iconCls="icon-zoomin" onclick="editMyBankAssess(1)">查看</a>
				<a class="nui-button" iconCls="icon-remove" onclick="removeMyBankAssess()" id="removeMyBankAssess">删除</a>
			</div>
		
		
			<div id="grid3" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.grt.grtMainManage.grtApply.getApplyTbAssessList.biz.ext" dataField="tbAssess"
				allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"allowAlternating="true"
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="SURETY_NO" headerAlign="center" allowSort="true"  >抵质押物编号</div>
					<div field="COLL_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0131">抵质押类型</div>
					<div field="SORT_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YPZL01">抵质押物类型</div>
					<div field="CURRENCY_CD" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="COST_VALUE" headerAlign="center" allowSort="true" dataType="currency">成本价值</div>
					<div field="ASSESS_VALUE" headerAlign="center" allowSort="true" dataType="currency">我行评估价值</div>
					<div field="ASSESS_DATE" headerAlign="center" allowSort="true" >评估日期</div>
					<div field="REMARK" headerAlign="center" allowSort="true" >备注</div>
				</div>
			</div>
		</fieldset>
		<fieldset>
		  	<legend>
		    	<span>外部评估</span>
		    </legend>
		    <div class="nui-toolbar" style="border-bottom:0;width:100%;text-align: left;">
				<a class="nui-button" iconCls="icon-add" onclick="addOuterAssess()" id="addOuterAssess">增加</a>
				<a class="nui-button" iconCls="icon-edit" onclick="editOuterAssess(0)" id="editOuterAssess">编辑</a>
				<a class="nui-button" iconCls="icon-zoomin" onclick="editOuterAssess(1)">查看</a>
				<a class="nui-button" iconCls="icon-remove" onclick="removeOuterAssess()" id="removeOuterAssess">删除</a>
			</div>
		
			<div id="grid2" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.grt.grtMainManage.grtApply.getApplyTbAssessList.biz.ext"
				dataField="tbAssess"allowAlternating="true"
				allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="SURETY_NO" headerAlign="center" allowSort="true"  >抵质押物编号</div>
					<div field="COLL_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0131">抵质押类型</div>
					<div field="SORT_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YPZL01">抵质押物类型</div>
					<div field="ASSESS_ORG" headerAlign="center" allowSort="true" >评估机构</div>
					<div field="CURRENCY_CD" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="COST_VALUE" headerAlign="center" allowSort="true" dataType="currency">成本价值</div>
					<div field="ASSESS_VALUE" headerAlign="center" allowSort="true"  dataType="currency">评估价值</div>
					<div field="ASSESS_DATE" headerAlign="center" allowSort="true" >评估日期</div>
					<div field="REMARK" headerAlign="center" allowSort="true" >备注</div>
				</div>
			</div>
		</fieldset>
	<script type="text/javascript">
		nui.parse();
		//押品主键ID
		var suretyId ="<%=request.getParameter("suretyId")%>";
		//押品分类
		var sortType="<%=request.getParameter("sortType")%>";
		 //外部评估信息
		var grid2 = nui.get("grid2");
		//我行评估信息
		var grid3 = nui.get("grid3");
		
		search2();//初始化外部评估信息
		search3();//初始化我行评估信息
		
		var v="<%=request.getParameter("view") %>";
		
		if ("1" == v){
			nui.get("addOuterAssess").hide();
			nui.get("editOuterAssess").hide();
			nui.get("removeOuterAssess").hide();
			nui.get("addMyBankAssess").hide();
			nui.get("editMyBankAssess").hide();
			nui.get("removeMyBankAssess").hide();
		}
		
	    function search2() {
			var json=({"item":{"suretyId":suretyId,"_entity":"com.bos.dataset.grt.TbGrtOuterAssess","sortType":sortType}});
	        grid2.load(json);
	    }
	    
	    function addOuterAssess(){
	    	 nui.open({
	            url: nui.context+"/grt/manage/assess/outerAssess/outerAssessAdd.jsp?suretyId="+suretyId+"&sortType="+sortType,
	            title: "新增外部评估信息", 
	            width: 800, 
	        	height: 300,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                search2();
	            }
	        });  
	    }
	    
	    function removeOuterAssess(){
	    	var row = grid2.getSelected();
	        if (row) {
	        	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"item":{"suretyKeyId":row.SURETY_KEY_ID,"_entity":"com.bos.dataset.grt.TbGrtOuterAssess","suretyId":suretyId}});
	                $.ajax({
	                    url: "com.bos.grt.grtMainManage.grtApply.delApplyTbAssess.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	nui.alert(text.msg);
	                    	search2();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
	            }); 
		      }else{
		        alert("请选中一条记录");
		      }
	    }
	    
	    function editOuterAssess(v){
	    	var row = grid2.getSelected();
	        var title1;
	        if(v == "0"){
				title1 = "编辑";        	
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {
	            nui.open({
	                url: nui.context+"/grt/manage/assess/outerAssess/outerAssessEdit.jsp?suretyKeyId="+row.SURETY_KEY_ID+"&view="+v+"&suretyId="+suretyId,
	                title: title1, 
	                width: 800,
	        		height: 300,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                },
	                ondestroy: function (action) {
						search2();
	                }
	            });
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	    
		if ("1" == "<%=request.getParameter("view") %>"){
			nui.get("addMyBankAssess").hide();
			nui.get("editMyBankAssess").hide(); 
			nui.get("removeMyBankAssess").hide();
		}
	    function search3() {
			var json=({"item":{"suretyId":suretyId,"_entity":"com.bos.dataset.grt.TbGrtMybankAssess","sortType":sortType}});
	        grid3.load(json);
	    }
	    
	      function addMyBankAssess(){
	    	nui.open({
	            url: nui.context+"/grt/manage/assess/myBankAssess/myBankAssessAdd.jsp?suretyId="+suretyId+"&sortType="+sortType,
	            title: "新增我行评估信息", 
	            width: 800, 
	        	height: 300,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                search3();
	            }
	        }); 
	    }
	    
	    function removeMyBankAssess(){
	    	var row = grid3.getSelected();
	        if (row) {
	        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"suretyKeyId":row.SURETY_KEY_ID,"_entity":"com.bos.dataset.grt.TbGrtMybankAssess","suretyId":suretyId}});
                $.ajax({
                    url: "com.bos.grt.grtMainManage.grtApply.delApplyTbAssess.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
                    	search3();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	    
	    function editMyBankAssess(v){
	    	var row = grid3.getSelected();
	        var title1;
	        if(v == "0"){
				title1 = "编辑";        	
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {
	            nui.open({
	                url: nui.context+"/grt/manage/assess/myBankAssess/myBankAssessEdit.jsp?suretyKeyId="+row.SURETY_KEY_ID+"&view="+v+"&suretyId="+suretyId,
	                title: title1, 
	                width: 800,
	        		height: 300,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                    //iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
						search3();
	                }
	            });
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	</script>
</body>
</html>
