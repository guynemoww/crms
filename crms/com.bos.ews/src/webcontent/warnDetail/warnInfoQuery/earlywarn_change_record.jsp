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
<!-- 
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		
		<label>客户编号</label>
		<input name="partyNum"  required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
        
        <label>客户名称</label>
		<input name="partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>机构</label>
		<input name="orgName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>预警级别</label>
		<input name="levelCd" required="false" class="nui-dictComboBox nui-form-input" dictTypeId="XD_YJCD0004" vtype="maxLength:32" />
		
	</div>
</div>
				
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
     borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search(e)">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div> -->
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-node" onclick="warnInfoDetail()">查看相关信号信息</a>
</div>

 <fieldset>
  	<legend>
    	<span>预警变更列表</span>
    </legend>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.earlyWarnChangeRecord.warnInfoQuery.biz.ext"    
	 dataField="adjusts" allowResize="true" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" 
	 pageSize="15" sortMode="client">
	<div property="columns">
	<div type="checkcolumn">选择</div>
	    <!-- <div field="changeType" headerAlign="center" allowSort="true"  dictTypeId="XD_YJLCCD001" >流程类型</div> -->
	    <div field="adjustDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >变更申请日期</div>
	    <div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user" >发起人</div>
		<div field="orgNum" headerAlign="center" allowSort="true"  dictTypeId="org" >发起机构</div>
		<!-- <div field="oldEarlyWarningLevelCd" headerAlign="center" allowSort="true"  dictTypeId="XD_YJCD0004" >客户原预警级别</div>
		<div field="earlyWarningLevelCd" headerAlign="center" allowSort="true"  dictTypeId="XD_YJCD0004" >生效的预警级别</div> -->
		<!-- <div field="matterState" headerAlign="center" allowSort="true" >预警事项描述</div>
		<div field="suggestState" headerAlign="center" allowSort="true" >拟采取的控制措施和建议</div> -->
	</div>
</div>
 </fieldset>	
    <script type="text/javascript">
 	nui.parse();
    //var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId="<%=request.getParameter("partyId") %>";             //获取参与人ID
	//grid.load({type:type});                                        //加载预警客户信息
    function search(e) {
        git.mask();                                                  //页面遮罩
	   // var warnBizInfo = form.getData();                          //获取表单多个控件的数据                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		grid.load({partyId:partyId});
		git.unmask(); 
	}
    search();
    
    function warnInfoDetail(){
        git.mask();                                              //页面遮罩
         var row = grid.getSelected();
         // var rule;                                                //判断是否察看权
         if(row){                                                   //if(row.userPlacingCd==3){rule=1; 为管护权时该客户不能作后续的修改 }
           var changeType = row.changeType;
           
           nui.open({
                url: nui.context + "/ews/warnDetail/warnInfoQuery/warnInfo_detail.jsp?bizId="+row.levelAdjustId+"&changeType="+changeType,
                title: "预警客户变更记录", 
                width: 1024,
        		height: 768,
        		state:"max",
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                },
                ondestroy: function (action) {
                }
            });
           
        } else {
            alert("请选中一条记录");
        }
        git.unmask();
    }
   </script>
</body>
</html>
