<%@page pageEncoding="UTF-8"%>


<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>专业贷款标识判断</title>
<%@include file="/common/nui/common.jsp"%>
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
	
	<label class="nui-form-label" >判断结论:</label> 
	<div id="professionalTypeCd" name="professionalTypeCd" dictTypeId="XD_PJCD0003" valueField="dictID" textField="dictName" class="nui-dictcombobox"></div>
	
	<div style="width:100%;height:auto;overflow:hidden;"></div>
	
<script type="text/javascript">
    nui.parse();
    
    var isItem = nui.get("isItemCompany");
	isItem.on("valuechanged", function (e) {
	    getIsItem(this.getValue());
	});
	
	var isReal = nui.get("isRealEstateComp");
	isReal.on("valuechanged", function (e) {
	    getIsReal(this.getValue());
	});
	document.getElementById("form3").style.display="block";//显示
	document.getElementById("form5").style.display="none";//隐藏
	
    var form1 = new nui.Form("#form1");
    var form2 = new nui.Form("#form2");
    var form3 = new nui.Form("#form3");
    var form4 = new nui.Form("#form4");
    var form5 = new nui.Form("#form5");
    var form = new nui.Form("#form");
    
    var partyId ="<%=request.getParameter("partyId") %>";
	var judgeRecordId ="<%=request.getParameter("judgeRecordId") %>";
	var irrApplyId = "<%=request.getParameter("bizId")%>";
    
//初始化方法
function initForm(){
	form1.setEnabled(false);
	form2.setEnabled(false);
	form3.setEnabled(false);
	form4.setEnabled(false);
	form5.setEnabled(false);
	form.setEnabled(false);
	var json = nui.encode({"judgeRecordId":judgeRecordId,"irrApplyId":irrApplyId});
	
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
       		var isItemCompany = o.professionalJudgeRecord.isItemCompany;
       		var isRealEstateComp = o.professionalJudgeRecord.isRealEstateComp;
       		var purposeCond = o.professionalJudgeRecord.purposeCond;
       		var repaymentSour = o.professionalJudgeRecord.repaymentSour;
       		var contractArrangement = o.professionalJudgeRecord.contractArrangement;
       		
       		var obj = nui.get("isItemCompany");
       		nui.get("professionalTypeCd").setValue(o.professionalJudgeRecord.professionalTypeCd);	//判断结论
       		nui.get("isItemCompany").setValue(o.professionalJudgeRecord.isItemCompany);				//借款人实体
       		nui.get("isRealEstateComp").setValue(o.professionalJudgeRecord.isRealEstateComp);		//是否项目公司
       		if(isRealEstateComp == 1){
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
       		nui.get("contractArrangement").setValue(o.professionalJudgeRecord.contractArrangement);	//合同安排
       		
        },
        error: function (jqXHR, textStatus, errorThrown) {
       		git.unmask();
            nui.alert(jqXHR.responseText);
        }
    });
}
	initForm();

   function back(){
   		 if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();  
   }

   function commit(){
   		var isRealEstateComp = nui.decode(nui.get("isRealEstateComp").getValue());					//是否项目公司
   
        var data = form.getData(); 																	//获取表单多个控件的数据
        var data5 = form5.getData(); 																//获取表单多个控件的数据
        var json = nui.encode({"tbProJudRec":data,"tbPro":data5,"judgeRecordId":judgeRecordId});   	//序列化成JSON
        if ( status == "3"){
        	return alert("该记录已生效，不能修改");
    	}
    	
        if(judgeRecordId){
        	addURL = updateURL;
        }else {
        	addURL = saveURL;
        }
        nui.ajax({
            url: addURL ,
            type: "post",
            contentType:'text/json',
            data:json ,
            success: function (text) {
                alert(text.msg);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        	}
        });
   }
	
   function getIsItem(e){
		if(e == "否"){
			document.getElementById("form2").style.display="none";//隐藏
			document.getElementById("form3").style.display="none";//隐藏
			document.getElementById("form4").style.display="none";//隐藏
			document.getElementById("form5").style.display="none";//隐藏
		}
		if(e == "是"){
			document.getElementById("form2").style.display="block";//显示
			document.getElementById("form3").style.display="block";//显示
			document.getElementById("form4").style.display="block";//显示
			//document.getElementById("form5").style.display="block";//显示
		}
	}
	
	function getIsReal(e){
		if(e =="A"){
			document.getElementById("form3").style.display="block";//显示
			document.getElementById("form5").style.display="none";//隐藏
			form3.reset();
			form5.reset();
		}else{
			document.getElementById("form5").style.display="block";//显示
			document.getElementById("form3").style.display="none";//隐藏
			form3.reset();
			form5.reset();
		}
	}
	

</script>
</body>
</html>
