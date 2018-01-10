<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>已办任务/跟踪列表 - 我的工作</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
			<label class="nui-form-label">客户名称：</label>
			<input id="wi.cusName" name="wi.cusName"  class="nui-textbox nui-form-input" />
			<label class="nui-form-label">发起机构：</label>
			<input id="wi.orgid" name="wi.orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			<label class="nui-form-label">发起人：</label>
			<input id="wi.createUserName" name="wi.createUserName" class="nui-textbox nui-form-input" />
			<label class="nui-form-label">业务类型：</label>
			<input id="wi.processDefName" name="wi.processDefName" data="data" valueField="dictID" textField="dictName" dictTypeId="XD_WFCD0001" class="nui-dictcombobox" />
			<label class="nui-form-label">流程状态：</label>
			<input id="wi.processStatus" name="wi.processStatus" data="data" valueField="dictID" textField="dictName" dictTypeId="XD_WFCD0003" class="nui-dictcombobox" />
			<label class="nui-form-label">创建时间：</label>
			<div>
			<input id="wi.startDate" name="wi.startDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />-
			<input id="wi.endDate" name="wi.endDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:65%;margin-top:10px"  sortMode="client"
	    url="com.bos.bps.op.WorkFlowManager.queryWorkedList.biz.ext" dataField="workeds"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <div field="PROCESSID" allowSort="true" width="" headerAlign="center">流程编号</div>
	        <div field="CUSNAME" allowSort="true" width="15%" headerAlign="center">客户名称</div>
	        <div field="PROCESSINSTANCENAME" allowSort="true" width="15%" headerAlign="center">业务类型</div>
	        <div field="CREATEORGNAME" allowSort="true" width="" headerAlign="center">发起机构</div>
	        <div field="CREATEUSERNAME" allowSort="true" width="" headerAlign="center">发起人</div> 
	        <div field="APPOINTORGNAME" allowSort="true" width="" headerAlign="center" >当前操作机构</div> 
	        <div field="APPOINTUSERNAME" allowSort="true" width="" headerAlign="center" >当前操作人</div>      
	        <div field="CREATETIME" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">创建时间</div>
	        <div field="LASTUPDATETIME" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">最近更新时间</div>
	        <div field="PROCESSSTATUS" allowSort="true" width="" renderer="onStatus"  headerAlign="center">流程状态</div>
	        <div field="op" autoEscape="false" headerAlign="center">操作</div>
	     </div>
	</div>
<script type="text/javascript">
	nui.parse();
	
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1");
	 grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       			//alert(nui.encode(e.data));
       		for (var i=0; i<e.data.length; i++){
       		
       			e.data[i]['op']='<a href="#" onclick="getWorItemDetail({bizId:\''
       				+e.data[i].PRODUCTID
       				+'\',processInstId:\''
       				+e.data[i].PROCESSID
       				+'\',viewUrl:\''
       				+e.data[i].BIZVIEWURL
       				+'\',bizType:\''
       				+e.data[i].PRODUCTCD
       				+'\',processStatus:\''
       				+e.data[i].PROCESSSTATUS
       				+'\',processDefName:\''
       				+e.data[i].PROCESSDEFNAME
       				+'\'});return false;">详情</a>';
       			if(null !=e.data[i].CUSNAME  && ''!=e.data[i].CUSNAME){
       			
	       			e.data[i]['CUSNAME']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].CUSTID+ '\');">'+e.data[i].CUSNAME+'</a>';
       			}
       		}
       });
	function query(){//对公单一客户查询
	   var o = form.getData();
       grid.load(o);
	}
	query();
	//重置
	function reset(){
		form.reset();
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
	
	//查看详情信息
	function getWorItemDetail(e){
	
		nui.open({
            url: nui.context + '/bps/mywork/work_flow_his_top.jsp?bizId='+e.bizId
				+'&processInstId='
				+e.processInstId
				+'&bizType='
				+e.bizType
				+'&processStatus='
				+e.processStatus
				+'&processDefName='
				+e.processDefName
				+'&viewUrl='
				+e.viewUrl,
            title: "查看详情信息",
            state:"max",
            showMaxButton:false,
            onload: function(e) {
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            	//alert(text);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                }
            }
        });
	}
	
	//查看流程信息
	function getWorItem(e) {
		var w=self.parent ? self.parent : self;
		//nui.alert(nui.encode(e));
		nui.open({
		//workflow_opinions.jsp
            url: nui.context + '/bps/mywork/work_flow_timeline_his.jsp?bizId='+e.bizId
				+'&processInstId='
				+e.processInstId,
            title: "查看流程信息",
            width: 673,
            height: 580,
            showMaxButton:false,
            allowResize:false,
            onload: function(e) {
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            	//alert(text);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                }
            }
        });
	}
	//查看业务信息
	function getWorItem2(e) {
		var w=self.parent ? self.parent : self;
		nui.open({
            url: nui.context +'/'+ e.url + '?wflow=1&bizId='+e.bizId+"&processInstId="+e.processInstId+"&bizType="+e.bizType+"&processStatus="+e.processStatus,
            showMaxButton: false,
            title: "查看业务信息",
            state:"max",
            onload: function(e) {
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            	//alert(text);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                }
            }
        });
	}
	
	function onStatus(e){
    	return nui.getDictText("XD_WFCD0003",e.value);
    }
	
</script>
</body>
</html>