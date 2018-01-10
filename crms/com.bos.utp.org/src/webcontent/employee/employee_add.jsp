<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>员工添加</title>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<input  id="level" name="level" class="nui-hidden"  />
		<input  id="parentOrgid" class="nui-hidden" name="org.orgid" />
		<div class="nui-dynpanel" columns="4">
			<label for="empcode$text">用户编号：</label>
			<input id="empcode" class="nui-textbox" name="employee.empcode" required="true" vtype="maxLength:30" onblur="check()"/>
			<label for="empname$text">用户名称：</label>
			<input id="empname" class="nui-textbox" name="employee.empname" required="true" vtype="maxLength:50"/>
			
			<label for="empcode$text">身份证号码：</label>
			<input id="empcardno" class="nui-textbox" name="employee.cardno" required="true" vtype="maxLength:18" onvalidation="getBirth"/>
			<label for="empname$text">出生日期：</label>
			<input id="empbirthdate" class="nui-textbox" name="employee.birthdate" required="true" vtype="maxLength:20" enabled="false"/>
			
			<label for="gender$text">性别：</label>
			<input id="gender" name="employee.gender" data="data" emptyText=""  required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_GENDER" enabled="false"/>
			<%--<label class="nui-form-label">所属部门：</label>
			<input id="employee.departmentId" name="employee.departmentId"  required="true" class="nui-textbox nui-form-input"/>
			--%>
			<label for="position$text">所在职位：</label>
			<input id="position" name="employee.position" data="data"  emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="OM_POSITION" />
			<label class="nui-form-label">所属机构行号：</label>
			<input id="orgcode" name="org.orgcode"  allowInput="false" class="nui-textbox" enabled="false"  required="true"/>
			<label class="nui-form-label">所属机构名称：</label>
			<input id="orgname" name="org.orgname"  allowInput="false" class="nui-textbox" enabled="false"  required="true"/>
			<label for="useridmap$text">动态口令：</label>
			<input id="user.useridmap" class="nui-textbox" name="user.useridmap" required="false" vtype="maxLength:50"/>
			<label for="dynamicswitch$text">口令开关：</label>
			<input id="user.dynamicswitch" name="user.dynamicswitch"  class="nui-combobox" textField="text" valueField="id" emptyText="--请选择--"/>
			
			<label for="empstatus$text">用户状态：</label>
			<input id="empstatus" name="employee.empstatus" data="data"  onvaluechanged="setOperStatus"  required="true"  emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_EMPSTATUS" />
			<label for="status$text">使用状态：</label>
			<input id="status" name="user.status" data="data"  required="true" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_OPERSTATUS" />
			<label for="education$text">学历：</label>
			<input id="education" name="employee.education" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="OM_EDUCATION" />
			<label for="otel$text">办公电话：</label>
			<input id="otel" name="employee.otel" class="nui-textbox" vtype="maxLength:12"  required="true"/>
			<label for="mobileno$text">移动电话：</label>
			<input id="mobileno" name="employee.mobileno" class="nui-textbox" vtype="int;maxLength:11"  required="true"/>
			<label for="msn$text">其他联系方式：</label>
			<input id="msn" name="employee.msn" class="nui-textbox" vtype="maxLength:16"  required="true"/>
			<label for="degree$text">职业资格类型：</label>
			<input id="degree" name="employee.degree" valueField="dictID" textField="dictName" class="nui-checkboxlist"/>
			<label for="intotradedate$text">登记日期：</label>
			<input id="intotradedate" name="employee.intotradedate" class="nui-datepicker" value="<%=GitUtil.getBusiDateStr()%>" readonly="readonly" />
			<label for="departmentId$text">独立审批官级别：</label>
			<input id="employee.emplevel" name="employee.emplevel"  class="nui-combobox" textField="text" valueField="id"  emptyText="--请选择--"/>
			<label for="remark$text">备注：</label>
			<input id="employee.remark" name="employee.remark" class="nui-TextArea" vtype="maxLength:512"/>
	</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"   iconCls="icon-save" onclick="add">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="cancel">取消</a>
