<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>查询集团客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	     <div class="nui-dynpanel" columns="4">
	     	<label  class="nui-form-label">机构名称</label>
			<input id="orgid" name="orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			
		    <label class="nui-form-label">集团客户名称</label>
		    <input name="partyName" class="nui-textbox nui-form-input" vtype="maxLength:64"/>
		    
	     </div>
	     <div class="nui-toolbar" style="text-align:right;border:none" >
             <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
            <!--  <a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a> -->
         </div>
	</div>
	
    
<fieldset>
  	<legend>
    	<span>集团客户列表</span>
    </legend>

	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;margin-top: 7px;" url="com.bos.ews.queryCsm.queryGroupCsm2.biz.ext"
	     dataField="csmWarnings" allowResize="true" showReloadButton="false" showPageSize="true"  pageSize="10" multiSelect="false" sortMode="client">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true"  headerAlign="center">集团客户编号</div>
	        <div field="partyName" allowSort="true"  headerAlign="center" >集团客户名称</div> 
	        <div field="groupManageWayCd" headerAlign="center"allowSort="true" dictTypeId="XD_KHCD0204">集团客户管理方式</div>
			<div field="status" headerAlign="center" allowSort="true"dictTypeId="XD_KHCD0231">集团认定状态</div>
	        <div field="orgNum" allowSort="true"  headerAlign="center" dictTypeId="org" >主办行</div>
	        <div field="userNum" allowSort="true"  headerAlign="center" dictTypeId="user" >主办客户经理</div>       
	    </div>
	</div>
</fieldset>
    <div class="nui-toolbar" style="text-align:right;margin-top: 7px;">
        <a class="nui-button" iconCls="icon-add" onclick="addWarnInfo()">预警信号新增/级别认定/级别上调</a>
    </div>

<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1"); 
	var type = "<%=request.getParameter("type") %>";                 //参与人类型
	var monitor = "<%=request.getParameter("monitor") %>";           //检查监测岗入口


//待条件搜索	 
	function query(){
	   git.mask();                                                     //表单遮罩
       var corp = form.getData();                                      //获取查询实体
       grid.load({corp:corp});                                         //加载客户数据
       git.unmask();                                                   //加载数据完成取消遮罩
	}
	query();

//给选中客户信息增加预警信号
	function view(){
	    git.mask();                                                    //表单遮罩
		var row=grid.getSelected();                                    //取得所选客户
		if (null == row) {
			nui.alert("请选择一条记录");
			git.unmask(); 
			return;
		}
		
		var url=nui.context + "/ews/warnDetail/warnTree/ews_warnInfo_tree_add.jsp?corpid="+row.partyId +"&type="+type+"&monitor="+monitor;
		git.go(url);                                                   //跳转到新增预警信号页面              
	}
	
	function reset(){
		form.reset();
		search();
	}
	
	
	//新增预警信号
	function  addSignalManager(){
	   nui.get("addSignal").setEnabled(false); 
	   var bizId;
		var row=grid.getSelected();                                    //取得所选客户
		if (null == row) {
			nui.alert("请选择一条记录");
			git.unmask(); 
			return;
		}
	  var json = nui.encode({partyId:row.partyId});                                   //赋值参与人ID
	  $.ajax({      
            url: "com.bos.ews.commonUtil.addSignal.biz.ext",           //该逻辑流用于查询是否存在未用的业务ID，如果不存在则新建ID用于后续操作中作为业务ID使用
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (data) {
            	nui.get("addSignal").setEnabled(true);
            	
            	if(data.msg){
                	if(data.msg != "创建成功！") {
	        			return alert(data.msg);
	        		}
                }
            	
            	nui.open({//传值到tree页面
		            url: nui.context+"/ews/warnDetail/warnTree/signal_tree_add.jsp?corpid="+row.partyId+"&type="+data.type+"&bizId="+data.bizId,
		            showMaxButton: false,
		            title: "预警信号新增/级别认定/级别上调",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	initForm();//重新刷新页面
		            }
	        	});
	        	
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
	});
}   

	//新增预警信号
	function  addWarnInfo(){
	   var bizId;
		var row=grid.getSelected();                                    //取得所选客户
		if (null == row) {
			nui.alert("请选择一条记录");
			git.unmask(); 
			return;
		}
	  var json = nui.encode({partyId:row.partyId});                                   //赋值参与人ID
	  $.ajax({      
            url: "com.bos.ews.commonUtil.checkWarnAdjust.biz.ext",           //该逻辑流用于查询是否存在未用的业务ID，如果不存在则新建ID用于后续操作中作为业务ID使用
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	
            		if(text.flag){
            			return nui.alert(text.flag);
            			git.unmask();
            		}else {
            			bizId = text.bizId; 
                         var node=text.node;
                         //alert("流程创建成功,请完成后续操作!");
            	         var url=nui.context+"/ews/warnDetail/warnTree/ews_warnInfo_tree_add.jsp?bizId="+bizId+"&corpid="+row.partyId+"&node="+nui.encode(node)+"&processInstId="+text.processInstId+"&proFlag=1";
                         git.go(url);
            		}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                
            }
	});
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
</body>
</html>