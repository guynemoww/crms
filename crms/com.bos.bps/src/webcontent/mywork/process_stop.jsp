<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): js1688
  - Date: 2014-05-29 10:25:22
  - Description:
-->
<head>
<title>指定流程代办</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form">
		<div class="nui-dynpanel" columns="6" style="margin-bottom: 0;margin-top: 0;">
			<label class="nui-form-label">客户名称：</label>
			<input id="wi.cusName" name="wi.cusName"  class="nui-textbox nui-form-input" />
			<label class="nui-form-label">发起机构：</label>
			<input id="wi.orgid" name="wi.orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			<label class="nui-form-label">发起人：</label>
			<input id="wi.createUserName" name="wi.createUserName" class="nui-textbox nui-form-input" />
			<label class="nui-form-label">流程编号：</label>
			<input id="wi.processId" name="wi.processId"  class="nui-textbox nui-form-input" />
			<label class="nui-form-label">业务类型：</label>
			<input id="wi.processDefName" name="wi.processDefName" data="data" valueField="dictID" textField="dictName" dictTypeId="XD_WFCD0001" class="nui-dictcombobox" />
			<label class="nui-form-label">创建时间：</label>
			<div>
			<input id="wi.startDate" name="wi.startDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />-
			<input id="wi.endDate" name="wi.endDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>	
	<div class="nui-toolbar" style="border-bottom:none;margin-top: 7px">
		<a class="nui-button"   onclick="stop()">批量终止</a>
	</div>
	<div class="nui-fit">
		<div id="datagrid1" class="nui-datagrid" style="100%;"  sortMode="client"
		    url="com.bos.bps.util.TbWfmProcessInstance.queryProcessScopByOrg.biz.ext" dataField="pis"
		    allowAlternating="true" multiSelect="true" showEmptyText="true"
		    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true">
		    <div property="columns">
		        <%--<div type="indexcolumn">序号</div>--%>
		        <div type="checkcolumn"></div>
		        <div field="processId" allowSort="true" width="" headerAlign="center">流程编号</div>
		        <div field="cusName" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="processinstancename" allowSort="true" width="" headerAlign="center">流程实例名称</div>   
		        <div field="createOrgName" allowSort="true" width="" headerAlign="center">发起机构</div>
		        <div field="createUserName" allowSort="true" width="" headerAlign="center">发起人</div> 
		        <div field="createTime" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">创建时间</div>
		        <div field="lastupdatetime" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">最近更新时间</div>
		        <div field="processStatus" allowSort="true" width="" renderer="onStatus"  headerAlign="center">流程状态</div>
		         <div field="op" autoEscape="false" headerAlign="center">操作</div>
		     </div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1");
	function query(){
	   var o = form.getData();
       grid.load(o);
       grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       			debugger;
       			//alert(nui.encode(e.data));
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['op']='<a href="#" onclick="getWorItem({bizId:\''
       				+e.data[i].productId
       				+'\',processInstId:\''
       				+e.data[i].processId
       				+'\'});return false;">流程信息</a>';
       			if (e.data[i].bizViewUrl) {
       				e.data[i]['op']+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="getWorItem2({bizId:\''
       					+e.data[i].productId
       					+'\',url:\''
       					+e.data[i].bizViewUrl
       					+'\',processInstId:\''
       					+e.data[i].processId
       					+'\',bizType:\''
       					+e.data[i].productCd
       					+'\',processStatus:\''
       					+e.data[i].processStatus
       					+'\'});return false;">业务信息</a>';
       			}
       				//alert(e.data[i]['op']);
       		}
       });
    }   
	query();
	//重置
	function reset(){
		form.reset();
	}
	
	function stop(){
		var rows = grid.getSelecteds();
		
		if(rows.length<1){
		
			nui.alert("请选择要终止的记录！");
			return;
		}
		
		nui.confirm("流程一旦终止，将无法恢复，是否确定要终止流程？","提示",function(action){
			if(action=="ok"){
				nui.open({
		            url: nui.context + "/bps/mywork/process_stop_reason.jsp",
		            showMaxButton: false,
		            title: "",
		            width: 550,
		            height: 250,
		            ondestroy: function (action) {
		                if (action == "ok") {
		                	//循环之前，防止重复提交,增加透明遮罩
							git.mask();
		                	var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.GetData();
		                    data = nui.clone(data);
		                    //终止流程
		                	//var processId = row.processId;
							//var bizId = row.productId;
							//var processdefname = row.processdefname;
							
			            	// var json = nui.encode({"processId":processId,"bizId":bizId,"processdefname":processdefname,"opinion":data});
			            	var json = nui.encode({"opinion":data,"rows":rows});
			            	 $.ajax({
					            url: "com.bos.bps.op.WorkFlowManager.stopProcess.biz.ext",
					            type: 'POST',
					            data: json,
					            cache: false,
					            contentType:'text/json',
					            success: function (text) {
					            	if(text&&text.msg){
					            		nui.alert(text.msg); //失败时后台直接返回出错信息
					            		//循环完成后，再取消遮罩
										git.unmask();
					            	} else {
										//循环完成后，再取消遮罩
										git.unmask();
										
										nui.alert("终止完成！");
										//重新加载列表
										query();
					            	}
					            },
					            error: function (jqXHR, textStatus, errorThrown) {
					                nui.alert(jqXHR.responseText);
					            }
					        });
		                }
		            }
		        });
       		}
		});
	}
	//流程信息事件
	function getWorItem(e) {
		var w=self.parent ? self.parent : self;
		//nui.alert(nui.encode(e));
		nui.open({
		//workflow_opinions.jsp
            url: nui.context + '/bps/mywork/work_flow_timeline_his.jsp?bizId='+e.bizId
				+'&processInstId='
				+e.processInstId,
            showMaxButton: true,
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
	//业务信息事件
	function getWorItem2(e) {
		var w=self.parent ? self.parent : self;
		nui.open({
            url: nui.context +'/'+ e.url + '?wflow=1&bizId='+e.bizId+"&processInstId="+e.processInstId+"&bizType="+e.bizType+"&processStatus="+e.processStatus,
            showMaxButton: false,
            title: "查看业务信息",
            state:"max",
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
	
	function onStatus(e){
    	return nui.getDictText("XD_WFCD0003",e.value);
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