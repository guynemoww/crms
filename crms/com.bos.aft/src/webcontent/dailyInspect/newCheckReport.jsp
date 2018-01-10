<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>大中贷后检查报告</title>
</head>
<body style="margin-left:10px;">
 <div>
     <a class="nui-button" iconCls="icon-save" onclick="goEdit()" id="goUpdate">修改指标信息</a>
 </div>
	<div id="check">
	<fieldset style="margin-top: 10px;">
  	<legend>
    	<span>检查方式:</span>    
    </legend>
	<div id="editform" class="nui-dynpanel" columns="4"  >
		<label>检查方式：</label>                                                                          
		<input name="inspectWayCd" id="inspectWayCd"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  required="true" /> 
		<label>接待人员姓名：</label>
		<input name="receptionName" id="receptionName" class="nui-textbox nui-form-input"  required="true"/>
		<label>检查时间/约见时间：</label>
		<input name="inspectDate" id="inspectDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"  required="true" />
		<label>接待人员职务：</label>
		<input name="receptionPost" id="receptionPost" class="nui-textbox nui-form-input"  required="true"/>
		<label>约见地点：</label>
		<input name="inspectPlace" id="inspectPlace" class="nui-textbox nui-form-input"  required="true" />
		
	</div>
	</fieldset>
	</div> 
    
    <fieldset style="margin-top: 10px;">
  	        <legend>
    	        <span>本期借款人的基本情况</span>
            </legend>
		    <div id="basicCorpInfo" class="nui-dynpanel" columns="4">
		      <!-- <label>客户编号：</label>
		      <input class="nui-text nui-form-input" name="partyNum" dictTypeId="XD_DHCD0001"/> -->
		      <label>客户名称：</label>
		      <input class="nui-text nui-form-input" name="partyName" dictTypeId="XD_DHCD0001"/>
	    	  <label>企业性质：</label>
		      <input class="nui-text nui-form-input" name="registrationType" id="registrationType" dictTypeId="CDKH0024"/>
		      <!-- <label>法定代表人：</label>
		      <input class="nui-text nui-form-input" name="ppname" id="ppname" dictTypeId="XD_DHCD0001"/>
		      <label>国标行业：</label>
		      <input class="nui-text nui-form-input" name="industrialTypeCd" id="industrialTypeCd" dictTypeId="CDKH0095" />
		      <label>主营业务：</label>
		      <input class="nui-text nui-form-input" name="operatingBusiness" id="operatingBusiness" dictTypeId="XD_DHCD0001"/>
		      <!-- <label>客户类型：</label>
		      <input class="nui-text nui-form-input" name="custRiskTypeCd" id="custRiskTypeCd" dictTypeId="XD_FLCD0002" />
		      <label>经营主责任人：</label>
		      <input class="nui-text nui-form-input" name="dutyManager" id="dutyManager"/>
		      <label>客户信用等级：</label>
		      <input class="nui-text nui-form-input" name="creditRatingCd" id="creditRatingCd" dictTypeId="XD_DHCD0001"/>
		      <label>评级有效期截至日：</label>
		      <input class="nui-text nui-form-input" name="effectiveEndDt" id="effectiveEndDt" dictTypeId="XD_DHCD0001"/>
		      <label>ECIF编号：</label>
		      <input class="nui-text nui-form-input" name="ecifPartyNum" id="ecifPartyNum"/> -->
		      <label>是否重点客户：</label>
		      <input class="nui-text nui-form-input" name="isImportant" id="inspectRate" dictTypeId="XD_0002"/>
		      <label>检查频率：</label>
	       	  <input class="nui-text nui-form-input" name="setRate" id="inspectRate" dictTypeId="XD_DHCD0001"/>
	        </div>
	</fieldset>

    <fieldset style="margin-top: 10px;">
  	        <legend>
    	        <span>本期借款人的一般额度授信情况</span>
            </legend>
		    <div id="creditInfo" class="nui-dynpanel" columns="4">
		      <!--  <label colspan="2">是否已做额度授信：</label>
		      <input colspan="2" class="nui-text nui-form-input" name="isCredit" id="isCredit" dictTypeId="XD_0002"/> -->
		      <!-- <label>额度编号：</label>
		      <input class="nui-text nui-form-input" name="limitNum" id="limitNum" dictTypeId="XD_DHCD0001"/>
	    	  <label>币种：</label>
		      <input class="nui-text nui-form-input" name="currencyCd" id="currencyCd" dictTypeId="CD000001"/> -->
		      <label>一般授信额度：</label>
		      <input class="nui-text nui-form-input" name="creditExposure" id="creditExposure" dictTypeId="XD_DHCD0001"/>
		      <!-- <label>汇率：</label>
		      <input class="nui-text nui-form-input" name="midExchangeRate" id="midExchangeRate" dictTypeId="XD_DHCD0001"/>
		      <label>起始日：</label>
		      <input class="nui-text nui-form-input" name="startDate" id="startDate" dictTypeId="XD_DHCD0001"/> -->
		      <label>到期日：</label>
		      <input class="nui-text nui-form-input" name="endDate" id="endDate" dictTypeId="XD_DHCD0001"/>
		      <label>已用额度：</label>
		      <input class="nui-text nui-form-input" name="occupiedExposure" id="occupiedExposure" dictTypeId="XD_DHCD0001"/>
		      <label>可用额度：</label>
		      <input class="nui-text nui-form-input" name="availableExposure" id="availableExposure" dictTypeId="XD_DHCD0001"/>
		      <!-- <label>本期授信额度是否存在被冻结、削减、恢复等情况：</label>
		      <input class="nui-text nui-form-input" name="inspectRate" id="inspectRate" dictTypeId="XD_DHgCD0001"/> -->
		      <label>五级分类：</label>
		     <input name="fiveLevelClass" class="nui-text nui-form-input" dictTypeId="XD_FLCD0001"/>
	        </div>
	 </fieldset>
	
     <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>基本信息</span>
             </legend>
		     <div id="isStopOrStopStatus" class="nui-dynpanel" columns="2">
                   <!-- <label>客户经营是否处于停止或基本停止状态:</label>
                   <input class="nui-text" id="isOrNo" name="isStopOrStopStatus1" /> -->
             </div>
	        <!-- 显示选项卡 -->
	        <div class="mini-dynpanel2Td" id="borrowMgrInfo">
	        </div>
	 </fieldset>    
