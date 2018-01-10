<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-10
  - Description:企业客户关键信息维护
-->
<head>
<title>企业客户关键信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
<div id="form1"  style="width:99.5%;height:auto;overflow:hidden; text-align:left">
	<input name="item.partyId" class="nui-hidden nui-form-input"/>
	<input id="party.examineState" name="party.examineState" class="nui-hidden nui-form-input"/>
	<fieldset>
  	<legend>
    	<span>概况</span>
    </legend>
    	<div class="nui-dynpanel"  columns="4">
			
   		<label>CRMS客户编号：</label>
		<input id="party.partyNum" name="party.partyNum"  required="true" enabled="false" class="nui-textbox nui-form-input"/>
		
		<label>客户名称：</label>
		<input id="party.partyName" name="party.partyName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" onvalidation=""/>	
		
		<label>区域类型：</label>
		<input id="item.areaType" name="item.areaType" onvaluechanged="dataCheck" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0210"  required="true" />
			
		<label>客户性质：</label>
		<input id="item.corpCustomerTypeCd" required="true"  name="item.corpCustomerTypeCd" 
				 class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0252" />
		</div>
    </fieldset>
    
    <fieldset>
  	<legend>
    	<span>证件信息</span>
    </legend>
    	<div class="nui-dynpanel" id="table1" columns="4">
			
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" onblur="dataCheck" onvalidation="checkUnifySocietyCreditNum" 
			vtype="minLength:18;maxLength:18"class="nui-textbox nui-form-input"/>
			
			<label>营业执照：</label>
			<input id="item.registrCd" name="item.registrCd" required="true" vtype="maxLength:32;"  onvalidation="checkBusinessLicenseNumUnique" class="nui-textbox nui-form-input"/>

    		<label>组织机构代码：</label>
			<input id="item.orgRegisterCd" name="item.orgRegisterCd"  onvalidation="checkOrgRegisterCdUnique" 
				class="nui-textbox nui-form-input"/>
    	</div>
    </fieldset>
    
    <fieldset>
  	<legend>
   		<span>注册、经营情况</span>
    </legend>
        <div class="nui-dynpanel" style="border:none" columns="4">
    
	    <label>统计口径企业规模：</label>
		<input id="item.countBoreEnterScale" name="item.countBoreEnterScale" required="true" onvaluechanged="dataCheck"
			class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0025"  />
    	</div>
    </fieldset>
    	
	<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
	    <a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	    <a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	git.mask("form1");
	var form = new nui.Form("#form1");
	var pi = "<%=request.getParameter("partyId")%>";
	var qote = "<%=request.getParameter("qote")%>";
	if(qote==1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	   nui.get("btnTest").hide();
	   nui.get("btnVali").hide();
	} 

	//初始化页面
    $(document).ready(function(){
		var json = nui.encode({"partyId":pi});
		$.ajax({
	        url: "com.bos.csm.corporation.corporation.getCorpration.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	git.unmask("form1");
               	form.setData(text);
				//备份数据
                window.form1Data=form.getData();
	        }
    	});
	});
	//------------------页面动态控制区------------------//
	//区域类型事件
	function dataCheck(){
		var area = nui.get("item.areaType").getValue();//境内外标识
		var unifySocietyCreditNum = nui.get("item.unifySocietyCreditNum").getValue();//统一社会信用代码
		if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
			nui.get("item.registrCd").setRequired(false);
			nui.get("item.orgRegisterCd").setRequired(false);
		}else{
			nui.get("item.registrCd").setRequired(true);
			if(area=="1"){//境内
				nui.get("item.orgRegisterCd").setRequired(true);
			}else{//境外
				nui.get("item.orgRegisterCd").setRequired(false);
			}
		}
		nui.get('table1').refreshTable();
	}
	
	
	//校验营业执照号码是否唯一
	function checkBusinessLicenseNumUnique(e) {
		var registerCode = nui.get("item.registrCd").getValue();
		//判断值是否改变,如改变，则做唯一校验，否则不校验
		var so = window.form1Data;
		var s_registerCode;
		if (null == so) {
			s_registerCode = nui.get("item.registrCd").getValue();
		} else {
			s_registerCode = so.item.registrCd;
		}
		if (s_registerCode != registerCode) {
			if (e.isValid) {
				var json = {"certno" : registerCode};
				msg = exeRule("RCSM_0001", "1", json);
				if (null != msg && '' != msg) {
					e.errorText = msg;
					e.isValid = false;
				}
			}
		}
	}

	//校验组织机构号码是否唯一
	function checkOrgRegisterCdUnique(e) {
		var registerCode = e.value;
		var areaType = nui.get("item.areaType").getValue();
		//校验组织机构代码有效
		isValidCompID(e);
		//如果有效，接着校验是否唯一
		//判断值是否改变,如改变，则做唯一校验，否则不校验
		var so = window.form1Data;
		var s_orgRegisterCode;
		if (null == so) {
			s_orgRegisterCode = e.value;
		} else {
			s_orgRegisterCode = so.item.orgRegisterCd;
		}
		if (s_orgRegisterCode != registerCode) {
			if (e.isValid) {
				var json = {"certno" : registerCode};
				msg = exeRule("RCSM_0011", "1", json);
				if (null != msg && '' != msg) {
					e.errorText = msg;
					e.isValid = false;
				}
			}
		}
	}

	//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e) {
		var unifySocietyCreditNum = e.value;
		if(null != unifySocietyCreditNum && '' != unifySocietyCreditNum){
	 	//校验统一社会信用代码,第9-17位符合组织机构代码校验
	 	e.value=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	   	isValidCompID(e);
		//判断值是否改变,如改变，则做唯一校验，否则不校验
		if (e.isValid) {
			var so = window.form1Data;
			var s_unifySocietyCreditNum;
			if (null == so) {
				s_unifySocietyCreditNum = unifySocietyCreditNum;
			} else {
				s_unifySocietyCreditNum = so.item.unifySocietyCreditNum;
			}
			if (s_unifySocietyCreditNum != unifySocietyCreditNum) {
				var json = {"certno" : unifySocietyCreditNum};
				msg = exeRule("RCSM_unifySocietyCreditNum", "1", json);
				if (null != msg && '' != msg) {
					e.errorText = msg;
					e.isValid = false;
				}
			}
		}
	}
}
	//----------------------事件处理区--------------------//
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请按规则填写信息！");
			return;
		}
		git.mask("form1");
		var o = form.getData();
		//设置操作标记，1表示关键信息维护
		o.co_flag = 1;
		o.party.partyName=nui.get("party.partyName").getValue().trim();
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.csm.corporation.corporation.updateCorpInfo.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
				if (text.msg) {
					alert(text.msg);
					return;
				} else {
					nui.alert("保存成功！");
					CloseWindow();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		});
	}
</script>
</body>
</html>

