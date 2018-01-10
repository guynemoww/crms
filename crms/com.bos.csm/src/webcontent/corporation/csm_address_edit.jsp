<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28

  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item.addressId" id="item.addressId" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmAddress" class="nui-hidden" />
<fieldset>
  	<legend>
    	<span>注册地址</span>
    </legend>
	<div class="nui-dynpanel" columns="4">
	
    <!-- 
		<label>注册地址</label>
		<input id="item.registerAddress" name="item.registerAddress" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:200" />
	 -->	
		<label>国家或地区：</label>
		<input id="item.nationalityCd" name="item.nationalityCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" onitemclick="nationChange"   onvaluechanged="setNotChina" requiredErrorText="国家地区必选"/>

		<label>省/自治区/直辖市：</label>
		<input id="item.provinceCd" name="item.provinceCd" required="true" class="nui-combobox nui-form-input"  onitemclick="provinceChange" valueField="dictid" textField="dictname" vtype="maxLength:20"   />

		<label>市/自治州：</label>
		<input id="item.cityCd" name="item.cityCd" required="true" class="nui-combobox nui-form-input" onitemclick="cityChange" valueField="dictid"  textField="dictname" vtype="maxLength:20"   />

		<label>区/县：</label>
		<input id="item.district" name="item.district" required="true" class="nui-combobox nui-form-input" onvaluechanged="setAdress" valueField="dictid" textField="dictname" vtype="maxLength:20"   />

		<label>住所（街道/路段）：</label>
		<input id="item.streetAddress" name="item.streetAddress" required="true" class="nui-textbox nui-form-input" onblur="setAdress" vtype="maxLength:200" />

		<label>注册地行政区划：</label>
		<input id="item.regAdministrativeDivisions"  required="true" class="nui-textbox nui-form-input"  allowInput="false" Enabled="false" />

		<label>邮编：</label>
		<input id="item.zipNumZc" name="item.zipNumZc" class="nui-textbox nui-form-input" vtype="int;minLength:6;maxLength:6" required="true"/>
	</div>
	</fieldset>	
	<fieldset>
  	<legend>
    	<span>其他信息</span>
    </legend>
	<div class="nui-dynpanel" columns="4">
	
		<label>经营地址：</label>
		<input id="item.addressValue" name="item.addressValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		
		<label>联系人姓名：</label>
		<input id="item.linkmanName" name="item.linkmanName" class="nui-textbox nui-form-input" vtype="maxLength:200" required="true"/>
		
		<label>联系人电话：</label>
		<input id="item.accountContactsPhone" name="item.accountContactsPhone" class="nui-textbox nui-form-input" vtype="int;minLength:11;maxLength:11" required="true"/>
		
		<label>固定电话：</label>
		<input id="item.telephone" name="item.telephone" class="nui-textbox nui-form-input"   vtype="maxLength:20" />
		
		<label>邮编：</label>
		<input id="item.zipNum" name="item.zipNum" class="nui-textbox nui-form-input" vtype="int;minLength:5;maxLength:11" />
		
		<label>电子邮箱(Email)：</label>
		<input id="item.email" name="item.email" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>传真：</label>
		<input id="item.fax" name="item.fax" required="false"  class="nui-textbox nui-form-input" onvalidation="onValidatechuanzhen" vtype="maxLength:30" />

		<label>网址：</label>
		<input id="item.website" name="item.website" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />
		
		<label>主要产品情况：</label>
		<input id="item.mainProductCircs" name="item.mainProductCircs"  class="nui-textbox nui-form-input" vtype="maxLength:500" required="true"/>
		
		<label>经营场地面积(平方米)：</label>
		<input id="item.businessFloorSpace" name="item.businessFloorSpace"  class="nui-textbox nui-form-input" vtype="int;maxLength:20" required="true"/>
		
		<label>经营场地所有权：</label>
		<input id="item.businessFloorPermissions" name="item.businessFloorPermissions"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1089" vtype="maxLength:20" required="true"/>
	</div>
	</fieldset>	
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	function initForm() {
		var json=nui.encode({"item":{"addressId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.dataset.csm.TbCsmAddress"}});
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	 //初始化地址类型
	 function init(){
	 		git.mask("form1");
	 		var cType = "<%=request.getParameter("cType") %>";
	        var json = nui.encode({parentId:cType});
	         $.ajax({
	            url: "com.bos.csm.pub.getDict.getAddressDict.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                git.unmask("form1");
	                nui.get("item.addressTypeCd").setData(text.levels);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		     });
	      }
     init();
     
	
	function nationChange(e){
		git.getDistrictsByParentid(e.sender.getValue(),function(data){
			nui.get('item.provinceCd').setData(data.items);
		});
	}
	function provinceChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
			nui.get('item.cityCd').setData(data.items);
		});
	}
	function cityChange(e){
			git.getDistrictsByParentid(e.sender.getValue(),function(data){
			nui.get('item.district').setData(data.items);
		});
	}
	
	//“国家” 选择了“中国”之外的字段，那么 省，市，区县不必输
    function setNotChina(){
    	var belongStation = nui.get("item.nationalityCd").getValue();
    	//alert(belongStation);
    	if(belongStation != "101"){
    		nui.get("item.provinceCd").setRequired(false);
    		nui.get("item.cityCd").setRequired(false);
    		nui.get("item.district").setRequired(false);
    		
    	}else{
    		nui.get("item.provinceCd").setRequired(true);
    		nui.get("item.cityCd").setRequired(true);
    		nui.get("item.district").setRequired(true);
    	}
    }
    
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.saveItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
</script>
</body>
</html>
