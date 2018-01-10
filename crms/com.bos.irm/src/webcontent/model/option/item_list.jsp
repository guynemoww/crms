<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-14
  - Description:TB_IRM_ADJUST_OPTION, com.bos.dataset.irm.TbIrmAdjustOption
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbIrmAdjustOption.modelNumber"  value="<%=request.getParameter("modelNum") %>"   class="nui-hidden"  />

</div>
				


	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.irm.model.getModelAdjustOptionList.biz.ext"
	dataField="tbIrmAdjustOptions"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div field="ADJUST_TYPE_CD" headerAlign="center" allowSort="true"   dictTypeId="XD_PJCD0014">选项类别</div>
		<div field="OPTION_TYPE_CD" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0010">调整类型</div>
		<div field="ADJUST_SERIES" headerAlign="center" allowSort="true" dictTypeId="XD_ADJUSTSERIES">调整级数</div>
		<div field="ADJUST_OPTION_DESCRIPTION" headerAlign="center" allowSort="true" >调整选项描述</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var modelNum="<%=request.getParameter("modelNum") %>";
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    function reset(){
		form.reset();
	}
	


	</script>
</body>
</html>
