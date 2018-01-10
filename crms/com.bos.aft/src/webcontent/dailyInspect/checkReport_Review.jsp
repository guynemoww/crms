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
 <div id="check">
	<fieldset style="margin-top: 10px;">
  	<legend>
    	     <span>检查方式</span>
    </legend>
	<div id="editform" class="nui-dynpanel" columns="4"  >
		<label>检查方式：</label>
		<input name="inspectWayCd" id="inspectWayCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  onvaluechanged="checkJudge" required="true" /> 
		<label>检查时间/约见时间：</label>
		<input name="inspectDate" id="inspectDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
		<div id="hiddenBox" colspan="4"></div>
		<label>接待人员姓名：</label>
		<input name="receptionName" id="receptionName" class="nui-textbox nui-form-input"/>
		<label>接待人员职务：</label>
		<input name="receptionPost" id="receptionPost" class="nui-textbox nui-form-input"/>
	</div>
	</fieldset>
    
    <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>检查频率</span>
             </legend>
	<div class="nui-dynpanel" columns="2">
		<label>当前检查频率：</label>
		<input name="setRate" id="InspectRate"  class="nui-text nui-form-input"  dictTypeId="XD_DHCD0001"/>
    </div>
    </fieldset>

	<fieldset style="margin-top: 10px;">
  	<legend>
    	     <span>抵质押物评估价值</span>
    </legend>
		<div colspan="3">
		    <div id="valuation" class="nui-dynpanel" columns="4">
		         <div style="text-align: center;">房屋抵押：</div>
		         <div>
		            <input name="value1" class="nui-textbox nui-form-input" style="width:80%;">万元</input>
		         </div>
		
		         <div style="text-align: center;">其他：</div>
		         <div>   
		            <input name="value2" class="nui-textbox nui-form-input" style="width:80%;">万元</input>
		         </div>
	        </div>
	    </div>
	</fieldset>
</div>
	
	<fieldset style="margin-top: 10px;">
  	<legend>
    	     <span>审批要求及贷后等意见落实情况</span>
    </legend>
	<div style="border-bottom:0;text-align:left;padding-top: 20px;padding-bottom:0px;" >
		<!--  <a  id="addBtn" class="nui-button" iconCls="icon-add" onclick="btnAdd">点击此处填写</a>-->
	</div>
	<div id="datagrid1" class="nui-datagrid" showPager="false" url="com.bos.aft.aft_inspect_report.getInspectCond.biz.ext" dataField="inspectConds" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
        <div property="columns">  
       		 <!--ComboBox：本地数据-->         
            <div type="dictcomboboxcolumn" autoShowPopup="true" name="gender" field="isImp" width="100" dictTypeId="XD_0002" align="center" headerAlign="center">是否落实
	             <input property="editor" class="nui-dictcombobox" style="width:100%;" dictTypeId="XD_0002" />                
	        </div> 
            <div field="approvalInfo" width="120" headerAlign="center">审批要求
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="notImpReason" width="120" headerAlign="center">未落实原因描述
                <input property="editor" class="nui-textarea" style="width:100%;" minHeight="50"/>
            </div>
            <div field="someMeasure" width="120" headerAlign="center">相关措施
                <input property="editor" class="nui-textarea" style="width:100%;" minHeight="50"/>
            </div>
        </div>
     </div>	
    </fieldset>
    
<fieldset style="margin-top: 10px;">
  	<legend>
    	     <span>资金流向情况</span>
    </legend>
	<div id="captial" class="nui-dynpanel" columns="2"  >
		<label>资金流向监控是否完成：</label>
		<input name="checkState" class="mini-dictradiogroup nui-form-input" enabled="false" dictTypeId="YesOrNo" />
		<label>实际与计划流向是否一致：</label>
		<input name="isActualUse" class="mini-dictradiogroup nui-form-input" enabled="false" dictTypeId="YesOrNo" />
		<label>相关交易资料收集是否完成：</label>
		<input name="isCollected" class="mini-dictradiogroup nui-form-input" enabled="false" dictTypeId="YesOrNo" />
		<label colspan="2">情况说明：</label>
		<input name="matter" class="nui-textarea nui-form-input" style="width:100%;" enabled="false" colspan="2"> 
    </div> 	    
	</fieldset>
