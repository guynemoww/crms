<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div><h5>预警管理->新增预警信号</h5></div>
   
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" /><input type="hidden" name="item._entity" value="com.bos.dataset.cls.TbRewCsmSignalList" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4" style="text-align:center;">
		<label>客户名称</label>
		<input name="item.csmSignalId" required="false"  class="nui-text nui-form-input"  />

		<label>客户编号</label>
		<input name="item.csmEarlyWarningId" required="false" class="nui-text nui-form-input"  />

		<label>授信额度</label>
		<input name="item.earlyWarningSignalId" required="false" class="nui-text nui-form-input"  />

		<label>授信余额</label>
		<input name="item.signalSourceCd" required="false" class="nui-text nui-form-input"  />

		<label>客户等级</label>
		<input name="item.launchDate" required="false" class="nui-text nui-form-input" />

		<label>最新分类</label>
		<input name="item.confirmDate" required="false" class="nui-text nui-form-input" />

		<label>预警级别</label>
		<input name="item.signalStatusCd" required="false" class="nui-text nui-form-input" />

		<label>认定日期</label>
		<input name="item.signalState" required="false" class="nui-text nui-form-input" />

	</div>
</div>

<div><h5>已有预警信号列表</h5></div>
<div  id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="" dataField="items"
	allowResize="true" sortMode="client" showPager="false">

	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="csmSignalId" headerAlign="center" allowSort="true" >预警信号</div>
		<div field="csmEarlyWarningId" headerAlign="center" allowSort="true" >预警信号类别</div>
		<div field="signalSourceCd" headerAlign="center" allowSort="true" >预警信号来源</div>
		<div field="launchDate" headerAlign="center" allowSort="true" >信号发起日期</div>
		<div field="confirmDate" headerAlign="center" allowSort="true" >信号认定日期</div>
		<div field="signalStatusCd" headerAlign="center" allowSort="true" >信号状态</div>
		<div field="signalState" headerAlign="center" allowSort="true" >预警详情</div>
	</div>
</div>
		
<div><h5>新增预警信号</h5></div>

<div id="form2"style="width:100%;height:auto;overflow:hidden;">
     <table style="border:0px solid black;width:100%;">
     <tr>
        <td style="border:1px solid black;width:10%;text-align:center;">
		  <a>预警信号说明</a>
		</td>
		<td style="border:1px solid black;width:50%;">
		  <div class="nui-dynpanel" columns="1"style="text-align:left;">
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="抵质押物市场价格大幅下降、担保价值不足，变现能力降低" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="借款人对外担保超过公司净资产" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="贷款资金违规流入股市、期市、房地产等" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="还款来源没有落实" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="他行贷款分类下调至次级（含）以下的" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="以非正式途径或不合理的条件取得其他融资" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="公司因涉诉导致账户被冻结、资产被查封" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="季节性贷款需求变化无常" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="董事会人员、主要股东、高级管理人员发生变动" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="主要关联交易方或贸易伙伴经营发生重大问题" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="有多次签发空头支票、向银行透支或其支票被退还的现象发生" onvaluechanged="onValueChanged"></div>
			    <div id="ck1" name="product" class="nui-checkbox" checked="true" readOnly="false" text="未办理工商年检、税务登记、贷款卡年检等" onvaluechanged="onValueChanged"></div>
		  </div>
		</td>
		<td style="border:1px solid black;width:40%;">  
		  <div class="nui-dynpanel" columns="1" style="width:auto;text-align:center;">
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
            <input id="" class="nui-textbox nui-form-input"/>
         
		  </div>
		 </td>
		 </tr>
     </table>
     
			<div>预警事项描述（包括预警信息、来源渠道、程度等）</div>
            <input class="nui-textarea nui-form-input" style="width:100%;"/>
            
			<div>拟采取的控制措施及建议</div>
            <input class="nui-textarea nui-form-input" style="width:100%;"/>
            
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">提交</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">取消</a>
</div>
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		form.setEnabled(false);
       

	</script>
</body>
</html>
