 <%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
 
<%@include file="/common/nui/common.jsp"%>
 
<head>
         
</head>
<body>   
    <div id="datagrid1" class="nui-datagrid" style="width:auto;height:400px;" 
        url="com.bos.csm.corporation.corporation.getCorpList.biz.ext" dataField="items"  idField="id"
        onselectionchanged="">
        <div property="columns">
        	<div type="checkcolumn">选择</div>
	        <div type="indexcolumn">序号</div>
            <div field="partyId" allowSort="true" width=""  headerAlign="center">抵质押人名称</div>
	        <div field="partyName" allowSort="true" width=""   headerAlign="center">抵质押物名称</div>
	        <div field="CCB_ASSESSED_VALUE" allowSort="true" width="" headerAlign="center" dataType="currency">我行确认价值</div>
	        <div field="CCB_ASSESSED_VALUE" allowSort="true" width="" headerAlign="center" dataType="currency">已设定担保额</div>       
	        <div field="rate" allowSort="true" width="" headerAlign="center">抵质押率</div>              
        </div>
    </div>      
   
	
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
	    
 
    <script type="text/javascript">
        nui.parse();
        var grid = nui.get("datagrid1");
        grid.load();
        grid.sortBy("createtime", "desc");
        
        ///////////////////////////////////////////////////////       
        
       
        
       function save() {
		var row=grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		var json=nui.encode({"item":{"guarantyId":row.partyId,"relationId":"<%=request.getParameter("relationId") %>","reType":"02"}});
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.biz.Collateral.addOrUpdateCollateral.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		nui.alert("替换成功");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
    </script>
 
</body>
</html>
