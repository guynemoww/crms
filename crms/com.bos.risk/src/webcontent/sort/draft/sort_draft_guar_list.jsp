<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<!--
		- Author(s): caohaijie - Date: 2015-07-22 分类保证人列表
	-->
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input type="hidden" name="DrafGuarList._entity" value="com.bos.risk.sort.DrafGuarList" class="nui-hidden" />
				<input type="hidden" name="DrafGuarList.acApplyId" value="<%=request.getParameter("acApplyId")%>"  class="nui-hidden" />
				<input type="hidden" name="item._entity" value="com.bos.risk.sort.DrafGuarList" class="nui-hidden" />
				<input name="item.acApplyId" id="item.acApplyId" class="nui-hidden" />
			</div>
			<div class="nui-toolbar" style="border-bottom:0;">
				<a id="query" class="nui-button" iconCls="icon-node" onclick="edit(1)">查看详情</a>
			</div>
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:99.5%;height:100%;"
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
				sizeList="[10,20,50,100]" 
				pageSize="10">
				<div property="columns">
					<div type="checkcolumn" allowSort="true" headerAlign="center" width="5%">选择</div>
				 	<div field="partyTypeCd" allowSort="true" headerAlign="center" autoEscape="false">客户属性</div>
				 	<div field="partyName" allowSort="true" headerAlign="center" autoEscape="false">客户名称</div>
	       			<div field="certType" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>       
	        		<div field="certNum" allowSort="true" headerAlign="center" >证件号码</div>
				</div>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			var grid = nui.get("datagrid");
	  		var form = new nui.Form("#form1");
	  		var partyId = "<%=request.getParameter("partyId") %>";
	  		var acApplyId = "<%=request.getParameter("acApplyId") %>";
			
			function query() {
				nui.get("item.acApplyId").setValue(acApplyId);
				var data = form.getData(); //获取表单多个控件的数据
		        grid.load(data);
		        git.unmask();
	    	}
	    	query();
	    	
	    	grid.on("preload",function(e){
	       		if (!e.data || e.data.length < 1)
	       			return;
	       		for (var i=0; i<e.data.length; i++){
	       			e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
	       		}
	    });
	  		 function edit(v) {
		        var row = grid.getSelected();
		        if (row) {
		            nui.open({
		                url: nui.context+"/risk/sort/draft/sort_draft_guarFinance_select.jsp?partyId="+row.partyId,
		                //url: "risk/sort/draft/sort_draft_guar_detail_info.jsp",
		                showMaxButton: true,
		                title: "财务指标", 
		                width: 1024,
			            height: 540,
		                onload: function () {
		                    var iframe = this.getIFrameEl();
		                    var data = row;
		                    //iframe.contentWindow.SetData(data);
		                },
		                ondestroy: function (action) {
		                   if(v=='0'){
		                        search();
		               	 	}
		                }
		            });
		        } else {
		            alert("请选中一条记录");
		        }
		        
		    }
		</script>
	</body>
</html>