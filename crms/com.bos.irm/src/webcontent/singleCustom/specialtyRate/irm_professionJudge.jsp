<%@page pageEncoding="UTF-8"%>


<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>专业贷款标识判断</title>

</head>
<body>

<div id="form">
	<div id="form1" style="width:100%;height:auto;overflow:hidden; margin-left:20px;margin-top:20px;" >
		 <!--<input type="hidden" name="tbProJudRec" class="nui-hidden" />-->
		 <!--  <input name="tbProJudRec." id="judgeRecordId" class="nui-hidden" />-->
		
		<label class="nui-form-label" style="font-weight:bold;">一、借款人实体</label> </br>
		<label class="nui-form-label" >借款人是否是专门为实物资产融资或运作实务资产而设立的项目公司。</label></br>
        <div id="isItemCompany" name="isItemCompany" class="nui-radiobuttonlist" textField="text" valueField="id" value="" url="shiti.txt" dataField="shiti">
        </div>
    </div>
    
    <div id="form2" style="width:100%;height:auto;overflow:hidden; margin-left:20px;margin-top:20px;" >   
		<label class="nui-form-label" >项目公司是：</label> </br>
       <div id="isRealEstateComp" name="isRealEstateComp" class="nui-radiobuttonlist" repeatItems="2" repeatDirection="vertical" repeatLayout="table"
        textField="text" valueField="id" value="" url="isReal.txt" dataField="isReal">
        </div>
    </div>
    
    <div id="form5" style="width:100%;height:auto;overflow:hidden; margin-left:20px;margin-top:20px;" >  
		<label class="nui-form-label" style="font-weight:bold;">二、贷款用途</label> </br>
         <div id="purposeCond" name="purposeCond" class="nui-radiobuttonlist" repeatItems="4" repeatDirection="vertical" repeatLayout="table"
        	  textField="text" valueField="id" value="" url="isNotBulidPurposeCond.txt" dataField="isNotBulidPurposeCond">
         </div>
      
		<label class="nui-form-label" style="font-weight:bold;">三、还款来源:还款主要来源依赖于（可多选）</label> </br>
		 
		 <div id="isNotBuli" name="repaymentSour" class="nui-checkboxlist" repeatItems="5" repeatDirection="vertical" repeatLayout="table"
         	 textField="text" valueField="id" value="" url="isNotBuliRrepaymentSour.txt" dataField="isNotBuliRrepaymentSour">
         </div>
	</div>
	
    <div id="form3" style="width:100%;height:auto;overflow:hidden; margin-left:20px;margin-top:20px;" >  
    
		<label class="nui-form-label" style="font-weight:bold;">二、贷款用途</label> </br>
		<div id="isBulidPurposeCond" name="purposeCond" class="nui-radiobuttonlist" repeatItems="5" repeatDirection="vertical" repeatLayout="table"
          textField="text" valueField="id" value="" url="isBulidPurposeCond.txt" dataField="isBulidPurposeCond">
           </div>
          
		<label class="nui-form-label" style="font-weight:bold;">三、还款来源:还款主要来源依赖于（可多选）</label> </br>
		 <div id="repaymentSour" name="repaymentSour"  class="nui-checkboxlist" repeatItems="5" repeatDirection="vertical" repeatLayout="table"
          textField="text" valueField="id" value="" url="isBuliRrepaymentSour.txt" dataField="isBuliRrepaymentSour">
           </div>
	</div>	
	 <div id="form4" style="width:100%;height:auto;overflow:hidden; margin-left:20px;margin-top:20px;" >  
		<label class="nui-form-label" style="font-weight:bold;">四、合同安排：选择符合条件的选项(多选)</label> </br>
		 <div id="contractArrangement" name="contractArrangement" class="nui-checkboxlist" repeatItems="5" repeatDirection="vertical" repeatLayout="table"
          textField="text" valueField="id" value="" url="contractArrangement.txt" dataField="contractArrangement" >
         </div>
	</div>
	<div id="form6" style="width:100%;height:auto;overflow:hidden; margin-left:20px;margin-top:20px;" >  
		<label class="nui-form-label" >判断结论:</label> 
		<div id="professionalTypeCd" name="professionalTypeCd" dictTypeId="XD_PJCD0003" valueField="dictID" textField="dictName" class="nui-dictcombobox"></div>
	</div>
	<div style="width:100%;height:auto;overflow:hidden;"></div>
		
	
	
	<div id="save" class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
    	<a class="nui-button" id="btnSaveRec"  onclick="saveProJudRec">保存</a>
	    
	</div>
</div>


