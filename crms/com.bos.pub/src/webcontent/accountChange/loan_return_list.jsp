<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-13 8:26:27
  - Description:
-->
<head>
<title>查询贷款还款计划表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="账务调整">
				<div id="form1" class="nui-form"style="width: 99.5%; height: auto; overflow: hidden;">
					<div class="nui-dynpanel" columns="6">
						<label>借据编号：</label>
						<input id="item.dueNum" name="item.dueNum" required="true" class="nui-textbox nui-form-input" />
						
						<label>放款金额：</label>
						<input id="item.amt"    name="item.amt" required="false" class="nui-textbox nui-form-input" />
						<label>起始日期：</label>			
						<input id="item.begDate"  name="item.begDate" required="false"  class="nui-DatePicker nui-form-input"/>
						
						<label>到期日期：</label>			
						<input id="item.endDate"   name="item.endDate" required="false" class="nui-DatePicker nui-form-input" />
						
						<label>正常利率：</label>
						<input id="item.norItrRate"    name="item.norItrRate" required="false" class="nui-textbox nui-form-input" />
						<label>指定还款日：</label>			
						<input id="item.payDate"   name="item.payDate" required="false" class="nui-DatePicker nui-form-input" />
										
						<label>还款方式：</label>
						<input id="item.curPrmPayTyp" name="item.curPrmPayTyp"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1162"  allowInput="false" />																											
						<label>阶段性首次还本期数：</label>
						<input id="item.stgFirstMon"    name="item.stgFirstMon" required="false" class="nui-textbox nui-form-input" />																
						<label>利率依据方式：</label>
						<input id="item.itrRateWay" name="item.itrRateWay"class="nui-textbox nui-form-input" />
																						
						<label>是否为已经发放贷款标志：</label>

							<input id="item.exiFlg" name="item.exiFlg"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SFYFFDKBZ"  allowInput="false" />
						
						
						
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					</div>
				</div>	
		
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		</div>
				
		<div id="datagrid1" class="nui-datagrid"style="width:100%; height: auto" allowAlternating="true"
					url="com.bos.pub.accountChange.loanKuan.biz.ext" dataField="items"allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="currPeri" allowSort="true"  headerAlign="center" dictTypeId="org">连续期次</div>
						<div field="forwProvDate" allowSort="true"  headerAlign="center">本期起始日</div>
						<div field="nextProvDate" allowSort="true"  headerAlign="center">本期到期日</div>
						<div field="dCurPrin" allowSort="true"  headerAlign="center">本期本金</div>
						<div field="dCurItr" allowSort="true"  headerAlign="center" >本期利息</div>
						<div field="dTotalAmt" allowSort="true"  headerAlign="center">本期合计</div>
						<div field="dTotalPrin" allowSort="true"  headerAlign="center">汇总本金</div>
						<div field="dTotalItr" allowSort="true"  headerAlign="center">汇总利息</div>
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
		}
	
	
	</script>
</body>
</html>