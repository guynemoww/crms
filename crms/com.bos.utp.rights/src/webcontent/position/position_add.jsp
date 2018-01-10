<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Date: 2014-02-19
  - author: 王世春
  - Description: 岗位添加
-->

<head>
	<title>添加岗位</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/common/nui/common.jsp" %>
	<style type="text/css">
		body{
			width:100%;
			height: 150px;
			padding: 0px;
		}
	</style>
</head>
<body>
	<div id="form1" style="margin-top:10px;">
	<div class="nui-dynpanel" columns="2">
		 
			 <label>岗位编码：</label> 
				  <input id="item.posicode" class="nui-textbox" name="item.posicode" required="true"/>
			 <label>岗位名称： </label>
				  <input id="item.posiname" class="nui-textbox" name="item.posiname" required="true" vtype="maxLength:128"/>
			 <label>岗位说明： </label>
			 	  <input id="item.posistate" class="nui-TextArea nui-form-input"  name="item.posistate" vtype="maxLength:2000" />
		 </div>
		<div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
			<table width="100%">
				<tr>
					<td style="text-align:center;">
						<a class="nui-button" iconCls="icon-save" onclick="formSaving">保存</a>
						<span style="display:inline-block;width:25px;"></span>
						<a class="nui-button" iconCls="icon-cancel" onclick="formCancel">返回</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var form1 = new nui.Form("#form1");

	<%-- 关闭窗口 --%>
	function CloseWindow(action){
		if(action=="close" && form1.isChanged()){
			if(confirm("数据已改变,是否先保存?")){
				return false;
			}
		}else if(window.CloseOwnerWindow){
			return window.CloseOwnerWindow(action);
		}else{
			return window.close();
		}
	}

	function formSaving(){
		form1.validate();
		if(form1.isValid()==false) return;

		var form1Data = form1.getData();
		var sendData = nui.encode(form1Data);
		var json=nui.encode({"map":{"posiname":nui.get("item.posiname").getValue(),
			"posicode":nui.get("item.posicode").getValue(),
			"posistate":nui.get("item.posistate").getValue()}});
		$.ajax({
			url:"com.bos.utp.rights.positionManager.checkPositionOnlyOne.biz.ext",
			type:'POST',
			data:json,
			cache:false,
			contentType:'text/json',
			success:function(text){
				if(text.msg){
				  alert(text.msg);
				  return;
				}else{
							  $.ajax({
									url:"com.bos.utp.rights.positionManager.addPosition.biz.ext",
									type:'POST',
									data:sendData,
									cache:false,
									contentType:'text/json',
									success:function(text){
										var returnJson = nui.decode(text);
										if(returnJson.exception == null){
											CloseWindow("saveSuccess");
										} else {
											nui.alert("添加岗位失败", "系统提示", function(action){
												if(action == "ok" || action == "close"){
													//CloseWindow("saveFailed");
												}
											});
										}
									},
						     		error: function (jqXHR, textStatus, errorThrown) {
						                nui.alert(jqXHR.responseText);
						            }
								});
				
				  }
			},
     		error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
		
	}

	function formCancel(){
		CloseWindow("cancel");
	}
</script>