<fieldset style="margin-top: 10px;">
  	<legend>
    	     <span>他行融资情况（万元）</span>
    </legend>
	<div class="nui-dynpanel" columns="6" textAlign="left">
	    <div style="text-align: left;width: 100%;">上年度末融资余额</div>
	    <input id="lastYearFinancingBalance" name="lastYearFinancingBalance" class="nui-textbox nui-form-input" style="width:100%;"> 
		<div style="text-align: left;width: 100%;">上期检查时融资余额</div>
		<input id="lastFinancingBalance"  name="lastFinancingBalance" class="nui-textbox nui-form-input" style="width:100%;"> 
		<div style="text-align: left;width: 100%;">本期检查时融资余额</div>
		<input id="currentFinancingBalance" name="currentFinancingBalance" class="nui-textbox nui-form-input" style="width:100%;"> 
    </div>     
</fieldset>	   
	

 	
 <div id="form">
	<div id="borrowBasicChoose" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left" 
	     colWidth="70%,30%" multiSelect="true"> </div>
    <label><span >借款人基本情况具体描述：（包括但不限于企业经营现状、企业在使用我行授信后对经营活动产生的影响、对外负债和或有负债变化情况及原因分析等）:</span></label>
	<div>
	     <input id="borrowBasicExplain2" name="borrowBasicExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	
	<div id="borrowFinanceChoose" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left" 
	     colWidth="70%,30%" multiSelect="true"> </div>
	<label><span >借款人财务状况具体描述：（包括但不限于本次检查周期发生的主要财务事件或异常情况、与申报授信或上年同期发生较大变化科目和数据的原因分析、财务状况整体分析判断等）:</span></label>
	<div>
	 <input id="borrowFinanceExplain2" name="borrowFinanceExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	
	<div id="warrant" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left" 
	     colWidth="70%,30%" multiSelect="true"> </div>
	<label><span >担保情况具体描述：（包括但不限于保证人经营及变化情况、抵质押物释放、增加或变更的情况、抵质押物市场估值与授信审批时的变化情况等）:</span></label>
	<div>
	 <input id="warrantExplain2" name="warrantExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>

	<div id="realEstateLoanChoose" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left" 
	     colWidth="70%,30%" multiSelect="true"> </div>
	<label><span >项目开发情况具体描述：（包括但不限于项目开发进展，按计划应达到的开发进度，实际开发进度与计划是否一致，不一致的具体原因及拟采取的相应措施等）:</span></label>
	<div>
	 <input name="realEstateLoanExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<label><span >销售情况具体描述：（包括但不限于销售成交量、成交价、按计划应达到的成交量、成交价，实际销售情况是否与计划一致，不一致的具体原因及拟采取的相应措施等）:</span></label>
	<div>
	 <input name="realEstateLoanExplain3" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>

	<div id="propertyLoanChoose" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left"  colWidth="70%,30%" multiSelect="true"> </div>
	<fieldset style="margin-top: 10px;">
  	    <legend>
    	     <span>租金收入情况（万元）</span>
        </legend>
	<div class="nui-dynpanel" columns="6" style="margin-top: 20px;" >    
	    <label>上期收入</label>
		<input id="lastRent" name="lastRent" class="nui-textbox nui-form-input"/>
		<label>本期收入</label>
		<input id="currentRent" name="currentRent" class="nui-textbox nui-form-input"/>
		<label>计划收入</label>
		<input id="planRent" name="planRent" class="nui-textbox nui-form-input"/>
	</div>
	</fieldset>	
	<label><span >出租情况具体描述：（包括但不限于整体出租情况、出租率、租金收入、计划出租率、计划租金收入、实际出租情况与计划是否一致，不一致的具体原因及拟采取的相应措施等:</span></label>
	<div>
	 <input id="propertyLoanExplain2" name="propertyLoanExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	
	<div id="governmentCreditChoose" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left" 
	     colWidth="70%,30%" multiSelect="true"> </div>
	<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>资金回笼情况（万元）</span>
    </legend>
	<div  class="nui-dynpanel" columns="6" style="margin-top: 20px;" >    
	    <label>上期回笼资金</label>
		<input id="lastReturnCapital" name="lastReturnCapital" class="nui-textbox nui-form-input"/>
		<label>本期回笼资金</label>
		<input id="currentReturnCapital" name="currentReturnCapital" class="nui-textbox nui-form-input"/>
		<label>计划回笼资金</label>
		<input id="planReturnCapital" name="planReturnCapital" class="nui-textbox nui-form-input"/>
	</div>
	</fieldset>	
	<label><span >项目进展情况具体描述：（包括但不限于项目具体进展，按计划应达到的进度，实际进度与计划是否一致，不一致的具体原因及拟采取的相应措施、政府财政收入变化对项目的影响、企业与政府的合作情况以及外部监管部门对借款企业或项目本身提出的监管意见等）:</span></label>
	<div>
	 <input id="governmentCreditExplain2" name="governmentCreditExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>

	<!--<div id="colligateIncomeChoose" class="nui-dynpanel2" style="margin-top: 10px;" width="100%" columnValueFields="data1,data2" dataField="children" colAlign="left,left" 
	     colWidth="70%,30%" multiSelect="true"> </div>
	 <fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	    <legend>
    	     <span>存款情况（万元）</span>
        </legend>
	<div  class="nui-dynpanel" columns="6" style="margin-top: 20px;" >    
	    <label>季日均存</label>
		<input id="seasonDateDeposit" name="seasonDateDeposit" class="nui-textbox nui-form-input"/>
		<label>近三个月存贷款情况</label>
		<input id="last3MonthDeposit" name="last3MonthDeposit" class="nui-textbox nui-form-input"/>
		<label>年累计中间收入</label>
		<input id="fullyearMiddleIncome" name="fullyearMiddleIncome" class="nui-textbox nui-form-input"/>
	</div>
	</fieldset>	
	<label><span >综合收益评价具体描述（授信到期前三个月内填写）：（包括但不限于本次授信业务综合收益回顾和交叉营销方案实施执行回顾等）:</span></label>
	<div>
	 <input id="colligateIncomeExplain2" name="colligateIncomeExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div> -->
