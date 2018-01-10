<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>机构添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<script type="text/javascript" src="<%=contextPath%>/utp/org/js/org_common.js"></script>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input class="nui-hidden" name="orgOrganization.orgid" />
	<input class="nui-hidden" name="orgOrganization.parentorgid" />
	<input class="nui-hidden" name="orgOrganization.orglevel" />
	<div class="nui-dynpanel" columns="4"  style="align:left">
			<label for="orgParentcode$text">上级行行号：</label>
			<input id="orgParentcode" class="nui-textbox nui-form-input"  enabled="false"/>
			<label for="orgParentname$text">上级行名称：</label>
			<input id="orgParentname" class="nui-textbox nui-form-input"  enabled="false" />
			<label for="orgcode$text">机构行号：</label>
			<input id="orgcode" name="orgOrganization.orgcode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32"  enabled="false"/>
			<label for="orgname$text">机构名称：</label>
			<input id="orgname" class="nui-textbox nui-form-input" name="orgOrganization.orgname" required="true" vtype="maxLength:100" onvaluechanged="check1()"/>
			<label for="orglevel$text">机构级别：</label>
			<input id="level" name="orgOrganization.orglevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004" enabled="false"/>
			<label for="status$text">机构状态：</label>
			<input id="status" name="orgOrganization.status" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择"/>
			<label for="status$text">机构性质：</label>
			<input id="orgdegree" name="orgOrganization.orgdegree" required="true" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6006" emptyText="请选择"/>
			<label for="isteam$text">机构类型：</label>
			<input id="isteam" name="orgOrganization.isteam" required="true" dvalue="1" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="XD_GGCD6005" />
			<label for="isTradeArea$text">机构城乡标志：</label>
			<input name="orgOrganization.isTradeArea" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择"  dictTypeId="XD_GGCD6007"/>
			
			<label for="financialnum$text">金融许可证号：</label>
			<input id="financialnum" name="orgOrganization.financialnum" class="nui-textbox nui-form-input" vtype="maxLength:100" required="true"/> 
			<label for="financialorgcode$text">金融机构编码：</label>
			<input id="financialorgcode" name="orgOrganization.financialorgcode" class="nui-textbox nui-form-input" vtype="maxLength:100" required="true"/> 
			<label for="nonlocalcode$text">非现场监管系统编码：</label>
			<input id="nonlocalcode" name="orgOrganization.nonlocalcode" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			<label for="orgcreditcode$text">机构信用代码：</label>
			<input id="orgcreditcode" name="orgOrganization.orgcreditcode" class="nui-textbox nui-form-input" vtype="maxLength:100" /> 
			
			<label for="auditbankno$text">清算机构代码：</label>
			<input id="orgOrganization.auditbankno" class="nui-textbox nui-form-input" name="orgOrganization.auditbankno" vtype="maxLength:10"/>
			<label for="subcount$text">企业征信机构代码：</label>
			<input name="orgOrganization.subcount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:11" />
			<label for="paymentsysno$text">个人征信机构代码：</label>
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
			<input id="orgOrganization.streetAddress" name="orgOrganization.streetAddress" required="true" class="nui-textbox nui-form-input"   vtype="maxLength:200" />
			
			<label>行政区划：</label>
			<input id="orgOrganization.regAdministrativeDivisions"  name="orgOrganization.regAdministrativeDivisions"  required="true" class="nui-textbox nui-form-input"  allowInput="false" Enabled="false" />
			<!-- <label for="orgaddr$text">机构地址：</label>
			<input id="orgaddr" class="nui-textbox nui-form-input" name="orgOrganization.orgaddr" required="true" vtype="maxLength:256"/> -->
			<label for="linktel$text">联系电话：</label>
			<input id="linktel" class="nui-textbox nui-form-input" name="orgOrganization.linktel" required="true"  vtype="phone;rangeLength:0,20"/>
			<label for="zipcode$text">邮政编码：</label>
			<input id="zipcode" class="nui-textbox nui-form-input" name="orgOrganization.zipcode" required="true"  vtype="int;rangeLength:0,10"/>
			<label>机构公章：</label>
			<input id="orgOrganization.orgSeal" class="nui-textbox nui-form-input" name="orgOrganization.orgSeal" vtype="rangeLength:0,50"/>
			<label for="createtime$text">登记日期：</label>
			<input id="orgOrganization.createtime" name="orgOrganization.createtime" class="nui-datepicker nui-form-input" enabled="false"/>
			<label for="remark$text">机构描述：</label>
			<input colspan="3" name="orgOrganization.remark" required="true" class="nui-TextArea" vtype="maxLength:512" />
			
			<%--<label for="orglevel$text">机构层级：</label>
			<input name="orgOrganization.orglevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" emptyText="请选择" dictTypeId="XD_GGCD6004" enabled="false"/>
			<label for="orgParentname$text">上级机构：</label>
			<input id="orgParentname" class="nui-textbox nui-form-input"  allowInput="false" />
			<label for="isteam$text">机构类型：</label>
			<input id="isteam" name="orgOrganization.isteam" required="true" data="data" onvaluechanged="onIsteamChanged" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="XD_GGCD6005" />
			<label for="area$text">金融机构代码：</label>
			<input id="orgOrganization.area" name="orgOrganization.area" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />
			<label for="paymentsysno$text">T24网点号：</label>
			<input id="orgOrganization.paymentsysno" name="orgOrganization.paymentsysno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			<label for="status$text">机构状态：</label>
			<input id="status" name="orgOrganization.status" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择"/>
			<label for="orgtype$text">机构性质：</label>
			<input name="orgOrganization.orgtype" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:12" dictTypeId="XD_PUB001" emptyText="请选择"/>
			<label for="subcount$text">人行征信系统机构代码：</label>
			<input name="orgOrganization.subcount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />
			<label for="auditbankno$text">网点缩写：</label>
			<input id="orgOrganization.auditbankno" class="nui-textbox nui-form-input" name="orgOrganization.auditbankno" />
			<label for="orgaddr$text">机构地址：</label>
			<input id="orgaddr" class="nui-textbox nui-form-input" name="orgOrganization.orgaddr"   vtype="maxLength:256"/>
			<label for="linkman$text">联系人：</label>
			<input id="linkman" class="nui-textbox nui-form-input" name="orgOrganization.linkman"  vtype="maxLength:30"/>
			<label for="linktel$text">联系电话：</label>
			<input id="linktel" class="nui-textbox nui-form-input" name="orgOrganization.linktel"  vtype="phone;rangeLength:0,20"/>
			<label for="remark$text">机构描述：</label>
			<input colspan="3" name="orgOrganization.remark" required="false" class="nui-TextArea" vtype="maxLength:512" />--%>
		</div>
