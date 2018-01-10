<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject" %>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2013-11-13 16:18:58
  - Description:流程审批
-->
<head>
<title>流程审批 - 意见</title>
</head>
<body>
<%
IUserObject user = CommonUtil.getIUserObject();
String orgdegree =(String)user.getAttributes().get("orgdegree");
String userid = user.getUserId();
 %>

<div id="panel1" class="nui-panel" title="审批"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input class="nui-hidden" id="it.processInstId" name="it.processInstId" value="<%=request.getAttribute("processInstId")%>"/>
		<input class="nui-hidden" id="it.workItemId" name="it.workItemId" value="<%=request.getAttribute("workItemId")%>"/>
		<input class="nui-hidden" id="it.activityDefId" name="it.activityDefId" value="<%=request.getAttribute("activityDefId")%>"/>
		<input class="nui-hidden" id="it.activityInstId" name="it.activityInstId" value="<%=request.getAttribute("activityInstId")%>"/>
		<input class="nui-hidden" id="it.processDefName" name="it.processDefName" value="<%=request.getAttribute("processDefName")%>"/>
		<input class="nui-hidden" id="it.activityInstName" name="it.activityInstName" value="<%=request.getAttribute("activityInstName")%>"/>
		<input class="nui-hidden" id="it.startTime" name="it.startTime" value="<%=request.getAttribute("startTime")%>"/>
		<input class="nui-hidden" id="it.templateVersion" name="it.templateVersion" value="<%=request.getAttribute("templateVersion")%>"/>
		<input class="nui-hidden" id="it.selectType" name="it.selectType" value="<%=request.getAttribute("selectType")%>"/>
		<input class="nui-hidden" id="it.ruleID" name="it.ruleID" value="<%=request.getAttribute("ruleID")%>"/>
		<input class="nui-hidden" id="it.isSrc" name="it.isSrc" value="<%=request.getAttribute("isSrc")%>"/>
		<input class="nui-hidden" id="it.loanChangeType" name="it.loanChangeType" value="<%=request.getAttribute("loanChangeType")%>"/>
		<input class="nui-hidden" id="it.termChangeWay" name="it.termChangeWay" value="<%=request.getAttribute("termChangeWay")%>"/>
		<input class="nui-hidden" id="it.nextOrgCd" name="it.nextOrgCd"/>
		<input class="nui-hidden" id="it.nextOrgName" name="it.nextOrgName"/>
		<input class="nui-hidden" id="it.nextPostCd" name="it.nextPostCd"/>
		<input class="nui-hidden" id="it.nextPostName" name="it.nextPostName"/>
		<input class="nui-hidden" id="it.nextActivityDefId" name="it.nextActivityDefId"/>
		<input class="nui-hidden" id="it.nextUsersName" name="it.nextUsersName"/>
		<input class="nui-hidden" id="it.nextUsersNum" name="it.nextUsersNum"/>
		<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
			<tr id="t_conclusion">
				<td style="width: 20%" >
				<label class="nui-form-label">意见结论： </label>
				</td>
				<td colspan="2" style="width: 80%" align="left">
					<select id="conclusion" name="it.conclusion" required="true"  class="nui-radiobuttonlist"  
					data="<%=request.getAttribute("conclusion")%>">
					</select>
				</td>
			</tr>
			<%--<%
			
				String workitemMappingId=(String)request.getAttribute("workitemMappingId");
				DataObject[] datas = BusinessParameterDAO.queryBusinessParameter(workitemMappingId);
				if(null!=datas&&datas.length>0){
				for(int i=0;i<datas.length;i++){
					DataObject data = datas[i];
					String porpertyName = data.getString("porpertyName");
					String porpertyNum = data.getString("porpertyNum");
					String porpertyDictName = data.getString("porpertyDictName");
					String showType = data.getString("showType");
					String isMust = data.getString("isMust");
			%>
					<tr>
						<td style="width: 20%"><label class="nui-form-label"><%=porpertyName%>：</label>
						</td>
						<td colspan="2" style="width: 80%">
							<input id="para.<%=porpertyNum%>" name="para.<%=porpertyNum%>" class="<%=showType%>"
							required="<%=isMust%>"  showNullItem="false" onvaluechanged="excuteFlowParaMonth()"  dictTypeId="<%=porpertyDictName%>"  style="width: 20%" />
						</td>
					</tr>
			<%
				}
			}
			%>--%>
			<tr>
				<td style="width: 20%"><label class="nui-form-label">意见： </label>
				</td>
				<td colspan="2" style="width: 80%">
					<input id="it.opinion" name="it.opinion" class="nui-textarea" onblur="blur"
					required="true" vtype="maxLength:4000" style="width: 80%;height:75px;"/>
				</td>
			</tr>
			<tr id="person" style="display: none;">
				<td style="width: 20%"><label id="selectuserLabel" class="nui-form-label">请选择人员： </label>
				</td>
				<td colspan="2" style="width: 80%">
					<input id="selectuser" name="username" textName="username"
						allowInput="false" class="nui-buttonEdit" required="true" onbuttonclick="selectEmpOrg"/> 
				</td>
				
			</tr>
			<tr id="m_person" style="display: none;">
				<td style="width: 20%"><label id="m_selectuserLabel" class="nui-form-label">请选择人员： </label>
				</td>
				<td colspan="2" style="width: 80%">
					<input id="m_selectuser" name="m_username"  textName="m_username" allowInput="false"  class="nui-buttonedit" 
						onbuttonclick="selectPositionAndEmp"  required="true"  />
				</td>
			</tr>
			<tr id="back" style="display: none;">	
				<td style="width: 20%"><label id="backLabel" class="nui-form-label">退回至： </label>
				<td colspan="2" style="width: 80%" >
				<input id="dacback" name="it.dactivityInstId"  class="nui-combobox"  textField="dactivityInstName" 
				    	valueField="dactivityInstId" emptyText="请选择..." value=""  required="true" allowInput="false" 
						showNullItem="true" nullItemText="请选择.."/>  
				</td>
			</tr>
		</table>
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
	    <a class="nui-button" onclick="submitEval" id="btn_submitEval">提交</a>
	   	<%--<a class="nui-button" iconCls="icon-save" onclick="updateEvalAdvice('1')">保存意见</a>--%>
	    <a class="nui-button" onclick="abortEval" id="btn_abortEval">撤销</a>
	</div>
