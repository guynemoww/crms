<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-08-26
  - Description:TB_BATCH_CHECK_HB_WB, TbBatchCheckHbWb
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="信贷台账与T24流水对账查询" >
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
	     
		<label>借据号：</label>
		<input name="map/dueSerialno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
	   
		<label>余额类型：</label>
		<input name="map/balancetype" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />

		<label>币种：</label>
          <input name="map/currency" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3"
				dictTypeId="CD000001"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:5px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit">导出Excel表格</a>--%>
	</div>
</form>
   
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.queryCheckHbWb.getTbBatchCheckHbWbList.biz.ext"
	dataField="items" allowAlternating="true"
	allowResize="true" showReloadButton="false"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="dueSerialno" headerAlign="center" allowSort="true" >借据号</div>
		<div field="balancetype" headerAlign="center" allowSort="true" >余额类型</div>
		<div field="currency" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="hbBalance" headerAlign="center" allowSort="true" >台账发生额</div>
	    <div field="wbBalance" headerAlign="center" allowSort="true" >T24流水发生额</div>
        <div  field="orgname" headerAlign="center"   allowSort="true" >经办机构</div>
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
        alert(row.serialno);
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
            	var json=nui.encode({"tbBatchCheckHbWb":{"serialno":
            		row.serialno,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbBatchCheckHbWb.biz.ext",
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
    
    
    	function selectCustManeger(type) {
        var btnEdit = this;
        var url;
        if(type=="1"){
        	url = "/csm/group/groupCust_teamInfo_queryCust.jsp?userNum="+this.value;
        	selectUser(url,btnEdit);
        	return;
        }else if(type=="2"){
        	//不可包含数字
        	<%--var str = this.value;
        	for(var i = 0;i<str.length;i++){
        		if(str[i].indexOf("1234567890")){
        			alert("输入值不可包含数字");
        			return;
        		}
        	}--%>	
        	if(keyFalg==true&&this.value){
        		url = "/csm/group/groupCust_teamInfo_queryCust.jsp?userName="+git.toUrlParam(this.value);
        		keyFalg==false;
        	}else{
        		alert("若以名称为查询条件,则名称不可为空");
        		return;
        	}
        	selectUser(url,btnEdit);
        	return;
        }else{
	        nui.open({
	            url: nui.context + "/pub/sys/select_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 350,
	            height: 450,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                        url = "/csm/group/groupCust_teamInfo_queryCust.jsp?orgNum="+data.orgCode+"&orgName="+git.toUrlParam(data.orgname);
	                        selectUser(url,btnEdit);
	                       
	                    }
	                }
	                return;
	            }
	        });   
        }
        	
      }
      
       //导出
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.dueSerialno != null) return true;
		});
		
		if(rows != null && rows.length > 0) {//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.bos.batch.batchTest.accountManager.flow?_eosFlowAction=exportFile&importCd=20";
			forms.submit();
		} else {
			alert('没有要导出的记录');
		}
    }
	

	</script>
</body>
</html>
