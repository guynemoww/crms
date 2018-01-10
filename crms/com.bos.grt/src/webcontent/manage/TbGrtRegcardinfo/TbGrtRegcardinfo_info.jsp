<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div title="权证信息" id="basicTab" name="basicTab" style="width:100%;height:auto;" >
		<div id="form5" style="width:100%;height:auto;overflow:hidden;">
			<input name="tbGrtRegcardinfo.suretyId" class="nui-hidden" id="suretyId"/>
			<input name="tbGrtRegcardinfo.partyId" id="tbGrtRegcardinfo.partyId" class="nui-hidden" />
			<div class="nui-dynpanel" columns="4" id="table1">
				<label><span id="registerCertino">抵质押人名称：</span></label>
				<input name="tbGrtRegcardinfo.partyName" id="tbGrtRegcardinfo.partyName" enabled="false" class="nui-text nui-form-input" />
				<label><span >登记权证编号</span></label>
				<input name="registerCertiNo" required="true" class="nui-textbox nui-form-input"/>
				<label >登记机构名称：</label>
				<input name="regOrgName" class="nui-textbox nui-form-input" vtype="maxLength:60" id="tbGrtRegcardinfo.regOrgName" />
				<label >登记金额：</label>
				<input name="regOrgMoney" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" id="tbGrtRegcardinfo.regOrgMoney" dataType="currency" />
				<label >登记生效日期：</label> 
				<input name="cardRegDate"  class="nui-datepicker nui-form-input"  allowinput="false"/>
				<label >登记到期日期：</label>
				<input name="regDueDate"  class="nui-datepicker nui-form-input"  allowinput="false"/>
			    <label>保管机构：</label>
				<input name="saveOrg"  class="nui-buttonEdit" allowInput="false"  onbuttonclick="selectEmpOrg" dictTypeId="org"/>
			</div>
		</div>
		<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
			<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		</div>
	</div>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form5");
		
		var suretyId="<%=request.getParameter("suretyId")%>";
		var view="<%=request.getParameter("view")%>";
		var partyId="<%=request.getParameter("partyId")%>";
		
		nui.get("tbGrtRegcardinfo.partyId").setValue(partyId);
		
		if(view==1){
			form.setEnabled(false);
			nui.get("btnSave").hide();
			
		}
		
		function initPage(){
			var json=nui.encode({"suretyId":suretyId});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.manage.TbGrtRegcardinfo.getTbGrtRegCardBySurtyId.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		var o=nui.decode(text.tbGrtRegcardinfo);
					form.setData(o);
					nui.get("suretyId").setValue(suretyId);
					initParty();//初始化表单的客户名
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
	    initPage();
	    
	    function initParty(){
	    	var json=nui.encode({"tbCsmParty":{"partyId":partyId}});
	    	$.ajax({
	        	url: "com.bos.grt.grtParty.partyInfo.getPartyById.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		nui.get("tbGrtRegcardinfo.partyName").setValue(text.tbCsmParty.partyName);;
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
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
			git.mask();
			$.ajax({
		        url: "com.bos.grt.manage.TbGrtRegcardinfo.addTbGrtRegcardinfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        		nui.get("suretyKeyId").setValue(text.tbGrtRegcardinfo.suretyKeyId);
		        		var tabs = nui.get("tabs1");
		        		tabs.reloadTab(); 
		        		//CloseWindow("ok");
		        	} else {
		        		nui.alert(text.msg);
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		//选择机构
		function selectEmpOrg(e){
			var btnEdit = this;
			 nui.open({
	            url:  nui.context + "/utp/org/employee/select_all_org_tree.jsp",
	            showMaxButton: false,
	            title: "选择保管机构",
	            width: 350,
	            height: 450,
	            ondestroy: function (action) {            
	            	if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
			                btnEdit.setValue(data.orgcode);
		                    btnEdit.setText(data.orgname);
	                }
	            }
	            }
	        });
		}
		
	</script>
</body>
</html>