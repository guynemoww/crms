<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>小企业贷后检查报告</title>
</head>
<body style="margin-left:10px;">
 <div>
     <a class="nui-button" iconCls="icon-save" onclick="goEdit()" id="goUpdate">相关指标信息维护</a>
 </div>
 <div id="check">

	<fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>检查方式:</span>
             </legend>
	<div id="editform" class="nui-dynpanel" columns="4"  >
		<label>检查方式：</label>                                                                          
		<input name="inspectWayCd" id="inspectWayCd"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  onvaluechanged="judge" required="true" /> 
		<label>接待人员姓名：</label>
		<input name="receptionName" id="receptionName" class="nui-textbox nui-form-input"  required="true"/>
		<label>检查时间/约见时间：</label>
		<input name="inspectDate" id="inspectDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"  required="true" />
		<label>接待人员职务：</label>
		<input name="receptionPost" id="receptionPost" class="nui-textbox nui-form-input"  required="true"/>
		<label>约见地点：</label>
		<input name="inspectPlace" id="inspectPlace" class="nui-textbox nui-form-input"  required="true" />
		<label>检查频率：</label>
		<input class="nui-text nui-form-input" name="inspectRate" id="inspectRate" dictTypeId="XD_DHCD0001"/>
	</div>
	</fieldset>
