<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-09 14:27:06
  - Description:
-->
<head>
<title>财务报表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<body>
	<div id="form1_1" style="width:100%;height:auto;overflow:hidden;">
		<input name="item.modelId" id="modelId" class="nui-hidden" />
		<input type="hidden" name="item.modelTypeCd" id="modelTypeCd" class="nui-hidden"  />
	</div>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div id="query" class="nui-toolbar" style="border-bottom:0; text-align: right;">
			<a class="nui-button" iconCls="icon-edit" onclick="edit1()">查看</a>
		</div>	
		<input  id="YN" name="yn"  property="editor" class="nui-hidden" onclick="showOn(this)"/>	
	
		<div id="form3" style="width:100%;height:auto;overflow:hidden;">	
			<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" 
			     dataField="corps"  showPager="false"
			    allowAlternating="true" multiSelect="false">
			    <div property="columns">
					<div type="checkcolumn" id="check" >选择</div>
					<div field="financeDeadline" name="financeDeadline" headerAlign="center" allowSort="false" >报告日期</div>
					<div field="financeTypeCd" valueField="dictID" dictTypeId="XD_ACCCD0001" headerAlign="center" allowSort="false" >报表类型</div>
					<div field="auditedInd" valueField="dictID" dictTypeId="YesOrNo" headerAlign="center" allowSort="false" >是否经过审计</div>
					<div field="regulatedInd" valueField="dictID" dictTypeId="YesOrNo" headerAlign="center" allowSort="false" >是否经过调整</div>
					<div field="caliberCd" valueField="dictID" dictTypeId="CDKH0071" headerAlign="center" allowSort="false" >报表口径</div>
					<div field="accountingPrinciple" headerAlign="center" allowSort="false">新旧会计准则</div>
					<div field="customerTypeCd" valueField="dictID" dictTypeId="XD_ACCCD0002" headerAlign="center" allowSort="false" >报表模板</div>
				</div>	
			</div>
			<div id="textarea1" style="margin-top: 10px;">
				<label>选择理由：</label>
				<input id="remark1" name="remark1" class="nui-textarea" style="width:100%;height:auto;" />
			</div>
		</div>
		<div style="width:100%;height:auto;overflow:hidden;"></div>
		<div id="form4" style="width:100%;height:auto;overflow:hidden;">
			<div><label>请根据公司/项目情况录入财务指标实际值。</label></div>
			<div id="datagrid1_1" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.irm.queryInfo.queryFinInfo.biz.ext"
				dataField="ratingFinInfo" showPager="false"
				allowResize="false" showReloadButton="false" oncellbeginedit="getIndexName" allowCellValid="true" oncellendedit="onPropertyTypeCdChanged" 
				sortMode="client" editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true">
				<div property="columns">
					<div type="indexcolumn" headerAlign="center" >序号</div>
					<div field="propertyTypeCd" headerAlign="center" allowSort="true" visible ='false' dictTypeId="XD_ACCCD0006" required="true">指标小类</div>
					<div field="indexName" id="indexName" headerAlign="center" allowSort="true"  required="true" >指标名称</div>
					<div field="indexFormula" headerAlign="center" allowSort="true" >指标公式</div>
					<div field="indexValueDataType" headerAlign="center" allowSort="true"   required="true" vtype="float"   >财务指标
						<input property="editor" class="nui-textbox" style="width:100%;" />
					</div>
					<div field="indexDisp" headerAlign="center" allowSort="true" >如有特殊事项，请各位填入备注
						<input property="editor" class="nui-textarea" style="width:100%;height:auto;" />
					</div>
					<div field="stringType" headerAlign="center" allowSort="true" class="nui-textarea" visible ='false' >备注</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="form2" style="width:100%;height:auto;overflow:hidden;">		
		<div id="query2" class="nui-toolbar" style="border-bottom:0; text-align: right;">
			<a class="nui-button" iconCls="icon-edit" onclick="edit2()">查看</a>
		</div>	
		<div id="form5" style="width:100%;height:auto;overflow:hidden;">	
			<div id="datagrid2" class="nui-datagrid" style="width:100%;height:auto;" showPager="false"
			    dataField="items"
			     multiSelect="false">
			    <div property="columns">
			    	<div type="indexcolumn"></div>
					<div type="checkcolumn" id="check" >选择</div>
					<div field="financeDeadline" name="financeDeadline" headerAlign="center" allowSort="false" >报告日期</div>
					<div field="financeTypeCd" valueField="dictID" dictTypeId="XD_ACCCD0001" headerAlign="center" allowSort="false" >报表类型</div>
					<div field="auditedInd" valueField="dictID" dictTypeId="YesOrNo" headerAlign="center" allowSort="false" >是否经过审计</div>
					<div field="regulatedInd" valueField="dictID" dictTypeId="YesOrNo" headerAlign="center" allowSort="false" >是否经过调整</div>
					<div field="caliberCd" valueField="dictID" dictTypeId="CDKH0071" headerAlign="center" allowSort="false" >报表口径</div>
					<div field="accountingPrinciple" headerAlign="center" allowSort="false">新旧会计准则</div>
					<div field="customerTypeCd" valueField="dictID" dictTypeId="XD_ACCCD0002" headerAlign="center" allowSort="false" >报表模板</div>
				</div>
			</div>
			<div id="textarea2">
				<label>选择理由：</label>
				<input id="remark2" name="remark2" class="nui-textarea" enabled="false" style="width:100%;height:auto;" />
			</div>
		</div>
		<div style="width:100%;height:auto;overflow:hidden;"></div>
		<div id="form6" style="width:100%;height:auto;overflow:hidden;">
			<div id="datagrid2_1" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.irm.queryInfo.queryFinInfo.biz.ext"
				dataField="ratingFinInfo" showPager="false"
				allowResize="true" showReloadButton="false" oncellbeginedit="getIndexName" allowCellValid="true" oncellendedit="onPropertyTypeCdChanged" 
				sortMode="client" editNextOnEnterKey="true" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true">
				<div property="columns">
					<div type="indexcolumn" headerAlign="center" >序号</div>
					<div field="propertyTypeCd" headerAlign="center" allowSort="true" visible ='false' dictTypeId="XD_ACCCD0006" required="true">指标小类</div>
					<div field="indexName" id="indexName" headerAlign="center" allowSort="true"  required="true" >指标名称</div>
					<div field="indexFormula" headerAlign="center" allowSort="true" >指标公式</div>
					<div field="indexValueDataType" headerAlign="center" allowSort="true" required="true" dataType="float" >财务指标</div>
					<div field="indexDisp" headerAlign="center" allowSort="true" class="nui-textarea" >如有特殊事项，请各位填入备注</div>
					<div field="stringType" headerAlign="center" allowSort="true" visible='false' class="nui-textarea" visible ='false' >备注</div>
				</div>
			</div>
		</div>
		
	</div>
	<div>
		
	</div>
    <div id="save" class="nui-toolbar"  style="border-bottom:0;text-align:right;margin-top: 20px;">
		<a class="nui-button" id="saveBtn" iconCls="icon-save" onclick="save()">保存</a>
	</div>	    
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var form2 = new nui.Form("#form2");
	var form4 = new nui.Form("#form4");
	var form1 = new nui.Form('#form1_1');
	var grid1 = nui.get("datagrid1");
	var grid2 = nui.get("datagrid2");
	var grid1_1 = nui.get("datagrid1_1");
	var grid2_1 = nui.get("datagrid2_1");
	var reAud="<%=request.getParameter("reAud")%>";	
	var partyId ="<%=request.getParameter("partyId") %>";//参与人id
	var applyId;//评级申请id;
	var posicode;//当前用户岗位
    var allowModifyFlag;//是否允许保存
    var modelId;//模型id
    var modelTypeCd;//评级模型
    var isImport = 0; //是否导入财务模版  1:导入财务模板 0：录入财务模板
    var financeId ;
    var flg;//年报是否超过18个月 
    var	flgFinance;//选择的年报是否最新（1：是，0：否）
    function search(){
    	if(reAud == "0"){
    		applyId = "<%=request.getParameter("applyId") %>";
    	}else{
    		applyId = "<%=request.getParameter("oldApplyId") %>";
    
    	}
    	var json = nui.encode({"applyId":applyId});
    	nui.ajax({
		        url: "com.bos.irm.queryInfo.queryRatingModelAndVer.biz.ext",//判断评级模型
		        type: 'POST',
		        data: json,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        	} else {
		        		nui.get("modelTypeCd").setValue(text.modelTypeCd); 
		        		nui.get("modelId").setValue(text.modelId); 
		        		modelId = text.modelId;
		        		modelTypeCd = text.modelTypeCd; 
		        		isImport = text.isImport; 
		        
		            }
		        }	
		});
		if(isImport == 1){
			var json1 = nui.encode({"applyId":applyId,"modelId":modelId});
			nui.ajax({//该客户所有财报集
			        url: "com.bos.irm.queryInfo.queryFinanceReports.biz.ext",
			        type: 'POST',
			        data: json1,
			        cache: false,
				    async:false,        
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		alert(text.msg);
			        	} else {
			        		var o = nui.decode(text);
			              	grid1.setData(o.corps);
			               	posicode = o.posicode;//当前用户岗位
			            }
			        }
			});
			nui.ajax({//被储存的年报信息
		        url: "com.bos.irm.queryInfo.queryFinanceReport.biz.ext",
		        type: 'POST',
		        data: json1,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        	}else{
		        		var o = nui.decode(text);
			            grid2.setData(o.items);
			            nui.get("remark1").setValue(o.remark);
			            nui.get("remark2").setValue(o.remark);
		        	}
		        }
			});
		}
		if(isImport == 0){//C9,S1,S2时进行加载
			grid1_1.load({"applyId":applyId,"modelId":modelId});
			grid2_1.load({"applyId":applyId,"modelId":modelId});
		}
		getAllowModifyFlag();//是否允许保存
		getRateState();//获取评级状态并控制画面的显示
	}
	search();
	
	function getAllowModifyFlag(){//是否允许保存
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
		            }
		        }
		});
	}
	function getRateState(){
	    var state;//评级处于状态
	   // var financeId;
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
	    if(reAud == "1"){//再审核 
	    	$("#save").hide();
	    	document.getElementById("form1").style.display="none";//隐藏
	    	if(isImport == 1){
	    		document.getElementById("form6").style.display="none";//隐藏
	    		$("#form6").hide();
	    	}else{
	    		document.getElementById("form5").style.display="none";//隐藏
	    		$("#query2").hide();	
	    	}
	    	
	    }else{
		    if(state == ("1")){//待审核      
		    	$("#save").hide();
		    	document.getElementById("form1").style.display="none";//隐藏
		    	if(isImport == 1){
		    		document.getElementById("form6").style.display="none";//隐藏
		    	}else{
		    		document.getElementById("form5").style.display="none";//隐藏
		    		$("#query2").hide();
		    	}
		    }
		    if(state == ("2")){//待认定{
		    	$("#save").hide();
		    	document.getElementById("form1").style.display="none";//隐藏
		    	if(isImport == 1){
		    		document.getElementById("form6").style.display="none";//隐藏
		    	}else{
		    		document.getElementById("form5").style.display="none";//隐藏
		    		$("#query2").hide();
		    	}
		    }
			if(state == ("0")){//待发起
				$("#form2").hide();
				if(isImport ==1){
					$("#form4").hide();
					var rows = grid2.findRows();
					financeId = rows[0].financeId;
					grid1.findRows(function(row){
						if (row.financeId == financeId){
						    grid1.select(row);
						}
					});
				}else{
					document.getElementById("form3").style.display="none";//隐藏
					$("#query").hide();
				}
		    }
		    if(state == ("3")){//退回重新发起
				$("#form2").hide();
			   if(isImport ==1){
			   		$("#form4").hide();
					var rows = grid2.findRows();
					financeId = rows[0].financeId;
					grid1.findRows(function(row){
					    if (row.financeId == financeId){
					    	grid1.select(row);
					    }
					});
				}else{
					document.getElementById("form3").style.display="none";//隐藏
					$("#query").hide();
				}
			}
		}
	}
	
 
	
	function save(){
	    
		//var financeId;
		if(allowModifyFlag == 2){
			alert("获取评级结果次数过多，不能进行保存！");
			return;
		}
		if(isImport == 0){//C8,S1,S2
			var  data = grid1_1.findRows();
			if (data.length　> 0) {
	        } else {
	            return alert("请填写指标权重！");
	        }
	        var isInput = grid1_1.findRows();
	        for (var i = 0 ; i < isInput.length ;i++){
				if (isInput[i].indexValueDataType){
				}else{
<!--				   return alert("请完整填写所有列出的财务指标！");
-->				}
			}
 	        var json = nui.encode({"item":{"reportId":financeId,"applyId":applyId},"data":data,"isImport":isImport,"modelTypeCd":modelTypeCd});
			nui.ajax({
				url:"com.bos.irm.insertInfomercial.addFinancialInfo.biz.ext",
				type: 'POST',
		  		data: json,
		  		cache: false,
		  	    contentType:'text/json',
		  	    async:false,
		  	    success: function (txt) {
		  	    		alert(txt.msg);
		  	    },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
	    	});
		}
		if(isImport == 1){//非C9,S1,S2
			var row = grid1.getSelected();;
			if(row){
		  		financeId = row.financeId;
			  	if(row.financeTypeCd != '1'){
			  		return alert("只能保存年报！");
			  	}
			}else {
		        return  alert("请选中一条记录进行保存！");
		    }
		    var json1 = nui.encode({"financeDeadline":row.financeDeadline});
	    	nui.ajax({//校验年报是否在18个月以内
				url:"com.bos.irm.queryInfo.checkFinanceDeadline.biz.ext",
				type: 'POST',
		  		data: json1,
		  		cache: false,
		  	    contentType:'text/json',
		  	    async:false,
		  	    success: function (txt) {
		  	    	if(txt.msg){
		  	    		flg = 1;
		  	    		alert(txt.msg); 	
		  	    		return;
		  	    	}else{
		  	    		flg = 0;
		  	    	}
		  	    },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
	    	});
    	
	    	if(flg == 0){//18个月以内
	    		var json2 = nui.encode({"financeId":financeId,"partyId":partyId});
	    		nui.ajax({
					url:"com.bos.irm.queryInfo.checkFirstFinance.biz.ext",
					type: 'POST',
			  		data: json2,
			  		cache: false,
			  	    contentType:'text/json',
			  	    async:false,
			  	    success: function (txt) {
			  	    	var o = nui.decode(txt);
			  	    	flgFinance = o.flg;
			  	    },
			        error: function (jqXHR, textStatus, errorThrown) {
			            alert(jqXHR.responseText);
			        }
		    	});
		    	var txt = nui.get("remark1").getValue();
		    	if(flgFinance == 1){//最新的和选择的财报id相同
	  	    	}else{//不相同
	  	    		if(txt){//填写
	  	    		}else{//未填写
	  	    			alert("所选择财务报表并非最新的年度财务报表，若确认，请录入理由。");
	  	    			return;
	  	    		}
	  	    	}
				if(row.auditedInd == "0"){//未审计标识
						nui.confirm("确定选择未审计的财务报表吗？","确认",function(action){
			            	if(action!="ok"){
			            		search();
			            	}else{
			            		//插入财报信息
						    	//var json = nui.encode({"item":{"reportId":financeId,"applyId":applyId}});
						    	var json = nui.encode({"item":{"reportId":financeId,"applyId":applyId},"data":data,"isImport":isImport,"modelTypeCd":modelTypeCd,"remark":txt});
								nui.ajax({
									url:"com.bos.irm.insertInfomercial.addFinancialInfo.biz.ext",
									type: 'POST',
							  		data: json,
							  		cache: false,
							  	    contentType:'text/json',
							  	    async:false,
							  	    success: function (txt) {     	
							  	    		alert(txt.msg);
							  	    },
							        error: function (jqXHR, textStatus, errorThrown) {
							            alert(jqXHR.responseText);
							        }
						    	});
						    }
			            });
				}else{
					//已审计
					//var json = nui.encode({"item":{"reportId":financeId,"applyId":applyId}});
					var json = nui.encode({"item":{"reportId":financeId,"applyId":applyId},"data":data,"isImport":isImport,"modelTypeCd":modelTypeCd,"remark":txt});
					nui.ajax({//插入财务信息表
						url:"com.bos.irm.insertInfomercial.addFinancialInfo.biz.ext",
						type: 'POST',
				  		data: json,
				  		cache: false,
				  	    contentType:'text/json',
				  	    async:false,
				  	    success: function (txt) {     	
				  	    		alert(txt.msg);
				  	    },
				        error: function (jqXHR, textStatus, errorThrown) {
				            alert(jqXHR.responseText);
				        }
			    	});
				}
			}
		}
	}
    function edit1() {
        var row = grid1.getSelected();
        if (row) {
            nui.open({
                url: "acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="+row.financeId+"&view="+1+"&reportType="+row.customerTypeCd+"&financeTypeCd="+row.financeTypeCd,
                title: "财务报表信息", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                   if(v=='0'){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }   
    }
    function edit2() {
        var row = grid2.getSelected();
        if (row) {
            nui.open({
                url: "acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="+row.financeId+"&view="+1+"&reportType="+row.customerTypeCd+"&financeTypeCd="+row.financeTypeCd,
                title: "财务报表信息", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                   if(v=='0'){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }   
    }
 	
 	function getIndexName(e){
		var rowIndex = e.rowIndex;
		var row=e.row;
		var column=e.column;
		var editor=e.editor;
		if (e.field == 'indexName'){
			if(row.propertyTypeCd==""||row.propertyTypeCd==null){
				alert("请先选择指标小类");
				return;
			}
			var json=nui.encode({"tbAccFinanceIndexCd":{"propertyTypeCd": row.propertyTypeCd}});
			//alert(json);
			$.ajax({
		        url: "com.bos.irm.model.getTbAccFinanceIndexCd.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		        		editor.load(text.tbAccFinanceIndexCds);
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
			});
		}
	}
	
	var propertyTypeCd;
	function onPropertyTypeCdChanged(e){
		if(e.field=='propertyTypeCd'){
			if(propertyTypeCd!=e.value){
		
				grid.updateRow(e.row, {"indexName":""});
				
			}
		}	
	}
	
	//是否引入财报信息
	function showOn(e){
        if(e.value=="true"){
			//$("#datagrid1_1").hide();
			document.getElementById("form4").style.display="none";//隐藏
			document.getElementById("form3").style.display="block";//显示
			isImport = 1;
        }else{
            //$("#datagrid1").hide();
            document.getElementById("form4").style.display="block";//显示
            document.getElementById("form3").style.display="none";//隐藏
            isImport = 0;
        }  
           
    }
 	    	
</script>
</body>
</html>