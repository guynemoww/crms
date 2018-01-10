<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<html>
<!-- 
  - Author(s): yangzhou
  - Date: 2013-03-21 11:24:50
  - Description:
-->
<head>
<title>员工基本信息修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
<style type="text/css">
    fieldset
    {
        border:solid 1px #aaa;
    }        
    .hideFieldset
    {
        border-left:0;
        border-right:0;
        border-bottom:0;
    }
    .hideFieldset .fieldset-body
    {
        display:none;
    }
</style>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input class="nui-hidden" name="employee.orgid" id="employee.orgid"/>
	<input class="nui-hidden" name="employee.orglevel" id="employee.orglevel" />
	<input class="nui-hidden" name="employee.empid"  id="employee.empid"/>
	<input class="nui-hidden"  name="orgidTwo" id="orgidTwo"/>
	<input class="nui-hidden" name="orgid" id="orgid" />
	<input class="nui-hidden" name="user.operatorid" id="user.operatorid"/>
		
	<div class="nui-dynpanel" columns="4">
			<label for="empcode$text">用户编号：</label>
			<input id="employee.empcode" class="nui-textbox" name="employee.empcode" required="true" enabled="false" vtype="maxLength:30" />
			<label for="empname$text">用户名称：</label>
			<input id="employee.empname" class="nui-textbox" name="employee.empname" required="true" vtype="maxLength:50"/>
			
			<label for="empcode$text">身份证号码：</label>
			<input id="employee.cardno" class="nui-textbox" name="employee.cardno" required="true" vtype="maxLength:18" onvalidation="getBirth"/>
			<label for="empname$text">出生日期：</label>
			<input id="employee.birthdate" class="nui-textbox" name="employee.birthdate" required="true" vtype="maxLength:20" enabled="false"/>
			
			<label for="gender$text">性别：</label>
			<input id="employee.gender" name="employee.gender" data="data" required="true" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_GENDER" enabled="false"/>
			<label for="position$text">所在职位：</label>
			<input id="employee.position" name="employee.position"  data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="OM_POSITION" />
			<%--<label for="departmentId$text">所属部门：</label>
			<input id="employee.departmentId" name="employee.departmentId"  class="nui-textbox nui-form-input" required="true"/>--%>
			<label class="nui-form-label">所属机构行号：</label>
			<input id="employee.orgcode" name="employee.orgcode"  allowInput="false" class="nui-textbox" enabled="false" required="true"/>
			<label class="nui-form-label">所属机构名称：</label>
			<input id="employee.orgname" name="employee.orgname"  allowInput="false" class="nui-textbox" enabled="false" required="true"/>
			<label for="useridmap$text">动态口令：</label>
			<input id="user.useridmap" class="nui-textbox" name="user.useridmap" required="false" vtype="maxLength:50"/>
			<label for="dynamicswitch$text">口令开关：</label>
			<input id="user.dynamicswitch" name="user.dynamicswitch"  class="nui-combobox" textField="text" valueField="id" emptyText="--请选择--"/>
			
			<label for="empstatus$text">用户状态：</label>
			<input id="employee.empstatus" name="employee.empstatus" data="data"  onvaluechanged="setOperStatus" required="true" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_EMPSTATUS" />
			<label for="status$text">使用状态：</label>
			<input id="user.status" name="user.status" data="data" required="true" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_OPERSTATUS" />
			<label for="education$text">学历：</label>
			<input id="employee.education" name="employee.education"  data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="OM_EDUCATION" />
			<label for="otel$text">办公电话：</label>
			<input id="employee.otel" name="employee.otel" class="nui-textbox" vtype="maxLength:12" required="true"/>
			<label for="mobileno$text">移动电话：</label>
			<input id="employee.mobileno" name="employee.mobileno" class="nui-textbox" vtype="int;maxLength:11" required="true"/>
			<label for="msn$text">其他联系方式：</label>
			<input id="employee.msn" name="employee.msn" class="nui-textbox" vtype="maxLength:16" required="true"/>
			<label for="degree$text">职业资格类型：</label>
			<input id="degree" name="employee.degree" valueField="dictID" textField="dictName" class="nui-checkboxlist"/>
			<label for="intotradedate$text">登记日期：</label>
			<input id="employee.intotradedate" name="employee.intotradedate" class="nui-datepicker"  readonly="readonly"/>
			<label for="departmentId$text">独立审批官级别：</label>
			<input id="employee.emplevel" name="employee.emplevel"  class="nui-combobox" textField="text" valueField="id"  emptyText="--请选择--"/>
			<label for="remark$text">备注：</label>
			<input id="employee.remark" name="employee.remark"  class="nui-TextArea" vtype="maxLength:512"/>
			
	</div>