</div>
<div class="mini-toolbar" borderstyle="border:0;" style="padding-right:20px;text-align:right; padding-top: 5px; padding-bottom: 5px; border: 0px none;">
    <a class="nui-button"  iconCls="icon-save" onclick="update">保存</a>
   <%-- <a class="nui-button" id="resetBtn_01" iconCls="icon-reset" onclick="resetForm">重置</a>--%>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-reload"  onclick="SynchronizationEcif">同步</a>
</div>

<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("form1");
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
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
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
	
	(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
		}
	})();
	//是否部门
	function onby(){
		var buno=nui.get("orgOrganization.buno").getValue();
		if(buno=="1"){
			nui.get("orgtype").setRequired(true);
		}else{
			nui.get("orgtype").setRequired(false);
		}
	
	}
    function update() {
        var o = form.getData();            
        form.validate();
        if (form.isValid() == false) return;
        
        //增加透明遮罩
    	git.mask();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.utp.org.organization.updateOrg.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response;
            	if(response && response.flag && window.isCloseWindow){
            		CloseWindow("ok");
            	}else{
            		nui.alert(response.message);
            		window['formData'] = o;
            		if(response.flag && window.parent){
            			window.parent.refreshParentNode();
            		}
            	}
            	//加载完成后，取消透明遮罩
    			git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
                //加载完成后，取消透明遮罩
    			git.unmask();
            }
        });
    }

    ////////////////////
    //标准方法接口定义
    function SetData(data) {
    	if(data.action=="update"){
    		window.isCloseWindow = true;
    		//showCancelBtn();
    		$("#form1").css("height","100%");
    	}
        //跨页面传递的数据对象，克隆后才可以安全使用
        data = nui.clone(data);
		var json = nui.encode({template:data.orgid});
        $.ajax({
            url: "com.bos.utp.org.organization.getOrgWithParent.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            debugger;
                var o = nui.decode(mydata);
                form.setData(o);
                
                window['formData'] = o;
                nui.get("orgParentname").setValue(mydata.parentorgname);
                nui.get("orgParentcode").setValue(mydata.parentorgcode);
                var orgdegree = o.orgOrganization.orgdegree;
                if('2' == orgdegree){
                	//如果是小贷中心机构，则改变机构级别下拉选项值
					nui.get("level").setData(nui.getDictData("XD_GGCD6044"));
					nui.get("level").setValue(o.orgOrganization.orglevel);
                }
               //初始化省份城市
                if(mydata.orgOrganization.nationalityCd==null||mydata.orgOrganization.nationalityCd==""){
                nui.get("orgOrganization.nationalityCd").setValue("CHN");
                }else{
                  nui.get("orgOrganization.nationalityCd").setValue(mydata.orgOrganization.nationalityCd);
                  }
                git.getDistrictsByParentidEcif(nui.get('orgOrganization.nationalityCd').getValue(),function(data){
					nui.get('orgOrganization.provinceCd').setData(data.items);
				});
				if(mydata.orgOrganization.provinceCd==null||mydata.orgOrganization.provinceCd==""){
                   nui.get("orgOrganization.provinceCd").setValue("51");
                }
				git.getDistrictsByParentidEcif(nui.get('orgOrganization.provinceCd').getValue(),function(data){
					nui.get('orgOrganization.cityCd').setData(data.items);
				});
				git.getDistrictsByParentidEcif(nui.get('orgOrganization.cityCd').getValue(),function(data){
					nui.get('orgOrganization.district').setData(data.items);
				});
				
                //页面初始化时 获取机构级别 如果为总行或者分行 则'非现场监管系统编码'为必输
                var orgLevel = nui.get("level").getValue();
				if(orgLevel == '1'||orgLevel == '2'){
					nui.get("nonlocalcode").setRequired(true);
				}else{
					nui.get("nonlocalcode").setRequired(false);
				}
				form.validate();
            }
        });
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
    	if(window.isCloseWindow){
	        CloseWindow("cancel");
    	}
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
	
     function showCancelBtn(){
    	$("#cancelBtn_01").show();
    	$("#resetBtn_01").hide();
    }
    
    function resetForm(){
		var data = window['formData'];
		if(data){
			form.setData(data);
		}else{
			form.reset();
		}
	}
	
	function SynchronizationEcif(){
		requestPSystem(nui.get("orgcode").getValue());
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
            		nui.alert(text.returnMsg);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
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