<script type="text/javascript">
    nui.parse();
    //git.mask();
    var isItem = nui.get("isItemCompany");
	isItem.on("valuechanged", function (e) {
	    getIsItem(this.getValue());
	});
	
	var isReal = nui.get("isRealEstateComp");
	isReal.on("valuechanged", function (e) {
	    getIsReal(this.getValue());
	});
	
	var updateURL = "com.bos.irm.getProfessionalRateInfo.updateProJudRec.biz.ext";
	var saveURL = "com.bos.irm.getProfessionalRateInfo.saveProJudRec.biz.ext";
	var addURL = "";
	var status = "";
	var isPurpose = "";
   	var isRepay = "";
	document.getElementById("form3").style.display="block";//显示
	document.getElementById("form5").style.display="none";//隐藏
	
    var form2 = new nui.Form("#form2");
    var form3 = new nui.Form("#form3");
    var form5 = new nui.Form("#form5");
    var form4 = new nui.Form("#form4");
    var form6 = new nui.Form("#form6");
    var form = new nui.Form("#form");
    
    var partyId ="<%=request.getParameter("partyId") %>";		//客户ID
	var judgeRecordId ="<%=request.getParameter("judgeRecordId") %>";	//专业贷款ID
	var projectId ="<%=request.getParameter("projectId") %>";	//项目ID
	var irrApplyId ="<%=request.getParameter("applyId") %>";	//评级申请ID
	var applyBizId ="<%=request.getParameter("applyBizId") %>";	//业务ID
	var showtype ="<%=request.getParameter("showtype") %>";	
	var judgeState ;
	
	form6.setEnabled(false);
    
