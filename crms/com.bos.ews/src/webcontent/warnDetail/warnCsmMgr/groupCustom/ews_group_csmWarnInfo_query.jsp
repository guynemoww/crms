<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-04-10
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		<label>预警级别</label>
		<input name="earlyWarningLevelCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

        <label>客户名称</label>
		<input name="partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
	</div>
</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search(e)">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit()">查看</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnBizInfo.queryWarnBizInfo.biz.ext"    
	dataField="warnBizInfos" allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
	<div type="checkcolumn">选择</div>
		<div field="orgNum" headerAlign="center" allowSort="true"  dictTypeId="org" >机构</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="guarantyType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1020"  >担保方式</div>
		<div field="availableExposure" headerAlign="center" allowSort="true" >授信可用余额</div>
		<div field="occupiedExposure" headerAlign="center" allowSort="true" >敞口余额</div>
		<div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004">预警级别</div>
		<div field="confirmDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >预警日期</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user" >主管客户经理</div>
	</div>
</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var type="<%=request.getParameter("type") %>";
	grid.load({type:type});                                                 //加载预警客户信息
    function search(e) {
        git.mask();                                                  //页面遮罩
	    var warnBizInfo = form.getData();                        //获取表单多个控件的数据
		grid.load({warnBizInfo:warnBizInfo,type:type});
		git.unmask(); 
	}
    
    function reset(){
        git.mask();                                              //页面遮罩
		form.reset();                                            //表单重置 
		git.unmask();                                            //取消页面遮罩
	}
    
    function edit() {
        git.mask();                                              //页面遮罩
        var row = grid.getSelected();
        var rule;                                                //判断是否察看权
        
        if (row) {
           if(row.userPlacingCd==3){                              
           rule=1;                                               //为查看权权时该客户不能作后续的修改
           }
           var url=nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid="+row.partyId+"&rule="+rule;
           git.go(url);
        } else {
            alert("请选中一条记录");
        }
        
    }
    
   </script>
</body>
</html>
