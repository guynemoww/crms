<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>催收台账查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>

	<div class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="image" class="nui-button" iconCls="icon-zoomin" onclick="image()">影像资料</a>
	</div>

		<div id="datagrid" class="nui-datagrid"
			style="width: 99.5%; height: auto"allowAlternating="true"
			url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
			allowResize="true" showReloadButton="false"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
			sortMode="client">
			<div property="columns">
					<div type="checkcolumn"></div>
				<div field="collectionDate" allowSort="true" headerAlign="center" autoEscape="false">催收时间</div>
				<div field="collection_type" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD3001">催收方式</div>
				<div field="collectionObject" allowSort="true" width="20%"headerAlign="center">催收对象</div>
				<div field="collectionPhone" allowSort="true" headerAlign="center" dictTypeId="">催收电话</div>
				<div field="collectionPlace" allowSort="true" headerAlign="center" dictTypeId="">催收地点</div>
				<div field="collectionDesc" allowSort="true" headerAlign="center" dictTypeId="">催收记录</div>
				<div field="analyseOpinion" allowSort="true" headerAlign="center" dictTypeId="">分析意见</div>
				<div field="createUserNum" allowSort="true" headerAlign="center" dictTypeId="user">经办人</div>
			</div>
		</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var grid = nui.get("datagrid");
		var summaryId = '<%=request.getParameter("summaryId")%>';
		function queryInit() {
			grid.load({"item":{"summaryId":summaryId},"sqlName":"com.bos.clt.clt.registerListByPo"})
			git.unmask();
		}
		queryInit();

 	function image(){
   			var row = grid.getSelected();
   			var url;
   			if(row){
   				if(row.createUserNum=="<%=((UserObject) session.getAttribute("userObject")).getUserId()%>"){
					url="/pub/imagePlatform/item_tree.jsp?businessNumber="+row.cltId+"&partyTypeCd=01&flowModuleType=112";
				}else{
					url="/pub/imagePlatform/item_tree.jsp?businessNumber="+row.cltId+"&partyTypeCd=01&flowModuleType=112&view=1";
				}
				nui.open({
		            url:  nui.context +url,
		            title: "影像资料",
		            width: 1200,
        			height: 800,
		            onload: function () {
		            },
	            	ondestroy: function (action) {
	            		grid.reload();
	            	}
	        	});
   			}else{
   				return alert("请选择一条催收信息");
   			}
   	
   	}
		
	</script>
</body>
</html>