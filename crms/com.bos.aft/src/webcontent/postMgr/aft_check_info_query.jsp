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

 <fieldset>
  	<legend>
    	<span>贷后检查查询条件</span>
    </legend>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		
		<label>客户编号</label>
		<input name="partyNum"  required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
        
        <label>客户名称</label>
		<input name="partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
	
        <label id="tConfirmDate">查询检查起始日期</label>
		<input id="startDate" name="startDt" required="false" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd" />
        
        <label>经办行名称</label>
		<input name="orgNum"  required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
        
        <label id="tConfirmDate">查询检查终止日期</label>
		<input id="endDate" name="endDt" required="false" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd" />        
		
		<label>查询类型</label>
	    <input class="nui-combobox nui-form-input" id="queryType" emptyText="--请选择--" />
	</div>
	
</div>

<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
     borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="queryCheckInfo">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</fieldset>

 <fieldset>
  	<legend>
    	<span>贷后检查情况列表</span>
    </legend>
<a class="nui-button" iconCls="icon-search" onclick="queryCheckReportInfo">检查报告查询</a>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.aft.common.queryAftLoanInfo.biz.ext"    
	 dataField="results" allowResize="true" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" 
	 pageSize="15" sortMode="client">
	<div property="columns">
	<div type="checkcolumn">选择</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="orgNum" headerAlign="center" allowSort="true"  dictTypeId="org" >经办行名称</div>
		<div field="friorCheckDt" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >最近检查日期</div>
		<div field="nextCheckDt" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >规定检查截止日期</div>
		<div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004">最新预警级别</div>
		<div field="setRate" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0001">检查频率</div>
		<div field="clsResult" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >分类级别</div> 
	</div>
</div>
 </fieldset>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var type="<%=request.getParameter("type") %>";
	//grid.load({type:type});                                       //加载预警客户信息
	var countrys=[{ "id": "1", "text": "按时完成" },{ "id": "2", "text": "未按时完成" }];
	nui.get("queryType").setData(countrys);
	function queryCheckInfo(){
	   var queryType = nui.get("queryType").value;
	   if(queryType==1){
	     search(1);
	   }else if(queryType==2){
	     search();
	   }else{
	     alert("请选择查询类型！");
	   }
	}
	
    function search(e) {
        git.mask();                                                  //页面遮罩
	    var warnBizInfo = form.getData();                            //获取表单多个控件的数据
        var rslt=e;
        grid.load({param:warnBizInfo,isOver:rslt});
		git.unmask(); 
	}
    search();
    function reset(){
        git.mask();                                              //页面遮罩
		form.reset();                                            //表单重置 
		git.unmask();                                            //取消页面遮罩
	}
    
    function edit() {
        git.mask();                                              //页面遮罩
        var row = grid.getSelected();
       // var rule;                                                //判断是否察看权
        
        if (row) {                                                         //if(row.userPlacingCd==3){rule=1; 为管护权时该客户不能作后续的修改 }
           var url=nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid="+row.partyId+"&rule="+3;
           git.go(url);
        } else {
            alert("请选中一条记录");
        }
        git.unmask();
    }
    
    function queryCheckReportInfo(){
        git.mask();                                                        //页面遮罩
        var row = grid.getSelected();
       // var rule;                                                        //判断是否察看权
        
        if (row) {                                                         //if(row.userPlacingCd==3){rule=1; 为管护权时该客户不能作后续的修改 }
           var url=nui.context+"/aft/postMgr/aft_corpCheckInfo_history.jsp?partyId="+row.partyId;
           //git.go(url);
           nui.open({
	            url:url,
	            showMaxButton: true,
	            title: "贷后检查报告查询",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
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
 