<!-- 
	 <fieldset>
  	         <legend>
    	             <span>借款人财务状况-财务报表分析结果：</span>
             </legend>
	         <div id="borrowFanceInfoGrid" class="nui-datagrid" url="com.bos.aft.checkReport.getOppions.biz.ext" dataField="codes" sizeList="[10,15,20,50,100]" 
	              allowResize="false" multiSelect="true" showPager="false" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
                 <div property="columns">  
       		     -->
       		     <!--ComboBox：本地数据        
                 <div field="indexName"  headerAlign="center">财务报表分析结果
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
                 </div>
                 --> 
                 <!-- <div field="indexValue"  headerAlign="center">具体分值
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
                 </div>
                 <div field="indexChoice" type="dictcomboboxcolumn" autoShowPopup="true" name="gender"  dictTypeId="XD_0002" align="center" headerAlign="center">检查结果
	                 <input property="editor" class="nui-dictcombobox" style="width:100%;" dictTypeId="XD_0002" />                
	            </div> 
                </div>
             </div>	
     </fieldset>
 --> 	 
	 <fieldset>
  	         <legend>
    	             <span>借款人对外担保情况：</span>
             </legend>
	         <div id="warrantAmtInfo" class="nui-dynpanel" columns="6"  >
		     <label>上年度对外担保金额：</label>                                                                          
		     <input name="lastYearWarrantBalance" id="lastYearWarrantBalance" class="nui-textbox nui-form-input"  /> 
		     <label>上期检查时对外担保金额：</label>
		     <input name="priorPeriodWarrantBalance" id="priorPeriodWarrantBalance" class="nui-text nui-form-input"  />
		     <label>本次检查时对外担保金额：</label>
		     <input name="currentCheckWarrantAmt" id="currentCheckWarrantAmt" class="nui-textbox nui-form-input" required="true"/>
		     <!-- 
		     <label>详细情况：</label>
		     <input name="detailInfo" id="detailInfo" class="nui-textarea nui-form-input" colspan="5" required="true"/>
		      -->
	         </div>
     </fieldset>
	 
	 <fieldset>
  	         <legend>
    	             <span>借款人在本行外的其他融资情况：</span> 
             </legend>
             <div id="datagrid1" class="nui-dynpanel" columns="4"  >
		     <label>上年度末他行融资余额（万元）：</label>                                                                          
		     <input name="lastYearFinancingBalance" id="lastYearFinancingBalance" class="nui-textbox nui-form-input"  /> 
		     <label>上期检查时他行融资余额（万元）：</label>
		     <input name="priorPeriodFinancingBalance" id="priorPeriodFinancingBalance" class="nui-textbox nui-form-input"  />
		     </div>
		     
             <a id="warrantButton" class="nui-button" style="float: right;" iconCls="icon-remove" onclick="remove">删除一行</a>
	         <a  id="warrantButton1" class="nui-button" style="float: right;" iconCls="icon-add" onclick="btnAdd">点击此处填写</a>
             <strong style="float: left;">本期他行融资情况</strong>
	         <div id="datagrid" class="nui-datagrid" showPager="false" url="com.bos.aft.checkReport.queryLoanInspectOth.biz.ext" idField="id"
	              oncellendedit="checkFloat(e)" dataField="others" allowCellEdit="true" allowCellSelect="true" multiSelect="false" editNextOnEnterKey="true" >
                 <div property="columns">
                 <div type="checkcolumn"></div>  
       		     <!--ComboBox：本地数据-->     
                 <div field="finMechanism" width="120" headerAlign="center" required="true">融资机构
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50" required="true"/>
                 </div>
                 <div field="finAmount" width="120" headerAlign="center" required="true">融资金额
                     <input property="editor" class="nui-spinner" style="width:100%;" decimalPlaces="2" minValue="0" maxValue="99999999999999"  vtype="float"  required="true"/>
                 </div>
                 <div field="finTerm" width="120" headerAlign="center" required="true">期限（月）
                     <input property="editor" class="nui-spinner" minValue="0" maxValue="1000" style="width:100%;" vtype="int" minHeight="50" required="true"/>
                 </div>
                 <div field="finNature" width="120" headerAlign="center" required="true">融资性质
	                 <input property="editor" class="nui-textbox" style="width:100%;" dictTypeId="XD_0002"  required="true"/>                
	             </div> 
                 </div>
             </div>	
      </fieldset>
     
     <fieldset style="margin-top: 10px;">
             <legend>
                      <span>担保信息</span>
             </legend>
             <div id="warrantInfo" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.queryWarrantContractInfo.biz.ext" dataField="warrantContracts"
	              allowResize="true" showReloadButton="false" showPageSize="false" pageSize="10" multiSelect="false" sortMode="client">
                 <div property="columns">
                 <div type="indexcolumn" >序号</div>
                     <div field="subContractNum" headerAlign="center" allowSort="true">担保合同编号</div>
                     <div field="loanType" headerAlign="center" allowSort="true">担保方式</div> 
                     <div field="suretyId" headerAlign="center" allowSort="true" dictTypeId="product">抵质押物类型</div> 
                     <div field="assessCycle" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1186">押品重估频率</div> 
                     <div field="assessCost" headerAlign="center" allowSort="true" >贷时评估价值</div> 
                 </div>
    </div>
     </fieldset >
     
      <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>财务信息</span>
             </legend>
	        <!-- 显示选项卡 -->
	        <div class="mini-dynpanel2Td" id="creditAndRepayMent">
	        </div>
	 </fieldset>
	
     <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>信用记录与还款意愿</span>
             </legend>
		    <div id="creditConditionPage" class="nui-dynpanel" columns="2">
		     <!--  <label>借款人是否出现拖欠各种税费、水电费的现象</label>
		      <input class="nui-text nui-form-input" name="indexName" id="creditConditionPage1" />  -->
	        </div>
	        <!-- 显示选项卡 -->
	        <div class="mini-dynpanel2Td" id="borrowFinance">
	        </div>
	 </fieldset>
	 <!--
	 fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>外部宏观经济政策影响因素</span>
             </legend>
	        --><!-- 显示选项卡 
	        <div class="mini-dynpanel2Td" id="outsideMacroscopic">
	        </div>
	 </fieldset>
	  -->
	 <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>外部环境与重大事项</span>
             </legend>
	        <!-- 显示选项卡 -->
	        <div class="mini-dynpanel2Td" id="majorMatterAnalysis">
	        </div>
	 </fieldset>
	 
	<!-- <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>抵质押信息</span>
             </legend> -->
	        <!-- 显示选项卡 -->
	     <!--   <div class="mini-dynpanel2Td" id="mortgageBasicInfo">
	       
	        </div>
	         <div class="mini-dynpanel2Td" id="">
	         </div>
	 </fieldset>  --> 
<fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>销售收入情况</span>
             </legend>
             <div id="saleInfo" class="nui-dynpanel" columns="4"  > rentalInfo saleInfo
		              <label>上期销售收入：</label>                                                                          
		              <input name="lastIncome" id="lastIncome" class="nui-text nui-form-input"  /> 
		              <label>本期销售收入：</label>
		              <input name="currentIncome" id="currentIncome" class="nui-textbox nui-form-input"  />
		              <label>销售情况具体描述(限1300字)：</label>
		              <input name="saleDescription" id="saleDescription" vtype="maxLength:4000" colspan="3" class="nui-textarea nui-form-input" required="true"/>
             </div>
</fieldset> 
<fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>租金收入情况</span>
             </legend>
             <div id="rentalInfo" class="nui-dynpanel" columns="4"  >
		              <label>上期租金收入：</label>                                                                          
		              <input name="lastRent" id="lastRent" class="nui-text nui-form-input"  /> 
		              <label>本期租金收入：</label>
		              <input name="currentRent" id="currentRent" class="nui-textbox nui-form-input"  />
		              <label>租金情况具体描述(限1300字)：</label>
		              <input name="rentDescription" id="rentalDescription" vtype="maxLength:4000" colspan="3" class="nui-textarea nui-form-input" required="true"/>
             </div>
