<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>共有人信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

		    <div class="nui-toolbar" style="border-bottom:0;width:100%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add()" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-zoomin" onclick="edit('1')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="remove">删除</a>
	</div>
		    
	<div id="grid4" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="commonName" headerAlign="center">共有人名称</div>
			<div field="certificateTypeCd" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
			<div field="certificateCode" headerAlign="center">证件号码</div>
			<div field="commonWay" headerAlign="center" dicttypeid="XD_YWDB0137">共有方式</div>
			<div field="commonLot" headerAlign="center" >共有份额（%）</div>
			<div field="createTime" headerAlign="center" dateFormat="yyyy-MM-dd">创建日期</div>
			<div field="updateTime" headerAlign="center" dateFormat="yyyy-MM-dd">更新日期</div>
		</div>
	</div>
	
	<script type="text/javascript">
 	nui.parse();
 	
 	//押品主键ID
	var suretyId ="<%=request.getParameter("suretyId")%>";
		
	var grid = nui.get("grid4");
	
	var v="<%=request.getParameter("view") %>";
	if ("1" == v){
		nui.get("add").hide();
		nui.get("edit0").hide();
		nui.get("remove").hide();
	}
	
    search();
    
    function search() {
		var json=({"item":{"suretyId":suretyId,"_entity":"com.bos.dataset.grt.TbGrtCommonPerson"}});
	    grid.load(json);
    }

	
    function add() {
    	var suretyId= '<%=request.getParameter("suretyId") %>';
        nui.open({																				
            url: nui.context+"/grt/manage/partOwner/partyOwner_add.jsp?suretyId="+suretyId,
            title: "新增", 
            width: 800, 
        	height: 400,
        	allowResize: false,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }


	function edit(v){
		var row = grid.getSelected();
        var title1;
        if(v == "0"){
        	title1 = "编辑";
        }else if(v == "1"){
        	title1 = "查看";
        }
        if (row) {			   
        	nui.open({
	            url: nui.context+"/grt/manage/partOwner/partyOwner_add.jsp?commonId="+row.commonId+"&view="+v,
	            showMaxButton: true,
	            title: title1+"共有人信息",
	            width: 800,
	            height: 400,
	            ondestroy: function(e) {
	            	search();
	            }
	        });
        } else {
            alert("请选中一条记录");
        }
	}


	function remove(){
    	var row = grid.getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var commonId=row.commonId;
            	var json=nui.encode({"item":{"commonId":commonId,"_entity":"com.bos.dataset.grt.TbGrtCommonPerson"}});
                $.ajax({
                    url: "com.bos.grt.grtManage.mortgageCURD.deleteMortgage.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
                    	search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
	      }else{
	        alert("请选中一条记录");
	      }
    }
	</script>
</body>
</html>
