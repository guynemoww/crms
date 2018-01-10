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
<div id="panel1"  class="nui-dynpanel" columns="10" title="相关链接" style="width:98%;height:auto;" >
	 <a href="#"  onclick="getCustmer()">客户信息</a> <br/>
	 <!--  a href="#"  onclick="getFinacial()">财务信息</a> <br/>-->
	 <a href="#"  onclick="upload()">相关文档</a> <br/>
	 <!--  <a href="#"  onclick="queryView(1)">影像新增</a> <br/> -->
	 <a href="#"  onclick="queryView()">影像查询</a> <br/>
	 <!-- <a href="#"  onclick="getCustmer()">条码打印</a> <br/> -->
 </div>

 <script type="text/javascript">
	nui.parse();
	var party = <%=request.getParameter("party")%>;
	var bizId = "<%=request.getParameter("bizId") %>";
	function getCustmer(){
		
		//显示客户信息
        nui.open({
            url: nui.context + "/csm/pub/cust_query.jsp?partyId="+party.partyId,
            title: "客户信息", 
            width: 800, 
        	height:400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
               
            }
        });
	}
	function queryView(e){
		var view = e;
		//显示客户信息
        nui.open({
            url: nui.context + "/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&image=loanover"+"&partyTypeCd="+party.partyTypeCd+"&view="+view,
            title: "影像信息", 
            width: 800, 
        	height:400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
               
            }
        });
	}
	
	function getAppHis(){
        nui.open({
            url: nui.context + "/biz/biz_pro_detail_info/pro_biz_app_his.jsp?applyId="+bizId,
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
            url: nui.context + "/aft/file/relevantFile.jsp?applyId="+bizId,
            title: "上传文档", 
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