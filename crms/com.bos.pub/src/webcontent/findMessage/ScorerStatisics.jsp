<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-19
  - Description:TB_PUNISH_MESSAGE, com.bos.pub.sys.TbPunishMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" >
	
	<div class="nui-dynpanel" columns="6">

		<label>计分人姓名：</label>
		<input  name="map/scoreName" class="nui-textbox nui-form-input"  />

		<label>计分人工号：</label>
		<input  name="map/scoreNumber" required="false"  class="nui-textbox nui-form-input"  />
		<label>计分起始时间-计分结束时间：</label>
		<div>
		<input name="map/startTime" required="false" class="nui-datepicker nui-form-input" />~
		<input name="map/endTime" required="false" class="nui-datepicker nui-form-input" />
		</div>
	</div>
 </form>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.openOrder.scorerStatistics.biz.ext"
	dataField="scorers"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="SCORE_NAME" headerAlign="center" allowSort="true" dictTypeId="org">计分对象</div>
		<div field="SCORE_NUMBER" headerAlign="center" allowSort="true" >计分人工号</div>
		<div field="ordernum" headerAlign="center" allowSort="true" >单子数</div>
		<div field="shouldscoring" headerAlign="center" allowSort="true" >应计分</div>
		<div field="realscoring" headerAlign="center" allowSort="true" >累计计分</div>
		</div>
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
	   //导出
    function exportEmp()
    {
     var forms = document.getElementById("form1");
		     forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=09";
		     forms.submit();
    }
	</script>
</body>
</html>
