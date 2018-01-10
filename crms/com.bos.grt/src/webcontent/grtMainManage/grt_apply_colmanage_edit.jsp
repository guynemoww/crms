<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-29

  - Description:TB_GRT_GENERAL_APPINFO, com.bos.dataset.grt.TbGrtGeneralAppinfo-->
<head>
	<%@include file="/common/nui/common.jsp" %>
		<%
		String urlPath ="/grt/manage/twouse/twouse_list.jsp";
		String myBankUrl="/grt/manage/assess/myBankAssess.jsp";
		String outerUrl="/grt/manage/assess/outerAssess.jsp";
		%>
	<title>编辑业务申请下的抵质押品</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:110%;">
		<div title="抵押物信息" id="basicTab" name="basicTab" style="width:90%;height:auto;">
		<div class="nui-dynpanel" columns="1" id="table2"  style="width:99.5%;">
			<fieldset>
			  	<legend>
			    	<span>概况信息</span>
			    </legend>
				<div id="form1" style="width:100%;height:auto;overflow:hidden;">
					<input name="tbGrtGuarantybasic.suretyId"  class="nui-hidden"   id="applyId"    value="<%=request.getParameter("suretyId") %>"/>
					<input name="relationId"  class="nui-hidden" id="relationId" value="<%=request.getParameter("relationId") %>" />
					<input name="party.partyId" class="nui-hidden"  id="party.partyId"  />
					<div id="panel1" class="nui-dynpanel" columns="4">
						<label>抵质押编号：</label>
						<input name="tbGrtGuarantybasic.suretyNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="suretyNum" enabled="false" />
									
						<label>抵质押人名称：</label>
						<input name="party.partyName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:300" id="party.partyName" allowinput="false" enabled="false"/>
								
						<label>抵质押物分类：</label>
						<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" id="tbGrtGuarantybasic.collType" enabled="true" dictTypeId="CDZC0005" />
								
						<label>抵质押类型：</label>
						<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:30" id="tbGrtGuarantybasic.sortType"  dictTypeId="XD_YWDB02" enabled="false"/>
						
						<label>评估方式：</label>
						<input name="tbGrtGuarantybasic.assessForm" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="assessForm" dictTypeId="YP_GLCD0010" />
						
						<label>评估日期：</label>
						<input name="tbGrtGuarantybasic.assessDate" required="false" class="nui-datepicker nui-form-input"  id="assessDate" format="yyyy-MM-dd" />
						
						<label>评估价值：</label>
						<input name="tbGrtGuarantybasic.assessValue" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" id="assessValue" dataType="currency" onblur="setRate"/>
						
						<label>币种：</label>
						<input name="tbGrtGuarantybasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001" />
						
						<label>我行确认价值：</label>
						<input name="tbGrtGuarantybasic.mybankAffirmValue" id="mybankAffirmValue"  required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" />
						
						<label>权利价值：</label>
						<input name="tbGrtGuarantybasic.mortgageValue" id="mortgageValue" dataType="currency" required="true" class="nui-textbox nui-form-input"  vtype="float;maxLength:26;" />
						
						<label>我行已设定担保额：</label>
						<input name="tbGrtGuarantybasic.mybankSetValue" id="mybankSetValue" dataType="currency" required="true" class="nui-textbox nui-form-input"  vtype="float;maxLength:26;" />	
						
						<label>本次担保金额：</label>
						<input name="tbGrtGuarantybasic.suretyAmt" id="suretyAmt" dataType="currency" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" onblur="setRate"/>	
						
						<label>抵押物状态：</label>
						<input name="tbGrtGuarantybasic.mortgageStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" id="valuationForm" dicttypeid="XD_YWDB0132"  />
						
						<label>抵质押率(%)：</label>
						<input name="tbGrtGuarantybasic.mortgageRate" id="mortgageRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" enabled="false"/> 
						
						<!-- <label>是否存在共有人：</label>
						<input name="tbGrtGuarantybasic.ifOtherCommon" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.ifOtherCommon" vtype="maxLength:1" dictTypeId="XD_0002" onItemclick="publicChange" /> -->
							
						<label>经办机构：</label>
						<input name="tbGrtGuarantybasic.orgNum" required="false" enabled="false" class="nui-text nui-form-input" vtype="maxLength:20" 
							value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" />
								
						<label>经办人：</label>
						<input name="tbGrtGuarantybasic.userNum" required="false" enabled="false" class="nui-text nui-form-input" vtype="maxLength:50" 
							value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user"/>
						
						<label>创建日期：</label>
						<input name="tbGrtGuarantybasic.createTime" required="false" class="nui-datepicker nui-form-input" id="tbGrtGuarantybasic.createTime" enabled="false" />
				
						<label>更新日期：</label>
						<input name="tbGrtGuarantybasic.updateTime" required="false" class="nui-datepicker nui-form-input"  id="tbGrtGuarantybasic.updateTime" enabled="false" />
							
						<!-- <label>评估方法：</label>
						<input name="tbGrtGuarantybasic.valuationForm" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" id="valuationForm" dicttypeid="YP_GLCD0011"  />
								
						<label>评估到期日：</label>
						<input name="tbGrtGuarantybasic.assessEndDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessEndDate" format="yyyy-MM-dd" />
						-->
					</div>
					<div style="border-bottom:0;width:99.5%; padding-right:30px;">
						<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
					</div>
				</div>
			</fieldset>
		</div>
			<div class="nui-dynpanel" columns="1" id="table1" style="style="width:99.5%;">
				<fieldset>
				  	<legend>
				    	<span>抵押物详细信息</span>
				    </legend>
				    	<jsp:include page="<%=urlPath %>"></jsp:include>
				 </fieldset>
			 </div>
		</div>
		<div title="评估信息">
	        <div id="form2" style="width:100%;height:auto;overflow:hidden;">
				<label class="nui-hidden">担保品ID：</label>
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:32" id="suretyId2"/>
				<div class="nui-dynpanel" columns="1" id="table3"  style="width:99.5%;">
					<fieldset>
					  	<legend>
					    	<span>外部评估</span>
					    </legend>
					    <jsp:include page="<%=outerUrl %>"></jsp:include>
					</fieldset>
				</div>
				<div class="nui-dynpanel" columns="1" id="table4"  style="width:99.5%;">
					<fieldset>
					  	<legend>
					    	<span>我行评估</span>
					    </legend>
					    	<jsp:include page="<%=myBankUrl%>"></jsp:include>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		var form2 = new nui.Form("#form2");
		
		var view="<%=request.getParameter("view") %>";
	    
	    //押品ID
	    var suretyId="<%=request.getParameter("suretyId") %>";
	    //押品和业务关联表ID
	    var relationId="<%=request.getParameter("relationId") %>";
	    
		/**
		 * 计算抵质押率
		 */
		function setRate(){
			 //本次担保金额
			var suretyAmt = nui.get("suretyAmt").getValue();
			//我行已设定担保金额			
			var mybankSetValue = nui.get("assessValue").getValue();
			if(mybankSetValue!=""&&suretyAmt!=""){
				if(parseFloat(mybankSetValue)>=parseFloat(suretyAmt)){
					var rate= Math.round(suretyAmt/mybankSetValue*10000)/100;
					nui.get("mortgageRate").setValue(rate);
				}else{
					nui.get("suretyAmt").setValue("");
					nui.get("assessValue").setValue("");
					nui.get("mortgageRate").setValue("");
					alert("本次担保金额不能大于我行设定担保额!");
				}
			} 
		}
	    
	    
		if (view=="1") {
			form.setEnabled(false);
			form2.setEnabled(false);
			nui.get("btnSave").hide();
			//nui.get("btnSave2").hide();
		}
		//设置抵质押类型的选择值
	    nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
	    
	    //抵质押类型、动态加载抵质押物
		var sortType;
		
		var parentSortType;
		
		/**
		 * 基础信息的初始化form表单方法
		 */
		function initForm() {
			var json=nui.encode({"suretyId":suretyId,"relationId":relationId});
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtMainManage.grtApply.getApplyTbGrtGuarantybasic.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
		        		form.setData(text);
		        		//登记信息
						addTab3(view);
		        		sortType=text.tbGrtGuarantybasic.sortType;
		        		<%-- nui.get("partyName").setValue(text.tbGrtGuarantybasic.partyName);
		        		nui.get("partyName").setText(text.tbGrtGuarantybasic.partyName);
		        		nui.get("assessOrg").setText(text.tbGrtGuarantybasic.assessOrg);
		        		parentSortType=text.tbGrtGuarantybasic.parentSortType;
		        		//申请传递的本次担保债权金额
		        		if("<%=request.getParameter("occupationAmount") %>" == "null" || "" == "<%=request.getParameter("occupationAmount") %>" || "<%=request.getParameter("occupationAmount") %>" == null){
		        			nui.get("occupationAmount").setValue(0);
		        		}else{
			        		nui.get("occupationAmount").setValue("<%=request.getParameter("occupationAmount") %>");
		        		}
		        		//比例
		        		if("<%=request.getParameter("parentSortType") %>"=="01010200" || "<%=request.getParameter("sortType") %>"=="01020201" || "<%=request.getParameter("sortType") %>"=="01020202" || "<%=request.getParameter("sortType") %>"=="01020203"){
			        		if("<%=request.getParameter("guaMoneyProportion") %>" == "null" || "<%=request.getParameter("guaMoneyProportion") %>" == "" || "<%=request.getParameter("guaMoneyProportion") %>" == null){
			        			nui.get("guaProportion").setValue(0);
			        		}else{
				        		nui.get("guaProportion").setValue("<%=request.getParameter("guaMoneyProportion") %>");
			        		}
		        		}--%>
		        		//根据押品类型显示抵押物页面
			        	var json  = nui.encode({"sortArgument":{"sortType":sortType}});
			        	
			        	$.ajax({
			        		url: "com.bos.grt.grtMainManage.grtApply.getApplyCollUrlBySortType.biz.ext",
			        		type:'POST',
			        		data: json,
				    		cache: false,
				   		    contentType:'text/json',
				   		    success: function (text) {
				    			var typeUrl = text.typeUrl;
				    			typeUrl = typeUrl+"?suretyId="+suretyId +"&sortType=" + sortType + "&parentSortType=" + parentSortType;
				    			//addTab(typeUrl);//根据查询出的url加载tab标签
				    			var tabs = nui.get("tabs1");
				    			//如果共有人不为是，删除共有人页签
					    		/* if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()!="1"){
				 					var publicInfo = tabs.getTab("publicInfo");
				 					tabs.removeTab(publicInfo);//删除publicInfo
				 				}else{
				 					addTab5();
				 				} */
				 
				    		},
				            error: function () {
				            	nui.alert("操作失败！");
				            }
			        	}); 
			        	git.unmask();
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		initForm();

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
		        url: "com.bos.grt.grtMainManage.grtOuter.addOuterTbGrtGuarantybasic.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	nui.alert(text.msg);
		        	git.unmask();	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		/**
		 * 登记权证信息
		 */
		function addTab3(view) {
			var tabs = nui.get("tabs1");
			var tab = {title: "登记权证信息",name: "regInfo",url: nui.context+"/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_list.jsp?suretyId="+suretyId+"&view="+view};
			tab = tabs.addTab(tab);            
			var el = tabs.getTabBodyEl(tab);
			//el.innerHTML = 4;
		}
		
		/**
		 * 共有人页签
		 */
		function addTab5() {
			var tabs = nui.get("tabs1");
			var tab = {title: "共有人信息",name: "publicInfo",url: nui.context+"/grt/manage/TbGrtGuarantypublic/TbGrtGuarantypublic_list.jsp"};
			tab = tabs.addTab(tab);            
			var el = tabs.getTabBodyEl(tab);
			//el.innerHTML = 5;
		}
		
		/**
		 * 共有人选择
		 */
		function publicChange(){
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="0"){
				var json = nui.encode({"tbGrtGuarantypublic":{"suretyId":suretyId}});
	 			git.mask();
	 			$.ajax({
	        		url: "com.bos.grt.manage.TbGrtGuarantypublic.getTbGrtGuarantypublicList.biz.ext",
	        		type: 'POST',
	        		data: json,
	        		cache: false,
	        		contentType:'text/json',
	        		success: function (text) {
	        			if(text.tbGrtGuarantypublics.length>0){
	        				nui.alert("存在共有人信息,不允许修改!");
	        				nui.get("tbGrtGuarantybasic.ifOtherCommon").setValue("1");
	        			}
	        			git.unmask();
	        		},
	        		error: function (jqXHR, textStatus, errorThrown) {
	            		nui.alert(jqXHR.responseText);
	        		}
				});
			}	
		}
	 	
	 	/**
		 * 评估机构
		 */
		function chooiseAssessOrg(){
			nui.open({
				url: nui.context+"/grt/grtPublic/grtAssessCsm.jsp",
				title: "选择评估机构", 
				width: 800, 
				height: 600,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action != "ok"){
						nui.get("tbGrtGuarantybasic.assessorgPartyId").setValue(action[0]);
						nui.get("assessOrg").setValue(action[2]);
						nui.get("assessOrg").setText(action[2]);
					}
				}
			});
		}
		
		/*
		 *点击窗口中的关闭按钮
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
		
		//抵押物详细信息
		var form3 = new nui.Form("#form3");
		var grid = nui.get("grid3");
		if ("1" == "<%=request.getParameter("view") %>"){
			nui.get("add").hide();
			nui.get("edit0").hide();
			nui.get("remove").hide();
		}
	    function search3() {
	        grid.load({"tbGrtHouse":{"suretyId":suretyId}});
	    }
	    search3();
				
	    function reset(){
			form3.reset();
		}
		
	    function add() {
	    	if(suretyId==""){
	    		nui.alert("请先保存概况信息!");
	    	}else{
	    		nui.open({
	            url: nui.context+"/grt/manage/twouse/twouse_add.jsp?sortType="+"<%=request.getParameter("sortType")%>"+"&parentSortType="+"<%=request.getParameter("parentSortType")%>"+"&suretyId="+suretyId,
	            title: "新增", 
	            width: 800, 
	        	height: 600,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                if(action=="ok"){
	                    search3();
	                }
	            }
	        	}); 
	    	}
	    }
	    
	    function edit(v) {
	        var row = grid.getSelected();
	        var title1;
	        if(v == "0"){
				title1 = "编辑";        	
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {
	            nui.open({
	                url: nui.context+"/grt/manage/twouse/twouse_edit.jsp?suretyKeyId="+row.suretyKeyId+"&view="+v+"&suretyId="+row.suretyId,
	                title: title1, 
	                width: 800,
	        		height: 600,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                    //iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
						search3();
	                }
	            });
	        } else {
	            alert("请选中一条记录");
	        }
	        
	    }
	    
	    function remove() {
	        var row = grid.getSelected();
	        if (row) {
	        	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"tbGrtHouse":{"suretyKeyId":
	            		row.suretyKeyId,version:row.version}});
	            	git.mask();
	                $.ajax({
	                     url: "com.bos.grt.manage.house.delTbGrtHouse.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    	}
	                        search3();
	                        git.unmask();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
	            }); 
	        } else {
	            alert("请选中一条记录");
	        }
	    }
		
		//评估信息
		var grid4 = nui.get("grid4");
		var grid5 = nui.get("grid5");
		if ("1" == "<%=request.getParameter("view") %>"){
			nui.get("addMyBankAssess").hide();
			nui.get("editMyBankAssess").hide(); 
			nui.get("removeMyBankAssess").hide();
			nui.get("addOuterAssess").hide();
			nui.get("editOuterAssess").hide();
			nui.get("removeOuterAssess").hide();
		}
		
		search4();
	    function search4() {
			var json=({"item":{"suretyId":suretyId,"_entity":"com.bos.dataset.grt.TbGrtMybankAssess"}});
	        grid4.load(json);
	    }
	    
	    search5();
	    function search5() {
			var json=({"item":{"suretyId":suretyId,"_entity":"com.bos.dataset.grt.TbGrtOuterAssess"}});
	        grid5.load(json);
	    }
	    
	    function editOuterAssess(v){
	    	var row = grid5.getSelected();
	        var title1;
	        if(v == "0"){
				title1 = "编辑";        	
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {
	            nui.open({
	                url: nui.context+"/grt/manage/assess/outerAssess/outerAssessEdit.jsp?suretyKeyId="+row.suretyKeyId+"&view="+v,
	                title: title1, 
	                width: 800,
	        		height: 600,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                    //iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
						search5();
	                }
	            });
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	    
	    function addOuterAssess(){
	    	nui.open({
	            url: nui.context+"/grt/manage/assess/outerAssess/outerAssessAdd.jsp?suretyId="+suretyId+"&sortType="+sortType,
	            title: "新增外部评估信息", 
	            width: 800, 
	        	height: 600,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                search5();
	            }
	        }); 
	    }
	    
	    function removeOuterAssess(){
	    	var row = grid5.getSelected();
	        if (row) {
	        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"suretyKeyId":row.suretyKeyId,"_entity":"com.bos.dataset.grt.TbGrtOuterAssess"}});
                $.ajax({
                    url: "com.bos.grt.grtMainManage.grtApply.delApplyTbAssess.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
                    	search5();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	    
	    function editMyBankAssess(v){
	    	var row = grid4.getSelected();
	        var title1;
	        if(v == "0"){
				title1 = "编辑";        	
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {
	            nui.open({
	                url: nui.context+"/grt/manage/assess/myBankAssess/myBankAssessEdit.jsp?suretyKeyId="+row.suretyKeyId+"&view="+v,
	                title: title1, 
	                width: 800,
	        		height: 600,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                    //iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
						search4();
	                }
	            });
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	    
	    
	    function addMyBankAssess(){
	    	nui.open({
	            url: nui.context+"/grt/manage/assess/myBankAssess/myBankAssessAdd.jsp?suretyId="+suretyId+"&sortType="+sortType,
	            title: "新增我行评估信息", 
	            width: 800, 
	        	height: 600,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                search4();
	            }
	        }); 
	    }
	    
	    function removeMyBankAssess(){
	    	var row = grid4.getSelected();
	        if (row) {
	        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"suretyKeyId":row.suretyKeyId,"_entity":"com.bos.dataset.grt.TbGrtMybankAssess"}});
                $.ajax({
                    url: "com.bos.grt.grtMainManage.grtApply.delApplyTbAssess.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
                    	search4();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	</script>
</body>
</html>
