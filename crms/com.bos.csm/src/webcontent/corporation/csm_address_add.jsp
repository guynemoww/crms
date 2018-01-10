<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2013-11-28
  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99%;height:auto;overflow:hidden;">
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<input name="item.addressId" id="item.addressId" class="nui-hidden" />
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
	<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save">保存</a>
	</div>
</div>
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote") %>";
	nui.get('item.partyId').setValue(partyId);
	if('1' == qote){
	
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	}
	
	//“国家” 选择了“中国”之外的字段，那么 省，市，区县不必输
    function setNotChina(){
    	var belongStation = nui.get("item.nationalityCd").getValue();
    	//alert(belongStation);
    	if(belongStation != "CHN"){
    		nui.get("item.provinceCd").setRequired(false);
    		nui.get("item.cityCd").setRequired(false);
    		nui.get("item.district").setRequired(false);
    		nui.get("item.regAdministrativeDivisions").setRequired(false);
    		nui.get("item.zipNum").setVtype('int;maxLength:9');
    		nui.get("item.accountContactsPhone").setVtype('');
    	}else{
    		nui.get("item.provinceCd").setRequired(true);
    		nui.get("item.cityCd").setRequired(true);
    		nui.get("item.district").setRequired(true);
    		nui.get("item.regAdministrativeDivisions").setRequired(true);
    		nui.get("item.zipNum").setVtype('int;minLength:6;maxLength:6');
    		nui.get("item.accountContactsPhone").setVtype('int;minLength:11;maxLength:11');
    	}
    	form.validate();
    }
    
    //国家事件
    function nationChange(e){
    debugger;
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
		
			nui.get('item.provinceCd').setData(data.items);
		});
		nui.get('item.provinceCd').setValue("");
		nui.get('item.cityCd').setValue("");
		nui.get('item.cityCd').setEnabled(false);
		nui.get('item.district').setValue("");
		nui.get('item.district').setEnabled(false);
		nui.get('item.regAdministrativeDivisions').setValue("");
		setAdress();
	}
	//省份事件
	function provinceChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
			nui.get('item.cityCd').setData(data.items);
		});
		nui.get('item.cityCd').setValue("");
		nui.get('item.cityCd').setEnabled(true);
		nui.get('item.district').setValue("");
		setAdress();
	}
	//城市 事件
	function cityChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
			nui.get('item.district').setData(data.items);
			nui.get('item.district').setEnabled(true);
		});
		nui.get('item.district').setValue("");
			setAdress();
	}
	//设置通讯地址事件
	function setAdress(){
		//为通讯地址赋值
		var addressValue = nui.get("item.nationalityCd").getText();
		var provinceCd = nui.get("item.provinceCd").getText();
		var cityCd = nui.get('item.cityCd').getText();
		var district = nui.get('item.district').getText();
		var districtId = nui.get('item.district').getValue();
		var streetAddress = nui.get('item.streetAddress').getValue();
		var str = addressValue+provinceCd+cityCd+district+streetAddress;
/* 		nui.get("item.addressValue").setValue(str); */
		if(districtId!=""){
			git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',//getDistrictNamesById  getByBizLogic
						nui.encode({"dictid":districtId}), function(text){
							var dictname=text.dictname ;
							if(dictname){
								nui.get("item.regAdministrativeDivisions").setValue(districtId.substring(0,6));
							}
							
						});
		}
	}
	
	
	
	//-----------------事件处理区------------------------------------------//    
	function save() {
		form.validate();
		
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		nui.get('item.partyId').setValue(partyId);
		var o=form.getData();
		var json=nui.encode(o);
		debugger;
		$.ajax({
            url: "com.bos.csm.corporation.corporation.AddAttachInfoEcif.biz.ext",//AddAttachInfoEcif
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
           		git.unmask("form1");
           		if("AAAAAAA"!=text.msgg.msg){
       alert("调用ECIF接口失败："+text.msgg.msgg);
            	}else{
            	     	if(text.msg){
            		alert(text.msg);
            	} else {
            		alert("保存成功");
            	}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
               	git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
		});
	}
	
	//初始化地址类型
	$(document).ready(function(){
 		git.mask("form1");
        var json = nui.encode({"partyId":partyId});
        if(qote!=1){ 
		$.ajax({
			url: "com.bos.csm.corporation.corporation.GetAttachInfoEcif.biz.ext",//GetAttachInfo
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                git.unmask("form1");
                //var o = nui.decode(text);
                form.setData(text);
                	nui.get('item.partyId').setValue(partyId);
                 if(text.item.nationalityCd==null||text.item.nationalityCd==""){
                nui.get("item.nationalityCd").setValue("CHN");
                }else{
                  nui.get("item.nationalityCd").setValue(text.item.nationalityCd);
                  }
                git.getDistrictsByParentidEcif(nui.get('item.nationalityCd').getValue(),function(data){
					nui.get('item.provinceCd').setData(data.items);
				});
				if(text.item.provinceCd==null||text.item.provinceCd==""){
                   nui.get("item.provinceCd").setValue("51");
                }
				git.getDistrictsByParentidEcif(nui.get('item.provinceCd').getValue(),function(data){
					nui.get('item.cityCd').setData(data.items);
				});
				git.getDistrictsByParentidEcif(nui.get('item.cityCd').getValue(),function(data){
					nui.get('item.district').setData(data.items);
				});
				
                //翻译注册地行政区划
    			var district = nui.get("item.regAdministrativeDivisions").getValue();
    			if(district!=""){
    				git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',
					nui.encode({"dictid":district}), function(text){
						var dictname=text.dictname ;
						nui.get("item.regAdministrativeDivisions").setValue(dictname);
					});
    			}
    	/* 		          if("AAAAAAA"!=text.msg.msg){
		             	nui.alert("调用ECIF接口失败:"+text.msg.msgg);
		               git.unmask("form1");
		             
		             	   return ;
            	} */
        		
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
		});
		}else{
		$.ajax({
			url: "com.bos.csm.corporation.corporation.getAttachInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
                git.unmask("form1");
                //var o = nui.decode(text);
                form.setData(text);
                nui.get('item.partyId').setValue(partyId);
                if(text.item.nationalityCd==null||text.item.nationalityCd==""){
                nui.get("item.nationalityCd").setValue("CHN");
                }
                git.getDistrictsByParentidEcif(nui.get('item.nationalityCd').getValue(),function(data){
					nui.get('item.provinceCd').setData(data.items);
				});
			 if(text.item.provinceCd==null||text.item.provinceCd==""){
                   nui.get("item.provinceCd").setValue("51");
                }
				git.getDistrictsByParentidEcif(nui.get('item.provinceCd').getValue(),function(data){
					nui.get('item.cityCd').setData(data.items);
				});
				git.getDistrictsByParentidEcif(nui.get('item.cityCd').getValue(),function(data){
					nui.get('item.district').setData(data.items);
				});
                //翻译注册地行政区划
    			var district = nui.get("item.regAdministrativeDivisions").getValue();
    			if(district!=""){
    				git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',
					nui.encode({"dictid":district}), function(text){
						var dictname=text.dictname ;
						nui.get("item.regAdministrativeDivisions").setValue(dictname);
					});
    			}      

        		
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
		});
		}
	});
	
	
	
	function onValidatechuanzhen(code){
		if(code.value!=""){
			var regTest = /^((\d{2,4})-)(\d{7,8})(-(\d{3,}))?$/;
			var regTest_i18n = /^((\d{3})-)((\d{2,4})-)(\d{7,8})(-(\d{3,}))?$/;
			if(!regTest_i18n.test(code.value)&&!regTest.test(code.value)){
				code.errorText = '不符合规则';
				code.isValid = false;
				return ;
		    }
		}
	}
</script>
</body>
</html>