</div> 

    <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>本期借款人的基本情况</span>
             </legend>
		    <div id="basicCorpInfo" class="nui-dynpanel" columns="4">
		      <label>客户编号：</label>
		      <input class="nui-text nui-form-input" name="partyNum" dictTypeId="XD_DHCD0001"/>
		      <label>客户名称：</label>
		      <input class="nui-text nui-form-input" name="partyName" dictTypeId="XD_DHCD0001"/>
	    	  <label>企业性质：</label>
		      <input class="nui-text nui-form-input" name="registrationType" id="registrationType" dictTypeId="CDKH0024"/>
		      <label>法定代表人：</label>
		      <input class="nui-text nui-form-input" name="ppname" id="ppname" dictTypeId="XD_DHCD0001"/>
		      <label>国标行业：</label>
		      <input class="nui-text nui-form-input" name="industrialTypeCd" id="industrialTypeCd" dictTypeId="CDKH0095" />
		      <label>主营业务：</label>
		      <input class="nui-text nui-form-input" name="operatingBusiness" id="operatingBusiness" dictTypeId="XD_DHCD0001"/>
		      <label>是否重点客户：</label>
		      <input class="nui-text nui-form-input" name="isImportant" id="inspectRate" dictTypeId="XD_0002"/>
	        </div>
	</fieldset>

    <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>本期借款人的一般额度授信情况</span>
             </legend>
		    <div id="creditInfo" class="nui-dynpanel" columns="4">
		      <!-- <label colspan="2">是否已做额度授信：</label>
		      <input colspan="2" class="nui-text nui-form-input" name="isCredit" id="isCredit" dictTypeId="XD_0002"/> -->
		      <label>额度编号：</label>
		      <input class="nui-text nui-form-input" name="limitNum" id="limitNum" dictTypeId="XD_DHCD0001"/>
	    	  <label>币种：</label>
		      <input class="nui-text nui-form-input" name="currencyCd" id="currencyCd" dictTypeId="CD000001"/>
		      <label>一般授信额度：</label>
		      <input class="nui-text nui-form-input" name="creditExposure" id="creditExposure" dictTypeId="XD_DHCD0001"/>
		      <div colspan="2"></div>
		      <!--
		      <label> 汇率： </label>
		      <input class="nui-text nui-form-input" name="midExchangeRate" id="midExchangeRate" dictTypeId="XD_DHCD0001"/>
		      -->
		      <label>起始日：</label>
		      <input class="nui-text nui-form-input" name="startDate" id="startDate" dictTypeId="XD_DHCD0001"/>
		      <label>到期日：</label>
		      <input class="nui-text nui-form-input" name="endDate" id="endDate" dictTypeId="XD_DHCD0001"/>
		      <label>已用额度：</label>
		      <input class="nui-text nui-form-input" name="occupiedExposure" id="occupiedExposure" dictTypeId="XD_DHCD0001"/>
		      <label>可用额度：</label>
		      <input class="nui-text nui-form-input" name="occupiedExposure" id="occupiedExposure" dictTypeId="XD_DHCD0001"/>
	        </div>
	 </fieldset>
	 
	  <fieldset>
  	         <legend>
    	             <span>单笔单批授信情况：</span>
             </legend>
	         <div id="datagrid3" class="nui-datagrid" url="com.bos.aft.checkReport.querySignalLimit.biz.ext" 
	              dataField="signalLimits" sizeList="[10,15,20,50,100]" allowResize="true" showReloadButton="false" pageSize="10">
                 <div property="columns">  
       		     <!--ComboBox：本地数据-->  
       		     <div type="checkcolumn"></div>
                 <div field="limitNum" width="120" headerAlign="center" >额度编号</div>
                 <div field="productType" width="120" headerAlign="center" dictTypeId="product">授信品种</div>
                 <div field="currencyCd" width="120" headerAlign="center" dictTypeId="CD000001" >币种</div>
                 <div field="creditAmt" width="120" headerAlign="center" required="true">授信金额</div>
                 <div field="availableAmt" width="120" headerAlign="center" allowSort="true" required="true">占用额度</div>
                 <div field="occupiedAmt" width="120" headerAlign="center" required="true">可用额度</div>
                 <div field="startDate" width="120" headerAlign="center" dateFormat="yyyy-MM-dd">起始日期</div>
                 <div field="endDate" width="120" headerAlign="center" dateFormat="yyyy-MM-dd">到期日期</div>
                 </div>
             </div>	
     </fieldset>
	 <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>借款人财务状况</span>
             </legend>
	        <!-- 显示选项卡 -->
	        <div class="mini-dynpanel2Td" id="financeConditionPage">
	        </div>
	 </fieldset>
	 <!-- <fieldset>
  	         <legend>
    	             <span>借款人财务状况-财务报表分析结果：</span>
             </legend>
	         <div id="borrowFanceInfoGrid" class="nui-datagrid" url="com.bos.aft.checkReport.getOppions.biz.ext" dataField="codes" sizeList="[10,15,20,50,100]" 
	              allowResize="false" multiSelect="true" showPager="false" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
                 <div property="columns">  
       		     --><!--ComboBox：本地数据        
                 <div field="indexName"  headerAlign="center">财务报表分析结果
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
                 </div>
                 --><!-- 
                 <div field="indexValue"  headerAlign="center">具体分值
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
                 </div>
                  
                 <div field="indexChoice" type="dictcomboboxcolumn" autoShowPopup="true" name="gender"  dictTypeId="XD_0002" align="center" headerAlign="center">检查结果
	                 <input property="editor" class="nui-dictcombobox" style="width:100%;" dictTypeId="XD_0002" />                
	            </div> 
                </div>
             </div>	
     </fieldset> -->
	 
	 <fieldset>
  	         <legend>
    	             <span>借款人本期在本行外的其他融资情况：</span>
             </legend>
             <a id="delButton" class="nui-button" style="float: right;" iconCls="icon-remove" onclick="remove">删除一行</a>
	         <a id="addButton" class="nui-button" style="float: right;" iconCls="icon-add" onclick="btnAdd">点击此处填写</a>

	         <div id="datagrid" class="nui-datagrid" showPager="false" url="com.bos.aft.checkReport.queryLoanInspectOth.biz.ext" idField="id"
	             oncellendedit="checkFloat(e)" dataField="others" allowCellEdit="true" allowCellSelect="true" multiSelect="false" editNextOnEnterKey="true" >
                 <div property="columns">  
       		     <!--ComboBox：本地数据-->  
       		     <div type="checkcolumn"></div>
                 <div field="finMechanism" width="120" headerAlign="center" required="true">融资机构
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50" required="true"/>
                 </div>
                 <div field="finAmount" width="120" headerAlign="center" allowSort="true" required="true">融资金额
                     <input property="editor" dataType="float" class="nui-textbox" required="true"/>
                 </div>
                 <div field="finTerm" width="120" headerAlign="center" required="true">期限（月）
                     <input property="editor" class="nui-spinner" minValue="0" style="width:100%;" vtype="int" minHeight="50" required="true"/>
                 </div>
                 <div field="finNature" width="120" headerAlign="center" required="true">融资性质
	                 <input property="editor" class="nui-textbox" style="width:100%;" dictTypeId="XD_0002"  required="true"/>                
	             </div> 
                 </div>
             </div>	
     </fieldset>
     
     <fieldset>
  	         <legend>
    	             <span>借款人对外担保情况：</span>
             </legend>
	         <div id="datagrid1" class="nui-dynpanel" columns="6"  >
		     <label>上年度对外担保金额：</label>                                                                          
		     <input name="inspectWayCd" id="lastYearAmt" class="nui-text nui-form-input"  /> 
		     <label>上期检查时对外担保金额：</label>
		     <input name="inspectDate" id="inspectDate" class="nui-text nui-form-input"  />
		     <label>本次检查时对外担保金额：</label>
		     <input name="currentCheckWarrantAmt" id="receptionName" class="nui-textbox nui-form-input" required="true"/>
		     <label>详细情况：</label>
		     <input name="detailInfo" id="detailInfo" class="nui-textarea nui-form-input" colspan="5" required="true"/>
	         </div>
     </fieldset>

