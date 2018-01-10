<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2014-02-19
  - Description:人员下的岗位
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<title>岗位人员</title>
<body>
 <div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove">删除</a>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" 
    url="com.bos.utp.rights.empposition.queryEmpposions.biz.ext" dataField="items"
    pageSize="1000" multiSelect="false" showPager="false">
    <div property="columns">
        <div type="checkcolumn"></div>
       <div field="omOrganization.orgname" headerAlign="center" >机构名称</div>
        <div field="omPosition.posiname" headerAlign="center" >岗位名称</div>
     </div>
</div>

<script type="text/javascript">
	nui.parse();
	
	var grid = nui.get("datagrid1");
	(function(){
		if(window.parent && window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
			//alert(nui.encode(parentNode));
    		grid.load({item:{omEmployee:{empid:window['parentNode'].empid}}});
		} else {
			var tmp = {
				empid:'<%=request.getParameter("empid") %>',
				orgid:'<%=request.getParameter("orgId") %>'
			};
			window['parentNode']=tmp;
		}
	})();
	
    grid.load({item:{omEmployee:{empid:window['parentNode'].empid}}});
	
	var bootPath = "<%=request.getContextPath() %>";
	
	function add(){
		nui.open({
            url: bootPath + "/utp/org/employee/employee_posi_add.jsp?empid="+window['parentNode'].empid,
            title: "添加岗位人员", width: 350, height: 250,
            onload:function(){
            },
            ondestroy: function (action) {
               if(action=="ok"){
	                grid.reload();
               }
            }
        });
	}
	
	function remove(){
		var rows = grid.getSelecteds();
		if(!rows || rows.length != 1){
			alert('请选择一条记录');
			return;
		}
		var json = nui.encode({item:rows[0]});
        $.ajax({
            url: "com.bos.utp.rights.empposition.delEmpposition.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                if(text.returnCode==1) {
            		grid.reload({item:{omEmployee:{empid:window['parentNode'].empid}}});
            	} else {
            		alert("操作失败！");
            	}
            },
            error: function () {
            }
        });
	}
</script>

</body>
</html>