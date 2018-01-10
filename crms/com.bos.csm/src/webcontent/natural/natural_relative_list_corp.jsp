<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-06 12:42:24
  - Description:
-->
<head>
<title>自然人关联关系</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.natural.natural.relativeOrgList" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" value="<%=request.getParameter("partyId") %>"/>
	<input name="cType" id="cType" class="nui-hidden" value="<%=request.getParameter("cType") %>"/>
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
	<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>  
	<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(1)">编辑</a>
	<a id="see" class="nui-button" iconCls="icon-node" onclick="edit(0)">查看</a>  
	<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a></div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	     url="com.bos.csm.pub.ibatis.getNaturalPersonRelative.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div type="indexcolumn" allowSort="true"  headerAlign="center" >序号</div>
	        <div field="partyName" allowSort="true"  headerAlign="center" width="15%">关系人名称</div>
	        <div field="partyTypeCd" allowSort="true" dictTypeId="XD_KHCD1001" headerAlign="center" >客户类型</div>
	        <div field="certType" id="certType1"  name="certType1" allowSort="true"  headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
	        <div field="certNum" allowSort="true"  headerAlign="center" >证件号码</div>
	        <div field="appellation" allowSort="true" headerAlign="center" dictTypeId="XD_GLGX0002">关联关系</div>
	        <div field="partnerCompany" allowSort="true" headerAlign="center" >工作单位</div>
	        <div field="partnerPhoneNum" allowSort="true" headerAlign="center" >联系电话</div>
	        <div field="remark" allowSort="true" headerAlign="center" >备注</div>
	     </div>
	</div>
<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var cType = "<%=request.getParameter("cType")%>" ;
	
	grid.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return ;
		}
	});
		if(qote==1){
		   nui.get("add").hide();
		   nui.get("remove").hide();
		   nui.get("edit").hide();
		}
		
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		if(qote==1){
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        }else{//修改时调ECIF
        var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);	
        }
		git.unmask();
     }
     init();
	function add() {
            nui.open({
                url:nui.context + "/csm/natural/natural_relative_add.jsp?partyId="+partyId+"&remark="+'01'+"&cType="+cType,
                title: "新增", 
                width: 800, 
            	height: 400,
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
                url: nui.context + "/csm/natural/natural_relative_org_edit.jsp?naturalRelativeId="+row.naturalRelativeId+"&appellation="+row.appellation+"&qote="+e+"&partyId="+partyId+"&partyTypeCd="+row.partyTypeCd,
                title: "查看编辑", 
                width: 800,
        		height: 400,
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
      //删除关联人信息  
      function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"item":{"naturalRelativeId":row.naturalRelativeId}});
                $.ajax({
                     url: "com.bos.csm.pub.crudCustInfo.delNaturalPsnRelative.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}else{
                    	 init();
                    	}
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
    
    function query(e) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/natural/natural_info.jsp?partyId="+row.partyId+"&qote=1",
                title: "查看编辑", 
                width: 800,
        		height: 500,
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
	
</script>
	
	
</body>
</html>