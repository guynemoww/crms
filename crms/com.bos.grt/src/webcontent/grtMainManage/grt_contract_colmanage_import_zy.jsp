<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-06-27 15:01:53
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>引入质押信息</title>
</head>
<body>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client"
		url="com.bos.grt.grtMainManage.grtContract.getGuarantyBasicInfoList.biz.ext"
			dataField="guarantyInfos" allowCellEdit="true" allowCellSelect="true"
		    allowAlternating="true" multiSelect="true" pageSize="15" showEmptyText="true"
		    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true">
		    <div property="columns">
		    <div type="checkcolumn">选择</div> 
		    <div field="suretyNum" headerAlign="center" allowSort="true">抵押品编号</div>
		    <div field="partyName" headerAlign="center" allowSort="true">抵质押人名称</div>
		    <div field="currencyCd" allowSort="true" headerAlign="center" dictTypeId="CD000001">币种</div>
			<div field="assessCost" name="assessCost" headerAlign="center" allowSort="true" >评估价值(元)</div>
			<div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">押品类别</div>
			<div field="mortgageRate" name="mortgageRate" headerAlign="center" allowSort="true" >抵质押率(%)</div>
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-ok" onclick="sureok()" id="sureok">确定</a>
	</div>
	
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var subcontractId = "<%=request.getParameter("subcontractId") %>";
		function search(){
	      	var json = nui.decode({"guarantyInfo":{"partyId":"<%=request.getParameter("partyId") %>","subcontractId":subcontractId,
	      	"subcontractNum":"<%=request.getParameter("subcontractNum") %>","subcontractId":"<%=request.getParameter("subcontractId") %>",
	      	"collType":"020201"}});
	      	grid.load(json);
	    }
	    search();
	    /**
		 * 获得选中的数据
		 */
		function getThisData(){
			var row = grid.getSelecteds();
			var data = {guarantyInfos:row};
			return data;
		}
	    function sureok(){
			var rows = grid.getSelecteds();
		    if(rows.length>0){
				var json = nui.encode({"tbConSubGrtRelations":rows})+nui.encode({"subcontractId":"<%=request.getParameter("subcontractId") %>"});
				$.ajax({
	       			url: "com.bos.grt.grtMainManage.grtContract.addContractImportTbGrtGuarantyBasicList.biz.ext",
	       			type: 'POST',
	       			data: json,
	       			cache: false,
	       			contentType:'text/json',
	       			success: function (text) {
	       				nui.alert(text.msg);
	       				CloseWindow("ok");
	       			},
	       			error: function (jqXHR, textStatus, errorThrown) {
						nui.alert(jqXHR.responseText);
	       			}
				});
		  }else{
		  	  nui.alert("请选择一条记录！");
		  }
		}
		
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['suretyNum']='<a href="#" onclick="clickGua(\''
       				+ e.data[i].suretyNum+","+e.data[i].suretyNum
       				+ '\');return false;" value="'
       				+ e.data[i].suretyNum
       				+ '">'+e.data[i]['suretyNum']+'</a>';
			}
		});
		
		function clickGua(e){
			var ps = e.split(",");
			var row = grid.getSelected();
			partyId = ps[0];
			partyNum = ps[1];
			var infourl = nui.context + "/grt/grtMainManage/grt_outer_colmanage_list_edit.jsp?sortType="+row.sortType+"&suretyId="+
				row.suretyId+"&getinfoview=1&view=1";
			nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 800,
	            height: 600,
	            allowResize:false,
    	    	showMaxButton: false,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            },
	            ondestroy: function (action) {
	                query();
				}
			});
		}
		
		/**
		 * 查看详情
		 */
		function getinfo(){
			var v = 1;
			var rows = grid.getSelecteds();
			var row = rows[0];
			if(row.length != 0){
				if(row.sortType=="01010101" || row.sortType=="01010102"){//01010101 活期保证金 01010102定期保证金
        			nui.open({
            			url: nui.context+"/grt/grtMainMargin/outerMargin_edit.jsp?sortType="+row.sortType+"&suretyId="+
            		 		row.suretyId+"&getinfoview=1&view=1",
            			title: "查看押品", 
            			width: 800, 
        				height: 600,
        				allowResize:false,
    	    			showMaxButton: false,
	            		ondestroy: function (action) {
            	  		}
        			});
        		}else{
        			nui.open({
	            		url: nui.context+"/grt/grtMainManage/grt_outer_colmanage_list_edit.jsp?sortType="+row.sortType+"&suretyId="+
	            		 	row.suretyId+"&getinfoview=1&view=1",
	            		title: "查看押品", 
	            		width: 800, 
	        			height: 500,
	        			allowResize:false,
	    	    		showMaxButton: false,
		            	ondestroy: function (action) {
		            		git.unmask();
	            		}
        			});
        		}
			}else{
				alert("请选择一条记录！");
				return;
			}
		}
		
		
		/**
		 * 关闭
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}

	</script>
</body>
</html>