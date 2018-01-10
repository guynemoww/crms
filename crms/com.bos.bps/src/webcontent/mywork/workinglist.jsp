<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2013-12-18 12:42:24
  - Description:展示待执行工作项，以及待领取工作项
-->
<head>
<title>待办任务/工作列表 - 我的工作</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		IUserObject user = CommonUtil.getIUserObject();
		String userid = user.getUserId();
	%>
	<div id="form1" class="nui-form" >
		<div class="nui-dynpanel" columns="4" style="margin-top: 0;margin-bottom: 0">
			<label class="nui-form-label">客户名称：</label>
			<input id="wi.custName" name="wi.custName"  class="nui-textbox nui-form-input"/>
			<label class="nui-form-label">创建时间：</label>
			<div colspan="1" >
				<input id="wi.startDate" name="wi.startDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />-
				<input id="wi.endDate" name="wi.endDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />
			</div>
			<label class="nui-form-label">业务类型：</label>
			<div colspan="5" >
				<a id="wi.flowTypeCd" name="wi.flowTypeCd" style="float:left;"  repeatItems="1000" textField="text" multiSelect="false" valueField="id"  class="nui-checkboxlist"></a >
			</div>
		</div>	
		<div class="nui-toolbar" style="text-align:right;padding-right:25px;border:none;">
		    <a class="nui-button" iconCls="icon-search" onclick="query(1)">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:none;margin-top:7px">
		<!-- <a class="nui-button"   onclick="reassignWorkItem()">代办</a> -->
		<%--<a class="nui-button" id="btn_exb" onclick="exceBatch()">批量提交</a>--%>
	</div>
	<div class="nui-fit">
		<div id="datagrid1" class="nui-datagrid" style="height:100%;"  sortMode="client"
		    url="com.bos.bps.op.WorkFlowManager.queryWorkingList.biz.ext" dataField="list"allowAlternating="true"
		    multiSelect="true"  emptyText="没有查到数据"
		    allowCellEdit="false" allowCellSelect="false">
		    <div property="columns">
		    	<div type="checkcolumn"></div>
		        <%--<div type="indexcolumn">序号</div>--%>
		        <div field="processInstId" allowSort="true" width="" headerAlign="center">流程编号</div> 
		        <div field="cusName" allowSort="true" width="15%" headerAlign="center">客户名称</div> 
		        <%--<div field="flowTypeCd" allowSort="true" width="" renderer="onFlowTypeCdRenderer" headerAlign="center">业务类型</div>--%>
		        <div field="processInstName" allowSort="true" width="15%" headerAlign="center">业务类型</div>
		       <%-- <div field="processInstName" allowSort="true" width="" headerAlign="center">流程实例名称</div> --%>  
		        <div field="wfCreateUserName" allowSort="true" width="100px" headerAlign="center">发起人</div> 
		        <div field="wfCreateOrgName" allowSort="true" width="" headerAlign="center">发起机构</div>
		        <div field="createTime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >创建时间</div>
		        <div field="activityInstName" allowSort="true" width="" headerAlign="center" >当前活动</div>
		        <%--<div field="isConfirmPer" name="isConfirmPer" renderer="onisConfirmerRenderer" allowSort="true" width="100px" headerAlign="center" >是否超权限</div> --%> 
		        <div field="wfReadStatus" renderer="onReadStatusRenderer" allowSort="true" width="100px" headerAlign="center" >阅读标志</div>    
		        <div field="op" autoEscape="false" headerAlign="center">操作</div>
		     </div>
		</div>
	</div>
