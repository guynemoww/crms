<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查报告</title>
</head>
<body style="margin-left:10px;">
    <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>一般授信业务详细信息</span>
             </legend>
	        <!-- 显示选项卡 -->
	        <div class="mini-dynpanel2Td" id="zhengFuShouXin"></div>
	        <div class="mini-dynpanel2Td" id="fangDiChanKaiFa"></div>
	        <div class="mini-dynpanel2Td" id="jingYingXingWuYe"></div>
	        <div class="mini-dynpanel2Td" id="creditInfo"></div>
	        <fieldset style="margin-top: 10px;">
  	           <legend>
    	                <span>保证人信息</span>
                </legend>
	            <!-- 显示选项卡 -->
	            <div class="mini-dynpanel2Td" id="warrantManInfo"></div>
	        </fieldset>
	        
	        <fieldset style="margin-top: 10px;">
  	           <legend>
    	                <span>担保合同信息</span>
                </legend>
	            <div id="contractInfo" class="nui-datagrid" url="com.bos.aft.checkReport.queryWarrantContractInfo.biz.ext" dataField="contractInfos" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                     <div field="subContractNum">合同序号</div>
                     <div field="subContractManualNum">保证从合同编号</div> 
                     <div field="partyName" >保证人名字</div> 
                     <div field="creditRatingCd" dictTypeId="CD000001">信用等级</div> 
                     <div field="guaranteeRightMoney">担保金额</div> 
                     <div field="currencyCd">币种</div> 
                     <!-- <div feild="whetherGovernmentFinanceCor">是否政府融资平台</div> -->
                 </div>
                </div>
	        </fieldset>
	        
	        <fieldset style="margin-top: 10px;">
  	           <legend>
    	                <span>抵质押信息</span>
                </legend>
	            <!-- 显示选项卡 -->
	            <div class="mini-dynpanel2Td" id="mortgage"></div>
	        </fieldset>
	        
	        <fieldset style="margin-top: 10px;">
  	           <legend>
    	                <span>抵质押合同信息</span>
                </legend>
	             <div id="guarantyContract" class="nui-datagrid" url="com.bos.aft.checkReport.queryGurantyContract.biz.ext" dataField="gurantyContracts" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                     <div field="partyName">抵质押人名称</div>
                     <div field="partyNum">抵质押人编号</div> 
                     <div field="sortType" dictTypeId="XD_DBCD4002">押品类型</div> 
                     <div field="assessCost" dictTypeId="CD000001">评估价值</div> 
                     <div field="assessWay" dictTypeId="YP_GLCD0010">评估方式</div> 
                     <div field="assessReg">评估机构</div> 
                     <!-- <div field="assessRegCode">评估机构组织机构代码</div>  -->
                     <div field="assessDate">评估日期</div> 
                 </div>
                </div>
	        </fieldset>
	        
	         <fieldset style="margin-top: 10px;">
  	           <legend>
    	                <span>信贷业务管理信息</span>
                </legend>
	            <!-- 显示选项卡 -->
	            <div class="mini-dynpanel2Td" id="creditMgrInfo"></div>
	        </fieldset>
	 </fieldset>

	<div class="nui-toolbar" style="border-bottom:0;text-align:right;">
	     <a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">确定</a>
	     
	</div>
  	<!-- <a class="nui-button" iconCls="icon-save" onclick="downLoad()" id="btnSave">下载</a> -->
