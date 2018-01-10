<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<%
	Object rootObj = XpathUtil.getDataContextRoot("request",pageContext);
	String empid = (String)XPathLocator.getInstance().getValue(rootObj,"empid");
%>	
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="com.eos.data.xpath.XPathLocator"%>
<html>
<head>
<title>用户信息修改</title>

</head>
<body >
<div id="form1" >
  <div id="panel1" class="nui-panel" title="详细信息" iconCls="icon-edit" style="width:99%;height:auto;" 
    showToolbar="false" showCollapseButton="true" showFooter="false" allowResize="true">
    <input class="nui-hidden" name="employee.orgid" />
	<input class="nui-hidden" name="employee.empid" />
	<input class="nui-hidden" name="employee.userid" />
	<input class="nui-hidden" name="employee.operatorid" />
    <table align="center" border="0" width="100%" >
      <tr>
      	<!-- 工号 -->
      	<td align="right">
         	 工号:
        </td>
        <td colspan="1">
        	<input id="empcode"  name="employee.empcode" class="nui-textbox asLabel"/>
        </td>
         <!-- 姓名 -->
        <td align="right">
          	姓名:
        </td>
        <td >
        	<input id="empname"  name="employee.empname" class="nui-textbox asLabel"/>
        </td>
         <!-- 性别 -->
        <td align="right" >
          性别:
        </td>
        <td colspan="1" >
        	<input id="gender" name="employee.gender" data="data" emptyText="请选择"    valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_GENDER" />
        </td>
      </tr>
      <tr>
      <!-- 出生日期 -->
        <td align="right" >
          出生日期:
        </td>
        <td colspan="1">
        	<input id="birthdate"  name="employee.birthdate" class="nui-textbox asLabel"/>
        </td>
        <!-- 学历 -->
        <td align="right" >
          学历:
        </td>
        <td colspan="1" >
          	<input id="education" name="employee.education" data="data" emptyText="请选择" valueField="dictID" textField="dictName"    class="nui-dictcombobox asLabel" dictTypeId="OM_EDUCATION" />
        </td>
        <!-- 职位 -->
        <td align="right">
          职位:
        </td>
        <td colspan="1"  >
        	<input id="position" name="employee.position" data="data" emptyText="请选择" valueField="dictID" textField="dictName"    class="nui-dictcombobox asLabel" dictTypeId="OM_POSITION" />
        </td>
      </tr>
      <tr>
      <!-- 上岗证编号 -->
        <td align="right">
         	 上岗证编号:
        </td>
        <td colspan="1">
        	<input id="licenseno"  name="employee.licenseno" class="nui-textbox asLabel"/>
        </td>
        <!-- 办公电话 -->
        <td align="right">
         办公电话:
        </td>
        <td colspan="1">
        	<input id="otel"  name="employee.otel" class="nui-textbox" required="true"/>
        </td>
        <!-- 手机号码 -->
        <td align="right">
          	手机号码:
        </td>
        <td colspan="1">
        	<input id="mobileno"  name="employee.mobileno" class="nui-textbox" required="true"/>
        </td>
      </tr>
      <tr>
       <!-- 办公邮箱 -->
      	 <td align="right">
          办公邮箱:
        </td>
        <td colspan="1">
        	<input id="oemail"  name="employee.oemail" class="nui-textbox" required="true"/>
        </td>
        <!-- 入行日期 -->
         <td align="right">
         	入行日期:
        </td>
        <td colspan="1">
        	<input id="intotradedate" name="employee.intotradedate"    class="nui-datepicker"/>
        </td>
        <!-- 从事营销日期- -->
        <td align="right">
            从事营销日期:
        </td>
        <td colspan="1">
        	<input id="runmarkdate"  name="employee.runmarkdate" class="nui-textbox asLabel"/>
        </td>
      </tr>
      <tr>
      <!-- 用户级别- -->
      	<td align="right">
          用户级别:
        </td>
        <td colspan="1">
        	<input id="emplevel" name="employee.emplevel" data="data" emptyText="请选择"    valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_EMPLEVEL" />
        </td>
        <!-- 客户经理状态 -->
        <td align="right">
            客户经理状态:
        </td>
        <td colspan="1">
        	<input id="empstatus" name="employee.empstatus" data="data" emptyText="请选择"    valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="ABF_EMPSTATUS" />
        </td>
        <td align="right">
          	主属机构:
        </td>
        <td >
        	<input id="orgid" name="employee.orgid" textName="employee.orgid"  allowInput="false" class="nui-buttonEdit" dictTypeId="org" onbuttonclick="selectEmpOrg" enabled="false"/>
		</td>
      </tr>
      <tr>
      	 <td align="right" cospan="3">
          	主属部门:
        </td>
        <td >
        	<input id="bumen" name="employee.departmentId"  textName="employee.departmentId"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectDepartment" enabled="false"/>
		</td>
      </tr>
    </table>
  </div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
     <a class="nui-button" iconCls="icon-save" id="btn_save" onclick="btnUpdate_onClick()" >保存</a>   
</div>
</div>

<script type="text/javascript">
   nui.parse();
   
   nui.get("empstatus").disable();
   nui.get("emplevel").disable();
   nui.get("intotradedate").disable();
   nui.get("runmarkdate").disable();
   nui.get("licenseno").disable();
   nui.get("position").disable();
   nui.get("education").disable();
   nui.get("birthdate").disable();
   nui.get("gender").disable();
   nui.get("empcode").disable();
   nui.get("empname").disable();
   nui.get("gender").disable();
   if(<%=empid %> == null){
   	  nui.get("btn_save").disable();
   	  nui.get("oemail").disable();
   	  nui.get("otel").disable();
   	  nui.get("mobileno").disable();
   }
   
   
   var form1 = new nui.Form("#form1");
   $.ajax({
       url: "com.bos.utp.org.empinfoManager.EmpManager.QueryEmployeeInfo.biz.ext",
       type: 'POST',
       cache: false,
       contentType:'text/json',
       cache: false,
       async: false,//非异步，即同步
       success: function (data) {
           var o = nui.decode(data);
           form1.setData(o);
           nui.get("specialty_bak").setText(o.rolename);
           var orgidlist = o.employee.orgidlist;
           var specialty = o.employee.specialty;
           o.employee.orgidlist = analysiString(orgidlist);
          //o.employee.specialty = analysiString(specialty);
          alert(o.employee.empid);
           if(o.employee.empid == ""){
           		nui.get("btn_save").disable();
           }	
           var texts = "";
           window['formData1'] = o;
           window['parentNode'] = o.employee;
       }
   });
   
   
   function btnUpdate_onClick() {
   		form1.validate();
   		if (form1.isValid() == false){
   			return;
   		}
   
        var form1Data = form1.getData(true,true);
        var data = form1Data;
        data.employee.oemail=data.employee.oemail || " ";
        data.employee.mobileno=data.employee.mobileno || " ";
        data.employee.otel=data.employee.otel || " ";
        var json = nui.encode(data);
        $.ajax({
            url: "com.bos.utp.org.empinfoManager.EmpManager.updateEmployeeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.retCode == "1"){
            		nui.alert("修改成功");
            	}else{
            		nui.alert("修改失败，请联系管理员");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }
</script>
</body>
</html>
