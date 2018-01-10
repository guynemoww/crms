<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-26 15:57:52
  - Description:贷后重点客户的管理
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>重点监控客户管理</title>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
	<div title="重点监控客户管理" >
	<center>
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;" 
		    multiSelect="true" allowAlternating="true"  showReloadButton="false" pageSize="10"
		    sizeList="[10,20,50,100]" url="com.bos.aft.aft_manage.queryMonitoringCorp.biz.ext" dataField="monitoringCorps">
		<div property="columns">
			<div type="checkcolumn" ></div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="partyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0219">客户类型</div>
			<div field="monitoringDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">纳入时间</div>
			<div field="monitoringLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0007">监控级别</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
			<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">所属机构</div>
			<!-- input class="nui-text nui-form-input" required="false" name="item.updateUserId" dictTypeId="user" enabled="true" -->
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
		var grid = nui.get("datagrid1");  
		
		function query(){//查询重点客户
		    grid.load();//逻辑流必须返回total
		    git.unmask();
		}                                                                                    
		query();//加载调用查询方法  
		
		function save(){
			  nui.open({
                url: nui.context+"/aft/aft_manage/aft_singleattentionCorp_list.jsp?type='monitoring'",
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
				url: "com.bos.aft.aft_manage.delMonitoringCorp.biz.ext",
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
		
	</script>
</body>
</html>