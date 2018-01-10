<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>

<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmInvestmentInfo" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div id="crud" class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.corporation.Investment.queryTbCsmInvestmentInfo.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="investCustName" headerAlign="center" allowSort="true"width="20%" >被投资客户名称</div>
		<div field="certType" headerAlign="center" allowSort="true"   dictTypeId="CDKH0002">证件类型</div>
		<div field="orgRegisterNum" headerAlign="center" allowSort="true" >证件号码</div>
		<div field="investimentMethodCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0076">出资方式</div>
		<div field="currecyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="investmentAmt" headerAlign="center" allowSort="true"  >投资金额</div>
		<div field="shareholdingRatio" headerAlign="center" allowSort="true" >持股比例（%）</div>
		<div field="investmentDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">投资日期</div>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
		
	if(qote==1){
	   nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}
			
    function search() {
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_investment_info_add.jsp?partyId="+partyId,
            title: "新增", 
            width: 850, 
        	height: 400,
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
        var title = "编辑";
        if('1' == v){
        	title = "查看";
        }
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_investment_info_edit.jsp?investmentId="+row.investmentId+"&view="+v+"&partyId="+partyId,
                title: title, 
                width: 850,
        		height: 400,
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
            	var json=nui.encode({"invest":row});
                $.ajax({
                     url: "com.bos.csm.corporation.Investment.delTbCsmInvestmentInfo.biz.ext",
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
                        grid.reload();
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
