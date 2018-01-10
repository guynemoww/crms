<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-top:5px;" >
				<input name="item.appDetailId" id="item.appDetailId" class="nui-hidden"/>
				<input name="item.cdInfoId" id="item.cdInfoId" class="nui-hidden"/>
				<input name="item.posicode" id="item.posicode" class="nui-hidden"/>
				<!-- <label>上一岗位分类结果</label> -->
				<input name="item.beforeResult" id="item.beforeResult" class="nui-hidden" />
				<div class="nui-dynpanel" columns="4">
					<label>客户名称</label>
					<input id="item.partyName" name="item.partyName" class="nui-text nui-form-input" value="<%=request.getParameter("partyName2")%>"/>
					
					<label>调整前分类结果</label>
					<input name="item.lastClsResult" id="item.lastClsResult" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0001" enabled="false"/>
					
					<label>调整后分类结果</label>
					<input name="item.approveResult" id="item.approveResult" required = "true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0001" allowInput="false" />
					
					<label>分类调整意见</label>
					<input name="item.approveOpinion" required="true" class="nui-textarea nui-form-input" vtype="maxLength:200" />
					
					<label>经办人</label>
					<input name="item.userNum" class="nui-text nui-form-input" dictTypeId="user" value="<%=((UserObject)session.getAttribute("userObject")).getUserId() %>"/>
					
					<label>经办机构</label>
					<input name="item.orgNum" class="nui-text nui-form-input" dictTypeId="org" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode") %>"/>
				</div>
			</div>
			<div style="margin-top:10px;">
				<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
				<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			//git.mask("form1");
	  		var form = new nui.Form("#form1");
			var cdInfoId = "<%=request.getParameter("cdInfoId")%>";
			var workItemId = "<%=request.getParameter("workItemId")%>";
			var clsResult = "<%=request.getParameter("clsResult")%>";
			var lastClsResult = "<%=request.getParameter("lastClsResult")%>";
		
			nui.get("item.beforeResult").setValue(clsResult);//上一岗位分类结果
			nui.get("item.lastClsResult").setValue(lastClsResult);//上一次分类结果
			nui.get("item.cdInfoId").setValue(cdInfoId);
		    nui.get("item.posicode").setValue(workItemId);
			
			//reload();
			function reload(){
				git.mask();
				//初始化页面
		    	var o = form.getData();
				var json = nui.encode(o);
				$.ajax({
			        url: "com.bos.risk.sort.querySortAdjust.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
		               	form.setData(text);
			        	git.unmask("form1");
			        }
		    	});
				git.unmask();
			}
			
			function save(){
				form.validate();
				if (form.isValid() == false) {
					nui.alert("请将信息填写完整");
					return;
				}
				if("<%=request.getParameter("lastClaMethod")%>" == "auto" & parseInt(nui.get("item.approveResult").getValue(),10) <= parseInt(lastClsResult,10)){
					 var lastClsResultName = null;
					 var json = nui.encode({dicttypeid:"XD_FLCD0001",dictid:lastClsResult});
				     $.ajax({
				        url: "com.bos.pub.dict.getDictName.biz.ext",
				        type: 'POST',
				        data: json,
				        cache: false,
				        contentType:'text/json',
				        success: function (text) {
				        	nui.alert("分类等级不能高于或等于上次自动分类结果【"+text.dictname+"】");
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				            git.unmask();
				            nui.alert(jqXHR.responseText);
				        }
				     });
					return;
				}
				var o = form.getData();
				var json = nui.encode(o);
				$.ajax({
					url:"com.bos.risk.sort.editSortAdjust.biz.ext",
					type:'POST',
					data:json,
					cache: false,
					contentType:'text/json',
					success:function(text){
						git.unmask("form1");
						if(text.msg){
		            		alert(text.msg);
		            		return;
		            	} else {
		            		//alert("保存成功!");
		            		CloseWindow();
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
