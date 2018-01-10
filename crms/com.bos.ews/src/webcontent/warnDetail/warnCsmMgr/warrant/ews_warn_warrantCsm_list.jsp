<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>查询担保客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	 <div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	     <div class="nui-dynpanel" columns="6">
		    <label>客户名称</label>
		    <input name="partyName" class="nui-textbox nui-form-input" vtype="maxLength:64"/>
		
		    <label>客户编号</label>
		    <input name="partyNum" class="nui-textbox nui-form-input"/>
		 </div>
		 <div class="nui-toolbar" style="text-align:right;margin-top: 7px;">
             <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
         </div>
	</div>

<fieldset>
  	<legend>
    	<span>客户列表</span>
    </legend>

	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" sortMode="client" url="com.bos.ews.queryCsm.queryWarrantCsm.biz.ext" 
	     dataField="csmWarnings" allowAlternating="true" multiSelect="false" sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyName" allowSort="true" width="20%" headerAlign="center" >客户名称</div> 
	        <div field="partyNum" allowSort="true" width="" headerAlign="center">客户编号</div>
	        <div field="orgNum" allowSort="true" width="20%" headerAlign="center" dictTypeId="org" >所属经办机构</div>
	        <div field="userNum" allowSort="true" width="20%" headerAlign="center" dictTypeId="user" >所属经办人</div>       
	    </div>
	  
	</div>
</fieldset>	
    <div class="nui-toolbar" style="text-align:right;margin-top: 7px;">
         <a class="nui-button" iconCls="icon-add" onclick="addWarnInfo()">新增信号</a>
    </div>

<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var type = "<%=request.getParameter("type") %>";
	                                               //表单遮罩效果
	
	function query(){
	   git.mask();                                                     //表单遮罩
       var corp = form.getData();                              //逻辑流必须返回total
       grid.load({corp:corp});                                                   //加载客户数据
       git.unmask();                                                   //加载数据完成取消遮罩
	}
    query();

//新增预警信号
function  addWarnInfo(){
        var bizId;
        git.mask();                                                    //表单遮罩
		var row=grid.getSelected();                                    //取得所选客户
		if (null == row) {
			nui.alert("请选择一条记录");
			git.unmask();
			return;
		}
  var json = nui.encode({partyId:row.partyId});                                   //赋值参与人ID
  $.ajax({      
            url: "com.bos.ews.commonUtil.checkWarnAdjust.biz.ext",           //该逻辑流用于查询是否存在未用的业务ID，如果不存在则新建ID用于后续操作中作为业务ID使用
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	     if(text.msg){
                         alert(text.msg);                                   //出现错误，提示信息
                     }else{
                         bizId = text.bizId; 
                         var node = text.node;
            	         var url=nui.context+"/ews/warnDetail/warnTree/ews_warnInfo_tree_add.jsp?corpid="+row.partyId+"&bizId="+bizId+"&type="+type+"&node="+nui.encode(node);
                         git.go(url);
                     }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                
            }
	});
}   
	
</script>
</body>
</html>