<div id="warn">
	<div  style="margin-top: 20px;">	 
	  <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>预警信息</span>
             </legend>
	         <div  class="nui-dynpanel" columns="8"  >
		        <div style="text-align: center;">首次级别认定时间</div>
		        <input name="firstWarnTime" style="width:70%;" class="nui-text"/>
		        <div style="text-align: center;">首次预警级别</div>
		        <input name="firstWarnLevel" style="width:70%;" dictTypeId="XD_YJCD0004" class="nui-text"/>
		        <div style="text-align: center;">目前级别认定时间</div>
		        <input name="currenttWarnTime" style="width:70%;" class="nui-text"/>
		        <div style="text-align: center;">目前预警级别</div>
		        <input name="currentLevel"  style="width:70%;" dictTypeId="XD_YJCD0004" class="nui-text"/>
		    </div>
		   
		   <div style="border-bottom: 1px"> 
		     <div style="margin-top: 20px;text-align: left;">1.当前预警信号</div>
		     <div id="cwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="signalInfos" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                     <div type="indexcolumn" ></div>
                     <div field="dictName">信号名称</div> 
                     <div field="parentType">信号类别</div> 
                     <div field="signalState">信号描述</div> 
                 </div>
             </div>
		  
		    <!--  <div style="margin-top: 20px;text-align: left;">2.其他预警信号</div>
		     <div id="fcwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="signalInfos" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                     <div type="indexcolumn" ></div>
                     <div field="dictName">信号名称</div> 
                 </div>
             </div>
		     -->
		  	 <!-- <div style="margin-top: 20px;text-align: left;">3.本期预警信号</div>
		     <div>
		       <input name="earnPlan" id="earnPlan" class="nui-textarea" style="width:100%;" enabled="false" required="false"/>
		     </div> -->
		     <div style="margin-top: 20px;text-align: left;">2.拟采取的控制措施和建议</div>
		     <div>
		       <input name="suggestState" class="nui-textarea" style="width:100%;" enabled="false"/>
		     </div>
		     <div style="margin-top: 20px;text-align: left;">3.预警事项描述</div>
		     <div>
		       <input name="matterState" class="nui-textarea" style="width:100%;" enabled="false"/>
		     </div>
		     <div style="margin-top: 20px;text-align: left;">4.预案执行情况</div>
		     <div>
		        <input name="planImplement" id="planImplement" class="nui-textarea" style="width:100%;" enabled="true" required="false"/>
		         <input name="csmSignalId" id="csmSignalId" class="nui-hidden"/>
		     </div>
	 </fieldset>
	</div>
</div>
	
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>债项信息</span>
    </legend>
    <a class="nui-button" iconCls="icon-save" onclick="edit()">查看债项信息</a>
	<div id="contractInfo" class="nui-datagrid" url="com.bos.aft.checkReport.queryReportContractInfo.biz.ext" dataField="contracts" 
	      sizeList="[10,15,20,50,100]" allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                 <div type="checkcolumn" >选择</div>
                     <div field="contractManualNum" >合同序号</div>
                     <div field="contractNum">合同编号</div> 
                     <div field="productType" dictTypeId="product">授信品种</div> 
                     <div field="currencyCd" dictTypeId="CD000001">合同币种</div> 
                     <div field="ifConvenientLoan" dictTypeId="">是否便捷贷</div> 
                     <div field="expirationDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日</div> 
                     <div field="mainSuretyMode" dictTypeId="CDZC0005">担保方式</div> 
                     <!-- <div field="contractbalance">保证人</div>  --> 
                     <div field="loanBalance">借据余额</div>  
                     <div field="overduebalance">逾期(垫款)本金</div> 
                     <div field="loanFiveLevelClassiFication" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001">资产分类</div>
                     <!-- <div feild="whetherGovernmentFinanceCor">是否政府融资平台</div> -->
                 </div>
    </div>
