<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>共有人信息新增、修改、查看</title>
</head>
<body>
	<div id="form4" style="width:99%;height:auto;overflow:hidden;">
		<input name="item.commonId" id="commonId" class="nui-hidden"  />
		<input name="item.suretyId" id="suretyId" class="nui-hidden"  value="<%=request.getParameter("suretyId") %>" />
		<input name="item._entity" id="item._entity" class="nui-hidden"  value="com.bos.dataset.grt.TbGrtCommonPerson" />
		
		<div id="panel1" class="nui-dynpanel" columns="4">
			<label>共有人名称：</label>
			<input name="item.commonName"  required="true" class="nui-buttonEdit nui-form-input" id="item.commonName" onbuttonclick="chooiseParty" allowinput="false"/>	
			
			<label>证件类型：</label>
			<input name="item.certificateTypeCd" id="item.certificateTypeCd" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false"  enabled="false"/>
			
			<label>证件号码：</label>
			<input name="item.certificateCode" id="item.certificateCode" required="true"  class="nui-textbox nui-form-input"  vtype="maxLength:18" enabled="false"/> 
			
			<label>共有方式：</label>
			<input name="item.commonWay"   required="true" class="nui-dictcombobox nui-form-input"  dicttypeid="XD_YWDB0137"/>
					
			<label>共有份额（%）：</label>
			<input name="item.commonLot"  required="true" class="nui-textbox nui-form-input" vtype="range:0,100;maxLength:3"/>
			
			<label>创建日期：</label>
			<input name="item.createTime" class="nui-textbox nui-form-input"  value="<%=com.bos.pub.GitUtil.getBusiDate() %>"  enabled="false" />
			
			<label>更新日期：</label>
			<input name="item.updateTime" id="updateTime" class="nui-textbox nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  enabled="false" />
		</div>
	</div>
					
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnsave">保存</a>
	</div>
		    
	<script type="text/javascript">
		nui.parse();
		
		var form = new nui.Form("#form4");
		
		var view="<%=request.getParameter("view") %>";
		
		if("1" == view){
			nui.get("btnsave").hide();
			form.setEnabled(false);
		}
		
		//init();	
		
		initPage();
		
		/**
		 * 初始化值
		 */
		function initPage(){
			if(view==null||view=="null"){
				nui.get("commonId").setValue("");
			}else if(view=="1"){
				//查看操作
				viewPageInit("<%=request.getParameter("commonId") %>")
			}else if(view=="0"){
				//编辑操作
				viewPageInit("<%=request.getParameter("commonId") %>");
			}
		}
		
		
		function viewPageInit(commonId){
			var json=nui.encode({"item":{"commonId":commonId,"_entity":"com.bos.dataset.grt.TbGrtCommonPerson"}});
			git.mask();
			$.ajax({
	        	url: "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
					form.setData(text);
					nui.get("item.commonName").setText(text.item.commonName);
					nui.get("item._entity").setValue("com.bos.dataset.grt.TbGrtCommonPerson");
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
		
		
		/**
		 * 保存
		 */
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtManage.mortgageCURD.saveOrUpdate.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	nui.alert(text.msg);
		        	git.unmask();
		        	CloseWindow("ok");
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		/**
		 * 选择保证人        2015.06.27选择客户的时候对partyId赋值 以便保证基本表插入相应的数据
		 */
		function chooiseParty(){
			var partyId;
	    	var sortType;
	    	nui.open({
		        url: nui.context + "/grt/manage/chioseParty/queryComParty.jsp",
		        showMaxButton: true,
		        title: "选择共有人",
		        width: 800,
		        height: 500,
		        ondestroy: function (action) {            
		            var iframe = this.getIFrameEl();
		            var data = iframe.contentWindow.getData();
		            data = nui.clone(data);
		            nui.get("item.commonName").setText(data.partyName);
		            nui.get("item.commonName").setValue(data.partyName);
		            nui.get("item.certificateTypeCd").setValue(data.certType);
		            nui.get("item.certificateCode").setValue(data.certNum);
		            
		        }
		    }); 
		}
	</script>
</body>
</html>
