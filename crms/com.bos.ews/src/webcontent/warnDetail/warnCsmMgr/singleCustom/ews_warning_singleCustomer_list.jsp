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

<div id="form1" style="width:100%;height:auto;overflow:hidden;border-bottom:none;margin-top: 7px;">
    <div id="form2" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
        <div class="nui-dynpanel" columns="4" style="text-align:center;">
        	<div>机构名称</div>
			<input id="orgid" name="orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			
		    <div>客户名称</div>
		    <input name="partyName" required="false"  class="nui-textBox nui-form-input" />
		    
		    <label>证件类型</label>
			<input name="certType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" />
			
	        <label>证件号码</label>
			<input name="certNum" required="false" class="nui-textbox nui-form-input" />
			
		</div>
   
     <div class="nui-toolbar" style="text-align:right;border:none" >
        <a class="nui-button" iconCls="icon-search" onclick="queryCsm()" id="btnAdd" >查询</a>
        <a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
	 </div>
    </div>
    
    <fieldset>
  	<legend>
    	<span>预警客户列表</span>
    </legend>
    	
    <div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" url="com.bos.ews.warning.queryCsmWarningInfo.biz.ext" dataField="csmWarnings"
	     allowResize="true" showReloadButton="false" showPageSize="true"  pageSize="10" multiSelect="false" sortMode="client">
	  
	     <div property="columns">
		    <div type="checkcolumn" >选择</div>
		    <div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
	    	<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		    <div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
		    <div field="certNum" headerAlign="center" allowSort="true" >证件号码</div>
		    <div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJJB0001" >预警级别</div>
		    <div field="confirmDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警级别认定日期</div>
		    <div field="orgNum" headerAlign="center" dictTypeId="org" allowSort="true" >经办行</div>
		    <div field="userNum" headerAlign="center"  dictTypeId="user" allowSort="true" >客户经理</div>
		</div>
		
	</div>
	</fieldset>
</div>
			
<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:right;margin-top:7px">
	    <a class="nui-button" iconCls="icon-add" onclick="addCsm()" id="btnAdd" >新增预警客户</a>
	    <a class="nui-button"  iconCls="icon-node" onclick="viewInfo()"  >详情</a>
</div>
   
</body>

 <script type="text/javascript">
	 	nui.parse();
	 	git.mask();                                                       //表单遮罩
	    var form = new nui.Form("#form1"); 
	    var form2 = new nui.Form("#form2");
	    var grid = nui.get("grid1");
		var type = "<%=request.getParameter("type") %>";
		var monitor = "<%=request.getParameter("montior") %>";
		
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

//根据条件查询	
function queryCsm(){
  var corp=form2.getData();
     grid.load({levelCd:"1",partyTypeCd:"01",corp:corp});                                            //加载有预警级别的客户信息
}


//添加预警客户
function addCsm(){
  git.mask();
  var url=nui.context+"/ews/warnDetail/warnCsmMgr/singleCustom/ews_warning_customer_list.jsp?type="+type+"&monitor="+monitor;
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
     var url= nui.context+ "/ews/warnDetail/ews_warn_main.jsp?corpid="+row.partyId+"&type="+type+"&monitor="+monitor;
     git.go(url);        
          
   }
}      
function reset(){
		form.reset();
		initForm();
	}
	
	//机构选择
	function selectOrg(){
	
		var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
	
	</script>
</html>