</fieldset>


 <fieldset>
  	         <legend>
    	             <span>审批意见落实情况：</span>
             </legend>
              <a id="delButton1" class="nui-button" style="float: right;" iconCls="icon-remove" onclick="remove1">删除一行</a>
	          <a id="addButton1" class="nui-button" style="float: right;" iconCls="icon-add" onclick="btnAdd1">点击此处填写</a>

	         <div id="datagrid2" class="nui-datagrid" showPager="false" url="com.bos.aft.checkReport.queryLoanInspectOth.biz.ext" idField="id"
	             dataField="others1" allowCellEdit="true" allowCellSelect="true" multiSelect="false" editNextOnEnterKey="true" >
                 <div property="columns">  
                 <div type="checkcolumn"></div> 
       		     <!--ComboBox：本地数据-->     
                 <div field="approvalInfo" width="120" headerAlign="center" required="true">审批意见书中授信后管理要求
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50" required="true"/>
                 </div>
                 <div field="isImp" width="120" type="dictcomboboxcolumn"  dictTypeId="XD_0002" align="center" >是否落实
	                 <input property="editor" class="nui-dictcombobox" dictTypeId="XD_0002" />                
	            </div> 
                 <div field="notImpReason" width="120" headerAlign="center" required="true">原因说明（未落实需填写）
                     <input property="editor" class="nui-textarea" style="width:100%;" minHeight="50" required="true"/>
                 </div>
                 </div>
             </div>	
     </fieldset>
 <fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>其他重要事项(长度不超过1000个汉字)</span>
    </legend>
        <div>
		        <input name="otherBusiness" id="otherBusiness" class="nui-textarea" style="width:100%;" enabled="true" required="false"/>
		  </div>
  </fieldset>  
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>检查意见及审批意见</span>
    </legend>
 <div id="overAllEvaluation">
	    <div style="margin-top: 20px;"   class="nui-dynpanel" columns="4" style="margin-top: 20px;" >
		    <label>履约能力综合评价：</label>
		    <input id="comprehensiveAbilityChoose" name="comprehensiveAbilityChoose" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_DHCD0004"/>
		    <div colspan="2"></div>
		    <label >检查结论：</label>
		    <input id="checkResult" name="checkResult" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0005" onvaluechanged="checkChoose()"/>
		    <div id="isNewExit" class="nui-dynpanel" colspan="2"  columns="2" style="text-align: center;">
		             <label>是否为本期新增“退出”客户</label>
		             <input id="isExit" required="true" name="isNewExit" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
		    </div>
		    <label>是否涉及分类调整：</label>
		    <input id="isAdjustment" name="isAdjustment" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_0002" onvaluechanged="checkChoose()"/>
		    <div id="adjustReason" class="nui-dynpanel" colspan="2"  columns="2" style="text-align: center;">
		             <label>调整建议及理由：</label>
		             <input name="adjustReason" class="nui-textbox nui-form-input" />
		    </div>
		    <div id="RiskControlMeasures" class="nui-dynpanel" colspan="4"  columns="2" >
		             <label>控制措施（针对检查结论为“退出类”的客户）：</label>
		             <input name="riskControlMeasures" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0006"/>
		    </div>
		    
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
<div id="reportSocre">
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>报告评分</span>
    </legend>
      <div>
	         <input id="reportSocre1" style="width: 60%;"  nullItemText="--请选择--" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0016" />
	    </div>
</fieldset>
</div>    
<div id="saveScore" class="nui-toolbar" style="border-bottom:0;text-align:right;">
	     <a class="nui-button" iconCls="icon-save" onclick="saveScore()" id="btnSave">保存报告评分</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:right;">
	     <a class="nui-button" iconCls="icon-save" onclick="submitData()" id="btnSave">确定</a>
</div>


  	<!-- <a class="nui-button" iconCls="icon-save" onclick="downLoad()" id="btnSave">下载</a> -->
