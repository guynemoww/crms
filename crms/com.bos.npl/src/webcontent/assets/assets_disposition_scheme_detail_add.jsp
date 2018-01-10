<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-23 18:15:23
  - Description:
-->
<head>
<title>资产处置明细添加</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
		 url="com.bos.npl.assets.AssetsDispositionSchemeDetail.getAssetsPerforming.biz.ext" dataField="tbNplAssetsPerformings" allowResize="false" 
		 showReloadButton="false" multiSelect="true" pageSize="5" sortMode="client" 
		 showPager="true" showFooter="false" virtualScroll="true">	 
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="aa" headerAlign="center" type="indexcolumn"  allowSort="false">序号</div>
			<div field="partyNum" headerAlign="center" allowSort="false">客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="false">客户名称</div>
			<div field="partyType" headerAlign="center" allowSort="false">客户类型</div>
			<div field="creditAmt" headerAlign="center" allowSort="false">授信额度</div>
			<div field="balanceAmt" headerAlign="center" allowSort="false">授信余额</div>
			<div field="riskClassify" headerAlign="center" allowSort="false">最新分类</div>
			<div field="holdDate" headerAlign="center" allowSort="false">认定时间</div>
			
			<div field="overdueInterest" headerAlign="center" allowSort="false">表内外应收未收利息</div>
			<div field="overduedays" headerAlign="center" allowSort="false">逾期或垫款天数</div>
			<div field="intoDate" headerAlign="center" allowSort="false">转入保全时间</div>
			<div field="clearDate" headerAlign="center" allowSort="false">结清日期</div>
			<div field="responsibleUserNum" headerAlign="center" allowSort="false">保全责任人</div>
			<div field="responsibleOrgNum" headerAlign="center" allowSort="false">保全责任机构</div>
			
			<div field="orgNum" headerAlign="center" allowSort="false" dictTypeId="org">经办机构</div>
			<div field="userNum" headerAlign="center" allowSort="false" dictTypeId="user">客户经理</div>
			<div field="assetsStatus" headerAlign="center" allowSort="false">状态</div>
		</div>
	</div>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="schemeId" name="schemeId" class="nui-hidden nui-form-input" value="<%=request.getParameter("schemeId")%>"/>
		
		<div type="comboboxcolumn" autoShowPopup="true" field="dispostionType" name="dispostionType" headerAlign="center" >处置类型：
			<input property="editor"  id="dispostionType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_BQ0001" allowInput="false" />
			<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="create">确定</a>
		</div>

	</div>
</center>
<script type="text/javascript">
	var con = [{ id: 1, text: '现金清收' }, { id: 2, text: '资产重组'}, { id: 3, text: '法律诉讼'}, { id: 4, text: '代理处置'}, { id: 5, text: '组包出售'}, { id: 6, text: '呆账核销'},{ id: 7, text: '资产抵债'}];
	nui.parse();
	
	//查询
	query();
	function query(){
		var grid = nui.get("grid");
      	grid.load({"schemeId":"<%=request.getParameter("schemeId")%>"});
	}
	
	function create(){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();
		var schemeId = nui.get("schemeId").getValue();
		var dispostionType = nui.get("dispostionType").getValue();
		if(""==dispostionType||null==dispostionType){
			alert("请选择处置类型！");
			return;
		}
        if (rows.length > 0){
        	var json = nui.encode({"tbNplAssetsPerformings":rows,"dispostionType":dispostionType,"schemeId":schemeId});
	        $.ajax({
	            url: "com.bos.npl.assets.AssetsDispositionSchemeDetail.createSchemeDetail.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	                alert("引入成功！");
	                CloseWindow("ok");
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
        }else{
        	alert("请选择资产信息");
        }
	}
	
</script>
</body>
</html>