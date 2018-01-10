<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): huangkai
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
  <div id="layout1" class="nui-layout" style="width:100%;height:100%;">
      <div title="押品分类树" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		 <ul id="tree1" class="nui-tree" style="width:300px;padding:5px;"  region="west"
			showTreeIcon="true" textField="sortName" idField="sortName" parentField="parentSortType" expandOnLoad="false"
			onnodeclick="nodeclick" dataField="collSortParameters">
		 </ul>
		 重复抵质押提示
      </div>
      <div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
          <div id="collsortparameterstabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;">
		      <div name="collSortParameters_list_tab"
		       title="押品分类维护" url="<%=request.getContextPath() %>/warnprompt/againreach/againreach_list.jsp" visible="true" >
		      </div>
		  </div>
     </div>
  </div>
     <script type="text/javascript">
        nui.parse();
	var tree = nui.get("tree1");
function reload() {
	$.ajax({
            url: "",//warnprompt/againreach/againreach_list.jsp
            type: 'POST',
            data: {},
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		//nui.alert(text.msg);
            	} else {
            		tree.loadList(text.collSortParameters,"sortName","parentSortType");  		
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
               // nui.alert(jqXHR.responseText);
            }
	});
}
reload();
	
	
	function nodeclick(e) {
		alert(e.node);
	}
	
	function refreshTab(node){
		var collsortparameterstabs = nui.get("collsortparameterstabs");
		var applicationtabs = applicationtabs_map[node.type];
		
		for(var i=0;i<applicationtabs.length;i++){
			var obj = applicationtabs[i];
			obj.url = setUrlParam(obj.path,node);
		}
		tabs.setTabs(collsortparameterstabs);
	}
     </script>
</body>
</html>