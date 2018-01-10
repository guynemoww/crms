<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<!--
		- Author(s): tianzhuo - Date: 2014-04-22 10:38:33 - Description:选中菜单的子菜单列表，tab页中使用
	-->
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<div id="form1" style="width:100%;height:auto;overflow:hidden;">
			<input type="hidden" name="item._entity" value="com.bos.sort.classify.MortgageInfo" class="nui-hidden" />
			<input name="item.partyId" id="item.partyId"  class="nui-hidden" />
		</div>
		<center>
			<!-- 押品信息展示 -->
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:99.5%;height:auto;"
				sortMode="client" 
				url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" 
				dataField="items" 
				allowAlternating="true" 
				multiSelect="false" 
				showEmptyText="true" 
				showPager="true" 
				emptyText="没有查到数据" 
				showReloadButton="false" 
				showColumnsMenu="true" 
				onrowdblclick="" 
				allowCellEdit="true" 
				allowCellSelect="true"
				onselectionchanged="onSelectionChanged" 
				sizeList="[10,20,50,100]" 
				pageSize="10">
				<div property="columns">
					<div field="cardRegDate" headerAlign="center" allowSort="true">调整日期</div>
					<div field="regDueDate" headerAlign="center" allowSort="true">调整结果</div>
				</div>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			//git.mask();
			var grid = nui.get("datagrid");
	  		var form = new nui.Form("#form1");
	  		function queryInit(){
		       var o = form.getData();//逻辑流必须返回total
		       grid.load(o);
			   git.unmask();
			}
			queryInit();
			
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
			            nui.get("input.certType").setData(text.levels);
			            custFlag = true;
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            git.unmask();
			            nui.alert(jqXHR.responseText);
			        }
			     });
			}
		    init();	
		</script>
	</body>
</html>