<script type="text/javascript">
	nui.parse();
	//待办列表
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1");
	function query(flag){
		//如果是在本页点击查询，则不初始化流程类型
		if(1==flag){
		
			var o = form.getData();
       		grid.load(o);
		
		}else{
			//加载任务列表
	       var json = nui.encode({o:1});
	        $.ajax({
	            url: "com.bos.bps.op.WorkFlowManager.workinglistCount.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            async: false, //同步处理
	            success: function (text) {
	            	if(text.flows){
	            		var flows=text.flows;
	            		var datajson=[];
	            		for (var i=0; i<flows.length; i++) {
	            			var t=nui.getDictText("XD_WFCD0001",flows[i].flowTypeCd);
	            			if(null == t || '' == t){
	            				t = "其它";
	            			}
	            			var json={};
	            			json["id"]=flows[i].flowTypeCd;
	            			json["text"]=t+"("+flows[i].cnt+")";
	            			datajson[i]=json;
	            		}
	            		nui.get("wi.flowTypeCd").setData(datajson);
	            		nui.get("wi.flowTypeCd").setValue('<%=request.getParameter("flowTypeCd")%>');
	            		
	            		var o = form.getData();
       					grid.load(o);
	            	} 
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
		}
       
       <%--//如果是资产分类，则显示是否超权限列
       var flowTypeCd = nui.get("wi.flowTypeCd").getValue();
       if("cls"==flowTypeCd){
       
       		grid.showColumn(grid.getColumn("isConfirmPer"));
       
       }else{
       
       		grid.hideColumn(grid.getColumn("isConfirmPer"));
       }--%>
      
	}
	query(0);
	
	 grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			
       			if(null !=e.data[i].cusName && ''!=e.data[i].cusName){
       			
       				e.data[i]['cusName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].custId+ '\');">'+e.data[i].cusName+'</a>';
       			}
       			var state = e.data[i].currentState;
       			debugger;
       			if('10' == state){
       				e.data[i]['op']='<a href="#" onclick="getWorItem({bizId:\''
       				+e.data[i].bizId
       				+'\'},'
       				+i
       				+');">执行</a>';
       			}else if('4' == state){
       				e.data[i]['op']='<a href="#" onclick="updateWorkItem({bizId:\''
       				+e.data[i].bizId
       				+'\'},'
       				+i
       				+');">认领执行</a>';
       			}
       		}
       });
	
	//重置
	function reset(){
		form.reset();
	}
	
	//改变行颜色或字体
	function setRowSytle(e){
		
		var field = e.field;
		if(field=='wfReadStatus'){
			if('1'==e.value){
				//字体变红或加粗
				e.rowStyle="color:red";//font-weight: bold
			}
		}
	}
	
	var ReadStatus = [{ id: 1, text: '未读' }, { id: 2, text: '已读'}];
    function onReadStatusRenderer(e) {
        for (var i = 0, l = ReadStatus.length; i < l; i++) {
            var g = ReadStatus[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }
	
	var isConfirmPer = [{ id: 1, text: '否' }, { id: 2, text: '是'}];
    function onisConfirmerRenderer(e) {
        for (var i = 0, l = isConfirmPer.length; i < l; i++) {
            var g = isConfirmPer[i];
            if (g.id == e.value) return g.text;
        }
        return "";
	}
	
	function onFlowTypeCdRenderer(e){
		return nui.getDictText("XD_WFCD0001",e.value);
	}
	
	//批量提交
	function exceBatch(){
		
		var rows = grid.getSelecteds();
		if(null==rows || ''==rows){
		
			nui.alert("请选择提交记录！");
			return;
		}
		//这里校验，只有创建机构为一样,业务类型一样的，才可以批量提交，并且岗位相同
		var flowtypecd=rows[0].processDefName;
		if(rows.length==1){
			//业务类型校验
			if("com.bos.bps.cls.impairment_classification"!=flowtypecd && "com.bos.bps.aft.loan_check_company"!=flowtypecd){
			
				nui.alert("只有资产分类业务或贷后检查流程(公司）支持批量提交！");
				return;
			}
		}else{
			var check_flag=true;
			//模板名称
			var processDefName=rows[0].processDefName; 
			//是否超权限
			var isChao = rows[0].isConfirmPer;
			for(var i=0;i<rows.length;i++){
				var row = rows[i];
				//业务类型校验
				if("com.bos.bps.cls.impairment_classification"!=row.processDefName && "com.bos.bps.cls.impairment_classification_check"!=row.processDefName && "com.bos.bps.aft.loan_check_company"!=row.processDefName){
				
					nui.alert("第"+(i+1)+"条数据,不是资产分类业务或贷后检查流程(公司）业务,不支持批量提交！");
					check_flag=false;
					break;
				}
				
				if(processDefName!=row.processDefName){
				
					nui.alert("第"+(i+1)+"条数据,与第一条数据流程模板不一致,不支持批量提交！");
					check_flag=false;
					break;
				}
				
				if(isChao!=row.isConfirmPer){
				
					nui.alert("第"+(i+1)+"条数据,与第一条数据权限不一致,不支持批量提交！");
					check_flag=false;
					break;
				}
				
			}
			if(!check_flag){
				return;
			}
		
		}
		
		
		//获取流程模板版本,单个流程的配置信息
		var processDefName = rows[0].processDefName;
		var activityDefId = rows[0].activityDefId;
		var processInstId = rows[0].processInstId;
		var json = nui.encode({"processDefName":processDefName,"activityDefId":activityDefId,"processInstId":processInstId});
        $.ajax({
            url: "com.bos.bps.util.TbWfmWorkItemMapping.getTbWfmworkitemMapping.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (data) {//alert(nui.encode(data.map));return;
            	if (null != data.itemInfo) {
            		//版本号，以及业务处理类
            		var templateVersion = data.itemInfo.templateVersion;
            		var doUrl = data.itemInfo.doUrl;
            		//弹出页面数据存储变量
            		//取第一条，获取意见信息与下一审批人
					nui.open({
			            url: nui.context + "/bps/mywork/work_flow_advice_batch.jsp?processInstId="+processInstId+"&activityDefId="+activityDefId+"&templateVersion="+templateVersion,
			            showMaxButton: false,
			            title: "",
			            width: 550,
			            height: 250,
			            ondestroy: function (action) {
			                if (action == "ok") {
			                    var iframe = this.getIFrameEl();
			                    iframe.contentWindow.setBtnEnable();
			                    var data = iframe.contentWindow.GetData();
			                    data = nui.clone(data);
			                    //循环之前，防止重复提交,增加透明遮罩
								git.mask();
			                   	//因为考虑到每提交一笔，都要进行校验，所以循环每一笔，单个提交
			                   	var flag=true;//执行标识
								for(var i=0;i<rows.length;i++){
									
									var row = rows[i];
									//初始化提交所需数据
									row.templateVersion=templateVersion;//版本号
									row.activityType = data.activityType;//下一节点类型：手工，结束
									if("finish" != data.activityType){
									 row.userVariable = "next_"+data.userVariable;//参与人相关数据域变量
									}
									row.opinion=data.opinion;//意见
									row.nextOrgCd=data.nextOrgCd;//下一机构
									row.nextOrgName=data.nextOrgName;//下一机构名称
									row.nextPostCd=data.nextPostCd;//下一岗位号
									row.nextPostName=data.nextPostName;//下一岗位名称
									row.nextUsersName=data.nextUsersName;//下一操作员名称
									row.nextUsersNum=data.nextUsersNum;//下一操作员号
									//提交流程
									submit(row,i);
								}
								//循环完成后，再取消遮罩
								git.unmask();
								query(1);
			                }
			            }
			        });
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	
	//提交方法
    function submit(row,i){
	        var json = nui.encode({"it":row});
	        $.ajax({
	            url: "com.bos.bps.op.WorkFlowManager.submitProcessToNext.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            async: false,
	            contentType:'text/json',
	            success: function (text) {
	            	//if(null != text.result && '' != text.result){
	            		
	        			//if('1' != text.result){
	        			
	            			//nui.alert("第"+(i+1)+"条，客户名称为["+row.cusName+"]数据，提交失败！"); //失败时后台直接返回出错信息
	        			//}
	            	//}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
    }
	
	
	//执行工作项事件，进行审批
	function getWorItem(e, idx) {
		var w=self.parent ? self.parent : self;
		var it = grid.getRow(idx);
		//var it = grid.getSelected();
		
		var processDefName = it.processDefName;
		var activityDefId = it.activityDefId;
		var processInstId = it.processInstId;
		if (!!it) {
			var jsonstr=nui.encode({'processid':processInstId,'workitemId':it.workItemId});
		$.ajax({
                url: "com.bos.bps.util.TbWfmProcessInstance.chackProcessStatus.biz.ext",
                type: 'POST',
                data: jsonstr,
                cache: false,
                async: false,
                contentType:'text/json',
                success: function (data) {//alert(nui.encode(data.map));return;
                	if(null!=data.map && "waiting"==data.map.status){
                		nui.alert("当前流程已被挂起:挂起意见["+data.map.opinion+"]","提示");
                		return;
                	}
                	if(null!=data.map && "claiming"==data.map.status){
                		nui.alert("该待办工作项，已经发送变更申请，正在等待["+data.map.opinion+"]确认,不能点击执行！","提示");
                		return;
                	}
                	if(null!=data.map && "exception"==data.map.status){
                		nui.alert("数据异常，原因是：["+data.map.opinion+"]，请联系管理员处理！","提示");
                		return;
                	}
                	
					var json = nui.encode({"processDefName":processDefName,"activityDefId":activityDefId,"processInstId":processInstId,"it":it});
		            $.ajax({
		                url: "com.bos.bps.util.TbWfmWorkItemInstance.insertTbWfmWorkitemInstance.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                async: false,
		                contentType:'text/json',
		                success: function (data) {//alert(nui.encode(data.map));return;
		                	if(data.msg){
		                		alert(data.msg);
		                		return;
		                	}else if(null!=data.workiteminstanceQuery && null!=data.workiteminstanceQuery.userNum && '<%=userid %>'!=data.workiteminstanceQuery.userNum){
		                		data.itemInfo=null;
		                		nui.alert("当前这条待办["+git.getUserName(data.workiteminstanceQuery.userNum)+"]正在操作");
		                	}else if (null != data.itemInfo) {
		                	//w.location.replace
		                		var urlobj=nui.context+"/bps/mywork/work_flow_top.jsp?bizId="+e.bizId+"&processInstId="+processInstId
		                		+"&workItemId="+it.workItemId+"&viewUrl="+data.itemInfo.viewUrl+"&conclusion="+data.itemInfo.finalJudge +"&postNum="+data.itemInfo.postNum
		                		+"&ruleID="+data.itemInfo.ruleId+"&activityDefId="+activityDefId+"&activityInstId="+it.activityInstId
		                		+"&processDefName="+processDefName+"&activityInstName="+it.activityInstName+"&selectType="+data.itemInfo.selectType
		                		+"&orgLvCd="+data.itemInfo.orgLvCd+"&bizType="+it.bizType+"&doUrl="+data.itemInfo.doUrl
		                		+"&workitemMappingId="+data.itemInfo.workitemMappingId+"&approveType="+it.approveType
		                		+"&templateVersion="+data.itemInfo.templateVersion+"&userVariable="+data.itemInfo.userVariable
		                		+"&startTime="+nui.formatDate(it.startTime,"yyyy-MM-dd HH:mm:ss")+"&wfBackOperPositionId="+it.wfBackOperPositionId+"&partyNum="+it.custId;
		                		w.location.replace(urlobj);
		                	}
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    nui.alert(jqXHR.responseText);
		                }
		            });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });

            
		}
		
		//w.location.href=nui.context+'/csm/corp/eval/eval_corp_tree.jsp?bizId='+e.bizId;
	}
	
	//认领工作项事件，更新审批人
	function updateWorkItem(e, idx){
	
		var w=self.parent ? self.parent : self;
		var it = grid.getRow(idx);
		//var it = grid.getSelected();
		if (null != it) {
		
			var json = nui.encode({"it":it});
            $.ajax({
                url: "com.bos.bps.op.WorkFlowManager.assignWorkItemToSelf.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (data) {
                	if ("1"==data.retflag) {
                		//w.location.reload();
                		getWorItem({bizId:e.bizId},idx);
                	}else{
                		 nui.alert("该待办任务已被认领,请刷新列表！");
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });
		
		}
	
	}
	
	//改派参与人
	function reassignWorkItem(){
		
		var rows = grid.getSelecteds();
		
		if(rows.length<1){
		
			nui.alert("请选择要代办的记录！");
			return;
		}
		
		if(rows.length>1){
		
			nui.alert("请选择一条要代办的记录！");
			return;
		}
		
		//数据有效检查通过，取第一条，初始化数据
		var row = rows[0];
        var json = nui.encode({"row":row});
        $.ajax({
            url: "com.bos.bps.op.WorkFlowManager.initReassginWorkItemMap.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (data) {
            	window['bps_attr_map'] = data.map;
            	//选择人员
            	selectEmpOrg(rows);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	
	//选择机构
    function selectEmpOrg(rows) {
   
        nui.open({
            url: nui.context + "/bps/mywork/select_bps_user.jsp?flag=2&map="+escape(nui.encode(window['bps_attr_map'])),
            showMaxButton: false,
            title: "选择人员",
            width: 350,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	
                    	var currentUserId = '<%=userid%>';
                    	if(currentUserId == data.USERID){
                    	
                    		nui.alert("指定代办的参与者与当前的参与者相同，不需要操作代办！");
                    	}else{
                    		//如果只有一条，反之，直接生效
                    		if(rows.length==1){
                    		
                    			var status='2';//待认领状态
                    	    	//保存代办日志
                    			var ret = saveParticipantLog(rows[0].processInstId,rows[0].workItemId,data,status);
                    			if('1'==ret){
                    			
                    				nui.alert("该待办工作项，已经发送变更申请，正在等待对方确认！");
                    			}else{
                    			
	                    			nui.alert("代办操作成功，等待"+data.OPERATORNAME+"确认！");
                    			}
                    		}else{
                    			//循环之前，防止重复提交,增加透明遮罩
								git.mask();
                    			var status='3';//生效状态
                    			for(var i=0;i<rows.length;i++){
                    			
                    				var row = rows[i];
                    					var json=nui.encode({'workitemId':row.workItemId,'personId':data.USERID});
					                $.ajax({
						                    url: "com.bos.bps.op.WorkFlowManager.reassignWorkItem.biz.ext",
							                type: 'POST',
							                data: json,
							                cache: false,
							                async: false,
							                contentType:'text/json',
						                    success: function (text) {
						                    	if(null==text.map.errCode || ''==text.map.errCode){
							                      	//保存代办日志
		                    						saveParticipantLog(row.processInstId,row.workItemId,data,status);
						                    	}
						                    },
						                    error: function () {
						                    }
					                	});
                    			}
                    			//循环完成后，再取消遮罩
								git.unmask()
                    		}
	      					
	      					//处理完成后，重新加载列表
	      					query(1);	 
                    	}
                    }
                }
            }
        });
    }
    
    //保存参与人代办日志
    function saveParticipantLog(processId,workItemId,data,status){
    
    	var ret =null;
    	var json = nui.encode({"processId":processId,"workitemId":workItemId,"personId":data.USERID,"personName":data.OPERATORNAME,"status":status,"operator":data.USERID});
  		$.ajax({
            url: "com.bos.bps.util.TbWfmParticipantlog.saveTbWfmParticipantlog.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (data) {
            	ret = data.ret;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
		return ret;
    }
	
	
	function renderType(e) {
		return nui.getDictText("flow_type_cd",e.row.wfBizType);
	}
</script>
</body>
</html>