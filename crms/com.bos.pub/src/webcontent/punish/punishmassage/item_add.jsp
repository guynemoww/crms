<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):lujinbin
  - Date: 2014-03-19
  - Description:TB_PUNISH_MESSAGE, com.bos.pub.sys.TbPunishMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<input id="scoreMessageId" name="tbOrderMessage.scoreMessageId"  class="nui-hidden" />
	<input id="scoreMessageIds" name="tbPunishMessage.scoreMessageId"  class="nui-hidden" />
	<input id="orderMessageNumber" name="tbPunishMessage.orderMessageNumber" required="true" class="nui-hidden"  vtype="maxLength:32" />
	<div class="nui-dynpanel" columns="2">
		<label>项目名称：</label>
		<input id="number"  name="punish.scoreProjectName" required="true" class="nui-buttonEdit" vtype="maxLength:60"  enabled="false"/>

		<label>计分事项：</label>
		<input  name="punish.scoreMatter" required="true" class="nui-TextArea" vtype="maxLength:60" enabled="false" />
		<label>实计分：</label>
		<input id="realScoring" name="punish.realScoring" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false"/>

		<label>处罚措施：</label>
		<input id="cuoshi" name="tbPunishMessage.punishMeasure" required="true" class="nui-TextArea" vtype="maxLength:60" enabled="false" />

		<label>处罚意见：</label>
		<input name="tbPunishMessage.punishOpinion" required="true" class="nui-TextArea" vtype="maxLength:60" />

		<label>经办人姓名：</label>
		<input id="orgPeopleName" name="tbPunishMessage.orgPeopleName" required="true"  class="nui-buttonEdit" vtype="maxLength:60" dictTypeId="user" enabled="false"/>

		<label>经办人工号：</label>
		<input id="userNum" name="tbPunishMessage.userNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:10"  enabled="false"/>

		<label>经办人机构：</label>
		<input id="orgNum" name="tbPunishMessage.orgPeopleNumber" required="true" class="nui-buttonEdit" vtype="maxLength:60"  dictTypeId="org" enabled="false"/>

		<label>经办日期：</label>
		<input id="time" name="tbPunishMessage.orgTime" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10"   enabled="false"/>

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    //标准方法接口定义
        function SetData(data) {
                    	// alert(nui.encode(data));
                       form.setData(data);
                        nui.get("scoreMessageId").setValue(data.punish.scoreMessageId);
                          nui.get("scoreMessageIds").setValue(data.punish.scoreMessageId);
                        nui.get("number").setText(data.punish.scoreProjectName);
                       nui.get("orderMessageNumber").setValue(data.punish.scoreMessageId);
                        nui.get("userNum").setValue(data.punish.orgPeopleName);
                        nui.get("orgPeopleName").setValue(data.punish.orgPeopleName);
                          nui.get("orgNum").setValue(data.punish.orgNum);
                           nui.get("time").setValue(data.punish.time);
                         var realScoring=  nui.get("realScoring").getValue();
	 var json=nui.encode({"shouldScore":realScoring});
                $.ajax({
                     url: "com.bos.pub.openOrder.findPunish.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                 //    alert(nui.encode(text));
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}else{
                    	var o=form.getData();
						var jsons=nui.encode(o);
			                    	var temp;
			                    	if(text.punish[0]==null){
			                    	  alert("没有相对应的处罚标准");
			                    	  return;
			                    	}else{
			                    	var temp=text.punish[0].PUNISH_MEASURE;
			                    	nui.get("cuoshi").setValue(temp);
			                    	}
			                    	
                    	}
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
	 
	 
                        
        }
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.punishDeal.addTbPunishMessage.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
nui.get("number").setText("<%=request.getParameter("scoreProjectName") %>");

   // 计分项目查询 
       function selectScore(e){
       		 nui.open({
            url: nui.context + "/pub/punish/punishmassage/punishBiaoZhun.jsp",
            showMaxButton: true,
            title: "处罚标准信息",
            width: 400,
            height: 400,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                     nui.get("jifen").setValue(data.integralEndValue);
                      nui.get("jifen").setText(data.integralEndValue);
                       nui.get("jibie").setValue("累计"+data.integralStartValue+"-"+data.integralEndValue+"分");
                       nui.get("cuoshi").setValue(data.punishMeasure);
                    }
                }
            }
        });   
       }
   
	</script>
</body>
</html>