</div>
<script type="text/javascript">
	
	nui.parse(document.getElementById("panel1"));
	var form = new nui.Form("#form1");
	var isAuth;
	var processDefName = 'com.bos.bps.csm.group_cust_rd_mccb';
	var processInstanceName = '客户管理-集团客户认定';
	var isGroup = false;
	var isMembers = false;
	var processInstId = "<%=request.getParameter("processInstId")%>";
	var partyId = "<%=request.getParameter("bizId") %>";
	function blur(){
		updateEvalAdvice('2');
	}
	
	//从待办列表中传过来
    var activityType=null;
    document.getElementById("person").style.display="none";
    document.getElementById("m_person").style.display="none";
    document.getElementById("back").style.display="none";
   	nui.get("btn_submitEval").setEnabled(false);
   	nui.get("btn_abortEval").hide();
   	
   	//特殊岗位人员选择控制标志，把当前活动ID转换成岗位ID进行判断
   	var postId = nui.get("it.activityDefId").getValue();
    	postId = "P1"+postId.substr(2,3);
   
    //监听审批结论控件
    var conclusion = nui.get("conclusion");
    conclusion.on("valuechanged", function (e) {
    	nui.get("btn_submitEval").setEnabled(false);
    	//触发审批结论时，增加透明遮罩
    	git.mask();
    	//退回/补充材料/预处理退回操作，初始化上一岗位信息
    	if("99"==this.getValue()||"997" == this.getValue()){
    		document.getElementById("person").style.display="none";
    		document.getElementById("m_person").style.display="none";
    		nui.get("it.opinion").setRequired(true);
    		initBack();
    	//补充材料
    	}else if("98"==this.getValue()){
    		document.getElementById("person").style.display="none";
    		document.getElementById("m_person").style.display="none";
		   	document.getElementById("back").style.display="none";
		   	nui.get("it.opinion").setRequired(true);
    		nui.get("btn_submitEval").setEnabled(true);
    	//否决操作	
    	}else if("999"==this.getValue()||"2"==this.getValue()){//全部隐藏
    		document.getElementById("person").style.display="none";
    		document.getElementById("m_person").style.display="none";
		   	document.getElementById("back").style.display="none";
		   	nui.get("btn_submitEval").setEnabled(true);
		   	nui.get("it.opinion").setRequired(true);
    	}else{
    		document.getElementById("back").style.display="none";
    		//nui.get("it.opinion").setRequired(false);
    		initSelect();
    	}
    	
    	//加载完成后，取消透明遮罩
    	git.unmask();
    });
    
    //执行流程参数初始化，如果是类似退回操作，则不执行
    function excuteFlowParaMonth(){
    
    	
    	//获取审批状态
		var conclusion = nui.get("conclusion").getValue();
		if("99"!=conclusion && "98"!=conclusion && "997"!=conclusion){
		
			//清空人员选项内容
			nui.get("selectuser").setValue("");
			nui.get("selectuser").setText("");
			nui.get("m_selectuser").setValue("");
			nui.get("m_selectuser").setText("");
			initSelect();
		}
    }
    
    function initConclusion(){
    
    	var conclusion = "<%=request.getAttribute("conclusion")%>";
    	
    	if(null==conclusion || ''==conclusion || 'null'==conclusion){
    	
    		document.getElementById("t_conclusion").style.display="none";
    	}
    
    }
    
    //初始化，是否显示人员/角色选择框
    function initSelect() {
     	nui.get("conclusion").setEnabled(false);
    	var o = form.getData();
        var json = nui.encode(o);
        var selectType = nui.get("it.selectType").getValue();
        //alert(json);
    	$.ajax({
        url: "com.bos.bps.op.WorkFlowManager.getNextNodeExtendAttibute.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text&&text.map){
        		if(text.map.errDesc){
        		
        			//nui.alert(text.map.errDesc);
        			nui.get("btn_submitEval").setEnabled(true);
        			isAuth = text.map.errDesc;
        		}else{
        		
        			activityType = text.map.type;
	        		if (text.map.type=='finish') {
	        			//无需选人
	        			document.getElementById("person").style.display="none";
	        			document.getElementById("m_person").style.display="none";
	        		} else {
	        			window['bps_attr_map'] = text.map;
	        			if(null !=text.map.participants && ''!=text.map.participants &&'null'!=text.map.participants){
	        				var participants = text.map.participants;
	        				var arrys = participants.split("_");
	        				nui.get("it.nextUsersNum").setValue(arrys[0]);
		                    nui.get("it.nextUsersName").setValue(arrys[1]);
		                    nui.get("it.nextOrgCd").setValue(arrys[2]);
		                    nui.get("it.nextOrgName").setValue(arrys[3]);
	        			
	        				displayPersonWin(selectType,false);
	        			}else{
	    					displayPersonWin(selectType,true);
	    					//清空人员选项内容
	    					nui.get("selectuser").setValue("");
	    					nui.get("selectuser").setText("");
	    					nui.get("m_selectuser").setValue("");
	    					nui.get("m_selectuser").setText("");
	    					var conclusion =nui.get("conclusion").getValue();
	    					//当选择方式为自动选择或者手工选择并且结论不为二次尽调且不为业务受理岗
	    					if('1'==selectType ||('3'==selectType && '7'!=conclusion && "P1216"!=postId)){
		    					nui.get("it.nextOrgCd").setValue(text.map.nextOrgCd);
		                        nui.get("it.nextOrgName").setValue(text.map.nextOrgName);
		                        nui.get("it.nextPostCd").setValue(text.map.nextPostCd);
		                        nui.get("it.nextPostName").setValue(text.map.nextPostName);
		                        nui.get("it.nextUsersNum").setValue(text.map.nextUserId);
		                        nui.get("it.nextUsersName").setValue(text.map.nextUserName);
	    					}
	        			}
	        		}
	        		nui.get("btn_submitEval").setEnabled(true);
        		
        		}
        	}else{
        	
        		nui.alert("初始化下一岗位人员发生异常，请联系管理员！");
        		nui.get("btn_submitEval").setEnabled(false);
        	}
        	nui.get("conclusion").setEnabled(true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
        });
            
    }
    
    //隐藏/显示选择人员窗口
    function displayPersonWin(selectType,isShow){
    
    	//显示
    	if(true==isShow){
    		//暂时改为1，不判断结论
    		if("3"==selectType){//选择类型为3：表示手工选择人员，2：手工选择岗。1：默认处理
    		
    			//获取审批结论
    			var conclusion = nui.get("conclusion").getValue();
				//一般机构，只有结论为“二次尽调”或者业务受理岗，才展示组件。
    			if("7"==conclusion||"P1216"==postId){
	    			document.getElementById("person").style.display="";
				}else{
					document.getElementById("person").style.display="none";
				}
    		}else if("2"==selectType){
    		
    			document.getElementById("m_person").style.display="";
    		}
    			
    	}else{//隐藏
    	
    		document.getElementById("person").style.display="none";
    		document.getElementById("m_person").style.display="none";
    	}
    
    }
    
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/bps/mywork/select_bps_user.jsp?map="+escape(nui.encode(window['bps_attr_map'])),
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
                    	//nui.alert(nui.encode(data));
                    	if('1'==<%=orgdegree %>){
                    	
	                    	btnEdit.setValue(data.USERID);
	                        btnEdit.setText(data.OPERATORNAME);
	                        nui.get("it.nextOrgCd").setValue(data.ORGCODE);
	                        nui.get("it.nextOrgName").setValue(data.ORGNAME);
	                        nui.get("it.nextPostCd").setValue(data.POSICODE);
	                        nui.get("it.nextPostName").setValue(data.POSINAME);
	                        nui.get("it.nextUsersName").setValue(data.OPERATORNAME);
	                        nui.get("it.nextUsersNum").setValue(data.USERID);
                    	}else{
                    	
                    		var nextUsersNum="";
	                        var nextUsersName="";
	                        for(var i=0;i<data.length;i++){
	                        	if(data[i].nodeType == "OrgEmployee"){
	                        		nui.get("it.nextOrgCd").setValue(data[i].ORGCODE);
			                        nui.get("it.nextOrgName").setValue(data[i].ORGNAME);
			                        nui.get("it.nextPostCd").setValue(data[i].POSICODE);
			                        nui.get("it.nextPostName").setValue(data[i].POSINAME);
	                        		if(i==data.length-1){
	                        	
		                        		nextUsersNum+=data[i].USERID;
		                        		nextUsersName+=data[i].OPERATORNAME;
		                        	}else{
		                        	
		                        		nextUsersNum+=data[i].USERID+",";
		                        		nextUsersName+=data[i].OPERATORNAME+",";
		                        	}
	                        	}
	                        	
	                        }
	                        
	                        btnEdit.setValue(nextUsersNum);
	                        btnEdit.setText(nextUsersName);
	                        nui.get("it.nextUsersNum").setValue(nextUsersNum);
	                        nui.get("it.nextUsersName").setValue(nextUsersName);
                    	}
                    }
                }
            }
        });
    }
    
    //手工选择岗位时，弹出岗位，人员选择列表
    function selectPositionAndEmp(e){
    
    	var btnEdit = this;
        nui.open({
            url: nui.context + "/bps/mywork/select_bps_position.jsp?map="+escape(nui.encode(window['bps_attr_map'])),
            showMaxButton: false,
            title: "选择人员",
            width: 550,
            height: 350,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        nui.get("it.nextOrgCd").setValue(data[0].ORGCODE);
                        nui.get("it.nextOrgName").setValue(data[0].ORGNAME);
                        nui.get("it.nextPostCd").setValue(data[0].POSICODE);
                        nui.get("it.nextPostName").setValue(data[0].POSINAME);
                        //这里增加下一活动定义ID
                        nui.get("it.nextActivityDefId").setValue(data[0].activityDefId);
                        var nextUsersNum="";
                        var nextUsersName="";
                        for(var i=0;i<data.length;i++){
                        	if(i==data.length-1){
                        	
                        		nextUsersNum+=data[i].USERID;
                        		nextUsersName+=data[i].OPERATORNAME;
                        	}else{
                        	
                        		nextUsersNum+=data[i].USERID+",";
                        		nextUsersName+=data[i].OPERATORNAME+",";
                        	}
                        }
                        
                        btnEdit.setValue(nextUsersNum);
                        btnEdit.setText(nextUsersName);
                        nui.get("it.nextUsersNum").setValue(nextUsersNum);
                        nui.get("it.nextUsersName").setValue(nextUsersName);
					}
				}
            }
        });
    
    }
    //初始化，是否展示退回选择框
    function initBack() {
   		 nui.get("conclusion").setEnabled(false);
		var currentActInstID = <%=request.getAttribute("activityInstId")%>;
    	var processInstId =  <%=request.getAttribute("processInstId")%>;
        var json = nui.encode({"currentActInstID":currentActInstID,"processInstId":processInstId});//alert(json);
        $.ajax({
            url: "com.bos.bps.op.WorkFlowManager.getValidPreviousWorkInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(null != text.backinfo.errCode && '' != text.backinfo.errCode){
            		nui.alert(text.backinfo.errDesc); //失败时后台直接返回出错信息
            		nui.get("btn_submitEval").setEnabled(false);
            	} else {
            		if (null==text.backinfo.list || ''==text.backinfo.list) {
            			//未获取到
            			 document.getElementById("back").style.display="none";
            			 var backSize = text.backinfo.backSize;
            			 if(backSize<=1){
            			 
            				 nui.get("btn_abortEval").show();
            			 }
            		} else {
            			//获取审批状态
    					var conclusion = nui.get("conclusion").getValue();
            			if("99"==conclusion ||"997" == conclusion){
	            			nui.get("dacback").setData(text.backinfo.list);
	            			document.getElementById("back").style.display="";
	            			nui.get("btn_submitEval").setEnabled(true);
	            			nui.get("conclusion").setEnabled(true);
            			}
            		}
            	}
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    //流程ID查询流程实例
    function getProcessInstance(){
    var json = nui.encode({"item":{"processInstId":processInstId},"sqlName":"com.bos.bps.dataset.bpmNamingSql.getProcessInstance"});
    	$.ajax({
			url:'com.bos.csm.pub.ibatis.getItem.biz.ext',
			type:'POST',
			data:json,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var data = text.items;
				var defName = nui.encode(data[0].PROCESSDEFNAME);
				var insName = nui.encode(data[0].PROCESSINSTANCENAME);
				if(nui.decode(defName) == processDefName ||nui.decode(insName) == processInstanceName){
					isGroup = true;
				}
			},error: function (jqXHR, textStatus, errorThrown){
	            nui.alert(jqXHR.responseText);
	        } 
		});
    
    }
    getProcessInstance();
    //根据parttyId查询集团成员数
    function getGroupMember(){
    var json = nui.encode({"item":{"partyId":partyId},"sqlName":"com.bos.bps.dataset.bpmNamingSql.getGroupMember"});
    	$.ajax({
			url:'com.bos.csm.pub.ibatis.getItem.biz.ext',
			type:'POST',
			data:json,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var data = text.items;
				if(nui.encode(data.length)>1){
					isMembers = true;
				}
			},error: function (jqXHR, textStatus, errorThrown){
	            nui.alert(jqXHR.responseText);
	        } 
		});
    }
    getGroupMember();
    //提交事件
    function submitEval() {
   		var o = form.getData();
   		form.validate();
        if (form.isValid()==false){
        	nui.alert("请将信息填写完整");
        	return;
        } 
		var productType = "<%=request.getParameter("productType") %>";
		var amountDetailId = "<%=request.getParameter("amountDetailId") %>";//contractId  contractNum relationType partyId
		var contractId = "<%=request.getParameter("contractId") %>";
		var contractNum = "<%=request.getParameter("contractNum") %>";
		var relationType = "<%=request.getParameter("relationType") %>";
		var partyId = "<%=request.getParameter("partyId") %>";
		var groupType = "<%=request.getParameter("groupType") %>";
		var doUrl1 = "<%=request.getAttribute("doUrl")%>";

        
		//判断集团客户成员数
 		if(isGroup && doUrl1=='null'){
        	if(!isMembers){
        		alert("集团客户的成员不得少于2个");
        		return;
        	}
        } 
        	  //集团与联保客服的新增 groupType =511  partyId
		   	if(relationType=="1"){
	   	    var jsonyj=nui.encode({'partyId':partyId,'groupType':groupType});
	   	   $.ajax({
            url: "com.bos.csm.company.company.saveCompanyEcifyj.biz.ext",//saveCompanyEcif
            type: 'POST',
            data: jsonyj,
            cache: false,
            contentType:'text/json',
            success: function (text) {
       			if("AAAAAAA"!=text.map.msg){
      			 nui.alert("调ECIF接口失败："+text.map.msgg);
      			 	return;
      			}else{
	   				dataCheck();
	   	    	}
	   	    },
	   	    error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
   		 }); 
   	 	}else{
	   	    dataCheck();
        }
    }
    
    function dataCheck(){
   		var o = form.getData();
        var applyId = "<%=request.getParameter("bizId") %>";
    	//业务数据校验
		var processInstId = "<%=request.getAttribute("processInstId")%>";
		var doUrl = "<%=request.getAttribute("doUrl")%>";
		var activityDefId = "<%=request.getAttribute("activityDefId")%>";

		var json=nui.encode({'processInstId':processInstId,'doUrl':doUrl,'conclusion':o.it.conclusion,'activityDefId':activityDefId,'applyId':applyId});
    	$.ajax({
            url: "com.bos.pub.commCheckMethod.sysParamCheck.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            success: function (text) {
            		errorNum = text.errorNum;
	            	if(text!=null&&text.errorNum=="2"&&text.errorCode!=null&&text.errorContent!=null){
	            		nui.alert("提示内容："+text.errorContent);
	            		
	            	}else if(text.errorNum=="3"){
						nui.confirm(text.errorContent,"确认",function(action){
				            if(action=="ok"){
				         	  submit(o);
				            }
			        	});
					}else{
						submit(o);
					}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    //提交方法
    function submit(o){
    
    	//校验是否找到授权
        if(null!=isAuth && ''!=isAuth){
        	nui.alert(isAuth);
        	return;
        }
        //校验是否选择人员或者获取到人员
        var nextUsersNum = nui.get("it.nextUsersNum").getValue();
        if(null == o.it.conclusion || '' == o.it.conclusion){
        
        	o.it.conclusion="1";
        }
        //除结束、下一岗有人、退回、不同意、补充材料外，只要没找到人，都提示
        if("finish"!=activityType && !nextUsersNum && '2'!=o.it.conclusion && '99'!=o.it.conclusion && '98'!=o.it.conclusion){
        	nui.alert("未获取到下一岗审批人员，请联系管理员给人员配置岗位或者修改其审批权限！");
        	return;
        }
	   nui.get("btn_submitEval").setEnabled(false);
   
       //o.reldata=o.reldata||{};
       if (!!window['bps_attr_map'] && window['bps_attr_map']['WFActivityDefineParticipantType']=='relevantdata') {
       		o.it.userVariable=window['bps_attr_map']['WFActivityDefineId'];
       		if (o.it.userVariable == 'null')
       			o.it.userVariable=null;
       		else {
       			o.it.userVariable='next_'+o.it.userVariable;
       		}
       }
       	o.it.activityType=activityType;	
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.bps.op.WorkFlowManager.submitProcessToNext.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            success: function (text) {
            	if(null != text.result && '' != text.result){
            		
        			if('1' == text.result){
        			
        				var processInstId = "<%=request.getAttribute("processInstId")%>";
        				var json=nui.encode({"processInstId":processInstId});
						$.ajax({  
					        url: "com.bos.aft.conLoanChange.toProUpdateJJ.biz.ext",
					        type: 'POST',
					        data: json,
					        contentType:'text/json',
					        cache: false,
					        async:false,
					        success: function (data) {
				            	if(data.flag == "0"){
				                	//return nui.alert("还款成功，但实时更新借据余额错误，请联系系统管理员手工更新！");
				                }
			
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            alert(jqXHR.responseText);
					            git.unmask();
					        }
				        });	
        				closeWindow("提交成功!");
        			}else{
        			
        				var message = text.message;
        				if(null != message && ''!=message){
        				
        					nui.alert("提交失败:"+message); //失败时后台直接返回出错信息
        				}else{
        					nui.alert("提交失败!"); //失败时后台直接返回出错信息
        				}
        				nui.get("btn_submitEval").setEnabled(true);
        			}
        			
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    //临时保存事件(暂不用）
    function updateEvalAdvice(flag) {

       var o = form.getData();
       var opinion1 = nui.get("it.opinion").getValue();
	   o.it.opinion = opinion1.replace(/[\r\n]/g,"");
        var json = nui.encode(o);//alert(json);
        $.ajax({
            url: "com.bos.bps.util.TbWfmWorkItemInstance.updateSingelWork.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text&&text.msg){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
            	} else {
					if('1'==flag){
						nui.alert("意见已保存！");
					}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    //撤销事件
    function abortEval() {
    
        var processInstId = '<%=request.getAttribute("processInstId")%>';
        var processDefName = '<%=request.getAttribute("processDefName")%>';
        var activityDefId = '<%=request.getAttribute("activityDefId")%>';
        var templateVersion = '<%=request.getAttribute("templateVersion")%>';
        var partyId = "<%=request.getParameter("partyId") %>";
	    var groupType = "<%=request.getParameter("groupType") %>";
        var relationType = "<%=request.getParameter("relationType") %>";
        	   	    if(relationType=="1"){
	   	    var jsonyj=nui.encode({'partyId':partyId,'groupType':groupType});
	   	    
	   	            $.ajax({
            url: "com.bos.csm.company.company.saveCompanyEcifyjDel.biz.ext",//saveCompanyEcif
            type: 'POST',
            data: jsonyj,
            cache: false,
            contentType:'text/json',
            success: function (text) {
      			if("AAAAAAA"!=text.map.msg){
      			 nui.alert(text.map.msgg);
      			 	return;
      			}else{
      			        nui.confirm("将删除该笔业务,是否确定撤销?","确认",function(action){
            if(action=="ok"){
         	 	nui.get("btn_abortEval").setEnabled(false);
         	 	var json = nui.encode({"processInstId":processInstId,"processDefName":processDefName,"activityDefId":activityDefId,"templateVersion":templateVersion});//alert(json);
		        $.ajax({
		            url: "com.bos.bps.op.WorkFlowManager.abortProcess.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	if(null != text.errCode && '' != text.errCode){
		            		nui.alert(text.errCode+":"+text.errDesc); //失败时后台直接返回出错信息
		        			nui.get("btn_abortEval").setEnabled(true);
		            	} else {
		            	 	closeWindow("撤销成功!");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
		        });
            }
    	});
      			
      			}
            },
            error: function (jqXHR, textStatus, errorThrown) {
              nui.alert(jqXHR.responseText);
            }
        });
	   	    }else{
        nui.confirm("将删除该笔业务,是否确定撤销?","确认",function(action){
            if(action=="ok"){
         	 	nui.get("btn_abortEval").setEnabled(false);
         	 	var json = nui.encode({"processInstId":processInstId,"processDefName":processDefName,"activityDefId":activityDefId,"templateVersion":templateVersion});//alert(json);
		        $.ajax({
		            url: "com.bos.bps.op.WorkFlowManager.abortProcess.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	if(null != text.errCode && '' != text.errCode){
		            		nui.alert(text.errCode+":"+text.errDesc); //失败时后台直接返回出错信息
		        			nui.get("btn_abortEval").setEnabled(true);
		            	} else {
		            	 	closeWindow("撤销成功!");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
		        });
            }
    	});
    	}
    }
    
    //判断是否存在父窗口
    function findParentWindow(window){
    	if(window.CloseOwnerWindow){
    		window.CloseOwnerWindow("submit"); 
    	}
    	if(window.parent && window.parent !=top){
    		findParentWindow(window.parent);
    	}else{
    		window.location.replace("<%=contextPath %>/csm/workdesk/mywork.jsp");
    	}
    }
    
    function closeWindow(str){
    	var isSrc = "<%=request.getAttribute("isSrc")%>";
   		if("2" == isSrc){
   			nui.alert(str,"提示",function(action){
   			var w=self.parent ? self.parent : self;
   				findParentWindow(w);
   			});
   		}else{
    		nui.alert(str+"点击确定返回待办工作列表","提示",function(action){
    			var w=self.parent ? self.parent : self;
    			w.parent.location.replace("<%=contextPath %>/csm/workdesk/mywork.jsp");
  	 		});
    	}
    }
    //加载意见
    $(document).ready(function(){
     
	var o = form.getData();
	        var json = nui.encode(o);//alert(json);
	        $.ajax({
	            url: "com.bos.bps.util.TbWfmWorkItemInstance.initSingelWork.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            var conclusion=text.outWorkItemInstance.conclusion;
	            //初始化页面数据
	            if(conclusion==null){
		           	 //有审批结论，但未选中，默认选中第一个选项
		           	 var conclusion = "<%=request.getAttribute("conclusion")%>";
		           	 if(null!=conclusion && ''!=conclusion && 'null'!=conclusion){
		           	 
			           	 var conclusions = nui.get("conclusion").getData();
			           	 nui.get("conclusion").setValue(conclusions[0].id);
		           	 }
		           	 initSelect();
		           	 initBack();
		        }else{
		           	setConclusion(conclusion);
		      	 	if("99"==conclusion||"997" == conclusion){
	            			initBack();
	            			nui.get("it.opinion").setRequired(true);
            		}else if(conclusion=="999"||"2"==conclusion){
            			 nui.get("btn_submitEval").setEnabled(true);
            			 nui.get("it.opinion").setRequired(true);
            		}else if(conclusion=="98"){
            			
            			nui.get("it.opinion").setRequired(true);
            			nui.get("btn_submitEval").setEnabled(true);
            		}else{
            			initSelect();
            			initBack();
            		}
		        }
  				  initConclusion();
  				  nui.get("it.opinion").setValue(text.outWorkItemInstance.opinion);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });

		});
	function setConclusion(conclusion){
		
		nui.get("conclusion").setValue(conclusion);
	}

</script>
</body>
</html>