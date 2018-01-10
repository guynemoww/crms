<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-14 09:31:26
  - Description:
-->
<head>
<title>评级调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;">
		<div id="dynpanel1" class="nui-dynpanel" columns="4">
	  		<label class="nui-form-label">初始评级（R0）：</label>
			<input id="initialRatingCd" property="editor" class="nui-text nui-form-input" enabled="false" vtype="maxLength:100" />
			<a id="getRateResults" class="nui-button" onclick="getRateResults()">获取评级结果</a>
			<label class="nui-form-label"></label>
		</div>
		<div id="datagrid1" class="nui-datagrid" datafield="adjustOptions"  showPager="false" idField="id" allowCellEdit="true"
	          allowCellSelect="true"  editNextOnEnterKey="true" url="com.bos.irm.queryInfo.queryAdjustOptions.biz.ext"  allowCellWrap="true">
	        <div property="columns">
	        		<div type="indexcolumn" headerAlign="center" >序号</div>
	        		<div field="adjustOrder" name="adjustOptionId" visible = "false" width="8%" >调整编号</div>
	          		<div field="adjustTypeCd" name="adjustTypeCd" headerAlign="center" align="center" allowSort="true" width="10%" visible='false'  dictTypeId="XD_PJCD0014" >分类</div>
		        	<div field="adjustOptionDescription" headerAlign="center" allowSort="true" width="60%" >调整事项</div>
		        	<div field="check" type="checkboxcolumn" trueValue="1" falseValue="2" headerAlign="center" width="8%">是否手动调整</div>
		            <!--<div field="adjustTypeCd" id="adjustTypeCd" name="adjustTypeCd" autoShowPopup="true" width="100" align="center" headerAlign="center" dictTypeId="XD_PJCD0010" >评级调整</div>
		       		<div field="adjustTo" id="adjustTo" name="adjustTo" type="comboboxcolumn" autoShowPopup="true" width="100" align="center" headerAlign="center">调整级别
		                 <input property="editor" class="nui-combobox" data="Gender1" />
		            </div> 	-->
	                <div field="remarks" headerAlign="center" width="15%" allowSort="true">手工调整说明
	          	        <input property="editor" name="remarks" class="nui-textarea" />
	            	</div>
	        </div>
	    </div>
	</div>
	<div id="save" class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 20px;">
			 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
	         <a id="deleteRating" class="nui-button" onclick="deleteRating()">评级流程撤销</a>
	</div>


<!--		<label class="nui-form-label" >注：若出现调整选项，请综合考虑客户初始评级（R0）或经融资平台调整后评级（R1），以及通用调整选项情况，在“系统评级（R2）”的下拉选项框中进行选择，作为该客户最终的系统评级。若认为系统评级不能准确反映客户，请在评级结论页的评级推翻栏中进行评级推翻。</label>-->
	
		
	<div id="dynpanel2" class="nui-dynpanel" columns="2">	
		<label id="government1" class="nui-form-label" >经融资平台调整后评级（R1）：</label>
		<input id="governmentRatingCd" property="editor" class="nui-text nui-form-input" enabled="false" vtype="maxLength:100" />
	</div>
		<label class="nui-form-label" >注：若出现调整选项，请综合考虑客户初始评级（R0）或经融资平台调整后评级（R1），以及通用调整选项情况，在“系统评级（R2）”的下拉选项框中进行选择，作为该客户最终的系统评级。若认为系统评级不能准确反映客户，请在评级结论页的评级推翻栏中进行评级推翻。</label>
	<div id="dynpanel3" class="nui-dynpanel" columns="2">	
		<label class="nui-form-label">系统评级（R2）：</label>
		<input id="generalRatingCd" property="editor" class="nui-combobox" textField="display" valueField="orderNo" dataField="out3" vtype="maxLength:100" />
	</div>
	<div id="dynpanel4" class="nui-dynpanel" columns="2">	
		<label class="nui-form-label">系统评级（R2）：</label>
		<input id="generalRatingCd2" property="editor" class="nui-text nui-form-input" enabled="false" vtype="maxLength:100" />
	</div>
	<input id="orderNo" property="editor" class="nui-text nui-form-input" style="display: none" vtype="maxLength:100" />
	<input id="orderNoGovernment" property="editor" class="nui-text nui-form-input" style="display: none" vtype="maxLength:100" />
