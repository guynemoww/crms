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
	<input name="item.partyId" id="item.partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>"/>

	<div id="crud"  class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>  
		<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(1)">编辑</a> 
			<a id="see" class="nui-button" iconCls="icon-node" onclick="edit(2)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	     url="com.bos.csm.natural.natural.queryNaturalBusiness.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,15,20,50,100]"   pageSize="15">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="dealproj" allowSort="true" headerAlign="center"width="15%">经营项目</div>
	        <div field="dealbrand" allowSort="true" headerAlign="center">经营字号</div>
	        <div field="licetype" allowSort="true"  headerAlign="center" dictTypeId="CDKH0024">注册类型</div>
 	        <div field="comedate" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">成立日期</div> 
 	    	<div field="liceid" allowSort="true"  headerAlign="center" >营业执照号</div> 
 	    	<div field="liceaddr" allowSort="true"  headerAlign="center" >注册地址</div>
 	    	<div field="dealscope" allowSort="true"  headerAlign="center" >经营范围</div>
 	    	<div field="dealaddr" allowSort="true"  headerAlign="center" >经营场所</div>
 	    	<div field="dealaddrkind" allowSort="true"  headerAlign="center" dictTypeId="XD_JYCSXZ0001">经营场所性质</div>
 	    	<div field="yearamt" allowSort="true"  headerAlign="center" >年营业额</div>
 	    	<div field="empnum" allowSort="true"  headerAlign="center" >雇员人数</div>  
 	    	<div field="lastchanperson" dictTypeId="user" allowSort="true"  headerAlign="center" >最后修改人</div>
 	    	<div field="lastchandate" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">最后修改日期</div>
 	    	<div field="monthavesell" allowSort="true"  headerAlign="center" >月均销售额</div>
 	    	<div field="monthavecost" allowSort="true"  headerAlign="center" >月均经营费用</div>
	     </div>
	</div>

<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var partyNum = "<%=request.getParameter("partyNum") %>";
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
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		}
		git.unmask();
     }
     init();
	
	function add() {
            nui.open({
                url:nui.context + "/csm/natural/natural_business_info.jsp?partyNum="+partyNum+"&partyId="+partyId+"&qote=0",
                title: "新增", 
                width: 1000, 
            	height: 300,
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
                url: nui.context + "/csm/natural/natural_business_info.jsp?businessId="+row.businessId+"&qote="+e,
                title: "查看编辑", 
                width: 1000,
        		height: 300,
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
             	var json=nui.encode({"businessId":
            		row.businessId});
                $.ajax({
                     url: "com.bos.csm.natural.natural.deleteNaturalBusiness.biz.ext",
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