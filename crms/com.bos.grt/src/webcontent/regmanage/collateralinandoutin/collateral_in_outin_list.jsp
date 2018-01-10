<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品入库</title>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="押品入库" >
	    <div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;">
	    	<div class="nui-dynpanel" columns="4">
	    		<label>抵质押人名称：</label>
				<input name="partyName"  class="nui-textbox nui-form-input" />
				<label>登记权证编号：</label>
				<input name="registerCertiNo"  class="nui-textbox nui-form-input" />
		    </div>
		<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
   			 borderStyle="border:0;">
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
		</div>	
		
		
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button" iconCls="icon-add" onclick="insertCard()">录入登记权证</a>
			<a class="nui-button" iconCls="icon-edit" onclick="editCard()">修改登记权证</a>
			<a class="nui-button" iconCls="icon-remove" onclick="removeCard()">删除登记权证</a>
			<a class="nui-button" iconCls="icon-ok" id="yprk" onclick="inStorage()">押品入库</a>
		</div>
	
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.grt.regmanage.collateralinandoutin.queryInStorageList.biz.ext"
			dataField="arrays" allowResize="false" showReloadButton="false" allowAlternating="true"
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
	    
	    	//modi by shangmf:20171023
			nui.get("yprk").setEnabled(true);
			
			var data = form.getData(); //获取表单多个控件的数据
	        grid.load(data);
	    }
	    search();
	    
	    function reset(){
			form.reset();
		}
		
		
		/**
		 * 获得选中的数据
		 */
		function getThisData(){
			var row = grid.getSelecteds();
			var data = {collConGrtRelationInAndOutIns:row};
			return data;
		}
		
		//录入登记权证
		function insertCard(){
	    	nui.open({
		        url: nui.context + "/grt/manage/chioseParty/queryCusParty.jsp",
		        showMaxButton: true,
		        title: "选择客户",
		        width: 1000,
		        height: 500,
		        ondestroy: function (action) {            
		            if (action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.getData();
		                data = nui.clone(data);
		               //录入登记权证
						nui.open({
				            url: nui.context + "/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_add.jsp?partyId="+data.partyId,
				            showMaxButton: true,
				            title: "录入登记权证",
				            width: 800,
				            height: 500,
				            ondestroy: function(action) {
				            	search();
				            	//CloseWindow("ok");
				            }
				        });
		            }else{
		            	search();
		            }
		        }
		    }); 
		}
		
		//编辑登记权证
		function editCard(){
			var rows= grid.getSelecteds();
			if(rows.length==1){
				var row = grid.getSelected();
				var suretyKeyId=row.SURETY_KEY_ID;
				var partyId=row.PARTY_ID;
				//编辑登记权证
				nui.open({
		            url: nui.context + "/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_add.jsp?suretyKeyId="+suretyKeyId+"&view=0&partyId="+partyId,
		            showMaxButton: true,
		            title: "编辑登记权证",
		            width: 900,
		            height: 450,
		            ondestroy: function(action) {
		            	search();
		            	CloseWindow("ok");
		            }
		        });
			}else{
				alert("请选择一行进行修改!");
			}
		} 
		
		//删除登记权证
	function removeCard(){
			var rows = grid.getSelecteds();
			if(rows.length>0){
	        	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
					var suretyKeyId=rows[0].SURETY_KEY_ID;
					var registerCertiNo=rows[0].REGISTER_CERTI_NO;
					 if(registerCertiNo!=null){
						var json=nui.encode({"tbGrtRegcardinfo":{"suretyKeyId":suretyKeyId}});
						$.ajax({
				        	url: "com.bos.grt.manage.TbGrtRegcardinfo.delTbGrtRegcardinfo.biz.ext",
				        	type: 'POST',
				        	data: json,
				        	cache: false,
				        	contentType:'text/json',
				        	success: function (text) {
				        		nui.alert(text.msg);
				        		search();
				        	},
				        	error: function (jqXHR, textStatus, errorThrown) {
				            	nui.alert(jqXHR.responseText);
				        	}
						});
					}else{
						nui.alert("选中行未录入登记权证信息!");
					} 
	            }); 
			}else{
				nui.alert("请选择某一条数据进行删除登记权证操作!");
			} 
		}
		
		//入库
		function inStorage(){
		
		   	//modi by shangmf:20171023
           	nui.get("yprk").setEnabled(false);
			
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if(rows.length>0){
			
			//add by shangmf:如果为历史数据，有的出库了，但没有出库明细，
			//那么只要是已经出库了就是正式出库，不允许再次入库
			var suretyKeyId=row.SURETY_KEY_ID;
			var jsonData=nui.encode({"suretyKeyId":suretyKeyId});
			$.ajax({
		        		url: "com.bos.grt.regmanage.collateralinandoutin.queryTransCollStatus.biz.ext",
		        		type: 'POST',
		        		data: jsonData,
		        		cache: false,
		        		contentType:'text/json',
		        		success: function (text) {
		        		//modi by shangmf:历史数据状态为出库，但出库原因是临时出库可以再入库
		        		var outResonStr = null;
		        		if(text.outReson != null){
		        			outResonStr = text.outReson.substr(0,2);
		        		}
		        		if((text.status == "04" && outResonStr!=null && outResonStr != '22')||
		        		   (text.status == "04" && outResonStr == null ) ){
						 	alert("此笔权证已正式出库，不允许再次入库!");
						 	return;
			        	}else{
			        		
			        		//校验，权证下押品的权利价值与前台录入的登记金额相等,担保日期校验
			   				var jsonNo=nui.encode({"registerCeritNo":row.REGISTER_CERTI_NO,"amount":row.REG_ORG_MONEY});
							$.ajax({
		        			url: "com.bos.grt.collService.collInOperation.checkInApp.biz.ext",
		        			type: 'POST',
		        			data: jsonNo,
		        			cache: false,
		        			contentType:'text/json',
		        			success: function (text) {
		        			if(text.map.flag == "true"){
							//入库操作
						 	var json=nui.encode({"regCards":rows});
							$.ajax({
					        	url: "com.bos.grt.regmanage.collateralinandoutin.collateralInStorage.biz.ext",
					        	type: 'POST',
					        	data: json,
					        	cache: false,
					        	contentType:'text/json',
					        	success: function (text) {
					            //	search();
					            		if(text.msg !="入库成功"){
					            			alert(text.msg); //失败时后台直接返回出错信息
					            			return;
					            		}
					            		var o = nui.decode(text);
					        			nui.open({
				            				url: nui.context+"/grt/grt_pro/grt_in_tree.jsp?processInstId="+o.processInstId+"&outId="+o.grtIn.inId+"&proFlag=1&isSrc=2",
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
			        		}else{
			        			alert(text.map.msg);
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
					
			
			
			}else{
				alert("请选中至少一条需要入库的押品信息");
			}
		}
	</script>
</body>
</html>