</div>	

<div id="warn">
	<div  style="margin-top: 20px;">
		 <fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	        <legend>
    	        <span>预警情况</span>
            </legend>
		    <div  class="nui-dynpanel" columns="6"  >
		        <div style="text-align: center;">首次预警时间</div>
		        <input name="firstWarnTime" style="width:70%;" class="nui-text"/>
		        <div style="text-align: center;">首次预警级别</div>
		        <input name="firstWarnLevel" style="width:70%;" dictTypeId="XD_YJCD0004" class="nui-text"/>
		        <div style="text-align: center;">目前预警级别</div>
		        <input name="currentLevel"  style="width:70%;" dictTypeId="XD_YJCD0004" class="nui-text"/>
		    </div>
		    <div style="font-size: 15px;text-align: left;">预警原因:</div> 
		    <div style="border-bottom: 1px"> 
		        <div style="margin-top: 20px;text-align: left;">1.财务信号</div>
		        <div id="cwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="debtDatas" sizeList="[10,15,20,50,100]" 
	                 allowResize="true" showReloadButton="false" pageSize="10" >
                     <div property="columns">
                         <div feild="">信号名称</div> 
                     </div>
                </div>
		  
		    <div style="margin-top: 20px;text-align: left;">2.非财务信号</div>
		    <div id="fcwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="debtDatas" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                 <div property="indexcolum"></div>
                     <div feild="">信号名称</div> 
                 </div>
             </div>
		  
		    <div style="margin-top: 20px;text-align: left;">3.预案</div>
		     <div>
		       <input name="preservePlan" class="nui-textarea"/>
		       <input name="csmSignalId" class="nui-hidden"/>
		    </div>
		    <div style="margin-top: 20px;text-align: left;">4.预案执行情况</div>
		    <div>
		        <input name="executionPlan" class="nui-textarea" required="false"/>
		    </div>
		 </fieldset>    
	</div>