//初始化方法
function initForm(){
	//var json = nui.encode({"judgeRecordId":judgeRecordId,"irrApplyId":irrApplyId});
	var json = nui.encode({"projectId":projectId});
	
	   nui.ajax({
		url: "com.bos.irm.getProfessionalRateInfo.getProfessionalJudRec.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        cache: false,
        success: function (text) {
    		var o = nui.decode(text);
       		form.setData(o);
       		nui.get("professionalTypeCd").setValue(o.professionalJudgeRecord.professionalTypeCd);	//判断结论
       		judgeRecordId = o.professionalJudgeRecord.judgeRecordId;
       		var obj = nui.get("isItemCompany");
       		nui.get("isItemCompany").setValue(o.professionalJudgeRecord.isItemCompany);				//借款人实体
       		nui.get("isRealEstateComp").setValue(o.professionalJudgeRecord.isRealEstateComp);		//是否项目公司
       		var isRealEstateComp = o.professionalJudgeRecord.isRealEstateComp;
      		if(isRealEstateComp == 'A'){
	       		document.getElementById("form3").style.display="block";								//显示
				document.getElementById("form5").style.display="none";								//隐藏
       			nui.get("isBulidPurposeCond").setValue(o.professionalJudgeRecord.purposeCond);		//贷款用途
   				nui.get("repaymentSour").setValue(o.professionalJudgeRecord.repaymentSour);			//还款来源
       		}else{
       			document.getElementById("form5").style.display="block";								//显示
				document.getElementById("form3").style.display="none";								//隐藏
       			nui.get("purposeCond").setValue(o.professionalJudgeRecord.purposeCond);				//贷款用途
       			nui.get("isNotBuli").setValue(o.professionalJudgeRecord.repaymentSour);				//还款来源
       		}
       		//getIsReal();
       		nui.get("contractArrangement").setValue(o.professionalJudgeRecord.contractArrangement);	//合同安排
       		judgeRecordId = o.professionalJudgeRecord.judgeRecordId;
       		judgeState = o.professionalJudgeRecord.judgeState;
		
       		if(showtype == 2){
   				form6.setEnabled(false);
				form.setEnabled(false);
				$("#save").hide();
       		}
       		
        },
        error: function (jqXHR, textStatus, errorThrown) {
       		git.unmask();
            nui.alert(jqXHR.responseText);
        }
    });
}
	//if(judgeRecordId != "null" || irrApplyId != 'null'){
		initForm();
	//}

   function cancelTo(){
   		 CloseWindow(); 
   }

   function saveProJudRec(){
   		//首先判断是否所有选项都都已选择
   		var isItemCompany =  nui.get('isItemCompany').getValue();				//借款人实体
		var	isRealEstateComp = nui.get('isRealEstateComp').getValue();			//项目公司
		var	purposeCond = nui.get('purposeCond').getValue();					//贷款用途
		var	isNotBuli = nui.get('isNotBuli').getValue();						//还款来源
		var	isBulidPurposeCond = nui.get('isBulidPurposeCond').getValue();		//贷款用途
		var	repaymentSour = nui.get('repaymentSour').getValue();				//还款来源
		var	contractArrangement = nui.get('contractArrangement').getValue();	//合同安排
		if(isItemCompany){
			if(isItemCompany != '2'){
				if(isRealEstateComp){
					if(isItemCompany == '1' && isRealEstateComp =='A'){
						if(!isBulidPurposeCond){
							alert('isBulidPurposeCond' +isBulidPurposeCond);
							return	alert("贷款用途不能为空1");
						}
						
						if(!repaymentSour){
							return	alert("还款来源不能为空");
						}
						
						if(!contractArrangement){
							return	alert("合同安排不能为空");
						}
						
					}else if(isItemCompany == '1' && isRealEstateComp =='B'){
						if(!purposeCond){
						alert('purposeCond'+purposeCond);
							return	alert("贷款用途不能为空2");
						}
						
						if(!isNotBuli){
							return	alert("还款来源不能为空");
						}
						
						if(!contractArrangement){
							return	alert("合同安排不能为空");
						}
					}
				}else{
					return alert('项目公司不能为空');
				}
			}
		}else{
			return alert('借款人实体不能为空');
		}
   		//var isEnt = nui.decode(nui.get("isItemCompany").getValue());
   		var isEnt =nui.get("isItemCompany").getValue();
   		if( isEnt){
   			git.mask();
	   		var isRealEstateComp = nui.get("isRealEstateComp").getValue();								//是否项目公司
	   
	        var data = form.getData(); 																	//获取表单多个控件的数据
	        var data5 = form5.getData(); 																//获取表单多个控件的数据
	        var json = nui.encode({"tbProJudRec":data,"tbPro":data5,"judgeRecordId":judgeRecordId,"partyId":partyId,"projectId":projectId,"irrApplyId":irrApplyId,"applyBizId":applyBizId});   	//序列化成JSON
	        if ( status == "3"){
	        	return alert("该记录已生效，不能修改");
	    	}
	    	//alert(judgeRecordId.length);
	    	
	    	
	        //if(judgeRecordId.length > 10 ){
	        //	addURL = saveURL ;
	        //}else{
	        	addURL = saveURL ;
	        //}
	        //alert(addURL);
	        //git.unmask();
	    	//return;
	        nui.ajax({
	            url: addURL ,
	            type: "post",
	            contentType:'text/json',
	            data:json ,
	            success: function (text) {
	            	judgeRecordId = text.judgeRecordId;
	            	nui.get("professionalTypeCd").setValue(text.judgeType);	//判断结论
	            	initForm();
	                alert(text.msg);
	                cancelTo();
	                git.unmask();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        	}
	        });
   		}else{
   			alert("请选择是否实体");
   			return;
   		}
   }
	
   function getIsItem(e){
		if(e == "2"){
			document.getElementById("form2").style.display="none";//隐藏
			document.getElementById("form3").style.display="none";//隐藏
			document.getElementById("form4").style.display="none";//隐藏
			document.getElementById("form5").style.display="none";//隐藏
			form2.reset();
			resetForm();
		}
		if(e == "1"){
			document.getElementById("form2").style.display="block";//显示
			document.getElementById("form3").style.display="block";//显示
			document.getElementById("form4").style.display="block";//显示
			//document.getElementById("form5").style.display="block";//显示
			form2.reset();
			resetForm();
		}
	}
	
	function getIsReal(e){
		if(e =="A"){
			document.getElementById("form3").style.display="block";//显示
			document.getElementById("form5").style.display="none";//隐藏
			resetForm();
		}else{
			document.getElementById("form5").style.display="block";//显示
			document.getElementById("form3").style.display="none";//隐藏
			resetForm();
		}
	}
	
	/* form表单reset */
	function resetForm(){
		form3.reset();
		form4.reset();
		form5.reset();
		form6.reset();
	}
	//创建专业贷款评级流程
	function createFlow(){
    	git.mask();
		var json=nui.encode({"partyId":partyId,"projectId":projectId,"judgeRecordId":judgeRecordId});
		 nui.ajax({
            url: "com.bos.irm.getProfessionalRateInfo.createFlowProfessionRate.biz.ext", //创建专业贷款评级流程
            data:json,
            type:"POST",
            contentType:'text/json',
            success: function (text) {
				git.unmask();
             	//var node=text.node;//弹出审批页面
                //openSubmitView(node);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
        });	
	}
	

</script>
</body>
</html>
