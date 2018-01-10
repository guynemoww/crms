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

				

<fieldset>
  	<legend>
    	<span>单一预警客户查询</span>
    </legend> 
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
     <div id="form2" style="width:100%;height:auto;overflow:hidden;">
        <div class="nui-dynpanel" columns="4" style="text-align:center;">
		    <div style="text-align:center;">客户名称</div>
		    <input name="partyName" required="false"  class="nui-textBox nui-form-input" style="width:100%;" />
		    <div style="text-align:center;">客户编号</div>
		    <input name="partyNum" required="false"  class="nui-textBox nui-form-input" style="width:100%;" />
		</div>
    </div>
    <div class="nui-toolbar" style="border-bottom:0;text-align:right;">
	    <a class="nui-button" iconCls="icon-add" onclick="queryCsm()" id="btnAdd">查询</a>
	</div>
</fieldset>
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>单一客户预警信号列表</span>
    </legend>
    <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.monitor.queryCsmWarnInfo.biz.ext" dataField="csmWarnings"
	     allowResize="true" showReloadButton="false" showPageSize="false"  pageSize="10" multiSelect="false" sortMode="client">
	     <div property="columns">
		    <div type="checkcolumn" >选择</div>
	    	<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		    <div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		    <div field="ecifPartyNum" headerAlign="center" allowSort="true" >ECIF编号</div>
		    <div field="outEvalResult" headerAlign="center" allowSort="true" >客户信用等级</div>
		    <div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004" >预警级别</div>
		    <div field="confirmDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">预警级别认定日期</div>
		    <div field="orgNum" headerAlign="center" dictTypeId="org" allowSort="true" >经办行</div>
		    <div field="userNum" headerAlign="center"  dictTypeId="user" allowSort="true" >客户经理</div>
		</div>
		
	</div>
</fieldset>
</div>
			
<div class="nui-toolbar" style="margin-top:40px;border-bottom:0;text-align:right;">
	    <a class="nui-button" iconCls="icon-add" onclick="addCsm()" id="btnAdd">新增预警客户</a>
	    <a class="nui-button"  iconCls="icon-add" onclick="viewInfo()">预警管理</a>
</div>
   
</body>

 <script type="text/javascript">
	 	nui.parse();
	 	git.mask();                                                       //表单遮罩
	    var form = new nui.Form("#form1"); 
	    var form2 = new nui.Form("#form2"); 
	    var grid = nui.get("grid1");
		
		
function initForm() {
     git.mask();                                                          //表单遮罩
     grid.load({levelCd:"1",partyTypeCd:"01"});                                            //加载有预警级别的客户信息
	 //setTimeout(initGridDetail, 100);    延时调用
     git.unmask();        		                                          //取消遮罩
}
initForm();

/*
function initGridDetail(){
		grid.selectAll();
}
*/

//查询客户
function queryCsm(){
  var corp=form2.getData();
     grid.load({levelCd:"1",partyTypeCd:"01",corp:corp});                                            //加载有预警级别的客户信息
}		

//添加预警客户
function addCsm(){
  git.mask();
  var url=nui.context+"/ews/warnDetail/warnMgr/ews_new_singleCsm_list.jsp";
  git.go(url);              
}       

//新增预警信号
function viewInfo(){
   git.mask();                                                                                          //表单遮罩
   var row = grid.getSelected();                                                                        //取得所选客户
   if(!row){
     alert("请选择！！！");
     git.unmask();
   return;
   }else{
     var url= nui.context+ "/ews/warnDetail/warnMgr/tree/ews_warnMgr_tree_add.jsp?corpid="+row.partyId+"&type=01&partyName="+row.partyName+"&partyNum="+row.partyNum;
     git.go(url);        
          
   }
   git.unmask();
}      

	</script>
</html>