</fieldset>
<div id="warn">
	<div  style="margin-top: 10px;">	 
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
                     <div type="indexcolumn"></div>
                     <div field="dictName">信号名称</div> 
                     <div field="parentType">信号类别</div> 
                     <div field="signalState">信号描述</div> 
                 </div>
             </div>
		  <!--  
		     <div style="margin-top: 20px;text-align: left;">2.非财务信号</div>
		     <div id="fcwxh" class="nui-datagrid" url="com.bos.aft.aft_inspect_report.getInspectSignals.biz.ext" dataField="signalInfos" sizeList="[10,15,20,50,100]" 
	              allowResize="true" showReloadButton="false" pageSize="10" >
                 <div property="columns">
                     <div type="indexcolumn"></div>
                     <div field="dictName">信号名称</div> 
                 </div>
             </div>
		  -->
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
<!-- <fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>客户与我行签订的合同概况</span>
             </legend>
		    <div id="basicCorpInfo" class="nui-dynpanel" columns="8" style="text-align: center;">
		      <label>总计：</label>
		      <input class="nui-text nui-form-input" name="partyNum" colspan="7"/>
		      <label>币种：</label>
		      <input class="nui-text nui-form-input" name="partyNum" dictTypeId="CD000001"/>
		      <label>授信余额：</label>
		      <input class="nui-text nui-form-input" name="partyName"/>
	    	  <label>不良贷款余额：</label>
		      <input class="nui-text nui-form-input" name="" id="inspectRate"/>
		      <label>不良率：</label>
		      <input class="nui-text nui-form-input" name="ppname" id="ppname"/>
	        </div>
</fieldset>
 -->
	
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>债项信息</span>
    </legend>
    <a class="nui-button" iconCls="icon-save" onclick="edit()">查看授信信息</a>
	<div id="grid1" class="nui-datagrid" url="com.bos.aft.checkReport.queryReportContractInfo.biz.ext" dataField="contracts"
	     allowResize="true" showReloadButton="false" showPageSize="false" pageSize="10" multiSelect="false" sortMode="client">
                 <div property="columns">
                 <div type="checkcolumn" >选择</div>
                 <div type="indexcolumn" >序号</div>
                     <div field="contractNum" headerAlign="center" allowSort="true">合同序号</div>
                     <div field="loanNum" headerAlign="center" allowSort="true">借据编号</div> 
                     <div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div> 
                     <div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">合同币种</div> 
                     <div field="startDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">起始日</div> 
                     <div field="expirationDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日</div> 
                     <div field="loanAmt" headerAlign="center" allowSort="true">借据金额</div>
                     <div field="loanBalance" headerAlign="center" allowSort="true">借据余额</div> 
                     <div field="summaryChange" headerAlign="center" allowSort="true">展期次数</div> 
                     <div field="overduebalance" headerAlign="center" allowSort="true">逾期(垫款)本金</div> 
                     <div field="interestbalance" headerAlign="center" allowSort="true">欠息金额</div> 
                     <div field="loanFiveLevelClassiFication" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001">资产分类</div>
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
                 <div field="notImpReason" width="120" headerAlign="center" required="true">落实情况说明或未落实原因
                     <input property="editor" class="nui-textarea" style="width:100%;" minHeight="50" required="true"/>
                 </div>
                 </div>
             </div>	
     </fieldset>   
   
