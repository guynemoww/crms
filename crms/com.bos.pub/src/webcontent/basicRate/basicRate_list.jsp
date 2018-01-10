<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-19
  - Description:TB_SYS_BASIC_RATE, com.bos.pub.sys.TbSysBasicRate
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>币种：</label>
		<input name="tbSysBasicRate.currencyCd" required="false" class="nui-buttonEdit" onbuttonclick="selectCurrency"  vtype="maxLength:3" />

		<label>利率名称：</label>
		<input name="tbSysBasicRate.intRateCd" required="false" class="nui-buttonEdit" vtype="maxLength:200" onbuttonclick="selectIntRateName"/>

		<label>生效日期：</label>
		<input name="tbSysBasicRate.validDate" required="false" class="nui-datepicker nui-form-input" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.basicRate.getTbSysBasicRateList.biz.ext"
	dataField="tbSysBasicRates"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="intRateCd" headerAlign="center" allowSort="true" dictTypeId="XD_GG29382">利率名称</div>
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="intRate" headerAlign="center" allowSort="true" >利率值</div>
		<div field="intRateCd" headerAlign="center" allowSort="true" >利率编号</div>
		<div field="dataDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >数据日期</div>
		<div field="invalidDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >日期</div>
		<div field="validDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >生效日期</div>
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
	function selectIntRateName(){
	    var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_GG29382",
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
