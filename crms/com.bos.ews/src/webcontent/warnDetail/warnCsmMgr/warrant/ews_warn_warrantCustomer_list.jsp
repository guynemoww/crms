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

				

<!-- <strong style="margin-top:30px;">预警管理->担保客户列表->已有预警信号客户列表</strong>    -->

<div id="form1" style="width:100%;height:auto;overflow:hidden;margin-top: 7px;">
     <div id="form2" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;border: 1px;" >
        <div class="nui-dynpanel" columns="4" style="text-align:center;">
		    <div>客户名称</div>
		    <input name="partyName" required="false"  class="nui-textBox nui-form-input" style="width:100%;" />
		    <div>客户编号</div>
		    <input name="partyNum" required="false"  class="nui-textBox nui-form-input" style="width:100%;" />
		</div>
		<div class="nui-toolbar" style="border:none;text-align:right;margin-top: 7px;">
	        <a class="nui-button" iconCls="icon-search" onclick="queryCsm()" id="btnAdd">查询</a>
	        <a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	    </div>
    </div>
<fieldset>
  	<legend>
    	<span>已有预警信号客户列表</span>
    </legend>
    <div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;margin-top: 7px;" url="com.bos.ews.warning.queryCsmWarningInfo.biz.ext" dataField="csmWarnings"
	     allowResize="true" showReloadButton="false" showPageSize="false"  pageSize="10" multiSelect="false" sortMode="client">
	     <div property="columns">
		    <div type="checkcolumn" >选择</div>
	    	<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		    <div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		    <div field="outEvalResult" headerAlign="center" allowSort="true" >客户信用等级</div>
		    <!--  担保客户没有预警级别认定和资产分类 所以该字段不做显示
		    <div field="classificationNum" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0139" >最新分类</div>
		    <div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004" >预警级别</div>
		    <div field="confirmDate" headerAlign="center" allowSort="true" >预警级别认定日期</div>
		    -->
		    <div field="orgNum" headerAlign="center" dictTypeId="org" allowSort="true" >经办行</div>
		    <div field="userNum" headerAlign="center"  dictTypeId="user" allowSort="true" >客户经理</div>
		</div>
		
	</div>
</fieldset>
</div>
			
<div class="nui-toolbar" style="margin-top:7px;border-bottom:0;text-align:right;">
	    <a class="nui-button" iconCls="icon-add" onclick="addCsm()" id="btnAdd">新增预警客户</a>
	    <a class="nui-button"  iconCls="icon-node" onclick="viewInfo()">详情</a>
</div>
   
</body>

 <script type="text/javascript">
	 	nui.parse();
	 	git.mask();                                                       //表单遮罩
	    var form = new nui.Form("#form1"); 
	    var form2 = new nui.Form("#form2");
	    var grid = nui.get("grid1");
		var type = "<%=request.getParameter("type") %>";                  //参与人类型
		
function initForm() {
     git.mask();                                                          //表单遮罩
     grid.load({levelCd:"1",partyTypeCd:"06"});                                            //加载有预警级别的客户信息
	 //setTimeout(initGridDetail, 100);    延时调用
     git.unmask();        		                                          //取消遮罩
}
initForm();

//根据条件查询	
function queryCsm(){
     var corp=form2.getData();
     grid.load({levelCd:"1",partyTypeCd:"01",corp:corp});                                            //加载有预警级别的客户信息
}
		

//添加预警客户
function addCsm(){
  git.mask();
  var url=nui.context+"/ews/warnDetail/warnCsmMgr/warrant/ews_warn_warrantCsm_list.jsp?type="+type;
  git.go(url);              
}       

//预警详情
function viewInfo(){
   git.mask();                                                                                          //表单遮罩
   var row = grid.getSelected();                                                                        //取得所选客户
   if(!row){
     alert("请选择！！！");
     git.unmask();
     return;
   }else{
     var url= nui.context+ "/ews/warnDetail/ews_warn_main.jsp?corpid="+row.partyId+"&type="+type;
     git.go(url);        
          
   }
}      
function reset(){
		form.reset();
		search();
	}
	</script>
</html>
