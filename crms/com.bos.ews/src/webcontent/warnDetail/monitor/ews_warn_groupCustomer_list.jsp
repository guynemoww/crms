<%@page pageEncoding="UTF-8"  %>
<%@include file="/common/nui/common.jsp" %>
<html >
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

				

<div style="margin-top:30px;">预警管理->集团预警客户列表</div>   
<div id="form1" style="width:100%;height:auto;overflow:hidden;">

    <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.monitor.queryCsmWarnInfo.biz.ext" dataField="csmWarnings"
	     allowResize="true" showReloadButton="false" showPageSize="false"  pageSize="10" multiSelect="true" sortMode="client">
	     <div property="columns">
		    <div type="checkcolumn" >选择</div>
	    	<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		    <div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		    <div field="outEvalResult" headerAlign="center" allowSort="true" >客户信用等级</div>
		    <div field="classificationNum" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0139" >最新分类</div>
		    <div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004" >预警级别</div>
		    <div field="confirmDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">预警级别认定日期</div>
		    <div field="orgNum" headerAlign="center" dictTypeId="org" allowSort="true" >经办行</div>
		    <div field="userNum" headerAlign="center"  dictTypeId="user" allowSort="true" >客户经理</div>
		</div>
		
	</div>
</div>
			
<div class="nui-toolbar" style="margin-top:40px;border-bottom:0;text-align:center;">
	    <a class="nui-button" iconCls="icon-add" onclick="addCsm()" id="btnAdd">新增预警客户</a>
	    <a class="nui-button"  iconCls="icon-add" onclick="viewInfo()">详情</a>
</div>
   
</body>

 <script type="text/javascript">
	 	nui.parse();
	 	git.mask();                                                       //表单遮罩
	    var form = new nui.Form("#form1"); 
	    var grid = nui.get("grid1");
		
		
function initForm() {
     git.mask();                                                          //表单遮罩
     grid.load({levelCd:"1",partyTypeCd:"05"});                                            //加载有预警级别的客户信息
	 //setTimeout(initGridDetail, 100);    延时调用
     git.unmask();        		                                          //取消遮罩
}
initForm();

/*
function initGridDetail(){
		grid.selectAll();
}
*/
		

//添加预警客户
function addCsm(){
  var url=nui.context+"/ews/warnDetail/monitor/ews_new_groupCsm_list.jsp?monitor=1&type=05";
  git.go(url);              
}       

//预警详情
function viewInfo(){
   git.mask();                                                                                          //表单遮罩
   var row = grid.getSelected();                                                                        //取得所选客户
   if(!row){
     alert("请选择！！！");
   }else{
     var url= nui.context+ "/ews/warnDetail/ews_warn_main.jsp?corpid="+row.partyId+"&monitor=1&type=05";
     git.go(url);        
          
   }

}      

//新增预警预案 
function applyWarningPlan(){
   git.mask();                                                                                          //表单遮罩
   var row = grid.getSelected();                                                                        //取得所选客户
   if(!row){
   alert("请选择！！！");
   }else{
   nui.open({
                url: "ews/ewsdetail/warnInfo/ews_warning_reservePlan_add.jsp?corpid="+row.partyId,
                title: "预警预案申请", 
                width: 900,  
                height: 500,
                onload: function () {
                }
            });
   }

}
	</script>
</html>