</div>
<div id="form3" style="display:none">	
	<fieldset border="0">
		<input class="nui-hidden" name="user.password" />
        <legend><label><input type="checkbox" checked="checked" id="userRefCheckbox" onclick="toggleFieldSet(this, 'form3')" hideFocus/>&nbsp;用户信息</label></legend>
        <div class="fieldset-body">
            <table style="width:100%;table-layout:fixed;" class="nui-form-table" >
				<tr>
					<td class="nui-form-label"><label for="userId$text">用户登录名：</label></td>
					<td><input id="userId" class="nui-textbox" name="user.userid" required="true" vtype="maxLength:30"/></td>
					<td class="nui-form-label"><label for="userstatus$text">用户状态：</label></td>
					<td><input id="userstatus" name="user.status" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_USERSTATUS" value="1"/></td>
				</tr>
				<tr class="odd">
					<td class="nui-form-label"><label for="authmode$text">认证模式：</label></td>
					<td><input id="authmode" name="user.authmode" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_AUTHMODE" value="local"/></td>
					<td class="nui-form-label"><label for="invaldate$text">密码失效日期：</label></td>
					<td><input id="invaldate" class="nui-datepicker" name="user.invaldate" /></td>
				</tr>	
				<tr>
					<td class="nui-form-label"><label for="startdate$text">有效开始时间：</label></td>
					<td><input id="startdate" name="user.startdate" class="nui-datepicker" /></td>
					<td class="nui-form-label"><label for="enddate$text">有效截止时间：</label></td>
					<td><input id="enddate" name="user.enddate" class="nui-datepicker" onvalidation="onEnddateValidation" />
					</td>
				<tr class="odd">
					<td class="nui-form-label"><label for="menutype$text">菜单风格：</label></td>
					<td><input id="menutype" name="user.menutype" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_SKINLAYOUT" value="0"/></td>
				</tr>	
			</table>
        </div>
	</fieldset>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right; " 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-save" onclick="selectOrg" id="updateOrg">变更所属机构</a>
    <a class="nui-button" iconCls="icon-save" onclick="update" id="btnUpdate">保存</a>
 <%--   <a class="nui-button" iconCls="icon-reload" onclick="requestPSystem" id="btnsyn">同步</a>
    
    <a class="nui-button" id="resetBtn_01"  iconCls="icon-reset" onclick="resetForm">同步</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="display:none;" onclick="cancel">取消22</a>--%>
