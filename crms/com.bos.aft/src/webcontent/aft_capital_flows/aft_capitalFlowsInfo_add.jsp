<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>资金流向监控情况</title>
</head>
<body>
<div style="padding: 10px;">
<fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>基本信息</span>
             </legend>	
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="corp.partyName"  class="nui-text nui-form-input" />
	
			<label>所属行业：</label>
			<input name="corp.industrialTypeCd"  class="nui-text nui-form-input" dictTypeId="XD_KHCD0092"/>
	
			<label>贷款卡号：</label>
			<input name="corp.loanCardNum"  class="nui-text nui-form-input" />
			
			<label>ECIF编号：</label>
			<input name="corp.ecifPartyNum"  class="nui-text nui-form-input"  />
			
			<label>授信额度：</label>
			<input name="corp.creditExposure"  class="nui-text nui-form-input"  />
	
			<label>授信余额：</label>
			<input name="corp.balanceExposure" class="nui-text nui-form-input"  />
	
			<label>主办行上级机构：</label>
			<input name="corp.parentOrgId" class="nui-text nui-form-input"  dictTypeId="org"/>
	
			<label>主办行：</label>
			<input name="corp.orgNum" class="nui-text nui-form-input" dictTypeId="org"/>
			
			<input id="corp.partyId" class="nui-hidden nui-form-input" name="corp.partyId"/>
		</div>
	</div>
</fieldset>

<fieldset style="margin-top: 10px;">
  	         <legend>
    	              <span>放款记录</span>
             </legend>	
    <div class="" style="border-bottom:0;text-align:left;margin-top: 20px;">
			 <a class="nui-button" iconCls="icon-ok" onclick="btnSubmit()">提交</a>
			 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">临时保存</a>
			 <a class="nui-button" iconCls="icon-remove" onclick="btnRevoke()">撤销</a>
	</div>
	<div id="datagrid1" class="nui-datagrid" dataField="capitalFlows" url="com.bos.aft.aft_capital_flows.queryFlowsInfo.biz.ext" onrowclick="check" 
	     multiSelect="true" oncellendedit="check" allowCellEdit="true" allowCellSelect="true"  oncellbeginedit="oncellbeginedit">
        <div property="columns"> 
            <div type="checkcolumn"> </div>
            <div field="contractNum" width="200px">合同编号</div>
			<div field="tradeDate" width="200px">支付日期</div>
			<div field="tradeAmt" width="80px">支付金额</div>
			<div field="accountNo" width="70px">收款方账户编号</div>
			<div field="accountName" width="70px">收款方账户名称</div>
			<div field="openingBankNo" width="70px">收款方账户开户行号</div>
			<div field="openingBankName" width="70px">收款方账户开户行名</div>
			<div field="isCollected" type="dictcomboboxcolumn" displayFiled="text" dictTypeId="YesOrNo" cellStyle="background:#fceee2;" width="80px">已收集凭证
				<input  property="editor"  class="nui-dictcombobox"  dictTypeId="YesOrNo" />
			</div>
			<div field="isActualUse" type="dictcomboboxcolumn" dictTypeId="YesOrNo" cellStyle="background:#fceee2;" width="80px">实际用途与审批一致
				<input property="editor" class="nui-dictcombobox"  required="true"  dictTypeId="YesOrNo"/>
			</div>
			<div field="notActualUseComment" cellStyle="background:#fceee2;">不一致说明
				<input property="editor" class="nui-textarea nui-form-input" required="true" />
			</div>
			<div field="checkState"  type="comboboxcolumn"  cellStyle="background:#fceee2;" width="80px">状态
				<input property="editor" class="nui-combobox nui-form-input"  required="true" data="zt" />
			</div>
		</div>
	</div>
</fieldset>	
		
	
</div>
	<script type="text/javascript">
		nui.parse();
		git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
		
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
        var partyId="<%=request.getParameter("corpid") %>";
        
		
		function query(){/* 数据加载 */
		    git.mask();
			var json = nui.encode({"item/partyId":partyId});
		    nui.ajax({
                url: "com.bos.aft.aft_capital_flows.queryCustomerInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text);
                	grid.load({"item/partyId":partyId,"item/yesOrNo":"1"});
                	git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });      			
		}
		query();

		
		function btnSubmit(){/* 提交 */
		        git.mask();
		        var res;
		        grid.findRows(
		           function(row){
			            if(grid.isSelected(row)==true){
			               if(row.checkState !="01"){                //未填写完成信息，表单不能提交。
			                res=1;
			                return;
			               }
			            }
                   });
            if(res==1){
            alert("填写未完整！");
            git.unmask();
			return;
            }
            var rows = grid.getSelecteds();
            if(rows.length==0){                                       //没有选中项，表单不提交。
               alert("请选择！");
               git.unmask();
			   return;
            }
            var json = nui.encode({"capitalFlows":rows,"partyId":nui.get("corp.partyId").getValue()});
		    nui.ajax({
                url: "com.bos.aft.aft_capital_flows.startCapitalFlow.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                	git.unmask();
                	var node=text.node;//弹出审批页面
                	openSubmitView(node);
                	
                }
            });   
		}
		
		function btnSave(){/* 临时保存 */
		    git.mask();
		        grid.findRows(
		           function(row){
			            if(grid.isSelected(row)==true){
			               if(row.checkState !="01"){                //未填写完成信息，表单不能提交。
			                alert("填写未完整！");
			                git.unmask();
			                return;
			               }
			            }
                   });
            var rows = grid.getSelecteds();
            if(rows.length==0){                                       //没有选中项，表单不提交。
               alert("请选择！");
               git.unmask();
			   return;
            }
            var json = nui.encode({"capitalFlows":rows});
		    nui.ajax({
                url: "com.bos.aft.aft_capital_flows.updateCapitalFlows.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                	git.unmask();
                }
            });   
		}
		
		function btnRevoke(){/* 撤销 */
		     query();        //重刷新
		}
		
		function oncellbeginedit(e){/* 是否输入说明 */
		    var record = e.record;
            var field = e.field;
            if (field == "notActualUseComment" && record.isActualUse == "1") {
                e.cancel = true;    //如果实际用途与审批不一致，说明：
            }
		}
		
		//输入校验
        function check(e){
           var record = e.record;
           if(record.notActualUseComment!= null){
            var sm = record.notActualUseComment;
           }
          
          
           if(record.isCollected == null || record.isActualUse == null||record.isCollected == " " || record.isActualUse == " "){      //判断选择项是否为空
               grid.updateRow(record, {checkState:"02"});
			   return;
		   }else{
                if(record.isActualUse =="2" && record.notActualUseComment == null){ // 判断说明项是否为空
                   grid.updateRow(record, {checkState:"02"});
                   return;
		        }else if(record.isActualUse =="2" && sm.length == 0 ){
		           grid.updateRow(record, {checkState:"02"});
                   return;
		        }else{
		           grid.updateRow(record, {checkState:"01"});
		           return;
		   }
		   }
           
        }
        
 		
		var zt = [
			{id:"01",text:"已完成"},
			{id:"02",text:"未完成"}
		];
		
		

	</script>
</body>
