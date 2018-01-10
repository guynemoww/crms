<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-06
  - Description:TB_AFT_AFTER_LOAN_INFO, com.bos.dataset.aft.TbAftAfterLoanInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	 
	<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	  <legend>
    	     <span>提示信息查询条件设置</span>
      </legend>
      <div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;border:none"">
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input id="partyName" name="partyName" class="nui-textbox nui-form-input" required="false" style="width:160px" />
			
			<label>提示信息状态：</label>
			<input id="infoStatus" name="infoStatus" class="nui-combobox nui-form-input" textField="text" valueField="id" required="false" style="width:160px" />
			
			<label>提示日期：</label>
			<div>
				<input id="infoDtStart" name="infoDtStart" required="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />至
				<input id="infoDtEnd" name="infoDtEnd" required="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:160px" />
			</div>
	
			<label>提示类型代码：</label>
			<input id="infoTypeCd" name="infoTypeCd" required="false" class="nui-dictcombobox nui-form-input" onitemclick="valueChange()" style="width:160px" dictTypeId="XD_DHCD0015"/>
			
			<label id="label" style="display: none;">离贷后检查截止日期剩余天数：</label>
			<input id="dayNum" name="dayNum" class="nui-textbox nui-form-input" required="false" dataType="int" style="width:160px;display:none;" />
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	  
	</div>
	</fieldset>
	<fieldset style="margin-top: 10px;margin-bottom: 10px;">
 	<legend>
    	     <span>提示信息列表</span>
    </legend>
	<div style="width:99.5%;height:auto;margin-top:10px">
		<a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-edit" onclick="view()">查看</a>
	</div>
	<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;margin-top:5px" 
		url="com.bos.pub.loanAfter.selectAfterLoanInfoList.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="infoDt" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >提示日期</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true"  >客户名称</div>
			<div field="infoTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0015">提示类型代码</div>
			<div field="infoComment" headerAlign="center" allowSort="true" >提示信息</div>
			<div field="infoStatus" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0014">提示信息状态</div>
			<div field="afterLoanInfoId" visible="false" headerAlign="center">贷后提示信息ID</div>
		</div>
	</div> 
	</fieldset>
<script type="text/javascript">
 	nui.parse();
 	var zt = [{id : 0 ,text : '新建' },{id : 1 ,text : '已阅' }];
 	nui.get("infoStatus").setData(zt);
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var flag = "01";
	var infoTypeCd = "<%=request.getParameter("infoTypeCd") %>";
	if(infoTypeCd!="null"){
		nui.get("infoTypeCd").setValue(infoTypeCd);
		nui.get("infoTypeCd").setEnabled(false);
		var data=form.getData();
		grid.load({data:data,flag:flag});
	}else{
		grid.on("preload",function(e){//客户信息链接
	   		if (!e.data || e.data.length < 1)
	   			return;
	   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	   			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
	   				+ e.data[i].partyId
	   				+ '\');return false;" value="'
	   				+ e.data[i].partyId
	   				+ '">'+e.data[i]['partyName'] +'</a>';
	   		}
	    });
	  	grid.load();  
	}
	
	

	
    
    
    
    function valueChange(){
    	var a = nui.get("infoTypeCd").getValue();
    	if(a=="01"){
    		document.getElementById("label").style.display="block";
    		document.getElementById("dayNum").style.display="block";
    	}else{
    		document.getElementById("label").style.display="none";
    		document.getElementById("dayNum").style.display="none";
    		nui.get("dayNum").setValue("");
    	}
    }
    
    function clickCust(partyId){//客户信息链接
	    var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId;
        git.go(infourl,parent);
	}
    function search() {
		var data = form.getData();       //获取表单多个控件的数据
		//var json = nui.encode({"data":data});
		grid.load({data:data,flag:flag});
		/*nui.ajax({
            url: "com.bos.pub.loanAfter.selectAfterLoanInfoList.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if (text.msg) {
            		nui.alert(text.msg);
            		
            	}else{
            		var o = nui.decode(text);
            		grid.setData(o.items);
            	}
            },
            error: function () {
            	nui.alert("操作失败！");
            }
        });*/
    }
    //search();
    
    function reset(){
		form.reset();
		document.getElementById("label").style.display="none";
    	document.getElementById("dayNum").style.display="none";
	}
	
	function view(){
		var row=grid.getSelected();
		if(row){
			nui.open({
                url: "pub/loanAfter/loanAfterView.jsp?afterLoanInfoId="+row.afterLoanInfoId,
                title: "", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                   
                },
                ondestroy: function (action) {
                	search();
                }
            });
		}else{
			alert("请选中一条记录");
		}
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
                url: "item_edit.jsp?afterLoanInfoId="+row.afterLoanInfoId+"&view="+v,
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
            	var json=nui.encode({"tbAftAfterLoanInfo":{"afterLoanInfoId":
            		row.afterLoanInfoId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbAftAfterLoanInfo.biz.ext",
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

</script>
</body>
</html>
