<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 叶奔
  - Date: 2014-05-23 10:51:45
  - Description:
-->
<head>
<title>Title</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
    <div class="nui-dynpanel" columns="4">
        <label for="isteam$text">业务性质：</label>
		<input id="biz.bizType" name="biz.bizType" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1038"/>
	</div>	
	<div class="nui-toolbar" style="text-align:left;"borderStyle="border:0;">
	     <a class="nui-button" id="btnSearch" iconCls="icon-search" onclick="search()">查询</a>
    </div>
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;margin-bottom:20px;margin-top: 30px;"  sortMode="client"
	    url="com.bos.biz.bizApplyUtil.getCustomerBusiness.biz.ext" dataField="businesses"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"  
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true" 
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	    	<div type="checkcolumn">选择</div>
			<div type="indexcolumn">序号</div>
	    	<div field="partyName" headerAlign="center">客户名称</div>                                        
			<div field="bizType" headerAlign="center" dictTypeId="XD_SXCD1038">业务性质</div>   
			<div field="bizNum" headerAlign="center" >业务编号</div>  
			<div field="createTime" headerAlign="center" dateFormat="yyyy-MM-dd">批复日</div>
			<div field="creditTotalExposure" headerAlign="center" dataType ="currency">批复金额</div>
			<div field="becomeEffectiveMark" headerAlign="center" dictTypeId="XD_SXCD8009">生效标识</div>  
			<div field="approveConclusion" headerAlign="center" dictTypeId="XD_WFCD0002">审批结论</div>  
			        
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    	<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="save">查看</a>
	</div>
	<br>
</center>
	<script type="text/javascript">
		nui.parse();
		var partyId="<%=request.getParameter("partyId") %>";//客户id
		var BizApply=null;
		var grid = nui.get("datagrid1");
		function search(){
		 var bizType = nui.get("biz.bizType").getValue(); 
		  if(bizType!=null&&bizType!=""&&bizType!="null"&&typeof(bizType)!="undefined"){
		    
		  
		 if(partyId!=null&&partyId!=""&&partyId!="null"){
             
        	 grid.load({"partyId":partyId,"bizType":bizType});
         }
        }else{
          alert("请选择查询条件查询！");
          
        }
		}
       // grid.load({"applyId":""});
        //初始化方法列表数据
       
        
        function save(){
        	BizApply = grid.getSelected();
        	if (!BizApply) {
				nui.alert("请选择一条记录");
				return;
			}
            nui.open({
	                url: nui.context + "/biz/biz_notice_approval/notice_approval_credit.jsp?applyId="+BizApply.applyId,
	                title: "查看批复信息", 
	                width: 800,
	        		height: 600,
	        		state:"max",
	                allowResize:true,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    this.max();
	                    //iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
	                }
	            })
        
        } 
        
        
	</script>
</body>
</html>