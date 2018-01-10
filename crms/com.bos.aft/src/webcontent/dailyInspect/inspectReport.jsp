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
	<label><span style="font-weight:bold;">检查方式:</span></label>
	<div id="editform" class="nui-dynpanel" columns="4"  >
		<label>检查方式：</label>
		<input name="inspectWayCd" id="inspectWayCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  onhidepopup="hidepopup" required="true" /> 
		<label>检查时间/约见时间：</label>
		<input name="inspectDate" id="inspectDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
		
		<label>接待人员姓名：</label>
		<input name="receptionName" id="receptionName" class="nui-textbox nui-form-input"/>
		<label>接待人员职务：</label>
		<input name="receptionPost" id="receptionPost" class="nui-textbox nui-form-input"/>
		
	</div>
	<div id="inspectMess">   
	</div>
	<div class="nui-dynpanel" columns="4">
		<label>检查频率：</label>
		<input class="nui-dictcombobox nui-form-input" name="inspectRate" id="inspectRate" dictTypeId="XD_DHCD0001"/>
		<label>其他频率：</label>
		<input class="nui-textbox nui-form-input" >
		<label>抵质押物评估价值：</label>
		<div>
		
		<label>房屋抵押：</label>
		<input class="nui-textbox nui-form-input" style="width:100px;">万元
		<br/>
		
		<label>其他：</label><input class="nui-textbox nui-form-input" style="width:180px;">
		<input class="nui-textbox nui-form-input" style="width:100px;">万元
	
		</div>
	</div>
	
	<div class="" style="border-bottom:0;text-align:left;padding-top: 10px;padding-bottom:10px;" >
		<label><span>审批要求及贷后等意见落实情况：</span></label>
		<a  id="addBtn" class="nui-button" iconCls="icon-add" onclick="btnAdd(1)">增加一行</a>
	</div>
	<div id="datagrid1" class="nui-datagrid" showPager="false"
        url="" idField="id" allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" >
        <div property="columns"> 
       		 <!--ComboBox：本地数据-->         
            <div type="comboboxcolumn" autoShowPopup="true" name="gender" field="isImp" width="100"  align="center" headerAlign="center">是否落实
	                <input property="editor" class="nui-combobox" style="width:100%;" data="Genders" />                
	        </div> 
            <div field="approvalInfo" width="120" headerAlign="center">审批要求
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="notImpReason" width="120" headerAlign="center">未落实原因描述
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
            <div field="someMeasure" width="120" headerAlign="center">相关措施
                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
            </div>
        </div>
     </div>	
	<label><span style="font-weight:bold;">资金流向:</span></label>
	<div id="condition" class="nui-dynpanel" columns="4"  >
		<label>资金流向监控是否完成：</label>
		<input  class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<label>情况说明：</label>
		<input class="nui-textbox nui-form-input" > 
		<label>实际流向与计划流向是否一致：</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<label>情况说明：</label>
		<input class="nui-textbox nui-form-input" > 
		<label>实相关交易资料收集是否完成：</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<label>情况说明：</label>
		<input class="nui-textbox nui-form-input" > 
		<label>上年度末他行融资余额（万元）：</label>
		<input class="nui-textbox nui-form-input" > 
		<label>上期检查时他行融资余额（万元）：</label>
		<input class="nui-textbox nui-form-input" > 
		<label>本期检查时他行融资余额（万元）：</label>
		<input class="nui-textbox nui-form-input" > 
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">A、借款人基本情况</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）借款人主体资格、组织架构、资本构成、经营范围、实际控制人、高层管理人员等是否发生变化，且该变化是否将对我行产生不利影响</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）借款人生产、销售出现萎缩或经营出现困难等其他异常情形</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）借款人是否存在盲目多元化经营、对外投资过大导致资金链紧张或断裂的情况</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）借款人在本行或其他银行是否存在贷款本金逾期或欠息、垫款（包括保证人或第三方已代偿的）或分类下调的情况</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）借款人在他行的授信额度、余额及状态的变动以及他行对其授信方案是否变化，该变化是否将对我行产生不利影响</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）借款人或有负债的是否激增</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（7）借款人是否存在对外代偿且该代偿行为可能会对我行到期贷款本息偿付产生不利影响</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（8）借款人在他行业务是否存在退出情况</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（9）贷款到期是否存在需要展期、借新还旧，或者通过其他融资方式偿还的情况</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（10）借款人、法定代表人或实际控制人是否涉及诉讼</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（11）借款人在我行或他行账户被冻结</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（12）借款人是否存在其他影响还款来源的信号，（请具体描述，并进行定性）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
	</div>
	<label><span style="font-weight:bold;">借款人基本情况具体描述：（包括但不限于企业经营现状、企业在使用我行授信后对经营活动产生的影响、对外负债和或有负债变化情况及原因分析等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
		<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">B、借款人财务状况（必填）</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）主营收入同比下降超过30%</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）毛利率同比下降超过20%</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）净利润同比下降超过20%或亏损</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）资产负债率同比上升超过30%</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）存货同比上升超过50%</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）应收账款较上期上升超过30%且坏账较大</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（7）其他应收款较上期上升超过20%</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（8）长短期借款较上期上升超过50%</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		
	</div>
	<label><span style="font-weight:bold;">借款人财务状况具体描述：（包括但不限于本次检查周期发生的主要财务事件或异常情况、与申报授信或上年同期发生较大变化科目和数据的原因分析、财务状况整体分析判断等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">C、担保情况（必填）（动态）</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）保证人生产、销售出现萎缩或经营出现困难等其他异常情形</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）保证人财务指标是否大幅变动，或将导致担保代偿能力减弱</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）保证人是否发生重大不利事项，如涉及大额诉讼、发生大额资金损失或代偿等</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）抵押物是否出现大幅价值变动，且具有不利于我行的因素</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）房地产抵押物是否被列入动拆迁范围、发生灭失或损毁</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）抵质押物是否被有关部门查封</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（7）抵质押物的保险期限是否已过期</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（8）保证人是否存在其他影响担保代偿能力的信号，（请具体描述，并进行定性）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		
	</div>
	<label><span style="font-weight:bold;">担保情况具体描述：（包括但不限于保证人经营及变化情况、抵质押物释放、增加或变更的情况、抵质押物市场估值与授信审批时的变化情况等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">D、房地产开发贷款检查情况（选填）（动态））</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）贷款资金是否专项用于指定房产开发项目建设</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）借款人是否与我行签订财务监管协议</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）是否设定资金监管账户，贷款资金的使用及销（预）售收入、租赁收入等的资金回笼款是否通过资金监管账户进行，由我行或专业监管机构进行监管</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）是否严格按照项目工程进度进行资金划付</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）单笔金额超过项目总投资5%或超过500万元人民币的贷款资金支付，是否采用受托支付方式。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）项目实际建设完成情况、销售情况与计划是否一致</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（7）资金流向是否符合合同约定、是否与项目进度一致</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（8）资金回笼的数额、时间与实际销售进度是否一致</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（9）借款人是否根据项目实际情况合理制订还款计划，如楼盘已开始预（销）售，预（销）售回笼款是否首先归还房产开发贷款</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（10）借款人是否按约定的还款计划按期或按比例进行还款</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label>上期销售回笼资金（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>本期销售回笼资金（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>计划销售回笼资金（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
	</div>
	<label><span style="font-weight:bold;">项目开发情况具体描述：（包括但不限于项目开发进展，按计划应达到的开发进度，实际开发进度与计划是否一致，不一致的具体原因及拟采取的相应措施等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<label><span style="font-weight:bold;">销售情况具体描述：（包括但不限于销售成交量、成交价、按计划应达到的成交量、成交价，实际销售情况是否与计划一致，不一致的具体原因及拟采取的相应措施等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">E、经营性物业贷款检查情况（选填）（选填）</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）若经营性物业由借款人经营管理，经营单位是否与借款人签订《经营性物业贷款财务监管协议》，并在协议中约定经营性收入款的回笼和支出方面的监管义务。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）若经营性物业由借款人经营管理，借款人是否在经营单位开立资金监管账户，用于经营性收入款的回笼，以及贷款资金的归还。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）若经营性物业由借款人委托其他机构进行管理，借款人是否向我行提供经我行认可的委托协议，经营单位是否与借款人及受托方签订《经营性物业贷款财务监管三方协议》，并在协议中约定经营性收入款的回笼和支出方面的监管义务。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）若经营性物业由借款人委托其他机构进行管理，受托方是否在经营单位开立资金监管账户，专门用于收取物业经营性收入款等款项。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）经营性收入款是否全部进入其在经营单位开立的结算账户中，是否在约定金额的范围内专项用于归还借款。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）如分次提用贷款资金的，与借款用途有关的证明材料及支付凭证的相关要素与约定用途是否一致</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（7）单笔金额超过项目总投资5%或超过500万元人民币的贷款资金支付，是否采用受托支付方式。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（8）租金收入是否能够覆盖贷款本息；若无法全额覆盖，是否有其他补充还款来源。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（9）账户流水、水电账单等信息是否与出租率和租金价格匹配。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（10）物业整体是否持续正常经营。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label>上期租金收入（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>本期租金收入（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>计划租金收入（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
	</div>
	<label><span style="font-weight:bold;">出租情况具体描述：（包括但不限于整体出租情况、出租率、租金收入、计划出租率、计划租金收入、实际出租情况与计划是否一致，不一致的具体原因及拟采取的相应措施等:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">F、政府平台企业授信业务（选填）（动态）</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）是否按项目资金使用监管方案对资金使用进行监管。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）资金流向是否符合合同约定、是否与项目进度一致</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）是否按资金回笼监管方案对回笼资金进行监管。</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）贷款资金是否用于指定项目，是否与工程进度相对应</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）项目本身的经济效益及产生的现金流入是否能按期归还贷款本息</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）我行贷款是否纳入政府预算或偿债基金情况</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label>上期回笼资金（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>本期回笼资金（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>计划回笼资金（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
	</div>
	<label><span style="font-weight:bold;">项目进展情况具体描述：（包括但不限于项目具体进展，按计划应达到的进度，实际进度与计划是否一致，不一致的具体原因及拟采取的相应措施、政府财政收入变化对项目的影响、企业与政府的合作情况以及外部监管部门对借款企业或项目本身提出的监管意见等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="2">G、综合收益评价（必填）</label>
		<label>检查结果</label>
		<label>原因/相关措施</label>
		<label colspan="2">（1）资金回存情况是否达到既定目标（如有）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" /> 
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（2）维修基金营销情况是否达到既定目标（如有）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（3）按揭业务营销情况是否达到既定目标（如有）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（4）承租商户POS结算、网银、其他结算、信贷业务等金融服务营销情况是否达到既定目标（如有）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（5）其他交叉营销、业务联动情况等审批书要求的综合收益是否达到既定目标（如有）</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label colspan="2">（6）与借款人是否具有进一步合作机会和领域</label>
		<input class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" />
		<input  class="nui-textbox nui-form-input"/>
		<label>季日均存款（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>最近三个月存贷款情况</label>
		<input  class="nui-textbox nui-form-input"/>
		<label>年累计中间收入（万元）</label>
		<input  class="nui-textbox nui-form-input"/>
	</div>
	<label><span style="font-weight:bold;">综合收益评价具体描述（授信到期前三个月内填写）：（包括但不限于本次授信业务综合收益回顾和交叉营销方案实施执行回顾等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<div>
		<table border="1">
		  <tr>
		    <th>H、预警情况（选填）</th>
		    <th colspan="5"></th>
		  </tr>
		  <tr>
		    <td>首次预警时间</td>
		    <td><input/></td>
		    <td>首次预警级别</td>
		    <td><input/></td>
		    <td>目前预警级别</td>
		    <td><input/></td>
		  </tr>
		  <tr>
		   <td rowspan="2">预警原因 </td>
		   <td>财务信号</td>
		   <td colspan="4"><textarea cols="80"></textarea></td>
		  </tr>
		  <tr>
		   <td>非财务信号</td>
		   <td colspan="4"><textarea cols="80"></textarea></td>
		  </tr>
		  <tr>
		   <td>预案</td>
		   <td colspan="5"><textarea cols="100"></textarea></td>
		  </tr>
		  <tr>
		   <td>预案执行情况</td>
		   <td colspan="5"><textarea cols="100"></textarea></td>
		  </tr>
		</table>
	</div>
	<div  class="nui-dynpanel" columns="4"  >
		<label colspan="4">I、对借款人及担保人的整体评价（必填）</label>
		<label>项目实施情况评价：</label>
		<input class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		<label>借款人财务状况评价：</label>
		<input class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		<label>借款人履约能力评价：</label>
		<input class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
		<label>担保人代偿能力评价：</label>
		<input class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"/>
	</div>
	<label><span style="font-weight:bold;">整体评价（包括但不限于项目实施情况、借款人财务状况、借款人履约能力和担保人代偿能力等）:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
	<label><span style="font-weight:bold;">上述评价勾选为“关注”或“恶化”的，除整体评价外需另行详细说明原因和相关措施:</span></label>
	<div>
	 <input class="nui-textarea nui-form-input"  style="width:100%;"/>
	</div>
