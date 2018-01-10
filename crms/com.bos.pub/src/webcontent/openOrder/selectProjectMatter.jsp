<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-14
  - Description:TB_SCORE_PROJECT_MESSAGE, com.bos.pub.sys.TbScoreProjectMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="2">
		
		<label>项目名称：</label>
		<input name="tbScoreProjectMessage.projectName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>

	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.scorePorject.getTbScoreProjectMessageList.biz.ext"
	dataField="tbScoreProjectMessages"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="projectName" headerAlign="center" allowSort="true" >项目名称</div>
		<div field="organizationName" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="orgLevel" headerAlign="center" allowSort="true" dictTypeId="CDZZ0002">机构级别</div>
		
		</div>
		
	</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick=selected()>确定</a>
        <span style="display:inline-block;width:25px;"></span>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    
  function selected() {
      var row = grid.getSelected();
        if (row) {
            CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
    
   
 function getData(){
    var row = grid.getSelected();
      if (row) {
            return row;
        } else {
            return null;
        }
    }
	</script>
</body>
</html>