</div>

<script type="text/javascript">
	nui.parse();
	
	$(function(){
		$(".mini-textbox-input").first().focus();
	});
	
	var form = new nui.Form("form1");
	//var form2 = new nui.Form("#form2");
	//var form3 = new nui.Form("#form3");
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
	
	var tab = nui.get("tab1");
	 // 校验机构编码是否存在
	function check(){
	var json1 = nui.encode({"map":{"empcode":nui.get("empcode").getValue()}});
         $.ajax({
            url: "com.bos.utp.org.employee.checkEmployeeOnly.biz.ext",
            type: 'POST',
            data: json1,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		alert(text.msg);
            		nui.get("empcode").setValue("");
            		return;
            	}else{
            		//requestPSystem(nui.get("empcode").getValue());
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
	}
	
	//调用人力资源接口
	function requestPSystem(empcode){
	
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
	            	nui.get("empname").setValue(text.employee.empname);
	            	nui.get("empstatus").setValue(text.employee.empstatus);
	            	nui.get("gender").setValue(text.employee.gender);
	            	nui.get("otel").setValue(text.employee.otel);
	            	nui.get("msn").setValue(text.employee.msn);	
	            	nui.get("mobileno").setValue(text.employee.mobileno);	
            	}else{
            		if('40018009999999'==text.retcode){
            		
            			nui.alert(text.returnMsg+",请先到人力资源系统增加人员！");
            			nui.get("empcode").setValue("");
            		}else{
            		
            			nui.alert(text.returnMsg);
            			nui.get("empcode").setValue("");
            		}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
		
	}
	
	//设置员工使用状态，如果用户状态为非在职，则使用现状为“停用”
	function setOperStatus(e){
		if(null != e.value && ''!=e.value){
			if('0'!=e.value){
				nui.get("status").setValue("stopuse");
				nui.get("status").setEnabled(false);
			}else{
			
				nui.get("status").setValue("");
				nui.get("status").setEnabled(true);
			}
		}
	
	}
	//通过输入的身份证号码 获取人员的出生日期和性别
	function getBirth(e){
		var cardno = nui.get("empcardno").getValue();
		var validateMsg = CsmValidateobj.validateIDCard(cardno,null);
		if(validateMsg != null){
			e.isValid = false;
			e.errorText = validateMsg;
			return;
		}else{
			if(cardno.length == 18){
				var str = cardno.substring(6,10)+'-'+cardno.substring(10,12)+'-'+cardno.substring(12,14);
				nui.get("empbirthdate").setValue(str);
				var gender = cardno.substring(16,17);
				if(gender%2 == 1){//女为偶
					nui.get("gender").setValue('m');
				}else{
					nui.get("gender").setValue('f');
				}
			}if(cardno.length == 15){
				var str = cardno.substring(6,8)+'-'+cardno.substring(8,10)+'-'+cardno.substring(10,12);
				nui.get("empbirthdate").setValue(str);
				var gender = cardno.substring(14,15);
				if(gender%2 == 1){//女为偶
					nui.get("gender").setValue('m');
				}else{
					nui.get("gender").setValue('f');
				}
			}
		}
	}
	//增加人员
	function add(){
		
		var data = {};
       	//校验
		form.validate();
        if (form.isValid()==false){
        	//tab.activeTab(tab.getTab(0));
        	return;
        }
/*		form2.validate();
        if (form2.isValid()==false) {
        	tab.activeTab(tab.getTab(1));
        	return;
        }
        if($("#userRefCheckbox")[0].checked){
        	//form3.validate();
        	//if (form3.isValid()==false) return;
        	//提交所有数据
        	data = form.getData(true,true);
        }else{
            //只提交emp的数据
        	var form1Data = form1.getData(true,true);
        	var form2Data = form2.getData(true,true);
        	if(!form1Data || !form2Data) return;
        	if(form2Data.employee){
	        	for(var p in form2Data.employee){
	        		form1Data.employee[p] = form2Data.employee[p];
	        	}
        	}
        	data = form1Data;
        }*/
        //增加透明遮罩
    	git.mask();
        data = form.getData(true,true);
        var json = nui.encode(data);
        //alert(json);
        $.ajax({
            url: "com.bos.utp.org.employee.addEmployee.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response || {};
            	if(response && response.flag){
            		//CloseWindow("ok");
            		
            		//add by shangmf Begin:操作成功候调用webservice更新押品，不管是否成功不影响信贷更新
			     		nui.ajax({
			     			url: "com.bos.utp.org.collService.CollServiceImplServiceService.updCollOperByWebService.biz.ext",
			     			type: "post",
			     			data: json, 
			     			cache: false,
			     			contentType: 'text/json',
			     			success: function (text) {
			     			if(text.retCode=="success"){
			     				alert("新增成功，同步押品系统操作成功!");
			     				CloseWindow("ok");
			     			}else{
			     					alert("新增成功，同步押品系统操作失败!");
			     					CloseWindow("ok");
			     				}
							search();
			     		},
			     			error: function () {
			     			}
			     	});
				//add by shangmf End
            		
            	}else{
            		nui.alert(response.message);
            	}
            	//加载完成后，取消透明遮罩
    			git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                CloseWindow();
                //加载完成后，取消透明遮罩
    			git.unmask();
            }
        });
        
	}
	
	function cancel(){
		CloseWindow("cancel");
	}
	
	function SetData(data){
		data = nui.clone(data);
		var org = data.parentNode || {};
		form.setData({org:{orgid:org.orgid||""}});
	}
	
	//校验日期
	function onOutdateValidation(e){
       	var o = form.getData();
       	var org = o.employee || {};
		if(org.outdate && org.indate && org.outdate<=org.indate){
			e.errorText = "离职日期必须大于入职日期";
			e.isValid = false;
		}else{
			e.errorText = "";
		}
	}
	
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.user || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}else{
			e.errorText = "";
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
	
	function resetForm(){
		form.reset();
	}
	
	var bootPath = "<%=request.getContextPath() %>";
	
	//选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_manageorg_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 500,
            height: 400,
            onload:function(){
                var iframe = this.getIFrameEl();
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   ids:ids,
                   texts:texts
                };
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
    
    function selectRole(e) {
    var level;
    level=nui.get("level").getValue();
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_managed_role.jsp?level="+level,
            showMaxButton: false,
            title: "选择可管理角色",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: {nodeId:"", nodeName:"", nodeType:"OrgEmployee"},
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
 
    //展开，折叠
    function toggleFieldSet(ck, id) {
        var dom = document.getElementById(id);
        if(ck.checked){
        	dom.className = "";//展开
        }else{
        	dom.className = "hideFieldset";
        }
    }
    
    function selectMajor(e){
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/utp/org/employee/select_major.jsp",
            showMaxButton: false,
            title: "选择直接主管",
            width: 800,
            height: 600,
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
    var ids;
    var level;
    function SetData(data){
		data = nui.clone(data);
		if(data){
		  // alert(nui.encode(data.parentNode.orgid));
		  ids=data.parentNode.orgid;
		  level=data.parentNode.orglevel;
		  nui.get('parentOrgid').setValue(ids);
		  nui.get('orgcode').setValue(data.parentNode.orgcode);
		  nui.get('orgname').setValue(data.parentNode.orgname);
		  nui.get('level').setValue(level);
		}
	}
	 
    // 添加员工所属部门
    function selectDepartment(e){
    //  alert("ids="+ids);
    		 var btnEdit = this;
    		 var orgId;
    		 if(ids==undefined){
    		 	orgId =<%=request.getParameter("orgId") %>;
    		 }else{
    		 	orgId=ids;
    		 }
        nui.open({
            url: bootPath + "/utp/org/department/department_list.jsp?orgId="+orgId,
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
</script>

</body>
</html>