</div>
	
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>对借款人及担保人的整体评价（必填）</span>
    </legend>
 <div id="overAllEvaluation">
	    <div id="wholeEvaluationChoose" style="margin-top: 20px;"   class="nui-dynpanel" columns="4" style="margin-top: 20px;" >
		    <label>项目实施情况评价：</label>
		    <input id="wholeEvaluationChoose1" name="wholeEvaluationChoose1" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		    <label>借款人财务状况评价：</label>
		    <input id="wholeEvaluationChoose2" name="wholeEvaluationChoose2" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		    <label>借款人履约能力评价：</label>
		    <input id="wholeEvaluationChoose3" name="wholeEvaluationChoose3" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		    <label>担保人代偿能力评价：</label>
		    <input id="wholeEvaluationChoose4" name="wholeEvaluationChoose4" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
	    </div>
	
	    <div style="margin-top: 20px;">整体评价（包括但不限于项目实施情况、借款人财务状况、借款人履约能力和担保人代偿能力等）:</div>
	    <div>
	        <input name="wholeEvaluationExplain1" class="nui-textarea nui-form-input"  style="width:100%;"/>
	    </div>
	    <div style="margin-top: 20px;">上述评价勾选为“关注”或“恶化”的，除整体评价外需另行详细说明原因和相关措施:</div>
	    <div>
	        <input name="wholeEvaluationExplain2" class="nui-textarea nui-form-input"  style="width:100%;"/>
	    </div>
 </div>
</fieldset>

<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>责任管理体系</span>
    </legend>
 <div id="form" style="width:99.5%;height:auto;overflow:hidden;margin-top: 10px;">
		    <input id="postId" class="nui-hidden nui-form-input"  name="postId" value=<%=request.getParameter("bizId")%> />
			<div class="nui-dynpanel" columns="2">
			
			<label class="nui-form-label" id="scfBusiness">岗位责任人（评审审批） ：</label>
			<input id="postName3" name="postName3" style="width: 100%;" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectUser(postName3)" required="true" />
			
			</div>
		
</div>
</fieldset>	    

<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>报告评分</span>
    </legend>
    <div>
         <input id="socre" style="width: 60%;" showNullItem="true" nullItemText="--请选择--" class="nui-comboBox nui-form-input"  textField="text" valueField="id"
         emptyText="--请选择--" data="[{'id':'1','text':'一分'},{'id':'2','text':'二分'},{'id':'3','text':'三分'},{'id':'4','text':'四分'},{'id':'5','text':'五分'}]" />
    </div>
</fieldset>	    

	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	     <a class="nui-button" iconCls="icon-save" onclick="update()" id="btnSave">确定</a>
	</div>
  	
