<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-23 14:40:01
  - Description:
-->
<head>
<title>评级结果查看</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div style="width:100%;height:auto;margin-top:5px;" align="center" ><h3>评级报告</h3></div>
	<div id="form" style="width:100%;height:auto;overflow:hidden;">
		<input id="item" class="nui-hidden nui-form-input" name="item"/>
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">客户编号：</label>
			<input name="item.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">组织机构代码：</label>
			<input name="item.orgnNum" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">客户名称：</label>
			<input id ='item.partyName' name="item.partyName" required="false" enabled='false' class="nui-textarea nui-form-input" vtype="maxLength:100" />
	
			<!--  
			<label class="nui-form-label">营业执照编号：</label>
			<input name="item.licenseNum" required="false"class="nui-text nui-form-input"  vtype="maxLength:100" />
			-->
			
			<div id="aa">
					<label id="a1" class="nui-form-label" style="display: none;">客户类型：</label>
					<label id="b1" class="nui-form-label" style="display: none;">同业类型：</label>
				</div>
				
			<div id="bb">
				<input id="a2" name="item.corpCustomerTypeCd" required="false" valueField="dictID" dictTypeId="CDKH0001" class="nui-text nui-form-input"  vtype="maxLength:100" />	
				<input id="b2" name="item.financialInstitutionTypeCd" required="false" valueField="dictID" dictTypeId="XD_KHCD0211" class="nui-text nui-form-input"  vtype="maxLength:100" />
			</div>
			<!--
			<label class="nui-form-label">贷款卡号：</label>
			<input name="item.loanCardNum" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			-->
			<div id="cc">
			<label id = 'a4' class="nui-form-label">客户行业：</label>
			<label id = 'b4' class="nui-form-label">客户行业：</label>
			</div>
			<div id="dd">
				<input id = 'a3' name="item.industrialTypeCd" required="false" valueField="dictID" dictTypeId="CDKH0095" class="nui-text nui-form-input" vtype="maxLength:10" />
				<input id = 'b3' name="item.subjectTypeCd" required="false" valueField="dictID" dictTypeId="CDKH0023" class="nui-text nui-form-input" vtype="maxLength:10" />
			</div>
			<label class="nui-form-label">企业规模：</label>
			<input name="item.shbackEnterpriseSizeCd" required="false" valueField="dictID" dictTypeId="CDKH0025" class="nui-text nui-form-input" vtype="maxLength:30" />
	
			<!-- 
			<label class="nui-form-label">首贷户标识：</label>
			<input name="item.firstFlg" required="false" class="nui-text nui-form-input" vtype="maxLength:20" />
			 -->
			<label class="nui-form-label">注册资本(元)：</label>
			<input name="item.registerAssets" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">职工人数(人)：</label>
			<input name="item.employeesNumber" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			<!--  
			<label class="nui-form-label">评级模型名称：</label>
			<input name="item.modelCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">模型版本：</label>
			<input name="item.modelVer" required="false"  class="nui-text nui-form-input" vtype="maxLength:20"/>
			-->	
			<label class="nui-form-label">公司成立日期：</label>
			<input name="item.dateOfEstablishment" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">是否政府融资平台：</label>
			<input name="item.isGovernmentr" required="false" class="nui-text nui-form-input" dictTypeId = "CDGY0001"  vtype="maxLength:100" />
			
			<label class="nui-form-label">是否专业贷款：</label>
			<input name="item.isProfession" required="false" class="nui-text nui-form-input" dictTypeId = "CDGY0001"  vtype="maxLength:100" />
			
			<label class="nui-form-label">所属机构：</label>
			<input name="item.orgNum" required="false" class="nui-text nui-form-input" valueField="dictID" dictTypeId="org"   vtype="maxLength:100" />
			
			<label class="nui-form-label">评级类型：</label>
			<input id = "ratingType" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">本次评级模型：</label>
			<input name="item.modelCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		   	
		   	<label class="nui-form-label">模型版本：</label>
			<input name="item.modelVer" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		   	
			<label class="nui-form-label">本次评级财务报表报告期：</label>
			<input name="item.financeDeadline" required="false" class="nui-text nui-form-input"   vtype="maxLength:100" />
		
			<label class="nui-form-label">本次评级财务报表模板：</label>
			<input name="item.customerTypeCd" required="false" class="nui-text nui-form-input" valueField="dictID" dictTypeId="XD_ACCCD0002"   vtype="maxLength:100" />
		
			<label class="nui-form-label">本次系统评级结果(R2)：</label>
			<input id="GENERAL_ADJUST_RATING_CD" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">本次最终评级结果(R3)：</label>
			<input id="ratResult" name="ratResult" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">本次最终评级违约概率：</label>
			<input id="PdAvag" name="PdAvag" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">本次评级生效状态：</label>
			<input id="ratingState" required="false" class="nui-text nui-form-input" vtype="maxLength:100"/>
			
			<label class="nui-form-label">本次评级生效日期起：</label>
			<input id="effectiveStartDt" required="false"  format="YYYY-MM-DD"  class="nui-text nui-form-input" vtype="maxLength:100" />
		   	
		   	<label class="nui-form-label">本次评级有效期限止：</label>
			<input id="effectiveEndDt" required="false"  format="YYYY-MM-DD"  class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">上次最终评级结果(R3)：</label>
			<input name="item.creditRatingCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
			<label class="nui-form-label">上次评级有效期起：</label>
			<input name="item.effectiveStartDt" required="false" dateFormat="yyyy-MM-dd" class="nui-text nui-form-input"   vtype="maxLength:100" />
		
			<label class="nui-form-label">上次评级有效期止：</label>
			<input name="item.effectiveEndDt" required="false" dateFormat="yyyy-MM-dd" class="nui-text nui-form-input"   vtype="maxLength:100" />
		   	  	
		   	
		
	        
	        <label class="nui-form-label">非财务信息：</label>
	        <div align="left" colspan="3">  
		        <div id="datagrid2"  class="nui-datagrid"  allowCellEdit="true"  allowCellWrap="true"  showPager="false" style="width:93%;height:auto"
		        	allowCellSelect="true" idField="id" url="com.bos.irm.ratingReportInfo.queryReportNonFincInfo.biz.ext" dataField="nonFinInfoS" editNextOnEnterKey="true" multiSelect="true" >
		       		 <div property="columns" >
		       		 	<div type="indexcolumn" headerAlign="center" >序号</div>
		        		<!--<div field="propertyTypeCd" name="propertyTypeCd" headerAlign="center" align="center" width="10%" dictTypeId="XD_PJCD0019" >指标类别</div>-->
			        	<div field="indexName" headerAlign="center" align="center" width="10%" >指标</div>        
			            <div field="indexDesc" headerAlign="center"  width="10%"  >指标说明</div> 
						<div field="indexOption" name="indexOption" headerAlign="center"   autoEscape="false" width="55%"  >指标选项
						</div>            
			            <div field="remarks" headerAlign="center" width="15%">如有特殊事项，请各位填入备注 </div>
		        </div>
		    </div>
			</div>
					        
	   
	        
	        <label class="nui-form-label">通用评级调整项信息：</label>
	        <div colspan="3">
				<div id="datagrid3" class="nui-datagrid" showPager="false" idField="id" allowCellEdit="true" style="width:93%;height:auto"
		          	allowCellSelect="true"  editNextOnEnterKey="true" url="com.bos.irm.queryInfo.queryOptionInfo.biz.ext" dataField="adjustOptions"  allowCellWrap="true">
			        <div property="columns">
			        		<div type="indexcolumn" headerAlign="center" >序号</div>
			        		<div field="adjustOrder" name="adjustOptionId" visible = "false" width="8%" >调整编号</div>
			          		<div field="adjustTypeCd" name="adjustTypeCd" headerAlign="center" align="center" allowSort="true" width="10%" dictTypeId="XD_PJCD0014" >分类</div>
				        	<div field="adjustOptionDescription" headerAlign="center" allowSort="true" width="60%" >调整事项</div>
				        	<div field="checked" type="checkboxcolumn" trueValue="1" falseValue="2" headerAlign="center" width="8%">是否存在</div>
			                <div field="remarks" headerAlign="center" width="15%" allowSort="true">如有特殊事项，请各位填入备注</div>
			        </div>
			    </div>
	        </div>		        
	        
	        
	        <label class="nui-form-label">推翻历史：</label>
   			<div colspan="3">
   				<div id="datagrid1_1" class="nui-datagrid" showPager="false" url="com.bos.irm.queryInfo.queryOverRecordInfo.biz.ext" dataField="items" style="width:93%;height:auto">
			        <div property="columns" >
			        	<div type="indexcolumn" headerAlign="center" >序号</div>
			        	<div field="isOverthrow" headerAlign="center" allowSort="false" width="100" dictTypeId="XD_0002">是否推翻</div>
			        	<div field="userNum" headerAlign="center" allowSort="false" width="100" dictTypeId="user" >经办人</div>
			        	<div field="orgNum" headerAlign="center" allowSort="false" width="100" dictTypeId="org" >经办机构</div>      
			            <div field="afterGrade" headerAlign="center" allowSort="false" width="120">推翻后评级结果</div>
			            <div field="overthrowDt" headerAlign="center" allowSort="false" width="120">推翻日期</div>
			            <div field="overthrowReason" headerAlign="center" allowSort="false" width="250">推翻原因</div> 
			            <div field="avagPd" headerAlign="center" allowSort="false" width="100">违约概率（PD2）</div> 
			        </div>
			     </div>
			     <div id="datagrid1_2" class="nui-datagrid" showPager="false" url="com.bos.irm.queryInfo.queryOverRecordInfo.biz.ext" dataField="items" style="width:93%;height:auto">
			        <div property="columns" >
			        	<div type="indexcolumn" headerAlign="center" >序号</div>
			        	<div field="isOverthrow" headerAlign="center" allowSort="false" width="100" dictTypeId="XD_0002">是否推翻</div>
			        	<div field="userNum" headerAlign="center" allowSort="false" width="100" dictTypeId="user" >经办人</div>
			        	<div field="orgNum" headerAlign="center" allowSort="false" width="100" dictTypeId="org" >经办机构</div>      
			            <div field="afterGrade" headerAlign="center" allowSort="false" width="120">推翻后评级结果</div>
			            <div field="overthrowDt" headerAlign="center" allowSort="false" width="120">推翻日期</div>
			            <div field="overthrowReason" headerAlign="center" allowSort="false" width="250">推翻原因</div> 
			        </div>
			     </div>
   			</div>
	        	
	    </div>	        	
   		<br/>
   		<div class="" style="border-bottom:0;text-align:center;margin-top: 20px;">
			<a class="nui-button" iconCls="icon-close" onclick="closeWindow()">关闭</a>
		</div>
   	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid1 = nui.get("datagrid1");
	var datagrid1_1 = nui.get("datagrid1_1");
	var datagrid1_2 = nui.get("datagrid1_2");
	var grid2 = nui.get("datagrid2");
	var grid3 = nui.get("datagrid3");
	var grid4 = nui.get("datagrid4");
	var grid5 = nui.get("datagrid5");
	
	var isProject;//是否专业贷款（1：是，0：否）
	
	var partyId="<%=request.getParameter("partyId") %>";//参与人id
	var applyId1="<%=request.getParameter("applyId") %>";//从前一画面获取
	var applyId2= "<%=request.getParameter("bizId") %>";//从section中获取
	var oldApplyId="<%=request.getParameter("oldApplyId") %>";//评级申请id
	//var reAud;
	var recId="<%=request.getParameter("recId") %>";//引擎计算id
	var processInstId="<%=request.getParameter("processInstId") %>";//实例号
	var flowType1  = "<%=request.getParameter("flowType") %>";//　　01：新增流程  02：更新流程
	var flowType2  = "<%=request.getParameter("bizType") %>";//　　01：新增流程  02：更新流程
	var applyId;
	var flg;
	var flg2;
	var flg3 = "1";//是否查看
	var isFinance;
	var flowType;	
	init();//初始化
	function getIsProject(){
		var json = nui.encode({"applyId":applyId});
		nui.ajax({
	        url: "com.bos.irm.queryInfo.queryIsProject.biz.ext",
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
	                isProject=o.isProject;
	            }
	        }
	    });
	    if(isProject == "1"){
	    	$("#datagrid1_1").hide();
	    }else{
	    	$("#datagrid1_2").hide();
	    }
	}
	function init(){
		//评级新建与更新
		if(flowType1 != "null"){
			flowType = flowType1;
		}
		if(flowType2 != "null"){
			flowType = flowType2;
		}
		//业务id从前一画面获取或者从section中获取。
		if(applyId1 != "null"){
			applyId = applyId1;
		}
		if(applyId2 != "null"){
			applyId = applyId2;
		}
		getIsProject();	
		//if(bizId != "null"){
			//if (flowType == "03"){
				//reAud = "0";
			//}else{
			//	reAud = "0"; 
			//}
			//applyId = bizId;
			var json = nui.encode({"applyId":applyId});
			$.ajax({
				url: "com.bos.irm.queryInfo.queryRatingApplyInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	var text = nui.decode(text);
		        	//applyId = text.ratingApplyInfo.iraApplyId;
		        	partyId = text.ratingApplyInfo.partyId;
		        	oldApplyId = text.ratingApplyInfo.originalIraApplyId;
		        }
			});
		//}
		var json = nui.encode({"partyId":partyId,"applyId":applyId});
	    $.ajax({
	        url: "com.bos.irm.queryInfo.queryCustInfo.biz.ext",
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
	                form.setData(o);
	                isFinance=o.isFinance;
	            }
	        }
	    });
	    if(isFinance == "1"){
			document.getElementById("a1").style.display="none";
			document.getElementById("a2").style.display="none";
			document.getElementById("a3").style.display="none";
			document.getElementById("a4").style.display="none";
			document.getElementById("b1").style.display="block";
			document.getElementById("b2").style.display="block";
			document.getElementById("b3").style.display="block";
			document.getElementById("b4").style.display="block";
		}else{
			document.getElementById("a1").style.display="block";
			document.getElementById("a2").style.display="block";
			document.getElementById("a3").style.display="block";
			document.getElementById("a4").style.display="block";
			document.getElementById("b1").style.display="none";
			document.getElementById("b2").style.display="none";
			document.getElementById("b3").style.display="none";
			document.getElementById("b4").style.display="none";
		} 
	    
	    
	    //if (reAud == "1"){
	   		//var json1 = nui.encode({"item":{"applyId":oldApplyId,"partyId":partyId,"recId":recId}});
	   		//grid1.load({"item":{"applyId":oldApplyId,"partyId":partyId,"recId":recId}});
	   	//}else{
	   		var json1 = nui.encode({"item":{"applyId":applyId,"partyId":partyId,"recId":recId}});
	   		//url: "com.bos.irm.queryInfo.queryFinanceInfo.biz.ext",
	   		//grid1.load({"item":{"applyId":applyId,"partyId":partyId,"recId":recId}});
	   	//}
	   	
	    nui.ajax({
		        url: "com.bos.irm.queryInfo.queryFinanceInfoInRes.biz.ext",
		        type: 'POST',
		        data: json1,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		$("#datagrid1").hide();
		        		flg2 = 0;
		        	} else {
		        		var p = nui.decode(text);		                
		               	grid1.setData(p.financeRes);
		               	recId = p.outRecId;
		            }
		        }
		});
		
	    /*grid2.on("load", function () {
           		 grid2.mergeColumns(["propertyTypeCd"]);
      	});*/
      	/*if (reAud == "1"){
      		var temp = oldApplyId;
      	}else{
      		var temp = applyId;
      	}*/
      	grid2.load({"applyId":applyId});
      	//grid3.on("load", function () {
        //   	grid3.mergeColumns(["optionTypeCd"]);
      	//});
      	/*if (reAud == "1"){
      		var temp1 = oldApplyId;
      	}else{
      		var temp1 = applyId;
      	}*/
		grid3.load({"applyId":applyId});
		var json3=nui.encode({"partyId":partyId});
    	nui.ajax({
        	url: "com.bos.irm.queryInfo.queryGovernmentFinance.biz.ext",
        	type: 'POST',
		    data: json3,
		    cache: false,
		    async:false,
		    contentType:'text/json',
		    success: function (text) {
			    if(text.msg){
			    	alert(text.msg);
			    } else {	
			    	var o = nui.decode(text);
			        flg = o.flg;
			    }
			    },
			    error: function (jqXHR, textStatus, errorThrown) {
			        alert(jqXHR.responseText);
			    }
		});
   		if(flg == "1"){
   			document.getElementById("datagrid4").style.display="block";
   			/*grid4.on("load", function () {
       			grid4.mergeColumns(["propertyTypeCd"]);
  		 	});*/
  		 	/*if (reAud == "1"){
      			var temp2 = oldApplyId;
	      	}else{
	      		var temp2 = applyId;
	      	}*/
   			grid4.load({"item":{"applyId":applyId,"partyId":partyId}});
   		}
   		if(flg == "2"){
   			document.getElementById("datagrid4").style.display="none";
   			$('#governmentFinance1').hide();
   		}
   		var json4=nui.encode({"recId":recId,"partyId":partyId});
    	nui.ajax({
        	url: "com.bos.irm.queryInfo.queryResult.biz.ext",
        	type: 'POST',
		    data: json4,
		    cache: false,
		    async:false,
		    contentType:'text/json',
		    success: function (text) {
			    if(text.msg){
			    	alert(text.msg);
			    } else {	
			    	var o = nui.decode(text);
			        //nui.get("FINANCE_MODEL_SCORE").setValue(o.FINANCE_MODEL_SCORE);
			        //nui.get("NON_FINANCE_MODEL_SCORE").setValue(o.NON_FINANCE_MODEL_SCORE);
			        //nui.get("RATING_TATAL_SCORE").setValue(o.RATING_TATAL_SCORE);
			        //nui.get("INITIAL_RATING_CD").setValue(o.INITIAL_RATING_CD);
			        //nui.get("GOVERNMENT_ADJUST_RATING_CD").setValue(o.GOVERNMENT_ADJUST_RATING_CD);
			        nui.get("GENERAL_ADJUST_RATING_CD").setValue(o.GENERAL_ADJUST_RATING_CD);
			        nui.get("avgPd").setValue(o.avgPd);
			    }
			    },
			    error: function (jqXHR, textStatus, errorThrown) {
			        alert(jqXHR.responseText);
			    }
		});	
		//grid5.load({"applyId":applyId,"partyId":partyId,"processInstId":processInstId});
		var json5 = nui.encode({"applyId":applyId,"partyId":partyId,"processInstId":processInstId});
		nui.ajax({
        	url: "com.bos.irm.queryInfo.queryOverRecords.biz.ext",
        	type: 'POST',
		    data: json5,
		    cache: false,
		    async:false,
		    contentType:'text/json',
		    success: function (text) {
			    if(text.msg){
			      	$("#grid5").hide();
			    } else {	
			    	var o = nui.decode(text);
			        grid5.setData(o.items)
			    }
			    },
			    error: function (jqXHR, textStatus, errorThrown) {
			        alert(jqXHR.responseText);
			    }
		});	
		
		datagrid1_1.load({"applyId":applyId,"oldApplyId":oldApplyId});
		datagrid1_2.load({"applyId":applyId,"oldApplyId":oldApplyId});
		//指标说明链接
		indexDesc1();
		indexDesc2();
		
		
		
		//查找评级结果
		var json4=nui.encode({"applyId":applyId,"partyId":partyId});
    	nui.ajax({
        	url: "com.bos.irm.queryRatingResult.queryRatingResultInfo.biz.ext",
        	type: 'POST',
		    data: json4,
		    cache: false,
		    async:false,
		    contentType:'text/json',
		    success: function (text) {
		    	var o = nui.decode(text);
		        nui.get("ratResult").setValue(o.ratResult);
		        nui.get("PdAvag").setValue(o.PdAvag);
		        nui.get("ratingState").setValue(o.ratingState);
		        nui.get("effectiveStartDt").setValue(o.item.effectiveStartDt);
		        nui.get("effectiveEndDt").setValue(o.item.effectiveEndDt);
		        //nui.get("remark").setValue(o.GOVERNMENT_ADJUST_RATING_CD);
		        //nui.get("remarkUser").setValue(o.GENERAL_ADJUST_RATING_CD);
			    },
			    error: function (jqXHR, textStatus, errorThrown) {
			        alert(jqXHR.responseText);
			    }
		});
		if(flowType == '01'){
			nui.get("ratingType").setValue("首次评级");
		}else{
			nui.get("ratingType").setValue("存量评级");
		}  	
	}
	//指标说明链接
		function indexDesc1(){
			grid4.on("preload",function(e){
	       		if (!e.data || e.data.length < 1)
	       			return;
	       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	       			e.data[i]['indexDesc']='<a href="#" onclick="clickCust(\''
	       				+","+e.data[i].indexId
	       				+ '\');return false;" value="'
	       				+ '">'+e.data[i]['indexDesc']+'</a>';
	       		}
	       });
		}
	//指标说明链接
		function indexDesc2(){
			grid2.on("preload",function(e){
	       		if (!e.data || e.data.length < 1)
	       			return;
	       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	       			e.data[i]['indexDesc']='<a href="#" onclick="clickCust(\''
	       				+","+e.data[i].indexId
	       				+ '\');return false;" value="'
	       				+ '">'+e.data[i]['indexDesc']+'</a>';
	       		}
	       });
		}
		function clickCust(e){
			var ps = e.split(",");
			var indexId = ps[1];
			var json = nui.encode({"indexId":indexId});
			var infourl = nui.context + "/irm/singleCustom/creditRate/irm_index_desc_detail.jsp?applyId="
	            + applyId+"&indexId="+indexId;
	           
	       nui.open({
		            url:infourl,
		            title: "指标说明", width: 600, height: 450,
		            onload: function(e){
		            	//var iframe = this.getIFrameEl();
		            	//var text = iframe.contentWindow.document.body.innerText;
		            	//alert(text);
		            },
		            ondestroy: function (action) {
		               // query();
		            }
	      	  });	
		}
	
	
	function setComboxData(e){
		if(e.field!="indexOption"){
			return;
		}
		var json=nui.encode({"index":e.row.indexId});
		nui.ajax({
		    url: "com.bos.irm.queryInfo.queryIndexOption.biz.ext",
		    type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {	
	        		e.editor.load(text.items);
	        	}
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		        alert(jqXHR.responseText);
		    }
		});	
	}
	
	/* function getOverRecordInfo(){//获取推翻记录
		var json = nui.encode({"applyId":applyId});
	    nui.ajax({
	        url: "com.bos.irm.queryInfo.queryOverRecordInfo.biz.ext",
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
	                nui.get("afterGrade").setValue(o.items[0].afterGrade);
	            }
	        }
	    });		
	}*/
	
	//打印
	function btnPrint(){
	
	}
	
	
	function renderCell(e) {
	      var id='custom_' + e.rowIndex + '_' + e.columnIndex + '_' + e.field;
	      if (e.field =='indexOption') {
	       var row=e.row;//这里是当前行的数据
	      var arr=[];
	      if(row.indexId =='undefined'){
	      	return ;
	      }
	       
	       var json=nui.encode({"applyId":applyId,"index":row.indexId});
			nui.ajax({
		        url: "com.bos.irm.queryInfo.queryIndexOption.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        async:false,    
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		        		arr = nui.decode(text.items);
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
			});	
	       
	       var html='';
	       for (var i=0; i<arr.length; i++) {//单选框按钮组
		        var str = '<input name="'+id+'" type="radio" '+(arr[i].checked ? 'checked' : '')
		         +' onclick="checkName(this)" value="'+arr[i].value+'"></input>'+arr[i].indexOption;
		        if (i!=0)
		         str = '<br/>' + str;
		        html += str;
	       }
	       return html;
	      }
     }
    //关闭 
    function closeWindow(){
		CloseWindow();
	}
     
     function checkName(e) {
	      var id=e.id||e.name;
	      var ids=id.substr(7).split('_');//共有3个元素，依次：rowIndex columnIndex field
	      var v=e.value;//这里直接取文本框的值，对单选框（组）要特殊处理
	      //可在此进行校验
	      var row=grid2.getRow(parseInt(ids[0]));
	      var col=grid2.getColumn(parseInt(ids[1]));
	      if (ids[2]=='indexOption') {
	   //在此将值更新回去
	   var obj={};
	   obj[ids[2]]=v;
	   grid2.updateRow(row, obj);
   
	   var arr=document.getElementsByName(e.name);
	   var idx=0;
	   for (var i=0; i<arr.length; i++) {
	    if (arr[i].value==e.value) {
	     idx=i;
	     continue;
	    }
	    arr[i].checked=false;
	   }
       setTimeout(function(){
        	document.getElementsByName(e.name)[idx].checked=true;
       },100);
  	 	return;
      }
      if (ids[2]=='name') {
       if (e.value=='222') {//如果值错误
        grid2.setCellIsValid(row,col, false, '请输入正确的值');
        return;
       } else {
        grid2.setCellIsValid(row,col, true);
       }
   //在此将值更新回去
   var obj={};
   obj[ids[2]]=v;
   grid2.updateRow(row, obj);
   alert(nui.encode(grid.getRow(parseInt(ids[0]))));
   return;
      }
     }
    
	function renderCellGrid4(e) {
	      var id='custom_' + e.rowIndex + '_' + e.columnIndex + '_' + e.field;
	      if (e.field =='indexOption') {
	       var row=e.row;//这里是当前行的数据
	      var arr=[];
	      if(row.indexId =='undefined'){
	      	return ;
	      }
	       var json=nui.encode({"applyId":applyId,"index":row.indexId});
			nui.ajax({
		        url: "com.bos.irm.queryInfo.queryIndexOption.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        async:false,    
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		        		arr = nui.decode(text.items);
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
			});	
	       
	       //这里的arr是我用的测试数据，实际执行时应该根据e.row[e.field]获取到的js数组对象进行初始化
	       var html='';
	       for (var i=0; i<arr.length; i++) {//单选框按钮组
		        var str = '<input name="'+id+'" type="radio" '+(arr[i].checked ? 'checked' : '')
		         +' onclick="checkName(this)" value="'+arr[i].value+'"></input>'+arr[i].indexOption;
		        if (i!=0)
		         str = '<br/>' + str;
		        html += str;
	       }
	       return html;
	      }
     }
     function checkNameGrid4(e) {
	    var id=e.id||e.name;
	      var ids=id.substr(7).split('_');//共有3个元素，依次：rowIndex columnIndex field
	      var v=e.value;//这里直接取文本框的值，对单选框（组）要特殊处理
	      //可在此进行校验
	      var row=grid4.getRow(parseInt(ids[0]));
	      var col=grid4.getColumn(parseInt(ids[1]));
	      if (ids[2]=='indexOption') {
	   //在此将值更新回去
	   var obj={};
	   obj[ids[2]]=v;
	   grid4.updateRow(row, obj);
	   //console.log(nui.encode(grid.getRow(parseInt(ids[0]))));
   
	   var arr=document.getElementsByName(e.name);
	   var idx=0;
	   for (var i=0; i<arr.length; i++) {
	    if (arr[i].value==e.value) {
	     idx=i;
	     continue;
	    }
	    arr[i].checked=false;
	   }
       setTimeout(function(){
        	document.getElementsByName(e.name)[idx].checked=true;
       },100);
  	 	return;
      }
      if (ids[2]=='name') {
       if (e.value=='222') {//如果值错误
        grid4.setCellIsValid(row,col, false, '请输入正确的值');
        return;
       } else {
        grid4.setCellIsValid(row,col, true);
       }
   //在此将值更新回去
   var obj={};
   obj[ids[2]]=v;
   grid4.updateRow(row, obj);
   alert(nui.encode(grid4.getRow(parseInt(ids[0]))));
   return;
      }
     }	
</script>
</body>
</html>