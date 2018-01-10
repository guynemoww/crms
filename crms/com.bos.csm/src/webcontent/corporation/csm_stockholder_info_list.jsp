<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): wangyanli
  - Date: 2013-11-18 16:54:20
  - Description:
-->
<head>
<title>注册资本信息</title>
</head>
<body>
<input name="tbCsmStockholderInfo.partyId" id="tbCsmStockholderInfo.partyId" class="nui-hidden"/>
<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit"  class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.corporation.Stockholder.getTbCsmStockholderInfoList.biz.ext" dataField="tbCsmStockholderInfos"
	allowResize="true" showReloadButton="false" allowAlternating="true" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn">选择</div>
		<div field="stockeholderName" headerAlign="center" allowSort="true">股东名称</div>
		<div field="stockholderTypeCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0034">股东类型</div>
		<div field="currecyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">出资币种</div>
		<div field="currencyAmt" headerAlign="center" allowSort="true" dataType="currency" >货币金额</div>
		<div field="realObjectAmt" headerAlign="center" allowSort="true" dataType="currency" >实物金额</div>
		<div field="intangibleAssetsAmt" headerAlign="center" allowSort="true" dataType="currency" >无形资产金额</div>
		<div field="otherAmt" headerAlign="center" allowSort="true" dataType="currency" >其他金额</div>
		<div field="actualInvestmentAmt" headerAlign="center" allowSort="true" dataType="currency" >实际出资金额</div>
		<div field="applyRegisterAmt" headerAlign="center" allowSort="true" dataType="currency" >申请注册金额</div>
		<div field="totalRegisterAmt" headerAlign="center" allowSort="true" dataType="currency" >总注册资本</div>
		<div field="totalSharesPercent" headerAlign="center" allowSort="true" >占股比例（%）</div>
		<div field="investmentInPlacePercent" headerAlign="center" allowSort="true" >投资到位率（%）</div>
	</div>
</div>

<script type="text/javascript">
 	nui.parse();
 	git.mask();
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		
	if(qote==1){
	   nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}	
	if (partyId) {
		nui.get("tbCsmStockholderInfo.partyId").setValue(partyId);
	}
	
    function search() {
		//var data = form.getData(); //获取表单多个控件的数据
        grid.load({"partyId":partyId});
        git.unmask();
    }
    search();	
    
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_stockholder_info_add.jsp?partyId=" + partyId,
            title: "新增", 
            width: 800, 
        	height: 550,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    git.mask();
                    search();
                }
            }
        });
    }
        
    function edit(v) {
        var row = grid.getSelected();
        var title="";
        if(v==1){
        	title = "查看";
        }else{
        	title = "编辑"
        }
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_stockholder_info_edit.jsp?itemId="+row.stockholderId+"&qote="+v+"&partyId="+partyId,
                title: title, 
                width: 800,
        		height: 550,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
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
            	git.mask();
            	var json=nui.encode({"tcrc":row});
                $.ajax({
                     url: "com.bos.csm.corporation.Stockholder.delTbCsmStockholderInfo.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        git.mask();
                        search();
                    },
                    error: function () {
                    	git.unmask();
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }

</script>
</body>
</html>