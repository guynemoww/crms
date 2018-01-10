<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-05-06 12:42:24
  - Description:
-->
<head>
<title>信用信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1"style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.natural.natural.creditList" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>"/>

	<div id="crud"  class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>  
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(1)">编辑</a> 
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	     url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,15,20,50,100]"   pageSize="15">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyName" allowSort="true" headerAlign="center"width="15%">客户名称</div>
	        <div field="infoSrc" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD2008">记录来源</div>
	        <div field="queryDate" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd" >查询日期</div>
 	        <div field="djkLxyqTimes"name="djkLxyqTimes" allowSort="true"  headerAlign="center" dictTypeId="XD_KHCD0255">贷记卡及准贷记卡连续逾期次数</div> 
 	    	<div field="djkLjyqTimes"name="djkLjyqTimes" allowSort="true"  headerAlign="center" dictTypeId="XD_KHCD0256">贷记卡及准贷记卡累计逾期次数</div> 
  	        <div field="dkLxqxTimes" allowSort="true"  headerAlign="center" dictTypeId="XD_KHCD0255">贷款连续欠息次数</div>
  	        <div field="dkLjqxTimes" allowSort="true"  headerAlign="center" dictTypeId="XD_KHCD0256">贷款累计欠息次数</div>
  	        <div field="sxbjYqCondition" allowSort="true"  headerAlign="center" dictTypeId="HaveOrNot">授信本金逾期情况</div>
	     </div>
	</div>

<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var partyTypeCd= "<%=request.getParameter("partyTypeCd")%>" ;

	if(qote==1){
		nui.get("add").hide();
		nui.get("edit").hide();
		nui.get("remove").hide();
	}
		
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        if(partyTypeCd=="01"){
			grid.hideColumn(grid.getColumn("djkLxyqTimes"));
			grid.hideColumn(grid.getColumn("djkLjyqTimes"));
		}else{
			grid.showColumn(grid.getColumn("djkLxyqTimes"));
			grid.showColumn(grid.getColumn("djkLjyqTimes"));
		}
		git.unmask();
     }
     init();
	
	function add() {
            nui.open({
                url:nui.context + "/csm/natural/natural_credit_info.jsp?partyId="+partyId+"&partyTypeCd="+partyTypeCd,
                title: "新增", 
                width: 1000, 
            	height: 330,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
                    }
                }
            });
        }
	
	function edit(e) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/natural/natural_credit_info.jsp?id="+row.id+"&qote=1&partyTypeCd="+partyTypeCd,
                title: "查看编辑", 
                width: 1000,
        		height: 330,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
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
            	var json=nui.encode({"item":{"id":
            		row.id,
					"_entity":"com.bos.dataset.csm.TbCsmCreditInfo"}});
                $.ajax({
                     url: "com.bos.csm.pub.crudCustInfo.delItem.biz.ext",
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