</body>
<script type="text/javascript">
                                                                                           //源地址页面aft/dailyInspect/inspectReport.jsp
    nui.parse();
     var check = new nui.Form("#check");                                                  //贷后检查部分
     var warn = new nui.Form("#warn");                                                    //预警部分
     var overAllEvaluation = new nui.Form("#overAllEvaluation");                          //整体评价部分
     var grid = nui.get("datagrid1");                                                     //审批要求及贷后等意见落实情况
     var form = new nui.Form("#form");                                                    //相关指标项部分
     var captial = new nui.Form("#captial");                                              //资金流向部分
     //check.setEnabled(false);
     //warn.setEnabled(false);
     //overAllEvaluation.setEnabled(false);
     //form.setEnabled(false);
     nui.get("btnSave").hide();
	 var indexResults;//当前页面选项卡集
	 var saveData = [];//保存当前选项数据，用于判断用户保存时应该发送哪些数据
     var name=["borrowBasicChoose","borrowFinanceChoose","warrant","realEstateLoanChoose","propertyLoanChoose","governmentCreditChoose"];//,"colligateIncomeChoose";
	 var name=["borrowBasicChoose","borrowFinanceChoose","warrant","realEstateLoanChoose","propertyLoanChoose","governmentCreditChoose"];//,"colligateIncomeChoose";
	 var inspect = <%=request.getParameter("inspect") %>.inspect;                         //预警报告数据
	 var partyId = inspect.partyId; 
	 var inspectRate = "<%=request.getParameter("inspectRate") %>";
    // alert(nui.encode(inspect));
	 //查询人员信息
	function selectUser(i) {
			nui.open({
            url: nui.context + "/aft/postMgr/pro_post_responsible_member.jsp?bizId="+partyId+"&responsiblePersonType=8",
            title: "选择人员信息",
            width: 1000,
            height: 600,
            ondestroy: function (action){
                getUserName(i);
        	}
        });     
	}
	
	function getUserName(i){
	 	 $.ajax({
	            url: "com.bos.aft.choosePost.getUserNames.biz.ext",
	            type: 'POST',
	            data: nui.encode({"bizId":partyId,"responsiblePersonType":"8"}),
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	 nui.get(i).setText(text.userNames);
	            	 nui.get(i).setValue(text.userNames);
	            }
	        });
	 
	 }
	
	
	
	//根据所选的检查方式显示不同的选项
	function checkJudge(){ 
	     var wayCd = nui.get("inspectWayCd").getValue();
	    //alert(wayCd);  RECEPTION_TYPE_CD 
	    var html1 = "<div columns='4' class='nui-dynpanel' id='inspectform'><label >检查地点</label> <input colspan='3' name='inspectPlace' class='nui-textbox nui-form-input' required='true'/></div>";
        var html2 = "<div columns='4' class='nui-dynpanel' id='inspectform'><label>人员类型</label> <input name='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010' colspan='2' required='true'/></div>";
        var html3 = "<div columns='4' class='nui-dynpanel' id='inspectform'><label >其他检查方式</label> <input colspan='3' name='otherInspectWay' class='nui-textbox nui-form-input' required='true' sytle='width:100%;'/></div>";
	    var html4 = "";
	    if(wayCd==1){
	      $("#hiddenBox").html(html1);
	       nui.parse($("#hiddenBox"));
	    }else if(wayCd==2){
	      $("#hiddenBox").html(html2);
	       nui.parse($("#hiddenBox"));
	    }else if(wayCd==3){
	      $("#hiddenBox").html(html3);
	       nui.parse($("#hiddenBox"));
	    }else{
	      $("#hiddenBox").html(html4);
	       nui.parse($("#hiddenBox"));
	    }
	   
	}
	
	function update(){
	    var score = nui.get("socre").value;
	    var json = nui.encode({irId:inspect.irId,score:score});
	    $.ajax({
				url: "com.bos.aft.common.updateInspectScore.biz.ext",
				type: 'POST',
				data: json,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					alert(text.msg);
				},
				error: function () {
			        
			    }
		});
	}
	  
    function init(){
       getUserName("postName3");
       var wholeEvaluationChoose =  inspect.wholeEvaluationChoose;                               //整体评价信息
       checkJudge();                                                                             //判断检查方式
       form.setData(inspect);                                                                    //加载原表单数据
       nui.get("inspectWayCd").setValue(inspect.inspectWayCd);
       //alert("wayCd:"+inspect.inspectWayCd);
       check.setData(inspect);                                                                   //加载检查报告基本信息
       var irId = inspect.irId;                                                                  //报告ID
       grid.load({bizId:irId});                                                                  //加载审批要求及贷后等意见落实情况
       overAllEvaluation.setData(inspect);                                                       //加载整体评价信息
      if(typeof(wholeEvaluationChoose) != "undefined" && wholeEvaluationChoose!="0000"){
         var reslt = wholeEvaluationChoose.split(",");                                //组装整体评价选择
         for(var i=0;i<reslt.length;i++){                                             //加载整体评价部分的信息
          var value = reslt[i];
          var idNum = "wholeEvaluationChoose"+(i+1);
          nui.get(idNum).setValue(value);
         } 
       } 
       nui.get("InspectRate").setValue(inspectRate);                                     //加载当前检查频率
       nui.get("lastYearFinancingBalance").setValue(inspect.lastYearFinancingBalance);   //上年他行融资余额
       nui.get("lastFinancingBalance").setValue(inspect.lastFinancingBalance);           //上期他行融资余额
       nui.get("currentFinancingBalance").setValue(inspect.currentFinancingBalance);     //本期他行融资余额                                                           //参与人ID
       var name=["governmentCreditChoose","propertyLoanChoose","realEstateLoanChoose","warrant","borrowFinanceChoose","borrowBasicChoose"];//,"colligateIncomeChoose";
	   var json=nui.encode({"pageName":"inspectReportPage","fileName":"targetConfig.xml",partyId:partyId});
	   $.ajax({
				url: "com.bos.aft.aft_inspect_report.getOptions.biz.ext",
				type: 'POST',
				data: json,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					var targetNums = text.targetNums;                              //指标父级选项码
					var lastIndicators = text.lastIndicators;                      //获取上期指标值
					captial.setData(text.capitalInfo);                             //资金流向数据
					for(var i=0;i<targetNums.length;i++){                          //取得相关指标
					   create(targetNums[i],name[i],lastIndicators);
					}
					setOtherInfo();		
						
				},
				error: function () {
			        
			    }
		});
	}
		init();

