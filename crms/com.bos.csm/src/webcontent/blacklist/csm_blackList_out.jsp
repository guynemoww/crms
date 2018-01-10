<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
总行级重点目标客户，转入
 -->
<head>
<title>全行重点及目标客户列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
	
	<div class="nui-dynpanel"  columns="6">
		<label>客户名称：</label>
		<input id="item.partyName"  name="item.partyName" required="false" class="nui-textbox nui-form-input"  />

		<label>客户编号：</label>
		<input id="item.partyNum" name="item.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>ECIF客户编号：</label>
		<input id="item.ecifPartyNum"  name="item.ecifPartyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

	</div>
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:none" >
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset">重置</a>
</div>
</div>



<div style="width:99.5%;margin-top:10px">
	<div class="nui-toolbar" style="border-bottom:left;text-align:left;">
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="removeIn">转入</a>
	</div>
</div>	

	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.blacklist.blacklist.queryBlackCustOutList.biz.ext" dataField="items"
	    allowAlternating="true" showEmptyText="true"  multiSelect="true"  allowCellEdit="true"
	    emptyText="没有查到数据" showReloadButton="false" 
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    
	    <div property="columns" >
	        <div type="checkcolumn" >选择</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="EcifPartyNum" headerAlign="center" allowSort="true" >ECIF客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >CRMS客户名称</div>
			<div field="contryRegionCd" headerAlign="center" allowSort="true" dictTypeId="CD000003">国家和地区</div>
			<div field="blackListReasonCd" headerAlign="center" dictTypeId="CDKH0010" allowSort="true" >进入黑名单原因
			<input property="editor" required="true" class="nui-dictcombobox" dictTypeId="CDKH0010"  />
		</div>
		<div field="interceptParam" headerAlign="center" dictTypeId="XD_KHCD0250" allowSort="true" >黑名单控制参数
			<input property="editor" required="true" class="nui-dictcombobox" dictTypeId="XD_KHCD0250"  />
		</div>
	     </div>
	 </div>
</center>
</body>
<script type="text/javascript">
	//初始化nui
	nui.parse();
	
	//获取form对象
	var form = new nui.Form("#form1");
	//获取列表对象
	var grid = nui.get("datagrid1");
	//查询事件
	function query(){//黑名单客户查询
		if(checkQueryConditions()== "true"){
			alert("必须输入一个查询条件搜索!");
			return;
		}
       var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		git.unmask();
	}

	
	//重置查询条件
	function reset() {
		form.setData({});
	}

	
	//新增一条记录
	function removeIn() {
              
        	var rows = grid.getSelecteds();
            for(var i=0; i<rows.length;i++){
				if( !rows[i].blackListReasonCd ){//|| !rows[i].interceptParam
					alert("请填写黑名单原因和控制参数!");
					return;
				}
            }
            
            if (rows.length > 0) {
        	nui.confirm("确定转入名单吗？","确认",function(action){
            	if(action!="ok") return;
            		git.mask();
            	var json=nui.encode({"rows":rows});
                $.ajax({
                     url: "com.bos.csm.blacklist.blacklist.insertBlackCusts.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		alert(text.msg);
                    		return;
                    	}else{
							if(text.flag){
								var node = text.node;
								openSubmitView(node);
							}else{
								alert("移入成功！");
							}
                    	}
                        git.mask();
                        query();
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
        
        
//验证查询条件必输有一条
    function checkQueryConditions(){
    	var partyName = nui.get("item.partyName").getValue()==""?null:nui.get("item.partyName").getValue() ;
    	var partyNum = nui.get("item.partyNum").getValue()==""?null:nui.get("item.partyNum").getValue() ;
    	var ecifPartyNum = nui.get("item.ecifPartyNum").getValue()==""?null:nui.get("item.ecifPartyNum").getValue() ;
    	var flag = "false";
    	if(null == partyName  && null == partyNum && null == ecifPartyNum){
    		flag = "true";
    	}
    	return flag;
    }
	
	
</script>
</html>