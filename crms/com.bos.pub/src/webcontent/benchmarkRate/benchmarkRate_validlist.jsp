<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-11-28
  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysBenchmarkRate" class="nui-hidden" />
	<input name="item.benchmarkRateId" id="item.benchmarkRateId" class="nui-hidden" />
		<h3>基准利率参数</h3>
	<div class="nui-dynpanel" style="text-align:center;padding-top:5px;padding-bottom:5px;" columns="4">
		<label>生效日期：</label>
		<input name="tbSysBenchmarkRate.validDate" required="false" class="nui-DatePicker nui-form-input"  />
	</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" onclick="statusNormal()">生效</a>
	<a class="nui-button" onclick="statusAbNormal()">失效</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.TbSysBenchmarkRate.queryTbSysBenchmarkRateList.biz.ext" dataField="tbSysBenchmarkRates"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<%--<div field="rateType" headerAlign="center" allowSort="true" dictTypeId="RateType">利率类型</div>
		<div field="rateDescription" headerAlign="center" allowSort="true" >利率描述</div>
		<div field="rateNum" headerAlign="center" allowSort="true" >利率数值</div>
		<div field="rateTerm" headerAlign="center" allowSort="true" >利率期限</div>
		<div field="rateTermUnit" headerAlign="center" allowSort="true" dictTypeId="RateTermUnit">利率期限单位</div>
		<div field="rateEffectDate" headerAlign="center" allowSort="true" >生效日期</div>--%>
		<div field="intRateCd" headerAlign="center" allowSort="true" >利率编号</div>
		<div field="intRateName" headerAlign="center" allowSort="true" >利率名称</div>
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="intRate" headerAlign="center" allowSort="true" >利率值</div>
		<div field="status" headerAlign="center" allowSort="true"  dictTypeId="XD_GGCD2012">利率状态</div>
		<div field="validDate" headerAlign="center" allowSort="true" dictTypeId="RateTermUnit">生效日期</div>
		<div field="invalidDate" headerAlign="center" allowSort="true" >失效日期</div>
		<div field="dataDate" headerAlign="center" allowSort="true" >数据日期</div>
		
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
			search();
		}
	   function statusNormal() {
    	var row = grid.getSelected();
    	if (!row) {
    		alert("请选中一条记录");
    		return;
    	}
    	<%--if (row.rstatus != '02') {
    		alert("只有状态为“未生效”的记录能进行生效操作！");
    		return;
    	}--%>
    	var data=nui.clone(row);
    	data.status='01';
    	var json=nui.encode({tbSysBasicRate:data});
    	$.ajax({
            url: "com.bos.pub.TbSysBenchmarkRate.updateTbSysBenchmarkRate.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		  grid.reload();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
    }
    
    function statusAbNormal() {
    	var row = grid.getSelected();
    	if (!row) {
    		alert("请选中一条记录");
    		return;
    	}
    	<%--if (row.rstatus != '01') {
    		alert("只有状态为“生效”的记录能进行失效操作！");
    		return;
    	}--%>
    	var data=nui.clone(row);
    	data.status='02';
    	var json=nui.encode({tbSysBasicRate:data});
    	$.ajax({
            url: "com.bos.pub.TbSysBenchmarkRate.updateTbSysBenchmarkRate.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		search();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
    }
	    
    

</script>
</body>
</html>
