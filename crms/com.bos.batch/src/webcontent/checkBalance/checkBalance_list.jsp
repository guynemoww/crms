<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/nui/common.jsp" %>
<title>信贷账务数据总分核对</title>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="总分对账查询" >
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>总账科目号：</label>
		<input name="map/subjectno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>机构：</label>
		<input name="map/errordescribe" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>币种：</label>
		<input name="map/businesscurrency" required="false"  
		class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
		data="data" valueField="dictID" textField="dictName" dictTypeId="CD000001"/>
		
		<label>发生日期：</label>
		<input name="map/occurdate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />
		<!-- 
		<label>余额类型：</label>
		<input name="tbBatchCheckBalance.balancetype" required="false" 
		class="nui-dictcombobox nui-form-input" vtype="maxLength:10" 
		data="data" valueField="dictID" textField="dictName"  dictTypeId="XD_RZCD0009"/>
		 -->
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:5px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	<%--	<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit">导出Excel表格(F2)</a>--%>
	</div>
 </form>
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" style="margin-left:5px" iconCls="icon-node" onclick="edit(1)">查看</a>
	</div>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.checkResultQry.getTbBatchCheckBalanceList.biz.ext"
	dataField="tbBatchCheckBalances" allowAlternating="true" 
	allowResize="false" showReloadButton="false"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="occurdate" headerAlign="center" allowSort="true">发生日期</div>
		<div field="duesubjectno" headerAlign="center" allowSort="true">借据表科目</div>
		<div field="subjectno" headerAlign="center" allowSort="true" >总账科目号</div>
		<div field="subjectname" headerAlign="center" allowSort="true" >科目名称</div>
		<div field="orgName" headerAlign="center" allowSort="true">机构</div>
		<div field="businesscurrency" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="mfbalance" headerAlign="center" allowSort="true" dataType="currency">总账余额</div>
		<div field="balance" headerAlign="center" allowSort="true" dataType="currency">借据余额</div>
		<!-- 
		<div field="balancetype" headerAlign="center" allowSort="true" dictTypeId="XD_RZCD0009">余额类型</div>
		 -->
		</div>
	</div>
	</center>
	</div>
	</div>		
    <script type="text/javascript">
    /**快捷键设置*/
    document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==27){ // 按 Esc 
                //要做的事情
                
              }
            if(e && e.keyCode==113){ // 按 F2 
                 //导出Excel表格(F2)
                exportEmp();
               }    
            if(e && e.keyCode==46){ // 按 Delete
                 //要做的事情
               }           
             if(e && e.keyCode==13){ // enter 键
                 //要做的事情
            }
        };
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
		//alert("1");
        grid.load(data);
        //alert("2");        
    }
    
    search();
    
    function reset(){
		form.reset();
	}

    function edit(v) {
        var row = grid.getSelected();
     
        if (row) {
            nui.open({
                url: encodeURI(nui.context+"/batch/checkBalance/busduebill_list.jsp?subjectno="+row.subjectno+
                		"&orgNum="+row.orgNum+
                		"&businesscurrency="+row.businesscurrency+
                		"&subjectname="+row.subjectname+
                		"&balancetype="+row.balancetype+
                		"&view="+v),
                title: "查看", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                   
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    //导出
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.serialno != null) return true;
		});
		
		if(rows != null && rows.length > 0) {//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=24";
			forms.submit();
		} else {
			alert('没有要导出的记录');
		}
    }
    

	</script>
</body>
</html>