</body>
<script type="text/javascript">
                                                                                           //源地址页面aft/dailyInspect/inspectReport.jsp
    nui.parse();
    /*特殊指标代码值：
      1. AFTL020100  客户经营是否处于停止或基本停止状态。
      2. AFTL090100  借款人财务状况-财务报表分析结果。
    */
    //var borrowFanceInfoGrid = nui.get("borrowFanceInfoGrid");                        //借款人财务信息
    var basicCorpInfo = new nui.Form("#basicCorpInfo");                              //客户基本信息
    var creditInfo = new nui.Form("#creditInfo");                                    //授信信息
    var check = new nui.Form("#check");                                              //检查方式
    var overAllEvaluation = new nui.Form("#overAllEvaluation");                      //整体评价部分
    var warn = new nui.Form("#warn");                                                //预警部分
    var overAllEvaluation = new nui.Form("#overAllEvaluation");                      //检查意见及审批意见
    var contractInfo = nui.get("contractInfo");                                      //合同信息部分
    var lastAlcInfoId;                                                               //上期贷后检查ID
    var alcInfoId;                                                                   //本期贷后检查ID
    var grid = nui.get("datagrid");                                                  //借款人其他融资情况
    var grid1 =new nui.Form("#datagrid1");                                           //借款人对外担保情况
    var grid2 =nui.get("datagrid2");                                                 //审批条件落实情况
    var grid3 =nui.get("datagrid3");
    var partyId = "<%=request.getParameter("partyId") %>";                           //;"borrowMgrInfo","borrowFinanceInfo",
    var bizId = "<%=request.getParameter("bizId") %>";
    var errorMsg;                                                                    //校验提示框
    var panDuan; 
    var node =<%=request.getParameter("node") %>;                                    //获取流程岗位信息
    nui.get("adjustReason").hide();
    nui.get("isNewExit").hide();
    nui.get("RiskControlMeasures").hide();
    var onlyRead="<%=request.getParameter("onlyRead") %>";                                //判断是否是审批界面
    var score = "<%=request.getParameter("score") %>";                                    //判断是否需要评分
    var inputID = [];                                 //备份指标项
    if(score==1){
        nui.get("saveScore").show();                                                      //显示评分按钮
        //nui.get("reportSocre").show();                                                    //显示评分
    }else{
        nui.get("saveScore").hide();                                                      //隐藏评分按钮
        $("#reportSocre").hide();                                                    //隐藏评分
    }
    
    function goEdit(){
        var param=nui.encode({"bizId":alcInfoId,"partyId":partyId,"irId":bizId});                                        //,"node":node 暂时不用的参数 注掉
        var url=nui.context+"/aft/dailyInspectAppr/daily_edit_tree_go.jsp?param="+param+"&goEdit=1";//
        nui.open({
	            url:url,
	            showMaxButton: true,
	            title: "贷后检查报告查询",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	        });
    }
    
    function checkFloat(e){                                                     //只能输入金额
        var field = e.column.field;                                              //获取当前单元格的文本对象
        if(field=="finAmount"){
           var object = e.sender;                                                   //获取表格对象
	       var row = object.getRow(e.rowIndex);                                     //取行对象
           var reg = /\d+.\d+/;
           var reg1 = /\d+/;
           var re = row.finAmount.match(reg);
           var re1 = row.finAmount.match(reg1);
           if(!re && !re1){
            row.finAmount=0;
            object.updateRow(row);
           }
        }
    }
    function checkChoose(){
       var isAdjustment = nui.get("isAdjustment").value;
       var checkResult = nui.get("checkResult").value;
       if(checkResult==3){
          nui.get("isNewExit").show();
          nui.get("RiskControlMeasures").show();
       }else{
          nui.get("isNewExit").hide();
          nui.get("RiskControlMeasures").hide();
       }     
       if(isAdjustment==1){
          nui.get("adjustReason").show();
       }else{
          nui.get("adjustReason").hide();
       }
    }
    
    function saveScore(){
	    var score = nui.get("reportSocre1").value;
	    var json = nui.encode({irId:bizId,score:score});
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
    
	function submitData(){
		check.validate();
        if (check.isValid() == false){
        	alert("请把必输项填写完整！");
        	return;
        } 
        overAllEvaluation.validate();
        if (overAllEvaluation.isValid() == false){
        	alert("请完善检查意见及审批意见！");
        	return;
        } 
        
        var otherBusiness=nui.get("otherBusiness").getValue();
        if(1000<otherBusiness.length){
            nui.alert("其他重大事项超过长度！");
            return;
        }
      
	    var inspect={};                               //贷后检查报告数据载体
	    //grid.validate();
	    panDuan = 1;
	    //alert("grid"+grid.isValid());
	       
	    var dataObj=[];                               //其他融资情况数据载体
	    var dataObj1 = grid1.getData();               //获取对外担保数据
	    var dataObj2=[];                               //审批条件落实情况
	    var index=0;
	    var index1=0;
	    var row = grid.findRow(function(row){
            if(row.finMechanism== null && row.finAmount== null && row.finTerm== null && row.finNature== null && $.trim(row.finMechanism)=="" && $.trim(row.finTerm)==""){
             panDuan=0; 
             return;  
            }
            dataObj[index] = {liId:bizId,finMechanism:row.finMechanism,finAmount:row.finAmount,finTerm:row.finTerm,finNature:row.finNature};
            index=index+1;
         });
         
         var row1 = grid2.findRow(function(row){    
            if(row.approvalInfo== null || row.isImp == null || row.notImpReason== null || $.trim(row.approvalInfo)=="" || $.trim(row.notImpReason)== ""){
             panDuan=3; 
             return;  
            }
            dataObj2[index1] = {liId:bizId,approvalInfo:row.approvalInfo,isImp:row.isImp,notImpReason:row.notImpReason};
            index1=index1+1;
         });
         if(panDuan == 0){
	       alert("其他融资情况信息的填写不完善！");
	       return;
	     }    
	     if(panDuan == 3){
	       alert("审批条件落实情况填写不完善！");
	       return;
	     } 
	     var overAllEvaluationData =overAllEvaluation.getData();
         var checkData = nui.decode(check.getData());                                               //获取检查基本数据
         inspect["inspectWayCd"] = checkData.inspectWayCd;                                          //贷后检查方式
         inspect["inspectDate"] = checkData.inspectDate;                                            //检查日期
         inspect["receptionName"] = checkData.receptionName;                                        //接待人员姓名
         inspect["receptionPost"] = checkData.receptionPost;                                        //接待人员职务
         inspect["inspectRate"] = checkData.inspectRate;                                            //检查频率
         var wayCd = nui.get("inspectWayCd").getValue();
         if(wayCd==1){
            inspect["inspectPlace"] = checkData.inspectPlace;                                       //检查地点
         }else if(wayCd==2){
            inspect["receptionTypeCd"] = checkData.receptionTypeCd;                                 //人员类型
         }else if(wayCd==3){
            inspect["otherInspectWay"] = checkData.otherInspectWay;                                 //其他检查方式
         }
         
         inspect["currentCheckWarrantAmt"] = dataObj1.currentCheckWarrantAmt;
         inspect["detailInfo"] = dataObj1.detailInfo;
         inspect["comprehensiveAbilityChoose"] = overAllEvaluationData.comprehensiveAbilityChoose;
         inspect["checkResult"] = overAllEvaluationData.checkResult;
         inspect["isNewExit"] = overAllEvaluationData.isNewExit;
         inspect["isAdjustment"] = overAllEvaluationData.isAdjustment;
         inspect["adjustReason"] = overAllEvaluationData.adjustReason;
         inspect["riskControlMeasures"] = overAllEvaluationData.riskControlMeasures;
         inspect["otherBusiness"] = nui.get("otherBusiness").getValue();  
     
         if(typeof(bizId)!="undefine"&&bizId!= null&&bizId!="null"&&bizId!=""){
                inspect["liId"] = bizId;
         }
         var isNewExit=0;
         var checkResult = nui.get("checkResult").getValue();
         if(checkResult == "3"){
            isNewExit=1;
         }
         inspect["partyId"] = partyId;
         var csmSignalId = nui.get("csmSignalId").getValue();
         var planImplement = nui.get("planImplement").getValue();
         
         var json=nui.encode({inspect:inspect,dataObj:dataObj,dataObj1:dataObj2,isSml:1,"isNewExit":isNewExit,"planImplement":planImplement,"csmSignalId":csmSignalId});
         $.ajax({
			            url: "com.bos.aft.checkReport.saveLoanInspectOth.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false, 
			            cache: false,
			            contentType:'text/json',
			            success: function(text) {
			            	if(text.msg){
			            	   if(node!=null&&node!=""&&typeof(node)!="undefined"){
			                         openSubmitView(node);
			            	     }else{
			                         alert(text.msg);
			                     }
			                  }
						},
			            error: function() {
		                    nui.alert("保存失败！");
		                }
				});	
	}
	
// 跳转授信子页面
 function edit() {
        var row = contractInfo.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/aft/dailyInspect/creditDetail_debt.jsp?contractId="+row.contractId+"&whetherGovernmentFinanceCor="+row.whetherGovernmentFinanceCor+"&alcInfoId="+alcInfoId+"&lastAlcInfoId="+lastAlcInfoId+"&productType="+row.productType+"&bizId="+bizId+"&partyId="+partyId,
                title: "编辑", 
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
                    if(action=="ok"){
                        git.mask();
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
  
//增加一行removeRow ( row, autoSelect )
function btnAdd(){ 
                nui.get("addButton").setEnabled(false);
		        var index=grid.totalCount;
		    	var newRow = { name: "New Row" };
		    	grid.addRow(newRow, index);
		    	nui.get("addButton").setEnabled(true);
         }    

 function remove() {
        nui.get("delButton").setEnabled(false);
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	grid.removeRow(row,false);/* 删除页面行 */
           }); 
        } else {
            nui.alert("请选中一条记录");
        }
        nui.get("delButton").setEnabled(true);
    }