</body>
<script type="text/javascript">
                                                                                           //源地址页面aft/dailyInspect/inspectReport.jsp
    nui.parse();
    var targeName = [];
    var errorMsg;                                                         //校验提示框
    var panDuan;                                                          //校验判断标示
    var guarantyContract = nui.get("guarantyContract");                   //抵质押合同信息
    var contractInfo = nui.get("contractInfo");                           //担保合同信息
    var alcInfoId = "<%=request.getParameter("alcInfoId") %>";            //本期贷后检查ID
    var lastAlcInfoId = "<%=request.getParameter("lastAlcInfoId") %>";    //上期贷后检查ID
    var aldInfoId;                                                        //债项检查ID
    var contractId = "<%=request.getParameter("contractId") %>";          //主合同ID
	var inputID = [];
    var bizId = "<%=request.getParameter("bizId") %>";       
    var partyId="<%=request.getParameter("partyId") %>";        //<%=request.getParameter("partyId") %>"8a70d1f045f318240145f340ca8f0014"
    var whetherGovernmentFinanceCor ="<%=request.getParameter("whetherGovernmentFinanceCor") %>";                     // "1";
    var productType = "<%=request.getParameter("productType") %>";    //"10100204";
    if(whetherGovernmentFinanceCor==1){                                      //是政府平台的客户
       if(productType=="10100204"){
         targeName = ["creditMgrInfo"];//"zhengFuShouXin","fangDiChanKaiFa","creditInfo","warrantManInfo","mortgage",
       }else if(productType=="10100205"){
         targeName = ["creditMgrInfo"];//"zhengFuShouXin","jingYingXingWuYe","creditInfo","warrantManInfo","mortgage",
       }else{
         targeName = ["creditMgrInfo"];//"zhengFuShouXin","creditInfo","warrantManInfo","mortgage",
       }
    }else{
        if(productType=="10100204"){
         targeName = ["creditMgrInfo"];//"fangDiChanKaiFa","creditInfo","warrantManInfo","mortgage",
       }else if(productType=="10100205"){
         targeName = ["creditMgrInfo"];//"jingYingXingWuYe","creditInfo","warrantManInfo","mortgage",
       }else{
         targeName = ["creditMgrInfo"];//"creditInfo","warrantManInfo","mortgage",
       }
    }
    var readOnly = "<%=request.getParameter("readOnly") %>";
    var isRead;
    if(readOnly==1){
        
        isRead=true;
        nui.get("btnSave").show();
    }else{
        isRead=false;
        nui.get("btnSave").hide();
    }
    
    function getAldInfoId(){
      var pageJsonData=nui.encode({"alcInfoId":alcInfoId});
      $.ajax({//获取页面选项卡集indexResults
				url: "com.bos.aft.dailyInspect.getAldInfoId.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					aldInfoId = text.aldInfoId;
				},
				error: function () {
			        
			    }
		});
  }
    
    function init(){
      //getAldInfoId();
      guarantyContract.load({partyId:partyId});             //加载从合同数据
      contractInfo.load({contractId:contractId});                 //加载抵质押合同数据
      for(var i=0;i<targeName.length;i++){
          initGrid(targeName[i]);
          //alert(nui.encode(inputID[targeName[i]]));
       }
       var pageJsonData = nui.encode({partyId:partyId,bizId:bizId})
    }
    init();

	function submitData(){
	    var dataObj=[];
	    var index=0;
	    panDuan = 0;
	    errorMsg = "";
	    for(var i=0;i<targeName.length;i++){
	      saveData(inputID[targeName[i]]);
	      if(panDuan==1){
	         alert(errorMsg);
	         return;
	      }
	    }
	    
	}
	
	function saveData(e){
	 var dataObj = e;
	 var num=dataObj.length;
		var dataObjects=[];
		for(var i=0;i<num;i++) {//遍历选项卡
			//var aliDataId=nui.get(dataObj[i]).getValue();
			var commentText = nui.get(dataObj[i]).getValue().data3;//现在的相关描述
			var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");//时间
			var context = nui.get(dataObj[i]).getValue();//获取对象值
			var titleIndexCd = context.id;//ACRS020100
			var newData1 = context.data1;//现在的选项
			var indexId = "";
			
			//校验  选项和描述不能同时为空
			if((newData1 == null || newData1 == '' || newData1 == undefined)
					&&(commentText == null || commentText == '' || commentText == undefined)){
					errorMsg = "第"+(i+1)+"个选项，选择和相关描述不能同时为空！";
				} 
			
			//校验多选，当选择“是”时，至少选择一项
			
			var result = document.getElementById(titleIndexCd).name;
			var checkList =  document.getElementsByName(result);
			for(var h=0;h<checkList.length;h++){
				if(checkList[h].checked){
					result = checkList[h].value;
					break;
				}
			}
			if(result==1){
			 if(newData1 =="")panDuan=1;
			 errorMsg ="多选为“是”时，至少选择一项！";
			}
			
			var temp = new Object();
			temp.comment=commentText;
			temp.updateTime=timestamp;
			temp.indexChoice=newData1;
			temp.indexId=titleIndexCd;
			temp.alcInfoId=alcInfoId;
			//temp.aliDataId=aliDataId=='null'?null:aliDataId;
			dataObjects[i]=temp;
			
	     }	
	       if(result==1){
			  return;
			}
	       var json=nui.encode({dataObjects:dataObjects});
	       $.ajax({
			            url: "com.bos.aft.checkReport.saveTarge.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	initGrid();
			            	git.unmask();
			            	if(text.msg){
			                      alert(text.msg);
			            	}
						},
			            error: function () {
		                    nui.alert("保存失败！");
		                }
				});
	
	}
  
  