<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>对借款人及担保人的整体评价（必填）</span>
    </legend>
 <div id="overAllEvaluation">
	    <div style="margin-top: 20px;"   class="nui-dynpanel" columns="4" style="margin-top: 20px;" >
		    <label>项目实施情况评价：</label>
		    <input id="wholeEvaluationChoose1" name="wholeEvaluationChoose1" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		    <label>借款人经营情况评价：</label>
		    <input id="wholeEvaluationChoose5" name="wholeEvaluationChoose5" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/> 
		    <label>借款人财务状况评价：</label>
		    <input id="wholeEvaluationChoose2" name="wholeEvaluationChoose2" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		    <label>借款人履约能力评价：</label>
		    <input id="wholeEvaluationChoose3" name="wholeEvaluationChoose3" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		    <label>担保人代偿能力评价：</label>
		    <input id="wholeEvaluationChoose4" name="wholeEvaluationChoose4" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
	        
	    </div>
	
	    <div style="margin-top: 20px;font-weight:bold;">整体评价（包括但不限于项目实施情况、借款人经营情况、借款人财务状况、借款人履约能力和担保人代偿能力等）:</div>
	    <div>
	        <input name="wholeEvaluationExplain1" class="nui-textarea nui-form-input"  style="width:100%;"/>
	    </div>
	    <div style="margin-top: 20px;font-weight:bold;">上述评价勾选为“关注”或“恶化”的，除整体评价外需另行详细说明原因和相关措施:</div>
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
			<input id="postName3" name="postName3" style="width: 100%;" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectUser(postName3)" required="false" />
			
			</div>
		
