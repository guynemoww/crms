<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lizhi
  - Date: 2014-05-07
  - Description:
-->
<head>
<title>基本情况</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div>
		<a></a>
	</div>
	<a class="nui-button" style="margin-right:55px;float: left;margin-top: 20px;" iconCls="icon-save" onclick="reLoad()" id="btnSave">获取上期值</a>
	<!-- 显示选项卡 -->
	<div id="dynpanelDiv">
	</div>
	<!-- 债项信息ID -->
	<div id="aldInfoIdDiv">
	</div>
	
	<!-- 保存和关闭按钮 -->
   <div class="nui-toolbar" style="text-align:right;border:none">
    <a class="nui-button" style="margin-right:55px;" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	</div>
	
	
<script type="text/javascript">
	
	nui.parse();
	var param=<%=request.getParameter("param") %>;
	var rows=<%=request.getParameter("rows")%>;                        //获取勾选的债项
	var goEdit = "<%=request.getParameter("goEdit") %>";
	var aldInfoId="<%=request.getParameter("aldInfoId") %>";           //贷后检查债项情况ID
	var loanDirection="";                                              //贷款发放后主要投向
	var callback='<%=request.getParameter("callback") %>';		//用于判断是否显示保存按钮（客户经理显示，营销团队负责人不显示）
	if(null!='<%=request.getParameter("loanDirection") %>'){   
	   loanDirection="<%=request.getParameter("loanDirection") %>";   //贷款发放后主要投向
	}
	var loanSummaryId="<%=request.getParameter("loanSummaryId") %>";  //借据ID
	var alcInfoId =param.alcInfoId;
	var lastAlcInfoId=param.lastAlcInfoId;
	//alert(lastAlcInfoId);
	var indexResults;
	//var loansMainlyToResult;
	var getFirst = "<%=request.getParameter("getFirst") %>";
	var reld="0";
	var partyId = "<%=request.getParameter("partyId") %>";
	if(getFirst=="1"){
	  reld="1";
	}
	function reLoad(){
	 var url=nui.context+"/aft/dailyInspect/debtDataInfo.jsp?param="+nui.encode(param)+"&callback="+callback+"&getFirst=1"+"&aldInfoId="+aldInfoId+"&partyId="+partyId+"&loanSummaryId="+loanSummaryId+"&rows="+nui.encode(rows);
	 git.go(url);
	}
	git.mask();
	function initGrid(){
	if(reld =="0"){
         var pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":"debtPage","alcInfoId":alcInfoId,"lastAlcInfoId":lastAlcInfoId,"aldInfoId":aldInfoId,loanSummaryId:loanSummaryId});       //获取本期值
  		}else{
         var pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":"debtPage","alcInfoId":lastAlcInfoId,"lastAlcInfoId":lastAlcInfoId,"aldInfoId":aldInfoId,show:"1",loanSummaryId:loanSummaryId});//获取上期值
		}
		$.ajax({
			url: "com.bos.aft.dailyInspect.getTargetNum.biz.ext",
			type: 'POST',
			data: pageJsonData,
			async:false,
			cache: false,
			contentType:'text/json',
			success: function (text) {
			     indexResults=text.indexResults;
			     //loansMainlyToResult=text.loansMainlyTo;                              //主要投向
				},
			error: function () {
		        
		        }
			});
		if(indexResults!=null&&indexResults.length>0){
					for(var m=0;m<indexResults.length;m++){
			  			var temp = {};
		            	var pros = indexResults[m].optionCards;
		            	var app = new Array();
		            	var multiSelect = false;
		            	//构造选项卡
		            	for(var i=0;i<pros.length;i++) {//循环开始
		            		if(i == 0) {//标题
		            			temp.id = pros[i].indexCd;
		            			temp.text = (m+1)+"、"+pros[i].indexName;
		            			//判断单双选
		            			if(pros[i].standardIndexCd === "2"){
		            				multiSelect = true;
		            			}
		            		} else {//选项
		            			app[i-1] = {id:pros[i].indexCd,text:pros[i].indexName};
		            		}
		            	}//循环结束
		            	temp.children = app;
		            	
		            	//初始化选择
		            	if(indexResults[m].loanIdxDataResult== null||indexResults[m].loanIdxDataResult.indexChoice==null) {
		            		temp.data1 = "";
		            		
		            	} else {
		            		temp.data1 = indexResults[m].loanIdxDataResult.indexChoice;
		            	}
		            	if(indexResults[m].lastLoanIdxDataResult== null||indexResults[m].lastLoanIdxDataResult.indexChoice==null) {
		            		temp.data2 = "";
		            	} else {
		            		temp.data2 = indexResults[m].lastLoanIdxDataResult.indexChoice;
		            	}
		            	if(indexResults[m].loanIdxDataResult== null||indexResults[m].loanIdxDataResult.comment==null) {
		            		temp.data3 = "";
		            		
		            	} else {
		            		temp.data3 = indexResults[m].loanIdxDataResult.comment;
		            	}
		            	var n=m+1
		            	var dynpanelId = 'dynpanel'+n;
		            	var aliDataId='aliDataId'+n;
		            	var html='<div id="'+dynpanelId+'" class="nui-dynpanel2" width="100%" columnValueFields="data1,data2" '
	    					+ 'dataField="children" colAlign="left,left" colWidth="70%,30%" multiSelect="'+multiSelect+'"';
	    				/*
	    					if(m==(indexResults.length-2)){
	    						 html+='onvaluechanged=valueChange('+dynpanelId+')';
	    					}
	    				*/	
	    				 html+='> </div>';
	    				var dom=$(html);
	    				var html2='<input id="'+aliDataId+'" class="nui-hidden" value="'+indexResults[m].loanIdxDataResult.aliDataId+'" name="'+aliDataId+'" />';
	    				var dom2=$(html2);
	    				dom.appendTo($('#dynpanelDiv'));
	    				dom2.appendTo($('#aldInfoIdDiv'));
	    				git._doParse(document.getElementById(dynpanelId));
	    				git._doParse(document.getElementById(aliDataId));
		            	nui.get(dynpanelId).setValue(temp);
	    				//选项卡分隔行
	    				$('<br/>').appendTo($('#dynpanelDiv'));
	    				/*if(m==(indexResults.length-2)){
	    					var html3='<div id="textPannel"><div class="nui-dynpanel" columns="2"><label>贷款发放投向:</label>'+
	    							'<input class="nui-textbox nui-form-input" id="loanDirection" enabled="false"/>'+
	    							'<label>修改贷款发放后主要投向:</label>'+
	    							'<input class="nui-textbox nui-form-input" id="loansMainlyTo" name="loansMainlyTo" enabled="false"/></div></div>';
	    					var dom3=$(html3);
	    					dom3.appendTo($('#dynpanelDiv'));
	    					$('<br/>').appendTo($('#dynpanelDiv'));
	    					git._doParse(document.getElementById("textPannel"));
	    					var loanDirectionName = nui.getDictText("XD_KHCD0092",loanDirection);
	    					var loansMainlyToResultName = nui.getDictText("XD_KHCD0092",loansMainlyToResult);
	    					nui.get("loanDirection").setValue(loanDirectionName);                  //贷款发放投向
	    					nui.get("loansMainlyTo").setValue(loansMainlyToResultName);            //修改贷款发放后主要投向
	    				}*/
				}
				
	}
	}
	initGrid();
	git.unmask();
	
	//保存选择
	var k;
	if(indexResults==null||indexResults==undefined){
		var k=0;
	}else{
		var k = indexResults.length;
	}
	var saveData = new Array();
	for(var i=0;i<k;i++) {
		saveData[i] = {};
	}
	
	function save() {
		nui.get("btnSave").setEnabled(false);
		var flag=1;
		//var loansMainlyTo=nui.get("loansMainlyTo").getValue();
		
		for(var i=1;i<=k;i++) {
				var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
				var con = nui.get('dynpanel'+i).getValue();
				var titleIndexCd = con.id;
				var newData1 = con.data1;
				var commentText=con.data3;                                         //现在的相关描述
				var aliDataId=nui.get('aliDataId'+i).getValue();
				
				var result = document.getElementById(titleIndexCd).name;
			    var checkList =  document.getElementsByName(result);
			    for(var h=0;h<checkList.length;h++){
				    if(checkList[h].checked){
					   result = checkList[h].value;
					   break;
				       }
			        }
			if(result==1){ 
			if((newData1 == null || $.trim(newData1) == "" || typeof(newData1) == "undefined")||(commentText == null || $.trim(commentText) == "" || typeof(commentText) == "undefined")){
					alert("第"+(i)+"个选项，选择和相关描述不能为空！");
					git.unmask();
					nui.get("btnSave").setEnabled(true);
					return;
				} 
			}
				
				saveData[i-1].aliDataId =aliDataId;
		        saveData[i-1].newData1 =newData1;
		        saveData[i-1].commentText=commentText;
		        saveData[i-1].timestamp=timestamp;
		        saveData[i-1].indexId=con.id;//选项卡标题ID
		 }
			var json=nui.encode({"saveData":saveData,"aldInfoId":aldInfoId,rows:rows});
			$.ajax({
			    url: "com.bos.aft.dailyInspect.batchUpdateDebtOptions.biz.ext",
			    type: 'POST',
			    data: json,
			    cache: false,
			    contentType:'text/json',
			    success: function (text) {  
			         reld="1";
			         CloseWindow();
			         nui.get("btnSave").setEnabled(true);
					},
			    error: function () {
		           nui.alert("保存失败！");
		           nui.get("btnSave").setEnabled(true);
		            }
			});
		
	}
	
	function valueChange(dynpanelId){
		if(nui.get(dynpanelId).getValue()=="AFTL061101"){
			//nui.get("loansMainlyTo").allowInput=true;//.class="nui-textbox nui-form-input"
		}else if(nui.get(dynpanelId).getValue()=="AFTL061102"){
			//nui.get("loansMainlyTo").allowInput=false;//.class="nui-text nui-form-input"
		}
	}
	
</script>
</body>
</html>