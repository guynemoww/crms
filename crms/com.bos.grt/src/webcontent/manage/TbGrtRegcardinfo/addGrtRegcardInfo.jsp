<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-08-24
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>录入附属权证信息页面</title>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:110%;"  >
	<div title="权证信息" id="basicTab" name="basicTab" style="width:90%;height:auto;" >
		<div id="form" style="width:100%;height:auto;overflow:hidden;">
			<input name="suretyKeyId" class="nui-hidden" id="suretyKeyId"/>
			<input name="partyId" id="partyId" class="nui-hidden" />
			<input name="id" id="id" class="nui-hidden" />
			<input name="newsuretyKeyId" class="nui-hidden" id="newsuretyKeyId"/>
			<input name="no" id="no" class="nui-hidden" />
			
			<div class="nui-dynpanel" columns="4" id="table1">
				<label><span id="registerCertino">权利类别：</span></label>
				<input name="tbGrtRegcardinfo.cardType" id="tbGrtRegcardinfo.cardType" required="true" class="nui-dictcombobox nui-form-input" allowInput="false"  dictTypeId="XD_SXFS0002"/>
				<label><span id="registerCertino">登记权证编号</span></label>
				<input name="tbGrtRegcardinfo.registerCertiNo" id="tbGrtRegcardinfo.registerCertiNo"  required="true" class="nui-textbox nui-form-input"/>
				<label><span id="registerCertino">抵质押人名称</span></label>
				<div>
				<input name="tbGrtRegcardinfo.partyId" id="tbGrtRegcardinfo.partyId"  enabled="false"  required="true"  class="nui-textbox nui-form-input"/><a class="nui-button" id="clear" onclick="insertParty">查询</a>
				</div>
			</div>
			<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
			    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="save">保存</a>
		    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
 	nui.parse();
	
	var partyId ="<%=request.getParameter("partyId")%>";
	
	var suretyKeyId ="<%=request.getParameter("suretyKeyId")%>";
	
	var contractNum ="<%=request.getParameter("contractNum")%>";
	
	var collType ="<%=request.getParameter("collType")%>";
	
	var saveFlag ="<%=request.getParameter("saveFlag")%>";
	
	nui.get("suretyKeyId").setValue(suretyKeyId);
	
	nui.get("partyId").setValue(partyId);
    var form = new nui.Form("#form");
    search();
    function search() {
	//	var json=({"partyId":partyId});
	//    grid.load(json);
	    if(saveFlag == 1){
		    var json = nui.encode({"suretyKeyId":suretyKeyId});
		     $.ajax({
		        url: "com.bos.grt.manage.TbGrtRegcardinfo.getGrtRegCard.biz.ext",
		        type: 'POST',
		        data: json,
		  	    cache: true,
		       	async:false,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            var o = nui.encode(text.tbGrtRegcardinfo);
		            nui.get("tbGrtRegcardinfo.cardType").setValue(text.tbGrtRegcardinfo[0].CARD_TYPE);
					nui.get("tbGrtRegcardinfo.registerCertiNo").setValue(text.tbGrtRegcardinfo[0].REGISTER_CERTI_NO);
					nui.get("tbGrtRegcardinfo.partyId").setValue(text.tbGrtRegcardinfo[0].PARTY_NAME);
					nui.get("id").setValue(text.tbGrtRegcardinfo[0].PARTY_ID);
					nui.get("newsuretyKeyId").setValue(text.tbGrtRegcardinfo[0].SURETY_KEY_ID);
					nui.get("no").setValue(text.tbGrtRegcardinfo[0].REGISTER_CERTI_NO);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		     });
	    }
    }

	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
		if(nui.get("tbGrtRegcardinfo.partyId").value == "" || nui.get("tbGrtRegcardinfo.partyId").value == null){
			nui.alert("请将信息填写完整");
			return;
		}
		
		var o=form.getData();
		var str=o.tbGrtRegcardinfo.registerCertiNo;
		str=str.replace(/ /g,"");
		o.tbGrtRegcardinfo.registerCertiNo=str;
		o.tbGrtRegcardinfo.mainSuretyKeyId=suretyKeyId;
		o.tbGrtRegcardinfo.suretyKeyId=nui.get("newsuretyKeyId").value;
		o.tbGrtRegcardinfo.partyId=nui.get("id").value;
		var json=nui.encode(o);
		//git.mask();
	
	
	if(saveFlag == 1){
			//保存
			if(nui.get("no").value == nui.get("tbGrtRegcardinfo.registerCertiNo").value){
				//alert("相等");
			}else{
				if(suretyKeyId!="null"&&suretyKeyId!=""){//编辑时
					var json1={"suretyKeyId":suretyKeyId,"registerCertiNo":nui.get("tbGrtRegcardinfo.registerCertiNo").getValue()};
					msg = exeRule("RGRT_0009", "1", json1);//登记权证编号是否存在
					if (null != msg && '' != msg) {
						nui.alert(msg);
						return;
					}
				}
			}
			
			$.ajax({
			        url: "com.bos.grt.manage.TbGrtRegcardinfo.updateTbGrtRegcardinfo.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		var tabs = nui.get("tabs1");
			        		nui.alert("保存成功！");
			        		CloseWindow("ok");
			        	} else {
			        		nui.alert(text.msg);
			        	}
			        	git.unmask();
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});
		
		
		}else{
		
			if(suretyKeyId!="null"&&suretyKeyId!=""){//编辑时
				var json1={"suretyKeyId":suretyKeyId,"registerCertiNo":nui.get("tbGrtRegcardinfo.registerCertiNo").getValue()};
				msg = exeRule("RGRT_0009", "1", json1);//登记权证编号是否存在
				if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
				}
			}
		
			$.ajax({
		        url: "com.bos.grt.manage.TbGrtRegcardinfo.addTbGrtRegcardinfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.get("suretyKeyId").setValue(text.tbGrtRegcardinfo.suretyKeyId);
		        		var tabs = nui.get("tabs1");
		        		nui.alert("保存成功！");
		        		CloseWindow("ok");
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
	
	
		
	}
	
	
	//录入登记权证
		function insertParty(){
	    	nui.open({
		        url: nui.context + "/grt/manage/chioseParty/queryCusParty.jsp",
		        showMaxButton: true,
		        title: "选择客户",
		        width: 1000,
		        height: 500,
		        ondestroy: function (action) {  
		             if(action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.getData();
		                data = nui.clone(data);
		              	nui.get("tbGrtRegcardinfo.partyId").setValue(data.partyName);
		              	nui.get("id").setValue(data.partyId);
		            }else{
		            	search();
		            }
		        }
		    }); 
		}
	</script>
</body>
</html>
