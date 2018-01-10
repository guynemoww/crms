<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>机构添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js"></script>
</head>
<body>
<div class="nui-fit" style="padding-top:5px;min-width:450px;min-height:300px;">
	<div id="form1" style="width:100%;height:99%;overflow:hidden;">
		<input class="nui-hidden" id="parentorgid" name="obj.orgid" />
		<input class="nui-hidden" id="parentorglevel" name="obj.orglevel" />
		<input class="nui-hidden" id ="parentorgseq" name="obj.orgseq" />
		<input class="nui-hidden" id ="orgid" name="orgOrganization.orgid" />
		<input class="nui-hidden" id ="orgid" name="orgOrganization.buno" value="0" />
		<div class="nui-dynpanel" columns="4"  style="align:left">
			<%--<label for="orgcode$text" >机构ID：</label>
			<input  class="nui-textbox nui-form-input" name="orgOrganization.orgid" enabled="false" required="false" vtype="maxLength:32" />
			<label for="orgname$text">机构名称：</label>
			<input id="orgname" class="nui-textbox nui-form-input" name="orgOrganization.orgname" required="true" vtype="maxLength:64" onvalidation="check1()"/>
			<label for="orgParentname$text">上级机构：</label>
			<input id="orgParentname" class="nui-textbox nui-form-input"  allowInput="false" />
			<label for="orgcode$text">机构编号：</label>
			<input id="orgcode" name="orgOrganization.orgcode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" onvalidation="check()"/>
			<label for="orgtype$text">机构性质：</label>
			<input name="orgOrganization.orgtype" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:12" dictTypeId="XD_PUB001" emptyText="请选择"/>
			<label for="auditbankno$text">金融机构代码：</label>
			<input name="orgOrganization.auditbankno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			<label for="subcount$text">人行征信系统机构代码：</label>
			<input name="orgOrganization.subcount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />
			<label for="isTradeArea$text">是否贸易区机构：</label>
			<input name="orgOrganization.isTradeArea" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择"  dictTypeId="XD_0002"/>
			<label for="paymentsysno$text">核算机构号：</label>
			<input name="orgOrganization.paymentsysno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			<label for="auditbankno$text">网点缩写：</label>
			<input id="auditbankno" class="nui-textbox nui-form-input" name="orgOrganization.auditbankno" />
			<label for="orgaddr$text">机构地址：</label>
			<input id="orgaddr" class="nui-textbox nui-form-input" name="orgOrganization.orgaddr"   vtype="maxLength:256"/>
			<label for="zipcode$text">邮编：</label>
			<input id="zipcode" class="nui-textbox nui-form-input" name="orgOrganization.zipcode"  vtype="int;rangeLength:0,10"/>
			<label for="status$text">机构状态：</label>
			<input id="status" name="orgOrganization.status" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择"/>
			<label for="linkman$text">联系人：</label>
			<input id="linkman" class="nui-textbox nui-form-input" name="orgOrganization.linkman"  vtype="maxLength:30"/>
			<label for="linktel$text">联系电话：</label>
			<input id="linktel" class="nui-textbox nui-form-input" name="orgOrganization.linktel"  vtype="phone;rangeLength:0,20"/>
			<label for="isteam$text">机构类型：</label>
			<input id="isteam" name="orgOrganization.isteam" required="true" data="data"  valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="XD_GGCD6005" />
			<label for="orglevel$text">机构级别：</label>
			<input id="level" name="orgOrganization.orglevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004"/>
			<label for="buno$text">是否部门：</label>
			<input name="orgOrganization.buno" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="YesOrNo" />
			<label for="remark$text">机构描述：</label>
			<input   colspan="3" name="orgOrganization.remark" required="false" class="nui-TextArea" vtype="maxLength:512" />--%>
			
			<label for="orgParentcode$text">上级行行号：</label>
			<input id="orgParentcode" class="nui-textbox nui-form-input"  enabled="false"/>
			<label for="orgParentname$text">上级行名称：</label>
			<input id="orgParentname" class="nui-textbox nui-form-input"  enabled="false" />
			<label for="orgcode$text">机构行号：</label>
			<input id="orgcode" name="orgOrganization.orgcode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" onblur="check()"  enabled="true"/>
			<label for="orgname$text">机构名称：</label>
			<input id="orgname" class="nui-textbox nui-form-input" name="orgOrganization.orgname" required="true" vtype="maxLength:100" />
			<label for="orglevel$text">机构级别：</label>
			<input id="level" name="orgOrganization.orglevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004" enabled="false"/>
			<label for="status$text">机构状态：</label>
			<input id="status" name="orgOrganization.status" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择"/>
			<label for="status$text">机构性质：</label>
			<input id="orgdegree" name="orgOrganization.orgdegree" required="true" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6006" emptyText="请选择"/>
			<label for="isteam$text">机构类型：</label>
			<input id="isteam" name="orgOrganization.isteam" required="true" dvalue="1" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="XD_GGCD6005" />
			<label for="isTradeArea$text">机构城乡标志：</label>
			<input id="isTradeArea" name="orgOrganization.isTradeArea" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择"  dictTypeId="XD_GGCD6007"/>
			<label for="auditbankno$text">清算机构代码：</label>
			<input id="orgOrganization.auditbankno" class="nui-textbox nui-form-input" name="orgOrganization.auditbankno" vtype="maxLength:10"/>
			<label for="subcount$text">企业机构代码：</label>
			<input name="orgOrganization.subcount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:11" />
			<label for="paymentsysno$text">个人机构代码：</label>
			<input id="orgOrganization.paymentsysno" name="orgOrganization.paymentsysno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			<label>国家或地区：</label>
			<input id="orgOrganization.nationalityCd" name="orgOrganization.nationalityCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" onitemclick="nationChange"  requiredErrorText="国家地区必选"/>
	
			<label>省/自治区/直辖市：</label>
			<input id="orgOrganization.provinceCd" name="orgOrganization.provinceCd" required="true" class="nui-combobox nui-form-input"  onitemclick="provinceChange" valueField="dictid" textField="dictname" vtype="maxLength:20"   />
	
			<label>市/自治州：</label>
			<input id="orgOrganization.cityCd" name="orgOrganization.cityCd" required="true" class="nui-combobox nui-form-input" onitemclick="cityChange" valueField="dictid"  textField="dictname" vtype="maxLength:20"   />
	
			<label>区/县：</label>
			<input id="orgOrganization.district" name="orgOrganization.district" required="true" class="nui-combobox nui-form-input" onvaluechanged="setAdress" valueField="dictid" textField="dictname" vtype="maxLength:20"   />
	
			<label for="orgaddr$text">机构地址（街道/路段）：</label>
			<input id="orgOrganization.streetAddress" name="orgOrganization.streetAddress" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:200" />
			
			<label>行政区划：</label>
			<input id="orgOrganization.regAdministrativeDivisions" name="orgOrganization.regAdministrativeDivisions" required="true" class="nui-textbox nui-form-input"  allowInput="false" Enabled="false" />
			<!-- <label for="orgaddr$text">机构地址：</label>
			<input id="orgaddr" class="nui-textbox nui-form-input" name="orgOrganization.orgaddr" required="true" vtype="maxLength:256"/> -->
			<label for="linktel$text">联系电话：</label>
			<input id="linktel" class="nui-textbox nui-form-input" name="orgOrganization.linktel" required="true"  vtype="phone;rangeLength:0,20"/>
			<label for="zipcode$text">邮政编码：</label>
			<input id="zipcode" class="nui-textbox nui-form-input" name="orgOrganization.zipcode" required="true"  vtype="int;rangeLength:0,10"/>
			<label for="createtime$text">登记日期：</label>
			<input id="orgOrganization.createtime" name="orgOrganization.createtime" class="nui-datepicker nui-form-input" enabled="false" value="<%=GitUtil.getBusiDateStr()%>" />
			<label for="remark$text">机构描述：</label>
			<input id="remark" colspan="3" name="orgOrganization.remark" required="true" class="nui-TextArea" vtype="maxLength:512" />
		</div>
	</div>
