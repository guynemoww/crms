<%@page pageEncoding="UTF-8"%>
<!-- <div class="nui-fit" style="padding:5px;">
    <div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    
    <fieldset>
		<legend>
			<span>（一）贷款资金使用用途     暂空</span>
		</legend> -->
<center>
  <div id="form1" style="width:99.4%;height:auto;overflow:hidden;">
  	<input id="tbAftFirstCheck.firstCheckId" class="nui-hidden nui-form-input" name ="tbAftFirstCheck.firstCheckId" value="<%=request.getParameter("firstCheckId") %>"/>
    <div class="nui-toolbar" style="border-bottom:0;text-align: left;" id="sptjdiv">
			<a id="add5" class="nui-button" iconCls="icon-add" onclick="add('grid5')">增加</a>
			<a id="remove5"  class="nui-button" iconCls="icon-remove" onclick="remove('grid5')">删除</a>
	</div>
	<div id="grid5" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.aft.normalCheck.queryMoneyUse.biz.ext" dataField="moneyUses"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowCellEdit="true" allowCellSelect="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
		
	    <div property="columns">
	    	<div type="checkcolumn" >选择</div>
	        <div type="indexcolumn">序号</div>        
	        <div field="PAY_TIME" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">支付时间
	        	<input id="tbLoanMoneyUse.payTime" name="tbLoanMoneyUse.payTime"  property="editor" class="nui-datepicker nui-form-input" required="true"  allowInput="false"/>
	        </div>
	        <div field="APPLY_AMOUNT" allowSort="true"  headerAlign="center"  dataType="currency" vtype="maxLength:20">支付金额
	        	<input  id="tbLoanMoneyUse.applyAmount" name="tbLoanMoneyUse.applyAmount" property="editor" class="nui-textbox nui-form-input"  required="true"  dataType="currency" vtype="maxLength:20"/>
	        </div>
	        <div field="PAY_OBJECT" allowSort="true"  headerAlign="center">支付对象
	        	<input   id="tbLoanMoneyUse.payObject" name="tbLoanMoneyUse.payObject" property="editor" class="nui-textbox nui-form-input"  vtype="string;maxLength:200"/>
	        </div>
	        <div field="PAY_USE" allowSort="true"  headerAlign="center" >支付用途
	        	<input   id="tbLoanMoneyUse.payUse" name="tbLoanMoneyUse.payUse" property="editor" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/>
	        </div>
	      	<div field="PAY_WAY" allowSort="true"  headerAlign="center"  valueField="dictID" textField="dictName"  dictTypeId="XD_SXYW0218" >支付方式
	      		<input   id="tbLoanMoneyUse.payWay" name="tbLoanMoneyUse.payWay"  property="editor" class="nui-dictcombobox nui-form-input" required="true"   dictTypeId="XD_SXYW0218" allowInput="false"/>
	      	</div>
			<div field="IS_FIT_DEAL" allowSort="true"  headerAlign="center"  valueField="dictID" textField="dictName"  dictTypeId="YesOrNo" >是否符合审批或约定用途
				<input   id="tbLoanMoneyUse.isFitDeal" name="tbLoanMoneyUse.isFitDeal"  property="editor" class="nui-dictcombobox nui-form-input"  required="true"  dictTypeId="YesOrNo" allowInput="false"/>
			</div>
	     </div>
	</div>
  </div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
	</div>
</center>
<script type="text/javascript">
		nui.parse();
		var form1 = new nui.Form("#form1"); 
		var grid = nui.get("grid5");
		var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识 
		 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("add5").hide();
			nui.get("remove5").hide();
			nui.get("btnCreate").hide();
			form1.setEnabled(false);
		} 
			grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		 });	
       		 
		function create(){
				var  o1 =form1.getData();
				var o = grid.getData();
		        var json = nui.encode(o[0])+nui.encode(o1);
				$.ajax({
		            url: "com.bos.aft.normalCheck.saveMoneyUse.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            cache: false,
		            success: function (text) {
		            	if(text.msg){
		            	nui.alert(text.msg);
		            	}else{
		            	alert("保存成功!");
		            	grid.reload();
		            	}
					}
		        });
		        nui.get("btnCreate").setEnabled(true);
			}
			
			//动态列表点击新增
			function add(gr) {
		    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
		    	var row={"periodsNumber":++count};
		        nui.get(gr).addRow(row,0);
		    }
		    //动态列表删除操作
		    function remove(gr) {
		        var row = nui.get(gr).getSelected();
		        if (row) {
		        	nui.confirm("确定删除吗？","确认",function(action){
		            	if(action!="ok") return;
		            	//删除数据库数据
		            	if(row.MONEY_USE_ID){
		            		var json = nui.encode({"moneyUseId":row.MONEY_USE_ID});
			            	$.ajax({
					            url: "com.bos.payInfo.MoneyUse.delMoneyUse.biz.ext",
					            type: 'POST',
					            data: json,
					            contentType:'text/json',
					            cache: false,
					            success: function (mydata) {
					            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
					            	grid.reload();
								}
			        		});
		            	}else{
		            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
		            	}
		            });
		        } else {
		            nui.alert("请选中一条记录");
		        }
		    }
</script>


