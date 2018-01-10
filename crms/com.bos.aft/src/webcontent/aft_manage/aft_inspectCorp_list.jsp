<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-26 09:13:06
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>待抽查客户列表</title>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
	<div title="待抽查客户列表" >
	<center>
	<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>客户类型：</label>
		<input name="map/partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_KHCD1001" emptyText="请选择"/>
		<label>预警级别：</label>
		<input name="map/earlyWarningLevelCd" id="earlyWarningLevelCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_YJCD0004" emptyText="请选择"/>
		<label></label><!-- 放在查询条件的最后  注意：隐藏的input占位-->
		<input name="map/earlyWarningLevelCdDictTypeId" class="nui-hidden" value="" id="earlyWarningLevelCdDictTypeId"/>
	</div>
 	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-reset" onclick="reset()">重置</a>
		<!-- <a class="nui-button" style="margin-right:20px;height:21px;" onclick="exportEmp" type="submit" />导出Excel表格</a> -->
	</div>
 	</form>
 	<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;;margin-top:7px">
		<!-- 
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">新增</a>
		<a id = "query" class="nui-button" iconCls="icon-node" onclick="query()">查看</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		<a id="smallCorp" class="nui-button" iconCls="icon-upload" onclick="smallCorpIdentify(0)">发起小企业认定</a>
		 -->
	</div>
</div>
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"
		    multiSelect="true" allowAlternating="true" pageSize="10"
		    sizeList="[10,20,50,100]" url="com.bos.aft.aft_manage.queryInspectCorp.biz.ext" dataField="inspectCorps" showReloadButton="false">
		<div property="columns">
			<div type="checkcolumn"></div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<!-- 
			<div field="orgNum" headerAlign="center" allowSort="true" >组织机构代码</div>
			 -->
			<div field="partyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0219">客户类型</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办客户经理</div>
			<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">所属机构</div>
			<div field="earlyWarningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004" >预警级别</div>
			<!-- <div field="ictCorpStock" headerAlign="center" allowSort="true" >存贷比</div> -->
			<div field="lastInspectDt" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">最近抽查日期</div>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none;width:99.5%">
		<a class="nui-button" style="margin-right:5px;height:21px;" onclick="save">移入</a>
		<a class="nui-button" style="margin-right:5px;height:21px" onclick="del">移出</a>
		<a class="nui-button" style="margin-right:23px;height:21px" onclick="create">创建抽样检查任务</a>
	</div>
	<iframe name="x" id="x" style="display:none;"></iframe>
	</center>
	</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1"); 
		var grid = nui.get("datagrid1");
		
		//获取业务字典，用于导出excel时的查询语句，防止当前页面业务字典发生变化
		nui.get('earlyWarningLevelCdDictTypeId').setValue(nui.get('earlyWarningLevelCd').dictTypeId);

		//查询待检查客户列表
		function query(){
		   //逻辑流必须返回total
		   git.mask();
	       var data = form.getData(); //获取表单多个控件的数据
		   grid.load(data);
	       git.unmask();
		}
		//加载调用查询方法                                                                                                           
		query();
		
		//移入待检查客户
		function save(){
			nui.open({
                url: nui.context+"/aft/aft_manage/aft_addInspectCorp_list.jsp",
                title: "移入抽样检查客户", 
                width: 800,  
                height: 500,
                onload: function () {
                	//grid.reload();
                },
                ondestroy: function (action) {
                    grid.reload();
                }
            });
		}
		
		//移出待检查客户
		function del(){
			var rows = grid.getSelecteds();
			if(rows==undefined||rows.length==0){
				nui.alert("请至少选择一条要删除的数据");
				return;
			}
			git.mask();
			var json=nui.encode({"item":rows});
			var url = "com.bos.aft.aft_manage.delInspectCorp.biz.ext";
			//移出选中客户
			nui.confirm("确定删除记录？", "确定？",function (action) {            
			    if (action == "ok") {
			    	$.ajax({
			    		url: url,
			     		type: 'POST',
			     		data: json,
			     		cache: false,
			     		contentType:'text/json',
			     		success: function (text) {
			     			if(text.msg==1){
			     				nui.alert("移除客户成功！");
							}else if(text.msg==0){
								nui.alert("移除客户失败！");
							}
							grid.reload();
							git.unmask();
						  },
					    error: function (jqXHR, textStatus, errorThrown) {
						         nui.alert(jqXHR.responseText);
						         git.unmask();
						     }
					});
			    }
			});
		}
		
		//创建抽样检查任务
		function create(){
			var rows = grid.getSelecteds();
			if(rows==undefined||rows.length==0){
				nui.alert("请至少选择一条数据！");
				return;
			}
			git.mask();
			var json=nui.encode({"rowsData":rows});
			 nui.ajax({
                url: "com.bos.aft.aft_manage.createSpotInspectWork.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	var exsitRows=text.existPartyNames;
                	var partyNums="";
                	for(var i=0;i<exsitRows.length;i++){
                		partyNums+=exsitRows[i].partyNum;
                		if(i<exsitRows.length-1){
                			partyNums+=",";
                		}
                	}
                	if(""==partyNums){
                		nui.alert(text.msg)
                	}else{
                		/* nui.alert(text.msg+"以下编号的客户流程未走完，未重复创建："+partyNums); */
                		nui.alert("以下编号的客户已经创建抽样检查任务，未重复创建："+partyNums);
                	}
                	grid.reload();
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    grid.reload();
                    git.unmask();
                }
            });		
		}
		/*var gridData = [
			{ictCorpId:'1',partyNum:"KH0001",partyName:"测试客户1",
			orgnNum:"94857348-9",corpCustomerTypeCd:"1",ictCorpDate:"2014/1/1",industrialTypeCd:"2",earlyWarningLevelCd:"2",ictCorpStock:"2",orgnName:"上海总行"},
			{ictCorpId:'2',partyNum:"KH0002",partyName:"测试客户2",
			orgnNum:"94857348-8",corpCustomerTypeCd:"1",ictCorpDate:"2014/1/2",industrialTypeCd:"2",earlyWarningLevelCd:"2",ictCorpStock:"2",orgnName:"上海总行"},
			{ictCorpId:'3',partyNum:"KH0003",partyName:"测试客户3",
			orgnNum:"94857348-7",corpCustomerTypeCd:"1",ictCorpDate:"2014/1/3",industrialTypeCd:"2",earlyWarningLevelCd:"2",ictCorpStock:"2",orgnName:"上海总行"},
			{ictCorpId:'4',partyNum:"KH0004",partyName:"测试客户4",
			orgnNum:"94857348-6",corpCustomerTypeCd:"1",ictCorpDate:"2014/1/4",industrialTypeCd:"2",earlyWarningLevelCd:"2",ictCorpStock:"2",orgnName:"上海总行"}
		];
		grid.loadData(gridData);
		*/
		function pubAjax(url,json){
	
		}
		//重置
		function reset(){
			form.reset();
		}
		//导出
	    function exportEmp() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.partyName != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");
				forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=11";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
			
		    
	    }
	</script>
</body>
</html>