</div>
<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
    <a class="nui-button" id="btnSave" iconCls="icon-save" onclick="add">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="cancel">取消</a>
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	 // 校验机构编码是否存在
	function check(){
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
	
	//调用人力资源接口
	function requestPSystem(orgcode){
		if(null == orgcode || '' == orgcode){
			return;
		}
	
		var json = nui.encode({"orgcode":orgcode});
		$.ajax({
            url: "com.bos.utp.org.employee.callPersonSystemOrg.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if('00000000000000' == text.retcode){
	            	nui.get("orgname").setValue(text.org.orgname);
	            	nui.get("status").setValue(text.org.status);
	            	nui.get("orgaddr").setValue(text.org.orgaddr);
	            	nui.get("zipcode").setValue(text.org.zipcode);
	            	nui.get("linktel").setValue(text.org.linktel);	
	            	nui.get("isTradeArea").setValue(text.org.isTradeArea);
	            	nui.get("remark").setValue(text.org.remark);
            	}else{
            		
            		if('40018009999999'==text.retcode){
            		
            			nui.alert(text.returnMsg+",请先到人力资源系统增加机构！");
            			nui.get("orgcode").setValue("");
            		}else{
            		
            			nui.alert(text.returnMsg);
            			nui.get("orgcode").setValue("");
            		}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
		
	}
	
	
	//是否部门
	function onby(){
		var buno=nui.get("buno").getValue();
		if(buno=="1"){
			nui.get("orgtype").setRequired(true);
		}else{
			nui.get("orgtype").setRequired(false);
		}
	
	}
	
	 // 校验机构名称是否存在
	function check1(){
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
	function add(){

		//校验
		form.validate();
        if (form.isValid()==false) return;
        
       var o = form.getData();
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
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
        
	}
	
	function cancel(){
		CloseWindow("cancel");
	}
	 
	function SetData(data){
		data = nui.clone(data);
		if(data){
			var formData = {
				orgOrganization:{orgOrganization:data.parentNode}
			};
			//form.setData(formData);
			//nui.get("isteam").setValue("1");		
			//刘子良 2014/10/15修改
			nui.getbyName("orgOrganization.isteam").setValue(0);
			nui.get("orgParentname").setValue(formData.orgOrganization.orgOrganization.orgname);
			nui.get("orgParentcode").setValue(formData.orgOrganization.orgOrganization.orgcode);
			nui.get("parentorgid").setValue(formData.orgOrganization.orgOrganization.orgid);
			nui.get("parentorglevel").setValue(formData.orgOrganization.orgOrganization.orglevel);
			
			//直接根据父机构的机构性质，设置本机构的机构性质。
			var orgdegree=formData.orgOrganization.orgOrganization.orgdegree;
			if(null == orgdegree || '' == orgdegree || 'undefined' ==orgdegree){
				nui.get("orgdegree").setValue("1");
			}else{
				nui.get("orgdegree").setValue(orgdegree);
			}
			
			var level=formData.orgOrganization.orgOrganization.orglevel;
			if('1'==nui.get("orgdegree").getValue()){
			
				if(level==1){
				  nui.get("level").setValue(2);
				}else if(level==2){
				 nui.get("level").setValue(3);
				}else if(level==3){
				 nui.get("level").setValue(4);
				}else if(level==4){
				 form.setEnabled(false);
			   	 nui.get("btnSave").hide();
			   	 alert("不能再添加下属机构");
				}
			}else{
				//如果是小贷中心机构，则改变机构级别下拉选项值
				nui.get("level").setData(nui.getDictData("XD_GGCD6044"));
			
				if(level==2){
				  nui.get("level").setValue(3);
				}else if(level==3){
				 nui.get("level").setValue(4);
				}else{
				 form.setEnabled(false);
			   	 nui.get("btnSave").hide();
			   	 alert("不能再添加下属机构");
				}
			}
			nui.get("parentorgseq").setValue(formData.orgOrganization.orgOrganization.orgseq);
		}
	}
	
	function resetForm(){
		form.reset();
	}
	
	//校验日期
	//失效日期必须大于生效日期
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.orgOrganization || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
	//国家事件
    function nationChange(e){
    debugger;
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
		
			nui.get('orgOrganization.provinceCd').setData(data.items);
		});
		nui.get('orgOrganization.provinceCd').setValue("");
		nui.get('orgOrganization.cityCd').setValue("");
		nui.get('orgOrganization.cityCd').setEnabled(false);
		nui.get('orgOrganization.district').setValue("");
		nui.get('orgOrganization.district').setEnabled(false);
		nui.get('orgOrganization.regAdministrativeDivisions').setValue("");
		setAdress();
	}
	//省份事件
	function provinceChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
			nui.get('orgOrganization.cityCd').setData(data.items);
		});
		nui.get('orgOrganization.cityCd').setValue("");
		nui.get('orgOrganization.cityCd').setEnabled(true);
		nui.get('orgOrganization.district').setValue("");
		setAdress();
	}
	//城市 事件
	function cityChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
			nui.get('orgOrganization.district').setData(data.items);
			nui.get('orgOrganization.district').setEnabled(true);
		});
		nui.get('orgOrganization.district').setValue("");
			setAdress();
	}
	//设置行政区划事件
	function setAdress(){
		var districtId = nui.get('orgOrganization.district').getValue();
		if(districtId!=""){
			git.getByBizLogicEcif('com.primeton.components.nui.DictLoader2.getDistrictNamesByIdEcif.biz.ext',//getDistrictNamesById  getByBizLogic
						nui.encode({"dictid":districtId}), function(text){
							var dictname=text.dictname ;
							if(dictname){
								nui.get("orgOrganization.regAdministrativeDivisions").setValue(districtId.substring(0,6));
							}
						});
		}
	}
</script>
</body>
</html>