<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:110%;" onbeforeactivechanged="tabChange" >
	<div title="权证信息" id="basicTab" name="basicTab" style="width:90%;height:auto;" >
		<div id="form5" style="width:100%;height:auto;overflow:hidden;">
		
			<input name="tbGrtRegcardinfo.suretyKeyId" class="nui-hidden" id="suretyKeyId"/>
			<input name="tbGrtRegcardinfo.partyId" id="tbGrtRegcardinfo.partyId" class="nui-hidden" />
			
			<div class="nui-dynpanel" columns="4" id="table1">
				<label><span id="registerCertino">权证类别：</span></label>
				<input name="tbGrtRegcardinfo.cardType" id="tbGrtRegcardinfo.cardType" required="true" class="nui-dictcombobox nui-form-input" allowInput="false"  dictTypeId="XD_SXFS0002"/>
				<label><span id="registerCertino">登记权证编号</span></label>
				<input name="tbGrtRegcardinfo.registerCertiNo" id="tbGrtRegcardinfo.registerCertiNo"  required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
				<label><span id="registerCertino">抵质押人名称</span></label>
				<input name="tbGrtRegcardinfo.partyName" id="tbGrtRegcardinfo.partyName" enabled="false" class="nui-text nui-form-input" />
				<label id="regOrgName">登记机构名称：</label>
				<input name="tbGrtRegcardinfo.regOrgName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="tbGrtRegcardinfo.regOrgName" />
				<label id="laidupValue">登记金额：</label>
				<input name="tbGrtRegcardinfo.regOrgMoney" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" id="tbGrtRegcardinfo.regOrgMoney" dataType="currency" />
				<label id="">登记生效日期：</label> 
				<input name="tbGrtRegcardinfo.cardRegDate"  required="true" class="nui-datepicker nui-form-input"  ondrawdate="oncardRegDate" onvaluechanged="oncardRegDate" id="tbGrtRegcardinfo.cardRegDate"  allowinput="false"/>
				<label id="">登记到期日期：</label>
				<input name="tbGrtRegcardinfo.regDueDate" required="true" class="nui-datepicker nui-form-input"  ondrawdate="onregDueDate" onvaluechanged="onregDueDate" id="tbGrtRegcardinfo.regDueDate" allowinput="false"/>
			    <label>保管机构：</label>
				<input name="tbGrtRegcardinfo.saveOrg" id="tbGrtRegcardinfo.saveOrg" required="true" class="nui-buttonEdit" allowInput="false"  onbuttonclick="selectEmpOrg"  dictTypeId="org"/>
			
			</div>
		</div>
	
	<div style="width:auto;background-color:#c9c9c9">  <span style="color:#424141;font-weight:bold;font-family:Tahoma, Verdana, 宋体;font-size:9pt">附属权证信息</span></div>
	<!-- 新增权证附属信息列表 -->	    
	<div id="grid" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.regmanage.collateralinandoutin.getAuxiliaryRegCard.biz.ext"
		dataField="arrays"allowAlternating="true"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="15" sortMode="client">
		<div property="columns">
	        <div field="PARTYNAME" allowSort="true" headerAlign="center">抵质押人名称</div>
	        <div field="CARDTYPE" allowSort="true" dictTypeId="XD_SXFS0002" headerAlign="center" >权证类别</div>
			<div field="REGISTERCERTINO" allowSort="true" headerAlign="center">权证编号</div>
		</div>
	</div>
	</div>
