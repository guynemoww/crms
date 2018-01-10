<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-14
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6" >
		
			<label>客户编号</label>
			<input name="item.partyNum" required="false" class="nui-text nui-form-input"   />
			
			<label>客户名称</label>
			<input name="item.partyName" required="false"  class="nui-text nui-form-input"  />
			
			<label>授信额度</label>
			<input name="item.creditAmt" required="false" class="nui-text nui-form-input" dataType="currency"  />
	
			<label>授信余额</label>
			<input name="item.balance" required="false" class="nui-text nui-form-input" dataType="currency" />
	
			<label>客户等级</label>
			<input name="item.creditRatingCd" required="false" class="nui-text nui-form-input"  />
	
			<label>分类</label>
			<input id="clsResult" name="item.clsResult" required="false" class="nui-text nui-form-input" dictTypeId="XD_FLCD0001"  />
	
			<label id="tWarnLevel">预警级别</label>
			<input id="warnLevel" name="item.warningLevelCd" required="false" class="nui-text nui-form-input" dictTypeId="XD_YJJB0001"  />
	
			<div id="tConfirmDate">认定日期</div>
			<input id="confirmDate" name="item.confirmDate" required="false" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"  />
	
		</div>
		<div style="border-bottom:0;text-align:right;margin-top: 10px;">
			 <a id="addWarnInfo" class="nui-button" iconCls="icon-add" onclick="addWarnInfo()">预警信号新增/级别认定/级别上调</a>  
		     <a id="closeWarnInfo" class="nui-button" iconCls="icon-remove" onclick="addWarnInfo(1)" >预警信号关闭/级别下调</a> 
		</div>
		
	</div>

	<fieldset>
		<legend>
			<span>已有预警信号列表</span>
		</legend>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.ews.warnInfo.queryEwsInfo.biz.ext" dataField="csmWarnInfo"
			allowResize="true" showReloadButton="false" showPageSize="false" pageSize="10" multiSelect="false" sortMode="client">
		   <div property="columns">
		     <div type="indexcolumn">序号</div>
		     <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
		     <div field="csmWarningTypeId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true">预警信号类别</div>
		     <div field="signalSourceCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0001">预警信号来源</div>
		     <div field="holdDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警信号认定日期</div>
		     <div field="closeDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预警信号关闭日期</div> 
		     <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002">预警信号状态</div>
		     <div field="signalState" headerAlign="center" allowSort="true">预警信号说明</div>
		   </div>
		</div>
	<fieldset>

</body>

<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("corpid") %>";
	var rule = "<%=request.getParameter("rule") %>";
	if(rule == 3){
		 $("#addWarnInfo").css("display","none");
		 $("#closeWarnInfo").css("display","none");
	}
	function initPage() {
	     var json=nui.encode({partyId:partyId});
	     $.ajax({      
            url: "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	    var text = nui.decode(text);
            	    form.setData(text);
               		grid.load({partyId:partyId,status:"2"});
            	    
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		 });
         git.unmask();	
	}
	
	initPage(); 

	//新增预警信号
	function addWarnInfo(isClosed){
		var operateType;
		if(isClosed == 1){
			operateType="close";
		}else{
			operateType="add";
		}
	
	  var json = nui.encode({"partyId":partyId,"isClosed":isClosed});
	  $.ajax({      
            url: "com.bos.ews.commonUtil.checkWarnAdjust.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            		if(text.flag){
	                	return nui.alert(text.flag);
	                }else {
						var url = nui.context+"/ews/warnDetail/warnTree/ews_warnInfo_tree.jsp";
                        nui.open({
        					url:url+"?operateType="+operateType+"&corpid="+partyId+"&bizId="+text.bizId+"&node="+nui.encode(text.node)+"&processInstId="+text.processInstId+"&proFlag=1",
        					title:"客户预警",
        					state:"max",
        					showMaxButton:true,
        		        	allowResize:true,
        		            ondestroy: function(e) {
        		            	initPage();
        		            }
        				});
	                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
	}   

</script>
</html>