//显示选项卡
	function initGrid(e) {
	    var indexResults;
	    var targetNums;                                                    //父级指标码
	    var k;                                                             //父级指标码长度
	    var IdName = '#'+e;
	    var targeName= e;                                                   //关联指标代码
		$(IdName).html('');
		var ogj = $(IdName);
		//$('#aliDataIdDiv').html('');
		var pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":targeName,"alcInfoId":alcInfoId,lastAlcInfoId:lastAlcInfoId,"aldInfoId":aldInfoId,show:1});
		$.ajax({//获取页面选项卡集indexResults
				url: "com.bos.aft.dailyInspect.getTargetNum.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					indexResults=text.indexResults;
				 targetNums = text.targetNums;
					inputID[e] = targetNums; 
					k = indexResults.length;
					//alert(nui.encode(inputID[e]));
				},
				error: function () {
			        
			    }
		});
		
		for(var j=0;j<k;j++) {//构造k个选项卡
			var temp = {};
		    var pros = indexResults[j].optionCards;
		    var options = new Array();
		    var multiSelect = false;
		    
		    for(var i=0;i<pros.length;i++) {//构造选项卡
		    	if(i == 0) {//标题
		        	temp.id = pros[i].indexCd;
		            temp.text = (j+1)+"、"+pros[i].indexName;
		            
		            if(pros[i].standardIndexCd === "2"){//判断单双选
		            	multiSelect = true;
		            }
		        } else {//选项
		        	options[i-1] = {id:pros[i].indexCd,text:pros[i].indexName};
		        }
		    }
		    
		    temp.children = options;
		   
		    
		    var h=j;
		    
		    //本期选择
		    if(indexResults[h].data1s == null || indexResults[h].data1s[0] == null 
		    	|| indexResults[h].data1s[0].indexChoice == null ) {
		        temp.data1 = "";
		    } else {
		        temp.data1 = indexResults[h].data1s[0].indexChoice;
		    }
		    
		    //上期选择
		    if(indexResults[h].data2s == null || indexResults[h].data2s[0] == null 
		    	|| indexResults[h].data2s[0].indexChoice == null ) {
		        temp.data2 = "";
		    } else {
		        temp.data2 = indexResults[h].data2s[0].indexChoice;
		    }
		    
		    //本期相关描述
		    if(indexResults[h].data1s[0] == null 
		    	|| indexResults[h].data1s[0].comment == null
		    	|| indexResults[h].data1s == null
		    	) {
		    	temp.data3 = '';
		    	
		    } else {
		    	temp.data3 = indexResults[h].data1s[0].comment;
		    }
		    
		    /*指标数据ID
		    if(indexResults[h].data1s == null||indexResults[h].data1s[0] == null
		     ||indexResults[h].data1s[0].aliDataId==null||indexResults[h].data1s[0].aliDataId==undefined) {
		    	v_aliDataId = null;
		    }else{
		    	v_aliDataId = indexResults[h].data1s[0].aliDataId
		    }*/
		    //动态构造选项卡
		    
		    var dynpanelId = targetNums[h];
		    //var aliDataId='aliDataId'+h;
		    var html='<div id="'+dynpanelId+'" class="nui-dynpanel2" width="100%" columnValueFields="data1,data2" '
	    			+ 'dataField="children" enabled="'+isRead+'" colAlign="left,left" colWidth="70%,30%" multiSelect="'+multiSelect+'"> </div>';
	    	var dom=$(html);
	    	//var html2='<input id="'+aliDataId+'" class="nui-hidden" value="'+v_aliDataId+'" name="'+aliDataId+'" />';
	    	//var dom2=$(html2);
	    	dom.appendTo(ogj);
	    	//dom2.appendTo($('#aliDataIdDiv'));
	    	var dynpane = document.getElementById(dynpanelId);
	    	git._doParse(document.getElementById(dynpanelId));
	    	var dynpane1 = nui.get(dynpanelId);
	    	//nui.parse(document.getElementById(aliDataId));
	    	//alert(dynpanelId+":"+nui.get(dynpanelId));
		    nui.get(dynpanelId).setValue(temp);
	    	$('<br/>').appendTo($(IdName));		
		}
	}
	
	function submitData(){
	    //grid.validate();
	    
	    //alert("grid"+grid.isValid());
	    
	    var dataObj=[];
	    var index=0;
	    var row = grid.findRow(function(row){
            if(row.finMechanism== null&&row.finAmount== null&&row.finTerm== null&&row.finNature== null){
             panDuan=0; 
             return;  
            }
            dataObj[index] = {finMechanism:row.finMechanism,finAmount:row.finAmount,finTerm:row.finTerm,finNature:row.finNature};
            index=index+1;
         });
         if(panDuan == 0){
	       alert("其他融资情况信息的填写不完善！");
	       return;
	     }
	     var check = check.getData();
	     var overAllEvaluationData =overAllEvaluation.getData();
	     var wholeEvaluationChoose = overAllEvaluationData.wholeEvaluationChoose1+","+overAllEvaluationData.wholeEvaluationChoose2+","+overAllEvaluationData.wholeEvaluationChoose3+","+overAllEvaluationData.wholeEvaluationChoose4;
         var inspect={wholeEvaluationChoose:wholeEvaluationChoose,wholeEvaluationExplain1:overAllEvaluationData.wholeEvaluationExplain1,wholeEvaluationExplain2:overAllEvaluationData.wholeEvaluationExplain2};
         var json=nui.encode({dataObj:dataObj,check:check,bizId:bizId,partyId:partyId,inspect:inspect});
         $.ajax({
			            url: "com.bos.aft.checkReport.saveLoanInspectOth.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	if(text.msg){
			                      alert(text.msg);
			                      return;
			            	}
						},
			            error: function () {
		                    nui.alert("保存失败！");
		                }
				});
	    panDuan = 0;
	    errorMsg = "";
	    for(var i=0;i<pageName.length;i++){
	      saveData(inputID[pageName[i]]);
	      if(panDuan==1){
	         alert(errorMsg);
	         return;
	      }
	    }
	    
	}
	
	function saveData(e){
	 var dataObj = e;
	 var num=dataObj.length;
		var dataObjects=[];
		for(var i=0;i<num;i++) {//遍历选项卡
			//var aliDataId=nui.get(dataObj[i]).getValue();
			var commentText = nui.get(dataObj[i]).getValue().data3;//现在的相关描述
			var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");//时间
			var context = nui.get(dataObj[i]).getValue();//获取对象值
			var titleIndexCd = context.id;//ACRS020100
			var newData1 = context.data1;//现在的选项
			var indexId = "";
			
			//校验  选项和描述不能同时为空
			if((newData1 == null || newData1 == '' || newData1 == undefined)
					&&(commentText == null || commentText == '' || commentText == undefined)){
					errorMsg = "第"+(i+1)+"个选项，选择和相关描述不能同时为空！";
				} 
			
			//校验多选，当选择“是”时，至少选择一项
			
			var result = document.getElementById(titleIndexCd).name;
			var checkList =  document.getElementsByName(result);
			for(var h=0;h<checkList.length;h++){
				if(checkList[h].checked){
					result = checkList[h].value;
					break;
				}
			}
			if(result==1){
			 if(newData1 =="")panDuan=1;
			 errorMsg ="多选为“是”时，至少选择一项！";
			}
			
			var temp = new Object();
			temp.comment=commentText;
			temp.updateTime=timestamp;
			temp.indexChoice=newData1;
			temp.indexId=titleIndexCd;
			temp.alcInfoId=bizId;
			//temp.aliDataId=aliDataId=='null'?null:aliDataId;
			dataObjects[i]=temp;
			
	     }	
	       if(result==1){
			  return;
			}
	       var json=nui.encode({dataObjects:dataObjects});
	       $.ajax({
			            url: "com.bos.aft.checkReport.saveTarge.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	initGrid();
			            	git.unmask();
			            	if(text.msg){
			                      alert(text.msg);
			            	}
						},
			            error: function () {
		                    nui.alert("保存失败！");
		                }
				});
	
	}          

</script>
</html>