//增加一行removeRow ( row, autoSelect )
function btnAdd1(){ 
                nui.get("addButton1").setEnabled(false);
		        var index=grid2.totalCount;
		    	var newRow = { name: "New Row" };
		    	grid2.addRow(newRow, index);
		    	nui.get("addButton1").setEnabled(true);
         }    

 function remove1() {
        nui.get("delButton1").setEnabled(false);
        var row = grid2.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	grid2.removeRow(row,false);/* 删除页面行 */
           }); 
        } else {
            nui.alert("请选中一条记录");
        }
        nui.get("delButton1").setEnabled(true);
    }

function init(){
    getUserName("postName3");
    grid.load({"bizId":bizId});                                                     //加载借款人其他融资情况信息
    grid2.load({"bizId":bizId,type:1});                                             //加载审批条件落实情况信息
    grid3.load({"partyId":partyId});
    contractInfo.load({"partyId":partyId,"isSmlCorp":"1"});  
    //borrowFanceInfoGrid.load({indexCd:"AFTL090100"});       
    setOtherInfo();                                                                 //加载预警方面的信息
                                                                   
    var pageJsonData = nui.encode({partyId:partyId,bizId:bizId})
    $.ajax({                                                                        //获取页面选项卡集indexResults
				url: "com.bos.aft.checkReport.queryCheckReport.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				       var checkMethods = nui.decode(text.checkMethods);
				       lastAlcInfoId = text.lastAlcInfoId;                                            //上期检查ID       
				       alcInfoId = text.alcInfoId;                                                    //本期检查ID
     //alert("checkMethods:"+nui.encode(checkMethods));
				       var dataObject = nui.decode(text.dataObject);
				       nui.get("inspectWayCd").setValue(checkMethods.inspectWayCd); 
				       nui.get("otherBusiness").setValue(checkMethods.otherBusiness);
                       basicCorpInfo.setData(dataObject);
                       creditInfo.setData(dataObject);
                       check.setData(checkMethods);                                                    //加载检查方式
                       grid1.setData(checkMethods);
                       nui.get("checkResult").setValue(checkMethods.checkResult);
                       nui.get("isAdjustment").setValue(checkMethods.isAdjustment);
                       overAllEvaluation.setData(checkMethods);                                        //
                       var wholeEvaluationChoose = checkMethods.wholeEvaluationChoose;                 //获取整体评价选择
                       if(typeof(wholeEvaluationChoose) != "null" && typeof(wholeEvaluationChoose) != "undefined" && wholeEvaluationChoose!= null && wholeEvaluationChoose !=""){
                          var reslt = wholeEvaluationChoose.split(",");                                //组装整体评价选择
                          for(var i=0;i<reslt.length;i++){                                             //加载整体评价部分的信息
                              var value = reslt[i];
                              var idNum = "wholeEvaluationChoose"+(i+1);
                              nui.get(idNum).setValue(value);                                          //加载整体评价信息选项
                          } 
                       }
                       overAllEvaluation.setData(checkMethods);                                         //加载整体评价信息
                        if(onlyRead==1){                    //   
		                 check.setEnabled(false);                                                       //检查方式禁用
		                 nui.get("datagrid").setEnabled(false);                                         //对外融资禁用
		                 overAllEvaluation.setEnabled(false);                                           //整体评价禁用
		                 grid1.setEnabled(false);                                                       //对外担保禁用
		                 nui.get("goUpdate").hide();                                                    //隐藏修改指标按钮
		                 //nui.get("warrantButton").setEnabled(false);                                  对外担保融资按钮禁用
		                 nui.get("btnSave").hide();
		                 nui.get("delButton").hide();
		                 nui.get("addButton").hide();
		                 nui.get("delButton1").hide();
		                 nui.get("addButton1").hide();
		                }   
		                initFinance();                                                                //加载财务类指标
				},
				error: function () {
			        
			    }
			  
		});
	//alert("contract:"+nui.encode(contractInfo.getData()));      
}
init();

