<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): ljf
  - Date: 2015-05-26
  - Description:提示列表汇总
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.queryRemindInfoGroup.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"  allowAlternating="true"
	showPager="false" multiSelect="false" >
	<div property="columns">
		<div type="indexcolumn">序号</div>
		<div field="remindType" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0015">提示类型代码</div>
		<div field="num" headerAlign="center" allowSort="true" >提示条数</div>
		<div field="op" headerAlign="center" allowSort="true"  align="center">操作</div>
	</div>
</div> 
<script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	function search() {
		grid.load();
		grid.on("preload",function(e){//客户信息链接
	   		if (!e.data || e.data.length < 1)
	   			return;
	   		for (var i=0; i<e.data.length; i++){
	   			e.data[i]['op']="<a href='#' onclick='view("
       				+e.data[i].remindType+");'>查看</a>";
	   		}
	    });
    }
    search();
	
    function reset(){
		form.reset();
	}
	
	function view(remindType){
		if(remindType<10){
			remindType="0"+remindType;
		}
		var url = "pub/remind/remind_"+remindType+"_view.jsp";
		nui.open({
            url: url+"?remindType="+remindType,
            title: nui.getDictText("XD_DHCD0015",remindType), 
            width: 1024,
    		height: 768,
    		state:"max",
            allowResize:true,
    		showMaxButton: true,
            onload: function () {
               
            },
            ondestroy: function (action) {
            	search();
            }
        });
	}
	
</script>
</body>
</html>