<!--	<div id="dynpanel2" class="nui-dynpanel" columns="2">
	  	<label class="nui-form-label">机评等级：</label>
		<input id="initialRatingCd2" property="editor" class="nui-combobox" textField="display" valueField="orderNo" dataField="out1" enabled="false" vtype="maxLength:100" />
		
		<label class="nui-form-label">通用调整后等级：</label>
		<input id="generalRatingCd2" property="editor" class="nui-combobox" textField="display" valueField="orderNo" dataField="out3" vtype="maxLength:100" />
	</div>-->
	<div id="save1" class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 10px;">
			 <a class="nui-button" iconCls="icon-save" onclick="btnSave1()">保存</a>
	</div>
<script type="text/javascript">
		nui.parse();
    	var partyId="<%=request.getParameter("partyId")%>";//参与人id
		var applyId;//评级申请id
		var reAud="<%=request.getParameter("reAud") %>"
		var form = new nui.Form("#form1");
		var grid1 = nui.get("datagrid1");//var checkFlg = 0;//每行check参数。
		var allowModifyFlag;//是否允许保存
		var flg = "2";//是否查看
		var governmentFlg;//是否政府融资平台
		
		var processId;//实例号processDefName
		var processdefname;
		search();
		function getProcessInfo(){
			var json = nui.encode({"applyId":applyId});
    		nui.ajax({
	            url: "com.bos.irm.queryInfo.getProcessInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            	} else {
						var o = nui.decode(text);
						processId=o.processId;
						processdefname=o.processdefname;
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
		}
		//流程撤销
		function deleteRating(){
			if (isCalc == "1"){
				getProcessInfo();
				nui.confirm("流程一旦终止，将无法恢复，是否确定要终止该流程？","提示",function(action){
					if(action=="ok"){
						nui.open({
				            url: nui.context + "/bps/mywork/process_stop_reason.jsp",
				            showMaxButton: false,
				            title: "",
				            width: 550,
				            height: 250,
				            ondestroy: function (action) {
				                if (action == "ok") {
				                	var iframe = this.getIFrameEl();
				                    var data = iframe.contentWindow.GetData();
				                    data = nui.clone(data);
				                    //终止流程	
					            	 var json = nui.encode({"processId":processId,"bizId":applyId,"processdefname":processdefname,"opinion":data});
					            	 nui.ajax({
							            url: "com.bos.bps.op.WorkFlowManager.stopProcess.biz.ext",
							            type: 'POST',
							            data: json,
							            cache: false,
							            contentType:'text/json',
							            success: function (text) {
							            	if(text&&text.msg){
							            		nui.alert(text.msg); //失败时后台直接返回出错信息
							            	} else {
												closeWindow();
							            	}
							            },
							            error: function (jqXHR, textStatus, errorThrown) {
							                nui.alert(jqXHR.responseText);
							            }
							        });
				                }
				            }
				        });
		       		}
				});
			}else{
				alert("已获取评级结果，不能撤销评级流程！");
			}
		}
		function closeWindow(){
	    	var isSrc = "2";
	    	if("1" == isSrc){
	    		if (window.CloseOwnerWindow){
	    			return window.CloseOwnerWindow("submit");
	    		}else{
	    			window.close(); 
	    		} 
	    	}else if("2" == isSrc){
	    	
	    		nui.alert("撤销成功!","提示",function(action){
	    			git.gohome();
	  	 		});
	    	}else{
	    		nui.alert("提交成功!点击确定返回待办工作列表","提示",function(action){
	    			var w=self.parent ? self.parent : self;
	    			w.location.replace("/default/csm/workdesk/mywork.jsp");
	  	 		});
	    	}
    	}	
	    function search(){
 	    	if(reAud == "0"){
	    		applyId = "<%=request.getParameter("applyId") %>";
	    	}else{
	    		applyId = "<%=request.getParameter("oldApplyId") %>";
	    	}

		    isExistOptionS();//判断调整选项是否已经保存
		    getRateState();//获取评级状态并控制画面的显示
		    grid1.on("load", function () {
           		 grid1.mergeColumns(["adjustTypeCd"]);
      		});
		    getAllowModifyFlag();//获取是否政府融资
		    grid1.load({"applyId":applyId,"viewFlg":flg});
		    getReAud();//通过再审核标识来控制页面编辑
		    getCreditRatingCd();//获取机评结果
		    check();
		    if(governmentFlg ==1){
			 	document.getElementById("save1").style.display="block";//显示
			}
 			
			if(state == "3"){
 				if(isCalc == "1"){
			    	nui.get("initialRatingCd").setValue(null);
			    	nui.get("governmentRatingCd").setValue(null);
			    	nui.get("generalRatingCd").setValue(null);
			     	nui.get("generalRatingCd2").setValue(null);
			    	nui.get("orderNo").setValue(null);
			    	nui.get("orderNoGovernment").setValue(null);
			    }
		    }
		}
		
		function check(){
			var json = nui.encode({"applyId":applyId});
			nui.ajax({//获取机评等级
				url: "com.bos.irm.queryInfo.queryRatingCdDisplay.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				async:false,        
				contentType:'text/json',
				success: function (text) {
				 	if(text.msg){
				    } else {
				       	var o = nui.decode(text);
				       	nui.get("orderNoGovernment").setValue(o.order1);
				       	generalRatingCd2();
<!--				       	nui.get("generalRatingCd").setValue(o.order2);
-->				    }
				}
			});
		}
		
		function generalRatingCd2(){
			//var a = nui.get("governmentRatingCd").getValue();
			valueChange();
		}
			
		function getCreditRatingCd(){
			var json = nui.encode({"applyId":applyId,"partyId":partyId});
		    nui.ajax({//获取机评等级
				url: "com.bos.irm.queryInfo.queryCreditRatingCd.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				async:false,        
				contentType:'text/json',
				success: function (text) {
				 	if(text.msg){
				    } else {
				       	var o = nui.decode(text);
				       	nui.get("initialRatingCd").setValue(o.initialRatingCd);
				       	nui.get("generalRatingCd2").setValue(o.initialRatingCd);
				       	nui.get("orderNo").setValue(o.orderNo);//机评orderNo
 				    }
				}
			});
		    if(governmentFlg==1){
				getGovernmentRatingCd();//获取政府融资调整选项
		    }else{
		    	var tt = nui.get("orderNo").getValue();
		    	nui.get("orderNoGovernment").setValue(tt);
				generalRatingCd();//通用调整选项
		    	nui.get("dynpanel2").hide();//融资平台选项隐藏
		    }
		}
		
		function valueChange(){
			var orderNo = nui.get("orderNoGovernment").getValue();
			var IsGeneral = 1;
			var json = nui.encode({"applyId":applyId,"partyId":partyId,"orderNo":orderNo,"IsGeneral":IsGeneral});
			nui.ajax({
				url: "com.bos.irm.queryInfo.queryGeneralRatingCd.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
				    async:false,        
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		alert(text.msg)
			        	} else {
			        		var o = nui.decode(text);
<!--			             	nui.get("generalRatingCd").setData(o.out3);
-->			            }
			        }
			});
		}
		
		function btnSave1(){//调整保存
			var gridFlg=0;//是否调整 1：调整 0：不调整
			var rows = grid1.findRows();
			for (var i = 0 ; i < rows.length ;i++){
				if (rows[i].check == 1){
					gridFlg = 1 ;
				}
			}
			if(governmentFlg==1){
				var d =nui.get("governmentRatingCd").getText();//政府融资平台
				if(!d){
					return alert("请进行“系统评级（R1）”的下拉选项的选择！");
				}
			}
			if(gridFlg == 1){
				var c = nui.get("generalRatingCd").getText();//通用调整
				if(!c){
					return alert("请进行“系统评级（R2）”的下拉选项的选择！");
				}
			}
			var a = nui.get("orderNo").getValue();
			if(a){
				if(governmentFlg == 1){//政府融资
					if(gridFlg == 0){//不调整
						var a = nui.get("orderNoGovernment").getValue();//政府融资
						var b = nui.get("generalRatingCd").getValue();//通用调整
						var json = nui.encode({"applyId":applyId,"governmentRatingCd":a,"generalRatingCd":a});
						nui.ajax({
							url: "com.bos.irm.queryInfo.saveRatingCd.biz.ext",
						        type: 'POST',
						        data: json,
						        cache: false,
							    async:false,        
						        contentType:'text/json',
						        success: function (text) {
						        	if(text.msg){
						        		alert(text.msg);
						        	}
						        }
						});
					}else{//调整
						var a = nui.get("orderNoGovernment").getValue();//政府融资
						var b = nui.get("generalRatingCd").getValue();//通用调整
						var json = nui.encode({"applyId":applyId,"governmentRatingCd":a,"generalRatingCd":b});
						nui.ajax({
							url: "com.bos.irm.queryInfo.saveRatingCd.biz.ext",
						        type: 'POST',
						        data: json,
						        cache: false,
							    async:false,        
						        contentType:'text/json',
						        success: function (text) {
						        	if(text.msg){
						        		alert(text.msg);
						        	}
						        }
						});
					}
				}else{//非政府融资平台
					var a = nui.get("orderNo").getValue();//政府融资
					var b = nui.get("generalRatingCd").getValue();//通用调整
					var json = nui.encode({"applyId":applyId,"governmentRatingCd":a,"generalRatingCd":b});
					nui.ajax({
						url: "com.bos.irm.queryInfo.saveRatingCd.biz.ext",
					        type: 'POST',
					        data: json,
					        cache: false,
						    async:false,        
					        contentType:'text/json',
					        success: function (text) {
					        	if(text.msg){
					        		alert(text.msg);
					        	}
					        }
					});
				}
			}else{
				return  alert("请先获取评级结果！");
			}
		}
		function getGovernmentRatingCd(){
			var orderNo = nui.get("orderNo").getValue();//机评结果No
	    	var json = nui.encode({"applyId":applyId,"partyId":partyId,"orderNo":orderNo});
			nui.ajax({
				url: "com.bos.irm.queryInfo.queryR1.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
				    async:false,        
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		alert(text.msg)
			        	} else {
			        		var o = nui.decode(text);
			             	nui.get("governmentRatingCd").setValue(o.governmentRatingCd);
			             	nui.get("orderNoGovernment").setData(o.orderNo);
			            }
			        }
			});
		}
		
		function generalRatingCd(){//通用调整选项
		 
		<!--	var orderNo = nui.get("orderNo").getValue();
			var IsGeneral = 1;
			var json = nui.encode({"applyId":applyId,"partyId":partyId,"orderNo":orderNo,"IsGeneral":IsGeneral});
			nui.ajax({
				url: "com.bos.irm.queryInfo.queryGeneralRatingCd.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
				    async:false,        
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		alert(text.msg)
			        	} else {
			        		var o = nui.decode(text);
			             	nui.get("generalRatingCd").setData(o.out3);
			            }
			        }
			});-->
			
		}
		
		function getAllowModifyFlag(){//是否允许保存,是否政府融资
			var json = nui.encode({"applyId":applyId});
			nui.ajax({
			        url: "com.bos.irm.queryInfo.queryAllowModifyFlag.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
				    async:false,        
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		alert(text.msg)
			        	} else {
			        		var o = nui.decode(text);
			             	allowModifyFlag = o.allowModifyFlag;
			             	governmentFlg = o.governmentFlg;
			            }
			        }
			});
		}
		//获取评级结果按钮
		function getRateResults(){
			if(allowModifyFlag == 2){
				alert("获取评级结果次数过多，不能进行获取评级结果！");
				return;
			}
			nui.confirm("获取评级结果后则不能修改客户财务、非财务、政府融资平台和通用调整选项信息。是否确认获取评级结果？","确认",function(action){
            	if(action!="ok"){
            		return;
            	}else{
            		var rows = grid1.findRows();
					var rows1 = new Array();
					for (var i = 0 ; i < rows.length ;i++){
						if (rows[i].check == 1){
							rows1.push(rows[i]);
						}
					}
					var json = nui.encode({"inRows":rows1,"applyId":applyId});
					nui.ajax({
					    url: "com.bos.irm.insertInfomercial.addAdjustOptionsInfo.biz.ext",
					    type: 'POST',
					    data: json,
					    cache: false,
					    async:false,        
					    contentType:'text/json',
					    success: function (text) {
					 	  	if(text.msg){
					 	  		if(text.isExist<1){
					 	  			document.getElementById("dynpanel3").style.display="none";
					 	  			document.getElementById("dynpanel4").style.display="block";
					 	  			if(governmentFlg !=1){
					 	  				document.getElementById("save1").style.display="none";
					 	  			}
					 	  		}else{
					 	  			document.getElementById("dynpanel3").style.display="block";
					 	  			document.getElementById("dynpanel4").style.display="none";
					 	  			search();
					 	  		}
					      	}
					    },
				        error: function (jqXHR, textStatus, errorThrown) {
				            alert(jqXHR.responseText);
			            }
				    });  
				    var json = nui.encode({"applyId":applyId});
				    nui.ajax({//获取引擎计算id
				        url: "com.bos.irm.queryInfo.queryEngineRecId.biz.ext",
				        type: 'POST',
				        data: json,
				        cache: false,
					    async:false,        
				        contentType:'text/json',
				        success: function (text) {
				        	if(text.msg){
				        	 	alert(text.msg);
				        		search();  
				        	    return;
				        	} else {
								 alert("获取成功！"); 
								isCalc = "2";
				        	}
				        }
				    });
				  
				    getCreditRatingCd();//获取机评结果
				    getAllowModifyFlag();
            	}
			});
		}

		function getReAud(){
			if (reAud == "1"){
				grid1.allowCellEdit=false;
		    	$("#save").hide();
		    	$("#save1").hide();
		    	$("#dynpanel1").hide();
		    	$("#dynpanel2").hide();
		    	$("#dynpanel3").hide();
		    	$("#dynpanel4").hide();
		    	
			}
		}

		function btnSave(){//保存
			if(allowModifyFlag == 2){
				alert("获取评级结果次数过多，不能进行保存！");
				return;
			}
			var rows = grid1.findRows();
			var rows1 = new Array();
			for (var i = 0 ; i < rows.length ;i++){
				if (rows[i].check == 1){
					rows1.push(rows[i]);
				}
			}
			
			var json = nui.encode({"inRows":rows1,"applyId":applyId});
			nui.ajax({
			    url: "com.bos.irm.insertInfomercial.addAdjustOptionsInfo.biz.ext",
			    type: 'POST',
			    data: json,
			    cache: false,
			    async:false,        
			    contentType:'text/json',
			    success: function (text) {
			 	  	if(text.msg){
			 	  		if(text.isExist<1){
			 	  			document.getElementById("dynpanel3").style.display="none";
			 	  			document.getElementById("dynpanel4").style.display="block";
			 	  			if(governmentFlg !=1){
			 	  				document.getElementById("save1").style.display="none";
			 	  			}
			 	  		}else{
			 	  			document.getElementById("dynpanel3").style.display="block";
			 	  			document.getElementById("dynpanel4").style.display="none";
			 	  			search();
			 	  		}
			   			alert(text.msg);
			      	}
			    },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
	            }
		    });  		
		}
		/*判断是否已经保存了调整选项*/
		function isExistOptionS(){
			var json = nui.encode({"applyId":applyId});
		    nui.ajax({
		        url: "com.bos.irm.insertInfomercial.isExistOptionS.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.isExist<1){
		 	  			document.getElementById("dynpanel3").style.display="none";
		 	  			document.getElementById("dynpanel4").style.display="block";
		 	  			if(governmentFlg !=1){
			 	  			document.getElementById("save1").style.display="none";
			 	  		}
		 	  		}else{
			 	  		document.getElementById("dynpanel3").style.display="block";
			 	  		document.getElementById("dynpanel4").style.display="none";
			 	  		document.getElementById("save1").style.display="block";
		 	  		}
		        }
		    });
		}
		function getRateEngineCalc(){
			var json = nui.encode({"applyId":applyId});
			nui.ajax({
		        url: "com.bos.irm.queryInfo.queryIsRateEngineCalc.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		                var o = nui.decode(text);
						isCalc = o.isCalc;
		            }
		        }
		    });
		}
		var isCalc;
		function getRateState(){
			var json = nui.encode({"applyId":applyId});
		    nui.ajax({
		        url: "com.bos.irm.queryInfo.queryRateState.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		                var o = nui.decode(text);
		                state = o.state;
		            }
		        }
		    });
		    getRateEngineCalc();
		    if (state == "1"){//待审核
		    	$("#save").hide();
		    	$("#save1").hide();
		    	$("#dynpanel1").hide();
		    	$("#dynpanel2").hide();
		    	$("#dynpanel3").hide();
		    	$("#dynpanel4").hide();
		    	grid1.allowCellEdit=false;
		    	$("#deleteRating").hide();
		    }
		    if (state ==  "2"){//待认定
		    	$("#save").hide();
		    	$("#save1").hide();
		    	$("#dynpanel1").hide();
		    	$("#dynpanel2").hide();
		    	$("#dynpanel3").hide();
		    	$("#dynpanel4").hide();
		    	grid1.allowCellEdit=false;
		    	$("#deleteRating").hide();
		    }
		}
</script>
</body>
</html>