//加载预警模块数据
function setOtherInfo(){
       var json = nui.encode({partyId:partyId});
       $.ajax({
				url: "com.bos.aft.aft_inspect_report.getInspectOtherInfo.biz.ext",
				type: 'POST',
				data: json,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				    //alert(nui.encode(text.otherInfo));
					warn.setData(text.otherInfo);                                      //加载预警数据
					//nui.get("fcwxh").load({partyId:partyId,parentId:"222005"});        //加载其他预警信号
					nui.get("cwxh").load({partyId:partyId});         //加载财务类型号
				},
				error: function () {
			        
			    }
		});
}

 //查询人员信息
	function selectUser(i) {
			nui.open({
            url: nui.context + "/aft/postMgr/pro_post_responsible_member.jsp?bizId=<%=request.getParameter("partyId")%>&responsiblePersonType=8",
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
	            data: nui.encode({"bizId":"<%=request.getParameter("partyId") %>","responsiblePersonType":"8"}),
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	 nui.get(i).setText(text.userNames);
	            	 nui.get(i).setValue(text.userNames);
	            }
	        });
	 
	 }
	 
	 //根据检查方式加载数据
function judge(){ 
	    var wayCd = nui.get("inspectWayCd").getValue();
	    //alert(wayCd);  RECEPTION_TYPE_CD 
	    var html1 = "<div columns='2' class='nui-dynpanel' id='inspectform'><label >检查地点</label> <input  name='inspectPlace' class='nui-textbox nui-form-input' required='true'/></div>";
        var html2 = "<div columns='2' class='nui-dynpanel' id='inspectform'><label>人员类型</label> <input name='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010'  required='true'/></div>";
        var html3 = "<div columns='2' class='nui-dynpanel' id='inspectform'><label >其他检查方式</label> <input  name='otherInspectWay' class='nui-textbox nui-form-input' required='true' /></div>";
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
	
	//显示选项卡
	function initFinance() {
	    var k;                                                                      //父级指标码长度
	    //var alcInfoId =newCheckReport;                                                     //检查ID
	    var IdName = '#'+"financeConditionPage";
	    var pageName= "financeConditionPage";                                                            //关联指标代码
		$(IdName).html('');
		var ogj = $(IdName);
		//$('#aliDataIdDiv').html('');                                                   //本期检查ID
		var pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":pageName,"alcInfoId":alcInfoId,"lastAlcInfoId":lastAlcInfoId,show:2});
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
					//alert(nui.encode(indexResults));
				},
				error: function () {
			        
			    }
		});
		
		for(var j=0;j<1;j++) {//构造k个选项卡
			var temp = {};
		    var pros = indexResults[j].optionCards;
		    var options = new Array();
		    var multiSelect = false;
		    
		    for(var i=0;i<pros.length;i++) {//构造选项卡
		    	if(i == 0) {//标题
		        	temp.id = pros[i].indexCd;
		            temp.text = (j+1)+"、"+pros[i].indexName;
		            
		            if(pros[i].standardIndexCd == "2"){//判断单双选
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
	    			+ 'dataField="children" colAlign="left,left" enabled="false" colWidth="70%,30%" multiSelect="'+multiSelect+'"> </div>';
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
</script>
</html>