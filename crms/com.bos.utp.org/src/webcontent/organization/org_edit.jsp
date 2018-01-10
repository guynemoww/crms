<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): sdl
  - Date: 2017-03-30 18:00:14
  - Description:
-->
<head>
<title>机构编辑</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>	
	<input id="orgname_" class="nui-hidden"/>
	<div id="form1" style="width: 100%; height: auto; overflow: hidden;">
		<input class="nui-hidden" name="orgOrganization.parentorgid" />
		<input class="nui-hidden" name="orgOrganization.orgid" />
		<div class="nui-dynpanel" columns="4" style="align: left">
			<label>上级行行号：</label> 
			<input name="orgOrganization.omOrganization.orgcode" id="orgParentcode" class="nui-textbox nui-form-input" enabled="false" /> 
			<label>上级行名称：</label>
			<input name="orgOrganization.omOrganization.orgname" id="orgParentname" class="nui-textbox nui-form-input" enabled="false" /> 
			<label>机构行号：</label> 
			<input name="orgOrganization.orgcode" id="orgcode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false" /> 
			<label>机构名称：</label> 
			<input name="orgOrganization.orgname" id="orgname" class="nui-textbox nui-form-input" required="true" vtype="maxLength:64"/> 
			<label>机构级别：</label> 
			<input name="orgOrganization.orglevel" id="level" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004" enabled="false" /> 
			<label>机构状态：</label>
			<input name="orgOrganization.status" id="status" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择" /> 
			<label>机构性质：</label> 
			<input name="orgOrganization.orgdegree" id="orgdegree" required="true" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6006" emptyText="请选择" /> 
			<label>机构类型：</label> 
			<input name="orgOrganization.isteam" id="isteam" required="true" dvalue="1" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="XD_GGCD6005" /> 
			<label>机构城乡标志：</label> 
			<input name="orgOrganization.isTradeArea" id="isTradeArea" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6007" /> 
			
			<label>金融许可证号：</label>
			<input id="financialnum" name="orgOrganization.financialnum" class="nui-textbox nui-form-input" vtype="maxLength:100" required="true"/> 
			<label>金融机构编码：</label>
			<input id="financialorgcode" name="orgOrganization.financialorgcode" class="nui-textbox nui-form-input" vtype="maxLength:100" required="true"/> 
			<label>非现场监管系统编码：</label>
			<input id="nonlocalcode" name="orgOrganization.nonlocalcode" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			<label>机构信用代码：</label>
			<input id="orgcreditcode" name="orgOrganization.orgcreditcode" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			
			<label>清算机构代码：</label>
			<input name="orgOrganization.auditbankno" id="auditbankno" class="nui-textbox nui-form-input" vtype="maxLength:10" /> 
			<label>企业征信机构代码：</label>
			<input name="orgOrganization.subcount" id="subcount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:11" /> 
			<label>个人征信机构代码：</label>
			<input name="orgOrganization.paymentsysno" id="paymentsysno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			<label>机构地址：</label> 
			<input name="orgOrganization.orgaddr" id="orgaddr" class="nui-textbox nui-form-input" required="true" vtype="maxLength:256" /> 
			<label>联系电话：</label>
			<input name="orgOrganization.linktel" id="linktel" class="nui-textbox nui-form-input" required="true" vtype="phone;rangeLength:0,20" /> 
			<label>邮政编码：</label> 
			<input name="orgOrganization.zipcode" id="zipcode" class="nui-textbox nui-form-input" required="true" vtype="int;rangeLength:0,10" /> 
			<label>登记日期：</label> 
			<input name="orgOrganization.createtime" id="createtime" class="nui-datepicker nui-form-input" enabled="false" /> 
			<label>机构描述：</label>
			<input name="orgOrganization.remark"  id="remark" colspan="3" required="true" class="nui-TextArea" vtype="maxLength:512" />
		</div>
	</div>
	<div class="mini-toolbar" borderstyle="border:0;" style="padding-right: 20px; text-align: right; padding-top: 5px; padding-bottom: 5px; border: 0px none;">
		<a class="nui-button" id="saveButton" iconCls="icon-save" onclick="update()">保存</a> 
		<a class="nui-button" id="synchronizationButton" iconCls="icon-reload" onclick="SynchronizationEcif()">同步</a>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	//参数获取
	var view = "<%=request.getParameter("view")%>";
	var form = new nui.Form("#form1"); 
	
	//初始化页面
	function initForm() {
		var json=nui.encode({"id":"<%=request.getParameter("id")%>"});
		$.ajax({
			url : "com.bos.utp.org.organization.getOrgInfo.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				if (text.msg) {
					nui.alert(text.msg);
				} else {
					form.setData(text);
					nui.get("orgname_").setValue(nui.get("orgname").getValue());
					//当机构级别为总行或分行时 '非现场监管系统编码'为必填
					var orgLevel = nui.get("level").getValue();
					if(orgLevel == '1'||orgLevel == '2'){
						nui.get("nonlocalcode").setRequired(true);
					}else{
						nui.get("nonlocalcode").setRequired(false);
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}
	initForm();
	//编辑页面view=1 查看 要屏蔽掉保存和同步按钮 同时要disable整个表单   
	function editPage() {
		if (view == null || view == 'null' || view == '') {
			return;
		}
		if (view == 1) {
			//nui.get("saveButton").setEnabled(false);
			$("#saveButton").hide();
			//nui.get("synchronizationButton").setEnabled(false);
			$("#synchronizationButton").hide();
			form.setEnabled(false);
		}
	}
	editPage();
	//更新
	function update() {
		/**
			修改信息的时候 需要校验 机构名称 
			机构名称不能重复存在 
			保存之前需要知道机构名称是否改变 先前台判断是否改变 如果没有就不用去后台验证
			如果改变就需要去后台校验这个名称是否存在 
		*/
		var orgname_ = nui.get("orgname_").getValue();
		var orgname = nui.get("orgname").getValue();
		if(orgname_ != orgname){//前台判断发生了改变 去后台验证这个名称是否存在 
			//校验机构名称是否存在
			var json2 = nui.encode({
				"map" : {"orgname" : nui.get("orgname").getValue()}
			});
			$.ajax({
				url : "com.bos.utp.org.organization.checkOnlyOrg2.biz.ext",
				type : 'POST',
				data : json2,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {//后台验证 发现机构名已经存在 
						alert(text.msg);
						nui.get("orgname").setValue("");
						return;
					} else {//后台验证 发现机构名不存在 即不重复 保存数据 
						saveData();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
					return;
				}
			});
		}else{//前台验证机构名称没有改变 保存数据
			saveData();
		}
	}
	//保存
	function saveData() {
		var o = form.getData();
		//机构名称校验通过再校验表单数据
		form.validate();
		if (form.isValid() == false) {
			return alert("请按规则填写信息");
		}
		//增加透明遮罩
		git.mask();
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.utp.org.organization.updateOrg.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			async : false,
			contentType : 'text/json',
			success : function(text) {
				//加载完成后，取消透明遮罩
				git.unmask();
				var response = text.response;
				nui.alert(response.message);
				CloseWindow();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				//加载完成后，取消透明遮罩
				git.unmask();
				nui.alert(jqXHR.responseText);
				window.close();
			}
		});
	}
	//关闭窗口
	function CloseWindow() {
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow();
		else
			window.close();
	}
	//同步 
	function SynchronizationEcif() {
		requestPSystem(nui.get("orgcode").getValue());
	}
</script>
</html>