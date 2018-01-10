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
	<!-- 显示选项卡 -->
	<div id="dynpanelDiv">
	</div>
	<!-- 债项信息ID -->
	<div id="aldInfoIdDiv">
	</div>
	
	<!-- 保存和关闭按钮 -->
   <div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">确定</a>
	</div>
	
	
<script type="text/javascript">
	
	nui.parse();
	var param=<%=request.getParameter("param") %>;
	var aldInfoId="<%=request.getParameter("aldInfoId") %>";
	git.mask();
	var targetNumList;
	var pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":"firstInspectDebtPage"});
	$.ajax({
			url: "com.bos.aft.dailyInspect.getTargetNum.biz.ext",
			type: 'POST',
			data: pageJsonData,
			async:false,
			cache: false,
			contentType:'text/json',
			success: function (text) {
			     targetNumList=text.targetNums;
				},
			error: function () {
		        
		        }
			});
	
	//选项卡设置
	//定义选项卡个数
	
	
	if(targetNumList==null||targetNumList==undefined){
		var k=0;
	}else{
		var k = targetNumList.length;
	}
	
	//定义指标代码
	//var indexCdBefore = "ACRS040";
	//传入贷后检查客户情况ID
	var alcInfoId =param.alcInfoId;
	var lastAlcInfoId=param.lastAlcInfoId;
	//var any = [{"indexCd":"","indexCdId":""},{"indexCd":"","indexCdId":""}];
	var any = new Array();
	for(var i=0;i<k;i++) {
		any[i] = {};
	}
	
	//显示选项卡
	function initGrid() {
		$('#dynpanelDiv').html('');
		$('#aldInfoIdDiv').html('');
		for(var j=1;j<=k;j++) {//构造所有选项卡并初始化选择----选项卡个数调整
			//构造单一选项卡并初始化选择
			var json=nui.encode({"indexCd":targetNumList[j-1],
								"alcInfoId":alcInfoId,
								"lastAlcInfoId":lastAlcInfoId,
								 "aldInfoId":aldInfoId});
			$.ajax({
		            url: "com.bos.aft.dailyInspect.getDebtOptionCards.biz.ext",
		            type: 'POST',
		            data: json,
		            async:false,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	//alert(nui.encode(text));
		            	var temp = {};
		            	var pros = text.optionCards;
		            	var app = new Array();
		            	var multiSelect = false;
		            	//构造选项卡
		            	for(var i=0;i<pros.length;i++) {//循环开始
		            		if(i == 0) {//标题
		            			temp.id = pros[i].indexCd;
		            			temp.text = pros[i].indexName;
		            			//根据指标代码，取指标代码ID
		            			any[j-1].indexCd = pros[i].indexCd;
		            			any[j-1].indexCdId = pros[i].indexCdId;
		            			
		            			//alert(any[j-1].indexCd+";"+any[j-1].indexCdId);
		            			//door.poor[j].indexCd = 
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
		            	//temp.data1 = text.data1s[0].indexChoice;
		            	//temp.data2 = text.data2s[0].indexChoice;
		            	
		            	if(text.loanIdxDataResult== null||text.loanIdxDataResult.indexChoice==null) {
		            		temp.data1 = "";
		            	} else {
		            		temp.data1 = text.loanIdxDataResult.indexChoice;
		            	}
		            	if(text.lastLoanIdxDataResult== null||text.lastLoanIdxDataResult.indexChoice==null) {
		            		temp.data2 = "";
		            	} else {
		            		temp.data2 = text.lastLoanIdxDataResult.indexChoice;
		            	}
		            	var dynpanelId = 'dynpanel'+j;
		            	var aliDataId='aliDataId'+j;
		            	var html='<div id="'+dynpanelId+'" class="nui-dynpanel2" width="100%" columnValueFields="data1,data2" '
	    					+ 'dataField="children" colAlign="left,left" colWidth="80%,20%" multiSelect="'+multiSelect+'"> </div>';
	    				var dom=$(html);
	    				var html2='<input id="'+aliDataId+'" class="nui-hidden" value="'+text.loanIdxDataResult.aliDataId+'" name="'+aliDataId+'" />';
	    				var dom2=$(html2);
	    				dom.appendTo($('#dynpanelDiv'));
	    				dom2.appendTo($('#aldInfoIdDiv'));
	    				nui.parse(document.getElementById(dynpanelId));
	    				nui.parse(document.getElementById(aliDataId));
		            	nui.get(dynpanelId).setValue(temp);
	    				//选项卡分隔行
	    				$('<br/>').appendTo($('#dynpanelDiv'));
		            },
		            error: function () {
	                    nui.alert("操作失败！");
	                }
			});
		}
		
	}
	initGrid();
	git.unmask();
	//保存选择
	function save() {
		nui.get("btnSave").setEnabled(false);
		var flag=1;
		for(var i=1;i<=k;i++) {
			//var flag = nui.get('dynpanel'+i).isChanged();
			
				var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
				var con = nui.get('dynpanel'+i).getValue();
				
				var newData1 = con.data1;
				//用户首次选择设置
				if(newData1 == "" || newData1 == null) {
					continue;
				}
				var aliDataId=nui.get('aliDataId'+i).getValue();
				var json=nui.encode({"aliDataId":aliDataId,"indexChoice":newData1,"updateTime":timestamp});
				$.ajax({
			            url: "com.bos.aft.dailyInspect.updateDebtOption.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	if(text.msg==0){
			            		flag=0;
			            		
			            	}
			            	//nui.alert("保存成功！");
						},
			            error: function () {
		                    flag=0;
		                }
				});
				if(flag==0){
					break;
				}
		}
		initGrid();
		//提示用户保存成功
		if(flag==1){
			//nui.alert("保存成功！");
			CloseWindow();
		}else{
			nui.alert("保存失败！");
			nui.get("btnSave").setEnabled(true);
		}
	}
	
	/*
		备注：
			1、如果客户为集团客户成员公司，则显示下述信息，并且必须填写下述信息；
			2、如果选择否，则将复选清空，并变为只读
	*/
</script>
</body>
</html>