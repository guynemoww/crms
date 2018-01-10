<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>资金流向监控情况1</title>
</head>
<body>
<div style="padding: 10px;">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="corp.partyName"  class="nui-text nui-form-input" />
	
			<label>所属行业：</label>
			<input name="corp.industrialTypeCd"  class="nui-text nui-form-input" dictTypeId="XD_KHCD0092"/>
	
			<label>贷款账户：</label>
			<input name="corp.loanCardNum"  class="nui-text nui-form-input" />
			
			<label>存款账户：</label>
			<input name="corp.ecifPartyNum"  class="nui-text nui-form-input"  />
			
			<label>授信额度：</label>
			<input name="corp.creditExposure"  class="nui-text nui-form-input"  />
	
			<label>授信余额：</label>
			<input name="corp.balanceExposure" class="nui-text nui-form-input"  />
	
			<label>分行：</label>
			<input name="" class="nui-text nui-form-input"  dictTypeId="org"/>
	
			<label>主办支行：</label>
			<input name="corp.orgNum" class="nui-text nui-form-input" dictTypeId="org"/>
			
			<input id="corp.partyId" class="nui-hidden nui-form-input" name="corp.partyId"/>
		</div>
	</div>
	<div style="padding-top: 10px;font-weight:bold;">放款记录1</div>
	<div id="datagrid1" class="nui-datagrid" dataField="capitalFlows" url="com.bos.aft.aft_capital_flows.getCapitalFlows.biz.ext" 
         allowCellEdit="true" allowResize="true" allowCellSelect="true" >
        <div property="columns"> 
            <div type="indexcolumn">序号</div>
            <div field="contractNum" width="40px">合同编号</div>
			<div field="loanDt" width="40px">放款日期</div>
			<div field="loanAmt" width="40px">放款金额</div>
			<div field="payee" width="40px">收款人</div>
			<div field="isCollected" width="40px">已收集凭证</div>
			<div field="isActualUse" width="40px">实际用途与审批一致</div>
			<div field="notActualUseComment" cellStyle="background:#fceee2;">不一致说明
				<input property="editor" class="nui-textarea nui-form-input" required="false" />
			</div>
			<!--  <div field="checkState" width="40px" data="zt">状态</div>-->
		</div>
	</div>
</div>
	<script type="text/javascript">
		nui.parse();
		git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
		
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
        var partyId="<%=request.getParameter("corpId") %>";
		
		function query(){/* 数据加载 */
		    git.mask();
			var json = nui.encode({"item/partyId":"8b81bef0458845550145887b91f60038"});
		    nui.ajax({
                url: "com.bos.aft.aft_capital_flows.queryCustomerInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text);
                	grid.load({"item/partyId":"8b81bef0458845550145887b91f60038","item/status":"1"});
                	git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });      			
		}
		query();

		
		
		
		function btnRevoke(){/* 撤销 */
		     query();        //重刷新
		}
	    
	    var zt = [
			{id:"01",text:"已完成"},
			{id:"02",text:"未完成"}
		];
		

	</script>
</body>
