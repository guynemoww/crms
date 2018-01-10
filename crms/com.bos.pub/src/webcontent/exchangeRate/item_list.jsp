<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-08
  - Description:TB_BATCH_MONEYEXCHANGE, com.bos.pub.sys.TbBatchMoneyexchange
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		<label>买入汇率：</label>
		<input name="tbBatchMoneyexchange.buyexchangerate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>币种：</label>
		<input name="tbBatchMoneyexchange.currency" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:18" onbuttonclick="selectCurrency" />

		<label>中间汇率：</label>
		<input name="tbBatchMoneyexchange.midexchangerate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>卖出汇率：</label>
		<input name="tbBatchMoneyexchange.sellexchangerate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>

	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.exchangeRate.getTbBatchMoneyexchangeList.biz.ext"
	dataField="tbBatchMoneyexchanges"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="buyexchangerate" headerAlign="center" allowSort="true" >买入汇率</div>
		<div field="currency" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="exchangedate" headerAlign="center" allowSort="true" >汇率日期</div>
		<div field="midexchangerate" headerAlign="center" allowSort="true" >中间汇率</div>
		<div field="ratetype" headerAlign="center" allowSort="true" >汇率类型</div>
		<div field="sellexchangerate" headerAlign="center" allowSort="true" >卖出汇率</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
	function selectCurrency(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CD000001",
            title: "选择字典项",
            width: 300,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}

	</script>
</body>
</html>