</body>
<script type="text/javascript">
	var Genders = [{ id: 'y', text: '是' }, { id: 'n', text: '否'}];
	var callback="<%=request.getParameter("callback") %>";
	var inspectWayCd = [
				{id:"1",text:"现场检查"},
				{id:"2",text:"约见检查"},
				{id:"3",text:"其他检查方式"}
			];
	var receptionType = [
				{id:"1",text:"企业接待人员"},
				{id:"2",text:"实际控制人或主要股东"},
				{id:"3",text:"财务管理人员"},
				{id:"4",text:"其他"}
			];
	
	nui.parse();
	//git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	var form = new nui.Form("#editform");
	var grid=nui.get("datagrid1");
	var grid2=nui.get("datagrid2");
	var grid3=nui.get("datagrid3");
	var condition=new nui.Form("#condition");
	var finaceCondition=new nui.Form("#finaceCondition");
	var form1=new nui.Form("#form1");
	var param=<%=request.getParameter("param") %>;
	var processInstId=<%=request.getParameter("processInstId") %>;
	//查询客户信息。
	function queryCorp(){
		var json=nui.encode({"param":param});
        $.ajax({
            url: "com.bos.aft.aft_small_inspect.querySmbInspectDetail.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	form.setData(mydata.smbInspectDetail);
            	if(nui.get("inspectWayCd").getValue()!=""){
            		hidepopup();
            		var inspectform=new nui.Form("#inspectform");
            		inspectform.setData(mydata.smbInspectDetail);
            	}
            	condition.setData(mydata.smbInspectDetail);
            	form1.setData(mydata.smbInspectDetail);
            	finaceCondition.setData(mydata.smbInspectDetail);
            	nui.get("acrossSaleCon").setValue(mydata.smbInspectDetail.acrossSaleCon);
            	nui.get("otherImportantProblem").setValue(mydata.smbInspectDetail.otherImportantProblem);
            	grid.loadData(mydata.smbConOuts1);
            	grid2.loadData(mydata.smbConOuts2);
            	grid3.loadData(mydata.smbConOuts3);
            
                git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
        });
	}
	