</div>
</fieldset>	
<div id="reportScore">
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
	     <a class="nui-button" iconCls="icon-save" onclick="submitData()" id="btnSave">保存报告</a>
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
    //var borrowFanceInfoGrid = nui.get("borrowFanceInfoGrid");                      //借款人财务信息 
    var rentalInfo = new nui.Form("#rentalInfo");                                    //租金收入情况
    var saleInfo = new nui.Form("#saleInfo");                                        //销售收入情况
    var basicCorpInfo = new nui.Form("#basicCorpInfo");                              //客户基本信息
    var creditInfo = new nui.Form("#creditInfo");                                    //授信信息
    var check = new nui.Form("#check");                                              //检查方式
    var overAllEvaluation = new nui.Form("#overAllEvaluation");                      //整体评价部分
    var warn = new nui.Form("#warn");                                                //预警部分
    var grid1 = nui.get("grid1");                                                    //合同信息
    var warrantInfo = nui.get("warrantInfo");                                        //客户担保信息
    var datagrid1 =new nui.Form("#datagrid1");                                       //借款人对外担保情况
    var warrantAmtInfo = new nui.Form("#warrantAmtInfo");                            //对外担保信息
    var pageName =["borrowMgrInfo","creditAndRepayMent","borrowFinance","majorMatterAnalysis"];  //指标配置 （外部宏观经济政策影响因素：,"outsideMacroscopic"）
    var lastAlcInfoId;                                                               //上期贷后检查ID
    var alcInfoId;                                                                   //本期贷后检查ID
   //var pageName =["mortgageBasicInfo"];
    var targNumCode = [];//["creditConditionPage","isStopOrStopStatus"];                                      
    var grid = nui.get("datagrid");
    var grid2 =nui.get("datagrid2");                                                 //审批条件落实情况
    var partyId = "<%=request.getParameter("partyId") %>"; // 参与人ID
    var bizId = "<%=request.getParameter("bizId") %>";
    var errorMsg;                                     //校验提示框
    var panDuan;                                      //校验判断标示
	var inputID = [];                                 //备份指标项
	var node =<%=request.getParameter("node") %>;     //获取流程岗位信息
    
	var targetNums;                                                                       //父级指标码
	var onlyRead="<%=request.getParameter("onlyRead") %>";                                //判断是否是审批界面
    var score = "<%=request.getParameter("score") %>";                                    //判断是否需要评分
    if(score==1){
        nui.get("saveScore").show();                                                          //显示评分按钮
        //nui.get("reportScore").show();                                                              //显示评分
    }else{
        nui.get("saveScore").hide();                                                          //隐藏评分按钮
        $("#reportScore").hide();                                                              //隐藏评分
    }
	                                                           
	for(var i=0;i<targNumCode.length;i++){
	     var id="#"+targNumCode[i];
	     var name=targNumCode[i];
         name = new nui.Form(id);
    }

  
  function init(){
    for(var i=0;i<targNumCode.length;i++){
      getTargNumCode(targNumCode[i]);
    }
    getUserName("postName3");
    warrantInfo.load({"partyId":partyId});                                                            //加载客户担保信息
    grid.load({"bizId":bizId});                                                                       //加载借款人其他融资情况信息
    grid2.load({"bizId":bizId,type:1});                                                               //加载审批条件落实情况信息
    var pageJsonData = nui.encode({partyId:partyId,bizId:bizId});
    grid1.load({"partyId":partyId});                                                                   //加载合同信息
   // borrowFanceInfoGrid.load({indexCd:"AFTL090100"});                                                //加载借款人财务信息
    $.ajax({                                                                                           //获取页面选项卡集indexResults
				url: "com.bos.aft.checkReport.queryCheckReport.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				       
				       var checkMethods = nui.decode(text.checkMethods);                                 
				       var dataObject = nui.decode(text.dataObject);
				       nui.get("inspectWayCd").setValue(checkMethods.inspectWayCd);
				       warrantAmtInfo.setData(checkMethods);                                           //加载对外担保信息                              
				       lastAlcInfoId = text.lastAlcInfoId;                                             //上期检查ID       
				       alcInfoId = text.alcInfoId;                                                     //本期检查ID
				       rentalInfo.setData(checkMethods);                                               //加载租金收入信息
				       saleInfo.setData(checkMethods);                                                 //加载销售收入信息
                       basicCorpInfo.setData(dataObject);                                              //加载借款人基本情况
                       creditInfo.setData(dataObject);                                                 //加载借款人授信情况
                       overAllEvaluation.setData(checkMethods);                                        //加载整体评价信息
                       check.setData(checkMethods);                                                    //加载检查方式
                       datagrid1.setData(checkMethods);                                                //加载他行融资情况
                       var wholeEvaluationChoose = checkMethods.wholeEvaluationChoose;                 //获取整体评价选择
                       for(var i=0;i<pageName.length;i++){
                           initGrid(pageName[i]);
                        }
                       if(typeof(wholeEvaluationChoose) != "null" && typeof(wholeEvaluationChoose) != "undefined" && wholeEvaluationChoose!= null && wholeEvaluationChoose !=""){
                          var reslt = wholeEvaluationChoose.split(",");                                //组装整体评价选择
                          for(var i=0;i<reslt.length;i++){                                             //加载整体评价部分的信息
                              var value = reslt[i];
                              var j=i+1;
                              var idNum = "wholeEvaluationChoose"+j;
                              nui.get(idNum).setValue(value);                                          //加载整体评价信息选项
                          } 
                       }
                       setOtherInfo();                                                                 //加载预警方面的信息
                       if(onlyRead==1){
		                        check.setEnabled(false); 
		                        datagrid1.setEnabled(false);
		                        warrantAmtInfo.setEnabled(false);
		                        nui.get("datagrid").setEnabled(false);
		                        overAllEvaluation.setEnabled(false);
		                        nui.get("btnSave").hide();
		                        nui.get("warrantButton").hide();                                     //对外担保融资按钮隐藏
		                        nui.get("warrantButton1").hide();                                    //对外担保融资按钮隐藏
		                        nui.get("delButton1").hide();                                        //只读状态下隐藏审批意见落实情况按钮
		                        nui.get("addButton1").hide();                                        //只读状态下隐藏审批意见落实情况按钮
		                        nui.get("goUpdate").hide();
		               }   
                       document.getElementById("AFTL040100").readOnly = false;
                      /*for(var i=0;i<pageName.length;i++){
                             var nameId=inputID[pageName[i]];
                           for(var j=0;j<nameId.length;j++){
                              alert(nameId[j]);
                              document.getElementById(nameId[j]).readOnly=true;
                           }
                          
                          }*/
                       //if(isOnlyRead==1){                                                              //审核界面页面禁用
                         // nui.get("borrowFanceInfoGrid").setEnabled(false);
                          
                      // }
				},
				error: function () {
			        
			    }
			   
		});
		
}
init();

  function submitData(){
	  check.validate();  
	  rentalInfo.validate();                                                                   //校验租金收入情况
	  saleInfo.validate();                                                                     //校验销售收入情况
	    if (saleInfo.isValid() == false||rentalInfo.isValid() == false){
	        alert("请检查输入项!");
        	return;
        } 
        if (check.isValid() == false){
        	alert("请把必输项填写完整！");
        	return;
        } 
        overAllEvaluation.validate();
        if (overAllEvaluation.isValid() == false){
        	alert("请完善检查意见及审批意见！");
        	return;
        }
	    var inspect={};
	    //alert("grid"+grid.isValid());
	    panDuan == 1;
	    var dataObj=[];                               //其他融资情况数据载体
	    var dataObj2=[];                              //审批条件落实情况
	    var index=0;
	    var index1=0;
	    grid.findRow(function(row){ 
            if(row.finMechanism== null && row.finAmount== null && row.finTerm== null && row.finNature== null && $.trim(row.finMechanism)=="" && $.trim(row.finTerm)==""){
             //alert(nui.encode(row));
             panDuan=0; 
             return;  
            }
            dataObj[index] = {liId:bizId,finMechanism:row.finMechanism,finAmount:row.finAmount,finTerm:row.finTerm,finNature:row.finNature};
            index=index+1;
         });
         //alert(nui.encode(dataObj));
         //return;
       //审批条件落实情况
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
	     var checkData = check.getData();
	     /*
        组装贷后检查表单数据      
     */
     var rentalInfoRlst = rentalInfo.getData();                                                 //获取租金收入信息
     var saleInfoRlst = saleInfo.getData();                                                     //获取销售收入信息
     inspect["currentRent"] = rentalInfoRlst.currentRent;                                       //本期租金收入
     inspect["rentDescription"] = rentalInfoRlst.rentDescription;                               //租金收入描述
     inspect["currentIncome"] = saleInfoRlst.currentIncome;                                     //本期销售收入
     inspect["saleDescription"] = saleInfoRlst.saleDescription;                                 //销售收入描述
     inspect["lastYearFinancingBalance"] = nui.get("lastYearFinancingBalance").value;           //上年对外融资金额
     inspect["priorPeriodFinancingBalance"] = nui.get("priorPeriodFinancingBalance").value;     //上期对外融资金额
     inspect["lastYearWarrantBalance"] = nui.get("lastYearWarrantBalance").value;               //上年对外担保金额
     //inspect["priorPeriodWarrantBalance"] = nui.get("priorPeriodWarrantBalance").value;         //上期对外担保金额
     inspect["currentCheckWarrantAmt"] = nui.get("currentCheckWarrantAmt").value;               //本期对外担保金额
     inspect["inspectWayCd"] = checkData.inspectWayCd;                                          //贷后检查方式
     inspect["inspectPlace"] = checkData.inspectPlace;                                          //检查地点
     inspect["inspectDate"] = checkData.inspectDate;                                            //检查日期
     inspect["receptionName"] = checkData.receptionName;                                        //接待人员姓名
     inspect["receptionPost"] = checkData.receptionPost;                                        //接待人员职务
     inspect["inspectRate"] = checkData.inspectRate;                                            //检查频率
     var datagridObj = datagrid1.getData();               //获取对外担保数据
     //inspect["detailInfo"] = datagridObj.detailInfo;                                          //详细情况
     var wayCd = nui.get("inspectWayCd").getValue();
     if(wayCd==1){
        inspect["inspectPlace"] = checkData.inspectPlace;                                       //检查地点
     }else if(wayCd==2){
        inspect["receptionTypeCd"] = checkData.receptionTypeCd;                                 //人员类型
     }else if(wayCd==3){
        inspect["otherInspectWay"] = checkData.otherInspectWay;                                 //其他检查方式
     } 
     var overAllEvaluationData =overAllEvaluation.getData();
     /*
       组装整体评价数据
     */
     var wholeEvaluationChoose = overAllEvaluationData.wholeEvaluationChoose1+","+overAllEvaluationData.wholeEvaluationChoose2+","+overAllEvaluationData.wholeEvaluationChoose3+","+overAllEvaluationData.wholeEvaluationChoose4+","+overAllEvaluationData.wholeEvaluationChoose5;
     inspect["wholeEvaluationChoose"] = wholeEvaluationChoose;
     inspect["wholeEvaluationExplain1"] = overAllEvaluationData.wholeEvaluationExplain1;
     inspect["wholeEvaluationExplain2"] = overAllEvaluationData.wholeEvaluationExplain2;
     if(typeof(bizId)!="undefine"&&bizId!= null&&bizId!="null"&&bizId!=""){
                inspect["liId"] = bizId;
         }
     inspect["partyId"] = partyId;
     var csmSignalId = nui.get("csmSignalId").getValue();
     var planImplement = nui.get("planImplement").getValue();
     var json=nui.encode({dataObj:dataObj,inspect:inspect,dataObj1:dataObj2,isSml:0,"planImplement":planImplement,"csmSignalId":csmSignalId});
        //alert(nui.encode(json)); 
        $.ajax({
			            url: "com.bos.aft.checkReport.saveLoanInspectOth.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	if(text.msg){
			            	   if(node!=null&&node!=""&&typeof(node)!="undefined"){
			                         openSubmitView(node);
			            	     }else{
			                         alert(text.msg);
			                     }
			                  }
			               
						},
			            error: function () {
		                    nui.alert("保存失败！");
		                }
				});
	    panDuan = 1;
	    errorMsg = "";
	    /*for(var i=0;i<pageName.length;i++){
	      saveData(inputID[pageName[i]]);
	      if(panDuan==1){
	         alert(errorMsg);
	         return;
	      }
	    }*/
	    
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
                                                //错误提示信息