//加载其他模块数据
function setOtherInfo(){
       var json = nui.encode({partyId:inspect.partyId});
       $.ajax({
				url: "com.bos.aft.aft_inspect_report.getInspectOtherInfo.biz.ext",
				type: 'POST',
				data: json,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				    //alert(nui.encode(text.otherInfo));
					warn.setData(text.otherInfo);                                              //加载预警数据
					nui.get("fcwxh").load({partyId:inspect.partyId,parentId:"212002"});        //加载非财务类信号
					nui.get("cwxh").load({partyId:inspect.partyId,parentId:"212001"});         //加载财务类型号
				},
				error: function () {
			        
			    }
		});
}
		
	//显示选项卡
function create(e,n,l) {
       
        var pageName = e;
        var idName=n;                                                                                          //控件
        var lastIndicators =l;
		var pageJsonData=nui.encode({"indexCd":pageName,"lastIndicators":lastIndicators,"idName":idName,"inspect":inspect});
		
		var data1s ;
		var data2s ;
		$.ajax({
				url: "com.bos.aft.aft_inspect_report.getOptionCards.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				
					indexResults=text.optionCards;
					k = indexResults.length;
					//alert("data1s:"+nui.encode(text.data1s));
					data1s=text.data1s;
					data2s=text.data2s;
				},
				error: function () {
			        
			    }
		});
		    var temp = {};
		    var options = new Array();
		    var multiSelect = false;
		    
		    for(var i=0;i<indexResults.length;i++) {//构造选项卡
		    	if(i == 0) {//标题
		        	temp.id = indexResults[i].indexCd;
		            temp.text = i+"、"+indexResults[i].indexName;
		            saveData[indexResults[i].indexCd] = indexResults[i].indexCdId;//根据标题指标代码，取指标代码ID
		            
		           
		        } else {//选项
		        	options[i-1] = {id:indexResults[i].indexCd,text:indexResults[i].indexName};
		        }
		    }
		    
		   temp.children = options;
		    	
		   if(null == data1s || 
		     null == data1s.indexChoice || 
		     data1s.indexChoice == ''||
		     data1s == null) {//加载完页面，保存初始化选项的值（包括首次加载页面和之后的每次修改）
		   	    temp.data1 = '';//没有选项
		   } else if(data1s.indexChoice =='notHaveData1') {
		   		temp.data1 = '';//没有选项
		   		//initChoices2[0] = 'notHaveData1';
		   } else {
		   	  temp.data1 = data1s.indexChoice;//初始化的选项值
		   	  //temp.data1 = indexResults[j-1].data1s[0].indexChoice == 'notHaveData1' ? '' : indexResults[j-1].data1s[0].indexChoice;
		   }
		   //initChoices[0] = temp.data1;//初始化的选项值
		   
		    
		    //上期选择
		    if(data2s == null || data2s == null 
		    	|| data2s.comment == null ) {
		        temp.data2 = "";
		    } else {
		        temp.data2 = data2s.indexChoice;
		    }
		    
		    if(data1s == null 
		    	|| data1s.comment == null
		    	|| data1s == null
		    	
		    	) {
		    	temp.data3 = '';
		    	
		    } else if (data1s.comment == 'notHaveCommentText') {
		    	temp.data3 = '';
		    	//initCommentTexts2[j-1] = 'notHaveCommentText';
		    } else {
		    	temp.data3 = data1s.comment;
		    	//temp.data3 = indexResults[j-1].data1s[0].comment == 'notHaveCommentText' ? '' :indexResults[j-1].data1s[0].comment;
		    }
		    
		     nui.get(idName).setValue(temp);
		    //initCommentTexts[j-1] = temp.data3;//初始化相关描述
	}
	
	
    
    
</script>
</html>