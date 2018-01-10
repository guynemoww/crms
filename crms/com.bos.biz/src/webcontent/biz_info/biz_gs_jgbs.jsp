<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 16:40:36
  - Description:
-->
<head>
<title>公司客户监管报送数据</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="bizJG.applyId" class="nui-hidden nui-form-input" name ="bizJG.applyId"/>
		<input id="bizJG.yesornoId" class="nui-hidden nui-form-input" name ="bizJG.yesornoId"/>
		<div class="nui-dynpanel" columns="4">
		
			<label class="nui-form-label">是否异地：</label>
			<input id="bizJG.isOffSite" name="bizJG.isOffSite" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  required="true" />
			
			<label class="nui-form-label">是否光伏企业：</label>
			<input id="bizJG.isPhotovoltaic" name="bizJG.isPhotovoltaic" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  required="true" />
			
			<label class="nui-form-label">是否钢贸企业：</label>
			<input id="bizJG.isSteelTrade" name="bizJG.isSteelTrade" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否造船企业：</label>
			<input id="bizJG.isShipbuilding" name="bizJG.isShipbuilding" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否钢铁企业：</label>
			<input id="bizJG.isSteel" name="bizJG.isSteel" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否水泥企业：</label>
			<input id="bizJG.isCement" name="bizJG.isCement" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否风电设备企业：</label>
			<input id="bizJG.isWindPower" name="bizJG.isWindPower" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否焦炭及煤化工：</label>
			<input id="bizJG.isBdo" name="bizJG.isBdo" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否电解铝企业：</label>
			<input id="bizJG.isCapacitor" name="bizJG.isCapacitor" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
			
			<label class="nui-form-label">是否其它产能过剩企业：</label>
			<input id="bizJG.isOverCapacity" name="bizJG.isOverCapacity" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
				
			<label class="nui-form-label">是否纺织企业：</label>
			<input id="bizJG.isFz" name="bizJG.isFz" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" required="true" />
				
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="biz_gs_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
		<div class="nui-dynpanel" columns="2">
		<label class="nui-form-label">说明：</label>
		<label class="nui-form-label"></label>
		<label class="nui-form-label"></label>
		<label class="nui-form-label">异地贷款：本行注册地所在市(含下辖区县、县级市)以及设立分支机构</label>
		<label class="nui-form-label"></label>
		<label class="nui-form-label">所在市(含下辖区县、县级市)之外的地区发放的所有贷款</label>
		<label class="nui-form-label"></label>
		<label class="nui-form-label">包括贷款、贸易融资、票据融资、从非金融机构买入返售资产、透支、各项垫款等</label>
		<label class="nui-form-label"></label>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId ="<%=request.getParameter("applyId") %>";//业务申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识

	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.getBizYesOrNo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	//nui.get("bizJG.isPhotovoltaic").setValue('0');
            	if(o.bizJG.isPhotovoltaic==null ||o.bizJG.isPhotovoltaic==''){
            		nui.get("bizJG.isPhotovoltaic").setValue('0');
            		nui.get("bizJG.isSteelTrade").setValue('0');
	            	nui.get("bizJG.isShipbuilding").setValue('0');
	            	nui.get("bizJG.isSteel").setValue('0');
	            	nui.get("bizJG.isCement").setValue('0');
	            	nui.get("bizJG.isWindPower").setValue('0');
	            	nui.get("bizJG.isBdo").setValue('0');
	            	nui.get("bizJG.isCapacitor").setValue('0');
	            	nui.get("bizJG.isOverCapacity").setValue('0');
	            	nui.get("bizJG.isFz").setValue('0');
            	}
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("biz_gs_info_save").hide();
			form.setEnabled(false);
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("biz_gs_info_save").setEnabled(false);
		var o = form.getData();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.bizInfo.bizInfo.saveBizYesOrNo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("biz_gs_info_save").setEnabled(true);
        	}
        	var o = nui.decode(text);
        	form.setData(o);
        	alert("保存成功！");
        	nui.get("biz_gs_info_save").setEnabled(true);
        }});
	}
</script>
</body>
</html>