// 跳转授信子页面
 function edit() {
        var row = grid1.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/aft/dailyInspect/creditDetail.jsp?contractId="+row.contractId+"&partyId="+partyId+"&whetherGovernmentFinanceCor="+row.whetherGovernmentFinanceCor+"&alcInfoId="+alcInfoId+"&lastAlcInfoId="+lastAlcInfoId+"&productType="+row.productType+"&bizId="+bizId,
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
  
//增加一行
function btnAdd(){
		        var index=grid.totalCount;
		    	var newRow = { name: "New Row" };
		    	grid.addRow(newRow, index);
         }    
    
function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	grid.removeRow(row,false);/* 删除页面行 */
           }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }

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
/*根据检查方式加载数据
function judge(){ 
	    var wayCd = nui.get("inspectWayCd").getValue();
	    //alert(wayCd);  RECEPTION_TYPE_CD 
        var html1 = "<div columns='4' class='nui-dynpanel' id='inspectform'><label>检查地点：</label> <input colspan='3' name='inspectPlace' class='nui-textbox nui-form-input' required='true'/></div>";
        var html2 = "<div columns='4' class='nui-dynpanel' id='inspectform'><label>人员类型：</label> <input name='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010' colspan='2' required='true'/></div>";
        var html3 = "<div columns='4' class='nui-dynpanel' id='inspectform'><label>其他检查方式：</label> <input colspan='3' name='otherInspectWay' class='nui-textbox nui-form-input' required='true' /></div>";
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
*/
	
//显示选项卡
	function initGrid(e) {
	    var k;                                                                      //父级指标码长度
	    //var alcInfoId =newCheckReport;                                                     //检查ID
	    var IdName = '#'+e;
	    var pageName= e;                                                            //关联指标代码
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
					//nui.get("fcwxh").load({partyId:partyId,parentId:"212002"});        //加载非财务类信号
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
	 
    function goEdit(){
        var param=nui.encode({"bizId":alcInfoId,"partyId":partyId,"irId":bizId,"lastAlcInfoId":lastAlcInfoId});                                        //,"node":node 暂时不用的参数 注掉
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
    
    }
    
  function getTargNumCode(e){
    var tName = e;
    var json = nui.encode({targNumName:tName,"partyId":partyId});
   
    $.ajax({                                                                                           //获取页面选项卡集indexResults
				url: "com.bos.aft.indexMgr.getChooseIndex.biz.ext",                              //com.bos.aft.indexMgr.getChooseIndex
				type: 'POST',
				data: json,
				async:false,  
				cache: false,
				contentType:'text/json',
				success: function (text) {
				     nui.get(tName+"1").setValue(text.dataObj.indexName);
				},
				error: function () {
			        
			    }
			   
		});
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
</script>
</html>