<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): liuzn (mailto:liuzn@primeton.com)
  - Date: 2013-03-11 14:20:35
  - Description:
-->
<head>
	<title>按钮资源修改</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="form1" >
	  
		<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
			<span>&nbsp;</span>
			<tr >
				<td class="nui-form-label"><label for="resource.resourceid$text">按钮标识：</td>
				<td>
				  <input id="resource.resourceid"  class="nui-textbox" name="resource.resourceid" allowInput="false" />
				</td>
				<td class="nui-form-label"><label for="resource.resourcename$text">按钮名称：</td>
				<td>
				  <input id="resource.resourcename" class="nui-textbox" name="resource.resourcename" />
				</td>				
			</tr>
			<tr class="odd">				
				<td class="nui-form-label" ><label for="resource.resourcedesc$text">按钮描述：</label></td>
				<td>
				 <input id="resource.resourcedesc" class="nui-textbox" name="resource.resourcedesc" />
				</td>
				<td class="nui-form-label"><label for="resource.resourcename$text">关联菜单：</td>
				<td>
					<input id="resource.funccode" class="nui-buttonedit nui-form-input"  onbuttonclick="onButtonEdit" name="resource.funccode" /><!-- textName="test2" -->    
					<input id="resource.funcname" name="resource.funcname" class="nui-hidden"/>
				</td>
			</tr>
		</table>
		<table style="width:100%;" class="nui-form-table">
			<tr class="odd">
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr class="odd">
				<td colspan="4" style="padding-right:20px;text-align:right;">
				 		<a class="nui-button" iconCls="icon-save" onclick="formSaving">保存</a>
						<span style="display:inline-block;width:25px;"></span>
						<a class="nui-button" iconCls="icon-cancel" onclick="formCancel">返回</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var form1 = new nui.Form("#form1");

     var flag="";
	<%-- 父页面调用：初始化传值 --%>
	function SetData(row){
        data = nui.clone(row);
        var btnEdit=nui.get("resource.funccode");
		var sendData = nui.encode({resource:data});
		var modData = nui.decode(sendData);		
		form1.setData(modData);
		btnEdit.setValue(row.funccode);
        btnEdit.setText(row.funcname);
		form1.setChanged(false);
	}
	
	/****
     *
	 * 函数名：resourBlur
	 * 机 能： 验页面资源ID非空活唯一性
	 * 输 入：e  当前对象
	 * 输 出: 无
	 * 日 期：2014-06-16
	 * 作 者：刘子良
	 *
	 *****/
	function resourBlur(e){
		 var obj=e.sender;
		 if (e.isValid) {
	         if(obj.getValue()==""){
	           e.errorText = "页面资源ID不能为空";
	           e.isValid = false;
	         }else{
	            <%-- 验证资源ID标识格式是否正确 --%>
		        var reg = /^([a-zA-Z0-9_]|[.]){0,254}$/;
		        if(!reg.test(e.value)){
		            e.errorText= "资源标识不能为中文，可包含数字、字母或_的字符串!";
           			e.isValid = false;
		        }
		        else{
		            <%-- 验证资源ID是否存在 --%>
		            var resourceId=e.value;
					var sendData = {"resourceId":resourceId};
					var isExist;
					$.ajax({
						url: "com.bos.utp.framework.ResourceManager.findResourceById.biz.ext",
						type: 'POST',
						data: nui.encode(sendData),
						cache: false,
						async: false,
						contentType: 'text/json',
						success: function(text){
							var returnJson = nui.decode(text);
							if(returnJson.returnValue !='0'){
								e.errorText = "资源ID已存在,不能重复添加!";
	           					e.isValid = false;
							}else{
							  e.isValid = true;
							}
						},
						error: function(jqXHR, textStatus, errorThrown){}
					});	
		        }
	         
	         }
	     }
	}

	function onButtonEdit(){
	   		var btnEdit = nui.get("resource.funccode");
	    	nui.open({
                url: "<%=request.getContextPath() %>/utp/framework/menu/menu_function_select.jsp",
                title: "选择功能调用入口",
                width: 800, 
                height: 480,
                allowResize:false,
                ondestroy: function (action) {
                    //grid.reload();
                   if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.getData();
                        data = nui.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.funccode);
                            btnEdit.setText(data.funcname);
                            var funcName = nui.get("resource.funcname");
                            funcName.setValue(data.funcname);
                        }
                    } 
                }
            });
	    }


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


    //更新保存操作
	function formSaving(){
		/**
		if(form1.isChanged() == false){
			nui.confirm("数据未改变。<br/>是否关闭窗口?", "系统提示", function(action){
				if(action == "ok"){
					CloseWindow("noChanged");
				}else{}
			});
			return false;
		}
		form1.validate();
		if(form1.isValid()==false) return;
		*/
		var form1Data = form1.getData(false,true);
		
		var sendData = nui.encode(form1Data);
		$.ajax({
			url:"com.bos.utp.framework.ResourceManager.updateResourceManager.biz.ext",
			type:'POST',
			data:sendData,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var returnJson = nui.decode(text.returnCode);
				if(returnJson=='1'){
					nui.alert("编辑页面资源成功", "系统提示", function(action){
						if(action == "ok" || action == "close"){
							CloseWindow("saveSuccess");
						}
					});
				}
				else{
					nui.alert("编辑页面资源失败", "系统提示", function(action){
						if(action == "ok" || action == "close"){
							//CloseWindow("saveFailed");
							return;
						}
				  });
				}
			}
		});
		
	}

	function formCancel(){
		CloseWindow("cancel");
	}     
</script>
