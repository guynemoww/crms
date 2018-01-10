<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): sdl
  - Date: 2017-03-30 18:00:14
  - Description:
-->
<head>
<title>机构新增</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width: 100%; height: auto; overflow: hidden;">
		<input class="nui-hidden" name="obj.orgid" id="orgParentid"/>
		<div class="nui-dynpanel" columns="4" style="align: left">
			<label>上级行行号：</label> 
			<input id="orgParentcode" name="obj.orgcode" class="nui-buttonEdit" allowInput="false" required="true" onbuttonclick="selectOrg"/> 
			<label>上级行名称：</label>
			<input id="orgParentname" name="obj.orgname" class="nui-textbox nui-form-input" enabled="false" /> 
			<label>上级行级别：</label>
			<input id="orgParentlevel" name="obj.orglevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004" enabled="false"/>  
			<label>机构行号：</label> 
			<input id="orgcode" name="orgOrganization.orgcode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" onvalidation="checkOrgcode()"/> 
			<label>机构名称：</label> 
			<input id="orgname" name="orgOrganization.orgname" class="nui-textbox nui-form-input" required="true" vtype="maxLength:64" onvalidation="checkOrgname()"/> 
			<label>机构级别：</label> 
			<input id="level" name="orgOrganization.orglevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004" onvaluechanged="codeSet"/> 
			<label>机构状态：</label>
			<input id="status" name="orgOrganization.status" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择" /> 
			<label>机构性质：</label> 
			<input id="orgdegree" name="orgOrganization.orgdegree" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6006" emptyText="请选择" /> 
			<label>机构类型：</label> 
			<input id="isteam" name="orgOrganization.isteam" required="true" dvalue="1" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="XD_GGCD6005" /> 
			<label>机构城乡标志：</label> 
			<input id="isTradeArea" name="orgOrganization.isTradeArea" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6007" /> 
			
			<label>金融许可证号：</label>
			<input id="financialnum" name="orgOrganization.financialnum" class="nui-textbox nui-form-input" vtype="maxLength:100" required="true"/> 
			<label>金融机构编码：</label>
			<input id="financialorgcode" name="orgOrganization.financialorgcode" class="nui-textbox nui-form-input" vtype="maxLength:100" required="true"/> 
			<label>非现场监管系统编码：</label>
			<input id="nonlocalcode" name="orgOrganization.nonlocalcode" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			<label>机构信用代码：</label>
			<input id="orgcreditcode" name="orgOrganization.orgcreditcode" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			
			<label>清算机构代码：</label>
			<input id="auditbankno" name="orgOrganization.auditbankno" class="nui-textbox nui-form-input" vtype="maxLength:10" /> 
			<label>企业征信机构代码：</label>
			<input id="subcount" name="orgOrganization.subcount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:11" /> 
			<label>个人征信机构代码：</label>
			<input id="paymentsysno" name="orgOrganization.paymentsysno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			<label>机构地址：</label> 
			<input id="orgaddr" name="orgOrganization.orgaddr" class="nui-textbox nui-form-input" required="true" vtype="maxLength:256" /> 
			<label>联系电话：</label>
			<input id="linktel" name="orgOrganization.linktel" class="nui-textbox nui-form-input" required="true" vtype="phone;rangeLength:0,20" /> 
			<label>邮政编码：</label> 
			<input id="zipcode" name="orgOrganization.zipcode" class="nui-textbox nui-form-input" required="true" vtype="int;rangeLength:0,10" /> 
			<label>登记日期：</label> 
			<input id="createtime" name="orgOrganization.createtime" class="nui-datepicker nui-form-input" enabled="false" value="<%=GitUtil.getBusiDateStr()%>"/> 
			<label>机构描述：</label>
			<input id="remark"  name="orgOrganization.remark" required="true" class="nui-TextArea" vtype="maxLength:512" />
		</div>
	</div>
	<div class="mini-toolbar" borderstyle="border:0;" style="padding-right: 20px; text-align: right; padding-top: 5px; padding-bottom: 5px; border: 0px none;">
		<a class="nui-button" id="saveButton" iconCls="icon-save" onclick="save()">保存</a> 
		<a class="nui-button" id="cancelButton" iconCls="icon-reload" onclick="cancel()">取消</a>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	
	//当机构级别为总行或分行时 '非现场监管系统编码'为必填
	function codeSet(){
		var orgLevel = nui.get("level").getValue();
		if(orgLevel == '1'||orgLevel == '2'){
			nui.get("nonlocalcode").setRequired(true);
		}else{
			nui.get("nonlocalcode").setRequired(false);
		}
	}
	
	// 校验机构编码是否存在
	function checkOrgcode(){
		 var json1 = nui.encode({"map":{"orgcode":nui.get("orgcode").getValue()}});
         $.ajax({
            url: "com.bos.utp.org.organization.checkOnlyOrg.biz.ext",
            type: 'POST',
            data: json1,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		alert(text.msg);
            		nui.get("orgcode").setValue("");
            		return;
            	}else{
            		//requestPSystem(nui.get("orgcode").getValue())
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
	}
	 // 校验机构名称是否存在
	function checkOrgname(){
        var json2 = nui.encode({"map":{"orgname":nui.get("orgname").getValue()}});
         $.ajax({
            url: "com.bos.utp.org.organization.checkOnlyOrg2.biz.ext",
            type: 'POST',
            data: json2,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		alert(text.msg);
            		nui.get("orgname").setValue("");
            		return;
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
        
	}
	//选择父级机构
	function selectOrg(){
		var btnEdit = this;
		nui.open({
            url: nui.context + '/utp/org/organization/queryOrgForSelected.jsp',
            showMaxButton: true,
            title: "选择",
            width: 1000,
            height: 500,
            ondestroy: function (action) {
            	 if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
	                	btnEdit.setValue(data.orgname);
		                btnEdit.setText(data.orgcode);
	                	nui.get("orgParentname").setValue(data.orgname);
	                	nui.get("orgParentlevel").setValue(data.orglevel);
	                	nui.get("orgParentid").setValue(data.orgid);
	                }
	            }
            }
        });
	}
	//保存
	function save(){
		//校验
		form.validate();
        if (form.isValid() == false) {
			return alert("请按规则填写信息");
		}
		//校验机构级别 新增的机构级别必须小于父级机构
		var orgParentlevel = nui.get("orgParentlevel").getValue();
		var orglevel = nui.get("level").getValue();
		if(orglevel&&orgParentlevel){
			if(orglevel<=orgParentlevel){
				nui.get("level").setValue("");
				return alert("新增机构的机构级别应该小于上级机构的机构级别");
			}
		}
        var o = form.getData();
        //增加透明遮罩
		git.mask();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.utp.org.organization.addOrg.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response || {};
            	if(response){
            		if(response.flag){
            			CloseWindow("ok");
            		}else{
            			nui.alert(response.message);
            		}
            	}else{
            		nui.alert("添加失败，请联系管理员");
            	}
            	//取消透明遮罩
				git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
                //取消透明遮罩
				git.unmask();
            }
        });
	}
	//取消
	function cancel(){
		CloseWindow("cancel");
	}
</script>

</html>