</div>	

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form5");
		form.setEnabled(false);
		var grid = nui.get("grid");
		var suretyKeyId="<%=request.getParameter("suretyKeyId")%>";
		var view="<%=request.getParameter("view")%>";
		
		var partyId="<%=request.getParameter("partyId")%>";
		if(view==1){
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
		var data = form.getData(); //获取表单多个控件的数据
		grid.load({"suretyKeyId":suretyKeyId});
		function initPage(){
			addSuretyList();
			if(suretyKeyId=="null"||suretyKeyId==""){
				nui.get("tbGrtRegcardinfo.partyId").setValue(partyId);
				initParty();//初始化表单的客户名
			}else{
				var json=nui.encode({"tbGrtRegcardinfo":{"suretyKeyId":suretyKeyId}});
				git.mask();
				$.ajax({
		        	url: "com.bos.grt.manage.TbGrtRegcardinfo.getTbGrtRegcardinfo.biz.ext",
		        	type: 'POST',
		        	data: json,
		        	cache: false,
		        	contentType:'text/json',
		        	success: function (text) {
		        		var o=nui.decode(text);
						form.setData(o);
						nui.get("tbGrtRegcardinfo.partyId").setValue(partyId);
						initParty();//初始化表单的客户名
		        		git.unmask();
		        	},
		        	error: function (jqXHR, textStatus, errorThrown) {
		            	nui.alert(jqXHR.responseText);
		        	}
				});
			}
			
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
			var str=o.tbGrtRegcardinfo.registerCertiNo;
			str=str.replace(/ /g,"");
			o.tbGrtRegcardinfo.registerCertiNo=str;
			suretyKeyId = nui.get("suretyKeyId").getValue();
			if(suretyKeyId!="null"&&suretyKeyId!=""){//编辑时
				var json1={"suretyKeyId":suretyKeyId,"registerCertiNo":nui.get("tbGrtRegcardinfo.registerCertiNo").getValue()};
				msg = exeRule("RGRT_0004", "1", json1);//登记权证编号是否存在
				if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
				}
			}else{
				var json4={"registerCertiNo":nui.get("tbGrtRegcardinfo.registerCertiNo").getValue()};
				msg = exeRule("RGRT_0009", "1", json4);//登记权证编号是否存在
				if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
				}
			}
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
		        	//	nui.alert(text.msg);
		        		nui.get("suretyKeyId").setValue(text.tbGrtRegcardinfo.suretyKeyId);
		        		var tabs = nui.get("tabs1");
		        		nui.alert("保存成功！");
		        		tabs.reloadTab(); 
		        		//CloseWindow("ok");
		        	} else {
		        		nui.alert("操作失败！");
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		//权证登记日期判断
		function oncardRegDate(){
			var cardRegDate = nui.get("tbGrtRegcardinfo.cardRegDate").getValue();//权证登记日期
			var regDueDate = nui.get("tbGrtRegcardinfo.regDueDate").getValue();//权证登记到期日期
			if(cardRegDate!=""&&regDueDate!=""){
				if(nui.parseDate(cardRegDate)-nui.parseDate(regDueDate)>0){
					//权证登记到期日期小于权证登记日期
					nui.alert("权证登记到期日期应该大于权证登记日期");
					nui.get("tbGrtRegcardinfo.cardRegDate").setValue("");
					return false;
				}
			}else{
				return true;
			}	
		}
		
		//权证登记到期日期判断
		function onregDueDate(){
			var cardRegDate = nui.get("tbGrtRegcardinfo.cardRegDate").getValue();//权证登记日期
			var regDueDate = nui.get("tbGrtRegcardinfo.regDueDate").getValue();//权证登记到期日期
			if(cardRegDate!=""&&regDueDate!=""){
				if(nui.parseDate(cardRegDate)-nui.parseDate(regDueDate)>0){
					//权证登记到期日期小于权证登记日期
					nui.alert("权证登记日期应该大于权证登记到期日期");
					nui.get("tbGrtRegcardinfo.regDueDate").setValue("");
					return false;
				}
			}else{
				return true;
			}
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
		
		//选择权利类别
		function selectGuarantyrightNm(e){
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
		/**
		 * 押品列表信息
		 */
		function addSuretyList() {
			var tabs = nui.get("tabs1");
			var tab = {title: "押品信息",name: "collateral",url: nui.context+"/grt/manage/collateral/collateral_view.jsp?"};
			tab = tabs.addTab(tab);            
		}
		
		function tabChange(e) {
			var suretyKeyId=nui.get("suretyKeyId").getValue();
			if(e.name=="collateral"){
				var tabs = nui.get("tabs1");
				var tabName = tabs.getTab(e.name);
				var tabUrl = tabName.url.split("?")[0];
				tabUrl = tabUrl +"?view="+view+"&partyId="+partyId+"&suretyKeyId="+suretyKeyId;
				tabName.url = tabUrl;
				tabs.reloadTab(tabName);//更新tab标签页
			}
		}
		function remove(){
		
    	var rows = grid.getSelecteds();
    	var row = grid.getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
		    	var json=nui.encode({"suretyKeyId":row.SURETYKEYID});
                $.ajax({
                    url: "com.bos.grt.regmanage.collateralinandoutin.deleteRegCard.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert("删除成功！");
                    	search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
	      }else{
	        alert("请选中一条记录");
	      }
    }
     function search() {
    	var grid = nui.get("grid");
		var json=({"suretyKeyId":suretyKeyId,"partyId":partyId,"view":view});
	    grid.load(json);
    }
     function link() {
        suretyKeyId = nui.get("suretyKeyId").getValue();
        if(suretyKeyId==""||suretyKeyId=="null"){
    		alert("请先录入主权证基本信息!");
    		return;
    	}
    	var contractNum;
    	var collType;
    	var rows = nui.get("grid").getData();
    	for(var i=0;i<rows.length;i++){
    		contractNum=rows[i].CONTRACT_NUM;
    		collType=rows[i].COLL_TYPE;
    		break;
    	}
        nui.open({																				
            url: nui.context+"/grt/manage/TbGrtRegcardinfo/addGrtRegcardInfo.jsp?partyId="+partyId+"&suretyKeyId="+suretyKeyId+"&contractNum="+contractNum+"&collType="+collType,
            title: "附属权证信息", 
            width: 800, 
        	height: 400,
        	allowResize: false,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
     function update() {
    	var rows = grid.getSelecteds();
    	var row = grid.getSelected();
        if (row) {
        	var skId = row.SURETYKEYID;
	        nui.open({																				
	            url: nui.context+"/grt/manage/TbGrtRegcardinfo/addGrtRegcardInfo.jsp?partyId="+partyId+"&suretyKeyId="+skId+"&saveFlag="+1,
	            title: "附属权证信息", 
	            width: 800, 
	        	height: 400,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                if(action=="ok"){
	                    search();
	                }
	            }
	        });
        }else{
         	alert("请选中一条记录");	
        }
    }
	</script>
</body>
</html>