<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>小企业贷后检查工作台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:2015px;height:auto;overflow:hidden;">
<div title="小企业贷后检查工作台账查询" >
<center>
<div id="form1" class="nui-form" style="width:auto;height:auto;overflow:hidden;" >
	<!--<div class="nui-dynpanel" columns="6">
		<label>合同号：</label>
		<input name="loanrepayinfo.contractNum" required="false" class="nui-textbox nui-form-input"  />
		<label>贷款申请人：</label>
		<input name="loanrepayinfo.cp" required="false" class="nui-textbox nui-form-input"  />
		<label>借款日：</label>
		<input name="loanrepayinfo.loanActualPaymentDate" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>-->
	<div id="grid1" class="nui-datagrid" style="width:2000px;height:auto;" 
		url="com.bos.pub.standingbook.aftmanager.smallcorpaftcheck.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field=""  headerAlign="center" allowSort="true" >主体行名</div>
			<div field="orgNum"  headerAlign="center" allowSort="true" >经营单位</div>
			<div field="userNum"  headerAlign="center" allowSort="true" >客户经理</div>
			<div field="partyName"  headerAlign="center" allowSort="true" >授信企业名称</div>
			<div field="creditAmt"  headerAlign="center" allowSort="true" >授信总额</div>
			<div field="creditExposure"  headerAlign="center" allowSort="true" >总授信敞口</div>
			<div field=""  headerAlign="center" allowSort="true" >其中期末表内余额</div>
			<div field=""  headerAlign="center" allowSort="true" >其中期末表外余额</div>
			<div field=""  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否属于“民营集团关联企业授信”</div>
			<div field=""  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否属于“钢贸企业授信”</div>
			<div field=""  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否属于“互保、联保授信”</div>
			<div field=""  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否属于“票据承兑授信”</div>
			<div field="warningLevelCd"  headerAlign="center" allowSort="true" dictTypeId="XD_DHCD4006">预警级别</div>
			<div field=""  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否属于重点监测客户</div>
			<div field="setRate"  headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0001">贷后检查频率</div>
			<div field="inspectState"  headerAlign="center" allowSort="true" >贷后检查状态</div>
			<div field=""  headerAlign="center" allowSort="true" >检查报告质量</div>
			<div field="inspectDate"  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">最后一次贷后检查时间</div>
			<div field=""  headerAlign="center" allowSort="true" >上季度末财务报表及CRMS系统录入</div>
			<div field="pfConclusion"  headerAlign="center" allowSort="true" dictTypeId="XD_DHJCJL">检查结论</div>
			<div field="pfRiskcontrol"  headerAlign="center" allowSort="true" dictTypeId="XD_DHFXKZ">控制措施</div>
			<div field="pfPropertyAdjust"  headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否涉及分类调整</div>
			<div field=""  headerAlign="center" allowSort="true" >小企业金融部已对企业进行现场检查</div>
			<div field=""  headerAlign="center" allowSort="true" >备注</div>
		</div>
	</div>
</div> 	
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	
	</script>
</body>
</html>