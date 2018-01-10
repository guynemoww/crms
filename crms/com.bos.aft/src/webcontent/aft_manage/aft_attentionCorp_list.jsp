<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-26 15:57:52
  - Description:贷后重点客户的管理
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>贷后重点客户管理</title>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
	<div title="贷后重点客户管理" >
	<center>   
 <form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>纳入类型：</label>
		<input name="map/attentionTypeCd" id="attentionTypeCd" required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_DHCD0003" emptyText="请选择"/>
		<label>纳入理由：</label>
		<input name="map/attentionReasonCd" id="attentionReasonCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0002" emptyText="请选择"/>
		<label>客户名称：</label>
		<input name="map/partyName"  class="nui-textbox nui-form-input" value=""/>
		<label>编号：</label>
		<input name="map/partyNum"  class="nui-textbox nui-form-input"  value=""/>
		<label></label><!-- 放在查询条件的最后  注意：隐藏的input占位-->
		<input name="map/attentionTypeCdDictTypeId" class="nui-hidden" value="" id="attentionTypeCdDictTypeId"/>
		<input name="map/attentionReasonCdDictTypeId" class="nui-hidden" value="" id="attentionReasonCdDictTypeId"/>
	</div>
 <div class="nui-toolbar" style="text-align:right;border:none" >
    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
	<a class="nui-button" style="margin-right:5px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit" />导出Excel表格</a>
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
	multiSelect="true" allowAlternating="true" showReloadButton="false"
	sizeList="[10,20,50,100]" pageSize="10"
	url="com.bos.aft.aft_manage.queryAttentionCorp.biz.ext"
	dataField="attentionCorps">
<div property="columns">
<div type="checkcolumn"></div>
<div field="partyNum" headerAlign="center" allowSort="true">客户编号</div>
<div field="partyName" headerAlign="center" allowSort="true">客户名称</div>
<div field="partyTypeCd" headerAlign="center" allowSort="true"
	dictTypeId="XD_KHCD0219">客户类型</div>
<div field="attentionTypeCd" headerAlign="center" allowSort="true"
	dictTypeId="XD_DHCD0003">纳入类型</div>
<div field="attentionReasonCd" headerAlign="center" allowSort="true"
	dictTypeId="XD_DHCD0002">纳入理由</div>
<div field="attentionDt" headerAlign="center" allowSort="true"
	dateFormat="yyyy-MM-dd">纳入时间</div>
<div field="userNum" headerAlign="center" allowSort="true"
	dictTypeId="user">经办人</div>
<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">所属机构</div>
</div>
</div>
<div class="nui-toolbar" style="text-align:right;border:none;width:99.5%">
	<a class="nui-button" style="margin-right:5px;height:21px;" onclick="save" id="button1">移入</a> 
	<a class="nui-button" style="margin-right:23px;height:21px" onclick="del" id="button2">移出</a>
</div>
	</center>
	</div>
	</div>

<script type="text/javascript">
		nui.parse();
		
		git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
		var form = new nui.Form("#form1"); 		
		var grid = nui.get("datagrid1");  
		
		//获取业务字典，用于导出excel时的查询语句，防止当前页面业务字典发生变化
		nui.get('attentionTypeCdDictTypeId').setValue(nui.get('attentionTypeCd').dictTypeId);
		nui.get('attentionReasonCdDictTypeId').setValue(nui.get('attentionReasonCd').dictTypeId);
		
		function query(){//查询重点客户
			var data = form.getData(); //获取表单多个控件的数据
		    grid.load(data);//逻辑流必须返回total
		    git.unmask();
		}                                                                                    
		query();//加载调用查询方法  
		
		//重置
		function reset(){
			form.reset();
		}
		
		
		function save(){
			  nui.open({
                url: nui.context+"/aft/aft_manage/aft_singleattentionCorp_list.jsp?type='attention'",
                title: "移入贷后重点客户", 
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
	 function del(){
		var rows=grid.getSelecteds();
		if(rows==undefined||rows.length==0){
			nui.alert("请至少选择一条要删除的数据");
			return;
		}
	
		var json=nui.encode({"item":rows});
			 $.ajax({
				url: "com.bos.aft.aft_manage.delAttentionCorp.biz.ext",
            	type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
           		success: function (data) {
           			nui.alert(data.msg);
            		grid.reload();
            	
           		},
           		error: function (jqXHR, textStatus, errorThrown) {
                	nui.alert(jqXHR.responseText);
            		}
			
			});
	 }
	 
	 
	 //导出
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.partyName != null) return true;
		});
		
		if(rows != null && rows.length > 0) {//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=10";
			forms.submit();
		} else {
			alert('没有要导出的记录');
		}
		
	    
    }
		
	</script>
</body>
</html>
