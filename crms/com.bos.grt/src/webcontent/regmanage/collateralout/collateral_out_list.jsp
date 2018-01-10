<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-06-03 09:20:03
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
	
	<title>押品出库</title>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="押品出库" >
	    <div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;">
	    	<div class="nui-dynpanel" columns="4">
				<label>抵质押人名称：</label>
				<input name="partyName"  class="nui-textbox nui-form-input" />
				<label>登记权证编号：</label>
				<input name="registerCertiNo"  class="nui-textbox nui-form-input" />
				<label>借款合同号：</label>
				<input name="contractNum"  class="nui-textbox nui-form-input" />
				<label>担保合同号：</label>
				<input name="subcontractNum"  class="nui-textbox nui-form-input" />
				<label>押品编号：</label>
				<input name="suretyNo"  class="nui-textbox nui-form-input" />
		    </div>
	    
	    			
		<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
   			 borderStyle="border:0;">
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>	
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-ok" onclick="outcool(21)">正式出库</a>
			<a class="nui-button" iconCls="icon-ok" onclick="outcool(22)">临时出库</a>
			<!-- <a class="nui-button" iconCls="icon-edit" onclick="getinfo(1)" id="getinfo">查看押品信息</a> -->
		</div>
	
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.grt.regmanage.collateralout.queryOutStorageList.biz.ext" allowAlternating="true"
			dataField="arrays" allowResize="false" showReloadButton="false" 
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div field="REGISTER_CERTI_NO" allowSort="true">登记权证编号</div>
				<div field="PARTY_NAME" allowSort="true">抵质押人名称</div>
				<div field="SAVE_ORG" dictTypeId="org">凭证保管地</div>
				<div field="CARD_REG_DATE"  allowSort="true" format="yyyy-MM-dd">登记生效日期</div>
				<div field="REG_DUE_DATE" allowSort="true" format="yyyy-MM-dd">登记到期日期</div>
			</div>
   		</div>
	</div>
</div>			
	<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
		
	    function search() {
			var data = form.getData(); //获取表单多个控件的数据
	        grid.load(data);
	    }
	    search();
	    
	    function reset(){
			form.reset();
		}
		
		/**
		 * 出库
		 */
	    function outcool(v) {
		    var rows = grid.getSelecteds();
		    var row = grid.getSelected();
			if(rows.length>0){
				//规则校验：出库是否有在途
			 	var json = {"suretyKeyId":row.SURETY_KEY_ID};
		   	    var msg = exeRule("RGRT_0001","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		return;
		   	    }
		   	    var msg = exeRule("RGRT_0008","1",json);
		   	    if(null != msg && '' != msg){
			   		nui.alert(msg);
			   		return;
		   	    }
		   	    
		   	   //判断押品是否已经做过质押扣划，如果做过了质押扣划，则不校验直接可以出库
		   	    var zykhjson = nui.encode({"id":row.SURETY_KEY_ID}); 
		   	    var zykhflag ;
		   	    $.ajax({
			        	url: "com.bos.grt.regmanage.collateralout.checkIfZykh.biz.ext",
			        	type: 'POST',
			        	data: zykhjson,
			        	cache: false,
			        	async: false,
			        	contentType:'text/json',
			        	success: function (text) {
			        		if(text.flag== "true"){
			        			zykhflag = "1";//存在质押扣划。可以不做校验直接出库
			        		}
			        	},
			        	error: function (jqXHR, textStatus, errorThrown) {
			            	nui.alert(jqXHR.responseText);
			        	}
					});
		   	    
		   	    if(v == "21"){
		   	    
			   	    //校验：存在有效的担保合同。不可以正式出库
					var jsonNO=nui.encode({"no":row.REGISTER_CERTI_NO});
					$.ajax({
			        	url: "com.bos.grt.regmanage.collateralout.getValidById.biz.ext",
			        	type: 'POST',
			        	data: jsonNO,
			        	cache: false,
			        	contentType:'text/json',
			        	success: function (text) {
			        		if(text.msg !=null){
			            		nui.alert(text.msg); //失败时后台直接返回出错信息
			            		return;
			            	}
			        		if(text.flag== "true" && zykhflag != "1"){
				        		//存在有效的担保合同
				        		alert("权证下押品存在担保合同的有效关联关系，不允许出库！");
			        		}else if(text.flag== "false" || (text.flag== "true" && zykhflag == "1")){
			        			if('<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("legorg") %>' == "5099"){
				   	    		//北川富民
				   	    		nui.open({
					            			url: nui.context+"/grt/grt_pro/grt_reason_list.jsp?SURETY_KEY_ID="+row.SURETY_KEY_ID+"&partyId="+row.PARTY_ID,
					            			title: "录入出库原因", 
					            			width: 600, 
					        				height: 400,
					        				allowResize:false,
					    	    			showMaxButton: false,
						            		ondestroy: function (action) {
						            			search();
					            	  		}
					        			});
				   	    		
					   	    	}else{
									var json=nui.encode({"regCards":rows,"partyId":row.PARTY_ID,"outType":v});
									$.ajax({
							        	url: "com.bos.grt.regmanage.collateralout.collateralOutStorage.biz.ext",
							        	type: 'POST',
							        	data: json,
							        	cache: false,
							        	contentType:'text/json',
							        	success: function (text) {
							        		if(text.msg !=null){
							            		nui.alert(text.msg); //失败时后台直接返回出错信息
							            		return;
							            	}
							        		var o = nui.decode(text);
							        		nui.open({
						            			url: nui.context+"/grt/grt_pro/grt_tree.jsp?processInstId="+o.processInstId+"&outId="+o.grtOut.outId+"&proFlag=1&isSrc=2",
						            			title: "查看押品", 
						            			width: 1200, 
						        				height: 600,
						        				allowResize:false,
						    	    			showMaxButton: false,
							            		ondestroy: function (action) {
							            			search();
						            	  		}
						        			});
							        		
							        	},
							        	error: function (jqXHR, textStatus, errorThrown) {
							            	nui.alert(jqXHR.responseText);
							        	}
									});
								}
			        		}
			        	}
					});
		   	    }else{
   	    				var json=nui.encode({"regCards":rows,"partyId":row.PARTY_ID,"outType":v});
						$.ajax({
				        	url: "com.bos.grt.regmanage.collateralout.collateralOutStorage.biz.ext",
				        	type: 'POST',
				        	data: json,
				        	cache: false,
				        	contentType:'text/json',
				        	success: function (text) {
				        		if(text.msg !=null){
				            		nui.alert(text.msg); //失败时后台直接返回出错信息
				            		return;
				            	}
				        		var o = nui.decode(text);
				        		nui.open({
			            			url: nui.context+"/grt/grt_pro/grt_tree.jsp?processInstId="+o.processInstId+"&outId="+o.grtOut.outId+"&proFlag=1&isSrc=2",
			            			title: "查看押品", 
			            			width: 1200, 
			        				height: 600,
			        				allowResize:false,
			    	    			showMaxButton: false,
				            		ondestroy: function (action) {
				            			search();
			            	  		}
			        			});
				        		
				        	},
				        	error: function (jqXHR, textStatus, errorThrown) {
				            	nui.alert(jqXHR.responseText);
				        	}
						});
		   	    }
			}else{
				alert("请选中至少一条需要入库的押品信息");
			}
	    }
	</script>
</body>
</html>