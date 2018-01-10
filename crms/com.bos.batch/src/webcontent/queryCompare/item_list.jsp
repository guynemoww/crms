<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-08-25
  - Description:TB_BATCH_DAYEND_CRMS, TbBatchDayendCrms
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="实时接口交易日终对账查询" >
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
	    <label>借据号：</label>
		<input name="map/loanNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
		
		<label>放款核准单号：</label>
		<input name="map/detailno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>渠道：</label>
		<input name="map/channels" required="false" class="nui-combobox nui-form-input" vtype="maxLength:10" data="con"/>
        
        <label>放款支付类型：</label>
        <input name="map/payoutType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
          data="data" valueField="dictID" textField="dictName" dictTypeId="XD_SXCD1178"/>
		
		<label>当前记录状态：</label>
		<input name="map/currecordstate" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
          data="data" valueField="dictID" textField="dictName" dictTypeId="XD_FHCD1008"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:5px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit">导出Excel表格</a>--%>
	</div>
</form>
<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;;margin-top:7px">
		<!-- 
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">新增</a>
		<a id = "query" class="nui-button" iconCls="icon-node" onclick="query()">查看</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		<a id="smallCorp" class="nui-button" iconCls="icon-upload" onclick="smallCorpIdentify(0)">发起小企业认定</a>
		 -->
	</div>
</div>				

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.queryCompare.getTbBatchDayendCrmsList.biz.ext"
	dataField="items" allowAlternating="true"
	allowResize="true" showReloadButton="false"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="loanNum" headerAlign="center" allowSort="true" >借据号</div>
		<div field="detailno" headerAlign="center" allowSort="true" >放款核准单号</div>
		<div field="channelsName" headerAlign="center" allowSort="true" >渠道</div>
	    <div field="payoutType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1178" >放款支付类型</div>
		<div field="crmsBalance" headerAlign="center" allowSort="true" >crms放款金额</div>
		<div field="channelsBalance" headerAlign="center" allowSort="true" >渠道放款金额</div>
		<div field="currecordstate" headerAlign="center" allowSort="true" dictTypeId="XD_FHCD1008">当前记录状态</div>
		<div field="inputdate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >数据日期</div>
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
    var con = [{ id: 1, text: '票据' }, { id: 2, text: 'T24'}];
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
	
    function add() {
        nui.open({
            url: "item_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "item_edit.jsp?serialno="+row.serialno+"&view="+v,
                title: "编辑", 
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
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbBatchDayendCrms":{"serialno":
            		row.serialno,version:row.version}});
                $.ajax({
                     url: "com.bos.batch.queryCompare.delTbBatchDayendCrms.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
	       //导出
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.loanNum != null) return true;
		});
		
		if(rows != null && rows.length > 0) {//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.bos.batch.batchTest.accountManager.flow?_eosFlowAction=exportFile&importCd=21";
			forms.submit();
		} else {
			alert('没有要导出的记录');
		}
    }
	</script>
</body>
</html>
