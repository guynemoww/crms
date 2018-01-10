<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 16:40:36
  - Description:
-->
<head>
<title>自然人客户监管报送数据</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="bizJG.applyId" class="nui-hidden nui-form-input" name ="bizJG.applyId"/>
		<input id="bizJG.yesornoId" class="nui-hidden nui-form-input" name ="bizJG.yesornoId"/>
		<div class="nui-dynpanel" columns="4" class="nui-dynpanel" id="grbs">
			<label class="nui-form-label" id="khfl">客户分类：</label>
			<input id="bizJG.cusType" name="bizJG.cusType" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8028"  required="true" />
			
			<label class="nui-form-label" id="gfts">购房套数：</label>
			<input id="bizJG.houseSum" name="bizJG.houseSum" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8029"  required="true" />
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="biz_gr_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId ="<%=request.getParameter("applyId") %>";//业务申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var prodType = "<%=request.getParameter("prodType") %>";//3-经营  2-消费（只有个人房屋按揭才有）
	if(prodType == '2'){//个人房屋按揭只显示购房套数
		$("#khfl").css("display","none");
		nui.get("bizJG.cusType").hide();
		nui.get('grbs').refreshTable();
	}else{
		$("#gfts").css("display","none");
		nui.get("bizJG.houseSum").hide();
		nui.get('grbs').refreshTable();
	}

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
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("biz_gr_info_save").hide();
			form.setEnabled(false);
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("biz_gr_info_save").setEnabled(false);
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
        		nui.get("biz_gr_info_save").setEnabled(true);
        		return;
        	}
        	var o = nui.decode(text);
        	form.setData(o);
        	alert("保存成功！");
        	nui.get("biz_gr_info_save").setEnabled(true);
        }});
	}
</script>
</body>
</html>