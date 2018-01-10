<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-06 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<div id="form1" clsss="nui-form"style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="school.schoolId" id="school.schoolId" value="<%=request.getParameter("schoolid")%>" class="nui-hidden" />
		        <input name="school.partyId" id="school.partyId"  value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
				<div id="djk"class="nui-dynpanel" columns="4">
					<label>高校名称：</label> 
					<input id="school.collegename" name="school.collegename" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:32"/> 
					
					<label>高校地址：</label> 
					<input id="school.collegeaddr" name="school.collegeaddr" required="ture" enabled="false" class="nui-textbox nui-form-input" vtype="string;maxLength:32"/> 
					<label>院系名称：</label> 
					<input id="school.institutename" name="school.institutename" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:32"/> 
					<label>院校类型：</label> 
					<input id="school.collegetype" name="school.collegetype" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YXLX0001"/> 
					<label>就读学位：</label> 
					<input id="school.educsign" name="school.educsign" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0014"/> 
					
					<label>专业名称：</label> 
					<input id="school.subjectname" name="school.subjectname" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:32"/> 
					
					<label>毕业年份：</label> 
					<input id="school.graduateyear" name="school.graduateyear" required="ture" class="nui-datepicker nui-form-input"/>
		<label>国家或地区：</label>
		<input id="school.nationalityCd" name="school.nationalityCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003"   onvaluechanged="setNotChina" requiredErrorText="国家地区必选"/>

		<label>省/自治区/直辖市：</label>
		<input id="school.provinceCd" name="school.provinceCd" required="true" class="nui-combobox nui-form-input"  onvaluechanged="provinceChange" valueField="dictid" textField="dictname" vtype="maxLength:20"   />

		<label>市/自治州：</label>
		<input id="school.cityCd" name="school.cityCd" required="true" class="nui-combobox nui-form-input" onvaluechanged="cityChange" valueField="dictid"  textField="dictname" vtype="maxLength:20"   />

		<label>区/县：</label>
		<input id="school.district" name="school.district" required="true" class="nui-combobox nui-form-input" onvaluechanged="strictcityChange" valueField="dictid" textField="dictname" vtype="maxLength:20"   />
					<label>行政区划代码：</label> 
					<input id="school.areacode" name="school.areacode" required="ture" enabled="false" class="nui-textbox nui-form-input" vtype="string;maxLength:32"/>
							<label  id="zhxgsj">最后修改日期：</label> 
					<input id="school.lastchandate" name="school.lastchandate" required="ture" enabled="false" class="nui-datepicker nui-form-input"/> 
					
					<label  id="zhxgr">最后修改人：</label> 
					<input id="school.lastchanperson" name="school.lastchanperson"  dictTypeId="user" required="ture" enabled="false" class="nui-text nui-form-input" vtype="string;maxLength:32"/> 
				</div>

			<div class="nui-toolbar"
				style="border: 0; text-align: right; padding-right:">
				<a id="save" class="nui-button" iconCls="icon-save" onclick="save()">保存</a> <a
				id="close"	class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
		</div>


	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
	    var qote = "<%=request.getParameter("qote") %>";
	    //“国家” 选择了“中国”之外的字段，那么 省，市，区县不必输
    function setNotChina(){
     debugger;
    	var belongStation = nui.get("school.nationalityCd").getValue();
    	//alert(belongStation);
    	if(belongStation != "CHN"){
    		nui.get("school.provinceCd").setRequired(false);
    		nui.get("school.cityCd").setRequired(false);
    		nui.get("school.district").setRequired(false);
    		nui.get('school.provinceCd').setValue("");
			nui.get('school.cityCd').setValue("");
			nui.get('school.district').setValue("");
    	}else{
    		nui.get("school.provinceCd").setRequired(true);
    		nui.get("school.cityCd").setRequired(true);
    		nui.get("school.district").setRequired(true);
    	git.getDistrictsByParentidEcif(nui.get("school.nationalityCd").getValue(),function(data){//getDistrictsByParentid
		
			nui.get('school.provinceCd').setData(data.items);
	
		});
		nui.get('school.provinceCd').setValue("");
		nui.get('school.cityCd').setValue("");
		nui.get('school.cityCd').setEnabled(false);
		nui.get('school.district').setValue("");
		nui.get('school.district').setEnabled(false);
    	}
    	form.validate();
    }
    
    //国家事件
    function nationChange(e){
    debugger;
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
		
			nui.get('school.provinceCd').setData(data.items);
		});
		nui.get('school.provinceCd').setValue("");
		nui.get('school.cityCd').setValue("");
		nui.get('school.cityCd').setEnabled(false);
		nui.get('school.district').setValue("");
		nui.get('school.district').setEnabled(false);
	}
	//省份事件
	function provinceChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
			nui.get('school.cityCd').setData(data.items);
		});
		nui.get('school.cityCd').setValue("");
		nui.get('school.cityCd').setEnabled(true);
		nui.get('school.district').setValue("");
	}
	//城市 事件
	function cityChange(e){
		git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){//getDistrictsByParentid
			nui.get('school.district').setData(data.items);
			nui.get('school.district').setEnabled(true);
		});
		nui.get('school.district').setValue("");
	}
	//学校地址
	function strictcityChange(e){
	debugger;
		var districtId = nui.get('school.district').getValue();
		var district = nui.get('school.district').getText();
		nui.get('school.collegeaddr').setValue(district);
		nui.get('school.areacode').setValue(districtId);
	}
	   	if(qote=="2"){
		nui.get("save").hide();
		nui.get("close").hide();
		form.setEnabled(false);
	    }
	    if(qote=="0"){
		$("#zhxgsj").hide();
		$("#zhxgr").hide();
		nui.get("school.lastchandate").hide();
		nui.get("school.lastchanperson").hide();
	    }else{
	     initForm();
	    }
		function initForm() {
			var json = nui.encode({
				"schoolId" : "<%=request.getParameter("schoolId")%>"});
			$.ajax({
				url : "com.bos.csm.natural.natural.getNaturalSchool.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(mydata) {
					git.unmask("form1");
					var o = nui.decode(mydata);
					form.setData(o);
				if(mydata.school.nationalityCd==null||mydata.school.nationalityCd==""){
                nui.get("school.nationalityCd").setValue("CHN");
                }else{
                  nui.get("school.nationalityCd").setValue(mydata.school.nationalityCd);
                  }
                git.getDistrictsByParentidEcif(nui.get('school.nationalityCd').getValue(),function(data){
					nui.get('school.provinceCd').setData(data.items);
				});
				if(mydata.school.provinceCd==null||mydata.school.provinceCd==""){
                   nui.get("school.provinceCd").setValue("51");
                }
				git.getDistrictsByParentidEcif(nui.get('school.provinceCd').getValue(),function(data){
					nui.get('school.cityCd').setData(data.items);
				});
				git.getDistrictsByParentidEcif(nui.get('school.cityCd').getValue(),function(data){
					nui.get('school.district').setData(data.items);
				});
				nui.get('school.collegeaddr').setValue(o.school.collegeaddr);
					oldData = form.getData();
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
		function save() {
			form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			git.mask("form1");
			var o = form.getData();
			var json = nui.encode(o);
			if(qote!="1"){
			$.ajax({
				url : "com.bos.csm.natural.natural.addNaturalSchool.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						alert(text.msg);
					} else {
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
			}else{
			$.ajax({
				url : "com.bos.csm.natural.natural.updateNaturalSchool.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						alert(text.msg);
					} else {
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
			}
		}
	</script>

</body>
</html>