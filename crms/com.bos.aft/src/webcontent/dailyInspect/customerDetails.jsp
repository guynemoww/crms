<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lizhi
  - Date: 2014-05-13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<!-- 显示贷后检查客户情况 -->	    
	<div id="tableDiv">
		
	</div>

	<script type="text/javascript">
 	nui.parse();
	//var form = new nui.Form("#tableDiv");
	
	//传入贷后检查客户情况ID
	var lastAlcInfoId = '<%=request.getParameter("lastAlcInfoId") %>';
	var pageName = '<%=request.getParameter("pageName") %>';
	var $tableDiv = $('#tableDiv');
	var titles = ['标题','本期选择','相关描述','上期选择','相关描述'];
	var inputArray = [];//保存"项目贷款类输入"所需数据
	var inputArrayIndex = 0;
	var k = 0;//序号
	
	//用户直接创建贷后检查查看流程
	if(lastAlcInfoId == null || lastAlcInfoId == '') {
		lastAlcInfoId = '990099';//数据库中不存在的id
	}
	//获取客户情况选项卡的选项值
	getAlcInfoOptionsValueList(pageName,lastAlcInfoId,$tableDiv);
	
	//创建项目资本金填写表格
	if(pageName == 'basicConditionPage') {
    	createInputTable(lastAlcInfoId,$tableDiv,k);
	}
	
	
    function getAlcInfoOptionsValueList(f_pageName,f_lastAlcInfoId,f_$tableDiv) {
    	
    	var pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":f_pageName,"alcInfoId":f_lastAlcInfoId});
		$.ajax({
				url: "com.bos.aft.dailyInspect.getTargetNum.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					//生成表格
					createTable(titles,text,f_$tableDiv);
					//document.write(nui.encode(text));
				},
				error: function () {
			        
			    }
		});
    }
    
    //动态生成表格 注意：显示时，序号表示之前的标题号，而非顺序号
    function createTable(f_titles,f_content,f_$tableDiv) {
    	if(f_titles == null || f_titles == ''
    		|| f_content == null || f_content == ''
    		|| f_$tableDiv == null || f_$tableDiv == '') {
    		alert('生成表格参数错误');
    		return;
    	}
    	
    	var ths = '';
    	//生成列名
    	for(var i=0;i<f_titles.length;i++) {
    		ths +='<th class="nui-form-label" align="center" colspan="1" width="220">'+f_titles[i]+'</th>';
    	}
    	
    	var tableString = '<table id="table"  border="" width="99.5%" class="nui-form-table">'+
		    								 '<tr>'+
							    			   ths
							    		     '</tr>'+
   									       '</table>';
   		var newTable = $(tableString);
   		
   		if(f_content.indexResults == null || f_content.indexResults == '') {
   			alert('indexResults中的内容为空');
   			return;
   		}
   		//生成行
   		var tdId = 0;
   		var mergeArray = [];
   		var beforeFlag = true;//如果上期选择不存在，则合并单元格
   		var currentFlag = true;//如果本期选择不存在，则合并单元格
   		for(k=0;k<f_content.indexResults.length;k++) {
   			tdId++;
			var optionTitle = '';
			var currentOptions = [];
			var currentOptionsIndex = 0;
			var currentRelatedDescribe = '';
			
			var beforeOptions = [];
			var beforeOptionsIndex = 0;
			var beforeRelatedDescribe = '';
					    
			var orglevel = f_content.indexResults[k].orglevel;
			var data1s = f_content.indexResults[k].data1s;
			var data2s = f_content.indexResults[k].data2s;
			var optionCards = f_content.indexResults[k].optionCards;
			
						//alert(nui.encode(optionCards));
						if(optionCards == null || optionCards.length == 0) {
							alert(nui.encode(optionCards));
							alert('optionCards中的内容为空');
							return;
						}
						//选项标题
						optionTitle = optionCards[0].indexName;
						
						for(var i=0;i<optionCards.length;i++) {
							//构造"项目贷款类输入"所需数据
							if(optionCards[0].indexCd == "PRJV010100") {//填写表格
								inputArray[inputArrayIndex++] = {"indexCd":optionCards[i].indexCd
																,"indexName":optionCards[i].indexName
																,"indexCdId":optionCards[i].indexCdId};
							} else {//选项卡
							
								//本期选择
								if(data1s != null && data1s.length != 0) {
									//alert('没有本期选择，data1s中的内容为空');
									//continue;
									var data1sArray = data1s[0].indexChoice.split(',');
									currentRelatedDescribe = (data1s[0].comment == 'notHaveCommentText'? '': data1s[0].comment);
									for(var j=0;j<data1sArray.length;j++) {
										if(data1sArray[j] == optionCards[i].indexCd) {
											currentOptions[currentOptionsIndex++] = optionCards[i].indexName;
										}
									}
								}
								
								//上期选择
								if(data2s != null && data2s.length != 0) {
									var data2sArray = data2s[0].indexChoice.split(',');
									beforeRelatedDescribe = (data2s[0].comment == 'notHaveCommentText'? '': data2s[0].comment);
									for(var g=0;g<data2sArray.length;g++) {
										if(data2sArray[g] == optionCards[i].indexCd) {
											beforeOptions[beforeOptionsIndex++] = optionCards[i].indexName;
										}							
									}
								}
							}
							
						}
						
						var rowNum = 1;//取本期或上期选择的最大选择数
						if(currentOptions != null && currentOptions.length != 0) {//本期选择存在
							if(beforeOptions != null && beforeOptions.length != 0) {//上期选择存在
								rowNum = (currentOptions.length > beforeOptions.length) ? currentOptions.length : beforeOptions.length;
							} else {//上期选择不存在
								rowNum = currentOptions.length;
								beforeFlag = false;
							}
						} else {//本期选择不存在
							if(beforeOptions != null && beforeOptions.length != 0) {//上期选择存在
								rowNum = beforeOptions.length;
								currentFlag = false;
							} else {//上期选择不存在
								
							}
						}
						
						if(optionCards[0].indexCd == "PRJV010100") {
							continue;
						}
						
						for(var u=1;u<=rowNum;u++) {
							//tdId = tdId+u-1;
							var trString = '<tr id="'+u+'">'+
								'<td class="nui-form-label" id="'+(tdId)+'/1" colspan="1" width="20%">'+(k+1)+'、'+optionTitle+
								'</td><td class="nui-form-label" id="'+(tdId)+'/2" colspan="1" width="20%">'+(currentOptions[u-1]== undefined?'':currentOptions[u-1])+
								'</td><td class="nui-form-label" id="'+(tdId)+'/3" colspan="1" width="20%">'+(currentRelatedDescribe == null ? '':currentRelatedDescribe)+
								'</td><td class="nui-form-label" id="'+(tdId)+'/4" colspan="1" width="20%">'+(beforeOptions[u-1] == undefined?'':beforeOptions[u-1])+
								'</td><td class="nui-form-label" id="'+(tdId)+'/5" colspan="1" width="20%">'+(beforeRelatedDescribe == null ? '':beforeRelatedDescribe)+
								'</td></tr>';
							var newTr = $(trString);
							newTr.appendTo(newTable);
							
							mergeArray[tdId] = k+1;
							
							if((u < rowNum) && (rowNum > 1)) {
								tdId++;
							} 
						}
			}
			
   		newTable.appendTo(f_$tableDiv);
   		
   		//合并相同单元格
   		var typeCount = 1;
   		mergeArray = mergeArray.concat('end');//合并最后的单元格
   		//alert(nui.encode(mergeArray));
   		for(var i=2;i<mergeArray.length;i++) {
	   		if(mergeArray[i-1] == mergeArray[i]) {
	   			typeCount++;	
	   		} else {
		   		//合并
		   		merge(i-typeCount,i-1,1,1);//合并标题
		   		merge(i-typeCount,i-1,3,3);//合并本期相关描述
		   		merge(i-typeCount,i-1,5,5);//合并上期相关描述
		   		/*
		   		if(!beforeFlag) {//上期选择不存在
		   			merge(i-typeCount,i-1,4,4);//合并选项栏
		   		}
		   		
		   		if(!currentFlag) {//本期选择不存在
		   			merge(i-typeCount,i-1,2,2);//合并选项栏
		   		}
		   		*/
		   		
		   		typeCount = 1;
	   		}
   		}
		nui.parse(document.getElementById('table'));
    }
    
    function createInputTable(f_alcInfoId,f_$tableDiv,k) {
    	//填写项目资本金等
		    	var inputItems = inputArray;
		    	var currentInputValues;
		    	var beforeInputValues;
		    	var currentInputJsonData=nui.encode({"alcInfoId":f_alcInfoId,"indexId":"PRJV010100"});
				$.ajax({
						url: "com.bos.aft.dailyInspect.getInputValues.biz.ext",
						type: 'POST',
						data: currentInputJsonData,
						async:false,
						cache: false,
						contentType:'text/json',
						success: function (text) {
							currentInputValues = text.currentInputValues;
							beforeInputValues = text.beforeInputValues;
						},
						error: function () {
					        
					    }
				});
				//标题行
				var titleText = '<br><div>'+k+'、'+inputItems[0].indexName+'</div>';
		    	var tableString = '<table id="table"  border="" width="99.5%" class="nui-form-table">'+
		    								 '<tr>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%"></th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">金额</th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">比例</th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">上期金额</th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">上期比例</th>'+
							    		     '</tr>'+
   									       '</table>';
		    	var newTable = $(tableString);
				for(var r=0;r< 4;r++) {//数据行
					
			    	var trString = '<tr id="">'+
									'<th class="nui-form-label" id="" colspan="1" style="text-align:right" width="20%">'+inputItems[r+1].indexName.replace("金额","：")+'</th>'+
								   '</tr>';
					var newTr = $(trString);
					for(var n=0;n<4;n++) {//数据单元格
						var enable = "false";
						var required = "true";
						var value = 0;
						
						switch(n) {
							case 0:
								if(currentInputValues == null || currentInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(currentInputValues[2*r].indexValue);
								}
							break;
							case 1:
								if(currentInputValues == null || currentInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(currentInputValues[2*r+1].indexValue);
								}
							break;
							case 2:
								if(beforeInputValues == null || beforeInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(beforeInputValues[2*r].indexValue);
								}
							break;
							case 3:
								if(beforeInputValues == null || beforeInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(beforeInputValues[2*r+1].indexValue);
								}
							break;
							default:
							break;
						}
						
						var tdString = '<td class="nui-form-label" id="" colspan="1">'+
										'<input id="input'+r+'/'+n+'" class="nui-textbox nui-form-input" vtype="float"'+
										'width="98%" enabled="'+enable+'" value="'+value+'" required="'+required+'"/>'+
									   '</td>';
						var newTd = $(tdString);
						newTd.appendTo(newTr);
					}//数据单元格结束
					newTr.appendTo(newTable);
				}//数据行结束
				var titleTextDiv = $(titleText);
		    	titleTextDiv.appendTo(f_$tableDiv);
				newTable.appendTo(f_$tableDiv);
		    	$('<br/>').appendTo(f_$tableDiv);	
		    	nui.parse(document.getElementById('table'));
	}
    
    //合并相同的单元格
    function merge(beginrow,endrow,begincell,endcell){
		var rowspan=endrow-beginrow+1;
		var cellspan=endcell-begincell+1;
		for (var i=endrow;i>=beginrow;i--)
				for (var j=endcell;j>=begincell;j--) {
					if (i==beginrow&&j==begincell){}else{//指定的起始单元格不能删除
						if(document.getElementById(i+'/'+j)!=null) {
							elem=document.getElementById(i+'/'+j);elem.parentNode.removeChild(elem);	
						}
				}
		}
		var obj=document.getElementById(beginrow+'/'+begincell);
		obj.setAttribute('rowspan',rowspan);
		obj.setAttribute('colspan',cellspan);
	}
	</script>
</body>
</html>
