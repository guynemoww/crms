<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<!--
		- Author(s): caohaijie - Date: 2014-04-22 10:38:33 - Description:选中菜单的子菜单列表，tab页中使用
	-->
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-top:5px;" >
				<div class="nui-dynpanel" columns="6">
					<input type="hidden" name="item._entity" value="com.bos.risk.sort.SortHistoryList" class="nui-hidden" />
					<input name="item.partyId" id="item.partyId"  class="nui-hidden" />
					<label>合同编号</label>
					<input id="item.contractNum" name="item.contractNum" required="false" class="nui-textbox nui-form-input" />
				</div>
				<div class="nui-toolbar" style="text-align:right;border:none" >
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
	  				<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<!-- 分类历史展示 -->
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
					<div type="checkcolumn" allowSort="true" headerAlign="center" width="5%">选择</div>
					<div field="partyName" headerAlign="center" allowSort="true">客户名称</div>
					<div field="contractNum" headerAlign="center" allowSort="true">合同编号</div>
					<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div>
					<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种 </div>
					<div field="contractAmt" headerAlign="center" allowSort="true" dataType="currency">合同金额</div>
					<div field="conBalance" headerAlign="center" allowSort="true" dataType="currency">合同已用金额</div>
					<div field="beginDate" headerAlign="center" allowSort="true">合同起期</div>
					<div field="endDate" headerAlign="center" allowSort="true">合同止期</div>
					<div field="regDueDate" headerAlign="center" allowSort="true">调整前分类结果</div>
					<div field="regDueDate" headerAlign="center" allowSort="true">调整后分类结果</div>
					<div field="regDueDate" headerAlign="center" allowSort="true">分类调整意见</div>
					<div field="regDueDate" headerAlign="center" allowSort="true">经办人</div>
					<div field="regDueDate" headerAlign="center" allowSort="true">经办日期</div>
				</div>
			</div>
			<div style="margin-top:10px;">
				<input class="nui-button" style="margin-right:5px;" text="查看分类记录" iconCls="icon-upload" onclick="selectHistoryInfo()"/>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			git.mask();
			var grid = nui.get("datagrid");
			var form = new nui.Form("#form1"); 
			var partyId = "<%=request.getParameter("partyId") %>";
			function query() {
				if (partyId) {
					nui.get("item.partyId").setValue(partyId);
				}
				var data = form.getData(); //获取表单多个控件的数据
		        grid.load(data);
		        git.setDistrictsByIds('district1,district2,district3')
		        git.unmask();
	    	}
	    	query();
	    	
	    	function selectHistoryInfo(){
				var row = grid.getSelected();
				if(row){
			  		nui.open({
			            url: nui.context + "/risk/sort/history/sort_history_select.jsp?partyId="+row.partyId,
			            title: "调整记录表", 
			            width: 800, 
		        		height: 400,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			                if(action=="ok"){
			                    search();
			                }
			            }
			        });
		        }else{
		        	alert("请选中一条记录");
		        }
	  		}
		</script>
	</body>
</html>
