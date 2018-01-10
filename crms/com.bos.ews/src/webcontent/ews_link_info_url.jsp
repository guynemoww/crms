<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 叶奔
  - Date: 2014-03-27 10:08:30
  - Description:
-->
<head>
<title>相关链接</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="panel1" class="nui-panel" title="相关链接"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="false">
	 
	 <a href="#"  onclick="getCustmer()">客户信息</a> <br/>
	 <a href="#"  onclick="getFinacial()">预警变更历史</a> <br/>
	 <a href="#"  onclick="upload()">相关文档</a> <br/>
	 <!-- <a href="#"  onclick="getCustmer()">影像新增</a> <br/>  -->
	 <a href="#"  onclick="getImageInfo()">影像查询</a> <br/>
	 <a href="#"  onclick="getCustmer()">条码打印</a> <br/>
 </div>

 <script type="text/javascript">
	nui.parse();
	var party=<%=request.getParameter("party")%>;
	var bizId="<%=request.getParameter("bizId") %>";
	
	function getCustmer(){  
		nui.open({
            			url: nui.context + "/csm/pub/cust_query.jsp?partyId="+party.partyId,
            	 		title: "客户信息", 
            			width: 1024, 
        				height: 600,    
        				allowResize:true,
        				showMaxButton: true,
           				 ondestroy: function (action) {
                		 
            }
        });
	}
	function getImageInfo(){
        nui.open({
            url: nui.context + "/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd+"&view=1",
            title: "历史信息", 
            width: 800, 
        	height:400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
               
            }
        });
	}
    function upload() {
        nui.open({
            url: nui.context + "/biz/biz_pro_detail_info/pro_biz_upload.jsp?applyId="+bizId,
            title: "上传文档", 
            width: 800, 
        	height: 400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
            }
        });
    }
    
    function getFinacial() {
        nui.open({
            url: nui.context + "/ews/warnDetail/warnLevel/ews_level_change_history.jsp?bizId="+bizId,
            title: "预警变更历史", 
            width: 800, 
        	height: 400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
            }
        });
    }
 </script>
</body>
</html>