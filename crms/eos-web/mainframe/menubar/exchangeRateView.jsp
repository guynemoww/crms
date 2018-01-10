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
<div id="grid1" class="nui-datagrid" style="width:100%;height:100%" 
	url="com.bos.pub.exchangeRate.getTbBatchMoneyexchangeList.biz.ext"
	dataField="tbBatchMoneyexchanges"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="6" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="fromCurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">外币币种</div>
		<div field="toCurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">本币币种</div>
		<div field="exchangedate" headerAlign="center" allowSort="true" >汇率日期</div>
		<div field="midexchangerate" headerAlign="center" allowSort="true" >中间汇率</div>
		<div field="createtime" headerAlign="center" allowSort="true" >创建时间</div>
		<div field="updatetime" headerAlign="center" allowSort="true" >更新时间</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	
    function search() {
        grid.load();
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