//	queryCorp();
	function hidepopup(){/* 检查方式改变时发生 */
			var inspectWayCd = nui.get("inspectWayCd").getValue();/* 客户编号 */
			var htm1="<div columns='4' class='nui-dynpanel' id='inspectform'>"+
					"<label >检查地点：</label>"+
					"<input name='inspectPlace' id='inspectPlace' class='nui-textbox nui-form-input'/>"+ 
					"<label>接待人员类型：</label>"+
					"<input name='receptionTypeCd' id='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010'/> "+
					"</div>";
			var htm2="<div columns='4' class='nui-dynpanel' id='inspectform'>"+
					"<label>接待人员类型：</label>"+
					"<input name='receptionTypeCd' id='receptionTypeCd' class='nui-dictcombobox nui-form-input' dictTypeId='XD_DHCD0010'/> "+
					"</div>";
			var htm3="<div columns='4' class='nui-dynpanel' id='inspectform'>"+
					"<label>其他检查方式：</label>"+
					"<input name='otherInspectWay' id='otherInspectWay' class='nui-textbox nui-form-input'/> "+
					"</div>";
		    if(inspectWayCd==1){
		    	$("#inspectMess").html(htm1);
		    	if(callback=="y"){
		    		$(".nui-textbox").attr("allowInput","false");
		    		$(".nui-dictcombobox").attr("enabled","false");
					$(".nui-combobox").attr("enabled","false");
		    	}
		    	nui.parse($('#inspectMess'));
		    }else if(inspectWayCd==2){
		    	$("#inspectMess").html(htm2);
		    	if(callback=="y"){
		    		$(".nui-textbox").attr("allowInput","false");
		    		$(".nui-dictcombobox").attr("enabled","false");
					$(".nui-combobox").attr("enabled","false");
		    	}
		    	nui.parse($('#inspectMess'));
		    }else if(inspectWayCd==3){
		    	$("#inspectMess").html(htm3);
		    	if(callback=="y"){
		    		$(".nui-textbox").attr("allowInput","false");
		    		$(".nui-dictcombobox").attr("enabled","false");
					$(".nui-combobox").attr("enabled","false");
		    	}
		    	nui.parse($('#inspectMess'));
		    }
			
      }	
	function btnAdd(a){
			
		    if(a==1){
		    	var index=grid.totalCount;
		    	var newRow = { name: "New Row" };
		    	grid.addRow(newRow, index);
		    }else if(a==2){
		    	var index2=grid2.totalCount;
		    	var newRow2 = { name: "New Row" };
		    	grid2.addRow(newRow2, index2);
		    }else if(a==3){
		    	var index3=grid3.totalCount;
		    	var newRow3 = { name: "New Row" };
		    	grid3.addRow(newRow3, index3);
		    }
            
		}
	function btnSave(){
		nui.get("btnSave").setEnabled(false);
		if(nui.get("inspectWayCd").getValue()==""){
			alert("请选择检查方式！");
			nui.get("btnSave").setEnabled(true);
			return;
		}
		var inspectform=new nui.Form("#inspectform");
		inspectformData=inspectform.getData();
		formData=form.getData();
		conditionData=condition.getData();
		form1Data=form1.getData();
		finaceConditionData=finaceCondition.getData();
		gridData=grid.findRows();
		grid2Data=grid2.findRows();
		grid3Data=grid3.findRows();
		var acrossSaleCon=nui.get("acrossSaleCon").getValue();
		var otherImportantProblem=nui.get("otherImportantProblem").getValue();
		var json=nui.encode({"param":param,"acrossSaleCon":acrossSaleCon,"otherImportantProblem":otherImportantProblem,"inspectformData":inspectformData,"formData":formData,"conditionData":conditionData,"form1Data":form1Data,"finaceConditionData":finaceConditionData,"gridData":gridData,"grid2Data":grid2Data,"grid3Data":grid3Data,"processInstId":processInstId});
		$.ajax({
            url: "com.bos.aft.aft_small_inspect.updateSmbInspectDetail.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	
            	alert(mydata.msg);
            	nui.get("btnSave").setEnabled(true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    nui.get("btnSave").setEnabled(true);
            }
        });	
	}
	 function valuechanged(){
			var pfPropertyAdjust = nui.get("pfPropertyAdjust");
			var pfAdjustType = nui.get("pfAdjustType");
			var pfAdjustReason = nui.get("pfAdjustReason");
			var i = pfPropertyAdjust.getValue();
			if(i == 1){
				pfAdjustType.setEnabled(true);
				pfAdjustReason.setEnabled(true);
			}else{
				pfAdjustType.setEnabled(false);
				pfAdjustReason.setEnabled(false);
				pfAdjustType.setValue("");
				pfAdjustReason.setValue("");
			}
		}
	function clickDownload(){
		document.getElementById('x').src="com.bos.aft.aft_small_inspect.downloadSmallBusiReport.biz.ext2?smId="+param.smId+"&partyId="+param.partyId+"&processId="+processInstId;
		return;
	}
</script>
</html>