</div>
<script type="text/javascript">
	 nui.parse();
	
	 (function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
		}
	 })();

	var form = new nui.Form("form1");
	var form3 = new nui.Form("#form3");
 	if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("updateOrg").hide();
			nui.get("btnUpdate").hide();
		//	nui.get("btnsyn").hide();
	}
	
	
	var emplevels =[
		{"id":"","text":"--请选择--"},
		{"id":"1","text":"一级独立审批官"},
		{"id":"2","text":"二级独立审批官"},
		{"id":"3","text":"三级独立审批官"},
		{"id":"4","text":"四级独立审批官"},
		{"id":"5","text":"五级独立审批官"},
		{"id":"6","text":"六级独立审批官"},
		{"id":"7","text":"七级独立审批官"},
		{"id":"8","text":"八级独立审批官"}
		];			
	nui.get("employee.emplevel").setData(emplevels);
	
	nui.get("degree").setData(git.getDictDataFilter("XD_GGCD6008","1,2,3,4,5,6"));
	
	var cswitch =[
		{"id":"0","text":"否"},
		{"id":"1","text":"是"}
		];	
	nui.get("user.dynamicswitch").setData(cswitch);
	nui.get("user.dynamicswitch").setValue("否");
	
	//通过输入的身份证号码 获取人员的出生日期和性别
	function getBirth(e){
		var cardno = nui.get("employee.cardno").getValue();
		var validateMsg = CsmValidateobj.validateIDCard(cardno,null);
		if(validateMsg != null){
			e.isValid = false;
			e.errorText = validateMsg;
			return;
		}else{
			if(cardno.length == 18){
				var str = cardno.substring(6,10)+'-'+cardno.substring(10,12)+'-'+cardno.substring(12,14);
				nui.get("employee.birthdate").setValue(str);
				var gender = cardno.substring(16,17);
				if(gender%2 == 1){//女为偶
					nui.get("employee.gender").setValue('m');
				}else{
					nui.get("employee.gender").setValue('f');
				}
			}if(cardno.length == 15){
				var str = cardno.substring(6,8)+'-'+cardno.substring(8,10)+'-'+cardno.substring(10,12);
				nui.get("employee.birthdate").setValue(str);
				var gender = cardno.substring(14,15);
				if(gender%2 == 1){//女为偶
					nui.get("employee.gender").setValue('m');
				}else{
					nui.get("employee.gender").setValue('f');
				}
			}
		}
	}

	
    function update() {
    	var data = {};
       	//校验
		form.validate();
        if (form.isValid()==false) return;
        if($("#userRefCheckbox")[0].checked){
        	form3.validate();
        	if (form3.isValid()==false) return;
        	//提交所有数据
        	data = form.getData(true,true);
        }else{
        	//只提交emp的数据
        	var form1Data = form.getData(true,true);
        	data = form1Data;
        }
        var orgidTwo=nui.get("orgidTwo").getValue();
        var orgid=nui.get("orgid").getValue();
        if(orgidTwo!=orgid){
			    var json = nui.encode(data);
			        $.ajax({
			            url: "com.bos.utp.org.employee.updateEmployee.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	var response = text.response;
			            	if(response){
				            	if(response.flag){
				            		if(window.isCloseWindow){
					            		//CloseWindow("ok");
					            		//alert('修改成功');
					            		
					            		
					            		//add by shangmf Begin:操作成功候调用webservice更新押品，不管是否成功不影响信贷更新
                     					nui.ajax({
                     						url: "com.bos.utp.org.collService.CollServiceImplServiceService.updCollOperByWebService.biz.ext",
                    	 					type: "post",
                     						data: json, 
                     						cache: false,
                     						contentType: 'text/json',
                     						success: function (text) {
                     						if(text.retCode=="success"){
                     							alert("修改成功，同步押品系统操作成功!");
                     						}else{
                       							alert("修改成功，同步押品系统操作失败!");
                       						}
			            					search();
                     					},
                    						error: function () {
                     						}
                 						});
                     					//add by shangmf End
					            		
					            		
					            		return;
				            		}
				            		window['formData']=data;
				            		nui.alert(response.message);
				            		//重新载入
				            		var data = form1.getData();
				            		SetData({empid:data.employee.empid});
				            		//刷新其父节点
				            		if(window.parent){
				            			window.parent.refreshParentNode();
				            		}
				            		return;
				            	}else{
				            		nui.alert(response.message);
				            		return;
				            	}
			            	}
			            	nui.alert("修改失败，请联系管理员");
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                alert(jqXHR.responseText);
			                //CloseWindow();
			            }
			        });
        }else{
                    var json = nui.encode(data);
			        $.ajax({
			            url: "com.bos.utp.org.employee.updateEmployee.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	var response = text.response;
			            	if(response){
				            	if(response.flag){
				            		if(window.isCloseWindow){
					            		//CloseWindow("ok");
					            		//alert('修改成功');
					            		
					            		
					            		//add by shangmf Begin:操作成功候调用webservice更新押品，不管是否成功不影响信贷更新
                     					nui.ajax({
                     						url: "com.bos.utp.org.collService.CollServiceImplServiceService.updCollOperByWebService.biz.ext",
                    	 					type: "post",
                     						data: json, 
                     						cache: false,
                     						contentType: 'text/json',
                     						success: function (text) {
                     						if(text.retCode=="success"){
                     							alert("修改成功，同步押品系统操作成功!");
                     						}else{
                       							alert("修改成功，同步押品系统操作失败!");
                       						}
			            					search();
                     					},
                    						error: function () {
                     						}
                 						});
                     					//add by shangmf End
                     					
					            		
					            		return;
				            		}
				            		window['formData']=data;
				            		nui.alert(response.message);
				            		//重新载入
				            		var data = form1.getData();
				            		SetData({empid:data.employee.empid});
				            		//刷新其父节点
				            		if(window.parent){
				            			window.parent.refreshParentNode();
				            		}
				            		return;
				            	}else{
				            		nui.alert(response.message);
				            		return;
				            	}
			            	}
			            	nui.alert("修改失败，请联系管理员");
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                alert(jqXHR.responseText);
			                //CloseWindow();
			            }
			        });
        }
        
      
    }

	var empid = '<%=request.getParameter("empid") %>';
	var action = '<%=request.getParameter("action") %>';
	empid = (empid == 'null' ? '' : empid);
	action = (action == 'null' ? '' : action);
	if (empid && action)
		SetData({});
    ////////////////////
    //标准方法接口定义
    function SetData(data) {
    	data = data || {};
    	if(data.action=="update" || action == "update"){
    		window.isCloseWindow = true;
    		//showCancelBtn();
    	}
        //跨页面传递的数据对象，克隆后才可以安全使用
        data = nui.clone(data);
        if (!data.empid) {
        	data.empid=empid;
        }
		var json = nui.encode({template:data});
        $.ajax({
            url: "com.bos.utp.org.employee.getEmployee.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            async: false,//非异步，即同步
            success: function (data) {
                var o = nui.decode(data);
                form.setData(o);
                if (o.employee.orglevelLessThanCurrentUser) {
                	nui.get("btnUpdate").disable();
                }
                window['formData1'] = o;
                loadUser(o.employee);
            }
        });
    }
    
    function loadUser(data){
    	 if(data.operatorid){
        	var userData = nui.encode({template:{operatorId:data.operatorid}});
	        $.ajax({
	        	url:"com.bos.utp.rights.UserManager.getCapUser.biz.ext",
	        	data:userData,
	        	cache:false,
	        	type:'POST',
	        	contentType:'text/json',
	        	success:function(ret){
	        		var o = nui.decode(ret);
	        		form3.setData(o);
	        		nui.get("user.status").setValue(o.user.status);
	        		nui.get("user.operatorid").setValue(o.user.operatorid);
	        		nui.get("user.useridmap").setValue(o.user.useridmap);
	        		nui.get("user.dynamicswitch").setValue(o.user.dynamicswitch);
	        		if ("<%=request.getParameter("view") %>"=="1") {
	        		
		        		nui.get("user.status").setEnabled(false);
	        		}	
	        		window['formData3'] = o;
	        	}
	        });
        }else{
           var checkbox = $("#userRefCheckbox")[0];
	       checkbox.checked = false;
	       toggleFieldSet(checkbox,'form3');
        }
    }
    
    
    //设置员工使用状态，如果用户状态为非在职，则使用现状为“停用”
	function setOperStatus(e){
		if(null != e.value && ''!=e.value){
			if('0'!=e.value){
				nui.get("user.status").setValue("stopuse");
				nui.get("user.status").setEnabled(false);
			}else{
			
				nui.get("user.status").setValue("");
				nui.get("user.status").setEnabled(true);
			}
		}
	
	}

    function CloseWindow(action) {
        if (action == "close" && form.isChanged()) {
            if (confirm("数据被修改了，是否先保存？")) {
                return false;
            }
        }
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();            
    }
    
    function cancel(e) {
        CloseWindow("cancel");
    }
	
	function resetForm(){
		//form.reset();
		var data = window['formData'];
		var data1 = window['formData1'];
		var data3 = window['formData3'];
		if(data1){
			form1.setData(data1);
			nui.get("specialty").setText(data1.rolename);
            nui.get("orgid").setText(data1.employee.orgname);
            if (o.employee.orglevelLessThanCurrentUser) {
            	nui.get("btnUpdate").disable();
            }
		}
		if(data3){
			form3.setData(data3);
		}
		if(data){
			form.setData(data);
		}
		if(!data1 && !data3){
			form.reset();
		}
	}
	
	//校验日期
	function onOutdateValidation(e){
       	var o = form.getData();
       	var org = o.employee || {};
		if(org.outdate && org.indate && org.outdate<=org.indate){
			e.errorText = "离职日期必须大于入职日期";
			e.isValid = false;
		}
	}
	
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.user || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
    var bootPath = "<%=request.getContextPath() %>";
    
    //选择机构
    function selectOrg() {
         nui.open({
				url : nui.context + "/pub/sys/select_org_tree.jsp?clear=0&searchMode=legorg",
				showMaxButton : true,
				title : "选择机构",
				width : 450,
				height : 650,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.GetData();
						debugger;
						nui.get("employee.orgid").setValue(data.orgid);
						nui.get("employee.orglevel").setValue(data.orglevel);
						nui.get("employee.orgcode").setValue(data.orgcode);
						nui.get("employee.orgname").setValue(data.orgname);
					}
				}
			});            
    }
    
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
    
    function showCancelBtn(){
    	//$("#cancelBtn_01").show();
    	$("#resetBtn_01").hide();
    }
    
  	//展开，折叠
    function toggleFieldSet(ck, id) {
        var dom = document.getElementById(id);
        if(ck.checked){
        	dom.className = "";//展开
        }else{
        	dom.className = "hideFieldset";
        }
    }
 function selectRole(e) {
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_managed_role.jsp?level=" + nui.get("employee.orglevel").value,
            showMaxButton: false,
            title: "选择人员在其归属机构的角色",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: window['parentNode'],
                   ids:ids,
                   texts:texts
                };
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }	
    // 添加员工所属部门
    function selectDepartment(e){
    		 var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/department/department_list.jsp?orgId="+nui.get("orgid").getValue(),
            showMaxButton: false,
            title: "选择机构",
            width: 700,
            height: 450,
            ondestroy: function (action) {      
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.departmentId);
                        btnEdit.setText(data.departmentName);
                    }
                }
            }
        });       
    }
    
    
    function selectCustManeger(e) {
        nui.open({
            url: nui.context + "/utp/org/employee/queryCust.jsp",
            showMaxButton: true,
            title: "选择客户经理",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                        nui.get("departmentId").setValue(data.userId);;
                    	
                    }
                }
            }
        });   
        }
        
    //调用人力资源接口,同步数据
	function requestPSystem(){
	
		var empcode = nui.get("employee.empcode").getValue();
		if(null == empcode || '' == empcode){
			return;
		}
		var json = nui.encode({"empcode":empcode});
		$.ajax({
            url: "com.bos.utp.org.employee.callPersonSystemEmp.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if('00000000000000' == text.retcode){
	            	nui.get("employee.empname").setValue(text.employee.empname);
	            	nui.get("employee.empstatus").setValue(text.employee.empstatus);
	            	nui.get("employee.gender").setValue(text.employee.gender);
	            	nui.get("employee.otel").setValue(text.employee.otel);
	            	nui.get("employee.msn").setValue(text.employee.msn);	
	            	nui.get("employee.mobileno").setValue(text.employee.mobileno);	
            	}else{
            		nui.alert(text.returnMsg);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
		
	}    
</script>

</body>
</html>