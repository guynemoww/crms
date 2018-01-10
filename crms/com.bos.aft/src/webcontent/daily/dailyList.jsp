<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<%-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
--%>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日常检查</title>

</head>
<body>
<fieldset>
  	<legend>
   		<span>客户信息</span>
    </legend>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4"><label>客户名称：</label> <input
		name="partyName" class="nui-text nui-form-input" /> <label>客户编码：</label>
	<input name="partyNum" class="nui-text nui-form-input" /> <label>ECIF客户编号：</label>
	<input name="ecifPartyNum" class="nui-text nui-form-input" /> <label>客户类型：</label>
	<input name="partyTypeCd" class="nui-text nui-form-input" dictTypeId="XD_KHCD0219" /></div>
	</div>
</fieldset>
<div class="nui-toolbar" style="text-align:right;border:none">
	<a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-node" onclick="addEdit()">贷后检查信息维护</a>
	<a id="createReport" class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-node" onclick="add()">创建贷后检查报告流程</a></div>
<!-- 
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.corp.customerinfo.queryCorps.biz.ext" dataField="dailyList"
	    allowAlternating="true" multiSelect="false"
	    sizeList="[10,20,50,100]">

	    <div property="columns">
			<div field="partyId" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="customerTypeCd" headerAlign="center" allowSort="true" >客户类型</div>
			<div field="inspectId" headerAlign="center" allowSort="true" >日常检查清单编号</div>
			<div field="exchangeDays" headerAlign="center" allowSort="true" >检查日期</div>
			<div field="lastInspectDate" headerAlign="center" allowSort="true" >截止日</div>
			<div field="inspectStatusCd" headerAlign="center" allowSort="true" >检查状态</div>
		</div>
	</div>
	 -->
<script type="text/javascript">
	nui.parse();
	var partyId="<%=request.getParameter("corpid") %>";
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var json=nui.encode({"partyId":partyId});
	var node;
	var msg;
	var bizId;
		function query(){/* 数据加载 */
		    git.mask();
			var json = nui.encode({"param":{"partyId":partyId}});
		    nui.ajax({
                url: "com.bos.aft.dailyInspect.queryCorpInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.corpInfo);
                	git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });      			
		}
		query();
	
	//创建一个新的检查
	function add() {
		//git.mask();
		nui.get("createReport").setEnabled(false);
		nui.ajax({
			url: "com.bos.aft.aft_small_inspect.adjustCorp.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
             /**
                1.大型企业
                2.中型企业
                3.小型企业
                4.微型企业
             */
             if(mydata.corpration.whetherPassPeanuts != 1){                                  //创建大众贷后检查报告流程
                 var rslt = norCorpReportFlow();
                 if(rslt==1){
                    alert("该客户的检查报告已存在流程审批中！");
                    nui.get("createReport").setEnabled(true);
                    return;
                  }else if(typeof(rslt) == "undefined"||rslt==2){
                    nui.get("createReport").setEnabled(true);
                    alert("出现异常，请联系管理员！");
                    return;
                  }else if(rslt==3){
                    nui.get("createReport").setEnabled(true);
                    return;
                  }else{
                    var url=nui.context+"/aft/daily/daily_tree_go.jsp?partyId="+partyId+"&node="+nui.encode(node)+"&bizId="+bizId;
            	    git.go(url);
                  }
                  git.unmask();
            	}else{
            		smCorpReportFlow();                                                       //创建小企业贷后检查报告流程
            		if(msg==1){
            		    var param=nui.encode({"bizId":bizId,"node":node});
            			var url=nui.context+"/aft/aft_small_inspect/aft_dailyIns_tree_go.jsp?param="+param;     //小企业贷后检查流程走向
            			git.go(url);
            		}else if(msg==2){
            			nui.alert("该客户贷后检查流程还未走完!");
            			nui.get("createReport").setEnabled(true);
            			return;
            		}else if(msg==3){
            			nui.alert("出现异常!");
            			nui.get("createReport").setEnabled(true);
            			return;
            	    }else if(msg==4){
            	        nui.get("createReport").setEnabled(true);
            	        return;
            	    }
            	    git.unmask();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		
		});
		
		//var url=nui.context +"/aft/aft_small_inspect/aft_dailyIns_tree.jsp?partyId"+partyId;
	}
	
	//创建大众型客户贷后检查报告流程
	function norCorpReportFlow(){
	    var isOrNO =0;
	    var json = nui.encode({partyId:partyId});
		nui.ajax({
			url: "com.bos.aft.dailyInspect.checkReportFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            cache: false,
            success: function (text) {
                if(text.errorMsg){
                   alert(errorMsg);
                   isOrNO = 3;
                   git.unmask();
                }else{
            	    isOrNO = text.msg;
            	    node = text.node;
            	    bizId = text.bizId;
            	    git.unmask();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		});
		return isOrNO;
	}
	
	
	
	//创建一个新的检查--编辑
	function addEdit() {
		git.mask();
		nui.ajax({
			url: "com.bos.aft.aft_small_inspect.adjustCorp.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.corpration){                                                                  //判断是否存在一般公司客户存在则创建流程。
            		norCreateFlowEdit();                                                                //校验是否在流程中有则不再创建新流程。
            		if(msg==1){
            		    var param=nui.encode({"bizId":bizId,"partyId":partyId});                                        //,"node":node 暂时不用的参数 注掉
            			var url=nui.context+"/aft/dailyInspectAppr/daily_edit_tree_go.jsp?param="+param;//
            			git.go(url);
            		//}else if(msg==2){                                                                   
            			//nui.alert("该客户贷后检查流程还未走完!");
            			//return;
            		}else if(msg==3){
            			nui.alert("出现异常!");
            			return;
            		}
            	}else{
            		git.unmask();
            		alert("不是一般公司类客户!");
            		return;
            		/*
            		norCreateFlowEdit();
            		if(msg==1){
            		    var param=nui.encode({"bizId":bizId,"node":node});
            			var url=nui.context+"/aft/aft_small_inspect/aft_dailyIns_tree_go.jsp?param="+param;
            			git.go(url);
            		}else if(msg==2){
            			nui.alert("该客户贷后检查流程还未走完!");
            			return;
            		}else if(msg==3){
            			nui.alert("出现异常!");
            			return;
            		}
            		*/
            	}
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		
		});
		
		//var url=nui.context +"/aft/aft_small_inspect/aft_dailyIns_tree.jsp?partyId"+partyId;
	}
	
	//创建小企业贷后检查报告流程
	function smCorpReportFlow(){
		nui.ajax({
			url: "com.bos.aft.aft_small_inspect.createSmallBusiFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            success: function (mydata) {
                if(mydata.errorMsg){
                   alert(mydata.errorMsg);
                   msg=4;
                   git.unmask();
                   return;
                }else{
            	msg=mydata.msg;                                                                         //提示信息（可创建流程、有在途流程...）
            	node=mydata.node;                                                                       //岗位信息                                                                      
            	bizId=mydata.bizId;                                                                     //小企业贷后检查ID
            	git.unmask();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		});
	   
	}
	
	
	//创建贷后检查流程
	function norCreateFlowEdit(){
		nui.ajax({
			url: "com.bos.aft.dailyInspect.createEditNormalBusiFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	msg=mydata.msg;                                                                   
            	bizId=mydata.alcInfoId;                                                                //获取贷后检查ID
            	git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		});
	}
	

	
</script>
</body>
</html>
