<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-05-31 12:42:24
  - Description:
-->
<head>
<title></title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item._entity" value="com.bos.dataset.csm.TbCsmManagementTeam" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<div  id="crud" class="nui-toolbar" style="border:0;text-align:left;">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="save" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>
 
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items"  allowAlternating="true"
	allowResize="true" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
	sizeList="[10,15,20,50,50,100]" multiSelect="true"  oncellbeginedit="oncellbeginedit"  pageSize="15" sortMode="client">
	<div property="columns">
		<div type="indexcolumn">序号</div>
		<div field="userNum" headerAlign="center"  class="nui-buttonEdit"   allowSort="true" >用户编号</div>
		<div field="userNum" headerAlign="center" class="nui-buttonEdit"  dictTypeId="user" allowSort="true" >用户名称</div>
		<div field="userPlacingCd" headerAlign="center" dictTypeId="XD_KHCD0187" allowSort="true" >管理权限</div>
		<div  field="orgNum" headerAlign="center"  class="nui-buttonEdit" dictTypeId="org" allowSort="true" >所在机构名称</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var selectedFlag = false;	
	  nui.get("crud").hide();
	  nui.get('datagrid1').setAllowCellSelect(false);
	   
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
			
		}
		
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        	//复制数据到userName
        	var data = grid.getData();
        	for(var i=0;i<data.length;i++){
        		data[i].userName = data[i].userNum;
        		//_alert(data[i].userName+"--"+data[i].userNum);
        		
        	}
        	//重新放入Data到Grid中
        	grid.setData(data);
        	//_alert(nui.encode(data));
        });
     	git.unmask();
     }
     init();
	
      	function add() {            
            var row = { name: "New Row" , partyId: partyId ,userPlacingCd:"02"}
            grid.addRow(row, 0);
        }
    
    var keyFalg = false;
	//判断是否输入
	function copyText(){
		keyFalg = true;
		this.value = "";
	}
	
function save(){
		if(selectedFlag==false){
			alert("请查询用户");
			return;
		}
		var data = {groupOrgs:grid.getChanges()};
		for(var i=0; i<data.groupOrgs.length; i++){
				var d = data.groupOrgs[i];
				if( !d.userNum || !d.userPlacingCd){
					alert("请将信息填写完整");
					return;
				}
			}
			
		var json = nui.encode(data);
		git.mask();
		nui.ajax({
            url: "com.bos.csm.corporation.ManageTeam.insertTeam.biz.ext",
            type: 'POST',
            data: json,
            success: function (text) {
                git.unmask();
            	if(text.msg){
            		alert(text.msg);
            		return;
            	} else {
            		alert("保存成功!");
            		init();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
	}
	
	
	   function remove() {
        var rows = grid.getSelecteds();
        var flag = true;
        for(var i = 0;i<rows.length;i++){
        	if(rows[i]["userPlacingCd"]=="01"){
        		alert("管户权不可删除");
        		return;
        	}
        	
        }
        
        
        if (rows.length > 0) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"teams":rows});
                $.ajax({
                     url: "com.bos.csm.corporation.ManageTeam.delTeams.biz.ext",
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
                         init();
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
    
    
    function oncellbeginedit(e) {
    	var datagrid2=nui.get('datagrid1');
        var cells = datagrid2.getCurrentCell();
        var row = cells[0];
        console.log(row.userPlacingCd);
        if(row.userPlacingCd=="01") {
				e.cancel=true;
		}
    	return;
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
	            url: nui.context + "/pub/orgDemolition/creditMove/select_all_org_tree.jsp",
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
	                        url = "/csm/group/groupCust_teamInfo_queryCust.jsp?orgNum="+data.orgcode+"&orgName="+git.toUrlParam(data.orgname);
	                        selectUser(url,btnEdit);
	                       
	                    }
	                }
	                return;
	            }
	        });   
        }
        	
      }
	
	function selectUser(url,btnEdit){
		nui.open({
            url: nui.context + url,
            showMaxButton: true,
            title: "选择客户经理",
            width: 800,
            height: 500,
            state:"max",
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                        var datagrid2=nui.get('datagrid1');
        				var cells = datagrid2.getCurrentCell();
        				var row = cells[0];
        				datagrid2.commitEdit();
                        datagrid2.updateRow(row, {userNum:data.userId,userName:data.userId, orgNum:data.orgCode});
                        selectedFlag=true;
                    }
                }
            }
        });   
	}
</script>
	
	
</body>
</html>